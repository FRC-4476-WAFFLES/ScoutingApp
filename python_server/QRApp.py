import pandas as pd
import json
import os
import cv2
import pyzbar.pyzbar as pyzbar
import time
import subprocess
import datavalidation as validate
import pandastable
import tkinter as tk
from PIL import ImageTk, Image
class QRApp():
    def __init__(self):
        # load header config from file
        with open("LEGEND.json") as f:
            self.header = json.load(f)

        # setup data management
        self.df = pd.DataFrame(columns=self.header)  # all competition data
        self.matchdf = pd.DataFrame(columns=self.header)  # most recent match data
        # keep track of which robots data has been submitted.
        self.submitted = [False, False, False, False, False, False]
        self.userID = ["r1", "r2", "r3", "b1", "b2", "b3"]

        # setup match verification queue
        self.toBeVerified = list()  # to be cross referenced with the online database
        self.toBEValidated = list()  # to be manually corrected.

        # OpenCV config
        self.cap = cv2.VideoCapture(0)
        self.cvFont = cv2.FONT_HERSHEY_PLAIN

        # tkinter setup
        self.root = tk.Tk()
        self.videoFeed = tk.Frame(self.root, bg="black", height=500, width=500)
        self.videoFrame = None
        self.scanSwitch = tk.Button(self.root, bg="yellow", text="Start Scanning", font=("CooperFiveOpti Black", 16),
                                    command=lambda: self.startScanning())
        self.submitted_HUD = tk.Frame(self.root, bg="white")
        self.SX = "750"
        self.SY = "500"
        self.TITLE = "Scouting App Server v2.0"


        # file save paths
        self.file_path = os.path.join(os.getcwd(), "server_comm.txt")
        self.df_out_path = os.path.join(os.getcwd(), "qr_out.csv")
        self.matchdf_out_path = os.path.join(os.getcwd(), "match_df.csv")
        self.toBeVerifiedPath = os.path.join(os.getcwd(), "toBeVerified.json")
        self.toBEValidatedPath = os.path.join(os.getcwd(), "toBeValidated.json")
        self.usbPath = r'E:/'  # Note r in front of windows path

        # logic trackers
        self.found_code = False
        self.scan = False

    def gen(self):  # remove (not necessary)
        string = ""
        for i in self.submitted:
            if i:
                tmp = "T"
            else:
                tmp = "F"
            string = string + tmp + ","
        return string

    def saveData(self):
        self.df.to_csv(self.df_out_path, index=False)
        self.matchdf.to_csv(self.matchdf_out_path, index=False)
        # use json to save the lists of dataframe strings.
        with open(self.toBEValidatedPath, "w+") as f:
            json.dump(self.toBEValidated, f)
        with open(self.toBeVerifiedPath, "w+") as f:
            json.dump(self.toBeVerified, f)
        if os.path.exists(self.usbPath):
            self.df.to_csv(self.df_out_path, index=False)

    def communicate(self):  # rename to save_state(), add saveData()
        # remove (not necessary)
        if os.path.exists(self.file_path):
            os.remove(self.file_path)
        with open(self.file_path, "w+") as f:
            f.write(self.gen())

    def checkReloadDF(self):
        # Check if there is already a saved file, if so load it.
        if os.path.exists(self.df_out_path):
            self.df = pd.read_csv(self.df_out_path)
            print("Loaded dataframe from previous save")
            # print(self.df.head())

    def checkReloadMatchDF(self):
        # Check if there is already a saved file, if so load it.
        if os.path.exists(self.matchdf_out_path):
            self.matchdf = pd.read_csv(self.matchdf_out_path)
            print("Loaded last match in progress")
            # print(self.matchdf.head())

    def restoreState(self):
        self.checkReloadDF()
        self.checkReloadMatchDF()
        collected = list(self.matchdf.Alliance.values)
        for coll in range(len(collected)):
            collected[coll] = collected[coll].lower()
        for i in range(len(self.userID)):
            if self.userID[i] in collected:
                self.submitted[i] = True
        self.communicate()
        # load validation and verification queues.
        if os.path.exists(self.toBEValidatedPath):
            with open(self.toBEValidatedPath, "r+") as f:
                self.toBEValidated = json.load(f)
        if os.path.exists(self.toBeVerifiedPath):
            with open(self.toBeVerifiedPath, "r+") as f:
                self.toBeVerified = json.load(f)

    def setup(self):  # do all tkinter graphics in here, use callbacks for running things.
        self.root.geometry(self.SX + "x" + self.SY)
        self.root.title(self.TITLE)
        self.root.resizable(False, False)
        self.videoFeed.pack(side="left", anchor="nw")
        self.scanSwitch.pack(side="left", anchor="ne")

    def startScanning(self):
        self.scan = True
        self.scanSwitch.config(command=self.stopScanning, text="Stop Scanning")

    def stopScanning(self):
        self.scan = False
        self.scanSwitch.config(command=self.startScanning, text="Start Scanning")

    def run(self):
        if self.scan and not self.found_code:
            self.runQRCapture()
        elif self.scan and self.found_code:
            self.found_code = False
            self.stopScanning()
        self.root.after(10, self.run)

    def runQRCapture(self):
        _, frame = self.cap.read()

        decodedObjects = pyzbar.decode(frame)
        for obj in decodedObjects:
            print("Data: ", obj.data)
            data = obj.data
            data = data.decode("utf-8")
            # make sure we don't accidentally try to read a qrcode that isn't a qrcode
            if not data == "" and 28 < len(data.split(",")):
                if self.userID.count(data[-1].lower()) < 1 and not self.submitted[self.userID.index(data[-2:].lower())]:
                    # Split the qrcode data into a list
                    data = data.replace("[", "").replace("]", "").split(",")
                    user = data[-1]
                    data = data[:28]
                    self.matchdf = self.matchdf.append(pd.DataFrame(data=[data], columns=self.header), ignore_index=True)
                    self.saveData()
                    # look to see what user just submitted their data
                    for i in range(len(self.userID)):
                        if self.userID[i] == user.lower():
                            self.submitted[i] = True
                    # tell the dashboard what to do
                    self.communicate()
                    # only read one QRcode at a time (end the search loop)
                    self.found_code = True
            #			window   text	   loc(l, t) font  size colour(BGR) thckness
            cv2.putText(frame, str(obj.data), (50, 50), self.font, 3, (255, 0, 0), 3)
            allTrue = all(i for i in self.submitted)
            if allTrue:
                self.submitted = [False, False, False, False, False, False]
                self.addToVerifyQueue()

        image = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
        image = Image.fromarray(image)
        image.thumbnail((500, 500), Image.ANTIALIAS)
        image = ImageTk.PhotoImage(image)
        if self.videoFrame == None:
            self.videoFrame = tk.Label(self.videoFeed, image=image)
            self.videoFrame.image = image
            self.videoFrame.pack(padx=5, pady=5)
        else:
            self.videoFrame.config(image=image)
            self.videoFrame.image = image

    def addToVerifyQueue(self):
        self.toBeVerified.append(self.matchdf.to_string())
        self.matchdf = pd.DataFrame(columns=self.header)
        #################################### make a loop to check these, then add them to the queue to be shown



### main Program ###
server = QRApp()
root = server.root
server.restoreState()
print("server started")
server.setup()
server.run()
server.root.mainloop()
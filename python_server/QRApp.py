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

        # OpenCV config
        self.cap = cv2.VideoCapture(0)
        self.cvFont = cv2.FONT_HERSHEY_PLAIN

        # tkinter setup
        self.root = tk.Tk()
        self.SX = "500"
        self.SY = "500"
        self.TITLE = "Scouting App Server v2.0"

        # file save paths
        self.file_path = os.path.join(os.getcwd(), "server_comm.txt")
        self.df_out_path = os.path.join(os.getcwd(), "qr_out.csv")
        self.matchdf_out_path = os.path.join(os.getcwd(), "match_df.csv")
        self.usbPath = r'E:/'  # Note r in front of windows path

        # logic trackers
        self.found_code = False

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

    def run(self):  # do all tkinter graphics in here, use callbacks for running things.
        # self.root.geometry(self.SX + "x" + self.SY)
        self.root.title(self.TITLE)
        # self.root.resizable(False, False)


### main Program ###
server = QRApp()
server.restoreState()
print("server started")
server.run()
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
# global vars general
communication_file = "server_comm.txt"
file_path = os.path.join(os.getcwd(), communication_file)
submitted = [False, False, False, False, False, False]
userID = ["r1", "r2", "r3", "b1", "b2", "b3"]
root = None

# add the subprocess for the gui
subprocess.Popen(["py", "-3", "new_test.py"])

# for the dataframe
with open("LEGEND.json") as f:
	header = json.load(f)
df = pd.DataFrame(columns=header)
matchdf = pd.DataFrame(columns=header)

# for opencv
cap = cv2.VideoCapture(0)
font = cv2.FONT_HERSHEY_PLAIN


def gen():
	global submitted
	string = ""
	for i in submitted:
		if i:
			tmp = "T"
		else:
			tmp = "F"
		string = string + tmp + ","
	return string


def verifydata():
	global df
	global root
	global matchdf
	# refresh tk
	root = tk.Tk()
	# get the last match
	last_match_df = matchdf.tail(6)
	validate.findMatch("qm19")
	# get any changes that need to be made
	msg = validate.findErrors(last_match_df)

	f = tk.Frame(root)
	f.pack(side=tk.BOTTOM, fill=tk.BOTH, expand=1)
	pt = pandastable.Table(f, dataframe=df, showtoolbar=True, showstatusbar=True)
	pt.show()
	# make the instructions appear above the dataframe
	otherstuff = tk.Frame(root)
	otherstuff.pack(side=tk.TOP)
	thing = tk.Label(otherstuff, text='Changes Needed:')
	thing.pack(side=tk.TOP)
	# make the thing run
	root.mainloop()
	df = df.append(pt.model.df)
	matchdf = pd.DataFrame(columns=header)
	saveData()


def saveData():
	global matchdf
	df.to_csv("qr_out.csv", index=False)
	usbPath = r'E:/'  # Note r in front of windows path
	saveMatchDF(matchdf)
	if os.path.exists(usbPath):
		df.to_csv(os.path.join(usbPath, "qr_out.csv"), index=False)


def communicate():
	if os.path.exists(file_path):
		os.remove(file_path)
	with open(file_path, "w+") as f:
		f.write(gen())


def checkReloadDF():
	global df
	# Check if there is already a saved file, if so load it.
	if os.path.exists(os.path.join(os.getcwd(), "qr_out.csv")):
		df = pd.read_csv("qr_out.csv")
		print("Loaded dataframe from previous save")
		print(df.head())

def checkReloadMatchDF():
	global matchdf
	# Check if there is already a saved file, if so load it.
	if os.path.exists(os.path.join(os.getcwd(), "match_df.csv")):
		matchdf = pd.read_csv("match_df.csv")
		print("Loaded last match in progress")
		print(matchdf.head())

def restoreState():
	global df
	global matchdf
	checkReloadDF()
	checkReloadMatchDF()
	collected = list(matchdf.Alliance.values)
	for coll in range(len(collected)):
		collected[coll] = collected[coll].lower()
	for i in range(len(userID)):
		if userID[i] in collected:
			submitted[i] = True
	communicate()


def saveMatchDF(matchdf):
	matchdf.to_csv("match_df.csv", index=False)


def runQRcapture():
	global cap
	global font
	global file_path
	global submitted
	global userID
	global df
	global matchdf
	shouldCont = True
	while shouldCont:
		_, frame = cap.read()

		decodedObjects = pyzbar.decode(frame)
		for obj in decodedObjects:
			print("Data: ", obj.data)
			data = obj.data
			data = data.decode("utf-8")
			# make sure we don't accidentally try to read a qrcode that isn't a qrcode
			if not data == "" and 28 < len(data.split(",")):
				if userID.count(data[-1].lower()) < 1 and not submitted[userID.index(data[-2:].lower())]:
					# Split the qrcode data into a list
					data = data.replace("[", "").replace("]", "").split(",")
					user = data[-1]
					data = data[:28]
					matchdf = matchdf.append(pd.DataFrame(data=[data], columns=header), ignore_index=True)
					saveMatchDF(matchdf)
					# look to see what user just submitted their data
					for i in range(len(userID)):
						if userID[i] == user.lower():
							submitted[i] = True
					# tell the dashboard what to do
					communicate()
					# only read one QRcode at a time (end the search loop)
					shouldCont = False
			#			window   text	   loc(l, t) font  size colour(BGR) thckness
			cv2.putText(frame, str(obj.data), (50, 50), font, 3, (255, 0, 0), 3)
			allTrue = all(i for i in submitted)
			if allTrue:
				submitted = [False, False, False, False, False, False]
				verifydata()
				time.sleep(10)
			return

		cv2.imshow("Frame", frame)
		key = cv2.waitKey(1)
		if key == 27 or not shouldCont:
			pass


def runWait():
	global root
	root = tk.Tk()

	def end():
		root.destroy()

	button = tk.Button(root, text='Scan a Code', width=25, command=end)
	button.pack()
	root.mainloop()
	return


# make sure to overwrite whatever state the dashboard was in when the program closed
restoreState()

print("looking for codes")

while True:
	runWait()
	runQRcapture()

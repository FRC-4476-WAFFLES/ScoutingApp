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
last_data = ""
communication_file = "server_comm.txt"
file_path = os.path.join(os.getcwd(), communication_file)
submitted = [False, False, False, False, False, False]
userID = ["r1", "r2", "r3", "b1", "b2", "b3"]
root = tk.Tk()

# add the subprocess for the gui
subprocess.Popen(["py", "-3", "new_test.py"])

# for the dataframe
with open("LEGEND.json") as f:
	header = json.load(f)
df = pd.DataFrame(columns=header)

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
	return


def verifydata():
	global df
	global root
	# get the last match
	last_match_df = df.tail(6)
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
	df = pt.model.df




print("looking for codes")
while True:
	_, frame = cap.read()


	decodedObjects = pyzbar.decode(frame)
	for obj in decodedObjects:
		print("Data: ", obj.data)
		print("Last Data", last_data)
		data = obj.data
		data = data.decode("utf-8")
		if userID.count(data[-1].lower()) < 1 and not submitted[userID.index(data[-2:].lower())] and not data == "":
			#
			data = data.replace("[", "").replace("]", "").split(",")
			last_data = data
			user = data[-1]
			data = data[:44]
			df = df.append(pd.DataFrame(data=[data], columns=header), ignore_index=True)
			df.to_csv("qr_out.csv", index=False)
			usbPath = r'E:/'   #Note r in front of windows path 
			if os.path.exists(usbPath):
				df.to_csv(os.path.join(usbPath, "qr_out.csv"), index=False)
			for i in range(len(userID)):
				if userID[i] == user.lower():
					submitted[i] = True;
			if os.path.exists(file_path):
				os.remove(file_path)
			with open(file_path, "w+") as f:
				f.write(gen())
		#			window   text	   loc(l, t) font  size colour(BGR) thckness
		cv2.putText(frame, str(obj.data), (50,50), font, 3, (255, 0, 0), 3)
		allTrue = all(i for i in submitted);
		if allTrue:
			submitted = [False, False, False, False, False, False]
			verifydata()
			time.sleep(10)



	cv2.imshow("Frame", frame)
	key = cv2.waitKey(1)
	if key == 27:
		pass
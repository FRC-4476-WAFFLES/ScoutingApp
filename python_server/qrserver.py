import pandas as pd
import numpy as np
import json
import os
import cv2
import pyzbar.pyzbar as pyzbar
last_data = ""
communication_file = "server_comm.txt"
file_path = os.path.join(os.getcwd(), communication_file)
R1Submit = False
R2Submit = False
R3Submit = False
B1Submit = False
B2Submit = False
B3Submit = False
def gen():
	global R1Submit
	global R2Submit
	global R3Submit
	global B1Submit
	global B2Submit
	global B3Submit
	string = ""
	if R1Submit:
		string = string + "T,"
	else:
		string = string + "F,"
	if R2Submit:
		string = string + "T,"
	else:
		string = string + "F,"
	if R3Submit:
		string = string + "T,"
	else:
		string = string + "F,"
	if B1Submit:
		string = string + "T,"
	else:
		string = string + "F,"
	if B2Submit:
		string = string + "T,"
	else:
		string = string + "F,"
	if B3Submit:
		string = string + "T,"
	else:
		string = string + "F,"
	if R1Submit and R2Submit and R3Submit and B1Submit and B2Submit and B3Submit:
		R1Submit = False
		R2Submit = False
		R3Submit = False
		B1Submit = False
		B2Submit = False
		B3Submit = False
	return string

with open("LEGEND.json") as f:
	header = json.load(f)
df = pd.DataFrame(columns=header)

cap = cv2.VideoCapture(0)
font = cv2.FONT_HERSHEY_PLAIN
print("looking for codes")
while True:
	_, frame = cap.read()


	decodedObjects = pyzbar.decode(frame)
	for obj in decodedObjects:
		print("Data: ", obj.data)
		print("Last Data", last_data)
		data = obj.data
		data = data.decode("utf-8")
		if(not data == last_data and not data == ""):
			data = data.replace("[", "").replace("]", "").split(",")
			last_data = data
			user = data[-1]
			data = data[:44]
			df = df.append(pd.DataFrame(data=[data], columns=header), ignore_index=True)
			df.to_csv("qr_out.csv")
			usbPath = r'E:/'   #Note r in front of windows path 
			if os.path.exists(usbPath):
				df.to_csv(os.path.join(usbPath, "qr_out.csv"))
			if user.lower() == "R1".lower():
				R1Submit = True
			elif user.lower() == "R2".lower():
				R2Submit = True
			elif user.lower() == "R3".lower():
				R2Submit = True
			elif user.lower() == "B1".lower():
				R2Submit = True
			elif user.lower() == "B2".lower():
				R2Submit = True
			elif user.lower() == "B3".lower():
				R2Submit = True
			if os.path.exists(file_path):
				os.remove(file_path)
			with open(file_path, "w+") as f:
				f.write(gen())
		#			window   text	   loc(l, t) font  size colour(BGR) thckness
		cv2.putText(frame, str(obj.data), (50,50), font, 3, (255, 0, 0), 3)
	cv2.imshow("Frame", frame)
	key = cv2.waitKey(1)
	if key == 27:
		pass
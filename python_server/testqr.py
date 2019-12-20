import cv2
import numpy as np
import pyzbar.pyzbar as pyzbar
# img = cv2.imread("pysource_qrcode.png")
# decodedObjects = pyzbar.decode(img)
# print(decodedObjects)
# for obj in decodedObjects:
# 	print("Data: ", obj.data)
# cv2.imshow("Image", img)
# cv2.waitKey(0)
cap = cv2.VideoCapture(0)
font = cv2.FONT_HERSHEY_PLAIN
while True:
	_, frame = cap.read()


	decodedObjects = pyzbar.decode(frame)
	for obj in decodedObjects:
		print("Data: ", obj.data)
		#            window   text       loc(l, t) font  size colour(BGR) thckness
		cv2.putText(frame, str(obj.data), (50,50), font, 3, (255, 0, 0), 3)
	cv2.imshow("Frame", frame)
	key = cv2.waitKey(1)
	if key == 27:
		pass
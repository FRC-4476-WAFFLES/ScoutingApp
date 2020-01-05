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


data = data.replace("[", "").replace("]", "").split(",")
			last_data = data
			user = data[-1]
			data = data[:44]
			save = False
			if user.lower() == "R1".lower() and not R1Submit:
				R1Submit = True
				save = True
			elif user.lower() == "R2".lower() and not R2Submit:
				R2Submit = True
				save = True
			elif user.lower() == "R3".lower() and not R3Submit:
				R2Submit = True
				save = True
			elif user.lower() == "B1".lower() and not B1Submit:
				R2Submit = True
				save = True
			elif user.lower() == "B2".lower() and not B2Submit:
				R2Submit = True
				save = True
			elif user.lower() == "B3".lower() and not B3Submit:
				R2Submit = True
				save = True
			if save == True:
				save = False
				df = df.append(pd.DataFrame(data=[data], columns=header), ignore_index=True)
				df.to_csv("qr_out.csv")
				usbPath = r'E:/'   #Note r in front of windows path 
				if os.path.exists(usbPath):
					df.to_csv(os.path.join(usbPath, "qr_out.csv"))
			if os.path.exists(file_path):
				os.remove(file_path)
			with open(file_path, "w+") as f:
				f.write(gen())
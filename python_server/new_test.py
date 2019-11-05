import pandas as pd
import numpy as np
import json
import tkinter as tk
import random
import threading
import bluetooth
with open("LEGEND.json") as f:
    header = json.load(f)
df = pd.DataFrame(columns=header)
data = ['7735', '7', 'r', 'c', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '898', '9888', "Duncan.s"]
df = df.append(pd.DataFrame(data=[data], columns=header), ignore_index=True)

R1Submit = False
R2Submit = False
R3Submit = False
B1Submit = False
B2Submit = False
B3Submit = False
r1 = None
r2 = None
r3 = None
b1 = None
b2 = None
b3 = None
maintain = True
root = tk.Tk()
######################### bluetooth setup
name = "waffle_scout_server"
target_name = "test"
uuid = "44764476-4476-4476-8000-00805F9B34FB"

with open("LEGEND.json") as f:
    header = json.load(f)
df = pd.DataFrame(columns=header)

# you had indentation problems on this line:
server_socket = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
port = bluetooth.PORT_ANY
server_socket.bind(("", port))
print("Listening for connections on port: ", port)
nearby_devices = bluetooth.discover_devices(lookup_names=True)
print(nearby_devices)

# wait for a message to be sent to this socket only once
server_socket.listen(1)
port = server_socket.getsockname()[1]

# you were 90% there, just needed to use the pyBluez command:
bluetooth.advertise_service(server_socket, "WaffleScout",
                            service_id=uuid,
                            service_classes=[uuid, bluetooth.SERIAL_PORT_CLASS],
                            profiles=[bluetooth.SERIAL_PORT_PROFILE]
                            )


def BluetoothDaemon():
    global R1Submit
    global R2Submit
    global R3Submit
    global B1Submit
    global B2Submit
    global B3Submit
    global df
    print("Listening for connections on port: ", port)
    input_socket, address = server_socket.accept()
    print("Got connection with", address)
    data_received = input_socket.recv(1024)
    print("received [%s] \n " % data_received)
    data_received = data_received.decode("utf-8")
    data_received = data_received.replace("[", "").replace("]", "").split(",")
    user = data_received[-1]
    data_received = data_received[:44]
    df = df.append(pd.DataFrame(data=[data], columns=header), ignore_index=True)
    df.to_csv("bt_out.csv")
    input_socket.close()
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
    server(label)
##################################################


root.title("Scouting Server")
label = tk.Label(root, fg="green")
label.pack()
r1 = tk.Label(root, text="Red 1 Not Submitted", bg="red", fg="white")
r1.pack(padx=5, ipady=10, side=tk.LEFT)
r2 = tk.Label(root, text="Red 2 Not Submitted", bg="red", fg="white")
r2.pack(padx=5, ipady=10, side=tk.LEFT)
r3 = tk.Label(root, text="Red 3 Not Submitted", bg="red", fg="white")
r3.pack(padx=5, ipady=10, side=tk.LEFT)

b1 = tk.Label(root, text="Red 1 Not Submitted", bg="blue", fg="white")
b1.pack(padx=5, ipady=10, side=tk.LEFT)
b2 = tk.Label(root, text="Red 2 Not Submitted", bg="blue", fg="white")
b2.pack(padx=5, ipady=10, side=tk.LEFT)
b3 = tk.Label(root, text="Red 3 Not Submitted", bg="blue", fg="white")
r3.pack(padx=5, ipady=10, side=tk.LEFT)

# counter_label(label)
def end():
    global maintain
    root.destroy()
    maintain = False
    server_socket.close()
button = tk.Button(root, text='Stop', width=25, command=end)
button.pack()
# def cont():
#     global R1Submit
#     global R2Submit
#     global R3Submit
#     global B1Submit
#     global B2Submit
#     global B3Submit
#     global root
def server(label):
    def cont():
        global R1Submit
        global R2Submit
        global R3Submit
        global B1Submit
        global B2Submit
        global B3Submit
        global root
        if maintain:
            # root.focus_force()
            #example.py is very example
            # counter += 1
            # label.config(text=str(counter))
            # label.after(1000, count)
            #if the scout has not yet submitted, check if they have yet
            print("im running")
            if not R1Submit:
                rand = random.randrange(0, 100)
                if rand > 50:
                    R1Submit = True
                    r1.configure(bg="green", fg="white")
                    r1.update()
                else:
                    r1.configure(bg="red", fg="white")
                    r1.update()
            if not R2Submit:
                rand = random.randrange(0, 100)
                if rand > 50:
                    R2Submit = True
                    r2.configure(bg="green", fg="white")
                    r2.update()
                else:
                    r2.configure(bg="red", fg="white")
                    r2.update()
            if not R3Submit:
                rand = random.randrange(0, 100)
                if rand > 50:
                    R3Submit = True
                    r3.configure(bg="green", fg="white")
                    r3.update()
                else:
                    r3.configure(bg="red", fg="white")
                    r3.update()
            if not B1Submit:
                rand = random.randrange(0, 100)
                if rand > 50:
                    B1Submit = True
                    b1.configure(bg="green", fg="white")
                    b1.update()
                else:
                    b1.configure(bg="blue", fg="white")
                    b1.update()
            if not B2Submit:
                rand = random.randrange(0, 100)
                if rand > 50:
                    B2Submit = True
                    b2.configure(bg="green", fg="white")
                    b2.update()
                else:
                    b2.configure(bg="blue", fg="white")
                    b2.update()
            if not B3Submit:
                rand = random.randrange(0, 100)
                if rand > 50:
                    B3Submit = True
                    b3.configure(bg="green", fg="white")
                    b3.update()
                else:
                    b3.configure(bg="blue", fg="white")
                    b3.update()
        if R1Submit and R2Submit and R3Submit and B1Submit and B2Submit and B3Submit:
            R1Submit = False
            R2Submit = False
            R3Submit = False
            B1Submit = False
            B2Submit = False
            B3Submit = False



        label.after(200, BluetoothDaemon)
    cont()
server(label)
root.mainloop()



import bluetooth
import pandas as pd
import numpy as np
import json

name = "waffle_scout_server"
target_name = "test"
uuid = "44764476-4476-4476-8000-00805F9B34FB"
communication_file = "server_comm.txt"
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
        string = string + "T,"
    if R2Submit:
        string = string + "T,"
    else:
        string = string + "T,"
    if R3Submit:
        string = string + "T,"
    else:
        string = string + "T,"
    if B1Submit:
        string = string + "T,"
    else:
        string = string + "T,"
    if B2Submit:
        string = string + "T,"
    else:
        string = string + "T,"
    if B3Submit:
        string = string + "T,"
    else:
        string = string + "T,"
    return string

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
while True:
    print("Listening for connections on port: ", port)
    input_socket, address = server_socket.accept()
    print("Got connection with", address)
    data = input_socket.recv(1024)
    print("received [%s] \n " % data)
    data = data.decode("utf-8")
    data = data.replace("[", "").replace("]", "").split(",")
    user = data[-1]
    data = data[:44]
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
    gen()


server_socket.close()


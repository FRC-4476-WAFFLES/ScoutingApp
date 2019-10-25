import bluetooth
import pandas as pd
import numpy as np
import json

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
while True:
    print("Listening for connections on port: ", port)
    input_socket, address = server_socket.accept()
    print("Got connection with", address)
    data = input_socket.recv(1024)
    print("received [%s] \n " % data)
    data = data.decode("utf-8")
    data = data.replace("[", "").replace("]", "").split(",")
    data = data[:44]
    df = df.append(pd.DataFrame(data=[data], columns=header), ignore_index=True)
    df.to_csv("bt_out.csv")
    input_socket.close()
server_socket.close()


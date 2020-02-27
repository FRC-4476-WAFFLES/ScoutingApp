import pandas as pd
import numpy as np
import json
import tkinter as tk
import os
with open("LEGEND.json") as f:
    header = json.load(f)
# df = pd.DataFrame(columns=header)
# data = ['7735', '7', 'r', 'c', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '898', '9888', "Duncan.s"]
# df = df.append(pd.DataFrame(data=[data], columns=header), ignore_index=True)

communication_file = "server_comm.txt"
file_path = os.path.join(os.getcwd(), communication_file)
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
root.title("Scouting Server")
label = tk.Label(root, fg="green")
label.pack()
r1 = tk.Label(root, text="Red 1 Not Submitted", bg="red", fg="white", font=("Helvetica", 16))
r1.pack(padx=10, ipady=200, side=tk.LEFT)
r2 = tk.Label(root, text="Red 2 Not Submitted", bg="red", fg="white", font=("Helvetica", 16))
r2.pack(padx=10, ipady=200, side=tk.LEFT)
r3 = tk.Label(root, text="Red 3 Not Submitted", bg="red", fg="white", font=("Helvetica", 16))
r3.pack(padx=10, ipady=200, side=tk.LEFT)

b1 = tk.Label(root, text="Blue 1 Not Submitted", bg="blue", fg="white", font=("Helvetica", 16))
b1.pack(padx=10, ipady=200, side=tk.LEFT)
b2 = tk.Label(root, text="Blue 2 Not Submitted", bg="blue", fg="white", font=("Helvetica", 16))
b2.pack(padx=10, ipady=200, side=tk.LEFT)
b3 = tk.Label(root, text="Blue 3 Not Submitted", bg="blue", fg="white", font=("Helvetica", 16))
b3.pack(padx=10, ipady=200, side=tk.LEFT)

# counter_label(label)
def end():
    global maintain
    root.destroy()
    maintain = False
button = tk.Button(root, text='Stop', width=25, command=end)
button.pack()
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
            content = ["F", "F", "F", "F", "F", "F"]
            # print("im running")
            if os.path.exists(file_path):
                try:
                    with open(file_path, "r") as current:
                        content = current.read()
                        content = content.strip()
                        content = content.split(",")
                except:
                    print("here's that weird error that doesn't really make sense.")
            if not R1Submit:
                if 'T' in content[0]:
                    R1Submit = True
                    r1.configure(text="Red 1 Submitted", bg="green", fg="red")
                    r1.update()
                else:
                    r1.configure(text="Red 1 Not Submitted", bg="red", fg="white")
                    r1.update()
            if not R2Submit:
                if "T" in content[1]:
                    R2Submit = True
                    r2.configure(text="Red 2 Submitted", bg="green", fg="red")
                    r2.update()
                else:
                    r2.configure(text="Red 2 Not Submitted", bg="red", fg="white")
                    r2.update()
            if not R3Submit:
                if "T" in content[2]:
                    R3Submit = True
                    r3.configure(text="Red 3 Submitted", bg="green", fg="red")
                    r3.update()
                else:
                    r3.configure(text="Red 3 Not Submitted", bg="red", fg="white")
                    r3.update()
            if not B1Submit:
                if "T" in content[3]:
                    B1Submit = True
                    b1.configure(text="Blue 1 Submitted", bg="green", fg="blue")
                    b1.update()
                else:
                    b1.configure(text="Blue 1 Not Submitted", bg="blue", fg="white")
                    b1.update()
            if not B2Submit:
                if "T" in content[4]:
                    B2Submit = True
                    b2.configure(text="Blue 2 Submitted", bg="green", fg="blue")
                    b2.update()
                else:
                    b2.configure(text="Blue 2 Not Submitted", bg="blue", fg="white")
                    b2.update()
            if not B3Submit:
                if "T" in content[5]:
                    B3Submit = True
                    b3.configure(text="Blue 3 Submitted", bg="green", fg="blue")
                    b3.update()
                else:
                    b3.configure(text="Blue 3 Not Submitted", bg="blue", fg="white")
                    b3.update()
        if R1Submit and R2Submit and R3Submit and B1Submit and B2Submit and B3Submit:
            R1Submit = False
            R2Submit = False
            R3Submit = False
            B1Submit = False
            B2Submit = False
            B3Submit = False
        label.after(200, cont)
    cont()
server(label)
root.mainloop()



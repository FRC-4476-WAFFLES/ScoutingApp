import socket
import tbapy
import tkinter as tk
import pandas as pd
import time
from statistics import mode

eventCode = "2020isde1"
apiKEY = "vMIHZYUhwQtwp5mB7hilezRBShGlfYTSmv8zPkcKxCHlTbmnYlQL7ikgf3YIDHmW"

root = tk.Tk()
root.title("Scouting Server")

def is_connected(hostname):
    try:
        # see if we can resolve the host name -- tells us if there is
        # a DNS listening
        host = socket.gethostbyname(hostname)
        # connect to the host -- tells us if the host is actually
        # reachable
        s = socket.create_connection((host, 80), 2)
        s.close()
        return True
    except:
        pass
    return False


# def findMatch(matchNum):
#     global eventCode
#     global apiKEY
#     global matchInfo
#     shouldCont = True
#     while shouldCont:
#         if is_connected("www.thebluealliance.com"):
#             tba = tbapy.TBA(apiKEY)
#             mat = tba.match(eventCode + "_" + matchNum)
#             if mat.winning_alliance is not None:
#                 shouldCont = False
#                 matchInfo = mat
#         else:
#             time.sleep(30)


def findErrors(df):
    global apiKEY
    global eventCode
    print("Starting finding errors")
    tba = tbapy.TBA(apiKEY)
    finalMSG = ""

    try:
        matchNum = f"qm{mode(df['Match'])}"
        match_data = tba.match(f"{eventCode}_{matchNum}")
    except:
        return " No consensus on match number!!! YO SCOUTS FIX YO STUFF"


    blueScore = match_data["alliances"]["blue"]["score"]
    redScore = match_data["alliances"]["red"]["score"]
    print(blueScore)
    print(redScore)
    scoreBreakdown = match_data["score_breakdown"]
    for index, row in df.iterrows():
        # Get alliance station
        alliance = row['Alliance'][:1]
        drive_station = row['Alliance'][1:]
        # Get correct alliance data
        if alliance == "R":
            allianceBreakdown = scoreBreakdown['red']
        else:
            allianceBreakdown = scoreBreakdown['blue']
        # Gets known values on a per robot basis
        measuredMovement = allianceBreakdown[f'initLineRobot{drive_station}']

        # INITIATION LINE CHECK
        # Convert string to boolean
        if measuredMovement == "Exited":
            measuredMovement = 1
        else:
            measuredMovement = 0
        # Check if value of initiation line is correct.
        if row['Movement'] != measuredMovement:
            finalMSG += f" {row['Team']} incorrect initiation line value.\n"

        # HANGING/PARK CHECK



    if finalMSG == "":
        finalMSG = "no changes needed, please close the frame"

    return finalMSG


# while True:
#     with open("dovalidate.txt", "r") as f:
#         save = f.read()
#     if save.count("g")>0:
#         # move to somewhere better
#         with open("dovalidate.txt", "w+") as f:
#             f.write("stop")
#         if(is_connected("www.thebluealliance.com")):
#             tba = tbapy.TBA(apiKEY)
#             tba.match("2019onsh_qm19")
#
#
#     else:
#         time.sleep(10)
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

    # Set counters
    redInnerAuto = 0
    redOuterAuto = 0
    redLowerAuto = 0
    blueInnerAuto = 0
    blueOuterAuto = 0
    blueLowerAuto = 0

    redInnerTele = 0
    redOuterTele = 0
    redLowerTele = 0
    blueInnerTele = 0
    blueOuterTele = 0
    blueLowerTele = 0

    for index, row in df.iterrows():
        # Get alliance station
        alliance = row['Alliance'][:1]
        drive_station = row['Alliance'][1:]
        # Get correct alliance data
        if alliance == "R":
            allianceBreakdown = scoreBreakdown['red']

            # AUTO SCORING
            redInnerAuto += row["A_Inner"]
            redOuterAuto += row['A_Outer']
            redLowerAuto += row["A_Lower"]

            # TELE SCORING
            redInnerTele += row["T_Inner"]
            redOuterTele += row['T_Outer']
            redLowerTele += row["T_Lower"]
        else:
            allianceBreakdown = scoreBreakdown['blue']
            # AUTO SCORING
            blueInnerAuto += row["A_Inner"]
            blueOuterAuto += row['A_Outer']
            blueLowerAuto += row["A_Lower"]

            # TELE SCORING
            blueInnerTele += row["T_Inner"]
            blueOuterTele += row['T_Outer']
            blueLowerTele += row["T_Lower"]
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
            finalMSG += f"\n{row['Team']} incorrect initiation line value."

        # HANGING/PARK CHECK
        # Convert park data
        if row['Park'] == "Success":
            parkStatus = 1
        else:
            parkStatus = 0

        # Convert hang data
        if row['Hang'] == "Success" or row['cargo'] == 1:
            climbStatus = 1
        else:
            climbStatus = 0

        # Check if you are climbing and parking at the same time
        if climbStatus == 1 and parkStatus == 1:
            finalMSG += f"\n{row['Team']} is both parking and climbing at the same time."

        # Verify climbs are correct
        measuredEndgame = allianceBreakdown[f'endgameRobot{drive_station}']
        # Convert data
        if measuredEndgame == "Hang":
            measuredClimbStatus = 1
            measuredParkStatus = 0
        elif measuredEndgame == "Park":
            measuredClimbStatus = 0
            measuredParkStatus = 1
        else:
            measuredParkStatus = 0
            measuredClimbStatus = 0
        # Checking our data!
        if climbStatus != measuredClimbStatus:
            finalMSG += f"\n{row['Team']} climbing data is invalid, please review."
        if parkStatus != measuredParkStatus:
            finalMSG += f"\n{row['Team']} parking status is incorrect."

        # Check scout names
        if "null" in row['Scout']:
            finalMSG += f"\n{row['Alliance']} has no scout name set."


    # Check AUTO SCORES
    # Inner scores
    if redInnerAuto != scoreBreakdown['red']['autoCellsInner']:
        finalMSG += f"\nRED alliance inner auto is off by {(redInnerAuto - scoreBreakdown['red']['autoCellsInner'])}"
    if blueInnerAuto != scoreBreakdown['blue']['autoCellsInner']:
        finalMSG += f"\nBLUE alliance inner auto is off by {(blueInnerAuto - scoreBreakdown['blue']['autoCellsInner'])}"

    # Outer scores
    if redOuterAuto != scoreBreakdown['red']['autoCellsOuter']:
        finalMSG += f"\nRED alliance outer auto is off by {(redOuterAuto - scoreBreakdown['red']['autoCellsOuter'])}"
    if blueOuterAuto != scoreBreakdown['blue']['autoCellsOuter']:
        finalMSG += f"\nBLUE alliance outer auto is off by {(blueOuterAuto - scoreBreakdown['blue']['autoCellsOuter'])}"

    # Lower scores
    if redLowerAuto != scoreBreakdown['red']['autoCellsBottom']:
        finalMSG += f"\nRED alliance lower auto is off by {(redLowerAuto - scoreBreakdown['red']['autoCellsBottom'])}"
    if blueLowerAuto != scoreBreakdown['blue']['autoCellsBottom']:
        finalMSG += f"\nBLUE alliance lower auto is off by {(blueLowerAuto - scoreBreakdown['blue']['autoCellsBottom'])}"

    # Check TELE SCORES
    # Inner scores
    if redInnerTele != scoreBreakdown['red']['teleopCellsInner']:
        finalMSG += f"\nRED alliance inner teleop is off by {(redInnerTele - scoreBreakdown['red']['teleopCellsInner'])}"
    if blueInnerTele != scoreBreakdown['blue']['teleopCellsInner']:
        finalMSG += f"\nBLUE alliance inner teleop is off by {(blueInnerTele - scoreBreakdown['blue']['teleopCellsInner'])}"

    # Outer scores
    if redOuterTele != scoreBreakdown['red']['teleopCellsOuter']:
        finalMSG += f"\nRED alliance outer teleop is off by {(redOuterTele - scoreBreakdown['red']['teleopCellsOuter'])}"
    if blueOuterTele != scoreBreakdown['blue']['teleopCellsOuter']:
        finalMSG += f"\nBLUE alliance outer teleop is off by {(blueOuterTele - scoreBreakdown['blue']['teleopCellsOuter'])}"

    # Lower scores
    if redLowerTele != scoreBreakdown['red']['teleopCellsBottom']:
        finalMSG += f"\nRED alliance lower teleop is off by {(redLowerTele - scoreBreakdown['red']['teleopCellsBottom'])}"
    if blueLowerTele != scoreBreakdown['blue']['teleopCellsBottom']:
        finalMSG += f"\nBLUE alliance lower teleop is off by {(blueLowerTele - scoreBreakdown['blue']['teleopCellsBottom'])}"


    if finalMSG == "":
        finalMSG = "no changes needed, please close the window"

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
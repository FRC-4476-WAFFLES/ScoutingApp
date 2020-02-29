import csv
import tbapy

apiKEY = "vMIHZYUhwQtwp5mB7hilezRBShGlfYTSmv8zPkcKxCHlTbmnYlQL7ikgf3YIDHmW"

tba = tbapy.TBA(apiKEY)
event_code = "2020misou"

schedule = []
matches_raw = tba.event_matches(event_code)
for match in matches_raw:
    red = match["alliances"]['red']['team_keys']
    blue = match["alliances"]['blue']['team_keys']
    red_teams = []
    blue_teams = []
    for team in red:
        red_teams.append(team[3:])
    for team in blue:
        blue_teams.append(team[3:])
    list = [match["match_number"]] + red_teams + blue_teams
    schedule.append(list)

    with open("Schedule.csv", 'w', newline='') as csvfile:
        wr = csv.writer(csvfile, quoting=csv.QUOTE_ALL)
        wr.writerows(schedule[1:len(schedule)])


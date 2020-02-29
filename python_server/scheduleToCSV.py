import csv
import tbapy
import operator


apiKEY = "vMIHZYUhwQtwp5mB7hilezRBShGlfYTSmv8zPkcKxCHlTbmnYlQL7ikgf3YIDHmW"

tba = tbapy.TBA(apiKEY)
event_code = "2020onosh"

header = ["Qual", "R1", "R2", "R3", "B1", "B2", "B3"]
schedule = list()
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
        schedule_sorted = sorted(schedule[0:len(schedule)], key=operator.itemgetter(0), reverse=False)
        schedule_actual = [header] + schedule_sorted
        wr.writerows(schedule_actual)


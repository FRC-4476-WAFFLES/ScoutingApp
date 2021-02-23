# ScoutingApp
The W.A.F.F.L.E.S. Scouting App was designed for the W.A.F.F.L.E.S. Community Robotics to replace the existing system of paper and manual data entry. It is compiled for each event with a match schedule in the form of a csv file, and deployed to 6 amazon fire tablets. Each tablet represents one driver station postion, meaning that for each match, the tablet is used to collect data on one competing robot.
The app checks what robot it is collecting data on by way of a preset 2 character string set as the devices Bluetooth name, (mostly due to the original use of bluetooth for data transfer) and informs the scout as to what robot they are collecting data on, and what driverstation the team is controlling the robot from.
The app automatically keeps track of the current match number, and can be overridden if a match has to be replayed.
an example of this screen can be seen below.

![Before the match screenshot](https://i.imgur.com/hO8U8nF.jpeg "Before The Match")

The app also allows for forwards and backwards navigation throught the pages, and data is preserved throught these actions. Bright colours are used for it to be as easy as possible for scouts to tell at a glance what the current state of the app is, and what their inputs will affect. Many elements involve counters, to keep a running total of a specific robot activity. by default, the app will increment the counter for each button press, but using the erase button will toggle the button colour from green to red, indicating that the button will now decrement the value.
An example of this is shown below.

![Teleop Screenshot](https://i.imgur.com/YGPFqqW.jpg "Teleop")

Also Note above certain buttons can only be active if others in the set are inactive. Pressing on one of these buttons will override its current state.

Other buttons have internal logic that is designed to prevent impossible data from being entered at the source. In the picture below, note the two last options. A simple example of this logic is that the robot cannot be listed as parked and hanging at the same time, and attempting to enter this will cause an on screen prompt and result in no data being recored. Another note about this screen specifically, and ones like it, is that it is impossible to progress unless some sort of data is entered. This is intended to prevent human error resulting from forgetting a piece of information.

![End Game Screenshot](https://i.imgur.com/QbaSajn.png "End Game")

The last data entry screen for each match also includes room for comments on a robots overall performance and demeanor.

![Post Match Screenshot](https://i.imgur.com/cS5CMMF.png "Post Match")

At the end of each match, data is transferred to a hub laptop via a QR code, encoded as plaintext in the csv format. Overall there are about 40 fields, including the scout's name. 

![data transmition](https://i.imgur.com/64XV2fe.png "QR Code")

Data from each match is then furthur processed by a python script on the laptop, which compares it with the official match data on a remote website via a cellular data connection. After the comparison, the laptop operator is then given the option to adjust any values based on the python scripts instructions, usign a exel-like editor courtesy of the pandastable library. The laptop side has since been significantly overhauled, though remaining mostly untested. An example screenshot is included below.

![python server](https://i.imgur.com/FqNhbE5.png, "Server Side")

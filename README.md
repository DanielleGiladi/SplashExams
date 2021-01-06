
What the problem?

Scheduling exams takes way too long...
It's done manually, and it takes weeks to get an initial schedule from the exams department.
Today the way to scheduling exams is done manually by the exams department.

SplashExams designed to save a huge amount of time and automatically schedule exams in seconds.

The challenge of the system is to consider different constraints, some of which are mandatory and some have to be prioritized by different criteria, such as exams period and the number of days between exams.

 SplashExams receives the requirements from the user and automatically calculates scheduling that meets as many constraints as possible.

SplashExams creates scheduling in seconds and optimizes the current scheduling time over 90% relative to manual work.

The system offers the user possible exams inlay for all exams in a particular semester, the inlay will be created according to the constraints that the user has inputted into the system. The system will try to produce an optimal inlay as possible for the user - meaning, creating an inlay that will answer as many user constraints as possible.
The system offers the user to enter constraints such as the difference in days between the exams. 
To make it easy to represent the constraints within the system, we created a new language with which we can easily describe the constraints of the exams and check if the constraints are met or not for a particular inlay.
For example, the constraint that describes the day difference is represented in our
language as follows:
x.id ! y.id > x.day DISTANCE   y.day >= 3

To ensure that the system presents an optimal final inlay, we used a genetic algorithm, its goal is to optimize the inlay and select the best inlay from all the inlays that the system randomly created.
The best inlay is the one that meets as many user constraints as possible.

<img src = "images/mainScreen.png" width="400">  <img src = "images/semesterScreen.png" width="400"> 

<img src = "images/inputScreen.png" width="400">  <img src = "images/waitingScreen.png" width="400"> 

<img src = "images/inlayScreen.png" width="400">   <img src = "images/diagram.PNG" width="400">

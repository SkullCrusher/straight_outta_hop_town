# Weather RSS - by team straight_outta_hop_town
A application that uses the NASA world wind SDK to create a simple GUI for the RSS feed information provided by the natural weather service.

#Building instructions:
1.	Clone the git repository into a clean directory. Also download the NASA World Wind dependencies from http://worldwind.arc.nasa.gov/java/ 
2.	Launch Eclipse IDE (other Java compilers can be used but have not been tested).
3.	Create a new workspace that is not contained within the clean svn repository directory.
4.	Create a new project titled “straight_outta_hop_town_git”.
5.	Create a new package inside this project titled “package_1”.
6.	With any file explorer navigate to the newly cloned git repository from step one. Next, open the folder where the cloned which should contain Java source files.
7.	Select all of the files and drag them into “package_1” created in step 4 within Eclipse IDE. When prompted select to link to files.
8.	Right click on the “straight_outta_hop_town_git” project in the package explorer of Eclipse IDE and select properties.
9.	Select Java Build Path and in that menu click libraries.
10.	Click the “Add External JARs…” button and navigate to the downloaded NASA world wind dependencies from step 1. Select all of the JAR files and click okay.
11.	Click on the “Apply” button and then the “Ok” button.
12.	(Optional) To insure that the steps 1 through 11 were done correctly the application can be launched by clicking the “Run” menu item and selecting “Run”. If the application is unable to run check that each step before was done correctly.
13.	On the top menu bar of Ellipse IDE click “File” and then “Export…”.
14.	Expand the Java tab and select “Runnable JAR file” and click next.
15.	Make sure the launch configuration is set to the main from the project “straight_outta_hop_town_git”, set the library handling to copy required libraries, and set a destination path and JAR name. 
16.	 Click finished and okay on any dialog boxes that appear until the main one disappears.
17.	 The executable JAR should be placed in the destination that was specified in step 15 with a folder containing the libraries. To the export is correct, the JAR can be run to insure that it is correct. If it is to be deployed the JAR and libraries should be compressed into a single zip file.

#Deployment Instructions:

0.	The system that is being deployed to must have Java JRE before deployment.
1.	The WeatherRSS application is deployed as a single zip archive file. Once it is acquired from the distributor, use any program to unarchive the application into a single directory that allows read and write access to the application.
2.	The user can run the JAR file to use the application.
3.	(Optional) A short cut can be created that links to the JAR file to make it easier for the user to launch the application.

# Credits
1. Jordan Hendry
2. David Harkins

# License
MIT License: Enjoy!

Dependencies: NASA Open Source Agreement v1.3

# Extra Information
Thug life is not a choice, it is destiny.

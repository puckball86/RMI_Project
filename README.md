**Asynchronous RMI String Comparison Service **
===================

**Students: Dara Starr**<br>
**Student no: G00209787**<br>
**Course: Software Development**<br>
**Module: Distributed Systems**<br>
**Lecturer: Dr. John Healy**<br>
**Galway-Mayo Institute of Technology**
****


Application  Overview
-------------

As part of the Distributed Systems module of my software development course I have created a a Web Application for comparing two strings and depending on which algorithm you choose the distance between them will be returned.

On launching the application the user will see the main page where there is a drop-down list, two text boxes and a compare button. First the user should select which of the algorithms to use, then enter the two strings they want to compare and click on the compare button. the user will be redirected to another page informing them that their request is being processed. The page is set to auto refresh every ten seconds and if the request has been successfully processed the result will be updated and displayed to the page. 


Launching Instructions
-------------
To launch this application you will need the Web Application Archive (War) file and also the String-Service java application archive (Jar) file that are provided in the Deployment folder in the GitHub repository. Next you will need to install the latest version of Apache Tomcat server from [http://tomcat.apache.org/](http://tomcat.apache.org/download-90.cgi). After you have setup tomcat, you must put the comparator.war file into the webapps folder within the Apache Tomcat directory. To start the the Tomcat server open a Window's command prompt and move to the bin directory of the Apache Tomcat Files and use the command *startup.bat*. Next you need to launch the RMI Server. Navigate to the directory where you have downloaded the *string-service.jar* file and and run the following command **java –cp ./string-service.jar ie.gmit.sw.Servant**. This will start the RMI server. Next open you favourite browser and enter **localhost:8080/comparator** and this will bring you to my String Comparison Web Application.

UML Diagram
-------------
![UML Diagram](https://github.com/puckball86/RMI_Project/blob/master/RMI_UML.png)

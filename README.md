# OOP-Database-Project
This is the GUI Database Project for my OOP Class. It is used to demonstrate the skills learned in this course.<br>
This project uses a SQL database called books (this is located in the lib folder).<br>
<br>
#GUI Design Principles:<br>
When designing this program I chose to simplify the programs UI inorder to ensure clarity and a clean asthetic. This Database project allowed me to have multiple pages which tells the users what they need to do in order to complete the action. Removal and addition to the database were given unique pages inorder to ensure that the user does not accidentally select the wrong function and delete a data point that already exists. I included input sanitation to ensure that the user fills out all feilds and does not leave feilds empty. Exceptions are also caught to prevent the application from crashing and prompts the user with error labels inorder to guide the user as to how fix the problem. 
<br>
![](https://i.imgur.com/Bq4jkKW.gif)

#Important Notes
This project need to have the derby.jar and jfoenix-8.0.7.jar included in the project structure. (derby.jar and jfoenix-8.0.7.jar is located in the lib folder.) 


#V1.1 <br>
Outputs Data to GUI only for Author based information. <br>
Files Included:<br>
src(Folder)<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Author.java<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DataBaseTableController.java<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Main.java<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DBTable.fxml<br>
<br>
Lib(Folder)<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;books(Folder containing SQL database)<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;derby.jar<br>

#V2.0 <br>
Added functions to add and remove values from database. <br>
Files Added:<br>
src(Folder)<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DBTableStyle.css<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JFXpages.css<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;AddToTableSubmissionController.java<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RemoveFromTableSubmissionController.java<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;addToTableSubmission.fxml<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;removeFromTableSubmission.fxml<br>
<br>
Lib(Folder)<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;jfoenix-8.0.7.jar<br>

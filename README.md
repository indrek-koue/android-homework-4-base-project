# android-homework-4-base-project
For students as a start for their homework nr. 4  

Current project consist of single fragment (let's call it fragment A) and is connected to Stackoverflow API to retrieve questions by android tag.

#Homework nr. 4

**Due date:** 9. june 2015  
**Submission protocol:** zipped src/main folder and .apk (from project build folder, unaligned or not aligned) to teachers official email.

Download this project and add **new** features to **this project**:

##1)
**Device in landscape:**  
Two fragments are displayed side by side (fragment A on left and fragment B on right). If item is clicked in fragment A, details of that question should be displayed in fragment B.

**Device in portrait:**  
Only single fragment is displayed and when item is selected in fragment A then the fragment B is opened with details.

Approximate example what the UI should look like: https://developer.android.com/images/training/basics/fragments-screen-mock.png

##2)
Fragment B needs to display these details about each question when item is selected from fragment A:
* Title
* Question id
* View count
* Answer count
* Question score
* Name of user who posted the question
* Link to a question. 

##3) 
When the question link is clicked in fragment B, it has to display the website or directe the user to the question on stackoverflow.com website.

##4)
During each loading a loading indicator has to be displayed and when the loading is done the indicator needs to be hidden. You can use whatever view or widget to indicate loading (I would recommend ProgressBar).

##5)
Button “next” and button “previous” in fragment A which select pages forward and backwards. When the button is clicked, new request with the correct page number is made and new data loaded and displayed in the list.  
For example with single request you get 100 objects, but in total there are 500 objects, then you display 100 items in listview and pressing next would load next items 101-200.

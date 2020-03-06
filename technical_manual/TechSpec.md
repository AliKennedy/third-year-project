### 0. Table of Contents

#### 1. Introduction

  1.1 Overview  
  1.2 Glossary

#### 2. System Architecture

  2.1 System Architecture Diagram  

#### 3. High-Level design

  3.1 Data Flow Diagram  
  3.2 Data Flow Diagram
  3.3 Component Diagram

#### 4. Problems and Resolution

  4.1 Not understanding the Arduino Uno and the GSM/GPS/GPRS Shield.  
  4.2 Trouble integrating a database into our app.  
  4.3 Integrating Google Maps into the Android app.  
  4.4 Parsing the NMEA data that the Arduino received.  
  4.5 Getting Arduino data to database.  
  4.6 Arduino Uno began to run out of internal memory due to the program.  

#### 5. Installation Guide

  5.1 List of Software and Hardware Required.  
  5.2 Assembling the Arduino and other components.  
  5.3 Android Studio and Android App Installation Guide.  
  5.4 Arduino Software IDE Installation Guide and Upload of Program.  

#### 6. Testing

  6.1 User Testing.  
  6.2 Unit Testing.  
  6.3 Integration Testing.  

---------------------------------------------------------------------------------------


#### 1. Introduction

##### 1.1 Overview

  The product developed is a wearable tracker for your pet. It comes with an accompanying app which displays the location of the tracker and in turn, the location of your pet. Our entire product consists of an Arduino Uno Microcontroller, a SIM808 GSM/GPRS shield, an Android App, a Firebase Database and a Server.  

  The Arduino has an I.D. It sends the I.D. accompanied with the latitude and longitude of the device to a PHP file stored on a server through a HTTP GET Request. This data then gets sent from the PHP file to the Real-Time Database provided by Firebase. The latitude and longitude is stored in the database under the Arduino I.D. This information is then displayed on the map within the app.  
  
  The app also allows multiple users to be linked to the same device. This allows an entire household to have access to the location of the same Arduino. This feature is available through the settings area of the app where you can manually enter the Arduino I.D.  

##### 1.2 Glossary

  * _**Arduino Uno Microcontroller**_ - This refers to a component of the tracker where the GSM/GPRS/GPS Shield is connected too. That part is labelled below.
  * _**SIM 808 GSM/GPRS/GPS Shield**_ - This refers to the component that retrieves the GPS data for the tracker using mobile data.
  * _**GPS**_ - Global Positioning System - A satellite based navigation system.
  * _**GSM**_ - Global System for Mobile Communications - Provides protocols for digital cellular networks used by portable devices such as mobile phones.
  * _**NMEA Data**_ - Acronym for National Marine Electronics Association. Standard data format supported by all GPS manufacturers.
  * _**Pet Wearable Device**_ - Consists of the Arduino Uno and the SIM 808 Shield

---------------------------------------------------------------------------------------


#### 2. System Architecture

  ![System Architecture Diagram][sysArchDiagram]

  The System Architecture Diagram includes three components. An Arduino Uno and a SIM 808 GSM/GPRS/GPS shield which together acts as a tracker. It also includes an Android app and a server.  

  The server receives the location of the tracker from the Arduino tracker. The Android app then receives the location of the Arduino tracker through the server. The App displays the location of the tracker to the user on a map.  

  If the App or the tracker can not display or send the latest location of the Arduino tracker respectively, the app will display the last known location of the Arduino tracker.  

----------------------------------------------------------------------------------------

#### 3. High-Level Design

  ![Data Flow Diagram][DFD1]

**Fig 3.1:** Above is a Data Flow Diagram when a user creates an account and links with the device. It begins with the user reading the Arduino Manual and then proceeds to signing up to the app. During this process, the user is required to fill in all necessary credentials. A user is given a random Arduino Unique I.D. upon sign up to ensure successful sign up. Once signed up, the user is required to go to settings and manually enter the Arduino Unique I.D. that is located in the Arduino Manual. Upon changing this, the user will be met with a success or failure message upon whether the link between the device and user was successful or not.  

  ![Data Flow Diagram][DFD2]

**Fig 3.2:** Above is a Data Flow Diagram that depicts the Pet Wearable Device retrieving its location and sending it to a user. Data from a number of satellites is received by the GPS module and is used to produce NMEA data. This data is sent to the Pet Wearable Device for processing. The Pet Wearable Device then sends the converted data, determining a location, to a PHP File located on the server. The PHP File sends this converted data to the Database. The user is then able to access this data as it is received by the Database.

  ![Component Diagram][compDiagram]

**Fig 3.3:** The above diagram is a Component Diagram. This diagram shows how all the physical components work together to create this project. The Pet Wearable Device retrieves the NMEA data from GPS satellites. That data is parsed to retrieve the latitude and longitude from it. This data computed is sent to a Server where it gets put into a Database. When a user accesses the app and signs in, the app retrieves this GPS data under the heading of the Arduinos Unique I.D. which can be seen being set up in Fig 3.1. This data is then displayed upon the map that is incorporated into the app and therefore reveals their pets location.  

--------------------------------------------------------------------------------------------


#### 4. Problems and Resolution

#####  4.1 Not understanding the Arduino Uno and the GSM/GPS/GPRS Shield.  

_**Problem:**_ When we first began to program the Arduino, We could not get the shield to work with the Arduino. We did not know how to transfer data using the shield. We did not know how to get the code to work with the shield.  

_**Resolution:**_ We investigated online and into the user manual to try solve our problem. We found out after vast amounts of research that the RX and TX pins are different on our shield compared to most. Changing the RX and TX pins gave us access to the shield through the arduino.  

##### 4.2 Trouble integrating a database into our app.  

_**Problem:**_ We had trouble integrating a database into our Android app on Android Studio. We originally had a SQLite database integrated into our app. It was time consuming to integrate this as we have never attempted it before. However, we soon realised that this was a local database confined to only one instance of the app and therefore was not what we were looking for.   

_**Resolution:**_ We soon researched on what would be the most practical database for our app. We agreed to use Firebase as it appeared to be the most practical for our project. Firebase had vast amounts of documentation compared to other databases. This in turn aided us with the process of integrating the database with our project and made it less time consuming in an already time consuming project.  

##### 4.3 Integrating Google Maps into the Android app.  

_**Problem:**_ Google recently updated the mechanism for integrating Google Maps into Android apps. This made a lot of the tutorials and manuals online obsolete. Even the tutorial integrated into Android Studio was obsolete. The only relevant tutorials we could locate online were written in Kotlin, which we are not using.  

_**Resolution:**_ We ended up learning the basics of Kotlin (variables, methods etc) and converted what we understood to Java as best we could, then filled in the gaps.  

#####  4.4 Parsing the NMEA data that the Arduino received.  

_**Problem:**_ We are used to high-level string manipulation such as in Python and Java. This was not an option in Arduino programming as posed a hurdle at first.  

_**Resolution:**_ From the Arduino website we discovered the SoftwareSerial method read(). This method takes a character from the serial buffer. We created a character array then set up a loop to read in characters from the buffer one by one and store them in the character array. We then used another loop to iterate through this array and extract the data we needed and stored them as string variables.  

##### 4.5 Getting Arduino data to database.  

_**Problem:**_ When we fixed the problem of retrieving and correctly parsing the NMEA data to display the latitude and longitude, we quickly hit another major problem. We did not know any way to get this new found data to the database. Our supervisor gave us a deadline of twenty-four hours to solve this problem as this was a very large feature of our project that did not work.  

_**Resolution:**_ After twelve hours of research, we found a solution. The Arduino sends the data to a PHP file which is hosted on a server. We found a Firebase REST API that worked with PHP. This allowed us to transfer the data that was sent to the PHP file to the database associated with the app.  

#####  4.6 Arduino Uno began to run out of internal memory due to the program.  

_**Problem:**_ After we believed our product was working successfully, we soon found out that the program on the Arduino would stop running after three loops. We figured out that the arduino was running out of internal memory. The Arduino Uno only has two KiloBytes of dynamic memory. After the compilation of the program, 48% of that memory was already used so the rest quickly filled up due to variables which resulted in the halting of the program.  

_**Resolution:**_ We began to research a way to reduce the program size and release the memory back to the memory pool after use. We found a solution to reduce the program size. We removed testing print statements as we discovered they use a large amount of memory. We also discovered a macro that allowed the Arduino to not save string literals in memory. As all of our commands were string literals, this also reduced the size of the program by a significant amount. There were no clear cut ways to release memory used back to the memory pool. However, there was a work around. Memory would be released after the Arduino is reset. We therefore created a function that would reset the program after every loop. Making these changes made the program size reduce from using 48% of memory to 23% of memory and it no longer uses all of the memory due to it being reset after every loop.  

-----------------------------------------------------------------------

#### 5. Installation Guide

##### 5.1 List of Software and Hardware Required

###### Software

  * Android Studio 3.6.1  
  * Arduino Software (IDE) 1.8.12

###### Hardware

  * Computer
  * Arduino Uno
  * SIM 808 GSM/GPRS/GPS Shield
  * GSM and GPS Antennas that accompany the SIM 808 GSM/GPRS/GPS Shield
  * Android Phone
  * SIM Card (Capable of 2g Connectivity)
  * Power Supply
  * A DC power cable or a Male USB A to Male USB B Connection (Depending on Power Supply)

##### 5.2 Assembling the Arduino and Other Components
###### Steps:

  1. Remove packaging of all required hardware listed above  
  2. Insert SIM card into the SIM card slot located on the underside of the SIM 808 Shield.  
  3. Connect the Arduino Uno and SIM 808 Shield by inserting the Shield into the Arduino Uno via the pin slots.  
  4. Connect antennas to their corresponding connection slots.  
  5. Connect the Arduino to a computer via the required USB cable to upload code to Arduino. This process is described in 5.4.  
  6. After the connection is successful, hold the power button that is located upon the SIM 808 shield until there are three green lights visible.  
  7. The device is now assembled and ready for code to be uploaded to it.  
  8. Once the code is uploaded to the device, it will need 3 - 5 minutes either outside or beside a window to calibrate its location correctly.  

#####  5.3 Android Studio and Android App Installation Guide.  
###### Steps:

  1. Install Android Studio from the following website: [https://developer.android.com/studio][downloadAndroidWindows]. If you are using Linux, follow this link: [https://developer.android.com/studio/install][downloadAndroidLinux].
  2. Download the following gitlab repo as a .zip file: [https://gitlab.computing.dcu.ie/kennea55/2020-ca326-akennedy-wheresmydoggo][downloadRepo]. 
  3. Extract the .zip file into a new folder.
  4. Open Android Studio.
  5. Go to File in the top left 
  6. Click on open to open PetWatch as an Android Studio Project.
  7. Plug Android Phone into Computer.
  8. Allow the computer access to the phone
  9. The phone should appear in the top right corner as a runnable device.
  10. Press the play button located in the top right corner in Android Studio.
  11. If successful, the app should have successfully installed.

#####  5.4 Arduino Software IDE Installation Guide and Upload of Program.  
###### Steps: 

  1. Install the Arduino IDE from the following Website: [https://www.arduino.cc/en/main/software][downloadArduinoIDE]. Links for all Operating Systems are located here.
  2. Follow steps 2 and 3 of 5.3 if not already done so.
  3. Upon launching the Arduino IDE, go to file located in the top left of the Arduino IDE
  4. Press open and go to _"2020-ca326-kennedy-where's my doggo/code/gpsDataToDatabase"_ within the extracted folder
  5. Open the file called gpsDataToDatabase.ino located in this directory within the Arduino IDE. 
  6. 5.2 needs to be completed before continuing.
  7. Plug the assembled Arduino into the computer.
  8. If the IDE is being run on Linux, permission errors could crop up in relation to allowing the arduino access to the USB port on the computer. If they do, run the following command: _“sudo chmod a+rw /dev/ttyACM0”_. The last digit could vary. The IDE will highlight what the final digit is.
  9. The last step that’s left to do is press the upload button. It’s located in the top left of the IDE.
  10. If you want to view the result of the code, this can be done through the Serial Monitor. This is accessible by pressing _“ctrl + shift + m”_.


----------------------------------------------------------------------------------------------

#### 6. Testing

##### 6.1 User Testing

  Due to users needing the device physically present for testing, the developers were on site, in another room for the evaluations. This allowed the participants to ask questions should they wish while maintaining their privacy. Most participants approached the developers afterwards to give additional feedback.
  Findings from the evaluations were:  

###### Negative: 
  
  + The app tends to crash when switching back and forth between the map and speed fragments quickly.
  + The back button in the Change Tracker ID section was not working.
  + Passwords were not accepted unless they were 6 characters or more, which is not communicated to the user when they enter a password of less than 6 characters.
  + Not intuitive as to how to sync pet device with the app after creating an account.

###### Positive: 

  + The layout, colour scheme and fonts chosen were favourable to the users. 
  + The app worked as they’d expected.
  + Setting up the device was very easy for most. Participants with less technical experience were slightly intimidated by the device at first, but reported being surprised at the simplicity of it. When asked if they would be comfortable setting it up by themselves with no manual or technical help in future, the vast majority said yes.
  + Users found setting up an account easy - very easy.
  + Users found syncing the app with the pet device easy - very easy.

###### Neutral: 

  + Users found the speed functionality of the app less important than being able to locate their pet on the map.
  + Could be improved by being brought straight to the map fragment after signing up successfully 


##### 6.2 Unit Testing

*Espresso:* We tested the UI of the app through unit testing using the framework Espresso. We created tests for all UI features on the app. This was done using JUnit. They went successful, all tests passed bar two, the two password fields in the sign up unit test. We also discovered at the beginning of testing that any button that had any links to the database would fail as the testing framework would not allow enough time for it to contact the database. This meant that we had to remove any test cases that involved clicking a button that was linked with a feature located outside of the app. This was rectified upon the completion of integration testing.  

##### 6.3 Integration Testing

  Integration testing was also completed using Espresso and JUnit. All the earlier tests involving clickable buttons started to succeed while implementing the integration tests. This allowed us to test all aspects of the UI and ensured the app and the database were working correctly.  


------------------------------------------------------------------------------------------------

[sysArchDiagram]: ./Images/SystemArchDiagramTechSpec.jpg "System Architecture Diagram"
[DFD1]: ./Images/DFDTeachSpec.jpg "Data Flow Diagram"
[DFD2]: ./Images/DFD2TechSpec.jpg "Data Flow Diagram"
[compDiagram]: ./Images/ComponentDiagramTechSpec.jpg "Component Diagram"
[downloadAndroidWindows]: https://developer.android.com/studio
[downloadAndroidLinux]: https://developer.android.com/studio/install
[downloadRepo]: https://gitlab.computing.dcu.ie/kennea55/2020-ca326-akennedy-wheresmydoggo. 
[downloadArduinoIDE]: https://www.arduino.cc/en/main/software
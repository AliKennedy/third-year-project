### 0. Table Of Contents

#### 1. Introduction

  1.1 Overview  
  1.2 Business Context  
  1.3 Glossary  

#### 2. General Description

  2.1 Product/System Functions  
  2.1.1 Hardware Interfaces  
  2.1.2 Communication Interfaces  
  2.2 User Characteristics and Objectives  
  2.3 Operational Scenarios  
  2.4 Constraints  

#### 3. Functional Requirements

  3.1 Finding a Pet Wearable Device  
  3.2 Being Able to Decode NMEA Data  
  3.3 Accurately Representing Device Location On Map  
  3.4 Updating Device Location in Real Time  
  3.5 Sending Alerts to other Users Upon Press of the Panic Button  
  3.6 Retrieving The Animal's Speed  

#### 4. System Architecture

  System Architecture Diagram

#### 5. High Level Design

  Data Flow Diagrams  
  Component Diagram

#### 6. Preliminary Schedule

  Gantt Chart

#### 7. Appendices

--------------------------------------------------------------------------------------------------

### 1. Introduction
#### 1.1 Overview

  The product to be developed is a pet wearable technology device with an accompanying Android application.

  The American Humane Association estimates that ten million pets are lost every year in the United States alone. The American Society Preventing Cruelty to Animals also estimates that each year, 1.5 million shelter animals are euthanized in the United States. The system we will develop aims to decrease the number of family pets being euthanized in animal shelters such as these, while also reuniting owners with their beloved companions.

  The system contains two major components; a pet wearable device that will be developed using a modified Arduino Uno Rev 3 microcontroller and an Android mobile application. The minimum viable product will allow us to gather unique insights on the pet in real time, such as location and speed, from the Arduino board that will then be displayed to the user via the Android application. Additional features we hope to incorporate include weather updates, multi-user capability, battery life detection, power saving and a ‘panic button’ to alert others if their pet has gone astray.

  In the following document, the terms ‘pet device’, ‘pet wearable device’, ‘Arduino device’ and ‘Arduino’ all refer to the microcontroller and its relevant components that is worn by the pet. These terms are used interchangeably throughout. This also applies to the terms ‘shield, ‘GSM shield’, ‘GPS shield’, ‘Bluetooth shield’.


#### 1.2 Business Context

  With commercialisation the prototype pet wearable device we are creating could be manufactured to a much smaller size, allowing the device to be more discreet and comfortable for the animal to wear.

  Profit could be made from a subscription service as well as the purchase of the device. The device contains a SIM card which needs to be topped-up in order to stay connected to the internet. An elevated monthly fee could be taken from the customer to keep the device active online, while also retaining a profit for the developing company.

  The target audience for the product is pet owners, particularly in urban areas where being reunited with your pet is less likely due to higher levels of human interference.

#### 1.3 Glossary

  * _**Arduino**_ - Arduino Uno Rev 3 microcontroller.
  * _**Shield**_ - External component added to the Arduino Uno Rev 3 to give it added functionality. Namely a GPS receiver and a GSM module
  * _**GPS**_ - Global Positioning System - A satellite based navigation system.
  * _**GSM**_ - Global System for Mobile Communications - Provides protocols for digital cellular networks used by portable devices such as mobile phones.
  * _**NMEA Data**_ - Acronym for National Marine Electronics Association. Standard data format supported by all GPS manufacturers.
  * _**CEP**_ - Circular Error Probable. Measure of accuracy. Gives the median error radius.
  * _**Power Bank**_ - Portable, external, rechargeable power source used to power the Arduino board.
  * _**SMS**_ - Short Message Service. Commonly known as a text message.
  * _**ISTQB**_ - International Software Testing Qualifications Board
  * _**ioT**_ - Internet of Things.

---------------------------------------------------------------------------------------------------

### 2. General Description
#### 2.1 Product/System Functions

**-User Functionality-**

  The user will access the Android app through their mobile device. The user will be asked to log in with their credentials, or create an account. If it is their first log in, they will be asked to synchronise with their pet’s device through bluetooth. Through the app they will be able to view their pet’s location and speed in real time. They will also have the capability of alerting others their pet has gone astray via the panic button.

**-Device App Functionality-**

  The GPS GSM Bluetooth shield attached to the Arduino board will allow the Arduino board to intercept GPS signals. The shield also has a GSM module, allowing for internet connectivity and a Bluetooth module, allowing the pet wearable device to communicate directly with the user’s mobile device. Both the user’s mobile device and the pet wearable device will communicate over the internet through a server. Essentially, we are designing an internet of things system. 

##### 2.1.1 Hardware Interfaces

  The system uses a moderate amount of hardware in its implementation. The user’s Android mobile device will connect with a server, which in turn will connect to the pet wearable device. The pet’s device is composed of the Arduino Uno Rev 3 microcontroller, which is physically connected to the SIM808 GPS/GPRS/GSM shield that contains a working SIM card. The shield also supports Bluetooth 3.0. The pet wearable may also incorporate an accelerometer. This however, does not come plug-and-play and will have to be soldered by hand. The inclusion of the accelerometer will be dependent on our constraints (time, hardware, cost). In the event we are unable to include the accelerometer, we will calculate the animals speed through mathematics, using the NMEA data.

##### 2.1.2 Communication Interfaces

  The Arduino device will be programmed to have a unique 256-bit key identifier. This key will be passed to the user’s application via bluetooth upon first set up. This key will be used to link the user and the pet wearable in the server. The user will communicate with the pet wearable device (and vice versa) through the server over internet connection. The user’s mobile device can connect to our server using either wifi or 3G/4G, while the pet’s device will communicate with the server using its 2G SIM card.

#### 2.2 User Characteristics and Objectives

  The user community would be pet owners, largely living in urban areas due to the decreased likelihood of retrieving a lost pet in built-up areas. There could be however, a minority of users living in rural areas also. Additionally, users would predominantly be over the age of 16 as this is legally required in order to have ownership of a canine.

  The expected expertise of users would be very average of the modern day person living in the first world. It is assumed that the user would be familiar with smartphones, wifi, and downloading applications from the Google Play Store to their mobile device.

  The product will be designed to be plug-and-play. Once the user has downloaded and opened the app they will be brought to a log-in screen. The user will be given the option to either sign in or create an account. If it’s the user’s first time to sign in, they will be asked to sync their account to their pet’s device via bluetooth. The log in will be completed only on the first instance the user opens the app on their Android device, with autologin taking place in future interactions. The main activity will display a map, pinpointing the pet’s location. The navigation bar will feature the panic button, a button leading to an activity to record and log the animal’s speed, and a button to bring up the settings. 

  The user will be able to quickly and easily view the current location of their pet within 2.5 meters CEP accuracy. The user will also have the ability to record the animal’s speed over a chosen time frame, view previous speeds recorded, and alert others that the pet has gone astray via the panic button.

#### 2.3 Operational Scenarios

_**Initial Set Up**_

  Once downloaded and installed, the user will open the app on the login/create account activity. If they are creating an account, they will be brought to an     activity allowing them to enter their details. Alternatively, if they have an     account they will simply log in using their username and password. They will  then be asked to synchronise their account with their pet’s wearable device.  They will require the Bluetooth on their device to be switched on and to be   within an acceptable distance to the pet’s device. Over bluetooth the pet’s     device will pass it’s unique key to the user. The user’s details and their pet’s  device’s key will be passed from the user to the server, where they will be     linked in a database, initialising a channel of communication between the two.  If this interaction succeeds, the user will be brought to the main activity of the  app. If not, they will be asked to try again.

_**Opening the App Post Login**_

  On bootup the user will momentarily see a loading screen with the PetWatch logo. The user will then be taken to the main activity, a view of the map with the navigation bar across the bottom of the screen. The map will be centered on the current location being transmitted from the GPS module, with an icon depicting the pet’s location within 2.5 meters CEP accuracy. If the device has lost power, the last known location prior to the device going offline will be displayed to the user. 

_**Pressing the Panic Button**_
  
  The panic button is located in the navigation bar at the bottom of the screen. Upon pressing the panic button, the user will be presented with a screen, asking them if they are sure they’d like to alert the listed people/accounts that the pet has gone astray. The listed accounts can be altered from the settings activity. If they choose to proceed, the other accounts associated with the pet wearable device in question will receive a push notification that the pet has gone astray, encouraging them to check the pet’s last known location in the main activity of the app.

_**Recording Speed**_

  The user will navigate to the speed activity in the navigation bar. The user will press the Start button, which will begin a timer shown on screen. The Start button will change to display the word Stop instead. When the user presses Stop, the information gathered in that time will be displayed on the screen; top speed, average speed, time and date recorded. The user will be given the option to save this data in the log. The user can toggle between recording speeds and viewing the log via the altered navigation bar at the bottom of the screen. From the log the user can view previous recorded speeds.

#### 2.4 Constraints

  * _**Time**_

    The project deadline is 5pm Friday the 6th of March 2020. As well as expected challenges of engineering all components to communicate seamlessly (the app, server, and pet wearable device) this project contains the additional challenge of maintaining the hardware of the pet wearable. Should any parts become damaged or fail to operate we must promptly acquire new ones and integrate them with the existing system.

  * _**Hardware**_

    Our project is relatively expensive compared to other projects in our year group. This is due to the purchase of hardware for the pet wearable device. This includes the Arduino Uno Rev 3 microcontroller, SIM808 GPRS/GSM/GPS shield and accessories such as programmable LEDs, resistors and accelerometer.
    
    The incorporation of an accelerometer also poses the challenge of soldering.

  * _**Learning a New Language**_

    A constraint that is upon the two of us is learning a new language in the form of Arduino programming. Its language is similar to that of the C language and as neither of us are familiar with this language, it will involve us learning the language from scratch.

--------------------------------------------------------------------------------------------------

### 3. Functional Requirements

  The requirements’ criticality has been ranked on the ISTQB’s scale referenced at the bottom of the document.

#### 3.1 Finding The Pet Wearable Device

_**Description**_

  On initial start up of the system, the user’s mobile device and the pet wearable device must establish a channel of communication. This will be accomplished through the use of a unique 256-bit key given to the Arduino device before start up of the system. The Arduino will share its key with the user through Bluetooth. The user’s information along with the key will be     passed to the server from the user’s app, creating a relation between the user and the pet’s device.

_**Criticality**_

  Critical

_**Technical Issues**_

  The user’s mobile device must have Bluetooth capability and be within an acceptable distance of the Arduino device.

_**Dependencies On Other Requirements**_

  N/A

#### 3.2 Being Able to Decode The NMEA Data

_**Description**_

  The Arduino microcontroller along with the GPS module must be able to decode the NMEA data retrieved from the GPS satellites. Otherwise, when a user starts their device and requests their pets location, nothing will appear if the pet wearable is unable to decode the data correctly. Therefore the project is critically dependent upon this requirement.

_**Criticality**_

  Critical

_**Technical Issues**_

  The GPS antenna is light and flimsy so if we do not take measures to protect it correctly, we run the risk of losing functionality of this vital component.

_**Dependencies On Other Requirements**_

  N/A


#### 3.3 Accurately Representing Device Location On Map

_**Description**_

  The shield connected to the Arduino board contains a GPS receiver and an antenna. Once powered on, the GPS is preprogrammed to calculate its location in relation to GPS satellites circling the globe. It returns its location in the form of NMEA data. Each piece of data returns the device’s current latitude and longitude, as well as other information such as a time stamp. We  must process this data correctly and make an icon representing the pet appear on the map in the specified location. It must also be satisfying on the eye for the user and easy to decipher.


_**Criticality**_

  Critical

_**Technical Issues**_

  N/A


_**Dependencies On Other Requirements**_

  This requirement depends on the requirements above, 3.1 and 3.2. If the pet   wearable device and the user’s app cannot communicate with each other it  will be impossible to display the pet’s location to the user.

#### Updating Device Location In Real Time

_**Description**_

  The pet wearable device must send frequent NMEA data to the server in order to represent the pet’s location with the best accuracy.

_**Criticality**_

  Major

_**Technical Issues**_

  Over frequency of sending the pet’s location to the server can result in congestion on the network as well as using up additional memory. Under frequency of sending the pet’s location to the server will mean the data is less  accurate in portraying the true location of the lost pet.


_**Dependencies On Other Requirements**_

  This requirement is dependent on the above requirements, 3.1 and 3.2 and 3.3.

#### 3.5 Sending Alerts to other Users Upon Press of the Panic Button

_**Description**_

  Users will be linked to pet wearable devices through a relational database on the server. When one user linked to a certain pet wearable device presses the Panic button, a message will be sent to the server, instructing a push notification to be sent to all other user accounts associated with the pet device in question.

_**Criticality**_

  Minor

_**Technical Issues**_

  All components of the system must be in working order and communicate with each other effectively in order for this requirement to be achieved.

_**Dependencies On Other Requirements**_

  This requirement depends on requirement 3.1 above.

#### 3.6 Retrieving The Animal’s Speed

_**Description**_

  The user must be able to gather information on the pet’s speed at any given time. Given time and hardware constraints this will either be achieved through the use of an accelerometer module or by calculating the distance travelled over a certain amount of time using GPS data.

_**Criticality**_

  Minor

_**Technical Issues**_

  The accelerometer module is not plug-and-play and will have to be soldered by hand.

_**Dependencies On Other Requirements**_

  This requirement depends on requirements 3.1, 3.2 and 3.3 above.


--------------------------------------------------------------------------------------------------

### 4. System Architecture

![alt text][SystemArchitecture]

**Fig 4.1** above illustrates the basic architecture of the system. The user first downloads the application onto their mobile device, and then connects to the pet wearable device through  bluetooth. The pet’s device sends its unique key/identifier to the user using this medium. The user then sends its own information with the key to the server, to create a relation between the two, thus opening a channel of communication between the two through the server. The pet wearable device, once powered on, is programmed to send its location automatically through the server, to be displayed to the user on the app. It deciphers its location through the shield, which has a GPS receiver and antenna. It then uses the shields GSM functionality, along with an active SIM card, to send this information to the server. The application fetches this data from the server, and represents it on the map visually to the user.

--------------------------------------------------------------------------------------------------

### 5. High-Level Design


![alt text][DFD1]

**Fig 5.1** above is a Data Flow Diagram in relation to when the user creates an account and syncs with the pet wearable device. The only entity is the user. They start by sending their credentials to the server, which adds these details to the user database, D1. The Arduino takes its unique key from its internal storage and passes it the user. The user then sends their username and the unique key of their pet wearable device to the server. The server creates a relation between the two, and stores it in their relevant databases, D1 and D3. When this process is complete a success or fail message is then returned to the user. 

![alt text][DFD2]

**Fig 5.2** above depicts a Data Flow Diagram of the pet wearable device sending the pet’s location to the user. Data from a number of satellites is received by the GPS module and is used to produce NMEA data. This data is sent to the Arduino for processing. The Arduino then sends the converted data, determining a location, to the server. It is stored in a file on the server and then sent to the user.

![alt text][ComponentDiagram]

**Fig 5.3** above is a component diagram. It shows all the necessary components for the system to function correctly. The GPS module gets the NMEA data from the GPS satellites and is then passed to the arduino where it gathers the latitude and longitude from this data. The latitude and longitude computed by the arduino is then acquired by the server where it gets placed into a file storage. This information, when requested by the user is displayed on the map incorporated within the app revealing their pets location.


--------------------------------------------------------------------------------------------------



### 6. Preliminary Schedule

![alt text][GantChart1]
![alt text][GantChart2]

**Fig 6.1/Fig 6.2** above outlines how we plan to spend our time in regards to the project. This chart was designed using draw.io. This is the initial plan as problems are expected to arise and interfere with the schedule This is based off estimates of how long each task is expected to take.

--------------------------------------------------------------------------------------------------



### 7. Appendices
#### Specifies other useful information for understanding the requirements.

_**References:**_

  * ““Every Day is Tag Day™” — Is Your Pet Protected?”, American Humane Association, 2019. [Online]. Available:
  [https://www.americanhumane.org/blog/every-day-is-tag-day-is-your-pet-protected/.][Reference1] 
  [Accessed: 24 - Nov - 2019].

  * “Pet Statistics”, American Society for the Prevention of Cruelty to Animals, 2018. [Online]. Available: 
  [https://www.aspca.org/animal-homelessness/shelter-intake-and-surrender/pet-statistics.][Reference2]
  [Accessed: 24 - Nov - 2019].

  * “Arduino programming for beginners-1”, HackerEarth, 2016. [Online]. Available: 
  [https://www.hackerearth.com/blog/developers/arduino-programming-for-beginners/.][Reference3]
  [Accessed: 5 - December - 2019].

  * “Defect Severity”, Software Testing Fundamentals. [Online]. Available: [http://softwaretestingfundamentals.com/defect-severity/.][Reference4] 
  [Accessed 2 - December - 2019].

  ------------------------------------------------------------------------------------------------




[SystemArchitecture]: ./Images/SystemArchitecture.png "System Architecture Diagram"
[DFD1]: ./Images/DFD1.png "Data Flow Diagram When a User Creates an Account"
[DFD2]: ./Images/DFD2.png "Data Flow Diagram Sending Pets Location to The User"
[ComponentDiagram]: ./Images/ComponentDiagram.jpg "Shows Physical Components for System to Function Correctly"
[GantChart1]: ./Images/GantChart1.jpg "First Half of the Gantt Chart Displaying our Schedule"
[GantChart2]: ./Images/GanttChart2.jpg "Second Half of the Gantt Chart Displaying our Schedule"
[Reference1]: https://www.americanhumane.org/blog/every-day-is-tag-day-is-your-pet-protected/
[Reference2]: https://www.aspca.org/animal-homelessness/shelter-intake-and-surrender/pet-statistics
[Reference3]: https://www.hackerearth.com/blog/developers/arduino-programming-for-beginners/
[Reference4]: http://softwaretestingfundamentals.com/defect-severity/
### 0. Table Of Contents

#### 1. Introduction

  1.1 Glossary.   

#### 2. Tracker

  2.1 Wiring.   
  2.2 GSM/GPRS shield and SIM card.   
  2.3 Turning The Device On.   

#### 3. Android App

  3.1 Signing Up.   
  3.2 Signing In.   
    3.2.1 Forgot Password.   
  3.3 The Main Activity   
    3.3.1 Map Fragment   
    3.3.2 Settings Fragment   
      3.3.2.1 Account   
        3.3.2.1.1 Change Password   
      3.3.2.2 Change Tracker ID   
      3.3.2.3 Privacy & Security   
      3.3.2.4 Contact Us   
      3.3.2.5 About   
    3.3.3 Speed Fragment  

------------------------------------------------------------------------------

#### 1. Introduction
##### 1.1 Glossary
  
  * _**Arduino**_ - This refers to a component of the tracker where the GSM/GPRS/GPS Shield is connected too. That part is labelled below.
  * _**GSM/GPRS/GPS Shield**_ - This refers to the component that retrieves the GPS data for the tracker using mobile data.
  * _**GPS**_ - Global Positioning System - A satellite based navigation system.
  * _**GSM**_ - Global System for Mobile Communications - Provides protocols for digital cellular networks used by portable devices such as mobile phones.
  * _**NMEA Data**_ - Acronym for National Marine Electronics Association. Standard data format supported by all GPS manufacturers.

---------------------------------------------------------------------------------

#### 2. Tracker
##### 2.1 Wiring

  The tracker device should come pre-assembled. The only item that needs to be connected manually by the user is the power supply. The power supply is connected to the green Arduino board lying at the bottom of the device. This can be done through a USB connection ( USB2.0 A-male to B-male) or to a DC power supply. A battery pack is most ideal as the tracker will most likely be mobile on a pet. When the device is connected to a power source successfully, a green light appears on the Arduino, beside the marker “ON”.

  ![Image of Tracker displaying pins][arduino1]

  If any items become disassembled during transport, they are easily put back together. The blue GSM/GPRS shield is connected to the arduino board through the pins circled in green. If the shield and arduino become disconnected, they can be reconnected by aligning the pins to the appropriate slots and pushing down from the top. The shield’s pins enter the arduinos pin slots with ease. If the power supply is connected to the Arduino, and the shield is connected successfully, a green light will appear beside the marker “PWR”.

  
  ![Image of Antennas showing connections][arduino2]

  The antennas are slightly more complicated to reassemble in the event they detach from the shield. They attach to the connectors highlighted above. The GSM antenna is long and narrow. It is solid green on one side and brown with green details on the reverse. This is attached to the shield beside the marker “GSM”. The GPS antenna is square and attached to the shield beside the marker “GPS”. 

  More force is needed to reattach the antennas in comparison to reattaching the GSM/GPRS shield, which should be done with care. Using too much force when connecting the shield may result in damage to the pins, leading to undesirable effects (or defects).


##### 2.2 GSM/GPRS/GPS Shield and SIM Card

  Within the GSM/GPRS shield, there is a designated slot to insert a SIM card. The SIM card provides a mobile service and internet connectivity. This in turn allows the shield to retrieve GPS data. The GPS data is processed by the Arduino in order to retrieve location and speed data.  

  The SIM card, like the other device components, comes preinstalled. However, in the event the SIM card becomes loose, defective, or needs to be replaced, the user must first disconnect the blue GSM/GPRS shield from the green Arduino board. This is achieved by holding the two parts at their edges and gently pulling them apart. The pins from the shield should become disattached from the slots on the Arduino. On the underside of the shield is the SIM card slot.  

  To open the SIM card slot, slide the plastic cover down, in the direction of the “OPEN” arrow indicated on it. If you wish to replace the SIM card, first turn the cover over and slide the SIM card out of the cover. A replacement SIM card can be installed by sliding it back into the cover, with the gold piece facing up. Then simply fold the cover back down, press down gently, and slide the plastic cover upwards, in the direction of the “CLOSE” arrow.  

  To reassemble the device, read the section above, 2.1 Wiring.  


##### 2.3 Turning the Device On

  The device must be connected to a power supply. See 2.1 Wiring, above. Once the power supply has been connected, the white power key along the top edge of the device must be held down for approximately 3 seconds. When this is successful, a green light will appear beside the “Status” marker. At first the LED light beside the “Netlight” marker will flash green approximately once per second. This indicates that the device is looking to connect to a mobile service. Once this has been secured, the light will flash at approximately once every 3 seconds. The device will then start to look for GPS signals. This may take several minutes. Best results can be found in outdoor areas or beside windows and doors. Once a GPS signal has been found, a blue light will begin to flash. When the Netlight LED begins to flash rapidly, the device is in the process of sending your location and speed information to the app.  

  Should this routine begin to act strangely, the user can turn the shield off and back on again by holding down the power key on the edge of the shield. Alternatively, the user can press the white reset key on the corner of the Arduino device, located beside the USB connection.  

----------------------------------------------------------------------------------

#### 3. Android App

  The app is designed for the Android platform, and so must be used on an Android device. Once the app has been downloaded and installed, it will open on the start-up page.  

  ![Image of Start Up Screen][startUpScreen]

  Here, the user will be given the option of signing in (if they already have an account) or signing up (if they do not yet have an account).  

##### 3.1 Signing Up

  When the sign up screen opens, the user is prompted to begin entering their details: first name, surname, e-mail address, pet’s name, password, and to confirm their password. Then press the SIGN UP button at the bottom of the screen.   

  ![Image of Sign Up Screen][signUpScreen]

##### 3.2 Signing In

  When the user presses the sign in button, they will be brought to a page asking them to enter their email address and password associated with their PetWatch account. The user then presses the SIGN IN button. If the details entered are correct, they will be brought to the main screen. Adversely, a message will be displayed in red, reading “Username or password incorrect”.  

  If the user does not remember their password, they may press the FORGOT PASSWORD? button at the bottom of the screen.  

##### 3.2.1 Forgot Password

  If the user presses the forgot password button, they will be brought to a new screen where they will be prompted to enter their email address and press the SEND EMAIL button. Upon clicking this button, they will receive an email with instructions on how to reset their password as shown below. The user is then brought back to the sign in screen.  

##### 3.3 The Main Activity

  Upon signing in, the app will open up onto the main activity.  

  ![Screenshot of Map][mapFragment]

  Along the bottom of the screen is the navigation bar, containing buttons for the fragments speed, map, and settings. The map fragment is set as the starting fragment i.e the app will open up on the map fragment as default upon signing in or signing up.  

##### 3.3.1 Map Fragment

  Here, the user will be able to view the location of their pet’s tracker on Google maps, denoted by a red marker, as shown above. The location will update approximately once every 30 seconds. This frequency is decreased due to external factors including harsh weather conditions, poor network coverage etc. It is recommended that when turning the tracking device on, the user places it outside or near a window to let the device calibrate. This will allow the app to find the tracker faster.  

  The map behaves in the same manner as Google Maps, denoting points of interest and having pinch zoom capability. If the tracker is turned off, loses power, does not have internet connectivity or the android device you are using does not have internet connectivity, the amp displays the last known location of the tracker.  

##### 3.3.2 Settings Fragment

  The settings fragment contains buttons to the activities: 
_account_ , 
_change tracker I.D._, 
_privacy & security_, 
_contact us_, 
_about_ and 
_log out_.  
  
  ![Screenshot of settings][settingsFragment]

  By pressing the LOGOUT button at the bottom of the screen, the user’s session is ended, they are logged out of the app, and brought back to the start up screen (see 3 Android App).  

##### 3.3.2.1 Account

  The account activity displays the user’s name, pet name and tracker ID. This is useful if the user would like to share the tracker ID with a friend or relative, so as they could also track the pet using the PetWatch app. A button to change the user account’s password is also displayed at the bottom of the screen.  

  ![Screenshot of Account][AccountScreen]

##### 3.3.2.1.1 Change Password

  The user is displayed three text boxes to edit: Old Password, New Password and Confirm Password. If they enter the details correctly they will successfully change their password. If not, they will be shown an error message on screen in red.  

##### 3.3.2.2 Change Tracker I.D.

  This section allows the user to change the pet device they are tracking. Each device comes with a unique ID. In commercial production, this would be visible on the device itself and on the box. For the user to track their pet, they must go to this screen, enter their pet device’s ID, and press UPDATE TRACKER I.D. The user will do this once, when they first create an account. They may also do this when they get a new pet device or would like to track a different animal. More than one user account can track a single pet device.  

##### 3.3.2.3 Privacy & Security

  This activity displays information about GDPR, how user data is being stored, and a button linking to the Contact Us activity (see 3.3.2.4) should users wish to request any more information.  

##### 3.3.2.4 Contact Us

  The contact us activity contains a simple form, with the text boxes name, email, subject and message, for the user to fill out. Upon pressing the “SUBMIT” button, which appears on top of the keyboard, the user is prompted to select a mailing app by the Android OS. Once the mailing app has been chosen, an email template will appear, with the relevant details they entered on the form. Should they choose to send the email, it will be sent to one of the developers.  

  ![Screenshot of contact us form][ContactUsScreen]

##### 3.3.2.5 About

  The About activity contains information about the app, why it was developed, and who it was developed by. There is also a button linking to the Contact Us activity (see 3.3.2.4).  

##### 3.3.3 Speed Fragment

  This fragment allows the user to view the speed the animal is moving at. This is achieved by measuring the distance the animal has covered between two location updates against the time taken for the location to update. When the user hits the START button, it will be replaced by the STOP button. If the location has not updated in this time frame, they will be given a speed of 0 km/h. If the location has updated more than once, the user will be shown the maximum of the speeds reached between location updates.  


  ![Screenshot of start timer][speedFragment1]  
  ![Screenshot of stop timer][speedFragment2]
  ![Screenshot of result speed][speedFragment3] 

-----------------------------------------------------------------------------------------------


[arduino1]: ./Images/arduino1.jpg  "Image of Tracker displaying pins"
[arduino2]: ./Images/arduino2.jpg  "Image of Antennas showing connections"
[startUpScreen]: ./Images/startUpScreen.jpg "Image of Start Up Screen"
[signUpScreen]: ./Images/signUpScreen.jpg "Image of Sign Up Screen"
[mapFragment]: ./Images/mapFragment.jpg "Screenshot of Map"
[settingsFragment]: ./Images/settingsFragment "Screenshot of settings"
[AccountScreen]: ./Images/accountScreen.jpg "Screenshot of Account"
[ContactUsScreen]: ./Images/contactUsScreen.jpg "Screenshot of contact us form"
[speedFragment1]: ./Images/speedFragment1.jpg "Screenshot of start timer"
[speedFragment2]: ./Images/speedFragment2.jpg "Screenshot of stop timer"
[speedFragment3]: ./Images/speedFragment3.jpg "Screenshot of result speed"

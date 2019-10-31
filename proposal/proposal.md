## School of Computing
## CA326 Year 3 Project Proposal Form

### SECTION A

#### Project Title: Pet Watch

**Student 1 Name:** Alison Kennedy **ID Number:** 17303133  
**Student 2 Name:** Ethan Sharkey  **ID Number:** 17355756  

**Staff Member Consulted:** Steve Blott

  
**Project Description (1-2 pages):**

1. **Description - Minimum 250 word description of the proposed project**

 Our proposed project is a pet wearable technology device created using an Arduino board and an Android app to accompany it, aimed at dogs.

 Using the Android app which will be developed using Java and XML, the user will be able to access information on their dog that otherwise wouldn’t be possible. Our proposed features include:

  * _A map pinpointing the dog's location_

    We will incorporate the Google Maps framework and attach a shield to the arduino board with GSM and GPS capability to give us the most accurate and up-to-date data.

  * _Multi-user capability_

    Multiple users can create an account that links to the one pet , allowing data to be shared between pet owners instantly and seamlessly. The database we will develop to allow this to happen will be written in SQL.

  * _Battery life detector_

    From the user's mobile device, they will be able to view the battery life of the pet wearable, avoiding unexpected outages. The arduino board and its components will include a resistor enabling us to measure decreasing voltage.

  * _Weather_

    Users will be able to view the weather in their pet's current location on the app. Weather will be implemented in the app through the use of Met Éireann's public datasets.

  * _Speed_

    An accelerometer in the device will allow us to capture the speed the dog is travelling at, in any given moment.

  * _Panic Button_

    The application will include a 'panic button' which can be used in the event of the dog going missing or getting out. This feature will send a push notification to the other users linked to the dog which will display an alert message that the dog has been reported missing.

  * _Power Saving_

    We can use a resistor in the hardware to limit the amount of power given to the arduino board so as there is no power wastage, meaning longer battery life for the user.

 The key features of the app will be the GPS location displayed on the map, speed and the panic button as we believe these would be the most prolfic for the end users. The other features discussed would become time-dependent in line with the rest of the project.
 

2. **Division of Work - Outlines how the work is envisaged to be split equally among the team members.**  

 **Hardware:**

   Assembly of Arduino Board, shield and other components in working order. **- Ethan** 

   Programming of the Arduino Board:

    GPS **- Ali**  
    Resistor **- Ethan** 
    Accelerometer **- Ali** 
    Networking **- Ethan**

 **Backend:**

   After completing the analysis, product design and class design phases together, we will split the proposed classes as **evenly** as possible.

   Development of the database and handling of queries from frontend to backend and vice versa **- Ali**

 **Frontend:**

   We will develop a user survey together and take action to push the survey to members of the public. We will meet with our findings and make decisions on the continuing development of the UI based off of these.

   We will individually wireframe a proposed UI for the app combine the best features of the two into one concept. From here we will create a template for the main features (navbar etc.) together in XML and split the app pages evenly to implement in our own time.

   Networking **- Ali**

   Google Maps implementation **- Ali**

 **Testing:**

   Testing will be the responsibility of the developer before pushing a new feature to the gitlab repository.
  
  
3. **Programming language(s) - List the proposed language(s) to be used:**

 * Arduino
 * Java
 * MySQL
 * XML


4. **Programming tool(s) - List tools(compiler, database, web server, etc.) to be used:**

 * SQL Database
 * Android Studio
 * DCU's School of Computing server


5. **Learning Challenges - List the main new things (technologies, languages, tools, etc) that you will have to learn:**

 * XML
 * Arduino Programming
 * GPS technology
 * GSM technology
 * Google Maps framework and API
 * App development


6. **Hardware / software platform - State the hardware and software platform for development, eg. PC, Linux, etc.**

 * PC
 * Linux (Ubuntu, OpenSUSE)
 * Android
 * Arduino Board
 * GSM and GPS shield
 * Arduino Programming
 * Git
 * Sublime Text
 * Arduino Software (IDE)
 * Android Studio


7. **Special hardware / software requirements - Describe any special requirements.**

 * Arduino Board(Uno)
 * GSM and GPS shield
 * Extra components - LED light, Resistor, Sim Card
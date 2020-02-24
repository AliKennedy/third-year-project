
#include <SoftwareSerial.h>
SoftwareSerial mySerial(7, 8);



void setup() {
  // put your setup code here, to run once:
  mySerial.begin(9600);
  Serial.begin(9600);

  Serial.println("Config SIM808...");
  delay(2000);
  Serial.println("Done!...");
  mySerial.flush();
  Serial.flush();

  while(!mySerial.println("AT+CGPSPWR=1"))  {
  Serial.println("not yet");
  delay(1000);
  }
  Serial.println("GPS power on");

}

void loop() {
  // put your main code here, to run repeatedly:
  char dataBuffer[64];
  int dataCount = 0;
  char endOfArray = "\0";

  mySerial.println("AT+CGPSINF=2");
  delay(5000);
  while( (mySerial.available()!= 0) && (dataCount<63) )  { //take data from buffer and put it in array 1 by 1
    dataBuffer[dataCount] = mySerial.read();
    dataCount++;
    dataBuffer[dataCount] = endOfArray; //stick terminating character at end of array

  }
  dataBuffer[dataCount] = endOfArray; //stick terminating character at end of array

  Serial.println(dataBuffer);
  dataCount = 0;
  char *output;
  output = strtok(dataBuffer,":");

  char *latitude;
  char *latdirection;
  char *longitude;
  char *longdirection;

  
  int field = 0;
  while (output != endOfArray)
  {
    field ++;
    output = strtok(endOfArray,",");
    if (field == 3)
      latitude = output;
    else if (field == 4)
      latdirection = output;
    else if (field == 5)
      longitude = output;
    else if (field == 6)
      longdirection = output;
   
    
  }
  
  Serial.println(longitude);
  Serial.println(longdirection);
  Serial.println(latitude);
  Serial.println(latdirection);
  
}



void toSerial()
{
  while(mySerial.available()!=0)
  {
    Serial.write(mySerial.read());
  }
}

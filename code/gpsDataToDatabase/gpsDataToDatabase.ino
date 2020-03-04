class __FlashStringHelper;
#define F(string_literal) (reinterpret_cast<const __FlashStringHelper *>(PSTR(string_literal)))

void(* resetFunc) (void) = 0;

#include <SoftwareSerial.h>
SoftwareSerial mySerial(7, 8); //rx and tx pins for shield

#define deviceID "uivwA"
#define pathLat "uivwA/lat"
#define pathLng "uivwA/lng"

void setup()  
{
  mySerial.begin(9600);
  Serial.begin(9600);
  Serial.println(F("Config SIM808..."));
  
  delay(1000); //give it a second to start up
  
  Serial.println(F("done!"));


  while(!mySerial.println(F("AT+CGPSPWR=1")))  
  {
    delay(200);
  }
  
  Serial.println(F("GPS power on")); 
  setupGSM(); 
}


void loop() 
{
  String latitude;
  String longitude;
  
  char dataBuffer[64];
  char* endOfArray = "\0";

  mySerial.println(F("AT+CGPSINF=2")); //Get GPS data
  delay(1500);

  while( mySerial.available()== 0 ) 
  {
     mySerial.println(F("AT+CGPSINF=2")); //Try again
     delay(1000);
  }
  
  int dataCount = 0;
  while( (mySerial.available()!= 0) && (dataCount<63) )  
  { 
    //take data from buffer and put it in array 1 by 1
    dataBuffer[dataCount] = mySerial.read();
    dataCount++;

  }
  dataBuffer[dataCount] = endOfArray; //stick terminating character at end of array

  delay(2000);
  char tester = dataBuffer[41];
  
  if (tester == '0' || tester == 'F')  
  {
    resetFunc();
  }

  char *output;  
  output = strtok(dataBuffer,"W");

  int field = 0;
  while (field < 60)
  {

    if ((field >= 38) && (field <= 46))
    {
        latitude += output[field];
    }
    else if ((field >= 50) && (field <= 59))
    {
      longitude += output[field];
    }

    field ++;
  }

  delay(1500);
  Serial.println(longitude);
  //delay(5000);
  Serial.println(latitude);

  clearIncomingBuffer();
  mySerial.flush();

  sendToDatabase(longitude, latitude);

  resetFunc();
}
 
void sendToDatabase(String longitude, String latitude)
{
  // send the data 
  Serial.println(F("Sending data"));
  
  mySerial.println(F("AT+HTTPINIT"));
  clearIncomingBuffer();
  delay(2000); 

  mySerial.println(F("AT+HTTPPARA=\"CID\",1"));
  clearIncomingBuffer();
  delay(1000);

  String url = "AT+HTTPPARA=\"URL\",\"http://student.computing.dcu.ie/~kennea55/firebaseTest.php?longitude=";
  url += longitude; 
  url += "&&latitude=";
  url += latitude;
  url += "&&firebasePathLat=";
  url += pathLat;
  url += "&&firebasePathLng=";
  url += pathLng;
  url +=  "\"";

  Serial.println(url);
  delay(1000);
  
  mySerial.println(url);
  clearIncomingBuffer();
  delay(1000);
 
  mySerial.println(F("AT+HTTPACTION=0"));
  clearIncomingBuffer();
  delay(1000);

  mySerial.println(F("AT+HTTPTERM"));
  clearIncomingBuffer();
  delay(1000);

  mySerial.println("");
  clearIncomingBuffer();
  delay(1000);

  mySerial.flush();
  clearIncomingBuffer(); 
}

void setupGSM() 
{
  mySerial.println(F("AT+CGATT?"));
  clearIncomingBuffer();
  delay(1000);

  mySerial.println(F("AT+CPIN?"));
  clearIncomingBuffer();
  delay(1000);
  
  mySerial.println(F("AT+SAPBR=0,1"));
  clearIncomingBuffer();
  delay(1000);

  mySerial.println(F("AT+SAPBR=3,1,\"CONTYPE\",\"GPRS\""));
  clearIncomingBuffer();
  delay(1000);

  mySerial.println(F("AT+SAPBR=3,1,\"APN\",\"isp.vodafone.ie\""));
  clearIncomingBuffer();
  delay(1000);

  mySerial.println(F("AT+SAPBR=3,1,\"USER\",\"vodafone\""));
  clearIncomingBuffer();
  delay(1000);

  mySerial.println(F("AT+SAPBR=3,1,\"PWD\",\"vodafone\""));
  clearIncomingBuffer();
  delay(1000);
  
  mySerial.println(F("AT+SAPBR=1,1"));
  clearIncomingBuffer();
  delay(1000);

  mySerial.println(F("AT+SAPBR=2,1"));
  clearIncomingBuffer();
  delay(1000);

  mySerial.flush();
  clearIncomingBuffer();
}

void clearIncomingBuffer()
{
  while (mySerial.available())
  {
    mySerial.read();
  }
}

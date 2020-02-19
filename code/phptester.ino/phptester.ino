
#include <SoftwareSerial.h>
SoftwareSerial gprsSerial(7, 8);

void setup()
{
  gprsSerial.begin(9600);
  Serial.begin(9600);

  Serial.println("Config SIM808...");
  delay(2000);
  Serial.println("Done!...");
  gprsSerial.flush();
  Serial.flush();

  // attach or detach from GPRS service 
  gprsSerial.println("AT+CGATT?");
  delay(100);
  toSerial();
  Serial.println("Add max response time");

  gprsSerial.println("AT+CPIN?");
  delay(1000);
  toSerial();

  gprsSerial.println("AT+SAPBR=0,1");
  delay(2000);
  toSerial();
  // bearer settings
  gprsSerial.println("AT+SAPBR=3,1,\"CONTYPE\",\"GPRS\"");
  delay(2000);
  toSerial();

  // bearer settings
  gprsSerial.println("AT+SAPBR=3,1,\"APN\",\"isp.vodafone.ie\"");
  delay(2000);
  toSerial();
  Serial.println("APN");

  gprsSerial.println("AT+SAPBR=3,1,\"USER\",\"vodafone\"");
  delay(2000);
  toSerial();
  Serial.println("APNU");

  gprsSerial.println("AT+SAPBR=3,1,\"PWD\",\"vodafone\"");
  delay(2000);
  toSerial();
  Serial.println("APNP");

  // bearer settings
  
  gprsSerial.println("AT+SAPBR=1,1");
  delay(6000);
  toSerial();

  gprsSerial.println("AT+SAPBR=2,1");
  delay(2000);
  toSerial();


}
void loop()
{
   // initialize http service
   gprsSerial.println("AT+HTTPINIT");
   delay(6000); 
   toSerial();

   gprsSerial.println("AT+HTTPPARA=\"CID\",1");
   delay(2000);
   toSerial();

   // set http param value
   gprsSerial.println("AT+HTTPPARA=\"URL\",\"http://student.computing.dcu.ie/~kennea55/hello.php?name=ali\"");
   delay(6000);
   toSerial();

   // set http action type 0 = GET, 1 = POST, 2 = HEAD
   gprsSerial.println("AT+HTTPACTION=0");
   delay(6000);
   toSerial();



   // read server response
   gprsSerial.println("AT+HTTPREAD"); 
   delay(1000);
   toSerial();

   gprsSerial.println("");
   gprsSerial.println("AT+HTTPTERM");
   toSerial();
   delay(300);

   gprsSerial.println("");
   delay(10000);
}

void toSerial()
{
  while(gprsSerial.available()!=0)
  {
    Serial.write(gprsSerial.read());
  }
}

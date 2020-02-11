
#include <DFRobot_sim808.h>
#include <SoftwareSerial.h>

#define PIN_TX    7
#define PIN_RX    8
SoftwareSerial mySerial(PIN_TX,PIN_RX);
DFRobot_SIM808 sim808(&mySerial);//Connect RX,TX,PWR,


void setup() {
  boolean DEBUG = true;
  mySerial.begin(9600);
  Serial.begin(9600);

 sendData("AT+CCID",3000,DEBUG);
 sendData("AT+CREG?",3000,DEBUG);
 sendData("AT+CGATT=1",1000,DEBUG);
 sendData("AT+CGACT=1,1",1000,DEBUG);
 sendData("AT+CSTT=CMNET",3000,DEBUG);
 sendData("AT+CIICR",1000,DEBUG);
 sendData("AT+CIFSR",1000,DEBUG);
 sendData("AT+CDNSGIP=\"www.sim.com\"",1000,DEBUG);
 sendData("AT+CDNSGIP=\"a b c d e f\"",1000,DEBUG);
 sendData("AT+CIPSTART=\"TCP\",\"WWW.SIM.COM\",80",5000,DEBUG);
 delay(10000);
 sendData("AT+CIPSEND=6",1000,DEBUG);
 sendData("123456",1000,DEBUG);
 delay(2000);
 sendData("AT+CIPCLOSE",1000,DEBUG); 
}

void loop() {
 
}

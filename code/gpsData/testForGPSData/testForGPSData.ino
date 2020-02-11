#include <SoftwareSerial.h>
#include <DFRobot_sim808.h>

#define PIN_TX 7
#define PIN_RX 8

SoftwareSerial SIM808(7, 8); //(RX-Pin,TX-Pin)
SoftwareSerial mySerial(PIN_TX, PIN_RX);
DFRobot_SIM808 sim808(&mySerial);//Connect RX,TX,PWR,


void setup() {
  mySerial.begin(9600);
  Serial.begin(9600);
  SIM808.begin(9600);
}

void loop() {
  sim808.attachGPS();
  if (SIM808.available())
    Serial.write(SIM808.read());
  if (Serial.available())
    SIM808.write(Serial.read());

}

void setup() {
  // put your setup code here, to run once:
  pinMode(13, OUTPUT); // LED is in pin 13

}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(13, HIGH); // Turn the LED on
  delay(200);            // Wait - Makes it flash
  digitalWrite(13, LOW);  // Turn LED off
  delay(200);            // Wait - Makes it flash
}

# usb-relay-controller
A very simple web application to allow switching on and off of the CH340G serial usb relay controller under /dev/tty.wchusbserial1420

The app uses pySerial to communicate with the relay and both python and pySerial need to be available.

usage:

```
git clone git@github.com:ztuit/hid-relay-controller.git

mvn clean package shade:shade

cd target

java -jar relay-controler-1.0-SNAPSHOT.jar

```

Navigate to: https://localhost:8081/


## Notes

Only tested on OSX Sierra.

Following link used to get a working driver to mount the device under /dev/tty.wchusbserial1420
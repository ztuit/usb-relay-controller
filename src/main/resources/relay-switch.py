#!/usr/bin/env python
"""
A script to send commands to a serial device
"""

import serial

def run(command='off', dev='/dev/tty.wchusbserial1420'):
    if command.lower()=='off':
        send('A00100A1', dev)
    elif command.lower()=='on':
        send('A00101A2', dev)
    elif command.lower()=='read':
        read(dev)
    else:
        send(command, dev);


def read(dev):
     ser = serial.Serial(dev, 9600, timeout=None, xonxoff=None, rtscts=None, dsrdtr=None)
     dtr = str(ser.dtr)
     cts = str(ser.cts)
     rts = str(ser.rts)
     ri = str(ser.ri)
     cd = str(ser.cd)
     print dtr + " " + cts + " " + rts + " " + ri + " " + cd
     print ser.get_settings()


def send(command='A00100A1', dev='/dev/tty.wchusbserial1420'):
    ser = serial.Serial(dev, 9600, timeout=None, xonxoff=None, rtscts=None, dsrdtr=None)
    ser.write(command.decode('HEX'))
    ser.close()

if __name__ == "__main__":
    from sys import argv

    if len(argv) == 3:
        run(command=argv[1], dev=argv[2])
    elif len(argv) == 2:
        run(command=argv[1])
    else:
        print 'Usage: ' + argv[0] + ' command [device]'

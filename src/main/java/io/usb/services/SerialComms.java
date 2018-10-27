package io.usb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;


public class SerialComms {

    Logger logger = LoggerFactory.getLogger(SerialComms.class);

    private final String device;
    private final URL script;
    private RelayState rs = RelayState.OFF;


    public enum RelayState{
        ON,
        OFF,
        ERROR;
    }


    public SerialComms(String device, String scriptName){

        this.device = device;

        this.script = SerialComms.class.getClassLoader().getResource(scriptName);
    }


    //TODO: proper state management
    public RelayState state(){
        return rs;
    }

    public void switchOn(){
        rs = RelayState.ON;
       doSwitch("on");

    }

    public void switchOff(){
        rs = RelayState.OFF;
        doSwitch("off");

    }

    private synchronized void doSwitch(String cmd){
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("python " + this.script.getPath() + " " + cmd + " " + this.device);
            Thread.sleep(2000);
            if(p.getErrorStream().available()>0){
                rs = RelayState.ERROR;
            }
            p.destroy();
        } catch (IOException e) {
            logger.error("",e);
        } catch (InterruptedException e) {
            logger.error("",e);
        }
    }

    public static void main(String args[]){
        SerialComms sc = new SerialComms("/dev/tty.wchusbserial1420", "relay-switch.py");
        sc.switchOn();

    }
}

package io.usb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;


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
            File f = File.createTempFile("relay-switch", "py");

             FileOutputStream fos = new FileOutputStream(f);
             fos.write(Files.readAllBytes(Paths.get(this.script.toURI())));
            p = Runtime.getRuntime().exec("python " + f.getPath() + " " + cmd + " " + this.device);
            Thread.sleep(2000);
            if(p.getErrorStream().available()>0){
                System.out.println("Unavailable " + f.getPath());
                rs = RelayState.ERROR;
            }
            p.destroy();
            f.delete();
        } catch (Exception e) {
            System.out.println(e);
            logger.error("",e);
            rs = RelayState.ERROR;
        } /*catch (InterruptedException e) {
            logger.error("",e);
        }*/
    }

    public static void main(String args[]){
        SerialComms sc = new SerialComms("/dev/tty.wchusbserial1420", "relay-switch.py");
        sc.switchOn();

    }
}

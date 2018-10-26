package io.hid;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.hid4java.event.HidServicesEvent;
import org.hid4java.HidServicesSpecification;
import org.hid4java.ScanMode;
import org.hid4java.HidServices;
import org.hid4java.HidManager;
import org.hid4java.HidDevice;
import org.hid4java.HidServicesListener;


import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Unit test for simple App.
 */
public class AppTest implements HidServicesListener 
{
  
    
    @Test
    public void enumerateHID(){
      // Configure to use custom specification
       HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();
       hidServicesSpecification.setAutoShutdown(true);
       hidServicesSpecification.setScanInterval(500);
       hidServicesSpecification.setPauseInterval(5000);
       hidServicesSpecification.setScanMode(ScanMode.SCAN_AT_FIXED_INTERVAL_WITH_PAUSE_AFTER_WRITE);

       // Get HID services using custom specification
       HidServices hidServices = HidManager.getHidServices(hidServicesSpecification);
       hidServices.addHidServicesListener(this);

       // Start the services
       System.out.println("Starting HID services.");
       hidServices.start();

       System.out.println("Enumerating attached devices...");

       // Provide a list of attached devices
       for (HidDevice hidDevice : hidServices.getAttachedHidDevices()) {
         System.out.println(hidDevice);
       }
    }
    
    @Override
 public void hidDeviceAttached(HidServicesEvent event) {

   System.out.println("Device attached: " + event);

   

 }

 @Override
 public void hidDeviceDetached(HidServicesEvent event) {

   System.err.println("Device detached: " + event);

 }

 @Override
 public void hidFailure(HidServicesEvent event) {

   System.err.println("HID failure: " + event);

 }
}

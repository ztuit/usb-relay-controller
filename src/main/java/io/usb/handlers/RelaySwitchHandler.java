package io.usb.handlers;


import io.usb.services.SerialComms;

import javax.ws.rs.*;

@Path("relay")
public class RelaySwitchHandler  {

    private final SerialComms sc;

    public RelaySwitchHandler(SerialComms sc) {
        this.sc = sc;
    }


    @Path("state")
    @GET
    public String state(){
        return "{ \"state\":\"" + sc.state().name() + "\"}";
    }

    @Path("on")
    @PUT
    public String on(){
        sc.switchOn();
        return state();
    }

    @Path("off")
    @PUT
    public String off(){
        sc.switchOff();
        return state();
    }


}

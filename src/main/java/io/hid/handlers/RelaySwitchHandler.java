package io.hid.handlers;


import javax.ws.rs.*;

@Path("relay")
public class RelaySwitchHandler  {


    @Path("state")
    @GET
    public String state(){
        return "";
    }

    @Path("on")
    @PUT
    public String on(){
        return "";
    }

    @Path("off")
    @PUT
    public String off(){
        return "";
    }


}

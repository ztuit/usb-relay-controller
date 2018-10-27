package io.usb;

import io.usb.handlers.DashboardHandler;
import io.usb.handlers.RelaySwitchHandler;
import io.usb.services.SerialComms;
import io.muserver.Method;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;

import io.muserver.rest.RestHandlerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Hello world!
 *
 */
public class App {

    Logger logger = LoggerFactory.getLogger(App.class);



    public static void main(String[] args) throws IOException, URISyntaxException {
        App app = new App();
        app.start();

    }

    public App() {

    }

    public void start() throws IOException, URISyntaxException {
        logger.info("starting application.");
        MuServer server = MuServerBuilder.httpsServer().withHttpsPort(8081)
                .addHandler(RestHandlerBuilder.restHandler(new RelaySwitchHandler(new SerialComms("/dev/tty.wchusbserial1420", "relay-switch.py"))).withOpenApiHtmlUrl("api.html"))
                .addHandler(Method.GET, "/", new DashboardHandler())
                .addShutdownHook(true).start();


        //TODO: handler here
         Runtime.getRuntime().addShutdownHook(new Thread(()->{
             server.stop();
         }));
    }


}




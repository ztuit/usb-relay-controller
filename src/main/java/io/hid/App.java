package io.hid;

import io.hid.handlers.RelaySwitchHandler;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.rest.RestHandlerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Hello world!
 *
 */
public class App {

    Logger logger = LoggerFactory.getLogger(App.class);



    public static void main(String[] args) {
        App app = new App();
        app.start();

    }

    public App() {

    }

    public void start() {
        logger.info("starting application.");
        MuServer server = MuServerBuilder.httpsServer()
                .addHandler(RestHandlerBuilder.restHandler(new RelaySwitchHandler())).start();

        //TODO:Runtime.getRuntime().addShutdownHook();
    }


}




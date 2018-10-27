package io.usb.handlers;

import io.muserver.MuRequest;
import io.muserver.MuResponse;
import io.muserver.RouteHandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

public class DashboardHandler implements RouteHandler {

    private String content;

    public DashboardHandler() throws IOException, URISyntaxException {

        URL r = DashboardHandler.class.getClassLoader().getResource("index.html");
        FileSystems.newFileSystem(r.toURI(), Collections.emptyMap());
        this.content = new String(Files.readAllBytes(Paths.get(r.toURI())));
    }
    @Override
    public void handle(MuRequest muRequest, MuResponse muResponse, Map<String, String> map) throws Exception {
            muResponse.contentType("text/html");
            muResponse.write(this.content);
    }
}

package network.topdown.application;

import network.topdown.application.webserver.WebServer;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class WebServerTest {

    @Test
    public void name() throws IOException {
        File dir = new File("F:\\下载");
        WebServer webServer = new WebServer(80, dir);
        webServer.start();
        System.in.read();
    }
}
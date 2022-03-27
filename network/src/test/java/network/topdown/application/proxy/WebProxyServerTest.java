package network.topdown.application.proxy;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class WebProxyServerTest {

    @Test
    public void name() throws IOException {
        WebProxyServer server = new WebProxyServer(80, new File("F:\\下载"));
        server.start();
    }
}
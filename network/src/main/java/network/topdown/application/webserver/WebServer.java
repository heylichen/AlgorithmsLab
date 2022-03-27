package network.topdown.application.webserver;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import network.topdown.application.http.protocol.HttpRequestUtil;
import network.topdown.application.http.protocol.HttpRequest;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
@Setter
public class WebServer {
    private int port;
    private File fileDir;

    public WebServer(int port, File fileDir) {
        this.port = port;
        this.fileDir = fileDir;
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(port);
        log.info("server started, bind port {}", port);
        while (true) {
            try (Socket connectionSocket = server.accept()) {
                log.info("listening...");
                HttpRequest request = HttpRequestUtil.readRequest(connectionSocket);
                service(request, connectionSocket);
                log.info("request {}, serviced", request.getUrlLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void service(HttpRequest request, Socket connectionSocket) throws IOException {
        if (request.getUrl() == null) {
            return;
        }
        try (OutputStream os = connectionSocket.getOutputStream()) {
            service(request, os);
        }
    }

    private void service(HttpRequest request, OutputStream ow) throws IOException {
        String filePath = fileDir.getPath() + request.getUrl();
        File file = new File(filePath);
        if (!file.exists()) {
            serviceNotFound(ow);
        } else {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            ow.write("HTTP/1.1 200 OK\r\n\r\n".getBytes(StandardCharsets.UTF_8));
            ow.write(bytes, 0, bytes.length);
        }
        ow.flush();
    }

    private void serviceNotFound(OutputStream ow) throws IOException {
        ow.write(("HTTP/1.1 404 File NOT Found\r\n"
                + "Content-Type: text/html\r\n"
                + "Content-Length: 23\r\n"
                + "\r\n" +
                "<h1>File Not Found</h1>").getBytes(StandardCharsets.UTF_8));
    }


}

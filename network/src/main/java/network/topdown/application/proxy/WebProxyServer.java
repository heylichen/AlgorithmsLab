package network.topdown.application.proxy;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import network.topdown.application.http.protocol.HttpRequest;
import network.topdown.application.http.protocol.HttpRequestUtil;
import network.topdown.application.http.protocol.HttpResponse;
import network.topdown.application.http.protocol.HttpResponseUtil;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * chunked data部分数据
 */
@Slf4j
@Setter
public class WebProxyServer {
    private int port;
    private File fileDir;

    public WebProxyServer(int port, File fileDir) {
        this.port = port;
        this.fileDir = fileDir;
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(port);
        log.info("server started, bind port {}", port);
        while (true) {
            try (Socket connectionSocket = server.accept()) {
                log.info("listening...");
                HttpRequest request = HttpRequestUtil.parseRequest(new BufferedReader(new InputStreamReader(connectionSocket.getInputStream(), StandardCharsets.UTF_8)));
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

    private void service(HttpRequest request, OutputStream os) throws IOException {
        String fileName = genFileName(request);

        String filePath = fileDir.getPath() +"\\"+ fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            serviceProxy(request, fileName, file);
        }

        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            os.write(bytes, 0, bytes.length);
            os.flush();
        } catch (FileNotFoundException e) {
            serviceNotFound(os);
        }
    }

    private void serviceProxy(HttpRequest request, String host, File cacheFile) throws IOException {
        //change headers
        request.setUrl("/");
        Map<String, String> oldHeaders = request.getHeaders();
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", host);
        headers.put("Accept", oldHeaders.get("Accept"));
        headers.put("Connection", oldHeaders.get("Connection"));
        headers.put("Accept-Encoding", oldHeaders.get("Accept-Encoding"));
        request.setHeaders(headers);

        try (Socket socket = new Socket(InetAddress.getByName(host), 80)) {
            socket.setSoTimeout(2000);
            HttpRequestUtil.writeRequest(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), request);

            HttpResponse response = HttpResponseUtil.parseRawResponse(socket.getInputStream());
            HttpResponseUtil.writeResponse(response, new FileOutputStream(cacheFile));

            HttpResponseUtil.logResponseHeader(response);
        } catch (UnknownHostException e) {
            log.info("unknown host {}", host);
        } catch (IOException e) {
            log.error("error ", e);
        }
    }

    private String genFileName(HttpRequest request) {
        int index = request.getUrl().lastIndexOf("/");
        String fileName = request.getUrl().substring(index + 1);
        return fileName;
    }

    private void serviceNotFound(OutputStream ow) throws IOException {
        ow.write(("HTTP/1.1 404 File NOT Found\r\n"
                + "Content-Type: text/html\r\n"
                + "Content-Length: 23\r\n"
                + "\r\n" +
                "<h1>File Not Found</h1>").getBytes(StandardCharsets.UTF_8));
    }

}

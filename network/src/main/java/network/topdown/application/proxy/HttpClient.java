package network.topdown.application.proxy;

import lombok.extern.slf4j.Slf4j;
import network.topdown.application.http.protocol.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpClient {
    public static final String HOST = "www.baidu.com";
    public static final String PATH = "/";
//    public static final String HOST = "www.nanjing.gov.cn";
//    public static final String PATH = "/index.html";



    public static void main(String[] args) {
        HttpRequest request = new HttpRequest();
        request.setHttpVersion("HTTP/1.1");
        request.setMethod("GET");
        request.setUrl(PATH);
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "text/html");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");

        headers.put("Connection", "keep-alive");
        headers.put("Host", HOST);
        request.setHeaders(headers);

        try (Socket socket = new Socket(InetAddress.getByName(HOST), 80)) {
            socket.setSoTimeout(2000);
            Writer writer = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
            HttpRequestUtil.writeRequest(writer,request);

            HttpResponse response = HttpResponseUtil.parseResponse(socket.getInputStream());
            HttpResponseUtil.logResponse(response);
        } catch (IOException e) {
            log.error("error ", e);
        }
    }


    private static void request1() {
        try (Socket socket = new Socket(InetAddress.getByName(HOST), 80)) {
            socket.setSoTimeout(2000);
            Writer writer = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
            writer.write("GET " + PATH + " HTTP/1.1\r\n");
            writer.write("Accept: text/html\r\n");
            writer.write("Accept-Encoding: gzip, deflate\r\n");
            writeHeader(writer, "Accept-Language: zh-CN,zh;q=0.9");
            writeHeader(writer, "Connection: keep-alive");
            writeHeader(writer, "Host: www.nanjing.gov.cn");
            writer.write(HttpConstant.CRLF);
            writer.flush();

            HttpResponse response = HttpResponseUtil.parseResponse(socket.getInputStream());
            HttpResponseUtil.logResponse(response);
        } catch (IOException e) {
            log.error("error ", e);
        }
    }

    private static void writeHeader(Writer writer, String header) throws IOException {
        writer.write(header + "\r\n");
    }


}

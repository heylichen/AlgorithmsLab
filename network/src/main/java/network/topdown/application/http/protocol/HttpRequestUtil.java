package network.topdown.application.http.protocol;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestUtil {

    public static void writeRequest(Writer writer, HttpRequest request) throws IOException {
        writeHeader(writer, request.getUrlLine());
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            writeHeader(writer, entry.getKey() + ":" + entry.getValue());
        }
        writer.write(HttpConstant.CRLF);
        writer.flush();
    }

    private static void writeHeader(Writer writer, String header) throws IOException {
        writer.write(header + HttpConstant.CRLF);
    }

    public static void write(OutputStream os, String line) throws IOException {
        os.write((line + HttpConstant.CRLF).getBytes(StandardCharsets.UTF_8));
    }

    public static HttpRequest readRequest(Socket connectionSocket) throws IOException {
        InputStreamReader ir = new InputStreamReader(connectionSocket.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(ir);
        return HttpRequestUtil.parseRequest(br);
    }

    public static HttpRequest parseRequest(BufferedReader br) throws IOException {
        boolean firstLine = true;
        HttpRequest request = new HttpRequest();
        Map<String, String> headers = new HashMap<>();
        request.setHeaders(headers);
        while (true) {
            String line = br.readLine();
            if (StringUtils.isBlank(line)) {
                break;
            }
            if (firstLine) {
                parseFirstLine(line, request);
                firstLine = false;
            } else {
                parseHeader(line, headers);
            }
        }
        return request;
    }


    private static void parseFirstLine(String line, HttpRequest request) {
        String[] pieces = line.split(" ");
        if (pieces.length < 3) {
            return;
        }
        request.setMethod(pieces[0]);
        request.setUrl(pieces[1]);
        request.setHttpVersion(pieces[2]);
    }

    private static void parseHeader(String line, Map<String, String> headers) {
        if (!line.contains(":")) {
            return;
        }
        String[] pieces = line.split(":");
        headers.put(pieces[0].trim(), pieces[1].trim());
    }
}

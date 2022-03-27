package network.topdown.application.http.protocol;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@Slf4j
public final class HttpResponseUtil {

    private HttpResponseUtil() {
    }

    public static void writeResponse(HttpResponse response, OutputStream os) throws IOException {
        HttpRequestUtil.write(os, response.getFirstLine().getLine());
        for (Map.Entry<String, String> entry : response.getHeaders().entrySet()) {
            HttpRequestUtil.write(os, entry.getKey() + ":" + entry.getValue());
        }
        HttpRequestUtil.write(os, "");
        response.getData().writeTo(os);
        os.flush();
    }

    public static HttpResponse parseResponse(InputStream in) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(in);
        String first = readLine(bis);
        ResponseFirstLine firstLine = parseFirstLine(first);

        Map<String, String> headers = HttpResponseUtil.parseHeaders(bis);

        String transferEncoding = headers.get("Transfer-Encoding");
        String contentLengthStr = headers.get("Content-Length");
        ByteArrayOutputStream bos = null;
        if ("chunked".equals(transferEncoding)) {
            bos = BlockCodingParser.readAllBlocks(bis);
        } else if (contentLengthStr != null) {
            int contentLength = Integer.parseInt(contentLengthStr);
            bos = HttpResponseUtil.parseData(bis, contentLength);
        } else {
            throw new IllegalStateException("unsupported！");
        }

        HttpResponse response = new HttpResponse();
        response.setFirstLine(firstLine);
        response.setHeaders(headers);
        response.setData(bos);
        return response;
    }

    public static HttpResponse parseRawResponse(InputStream in) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(in);
        String first = readLine(bis);
        ResponseFirstLine firstLine = parseFirstLine(first);

        Map<String, String> headers = HttpResponseUtil.parseHeaders(bis);

        String transferEncoding = headers.get("Transfer-Encoding");
        String contentLengthStr = headers.get("Content-Length");
        ByteArrayOutputStream bos = null;
        if ("chunked".equals(transferEncoding)) {
            bos = BlockCodingParser.readAllRawBlocks(bis);
        } else if (contentLengthStr != null) {
            int contentLength = Integer.parseInt(contentLengthStr);
            bos = HttpResponseUtil.parseData(bis, contentLength);
        } else {
            throw new IllegalStateException("unsupported！");
        }

        HttpResponse response = new HttpResponse();
        response.setFirstLine(firstLine);
        response.setHeaders(headers);
        response.setData(bos);
        return response;
    }

    public static ResponseFirstLine parseFirstLine(String line) {
        String[] pieces = line.split(" ");
        if (pieces.length < 3) {
            return null;
        }
        ResponseFirstLine fl = new ResponseFirstLine();
        fl.setHttpVersion(pieces[0]);
        fl.setStatus(pieces[1]);
        fl.setStatusMsg(pieces[2]);
        return fl;
    }

    public static void parseHeader(String line, Map<String, String> headers) {
        if (!line.contains(":")) {
            return;
        }
        String[] pieces = line.split(":");
        headers.put(pieces[0].trim(), pieces[1].trim());
    }

    public static Map<String, String> parseHeaders(InputStream in) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String line;
        while (!(line = readLine(in)).equals("")) {
            parseHeader(line, headers);
        }
        return headers;
    }

    public static String readLine(InputStream bis) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte b;
        while ((b = (byte) bis.read()) != '\n') {
            if (b != '\r') {
                bos.write(b);
            }
        }
        return bos.toString(StandardCharsets.UTF_8);
    }

    public static ByteArrayOutputStream parseData(InputStream bis, int length) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte b;
        int len = 0;
        while (len < length) {
            b = (byte) bis.read();
            bos.write(b);
            len++;
        }
        return bos;
    }

    public static void logResponseHeader(HttpResponse response) throws IOException {
        log.info("response:{}", response.getFirstLine().getLine());
        Map<String, String> headers = response.getHeaders();
        ByteArrayOutputStream bos = response.getData();
        viewHeaders(headers);
        if (bos == null) {
            log.info("unknown response null");
            return;
        }
    }

    public static void logResponse(HttpResponse response) throws IOException {
        log.info("response:{}", response.getFirstLine().getLine());
        Map<String, String> headers = response.getHeaders();
        ByteArrayOutputStream bos = response.getData();
        viewHeaders(headers);
        if (bos == null) {
            log.info("unknown response null");
            return;
        }

        String contentType = headers.get("Content-Type");
        if (!isTextContent(contentType)) {
            log.info("non-text content, {} bytes", bos.toByteArray().length);
            return;
        }

        String contentEncoding = headers.get("Content-Encoding");
        String responseText;
        if (contentEncoding != null && contentEncoding.equals("gzip")) {
            responseText = parseGzipString(bos.toInputStream());
        } else {
            responseText = bos.toString(StandardCharsets.UTF_8);
        }
        log.info("response text--------\n{}", responseText);
    }


    private static boolean isTextContent(String contentType) {
        return contentType.startsWith("text/") || contentType.endsWith("/json") || contentType.endsWith("/xml");
    }

    private static void viewHeaders(Map<String, String> headers) {
        System.out.println("response headers --------------");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println();
    }

    private static String parseGzipString(InputStream in) throws IOException {
        GZIPInputStream gin = new GZIPInputStream(in);
        InputStreamReader reader = new InputStreamReader(gin, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        return sb.toString();
    }
}

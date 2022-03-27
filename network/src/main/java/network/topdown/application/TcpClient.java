package network.topdown.application;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TcpClient {
    public static final int PORT = 14;

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), PORT)) {
            socket.setSoTimeout(4000);

            Writer writer = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
            writer.write("hello, world!\n");
            writer.flush();

            log.info("--begin read ");

            InputStreamReader ir = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            for (int c = ir.read(); c != -1; c = ir.read()) {
                sb.append((char) c);
            }
            String msg = sb.toString();
            log.info("got resp {}", msg);
            writer.close();
            ir.close();
        } catch (IOException e) {
            log.error("error ", e);
        }
    }
}

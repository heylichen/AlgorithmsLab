package network.topdown.application;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TcpServer {
    public static final int PORT = 14;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        while (true) {
            try (Socket connectionSocket = server.accept()) {
                log.info("listening...");
                InputStream is = connectionSocket.getInputStream();
                OutputStream os = connectionSocket.getOutputStream();
                InputStreamReader ir = new InputStreamReader(is, StandardCharsets.UTF_8);
               //for read 为何不行？
                BufferedReader br = new BufferedReader(ir);
                String msg = br.readLine();


                log.info("got request " + msg);
                msg = msg.toUpperCase();

                log.info("begin to resp");
                OutputStreamWriter ow = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                ow.write(msg);
                ow.flush();

                ir.close();
                ow.close();
                log.info("after resp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package network.topdown.application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UdpClient {
    public static final int PORT = 13;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(0)) {
            socket.setSoTimeout(10000);
            InetAddress server = InetAddress.getLocalHost();
            String msg = "hello, world!";
            byte[] msgData = msg.getBytes(StandardCharsets.UTF_8);
            DatagramPacket request = new DatagramPacket(msgData, msgData.length, server, PORT);
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            socket.send(request);
            socket.receive(response);

            String result = new String(response.getData(), 0, response.getLength(), StandardCharsets.UTF_8);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

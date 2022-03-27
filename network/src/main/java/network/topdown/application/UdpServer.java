package network.topdown.application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class UdpServer {
    public static final int PORT = 13;

    public static void main(String[] args) {
        service();
    }

    public static final void service() {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            while (true) {
                try {
                    DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(response);

                    String result = new String(response.getData(), 0, response.getLength(), StandardCharsets.UTF_8);
                    System.out.println("got request "+result);
                    byte[] data = result.toUpperCase().getBytes(StandardCharsets.UTF_8);

                    DatagramPacket request = new DatagramPacket(data, data.length, response.getAddress(), response.getPort());
                    socket.send(request);
                } catch (IOException | RuntimeException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

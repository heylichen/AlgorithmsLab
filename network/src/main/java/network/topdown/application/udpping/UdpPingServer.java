package network.topdown.application.udpping;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Slf4j
public class UdpPingServer {
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
                    log.info("got request msg {}", result);

                    byte[] data = result.toUpperCase().getBytes(StandardCharsets.UTF_8);
                    Random random = new Random();
                    int rand = random.nextInt(10);
                    if (rand >= 2) {
                        DatagramPacket request = new DatagramPacket(data, data.length, response.getAddress(), response.getPort());
                        socket.send(request);
                    }
                } catch (IOException | RuntimeException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

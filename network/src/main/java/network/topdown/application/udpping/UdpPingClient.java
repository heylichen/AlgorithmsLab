package network.topdown.application.udpping;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UdpPingClient {
    public static final int PORT = 13;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(0)) {
            socket.setSoTimeout(1000);
            ping(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ping(DatagramSocket socket) throws IOException {
        InetAddress server = InetAddress.getLocalHost();
        String msg = "hello, world!";
        byte[] msgData = msg.getBytes(StandardCharsets.UTF_8);
        DatagramPacket request = new DatagramPacket(msgData, msgData.length, server, PORT);

        Stopwatch stopwatch = Stopwatch.createUnstarted();

        for (int i = 0; i < 10; i++) {
            try {
                stopwatch.reset();
                stopwatch.start();
                socket.send(request);

                DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
                socket.receive(response);

                stopwatch.stop();
                String result = new String(response.getData(), 0, response.getLength(), StandardCharsets.UTF_8);
                log.info("received resp={} RTT={} micro seconds", result, stopwatch.elapsed(TimeUnit.MICROSECONDS));
            } catch (SocketTimeoutException e) {
                stopwatch.stop();
                log.info("packet loss, elasped {} micro seconds", stopwatch.elapsed(TimeUnit.MICROSECONDS));
            }
        }
    }
}

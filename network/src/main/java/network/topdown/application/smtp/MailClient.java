package network.topdown.application.smtp;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//账号qq号码base64 Nzg0MTM0Nzkw heylichen@qq.com 授权码 aW9xbXJ1bHhhYnp2YmRhZw==
//疑问：inputstream怎样知道都多少字符停止？
@Slf4j
public class MailClient {
    public static final String server = "smtp.qq.com";
    public static final int port = 587;

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName(server), port)) {
            socket.setSoTimeout(1000);

            InputStreamReader ir = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(ir);
            receive(br,1);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            send("HELO heylichen", bw);
            receive(br,3);

            send("AUTH LOGIN", bw);
            receive(br,1);

            send("Nzg0MTM0Nzkw", bw);
            receive(br,1);

            send("aW9xbXJ1bHhhYnp2YmRhZw==", bw);
            receive(br,1);

            send("MAIL FROM:<heylichen@qq.com>", bw);
            receive(br,1);

            send("RCPT TO:<heylichen@qq.com>", bw);
            receive(br,1);

            send("DATA", bw);
            receive(br,1);
            // header section
            send("Subject:测试邮件", bw);
            send("From:heylichen@qq.com", bw);
            send("To:heylichen@qq.com", bw);
            send("", bw);
            //空行，下面是正文
            send("Hello, there!", bw);
            send(".", bw);
            receive(br,1);

            send("QUIT", bw);
            receive(br,1);
        } catch (IOException e) {
            log.error("error ", e);
        }
    }

    private static void receive(BufferedReader br, int times) throws IOException {
        for (int i = 0; i < times; i++) {
            String line = br.readLine();
            log.info("got: {}", line);
        }
    }

    private static void send(String msg, BufferedWriter bw) throws IOException {
        log.info("{}", msg);
        msg = msg.replace("\n", "\r\n");
        msg += "\r\n";
        bw.write(msg);
        bw.flush();
    }
}

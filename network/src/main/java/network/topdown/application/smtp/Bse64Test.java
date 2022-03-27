package network.topdown.application.smtp;

import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;

public class Bse64Test {
    public static void main(String[] args) {
        //ioqmrulxabzvbdag
        BASE64Encoder encoder = new BASE64Encoder();
        String en1 = encoder.encode("784134790".getBytes(StandardCharsets.UTF_8));
        System.out.println(en1);
        en1=encoder.encode("ioqmrulxabzvbdag".getBytes(StandardCharsets.UTF_8));
        System.out.println(en1);
    }
}

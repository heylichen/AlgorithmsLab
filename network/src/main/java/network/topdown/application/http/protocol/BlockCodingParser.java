package network.topdown.application.http.protocol;

import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;

//TODO 读取原始格式
public class BlockCodingParser {

    public static ByteArrayOutputStream readAllBlocks(InputStream in) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while (readBlock(in, bos)) {
        }
        return bos;
    }

    private static boolean readBlock(InputStream bis, ByteArrayOutputStream bos) throws IOException {
        String length = HttpResponseUtil.readLine(bis);
        if (length.length() == 0) {
            length = HttpResponseUtil.readLine(bis);
        }
        int byteCount = Integer.valueOf(length, 16);
        if (byteCount <= 0) {
            return false;
        }
        readBlock(bis, byteCount, bos);
        return true;
    }

    public static ByteArrayOutputStream readAllRawBlocks(InputStream in) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while (readRawBlock(in, bos)) {
        }
        return bos;
    }

    private static boolean readRawBlock(InputStream bis, ByteArrayOutputStream bos) throws IOException {
        String length = HttpResponseUtil.readLine(bis);
        if (length.length() == 0) {
            HttpRequestUtil.write(bos,"");
            length = HttpResponseUtil.readLine(bis);
        }
        HttpRequestUtil.write(bos,length);

        int byteCount = Integer.valueOf(length, 16);
        if (byteCount <= 0) {
            HttpRequestUtil.write(bos,"");
            return false;
        }
        readBlock(bis, byteCount, bos);
        return true;
    }

    private static void readBlock(InputStream bis, int byteCount, ByteArrayOutputStream bos) throws IOException {
        int count = 0;
        while (count < byteCount) {
            bos.write(bis.read());
            count++;
        }
    }
}

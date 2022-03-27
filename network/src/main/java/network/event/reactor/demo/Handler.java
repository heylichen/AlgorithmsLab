package network.event.reactor.demo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Handler implements Runnable {

    final SocketChannel socketChannel;
    final SelectionKey selectionKey;
    ByteBuffer input = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING = 1;
    int state = READING;
    String clientName = "";

    /**
     * use shared selector
     *
     * @param selector
     * @param c
     * @throws IOException
     */
    Handler(Selector selector, SocketChannel c) throws IOException {
        socketChannel = c;
        c.configureBlocking(false);
        //register for read immediately
        selectionKey = socketChannel.register(selector, 0);
        selectionKey.attach(this);
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }


    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            } else {
                System.out.println("state=" + state + " no op.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("你的主机中的软件中止了一个已建立的连接, state=" + state);
            selectionKey.cancel();
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void read() throws IOException {
        int readCount = 0;

        readCount = socketChannel.read(input);

        if (readCount > 0) {
            readProcess(readCount);
        }
        state = SENDING;
        // Interested in writing
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    /**
     * Processing of the read message. This only prints the message to stdOut.
     *
     * @param readCount
     */
    synchronized void readProcess(int readCount) {
        StringBuilder sb = new StringBuilder();
        input.flip();
        byte[] subStringBytes = new byte[readCount];
        byte[] array = input.array();
        System.arraycopy(array, 0, subStringBytes, 0, readCount);
        // Assuming ASCII (bad assumption but simplifies the example)
        sb.append(new String(subStringBytes));
        input.clear();
        clientName = sb.toString().trim();
    }

    void send() throws IOException {
        System.out.println("Saying hello to " + clientName);
        ByteBuffer output = ByteBuffer.wrap(("Hello " + clientName + "\n").getBytes());
        socketChannel.write(output);
        selectionKey.interestOps(SelectionKey.OP_READ);
        state = READING;
    }

    public static void main(String[] args) throws IOException {

        Reactor reactor = new Reactor(9900, false);
        new Thread(reactor).start();
    }

}
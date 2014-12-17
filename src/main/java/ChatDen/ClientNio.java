package ChatDen;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Created by denis on 17.12.14.
 */
public class ClientNio {

    private int port;
    private String ip;
    SocketChannel sc;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public ClientNio(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void startClientNio() throws IOException {

        sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(ip, port));

        Scanner scanner = new Scanner(System.in);

        while (true) {

            String str = scanner.nextLine();
            buffer.clear();
            buffer.put(str.getBytes());

            buffer.flip();

            while (buffer.hasRemaining()) {
                sc.write(buffer);
            }

            buffer.clear();
            sc.read(buffer);
            buffer.flip();
            while (buffer.remaining() > 0) {
                byte b = buffer.get();
                System.out.print((char) b);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        ClientNio clientNio = new ClientNio("127.0.0.1", 8283);
        try {
            clientNio.startClientNio();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

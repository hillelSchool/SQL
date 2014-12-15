package ChatDen;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * Created by denis on 15.12.14.
 */
public class ServerNio {

    private int port;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public ServerNio(int port) {
        this.port = port;

        startServer();
    }

    private void startServer() {

        try {

            ServerSocketChannel ssc = ServerSocketChannel.open();
            InetSocketAddress address = new InetSocketAddress(port);
            ssc.socket().bind(address);

            System.out.println("Going to listen on " + port);
            SocketChannel sc = ssc.accept();


            while (true) {

                // Echo data
                while (true) {
                    buffer.clear();

                    int r = sc.read(buffer);

                    if (r <= 0) {
                        break;
                    }

                    buffer.flip();

                    sc.write(buffer);
                    System.out.println("Echoed from " + sc + new Date());
                }
            }

        } catch (IOException e) {
            System.out.println("IOException startServer()");
        }
    }

    static public void main(String args[]) {
        if (args.length <= 0) {
            System.err.println("Usage: java MultiPortEcho port");
            System.exit(1);
        }

        int port = 8283;

        new ServerNio(port);
    }
}

package ChatDen;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by denis on 15.12.14.
 */
public class ServerEchoNio {

    private int port;
    private ByteBuffer echoBuffer = ByteBuffer.allocate(1024);

    public ServerEchoNio(int port) throws IOException {
        this.port = port;
    }

    void startServerEchoNio() throws IOException {
        // Create a new selector
        Selector selector = Selector.open();

        // Open a listener on each port, and register each one
        // with the selector
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket ss = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        ss.bind(address);

        SelectionKey keyAccept = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Going to listen on " + port);

        while (true) {
            int num = selector.select();

            Set selectedKeys = selector.selectedKeys();
            Iterator it = selectedKeys.iterator();

            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();

                if ((key.readyOps() & SelectionKey.OP_ACCEPT)
                        == SelectionKey.OP_ACCEPT) {
                    // Accept the new connection
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);

                    // Add the new connection to the selector
                    SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
                    it.remove();

                    System.out.println("Got connection from " + sc);
                } else if ((key.readyOps() & SelectionKey.OP_READ)
                        == SelectionKey.OP_READ) {
                    // Read the data
                    SocketChannel sc = (SocketChannel) key.channel();

                    // Echo data
                    int bytesEchoed = 0;
                    while (true) {
                        echoBuffer.clear();

                        int r = sc.read(echoBuffer);

                        if (r <= 0) {
                            break;
                        }

                        echoBuffer.flip();

                        sc.write(echoBuffer);
                        bytesEchoed += r;
                    }

                    System.out.println("Echoed " + bytesEchoed + " from " + sc);

                    it.remove();
                }

            }

            selectedKeys.clear();
        }
    }


    public static void main(String[] args) {

        try {
            ServerEchoNio serverEchoNio = new ServerEchoNio(8283);
            serverEchoNio.startServerEchoNio();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

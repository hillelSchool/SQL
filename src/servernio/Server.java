package serverio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.*;
import java.nio.channels.*;
import java.nio.DataOutputStream;
import java.nio.InputStream;
import java.nio.OutputStream;
import java.net.ServerSocket;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import static java.nio.channels.SocketChannel.*;

/**
 * Created by Богдан on 08.12.2014.
 */
public class Server {
    public static void main(String[] ar)    {
        int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)

        ServerSocketChannel ss = null;
        try {
            ss = ServerSocketChannel.open();

            ss.socket().bind(new InetSocketAddress(port));

            // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");

            SocketChannel socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
            System.out.println();

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            System.out.println(byteBuffer);
            while(ss.(buffer) > 0)
            {
                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++)
                {
                    System.out.print((char) buffer.get());
                }
                buffer.clear(); // do something with the data and clear/compact it.
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        /* while(ss.read(buffer) > 0)
            {
                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++)
                {
                    System.out.print((char) buffer.get());
                }
                buffer.clear(); // do something with the data and clear/compact it.
            }
            inChannel.close();

                 OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            boolean t = true;
            while(t) {
                line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                System.out.println("The dumb client just sent me this line : " + line);
                System.out.println("You have to say smth:  ");
                String key = keyboard.readLine();
                if ("exit".equals(key)){
                    System.out.println("we already close");
                    out.flush();
                }
                out.writeUTF(key); // отсылаем клиенту обратно ту самую строку текста.

                out.flush(); // заставляем поток закончить передачу данных.
                if ("exit".equals(key)){
                    System.out.println("we already close");
                    System.exit(10);
                }else {
                    System.out.println("Waiting for the next line...");
                    System.out.println();
                }
            } */

    }
}



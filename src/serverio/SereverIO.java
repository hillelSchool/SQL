package serverio;

import java.io.*;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Богдан on 08.12.2014.
 */
public class SereverIO {
        public static void main(String[] ar)    {
            int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
            try {
                ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
                System.out.println("Waiting for a client...");

                Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
                System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
                System.out.println();

                // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
                InputStream sin = socket.getInputStream();
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
                }
            } catch(Exception x) { x.printStackTrace(); }
        }
    }



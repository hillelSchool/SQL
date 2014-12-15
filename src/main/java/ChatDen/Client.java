package ChatDen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Обеспечивает работу программы в режиме клиента
 */
public class Client {
    private BufferedReader inputStream;
    private PrintWriter outputStream;
    private Socket socket;
    private String ip;
    private int port;

    /**
     * Запрашивает у пользователя ник и организовывает обмен сообщениями с
     * сервером
     */
    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;

        startClient();
    }

    private void startClient(){
        Scanner scan = new Scanner(System.in);

        try {
            // Подключаемся в серверу и получаем потоки(inputStream и outputStream) для передачи сообщений

            while (true) {
                socket = new Socket(ip, port);
                inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                outputStream = new PrintWriter(socket.getOutputStream(), true);  //true - autoFlush!!!

                System.out.println("C(hat) or D(isconnect): ");
                String command = scan.nextLine().toLowerCase();
                if (command.equals("c")) {

                    System.out.println("Введите свой ник:");
                    outputStream.println(scan.nextLine());

                    // Запускаем вывод всех входящих сообщений в консоль
                    ReSender resend = new ReSender();
                    resend.start();

                    // Пока пользователь не введёт "exit" отправляем на сервер всё, что
                    // введено из консоли
                    String str = "";
                    while (!str.equals("exit")) {
                        str = scan.nextLine();
                        outputStream.println(str);
                    }
                    resend.setStop();
                } else if (command.equals("d")) {
                    System.exit(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    /**
     * Закрывает входной и выходной потоки и сокет
     */
    private void close() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Потоки не были закрыты!");
        }
    }

    /**
     * Класс в отдельной нити пересылает все сообщения от сервера в консоль.
     * Работает пока не будет вызван метод setStop().
     */
    private class ReSender extends Thread {

        private boolean stoped;

        /**
         * Прекращает пересылку сообщений
         */
        public void setStop() {
            stoped = true;
        }

        /**
         * Считывает все сообщения от сервера и печатает их в консоль.
         * Останавливается вызовом метода setStop()
         *
         * @see java.lang.Thread#run()
         */
        @Override
        public void run() {
            try {
                while (!stoped) {
                    String str = inputStream.readLine();
                    System.out.println(str);
                    if (str == null) {
                        System.out.println("Server was stop");
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка при получении сообщения.");
                e.printStackTrace();
            }
        }
    }
}

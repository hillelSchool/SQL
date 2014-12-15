package ChatDen;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class MainChat {

    /**
     * Example:
     * server: s
     */
    public static void main(String[] args) {

        if (args.length <= 0) {
            System.err.println("Usage: java MainChat s (s - server/ c - client/ n - serverNIO)");
            System.exit(1);
        }

        Properties properties = new Properties();
        InputStream inputStream;

        try {
            inputStream = new FileInputStream("./src/main/java/ChatDen/chat.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String ip = properties.getProperty("ip");
        int port = Integer.parseInt(properties.getProperty("port"));

//        Scanner in = new Scanner(System.in);
//        System.out.print("Запустить программу в режиме сервера или клиента? (S(erver) / C(lient)): ");
//        char answer = Character.toLowerCase(in.nextLine().charAt(0));
        char answer = Character.toLowerCase(args[0].charAt(0));
        if (answer == 's') {

            new Server(port);

        } else if (answer == 'c') {

            new Client(ip, port);

        } else if (answer == 'n') {

            new ServerNio(port);

        } else {
            System.out.println("Wrong command");
        }
    }
}

package Den;

import java.util.Map;

public class ConnectionConfig {

    public static String user;//Логин пользователя
    public static String password;//Пароль пользователя
    public static String url;//URL адрес
    public static String driver;

    static {
        Map<Integer, String> map = ConsoleOperation.readFile("src/Den/ConnectionConfig.txt");

        user = map.get(0).split("\"")[1];
        password = map.get(1).split("\"")[1];
        url = map.get(2).split("\"")[1];
        driver = map.get(3).split("\"")[1];
    }
}

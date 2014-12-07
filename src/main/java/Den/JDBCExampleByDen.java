package Den;

/*
 * Внимание, код заговорен! Изменение данного кода(без согласия автора)
 * приведёт к половому бессилию и неизлечимым заболеваниям!!!
 */

import java.sql.Connection;

public class JDBCExampleByDen {
    public static void main(String[] args) {

        Connection connection = ConnectionToDB.connectToDB();
        ConsoleOperation.selectMenuItem();      // Ввод запроса с консоли
        ConnectionToDB.disConnectFromDB(connection);
    }
}

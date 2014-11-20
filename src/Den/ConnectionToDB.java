package Den;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {

    private static String user = ConnectionConfig.getUser();
    private static String password = ConnectionConfig.getPassword();
    private static String url = ConnectionConfig.getUrl();
    private static String driver = ConnectionConfig.getDriver();

    private static Connection connection;
    public static Connection getConnection() {
        return connection;
    }

    protected static Connection connectToDB() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found!");
            e.printStackTrace();
        }
        connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error connection to DB!");
            e.printStackTrace();
        }
        return connection;
    }

    protected static void disConnectFromDB(Connection connection){
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println("Error disconnect from DB!");
            e.printStackTrace();
        }
    }
}
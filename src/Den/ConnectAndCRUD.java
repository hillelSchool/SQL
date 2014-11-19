package Den;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectAndCRUD {
    private static String query = ConsoleOperation.query;

    private static String user = ConnectionConfig.user;
    private static String password = ConnectionConfig.password;
    private static String url = ConnectionConfig.url;
    private static String driver = ConnectionConfig.driver;

    protected static void connectAndCRUDToDB() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found!");
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();

            if (query.split(" ")[0].equals("select")) {

                ResultSet rs = st.executeQuery(query);
                ResultSetMetaData rsMetaData = rs.getMetaData();

                int count = rsMetaData.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    QueryObject.columnsTitle.add(rsMetaData.getColumnName(i));
                }
                while (rs.next()) {
                    List<Object> list = new ArrayList<>();
                    for (int i = 1; i <= count; i++) {
                        list.add(rs.getObject(i));
                    }
                    QueryObject.columnsTable.add(list);
                }
            } else {
                st.execute(query);
            }
        } catch (SQLException e) {
            System.err.println("Error interaction with DB!");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println("Error close connection!");
                e.printStackTrace();
            }
        }
    }
}
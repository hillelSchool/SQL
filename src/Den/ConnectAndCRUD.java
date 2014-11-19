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

    protected static void connectAndCUDToDB() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found!");
            e.printStackTrace();
        }
        Connection c = null;
        try {
            c = DriverManager.getConnection(url, user, password);
            Statement st = c.createStatement();

            if (query.split(" ")[0].equals("select")) {

                ResultSet rs = st.executeQuery(query);
                ResultSetMetaData rsMetaData = rs.getMetaData();

                int count = rsMetaData.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    QueryObject.columnName.add(rsMetaData.getColumnName(i));
                }
                while (rs.next()) {
                    List<Object> list = new ArrayList<>();
                    for (int i = 1; i <= count; i++) {
                        list.add(rs.getObject(i));
                    }
                    QueryObject.columnTable.add(list);
                }
            } else {
                st.execute(query);
            }
        } catch (SQLException e) {
            System.out.println("Error interaction with DB!");
            e.printStackTrace();
        } finally {
            try {
                if (c != null)
                    c.close();
            } catch (SQLException e) {
                System.out.println("Error close connection!");
                e.printStackTrace();
            }
        }
    }
}
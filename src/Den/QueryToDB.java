package Den;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryToDB {

    protected static QueryObject queryToDB(Connection connection, String query){

        QueryObject queryObject = null;
        try {
            Statement st = connection.createStatement();

            if (query.split(" ")[0].equals("select") || query.split(" ")[0].equals("show") || query.split(" ")[0].equals("describe")) {
            
            //query.split(" ")[0].equals("select") ---->  query.startWith("select")
               
               queryObject = new QueryObject();

                List<String> columnsTitle = new ArrayList<>();
                List<List<Object>> columnsTable = new ArrayList<>();

                ResultSet rs = st.executeQuery(query);
                ResultSetMetaData rsMetaData = rs.getMetaData();

                int count = rsMetaData.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    columnsTitle.add(rsMetaData.getColumnName(i));
                }
                while (rs.next()) {
                    List<Object> list = new ArrayList<>();
                    for (int i = 1; i <= count; i++) {
                        list.add(rs.getObject(i));
                    }
                    columnsTable.add(list);
                }
                queryObject.setColumnsTitle(columnsTitle);
                queryObject.setColumnsTable(columnsTable);
            } else {
                st.execute(query);
            }
        } catch (SQLSyntaxErrorException e) {
            System.out.println("SQL Syntax Error\n");
        } catch (SQLException e) {
            System.out.println("SQL Error\n");
        }
        return queryObject;
    }
}

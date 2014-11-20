package Den;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryToDB {

    protected static QueryObject queryToDB(Connection connection, String query){

        QueryObject queryObject = new QueryObject();
        List<String> columnsTitle = new ArrayList<>();
        List<List<Object>> columnsTable = new ArrayList<>();

        try{
            Statement st = connection.createStatement();
            if (query.split(" ")[0].equals("select")) {

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
            } else {
                st.execute(query);
            }
        }catch (SQLException e){
            System.err.println("Error interaction with DB!");
            e.printStackTrace();
        }

        queryObject.setColumnsTitle(columnsTitle);
        queryObject.setColumnsTable(columnsTable);

        return queryObject;
    }
}

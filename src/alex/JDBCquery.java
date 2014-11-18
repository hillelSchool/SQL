package alex;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JDBCquery {

    public void QuerySQL(String queryBR, Statement st){
        try {

            ResultSet rezQuery = st.executeQuery(queryBR);

            ResultSetMetaData rez = rezQuery.getMetaData();
            int count = rez.getColumnCount();
            for (int i = 1; i < count + 1; i++) {
                String a = rez.getColumnName(i); // Вывод запроса в консоль
                System.out.print(a + "  ");
                }

            System.out.println();
            while (rezQuery.next()) {
                for (int i = 1; i < count + 1; i++) {
                    String a = rezQuery.getString(i); // Вывод запроса в консоль
                    System.out.print(a + "  ");
                    }
                System.out.println();
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}


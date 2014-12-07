package alex;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCmenu {
    List<String> list = new ArrayList<String>();

    public void QuerySQL(String query, Statement st){
        try {
            ResultSet rezQuery = st.executeQuery(query);

            ResultSetMetaData rez = rezQuery.getMetaData();
            int count = rez.getColumnCount();

            while (rezQuery.next()) {
                for (int i = 1; i < count + 1; i++) {
                    String a = rezQuery.getString(i);
                    System.out.print(a + "  ");
                    }
                System.out.println();

                list.add(rezQuery.getString(2));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }


}


package alex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCmenu {
    String user = "student";//Логин пользователя
    String password = "P@ssw0rd";//Пароль пользователя
    String url = "jdbc:mysql://217.146.253.33/tram_groundhog_day";//URL адрес
    String driver = "com.mysql.jdbc.Driver";//Имя драйвера
    List<String> list = new ArrayList<String>();

    public void QuerySQL(String query){
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Connection c = null;//Соединение с БД

        try{
            c = DriverManager.getConnection(url, user, password);//Установка соединения с БД

            Statement st = c.createStatement();//Готовим запрос
            ResultSet rezQuery = st.executeQuery(query);

            ResultSetMetaData rez = rezQuery.getMetaData();
            int count = rez.getColumnCount();

            while (rezQuery.next()) {
                for (int i = 1; i < count + 1; i++) {
                    String a = rezQuery.getString(i); // Вывод запроса в консоль
                    System.out.print(a + "  ");
                    }
                System.out.println();

                list.add(rezQuery.getString(2));
            }


        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            //Обязательно необходимо закрыть соединение
            try {
                if(c != null)
                    c.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}


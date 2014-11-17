package alex;

import java.sql.*;

public class JDBCquery {
    String user = "student";                                        //Логин пользователя
    String password = "P@ssw0rd";                                   //Пароль пользователя
    String url = "jdbc:mysql://217.146.253.33/tram_groundhog_day";  //URL адрес
    String driver = "com.mysql.jdbc.Driver";                        //Имя драйвера

    public void QuerySQL(String queryBR){
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


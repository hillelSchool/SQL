package alex;

import java.sql.*;

public class JDBCjoin {
    String user = "student";//Логин пользователя
    String password = "P@ssw0rd";//Пароль пользователя
    String url = "jdbc:mysql://217.146.253.33/tram_groundhog_day";//URL адрес
    String driver = "com.mysql.jdbc.Driver";//Имя драйвера

    public void formatTable(){
        System.out.println("+---------------------+---------------------+");
        System.out.format("%23s", "|      Table A        |");
        System.out.format("%22s%n", " Table B       |");
        System.out.println("+-------+-------------+-------+-------------+");
        System.out.format("%8s", "|  id   |");
        System.out.format("%14s", "name   |");
        System.out.format("%8s", "id   |");
        System.out.format("%14s", "name   |");
        System.out.println();
        System.out.println("+-------+-------------+-------+-------------+");
    }


    public void testJoin(String query ){
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
            //String query =  "select * from tableA inner join tableB on tableA.name = tableB.name;";


            ResultSet innerJoin = st.executeQuery(query);//Выполняем запрос к БД, результат в переменной rsPhone
            System.out.printf("Result SQL query: %n" + query.toString().toUpperCase());
            System.out.println();
            System.out.println();
            formatTable();

            while (innerJoin.next()){
                String idA = innerJoin.getString("tableA.id");
                String nameA = innerJoin.getString("tableA.name");
                String idB = innerJoin.getString("tableB.id");
                String nameB = innerJoin.getString("tableB.name");
                System.out.print("|");
                System.out.format("%8s", idA  + " |");
                System.out.format("%14s", nameA  + " |");
                System.out.format("%8s",  idB  + " |");
                System.out.format("%14s%n", nameB + " |");
            }
            System.out.println("+-------+-------------+-------+-------------+");
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


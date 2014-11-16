package Den;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCrud {
    private static String query = ConsoleInputCrud.query;
    private static String update = ConsoleInputCrud.update;
//    private static String update = "insert into street (idcity, street_name) values (1, 'Polskyi_descent'), (1, 'Katerynynska')"; // CreateUpdateDelete BD
//    private static String update = "create table street (id int (10) not null auto_increment, idcity int (10) not null , street_name varchar (45), primary key (id), foreign key (idcity) references city (idcity))"; // CreateUpdateDelete BD

    protected static void connectQueryUpdateDB(Query queryToDBObject) {

        String user = "student";//Логин пользователя
        String password = "P@ssw0rd";//Пароль пользователя
        String url = "jdbc:mysql://217.146.253.33/tram_groundhog_day";//URL адрес
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found");
            e.printStackTrace();
        }
        Connection c = null;//Соединение с БД
        try {
            c = DriverManager.getConnection(url, user, password); //Установка соединения с БД
            Statement st = c.createStatement(); //Готовим запрос
            if (!query.equals("")) {
                printQueryUpdateText();//Вывод текста запроса и апдейта в консоль
                queryToDBObject.query(st);
            }

        } catch (Exception e) {
            System.err.println("Error connecting or reading");
            e.printStackTrace();
        } finally { //Обязательно необходимо закрыть соединение
            try {
                if (c != null)
                    c.close();
            } catch (SQLException e) {
                System.err.println("Error close connection");
                e.printStackTrace();
            }
        }
    }

    protected static void connectQueryUpdateDB() {

        String user = "student";//Логин пользователя
        String password = "P@ssw0rd";//Пароль пользователя
        String url = "jdbc:mysql://217.146.253.33/tram_groundhog_day";//URL адрес
        String driver = "com.mysql.jdbc.Driver";//Имя драйвера
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found");
            e.printStackTrace();
        }
        Connection c = null;//Соединение с БД
        try {
            c = DriverManager.getConnection(url, user, password); //Установка соединения с БД
            Statement st = c.createStatement(); //Готовим запрос
            if (!update.equals("")) {
                printQueryUpdateText();//Вывод текста запроса и апдейта в консоль
                updateDB(st);
            }

        } catch (Exception e) {
            System.err.println("Error connecting or reading");
            e.printStackTrace();
        } finally { //Обязательно необходимо закрыть соединение
            try {
                if (c != null)
                    c.close();
            } catch (SQLException e) {
                System.err.println("Error close connection");
                e.printStackTrace();
            }
        }
    }

    protected static void updateDB(Statement st) {
        try {
            st.execute(update);
        } catch (SQLException e) {
            System.err.println("Error update");
            e.printStackTrace();
        }
    }

    private static void printQueryUpdateText() {
        if (!query.equals("")) {
            String[] arrayQuery = query.split(" ");
            for (int i = 0; i < arrayQuery.length; i++) {
                switch (arrayQuery[i]) {
                    case "select":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        System.out.println();
                        break;
                    case "from":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        System.out.println();
                        break;
                    case "where":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        System.out.println();
                        break;
                    case "order":
                        arrayQuery[i + 1] = arrayQuery[i + 1].toUpperCase();
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        System.out.println();
                        break;
                    case "or":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "and":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "not":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "outer":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "left":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "join":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "on":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                }
                System.out.print(arrayQuery[i] + " ");
            }
            System.out.println("\n");
        }
        if (!update.equals("")) {
            String[] arrayQuery = update.split(" ");
            for (int i = 0; i < arrayQuery.length; i++) {
                switch (arrayQuery[i]) {
                    case "or":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "and":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "not":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "insert":
                        System.out.println();
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "into":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "values":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "delete":
                        System.out.println();
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "from":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                }
                System.out.print(arrayQuery[i] + " ");
            }
            System.out.println();
        }
    }
}
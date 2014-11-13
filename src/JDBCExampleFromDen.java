import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Внимание, код заговорен! Изменение данного кода(без согласия автора)
 * приведёт к половому бессилию и неизлечимым заболеваниям!!!
 */

public class JDBCExampleFromDen {
    private static int switchPrintBeforeAfter = 0;
    private static Date deadLine = null;

    // Запрос и апдейт БД вынес в статик что бы было удобно редактировать запросы(подсветка и подстановка IDEA)
    private static String query = "select phonebook.id, phone.number, phonebook.first_name, phonecall.description, phone.type, phonecall.dt from phone, phonebook, phonecall where phone.phonebook_id = phonebook.id and phone.id = phonecall.phone_id order by phonebook.id"; // Запрос к БД
    private static String update = "insert into phonebook (id, first_name, last_name, city, description) values (12,'Rita', 'Vrataski', 'UdF', 'Full Metal Bitch')"; // CreateUpdateDelete BD
//    private static String update = "delete from phonebook where id=11";

    public static void main(String[] args) {

        setDeadLine(); //Запрос времени относительно которого проводится сортировка запроса
        printQuery(); //Вывод запроса в консоль
        printUpdate(); //вывод апдейта БД в консоль

        connectQueryUpdateDB(); // конект к БД и выполнение запросов
    }

    private static void setDeadLine() {
        System.out.print("Введите время в формате hh:mm ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = reader.readLine();
            if (!line.equals("")) {
                deadLine = new SimpleDateFormat("HH:mm").parse(line);
            }
        } catch (ParseException | IOException e) {
            System.err.println("Error set time");
            e.printStackTrace();
        }
    }

    private static void connectQueryUpdateDB() {

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
                queryAndPrint(st);
            }
            if (!update.equals("")) {
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

    private static void queryAndPrint(Statement st) {
        StringBuilder buffer1 = new StringBuilder(""); //буфер куда записываем данные считаные из БД если звонок произведен раньше deadLine
        StringBuilder buffer2 = new StringBuilder(""); //буфер куда записываем данные считаные из БД если звонок произведен позже deadLine

        try {
            ResultSet rs = st.executeQuery(query); //Выполняем запрос к БД, результат в переменной rs
            while (rs.next()) {
                if (rs.getTime("dt").getTime() < deadLine.getTime()) {
                    buffer1.append(formatDateForPrint(rs));//Последовательно для каждой строки выводим значение из колонок
                } else {
                    buffer2.append(formatDateForPrint(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printTable(buffer1);
        printTable(buffer2);
    }

    private static void updateDB(Statement st) {
        try {
            st.execute(update);
        } catch (SQLException e) {
            System.err.println("Error update");
            e.printStackTrace();
        }
    }

    private static void printQuery() {
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
                }
                System.out.print(arrayQuery[i] + " ");
            }
            System.out.println();
        }
    }

    private static void printUpdate() {
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

    private static void printTable(StringBuilder buffer) {
        if (!buffer.toString().equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            System.out.println("\nCalls " + (switchPrintBeforeAfter == 0 ? "before " : "after ") + dateFormat.format(deadLine));
            System.out.println("+---+------------+------------------+-------------+----------------------------+-----------------------+");
            System.out.println("| № | first_name | phone number     | place       | description                | date                  |");
            System.out.println("+---+------------+------------------+-------------+----------------------------+-----------------------+");
            System.out.print(buffer);
            System.out.println("+---+------------+------------------+-------------+----------------------------+-----------------------+");
            switchPrintBeforeAfter++;
        } else {
            switchPrintBeforeAfter++;
        }
    }

    private static String formatDateForPrint(ResultSet rs) {
        String tempString = null;
        try {
            tempString = String.format("| %s | %-10s | %-16s |  %-10s | %-26s | %s |\n", rs.getString("id"), rs.getString("first_name"), rs.getString("number"), rs.getString("type"), rs.getString("description"), rs.getString("dt"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempString;
    }
}
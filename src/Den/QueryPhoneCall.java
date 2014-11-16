package Den;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryPhoneCall implements Query {

    public static String query = "select phonebook.id, phone.number, phonebook.first_name, phonecall.description, phone.type, phonecall.dt from phone, phonebook, phonecall where phone.phonebook_id = phonebook.id and phone.id = phonecall.phone_id order by phonebook.id"; // Запрос к БД

    private static int switchPrintBeforeAfter = 0;
    private static Date deadLine = null;

    public String getQuery() {
        return query;
    }

    public void query(Statement st) {
        StringBuilder buffer1 = new StringBuilder(""); //буфер куда записываем данные считаные из БД если звонок произведен раньше deadLine
        StringBuilder buffer2 = new StringBuilder(""); //буфер куда записываем данные считаные из БД если звонок произведен позже deadLine

        setDeadLine();//Запрос времени относительно которого проводится сортировка запроса
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
            tempString = String.format("| %s | %-10s | %-16s |  %-10s | %-26s | %s |\n",
                    rs.getString("id"),
                    rs.getString("first_name"),
                    rs.getString("number"),
                    rs.getString("type"),
                    rs.getString("description"),
                    rs.getString("dt"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempString;
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
}

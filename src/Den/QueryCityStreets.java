package Den;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class QueryCityStreets implements Query {

    private static String query = "select city.idcity, city.city_Name, street.street_name from city left outer join street on city.idcity = street.idcity order by city.idcity"; // Запрос к БД
    private static int count = 1;

    public String getQuery() {
        return query;
    }

    public void query(Statement st) {
        StringBuilder buffer1 = new StringBuilder(""); //буфер куда записываем данные считаные из БД если звонок произведен раньше deadLine

        try {
            ResultSet rs = st.executeQuery(query); //Выполняем запрос к БД, результат в переменной rs
            while (rs.next()) {
                buffer1.append(formatDateForPrint(rs));//Последовательно для каждой строки выводим значение из колонок
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printTable(buffer1);
    }

    private void printTable(StringBuilder buffer) {
        if (!buffer.toString().equals("")) {
            System.out.println("+---+--------+------------------+------------------+");
            System.out.println("| № | idcity | city_Name        | street_name      |");
            System.out.println("+---+--------+------------------+------------------+");
            System.out.print(buffer);
            System.out.println("+---+--------+------------------+------------------+");
        }
    }

    private String formatDateForPrint(ResultSet rs) {
        String tempString = null;
        try {
            tempString = String.format("| %s | %-6s | %-16s | %-16s |\n",
                    count++,
                    rs.getString("idcity"),
                    rs.getString("city_Name"),
                    rs.getString("street_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempString;
    }


}


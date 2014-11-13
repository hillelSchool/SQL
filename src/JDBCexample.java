import java.sql.*;
import java.util.Calendar;

/**
 * Created by Богдан on 13.11.2014.
 */
public class JDBCexample {
    String user = "student";//Логин пользователя
    String password = "P@ssw0rd";//Пароль пользователя
    String url = "jdbc:mysql://217.146.253.33/tram_groundhog_day";//URL адрес
    String driver = "com.mysql.jdbc.Driver";//Имя драйвера

    public void testInject(String phoneOwnName){
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
            String query = "select phone.added, phone.number, phonecall.dt," +
                    " phonebook.last_name, phonebook.id, " +
                    " phonecall.phone_id, phonecall.description " +
                    "from phonebook, phone, phonecall " +
                    "where phonebook.last_name like '%" +
                    phoneOwnName +
                    "%' and phonebook.id = phone.phonebook_id " +
                    "and phonebook.id = phonecall.phone_id;";
            //System.out.println(query);
            ResultSet rsPhone = st.executeQuery(query);//Выполняем запрос к БД, результат в переменной rsPhone
            while (rsPhone.next()){
                Calendar date = Calendar.getInstance();
                date.setTime(rsPhone.getTime("added"));

                String a = formatPrintQuery(rsPhone); // Вывод запроса в консоль

                System.out.println(a);
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

    public void callsByTime(String phoneOwnName, int hour, boolean operator){
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
            String query = "select phone.added, phone.number, phonecall.dt," +
                    " phonebook.last_name, phonebook.id, " +
                    " phonecall.phone_id, phonecall.description " +
                    "from phonebook, phone, phonecall " +
                    "where phonebook.last_name like '%" +
                    phoneOwnName +
                    "%' and phonebook.id = phone.phonebook_id " +
                    "and phonebook.id = phonecall.phone_id;";
            System.out.println(query);
            ResultSet rsPhone = st.executeQuery(query);//Выполняем запрос к БД, результат в переменной rsPhone
            String callAfterHour = "";
            String callBeforeHour = "";
            while (rsPhone.next()){
                Calendar date = Calendar.getInstance();
                date.setTime(rsPhone.getTime("added"));

                String a = formatPrintQuery(rsPhone); // Вывод запроса в консоль

                if (date.get(Calendar.HOUR_OF_DAY) >= hour) {
                    callAfterHour = callAfterHour + a + '\n';
                }
                else {
                    callBeforeHour = callBeforeHour + a + '\n';
                }
            }
            if (operator) {
                System.out.println("______");
                System.out.println("calls After");
                System.out.println(callAfterHour);
                System.out.println("______");
                System.out.println("cals before");
                System.out.println(callBeforeHour);
            }
            else{
                System.out.println("______");
                System.out.println("cals before");
                System.out.println(callBeforeHour);
                System.out.println("______");
                System.out.println("calls After");
                System.out.println(callAfterHour);
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

    private static String formatPrintQuery(ResultSet rsPhone) {
      String a = null;
        try {
            a = String.format("| %s | %-10s | %s | %-16s | %-25s |",
                            rsPhone.getString("added"),
                            rsPhone.getString("last_name"),
                            rsPhone.getString("phonecall.dt"),
                            rsPhone.getString("number"),
                            rsPhone.getString("phonecall.description"));
        }catch (SQLException e){
            System.out.println("Error formatPrintQuery()");
        }
        return a;
    }
}

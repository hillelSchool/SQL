package Den;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputCrud {
    public static String query = "";
    public static String update = "";

    public static void consoleInputCrud() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Read or CUD [R/u]: ");
            String line = reader.readLine().toLowerCase();
            if (line.equals("r") || line.equals("")) {
                System.out.println("1. - PhoneCall");
                System.out.println("2. - CityStreets");
                System.out.print("Input number of query to DB: ");
                String number = reader.readLine();
                chooseQueryToDB(number);

            } else if (line.equals("u")) {
                System.out.print("Input CUD to DB: ");
                update = reader.readLine();
                SQLCrud.connectQueryUpdateDB();
            }
            reader.close(); // ПОЧЕМУ ЕСЛИ ЗАКРЫТЬ ПОТОК ВВОДА ВЫБРАСЫВАЕТ ОШИБКУ так же как и в почте!!!! СПРОСИТЬ!
        } catch (IOException e) {
            System.out.println("Error console command");
        }
    }

    private static void chooseQueryToDB(String number) {
        switch (number) {
            case "1": {
                Query obj = new QueryPhoneCall();
                query = obj.getQuery();
                SQLCrud.connectQueryUpdateDB(obj);
                break;
            }
            case "2": {
                Query obj = new QueryCityStreets();
                query = obj.getQuery();
                SQLCrud.connectQueryUpdateDB(obj); // конект к БД и выполнение запросов
                break;
            }
            default:
                System.out.println("Incorrect choice!");
                break;
        }
    }
}

package Den;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class ConsoleOperation {
    public static String query = null;
    public static String menu = null;
    private static String pathToQueryToDBFile = "src/Den/QueryToDB.txt";

    public static void selectMenuItem() {
        BufferedReader reader = null;
        try {
            printMenu();
            System.out.print("Please, select the menu item: "); // оставил отдельной строкой для циклического меню
            reader = new BufferedReader(new InputStreamReader(System.in));
            menu = reader.readLine();
            processingMenu();
        } catch (IOException e) {
            System.out.println("Error input to console");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void processingMenu() {
        if (menu.trim().equals("1")) {
            doingQueryToDB();
        } else if (menu.trim().equals("2")) {
            doingTemplateQueryToDB();
        } else if (menu.trim().toLowerCase().equals("exit")) {
            System.out.println("Program close.");
            System.exit(0);    // Заменить на флаг циклического меню
        } else {
            System.out.println("Wrong command!");
        }
    }

    private static void doingQueryToDB() {
        System.out.print("Write your query to DB: ");
        query = readFromConsole().trim();
        printQueryText(); //вывод текста запроса в консоль до отсылки самого запроса
        ConnectAndCRUD.connectAndCRUDToDB();
    }

    private static void doingTemplateQueryToDB() {
        System.out.println("query list:");
        Map<Integer, String> mapQuery = readFile(pathToQueryToDBFile);
        int count = 1;
        for (String s : mapQuery.values()) {
            System.out.println(count + " " + s);
            count++;
        }
        System.out.print("Select number of query: ");
        int selectMenuItem = Integer.parseInt(readFromConsole().trim()) - 1;
        query = mapQuery.get(selectMenuItem).trim();
        printQueryText(); //вывод текста запроса в консоль до отсылки самого запроса
        ConnectAndCRUD.connectAndCRUDToDB();
    }

    private static void printMenu() {
        System.out.printf("%-29s %s\n", "for SQL Query write:", "1");
        System.out.printf("%-29s %s\n", "for SQL Template Query write:", "2");
        System.out.printf("%-29s %s\n", "exit from program write:", "exit");
    }

    private static String readFromConsole() {
        String lineConsole = null;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            lineConsole = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error input to console");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lineConsole;
    }

    private static Map<Integer, String> readFile(String pathFile) {
        Map<Integer, String> buffer = new TreeMap<>();
        BufferedReader reader = null;
        try {
            int count = 0;
            reader = new BufferedReader(new FileReader(pathFile));
            while (reader.ready()) {
                buffer.put(count, reader.readLine());
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error read file");
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error close reader");
                e.printStackTrace();
            }
        }
        return buffer;
    }

    private static void printQueryText() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!query.equals("")) {
            String[] arrayQuery = query.split(" ");
            for (String anArrayQuery : arrayQuery) {
                switch (anArrayQuery) {
                    case "select":
                        stringBuilder.append("\n");
                        break;
                    case "from":
                        stringBuilder.append("\n");
                        break;
                    case "where":
                        stringBuilder.append("\n");
                        break;
                    case "order":
                        stringBuilder.append("\n");
                        break;
                    case "insert":
                        stringBuilder.append("\n");
                        break;
                    case "delete":
                        stringBuilder.append("\n");
                        break;
                    case "left":
                        stringBuilder.append("\n");
                        break;
                    case "right":
                        stringBuilder.append("\n");
                        break;
                    case "alter":
                        stringBuilder.append("\n");
                        break;
                    case "update":
                        stringBuilder.append("\n");
                        break;
                }
                stringBuilder.append(anArrayQuery).append(" ");
            }
        }
        stringBuilder.append("\n");
        System.out.println(stringBuilder);
    }
}

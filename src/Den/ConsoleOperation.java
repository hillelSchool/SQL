package Den;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ConsoleOperation {
    public static String query = null;
    public static String menu = null;
    private static String pathToQueryToDBFile = "src/Den/QueryToDB.txt";
    private static boolean exitFromProgram = false;

    public static void selectMenuItem() {
        while (!exitFromProgram) {
            printMenu();
            menu = readFromConsole();
            processingMenu();
        }
    }

    private static void processingMenu() {
        if (menu.trim().equals("1")) {
            doingQueryToDB();
        } else if (menu.trim().equals("2")) {
            doingTemplateQueryToDB();
        } else if (menu.trim().toLowerCase().equals("exit")) {
            exitFromProgram = true;
        } else {
            System.out.println("Wrong command!");
        }
    }

    private static void doingQueryToDB() {
        System.out.print("Write your query to DB: ");
        query = readFromConsole().trim();
        printQueryText(); //вывод текста запроса в консоль до отсылки самого запроса
        PrintQueryObject.printQueryObject(QueryToDB.queryToDB(ConnectionToDB.getConnection(), query));
    }

    private static void doingTemplateQueryToDB() {
        boolean flag = false;

        System.out.println("query list:");
        Map<Integer, String> mapQuery = readFile(pathToQueryToDBFile);
        int count = 1;
        for (String s : mapQuery.values()) {
            System.out.println(count + " " + s);
            count++;
        }
        while (!flag) {
            System.out.print("Select number of query: ");
            try {
                int selectMenuItem = Integer.parseInt(readFromConsole().trim()) - 1;
                query = mapQuery.get(selectMenuItem).trim();
                flag = true;
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println("Incorrect choice");
            }
        }
        printQueryText();
        PrintQueryObject.printQueryObject(QueryToDB.queryToDB(ConnectionToDB.getConnection(), query));
    }

    private static void printMenu() {
        System.out.printf("%-29s %s\n", "for SQL Query write:", "1");
        System.out.printf("%-29s %s\n", "for SQL Template Query write:", "2");
        System.out.printf("%-29s %s\n", "exit from program write:", "exit");
        System.out.print("Please, select the menu item: ");
    }

    private static String readFromConsole() {
        String lineConsole;
        Scanner scanner = new Scanner(System.in);
        lineConsole = scanner.nextLine();
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
        if (query.length() > 70) {
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
        } else if (!query.equals("")) {
            stringBuilder.append("\n").append(query);
        }
        stringBuilder.append("\n");
        System.out.println(stringBuilder);
    }
}

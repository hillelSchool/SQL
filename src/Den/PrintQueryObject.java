package Den;

import java.util.ArrayList;
import java.util.List;

public class PrintQueryObject {
    private static String query = ConsoleOperation.query;
    private static List<Integer> columnLength = getColumnsLength();

    public static void printQueryObject() {
        for (int i = 0; i < QueryObject.columnName.size(); i++) {
            String line = "%-" + columnLength.get(i) + "s";
            System.out.printf(line, formatTextInColumn(QueryObject.columnName.get(i), i));
        }
        System.out.println();
        for (int i = 0; i < QueryObject.columnTable.size(); i++) {
            for (int j = 0; j < QueryObject.columnTable.get(i).size(); j++) {
                String line = "%-" + columnLength.get(j) + "s";
                System.out.printf(line, formatTextInColumn(String.valueOf(QueryObject.columnTable.get(i).get(j)), j));
            }
            System.out.println();
        }
    }

    public static void printQueryText() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!query.equals("")) {
            String[] arrayQuery = query.split(" ");
            for (int i = 0; i < arrayQuery.length; i++) {
                switch (arrayQuery[i]) {
                    case "select":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        stringBuilder.append("\n");
                        break;
                    case "from":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        stringBuilder.append("\n");
                        break;
                    case "where":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        stringBuilder.append("\n");
                        break;
                    case "order":
                        arrayQuery[i + 1] = arrayQuery[i + 1].toUpperCase();
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        stringBuilder.append("\n");
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
                    case "insert":
                        stringBuilder.append("\n");
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "into":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "values":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "delete":
                        stringBuilder.append("\n");
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                }
                stringBuilder.append(arrayQuery[i]).append(" ");
            }
        }
        System.out.println(stringBuilder);
    }

    private static List<Integer> getColumnsLength() {
        List<Integer> lengthColumnTitle = new ArrayList<>();
        List<Integer> lengthColumn = new ArrayList<>();
        for (int i = 0; i < QueryObject.columnName.size(); i++) {
            lengthColumnTitle.add(QueryObject.columnName.get(i).length());
        }
        for (int i = 0; i < QueryObject.columnTable.size(); i++) {
            for (int j = 0; j < QueryObject.columnTable.get(i).size(); j++) {
                if (i == 0) {
                    if (QueryObject.columnTable.get(i).get(j) == null) {
                        lengthColumn.add(0);
                    } else {
                        lengthColumn.add(QueryObject.columnTable.get(i).get(j).toString().length());
                    }
                } else {
                    if ((QueryObject.columnTable.get(i).get(j) != null)) {
                        if (lengthColumn.get(j) < QueryObject.columnTable.get(i).get(j).toString().length()) {
                            lengthColumn.set(j, QueryObject.columnTable.get(i).get(j).toString().length());
                        }
                    }
                }
            }
        }

        for (int i = 0; i < lengthColumnTitle.size(); i++) {
            if (lengthColumnTitle.get(i) < lengthColumn.get(i)) {
                lengthColumnTitle.set(i, lengthColumn.get(i));
            }
        }
        return lengthColumnTitle;
    }

    private static String formatTextInColumn(String textInColumn, Integer i) {
        StringBuilder stringBuilder = new StringBuilder();
        if (textInColumn == null) {
            textInColumn = "null";
        }
        stringBuilder.append(textInColumn);

        for (int j = 0; j < columnLength.get(i); j++) {
            if (stringBuilder.length() <= columnLength.get(i)) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }
}

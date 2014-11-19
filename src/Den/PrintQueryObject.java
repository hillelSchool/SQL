package Den;

import java.util.ArrayList;
import java.util.List;

public class PrintQueryObject {
    private static List<String> columnsTitleForPrint = QueryObject.columnsTitle;
    private static List<List<Object>> columnsTableForPrint = QueryObject.columnsTable;
    private static List<Integer> columnsLength = getColumnsLength();

    public static void printQueryObject() {
        for (int i = 0; i < columnsTitleForPrint.size(); i++) {
            String line = "%-" + columnsLength.get(i) + "s";
            System.out.printf(line, formatTextInColumn(columnsTitleForPrint.get(i), i));
        }
        System.out.println();
        for (int i = 0; i < columnsTableForPrint.size(); i++) {
            for (int j = 0; j < columnsTableForPrint.get(i).size(); j++) {
                String line = "%-" + columnsLength.get(j) + "s";
                System.out.printf(line, formatTextInColumn(String.valueOf(columnsTableForPrint.get(i).get(j)), j));
            }
            System.out.println();
        }
    }

    private static List<Integer> getColumnsLength() {
        List<Integer> lengthColumnTitle = new ArrayList<>();
        List<Integer> lengthColumn = new ArrayList<>();
        for (int i = 0; i < columnsTitleForPrint.size(); i++) {
            lengthColumnTitle.add(columnsTitleForPrint.get(i).length());
        }
        for (int i = 0; i < columnsTableForPrint.size(); i++) {
            for (int j = 0; j < columnsTableForPrint.get(i).size(); j++) {
                if (i == 0) {
                    if (columnsTableForPrint.get(i).get(j) == null) {
                        lengthColumn.add(0);
                    } else {
                        lengthColumn.add(columnsTableForPrint.get(i).get(j).toString().length());
                    }
                } else {
                    if ((columnsTableForPrint.get(i).get(j) != null)) {
                        if (lengthColumn.get(j) < columnsTableForPrint.get(i).get(j).toString().length()) {
                            lengthColumn.set(j, columnsTableForPrint.get(i).get(j).toString().length());
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

        for (int j = 0; j < columnsLength.get(i); j++) {
            if (stringBuilder.length() <= columnsLength.get(i)) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}

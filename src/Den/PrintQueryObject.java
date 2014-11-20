package Den;

import dnl.utils.text.table.TextTable;

import java.util.List;

public class PrintQueryObject {

    public static void printQueryObject(QueryObject queryObject) {
        List<String> columnsTitleForPrint = queryObject.getColumnsTitle();
        List<List<Object>> columnsTableForPrint = queryObject.getColumnsTable();

        String[] columnNames = new String[columnsTitleForPrint.size()];
        for (int i = 0; i < columnsTitleForPrint.size(); i++) {
            columnNames[i] = String.valueOf(columnsTitleForPrint.get(i));
        }

        Object[][] data = new Object[columnsTableForPrint.size()][columnsTableForPrint.get(0).size()];

        for (int i = 0; i < columnsTableForPrint.size(); i++) {
            for (int j = 0; j < columnsTableForPrint.get(i).size(); j++) {
                data[i][j] = columnsTableForPrint.get(i).get(j);
            }
        }

        TextTable tt = new TextTable(columnNames, data);
// this adds the numbering on the left
//        tt.setAddRowNumbering(true);
// sort by the first column
        tt.setSort(0);
        tt.printTable();
    }
}

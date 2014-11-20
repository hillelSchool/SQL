package Den;

import java.util.ArrayList;
import java.util.List;

public class QueryObject {
    private List<String> columnsTitle = new ArrayList<>();
    private List<List<Object>> columnsTable = new ArrayList<>();

    public List<String> getColumnsTitle() {
        return columnsTitle;
    }

    public void setColumnsTitle(List<String> columnsTitle) {
        this.columnsTitle = columnsTitle;
    }

    public List<List<Object>> getColumnsTable() {
        return columnsTable;
    }

    public void setColumnsTable(List<List<Object>> columnsTable) {
        this.columnsTable = columnsTable;
    }
}

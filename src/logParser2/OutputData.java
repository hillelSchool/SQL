package logParser2;

import java.util.List;
import java.util.TreeMap;

public class OutputData {
    private List<String> listModifiedData;
    private TreeMap<String, String> mapReplacement = new TreeMap<>();

    public TreeMap<String, String> getMapReplacement() {
        return mapReplacement;
    }

    public void putAllMapReplacement(TreeMap<String, String> mapReplacement){
        this.mapReplacement.putAll(mapReplacement);
    }

    public List<String> getListModifiedData() {
        return listModifiedData;
    }

    public void setListModifiedData(List<String> listModifiedData) {
        this.listModifiedData = listModifiedData;
    }
}

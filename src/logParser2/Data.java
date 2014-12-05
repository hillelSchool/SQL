package logParser2;

import java.util.List;
import java.util.TreeMap;

public class Data {

    private List<String> listRules;
    private List<String> listDataFromFile;
    private TreeMap<String, String> mapReplacement = new TreeMap<>();

    public TreeMap<String, String> getMapReplacement() {
        return mapReplacement;
    }

    public void putAllMapReplacement(TreeMap<String, String> mapReplacement){
        this.mapReplacement.putAll(mapReplacement);
    }

    public List<String> getListDataFromFile() {
        return listDataFromFile;
    }

    public void setListDataFromFile(List<String> listDataFromFile) {
        this.listDataFromFile = listDataFromFile;
    }

    public List<String> getListRules() {
        return listRules;
    }

    public void setListRules(List<String> listRules) {
        this.listRules = listRules;
    }
}

package logParser2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataProcessing {

    public static Set<String> FindDataForReplacement(List<String> listDataFromFile, String regex) {

        Set<String> setDataForReplacement = new HashSet<>();
        Pattern p = Pattern.compile(regex);

        for (String line : listDataFromFile) {
            Matcher m = p.matcher(line);
            while (m.find()) {
                String data = m.group();
                setDataForReplacement.add(data);
            }
        }
        return setDataForReplacement;
    }

    public static List<String> replacementListDataByMapReplacement(List<String> listData, Map<String, String> mapReplacement) {
        for (int i = 0; i < listData.size(); i++) {
            for (Map.Entry<String, String> entry : mapReplacement.entrySet()) {
                if (listData.get(i).contains(entry.getKey())) {
                    listData.set(i, listData.get(i).replaceAll(entry.getKey(), entry.getValue()));
                }
            }
        }
        return listData;
    }

    public static TreeMap<String, String> getMapReplacement(Set<String> setData, Set<String> setRandomData) {
        TreeMap<String, String> mapReplacement = new TreeMap<>();

        Iterator<String> iteratorSet = setRandomData.iterator();
        Iterator<String> iteratorSetData = setData.iterator();
        while (iteratorSet.hasNext() && iteratorSetData.hasNext()) {
            mapReplacement.put(iteratorSetData.next(), iteratorSet.next());
        }

        return mapReplacement;
    }
}
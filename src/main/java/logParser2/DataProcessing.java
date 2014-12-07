package logParser2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataProcessing {

    public OutputData transformationData(UserData userData, Data data) {
        OutputData outputData = new OutputData();

        if (userData.getParameters()[0].equals("-e")) {
            switch (userData.getParameters()[1]) {
                case "1":
                    List<String> listRules = data.getListRules();
                    for (String listRule : listRules) {

                        TreeMap<String, String> mapReplacement = getMapReplacement(data, listRule);

                        outputData.putAllMapReplacement(mapReplacement);
                        data.putAllMapReplacement(mapReplacement);

                        outputData.setListModifiedData(replacementListDataByMapReplacement(data));
                    }
                    break;
                case "2":
                    System.out.println("1->1'");
                    System.out.println("Bla-bla-bla");
                    break;
                default:
                    System.out.println("Script");
                    System.out.println("Bla-bla-bla");
                    break;
            }

        } else if (userData.getParameters()[0].equals("-d")) {
            outputData.setListModifiedData(replacementListDataByMapReplacement(data));
        }
        return outputData;
    }

    private List<String> replacementListDataByMapReplacement(Data data) {
        List<String> listModifiedData = data.getListDataFromFile();
        for (int i = 0; i < listModifiedData.size(); i++) {
            for (Map.Entry<String, String> entry : data.getMapReplacement().entrySet()) {
                if (listModifiedData.get(i).contains(entry.getKey())) {
                    //replaceAll takes "+" like concatenate lines!!! but this with Strings. if Object????
                    if (entry.getKey().contains("+")) {
                        String lineWithPlus = entry.getKey().replaceAll("\\+", "\\\\\\+");
                        listModifiedData.set(i, listModifiedData.get(i).replaceAll(lineWithPlus, entry.getValue()));
                    } else {
                        listModifiedData.set(i, listModifiedData.get(i).replaceAll(entry.getKey(), entry.getValue()));
                    }
                }
            }
        }
        return listModifiedData;
    }

    private static Set<String> FindDataForReplacement(List<String> listDataFromFile, String regex) {

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

    private TreeMap<String, String> getMapReplacement(Data data, String ruleLine) {

        String[] rule = ruleLine.split(" = ");

        Set<String> setDataForReplacement = DataProcessing.FindDataForReplacement(data.getListDataFromFile(), rule[1]);

        GetReplacementMethod replacementMethod = ReplacementMethods.getReplacementMethod(rule[0]);

        Set<String> setRandomData = replacementMethod.getRandom(setDataForReplacement);

        TreeMap<String, String> mapReplacement = new TreeMap<>();

        Iterator<String> iteratorSet = setRandomData.iterator();
        Iterator<String> iteratorSetData = setDataForReplacement.iterator();
        while (iteratorSet.hasNext() && iteratorSetData.hasNext()) {
            mapReplacement.put(iteratorSetData.next(), iteratorSet.next());
        }
        return mapReplacement;
    }
}
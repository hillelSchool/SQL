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

    public static Set<String> getRandomIPv4(Set<String> setData) {

        Set<String> setRandomData = new HashSet<>();

        for (int i = 0; i < setData.size(); i++) {
            StringBuilder randomIPv4;
            do {
                randomIPv4 =  new StringBuilder();
                int b1 = (int) ((Math.random()) * 246 + 10);
                int b2 = (int) ((Math.random()) * 256);
                int b3 = (int) ((Math.random()) * 256);
                int b4 = (int) ((Math.random()) * 256);
                randomIPv4.append(b1).append(".").append(b2).append(".").append(b3).append(".").append(b4);
            } while (setData.contains(String.valueOf(randomIPv4)) && setRandomData.contains(String.valueOf(randomIPv4)));
            setRandomData.add(String.valueOf(randomIPv4));
        }
        return setRandomData;
    }

    public static Set<String> getRandomIPv6(Set<String> setData) {

        Set<String> setRandomData = new HashSet<>();
        String[] ip = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        String randomIPv6 = "";
        for (int k = 0; k < setData.size(); k++) {
            do {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        randomIPv6 += String.valueOf(ip[(int) (Math.random() * 16)]);
                    }
                    if (i != 7) {
                        randomIPv6 += ":";
                    }
                }
            } while (setData.contains(randomIPv6) && setRandomData.contains(randomIPv6));
            setRandomData.add(randomIPv6);
        }
        return setRandomData;
    }

    public static Set<String> getRandomDomain(Set<String> setData) {
        Set<String> setRandomData = new HashSet<>();
        String[] domain = new String[27];
        char ch;
        int count = 0;
        for (ch = 'a'; ch <= 'z'; ch++) {
            domain[count] = String.valueOf(ch);
            count++;
        }
        String[] domainSuffix = {"com", "edu", "gov", "mil", "net", "org", "int"};

        String randomDomain = "";
        for (int k = 0; k < setData.size(); k++) {
            do {
                for (int i = 0; i < (int) (Math.random() * 2 + 1); i++) {
                    int length = (int) (Math.random() * 3 + 2);
                    for (int j = 0; j < length; j++) {
                        randomDomain += String.valueOf(domain[(int) (Math.random() * domain.length)]);
                    }
                    if (i < 2) {
                        randomDomain += ".";
                    }
                }
                randomDomain += domainSuffix[(int) (Math.random() * domainSuffix.length)];
            } while (setData.contains(randomDomain) && setRandomData.contains(randomDomain));
            setRandomData.add(randomDomain);
        }
        return setRandomData;
    }
}
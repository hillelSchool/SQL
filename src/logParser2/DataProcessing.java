package logParser2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataProcessing {

    public static void encrypt() {

        List<String> listStringsFromFile = OperationWithFile.readFromSourceFile();

        List<String> listIPv4 = new ArrayList<>();
        List<String> listIPv6 = new ArrayList<>();
        List<String> listHost = new ArrayList<>();

        //поиск данных необходимых для замены. Один раз прохожу по всему файлу и ищу все необходимые замены.
        for (int i = 0; i < listStringsFromFile.size(); i++) {
            String line = listStringsFromFile.get(i);

            matchDataFromLineSourceFile(listIPv4, line, "((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)");

            matchDataFromLineSourceFile(listIPv6, line, "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))");

            matchDataFromLineSourceFile(listHost, line, "(([a-z0-9]+\\.){1,}((com)|(edu)|(gov)|(mil)|(net)|(org)|(int)|(ua)))");
        }

        Map<String, String> mapForLog = new TreeMap<>();
        //подбор рендомных значений к найденым данным
        for (int i = 0; i < listIPv4.size(); i++) {
            mapForLog.put(listIPv4.get(i), getRandomIPv4(listIPv4, mapForLog));
        }
        for (int i = 0; i < listIPv6.size(); i++) {
            mapForLog.put(listIPv6.get(i), getRandomIPv6(listIPv6, mapForLog));
        }
        for (int i = 0; i < listHost.size(); i++) {
            mapForLog.put(listHost.get(i), getRandomDomain(listHost, mapForLog));
        }

        // замена найденых данных на рендомные
        for (int i = 0; i < listStringsFromFile.size(); i++) {
            for (Map.Entry<String, String> entry : mapForLog.entrySet()) {
                if (listStringsFromFile.get(i).contains(entry.getKey())) {
                    listStringsFromFile.set(i, listStringsFromFile.get(i).replaceAll(entry.getKey(), entry.getValue()));
                }
            }
        }

        // вывод на экран таблицы соответсвия после замены
        for (Map.Entry<String, String> entry : mapForLog.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        // запись измененного файла
        OperationWithFile.writeToSourceFile(listStringsFromFile);
        // и таблицы соответсвия
        OperationWithFile.writeToLogFile(mapForLog);
    }

    public static void decrypt() {
        //читаем файл для изменения
        List<String> listFromFile = OperationWithFile.readFromSourceFile();
        //читаем таблицу соответствия из лога
        Map<String, String> map = OperationWithFile.readFromLogFile();
        //замена данных согласно таблицы соответсвия
        for (int i = 0; i < listFromFile.size(); i++) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (listFromFile.get(i).contains(entry.getValue())) {
                    listFromFile.set(i, listFromFile.get(i).replaceAll(entry.getValue(), entry.getKey()));
                }
            }
        }
        //запись измененного файла
        OperationWithFile.writeToSourceFile(listFromFile);
    }

    private static void matchDataFromLineSourceFile(List<String> listDataFromFile, String line, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        while (m.find()) {
            String data = m.group();
            if (!listDataFromFile.contains(data)) {
                listDataFromFile.add(data);
            }
        }
    }

    public static String getRandomIPv4(List<String> list, Map<String, String> mapForLog) {

        String randomIPv4;
        int b1 = (int) ((Math.random()) * 246 + 10);
        int b2 = (int) ((Math.random()) * 256);
        int b3 = (int) ((Math.random()) * 256);
        int b4 = (int) ((Math.random()) * 256);

        do {
            randomIPv4 = b1 + "." + b2 + "." + b3 + "." + b4;
        } while (list.contains(randomIPv4) && mapForLog.containsValue(randomIPv4));

        return randomIPv4;
    }

    public static String getRandomIPv6(List<String> list, Map<String, String> mapForLog) {

        String[] ip = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        String randomIPv6 = "";
        do {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    randomIPv6 += String.valueOf(ip[(int) (Math.random() * 16)]);
                }
                if (i != 7) {
                    randomIPv6 += ":";
                }
            }
        } while (list.contains(randomIPv6) && mapForLog.containsValue(randomIPv6));

        return randomIPv6;
    }

    public static String getRandomDomain(List<String> list, Map<String, String> mapForLog) {

        String[] domain = new String[27];
        char ch;
        int count = 0;
        for (ch = 'a'; ch <= 'z'; ch++) {
            domain[count] = String.valueOf(ch);
            count++;
        }
        String[] domainSuffix = {"com", "edu", "gov", "mil", "net", "org", "int"};

        String randomDomain = "";
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
        } while (list.contains(randomDomain) && mapForLog.containsValue(randomDomain));
        return randomDomain;
    }
}
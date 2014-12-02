package logParser2;

import java.util.HashSet;
import java.util.Set;

public class ReplacementMethods {

    public static Set<String> getRandomMethod(String method, Set<String> setData) {
        Set<String> set = null;
        if (method.equals("IPv4")) {
            set = getRandomIPv4(setData);
        } else if (method.equals("IPv6")) {
            set = getRandomIPv6(setData);
        } else if (method.equals("Domain")) {
            set = getRandomDomain(setData);
        }
        return set;
    }

    public static Set<String> getRandomIPv4(Set<String> setData) {

        Set<String> setRandomData = new HashSet<>();

        for (int i = 0; i < setData.size(); i++) {
            StringBuilder randomIPv4;
            do {
                randomIPv4 = new StringBuilder();
                int b1 = (int) ((Math.random()) * 246 + 10);
                int b2 = (int) ((Math.random()) * 256);
                int b3 = (int) ((Math.random()) * 256);
                int b4 = (int) ((Math.random()) * 256);
                randomIPv4.append(b1).append(".").append(b2).append(".").append(b3).append(".").append(b4);
            }
            while (setData.contains(String.valueOf(randomIPv4)) && setRandomData.contains(String.valueOf(randomIPv4)));
            setRandomData.add(String.valueOf(randomIPv4));
        }
        return setRandomData;
    }

    public static Set<String> getRandomIPv6(Set<String> setData) {

        Set<String> setRandomData = new HashSet<>();
        String[] ip = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        String randomIPv6;
        for (int k = 0; k < setData.size(); k++) {
            do {
                randomIPv6 = "";
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

        String randomDomain;
        for (int k = 0; k < setData.size(); k++) {
            randomDomain = "";
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

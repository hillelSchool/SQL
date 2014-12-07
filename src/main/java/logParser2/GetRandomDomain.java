package logParser2;

import java.util.HashSet;
import java.util.Set;

public class GetRandomDomain implements GetReplacementMethod {
    @Override
    public Set<String> getRandom(Set<String> setData) {
        Set<String> setRandomData = new HashSet<>();
        String[] domain = new String[26];
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

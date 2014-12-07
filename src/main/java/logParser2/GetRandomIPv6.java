package logParser2;

import java.util.HashSet;
import java.util.Set;

public class GetRandomIPv6 implements GetReplacementMethod{
    @Override
    public Set<String> getRandom(Set<String> setData) {
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
}

package logParser2;

import java.util.HashSet;
import java.util.Set;

public class GetRandomIPv4 implements GetReplacementMethod{

    @Override
    public Set<String> getRandom(Set<String> setData) {

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
}

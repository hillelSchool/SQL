package logParser2;

import java.util.HashSet;
import java.util.Set;

public class GetRandomEmail implements GetReplacementMethod {
    @Override
    public Set<String> getRandom(Set<String> setData) {
        /**
         * mkyong+100@gmail.com //ПРИ replaceAll данного email он не меняется, т.к. replaceAll воспринимает "+" как соединение строк!!!!!
         */
        Set<String> setRandomData = new HashSet<>();

        int count = 1;
        String emailRandom = "email_";
        for (int i = 0; i < setData.size(); i++) {
            setRandomData.add(emailRandom + count);
            count++;
        }

        return setRandomData;
    }
}

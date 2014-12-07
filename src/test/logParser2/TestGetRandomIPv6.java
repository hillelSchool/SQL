package logParser2;


import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;

public class TestGetRandomIPv6 {
    @Test
    public void testGetRandomIPv6(){
        Set<String> setIPv6in = new HashSet<String>();
        Set<String> setIPv6out = new HashSet<String>();

        setIPv6in.add("FE80:0000:0000:0000:0202:B3FF:FE1E:8329");
        setIPv6in.add("FE80:0000:0000:0000:0202:B3FF:FE1E:8330");
        setIPv6in.add("FE80:0000:0000:0000:0202:B3FF:FE1E:8331");
        setIPv6in.add("FE80:0000:0000:0000:0202:B3FF:FE1E:8332");
        setIPv6in.add("FE80:0000:0000:0000:0202:B3FF:FE1E:8333");

        GetRandomIPv6 getRandomIPv6 = new GetRandomIPv6();

        setIPv6out = getRandomIPv6.getRandom(setIPv6in);

        String IPv6_NAME_PATTERN = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";

        Pattern pIPv6 = Pattern.compile(IPv6_NAME_PATTERN);

        for (String str : setIPv6out){
            Matcher mt = pIPv6.matcher(str);
            if(!mt.find()) fail();
        }
    }



}

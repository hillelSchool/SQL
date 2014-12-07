package logParser2;
        import org.junit.Test;
        import static org.junit.Assert.fail;
        import java.util.HashSet;
        import java.util.Set;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class TestGetRandomDomain {
    @Test
    public void testGetRandomDomain(){

        Set<String> setDomainNameIn  = new HashSet<String>();
        Set<String> setDomainNameOut  = new HashSet<String>();
        GetRandomDomain getRandomDomain = new GetRandomDomain();

        setDomainNameIn.add("google.com");
        setDomainNameIn.add("bing.com");
        setDomainNameIn.add("yandex.ru");
        setDomainNameIn.add("meta.ua");
        setDomainNameIn.add("google.com.ua");

        setDomainNameOut = getRandomDomain.getRandom(setDomainNameIn);

        String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";

        Pattern pDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);

        for (String str : setDomainNameOut){
            Matcher mt = pDomainName.matcher(str);
            if(!mt.find()) fail();
        }
    }
}



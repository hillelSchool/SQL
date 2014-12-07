package logParser2;

import org.junit.*;
import static org.junit.Assert.fail;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;

public class TestGetRandomEmail {
   @org.junit.Test
    public void testGetRandomEmail(){

       Set<String> setEmailIn = new HashSet<>();
       Set<String> setEmailOut = new HashSet<>();
       GetRandomEmail getRandomEmail = new GetRandomEmail();

       setEmailIn.add("Ivan@gmail.com");
       setEmailIn.add("Petr@gmail.com");
       setEmailIn.add("Sidr@gmail.com");
       setEmailIn.add("Marfa@gmail.com");
       setEmailIn.add("Ivanka@gmail.com");

       setEmailOut = getRandomEmail.getRandom(setEmailIn);

//       for (String str : setEmailIn) System.out.println(str);
//       for (String str : setEmailOut) System.out.println(str);


       String EMAIL_NAME_PATTERN = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

       Pattern pEmail = Pattern.compile(EMAIL_NAME_PATTERN);

       for (String str : setEmailIn){
           Matcher mt = pEmail.matcher(str);
           if(!mt.find()) fail();
       }



    }

}

package logParser2;


import org.junit.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.fail;

public class TestGetRandomIPv4 {
    @Test
    public void testGetRandomIPv4(){
        Set<String> setIPv4in = new HashSet<String>();
        Set<String> setIPv4out = new HashSet<String>();
        setIPv4in.add("10.10.10.10");
        setIPv4in.add("127.0.0.1");

        GetRandomIPv4 getRandomIPv4 = new GetRandomIPv4();

        setIPv4out = getRandomIPv4.getRandom(setIPv4in);

        for (String s : setIPv4out) {
           String[] arr = s.split("\\.");

               if (Integer.valueOf(arr[0]) < 10 && Integer.valueOf(arr[0]) > 256){
                   fail();
               }
               if (Integer.valueOf(arr[1]) < 0 && Integer.valueOf(arr[1]) > 256){
                   fail();
               }
               if (Integer.valueOf(arr[2]) < 0 && Integer.valueOf(arr[2]) > 256){
                   fail();
               }
               if (Integer.valueOf(arr[3]) < 0 && Integer.valueOf(arr[3]) > 256){
                   fail();
               }
            }
        }


}


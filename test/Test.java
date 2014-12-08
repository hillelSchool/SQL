import junit.framework.TestCase;
import logParser2.Menu;

import java.io.*;
import java.io.PrintStream;

/**
 * Created by Богдан on 08.12.2014.
 */
public class Test extends TestCase {


    public void testPrints(){
        PrintStream original = System.out;
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        PrintStream p = new PrintStream(buff);
        System.setOut(p);
        new Menu().printMenuBanner();
        String expacted = "Encrypt - 1 | Decrypt - 2 | Exit - exit" + "\r\n" + "Select menu item: ";
        String actual = new String(buff.toByteArray());
        System.setOut(original);

        System.out.println(actual);
        System.out.println("_");
        System.out.println(expacted);



        assertEquals(expacted, actual);


    }
}

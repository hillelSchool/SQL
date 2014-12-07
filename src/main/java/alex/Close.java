package alex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Close {

    public static void exit() throws IOException {
        String select;
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        while (true) {
            System.out.println("Continue ... (Y / N)?");
            select = br.readLine();
            if (select.toUpperCase().equals("Y")) break;
            if (select.toUpperCase().equals("N")) {

                System.exit(0);
            }

        }
    }
}

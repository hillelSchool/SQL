package logParser;

import java.util.Random;

public class IpAddressCreate {

    public static String ipAddressGenerator(String str) {

        Random r = new Random();
        String ip = null;
        String[] arr = str.split("\\.");

        int value = Integer.valueOf(arr[0]);

        if (value > 0 & value < 128) {
            ip = "10." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
        } else if (value > 127 & value < 192) {
            ip = "172." + (r.nextInt(15) + 16) + "." + r.nextInt(256) + "." + r.nextInt(256);
        } else if (value > 191 & value < 224) {
            ip = "192.168." + r.nextInt(256) + "." + r.nextInt(256);
        }

    return ip;

    }
}

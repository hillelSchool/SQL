package logParser;

import java.util.Random;


public class IpModify {
    public static String ipAddressModify(String str) {

        String ip = null;
        String[] arr = str.split("\\.");

        ip =  "192.168."+arr[2]+"."+arr[3];

        return ip;

    }
}

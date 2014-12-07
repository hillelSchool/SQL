package logParser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyLogQueue {

    public static void main(String[] args) throws InterruptedException {

        BufferedReader br = null;
        try {
            String sCurrentLine;
            int lineCount = 0;
            Map<String, String> mapIP = new HashMap<String, String>();
            DateStamp ds = new DateStamp();

            File fileOutput = new File(".\\src\\logParser\\output.txt");
            File fileInput = new File(".\\src\\logParser\\input.txt");
            File fileInputLog = new File(".\\src\\logParser\\inputLog.txt");

            br = new BufferedReader(new FileReader(fileOutput));


            FileWriter fwInput = new FileWriter(fileInput.getAbsoluteFile());
            BufferedWriter bwInput = new BufferedWriter(fwInput);

            FileWriter fwInputLog = new FileWriter(fileInputLog.getAbsoluteFile(), true);
            BufferedWriter bwInputLog = new BufferedWriter(fwInputLog);


            if (!fileInput.exists()) {
                fileInput.createNewFile();
            }

            if (!fileInputLog.exists()) {
                fileInputLog.createNewFile();
            }

            String ipPattern = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

            Pattern pt = Pattern.compile(ipPattern);
            // change each line in text
            while ((sCurrentLine = br.readLine()) != null) {
                lineCount++;
                Matcher mt = pt.matcher(sCurrentLine);
                if (mt.find()) {
                    String ipAddress = mt.group(1) + "." + mt.group(2) + "." + mt.group(3) + "." + mt.group(4);
                    mapIP.put(ipAddress, null);
                }

                for (Map.Entry<String, String> entry : mapIP.entrySet()) {
                    mapIP.put(entry.getKey(), IpAddressCreate.ipAddressGenerator(entry.getKey()));
                }

                for (Map.Entry<String, String> entry : mapIP.entrySet()){
                    if (sCurrentLine.matches("(.*)" + entry.getKey() + "(.*)" )) {
                        sCurrentLine = sCurrentLine.replaceAll(entry.getKey(), entry.getValue());

                    } else continue;

                    bwInputLog.write(ds.dateStamp() + " " + "Line number: " + lineCount + " IP change: " + entry.getKey() +
                            " ----> " + entry.getValue() + "\n");

                }
                bwInput.write(sCurrentLine + "\n");
            }
            br.close();
            bwInput.close();
            bwInputLog.close();
        }


        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e)           { e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

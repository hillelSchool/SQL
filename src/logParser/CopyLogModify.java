package logParser;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyLogModify {

    public static void main(String[] args) {

        BufferedReader br = null;

        try {
            DateStamp ds = new DateStamp();
            IpModify newIpModify = new IpModify();

            int lineCount = 0;
            String sCurrentLine;
            String ipAddress = null;
            String strRepl = null;
            String strOut;

            File fileOutput = new File(".\\src\\logParser\\output.txt");
            File fileInput = new File(".\\src\\logParser\\input.txt");
            File fileInputLog = new File(".\\src\\logParser\\inputLog.txt");

            br = new BufferedReader(new FileReader(fileOutput));

            if (!fileInput.exists()) {
                fileInput.createNewFile();
            }
            if (!fileInputLog.exists()) {
                fileInputLog.createNewFile();
            }

            FileWriter fwInput = new FileWriter(fileInput.getAbsoluteFile());
            BufferedWriter bwInput = new BufferedWriter(fwInput);

            FileWriter fwInputLog = new FileWriter(fileInputLog.getAbsoluteFile(), true);
            BufferedWriter bwInputLog = new BufferedWriter(fwInputLog);


            String ipPattern = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                               "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                               "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                               "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

            Pattern pt = Pattern.compile(ipPattern);


            while ((sCurrentLine = br.readLine()) != null) {
                Matcher mt = pt.matcher(sCurrentLine);
                lineCount++;

                if (mt.find()) {
                    ipAddress = mt.group(1) + "." + mt.group(2) + "." + mt.group(3) + "." + mt.group(4);

                    strRepl = newIpModify.ipAddressModify(ipAddress);
                    System.out.println(ipAddress + " ---> " + strRepl + "  line#" + lineCount);
                    strOut = sCurrentLine.replaceAll(ipAddress, strRepl);
                    bwInput.write(strOut + "\n");
                    bwInputLog.write(ds.dateStamp() + " " + "Line number: " + lineCount + " IP change: " + ipAddress + " ----> " + strRepl + "\n");
                } else {
                    bwInput.write(sCurrentLine + "\n");
                }
            }
        bwInput.close();
            bwInputLog.close();
            br.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }


}

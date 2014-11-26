package logParser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyLog {
 
	public static void main(String[] args) {
 
		BufferedReader br = null;
 
		try {
            DateStamp ds = new DateStamp();
            IpAddressCreate newIP = new IpAddressCreate();
            IpModify newIpModify = new IpModify();

            int lineCount = 0;
            String sCurrentLine;
            String strOut = null;
            Map<String, String> mapIPAddress = new HashMap<String, String>();

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
                    String ipAddress = mt.group(1) + "." + mt.group(2) + "." + mt.group(3) + "." + mt.group(4);
                    mapIPAddress.put(ipAddress, null);

                    for (Map.Entry<String, String> entry : mapIPAddress.entrySet()) {
                        if (!ipAddress.equals(entry.getKey())) {
                            mapIPAddress.put(ipAddress, entry.setValue(newIP.ipAddressGenerator(entry.getKey())));
                            }
                        else continue;
                        String strRepl = entry.getValue();
                        strOut = sCurrentLine.replaceAll(ipAddress, strRepl);
                        System.out.println(strOut);
                    }
                }

            }

            for (Map.Entry<String, String> entry : mapIPAddress.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            bwInput.close();
            bwInputLog.close();
            br.close();
        } catch (IOException e) {
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
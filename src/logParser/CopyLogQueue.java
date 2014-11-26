package logParser;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyLogQueue {

    public static void main(String[] args) throws InterruptedException {

        BufferedReader br = null;

        try {
            String sCurrentLine;
            int lineCount;
            Queue<String> q = new LinkedList<String>();
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

            // Регулярное выражение для поиска IP адреса
            String ipPattern = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                               "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                               "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                               "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

            Pattern pt = Pattern.compile(ipPattern);
            lineCount = 0;
            // Запись в очередь сток из файла
            while ((sCurrentLine = br.readLine()) != null) {
                // Добавление в очередь объекта
                lineCount++;
                q.add(sCurrentLine);
                Matcher mt = pt.matcher(sCurrentLine);
                if (mt.find()) {
                    String ipAddress = mt.group(1) + "." + mt.group(2) + "." + mt.group(3) + "." + mt.group(4);
                    mapIP.put(ipAddress, null);
                }
                //bwInputLog.write(lineCount + "  " + sCurrentLine + "\n");
            }

            for (Map.Entry<String, String> entry : mapIP.entrySet()) {
                mapIP.put(entry.getKey(), IpAddressCreate.ipAddressGenerator(entry.getKey()));
            }

            lineCount = 0;
            // Считывание из очереди строк и запись в файл
            while( !q.isEmpty() ){
                lineCount++;
                // Забираем и удаляем из очереди объект
                String str = q.poll();

                for (Map.Entry<String, String> entry : mapIP.entrySet()){
                    if (str.matches("(.*)" + entry.getKey() + "(.*)" )) {
                        str = str.replaceAll(entry.getKey(), entry.getValue());

                    } else continue;

                    bwInputLog.write(ds.dateStamp() + " " + "Line number: " + lineCount + " IP change: " + entry.getKey() +
                            " ----> " + entry.getValue() + "\n");

                }
                bwInput.write(str + "\n");
        }


            br.close();
            bwInput.close();
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

package logParser2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class OperationWithFile {

    protected static List<String> readFromSourceFile() {
        System.out.print("path to source file: ");
        String pathToFile = Menu.readFromConsole();
        List<String> listLineFromFile = new ArrayList<String>();
        BufferedReader readFromFile = null;
        try {
            readFromFile = new BufferedReader(new FileReader(pathToFile));
            while (readFromFile.ready()) {
                listLineFromFile.add(readFromFile.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (readFromFile != null) {
                    readFromFile.close();
                    System.out.println("File was read");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listLineFromFile;
    }

    protected static void writeToSourceFile(List<String> list) {
        BufferedWriter writeToFile = null;
        try {
            System.out.print("path to new file: ");
            writeToFile = new BufferedWriter(new FileWriter(Menu.readFromConsole()));
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                    writeToFile.write(list.get(i) + "\n");
                } else {
                    writeToFile.write(list.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writeToFile != null) {
                    writeToFile.close();
                    System.out.println("File changed!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected static Map<String, String> readFromLogFile() {
        Map<String,String> map = new HashMap<>();
        BufferedReader readFromLog = null;
        String pathToFile = null;
        try {
            System.out.print("path to log file: ");
            pathToFile = Menu.readFromConsole();
            readFromLog = new BufferedReader(new FileReader(pathToFile));
            while (readFromLog.ready()) {
                String[] line = readFromLog.readLine().split(" ");
                map.put(line[0], line[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (readFromLog != null) {
                    readFromLog.close();
                    System.out.println("File " + pathToFile + " was read");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    protected static void writeToLogFile(Map<String, String> map) {
        BufferedWriter writeToFile = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(".yyyy.MM.dd.kk.mm");
        String pathToLogFile = "./log" + dateFormat.format(new Date()) + ".txt";
        try {
            writeToFile = new BufferedWriter(new FileWriter(pathToLogFile));
            int count = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (count != map.size() - 1) {
                    writeToFile.write(entry.getKey() + " " + entry.getValue() + "\n");
                    count++;
                } else {
                    writeToFile.write(entry.getKey() + " " + entry.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writeToFile != null) {
                    writeToFile.close();
                    System.out.println("File " + pathToLogFile + " was created.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

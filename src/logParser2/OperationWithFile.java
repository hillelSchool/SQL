package logParser2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OperationWithFile {

    public Data readDataFromFiles(UserData userData) {

        Data data = new Data();

        if (userData.getParameters()[0].equals("-e")) {
            switch (userData.getParameters()[1]) {
                case "1":
                    data.setListDataFromFile(readFromFile(userData.getPathToOriginFile()));

                    data.setListRules(readFromFile(userData.getPathToRulesFile()));
                    break;
                case "2":
                    System.out.println("1->1'");
                    System.out.println("Bla-bla-bla");
                    break;
                default:
                    System.out.println("Script");
                    System.out.println("Bla-bla-bla");
                    break;
            }

        } else if (userData.getParameters()[0].equals("-d")) {
            List<String> listReplacement = readFromFile(userData.getPathToMapReplacementFile());
            TreeMap<String, String> mapReplacement = new TreeMap<>();
            for (String s : listReplacement) {
                String[] str = s.split(" ");
                mapReplacement.put(str[1], str[0]);
            }
            data.putAllMapReplacement(mapReplacement);

            data.setListDataFromFile(readFromFile(userData.getPathToOriginFile()));
        }

        return data;
    }

    public void writeModifyDataToFile(UserData userData, OutputData outputData) {

        if (userData.getParameters()[0].equals("-e")) {
            switch (userData.getParameters()[1]) {
                case "1":

                    List<String> listReplacement = new ArrayList<>();
                    for (Map.Entry<String, String> entry : outputData.getMapReplacement().entrySet()) {
                        listReplacement.add(entry.getKey() + " " + entry.getValue());
                    }
                    writeToFile(userData.getPathToMapReplacementFile(), listReplacement);

                    writeToFile(userData.getPathToDestinationFile(), outputData.getListModifiedData());
                    break;
                case "2":
                    System.out.println("1->1'");
                    System.out.println("Bla-bla-bla");
                    break;
                default:
                    System.out.println("Script");
                    System.out.println("Bla-bla-bla");
                    break;
            }

        } else if (userData.getParameters()[0].equals("-d")) {

            writeToFile(userData.getPathToDestinationFile(), outputData.getListModifiedData());
        }
    }

    private static List<String> readFromFile(String pathToFile) {

        List<String> listDataFromFile = new ArrayList<>();
        boolean stopReadingFile = false;
        BufferedReader readFromFile = null;
        try {
            readFromFile = new BufferedReader(new FileReader(pathToFile));
            while (!stopReadingFile) {
                try {
                    while (readFromFile.ready()) {
                        listDataFromFile.add(readFromFile.readLine());
                    }
                    stopReadingFile = true;
                } catch (IOException e) {
                    System.out.println("Error read data!");
                    boolean flag = false;
                    while (!flag) {
                        System.out.print("Try again? Y/n: ");
                        String consoleLine = CreateUserData.readFromConsole().toLowerCase();
                        if (consoleLine.equals("n")) {
                            stopReadingFile = true;
                        } else if (consoleLine.equals("y")) {
                            flag = true;
                        } else {
                            System.out.println("Menu item was incorrectly selected!");
                        }
                    }
                }
            }

            System.out.println("Data from " + pathToFile + " was read");
        } catch (FileNotFoundException e) {
            System.out.println("File not exist!");
        }
        try {
            readFromFile.close();
        } catch (IOException e) {
            //NOD
        }
        return listDataFromFile;
    }

    private static void writeToFile(String pathToFile, List<String> listModifyData) {
        BufferedWriter writeToFile;
        boolean stopWriteFile = false;

        while (!stopWriteFile) {
            try {
                writeToFile = new BufferedWriter(new FileWriter(pathToFile));
                for (int i = 0; i < listModifyData.size(); i++) {
                    if (i != listModifyData.size() - 1) {
                        writeToFile.write(listModifyData.get(i) + "\n");
                    } else {
                        writeToFile.write(listModifyData.get(i));
                    }
                }
                writeToFile.close();
                System.out.println("The file " + pathToFile + " is recorded!");
                stopWriteFile = true;
            } catch (IOException e) {
                System.out.println("Error write data!");
                boolean flag = false;
                while (!flag) {
                    System.out.print("Try again? Y/n: ");
                    String consoleLine = CreateUserData.readFromConsole().toLowerCase();
                    if (consoleLine.equals("n")) {
                        stopWriteFile = true;
                    } else if (consoleLine.equals("y")) {
                        flag = true;
                    } else {
                        System.out.println("Menu item was incorrectly selected!");
                    }
                }
            }
        }
    }
}
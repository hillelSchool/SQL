package logParser2;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * IPv4 - "((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)"
 * IPv6 - "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))");
 * Domain - "(([a-z0-9]+\\.){1,}((com)|(edu)|(gov)|(mil)|(net)|(org)|(int)|(ua)))");
 */

public class Menu {

    public static boolean exitFromProgram = false;

    public static void menuOperation() {
        while (!exitFromProgram) {
            printMenuBanner();
            String lineConsole = readFromConsole().toLowerCase();
            if (lineConsole.equals("1")) {
                List<String> listDataFromFile = null;
                boolean fileExist = false;
                while (!fileExist) {
                    try {
                        System.out.print("path to origin file: ");
                        String pathToOriginFile = readFromConsole();
                        listDataFromFile = OperationWithFile.readFromFile(pathToOriginFile);
                        fileExist = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Wrong path to file!");
                    }
                }
                String regex = "((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)";
                Set<String> setDataForReplacement = DataProcessing.FindDataForReplacement(listDataFromFile, regex);
                Set<String> setRandomData = DataProcessing.getRandomIPv4(setDataForReplacement);
                TreeMap<String, String> mapReplacement = DataProcessing.getMapReplacement(setDataForReplacement, setRandomData);
                for (Map.Entry<String, String> entry : mapReplacement.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }

                System.out.print("path to mapReplacement file: ");
                String pathToMapReplacementFile = Menu.readFromConsole();
                List<String> listReplacement = new ArrayList<>();
                for (Map.Entry<String, String> entry : mapReplacement.entrySet()) {
                    listReplacement.add(entry.getKey() + " " + entry.getValue());
                }
                OperationWithFile.writeToFile(pathToMapReplacementFile, listReplacement);

                System.out.print("path to destination file: ");
                String pathToDestinationFile = Menu.readFromConsole();
                List<String> listModifiedData = DataProcessing.replacementListDataByMapReplacement(listDataFromFile, mapReplacement);
                OperationWithFile.writeToFile(pathToDestinationFile, listModifiedData);

            } else if (lineConsole.equals("2")) {
                List<String> listDataFromFile = null;
                boolean fileExist = false;
                while (!fileExist) {
                    try {
                        System.out.print("path to origin file: ");
                        String pathToOriginFile = readFromConsole();
                        listDataFromFile = OperationWithFile.readFromFile(pathToOriginFile);
                        fileExist = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Wrong path to file!");
                    }
                }

                List<String> listDataFromReplacementFile = null;
                fileExist = false;
                while (!fileExist) {
                    try {
                System.out.print("path to mapReplacement file: ");
                String pathToReplacementFile = readFromConsole();
                listDataFromReplacementFile = OperationWithFile.readFromFile(pathToReplacementFile);
                        fileExist = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Wrong path to file!");
                    }
                }

                Map<String, String> mapReplacement = new HashMap<>();
                for (String s : listDataFromReplacementFile) {
                    String[] str = s.split(" ");
                    mapReplacement.put(str[1], str[0]);
                }

                List<String> listModifiedData = DataProcessing.replacementListDataByMapReplacement(listDataFromFile, mapReplacement);

                System.out.print("path to destination file: ");
                String pathToDestinationFile = Menu.readFromConsole();
                OperationWithFile.writeToFile(pathToDestinationFile, listModifiedData);

            } else if (lineConsole.equals("exit")) {

                exitFromProgram = true;

            } else {
                System.out.println("Menu item was incorrectly selected!");
            }
        }
    }

    private static void printMenuBanner() {
        System.out.println("Transformation - 1 | Inverse transformation - 2 | Exit - exit");
        System.out.print("Select menu item: ");
    }

    public static String readFromConsole() {
        Scanner scanConsole = new Scanner(System.in);
        return scanConsole.nextLine();
    }
}
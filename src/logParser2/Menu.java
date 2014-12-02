package logParser2;

import java.util.*;

/**
 listIPv6 - "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))");
 listHost - "(([a-z0-9]+\\.){1,}((com)|(edu)|(gov)|(mil)|(net)|(org)|(int)|(ua)))");
 */

public class Menu {

    public static boolean exitFromProgram = false;

    public static void menuOperation() {
        while (!exitFromProgram) {
            printMenuBanner();
            String lineConsole = readFromConsole().toLowerCase();
            if (lineConsole.equals("1")) {

                System.out.print("path to origin file: ");
                String pathToOriginFile = readFromConsole();
                List<String> listDataFromFile = OperationWithFile.readFromFile(pathToOriginFile);

                String regex = "((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)";
                Set<String> setDataForModify = DataProcessing.FindDataForReplacement(listDataFromFile, regex);
                TreeMap<String,String> mapReplacement = DataProcessing.getMapReplacementIPv4(setDataForModify);
                for (Map.Entry<String, String> entry : mapReplacement.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }

                System.out.print("path to destination file: ");
                String pathToDestinationFile = Menu.readFromConsole();
                List<String> listModifiedData = DataProcessing.replacementListDataByMapReplacement(listDataFromFile, mapReplacement);
                OperationWithFile.writeToDestinationFile(pathToDestinationFile, listModifiedData);

                System.out.print("path to mapReplacement file: ");
                String pathToMapReplacementFile = Menu.readFromConsole();
                List<String> listReplacement = new ArrayList<>();
                for (Map.Entry<String, String> entry : mapReplacement.entrySet()) {
                        listReplacement.add(entry.getKey() + " " + entry.getValue());
                }
                OperationWithFile.writeToDestinationFile(pathToMapReplacementFile, listReplacement);

            } else if (lineConsole.equals("2")) {

                System.out.print("path to origin file: ");
                String pathToOriginFile = readFromConsole();
                List<String> listDataFromFile = OperationWithFile.readFromFile(pathToOriginFile);

                System.out.print("path to mapReplacement file: ");
                String pathToReplacementFile = readFromConsole();
                List<String> listDataFromReplacementFile = OperationWithFile.readFromFile(pathToReplacementFile);
                Map<String, String> mapReplacement = new HashMap<>();
                for (String s : listDataFromReplacementFile) {
                    String[] str = s.split(" ");
                    mapReplacement.put(str[1], str[0]);
                }

                List<String> listModifiedData = DataProcessing.replacementListDataByMapReplacement(listDataFromFile, mapReplacement);

                System.out.print("path to destination file: ");
                String pathToDestinationFile = Menu.readFromConsole();
                OperationWithFile.writeToDestinationFile(pathToDestinationFile, listModifiedData);

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
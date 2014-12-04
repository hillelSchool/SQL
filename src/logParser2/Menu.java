package logParser2;

import java.io.FileNotFoundException;
import java.util.*;

public class Menu {

    public static boolean exitFromProgram = false;

    public static void menuOperation() {
        while (!exitFromProgram) {
            printMenuBanner();
            String lineConsole = readFromConsole().toLowerCase();
            if (lineConsole.equals("1")) {
                //read data from origin file to listData
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
                //read regex from file to list
                List<String> listRegex = new ArrayList<>();
                fileExist = false;
                while (!fileExist) {
                    try {
//                        System.out.print("path to regex file: ");
//                        String pathToRegexFile = readFromConsole();
                        String pathToRegexFile = "./src/logParser2/regex";
                        listRegex = OperationWithFile.readFromFile(pathToRegexFile);
                        fileExist = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Wrong path to file!");
                    }
                }
                //print to console list regex from file
                int count = 0;
                for (String s : listRegex) {
                    System.out.println(++count + " " + s);
                }
                //select the type of regex
                System.out.print("Input number regex: ");
                String[] regex = listRegex.get(Integer.parseInt(Menu.readFromConsole()) - 1).split(" = ");
                //search data from listData matches the regex
                Set<String> setDataForReplacement = DataProcessing.FindDataForReplacement(listDataFromFile, regex[1]);
                //Generate a random list of conformity according to the regex
                GetReplacementMethod replacementMethod = ReplacementMethods.getReplacementMethod(regex[0]);
                Set<String> setRandomData = replacementMethod.getRandom(setDataForReplacement);
                //Generate a mapReplacement of conformity according to the regex
                TreeMap<String, String> mapReplacement = DataProcessing.getMapReplacement(setDataForReplacement, setRandomData);
                //print to console mapReplacement
                for (Map.Entry<String, String> entry : mapReplacement.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
                //write mapReplacement to file
                System.out.print("path to mapReplacement file: ");
                String pathToMapReplacementFile = Menu.readFromConsole();
                List<String> listReplacement = new ArrayList<>();
                for (Map.Entry<String, String> entry : mapReplacement.entrySet()) {
                    listReplacement.add(entry.getKey() + " " + entry.getValue());
                }
                OperationWithFile.writeToFile(pathToMapReplacementFile, listReplacement);
                //transformation and write list data to destination file
                System.out.print("path to destination file: ");
                String pathToDestinationFile = Menu.readFromConsole();
                List<String> listModifiedData = DataProcessing.replacementListDataByMapReplacement(listDataFromFile, mapReplacement);
                OperationWithFile.writeToFile(pathToDestinationFile, listModifiedData);

            } else if (lineConsole.equals("2")) {
                //read data from origin file to listData
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
                //read data from mapReplacement file to listReplacement
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
                //transformation listReplacement to mapReplacement
                Map<String, String> mapReplacement = new HashMap<>();
                for (String s : listDataFromReplacementFile) {
                    String[] str = s.split(" ");
                    mapReplacement.put(str[1], str[0]);
                }
                //replacement listData by mapReplacement ...
                List<String> listModifiedData = DataProcessing.replacementListDataByMapReplacement(listDataFromFile, mapReplacement);
                //...and write list data to destination file
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
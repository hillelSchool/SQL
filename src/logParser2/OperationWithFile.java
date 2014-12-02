package logParser2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperationWithFile {

    protected static List<String> readFromFile(String pathToFile) throws FileNotFoundException {
        List<String> listDataFromFile = new ArrayList<>();
        boolean stopReadingFile = false;

        BufferedReader readFromFile = new BufferedReader(new FileReader(pathToFile));
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
                    String consoleLine = Menu.readFromConsole().toLowerCase();
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
        System.out.println("Data was read");
        try {
            readFromFile.close();
        } catch (IOException e) {
            //NOD
        }
        return listDataFromFile;
    }

    protected static void writeToFile(String pathToFile, List<String> list) {
        BufferedWriter writeToFile;
        boolean stopWriteFile = false;
        while (!stopWriteFile) {
            try {
                writeToFile = new BufferedWriter(new FileWriter(pathToFile));
                for (int i = 0; i < list.size(); i++) {
                    if (i != list.size() - 1) {
                        writeToFile.write(list.get(i) + "\n");
                    } else {
                        writeToFile.write(list.get(i));
                    }
                }
                writeToFile.close();
                System.out.println("The file is recorded!");
                stopWriteFile = true;

            } catch (IOException e) {
                System.out.println("Error write data!");
                boolean flag = false;
                while (!flag) {
                    System.out.print("Try again? Y/n: ");
                    String consoleLine = Menu.readFromConsole().toLowerCase();
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
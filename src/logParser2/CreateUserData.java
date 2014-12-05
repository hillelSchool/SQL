package logParser2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CreateUserData {

    public UserData processingParameter(String[] args) {

        UserData userData = new UserData();

        if (args[0].equals("-e") && (args.length >= 5)) {
            System.out.println(args[0]);
            switch (args[1]) {
                case "1":
                    System.out.println("Regex");
                    userData.setParameters(args);
                    userData.setPathToOriginFile(checkReadFromFile(args[2]));
                    userData.setPathToRulesFile(checkReadFromFile(args[3]));
                    userData.setPathToMapReplacementFile(checkWriteToFile(args[4]));
                    userData.setPathToDestinationFile(checkWriteToFile(args[5]));
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

        } else if (args[0].equals("-d")) {
            System.out.println(args[0]);
            userData.setParameters(args);
            userData.setPathToOriginFile(checkReadFromFile(args[1]));
            userData.setPathToMapReplacementFile(checkReadFromFile(args[2]));
            userData.setPathToDestinationFile(checkWriteToFile(args[3]));
        } else {
            System.out.println("Wrong program arguments!");
            System.exit(0);
        }

        return userData;
    }

    private static String checkReadFromFile(String pathToFile) {
        boolean fileCanRead = false;
        while (!fileCanRead) {
            if ((new File(pathToFile)).canRead()) {
                fileCanRead = true;
            } else {
                System.out.println("Incorrect path " + pathToFile + " !");
                System.out.println("Input correct path: ");
                pathToFile = readFromConsole();
            }
        }
        return pathToFile;
    }

    private static String checkWriteToFile(String pathToFile) {
        boolean fileCanWrite = false;
        while (!fileCanWrite) {
            try {
                if ((new File(pathToFile)).createNewFile()) {
                    fileCanWrite = true;
                } else if ((new File(pathToFile)).canWrite()) {
                    fileCanWrite = true;
                } else {
                    System.out.println("Incorrect path " + pathToFile + " !");
                    System.out.println("Input correct path: ");
                    pathToFile = readFromConsole();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pathToFile;
    }

    public static String readFromConsole() {
        Scanner scanConsole = new Scanner(System.in);
        return scanConsole.nextLine();
    }
}
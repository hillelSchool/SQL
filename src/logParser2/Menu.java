package logParser2;

import java.util.Scanner;

public class Menu {

    public static boolean exitFromProgram = false;

    public static void menuOperation(){
        while (!exitFromProgram) {
            printMenuBanner();
            String lineConsole = readFromConsole().toLowerCase();
            if (lineConsole.equals("1")) {
                DataProcessing.encrypt();
            } else if (lineConsole.equals("2")){
                DataProcessing.decrypt();
            }else if(lineConsole.equals("exit")){
                exitFromProgram = true;
            }else{
                System.out.println("Incorrect command!");
            }
        }
    }

    private static void printMenuBanner(){
        System.out.println("Encrypt - 1 | Decrypt - 2 | Exit - exit");
        System.out.print("Select menu item: ");
    }

    public static String readFromConsole() {
        Scanner scanConsole = new Scanner(System.in);
        return scanConsole.nextLine();
    }
}
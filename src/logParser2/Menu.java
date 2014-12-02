package logParser2;

import java.util.Scanner;

public class Menu {

   // public Menu menu = new Menu();

    public void menuOperation(){
        boolean exitFromProgram = false;
        while (!exitFromProgram) {
            printMenuBanner();
            String lineConsole = readFromConsole().toLowerCase();
            if ("1".equals(lineConsole)) {
               new  DataProcessing().encrypt();
            } else if ("2".equals(lineConsole)){
                new DataProcessing().decrypt();
            }else if("exit".equals(lineConsole)){
                exitFromProgram = true;
                System.out.println("Program ended correct.");
            } else {
                System.out.println("Incorrect command.");
                System.out.println("Please try again.");
            }
        }
    }

    private void printMenuBanner(){
        System.out.println("Encrypt - 1 | Decrypt - 2 | Exit - exit");
        System.out.print("Select menu item: ");
    }

    public String readFromConsole() {
        Scanner scanConsole = new Scanner(System.in);
        return scanConsole.nextLine();
    }
}
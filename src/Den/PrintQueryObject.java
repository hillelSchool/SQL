package Den;

public class PrintQueryObject {
    private static String query = ConsoleOperation.query;

    public static void printQueryObject() {

        printQueryUpdateText();

        for (int i = 0; i < QueryObject.columnName.size(); i++) {
            System.out.print(" " + QueryObject.columnName.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < QueryObject.columnTable.size(); i++) {
            for (int j = 0; j < QueryObject.columnTable.get(i).size(); j++) {
                System.out.print(QueryObject.columnTable.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private static void printQueryUpdateText() {
        if (!query.equals("")) {
            String[] arrayQuery = query.split(" ");
            for (int i = 0; i < arrayQuery.length; i++) {
                switch (arrayQuery[i]) {
                    case "select":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        System.out.println();
                        break;
                    case "from":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        System.out.println();
                        break;
                    case "where":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        System.out.println();
                        break;
                    case "order":
                        arrayQuery[i + 1] = arrayQuery[i + 1].toUpperCase();
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        System.out.println();
                        break;
                    case "or":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "and":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "not":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "outer":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "left":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "join":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "on":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "insert":
                        System.out.println();
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "into":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "values":
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                    case "delete":
                        System.out.println();
                        arrayQuery[i] = arrayQuery[i].toUpperCase();
                        break;
                }
                System.out.print(arrayQuery[i] + " ");
            }
        }
    }
}

package alex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StartEXEC {

    Connection c = null;
    public static void main(String[] args) throws IOException, SQLException {

//********************************************************************************************************************//
//**                              Блок программы выполняющий произвольный SQL запрос                                **//
//********************************************************************************************************************//

        Connection c = null;
        Connection d = null;
        JDBCquery manualQuery = new JDBCquery();
        JDBCmenu menu = new JDBCmenu();
        JDBCjoin join = new JDBCjoin();
        
        JDBCconnection conn = new JDBCconnection();
        c = conn.connect();

        Statement st = c.createStatement();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("+----------------------------+");
            System.out.println("| SQL query explorer ver 0.8 |");
            System.out.println("+----------------------------+");
            System.out.println("| SQL query -------------> 1 |");
            System.out.println("| SQL template ----------> 2 |");
            System.out.println("| SQL JOIN example ------> 3 |");
            System.out.println("| Exit program ----------> 4 |");
            System.out.println("+----------------------------+");

            int number = Integer.parseInt(br.readLine());


            switch (number) {
                case 1:

                    System.out.println("Enter SQL query:");
                    String queryBR = br.readLine();
                    manualQuery.QuerySQL(queryBR, st);
                    Close.exit();
                    break;
                case 2:

                    menu.QuerySQL("select * from sql_query", st);
                    int num = Integer.parseInt(br.readLine());
                    new JDBCmenu().QuerySQL(menu.list.get(num - 1), st);
                    Close.exit();
                    break;

                case 3:

                    join.testJoin("select * from tableA inner join tableB on tableA.name = tableB.name;", st);
                    join.testJoin("select * from tableA left join tableB on tableA.name = tableB.name;", st);
                    join.testJoin("select * from tableA right join tableB on tableA.name = tableB.name;", st);
                    Close.exit();
                    break;

                case 4:
                    System.out.println("Good buy! Have a nice day!");
                    System.exit(0);

                default:
                    System.out.println("Please, enter correct number!");
                    break;
            }
        }
    }
}
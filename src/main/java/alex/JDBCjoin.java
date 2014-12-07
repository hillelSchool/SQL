package alex;

import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCjoin {

    public void formatTable(){
        System.out.println("+---------------------+---------------------+");
        System.out.format("%23s", "|      Table A        |");
        System.out.format("%22s%n", " Table B       |");
        System.out.println("+-------+-------------+-------+-------------+");
        System.out.format("%8s", "|  id   |");
        System.out.format("%14s", "name   |");
        System.out.format("%8s", "id   |");
        System.out.format("%14s", "name   |");
        System.out.println();
        System.out.println("+-------+-------------+-------+-------------+");
    }


    public void testJoin(String query, Statement st ){

        try {
            ResultSet innerJoin = st.executeQuery(query);//Выполняем запрос к БД, результат в переменной rsPhone
            System.out.printf("Result SQL query: %n" + query.toString().toUpperCase());
            System.out.println();
            System.out.println();
            formatTable();

            while (innerJoin.next()){
                String idA = innerJoin.getString("tableA.id");
                String nameA = innerJoin.getString("tableA.name");
                String idB = innerJoin.getString("tableB.id");
                String nameB = innerJoin.getString("tableB.name");
                System.out.print("|");
                System.out.format("%8s", idA  + " |");
                System.out.format("%14s", nameA  + " |");
                System.out.format("%8s",  idB  + " |");
                System.out.format("%14s%n", nameB + " |");
            }
            System.out.println("+-------+-------------+-------+-------------+");
        } catch(Exception e){
            e.printStackTrace();
        }
    }



}


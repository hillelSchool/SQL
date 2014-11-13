/*
- добавьте функцию которая выводит номера телефонов так как это сделала Маргарита
(сделано, время настраивается)- Добавьте функцию которая выводит сначала кол-во записей с датой после 19:00, потом эти записи, а потом все остальные записи
- добавьте функцию, которая записывает в базу новые записи - разделитесь, возьмите по таблице. с помощью PreparedStatement
.... коллеги, придумайте каждый какую-то функцию, сделайте ее, и потом пушните в гит, объедините с текущей версией, добавьте вызов в мейн и проверьте что все ОК и вы не затерли ничьих изменений
 после того как вы это сделали, внесите правки в чужую функцию, и отпишите в комменте что вы исправили.
 */

public class Main {

    public static void main(String[] args) {

        String sqlInject = "";
        // String sqlInject = "q%' Delete from phonebook where phonebook.last_name like '%Stepurko%'; -- ";

        int hour = 16;
        boolean operator = false; // true: after than before, false: before than after

        new JDBCexample().testInject(sqlInject); //можно закомментить одно из них или оба
        new JDBCexample().callsByTime(sqlInject, hour, operator); // и здесь
    }
}
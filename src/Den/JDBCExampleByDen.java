package Den;

/*
 * Внимание, код заговорен! Изменение данного кода(без согласия автора)
 * приведёт к половому бессилию и неизлечимым заболеваниям!!!
 */

public class JDBCExampleByDen {
    public static void main(String[] args) {

//          Сделать циклический запрос меню. Если вывод запроса еще не завершен,
// то происходит наложение: вывод попадает на запрос меню -> выход с ошибкой.
        ConsoleOperation.selectMenuItem();      // Ввод запроса с консоли
        PrintQueryObject.printQueryText();      // Вывод текста запроса
        PrintQueryObject.printQueryObject();    // Вывод результата в консоль
    }
}

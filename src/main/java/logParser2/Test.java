package logParser2;

public class Test {
    public static void main(String[] args) {
        String str = "abc1234+5678abc";
        System.out.println(str);

        String replace = "4+5";
        System.out.println(replace);
        System.out.println(str.replaceAll(replace, "!"));

        replace = "4+5";
        replace = replace.replaceAll("\\+", "\\\\\\+");
        System.out.println(replace);
        System.out.println(str.replaceAll(replace, "!"));

        replace = "4\\+5";
        System.out.println(replace);
        System.out.println(str.replaceAll(replace, "!"));
    }
}

package logParser2;

public class ReplacementMethods {

    public static GetReplacementMethod getReplacementMethod(String method) throws IllegalArgumentException {
/**
 * if string isn't correct?
 * program fall down :(
 */
        GetReplacementMethod getReplacementMethod = null;
        if (method.equals(EnumReplacementMethods.IPv4)) {
            getReplacementMethod = new GetRandomIPv4();
        } else if (method.equals(EnumReplacementMethods.IPv6)) {
            getReplacementMethod = new GetRandomIPv6();
        } else if (method.equals(EnumReplacementMethods.Domain)) {
            getReplacementMethod = new GetRandomDomain();
        } else if (method.equals(EnumReplacementMethods.Email)) {
            getReplacementMethod = new GetRandomEmail();
        } else {
            System.out.println("Unknown method type");
        }
        return getReplacementMethod;
    }
}

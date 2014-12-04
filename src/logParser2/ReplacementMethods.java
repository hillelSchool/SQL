package logParser2;

public class ReplacementMethods {

    public static GetReplacementMethod getReplacementMethod(String method) throws IllegalArgumentException {

        GetReplacementMethod getReplacementMethod;
        if (method.equals(EnumReplacementMethods.IPv4)) {
            getReplacementMethod = new GetRandomIPv4();
        } else if (method.equals(EnumReplacementMethods.IPv6)) {
            getReplacementMethod = new GetRandomIPv6();
        } else if (method.equals(EnumReplacementMethods.Domain)) {
            getReplacementMethod = new GetRandomDomain();
        } else if (method.equals(EnumReplacementMethods.Email)) {
            getReplacementMethod = new GetRandomEmail();
        } else {
            throw new IllegalArgumentException("Unknown method type");
        }
        return getReplacementMethod;
    }
}

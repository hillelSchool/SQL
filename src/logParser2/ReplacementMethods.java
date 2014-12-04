package logParser2;

public class ReplacementMethods {

    public static GetReplacementMethod getReplacementMethod(String method) {

        GetReplacementMethod getReplacementMethod = null;
        if (method.equals("IPv4")) {
            getReplacementMethod = new GetRandomIPv4();
        } else if (method.equals("IPv6")) {
            getReplacementMethod = new GetRandomIPv6();
        } else if (method.equals("Domain")) {
            getReplacementMethod = new GetRandomDomain();
        } else if (method.equals("Email")){
            getReplacementMethod = new GetRandomEmail();
        }
        return getReplacementMethod;
    }
}

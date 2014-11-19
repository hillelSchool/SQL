package Den;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class ConnectionConfig {

    public static String user;
    public static String password;
    public static String url;
    public static String driver;

    private static String pathToConnectConfigFile = "src/Den/config.xml";

    static {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        DefaultHandler handler = new DefaultHandler() {

            boolean aUrl = false;
            boolean aDriver = false;
            boolean aUsername = false;
            boolean aPassword = false;

            public void startElement(String uri, String localName, String qName,
                                     Attributes attributes) throws SAXException {

                if (qName.equalsIgnoreCase("url")) aUrl = true;
                if (qName.equalsIgnoreCase("driver")) aDriver = true;
                if (qName.equalsIgnoreCase("username")) aUsername = true;
                if (qName.equalsIgnoreCase("password")) aPassword = true;
            }

            public void characters(char ch[], int start, int length) throws SAXException {

                if (aDriver) {
                    driver = new String(ch, start, length);
                    aDriver = false;
                }
                if (aUrl) {
                    url = new String(ch, start, length);
                    aUrl = false;
                }
                if (aUsername) {
                    user = new String(ch, start, length);
                    aUsername = false;
                }
                if (aPassword) {
                    password = new String(ch, start, length);
                    aPassword = false;
                }
            }

        };
        try {
            if (saxParser != null) {
                saxParser.parse(pathToConnectConfigFile, handler);
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}

package alex;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCconnection {

    static String urlS;
    static String driverS;
    static String usernameS;
    static String passwordS;
    static Connection conn = null;

    public Connection connect() {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {


                boolean url = false;
                boolean driver = false;
                boolean username = false;
                boolean password = false;

                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("url")) url = true;

                    if (qName.equalsIgnoreCase("driver")) driver = true;

                    if (qName.equalsIgnoreCase("username")) username = true;

                    if (qName.equalsIgnoreCase("password")) password = true;

                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (driver) {
                        driverS = new String(ch, start, length);
                        System.out.println("Driver -----> " + driverS);
                        driver = false;
                    }

                    if (url) {
                        urlS = new String(ch, start, length);
                        System.out.println("URL --------> " + urlS);
                        url = false;
                    }

                    if (username) {
                        usernameS = new String(ch, start, length);
                        System.out.println("Username ---> " + usernameS);
                        username = false;
                    }

                    if (password) {
                        passwordS = new String(ch, start, length);
                        System.out.println("Password ---> " + passwordS);
                        password = false;
                    }

                }

            };
            System.out.println("------------------- MySQL JDBC PARAMETRS -------------------");
            saxParser.parse("src\\alex\\config.xml", handler);

            System.out.println("------------------- MySQL JDBC Connection ------------------");

            try {
                Class.forName(driverS);
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your MySQL JDBC Driver?");
                e.printStackTrace();
                return null;
            }

            System.out.println("MySQL JDBC Driver Registered!");


            try {
                conn = DriverManager.getConnection(urlS, usernameS, passwordS);

            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
                return null;
            }

            if (conn != null) {
                System.out.println("---------------- MySQL JDBC Connection OK! -----------------");
            } else {
                System.out.println("-------------- MySQL JDBC Connection FAILD ------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void disconect(Connection conn) throws SQLException {
        conn.close();
        System.out.println("--------------- MySQL JDBC Connection CLOSED ---------------");


    }

}







package Den;

import java.sql.Statement;

interface Query {

    String getQuery();
    void query(Statement st);
}

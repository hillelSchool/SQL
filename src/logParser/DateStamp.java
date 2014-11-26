package logParser;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Stepurko-AA on 25.11.2014.
 */
public class DateStamp {

    public String dateStamp(){

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String time = sdf.format(date);

        return time;
    }
}

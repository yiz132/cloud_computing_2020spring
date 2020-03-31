import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

    private static String parseDateString(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss");
        Date date = format.parse(s);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format2.format(date);
    }

    //2009-07-15 15:50:35
    private static String getMonth(String s){
        int first = s.indexOf("-")+1;
        int sec = s.indexOf("-",first);
        return s.substring(0,sec);
    }

    public static void main(String[] args) throws ParseException {
        String input = "02/Sep/2011:15:09:37";
        System.out.println(parseDateString(input));
        System.out.println(getMonth(parseDateString(input)));
    }
}

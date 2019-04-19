import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTest {



    public static void testDate(){

        LocalTime lt = LocalTime.parse("01:20", DateTimeFormatter.ISO_TIME);
        LocalTime n = LocalTime.parse("01:20", DateTimeFormatter.ISO_TIME);
        System.out.println(lt.equals(n));

        System.out.println(lt.isBefore(n));

    }


    public static void main(String[] args) {
        testDate();
    }


}

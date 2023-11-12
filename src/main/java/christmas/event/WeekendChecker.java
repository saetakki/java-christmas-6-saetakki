package christmas.event;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class WeekendChecker {
    public static boolean isWeekend(int date){
        int dayOfWeek = date % 7;
        return dayOfWeek == 1 || dayOfWeek == 2;
    }

}

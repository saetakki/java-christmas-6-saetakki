package christmas.event;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class WeekendChecker {
    public static boolean isWeekend(int date){
        if( date > 31 || date < 1)
            throw new IllegalArgumentException("맞지않는 범위");
        int dayOfWeek = date % 7;
        return dayOfWeek == 1 || dayOfWeek == 2;
    }

}

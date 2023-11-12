package event;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class WeekendChecker {
    public static boolean isWeekend(LocalDate date){
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY;
    }

}

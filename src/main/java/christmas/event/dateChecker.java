package christmas.event;

public class dateChecker {
    public static boolean isWeekend(int date){
        int dayOfWeek = date % 7;
        return dayOfWeek == 1 || dayOfWeek == 2;
    }


    public static boolean isSundayOrChristmas(int date){
        int HappySunday = date % 7;
        int MerryChristmas = 25;
        return HappySunday == 3 || date == MerryChristmas;
    }

    public static String todayIs(int date) {
        String today = "평일";
        if (isWeekend(date)) {
            today = "주말";
        }
        return today;
    }

}

package christmas.event;

public class dateChecker {
    // 입력받은 요일이 주말(금,토)에 해당하는지 확인하는 메서드
    // 문제 상황에서 금요일은 1일이기 때문에 7로 모듈로 연산을 했을 경우 나머지가 1일 경우가 금요일
    public static boolean isWeekend(int date){
        int dayOfWeek = date % 7;
        return dayOfWeek == 1 || dayOfWeek == 2;
    }


    // 입력받은 요일이 주말 혹은 크리스마스인지 확인하는 메서드
    // 문제 상황에서 '특별일'에 해당하는 일은 매주 일요일과 크리스마스 당일 뿐
    public static boolean isSundayOrChristmas(int date){
        int HappySunday = date % 7;
        int MerryChristmas = 25;
        return HappySunday == 3 || date == MerryChristmas;
    }

    // 입력받은 요일이 주말인지 평일인지 계산하기 위한 메서드
    // 주말의 경우 메인요리, 평일의 경우 디저트를 할인 받기 때문에 해당 로직을 위해 추가
    public static String todayIs(int date) {
        String today = "평일";
        if (isWeekend(date)) {
            today = "주말";
        }
        return today;
    }

}

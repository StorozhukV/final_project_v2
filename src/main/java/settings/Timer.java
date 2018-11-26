package settings;

public class Timer {
    public static long currentTimeMills;

    public static void startTimer() {
        currentTimeMills = System.currentTimeMillis();
    }

    public static String finishTimer() {
        currentTimeMills = System.currentTimeMillis() - currentTimeMills;
        long hour = ( currentTimeMills / ( 1000 * 60 * 60 ) ) % 24;
        long minute = ( currentTimeMills / ( 1000 * 60 ) ) % 60;
        long second = ( currentTimeMills / 1000 ) % 60;

        System.out.println("Витрачено часу " + currentTimeMills + " мілісекунд"); // Видалити
        return String.format("Витрачено часу " + "%02d:%02d:%02d", hour, minute, second);
    }
}

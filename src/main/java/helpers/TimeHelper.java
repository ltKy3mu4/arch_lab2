package helpers;

public class TimeHelper {

    private static long initialTime = System.currentTimeMillis();
    public static int hourDurationMs = 10_000;

    public static int getCurrentHour(){
        return (int)(System.currentTimeMillis() - initialTime) / hourDurationMs %24;
    }

    public static int getMsBeforeNextHour(){
        return (int)(System.currentTimeMillis() - initialTime) % hourDurationMs;
    }

}

package Utils;

public class WallUtils {
    public static double cosec(double v) {
        return 1 / Math.sin(v);
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

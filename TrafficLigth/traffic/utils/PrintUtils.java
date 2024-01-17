package traffic.utils;

public class PrintUtils {

    public static void inRead(String s){
        System.out.println("\u001B[31m" + s + "\u001B[0m");
    }

    public static void inGreen(String s){
        System.out.println("\u001B[32m" + s + "\u001B[0m");
    }

}

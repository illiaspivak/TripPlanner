package sk.kosickaakademia.spivak.tripplanner.log;

public class Log {
    public static void error(String str){
        System.out.println("[ERROR] - "+str);
    }

    public static void okay(String str){
        System.out.println("[OK] - "+str);
    }

    public static void info(String str){
        System.out.println("[INFO] - "+str);
    }
}

package start.structure;

import java.util.TimerTask;

public class TimerT {

    static int max = 5;
    static int min = 0;
    static int range = max - min + 1;
    static int rand = 0;

    public static int getRand() {
        return rand;
    }

    public static int tempsRestant() {
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    rand = (int) (Math.random() * range) + min;
                }
                System.out.println(rand);
            }
        }, 5000, 5000);
        return rand;
    }
}
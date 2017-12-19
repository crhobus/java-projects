package ThreadSynchronized;

import java.util.Date;

public class GreetingThread extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10; i++) {
                System.out.println(new Date() + " " + "greeting");
                sleep(1000);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

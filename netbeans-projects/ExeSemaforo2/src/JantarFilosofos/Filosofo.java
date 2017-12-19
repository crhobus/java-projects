package JantarFilosofos;

import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {

    private Semaphore semaforo;

    public Filosofo(Semaphore semaforo) {
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            semaforo.acquire();
            System.out.println(getName() + " ocupou um garfo 1");
            if (semaforo.tryAcquire()) {
                System.out.println(getName() + " ocupou um garfo 2");
                sleep(10);
                semaforo.release();
                System.out.println(getName() + " liberou garfo 1");
                semaforo.release();
                System.out.println(getName() + " liberou garfo 2");
            } else {
                semaforo.release();
                System.out.println(getName() + " liberou garfo 1");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

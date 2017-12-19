package JantarFilosofos;

import java.util.concurrent.Semaphore;

public class Sistema {

    public static void main(String[] args) {
        Semaphore garfo = new Semaphore(5, true);
        Filosofo filosofo1 = new Filosofo(garfo);
        Filosofo filosofo2 = new Filosofo(garfo);
        Filosofo filosofo3 = new Filosofo(garfo);
        Filosofo filosofo4 = new Filosofo(garfo);
        Filosofo filosofo5 = new Filosofo(garfo);
        filosofo1.start();
        filosofo2.start();
        filosofo3.start();
        filosofo4.start();
        filosofo5.start();
    }
}

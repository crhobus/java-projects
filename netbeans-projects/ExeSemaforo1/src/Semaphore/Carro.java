package Semaphore;

import java.util.concurrent.Semaphore;

public class Carro extends Thread {

    private static Semaphore estacionamento = new Semaphore(10, true);//mais de uma thread pode entrar na seção critica, neste caso no run, nº de thread passado por parametro, no true 

    @Override
    public void run() {
        try {
            estacionamento.acquire();//Aloca o tanto que foi definido no construtor do Semaphore, neste caso aloca 10 por vez, se um liberar outra thread aloca
            System.out.println(getName() + " ocupou vaga");
            sleep((long) (Math.random() * 10000));
            System.out.println(getName() + " liberou vaga");
            estacionamento.release();//libera a thread conforme o tempo estimado no sleep
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

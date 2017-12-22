package Sincronizado;

public class Sincronizado {

    public static void main(String[] args) throws InterruptedException {
        Valor v = new Valor();
        MThread t1 = new MThread(v);
        MThread t2 = new MThread(v);
        MThread t3 = new MThread(v);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(v.get());
    }
}

class Valor {

    int val = 0;

    synchronized void add(int qdt) {
        for (int i = 0; i < qdt; i++) {
            int temp = val;
            System.out.print("," + temp);
            val = temp + 1;
        }
    }

    int get() {
        return val;
    }
}

class MThread extends Thread {

    Valor vl;

    MThread(Valor v) {
        vl = v;
    }

    public void run() {
        vl.add(100);
    }
}

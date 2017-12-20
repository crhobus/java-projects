package FabricaChocolate;

public class Singleton {

    private volatile static Singleton unicaInstancia;

    private Singleton() {
        //Construtor vazio para usar o padrao singleton;
    }

    public static Singleton getInstancie() {
        if (unicaInstancia == null) {
            synchronized (Singleton.class) {
                if (unicaInstancia == null) {
                    unicaInstancia = new Singleton();
                }
            }
        }
        return unicaInstancia;
    }
}

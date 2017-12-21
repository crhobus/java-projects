package L1311H06;

/**
 *
 * @author Caio
 */
public class Processo extends Thread {

    private Armazem armazem;
    private int identificador;
    private boolean flagTerminar;

    public Processo(int identificador, Armazem armazem) {
        this.identificador = identificador;
        this.armazem = armazem;
        this.flagTerminar = false;
        start();
    }

    public void terminar() {
        flagTerminar = true;
    }

    public Armazem getArmazem() {
        return armazem;
    }

    public int getIdentificador() {
        return identificador;
    }

    public boolean isFlagTerminar() {
        return flagTerminar;
    }
}

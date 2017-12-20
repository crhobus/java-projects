package FabricaChocolate;

public class CaldeiraChocolate {

    private boolean vazio, fervido;
    private static CaldeiraChocolate unicoChocolate;

    private CaldeiraChocolate() {
        vazio = true;
        fervido = false;
    }

    public static CaldeiraChocolate getInstance() {
        if (unicoChocolate == null) {
            unicoChocolate = new CaldeiraChocolate();
        }
        return unicoChocolate;
    }

    public void preencher() {
        if (estaVazio()) {
            vazio = false;
            fervido = false;
        }
    }

    public void dreno() {
        if (!estaVazio() && !estaFervido()) {
            vazio = true;
        }
    }

    public void ferver() {
        if (!estaVazio() && !estaFervido()) {
            fervido = true;
        }
    }

    public boolean estaVazio() {
        return vazio;
    }

    public boolean estaFervido() {
        return fervido;
    }
}

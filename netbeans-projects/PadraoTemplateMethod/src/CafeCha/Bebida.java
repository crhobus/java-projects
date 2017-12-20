package CafeCha;

public abstract class Bebida {

    public final void prepareReceita() {
        ferverAgua();
        mistura();
        servirCafeXicara();
        addCondimentos();
    }

    public abstract void mistura();

    public abstract void addCondimentos();

    public void ferverAgua() {
        System.out.println("Ferver um pouco de água");
    }

    public void servirCafeXicara() {
        System.out.println("Servir na xícara");
    }
}

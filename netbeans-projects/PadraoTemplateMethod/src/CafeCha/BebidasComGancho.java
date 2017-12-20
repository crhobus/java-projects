package CafeCha;

public abstract class BebidasComGancho {

    public void prepareReceita() {
        ferverAgua();
        mistura();
        servirCafeXicara();
        if (clienteQuerCondimentos()) {
            addCondimentos();
        }
    }

    public abstract void mistura();

    public abstract void addCondimentos();

    public void ferverAgua() {
        System.out.println("Ferver um pouco de água");
    }

    public void servirCafeXicara() {
        System.out.println("Servir na xícara");
    }

    public boolean clienteQuerCondimentos() {
        return true;
    }
}

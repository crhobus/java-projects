package CafeCha;

public class Cafe extends Bebida {

    @Override
    public void mistura() {
        System.out.println("Misturar café na água fervente");
    }

    @Override
    public void addCondimentos() {
        System.out.println("Acrescentar açucar ao leite");
    }
}

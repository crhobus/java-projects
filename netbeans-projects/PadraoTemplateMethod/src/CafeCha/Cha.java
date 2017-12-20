package CafeCha;

public class Cha extends Bebida {

    @Override
    public void mistura() {
        System.out.println("Colocar o chá em influsão na água fervente");
    }

    @Override
    public void addCondimentos() {
        System.out.println("Adicionar limão");
    }
}

package SimuladorPato;

public class PatoReal extends Pato {

    public PatoReal() {
        quackComportamento = new Quack();
        voarComportamento = new VoarComAsas();
    }

    public void mostrar() {
        System.out.println("Eu sou um pato real");
    }
}

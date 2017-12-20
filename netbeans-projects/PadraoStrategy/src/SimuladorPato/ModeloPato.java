package SimuladorPato;

public class ModeloPato extends Pato {

    public ModeloPato() {
        voarComportamento = new NaoPodeVoar();
        quackComportamento = new Quack();
    }

    @Override
    public void mostrar() {
        System.out.println("Eu sou um pato modelo");
    }
}

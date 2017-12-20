package SimuladorPato;

public abstract class Pato {

    QuackComportamento quackComportamento;
    VoarComportamento voarComportamento;

    public abstract void mostrar();

    public void realizarVoar() {
        voarComportamento.voar();
    }

    public void realizarQuack() {
        quackComportamento.quack();
    }

    public void flutuar() {
        System.out.println("Todos os patos flutuan, mesmo chamarizes");
    }

    public void setQuackComportamento(QuackComportamento quackComportamento) {
        this.quackComportamento = quackComportamento;
    }

    public void setVoarComportamento(VoarComportamento voarComportamento) {
        this.voarComportamento = voarComportamento;
    }
}

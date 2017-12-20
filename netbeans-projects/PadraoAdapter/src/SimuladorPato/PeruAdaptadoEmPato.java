package SimuladorPato;

public class PeruAdaptadoEmPato implements Pato {

    private Peru peru;

    public PeruAdaptadoEmPato(Peru peru) {
        this.peru = peru;
    }

    public void quack() {
        peru.gorgolejar();
    }

    public void voar() {
        for (int i = 0; i < 5; i++) {
            peru.voar();
        }
    }
}

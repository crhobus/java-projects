package SimuladorPato;

public class Sistema {

    public static void main(String[] args) {
        /*Pato patoReal = new PatoReal();
        patoReal.realizarQuack();
        patoReal.realizarVoar();*/
        Pato modelo = new ModeloPato();
        modelo.realizarVoar();
        modelo.setVoarComportamento(new VoarFogueteMovido());
        modelo.realizarVoar();
    }
}

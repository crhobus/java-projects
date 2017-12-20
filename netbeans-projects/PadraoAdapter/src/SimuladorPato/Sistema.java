package SimuladorPato;

public class Sistema {

    public static void main(String[] args) {
        PatoReal pato = new PatoReal();
        PeruReal peru = new PeruReal();
        Pato peruAdaptado = new PeruAdaptadoEmPato(peru);

        System.out.println("O peru diz...");
        peru.gorgolejar();
        peru.voar();

        System.out.println("\nO pato diz...");
        patoTeste(pato);

        System.out.println("\nO peru adaptado ao pato diz...");
        patoTeste(peruAdaptado);
    }

    private static void patoTeste(Pato pato) {
        pato.quack();
        pato.voar();
    }
}

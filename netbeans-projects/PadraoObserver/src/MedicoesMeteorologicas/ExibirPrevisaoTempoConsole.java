package MedicoesMeteorologicas;

public class ExibirPrevisaoTempoConsole implements PrevisaoTempoListener {

    private PrevisaoTempo tempo;

    public ExibirPrevisaoTempoConsole(PrevisaoTempo tempo) {
        this.tempo = tempo;
    }

    @Override
    public void tempoMudou() {
        System.out.printf("Tempo mudou: Temperatura: %d  Pressão: %d  Umidade: %d\n\n", tempo.getTemperatura(), tempo.getPressao(), tempo.getUmidade());
    }
}

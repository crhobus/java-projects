package MedicoesMeteorologicas;

public class Sistema {

    public static void main(String[] args) {
        PrevisaoTempo previsaoTempo = new PrevisaoTempo();
        ExibirPrevisaoTempoConsole exibirConsole = new ExibirPrevisaoTempoConsole(previsaoTempo);
        previsaoTempo.addListener(exibirConsole);
        ExibirPrevisaoTempoAlerta alerta = new ExibirPrevisaoTempoAlerta(previsaoTempo);
        previsaoTempo.addListener(alerta);
        previsaoTempo.setTempo(20, 10, 25);
        previsaoTempo.removeListener(alerta);
        previsaoTempo.setTempo(1, 1, 1);
    }
}

package MedicoesMeteorologicas;

import java.awt.Frame;
import java.text.MessageFormat;
import javax.swing.JOptionPane;

public class ExibirPrevisaoTempoAlerta implements PrevisaoTempoListener {

    private PrevisaoTempo tempo;

    public ExibirPrevisaoTempoAlerta(PrevisaoTempo tempo) {
        this.tempo = tempo;
    }

    @Override
    public void tempoMudou() {
        String texto = MessageFormat.format("Tempo mudou: temperatura: {0}, umidade: {1}, pressão: {2}", tempo.getTemperatura(), tempo.getUmidade(), tempo.getPressao());
        JOptionPane.showMessageDialog(new Frame(), texto);
    }
}

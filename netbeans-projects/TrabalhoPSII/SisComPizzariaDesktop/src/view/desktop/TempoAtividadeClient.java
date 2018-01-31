package view.desktop;

import control.funcoes.Funcoes;
import java.util.Date;

public class TempoAtividadeClient extends Thread {

    private SisComPizzaria sisCom;

    public TempoAtividadeClient(SisComPizzaria sisCom) {
        this.sisCom = sisCom;
    }

    @Override
    public void run() {
        String data;
        String hora;
        while (true) {
            try {
                data = Funcoes.dateToString(new Date(), 2);
                hora = data.substring(11, 16);
                data = data.substring(0, 10);
                if ("00:00".equals(hora)) {
                    sisCom.alterarData(data);
                }
                sleep(8000);
            } catch (InterruptedException ex) {}
        }
    }
}

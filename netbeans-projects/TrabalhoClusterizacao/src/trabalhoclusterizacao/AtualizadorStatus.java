package trabalhoclusterizacao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AtualizadorStatus extends Thread {

    private Legenda legenda;
    private SimpleDateFormat formater = new SimpleDateFormat("mm:ss");

    public AtualizadorStatus(Legenda legenda) {
        this.legenda = legenda;
    }

    @Override
    public void run() {
        String lStt = legenda.getStatus();
        Date t = new Date();
        do {
            try {
                sleep(1000);
                legenda.setTempo("Tempo : " + formater.format(new Date().getTime() - t.getTime()));
                if (!lStt.equalsIgnoreCase(legenda.getStatus())) {
                    lStt = legenda.getStatus();
                    legenda.setStatus(lStt);
                }
            } catch (Exception ex) {
            }
        } while (!"Concluído".equals(legenda.getStatus()));
    }
}

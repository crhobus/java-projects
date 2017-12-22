package view;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class ThreadHoraSistema extends Thread {

    private Principal principal;
    private SimpleDateFormat formatDateHora;

    public ThreadHoraSistema(Principal principal, JLabel lbHorario) {
        this.principal = principal;
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
                principal.setHorarioSistema(formatDateHora.format(new Date()));
            } catch (InterruptedException ex) {
            }
        }
    }
}

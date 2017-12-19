package view.mail;

import javax.swing.JOptionPane;

// Thread externa que executa uma thread interna no cliente de email
// Baixar mensagens do servidor a cada 2 minutos
public class BaixarMsg extends Thread {

    private ClienteEmail clienteEmail;

    public BaixarMsg(ClienteEmail clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    @Override
    public void run() {
        while (true) {
            try {
                clienteEmail.baixarNovasMsgs();// Dispara a thread interna no cliente de email
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            try {
                sleep(120000);// Aguarda 2 minutos
            } catch (InterruptedException ex) {
            }
        }
    }
}

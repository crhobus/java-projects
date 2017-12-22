package Servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ThreadServCliente extends Thread {

    private Servidor servidor;
    private Socket socket;

    public ThreadServCliente(Socket socket, Servidor servidor) {
        this.servidor = servidor;
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String linha;
            while ((linha = reader.readLine()) != null) {
                if ("".equals(linha.trim())) {
                    servidor.enviarMsgCliente(linha);
                } else {
                    servidor.enviarMsgCliente(socket.getInetAddress().getHostAddress() + ">>  " + linha);
                }
            }
        } catch (Exception ex) {
        } finally {
            try {
                reader.close();
                socket.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de um cliente", "Erro inesperado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

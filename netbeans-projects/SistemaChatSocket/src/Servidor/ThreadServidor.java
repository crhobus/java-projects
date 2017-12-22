package Servidor;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ThreadServidor extends Thread {

    private Servidor servidor;
    private int porta;

    public ThreadServidor(Servidor servidor, int porta) {
        this.servidor = servidor;
        this.porta = porta;
    }

    @Override
    public void run() {
        ServerSocket serv = null;
        try {
            serv = new ServerSocket(porta);
            Socket socket = null;
            PrintWriter writer;
            while (true) {
                socket = serv.accept();// a thread para aqui até que um cliente se conectar, para então continuar executando
                writer = new PrintWriter(socket.getOutputStream());
                servidor.addMsgCliente(writer);
                ThreadServCliente thread = new ThreadServCliente(socket, servidor);
                thread.start();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Servidor encontrou um problema com a conexão", "Erro inesperado", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                servidor.desconectar();
                serv.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão do servidor", "Erro inesperado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

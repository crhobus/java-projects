package Cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ThreadCliente extends Thread {

    private String ip;
    private int porta;
    private Cliente cliente;
    private PrintStream ps;

    public ThreadCliente(String ip, int porta, Cliente cliente) {
        this.ip = ip;
        this.porta = porta;
        this.cliente = cliente;
    }

    public PrintStream getPrintStream() {
        return ps;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        Socket socket = null;
        try {
            socket = new Socket(ip, porta);
            ps = new PrintStream(socket.getOutputStream());
            cliente.atualizaConexao();
            String linha;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((linha = reader.readLine()) != null) {
                cliente.mostrarMsgServidor(linha);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "UM Problema foi detectado na tentativa de conexão"
                    + "\nCertifique-se que o número de IP e porta foram digitados corretamente"
                    + "\nVerifique se o servidor esta ativo",
                    "Erro inesperado", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                JOptionPane.showMessageDialog(null, "A conexão com o servidor foi fechada inesperadamente", "Conexão", JOptionPane.INFORMATION_MESSAGE);
                if (socket != null && ps != null && reader != null) {
                    socket.close();
                    ps.close();
                    reader.close();
                }
                cliente.atualizaDesconexao();
                socket = null;
                ps = null;
                reader = null;
            } catch (Exception ex) {
            }
        }
    }
}

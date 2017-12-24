package Cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Cliente {

    public static void main(String[] args) {
        Socket socket = null;
        PrintStream ps = null;
        try {
            socket = new Socket("192.168.56.1", 8080);
            ps = new PrintStream(socket.getOutputStream());
            ps.println(JOptionPane.showInputDialog("PH"));
        } catch (IOException e) {
            System.out.println("Algum problema ocorreu ao criar ou enviar dados pelo socket.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}

package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket s = null;
        BufferedReader entrada = null;
        try {
            serv = new ServerSocket(4);
            s = serv.accept();
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            JOptionPane.showInputDialog(entrada.readLine());
        } catch (IOException e) {
            System.out.println("Algum problema ocorreu para criar ou receber o socket.");
        } finally {
            try {
                s.close();
                serv.close();
            } catch (IOException e) {
            }
        }
    }
}
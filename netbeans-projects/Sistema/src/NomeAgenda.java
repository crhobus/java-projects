
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class NomeAgenda extends JDialog {

    private ArrayList<ArrayFuncionario> lista = new ArrayList();
    private InterfaceAgenda listener;
    private JTextField tf_Nome;

    NomeAgenda() {
        this.setSize(350, 160);
        this.setLocationRelativeTo(null);//Posiciona no centro da tela
        this.setResizable(false);
        LerArquivo();
        this.criar();
        this.setVisible(true);
    }

    private void criar() {
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial Black", Font.BOLD, 11);
        JLabel label = new JLabel("Insira Nome:");
        label.setBounds(130, 15, 200, 20);
        label.setFont(fonte);
        tela.add(label);
        JButton ok = new JButton("Ok");
        ok.setBounds(45, 80, 85, 26);
        tela.add(ok);
        JButton Cancelar = new JButton("Cancelar");
        Cancelar.setBounds(195, 80, 85, 26);
        tela.add(Cancelar);
        Cancelar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        tf_Nome = new JTextField();
        tf_Nome.setBounds(10, 45, 310, 20);
        tela.add(tf_Nome);
        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if ("".equals(tf_Nome.getText())) {
                    JOptionPane.showMessageDialog(null, "Campo Nome esta em branco", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    Quem();
                }
            }
        });
    }

    public void setListener(InterfaceAgenda listener) {
        this.listener = listener;
    }

    //listener do botão quando ele for pressionado
    private void botaoOkPressionado() {
        setVisible(false);

        //se houver um listener registrado, notifica
        if (listener != null) {
            listener.campoTextoAlterado(tf_Nome.getText());
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Funcionario.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista = (ArrayList<ArrayFuncionario>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Quem() {
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Funcionarios cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = tf_Nome.getText();
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayFuncionario aux = lista.get(i);
                if (Texto.equalsIgnoreCase(aux.getNome())) {//Não importa se texto digitado esta em maiusculo ou minusculo
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "O Funcionario " + Texto + " esta inserido ", "Funcionario", JOptionPane.WARNING_MESSAGE);
                tf_Nome.setText(Texto);
                botaoOkPressionado();
            } else {
                JOptionPane.showMessageDialog(null, "O Funcionario " + Texto + " não esta inserido", "Funcionario", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

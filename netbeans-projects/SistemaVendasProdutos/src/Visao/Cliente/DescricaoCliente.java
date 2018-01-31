package Visao.Cliente;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DescricaoCliente extends JDialog implements ActionListener {

    private JTextArea taArea;
    private ListenerCliente listener;

    public DescricaoCliente() {
        setTitle("");
        Container tela = getContentPane();
        tela.setLayout(null);
        Color cor1 = new Color(0, 0, 0);
        Color cor2 = new Color(250, 250, 250);
        Font fonte = new Font("Arial Black", Font.BOLD, 11);
        JPanel painel = new JPanel();
        painel.setBackground(cor2);
        painel.setLayout(null);
        painel.setBounds(5, 5, 335, 262);
        tela.add(painel);
        painel.setBorder(BorderFactory.createLineBorder(cor1.darkGray, 4));//Borda em volta no JPanel

        JLabel lbdescricaoClie = new JLabel("Descrição do Cliente");
        lbdescricaoClie.setBounds(90, 15, 200, 20);
        lbdescricaoClie.setFont(fonte);
        painel.add(lbdescricaoClie);

        JButton btok = new JButton("...");
        btok.setBounds(15, 10, 20, 20);
        painel.add(btok);
        btok.addActionListener(this);

        taArea = new JTextArea();
        taArea.setBorder(BorderFactory.createLineBorder(cor1.darkGray, 2));//Borda em volta no JPanel
        JScrollPane rolagem = new JScrollPane(taArea);//barra de rolagem
        rolagem.setBounds(10, 40, 315, 210);
        painel.add(rolagem);

        setSize(350, 300);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);
    }

    public void actionPerformed(ActionEvent evento) {
        if ("".equals(taArea.getText())) {
            JOptionPane.showMessageDialog(null, "Campo de texto inválido", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            if (listener != null) {
                listener.campoAlterado(taArea.getText());
            }
            setVisible(false);
        }
    }

    public void setListener(ListenerCliente listener) {
        this.listener = listener;
    }
}

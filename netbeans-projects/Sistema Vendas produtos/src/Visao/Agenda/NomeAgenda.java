package Visao.Agenda;

import Arquivos.LerArquivo;
import Controle.FuncionarioControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NomeAgenda extends JDialog implements ActionListener {

    private JTextField tfNome;
    private JButton btOk, btCancelar;
    private ListenerAgenda listener;
    private FuncionarioControl control;
    private LerArquivo lerArquivo;

    public NomeAgenda() {
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial Black", Font.BOLD, 11);
        control = new FuncionarioControl();
        lerArquivo = new LerArquivo();
        control.lerArquivo(lerArquivo);
        
        JLabel label = new JLabel("Insira Nome:");
        label.setBounds(130, 15, 200, 20);
        label.setFont(fonte);
        tela.add(label);

        btOk = new JButton("Ok");
        btOk.setBounds(45, 80, 85, 26);
        tela.add(btOk);
        btOk.addActionListener(this);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(195, 80, 85, 26);
        tela.add(btCancelar);
        btCancelar.addActionListener(this);

        tfNome = new JTextField();
        tfNome.setBounds(10, 45, 310, 20);
        tela.add(tfNome);
        tfNome.addActionListener(this);

        setSize(350, 160);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);
    }

    public void setListener(ListenerAgenda listener) {
        this.listener = listener;
    }

    private void verificaNome() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há funcionários cadastrados");
        } else {
            if (control.funcionarioCadasNome(tfNome.getText())) {
                JOptionPane.showMessageDialog(null, "O funcionario " + tfNome.getText() + " esta cadastrado ", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                tfNome.setText(tfNome.getText());
                setVisible(false);
                if (listener != null) {
                    listener.campoAlterado(tfNome.getText());
                }
            } else {
                JOptionPane.showMessageDialog(null, "O funcionario " + tfNome.getText() + " não esta cadastrado", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOk || evento.getSource() == tfNome) {
            if ("".equals(tfNome.getText())) {
                JOptionPane.showMessageDialog(null, "Campo nome inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    verificaNome();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (evento.getSource() == btCancelar) {
            setVisible(false);
        }
    }
}

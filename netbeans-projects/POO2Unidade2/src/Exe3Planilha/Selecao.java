package Exe3Planilha;

import java.awt.event.*;
import javax.swing.*;

public class Selecao extends JDialog implements ActionListener {

    private JComboBox cbSelecionar;
    private JButton btOk, btCancelar;
    private String operacao;

    public Selecao() {
        setTitle("Seleção de operação");
        setSize(200, 150);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lbOperacao = new JLabel("Operação:");
        lbOperacao.setBounds(20, 20, 80, 14);
        add(lbOperacao);

        cbSelecionar = new JComboBox();
        cbSelecionar.addItem(".......");
        cbSelecionar.addItem(" +");
        cbSelecionar.addItem(" -");
        cbSelecionar.addItem(" *");
        cbSelecionar.addItem(" /");
        cbSelecionar.addItem(" som c1");
        cbSelecionar.addItem(" som c2");
        cbSelecionar.setBounds(90, 17, 74, 25);
        add(cbSelecionar);

        btOk = new JButton("OK");
        btOk.setBounds(20, 65, 55, 28);
        add(btOk);
        btOk.addActionListener(this);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(83, 65, 85, 28);
        add(btCancelar);
        btCancelar.addActionListener(this);
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOk) {
            if (".......".equals(cbSelecionar.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "Campo operação inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                setOperacao((String) cbSelecionar.getSelectedItem());
                dispose();
            }
        }
        if (evento.getSource() == btCancelar) {
            dispose();
        }
    }
}

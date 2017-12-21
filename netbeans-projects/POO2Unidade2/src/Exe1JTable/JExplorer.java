package Exe1JTable;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class JExplorer extends JFrame {

    private JTextField campo;
    private ListarArquivosTableModel listaraArquivosModel;

    public JExplorer() {
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("JExplorer");
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        listaraArquivosModel = new ListarArquivosTableModel();
        TrataEventos trata = new TrataEventos();
        JPanel painel = new JPanel(new GridLayout(1, 3));
        JLabel label = new JLabel("Pasta:");
        campo = new JTextField();
        campo.addActionListener(trata);
        JButton btAtualizar = new JButton("Atualizar");
        btAtualizar.addActionListener(trata);
        add(painel, BorderLayout.NORTH);
        painel.add(label);
        painel.add(campo);
        painel.add(btAtualizar);
        JTable tabela = new JTable(listaraArquivosModel);
        ArquivosMaioresRenderer rendener = new ArquivosMaioresRenderer(500);
        for (int i = 0; i < tabela.getColumnModel().getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(rendener);
        }
        JScrollPane scroll = new JScrollPane(tabela);
        add(BorderLayout.CENTER, scroll);
    }

    private void atualizar() throws Exception {
        if ("".equals(campo.getText())) {
            campo.grabFocus();
            throw new Exception("Campo de texto vazio");
        } else {
            try {
                listaraArquivosModel.atualizar(campo.getText());
                listaraArquivosModel.fireTableDataChanged();//Esse mostra as linhas na tabela
            } catch (Exception ex) {
                campo.grabFocus();
                throw new Exception("Diretório ou pasta inválida");
            }
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            try {
                atualizar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}



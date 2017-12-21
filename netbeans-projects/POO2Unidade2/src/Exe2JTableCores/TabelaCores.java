package Exe2JTableCores;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TabelaCores extends JFrame {

    private JTextField tfTexto;
    private JButton btAdicionar;
    private TableModel tableModel;
    private Renderizador rendener;

    public TabelaCores() {
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Tabela usando cores");
        criar();
        setVisible(true);
    }

    public void criar() {
        tableModel = new TableModel();
        rendener = new Renderizador();
        TrataEventos trata = new TrataEventos();
        JPanel painel = new JPanel(new GridLayout(1, 3));
        tfTexto = new JTextField();
        tfTexto.addActionListener(trata);
        btAdicionar = new JButton("Adicionar");
        btAdicionar.addActionListener(trata);
        add(painel, BorderLayout.NORTH);
        painel.add(tfTexto);
        painel.add(btAdicionar);
        JTable tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }

        JCheckBox check = new JCheckBox();
        check.setSelected(false);
        check.setSelected(true);
        check.setHorizontalAlignment(SwingConstants.CENTER);
        
        JComboBox opcao = new JComboBox();
        opcao.addItem("java.awt.color[r=255,g=255,b=255]");
        opcao.addItem("java.awt.color[r=0,g=0,b=0]");
        opcao.addItem("java.awt.color[r=0,g=0,b=255]");
        opcao.addItem("java.awt.color[r=255,g=255,b=0]");
        opcao.addItem("java.awt.color[r=255,g=0,b=0]");

        tabela.setDefaultEditor(Boolean.class, new DefaultCellEditor(check));
        tabela.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(opcao));
        tabela.getColumnModel().getColumn(3).setCellEditor(new Editor());

        JScrollPane scroll = new JScrollPane(tabela);
        add(BorderLayout.CENTER, scroll);

        tabela.setRowHeight(20);
        tabela.getColumnModel().getColumn(0).setMinWidth(120);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(5);
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if ("".equals(tfTexto.getText())) {
                JOptionPane.showMessageDialog(null, "Campo de texto vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                tfTexto.grabFocus();
            } else {
                tableModel.adicionar(tfTexto.getText());
                tableModel.fireTableDataChanged();
            }
        }
    }
}

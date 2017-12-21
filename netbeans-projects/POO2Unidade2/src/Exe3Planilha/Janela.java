package Exe3Planilha;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Janela extends JFrame implements ActionListener {

    private JButton btAdicionar, btRemover;
    private TableModel tableModel;
    private JTable tabela;
    private Renderizador rendener;

    public Janela() {
        super("Planilha");
        Container tela = getContentPane();
        tela.setLayout(null);
        JPanel painel1 = new JPanel();
        painel1.setLayout(null);
        tela.add(painel1);
        painel1.setBounds(10, 10, 435, 330);
        painel1.setBorder(BorderFactory.createTitledBorder("Planilha"));
        tableModel = new TableModel();
        rendener = new Renderizador();

        btAdicionar = new JButton("Nova Linha");
        btAdicionar.setBounds(70, 35, 115, 26);
        painel1.add(btAdicionar);
        btAdicionar.addActionListener(this);

        btRemover = new JButton("Remover linha");
        btRemover.setBounds(210, 35, 115, 26);
        painel1.add(btRemover);
        btRemover.addActionListener(this);

        tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(2).setCellEditor(new Editor());
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(10, 70, 416, 250);
        painel1.add(scroll);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(463, 380);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btAdicionar) {
            tableModel.adicionar();
            tableModel.fireTableDataChanged();
        }
        if (evento.getSource() == btRemover) {
            if (tabela.getSelectedRow() > -1) {
                tableModel.remover(tabela.getSelectedRow());
            }
        }
    }
}

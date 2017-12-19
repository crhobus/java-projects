package Aula3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JanelaTurmaMonica extends JFrame {

    public JanelaTurmaMonica() {

        setSize(500, 300);
        setTitle("Aula de JTable");
        setLocationRelativeTo(null); // centraliza
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();

    }

    private void initComponents() {
        JPanel painel = new JPanel(new GridLayout(3, 2));
        JLabel label1 = new JLabel("Posição :");
        final JTextField jtfPosicao = new JTextField();
        JLabel label2 = new JLabel("Nome :");
        final JTextField jtfNome = new JTextField();
        final TurmaMonicaTableModel tableModel = new TurmaMonicaTableModel();
        JButton jbOK = new JButton("OK");
        jbOK.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                tableModel.alterarNome(
                        Integer.parseInt(jtfPosicao.getText()),
                        jtfNome.getText());
            }
        });

        painel.add(label1);
        painel.add(jtfPosicao);
        painel.add(label2);
        painel.add(jtfNome);
        painel.add(jbOK);

        add(painel, BorderLayout.NORTH);

        JTable tabela = new JTable();
        tabela.setRowHeight(50);
        tabela.setModel(tableModel);

        NegritoCellRenderer negrito = new NegritoCellRenderer();
        tabela.setDefaultRenderer(String.class, negrito);
//		tabela.setDefaultRenderer(Integer.class, negrito);

        SliderRenderer slider = new SliderRenderer();
        tabela.getColumnModel().getColumn(2).
                setCellRenderer(slider);
        //tabela.getColumnModel().getColumn(2).setMaxWidth(20);

        PainelIconeRenderer painelIcon = new PainelIconeRenderer();
        tabela.getColumnModel().getColumn(3).
                setCellRenderer(painelIcon);

        /*
        JComboBox nomes = new JComboBox();
        nomes.addItem("Monica");
        nomes.addItem("Cebolinha");
        nomes.addItem("Cascao");
        nomes.addItem("Bidu");

        tabela.getColumnModel().getColumn(0).
        setCellEditor(new DefaultCellEditor(nomes));
         */

        tabela.getColumnModel().getColumn(0).
                setCellEditor(new FileNameEditor());
        tabela.getColumnModel().getColumn(2).
                setCellEditor(new SliderEditor());
        tabela.getColumnModel().getColumn(3).
                setCellEditor(new AbreJanela());

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tabela);
        //scrollPane.setViewportView(tabela);

        add(scrollPane, BorderLayout.CENTER);


    }

    public static void main(String[] args) {

        JanelaTurmaMonica j = new JanelaTurmaMonica();
        j.setVisible(true);

    }
}









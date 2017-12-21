package JTable;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Tabela extends JFrame implements ActionListener {

    private JTextField tfNome, tfCredito;
    private JButton btAdicionar;
    private TableModel tableModel;
    private JTextFieldDouble jTextFieldDouble;
    private Renderizadora rendener;

    public Tabela() {
        super("JTable");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        JPanel painel1 = new JPanel();
        painel1.setLayout(null);
        tela.add(painel1);
        painel1.setBounds(10, 10, 472, 330);
        painel1.setBorder(BorderFactory.createTitledBorder("JTable"));
        tableModel = new TableModel();
        jTextFieldDouble = new JTextFieldDouble();
        rendener = new Renderizadora();

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(20, 20, 80, 14);
        lbNome.setFont(fonte);
        painel1.add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(20, 38, 150, 20);
        painel1.add(tfNome);

        JLabel lbCretito = new JLabel("Credito");
        lbCretito.setBounds(180, 20, 80, 14);
        lbCretito.setFont(fonte);
        painel1.add(lbCretito);

        tfCredito = new JTextField();
        tfCredito.setBounds(180, 38, 120, 20);
        painel1.add(tfCredito);
        tfCredito.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent evento) {
                try {
                    jTextFieldDouble.validaCampo(tfCredito);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btAdicionar = new JButton("Adicionar");
        btAdicionar.setBounds(310, 35, 90, 26);
        painel1.add(btAdicionar);
        btAdicionar.addActionListener(this);

        JTable tabela = new JTable(tableModel);
        for (int y = 0; y < tabela.getColumnModel().getColumnCount() - 1; y++) {
            tabela.getColumnModel().getColumn(y).setCellRenderer(rendener);
        }
        JComboBox opcoes = new JComboBox();
        opcoes.addItem("R$ 20,00");
        opcoes.addItem("R$ 50,00");
        opcoes.addItem("R$ 100,00");
        opcoes.addItem("R$ 500,00");
        opcoes.addItem("R$ 1.000,00");
        tabela.setDefaultEditor(Double.class, new DefaultCellEditor(opcoes));
        tabela.getColumnModel().getColumn(0).setCellEditor(new NomeEditor());
        tabela.setDefaultEditor(ImageIcon.class, new AbreJanela());
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(10, 70, 452, 250);
        painel1.add(scroll);

        tabela.setRowHeight(50);
        tabela.setDefaultRenderer(ImageIcon.class, new ImagemRenderer());
        tabela.getColumnModel().getColumn(0).setMinWidth(120);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(20);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 380);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void add() throws Exception {
        String nome;
        boolean vip;
        double credito;
        Date data;

        if ("".equals(tfNome.getText())) {
            tfNome.grabFocus();
            throw new Exception("Campo nome inválido");
        } else {
            nome = tfNome.getText();
            if ("".equals(tfCredito.getText()) || Double.parseDouble(tfCredito.getText()) <= 0) {
                tfCredito.grabFocus();
                throw new Exception("Campo Credito inválido");
            } else {
                credito = Double.parseDouble(tfCredito.getText());
                if (credito >= 100) {
                    vip = true;
                } else {
                    vip = false;
                }
                data = new Date();
                tableModel.adicionar(nome, credito, vip, data);
                tableModel.fireTableDataChanged();
            }
        }
    }

    public void actionPerformed(ActionEvent evento) {
        try {
            add();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

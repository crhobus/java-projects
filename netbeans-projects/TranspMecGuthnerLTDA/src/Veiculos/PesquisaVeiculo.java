package Veiculos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import FormatacaoCampos.CriarObjGrafico;

public class PesquisaVeiculo extends JDialog implements ActionListener {

    private JTextField tfPesquisa;
    private JLabel lbRecebe;
    private JButton btOK, btCancelar;
    private JTable tabela;
    private TableModel tableModel;
    private VeiculoControl controle;

    public PesquisaVeiculo(VeiculoControl controle) {
        this.controle = controle;
        tableModel = new TableModel(controle);
        initComponents();
    }

    private void initComponents() {
        setTitle("Pesquisa Veículos");
        setLayout(null);
        JPanel panel1 = CriarObjGrafico.criarJPanel(5, 5, 482, 340);
        add(panel1);

        JLabel lbPesquisa = CriarObjGrafico.criarJLabel("Pesquisar Por...", 20, 30, 100, 14);
        lbPesquisa.setForeground(Color.BLUE);
        panel1.add(lbPesquisa);

        lbRecebe = CriarObjGrafico.criarJLabel("Codigo", 150, 30, 150, 14);
        lbRecebe.setForeground(Color.RED);
        panel1.add(lbRecebe);

        tfPesquisa = CriarObjGrafico.criarJTextField(20, 55, 270, 20);
        tfPesquisa.addActionListener(this);
        panel1.add(tfPesquisa);

        btOK = CriarObjGrafico.criarJButton("OK", 310, 52, 51, 26);
        btOK.addActionListener(this);
        panel1.add(btOK);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 370, 52, 90, 26);
        btCancelar.addActionListener(this);
        panel1.add(btCancelar);

        tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getColumn(0).setMinWidth(50);
        tabela.getColumnModel().getColumn(1).setMinWidth(300);
        tabela.getColumnModel().getColumn(2).setMinWidth(80);
        tabela.getColumnModel().getColumn(3).setMinWidth(80);
        tabela.getColumnModel().getColumn(4).setMinWidth(140);
        tabela.getColumnModel().getColumn(5).setMinWidth(140);
        tabela.getColumnModel().getColumn(6).setMinWidth(90);
        tabela.getColumnModel().getColumn(7).setMinWidth(90);
        tabela.getColumnModel().getColumn(8).setMinWidth(90);
        tabela.getColumnModel().getColumn(9).setMinWidth(120);
        tabela.getColumnModel().getColumn(10).setMinWidth(170);
        tabela.getColumnModel().getColumn(11).setMinWidth(170);
        tabela.getColumnModel().getColumn(12).setMinWidth(150);
        tabela.getColumnModel().getColumn(13).setMinWidth(140);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JTableHeader header = tabela.getTableHeader();
        header.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evento) {
                int coluna = tabela.columnAtPoint(evento.getPoint());
                lbRecebe.setText(tabela.getColumnName(coluna));
            }
        });
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(15, 90, 450, 240);
        panel1.add(scroll);

        setResizable(false);
        setSize(500, 380);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOK || evento.getSource() == tfPesquisa) {
            if ("".equals(tfPesquisa.getText())) {
                JOptionPane.showMessageDialog(null, "Campo pesquisa invalido", "Erro", JOptionPane.ERROR_MESSAGE);
                tfPesquisa.grabFocus();
            } else {
                try {
                    controle.listaTodos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                if (lbRecebe.getText().equals("Codigo")) {
                    try {
                        controle.getListaVeiCod(Integer.parseInt(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Nome")) {
                    controle.getListaVeiNome(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Ano")) {
                    try {
                        controle.getListaVeiAno(Integer.parseInt(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Modelo")) {
                    try {
                        controle.getListaVeiMod(Integer.parseInt(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Cor")) {
                    controle.getListaVeiCor(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Cor")) {
                    controle.getListaVeiCor(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Combustivel")) {
                    controle.getListaVeiCom(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Potência")) {
                    try {
                        controle.getListaVeiPot(Double.parseDouble(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Vávulas")) {
                    try {
                        controle.getListaVeiVal(Integer.parseInt(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Cilíndros")) {
                    try {
                        controle.getListaVeiCil(Integer.parseInt(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Placa")) {
                    controle.getListaVeiPla(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Chassi")) {
                    controle.getListaVeiCha(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("RENAVAM")) {
                    controle.getListaVeiRen(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Tipo")) {
                    controle.getListaVeiTip(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("CPF / CNPJ Cliente")) {
                    controle.getListaVeiCPFCNPJ(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
            }
        }
        if (evento.getSource() == btCancelar) {
            tfPesquisa.setText("");
            controle.limparLista();
            try {
                controle.listaTodos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            tableModel.fireTableDataChanged();
        }
    }
}

package Fornecedores;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

import Formatacao.ObjGraficos;

public class PesquisaFornecedores extends ObjGraficos implements ActionListener {

    private static final long serialVersionUID = -9031181596842943500L;
    private JTextField tfPesquisa;
    private JLabel lbRecebe;
    private JButton btOK, btCancelar;
    private JTable tabela;
    private TableModelForn tableModel;
    private FornecedorControl controle;
    private ListenerFornecedor listener;

    public PesquisaFornecedores(FornecedorControl controle) {
        this.controle = controle;
        tableModel = new TableModelForn(controle);
        initComponents();
    }

    private void initComponents() {
        setTitle("Pesquisa Fornecedores");
        setLayout(null);
        JPanel panel = getJPanelLineBorder(5, 5, 482, 340);
        add(panel);

        JLabel lbPesquisa = getJLabel("Pesquisar Por...", 20, 30, 100, 14);
        lbPesquisa.setForeground(Color.BLUE);
        panel.add(lbPesquisa);

        lbRecebe = getJLabel("Codigo", 150, 30, 150, 14);
        lbRecebe.setForeground(Color.RED);
        panel.add(lbRecebe);

        tfPesquisa = getJTextField(20, 55, 270, 20);
        tfPesquisa.addActionListener(this);
        panel.add(tfPesquisa);

        btOK = getJButton("OK", 310, 52, 51, 26);
        btOK.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOK.setToolTipText("OK");
        btOK.addActionListener(this);
        panel.add(btOK);

        btCancelar = getJButton("Cancelar", 370, 52, 90, 26);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getColumn(0).setMinWidth(50);
        tabela.getColumnModel().getColumn(1).setMinWidth(300);
        tabela.getColumnModel().getColumn(2).setMinWidth(300);
        tabela.getColumnModel().getColumn(3).setMinWidth(120);
        tabela.getColumnModel().getColumn(4).setMinWidth(350);
        tabela.getColumnModel().getColumn(5).setMinWidth(320);
        tabela.getColumnModel().getColumn(6).setMinWidth(60);
        tabela.getColumnModel().getColumn(7).setMinWidth(150);
        tabela.getColumnModel().getColumn(8).setMinWidth(140);
        tabela.getColumnModel().getColumn(9).setMinWidth(110);
        tabela.getColumnModel().getColumn(10).setMinWidth(110);
        tabela.getColumnModel().getColumn(11).setMinWidth(130);
        tabela.getColumnModel().getColumn(12).setMinWidth(110);
        tabela.getColumnModel().getColumn(13).setMinWidth(380);
        tabela.getColumnModel().getColumn(14).setMinWidth(130);
        tabela.getColumnModel().getColumn(15).setMinWidth(130);
        tabela.getColumnModel().getColumn(16).setMinWidth(130);
        tabela.getColumnModel().getColumn(17).setMinWidth(120);
        tabela.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    if (listener != null) {
                        listener.exibeFornecedor(controle.getListaPosicao(tabela.getSelectedRow()));
                        setVisible(false);
                    }
                }
            }
        });
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
        panel.add(scroll);

        setResizable(false);
        setSize(500, 380);
        setLocationRelativeTo(null);
    }

    public void setListener(ListenerFornecedor listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOK || evento.getSource() == tfPesquisa) {
            if ("".equals(tfPesquisa.getText())) {
                JOptionPane.showMessageDialog(null, "Campo pesquisa invalido", "Erro", JOptionPane.ERROR_MESSAGE);
                tfPesquisa.grabFocus();
            } else {
                try {
                    controle.listarFornecedores();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                if (lbRecebe.getText().equals("Codigo")) {
                    try {
                        controle.getListaCod(Integer.parseInt(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Nome")) {
                    controle.getListaNome(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Nome Fantasia")) {
                    controle.getListaNomeFan(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Sigla")) {
                    controle.getListaSig(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Endereço")) {
                    controle.getListaEnd(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Bairro")) {
                    controle.getListaBai(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Número")) {
                    try {
                        controle.getListaNum(Integer.parseInt(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Cidade")) {
                    controle.getListaCid(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("UF")) {
                    controle.getListaUF(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Fone")) {
                    controle.getListaFon(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Celular")) {
                    controle.getListaCel(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("CNPJ")) {
                    controle.getListaCNPJ(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("IE")) {
                    controle.getListaIE(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("E-Mail")) {
                    controle.getListaMail(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Compra Mínima")) {
                    try {
                        controle.getListaComMin(Double.parseDouble(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Compra Máxima")) {
                    try {
                        controle.getListaComMax(Double.parseDouble(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Valor Frete")) {
                    try {
                        controle.getListaValFre(Double.parseDouble(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Juros a.m.")) {
                    try {
                        controle.getListaJur(Double.parseDouble(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
            }
        }
        if (evento.getSource() == btCancelar) {
            tfPesquisa.setText("");
            controle.limparLista();
            try {
                controle.listarFornecedores();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            tableModel.fireTableDataChanged();
        }
    }
}

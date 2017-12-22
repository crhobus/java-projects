package OrdensServicos;

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
import javax.swing.table.JTableHeader;

import Formatacao.ObjGraficos;

public class PesquisaOrdemServico extends ObjGraficos implements ActionListener {

    private static final long serialVersionUID = -7820712201978346317L;
    private JTextField tfPesquisa;
    private JLabel lbRecebe;
    private JButton btOK, btCancelar;
    private JTable tabela;
    private TableModelOS tableModel;
    private NovaOSControl controle;
    private ListenerOS listener;

    public PesquisaOrdemServico(NovaOSControl controle) {
        this.controle = controle;
        tableModel = new TableModelOS(controle);
        initComponents();
    }

    private void initComponents() {
        setTitle("Pesquisa Ordem Servico");
        setLayout(null);
        JPanel panel = getJPanelLineBorder(5, 5, 482, 340);
        add(panel);

        JLabel lbPesquisa = getJLabel("Pesquisar Por...", 20, 30, 100, 14);
        lbPesquisa.setForeground(Color.BLUE);
        panel.add(lbPesquisa);

        lbRecebe = getJLabel("Número OS", 150, 30, 150, 14);
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
        tabela.getColumnModel().getColumn(0).setMinWidth(50);
        tabela.getColumnModel().getColumn(1).setMinWidth(130);
        tabela.getColumnModel().getColumn(2).setMinWidth(110);
        tabela.getColumnModel().getColumn(3).setMinWidth(350);
        tabela.getColumnModel().getColumn(4).setMinWidth(120);
        tabela.getColumnModel().getColumn(5).setMinWidth(250);
        tabela.getColumnModel().getColumn(6).setMinWidth(200);
        tabela.getColumnModel().getColumn(7).setMinWidth(50);
        tabela.getColumnModel().getColumn(8).setMinWidth(180);
        tabela.getColumnModel().getColumn(9).setMinWidth(150);
        tabela.getColumnModel().getColumn(10).setMinWidth(110);
        tabela.getColumnModel().getColumn(11).setMinWidth(110);
        tabela.getColumnModel().getColumn(12).setMinWidth(200);
        tabela.getColumnModel().getColumn(13).setMinWidth(180);
        tabela.getColumnModel().getColumn(14).setMinWidth(160);
        tabela.getColumnModel().getColumn(15).setMinWidth(120);
        tabela.getColumnModel().getColumn(16).setMinWidth(80);
        tabela.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    if (listener != null) {
                        try {
                            listener.exibeOrdemServico(controle.getListaPosicao(tabela.getSelectedRow()));
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
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

    public void setListener(ListenerOS listener) {
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
                    controle.listaTodos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                if (lbRecebe.getText().equals("Número OS")) {
                    try {
                        controle.getListaNumOS(Integer.parseInt(tfPesquisa.getText()));
                        tableModel.fireTableDataChanged();
                        return;
                    } catch (NumberFormatException ex) {
                    }
                }
                if (lbRecebe.getText().equals("Situação")) {
                    controle.getListaSit(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Data Geração")) {
                    controle.getListaDataGer(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Cliente")) {
                    controle.getListaClie(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("CPF / CNPJ")) {
                    controle.getListaCpfCnpj(tfPesquisa.getText());
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
                    controle.getListaFone(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Celular")) {
                    controle.getListaCel(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Nome Aparelho")) {
                    controle.getListaNomeApa(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Marca")) {
                    controle.getListaMarcaApa(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Modelo")) {
                    controle.getListaModApa(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Assistência")) {
                    controle.getListaAssi(tfPesquisa.getText());
                    tableModel.fireTableDataChanged();
                    return;
                }
                if (lbRecebe.getText().equals("Orçamento")) {
                    controle.getListaOrc(tfPesquisa.getText());
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

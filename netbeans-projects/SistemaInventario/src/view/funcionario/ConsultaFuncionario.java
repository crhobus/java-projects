package view.funcionario;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;

import dbOracle.FuncionarioDAO;

import view.PanelComponentes;

public class ConsultaFuncionario extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfProcura;
    private JLabel lbNomeColuna;
    private JButton btOK, btCancelar;
    private JTable tabela;
    private TableModelFuncionario tableModel;
    private FuncionarioDAO funcionarioDAO;
    private ListenerFuncionario listener;
    private int coluna;

    public ConsultaFuncionario(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
        this.coluna = -1;
        tableModel = new TableModelFuncionario(funcionarioDAO);
        initComponents();
    }

    private void initComponents() {
        setTitle("Consulta Funcionários");
        setLayout(null);

        PanelComponentes panel = new PanelComponentes(5, 5, 685, 464);
        this.add(panel);

        panel.addJLabel("Pesquisar Por...", 20, 30, 100, 14);

        lbNomeColuna = panel.addJLabelRED(110, 30, 150, 14);

        tfProcura = panel.addJTextField(20, 55, 485, 20);
        tfProcura.addActionListener(this);

        btOK = panel.addJButtonOK(515, 52, 51, 26);
        btOK.setMargin(new Insets(0, 0, 0, 0));
        btOK.setToolTipText("Pesquisar...");
        btOK.addActionListener(this);

        btCancelar = panel.addJButtonCancelar(575, 52, 90, 26);
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);

        tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getColumn(0).setMinWidth(80);
        tabela.getColumnModel().getColumn(1).setMinWidth(200);
        tabela.getColumnModel().getColumn(2).setMinWidth(120);
        tabela.getColumnModel().getColumn(3).setMinWidth(110);
        tabela.getColumnModel().getColumn(4).setMinWidth(280);
        tabela.getColumnModel().getColumn(5).setMinWidth(200);
        tabela.getColumnModel().getColumn(6).setMinWidth(80);
        tabela.getColumnModel().getColumn(7).setMinWidth(100);
        tabela.getColumnModel().getColumn(8).setMinWidth(200);
        tabela.getColumnModel().getColumn(9).setMinWidth(110);
        tabela.getColumnModel().getColumn(10).setMinWidth(180);
        tabela.getColumnModel().getColumn(11).setMinWidth(120);
        tabela.getColumnModel().getColumn(12).setMinWidth(60);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    if (listener != null) {
                        listener.exibeFuncionario(funcionarioDAO.getFuncionarioMapa(tabela.getSelectedRow()));
                        dispose();
                    }
                }
            }
        });
        JTableHeader header = tabela.getTableHeader();
        header.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evento) {
                coluna = tabela.columnAtPoint(evento.getPoint());
                lbNomeColuna.setText(tabela.getColumnName(coluna));
            }
        });
        header.setReorderingAllowed(false);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(15, 90, 650, 360);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);

        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void setListener(ListenerFuncionario listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOK || evento.getSource() == tfProcura) {
            if ("".equals(lbNomeColuna.getText())) {
                JOptionPane.showMessageDialog(null, "Selecione uma coluna", "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            } else {
                if ("".equals(tfProcura.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Campo pesquisa inválido, entre com a respectiva pesquisa", "Erro no sistema", JOptionPane.ERROR_MESSAGE);
                    tfProcura.grabFocus();
                } else {
                    try {
                        if (coluna == 0 || coluna == 6) {
                            try {
                                funcionarioDAO.listFuncionariosCondicao(coluna, "", Integer.parseInt(tfProcura.getText()), 0);
                            } catch (NumberFormatException ex) {
                            }
                        } else {
                            if (coluna == 11) {
                                try {
                                    funcionarioDAO.listFuncionariosCondicao(coluna, "", 0, Double.parseDouble(tfProcura.getText()));
                                } catch (NumberFormatException ex) {
                                }
                            } else {
                                funcionarioDAO.listFuncionariosCondicao(coluna, tfProcura.getText(), 0, 0);
                            }
                        }
                        tableModel.fireTableDataChanged();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (evento.getSource() == btCancelar) {
            tfProcura.setText("");
            lbNomeColuna.setText("");
            funcionarioDAO.limparMapa();
            try {
                funcionarioDAO.listFuncionarios();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
            this.coluna = -1;
            tableModel.fireTableDataChanged();
        }
    }
}

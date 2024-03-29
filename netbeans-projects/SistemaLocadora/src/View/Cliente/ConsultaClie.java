package View.Cliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;

import Control.Cliente.ClienteControl;
import Control.Cliente.ListenerClie;
import View.Componentes;

public class ConsultaClie extends Componentes {

    private static final long serialVersionUID = 1L;
    private JTextField tfProcura;
    private JLabel lbNomeColuna;
    private JButton btOK, btCancelar;
    private JTable tabela;
    private TableModelClie tableModel;
    private ClienteControl controleCliente;
    private ListenerClie listener;
    private int coluna;

    public ConsultaClie(ClienteControl controleCliente) {
        this.controleCliente = controleCliente;
        this.coluna = -1;
        tableModel = new TableModelClie(controleCliente);
        initComponents();
    }

    private void initComponents() {
        setTitle("Consulta Clientes");
        setLayout(null);

        JPanel panel = getJPanel(5, 5, 698, 462);

        panel.add(getJLabel("Pesquisar Por...", 20, 30, 100, 14));

        lbNomeColuna = getJLabelVermelho(120, 30, 150, 14);
        panel.add(lbNomeColuna);

        tfProcura = getJTextField(20, 48, 485, 28);
        tfProcura.addActionListener(this);
        panel.add(tfProcura);

        btOK = getJButtonOK("Pesquisar...", 515, 47, 64, 30);
        panel.add(btOK);

        btCancelar = getJButtonCancelar("Cancelar", 585, 47, 97, 30);
        panel.add(btCancelar);

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
        tabela.getColumnModel().getColumn(9).setMinWidth(180);
        tabela.getColumnModel().getColumn(10).setMinWidth(110);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    if (listener != null) {
                        listener.exibeClie(controleCliente.getListaPosicao(tabela.getSelectedRow()));
                        dispose();
                    }
                }
            }
        });
        JTableHeader header = tabela.getTableHeader();
        header.setReorderingAllowed(false);
        header.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evento) {
                coluna = tabela.columnAtPoint(evento.getPoint());
                lbNomeColuna.setText(tabela.getColumnName(coluna));
            }
        });
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(15, 90, 650, 360);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);

        add(panel);

        setSize(715, 500);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void setListener(ListenerClie listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOK || evento.getSource() == tfProcura) {
            if ("".equals(lbNomeColuna.getText())) {
                JOptionPane.showMessageDialog(null, "Selecione uma coluna", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                if ("".equals(tfProcura.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Campo pesquisa inv�lido", "Erro", JOptionPane.ERROR_MESSAGE);
                    tfProcura.grabFocus();
                } else {
                    try {
                        if (coluna == 0 || coluna == 6) {
                            try {
                                controleCliente.getLista(coluna, "", Integer.parseInt(tfProcura.getText()));
                            } catch (NumberFormatException ex) {
                            }
                        } else {
                            controleCliente.getLista(coluna, tfProcura.getText(), 0);
                        }
                        tableModel.fireTableDataChanged();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (evento.getSource() == btCancelar) {
            tfProcura.setText("");
            lbNomeColuna.setText("");
            controleCliente.limparLista();
            try {
                controleCliente.listarClie();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            this.coluna = -1;
            tableModel.fireTableDataChanged();
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        tfProcura.setBackground(Color.YELLOW);
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfProcura.setBackground(Color.WHITE);
    }
}

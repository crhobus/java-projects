package View.Endereco.Cidade;

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

import Control.Endereco.Cidade.CidadeControl;
import Control.Endereco.Cidade.ListenerCidade;
import View.Componentes;

public class ConsultaCidade extends Componentes {

    private static final long serialVersionUID = 1L;
    private JTextField tfProcura;
    private JLabel lbNomeColuna;
    private JButton btOK, btCancelar;
    private JTable tabela;
    private TableModelCidade tableModel;
    private CidadeControl controleCidade;
    private ListenerCidade listener;
    private int coluna;

    public ConsultaCidade(CidadeControl controleCidade) {
        this.controleCidade = controleCidade;
        this.coluna = -1;
        tableModel = new TableModelCidade(controleCidade);
        initComponents();
    }

    private void initComponents() {
        setTitle("Consulta Cidades");
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
        tabela.getColumnModel().getColumn(1).setMinWidth(320);
        tabela.getColumnModel().getColumn(2).setMinWidth(232);
        tabela.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    if (listener != null) {
                        listener.exibeCidade(controleCidade.getListaPosicao(tabela.getSelectedRow()));
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

    public void setListener(ListenerCidade listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOK || evento.getSource() == tfProcura) {
            if ("".equals(lbNomeColuna.getText())) {
                JOptionPane.showMessageDialog(null, "Selecione uma coluna", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                if ("".equals(tfProcura.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Campo pesquisa inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                    tfProcura.grabFocus();
                } else {
                    try {
                        if (coluna == 0) {
                            try {
                                controleCidade.getLista(coluna, "", Integer.parseInt(tfProcura.getText()));
                            } catch (NumberFormatException ex) {
                            }
                        } else {
                            controleCidade.getLista(coluna, tfProcura.getText(), 0);
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
            controleCidade.limparLista();
            try {
                controleCidade.listarCidades();
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

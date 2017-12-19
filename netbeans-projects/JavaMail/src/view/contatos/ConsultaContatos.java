package view.contatos;

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

import control.ContatosDAO;

import view.PanelComponentes;

//Classe que realiza a consulta ao banco de dados
public class ConsultaContatos extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfProcura;
    private JLabel lbNomeColuna;
    private JButton btOK, btCancelar;
    private JTable tabela;// Tabela de dados
    private ContatosTableModel tableModel;// Modelo da tabela
    private ContatosDAO contatosDAO;// Objeto que conecta as configurações de conexão ao banco de dados
    private ListenerContatos listener;
    private int coluna;

    public ConsultaContatos(ContatosDAO contatosDAO) {
        this.contatosDAO = contatosDAO;
        this.coluna = -1;
        tableModel = new ContatosTableModel(contatosDAO);
        initComponents();
    }

    // Cria os componentes gráficos
    private void initComponents() {
        setTitle("Consulta Contatos");
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
        tabela.getColumnModel().getColumn(0).setMinWidth(250);
        tabela.getColumnModel().getColumn(1).setMinWidth(382);
        tabela.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {// No segundo click do mouse sobre uma linha obtém o contato selecionado e fecha esta tela de diálogo, para ser exibido na tela de cadastro
                    if (listener != null) {
                        listener.exibeContato(contatosDAO.getContatoMapa(tabela.getSelectedRow()));
                        dispose();
                    }
                }
            }
        });
        JTableHeader header = tabela.getTableHeader();
        header.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evento) {
                // Quando clicado sobre uma coluna exibe o nome dela em uma label
                coluna = tabela.columnAtPoint(evento.getPoint());
                lbNomeColuna.setText(tabela.getColumnName(coluna));
            }
        });
        header.setReorderingAllowed(false);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(15, 90, 650, 360);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);// Dixa a barra de rolagem sempre visível na vertical
        panel.add(scroll);

        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void setListener(ListenerContatos listener) {
        this.listener = listener;
    }

    // Trata eventos
    @Override
    public void actionPerformed(ActionEvent evento) {
        // Exibe a consulta
        if (evento.getSource() == btOK || evento.getSource() == tfProcura) {
            if ("".equals(lbNomeColuna.getText())) {
                JOptionPane.showMessageDialog(null, "Selecione uma coluna", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                if ("".equals(tfProcura.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Campo pesquisa inválido, entre com a respectiva pesquisa", "Erro", JOptionPane.ERROR_MESSAGE);
                    tfProcura.grabFocus();
                } else {
                    try {
                        // Filtra do banco de dados e exibe na tela conforme condição especificada no textfield e coluna
                        contatosDAO.listContatosCondicao(coluna, tfProcura.getText());
                        tableModel.fireTableDataChanged();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        // Retorna ao estado inicial
        if (evento.getSource() == btCancelar) {
            tfProcura.setText("");
            lbNomeColuna.setText("");
            contatosDAO.limparMapa();
            try {
                contatosDAO.listContatos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            this.coluna = -1;
            tableModel.fireTableDataChanged();
        }
    }
}

package Setor;

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
import javax.swing.table.JTableHeader;

import FormatacaoCampos.CriarObjGrafico;

public class PesquisaSetor extends JDialog implements ActionListener {

    private JTextField tfPesquisa;
    private JLabel lbRecebe;
    private JButton btOK;
    private JTable tabela;
    private TableModel tableModel;
    private Renderizadora rendener;
    private String texto = "", pesquisa = "";

    public PesquisaSetor(SetorControl controle) {
        tableModel = new TableModel(controle);
        rendener = new Renderizadora(texto, pesquisa);
        initComponents(controle);
    }

    private void initComponents(SetorControl controle) {
        setTitle("Pesquisa Setor");
        setLayout(null);
        JPanel panel = CriarObjGrafico.criarJPanel(5, 5, 482, 340);
        add(panel);

        JLabel lbPesquisa = CriarObjGrafico.criarJLabel("Pesquisar Por...", 20, 30, 100, 14);
        lbPesquisa.setForeground(Color.BLUE);
        panel.add(lbPesquisa);

        lbRecebe = CriarObjGrafico.criarJLabel("Codigo", 150, 30, 100, 14);
        lbRecebe.setForeground(Color.RED);
        panel.add(lbRecebe);

        tfPesquisa = CriarObjGrafico.criarJTextField(20, 55, 380, 20);
        tfPesquisa.addActionListener(this);
        panel.add(tfPesquisa);

        btOK = CriarObjGrafico.criarJButton("OK", 405, 52, 51, 26);
        btOK.addActionListener(this);
        panel.add(btOK);

        tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(0).setMinWidth(100);
        tabela.getColumnModel().getColumn(1).setMinWidth(350);
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

        setSize(500, 380);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if ("".equals(tfPesquisa.getText())) {
            JOptionPane.showMessageDialog(null, "Campo pesquisa invalido", "Erro", JOptionPane.ERROR_MESSAGE);
            tfPesquisa.grabFocus();
        } else {
            texto = tfPesquisa.getText();
            pesquisa = lbRecebe.getText();
            rendener = new Renderizadora(texto, pesquisa);
            for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
                tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
            }
            tabela.repaint();
        }
    }
}

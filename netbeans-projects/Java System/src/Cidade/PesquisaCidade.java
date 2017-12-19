package Cidade;

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

public class PesquisaCidade extends JDialog implements ActionListener {

    private JTextField tfPesquisa;
    private JLabel lbRecebe;
    private JButton btOK;
    private JTable tabela;
    private TableModel tableModel;
    private Renderizadora rendener;
    private String texto = "", pesquisa = "";

    public PesquisaCidade(CidadeControl controle) {
        tableModel = new TableModel(controle);
        rendener = new Renderizadora(texto, pesquisa);
        initComponents(controle);
    }

    private void initComponents(CidadeControl controle) {
        setTitle("Pesquisa Cidade");
        setLayout(null);
        JPanel panel1 = CriarObjGrafico.criarJPanel(5, 5, 482, 340);
        add(panel1);

        JLabel lbPesquisa = CriarObjGrafico.criarJLabel("Pesquisar Por...", 20, 30, 100, 14);
        lbPesquisa.setForeground(Color.BLUE);
        panel1.add(lbPesquisa);

        lbRecebe = CriarObjGrafico.criarJLabel("Codigo", 150, 30, 100, 14);
        lbRecebe.setForeground(Color.RED);
        panel1.add(lbRecebe);

        tfPesquisa = CriarObjGrafico.criarJTextField(20, 55, 380, 20);
        tfPesquisa.addActionListener(this);
        panel1.add(tfPesquisa);

        btOK = CriarObjGrafico.criarJButton("OK", 405, 52, 51, 26);
        btOK.addActionListener(this);
        panel1.add(btOK);

        tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(0).setMinWidth(50);
        tabela.getColumnModel().getColumn(1).setMinWidth(200);
        tabela.getColumnModel().getColumn(2).setMinWidth(172);
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
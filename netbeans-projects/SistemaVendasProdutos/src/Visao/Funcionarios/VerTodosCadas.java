package Visao.Funcionarios;

import Controle.FuncionarioControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class VerTodosCadas extends JDialog implements ActionListener {

    private JTextField tfPesquisa;
    private JLabel lbRecebe;
    private JButton btOk;
    private TableModel tableModel;
    private Renderizadora rendener;
    private String texto = "", pesquisa = "";
    private JTable tabela;

    public VerTodosCadas(FuncionarioControl controle) {
        setTitle("Funcionários Cadastrados");
        Container tela = getContentPane();
        tela.setLayout(null);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(5, 5, 450, 295);
        tela.add(painel);
        painel.setBorder(BorderFactory.createTitledBorder("Funcionários"));
        tableModel = new TableModel(controle);
        rendener = new Renderizadora(getTexto(), getPesquisa());

        JLabel lbPesquisa = new JLabel("Pesquisar Por...");
        lbPesquisa.setBounds(20, 30, 100, 14);
        lbPesquisa.setForeground(Color.BLUE);//Cor Azul no lbPesquisa
        painel.add(lbPesquisa);

        lbRecebe = new JLabel("Codigo");
        lbRecebe.setBounds(150, 30, 100, 14);
        lbRecebe.setForeground(Color.RED);
        painel.add(lbRecebe);

        tfPesquisa = new JTextField();
        tfPesquisa.setBounds(20, 55, 340, 20);
        painel.add(tfPesquisa);
        tfPesquisa.addActionListener(this);

        btOk = new JButton("OK");
        btOk.setBounds(370, 52, 51, 26);
        painel.add(btOk);
        btOk.addActionListener(this);

        tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(0).setMinWidth(50);
        tabela.getColumnModel().getColumn(1).setMinWidth(190);
        tabela.getColumnModel().getColumn(2).setMinWidth(92);
        tabela.getColumnModel().getColumn(3).setMinWidth(150);
        tabela.getColumnModel().getColumn(4).setMinWidth(100);
        tabela.getColumnModel().getColumn(5).setMinWidth(100);
        tabela.getColumnModel().getColumn(6).setMinWidth(80);
        tabela.getColumnModel().getColumn(7).setMinWidth(180);
        tabela.getColumnModel().getColumn(9).setMinWidth(120);
        tabela.getColumnModel().getColumn(11).setMinWidth(115);
        tabela.getColumnModel().getColumn(12).setMinWidth(115);
        tabela.getColumnModel().getColumn(13).setMinWidth(115);
        tabela.getColumnModel().getColumn(14).setMinWidth(110);
        tabela.getColumnModel().getColumn(15).setMinWidth(110);
        tabela.getColumnModel().getColumn(16).setMinWidth(115);
        tabela.getColumnModel().getColumn(17).setMinWidth(105);
        tabela.getColumnModel().getColumn(18).setMinWidth(200);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JTableHeader header = tabela.getTableHeader();
        header.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent evento) {
                        int coluna = tabela.columnAtPoint(evento.getPoint());
                        lbRecebe.setText(tabela.getColumnName(coluna));
                    }
                });
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 100, 416, 185);
        painel.add(scroll);

        setResizable(false);
        setSize(480, 345);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent evento) {
        if ("".equalsIgnoreCase(tfPesquisa.getText())) {
            JOptionPane.showMessageDialog(null, "Campo pesquisa invalido", "Erro", JOptionPane.ERROR_MESSAGE);
            tfPesquisa.grabFocus();
        } else {
            setTexto(tfPesquisa.getText());
            setPesquisa(lbRecebe.getText());
            rendener = new Renderizadora(getTexto(), getPesquisa());
            for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
                tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
            }
            tabela.repaint();
        }
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}

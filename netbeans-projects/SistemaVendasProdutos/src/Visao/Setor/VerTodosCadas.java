package Visao.Setor;

import Controle.SetorControl;
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
    private String nome = "", pesquisa = "";
    private JTable tabela;

    public VerTodosCadas(SetorControl controle) {
        setTitle("Setores Cadastrados");
        Container tela = getContentPane();
        tela.setLayout(null);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(5, 5, 450, 295);
        tela.add(painel);
        painel.setBorder(BorderFactory.createTitledBorder("Setores"));
        tableModel = new TableModel(controle);
        rendener = new Renderizadora(getNome(), getPesquisa());

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
            setNome(tfPesquisa.getText());
            setPesquisa(lbRecebe.getText());
            rendener = new Renderizadora(getNome(), getPesquisa());
            for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
                tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
            }
            tabela.repaint();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
}

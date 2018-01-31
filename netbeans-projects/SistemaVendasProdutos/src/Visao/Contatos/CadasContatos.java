package Visao.Contatos;

import Arquivos.*;
import Controle.ContatosControl;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;

public class CadasContatos extends JDialog implements ActionListener {

    private JButton btAdicionar, btOk;
    private JLabel lbRecebe;
    private JTextField tfPesquisa;
    private JTable tabela;
    private ContatosControl control;
    private TableModel tableModel;
    private Renderizadora rendener;
    private String texto = "", pesquisa = "";
    private Arquivo arquivo;
    private LerArquivo lerArquivo;

    public CadasContatos() {
        setTitle("Contatos");
        Container tela = getContentPane();
        tela.setLayout(null);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(10, 10, 865, 594);
        tela.add(painel);
        painel.setBorder(BorderFactory.createTitledBorder("Contatos"));
        arquivo = new Arquivo();
        lerArquivo = new LerArquivo();
        control = new ContatosControl(arquivo);
        tableModel = new TableModel(control);
        rendener = new Renderizadora(getTexto(), getPesquisa());
        control.lerArquivo(lerArquivo);

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

        btAdicionar = new JButton("Adicionar Contato");
        btAdicionar.setBounds(435, 52, 135, 26);
        painel.add(btAdicionar);
        btAdicionar.addActionListener(this);

        tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(0).setCellEditor(new EditorSelecao(control));
        tabela.getColumnModel().getColumn(3).setCellEditor(new EditorVip());
        try {
            tabela.getColumnModel().getColumn(4).setCellEditor(new EditorFone1());
            tabela.getColumnModel().getColumn(5).setCellEditor(new EditorFone2());
            tabela.getColumnModel().getColumn(6).setCellEditor(new EditorFone3());
        } catch (ParseException ex) {
        }
        tabela.getColumnModel().getColumn(8).setCellEditor(new EditorRemover(control));
        tabela.setRowHeight(30);
        tabela.getColumnModel().getColumn(0).setMinWidth(5);
        tabela.getColumnModel().getColumn(1).setMinWidth(40);
        tabela.getColumnModel().getColumn(2).setMinWidth(220);
        tabela.getColumnModel().getColumn(3).setMinWidth(100);
        tabela.getColumnModel().getColumn(4).setMinWidth(98);
        tabela.getColumnModel().getColumn(5).setMinWidth(98);
        tabela.getColumnModel().getColumn(6).setMinWidth(98);
        tabela.getColumnModel().getColumn(7).setMinWidth(300);
        tabela.getColumnModel().getColumn(8).setMinWidth(50);
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
        scroll.setBounds(10, 90, 845, 490);
        painel.add(scroll);

        setResizable(false);
        setSize(900, 640);
        setLocationRelativeTo(null);//Posiciona no centro da tela
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btAdicionar) {
            tableModel.adicionar();
            tableModel.fireTableDataChanged();
        }
        if (evento.getSource() == btOk || evento.getSource() == tfPesquisa) {
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

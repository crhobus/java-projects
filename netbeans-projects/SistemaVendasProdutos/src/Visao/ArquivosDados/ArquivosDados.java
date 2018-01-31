package Visao.ArquivosDados;

import java.awt.*;
import javax.swing.*;

public class ArquivosDados extends JDialog {

    private TableModel tableModel;
    private Renderizadora rendener;

    public ArquivosDados() {
        setTitle("Arquivos de Armazenamento");
        Container tela = getContentPane();
        tela.setLayout(null);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(5, 5, 550, 360);
        tela.add(painel);
        painel.setBorder(BorderFactory.createTitledBorder("Dados"));
        tableModel = new TableModel();
        tableModel.adicionar("C:/Users/Caio/Documents/NetBeansProjects/Sistema Vendas produtos/Dados/");
        rendener = new Renderizadora();

        JLabel lbDiretorio = new JLabel("Diretório:");
        lbDiretorio.setBounds(10, 30, 100, 14);
        painel.add(lbDiretorio);

        JTextField tfCampo = new JTextField("C:/Users/Caio/Documents/NetBeansProjects/Sistema Vendas produtos/Dados/");
        tfCampo.setBounds(70, 28, 450, 20);
        tfCampo.setEditable(false);
        tfCampo.setBackground(Color.WHITE);
        painel.add(tfCampo);

        JTable tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(0).setMinWidth(10);
        tabela.getColumnModel().getColumn(1).setMinWidth(140);
        tabela.getColumnModel().getColumn(2).setMinWidth(100);
        tabela.getColumnModel().getColumn(3).setMinWidth(100);
        tabela.getColumnModel().getColumn(4).setMinWidth(10);
        tabela.getColumnModel().getColumn(5).setMinWidth(10);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(11, 60, 530, 290);
        painel.add(scroll);

        setResizable(false);
        setSize(570, 402);
        setLocationRelativeTo(null);
    }
}

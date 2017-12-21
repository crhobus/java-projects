package CaioRenanHobus.Visao;

import CaioRenanHobus.Controle.*;
import java.awt.*;
import javax.swing.*;

public class CadastroProduto extends JFrame {

    private TableModel tableModel;
    private ProdutoControl control;
    private Renderizadora rendener;
    private JTable tabela;

    public CadastroProduto() {
        super("Cadastro Produtos");
        Container tela = getContentPane();
        tela.setLayout(null);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        tela.add(painel);
        painel.setBounds(10, 10, 435, 330);
        painel.setBorder(BorderFactory.createTitledBorder("Produtos"));
        control = new ProdutoControl();
        tableModel = new TableModel(control);
        rendener = new Renderizadora();

        tabela = new JTable(tableModel);
        for (int y = 0; y < tabela.getColumnModel().getColumnCount(); y++) {
            tabela.getColumnModel().getColumn(y).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(0).setCellEditor(new EditorNome(control));
        tabela.getColumnModel().getColumn(1).setCellEditor(new EditorQuantidade());
        tabela.getColumnModel().getColumn(2).setCellEditor(new EditorUnidade());
        tabela.getColumnModel().getColumn(3).setCellEditor(new EditorAdicionar(control));
        tabela.getColumnModel().getColumn(4).setCellEditor(new EditorRemover(control));
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(10, 20, 416, 300);
        painel.add(scroll);

        tabela.setRowHeight(26);
        tabela.getColumnModel().getColumn(0).setMinWidth(120);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(115);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(70);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(463, 380);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

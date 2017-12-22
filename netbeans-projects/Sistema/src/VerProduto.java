
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.*;
import java.io.*;

public class VerProduto extends JDialog {

    private ArrayList<ArrayProdutos> lista = new ArrayList();
    private final DefaultTableModel Modelo;
    private JTable Tabela;
    private JTextField tf_Pesquisa;
    private JLabel lb_Recebe;
    private JButton bt_OK;

    VerProduto() {
        setTitle("Pesquisa Produtos");
        Container tela = getContentPane();
        tela.setLayout(null);
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(5, 5, 787, 305);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("Pesquisa Protudo"));
        TrataEventos trata = new TrataEventos();
        LerArquivo();

        JLabel lb_Pesquisa = new JLabel("Pesquisar Por...");
        lb_Pesquisa.setBounds(20, 30, 100, 14);
        lb_Pesquisa.setForeground(Color.BLUE);//Cor Azul no lb_Pesquisa
        Painel1.add(lb_Pesquisa);

        lb_Recebe = new JLabel("Codigo");
        lb_Recebe.setBounds(150, 30, 100, 14);
        lb_Recebe.setForeground(Color.RED);
        Painel1.add(lb_Recebe);

        tf_Pesquisa = new JTextField();
        tf_Pesquisa.setBounds(20, 55, 340, 20);
        Painel1.add(tf_Pesquisa);

        bt_OK = new JButton("OK");
        bt_OK.setBounds(370, 52, 51, 26);
        Painel1.add(bt_OK);
        bt_OK.addActionListener(trata);

        Modelo = new DefaultTableModel();
        Tabela = new JTable(Modelo);//Constrói a tabela
        Modelo.addColumn("Codigo");//Cria colunas
        Modelo.addColumn("Nome");
        Modelo.addColumn("Descrição");
        Modelo.addColumn("Núm. Série");
        Modelo.addColumn("Modelo");
        Modelo.addColumn("Quantidade");
        Modelo.addColumn("Valor Unitario");
        Modelo.addColumn("IPI");
        Modelo.addColumn("Descontos");
        Modelo.addColumn("Valor Total");
        JScrollPane rolagem = new JScrollPane(Tabela);
        rolagem.setBounds(5, 90, 776, 200);
        Painel1.add(rolagem);

        RecuperarProdutos();
        setSize(804, 345);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);
        setVisible(true);
    }

    private void OK() {
        int Num = 0;
        if ("".equals(tf_Pesquisa.getText())) {
            JOptionPane.showMessageDialog(null, "Campo Pesquisa esta em branco", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            if (lista.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "Não há Produtos cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                String Texto = tf_Pesquisa.getText();
                Num = Integer.parseInt(Texto);
                boolean encontrado = false;
                for (int i = 0; i < lista.size(); i++) {
                    ArrayProdutos aux = lista.get(i);
                    if (Num == aux.getCodigo()) {
                        Num = aux.getCodigo();
                        encontrado = true;
                    }
                }
                if (encontrado == true) {
                    //Tabela.setValueAt(Num, 2, 0);
                    for (int y = 0; y < Modelo.getRowCount(); y++) {
                    //int aux = Tabela.getValueAt(Num, 0);
                    // int a = Integer.toHexString(aux);
                    //while (Num < Modelo.getRowCount()) {
                        //if (y == Tabela.getValueAt(Modelo.getRowCount(), Modelo.getColumnCount())) {
                            Tabela.getSelectionModel().setSelectionInterval(Num, Num);//Seleciona linha na tabela
                            //Num ++;
                        //}
                        //Num++;    
                    }
                    /*for (int linhas = 0; linhas < Tabela.getRowCount(); linhas ++ ) {
                    for ( int colunas = 0; colunas < Tabela.getColumnCount(); colunas++ ) {
                    Tabela.getSelectionModel().setSelectionInterval(Num, Num);//Seleciona linha na tabela
                    ///String celula = getValueAt(Num, Num).toString().trim();
                    //int index = celula.indexOf(sword);
                    }
                    }*/
                }
            }
        }
    }
    /*String Texto = tf_Pesquisa.getText();
    Num = Integer.parseInt(Texto);

    Tabela.getValueAt(Num, Num);
    int linhaSel = Tabela.getSelectedRow();
    int colunaSel = Tabela.getSelectedColumn();

    for (int y = 0; y < Modelo.getRowCount(); y++) {
    Tabela.getSelectionModel().setSelectionInterval(1, 1);//Seleciona linha na tabela
    }
    }*/

    /*private void Quem() {
    int Codigo = 0;
    if (lista.isEmpty() == true) {
    JOptionPane.showMessageDialog(null, "Não há Clientes cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
    } else {
    String Texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Cliente", JOptionPane.WARNING_MESSAGE);
    boolean encontrado = false;
    for (int i = 0; i < lista.size(); i++) {
    ArrayCliente aux = lista.get(i);
    if (Texto.equalsIgnoreCase(aux.getNome())) {
    Codigo = aux.getCodigo();
    encontrado = true;
    }
    }
    if (encontrado == true) {
    JOptionPane.showMessageDialog(null, "O cliente " + Texto + " esta inserido com o codigo " + Codigo, "Cliente", JOptionPane.WARNING_MESSAGE);
    } else {
    JOptionPane.showMessageDialog(null, "O cliente " + Texto + " não esta inserido", "Cliente", JOptionPane.WARNING_MESSAGE);
    }
    }
    }*/
    private void RecuperarProdutos() {
        for (int i = 0; i < lista.size(); i++) {
            ArrayProdutos n = lista.get(i);
            Modelo.addRow(new Object[]{n.getCodigo(), n.getNome(), n.getDescricao(), n.getNumero_Serie(), n.getModelo(), n.getQuantidade(), n.getValor_Unitario(), n.getIPI(), n.getDescontos(), n.getValor_Total()});//Adiciona linha na tabela
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Produto.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista = (ArrayList<ArrayProdutos>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == bt_OK) {
                OK();
            }
        }
    }
}

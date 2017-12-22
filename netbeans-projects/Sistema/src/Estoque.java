
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.table.*;

public class Estoque extends JFrame {

    private ArrayList<ArrayProdutos> lista = new ArrayList();
    private JTextField tf_Codigo, tf_Nome, tf_Descricao, tf_Modelo, tf_Quantidade, tf_ValorUnitario, tf_Total, tf_QuantidadeTotal, tf_ValorTotal;
    private final DefaultTableModel Modelo;
    private JButton bt_PesquisaAvan, bt_Ok, bt_Cancelar;
    private JRadioButton Novos, Assistencia;
    private int INTNovos = 0, INTAssistencias = 0;

    Estoque() {
        super("Estoque");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(10, 10, 430, 200);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("Estoque"));
        JPanel Painel2 = new JPanel();
        Painel2.setLayout(null);
        Painel2.setBounds(10, 207, 585, 230);
        tela.add(Painel2);
        Painel2.setBorder(BorderFactory.createTitledBorder("Detalhes"));
        TrataEventos trata = new TrataEventos();
        Manipula manuseia = new Manipula();
        TrataCores cores = new TrataCores();
        LerArquivoProduto();

        JLabel lb_Codigo = new JLabel("Codigo");
        lb_Codigo.setBounds(20, 40, 80, 14);
        lb_Codigo.setFont(fonte);
        Painel1.add(lb_Codigo);

        tf_Codigo = new JTextField();
        tf_Codigo.setBounds(20, 58, 80, 20);
        tf_Codigo.setDocument(new MeuDocument());
        Painel1.add(tf_Codigo);
        tf_Codigo.addFocusListener(cores);

        JLabel lb_Nome = new JLabel("Nome");
        lb_Nome.setBounds(112, 40, 80, 14);
        lb_Nome.setFont(fonte);
        Painel1.add(lb_Nome);

        tf_Nome = new JTextField();
        tf_Nome.setBounds(112, 58, 270, 20);
        tf_Nome.setEditable(false);
        tf_Nome.setBackground(cor);
        Painel1.add(tf_Nome);
        tf_Nome.addFocusListener(cores);

        JLabel lb_Descricao = new JLabel("Descrição");
        lb_Descricao.setBounds(20, 78, 90, 14);
        lb_Descricao.setFont(fonte);
        Painel1.add(lb_Descricao);

        tf_Descricao = new JTextField();
        tf_Descricao.setBounds(20, 94, 110, 20);
        tf_Descricao.setEditable(false);
        tf_Descricao.setBackground(cor);
        Painel1.add(tf_Descricao);
        tf_Descricao.addFocusListener(cores);

        JLabel lb_Modelo = new JLabel("Modelo");
        lb_Modelo.setBounds(140, 78, 80, 14);
        lb_Modelo.setFont(fonte);
        Painel1.add(lb_Modelo);

        tf_Modelo = new JTextField();
        tf_Modelo.setBounds(140, 94, 110, 20);
        tf_Modelo.setEditable(false);
        tf_Modelo.setBackground(cor);
        Painel1.add(tf_Modelo);
        tf_Modelo.addFocusListener(cores);

        JLabel lb_Quantidade = new JLabel("Quantidade");
        lb_Quantidade.setBounds(260, 78, 80, 14);
        lb_Quantidade.setFont(fonte);
        Painel1.add(lb_Quantidade);

        tf_Quantidade = new JTextField();
        tf_Quantidade.setBounds(260, 94, 123, 20);
        tf_Quantidade.setEditable(false);
        tf_Quantidade.setBackground(cor);
        Painel1.add(tf_Quantidade);
        tf_Quantidade.addFocusListener(cores);

        JLabel lb_ValorUnitario = new JLabel("Valor Unitário");
        lb_ValorUnitario.setBounds(20, 114, 80, 14);
        lb_ValorUnitario.setFont(fonte);
        Painel1.add(lb_ValorUnitario);

        tf_ValorUnitario = new JTextField();
        tf_ValorUnitario.setBounds(20, 130, 110, 20);
        tf_ValorUnitario.setEditable(false);
        tf_ValorUnitario.setBackground(cor);
        Painel1.add(tf_ValorUnitario);
        tf_ValorUnitario.addFocusListener(cores);

        JLabel lb_Total = new JLabel("Total");
        lb_Total.setBounds(140, 114, 110, 14);
        lb_Total.setFont(fonte);
        Painel1.add(lb_Total);

        tf_Total = new JTextField();
        tf_Total.setBounds(140, 130, 110, 20);
        tf_Total.setEditable(false);
        tf_Total.setBackground(cor);
        Painel1.add(tf_Total);
        tf_Total.addFocusListener(cores);

        JLabel lb_Quantidade_Total = new JLabel("Quantidade Total");
        lb_Quantidade_Total.setBounds(260, 114, 120, 14);
        lb_Quantidade_Total.setFont(fonte);
        Painel1.add(lb_Quantidade_Total);

        tf_QuantidadeTotal = new JTextField();
        tf_QuantidadeTotal.setBounds(260, 130, 120, 20);
        tf_QuantidadeTotal.setEditable(false);
        tf_QuantidadeTotal.setBackground(cor);
        Painel1.add(tf_QuantidadeTotal);
        tf_QuantidadeTotal.addFocusListener(cores);

        JLabel lb_ValorTotal = new JLabel("Valor Total");
        lb_ValorTotal.setBounds(20, 150, 80, 14);
        lb_ValorTotal.setFont(fonte);
        Painel1.add(lb_ValorTotal);

        tf_ValorTotal = new JTextField();
        tf_ValorTotal.setBounds(20, 166, 110, 20);
        tf_ValorTotal.setEditable(false);
        tf_ValorTotal.setBackground(cor);
        Painel1.add(tf_ValorTotal);
        tf_ValorTotal.addFocusListener(cores);

        Modelo = new DefaultTableModel();
        JTable Tabela = new JTable(Modelo);//Constrói a tabela
        Modelo.addColumn("Codigo");//Cria colunas
        Modelo.addColumn("Produto");
        Modelo.addColumn("Descrição");
        Modelo.addColumn("Núm. Série");
        Modelo.addColumn("Modelo");
        Modelo.addColumn("Quantidade");
        Modelo.addColumn("Total");
        JScrollPane rolagem = new JScrollPane(Tabela);
        rolagem.setBounds(5, 25, 576, 194);
        Painel2.add(rolagem);

        bt_PesquisaAvan = new JButton("Busca Avançada");
        bt_PesquisaAvan.setBounds(455, 25, 129, 26);
        tela.add(bt_PesquisaAvan);
        bt_PesquisaAvan.addActionListener(trata);

        Novos = new JRadioButton("Novo");
        Novos.setBounds(458, 65, 80, 20);
        Novos.setFont(fonte);
        tela.add(Novos);
        Novos.addItemListener(manuseia);

        Assistencia = new JRadioButton("Assistência");
        Assistencia.setBounds(458, 87, 110, 20);
        Assistencia.setFont(fonte);
        tela.add(Assistencia);
        Assistencia.addItemListener(manuseia);

        bt_Ok = new JButton("OK");
        bt_Ok.setBounds(455, 130, 100, 26);
        tela.add(bt_Ok);
        bt_Ok.addActionListener(trata);

        bt_Cancelar = new JButton("Cancelar");
        bt_Cancelar.setBounds(455, 175, 100, 26);
        tela.add(bt_Cancelar);
        bt_Cancelar.addActionListener(trata);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(Painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        Painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(610, 485);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    private void LimparTela() {
        tf_Codigo.setText("");
        tf_Nome.setText("");
        tf_Descricao.setText("");
        tf_Modelo.setText("");
        tf_Quantidade.setText("");
        tf_ValorUnitario.setText("");
        tf_Total.setText("");
        tf_QuantidadeTotal.setText("");
        tf_ValorTotal.setText("");
        Novos.setSelected(false);
        Assistencia.setSelected(false);
        INTNovos = 0;
        INTAssistencias = 0;
        if (Modelo.getRowCount() == 0) {//Verifica se há linhas na tabela
        } else {
            if (Modelo.getRowCount() >= 1) {
                for (int i = 0; i <= Modelo.getRowCount(); i++) {//Contador
                    Modelo.setNumRows(0);//Exclui Todas as linhas
                }
            }
        }
    }

    private void ProdutoNovo() {
        if (!"".equals(tf_Nome.getText())) {
            String Texto = tf_Codigo.getText();
            LimparTela();
            tf_Codigo.setText(Texto);
            RecuperarProdutos();
        } else {
            RecuperarProdutos();
        }
    }

    private void PesquisaAvancada() {
        int Codigo = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Produtos cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Produtos", JOptionPane.WARNING_MESSAGE);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayProdutos aux = lista.get(i);
                if (Texto.equalsIgnoreCase(aux.getNome())) {//Não importa se texto digitado esta em maiusculo ou minusculo
                    Codigo = aux.getCodigo();
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "O Produto " + Texto + " esta inserido com o codigo " + Codigo, "Produto", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "O Produto " + Texto + " não esta inserido", "Produto", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void RecuperarProdutos() {
        if ("".equals(tf_Codigo.getText())) {
            JOptionPane.showMessageDialog(null, "Campo Código esta em branco", "Consulta Vendas", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!"".equals(tf_Codigo.getText())) {
                if (lista.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Não há Produtos cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    String Texto = tf_Codigo.getText();
                    int n = Integer.parseInt(Texto);//Conversão String para Integer
                    for (int i = 0; i < lista.size(); i++) {
                        ArrayProdutos aux = lista.get(i);
                        if (n == (aux.getCodigo())) {
                            String TextoNome = aux.getNome();
                            tf_Nome.setText(TextoNome);
                            String TextoDescricao = aux.getDescricao();
                            tf_Descricao.setText(TextoDescricao);
                            String TextoModelo = aux.getModelo();
                            tf_Modelo.setText(TextoModelo);
                            float TextoFLOATValorUnitario = aux.getValor_Total();
                            String TextoValorUnitario = Float.toString(TextoFLOATValorUnitario);//Conversão float para String
                            tf_ValorUnitario.setText(TextoValorUnitario);
                            RecuperarTodosProdutos();
                            QuantidadeTotal();
                            ValorTotal();
                            Quantidade(aux.getNome(), aux.getDescricao());
                        }
                    }
                }
            }
        }
    }

    private void RecuperarTodosProdutos() {
        for (int i = 0; i < lista.size(); i++) {
            ArrayProdutos aux = lista.get(i);
            Modelo.addRow(new Object[]{aux.getCodigo(), aux.getNome(), aux.getDescricao(), aux.getNumero_Serie(), aux.getModelo(), aux.getQuantidade(), aux.getValor_Total()});//Adiciona linha na tabela
        }
    }

    private void QuantidadeTotal() {
        int soma = 0;
        for (int i = 0; i < lista.size(); i++) {
            ArrayProdutos aux = lista.get(i);
            soma = soma + aux.getQuantidade();
        }
        String TextoSoma = Integer.toString(soma);//Converção int para String
        tf_QuantidadeTotal.setText(TextoSoma);
    }

    private void ValorTotal() {
        float soma = 0;
        for (int i = 0; i < lista.size(); i++) {
            ArrayProdutos aux = lista.get(i);
            soma = soma + aux.getValor_Total();
        }
        String TextoSoma = Float.toString(soma);//Converção float para String
        tf_ValorTotal.setText(TextoSoma);
    }

    private void Quantidade(String nome, String descricao) {
        int SomaQuan = 0;
        float SomaValor = 0;
        for (int i = 0; i < lista.size(); i++) {
            ArrayProdutos aux = lista.get(i);
            if (aux.getNome().equalsIgnoreCase(nome) & aux.getDescricao().equalsIgnoreCase(descricao)) {
                SomaQuan = SomaQuan + aux.getQuantidade();
                SomaValor = SomaValor + aux.getValor_Total();
            }
        }
        String TextoSomaQuan = Integer.toString(SomaQuan);//Converção int para String
        tf_Quantidade.setText(TextoSomaQuan);
        String TextoSomaValor = Float.toString(SomaValor);//Converção float para String
        tf_Total.setText(TextoSomaValor);
    }

    private void LerArquivoProduto() {
        try {
            FileInputStream Arq = new FileInputStream("Produto.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista = (ArrayList<ArrayProdutos>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Manipula implements ItemListener {

        public void itemStateChanged(ItemEvent evento) {
            if (evento.getSource() == Novos) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    INTNovos = 1;
                    INTAssistencias = 0;
                    Assistencia.setSelected(false);
                }
            }
            if (evento.getSource() == Assistencia) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    INTNovos = 0;
                    INTAssistencias = 1;
                    Novos.setSelected(false);
                }
            }
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == bt_PesquisaAvan) {
                PesquisaAvancada();
            }
            if (evento.getSource() == bt_Ok) {
                if (Novos.isSelected() == false & Assistencia.isSelected() == false) {
                    JOptionPane.showMessageDialog(null, "Selecione uma opção", "Fornecedor", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (Novos.isSelected() == true) {//Verifica se JRadioButton esta ou não selecionado
                        ProdutoNovo();
                        INTNovos = 0;
                        INTAssistencias = 0;
                    }
                    if (Assistencia.isSelected() == true) {
                        //Recuperar();
                        INTNovos = 0;
                        INTAssistencias = 0;
                    }
                }
            }
            if (evento.getSource() == bt_Cancelar) {
                LimparTela();
            }
        }
    }

    private class TrataCores implements FocusListener {

        public void focusGained(FocusEvent evento) {//Ganha Focus
            if (evento.getSource() == tf_Codigo) {
                tf_Codigo.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Nome) {
                tf_Nome.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Descricao) {
                tf_Descricao.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Modelo) {
                tf_Modelo.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Quantidade) {
                tf_Quantidade.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_ValorUnitario) {
                tf_ValorUnitario.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Total) {
                tf_Total.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_QuantidadeTotal) {
                tf_QuantidadeTotal.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_ValorTotal) {
                tf_ValorTotal.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tf_Codigo.setBackground(Color.WHITE);
            tf_Nome.setBackground(Color.WHITE);
            tf_Descricao.setBackground(Color.WHITE);
            tf_Modelo.setBackground(Color.WHITE);
            tf_Quantidade.setBackground(Color.WHITE);
            tf_ValorUnitario.setBackground(Color.WHITE);
            tf_Total.setBackground(Color.WHITE);
            tf_QuantidadeTotal.setBackground(Color.WHITE);
            tf_ValorTotal.setBackground(Color.WHITE);
        }
    }
}

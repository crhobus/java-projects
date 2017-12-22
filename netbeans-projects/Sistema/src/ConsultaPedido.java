
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.table.*;

public class ConsultaPedido extends JFrame {

    private ArrayList<ArrayPedidos> lista1 = new ArrayList();
    private ArrayList<ArrayFornecedor> lista2 = new ArrayList();
    private ArrayList<ArrayVendedor> lista3 = new ArrayList();
    private ArrayList<ArrayItemPedido> lista4 = new ArrayList();
    private ArrayList<ArrayTransportadora> lista5 = new ArrayList();
    private JTextField tf_Codigo, tf_DataEmissao, tf_CodVendedor, tf_NomeVendedor, tf_CNPJVendedor, tf_IEVendedor, tf_Empresa, tf_EndedecoVendedor, tf_NumeroVendedor, tf_BairroVendedor, tf_CidadeVendedor, tf_EstadoVendedor, tf_ContatoVendedor, tf_CodForne, tf_NomeForne, tf_Situacao, tf_CodTransport, tf_NomeTransport, tf_Descricao;
    private JButton bt_Gerar, bt_Cancelar;
    private final DefaultTableModel Modelo;

    ConsultaPedido() {
        super("Consulta Pedidos");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(10, 10, 585, 580);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("Consulta Pedidos"));
        TrataEventos trata = new TrataEventos();
        TrataCores cores = new TrataCores();

        JLabel lb_Codigo = new JLabel("Codigo");
        lb_Codigo.setBounds(20, 52, 80, 14);
        lb_Codigo.setFont(fonte);
        Painel1.add(lb_Codigo);

        tf_Codigo = new JTextField();
        tf_Codigo.setBounds(68, 48, 110, 20);
        tf_Codigo.setDocument(new MeuDocument());
        Painel1.add(tf_Codigo);
        tf_Codigo.addFocusListener(cores);
        tf_Codigo.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                if ("".equals(tf_Codigo.getText())) {
                } else {
                    RecuperarPedido();
                }
            }
        });

        bt_Gerar = new JButton("Ok");
        bt_Gerar.setBounds(194, 46, 85, 23);
        Painel1.add(bt_Gerar);
        bt_Gerar.addActionListener(trata);

        bt_Cancelar = new JButton("Cancelar");
        bt_Cancelar.setBounds(304, 46, 85, 23);
        Painel1.add(bt_Cancelar);
        bt_Cancelar.addActionListener(trata);

        JLabel lb_DataEmissao = new JLabel("Data Emissão");
        lb_DataEmissao.setBounds(20, 95, 110, 14);
        lb_DataEmissao.setFont(fonte);
        Painel1.add(lb_DataEmissao);

        tf_DataEmissao = new JTextField();
        tf_DataEmissao.setBounds(20, 112, 120, 20);
        tf_DataEmissao.setEditable(false);
        tf_DataEmissao.setBackground(cor);
        Painel1.add(tf_DataEmissao);

        JLabel lb_CodVendedor = new JLabel("Vendedor");
        lb_CodVendedor.setBounds(160, 95, 110, 14);
        lb_CodVendedor.setFont(fonte);
        Painel1.add(lb_CodVendedor);

        tf_CodVendedor = new JTextField();
        tf_CodVendedor.setBounds(160, 112, 120, 20);
        tf_CodVendedor.setEditable(false);
        tf_CodVendedor.setBackground(cor);
        Painel1.add(tf_CodVendedor);

        JLabel lb_NomeVendedor = new JLabel("Nome");
        lb_NomeVendedor.setBounds(300, 95, 110, 14);
        lb_NomeVendedor.setFont(fonte);
        Painel1.add(lb_NomeVendedor);

        tf_NomeVendedor = new JTextField();
        tf_NomeVendedor.setBounds(300, 112, 120, 20);
        tf_NomeVendedor.setEditable(false);
        tf_NomeVendedor.setBackground(cor);
        Painel1.add(tf_NomeVendedor);

        JLabel lb_CodEmpresaVendedor = new JLabel("Empresa");
        lb_CodEmpresaVendedor.setBounds(20, 133, 110, 14);
        lb_CodEmpresaVendedor.setFont(fonte);
        Painel1.add(lb_CodEmpresaVendedor);

        tf_Empresa = new JTextField();
        tf_Empresa.setBounds(20, 150, 120, 20);
        tf_Empresa.setEditable(false);
        tf_Empresa.setBackground(cor);
        Painel1.add(tf_Empresa);

        JLabel lb_CNPJVendedor = new JLabel("CNPJ");
        lb_CNPJVendedor.setBounds(160, 133, 110, 14);
        lb_CNPJVendedor.setFont(fonte);
        Painel1.add(lb_CNPJVendedor);

        tf_CNPJVendedor = new JTextField();
        tf_CNPJVendedor.setBounds(160, 150, 120, 20);
        tf_CNPJVendedor.setEditable(false);
        tf_CNPJVendedor.setBackground(cor);
        Painel1.add(tf_CNPJVendedor);

        JLabel lb_IEVendedor = new JLabel("IE");
        lb_IEVendedor.setBounds(300, 133, 110, 14);
        lb_IEVendedor.setFont(fonte);
        Painel1.add(lb_IEVendedor);

        tf_IEVendedor = new JTextField();
        tf_IEVendedor.setBounds(300, 150, 120, 20);
        tf_IEVendedor.setEditable(false);
        tf_IEVendedor.setBackground(cor);
        Painel1.add(tf_IEVendedor);

        JLabel lb_EnderecoVendedor = new JLabel("Endereço");
        lb_EnderecoVendedor.setBounds(20, 170, 110, 14);
        lb_EnderecoVendedor.setFont(fonte);
        Painel1.add(lb_EnderecoVendedor);

        tf_EndedecoVendedor = new JTextField();
        tf_EndedecoVendedor.setBounds(20, 186, 120, 20);
        tf_EndedecoVendedor.setEditable(false);
        tf_EndedecoVendedor.setBackground(cor);
        Painel1.add(tf_EndedecoVendedor);

        JLabel lb_Bairro = new JLabel("Bairro");
        lb_Bairro.setBounds(160, 170, 110, 14);
        lb_Bairro.setFont(fonte);
        Painel1.add(lb_Bairro);

        tf_BairroVendedor = new JTextField();
        tf_BairroVendedor.setBounds(160, 186, 120, 20);
        tf_BairroVendedor.setEditable(false);
        tf_BairroVendedor.setBackground(cor);
        Painel1.add(tf_BairroVendedor);

        JLabel lb_Numero = new JLabel("Número");
        lb_Numero.setBounds(300, 170, 125, 14);
        lb_Numero.setFont(fonte);
        Painel1.add(lb_Numero);

        tf_NumeroVendedor = new JTextField();
        tf_NumeroVendedor.setBounds(300, 186, 120, 20);
        tf_NumeroVendedor.setEditable(false);
        tf_NumeroVendedor.setBackground(cor);
        Painel1.add(tf_NumeroVendedor);

        JLabel lb_CidadeVendedor = new JLabel("Cidade");
        lb_CidadeVendedor.setBounds(20, 205, 125, 14);
        lb_CidadeVendedor.setFont(fonte);
        Painel1.add(lb_CidadeVendedor);

        tf_CidadeVendedor = new JTextField();
        tf_CidadeVendedor.setBounds(20, 220, 120, 20);
        tf_CidadeVendedor.setEditable(false);
        tf_CidadeVendedor.setBackground(cor);
        Painel1.add(tf_CidadeVendedor);

        JLabel lb_EstadoVendedor = new JLabel("Estado");
        lb_EstadoVendedor.setBounds(160, 205, 110, 14);
        lb_EstadoVendedor.setFont(fonte);
        Painel1.add(lb_EstadoVendedor);

        tf_EstadoVendedor = new JTextField();
        tf_EstadoVendedor.setBounds(160, 220, 120, 20);
        tf_EstadoVendedor.setEditable(false);
        tf_EstadoVendedor.setBackground(cor);
        Painel1.add(tf_EstadoVendedor);

        JLabel lb_ContatoVendedor = new JLabel("Contato");
        lb_ContatoVendedor.setBounds(300, 205, 110, 14);
        lb_ContatoVendedor.setFont(fonte);
        Painel1.add(lb_ContatoVendedor);

        tf_ContatoVendedor = new JTextField();
        tf_ContatoVendedor.setBounds(300, 220, 120, 20);
        tf_ContatoVendedor.setEditable(false);
        tf_ContatoVendedor.setBackground(cor);
        Painel1.add(tf_ContatoVendedor);

        JLabel lb_CodForne = new JLabel("Fornecedor");
        lb_CodForne.setBounds(20, 242, 125, 14);
        lb_CodForne.setFont(fonte);
        Painel1.add(lb_CodForne);

        tf_CodForne = new JTextField();
        tf_CodForne.setBounds(20, 258, 120, 20);
        tf_CodForne.setEditable(false);
        tf_CodForne.setBackground(cor);
        Painel1.add(tf_CodForne);

        JLabel lb_NomeForne = new JLabel("Nome");
        lb_NomeForne.setBounds(160, 242, 110, 14);
        lb_NomeForne.setFont(fonte);
        Painel1.add(lb_NomeForne);

        tf_NomeForne = new JTextField();
        tf_NomeForne.setBounds(160, 258, 120, 20);
        tf_NomeForne.setEditable(false);
        tf_NomeForne.setBackground(cor);
        Painel1.add(tf_NomeForne);

        JLabel lb_Situacao = new JLabel("Situação");
        lb_Situacao.setBounds(300, 242, 110, 14);
        lb_Situacao.setFont(fonte);
        Painel1.add(lb_Situacao);

        tf_Situacao = new JTextField();
        tf_Situacao.setBounds(300, 258, 120, 20);
        tf_Situacao.setEditable(false);
        tf_Situacao.setBackground(cor);
        Painel1.add(tf_Situacao);

        JLabel lb_CodTransport = new JLabel("Transportadora");
        lb_CodTransport.setBounds(20, 279, 125, 14);
        lb_CodTransport.setFont(fonte);
        Painel1.add(lb_CodTransport);

        tf_CodTransport = new JTextField();
        tf_CodTransport.setBounds(20, 295, 120, 20);
        tf_CodTransport.setEditable(false);
        tf_CodTransport.setBackground(cor);
        Painel1.add(tf_CodTransport);

        JLabel lb_NomeTransport = new JLabel("Nome");
        lb_NomeTransport.setBounds(160, 279, 110, 14);
        lb_NomeTransport.setFont(fonte);
        Painel1.add(lb_NomeTransport);

        tf_NomeTransport = new JTextField();
        tf_NomeTransport.setBounds(160, 295, 120, 20);
        tf_NomeTransport.setEditable(false);
        tf_NomeTransport.setBackground(cor);
        Painel1.add(tf_NomeTransport);

        JLabel lb_Descricao = new JLabel("Descrição");
        lb_Descricao.setBounds(20, 316, 125, 14);
        lb_Descricao.setFont(fonte);
        Painel1.add(lb_Descricao);

        tf_Descricao = new JTextField();
        tf_Descricao.setBounds(20, 332, 400, 20);
        tf_Descricao.setEditable(false);
        tf_Descricao.setBackground(cor);
        Painel1.add(tf_Descricao);

        Modelo = new DefaultTableModel();
        JTable Tabela = new JTable(Modelo);//Constrói a tabela
        Modelo.addColumn("Codigo");//Cria colunas
        Modelo.addColumn("Produto");
        Modelo.addColumn("Descrição");
        Modelo.addColumn("Modelo");
        JScrollPane rolagem = new JScrollPane(Tabela);
        rolagem.setBounds(10, 377, 560, 178);
        Painel1.add(rolagem);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(Painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        Painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        LerArquivoFornecedor();
        LerArquivoPedidos();
        LerArquivoVendedor();
        LerArquivoItemPedido();
        LerArquivoTransportadora();
        setSize(610, 630);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    private void RecuperarVendedor() {
        if (lista3.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Vendedores cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = tf_CodVendedor.getText();
            int n = Integer.parseInt(Texto);//Conversão String para Integer
            for (int i = 0; i < lista3.size(); i++) {
                ArrayVendedor aux = lista3.get(i);
                if (n == (aux.getCodigo())) {
                    String TextoNome = aux.getNome();
                    tf_NomeVendedor.setText(TextoNome);
                    String TextoEmpresa = aux.getEmpresa();
                    tf_Empresa.setText(TextoEmpresa);
                    String TextoCNPJ = aux.getCNPJ();
                    tf_CNPJVendedor.setText(TextoCNPJ);
                    String TextoIE = aux.getIE();
                    tf_IEVendedor.setText(TextoIE);
                    String TextoEndereco = aux.getEndereco();
                    tf_EndedecoVendedor.setText(TextoEndereco);
                    String TextoBairro = aux.getBairro();
                    tf_BairroVendedor.setText(TextoBairro);
                    int INTNumero = aux.getNumero();
                    String TextoNumero = Integer.toString(INTNumero);//Conversão de Integer para String
                    tf_NumeroVendedor.setText(TextoNumero);
                    String TextoCidade = aux.getCidade();
                    tf_CidadeVendedor.setText(TextoCidade);
                    String TextoEstado = aux.getEstado();
                    tf_EstadoVendedor.setText(TextoEstado);
                    String TextoContato = aux.getFone();
                    tf_ContatoVendedor.setText(TextoContato);
                }
            }
        }
    }

    private void RecuperarFornecedor() {
        if (lista2.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Fornecedores cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = tf_CodForne.getText();
            int n = Integer.parseInt(Texto);//Conversão String para Integer
            for (int i = 0; i < lista2.size(); i++) {
                ArrayFornecedor aux = lista2.get(i);
                if (n == (aux.getCodigo())) {
                    String TextoNome = aux.getNome();
                    tf_NomeForne.setText(TextoNome);
                }
            }
        }
    }

    private void RecuperarTransportadora() {
        if (lista5.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Transportadora cadastrada na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = tf_CodForne.getText();
            int n = Integer.parseInt(Texto);//Conversão String para Integer
            for (int i = 0; i < lista5.size(); i++) {
                ArrayTransportadora aux = lista5.get(i);
                if (n == (aux.getCodigo())) {
                    String TextoNome = aux.getNome();
                    tf_NomeTransport.setText(TextoNome);
                }
            }
        }
    }

    private void RecuperarItemPedido() {
        String CodigoString = tf_Codigo.getText();
        int CodigoInt = Integer.parseInt(CodigoString);
        for (int i = 0; i < lista4.size(); i++) {
            ArrayItemPedido n = lista4.get(i);
            if (n.getCodItem() == n.getCodPedido()) {
                if (CodigoInt == n.getCodPedido()) {
                    Modelo.addRow(new Object[]{n.getCodProduto(), n.getNome(), n.getDescricao(), n.getModelo()});//Adiciona linha na tabela
                }
            }
        }
    }

    private void OK() {
        if (!"".equals(tf_DataEmissao.getText())) {
            String Texto = tf_Codigo.getText();
            Cancelar();
            tf_Codigo.setText(Texto);
            RecuperarPedido();
        } else {
            RecuperarPedido();
        }
    }

    private void RecuperarPedido() {
        if ("".equals(tf_Codigo.getText())) {
            JOptionPane.showMessageDialog(null, "Campo Código esta em branco", "Consulta Vendas", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!"".equals(tf_Codigo.getText())) {
                if (lista1.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Não há Pedidos cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    String Texto = tf_Codigo.getText();
                    int n = Integer.parseInt(Texto);//Conversão String para Integer
                    for (int i = 0; i < lista1.size(); i++) {
                        ArrayPedidos aux = lista1.get(i);
                        if (n == (aux.getCodigoPed())) {
                            String TextoDataEmissao = aux.getEmissao();
                            tf_DataEmissao.setText(TextoDataEmissao);
                            int TextoINTCodVendedor = aux.getCodigoVendedor();
                            String TextoCodVendedor = Integer.toString(TextoINTCodVendedor);//Conversão int para String
                            tf_CodVendedor.setText(TextoCodVendedor);
                            int TextoINTCodForne = aux.getCodigoForne();
                            String TextoCodForne = Integer.toString(TextoINTCodForne);//Conversão int para String
                            tf_CodForne.setText(TextoCodForne);
                            int TextoINTCodTransport = aux.getCodTransp();
                            String TextoCodTransport = Integer.toString(TextoINTCodTransport);//Conversão int para String
                            tf_CodTransport.setText(TextoCodTransport);
                            String TextoSituacao = aux.getSituacao();
                            tf_Situacao.setText(TextoSituacao);
                            String TextoDescricao = aux.getDescricao();
                            tf_Descricao.setText(TextoDescricao);
                            RecuperarVendedor();
                            RecuperarFornecedor();
                            RecuperarTransportadora();
                            RecuperarItemPedido();
                        }
                    }
                }
            }
        }
    }

    private void Cancelar() {
        tf_Codigo.setText("");
        tf_DataEmissao.setText("");
        tf_CodVendedor.setText("");
        tf_NomeVendedor.setText("");
        tf_Empresa.setText("");
        tf_CNPJVendedor.setText("");
        tf_IEVendedor.setText("");
        tf_EndedecoVendedor.setText("");
        tf_BairroVendedor.setText("");
        tf_NumeroVendedor.setText("");
        tf_CidadeVendedor.setText("");
        tf_EstadoVendedor.setText("");
        tf_ContatoVendedor.setText("");
        tf_CodForne.setText("");
        tf_NomeForne.setText("");
        tf_Situacao.setText("");
        tf_CodTransport.setText("");
        tf_NomeTransport.setText("");
        tf_Descricao.setText("");
        if (Modelo.getRowCount() != 0) {//Verifica se há linhas na tabela
            for (int i = 0; i < Modelo.getRowCount(); i++) {//Contador
                Modelo.removeRow(i);//Exclui linas
                i--;
            }
        }
    }

    private void LerArquivoFornecedor() {
        try {
            FileInputStream Arq = new FileInputStream("Fornecedor.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista2 = (ArrayList<ArrayFornecedor>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoPedidos() {
        try {
            FileInputStream Arq = new FileInputStream("Pedido.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista1 = (ArrayList<ArrayPedidos>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoVendedor() {
        try {
            FileInputStream Arq = new FileInputStream("Vendedor.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista3 = (ArrayList<ArrayVendedor>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoItemPedido() {
        try {
            FileInputStream Arq = new FileInputStream("ItemPedido.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista4 = (ArrayList<ArrayItemPedido>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoTransportadora() {
        try {
            FileInputStream Arq = new FileInputStream("Transportadora.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista5 = (ArrayList<ArrayTransportadora>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == bt_Gerar) {
                OK();
            }
            if (evento.getSource() == bt_Cancelar) {
                Cancelar();
            }
        }
    }

    private class TrataCores implements FocusListener {

        public void focusGained(FocusEvent evento) {//Ganha Focus
            if (evento.getSource() == tf_Codigo) {
                tf_Codigo.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tf_Codigo.setBackground(Color.WHITE);
        }
    }
}

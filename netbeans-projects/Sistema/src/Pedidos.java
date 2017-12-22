
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.*;

public class Pedidos extends JFrame {

    private ArrayList<ArrayPedidos> lista1 = new ArrayList();
    private ArrayList<ArrayItemPedido> lista2 = new ArrayList();
    private ArrayList<ArrayVendedor> lista3 = new ArrayList();
    private ArrayList<ArrayFornecedor> lista4 = new ArrayList();
    private ArrayList<ArrayTransportadora> lista5 = new ArrayList();
    private JTextField tf_Codigo, tf_CodVendedor, tf_NomeVendedor, tf_EmpresaVendedor, tf_EnderecoVendedor, tf_BairroVendedor, tf_NumeroVendedor, tf_CidadeVendedor, tf_EstadoVendedor, tf_ContatoVendedor, tf_CodFornecedor, tf_NomeFornecedor, tf_EnderecoFornecedor, tf_BairroFornecedor, tf_NumeroFornecedor, tf_CidadeFornecedor, tf_EstadoFornecedor, tf_CodTransport, tf_NomeTransport, tf_EnderecoTransport, tf_BairroTransport, tf_NumeroTransport, tf_CidadeTransport, tf_EstadoTransport, tf_CodProd, tf_NomeProd, tf_DescricaoProd, tf_ModeloProd;
    private JButton PesquisaPed, PesquisaVendedor, PesquisaFor, PesquisaTra, OKProd, Confirmar, Cancelar;
    private JFormattedTextField DataEmissao, CNPJVendedor, IEVendedor, ContatoFornecedor, ContatoTransport;
    private JComboBox cb_Situacao;
    private MaskFormatter mascaraDataEmissao, mascaraCNPJVendedor, mascaraIEVendedor, mascaraContatoFornecedor, mascaraContatoTransport;
    private JCheckBox Seleciona;
    private JTextArea area;
    private String InserSeleciona = null;
    private int CodigoItem = 0;
    private final DefaultTableModel Modelo;

    Pedidos() {
        super("Pedidos");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        Color cor2 = new Color(0, 0, 0);//Preto
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(10, 10, 410, 311);
        Painel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pedido"));
        tela.add(Painel1);
        JPanel Painel2 = new JPanel();
        Painel2.setLayout(null);
        Painel2.setBounds(425, 10, 410, 165);
        tela.add(Painel2);
        Painel2.setBorder(BorderFactory.createTitledBorder("Dados Fornecedor"));
        JPanel Painel3 = new JPanel();
        Painel3.setLayout(null);
        Painel3.setBounds(425, 172, 410, 149);
        tela.add(Painel3);
        Painel3.setBorder(BorderFactory.createTitledBorder("Dados Transportadora"));
        JPanel Painel4 = new JPanel();
        Painel4.setLayout(null);
        Painel4.setBounds(11, 321, 822, 51);
        tela.add(Painel4);
        Painel4.setBorder(BorderFactory.createTitledBorder(""));
        JPanel Painel5 = new JPanel();
        Painel5.setLayout(null);
        Painel5.setBounds(11, 368, 822, 165);
        tela.add(Painel5);
        Painel5.setBorder(BorderFactory.createTitledBorder(""));
        JPanel Painel6 = new JPanel();
        Painel6.setLayout(null);
        Painel6.setBounds(11, 529, 822, 88);
        tela.add(Painel6);
        Painel6.setBorder(BorderFactory.createTitledBorder(""));
        CodigoItem = lista2.size() + 1;
        LerArquivoVendedor();
        LerArquivoFornecedor();
        LerArquivoTransportadora();
        LerArquivoItemPedido();
        TrataEventos trata = new TrataEventos();
        Manipula manuseia = new Manipula();
        TrataCores cores = new TrataCores();

        JLabel lb_Codigo = new JLabel("Codigo");
        lb_Codigo.setBounds(20, 40, 80, 14);
        lb_Codigo.setFont(fonte);
        Painel1.add(lb_Codigo);

        tf_Codigo = new JTextField();
        tf_Codigo.setBounds(20, 58, 80, 20);
        int codigoint = lista1.size();
        String codigoString = Integer.toString(codigoint + 1);
        tf_Codigo.setText(codigoString);
        tf_Codigo.setBackground(cor);
        tf_Codigo.setDocument(new MeuDocument());
        Painel1.add(tf_Codigo);
        tf_Codigo.addFocusListener(cores);
        tf_Codigo.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Codigo();
            }
        });

        PesquisaPed = new JButton("...");
        PesquisaPed.setBounds(104, 56, 31, 24);
        Painel1.add(PesquisaPed);
        PesquisaPed.addActionListener(trata);

        JLabel lb_DataEmissao = new JLabel("Emissão");
        lb_DataEmissao.setBounds(140, 40, 80, 14);
        lb_DataEmissao.setFont(fonte);
        Painel1.add(lb_DataEmissao);

        try {
            mascaraDataEmissao = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
        }
        DataEmissao = new JFormattedTextField(mascaraDataEmissao);
        DataEmissao.setBounds(140, 58, 110, 20);
        Painel1.add(DataEmissao);
        DataEmissao.addFocusListener(cores);

        JLabel lb_Situacao = new JLabel("Situação");
        lb_Situacao.setBounds(260, 40, 80, 14);
        lb_Situacao.setFont(fonte);
        Painel1.add(lb_Situacao);

        cb_Situacao = new JComboBox();
        cb_Situacao.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        cb_Situacao.addItem("Aberto");
        cb_Situacao.addItem("Pago");
        cb_Situacao.setFont(fonte);
        cb_Situacao.setBackground(cor);
        cb_Situacao.setBounds(260, 58, 110, 20);
        Painel1.add(cb_Situacao);
        cb_Situacao.addFocusListener(cores);

        JLabel lb_CodVendedor = new JLabel("Vendedor");
        lb_CodVendedor.setBounds(20, 78, 80, 14);
        lb_CodVendedor.setFont(fonte);
        Painel1.add(lb_CodVendedor);

        tf_CodVendedor = new JTextField();
        tf_CodVendedor.setBounds(20, 94, 80, 20);
        tf_CodVendedor.setDocument(new MeuDocument());
        Painel1.add(tf_CodVendedor);
        tf_CodVendedor.addFocusListener(cores);
        tf_CodVendedor.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                RecuperarVendedor();
            }
        });

        PesquisaVendedor = new JButton("...");
        PesquisaVendedor.setBounds(104, 92, 31, 24);
        Painel1.add(PesquisaVendedor);
        //PesquisaVendedor.addActionListener(trata);

        tf_NomeVendedor = new JTextField();
        tf_NomeVendedor.setBounds(140, 94, 229, 20);
        Painel1.add(tf_NomeVendedor);
        tf_NomeVendedor.setEditable(false);
        tf_NomeVendedor.setBackground(cor);
        tf_NomeVendedor.addFocusListener(cores);

        JLabel lb_EmpresaVendedor = new JLabel("Empresa");
        lb_EmpresaVendedor.setBounds(20, 114, 80, 14);
        lb_EmpresaVendedor.setFont(fonte);
        Painel1.add(lb_EmpresaVendedor);

        tf_EmpresaVendedor = new JTextField();
        tf_EmpresaVendedor.setBounds(20, 130, 110, 20);
        Painel1.add(tf_EmpresaVendedor);
        tf_EmpresaVendedor.setEditable(false);
        tf_EmpresaVendedor.setBackground(cor);
        tf_EmpresaVendedor.addFocusListener(cores);

        JLabel lb_CNPJVendedor = new JLabel("CNPJ");
        lb_CNPJVendedor.setBounds(140, 114, 110, 14);
        lb_CNPJVendedor.setFont(fonte);
        Painel1.add(lb_CNPJVendedor);

        try {
            mascaraCNPJVendedor = new MaskFormatter("###.###.###/####-##");
        } catch (ParseException excp) {
        }
        CNPJVendedor = new JFormattedTextField(mascaraCNPJVendedor);
        CNPJVendedor.setBounds(140, 130, 110, 20);
        Painel1.add(CNPJVendedor);
        CNPJVendedor.setEditable(false);
        CNPJVendedor.setBackground(cor);
        CNPJVendedor.addFocusListener(cores);

        JLabel lb_IEVendedor = new JLabel("IE");
        lb_IEVendedor.setBounds(260, 114, 80, 14);
        lb_IEVendedor.setFont(fonte);
        Painel1.add(lb_IEVendedor);

        try {
            mascaraIEVendedor = new MaskFormatter("###.###.###");
        } catch (ParseException excp) {
        }
        IEVendedor = new JFormattedTextField(mascaraIEVendedor);
        IEVendedor.setBounds(260, 130, 120, 20);
        Painel1.add(IEVendedor);
        IEVendedor.setEditable(false);
        IEVendedor.setBackground(cor);
        IEVendedor.addFocusListener(cores);

        JLabel lb_EnderecoVendedor = new JLabel("Endereço");
        lb_EnderecoVendedor.setBounds(20, 150, 80, 14);
        lb_EnderecoVendedor.setFont(fonte);
        Painel1.add(lb_EnderecoVendedor);

        tf_EnderecoVendedor = new JTextField();
        tf_EnderecoVendedor.setBounds(20, 166, 110, 20);
        Painel1.add(tf_EnderecoVendedor);
        tf_EnderecoVendedor.setEditable(false);
        tf_EnderecoVendedor.setBackground(cor);
        tf_EnderecoVendedor.addFocusListener(cores);

        JLabel lb_BairroVendedor = new JLabel("Bairro");
        lb_BairroVendedor.setBounds(140, 150, 80, 14);
        lb_BairroVendedor.setFont(fonte);
        Painel1.add(lb_BairroVendedor);

        tf_BairroVendedor = new JTextField();
        tf_BairroVendedor.setBounds(140, 166, 110, 20);
        Painel1.add(tf_BairroVendedor);
        tf_BairroVendedor.setEditable(false);
        tf_BairroVendedor.setBackground(cor);
        tf_BairroVendedor.addFocusListener(cores);

        JLabel lb_NumeroVendedor = new JLabel("Numero");
        lb_NumeroVendedor.setBounds(260, 150, 80, 14);
        lb_NumeroVendedor.setFont(fonte);
        Painel1.add(lb_NumeroVendedor);

        tf_NumeroVendedor = new JTextField();
        tf_NumeroVendedor.setBounds(260, 166, 120, 20);
        Painel1.add(tf_NumeroVendedor);
        tf_NumeroVendedor.setEditable(false);
        tf_NumeroVendedor.setBackground(cor);
        tf_NumeroVendedor.addFocusListener(cores);

        JLabel lb_CidadeVendedor = new JLabel("Cidade");
        lb_CidadeVendedor.setBounds(20, 185, 80, 14);
        lb_CidadeVendedor.setFont(fonte);
        Painel1.add(lb_CidadeVendedor);

        tf_CidadeVendedor = new JTextField();
        tf_CidadeVendedor.setBounds(20, 200, 110, 20);
        Painel1.add(tf_CidadeVendedor);
        tf_CidadeVendedor.setEditable(false);
        tf_CidadeVendedor.setBackground(cor);
        tf_CidadeVendedor.addFocusListener(cores);

        JLabel lb_EstadoVendedor = new JLabel("Estado");
        lb_EstadoVendedor.setBounds(140, 185, 80, 14);
        lb_EstadoVendedor.setFont(fonte);
        Painel1.add(lb_EstadoVendedor);

        tf_EstadoVendedor = new JTextField();
        tf_EstadoVendedor.setBounds(140, 200, 110, 20);
        Painel1.add(tf_EstadoVendedor);
        tf_EstadoVendedor.setEditable(false);
        tf_EstadoVendedor.setBackground(cor);
        tf_EstadoVendedor.addFocusListener(cores);

        JLabel lb_ContatoVendedor = new JLabel("Contato");
        lb_ContatoVendedor.setBounds(260, 185, 80, 14);
        lb_ContatoVendedor.setFont(fonte);
        Painel1.add(lb_ContatoVendedor);

        tf_ContatoVendedor = new JTextField();
        tf_ContatoVendedor.setBounds(260, 200, 120, 20);
        Painel1.add(tf_ContatoVendedor);
        tf_ContatoVendedor.setEditable(false);
        tf_ContatoVendedor.setBackground(cor);
        tf_ContatoVendedor.addFocusListener(cores);

        JLabel lb_Descricao = new JLabel("Descrição");
        lb_Descricao.setBounds(20, 222, 80, 14);
        lb_Descricao.setFont(fonte);
        Painel1.add(lb_Descricao);

        area = new JTextArea();
        area.setBorder(BorderFactory.createLineBorder(cor2.darkGray, 0));//Borda em volta no JTextArea
        JScrollPane rolagem1 = new JScrollPane(area);
        rolagem1.setBounds(20, 240, 360, 60);
        Painel1.add(rolagem1);
        area.addFocusListener(cores);

        Seleciona = new JCheckBox();
        Seleciona.setBounds(75, 222, 22, 15);
        Painel1.add(Seleciona);
        Seleciona.addItemListener(manuseia);

        JLabel lb_CodFornecedor = new JLabel("Fornecedor");
        lb_CodFornecedor.setBounds(20, 40, 80, 14);
        lb_CodFornecedor.setFont(fonte);
        Painel2.add(lb_CodFornecedor);

        tf_CodFornecedor = new JTextField();
        tf_CodFornecedor.setBounds(20, 58, 80, 20);
        tf_CodFornecedor.setDocument(new MeuDocument());
        Painel2.add(tf_CodFornecedor);
        tf_CodFornecedor.addFocusListener(cores);
        tf_CodFornecedor.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                RecuperarFornecedor();
            }
        });

        PesquisaFor = new JButton("...");
        PesquisaFor.setBounds(104, 56, 31, 24);
        Painel2.add(PesquisaFor);
        //PesquisaFor.addActionListener(trata);

        tf_NomeFornecedor = new JTextField();
        tf_NomeFornecedor.setBounds(140, 58, 243, 20);
        Painel2.add(tf_NomeFornecedor);
        tf_NomeFornecedor.setEditable(false);
        tf_NomeFornecedor.setBackground(cor);
        tf_NomeFornecedor.addFocusListener(cores);

        JLabel lb_EnderecoFornecedor = new JLabel("Endereço");
        lb_EnderecoFornecedor.setBounds(20, 78, 80, 14);
        lb_EnderecoFornecedor.setFont(fonte);
        Painel2.add(lb_EnderecoFornecedor);

        tf_EnderecoFornecedor = new JTextField();
        tf_EnderecoFornecedor.setBounds(20, 94, 110, 20);
        Painel2.add(tf_EnderecoFornecedor);
        tf_EnderecoFornecedor.setEditable(false);
        tf_EnderecoFornecedor.setBackground(cor);
        tf_EnderecoFornecedor.addFocusListener(cores);

        JLabel lb_BairroFonecedor = new JLabel("Bairro");
        lb_BairroFonecedor.setBounds(140, 78, 80, 14);
        lb_BairroFonecedor.setFont(fonte);
        Painel2.add(lb_BairroFonecedor);

        tf_BairroFornecedor = new JTextField();
        tf_BairroFornecedor.setBounds(140, 94, 110, 20);
        Painel2.add(tf_BairroFornecedor);
        tf_BairroFornecedor.setEditable(false);
        tf_BairroFornecedor.setBackground(cor);
        tf_BairroFornecedor.addFocusListener(cores);

        JLabel lb_NumeroFornecedor = new JLabel("Numero");
        lb_NumeroFornecedor.setBounds(260, 78, 80, 14);
        lb_NumeroFornecedor.setFont(fonte);
        Painel2.add(lb_NumeroFornecedor);

        tf_NumeroFornecedor = new JTextField();
        tf_NumeroFornecedor.setBounds(260, 94, 123, 20);
        Painel2.add(tf_NumeroFornecedor);
        tf_NumeroFornecedor.setEditable(false);
        tf_NumeroFornecedor.setBackground(cor);
        tf_NumeroFornecedor.addFocusListener(cores);

        JLabel lb_CidadeFornecedor = new JLabel("Cidade");
        lb_CidadeFornecedor.setBounds(20, 114, 80, 14);
        lb_CidadeFornecedor.setFont(fonte);
        Painel2.add(lb_CidadeFornecedor);

        tf_CidadeFornecedor = new JTextField();
        tf_CidadeFornecedor.setBounds(20, 130, 110, 20);
        Painel2.add(tf_CidadeFornecedor);
        tf_CidadeFornecedor.setEditable(false);
        tf_CidadeFornecedor.setBackground(cor);
        tf_CidadeFornecedor.addFocusListener(cores);

        JLabel lb_EstadoFornecedor = new JLabel("Estado");
        lb_EstadoFornecedor.setBounds(140, 114, 110, 14);
        lb_EstadoFornecedor.setFont(fonte);
        Painel2.add(lb_EstadoFornecedor);

        tf_EstadoFornecedor = new JTextField();
        tf_EstadoFornecedor.setBounds(140, 130, 110, 20);
        Painel2.add(tf_EstadoFornecedor);
        tf_EstadoFornecedor.setEditable(false);
        tf_EstadoFornecedor.setBackground(cor);
        tf_EstadoFornecedor.addFocusListener(cores);

        JLabel lb_ContatoFornecedor = new JLabel("Contato");
        lb_ContatoFornecedor.setBounds(260, 114, 80, 14);
        lb_ContatoFornecedor.setFont(fonte);
        Painel2.add(lb_ContatoFornecedor);

        try {
            mascaraContatoFornecedor = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ContatoFornecedor = new JFormattedTextField(mascaraContatoFornecedor);
        ContatoFornecedor.setBounds(260, 130, 120, 20);
        Painel2.add(ContatoFornecedor);
        ContatoFornecedor.setEditable(false);
        ContatoFornecedor.setBackground(cor);
        ContatoFornecedor.addFocusListener(cores);

        JLabel lb_CodTranspor = new JLabel("Transportadora");
        lb_CodTranspor.setBounds(20, 25, 90, 14);
        lb_CodTranspor.setFont(fonte);
        Painel3.add(lb_CodTranspor);

        tf_CodTransport = new JTextField();
        tf_CodTransport.setBounds(20, 43, 80, 20);
        tf_CodTransport.setDocument(new MeuDocument());
        Painel3.add(tf_CodTransport);
        tf_CodTransport.addFocusListener(cores);
        tf_CodTransport.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                RecuperarTransportadora();
            }
        });

        PesquisaTra = new JButton("...");
        PesquisaTra.setBounds(104, 41, 31, 24);
        Painel3.add(PesquisaTra);
        //PesquisaTra.addActionListener(trata);

        tf_NomeTransport = new JTextField();
        tf_NomeTransport.setBounds(140, 43, 243, 20);
        Painel3.add(tf_NomeTransport);
        tf_NomeTransport.setEditable(false);
        tf_NomeTransport.setBackground(cor);
        tf_NomeTransport.addFocusListener(cores);

        JLabel lb_EnderecoTransport = new JLabel("Endereço");
        lb_EnderecoTransport.setBounds(20, 63, 80, 14);
        lb_EnderecoTransport.setFont(fonte);
        Painel3.add(lb_EnderecoTransport);

        tf_EnderecoTransport = new JTextField();
        tf_EnderecoTransport.setBounds(20, 79, 110, 20);
        Painel3.add(tf_EnderecoTransport);
        tf_EnderecoTransport.setEditable(false);
        tf_EnderecoTransport.setBackground(cor);
        tf_EnderecoTransport.addFocusListener(cores);

        JLabel lb_BairroTransport = new JLabel("Bairro");
        lb_BairroTransport.setBounds(140, 63, 80, 14);
        lb_BairroTransport.setFont(fonte);
        Painel3.add(lb_BairroTransport);

        tf_BairroTransport = new JTextField();
        tf_BairroTransport.setBounds(140, 79, 110, 20);
        Painel3.add(tf_BairroTransport);
        tf_BairroTransport.setEditable(false);
        tf_BairroTransport.setBackground(cor);
        tf_BairroTransport.addFocusListener(cores);

        JLabel lb_NumeroTransport = new JLabel("Numero");
        lb_NumeroTransport.setBounds(260, 63, 80, 14);
        lb_NumeroTransport.setFont(fonte);
        Painel3.add(lb_NumeroTransport);

        tf_NumeroTransport = new JTextField();
        tf_NumeroTransport.setBounds(260, 79, 123, 20);
        Painel3.add(tf_NumeroTransport);
        tf_NumeroTransport.setEditable(false);
        tf_NumeroTransport.setBackground(cor);
        tf_NumeroTransport.addFocusListener(cores);

        JLabel lb_CidadeTransport = new JLabel("Cidade");
        lb_CidadeTransport.setBounds(20, 99, 80, 14);
        lb_CidadeTransport.setFont(fonte);
        Painel3.add(lb_CidadeTransport);

        tf_CidadeTransport = new JTextField();
        tf_CidadeTransport.setBounds(20, 115, 110, 20);
        Painel3.add(tf_CidadeTransport);
        tf_CidadeTransport.setEditable(false);
        tf_CidadeTransport.setBackground(cor);
        tf_CidadeTransport.addFocusListener(cores);

        JLabel lb_EstadoTransport = new JLabel("Estado");
        lb_EstadoTransport.setBounds(140, 99, 110, 14);
        lb_EstadoTransport.setFont(fonte);
        Painel3.add(lb_EstadoTransport);

        tf_EstadoTransport = new JTextField();
        tf_EstadoTransport.setBounds(140, 115, 110, 20);
        Painel3.add(tf_EstadoTransport);
        tf_EstadoTransport.setEditable(false);
        tf_EstadoTransport.setBackground(cor);
        tf_EstadoTransport.addFocusListener(cores);

        JLabel lb_ContatoTransport = new JLabel("Contato");
        lb_ContatoTransport.setBounds(260, 99, 80, 14);
        lb_ContatoTransport.setFont(fonte);
        Painel3.add(lb_ContatoTransport);

        try {
            mascaraContatoTransport = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ContatoTransport = new JFormattedTextField(mascaraContatoTransport);
        ContatoTransport.setBounds(260, 115, 120, 20);
        Painel3.add(ContatoTransport);
        ContatoTransport.setEditable(false);
        ContatoTransport.setBackground(cor);
        ContatoTransport.addFocusListener(cores);

        JLabel lb_CodigoProd = new JLabel("Cod. Produto");
        lb_CodigoProd.setBounds(15, 14, 80, 14);
        lb_CodigoProd.setFont(fonte);
        Painel4.add(lb_CodigoProd);

        tf_CodProd = new JTextField();
        tf_CodProd.setBounds(90, 12, 75, 20);
        Painel4.add(tf_CodProd);
        tf_CodProd.setDocument(new MeuDocument());
        tf_CodProd.addFocusListener(cores);

        JLabel lb_NomeProd = new JLabel("Nome");
        lb_NomeProd.setBounds(170, 14, 80, 14);
        lb_NomeProd.setFont(fonte);
        Painel4.add(lb_NomeProd);

        tf_NomeProd = new JTextField();
        tf_NomeProd.setBounds(208, 12, 130, 20);
        Painel4.add(tf_NomeProd);
        tf_NomeProd.addFocusListener(cores);

        JLabel lb_DescricaoProd = new JLabel("Descriçao");
        lb_DescricaoProd.setBounds(341, 14, 80, 14);
        lb_DescricaoProd.setFont(fonte);
        Painel4.add(lb_DescricaoProd);

        tf_DescricaoProd = new JTextField();
        tf_DescricaoProd.setBounds(401, 12, 130, 20);
        Painel4.add(tf_DescricaoProd);
        tf_DescricaoProd.addFocusListener(cores);

        JLabel lb_Modelo = new JLabel("Modelo");
        lb_Modelo.setBounds(535, 14, 80, 14);
        lb_Modelo.setFont(fonte);
        Painel4.add(lb_Modelo);

        tf_ModeloProd = new JTextField();
        tf_ModeloProd.setBounds(580, 12, 110, 20);
        Painel4.add(tf_ModeloProd);
        tf_ModeloProd.addFocusListener(cores);

        OKProd = new JButton("OK");
        OKProd.setBounds(705, 9, 51, 26);
        Painel4.add(OKProd);
        OKProd.addActionListener(trata);

        Modelo = new DefaultTableModel();
        JTable Tabela = new JTable(Modelo);//Constrói a tabela
        Modelo.addColumn("Codigo");//Cria colunas
        Modelo.addColumn("Produto");
        Modelo.addColumn("Descrição");
        Modelo.addColumn("Modelo");
        JScrollPane rolagem2 = new JScrollPane(Tabela);
        rolagem2.setBounds(2, 3, 820, 160);
        Painel5.add(rolagem2);

        Confirmar = new JButton("Confirmar");
        Confirmar.setBounds(720, 15, 92, 26);
        Painel6.add(Confirmar);
        Confirmar.setEnabled(false);
        Confirmar.addActionListener(trata);

        Cancelar = new JButton("Cancelar");
        Cancelar.setBounds(720, 50, 92, 26);
        Painel6.add(Cancelar);
        Cancelar.addActionListener(trata);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(Painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        Painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        Painel2.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        Painel3.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        Painel4.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        Painel5.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        Painel6.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        LerArquivo();
        setSize(855, 650);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    private void GravarItens() {
        String textocodvenda, textocodprod, InserNome = "", InserDescricao = "", InserModelo = "";
        int InserCodPedido = 0, InserCodProd = 0, InserCodItemPedido = 0, InserCodigo = 0;

        if (!"".equals(tf_Codigo.getText().trim())) {//Se campo não esta vazio//trim tira espaço
            textocodvenda = tf_Codigo.getText();
            InserCodPedido = Integer.parseInt(textocodvenda);//Conversão de String para Integer
            String StringCodItemVen = tf_Codigo.getText();
            InserCodItemPedido = Integer.parseInt(StringCodItemVen);//Conversão Intrger para String
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Codigo.setText("");//Limpa o campo
            tf_Codigo.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_CodProd.getText())) {//Se campo não esta vazio
            textocodprod = tf_CodProd.getText();
            InserCodProd = Integer.parseInt(textocodprod);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código Produto esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_CodProd.setText("");//Limpa o campo
            tf_CodProd.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_NomeProd.getText())) {//Se campo não esta vazio
            InserNome = tf_NomeProd.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Nome Produto esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_NomeProd.setText("");//Limpa o campo
            tf_NomeProd.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_DescricaoProd.getText())) {//Se campo não esta vazio
            InserDescricao = tf_DescricaoProd.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Descrição Produto esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_DescricaoProd.setText("");//Limpa o campo
            tf_DescricaoProd.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_ModeloProd.getText())) {//Se campo não esta vazio
            InserModelo = tf_ModeloProd.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Modelo Produto esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_ModeloProd.setText("");//Limpa o campo
            tf_ModeloProd.grabFocus();//Focaliza o campo
        }

        InserCodigo = CodigoItem;

        if (!"".equals(tf_Codigo.getText()) & !"".equals(tf_NomeProd.getText()) & !"".equals(tf_DescricaoProd.getText()) & !"".equals(tf_CodProd.getText()) & !"".equals(tf_ModeloProd.getText())) {
            ArrayItemPedido aux = new ArrayItemPedido(InserNome, InserDescricao, InserModelo, InserCodItemPedido, InserCodPedido, InserCodProd, InserCodigo);
            lista2.add(aux);
            OKProduto();
            Confirmar.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Total de Pedidos realizados: " + lista2.size(), "Pedidos", JOptionPane.WARNING_MESSAGE);
            tf_Codigo.setEditable(false);
            Cancelar.setEnabled(false);
            LimparProduto();
            tf_CodProd.grabFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel executar operação no sistema", "Erro Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void GravarPedido() {
        String textocod, textocodVendedor, textocodfornecedor, textocodtransportadora, textocoditenped, InserDataEmissao = "", InserSituacao = "", InserDescricao = null;
        int InserCodigo = 0, InserCodVendedor = 0, InserCodForne = 0, InserCodItemPedido = 0, InserCodTransport = 0;

        if (!"".equals(tf_Codigo.getText())) {//Se campo não esta vazio
            textocod = tf_Codigo.getText();
            InserCodigo = Integer.parseInt(textocod);//Conversão de String para Integer
            textocoditenped = tf_Codigo.getText();
            InserCodItemPedido = Integer.parseInt(textocoditenped);//Conversão Intrger para String
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Codigo.setText("");//Limpa o campo
            tf_Codigo.grabFocus();//Focaliza o campo
        }

        if (!"  /  /    ".equals(DataEmissao.getText())) {//Se campo não esta vazio
            InserDataEmissao = DataEmissao.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Data Emissão esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            DataEmissao.setText("");//Limpa o campo
            DataEmissao.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(cb_Situacao.getSelectedItem())) {//Se campo não esta vazio
            InserSituacao = (String) cb_Situacao.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Situação esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            cb_Situacao.setSelectedItem("Selecione");//Campo recebe Selecione
            cb_Situacao.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_CodVendedor.getText())) {//Se campo não esta vazio
            textocodVendedor = tf_CodVendedor.getText();
            InserCodVendedor = Integer.parseInt(textocodVendedor);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código Vendedor esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_CodVendedor.setText("");//Limpa o campo
            tf_CodVendedor.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_CodFornecedor.getText())) {//Se campo não esta vazio
            textocodfornecedor = tf_CodFornecedor.getText();
            InserCodForne = Integer.parseInt(textocodfornecedor);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código Cliente esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_CodFornecedor.setText("");//Limpa o campo
            tf_CodFornecedor.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_CodTransport.getText())) {//Se campo não esta vazio
            textocodtransportadora = tf_CodTransport.getText();
            InserCodTransport = Integer.parseInt(textocodtransportadora);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código Transportadora esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_CodTransport.setText("");//Limpa o campo
            tf_CodTransport.grabFocus();//Focaliza o campo
        }

        if (InserSeleciona == null) {
            if ("".equals(area.getText())) {
                JOptionPane.showMessageDialog(null, "Campo Descrição esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            } else {
                InserDescricao = area.getText();
            }
        } else {
            InserDescricao = InserSeleciona;
        }

        if (!"".equals(tf_Codigo.getText()) & !"  /  /    ".equals(DataEmissao.getText()) & !"Selecione".equals(cb_Situacao.getSelectedItem()) & !"".equals(tf_CodVendedor.getText()) & !"".equals(tf_CodFornecedor.getText()) & !"".equals(tf_CodTransport.getText()) & InserDescricao != null) {
            ArrayPedidos aux = new ArrayPedidos(InserDataEmissao, InserSituacao, InserDescricao, InserCodigo, InserCodVendedor, InserCodForne, InserCodTransport, InserCodItemPedido);
            lista1.add(aux);
            JOptionPane.showMessageDialog(null, "Número de Pedidos cadastrados: " + lista1.size(), "Entrada de Dados", JOptionPane.WARNING_MESSAGE);
            LimparProduto();
            LimparPedido();
            Confirmar.setEnabled(false);
            Cancelar.setEnabled(true);
            tf_Codigo.setEditable(true);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel executar operação no sistema", "Erro Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void OKProduto() {
        Modelo.addRow(new Object[]{tf_CodProd.getText(), tf_NomeProd.getText(), tf_DescricaoProd.getText(), tf_ModeloProd.getText()});//Adiciona linha na tabela
        tf_CodProd.grabFocus();
    }

    private void Codigo() {
        if ("".equals(tf_Codigo.getText())) {
            int codigoint = lista1.size();
            String codigoString = Integer.toString(codigoint + 1);
            tf_Codigo.setText(codigoString);
        } else {
            if (lista1.isEmpty() == true) {
            } else {
                String t = tf_Codigo.getText();
                int n = Integer.parseInt(t);
                boolean encontrado = false;
                for (int i = 0; i < lista1.size(); i++) {
                    ArrayPedidos aux = lista1.get(i);
                    if (n == (aux.getCodigoPed())) {
                        encontrado = true;
                    }
                }
                if (encontrado == true) {
                    int resposta = JOptionPane.showConfirmDialog(null, "O Pedido " + tf_Codigo.getText() + " ja esta cadastrado? ", "Pedido", JOptionPane.WARNING_MESSAGE);
                    int codigoint = lista1.size();
                    String codigoString = Integer.toString(codigoint + 1);
                    tf_Codigo.setText(codigoString);
                }
            }
        }
    }

    private void Confirmar() {
        GravarPedido();
    }

    private void LimparProduto() {
        tf_CodProd.setText("");
        tf_NomeProd.setText("");
        tf_DescricaoProd.setText("");
        tf_ModeloProd.setText("");
    }

    private void LimparPedido() {
        int codigoint = lista1.size();
        String codigoString = Integer.toString(codigoint + 1);
        tf_Codigo.setText(codigoString);
        DataEmissao.setText("");
        cb_Situacao.setSelectedItem("Selecione");
        tf_CodVendedor.setText("");
        tf_NomeVendedor.setText("");
        tf_EmpresaVendedor.setText("");
        CNPJVendedor.setText("");
        IEVendedor.setText("");
        tf_EnderecoVendedor.setText("");
        tf_BairroVendedor.setText("");
        tf_NumeroVendedor.setText("");
        tf_CidadeVendedor.setText("");
        tf_EstadoVendedor.setText("");
        tf_ContatoVendedor.setText("");
        area.setText("");
        tf_CidadeVendedor.setText("");
        tf_EstadoVendedor.setText("");
        tf_CodFornecedor.setText("");
        tf_NomeFornecedor.setText("");
        tf_EnderecoFornecedor.setText("");
        tf_BairroFornecedor.setText("");
        tf_NumeroFornecedor.setText("");
        tf_CidadeFornecedor.setText("");
        tf_EstadoFornecedor.setText("");
        ContatoFornecedor.setText("");
        tf_CodTransport.setText("");
        tf_NomeTransport.setText("");
        tf_EnderecoTransport.setText("");
        tf_BairroTransport.setText("");
        tf_NumeroTransport.setText("");
        tf_CidadeTransport.setText("");
        tf_EstadoTransport.setText("");
        ContatoTransport.setText("");
        Seleciona.setSelected(false);//Desmarca JCheckbox
        InserSeleciona = null;//Limpa Variavel
        if (Modelo.getRowCount() == 0) {//Verifica se há linhas na tabela
        } else {
            if (Modelo.getRowCount() >= 1) {
                for (int i = 0; i <= Modelo.getRowCount(); i++) {//Contador
                    Modelo.setNumRows(0);//Exclui Todas as linhas
                }
            }
        }
    }

    private void RecuperarVendedor() {
        if (!"".equals(tf_CodVendedor.getText())) {
            if (lista3.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "Não há Vendedor cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                String Texto = tf_CodVendedor.getText();
                int n = Integer.parseInt(Texto);//Conversão String para Integer
                for (int i = 0; i < lista3.size(); i++) {
                    ArrayVendedor aux = lista3.get(i);
                    if (n == (aux.getCodigo())) {
                        int TextoINTCodigo = aux.getCodigo();
                        String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                        tf_CodVendedor.setText(TextoCodigo);
                        String TextoNome = aux.getNome();
                        tf_NomeVendedor.setText(TextoNome);
                        String TextoEmpresa = aux.getEmpresa();
                        tf_EmpresaVendedor.setText(TextoEmpresa);
                        String TextoCNPJ = aux.getCNPJ();
                        CNPJVendedor.setText(TextoCNPJ);
                        String TextoIE = aux.getIE();
                        IEVendedor.setText(TextoIE);
                        String TextoEndereco = aux.getEndereco();
                        tf_EnderecoVendedor.setText(TextoEndereco);
                        String TextoBairro = aux.getBairro();
                        tf_BairroVendedor.setText(TextoBairro);
                        int INTNumero = aux.getNumero();
                        String TextoNumero = Integer.toString(INTNumero);//Converssao int para String
                        tf_NumeroVendedor.setText(TextoNumero);
                        String TextoCidade = aux.getCidade();
                        tf_CidadeVendedor.setText(TextoCidade);
                        String TextoEstado = aux.getEstado();
                        tf_EstadoVendedor.setText(TextoEstado);
                        String TextoFone = aux.getFone();
                        tf_ContatoVendedor.setText(TextoFone);
                    }
                }
            }
        }
    }

    private void RecuperarFornecedor() {
        if (!"".equals(tf_CodFornecedor.getText())) {
            if (lista4.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "Não há Fornecedor cadastrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                String Texto = tf_CodFornecedor.getText();
                int n = Integer.parseInt(Texto);//Conversão String para Integer
                for (int i = 0; i < lista4.size(); i++) {
                    ArrayFornecedor aux = lista4.get(i);
                    if (n == (aux.getCodigo())) {
                        int TextoINTCodigo = aux.getCodigo();
                        String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                        tf_CodFornecedor.setText(TextoCodigo);
                        String TextoNome = aux.getNome();
                        tf_NomeFornecedor.setText(TextoNome);
                        String TextoEndereco = aux.getEndereco();
                        tf_EnderecoFornecedor.setText(TextoEndereco);
                        String TextoBairro = aux.getBairro();
                        tf_BairroFornecedor.setText(TextoBairro);
                        int INTNumero = aux.getNumero();
                        String TextoNumero = Integer.toString(INTNumero);//Converssao int para String
                        tf_NumeroFornecedor.setText(TextoNumero);
                        String TextoCidade = aux.getCidade();
                        tf_CidadeFornecedor.setText(TextoCidade);
                        String TextoEstado = aux.getEstado();
                        tf_EstadoFornecedor.setText(TextoEstado);
                        String TextoFone = aux.getFone();
                        ContatoFornecedor.setText(TextoFone);
                    }
                }
            }
        }
    }

    private void RecuperarTransportadora() {
        if (!"".equals(tf_CodTransport.getText())) {
            if (lista5.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "Não há Transportadora cadastrada na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                String Texto = tf_CodTransport.getText();
                int n = Integer.parseInt(Texto);//Conversão String para Integer
                for (int i = 0; i < lista5.size(); i++) {
                    ArrayTransportadora aux = lista5.get(i);
                    if (n == (aux.getCodigo())) {
                        int TextoINTCodigo = aux.getCodigo();
                        String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                        tf_CodTransport.setText(TextoCodigo);
                        String TextoNome = aux.getNome();
                        tf_NomeTransport.setText(TextoNome);
                        String TextoEndereco = aux.getEndereco();
                        tf_EnderecoTransport.setText(TextoEndereco);
                        String TextoBairro = aux.getBairro();
                        tf_BairroTransport.setText(TextoBairro);
                        int INTNumero = aux.getNumero();
                        String TextoNumero = Integer.toString(INTNumero);//Converssao int para String
                        tf_NumeroTransport.setText(TextoNumero);
                        String TextoCidade = aux.getCidade();
                        tf_CidadeTransport.setText(TextoCidade);
                        String TextoEstado = aux.getEstado();
                        tf_EstadoTransport.setText(TextoEstado);
                        String TextoFone = aux.getFone();
                        ContatoTransport.setText(TextoFone);
                    }
                }
            }
        }
    }

    private void Vendedor() {
        if (lista3.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Vendedores cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            //VerFuncionario aux = new VerFuncionario(lista2);
        }
    }

    private void Fornecedor() {
        if (lista4.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Fornecedores cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            VerCliente aux = new VerCliente(lista3);
        }
    }

    private void Transportadora() {
        if (lista5.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Transportadora cadastrada na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            //VerProduto aux = new VerProduto(lista4);
        }
    }

    private void Pedido() {
        if (lista1.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Pedidos cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            ConsultaPedido aux = new ConsultaPedido();
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

    private void LerArquivoFornecedor() {
        try {
            FileInputStream Arq = new FileInputStream("Fornecedor.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista4 = (ArrayList<ArrayFornecedor>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ArquivoPedido() {
        try {
            FileOutputStream arqDados = new FileOutputStream("Pedido.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista1);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ArquivoItemPedido() {
        try {
            FileOutputStream arqDados = new FileOutputStream("ItemPedido.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista2);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoItemPedido() {
        try {
            FileInputStream Arq = new FileInputStream("ItemPedido.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista2 = (ArrayList<ArrayItemPedido>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Pedido.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista1 = (ArrayList<ArrayPedidos>) entra.readObject();
            entra.close();
            int codigoint = lista1.size();
            String codigoString = Integer.toString(codigoint + 1);
            tf_Codigo.setText(codigoString);
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

    private class Manipula implements ItemListener {

        public void itemStateChanged(ItemEvent evento) {
            if (evento.getSource() == Seleciona) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    InserSeleciona = " Sem Descrição";
                } else {
                    InserSeleciona = null;
                }
            }
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == PesquisaVendedor) {
                Vendedor();
            }
            if (evento.getSource() == PesquisaFor) {
                Fornecedor();
            }
            if (evento.getSource() == PesquisaPed) {
                Pedido();
            }
            if (evento.getSource() == PesquisaTra) {
                Transportadora();
            }
            if (evento.getSource() == OKProd) {
                GravarItens();
                ArquivoItemPedido();
            }
            if (evento.getSource() == Confirmar) {
                Confirmar();
                ArquivoPedido();
            }
            if (evento.getSource() == Cancelar) {
                LimparProduto();
                LimparPedido();
            }
        }
    }

    private class TrataCores implements FocusListener {

        public void focusGained(FocusEvent evento) {//Ganha Focus
            if (evento.getSource() == tf_Codigo) {
                tf_Codigo.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == DataEmissao) {
                DataEmissao.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == cb_Situacao) {
                cb_Situacao.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CodVendedor) {
                tf_CodVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NomeVendedor) {
                tf_NomeVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_EmpresaVendedor) {
                tf_EmpresaVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == CNPJVendedor) {
                CNPJVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == IEVendedor) {
                IEVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_EnderecoVendedor) {
                tf_EnderecoVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_BairroVendedor) {
                tf_BairroVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NumeroVendedor) {
                tf_NumeroVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CidadeVendedor) {
                tf_CidadeVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_EstadoVendedor) {
                tf_EstadoVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_ContatoVendedor) {
                tf_ContatoVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == area) {
                area.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CodFornecedor) {
                tf_CodFornecedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NomeFornecedor) {
                tf_NomeFornecedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_EnderecoFornecedor) {
                tf_EnderecoFornecedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_BairroFornecedor) {
                tf_BairroFornecedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NumeroFornecedor) {
                tf_NumeroFornecedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CidadeFornecedor) {
                tf_CidadeFornecedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_EstadoFornecedor) {
                tf_EstadoFornecedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == ContatoFornecedor) {
                ContatoFornecedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CodTransport) {
                tf_CodTransport.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NomeTransport) {
                tf_NomeTransport.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_EnderecoTransport) {
                tf_EnderecoTransport.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_BairroTransport) {
                tf_BairroTransport.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NumeroTransport) {
                tf_NumeroTransport.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CidadeTransport) {
                tf_CidadeTransport.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_EstadoTransport) {
                tf_EstadoTransport.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == ContatoTransport) {
                ContatoTransport.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CodProd) {
                tf_CodProd.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NomeProd) {
                tf_NomeProd.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_DescricaoProd) {
                tf_DescricaoProd.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_ModeloProd) {
                tf_ModeloProd.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tf_Codigo.setBackground(Color.WHITE);
            DataEmissao.setBackground(Color.WHITE);
            cb_Situacao.setBackground(Color.WHITE);
            tf_CodVendedor.setBackground(Color.WHITE);
            tf_NomeVendedor.setBackground(Color.WHITE);
            tf_EmpresaVendedor.setBackground(Color.WHITE);
            CNPJVendedor.setBackground(Color.WHITE);
            IEVendedor.setBackground(Color.WHITE);
            tf_EnderecoVendedor.setBackground(Color.WHITE);
            tf_BairroVendedor.setBackground(Color.WHITE);
            tf_NumeroVendedor.setBackground(Color.WHITE);
            tf_CidadeVendedor.setBackground(Color.WHITE);
            tf_EstadoVendedor.setBackground(Color.WHITE);
            tf_ContatoVendedor.setBackground(Color.WHITE);
            area.setBackground(Color.WHITE);
            tf_CodFornecedor.setBackground(Color.WHITE);
            tf_NomeFornecedor.setBackground(Color.WHITE);
            tf_EnderecoFornecedor.setBackground(Color.WHITE);
            tf_BairroFornecedor.setBackground(Color.WHITE);
            tf_NumeroFornecedor.setBackground(Color.WHITE);
            tf_CidadeFornecedor.setBackground(Color.WHITE);
            tf_EstadoFornecedor.setBackground(Color.WHITE);
            ContatoFornecedor.setBackground(Color.WHITE);
            tf_CodTransport.setBackground(Color.WHITE);
            tf_NomeTransport.setBackground(Color.WHITE);
            tf_EnderecoTransport.setBackground(Color.WHITE);
            tf_BairroTransport.setBackground(Color.WHITE);
            tf_NumeroTransport.setBackground(Color.WHITE);
            tf_CidadeTransport.setBackground(Color.WHITE);
            tf_EstadoTransport.setBackground(Color.WHITE);
            ContatoTransport.setBackground(Color.WHITE);
            tf_CodProd.setBackground(Color.WHITE);
            tf_NomeProd.setBackground(Color.WHITE);
            tf_DescricaoProd.setBackground(Color.WHITE);
            tf_ModeloProd.setBackground(Color.WHITE);
        }
    }
}


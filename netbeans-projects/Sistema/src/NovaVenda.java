
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.io.*;
import javax.swing.table.*;

public class NovaVenda extends JFrame {

    private ArrayList<ArrayNovaVenda> lista1 = new ArrayList();
    private ArrayList<ArrayVendedor> lista2 = new ArrayList();
    private ArrayList<ArrayCliente> lista3 = new ArrayList();
    private ArrayList<ArrayProdutos> lista4 = new ArrayList();
    private ArrayList<ArrayItenVenda> lista5 = new ArrayList();
    private ArrayList<ArrayCidade> lista6 = new ArrayList();
    private JTextField tf_Codigo, tf_CodCliente, tf_NomeClie, tf_EnderecoClie, tf_BairroClie, tf_CidadeClie, tf_EstadoClie, tf_NumeroClie, tf_NomeVendedor, tf_CodVendedor, tf_ComissaoVendedor, tf_EmpresaVendedor, tf_EnderecoVendedor, tf_BairroVendedor, tf_EstadoVendedor, tf_NumeroVendedor, tf_CidadeVendedor, tf_subTotal, tf_Descontos, tf_Total, tf_CodProd, tf_NomeProd, tf_DescriProd, tf_ModeloProd, tf_TotalProd, tf_EnderecoEnt, tf_BairroEnt, tf_NumeroEnt;
    private JComboBox cb_FormaPagto, cb_CondPagto, Cb_Situacao, Cb_CidadeEnt, Cb_EstadoEnt;
    private JFormattedTextField FoneClie, CelularClie, DataEmissao, CNPJVendedor, IEVendedor, ContatoEnt;
    private MaskFormatter mascaraFoneClie, mascaraCelularClie, mascaraDataEmissao, mascaraCNPJVendedor, mascaraIEVendedor, mascaraContatoEnt;
    private JButton PesquisaPed, PesquisaVendedor, PesquisaClie, PesquisaProd, OKProd, Confirmar, Cancelar;
    private int CodigoItem = 0;
    private final DefaultTableModel Modelo;
    private JCheckBox Seleciona;
    private String InserSeleciona = null;
    private JTextArea area;
    private float Numero = 0;

    NovaVenda() {
        super("Nova Venda");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor1 = new Color(0, 0, 0);//Preto
        Color cor2 = new Color(255, 255, 255);//Branco
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(10, 10, 410, 311);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("Dados Venda"));
        JPanel Painel2 = new JPanel();
        Painel2.setLayout(null);
        Painel2.setBounds(425, 10, 410, 200);
        tela.add(Painel2);
        Painel2.setBorder(BorderFactory.createTitledBorder("Dados Cliente"));
        JPanel Painel3 = new JPanel();
        Painel3.setLayout(null);
        Painel3.setBounds(425, 207, 410, 113);
        tela.add(Painel3);
        Painel3.setBorder(BorderFactory.createTitledBorder("Dados Entrega"));
        JPanel Painel4 = new JPanel();
        Painel4.setLayout(null);
        Painel4.setBounds(11, 321, 885, 51);
        tela.add(Painel4);
        Painel4.setBorder(BorderFactory.createTitledBorder(""));
        JPanel Painel5 = new JPanel();
        Painel5.setLayout(null);
        Painel5.setBounds(11, 368, 885, 165);
        tela.add(Painel5);
        Painel5.setBorder(BorderFactory.createTitledBorder(""));
        JPanel Painel6 = new JPanel();
        Painel6.setLayout(null);
        Painel6.setBounds(11, 529, 885, 90);
        tela.add(Painel6);
        Painel6.setBorder(BorderFactory.createTitledBorder(""));
        LerArquivoVendedor();
        LerArquivoCliente();
        LerArquivoProdutos();
        LerArquivoItemVenda();
        LerArquivoCidade();
        CodigoItem = lista5.size() + 1;
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

        Cb_Situacao = new JComboBox();
        Cb_Situacao.addItem("Aberto");//addItem adiciona no JComboBox as opçoes
        Cb_Situacao.setFont(fonte);
        Cb_Situacao.setBackground(cor2);
        Cb_Situacao.setBounds(260, 58, 110, 20);
        Painel1.add(Cb_Situacao);
        Cb_Situacao.addFocusListener(cores);

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
        PesquisaVendedor.addActionListener(trata);

        tf_NomeVendedor = new JTextField();
        tf_NomeVendedor.setBounds(140, 94, 229, 20);
        Painel1.add(tf_NomeVendedor);
        tf_NomeVendedor.setEditable(false);
        tf_NomeVendedor.setBackground(cor2);
        tf_NomeVendedor.addFocusListener(cores);

        JLabel lb_ComissaoVendedor = new JLabel("Comissao");
        lb_ComissaoVendedor.setBounds(20, 114, 80, 14);
        lb_ComissaoVendedor.setFont(fonte);
        Painel1.add(lb_ComissaoVendedor);

        tf_ComissaoVendedor = new JTextField();
        tf_ComissaoVendedor.setBounds(20, 130, 110, 20);
        Painel1.add(tf_ComissaoVendedor);
        tf_ComissaoVendedor.setEditable(false);
        tf_ComissaoVendedor.setBackground(cor2);
        tf_ComissaoVendedor.addFocusListener(cores);

        JLabel lb_EmpresaVendedor = new JLabel("Empresa");
        lb_EmpresaVendedor.setBounds(140, 114, 110, 14);
        lb_EmpresaVendedor.setFont(fonte);
        Painel1.add(lb_EmpresaVendedor);

        tf_EmpresaVendedor = new JTextField();
        tf_EmpresaVendedor.setBounds(140, 130, 110, 20);
        Painel1.add(tf_EmpresaVendedor);
        tf_EmpresaVendedor.setEditable(false);
        tf_EmpresaVendedor.setBackground(cor2);
        tf_EmpresaVendedor.addFocusListener(cores);

        JLabel lb_CNPJVendedor = new JLabel("CNPJ");
        lb_CNPJVendedor.setBounds(260, 114, 80, 14);
        lb_CNPJVendedor.setFont(fonte);
        Painel1.add(lb_CNPJVendedor);

        try {
            mascaraCNPJVendedor = new MaskFormatter("###.###.###/####-##");
        } catch (ParseException excp) {
        }
        CNPJVendedor = new JFormattedTextField(mascaraCNPJVendedor);
        CNPJVendedor.setBounds(260, 130, 120, 20);
        Painel1.add(CNPJVendedor);
        CNPJVendedor.setEditable(false);
        CNPJVendedor.setBackground(cor2);
        CNPJVendedor.addFocusListener(cores);

        JLabel lb_IEVendedor = new JLabel("IE");
        lb_IEVendedor.setBounds(20, 150, 80, 14);
        lb_IEVendedor.setFont(fonte);
        Painel1.add(lb_IEVendedor);

        try {
            mascaraIEVendedor = new MaskFormatter("###.###.###");
        } catch (ParseException excp) {
        }
        IEVendedor = new JFormattedTextField(mascaraIEVendedor);
        IEVendedor.setBounds(20, 166, 110, 20);
        Painel1.add(IEVendedor);
        IEVendedor.setEditable(false);
        IEVendedor.setBackground(cor2);
        IEVendedor.addFocusListener(cores);

        JLabel lb_EnderecoVendedor = new JLabel("Endereço");
        lb_EnderecoVendedor.setBounds(140, 150, 80, 14);
        lb_EnderecoVendedor.setFont(fonte);
        Painel1.add(lb_EnderecoVendedor);

        tf_EnderecoVendedor = new JTextField();
        tf_EnderecoVendedor.setBounds(140, 166, 110, 20);
        Painel1.add(tf_EnderecoVendedor);
        tf_EnderecoVendedor.setEditable(false);
        tf_EnderecoVendedor.setBackground(cor2);
        tf_EnderecoVendedor.addFocusListener(cores);

        JLabel lb_BairroVendedor = new JLabel("Bairro");
        lb_BairroVendedor.setBounds(260, 150, 80, 14);
        lb_BairroVendedor.setFont(fonte);
        Painel1.add(lb_BairroVendedor);

        tf_BairroVendedor = new JTextField();
        tf_BairroVendedor.setBounds(260, 166, 120, 20);
        Painel1.add(tf_BairroVendedor);
        tf_BairroVendedor.setEditable(false);
        tf_BairroVendedor.setBackground(cor2);
        tf_BairroVendedor.addFocusListener(cores);

        JLabel lb_NumeroVendedor = new JLabel("Numero");
        lb_NumeroVendedor.setBounds(20, 185, 80, 14);
        lb_NumeroVendedor.setFont(fonte);
        Painel1.add(lb_NumeroVendedor);

        tf_NumeroVendedor = new JTextField();
        tf_NumeroVendedor.setBounds(20, 200, 110, 20);
        Painel1.add(tf_NumeroVendedor);
        tf_NumeroVendedor.setEditable(false);
        tf_NumeroVendedor.setBackground(cor2);
        tf_NumeroVendedor.addFocusListener(cores);

        JLabel lb_CidadeVendedor = new JLabel("Cidade");
        lb_CidadeVendedor.setBounds(140, 185, 80, 14);
        lb_CidadeVendedor.setFont(fonte);
        Painel1.add(lb_CidadeVendedor);

        tf_CidadeVendedor = new JTextField();
        tf_CidadeVendedor.setBounds(140, 200, 110, 20);
        Painel1.add(tf_CidadeVendedor);
        tf_CidadeVendedor.setEditable(false);
        tf_CidadeVendedor.setBackground(cor2);
        tf_CidadeVendedor.addFocusListener(cores);

        JLabel lb_EstadoVendedor = new JLabel("Estado");
        lb_EstadoVendedor.setBounds(260, 185, 80, 14);
        lb_EstadoVendedor.setFont(fonte);
        Painel1.add(lb_EstadoVendedor);

        tf_EstadoVendedor = new JTextField();
        tf_EstadoVendedor.setBounds(260, 200, 120, 20);
        Painel1.add(tf_EstadoVendedor);
        tf_EstadoVendedor.setEditable(false);
        tf_EstadoVendedor.setBackground(cor2);
        tf_EstadoVendedor.addFocusListener(cores);

        JLabel lb_SubTotal = new JLabel("Sub-Total");
        lb_SubTotal.setBounds(20, 218, 80, 14);
        lb_SubTotal.setFont(fonte);
        Painel1.add(lb_SubTotal);

        tf_subTotal = new JTextField();
        tf_subTotal.setEditable(false);
        tf_subTotal.setBackground(cor2);
        tf_subTotal.setBounds(20, 233, 110, 20);
        Painel1.add(tf_subTotal);
        tf_subTotal.addFocusListener(cores);

        JLabel lb_Descontos = new JLabel("Descontos");
        lb_Descontos.setBounds(140, 218, 80, 14);
        lb_Descontos.setFont(fonte);
        Painel1.add(lb_Descontos);

        tf_Descontos = new JTextField();
        tf_Descontos.setBounds(140, 233, 110, 20);
        Painel1.add(tf_Descontos);
        tf_Descontos.addFocusListener(cores);
        tf_Descontos.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Calcula();
            }
        });

        JLabel Total = new JLabel("Total");
        Total.setBounds(260, 218, 80, 14);
        Total.setFont(fonte);
        Painel1.add(Total);

        tf_Total = new JTextField();
        tf_Total.setBounds(260, 233, 120, 20);
        tf_Total.setEditable(false);
        tf_Total.setBackground(cor2);
        Painel1.add(tf_Total);
        tf_Total.addFocusListener(cores);

        JLabel lb_CondPagto = new JLabel("Cond. Pagto");
        lb_CondPagto.setBounds(20, 252, 80, 14);
        lb_CondPagto.setFont(fonte);
        Painel1.add(lb_CondPagto);

        cb_CondPagto = new JComboBox();
        cb_CondPagto.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        cb_CondPagto.addItem("1X");
        cb_CondPagto.addItem("2X");
        cb_CondPagto.addItem("3X");
        cb_CondPagto.addItem("4X");
        cb_CondPagto.addItem("5X");
        cb_CondPagto.addItem("6X");
        cb_CondPagto.addItem("7X");
        cb_CondPagto.addItem("8X");
        cb_CondPagto.setBackground(cor2);
        cb_CondPagto.setFont(fonte);
        cb_CondPagto.setBounds(20, 268, 110, 20);
        Painel1.add(cb_CondPagto);
        cb_CondPagto.addFocusListener(cores);

        JLabel lb_FormaPagto = new JLabel("Forma Pagto");
        lb_FormaPagto.setBounds(140, 252, 80, 14);
        lb_FormaPagto.setFont(fonte);
        Painel1.add(lb_FormaPagto);

        cb_FormaPagto = new JComboBox();
        cb_FormaPagto.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        cb_FormaPagto.addItem("Dinheiro");
        cb_FormaPagto.addItem("Cheque");
        cb_FormaPagto.addItem("Cartão");
        cb_FormaPagto.setFont(fonte);
        cb_FormaPagto.setBackground(cor2);
        cb_FormaPagto.setBounds(140, 268, 110, 20);
        Painel1.add(cb_FormaPagto);
        cb_FormaPagto.addFocusListener(cores);

        JLabel lb_CodCliente = new JLabel("Cliente");
        lb_CodCliente.setBounds(20, 40, 80, 14);
        lb_CodCliente.setFont(fonte);
        Painel2.add(lb_CodCliente);

        tf_CodCliente = new JTextField();
        tf_CodCliente.setBounds(20, 58, 80, 20);
        tf_CodCliente.setDocument(new MeuDocument());
        Painel2.add(tf_CodCliente);
        tf_CodCliente.addFocusListener(cores);
        tf_CodCliente.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                RecuperarCliente();
            }
        });

        PesquisaClie = new JButton("...");
        PesquisaClie.setBounds(104, 56, 31, 24);
        Painel2.add(PesquisaClie);
        PesquisaClie.addActionListener(trata);

        tf_NomeClie = new JTextField();
        tf_NomeClie.setBounds(140, 58, 243, 20);
        Painel2.add(tf_NomeClie);
        tf_NomeClie.setEditable(false);
        tf_NomeClie.setBackground(cor2);
        tf_NomeClie.addFocusListener(cores);

        JLabel lb_EnderecoClie = new JLabel("Endereço");
        lb_EnderecoClie.setBounds(20, 78, 80, 14);
        lb_EnderecoClie.setFont(fonte);
        Painel2.add(lb_EnderecoClie);

        tf_EnderecoClie = new JTextField();
        tf_EnderecoClie.setBounds(20, 94, 110, 20);
        Painel2.add(tf_EnderecoClie);
        tf_EnderecoClie.setEditable(false);
        tf_EnderecoClie.setBackground(cor2);
        tf_EnderecoClie.addFocusListener(cores);

        JLabel lb_BairroClie = new JLabel("Bairro");
        lb_BairroClie.setBounds(140, 78, 80, 14);
        lb_BairroClie.setFont(fonte);
        Painel2.add(lb_BairroClie);

        tf_BairroClie = new JTextField();
        tf_BairroClie.setBounds(140, 94, 110, 20);
        Painel2.add(tf_BairroClie);
        tf_BairroClie.setEditable(false);
        tf_BairroClie.setBackground(cor2);
        tf_BairroClie.addFocusListener(cores);

        JLabel lb_NumeroClie = new JLabel("Numero");
        lb_NumeroClie.setBounds(260, 78, 80, 14);
        lb_NumeroClie.setFont(fonte);
        Painel2.add(lb_NumeroClie);

        tf_NumeroClie = new JTextField();
        tf_NumeroClie.setBounds(260, 94, 123, 20);
        Painel2.add(tf_NumeroClie);
        tf_NumeroClie.setEditable(false);
        tf_NumeroClie.setBackground(cor2);
        tf_NumeroClie.addFocusListener(cores);

        JLabel lb_CidadeClie = new JLabel("Cidade");
        lb_CidadeClie.setBounds(20, 114, 80, 14);
        lb_CidadeClie.setFont(fonte);
        Painel2.add(lb_CidadeClie);

        tf_CidadeClie = new JTextField();
        tf_CidadeClie.setBounds(20, 130, 110, 20);
        Painel2.add(tf_CidadeClie);
        tf_CidadeClie.setEditable(false);
        tf_CidadeClie.setBackground(cor2);
        tf_CidadeClie.addFocusListener(cores);

        JLabel lb_EstadoClie = new JLabel("Estado");
        lb_EstadoClie.setBounds(140, 114, 110, 14);
        lb_EstadoClie.setFont(fonte);
        Painel2.add(lb_EstadoClie);

        tf_EstadoClie = new JTextField();
        tf_EstadoClie.setBounds(140, 130, 110, 20);
        Painel2.add(tf_EstadoClie);
        tf_EstadoClie.setEditable(false);
        tf_EstadoClie.setBackground(cor2);
        tf_EstadoClie.addFocusListener(cores);

        JLabel lb_Fone = new JLabel("Fone");
        lb_Fone.setBounds(260, 114, 80, 14);
        lb_Fone.setFont(fonte);
        Painel2.add(lb_Fone);

        try {
            mascaraFoneClie = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        FoneClie = new JFormattedTextField(mascaraFoneClie);
        FoneClie.setBounds(260, 130, 120, 20);
        Painel2.add(FoneClie);
        FoneClie.setEditable(false);
        FoneClie.setBackground(cor2);
        FoneClie.addFocusListener(cores);

        JLabel lb_Celular = new JLabel("Celular");
        lb_Celular.setBounds(20, 150, 80, 14);
        lb_Celular.setFont(fonte);
        Painel2.add(lb_Celular);

        try {
            mascaraCelularClie = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        CelularClie = new JFormattedTextField(mascaraCelularClie);
        CelularClie.setBounds(20, 166, 110, 20);
        Painel2.add(CelularClie);
        CelularClie.setEditable(false);
        CelularClie.setBackground(cor2);
        CelularClie.addFocusListener(cores);

        JLabel lb_EnderecoEnt = new JLabel("Endereço");
        lb_EnderecoEnt.setBounds(20, 30, 80, 14);
        lb_EnderecoEnt.setFont(fonte);
        Painel3.add(lb_EnderecoEnt);

        tf_EnderecoEnt = new JTextField();
        tf_EnderecoEnt.setBounds(20, 48, 110, 20);
        tf_Codigo.setText(codigoString);
        Painel3.add(tf_EnderecoEnt);
        tf_EnderecoEnt.addFocusListener(cores);

        JLabel lb_BairroEnt = new JLabel("Bairro");
        lb_BairroEnt.setBounds(140, 30, 80, 14);
        lb_BairroEnt.setFont(fonte);
        Painel3.add(lb_BairroEnt);

        tf_BairroEnt = new JTextField();
        tf_BairroEnt.setBounds(140, 48, 110, 20);
        Painel3.add(tf_BairroEnt);
        tf_BairroEnt.addFocusListener(cores);

        JLabel lb_NumeroEnt = new JLabel("Numero");
        lb_NumeroEnt.setBounds(260, 30, 80, 14);
        lb_NumeroEnt.setFont(fonte);
        Painel3.add(lb_NumeroEnt);

        tf_NumeroEnt = new JTextField();
        tf_NumeroEnt.setBounds(260, 48, 110, 20);
        tf_NumeroEnt.setDocument(new MeuDocument());
        Painel3.add(tf_NumeroEnt);
        tf_NumeroEnt.addFocusListener(cores);

        JLabel lb_CidadeEnt = new JLabel("Cidade");
        lb_CidadeEnt.setBounds(20, 68, 80, 14);
        lb_CidadeEnt.setFont(fonte);
        Painel3.add(lb_CidadeEnt);

        Cb_CidadeEnt = new JComboBox();
        Cb_CidadeEnt.setBounds(20, 84, 110, 20);
        Cb_CidadeEnt.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        for (int i = 0; i < lista6.size(); i++) {
            ArrayCidade aux = lista6.get(i);
            if (lista6.size() != 0) {//Se lista.size for diferente de zero
                String n = aux.getCidade();
                Cb_CidadeEnt.addItem(n);
            }
        }
        Cb_CidadeEnt.setBackground(cor2);
        Cb_CidadeEnt.setFont(fonte);
        Painel3.add(Cb_CidadeEnt);
        Cb_CidadeEnt.addFocusListener(cores);

        JLabel lb_EstadoEnt = new JLabel("Estado");
        lb_EstadoEnt.setBounds(140, 68, 80, 14);
        lb_EstadoEnt.setFont(fonte);
        Painel3.add(lb_EstadoEnt);

        Cb_EstadoEnt = new JComboBox();
        Cb_EstadoEnt.setBounds(140, 84, 110, 20);
        Cb_EstadoEnt.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        for (int i = 0; i < lista6.size(); i++) {
            ArrayCidade aux = lista6.get(i);
            if (lista6.size() != 0) {//Se lista.size for diferente de zero
                String n = aux.getEstado();
                Cb_EstadoEnt.addItem(n);
            }
        }
        Cb_EstadoEnt.setBackground(cor2);
        Cb_EstadoEnt.setFont(fonte);
        Painel3.add(Cb_EstadoEnt);
        Cb_EstadoEnt.addFocusListener(cores);

        JLabel lb_ContatoEnt = new JLabel("Contato");
        lb_ContatoEnt.setBounds(260, 68, 80, 14);
        lb_ContatoEnt.setFont(fonte);
        Painel3.add(lb_ContatoEnt);

        try {
            mascaraContatoEnt = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ContatoEnt = new JFormattedTextField(mascaraContatoEnt);
        ContatoEnt.setBounds(260, 84, 123, 20);
        Painel3.add(ContatoEnt);
        ContatoEnt.addFocusListener(cores);

        JLabel lb_CodigoProd = new JLabel("Cod. Produto");
        lb_CodigoProd.setBounds(15, 14, 80, 14);
        lb_CodigoProd.setFont(fonte);
        Painel4.add(lb_CodigoProd);

        tf_CodProd = new JTextField();
        tf_CodProd.setBounds(90, 12, 50, 20);
        Painel4.add(tf_CodProd);
        tf_CodProd.setDocument(new MeuDocument());
        tf_CodProd.addFocusListener(cores);
        tf_CodProd.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                RecuperaProduto();
            }
        });

        PesquisaProd = new JButton("...");
        PesquisaProd.setBounds(146, 9, 40, 26);
        Painel4.add(PesquisaProd);
        PesquisaProd.addActionListener(trata);

        tf_NomeProd = new JTextField();
        tf_NomeProd.setBounds(197, 12, 130, 20);
        Painel4.add(tf_NomeProd);
        tf_NomeProd.setEditable(false);
        tf_NomeProd.setBackground(cor2);
        tf_NomeProd.addFocusListener(cores);

        JLabel lb_DescricaoProd = new JLabel("Descriçao");
        lb_DescricaoProd.setBounds(330, 14, 80, 14);
        lb_DescricaoProd.setFont(fonte);
        Painel4.add(lb_DescricaoProd);

        tf_DescriProd = new JTextField();
        tf_DescriProd.setBounds(389, 12, 130, 20);
        Painel4.add(tf_DescriProd);
        tf_DescriProd.setEditable(false);
        tf_DescriProd.setBackground(cor2);
        tf_DescriProd.addFocusListener(cores);

        JLabel lb_Modelo = new JLabel("Modelo");
        lb_Modelo.setBounds(523, 14, 80, 14);
        lb_Modelo.setFont(fonte);
        Painel4.add(lb_Modelo);

        tf_ModeloProd = new JTextField();
        tf_ModeloProd.setBounds(566, 12, 110, 20);
        Painel4.add(tf_ModeloProd);
        tf_ModeloProd.setEditable(false);
        tf_ModeloProd.setBackground(cor2);
        tf_ModeloProd.addFocusListener(cores);

        JLabel lb_TotalProd = new JLabel("Total");
        lb_TotalProd.setBounds(678, 14, 80, 14);
        lb_TotalProd.setFont(fonte);
        Painel4.add(lb_TotalProd);

        tf_TotalProd = new JTextField();
        tf_TotalProd.setBounds(708, 12, 110, 20);
        Painel4.add(tf_TotalProd);
        tf_TotalProd.setEditable(false);
        tf_TotalProd.setBackground(cor2);
        tf_TotalProd.addFocusListener(cores);

        OKProd = new JButton("OK");
        OKProd.setBounds(825, 9, 51, 26);
        Painel4.add(OKProd);
        OKProd.addActionListener(trata);

        Modelo = new DefaultTableModel();
        JTable Tabela = new JTable(Modelo);//Constrói a tabela
        Modelo.addColumn("Codigo");//Cria colunas
        Modelo.addColumn("Produto");
        Modelo.addColumn("Descrição");
        Modelo.addColumn("Modelo");
        Modelo.addColumn("Total");
        JScrollPane rolagem2 = new JScrollPane(Tabela);
        rolagem2.setBounds(2, 3, 881, 160);
        Painel5.add(rolagem2);

        JLabel lb_Descricao = new JLabel("Descrição");
        lb_Descricao.setBounds(10, 4, 80, 14);
        lb_Descricao.setFont(fonte);
        Painel6.add(lb_Descricao);

        area = new JTextArea();
        area.setBorder(BorderFactory.createLineBorder(cor1.darkGray, 0));//Borda em volta no JTextArea
        JScrollPane rolagem1 = new JScrollPane(area);
        rolagem1.setBounds(10, 20, 760, 68);
        Painel6.add(rolagem1);
        area.addFocusListener(cores);

        Seleciona = new JCheckBox();
        Seleciona.setBounds(69, 5, 22, 15);
        Painel6.add(Seleciona);
        Seleciona.addItemListener(manuseia);

        Confirmar = new JButton("Confirmar");
        Confirmar.setBounds(783, 15, 92, 26);
        Painel6.add(Confirmar);
        Confirmar.setEnabled(false);
        Confirmar.addActionListener(trata);

        Cancelar = new JButton("Cancelar");
        Cancelar.setBounds(783, 50, 92, 26);
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
        setSize(900, 652);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    private void GravarItens() {
        String textocodvenda, textocodprod, textototal, InserNome = "", InserDescricao = "", InserModelo = "";
        int InserCodVenda = 0, InserCodProd = 0, InserCodItemVenda = 0, InserCodigo = 0;
        float InserTotal = 0;

        if (!"".equals(tf_Codigo.getText().trim())) {//Se campo não esta vazio//trim tira espaço
            textocodvenda = tf_Codigo.getText();
            InserCodVenda = Integer.parseInt(textocodvenda);//Conversão de String para Integer
            String StringCodItemVen = tf_Codigo.getText();
            InserCodItemVenda = Integer.parseInt(StringCodItemVen);//Conversão Intrger para String
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

        if (!"".equals(tf_DescriProd.getText())) {//Se campo não esta vazio
            InserDescricao = tf_DescriProd.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Descrição Produto esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_DescriProd.setText("");//Limpa o campo
            tf_DescriProd.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_ModeloProd.getText())) {//Se campo não esta vazio
            InserModelo = tf_ModeloProd.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Modelo Produto esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_ModeloProd.setText("");//Limpa o campo
            tf_ModeloProd.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_TotalProd.getText())) {//Se campo não esta vazio
            textototal = tf_TotalProd.getText();
            InserTotal = Float.parseFloat(textototal);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Total Produto esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_TotalProd.setText("");//Limpa o campo
            tf_TotalProd.grabFocus();//Focaliza o campo
        }

        InserCodigo = CodigoItem;

        if (!"".equals(tf_Codigo.getText()) & !"".equals(tf_NomeProd.getText()) & !"".equals(tf_DescriProd.getText()) & !"".equals(tf_CodProd.getText()) & !"".equals(tf_ModeloProd.getText()) & !"".equals(tf_TotalProd.getText())) {
            ArrayItenVenda aux = new ArrayItenVenda(InserNome, InserDescricao, InserModelo, InserCodItemVenda, InserCodVenda, InserCodProd, InserCodigo, InserTotal);
            lista5.add(aux);
            OKProduto();
            Confirmar.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Total de Itens Vendidos: " + lista5.size(), "Saída de Produtos", JOptionPane.WARNING_MESSAGE);
            tf_Codigo.setEditable(false);
            Cancelar.setEnabled(false);
            CodigoItem = CodigoItem + 1;
            String StringCodigo = tf_CodProd.getText();
            int IntCodigo = Integer.parseInt(StringCodigo);
            for (int i = 0; i < lista4.size(); i++) {
                ArrayProdutos n = lista4.get(i);
                if (IntCodigo == n.getCodigo()) {
                    lista4.remove(n);
                    LimparProduto();
                }
            }
            ArquivoProduto();
            float soma = 0;
            soma = InserTotal;
            Numero = Numero + soma;
            String Textosoma = Float.toString(Numero);
            tf_subTotal.setText(Textosoma);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel executar operação no sistema", "Erro Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void GravarVenda() {
        String textocod, textocodvendedor, textocodcliente, textocoditenven, textosubtotal, textodescontos, textototal, Textonumeroent, InserDataEmissao = "", InserSituacao = "", InserCondPagto = "", InserFormaPagto = "", InserEnderecoEnt = "", InserBairroEnt = "", InserCidadeEnt = "", InserEstadoEnt = "", InserContato = "", InserDescricao = null;
        int InserCodigo = 0, InserCodVendedor = 0, InserCodCliente = 0, InserCodItemVenda = 0, InserNumeroEnt = 0;
        float InserSubTotal = 0, InserDescontos = 0, InserTotal = 0;

        if (!"".equals(tf_Codigo.getText())) {//Se campo não esta vazio
            textocod = tf_Codigo.getText();
            InserCodigo = Integer.parseInt(textocod);//Conversão de String para Integer
            textocoditenven = tf_Codigo.getText();
            InserCodItemVenda = Integer.parseInt(textocoditenven);//Conversão Intrger para String
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

        InserSituacao = (String) Cb_Situacao.getSelectedItem();//Converte para String e joga para Insersexo

        if (!"Selecione".equals(cb_FormaPagto.getSelectedItem())) {//Se campo não esta vazio
            InserFormaPagto = (String) cb_FormaPagto.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Forma Pagamento esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            cb_FormaPagto.setSelectedItem("Selecione");//Campo recebe Selecione
            cb_FormaPagto.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(cb_CondPagto.getSelectedItem())) {//Se campo não esta vazio
            InserCondPagto = (String) cb_CondPagto.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Condição de Pagamento esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            cb_CondPagto.setSelectedItem("Selecione");//Campo recebe Selecione
            cb_CondPagto.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_CodVendedor.getText())) {//Se campo não esta vazio
            textocodvendedor = tf_CodVendedor.getText();
            InserCodVendedor = Integer.parseInt(textocodvendedor);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código Vendedor esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_CodVendedor.setText("");//Limpa o campo
            tf_CodVendedor.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_CodCliente.getText())) {//Se campo não esta vazio
            textocodcliente = tf_CodCliente.getText();
            InserCodCliente = Integer.parseInt(textocodcliente);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código Cliente esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_CodCliente.setText("");//Limpa o campo
            tf_CodCliente.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_subTotal.getText())) {//Se campo não esta vazio
            textosubtotal = tf_subTotal.getText();
            InserSubTotal = Float.parseFloat(textosubtotal);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Sub Total esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_subTotal.setText("");//Limpa o campo
            tf_subTotal.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Descontos.getText())) {//Se campo não esta vazio
            textodescontos = tf_Descontos.getText();
            InserDescontos = Float.parseFloat(textodescontos);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Descontos esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Descontos.setText("");//Limpa o campo
            tf_Descontos.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Total.getText())) {//Se campo não esta vazio
            textototal = tf_Total.getText();
            InserTotal = Float.parseFloat(textototal);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Total esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Total.setText("");//Limpa o campo
            tf_Total.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_EnderecoEnt.getText())) {//Se campo não esta vazio
            InserEnderecoEnt = tf_EnderecoEnt.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Endereço esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_EnderecoEnt.setText("");//Limpa o campo
            tf_EnderecoEnt.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_BairroEnt.getText())) {//Se campo não esta vazio
            InserBairroEnt = tf_BairroEnt.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Bairro esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_BairroEnt.setText("");//Limpa o campo
            tf_BairroEnt.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_NumeroEnt.getText())) {//Se campo não esta vazio
            Textonumeroent = tf_NumeroEnt.getText();
            InserNumeroEnt = Integer.parseInt(Textonumeroent);//Converssão String para int
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Numero esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_NumeroEnt.setText("");//Limpa o campo
            tf_NumeroEnt.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(Cb_CidadeEnt.getSelectedItem())) {//Se campo não esta vazio
            InserCidadeEnt = (String) Cb_CidadeEnt.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Cidade esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            Cb_CidadeEnt.setSelectedItem("Selecione");//Campo recebe Selecione
            Cb_CidadeEnt.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(Cb_EstadoEnt.getSelectedItem())) {//Se campo não esta vazio
            InserEstadoEnt = (String) Cb_EstadoEnt.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Estado esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            Cb_EstadoEnt.setSelectedItem("Selecione");//Campo recebe Selecione
            Cb_EstadoEnt.grabFocus();//Focaliza o campo
        }

        if (!"".equals(ContatoEnt.getText())) {//Se campo não esta vazio
            InserContato = ContatoEnt.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Contato esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            ContatoEnt.setText("");//Limpa o campo
            ContatoEnt.grabFocus();//Focaliza o campo
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

        if (!"".equals(tf_Codigo.getText()) & !"  /  /    ".equals(DataEmissao.getText()) & !"Selecione".equals(cb_CondPagto.getSelectedItem()) & !"Selecione".equals(cb_FormaPagto.getSelectedItem()) & !"".equals(tf_CodVendedor.getText()) & !"".equals(tf_CodCliente.getText()) & !"".equals(tf_subTotal.getText()) & !"".equals(tf_Descontos.getText()) & !"".equals(tf_Total.getText()) & !"".equals(tf_EnderecoEnt.getText()) & !"".equals(tf_BairroEnt.getText()) & !"".equals(tf_NumeroEnt.getText()) & !"Selecione".equals(Cb_CidadeEnt.getSelectedItem()) & !"Selecione".equals(Cb_EstadoEnt.getSelectedItem()) & !"(  )    -    ".equals(ContatoEnt.getText()) & InserDescricao != null) {
            ArrayNovaVenda aux = new ArrayNovaVenda(InserDataEmissao, InserSituacao, InserCondPagto, InserFormaPagto, InserDescricao, InserEnderecoEnt, InserBairroEnt, InserCidadeEnt, InserEstadoEnt, InserContato, InserCodigo, InserCodVendedor, InserCodCliente, InserCodItemVenda, InserNumeroEnt, InserSubTotal, InserDescontos, InserTotal);
            lista1.add(aux);
            JOptionPane.showMessageDialog(null, "Número de Vendas cadastradas: " + lista1.size(), "Entrada de Dados", JOptionPane.WARNING_MESSAGE);
            LimparProduto();
            LimparVendas();
            Confirmar.setEnabled(false);
            Cancelar.setEnabled(true);
            tf_Codigo.setEditable(true);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel executar operação no sistema", "Erro Fatal", JOptionPane.ERROR_MESSAGE);
        }
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
                    ArrayNovaVenda aux = lista1.get(i);
                    if (n == (aux.getCodigo())) {
                        encontrado = true;
                    }
                }
                if (encontrado == true) {
                    int resposta = JOptionPane.showConfirmDialog(null, "A Venda " + tf_Codigo.getText() + " ja esta cadastrada? ", "Venda", JOptionPane.WARNING_MESSAGE);
                    int codigoint = lista1.size();
                    String codigoString = Integer.toString(codigoint + 1);
                    tf_Codigo.setText(codigoString);
                }
            }
        }
    }

    private void Confirmar() {
        GravarVenda();
    }

    private void OKProduto() {
        Modelo.addRow(new Object[]{tf_CodProd.getText(), tf_NomeProd.getText(), tf_DescriProd.getText(), tf_ModeloProd.getText(), tf_TotalProd.getText()});//Adiciona linha na tabela
        tf_CodProd.grabFocus();
    }

    private void LimparProduto() {
        tf_CodProd.setText("");
        tf_NomeProd.setText("");
        tf_DescriProd.setText("");
        tf_ModeloProd.setText("");
        tf_TotalProd.setText("");
    }

    private void LimparVendas() {
        int codigoint = lista1.size();
        String codigoString = Integer.toString(codigoint + 1);
        tf_Codigo.setText(codigoString);
        DataEmissao.setText("");
        tf_subTotal.setText("");
        tf_Descontos.setText("");
        tf_Total.setText("");
        Cb_Situacao.setSelectedItem("Aberto");//Campo recebe Selecione
        tf_CodVendedor.setText("");
        tf_NomeVendedor.setText("");
        tf_ComissaoVendedor.setText("");
        tf_EmpresaVendedor.setText("");
        CNPJVendedor.setText("");
        IEVendedor.setText("");
        tf_EnderecoVendedor.setText("");
        tf_BairroVendedor.setText("");
        tf_NumeroVendedor.setText("");
        tf_CidadeVendedor.setText("");
        tf_EstadoVendedor.setText("");
        cb_CondPagto.setSelectedItem("Selecione");//Campo recebe Selecione
        tf_CodCliente.setText("");
        tf_NomeClie.setText("");
        cb_FormaPagto.setSelectedItem("Selecione");//Campo recebe Selecione
        tf_EnderecoClie.setText("");
        tf_BairroClie.setText("");
        tf_NumeroClie.setText("");
        tf_CidadeClie.setText("");
        tf_EstadoClie.setText("");
        FoneClie.setText("");
        CelularClie.setText("");
        tf_EnderecoEnt.setText("");
        tf_BairroEnt.setText("");
        tf_NumeroEnt.setText("");
        Cb_CidadeEnt.setSelectedItem("Selecione");//Campo recebe Selecione
        Cb_EstadoEnt.setSelectedItem("Selecione");//Campo recebe Selecione
        ContatoEnt.setText("");
        Seleciona.setSelected(false);//Desmarca JCheckbox
        InserSeleciona = null;//Limpa Variavel
        area.setText("");
        if (Modelo.getRowCount() == 0) {//Verifica se há linhas na tabela
        } else {
            if (Modelo.getRowCount() >= 1) {
                for (int i = 0; i <= Modelo.getRowCount(); i++) {//Contador
                    Modelo.setNumRows(0);//Exclui Todas as linhas
                }
            }
        }
    }

    private void RecuperaProduto() {
        if (!"".equals(tf_CodProd.getText())) {
            if (lista4.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "Não há Produtos cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                String Texto = tf_CodProd.getText();
                int n = Integer.parseInt(Texto);//Conversão String para Integer
                for (int i = 0; i < lista4.size(); i++) {
                    ArrayProdutos aux = lista4.get(i);
                    if (n == (aux.getCodigo())) {
                        int TextoINTCodigo = aux.getCodigo();
                        String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                        tf_CodProd.setText(TextoCodigo);
                        String TextoNome = aux.getNome();
                        tf_NomeProd.setText(TextoNome);
                        String TextoDescricao = aux.getDescricao();
                        tf_DescriProd.setText(TextoDescricao);
                        String TextoModelo = aux.getModelo();
                        tf_ModeloProd.setText(TextoModelo);
                        float TextoFLOATValor_Total = aux.getValor_Total();
                        String TextoValor_Total = Float.toString(TextoFLOATValor_Total);//Conversão float para String
                        tf_TotalProd.setText(TextoValor_Total);
                    }
                }
            }
        }
    }

    private void RecuperarVendedor() {
        if (!"".equals(tf_CodVendedor.getText())) {
            if (lista2.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "Não há Vendedor cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                String Texto = tf_CodVendedor.getText();
                int n = Integer.parseInt(Texto);//Conversão String para Integer
                for (int i = 0; i < lista2.size(); i++) {
                    ArrayVendedor aux = lista2.get(i);
                    if (n == (aux.getCodigo())) {
                        int TextoINTCodigo = aux.getCodigo();
                        String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                        tf_CodVendedor.setText(TextoCodigo);
                        String TextoNome = aux.getNome();
                        tf_NomeVendedor.setText(TextoNome);
                        String TextoComissao = aux.getComissao();
                        tf_ComissaoVendedor.setText(TextoComissao);
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
                    }
                }
            }
        }
    }

    private void RecuperarCliente() {
        if (!"".equals(tf_CodCliente.getText())) {
            if (lista3.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "Não há Clientes cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                String Texto = tf_CodCliente.getText();
                int n = Integer.parseInt(Texto);//Conversão String para Integer
                for (int i = 0; i < lista3.size(); i++) {
                    ArrayCliente aux = lista3.get(i);
                    if (n == (aux.getCodigo())) {
                        int TextoINTCodigo = aux.getCodigo();
                        String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                        tf_CodCliente.setText(TextoCodigo);
                        String TextoNome = aux.getNome();
                        tf_NomeClie.setText(TextoNome);
                        String TextoEndereco = aux.getEndereco();
                        tf_EnderecoClie.setText(TextoEndereco);
                        String TextoBairro = aux.getBairro();
                        tf_BairroClie.setText(TextoBairro);
                        int TextoINTNumero = aux.getNumero();
                        String TextoNumero = Integer.toString(TextoINTNumero);//Conversão int para String
                        tf_NumeroClie.setText(TextoNumero);
                        String TextoCidade = aux.getCidade();
                        tf_CidadeClie.setText(TextoCidade);
                        String TextoEstado = aux.getEstado();
                        tf_EstadoClie.setText(TextoEstado);
                        String TextoFone = aux.getFone();
                        FoneClie.setText(TextoFone);
                        String TextoCelular = aux.getCelular();
                        CelularClie.setText(TextoCelular);
                    }
                }
            }
        }
    }

    private void Vendedor() {
        if (lista2.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Vendedores cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            //VerFuncionario aux = new VerFuncionario(lista2);
        }
    }

    private void Clientes() {
        if (lista3.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Clientes cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            VerCliente aux = new VerCliente(lista3);
        }
    }

    private void Produtos() {
        if (lista4.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Produtos cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            VerProduto aux = new VerProduto();
        }
    }

    private void Vendas() {
        if (lista1.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Vendas cadastradas na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            ConsultaVenda aux = new ConsultaVenda();
        }
    }

    private void Calcula() {
        String textoSubTotal1, textoSubTotal2, textoDescontos, texto = "";
        float SubTotal1 = 0, SubTotal2 = 0, Descontos = 0, Valor_Total1, Valor_Total2;

        if (!"".equals(tf_Descontos.getText()) & !"".equals(tf_subTotal.getText())) {
            textoSubTotal1 = tf_subTotal.getText();
            SubTotal1 = Float.parseFloat(textoSubTotal1);//Conversão de String para float
            textoDescontos = tf_Descontos.getText();
            Descontos = Float.parseFloat(textoDescontos);//Conversão de String para Float
            Valor_Total1 = (SubTotal1 / 100) * Descontos;
            textoSubTotal2 = tf_subTotal.getText();
            SubTotal2 = Float.parseFloat(textoSubTotal2);//Conversão de String para float
            Valor_Total2 = SubTotal2 - Valor_Total1;
            texto = Float.toString(Valor_Total2);//Conversão de float para String
            tf_Total.setText(texto);
        }
    }

    private void LerArquivoVendedor() {
        try {
            FileInputStream Arq = new FileInputStream("Vendedor.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista2 = (ArrayList<ArrayVendedor>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoCliente() {
        try {
            FileInputStream Arq = new FileInputStream("Cliente.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista3 = (ArrayList<ArrayCliente>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoProdutos() {
        try {
            FileInputStream Arq = new FileInputStream("Produto.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista4 = (ArrayList<ArrayProdutos>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ArquivoVenda() {
        try {
            FileOutputStream arqDados = new FileOutputStream("Vendas.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista1);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ArquivoItemVenda() {
        try {
            FileOutputStream arqDados = new FileOutputStream("ItemVendas.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista5);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoItemVenda() {
        try {
            FileInputStream Arq = new FileInputStream("ItemVendas.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista5 = (ArrayList<ArrayItenVenda>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Vendas.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista1 = (ArrayList<ArrayNovaVenda>) entra.readObject();
            entra.close();
            int codigoint = lista1.size();
            String codigoString = Integer.toString(codigoint + 1);
            tf_Codigo.setText(codigoString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ArquivoProduto() {
        try {
            FileOutputStream arqDados = new FileOutputStream("Produto.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista4);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoCidade() {
        try {
            FileInputStream Arq = new FileInputStream("Cidade.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista6 = (ArrayList<ArrayCidade>) entra.readObject();
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
            if (evento.getSource() == PesquisaClie) {
                Clientes();
            }
            if (evento.getSource() == PesquisaPed) {
                Vendas();
            }
            if (evento.getSource() == PesquisaProd) {
                Produtos();
            }
            if (evento.getSource() == OKProd) {
                GravarItens();
                ArquivoItemVenda();
            }
            if (evento.getSource() == Confirmar) {
                Confirmar();
                ArquivoVenda();
            }
            if (evento.getSource() == Cancelar) {
                LimparProduto();
                LimparVendas();
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
            if (evento.getSource() == Cb_Situacao) {
                Cb_Situacao.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CodVendedor) {
                tf_CodVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NomeVendedor) {
                tf_NomeVendedor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_ComissaoVendedor) {
                tf_ComissaoVendedor.setBackground(Color.YELLOW);
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
            if (evento.getSource() == tf_subTotal) {
                tf_subTotal.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Descontos) {
                tf_Descontos.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Total) {
                tf_Total.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == cb_CondPagto) {
                cb_CondPagto.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == cb_FormaPagto) {
                cb_FormaPagto.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CodCliente) {
                tf_CodCliente.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NomeClie) {
                tf_NomeClie.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_EnderecoClie) {
                tf_EnderecoClie.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_BairroClie) {
                tf_BairroClie.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CidadeClie) {
                tf_CidadeClie.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_EstadoClie) {
                tf_EstadoClie.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == FoneClie) {
                FoneClie.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == CelularClie) {
                CelularClie.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_EnderecoEnt) {
                tf_EnderecoEnt.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NumeroClie) {
                tf_NumeroClie.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_BairroEnt) {
                tf_BairroEnt.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NumeroEnt) {
                tf_NumeroEnt.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == Cb_CidadeEnt) {
                Cb_CidadeEnt.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == Cb_EstadoEnt) {
                Cb_EstadoEnt.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == ContatoEnt) {
                ContatoEnt.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CodProd) {
                tf_CodProd.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NomeProd) {
                tf_NomeProd.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_DescriProd) {
                tf_DescriProd.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_ModeloProd) {
                tf_ModeloProd.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_TotalProd) {
                tf_TotalProd.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == area) {
                area.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tf_Codigo.setBackground(Color.WHITE);
            DataEmissao.setBackground(Color.WHITE);
            Cb_Situacao.setBackground(Color.WHITE);
            tf_CodVendedor.setBackground(Color.WHITE);
            tf_NomeVendedor.setBackground(Color.WHITE);
            tf_ComissaoVendedor.setBackground(Color.WHITE);
            tf_EmpresaVendedor.setBackground(Color.WHITE);
            CNPJVendedor.setBackground(Color.WHITE);
            IEVendedor.setBackground(Color.WHITE);
            tf_EnderecoVendedor.setBackground(Color.WHITE);
            tf_BairroVendedor.setBackground(Color.WHITE);
            tf_NumeroVendedor.setBackground(Color.WHITE);
            tf_CidadeVendedor.setBackground(Color.WHITE);
            tf_EstadoVendedor.setBackground(Color.WHITE);
            tf_subTotal.setBackground(Color.WHITE);
            tf_Descontos.setBackground(Color.WHITE);
            tf_Total.setBackground(Color.WHITE);
            cb_CondPagto.setBackground(Color.WHITE);
            cb_FormaPagto.setBackground(Color.WHITE);
            tf_CodCliente.setBackground(Color.WHITE);
            tf_NomeClie.setBackground(Color.WHITE);
            tf_EnderecoClie.setBackground(Color.WHITE);
            tf_BairroClie.setBackground(Color.WHITE);
            tf_NumeroClie.setBackground(Color.WHITE);
            tf_CidadeClie.setBackground(Color.WHITE);
            tf_EstadoClie.setBackground(Color.WHITE);
            FoneClie.setBackground(Color.WHITE);
            CelularClie.setBackground(Color.WHITE);
            tf_EnderecoEnt.setBackground(Color.WHITE);
            tf_BairroEnt.setBackground(Color.WHITE);
            tf_NumeroEnt.setBackground(Color.WHITE);
            Cb_CidadeEnt.setBackground(Color.WHITE);
            Cb_EstadoEnt.setBackground(Color.WHITE);
            ContatoEnt.setBackground(Color.WHITE);
            tf_CodProd.setBackground(Color.WHITE);
            tf_NomeProd.setBackground(Color.WHITE);
            tf_DescriProd.setBackground(Color.WHITE);
            tf_ModeloProd.setBackground(Color.WHITE);
            tf_TotalProd.setBackground(Color.WHITE);
            area.setBackground(Color.WHITE);
        }
    }
}




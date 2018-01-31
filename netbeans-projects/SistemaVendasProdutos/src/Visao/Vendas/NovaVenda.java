package Visao.Vendas;

import Arquivos.*;
import ClassPadrao.*;
import Controle.CidadeControl;
import Controle.ClienteControl;
import Controle.ItemVendaAdiciona;
import Controle.ItemVendaControl;
import Controle.ProdutoControl;
import Controle.VendasControl;
import Controle.VendedorControl;
import Visao.Cliente.VerTodosCadasClie;
import Visao.Produto.VerTodosCadasProd;
import Visao.Vendedor.VerTodosCadasVendedor;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

public class NovaVenda extends JDialog implements ActionListener, FocusListener, ItemListener {

    private JTextField tfCodigo, tfSituacao, tfCodCliente, tfNomeClie, tfEnderecoClie, tfBairroClie, tfCidadeClie, tfEstadoClie, tfNumeroClie, tfNomeVendedor, tfCodVendedor, tfComissaoVendedor, tfEmpresaVendedor, tfEnderecoVendedor, tfBairroVendedor, tfEstadoVendedor, tfNumeroVendedor, tfCidadeVendedor, tfSubTotal, tfDescontos, tfTotal, tfCodProd, tfNomeProd, tfDescriProd, tfModeloProd, tfTotalProd, tfEnderecoEnt, tfBairroEnt, tfNumeroEnt;
    private JComboBox cbFormaPagto, cbCondPagto, cbCidadeEnt, cbEstadoEnt;
    private JFormattedTextField ftfFoneClie, ftfCelularClie, ftfDataEmissao, ftfCNPJVendedor, ftfIEVendedor, ftfContatoEnt;
    private MaskFormatter mascaraFoneClie, mascaraCelularClie, mascaraDataEmissao, mascaraCNPJVendedor, mascaraIEVendedor, mascaraContatoEnt;
    private JButton btPesquisaPed, btPesquisaVendedor, btPesquisaClie, btPesquisaProd, btOKProd, btConfirmar, btCancelar;
    private JCheckBox checkSeleciona;
    private JTextArea taDescricao;
    private JTable tabela;
    private String descricao = null;
    private double subTotal = 0;
    private ItemVendaControl controlItemVenda;
    private VendasControl controlVendas;
    private Arquivo arquivo;
    private LerArquivo lerArquivo;
    private CidadeControl controlCidade;
    private ProdutoControl controlProduto;
    private VendedorControl controlVendedor;
    private ClienteControl controlCliente;
    private TableModelItemsVendas tableModel;
    private JTextFieldDouble jTextFieldDouble;
    private ItemVendaAdiciona itemVendaAdiciona;
    private RenderizadoraItemVenda rendener;

    public NovaVenda() {
        setTitle("Nova Venda");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(10, 10, 410, 311);
        tela.add(painel1);
        painel1.setBorder(BorderFactory.createTitledBorder("Dados Venda"));
        JPanel painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBounds(425, 10, 410, 200);
        tela.add(painel2);
        painel2.setBorder(BorderFactory.createTitledBorder("Dados Cliente"));
        JPanel painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(425, 207, 410, 113);
        tela.add(painel3);
        painel3.setBorder(BorderFactory.createTitledBorder("Dados Entrega"));
        JPanel painel4 = new JPanel();
        painel4.setLayout(null);
        painel4.setBounds(11, 321, 885, 51);
        tela.add(painel4);
        painel4.setBorder(BorderFactory.createTitledBorder(""));
        JPanel painel5 = new JPanel();
        painel5.setLayout(null);
        painel5.setBounds(11, 368, 885, 165);
        tela.add(painel5);
        painel5.setBorder(BorderFactory.createTitledBorder(""));
        JPanel painel6 = new JPanel();
        painel6.setLayout(null);
        painel6.setBounds(11, 529, 885, 90);
        tela.add(painel6);
        painel6.setBorder(BorderFactory.createTitledBorder(""));
        controlVendas = new VendasControl();
        controlItemVenda = new ItemVendaControl();
        controlCidade = new CidadeControl();
        controlProduto = new ProdutoControl();
        controlVendedor = new VendedorControl();
        controlCliente = new ClienteControl();
        tableModel = new TableModelItemsVendas(controlItemVenda);
        arquivo = new Arquivo();
        lerArquivo = new LerArquivo();
        itemVendaAdiciona = new ItemVendaAdiciona();
        jTextFieldDouble = new JTextFieldDouble();
        controlCidade.lerArquivo(lerArquivo);
        controlProduto.lerArquivo(lerArquivo);
        controlVendedor.lerArquivo(lerArquivo);
        controlCliente.lerArquivo(lerArquivo);
        controlVendas.lerArquivo(lerArquivo);
        itemVendaAdiciona.lerArquivo(lerArquivo);
        rendener = new RenderizadoraItemVenda();

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 40, 80, 14);
        lbCodigo.setFont(fonte);
        painel1.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 58, 80, 20);
        tfCodigo.setDocument(new MeuDocument());
        tfCodigo.setText(Integer.toString(controlVendas.ultimoCadasCod() + 1));
        painel1.add(tfCodigo);
        tfCodigo.addFocusListener(this);

        btPesquisaPed = new JButton("...");
        btPesquisaPed.setBounds(104, 56, 31, 24);
        painel1.add(btPesquisaPed);
        btPesquisaPed.addActionListener(this);

        JLabel lbDataEmissao = new JLabel("Emissão");
        lbDataEmissao.setBounds(140, 40, 80, 14);
        lbDataEmissao.setFont(fonte);
        painel1.add(lbDataEmissao);

        try {
            mascaraDataEmissao = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
        }
        ftfDataEmissao = new JFormattedTextField(mascaraDataEmissao);
        ftfDataEmissao.setBounds(140, 58, 110, 20);
        painel1.add(ftfDataEmissao);
        ftfDataEmissao.addFocusListener(this);

        JLabel lbSituacao = new JLabel("Situação");
        lbSituacao.setBounds(260, 40, 80, 14);
        lbSituacao.setFont(fonte);
        painel1.add(lbSituacao);

        tfSituacao = new JTextField("Aberto");
        tfSituacao.setBounds(260, 58, 110, 20);
        painel1.add(tfSituacao);
        tfSituacao.setEditable(false);
        tfSituacao.setBackground(cor);
        tfSituacao.addFocusListener(this);

        JLabel lbCodVendedor = new JLabel("Vendedor");
        lbCodVendedor.setBounds(20, 78, 80, 14);
        lbCodVendedor.setFont(fonte);
        painel1.add(lbCodVendedor);

        tfCodVendedor = new JTextField();
        tfCodVendedor.setBounds(20, 94, 80, 20);
        tfCodVendedor.setDocument(new MeuDocument());
        painel1.add(tfCodVendedor);
        tfCodVendedor.addFocusListener(this);

        btPesquisaVendedor = new JButton("...");
        btPesquisaVendedor.setBounds(104, 92, 31, 24);
        painel1.add(btPesquisaVendedor);
        btPesquisaVendedor.addActionListener(this);

        tfNomeVendedor = new JTextField();
        tfNomeVendedor.setBounds(140, 94, 229, 20);
        painel1.add(tfNomeVendedor);
        tfNomeVendedor.setEditable(false);
        tfNomeVendedor.setBackground(cor);
        tfNomeVendedor.addFocusListener(this);

        JLabel lbComissaoVendedor = new JLabel("Comissao");
        lbComissaoVendedor.setBounds(20, 114, 80, 14);
        lbComissaoVendedor.setFont(fonte);
        painel1.add(lbComissaoVendedor);

        tfComissaoVendedor = new JTextField();
        tfComissaoVendedor.setBounds(20, 130, 110, 20);
        painel1.add(tfComissaoVendedor);
        tfComissaoVendedor.setEditable(false);
        tfComissaoVendedor.setBackground(cor);
        tfComissaoVendedor.addFocusListener(this);

        JLabel lbEmpresaVendedor = new JLabel("Empresa");
        lbEmpresaVendedor.setBounds(140, 114, 110, 14);
        lbEmpresaVendedor.setFont(fonte);
        painel1.add(lbEmpresaVendedor);

        tfEmpresaVendedor = new JTextField();
        tfEmpresaVendedor.setBounds(140, 130, 110, 20);
        painel1.add(tfEmpresaVendedor);
        tfEmpresaVendedor.setEditable(false);
        tfEmpresaVendedor.setBackground(cor);
        tfEmpresaVendedor.addFocusListener(this);

        JLabel lbCNPJVendedor = new JLabel("CNPJ");
        lbCNPJVendedor.setBounds(260, 114, 80, 14);
        lbCNPJVendedor.setFont(fonte);
        painel1.add(lbCNPJVendedor);

        try {
            mascaraCNPJVendedor = new MaskFormatter("###.###.###/####-##");
        } catch (ParseException excp) {
        }
        ftfCNPJVendedor = new JFormattedTextField(mascaraCNPJVendedor);
        ftfCNPJVendedor.setBounds(260, 130, 120, 20);
        painel1.add(ftfCNPJVendedor);
        ftfCNPJVendedor.setEditable(false);
        ftfCNPJVendedor.setBackground(cor);
        ftfCNPJVendedor.addFocusListener(this);

        JLabel lbIEVendedor = new JLabel("IE");
        lbIEVendedor.setBounds(20, 150, 80, 14);
        lbIEVendedor.setFont(fonte);
        painel1.add(lbIEVendedor);

        try {
            mascaraIEVendedor = new MaskFormatter("###.###.###");
        } catch (ParseException excp) {
        }
        ftfIEVendedor = new JFormattedTextField(mascaraIEVendedor);
        ftfIEVendedor.setBounds(20, 166, 110, 20);
        painel1.add(ftfIEVendedor);
        ftfIEVendedor.setEditable(false);
        ftfIEVendedor.setBackground(cor);
        ftfIEVendedor.addFocusListener(this);

        JLabel lbEnderecoVendedor = new JLabel("Endereço");
        lbEnderecoVendedor.setBounds(140, 150, 80, 14);
        lbEnderecoVendedor.setFont(fonte);
        painel1.add(lbEnderecoVendedor);

        tfEnderecoVendedor = new JTextField();
        tfEnderecoVendedor.setBounds(140, 166, 110, 20);
        painel1.add(tfEnderecoVendedor);
        tfEnderecoVendedor.setEditable(false);
        tfEnderecoVendedor.setBackground(cor);
        tfEnderecoVendedor.addFocusListener(this);

        JLabel lbBairroVendedor = new JLabel("Bairro");
        lbBairroVendedor.setBounds(260, 150, 80, 14);
        lbBairroVendedor.setFont(fonte);
        painel1.add(lbBairroVendedor);

        tfBairroVendedor = new JTextField();
        tfBairroVendedor.setBounds(260, 166, 120, 20);
        painel1.add(tfBairroVendedor);
        tfBairroVendedor.setEditable(false);
        tfBairroVendedor.setBackground(cor);
        tfBairroVendedor.addFocusListener(this);

        JLabel lbNumeroVendedor = new JLabel("Numero");
        lbNumeroVendedor.setBounds(20, 185, 80, 14);
        lbNumeroVendedor.setFont(fonte);
        painel1.add(lbNumeroVendedor);

        tfNumeroVendedor = new JTextField();
        tfNumeroVendedor.setBounds(20, 200, 110, 20);
        painel1.add(tfNumeroVendedor);
        tfNumeroVendedor.setEditable(false);
        tfNumeroVendedor.setBackground(cor);
        tfNumeroVendedor.addFocusListener(this);

        JLabel lbCidadeVendedor = new JLabel("Cidade");
        lbCidadeVendedor.setBounds(140, 185, 80, 14);
        lbCidadeVendedor.setFont(fonte);
        painel1.add(lbCidadeVendedor);

        tfCidadeVendedor = new JTextField();
        tfCidadeVendedor.setBounds(140, 200, 110, 20);
        painel1.add(tfCidadeVendedor);
        tfCidadeVendedor.setEditable(false);
        tfCidadeVendedor.setBackground(cor);
        tfCidadeVendedor.addFocusListener(this);

        JLabel lbEstadoVendedor = new JLabel("Estado");
        lbEstadoVendedor.setBounds(260, 185, 80, 14);
        lbEstadoVendedor.setFont(fonte);
        painel1.add(lbEstadoVendedor);

        tfEstadoVendedor = new JTextField();
        tfEstadoVendedor.setBounds(260, 200, 120, 20);
        painel1.add(tfEstadoVendedor);
        tfEstadoVendedor.setEditable(false);
        tfEstadoVendedor.setBackground(cor);
        tfEstadoVendedor.addFocusListener(this);

        JLabel lbSubTotal = new JLabel("Sub-Total");
        lbSubTotal.setBounds(20, 218, 80, 14);
        lbSubTotal.setFont(fonte);
        painel1.add(lbSubTotal);

        tfSubTotal = new JTextField();
        tfSubTotal.setEditable(false);
        tfSubTotal.setBackground(cor);
        tfSubTotal.setBounds(20, 233, 110, 20);
        painel1.add(tfSubTotal);
        tfSubTotal.addFocusListener(this);

        JLabel lbDescontos = new JLabel("Descontos");
        lbDescontos.setBounds(140, 218, 80, 14);
        lbDescontos.setFont(fonte);
        painel1.add(lbDescontos);

        tfDescontos = new JTextField();
        tfDescontos.setBounds(140, 233, 110, 20);
        painel1.add(tfDescontos);
        tfDescontos.addFocusListener(this);

        JLabel lbTotal = new JLabel("Total");
        lbTotal.setBounds(260, 218, 80, 14);
        lbTotal.setFont(fonte);
        painel1.add(lbTotal);

        tfTotal = new JTextField();
        tfTotal.setBounds(260, 233, 120, 20);
        tfTotal.setEditable(false);
        tfTotal.setBackground(cor);
        painel1.add(tfTotal);
        tfTotal.addFocusListener(this);

        JLabel lbCondPagto = new JLabel("Cond. Pagto");
        lbCondPagto.setBounds(20, 252, 80, 14);
        lbCondPagto.setFont(fonte);
        painel1.add(lbCondPagto);

        cbCondPagto = new JComboBox();
        cbCondPagto.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        cbCondPagto.addItem("1X");
        cbCondPagto.addItem("2X");
        cbCondPagto.addItem("3X");
        cbCondPagto.addItem("4X");
        cbCondPagto.addItem("5X");
        cbCondPagto.addItem("6X");
        cbCondPagto.addItem("7X");
        cbCondPagto.addItem("8X");
        cbCondPagto.setBackground(cor);
        cbCondPagto.setFont(fonte);
        cbCondPagto.setBounds(20, 268, 110, 20);
        painel1.add(cbCondPagto);
        cbCondPagto.addFocusListener(this);

        JLabel lbFormaPagto = new JLabel("Forma Pagto");
        lbFormaPagto.setBounds(140, 252, 80, 14);
        lbFormaPagto.setFont(fonte);
        painel1.add(lbFormaPagto);

        cbFormaPagto = new JComboBox();
        cbFormaPagto.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        cbFormaPagto.addItem("Dinheiro");
        cbFormaPagto.addItem("Cheque");
        cbFormaPagto.addItem("Cartão");
        cbFormaPagto.setFont(fonte);
        cbFormaPagto.setBackground(cor);
        cbFormaPagto.setBounds(140, 268, 110, 20);
        painel1.add(cbFormaPagto);
        cbFormaPagto.addFocusListener(this);

        JLabel lbCodCliente = new JLabel("Cliente");
        lbCodCliente.setBounds(20, 40, 80, 14);
        lbCodCliente.setFont(fonte);
        painel2.add(lbCodCliente);

        tfCodCliente = new JTextField();
        tfCodCliente.setBounds(20, 58, 80, 20);
        tfCodCliente.setDocument(new MeuDocument());
        painel2.add(tfCodCliente);
        tfCodCliente.addFocusListener(this);

        btPesquisaClie = new JButton("...");
        btPesquisaClie.setBounds(104, 56, 31, 24);
        painel2.add(btPesquisaClie);
        btPesquisaClie.addActionListener(this);

        tfNomeClie = new JTextField();
        tfNomeClie.setBounds(140, 58, 243, 20);
        painel2.add(tfNomeClie);
        tfNomeClie.setEditable(false);
        tfNomeClie.setBackground(cor);
        tfNomeClie.addFocusListener(this);

        JLabel lbEnderecoClie = new JLabel("Endereço");
        lbEnderecoClie.setBounds(20, 78, 80, 14);
        lbEnderecoClie.setFont(fonte);
        painel2.add(lbEnderecoClie);

        tfEnderecoClie = new JTextField();
        tfEnderecoClie.setBounds(20, 94, 110, 20);
        painel2.add(tfEnderecoClie);
        tfEnderecoClie.setEditable(false);
        tfEnderecoClie.setBackground(cor);
        tfEnderecoClie.addFocusListener(this);

        JLabel lbBairroClie = new JLabel("Bairro");
        lbBairroClie.setBounds(140, 78, 80, 14);
        lbBairroClie.setFont(fonte);
        painel2.add(lbBairroClie);

        tfBairroClie = new JTextField();
        tfBairroClie.setBounds(140, 94, 110, 20);
        painel2.add(tfBairroClie);
        tfBairroClie.setEditable(false);
        tfBairroClie.setBackground(cor);
        tfBairroClie.addFocusListener(this);

        JLabel lbNumeroClie = new JLabel("Numero");
        lbNumeroClie.setBounds(260, 78, 80, 14);
        lbNumeroClie.setFont(fonte);
        painel2.add(lbNumeroClie);

        tfNumeroClie = new JTextField();
        tfNumeroClie.setBounds(260, 94, 123, 20);
        painel2.add(tfNumeroClie);
        tfNumeroClie.setEditable(false);
        tfNumeroClie.setBackground(cor);
        tfNumeroClie.addFocusListener(this);

        JLabel lbCidadeClie = new JLabel("Cidade");
        lbCidadeClie.setBounds(20, 114, 80, 14);
        lbCidadeClie.setFont(fonte);
        painel2.add(lbCidadeClie);

        tfCidadeClie = new JTextField();
        tfCidadeClie.setBounds(20, 130, 110, 20);
        painel2.add(tfCidadeClie);
        tfCidadeClie.setEditable(false);
        tfCidadeClie.setBackground(cor);
        tfCidadeClie.addFocusListener(this);

        JLabel lbEstadoClie = new JLabel("Estado");
        lbEstadoClie.setBounds(140, 114, 110, 14);
        lbEstadoClie.setFont(fonte);
        painel2.add(lbEstadoClie);

        tfEstadoClie = new JTextField();
        tfEstadoClie.setBounds(140, 130, 110, 20);
        painel2.add(tfEstadoClie);
        tfEstadoClie.setEditable(false);
        tfEstadoClie.setBackground(cor);
        tfEstadoClie.addFocusListener(this);

        JLabel lbFone = new JLabel("Fone");
        lbFone.setBounds(260, 114, 80, 14);
        lbFone.setFont(fonte);
        painel2.add(lbFone);

        try {
            mascaraFoneClie = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfFoneClie = new JFormattedTextField(mascaraFoneClie);
        ftfFoneClie.setBounds(260, 130, 120, 20);
        painel2.add(ftfFoneClie);
        ftfFoneClie.setEditable(false);
        ftfFoneClie.setBackground(cor);
        ftfFoneClie.addFocusListener(this);

        JLabel lbCelular = new JLabel("Celular");
        lbCelular.setBounds(20, 150, 80, 14);
        lbCelular.setFont(fonte);
        painel2.add(lbCelular);

        try {
            mascaraCelularClie = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfCelularClie = new JFormattedTextField(mascaraCelularClie);
        ftfCelularClie.setBounds(20, 166, 110, 20);
        painel2.add(ftfCelularClie);
        ftfCelularClie.setEditable(false);
        ftfCelularClie.setBackground(cor);
        ftfCelularClie.addFocusListener(this);

        JLabel lbEnderecoEnt = new JLabel("Endereço");
        lbEnderecoEnt.setBounds(20, 30, 80, 14);
        lbEnderecoEnt.setFont(fonte);
        painel3.add(lbEnderecoEnt);

        tfEnderecoEnt = new JTextField();
        tfEnderecoEnt.setBounds(20, 48, 110, 20);
        painel3.add(tfEnderecoEnt);
        tfEnderecoEnt.addFocusListener(this);

        JLabel lbBairroEnt = new JLabel("Bairro");
        lbBairroEnt.setBounds(140, 30, 80, 14);
        lbBairroEnt.setFont(fonte);
        painel3.add(lbBairroEnt);

        tfBairroEnt = new JTextField();
        tfBairroEnt.setBounds(140, 48, 110, 20);
        painel3.add(tfBairroEnt);
        tfBairroEnt.addFocusListener(this);

        JLabel lbNumeroEnt = new JLabel("Numero");
        lbNumeroEnt.setBounds(260, 30, 80, 14);
        lbNumeroEnt.setFont(fonte);
        painel3.add(lbNumeroEnt);

        tfNumeroEnt = new JTextField();
        tfNumeroEnt.setBounds(260, 48, 110, 20);
        tfNumeroEnt.setDocument(new MeuDocument());
        painel3.add(tfNumeroEnt);
        tfNumeroEnt.addFocusListener(this);

        JLabel lbCidadeEnt = new JLabel("Cidade");
        lbCidadeEnt.setBounds(20, 68, 80, 14);
        lbCidadeEnt.setFont(fonte);
        painel3.add(lbCidadeEnt);

        cbCidadeEnt = new JComboBox();
        cbCidadeEnt.setBounds(20, 84, 110, 20);
        cbCidadeEnt.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        ArrayList listaCidade = new ArrayList();
        listaCidade = controlCidade.cidade();
        for (int i = 0; i < listaCidade.size(); i++) {
            String n = (String) listaCidade.get(i);
            cbCidadeEnt.addItem(n);
        }
        cbCidadeEnt.setBackground(cor);
        cbCidadeEnt.setFont(fonte);
        painel3.add(cbCidadeEnt);
        cbCidadeEnt.addFocusListener(this);

        JLabel lbEstadoEnt = new JLabel("Estado");
        lbEstadoEnt.setBounds(140, 68, 80, 14);
        lbEstadoEnt.setFont(fonte);
        painel3.add(lbEstadoEnt);

        cbEstadoEnt = new JComboBox();
        cbEstadoEnt.setBounds(140, 84, 110, 20);
        cbEstadoEnt.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        ArrayList listaEstado = new ArrayList();
        listaEstado = controlCidade.estado();
        for (int i = 0; i < listaEstado.size(); i++) {
            String n2 = (String) listaEstado.get(i);
            cbEstadoEnt.addItem(n2);
        }
        cbEstadoEnt.setBackground(cor);
        cbEstadoEnt.setFont(fonte);
        painel3.add(cbEstadoEnt);
        cbEstadoEnt.addFocusListener(this);

        JLabel lbContatoEnt = new JLabel("Contato");
        lbContatoEnt.setBounds(260, 68, 80, 14);
        lbContatoEnt.setFont(fonte);
        painel3.add(lbContatoEnt);

        try {
            mascaraContatoEnt = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfContatoEnt = new JFormattedTextField(mascaraContatoEnt);
        ftfContatoEnt.setBounds(260, 84, 123, 20);
        painel3.add(ftfContatoEnt);
        ftfContatoEnt.addFocusListener(this);

        JLabel lbCodigoProd = new JLabel("Cod. Produto");
        lbCodigoProd.setBounds(15, 14, 80, 14);
        lbCodigoProd.setFont(fonte);
        painel4.add(lbCodigoProd);

        tfCodProd = new JTextField();
        tfCodProd.setBounds(90, 12, 50, 20);
        painel4.add(tfCodProd);
        tfCodProd.setDocument(new MeuDocument());
        tfCodProd.addFocusListener(this);

        btPesquisaProd = new JButton("...");
        btPesquisaProd.setBounds(146, 9, 40, 26);
        painel4.add(btPesquisaProd);
        btPesquisaProd.addActionListener(this);

        tfNomeProd = new JTextField();
        tfNomeProd.setBounds(197, 12, 130, 20);
        painel4.add(tfNomeProd);
        tfNomeProd.setEditable(false);
        tfNomeProd.setBackground(cor);
        tfNomeProd.addFocusListener(this);

        JLabel lbDescricaoProd = new JLabel("Descriçao");
        lbDescricaoProd.setBounds(330, 14, 80, 14);
        lbDescricaoProd.setFont(fonte);
        painel4.add(lbDescricaoProd);

        tfDescriProd = new JTextField();
        tfDescriProd.setBounds(389, 12, 130, 20);
        painel4.add(tfDescriProd);
        tfDescriProd.setEditable(false);
        tfDescriProd.setBackground(cor);
        tfDescriProd.addFocusListener(this);

        JLabel lbModelo = new JLabel("Modelo");
        lbModelo.setBounds(523, 14, 80, 14);
        lbModelo.setFont(fonte);
        painel4.add(lbModelo);

        tfModeloProd = new JTextField();
        tfModeloProd.setBounds(566, 12, 110, 20);
        painel4.add(tfModeloProd);
        tfModeloProd.setEditable(false);
        tfModeloProd.setBackground(cor);
        tfModeloProd.addFocusListener(this);

        JLabel lbTotalProd = new JLabel("Total");
        lbTotalProd.setBounds(678, 14, 80, 14);
        lbTotalProd.setFont(fonte);
        painel4.add(lbTotalProd);

        tfTotalProd = new JTextField();
        tfTotalProd.setBounds(708, 12, 110, 20);
        painel4.add(tfTotalProd);
        tfTotalProd.setEditable(false);
        tfTotalProd.setBackground(cor);
        tfTotalProd.addFocusListener(this);

        btOKProd = new JButton("OK");
        btOKProd.setBounds(825, 9, 51, 26);
        painel4.add(btOKProd);
        btOKProd.addActionListener(this);

        tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(2, 3, 881, 160);
        painel5.add(scroll);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(10, 4, 80, 14);
        lbDescricao.setFont(fonte);
        painel6.add(lbDescricao);

        taDescricao = new JTextArea();
        taDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 0));//Borda em volta no JTextArea
        JScrollPane rolagem1 = new JScrollPane(taDescricao);
        rolagem1.setBounds(10, 20, 760, 68);
        painel6.add(rolagem1);
        taDescricao.addFocusListener(this);

        checkSeleciona = new JCheckBox();
        checkSeleciona.setBounds(69, 5, 22, 15);
        painel6.add(checkSeleciona);
        checkSeleciona.addItemListener(this);

        btConfirmar = new JButton("Confirmar");
        btConfirmar.setBounds(783, 15, 92, 26);
        painel6.add(btConfirmar);
        btConfirmar.setEnabled(false);
        btConfirmar.addActionListener(this);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(783, 50, 92, 26);
        painel6.add(btCancelar);
        btCancelar.addActionListener(this);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        painel2.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        painel3.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        painel4.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        painel5.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        painel6.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(900, 652);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
    }

    private void gravarItens() throws Exception {
        String nomeProd, descricaoProd, modeloProd;
        int codItem, codVenda, codProd, codigoAux;
        double totalProd;

        if ("".equals(tfCodigo.getText()) || Integer.parseInt(tfCodigo.getText()) <= 0) {
            tfCodigo.grabFocus();
            throw new Exception("Campo codigo inválido");
        } else {
            codItem = Integer.parseInt(tfCodigo.getText());
            codVenda = Integer.parseInt(tfCodigo.getText());
            if ("".equals(tfCodProd.getText()) || Integer.parseInt(tfCodProd.getText()) <= 0) {
                tfCodProd.grabFocus();
                throw new Exception("Campo codigo produto inválido");
            } else {
                codProd = Integer.parseInt(tfCodProd.getText());
                if ("".equals(tfNomeProd.getText())) {
                    tfNomeProd.grabFocus();
                    throw new Exception("Campo nome produto inválido");
                } else {
                    nomeProd = tfNomeProd.getText();
                    descricaoProd = tfDescriProd.getText();
                    modeloProd = tfModeloProd.getText();
                    totalProd = Double.parseDouble(tfTotalProd.getText());
                    codigoAux = itemVendaAdiciona.ultimoCodigoAuxCadas() + 1;
                    System.out.println(codigoAux);
                    controlItemVenda.adicionar(nomeProd, descricaoProd, modeloProd, codProd, totalProd);
                    tableModel.fireTableDataChanged();
                    itemVendaAdiciona.adicionar(nomeProd, descricaoProd, modeloProd, codItem, codVenda, codProd, totalProd, codigoAux);
                    itemVendaAdiciona.arquivo(arquivo);
                    btConfirmar.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Numeros de itens vendidos " + controlItemVenda.tamanho(), "Informação", JOptionPane.INFORMATION_MESSAGE);
                    tfCodigo.setEditable(false);
                    btCancelar.setEnabled(false);
                    controlProduto.removerCod(Integer.parseInt(tfCodProd.getText()));
                    controlProduto.arquivo(arquivo);
                    double soma = totalProd;
                    subTotal = subTotal + soma;
                    tfSubTotal.setText(Double.toString(subTotal));
                    limparProduto();
                }
            }
        }
    }

    private void gravarVendas() throws Exception {
        String dataEmissao, situacao, condPagto, formaPagto, descricaoVen, endedrecoEnt, bairroEnt, cidadeEnt, estadoEnt, contatoEnt;
        int codigo, codigoVendedor, codigoCliente, codigoItemVendas, numeroEnt;
        double subTotalVen, descontos, total;

        if ("".equals(tfCodigo.getText()) || Integer.parseInt(tfCodigo.getText()) <= 0) {
            tfCodigo.grabFocus();
            throw new Exception("Campo codigo inválido");
        } else {
            codigo = Integer.parseInt(tfCodigo.getText());
            codigoItemVendas = Integer.parseInt(tfCodigo.getText());
            if ("  /  /    ".equals(ftfDataEmissao.getText())) {
                ftfDataEmissao.grabFocus();
                throw new Exception("Campo data emissão inválido");
            } else {
                dataEmissao = ftfDataEmissao.getText();
                situacao = tfSituacao.getText();
                if ("".equals(tfCodVendedor.getText()) || Integer.parseInt(tfCodVendedor.getText()) <= 0) {
                    tfCodVendedor.grabFocus();
                    throw new Exception("Campo codigo vendedor inválido");
                } else {
                    codigoVendedor = Integer.parseInt(tfCodVendedor.getText());
                    if ("".equals(tfSubTotal.getText()) || Double.parseDouble(tfSubTotal.getText()) <= 0) {
                        tfSubTotal.grabFocus();
                        throw new Exception("Campo sub-total inválido");
                    } else {
                        subTotalVen = Double.parseDouble(tfSubTotal.getText());
                        if ("".equals(tfDescontos.getText())) {
                            tfDescontos.grabFocus();
                            throw new Exception("Campo descontos inválido");
                        } else {
                            descontos = Double.parseDouble(tfDescontos.getText());
                            if ("".equals(tfTotal.getText()) || Double.parseDouble(tfTotal.getText()) <= 0) {
                                tfTotal.grabFocus();
                                throw new Exception("Campo total inválido");
                            } else {
                                total = Double.parseDouble(tfTotal.getText());
                                if ("Selecione".equals(cbCondPagto.getSelectedItem())) {
                                    cbCondPagto.grabFocus();
                                    throw new Exception("Campo condição de pagamento inválido");
                                } else {
                                    condPagto = (String) cbCondPagto.getSelectedItem();
                                    if ("Selecione".equals(cbFormaPagto.getSelectedItem())) {
                                        cbFormaPagto.grabFocus();
                                        throw new Exception("Campo forrma de pagamento inválido");
                                    } else {
                                        formaPagto = (String) cbFormaPagto.getSelectedItem();
                                        if ("".equals(tfCodCliente.getText()) || Integer.parseInt(tfCodCliente.getText()) <= 0) {
                                            tfCodCliente.grabFocus();
                                            throw new Exception("Campo codigo cliente inválido");
                                        } else {
                                            codigoCliente = Integer.parseInt(tfCodCliente.getText());
                                            if ("".equals(tfEnderecoEnt.getText())) {
                                                tfEnderecoEnt.grabFocus();
                                                throw new Exception("Campo endereço entrega inválido");
                                            } else {
                                                endedrecoEnt = tfEnderecoEnt.getText();
                                                if ("".equals(tfBairroEnt.getText())) {
                                                    tfBairroEnt.grabFocus();
                                                    throw new Exception("Campo bairro entrega inválido");
                                                } else {
                                                    bairroEnt = tfBairroEnt.getText();
                                                    if ("".equals(tfNumeroEnt.getText()) || Integer.parseInt(tfNumeroEnt.getText()) <= 0) {
                                                        tfNumeroEnt.grabFocus();
                                                        throw new Exception("Campo número entrega inválido");
                                                    } else {
                                                        numeroEnt = Integer.parseInt(tfNumeroEnt.getText());
                                                        if ("Selecione".equals(cbCidadeEnt.getSelectedItem())) {
                                                            cbCidadeEnt.grabFocus();
                                                            throw new Exception("Campo cidade entrega inválido");
                                                        } else {
                                                            cidadeEnt = (String) cbCidadeEnt.getSelectedItem();
                                                            if ("Selecione".equals(cbEstadoEnt.getSelectedItem())) {
                                                                cbEstadoEnt.grabFocus();
                                                                throw new Exception("Campo estado entrega inválido");
                                                            } else {
                                                                estadoEnt = (String) cbEstadoEnt.getSelectedItem();
                                                                if ("(  )    -    ".equals(ftfContatoEnt.getText())) {
                                                                    ftfContatoEnt.grabFocus();
                                                                    throw new Exception("Campo contato entrega inválido");
                                                                } else {
                                                                    contatoEnt = ftfContatoEnt.getText();
                                                                    if (descricao == null) {
                                                                        if ("".equals(taDescricao.getText())) {
                                                                            taDescricao.grabFocus();
                                                                            throw new Exception("Campo descrição inválido");
                                                                        } else {
                                                                            descricaoVen = taDescricao.getText();
                                                                        }
                                                                    } else {
                                                                        descricaoVen = descricao;
                                                                    }
                                                                    controlVendas.adicionar(dataEmissao, situacao, condPagto, formaPagto, descricaoVen, endedrecoEnt, bairroEnt, cidadeEnt, estadoEnt, contatoEnt, codigo, codigoVendedor, codigoCliente, codigoItemVendas, numeroEnt, subTotalVen, descontos, total);
                                                                    JOptionPane.showMessageDialog(null, "Numeros de vendas cadastradas " + controlVendas.tamanho(), "Informação", JOptionPane.INFORMATION_MESSAGE);
                                                                    limparProduto();
                                                                    limparVendas();
                                                                    btConfirmar.setEnabled(false);
                                                                    btCancelar.setEnabled(true);
                                                                    tfCodigo.setEditable(true);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void recuperarProduto() throws Exception {
        if (!"".equals(tfCodProd.getText())) {
            if (controlProduto.vazio() == true) {
                throw new Exception("Não há produtos cadastrados");
            } else {
                if (controlProduto.recuperar(Integer.parseInt(tfCodProd.getText())) == true) {
                    tfCodProd.setText(Integer.toString(controlProduto.getCodigo()));
                    tfNomeProd.setText(controlProduto.getNome());
                    tfDescriProd.setText(controlProduto.getDescricao());
                    tfModeloProd.setText(controlProduto.getModelo());
                    tfTotalProd.setText(Double.toString(controlProduto.getValorTotal()));
                } else {
                    limparProduto();
                }
            }
        }
    }

    private void recuperarVendedor() throws Exception {
        if (!"".equals(tfCodVendedor.getText())) {
            if (controlVendedor.vazio() == true) {
                throw new Exception("Não há vendedores cadastrados");
            } else {
                if (controlVendedor.recuperar(Integer.parseInt(tfCodVendedor.getText())) == true) {
                    tfCodVendedor.setText(Integer.toString(controlVendedor.getCodigo()));
                    tfNomeVendedor.setText(controlVendedor.getNome());
                    tfComissaoVendedor.setText(Double.toString(controlVendedor.getComissao()));
                    tfEmpresaVendedor.setText(controlVendedor.getEmpresa());
                    ftfCNPJVendedor.setText(controlVendedor.getCnpj());
                    ftfIEVendedor.setText(controlVendedor.getIe());
                    tfEnderecoVendedor.setText(controlVendedor.getEndereco());
                    tfBairroVendedor.setText(controlVendedor.getBairro());
                    tfNumeroVendedor.setText(Integer.toString(controlVendedor.getNumero()));
                    tfCidadeVendedor.setText(controlVendedor.getCidade());
                    tfEstadoVendedor.setText(controlVendedor.getEstado());
                } else {
                    tfCodVendedor.setText("");
                    tfNomeVendedor.setText("");
                    tfComissaoVendedor.setText("");
                    tfEmpresaVendedor.setText("");
                    ftfCNPJVendedor.setText("");
                    ftfIEVendedor.setText("");
                    tfEnderecoVendedor.setText("");
                    tfBairroVendedor.setText("");
                    tfNumeroVendedor.setText("");
                    tfCidadeVendedor.setText("");
                    tfEstadoVendedor.setText("");
                }
            }
        }
    }

    private void recuperarCliente() throws Exception {
        if (!"".equals(tfCodCliente.getText())) {
            if (controlCliente.vazio() == true) {
                throw new Exception("Não há clientes cadastrados");
            } else {
                if (controlCliente.recuperar(Integer.parseInt(tfCodCliente.getText())) == true) {
                    tfCodCliente.setText(Integer.toString(controlCliente.getCodigo()));
                    tfNomeClie.setText(controlCliente.getNome());
                    tfEnderecoClie.setText(controlCliente.getEndereco());
                    tfBairroClie.setText(controlCliente.getBairro());
                    tfNumeroClie.setText(Integer.toString(controlCliente.getNumero()));
                    tfCidadeClie.setText(controlCliente.getCidade());
                    tfEstadoClie.setText(controlCliente.getEstado());
                    ftfFoneClie.setText(controlCliente.getFone());
                    ftfCelularClie.setText(controlCliente.getCelular());
                } else {
                    tfCodCliente.setText("");
                    tfNomeClie.setText("");
                    tfEnderecoClie.setText("");
                    tfBairroClie.setText("");
                    tfNumeroClie.setText("");
                    tfCidadeClie.setText("");
                    tfEstadoClie.setText("");
                    ftfFoneClie.setText("");
                    ftfCelularClie.setText("");
                }
            }
        }
    }

    private void limparProduto() {
        tfCodProd.setText("");
        tfNomeProd.setText("");
        tfDescriProd.setText("");
        tfModeloProd.setText("");
        tfTotalProd.setText("");
    }

    private void limparVendas() {
        tfCodigo.setText(Integer.toString(controlVendas.ultimoCadasCod() + 1));
        ftfDataEmissao.setText("");
        tfSubTotal.setText("");
        tfDescontos.setText("");
        tfTotal.setText("");
        tfCodVendedor.setText("");
        tfNomeVendedor.setText("");
        tfComissaoVendedor.setText("");
        tfEmpresaVendedor.setText("");
        ftfCNPJVendedor.setText("");
        ftfIEVendedor.setText("");
        tfEnderecoVendedor.setText("");
        tfBairroVendedor.setText("");
        tfNumeroVendedor.setText("");
        tfCidadeVendedor.setText("");
        tfEstadoVendedor.setText("");
        cbCondPagto.setSelectedItem("Selecione");//Campo recebe Selecione
        tfCodCliente.setText("");
        tfNomeClie.setText("");
        cbFormaPagto.setSelectedItem("Selecione");//Campo recebe Selecione
        tfEnderecoClie.setText("");
        tfBairroClie.setText("");
        tfNumeroClie.setText("");
        tfCidadeClie.setText("");
        tfEstadoClie.setText("");
        ftfFoneClie.setText("");
        ftfCelularClie.setText("");
        tfEnderecoEnt.setText("");
        tfBairroEnt.setText("");
        tfNumeroEnt.setText("");
        cbCidadeEnt.setSelectedItem("Selecione");//Campo recebe Selecione
        cbEstadoEnt.setSelectedItem("Selecione");//Campo recebe Selecione
        ftfContatoEnt.setText("");
        checkSeleciona.setSelected(false);//Desmarca JCheckbox
        descricao = null;//Limpa Variavel
        taDescricao.setText("");
        tableModel.limparTabela();
    }

    private void Calcula() throws Exception {
        try {
            if (!"".equals(tfDescontos.getText()) & !"".equals(tfSubTotal.getText())) {
                double subTotal1 = Double.parseDouble(tfSubTotal.getText());
                double descontos = Double.parseDouble(tfDescontos.getText());
                double valorTotal1 = (subTotal1 / 100) * descontos;
                double subTotal2 = Double.parseDouble(tfSubTotal.getText());
                double valorTotal2 = subTotal2 - valorTotal1;
                tfTotal.setText(Double.toString(valorTotal2));
            }
        } catch (NumberFormatException ex) {
            jTextFieldDouble.validaCampo(tfDescontos);
        }
    }

    private void codigo() {
        if ("".equals(tfCodigo.getText())) {
            tfCodigo.setText(Integer.toString(controlVendas.ultimoCadasCod() + 1));
        } else {
            if (controlVendas.vazio() == true) {
            } else {
                int n = Integer.parseInt(tfCodigo.getText());
                boolean encontrado = false;
                if (controlVendas.vendasCadasCod(n) == true) {
                    encontrado = true;
                }
                if (encontrado == true) {
                    tfCodigo.setText(Integer.toString(controlVendas.ultimoCadasCod() + 1));
                }
            }
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btPesquisaVendedor) {
            if (controlVendedor.vazio() == true) {
                JOptionPane.showMessageDialog(null, "Não há vendedores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                VerTodosCadasVendedor ver = new VerTodosCadasVendedor(controlVendedor);
                ver.setModal(true);
                ver.setVisible(true);
            }
        }
        if (evento.getSource() == btPesquisaClie) {
            if (controlCliente.vazio() == true) {
                JOptionPane.showMessageDialog(null, "Não há clientes cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                VerTodosCadasClie ver = new VerTodosCadasClie(controlCliente);
                ver.setModal(true);
                ver.setVisible(true);
            }
        }
        if (evento.getSource() == btPesquisaPed) {
            ConsultaVendas consulta = new ConsultaVendas();
            consulta.setModal(true);
            consulta.setVisible(true);
        }
        if (evento.getSource() == btPesquisaProd) {
            if (controlProduto.vazio() == true) {
                JOptionPane.showMessageDialog(null, "Não há produtos cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                VerTodosCadasProd ver = new VerTodosCadasProd(controlProduto);
                ver.setModal(true);
                ver.setVisible(true);
            }
        }
        if (evento.getSource() == btOKProd) {
            try {
                gravarItens();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConfirmar) {
            try {
                gravarVendas();
                controlVendas.arquivo(arquivo);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCancelar) {
            limparProduto();
            limparVendas();
        }
    }

    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataEmissao) {
            ftfDataEmissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSituacao) {
            tfSituacao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodVendedor) {
            tfCodVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeVendedor) {
            tfNomeVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfComissaoVendedor) {
            tfComissaoVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmpresaVendedor) {
            tfEmpresaVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCNPJVendedor) {
            ftfCNPJVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfIEVendedor) {
            ftfIEVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEnderecoVendedor) {
            tfEnderecoVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBairroVendedor) {
            tfBairroVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumeroVendedor) {
            tfNumeroVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCidadeVendedor) {
            tfCidadeVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEstadoVendedor) {
            tfEstadoVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSubTotal) {
            tfSubTotal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescontos) {
            tfDescontos.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTotal) {
            tfTotal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbCondPagto) {
            cbCondPagto.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbFormaPagto) {
            cbFormaPagto.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodCliente) {
            tfCodCliente.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeClie) {
            tfNomeClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEnderecoClie) {
            tfEnderecoClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBairroClie) {
            tfBairroClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCidadeClie) {
            tfCidadeClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEstadoClie) {
            tfEstadoClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFoneClie) {
            ftfFoneClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCelularClie) {
            ftfCelularClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEnderecoEnt) {
            tfEnderecoEnt.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumeroClie) {
            tfNumeroClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBairroEnt) {
            tfBairroEnt.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumeroEnt) {
            tfNumeroEnt.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbCidadeEnt) {
            cbCidadeEnt.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbEstadoEnt) {
            cbEstadoEnt.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfContatoEnt) {
            ftfContatoEnt.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodProd) {
            tfCodProd.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeProd) {
            tfNomeProd.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescriProd) {
            tfDescriProd.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfModeloProd) {
            tfModeloProd.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTotalProd) {
            tfTotalProd.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == taDescricao) {
            taDescricao.setBackground(Color.YELLOW);
        }
    }

    public void focusLost(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.WHITE);
            codigo();
        }
        ftfDataEmissao.setBackground(Color.WHITE);
        tfSituacao.setBackground(Color.WHITE);
        if (evento.getSource() == tfCodVendedor) {
            tfCodVendedor.setBackground(Color.WHITE);
            try {
                recuperarVendedor();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfNomeVendedor.setBackground(Color.WHITE);
        tfComissaoVendedor.setBackground(Color.WHITE);
        tfEmpresaVendedor.setBackground(Color.WHITE);
        ftfCNPJVendedor.setBackground(Color.WHITE);
        ftfIEVendedor.setBackground(Color.WHITE);
        tfEnderecoVendedor.setBackground(Color.WHITE);
        tfBairroVendedor.setBackground(Color.WHITE);
        tfNumeroVendedor.setBackground(Color.WHITE);
        tfCidadeVendedor.setBackground(Color.WHITE);
        tfEstadoVendedor.setBackground(Color.WHITE);
        tfSubTotal.setBackground(Color.WHITE);
        if (evento.getSource() == tfDescontos) {
            tfDescontos.setBackground(Color.WHITE);
            try {
                Calcula();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfTotal.setBackground(Color.WHITE);
        cbCondPagto.setBackground(Color.WHITE);
        cbFormaPagto.setBackground(Color.WHITE);
        if (evento.getSource() == tfCodCliente) {
            tfCodCliente.setBackground(Color.WHITE);
            try {
                recuperarCliente();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfNomeClie.setBackground(Color.WHITE);
        tfEnderecoClie.setBackground(Color.WHITE);
        tfBairroClie.setBackground(Color.WHITE);
        tfNumeroClie.setBackground(Color.WHITE);
        tfCidadeClie.setBackground(Color.WHITE);
        tfEstadoClie.setBackground(Color.WHITE);
        ftfFoneClie.setBackground(Color.WHITE);
        ftfCelularClie.setBackground(Color.WHITE);
        tfEnderecoEnt.setBackground(Color.WHITE);
        tfBairroEnt.setBackground(Color.WHITE);
        tfNumeroEnt.setBackground(Color.WHITE);
        cbCidadeEnt.setBackground(Color.WHITE);
        cbEstadoEnt.setBackground(Color.WHITE);
        ftfContatoEnt.setBackground(Color.WHITE);
        if (evento.getSource() == tfCodProd) {
            tfCodProd.setBackground(Color.WHITE);
            try {
                recuperarProduto();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfNomeProd.setBackground(Color.WHITE);
        tfDescriProd.setBackground(Color.WHITE);
        tfModeloProd.setBackground(Color.WHITE);
        tfTotalProd.setBackground(Color.WHITE);
        taDescricao.setBackground(Color.WHITE);
    }

    public void itemStateChanged(ItemEvent evento) {
        if (evento.getSource() == checkSeleciona) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                descricao = " Sem Descrição";
            } else {
                descricao = null;
            }
        }
    }
}

package Principal;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Cliente.CadasCliente;
import Cliente.ClienteControl;
import Cliente.ConsultaClie;
import Cliente.ListenerClie;
import Departamento.CadasDepartamento;
import Endereco.CadasEndereco;
import Fornecedor.CadasFornecedor;
import Fornecedor.ConsultaForn;
import Fornecedor.FornecedorControl;
import Funcionario.CadasFuncionario;
import Funcionario.ConsultaFunc;
import Funcionario.FuncionarioControl;
import Funcionario.ListenerFunc;
import ItensVenda.ItensVendaControl;
import ItensVenda.TableModelItensVenda;
import Modelo.Cliente;
import Modelo.Funcionario;
import Modelo.ItensVenda;
import Modelo.Produto;
import Modelo.Vendas;
import Produto.CadasProduto;
import Produto.ConsultaProduto;
import Produto.ListenerProduto;
import Produto.ProdutoControl;
import Seguranca.CertificadoDigital;
import Seguranca.Seguranca;
import Setor.CadasSetor;
import Transportadora.CadasTransportadora;
import Transportadora.ConsultaTrans;
import Transportadora.TransportadoraControl;
import Usuario.CadasUsuario;
import Usuario.ConsultaUsuario;
import Usuario.UsuarioControl;
import Vendas.ListenerVendas;
import Vendas.VendasControl;
import Vendas.ConsultaVendas;

public class Principal extends JFrame implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JMenuItem miSair, miCadasUsuario, miCadasClientes,
            miCadasFuncionario, miCadasFornecedores, miCadasTransportadora,
            miCadasProdutos, miCadasSetor, miCadasEndereco,
            miCadasDepartamento, miConsUsuario, miConsClientes,
            miConsFuncionario, miConsFornecedor, miConsTransportadora,
            miConsProduto, miArqCertificado;
    private JTextField tfNumeroVenda, tfDataEmissao, tfSituacao, tfCodFunc,
            tfNomeFunc, tfSubTotal, tfDescontos, tfTotal, tfCondPagto, tfTipo,
            tfFormaPagto, tfJuros, tfParcelasMes, tfCodCliente, tfNomeClie,
            tfCpfCnpjClie, tfEnderecoClie, tfBairroClie, tfNumeroClie,
            tfCidadeClie, tfEstadoClie, tfFoneClie, tfFrete, tfValorPago,
            tfValorRestante, tfParcelasRestante, tfProduto, tfDescricaoProd,
            tfQtdadeProd, tfValorUnitProd, tfValorTotalProd;
    private JFormattedTextField ftfDataEntrega;
    private JTextArea taDescricao;
    private JComboBox cbTipo, cbCondPagto, cbFormaPagto;
    private JCheckBox cxCliente;
    private JButton btConsulta, btConsultaFunc, btConsultaClie, btPagar,
            btConsultaProd, btOk, btConfirmar, btGerar, btCancelar, btEncerar;
    private JLabel lbHorarioSistema;
    private SimpleDateFormat formatDate;
    private ClienteControl controleClie;
    private FuncionarioControl controleFunc;
    private ProdutoControl controleProd;
    private VendasControl controleVendas;
    private ItensVendaControl controleItensVenda;
    private TableModelItensVenda tableModel;
    private int codProduto;
    private Connection con;
    private Seguranca seguranca;

    public Principal(Connection con, String usuario, int permissao, Seguranca seguranca) {
        super("System DB");
        this.con = con;
        this.seguranca = seguranca;
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        controleClie = new ClienteControl(con);
        controleFunc = new FuncionarioControl(con);
        controleProd = new ProdutoControl(con);
        controleVendas = new VendasControl(con);
        controleItensVenda = new ItensVendaControl(con);
        tableModel = new TableModelItensVenda(controleItensVenda);
        codProduto = -1;
        initComponents(usuario, permissao);
    }

    private void initComponents(String usuario, int permissao) {
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu mnSistema = new JMenu("Sistema");

        miSair = new JMenuItem("Sair");
        mnSistema.add(miSair);
        miSair.addActionListener(this);

        JMenu mnCadastros = new JMenu("Cadastros");

        miCadasUsuario = new JMenuItem("Usuário");
        mnCadastros.add(miCadasUsuario);
        miCadasUsuario.addActionListener(this);

        miCadasClientes = new JMenuItem("Clientes");
        mnCadastros.add(miCadasClientes);
        miCadasClientes.addActionListener(this);

        miCadasFuncionario = new JMenuItem("Funcionários");
        mnCadastros.add(miCadasFuncionario);
        miCadasFuncionario.addActionListener(this);

        miCadasSetor = new JMenuItem("Setor");
        mnCadastros.add(miCadasSetor);
        miCadasSetor.addActionListener(this);

        miCadasDepartamento = new JMenuItem("Departamento");
        mnCadastros.add(miCadasDepartamento);
        miCadasDepartamento.addActionListener(this);

        miCadasEndereco = new JMenuItem("Endereço");
        mnCadastros.add(miCadasEndereco);
        miCadasEndereco.addActionListener(this);

        miCadasFornecedores = new JMenuItem("Fornecedores");
        mnCadastros.add(miCadasFornecedores);
        miCadasFornecedores.addActionListener(this);

        miCadasTransportadora = new JMenuItem("Transportadora");
        mnCadastros.add(miCadasTransportadora);
        miCadasTransportadora.addActionListener(this);

        miCadasProdutos = new JMenuItem("Produtos");
        mnCadastros.add(miCadasProdutos);
        miCadasProdutos.addActionListener(this);

        JMenu mnConsultas = new JMenu("Consultas");

        miConsUsuario = new JMenuItem("Usuários");
        mnConsultas.add(miConsUsuario);
        miConsUsuario.addActionListener(this);

        miConsClientes = new JMenuItem("Clientes");
        mnConsultas.add(miConsClientes);
        miConsClientes.addActionListener(this);

        miConsFuncionario = new JMenuItem("Funcionários");
        mnConsultas.add(miConsFuncionario);
        miConsFuncionario.addActionListener(this);

        miConsFornecedor = new JMenuItem("Fornecedores");
        mnConsultas.add(miConsFornecedor);
        miConsFornecedor.addActionListener(this);

        miConsTransportadora = new JMenuItem("Transportadoras");
        mnConsultas.add(miConsTransportadora);
        miConsTransportadora.addActionListener(this);

        miConsProduto = new JMenuItem("Produtos");
        mnConsultas.add(miConsProduto);
        miConsProduto.addActionListener(this);

        JMenu mnSeguranca = new JMenu("Segurança");

        miArqCertificado = new JMenuItem("Certificado");
        mnSeguranca.add(miArqCertificado);
        miArqCertificado.addActionListener(this);

        menuBar.add(mnSistema);
        menuBar.add(mnCadastros);
        menuBar.add(mnConsultas);
        menuBar.add(mnSeguranca);
        setJMenuBar(menuBar);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelBotoes.setBorder(BorderFactory.createTitledBorder(""));
        add(panelBotoes, BorderLayout.NORTH);

        JPanel panelVenda = new JPanel();
        panelVenda.setLayout(null);
        panelVenda.setBackground(new Color(248, 248, 248));
        panelVenda.setBorder(BorderFactory.createTitledBorder(""));
        add(panelVenda, BorderLayout.CENTER);

        JPanel panelDadosVenda = new JPanel();
        panelDadosVenda.setLayout(null);
        panelDadosVenda.setBounds(10, 10, 410, 222);
        panelDadosVenda.setBackground(new Color(248, 248, 248));
        panelDadosVenda.setBorder(BorderFactory.createTitledBorder("Dados Venda"));
        panelVenda.add(panelDadosVenda);

        JLabel lbNumeroVenda = new JLabel("Número");
        lbNumeroVenda.setBounds(20, 30, 60, 14);
        panelDadosVenda.add(lbNumeroVenda);

        tfNumeroVenda = new JTextField();
        tfNumeroVenda.setBounds(20, 48, 80, 20);
        try {
            tfNumeroVenda.setText(Integer.toString(controleVendas.getProxCodVenda()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfNumeroVenda.setEditable(false);
        tfNumeroVenda.setBackground(Color.WHITE);
        tfNumeroVenda.addFocusListener(this);
        panelDadosVenda.add(tfNumeroVenda);

        btConsulta = new JButton("...");
        btConsulta.setBounds(106, 45, 31, 26);
        btConsulta.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsulta.setToolTipText("Consulta Vendas");
        btConsulta.addActionListener(this);
        panelDadosVenda.add(btConsulta);

        JLabel lbDataEmissao = new JLabel("Data Emissão");
        lbDataEmissao.setBounds(143, 30, 90, 14);
        panelDadosVenda.add(lbDataEmissao);

        tfDataEmissao = new JTextField();
        tfDataEmissao.setBounds(143, 48, 120, 20);
        tfDataEmissao.setEditable(false);
        tfDataEmissao.setBackground(Color.WHITE);
        tfDataEmissao.addFocusListener(this);
        panelDadosVenda.add(tfDataEmissao);

        JLabel lbSituacao = new JLabel("Situação");
        lbSituacao.setBounds(270, 30, 60, 14);
        panelDadosVenda.add(lbSituacao);

        tfSituacao = new JTextField();
        tfSituacao.setBounds(270, 48, 120, 20);
        tfSituacao.setEditable(false);
        tfSituacao.setBackground(Color.WHITE);
        tfSituacao.addFocusListener(this);
        panelDadosVenda.add(tfSituacao);

        JLabel lbCodFunc = new JLabel("Cod. Func");
        lbCodFunc.setBounds(20, 73, 60, 14);
        panelDadosVenda.add(lbCodFunc);

        tfCodFunc = new JTextField();
        tfCodFunc.setBounds(20, 91, 80, 20);
        tfCodFunc.setEditable(false);
        tfCodFunc.setBackground(Color.WHITE);
        tfCodFunc.addFocusListener(this);
        panelDadosVenda.add(tfCodFunc);

        btConsultaFunc = new JButton("...");
        btConsultaFunc.setBounds(106, 88, 31, 26);
        btConsultaFunc.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaFunc.setToolTipText("Consulta Funcionário");
        btConsultaFunc.addActionListener(this);
        panelDadosVenda.add(btConsultaFunc);

        JLabel lbNomeFunc = new JLabel("Funcionário - Vendedor");
        lbNomeFunc.setBounds(143, 73, 120, 14);
        panelDadosVenda.add(lbNomeFunc);

        tfNomeFunc = new JTextField();
        tfNomeFunc.setBounds(143, 91, 246, 20);
        tfNomeFunc.setEditable(false);
        tfNomeFunc.setBackground(Color.WHITE);
        tfNomeFunc.addFocusListener(this);
        panelDadosVenda.add(tfNomeFunc);

        JLabel lbTipo = new JLabel("Tipo");
        lbTipo.setBounds(20, 116, 50, 14);
        panelDadosVenda.add(lbTipo);

        cbTipo = new JComboBox();
        cbTipo.setBounds(20, 134, 90, 20);
        cbTipo.addItem("Selecione");
        cbTipo.addItem("Orçamento");
        cbTipo.addItem("Nova Venda");
        cbTipo.addFocusListener(this);
        panelDadosVenda.add(cbTipo);

        tfTipo = new JTextField();
        tfTipo.setBounds(20, 134, 90, 20);
        tfTipo.setEditable(false);
        tfTipo.setBackground(Color.WHITE);
        tfTipo.addFocusListener(this);
        tfTipo.setVisible(false);
        panelDadosVenda.add(tfTipo);

        JLabel lbSubTotal = new JLabel("Sub-Total");
        lbSubTotal.setBounds(120, 116, 60, 14);
        panelDadosVenda.add(lbSubTotal);

        tfSubTotal = new JTextField();
        tfSubTotal.setBounds(120, 134, 80, 20);
        tfSubTotal.setText("R$ 0,00");
        tfSubTotal.setEditable(false);
        tfSubTotal.setBackground(Color.WHITE);
        tfSubTotal.addFocusListener(this);
        panelDadosVenda.add(tfSubTotal);

        JLabel lbDescontos = new JLabel("Descontos");
        lbDescontos.setBounds(210, 116, 70, 14);
        panelDadosVenda.add(lbDescontos);

        tfDescontos = new JTextField();
        tfDescontos.setBounds(210, 134, 80, 20);
        tfDescontos.setBackground(Color.WHITE);
        tfDescontos.addFocusListener(this);
        panelDadosVenda.add(tfDescontos);

        JLabel lbPorcentagem = new JLabel("%");
        lbPorcentagem.setBounds(293, 137, 14, 14);
        panelDadosVenda.add(lbPorcentagem);

        JLabel lbTotal = new JLabel("Total");
        lbTotal.setBounds(310, 116, 50, 14);
        panelDadosVenda.add(lbTotal);

        tfTotal = new JTextField();
        tfTotal.setBounds(310, 134, 75, 20);
        tfTotal.setText("R$ 0,00");
        tfTotal.setEditable(false);
        tfTotal.setBackground(Color.WHITE);
        tfTotal.addFocusListener(this);
        panelDadosVenda.add(tfTotal);

        JLabel lbCondPagto = new JLabel("Cond. Pagto");
        lbCondPagto.setBounds(20, 159, 60, 14);
        panelDadosVenda.add(lbCondPagto);

        cbCondPagto = new JComboBox();
        cbCondPagto.setBounds(20, 177, 90, 20);
        cbCondPagto.addItem("Selecione");
        cbCondPagto.addItem("Á Vista");
        cbCondPagto.addItem("1X");
        cbCondPagto.addItem("2X");
        cbCondPagto.addItem("3X");
        cbCondPagto.addItem("4X");
        cbCondPagto.addItem("5X");
        cbCondPagto.addItem("6X");
        cbCondPagto.addItem("7X");
        cbCondPagto.addItem("8X");
        cbCondPagto.addFocusListener(this);
        panelDadosVenda.add(cbCondPagto);

        tfCondPagto = new JTextField();
        tfCondPagto.setBounds(20, 177, 90, 20);
        tfCondPagto.setEditable(false);
        tfCondPagto.setBackground(Color.WHITE);
        tfCondPagto.addFocusListener(this);
        tfCondPagto.setVisible(false);
        panelDadosVenda.add(tfCondPagto);

        JLabel lbFormaPagto = new JLabel("Forma Pagto");
        lbFormaPagto.setBounds(120, 159, 65, 14);
        panelDadosVenda.add(lbFormaPagto);

        cbFormaPagto = new JComboBox();
        cbFormaPagto.setBounds(120, 177, 90, 20);
        cbFormaPagto.addItem("Selecione");
        cbFormaPagto.addItem("Dinheiro");
        cbFormaPagto.addItem("Cheque");
        cbFormaPagto.addItem("Cartão");
        cbFormaPagto.addFocusListener(this);
        panelDadosVenda.add(cbFormaPagto);

        tfFormaPagto = new JTextField();
        tfFormaPagto.setBounds(120, 177, 90, 20);
        tfFormaPagto.setEditable(false);
        tfFormaPagto.setBackground(Color.WHITE);
        tfFormaPagto.addFocusListener(this);
        tfFormaPagto.setVisible(false);
        panelDadosVenda.add(tfFormaPagto);

        JLabel lbJuros = new JLabel("Juros");
        lbJuros.setBounds(220, 159, 50, 14);
        panelDadosVenda.add(lbJuros);

        tfJuros = new JTextField();
        tfJuros.setBounds(220, 177, 70, 20);
        tfJuros.setBackground(Color.WHITE);
        tfJuros.addFocusListener(this);
        panelDadosVenda.add(tfJuros);

        lbPorcentagem = new JLabel("%");
        lbPorcentagem.setBounds(293, 180, 14, 14);
        panelDadosVenda.add(lbPorcentagem);

        JLabel lbParcelasMes = new JLabel("Parcelas Mês");
        lbParcelasMes.setBounds(310, 159, 70, 14);
        panelDadosVenda.add(lbParcelasMes);

        tfParcelasMes = new JTextField();
        tfParcelasMes.setBounds(310, 177, 75, 20);
        tfParcelasMes.setText("R$ 0,00");
        tfParcelasMes.setEditable(false);
        tfParcelasMes.setBackground(Color.WHITE);
        tfParcelasMes.addFocusListener(this);
        panelDadosVenda.add(tfParcelasMes);

        JPanel panelDadosCliente = new JPanel();
        panelDadosCliente.setLayout(null);
        panelDadosCliente.setBounds(420, 10, 490, 170);
        panelDadosCliente.setBackground(new Color(248, 248, 248));
        panelDadosCliente.setBorder(BorderFactory.createTitledBorder("Dados Cliente"));
        panelVenda.add(panelDadosCliente);

        JLabel lbCodCliente = new JLabel("Cod. Cliente");
        lbCodCliente.setBounds(20, 30, 60, 14);
        panelDadosCliente.add(lbCodCliente);

        tfCodCliente = new JTextField();
        tfCodCliente.setBounds(20, 48, 80, 20);
        tfCodCliente.setEditable(false);
        tfCodCliente.setBackground(Color.WHITE);
        tfCodCliente.addFocusListener(this);
        panelDadosCliente.add(tfCodCliente);

        btConsultaClie = new JButton("...");
        btConsultaClie.setBounds(106, 45, 31, 26);
        btConsultaClie.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaClie.setToolTipText("Consulta Cliente");
        btConsultaClie.addActionListener(this);
        panelDadosCliente.add(btConsultaClie);

        JLabel lbNomeClie = new JLabel("Cliente");
        lbNomeClie.setBounds(143, 30, 50, 14);
        panelDadosCliente.add(lbNomeClie);

        tfNomeClie = new JTextField();
        tfNomeClie.setBounds(143, 48, 200, 20);
        tfNomeClie.setEditable(false);
        tfNomeClie.setBackground(Color.WHITE);
        tfNomeClie.addFocusListener(this);
        panelDadosCliente.add(tfNomeClie);

        JLabel lbCpfCnpjClie = new JLabel("CPF / CNPJ");
        lbCpfCnpjClie.setBounds(350, 30, 60, 14);
        panelDadosCliente.add(lbCpfCnpjClie);

        tfCpfCnpjClie = new JTextField();
        tfCpfCnpjClie.setBounds(350, 48, 120, 20);
        tfCpfCnpjClie.setEditable(false);
        tfCpfCnpjClie.setBackground(Color.WHITE);
        tfCpfCnpjClie.addFocusListener(this);
        panelDadosCliente.add(tfCpfCnpjClie);

        JLabel lbEnderecoClie = new JLabel("Endereço");
        lbEnderecoClie.setBounds(20, 73, 60, 14);
        panelDadosCliente.add(lbEnderecoClie);

        tfEnderecoClie = new JTextField();
        tfEnderecoClie.setBounds(20, 91, 180, 20);
        tfEnderecoClie.setEditable(false);
        tfEnderecoClie.setBackground(Color.WHITE);
        tfEnderecoClie.addFocusListener(this);
        panelDadosCliente.add(tfEnderecoClie);

        JLabel lbBairroClie = new JLabel("Bairro");
        lbBairroClie.setBounds(210, 73, 60, 14);
        panelDadosCliente.add(lbBairroClie);

        tfBairroClie = new JTextField();
        tfBairroClie.setBounds(210, 91, 170, 20);
        tfBairroClie.setEditable(false);
        tfBairroClie.setBackground(Color.WHITE);
        tfBairroClie.addFocusListener(this);
        panelDadosCliente.add(tfBairroClie);

        JLabel lbNumeroClie = new JLabel("Número");
        lbNumeroClie.setBounds(390, 73, 60, 14);
        panelDadosCliente.add(lbNumeroClie);

        tfNumeroClie = new JTextField();
        tfNumeroClie.setBounds(390, 91, 80, 20);
        tfNumeroClie.setEditable(false);
        tfNumeroClie.setBackground(Color.WHITE);
        tfNumeroClie.addFocusListener(this);
        panelDadosCliente.add(tfNumeroClie);

        JLabel lbCidadeClie = new JLabel("Cidade");
        lbCidadeClie.setBounds(20, 116, 60, 14);
        panelDadosCliente.add(lbCidadeClie);

        tfCidadeClie = new JTextField();
        tfCidadeClie.setBounds(20, 134, 125, 20);
        tfCidadeClie.setEditable(false);
        tfCidadeClie.setBackground(Color.WHITE);
        tfCidadeClie.addFocusListener(this);
        panelDadosCliente.add(tfCidadeClie);

        JLabel lbEstadoClie = new JLabel("Estado");
        lbEstadoClie.setBounds(155, 116, 60, 14);
        panelDadosCliente.add(lbEstadoClie);

        tfEstadoClie = new JTextField();
        tfEstadoClie.setBounds(155, 134, 105, 20);
        tfEstadoClie.setEditable(false);
        tfEstadoClie.setBackground(Color.WHITE);
        tfEstadoClie.addFocusListener(this);
        panelDadosCliente.add(tfEstadoClie);

        JLabel lbFoneClie = new JLabel("Fone");
        lbFoneClie.setBounds(270, 116, 60, 14);
        panelDadosCliente.add(lbFoneClie);

        tfFoneClie = new JTextField();
        tfFoneClie.setBounds(270, 134, 85, 20);
        tfFoneClie.setEditable(false);
        tfFoneClie.setBackground(Color.WHITE);
        tfFoneClie.addFocusListener(this);
        panelDadosCliente.add(tfFoneClie);

        cxCliente = new JCheckBox();
        cxCliente.setBounds(360, 136, 18, 13);
        cxCliente.setBackground(new Color(248, 248, 248));
        panelDadosCliente.add(cxCliente);

        JLabel lbCliente = new JLabel("Cliente não Cadas.");
        lbCliente.setBounds(385, 136, 100, 14);
        panelDadosCliente.add(lbCliente);

        JPanel panelDados = new JPanel();
        panelDados.setLayout(null);
        panelDados.setBounds(420, 180, 490, 50);
        panelDados.setBackground(new Color(248, 248, 248));
        panelDados.setBorder(BorderFactory.createTitledBorder(""));
        panelVenda.add(panelDados);

        JLabel lbDataEntrega = new JLabel("Data Entrega");
        lbDataEntrega.setBounds(20, 5, 70, 14);
        panelDados.add(lbDataEntrega);

        try {
            ftfDataEntrega = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException ex) {
        }
        ftfDataEntrega.setBounds(20, 23, 70, 20);
        ftfDataEntrega.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfDataEntrega.addFocusListener(this);
        panelDados.add(ftfDataEntrega);

        JLabel lbFrete = new JLabel("Frete");
        lbFrete.setBounds(100, 5, 60, 14);
        panelDados.add(lbFrete);

        tfFrete = new JTextField();
        tfFrete.setBounds(100, 23, 60, 20);
        tfFrete.addFocusListener(this);
        panelDados.add(tfFrete);

        JLabel lbValorPago = new JLabel("Valor Pago");
        lbValorPago.setBounds(170, 5, 60, 14);
        panelDados.add(lbValorPago);

        tfValorPago = new JTextField();
        tfValorPago.setBounds(170, 23, 60, 20);
        tfValorPago.setEditable(false);
        tfValorPago.setBackground(Color.WHITE);
        tfValorPago.addFocusListener(this);
        panelDados.add(tfValorPago);

        JLabel lbValorRestante = new JLabel("Valor Restante");
        lbValorRestante.setBounds(240, 5, 80, 14);
        panelDados.add(lbValorRestante);

        tfValorRestante = new JTextField();
        tfValorRestante.setBounds(240, 23, 70, 20);
        tfValorRestante.setText("R$ 0,00");
        tfValorRestante.setEditable(false);
        tfValorRestante.setBackground(Color.WHITE);
        tfValorRestante.addFocusListener(this);
        panelDados.add(tfValorRestante);

        JLabel lbParcelasRestante = new JLabel("Parcelas Restante");
        lbParcelasRestante.setBounds(320, 5, 90, 14);
        panelDados.add(lbParcelasRestante);

        tfParcelasRestante = new JTextField();
        tfParcelasRestante.setBounds(320, 23, 90, 20);
        tfParcelasRestante.setEditable(false);
        tfParcelasRestante.setBackground(Color.WHITE);
        tfParcelasRestante.addFocusListener(this);
        panelDados.add(tfParcelasRestante);

        btPagar = new JButton("Pagar");
        btPagar.setBounds(420, 18, 60, 26);
        btPagar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btPagar.setToolTipText("Pagar");
        btPagar.setEnabled(false);
        btPagar.addActionListener(this);
        panelDados.add(btPagar);

        JPanel panelDadosProd = new JPanel();
        panelDadosProd.setLayout(null);
        panelDadosProd.setBounds(10, 235, 900, 50);
        panelDadosProd.setBackground(new Color(248, 248, 248));
        panelDadosProd.setBorder(BorderFactory.createTitledBorder(""));
        panelVenda.add(panelDadosProd);

        JLabel lbProduto = new JLabel("Produto");
        lbProduto.setBounds(20, 18, 60, 14);
        panelDadosProd.add(lbProduto);

        tfProduto = new JTextField();
        tfProduto.setBounds(65, 15, 150, 20);
        tfProduto.setEditable(false);
        tfProduto.setBackground(Color.WHITE);
        tfProduto.addFocusListener(this);
        panelDadosProd.add(tfProduto);

        btConsultaProd = new JButton("...");
        btConsultaProd.setBounds(220, 12, 31, 26);
        btConsultaProd.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaProd.setToolTipText("Consulta Produtos");
        btConsultaProd.addActionListener(this);
        panelDadosProd.add(btConsultaProd);

        JLabel lbDescricaoProd = new JLabel("Descrição");
        lbDescricaoProd.setBounds(260, 18, 60, 14);
        panelDadosProd.add(lbDescricaoProd);

        tfDescricaoProd = new JTextField();
        tfDescricaoProd.setBounds(310, 15, 150, 20);
        tfDescricaoProd.setEditable(false);
        tfDescricaoProd.setBackground(Color.WHITE);
        tfDescricaoProd.addFocusListener(this);
        panelDadosProd.add(tfDescricaoProd);

        JLabel lbQtdadeProd = new JLabel("Qtdade");
        lbQtdadeProd.setBounds(468, 18, 50, 14);
        panelDadosProd.add(lbQtdadeProd);

        tfQtdadeProd = new JTextField();
        tfQtdadeProd.setBounds(510, 15, 50, 20);
        tfQtdadeProd.setDocument(new CamposInt());
        tfQtdadeProd.addFocusListener(this);
        panelDadosProd.add(tfQtdadeProd);

        JLabel lbValorUnitProd = new JLabel("Valor Unit.");
        lbValorUnitProd.setBounds(567, 18, 60, 14);
        panelDadosProd.add(lbValorUnitProd);

        tfValorUnitProd = new JTextField();
        tfValorUnitProd.setBounds(622, 15, 75, 20);
        tfValorUnitProd.setText("R$ 0,00");
        tfValorUnitProd.setEditable(false);
        tfValorUnitProd.setBackground(Color.WHITE);
        tfValorUnitProd.addFocusListener(this);
        panelDadosProd.add(tfValorUnitProd);

        JLabel lbValorTotalProd = new JLabel("Valor Total.");
        lbValorTotalProd.setBounds(702, 18, 60, 14);
        panelDadosProd.add(lbValorTotalProd);

        tfValorTotalProd = new JTextField();
        tfValorTotalProd.setBounds(760, 15, 75, 20);
        tfValorTotalProd.setText("R$ 0,00");
        tfValorTotalProd.setEditable(false);
        tfValorTotalProd.setBackground(Color.WHITE);
        tfValorTotalProd.addFocusListener(this);
        panelDadosProd.add(tfValorTotalProd);

        btOk = new JButton("OK");
        btOk.setBounds(840, 12, 50, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("OK");
        btOk.setEnabled(false);
        btOk.addActionListener(this);
        panelDadosProd.add(btOk);

        JTable tabela = new JTable(tableModel);
        tabela.getColumnModel().getColumn(0).setMinWidth(208);
        tabela.getColumnModel().getColumn(1).setMinWidth(310);
        tabela.getColumnModel().getColumn(2).setMinWidth(100);
        tabela.getColumnModel().getColumn(3).setMinWidth(140);
        tabela.getColumnModel().getColumn(4).setMinWidth(140);
        tabela.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBounds(10, 295, 900, 165);
        panelVenda.add(scrollTabela);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(10, 465, 60, 14);
        panelVenda.add(lbDescricao);

        taDescricao = new JTextArea();
        taDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        taDescricao.addFocusListener(this);
        JScrollPane scrollArea = new JScrollPane(taDescricao);
        scrollArea.setBounds(10, 480, 720, 80);
        panelVenda.add(scrollArea);

        btConfirmar = new JButton("Confirmar");
        btConfirmar.setBounds(750, 490, 79, 26);
        btConfirmar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConfirmar.setToolTipText("Confirmar Venda");
        btConfirmar.addActionListener(this);
        panelVenda.add(btConfirmar);

        btGerar = new JButton("Gerar");
        btGerar.setBounds(840, 490, 70, 26);
        btGerar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btGerar.setToolTipText("Gerar Comprovante");
        btGerar.addActionListener(this);
        btGerar.setEnabled(false);
        panelVenda.add(btGerar);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(750, 525, 79, 26);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Limpar Campos");
        btCancelar.addActionListener(this);
        panelVenda.add(btCancelar);

        btEncerar = new JButton("Encerar");
        btEncerar.setBounds(840, 525, 70, 26);
        btEncerar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btEncerar.setToolTipText("Encerar");
        btEncerar.setEnabled(false);
        btEncerar.addActionListener(this);
        panelVenda.add(btEncerar);

        JPanel panelPersistencia = new JPanel();
        panelPersistencia.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelPersistencia.setBorder(BorderFactory.createTitledBorder(""));
        panelPersistencia.setPreferredSize(new Dimension(350, 40));

        JLabel lbPersistencia = new JLabel("Persistência de dados");
        panelPersistencia.add(lbPersistencia);

        JTextField tfPersistencia = new JTextField("Banco de Dados: Oracle");
        tfPersistencia.setEditable(false);
        tfPersistencia.setBackground(Color.WHITE);
        tfPersistencia.setPreferredSize(new Dimension(160, 22));
        tfPersistencia.addActionListener(this);
        panelPersistencia.add(tfPersistencia);

        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelUsuario.setBorder(BorderFactory.createTitledBorder(""));
        panelUsuario.setPreferredSize(new Dimension(310, 40));

        JLabel lbUsuario = new JLabel("Usuário: " + usuario);
        lbUsuario.setPreferredSize(new Dimension(150, 14));
        panelUsuario.add(lbUsuario);

        JLabel lbPermissao = new JLabel("Permissão: " + permissao);
        panelUsuario.add(lbPermissao);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder(""));

        lbHorarioSistema = new JLabel("");
        lbHorarioSistema.setFont(new Font("Arial", Font.BOLD, 15));
        lbHorarioSistema.setBounds(75, 15, 140, 15);
        ThreadHoraSistema thread = new ThreadHoraSistema(this, lbHorarioSistema);
        thread.start();
        panel.add(lbHorarioSistema);

        JPanel panelBarraStatus = new JPanel();
        panelBarraStatus.setBorder(BorderFactory.createTitledBorder(""));
        panelBarraStatus.setLayout(new BorderLayout());
        panelBarraStatus.add(panelUsuario, BorderLayout.WEST);
        panelBarraStatus.add(panel, BorderLayout.CENTER);
        panelBarraStatus.add(panelPersistencia, BorderLayout.EAST);
        add(panelBarraStatus, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evento) {
                sair();
            }
        });

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panelDadosVenda.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);
        panelDadosCliente.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);
        panelDadosProd.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);
        panelDados.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(940, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void limparTela() {
        try {
            tfNumeroVenda.setText(Integer.toString(controleVendas.getProxCodVenda()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataEmissao.setText("");
        tfSituacao.setText("");
        cbTipo.setVisible(true);
        cbTipo.setSelectedItem("Selecione");
        tfTipo.setText("");
        tfTipo.setVisible(false);
        tfSubTotal.setText("R$ 0,00");
        tfDescontos.setText("");
        tfTotal.setText("R$ 0,00");
        cbCondPagto.setVisible(true);
        cbCondPagto.setSelectedItem("Selecione");
        tfCondPagto.setText("");
        tfCondPagto.setVisible(false);
        cbFormaPagto.setVisible(true);
        cbFormaPagto.setSelectedItem("Selecione");
        tfFormaPagto.setText("");
        tfFormaPagto.setVisible(false);
        tfJuros.setText("");
        tfParcelasMes.setText("R$ 0,00");
        cxCliente.setSelected(false);
        ftfDataEntrega.setText("");
        tfFrete.setText("");
        tfValorPago.setEditable(false);
        tfValorPago.setText("");
        tfValorRestante.setText("R$ 0,00");
        tfParcelasRestante.setText("");
        taDescricao.setText("");
        btPagar.setEnabled(false);
        btOk.setEnabled(false);
        btEncerar.setEnabled(false);
        btGerar.setEnabled(false);
        btConsultaClie.setEnabled(true);
        btConsultaFunc.setEnabled(true);
        btConfirmar.setEnabled(true);
        cxCliente.setEnabled(true);
        tfDescontos.setEditable(true);
        tfJuros.setEditable(true);
        tfFrete.setEditable(true);
        limparTelaClie();
        limparTelaFunc();
        limparTelaProd();
        controleItensVenda.limparLista();
        tableModel.fireTableDataChanged();
    }

    private void limparTelaClie() {
        tfCodCliente.setText("");
        tfNomeClie.setText("");
        tfCpfCnpjClie.setText("");
        tfEnderecoClie.setText("");
        tfBairroClie.setText("");
        tfNumeroClie.setText("");
        tfCidadeClie.setText("");
        tfEstadoClie.setText("");
        tfFoneClie.setText("");
    }

    private void limparTelaFunc() {
        tfCodFunc.setText("");
        tfNomeFunc.setText("");
    }

    private void limparTelaProd() {
        tfProduto.setText("");
        tfDescricaoProd.setText("");
        tfQtdadeProd.setText("");
        tfValorUnitProd.setText("R$ 0,00");
        tfValorTotalProd.setText("R$ 0,00");
        codProduto = -1;
    }

    private void consultaClie() throws Exception {
        if (controleClie.isClieVazio()) {
            throw new Exception("Não há clientes cadastrados");
        }
        ConsultaClie consulta = new ConsultaClie(controleClie);
        consulta.setListener(new ListenerClie() {
            @Override
            public void exibeClie(Cliente cliente) {
                limparTelaClie();
                tfCodCliente.setText(Integer.toString(cliente.getCodClie()));
                tfNomeClie.setText(cliente.getNome());
                tfCpfCnpjClie.setText(cliente.getCpfCnpj());
                tfEnderecoClie.setText(cliente.getEndereco());
                tfBairroClie.setText(cliente.getBairro());
                tfNumeroClie.setText(Integer.toString(cliente.getNumero()));
                tfCidadeClie.setText(cliente.getCidade());
                tfEstadoClie.setText(cliente.getEstado());
                tfFoneClie.setText(cliente.getFone());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void consultaFunc() throws Exception {
        if (controleFunc.isFuncVazio()) {
            throw new Exception("Não há funcionários cadastrados");
        }
        ConsultaFunc consulta = new ConsultaFunc(controleFunc);
        consulta.setListener(new ListenerFunc() {
            @Override
            public void exibeFunc(Funcionario funcionario) {
                limparTelaFunc();
                tfCodFunc.setText(Integer.toString(funcionario.getCodFunc()));
                tfNomeFunc.setText(funcionario.getNome());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void consultaProd() throws Exception {
        if (controleProd.isProdVazio()) {
            throw new Exception("Não há produtos cadastrados");
        }
        ConsultaProduto consulta = new ConsultaProduto(controleProd);
        consulta.setListener(new ListenerProduto() {
            @Override
            public void exibeProduto(Produto produto) {
                limparTelaProd();
                codProduto = produto.getCodProduto();
                tfProduto.setText(produto.getProduto());
                tfDescricaoProd.setText(produto.getMarca() + " - " + produto.getModelo() + " - " + produto.getDescricao());
                double precoUnitCom = produto.getPrecoCompra();
                precoUnitCom += precoUnitCom * (produto.getPercentualLucro() / 100);
                precoUnitCom += precoUnitCom * (produto.getIpi() / 100);
                precoUnitCom -= precoUnitCom * (produto.getDescontos() / 100);
                tfValorUnitProd.setText(NumberFormat.getCurrencyInstance().format(precoUnitCom));
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void valorTotalProd() {
        if (!"".equals(tfQtdadeProd.getText()) && !"R$ 0,00".equals(tfValorUnitProd.getText())) {
            tfValorTotalProd.setText(NumberFormat.getCurrencyInstance().format(Integer.parseInt(tfQtdadeProd.getText()) * Double.parseDouble(tfValorUnitProd.getText().replace("R$ ", "").replace(".", "").replace(",", "."))));
        }
    }

    private void novaVenda() throws Exception {
        if ("".equals(tfCodFunc.getText())) {
            throw new Exception("Selecione um vendedor");
        }
        if ("Selecione".equals(cbTipo.getSelectedItem())) {
            throw new Exception("Selecione o tipo da venda");
        }
        if ("".equals(tfCodCliente.getText()) && !cxCliente.isSelected()) {
            throw new Exception("Selecione um cliente");
        }
        if (!"".equals(tfCodCliente.getText()) && cxCliente.isSelected()) {
            cxCliente.setSelected(false);
        }
        tfSituacao.setText("Em aberto");
        tfDataEmissao.setText(formatDate.format(new Date()));
        btConfirmar.setEnabled(false);
        btConsultaClie.setEnabled(false);
        btConsultaFunc.setEnabled(false);
        cxCliente.setEnabled(false);
        btOk.setEnabled(true);
        tfTipo.setText((String) cbTipo.getSelectedItem());
        cbTipo.setVisible(false);
        tfTipo.setVisible(true);
        if (controleVendas.insertVenda(gravarVenda())) {
            JOptionPane.showMessageDialog(null, "Nova venda confirmada", "Venda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Vendas gravarVenda() {
        Vendas vendas = new Vendas();
        vendas.setNumVenda(Integer.parseInt(tfNumeroVenda.getText()));
        Funcionario funcionario = new Funcionario();
        funcionario.setCodFunc(Integer.parseInt(tfCodFunc.getText()));
        vendas.setFuncionario(funcionario);
        if (!"".equals(tfCodCliente.getText())) {
            Cliente cliente = new Cliente();
            cliente.setCodClie(Integer.parseInt(tfCodCliente.getText()));
            vendas.setCliente(cliente);
        }
        vendas.setDataEmissao(new Date());
        vendas.setSituacao(tfSituacao.getText());
        vendas.setTipo(tfTipo.getText());
        if ("R$ 0,00".equals(tfSubTotal.getText())) {
            vendas.setSubTotal(-1);
        } else {
            vendas.setSubTotal(Double.parseDouble(tfSubTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
        }
        if ("".equals(tfDescontos.getText())) {
            vendas.setDescontos(-1);
        } else {
            vendas.setDescontos(Double.parseDouble(tfDescontos.getText()));
        }
        if (cbCondPagto.isVisible()) {
            vendas.setCondPagto((String) cbCondPagto.getSelectedItem());
        } else {
            vendas.setCondPagto(tfCondPagto.getText());
        }
        if (cbFormaPagto.isVisible()) {
            vendas.setFormaPagto((String) cbFormaPagto.getSelectedItem());
        } else {
            vendas.setFormaPagto(tfFormaPagto.getText());
        }
        if ("".equals(tfJuros.getText())) {
            vendas.setJuros(-1);
        } else {
            vendas.setJuros(Double.parseDouble(tfJuros.getText()));
        }
        if ("  /  /    ".equals(ftfDataEntrega.getText())) {
            vendas.setDataEntrega(null);
        } else {
            try {
                vendas.setDataEntrega(formatDate.parse(ftfDataEntrega.getText()));
            } catch (ParseException ex) {
            }
        }
        if ("".equals(tfFrete.getText())) {
            vendas.setFrete(-1);
        } else {
            vendas.setFrete(Double.parseDouble(tfFrete.getText()));
        }
        if ("".equals(tfParcelasRestante.getText())) {
            vendas.setParcelasRes(-1);
        } else {
            vendas.setParcelasRes(Integer.parseInt(tfParcelasRestante.getText()));
        }
        vendas.setDescricao(taDescricao.getText());
        return vendas;
    }

    private void gravarItemVenda() throws Exception {
        if ("".equals(tfProduto.getText())) {
            throw new Exception("Selecione um produto");
        }
        if ("".equals(tfQtdadeProd.getText())) {
            throw new Exception("Informe a quantidade");
        }
        if (Integer.parseInt(tfQtdadeProd.getText()) == 0) {
            throw new Exception("Quantidade não pode ser 0");
        }
        if ("Nova Venda".equals(tfTipo.getText())) {
            int qtdade = controleProd.getQtdadeProd(codProduto);
            if (qtdade < Integer.parseInt(tfQtdadeProd.getText())) {
                throw new Exception("Quantidade não disponível");
            }
            controleProd.updateQtdadeProduto(codProduto, (qtdade - Integer.parseInt(tfQtdadeProd.getText())));
        }
        ItensVenda item = new ItensVenda();
        Vendas vendas = new Vendas();
        vendas.setNumVenda(Integer.parseInt(tfNumeroVenda.getText()));
        item.setVendas(vendas);
        Produto produto = new Produto();
        produto.setCodProduto(codProduto);
        item.setProduto(produto);
        item.setQtdade(Integer.parseInt(tfQtdadeProd.getText()));
        if (controleItensVenda.insertItemVenda(item)) {
            controleItensVenda.listarItens(item.getVendas().getNumVenda());
            tableModel.fireTableDataChanged();
            tfSubTotal.setText(NumberFormat.getCurrencyInstance().format((Double.parseDouble(tfSubTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")) + Double.parseDouble(tfValorTotalProd.getText().replace("R$ ", "").replace(".", "").replace(",", ".")))));
            btEncerar.setEnabled(true);
            controleVendas.updateSusbTotalVenda(Double.parseDouble(tfSubTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")), Integer.parseInt(tfNumeroVenda.getText()));
            JOptionPane.showMessageDialog(null, "Novo item cadastrado", "Venda", JOptionPane.INFORMATION_MESSAGE);
            limparTelaProd();
        }
    }

    private void descontos() throws Exception {
        if (!"R$ 0,00".equals(tfSubTotal.getText()) && !"".equals(tfDescontos.getText())) {
            try {
                double descontos = Double.parseDouble(tfDescontos.getText());
                if (descontos < 0 || descontos > 100) {
                    throw new Exception("Desconto inválido, valor deve ser entre 0 e 100");
                }
                double subTotal = Double.parseDouble(tfSubTotal.getText().replace("R$ ", "").replace(".", "").replace(",", "."));
                subTotal -= subTotal * (descontos / 100);
                tfTotal.setText(NumberFormat.getCurrencyInstance().format(subTotal));
            } catch (NumberFormatException ex) {
                tfTotal.setText("R$ 0,00");
                throw new Exception("Desconto inválido");
            }
        } else {
            tfTotal.setText("R$ 0,00");
        }
    }

    private void frete() throws Exception {
        if (!"".equals(tfFrete.getText())) {
            try {
                tfSubTotal.setText(NumberFormat.getCurrencyInstance().format(Double.parseDouble(tfSubTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")) + Double.parseDouble(tfFrete.getText())));
            } catch (NumberFormatException ex) {
                throw new Exception("Frete inválido");
            }
        }
    }

    private void juros() throws Exception {
        if ("Á Vista".equals(cbCondPagto.getSelectedItem())) {
            return;
        }
        if (!"Selecione".equals(cbCondPagto.getSelectedItem()) && !"".equals(tfJuros.getText()) && !"R$ 0,00".equals(tfTotal.getText())) {
            try {
                double result = Double.parseDouble(tfTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")) / Integer.parseInt((String) cbCondPagto.getSelectedItem().toString().substring(0, 1));
                result += result * (Double.parseDouble(tfJuros.getText()) / 100);
                tfParcelasMes.setText(NumberFormat.getCurrencyInstance().format(result));
            } catch (NumberFormatException ex) {
                tfParcelasMes.setText("R$ 0,00");
                throw new Exception("Juros inválido");
            }
        } else {
            tfParcelasMes.setText("R$ 0,00");
        }
    }

    private void encerar() throws Exception {
        if ("R$ 0,00".equals(tfTotal.getText())) {
            throw new Exception("Informe o total da venda");
        }
        if ("Selecione".equals(cbCondPagto.getSelectedItem())) {
            throw new Exception("Selecione a condição de pagamento");
        }
        if ("Selecione".equals(cbFormaPagto.getSelectedItem())) {
            throw new Exception("Selecione a forma de pagamento");
        }
        if ("Á Vista".equals(cbCondPagto.getSelectedItem())) {
            frete();
            descontos();
            juros();
            btPagar.setEnabled(false);
            tfSituacao.setText("Encerado - Pago");
        } else {
            if ("".equals(tfJuros.getText().trim())) {
                throw new Exception("Informe os júros da venda");
            }
            if ("R$ 0,00".equals(tfParcelasMes.getText())) {
                throw new Exception("Informe o valor da parcelas");
            }
            frete();
            descontos();
            juros();
            tfParcelasRestante.setText((String) cbCondPagto.getSelectedItem().toString().substring(0, 1));
            calculoValorRestante();
            btPagar.setEnabled(true);
            tfValorPago.setEditable(true);
            tfSituacao.setText("Encerado - Aberto");
        }
        if (controleVendas.updateVenda(gravarVenda())) {
            btEncerar.setEnabled(false);
            btGerar.setEnabled(true);
            btOk.setEnabled(false);
            cbCondPagto.setVisible(false);
            tfCondPagto.setVisible(true);
            tfCondPagto.setText((String) cbCondPagto.getSelectedItem());
            cbFormaPagto.setVisible(false);
            tfFormaPagto.setVisible(true);
            tfFormaPagto.setText((String) cbFormaPagto.getSelectedItem());
            tfDescontos.setEditable(false);
            tfJuros.setEditable(false);
            tfFrete.setEditable(false);
            JOptionPane.showMessageDialog(null, "Venda encerada", "Venda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void calculoValorRestante() {
        int condPagto = Integer.parseInt(cbCondPagto.getSelectedItem().toString().substring(0, 1));
        double resultTotal = Double.parseDouble(tfTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")) / condPagto;
        resultTotal += resultTotal * (Double.parseDouble(tfJuros.getText()) / 100);
        resultTotal = resultTotal * condPagto;
        tfValorRestante.setText(NumberFormat.getCurrencyInstance().format(resultTotal));
    }

    private void consultaVendas() throws Exception {
        if (controleVendas.isVendaVazio()) {
            throw new Exception("Não há vendas cadastradas");
        }
        ConsultaVendas consulta = new ConsultaVendas(controleVendas);
        consulta.setListener(new ListenerVendas() {
            @Override
            public void exibeVenda(Vendas vendas) {
                limparTela();
                try {
                    controleItensVenda.listarItens(vendas.getNumVenda());
                    tableModel.fireTableDataChanged();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                tfNumeroVenda.setText(Integer.toString(vendas.getNumVenda()));
                tfDataEmissao.setText(formatDate.format(vendas.getDataEmissao()));
                tfSituacao.setText(vendas.getSituacao());
                tfCodFunc.setText(Integer.toString(vendas.getFuncionario().getCodFunc()));
                tfNomeFunc.setText(vendas.getFuncionario().getNome());
                tfTipo.setVisible(true);
                tfTipo.setText(vendas.getTipo());
                cbTipo.setVisible(false);
                tfSubTotal.setText(NumberFormat.getCurrencyInstance().format(vendas.getSubTotal()));
                if (vendas.getDataEntrega() != null) {
                    ftfDataEntrega.setText(formatDate.format(vendas.getDataEntrega()));
                }
                if (vendas.getCliente().getCodClie() == -1) {
                    cxCliente.setSelected(true);
                } else {
                    tfCodCliente.setText(Integer.toString(vendas.getCliente().getCodClie()));
                    tfNomeClie.setText(vendas.getCliente().getNome());
                    tfCpfCnpjClie.setText(vendas.getCliente().getCpfCnpj());
                    tfEnderecoClie.setText(vendas.getCliente().getEndereco());
                    tfBairroClie.setText(vendas.getCliente().getBairro());
                    tfNumeroClie.setText(Integer.toString(vendas.getCliente().getNumero()));
                    tfCidadeClie.setText(vendas.getCliente().getCidade());
                    tfEstadoClie.setText(vendas.getCliente().getEstado());
                    tfFoneClie.setText(vendas.getCliente().getFone());
                }
                taDescricao.setText(vendas.getDescricao());
                cxCliente.setEnabled(false);
                if ("Em aberto".equals(vendas.getSituacao())) {
                    btOk.setEnabled(true);
                    if (!controleItensVenda.isListaVazia()) {
                        btEncerar.setEnabled(true);
                    }
                } else {
                    tfDescontos.setEditable(false);
                    tfDescontos.setText(Double.toString(vendas.getDescontos()));
                    tfCondPagto.setVisible(true);
                    tfCondPagto.setText(vendas.getCondPagto());
                    cbCondPagto.setVisible(false);
                    tfFormaPagto.setVisible(true);
                    tfFormaPagto.setText(vendas.getFormaPagto());
                    cbFormaPagto.setVisible(false);
                    if (vendas.getFrete() != -1) {
                        tfFrete.setText(Double.toString(vendas.getFrete()));
                    }
                    if (vendas.getParcelasRes() != -1) {
                        tfParcelasRestante.setText(Integer.toString(vendas.getParcelasRes()));
                    }
                    try {
                        descontos();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    if ("Encerado - Pago".equals(vendas.getSituacao())) {
                        if (!"Á Vista".equals(vendas.getCondPagto())) {
                            btPagar.setEnabled(false);
                            tfValorPago.setEditable(false);
                            calculoRecuperaValores(vendas);
                        }
                    } else {
                        if ("Encerado - Aberto".equals(vendas.getSituacao())) {
                            btPagar.setEnabled(true);
                            tfValorPago.setEditable(true);
                            calculoRecuperaValores(vendas);
                        }
                    }
                    tfJuros.setEditable(false);
                    tfFrete.setEditable(false);
                    btEncerar.setEnabled(false);
                    btGerar.setEnabled(true);
                    btOk.setEnabled(false);
                }
                btConsultaFunc.setEnabled(false);
                btConsultaClie.setEnabled(false);
                btConfirmar.setEnabled(false);
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void calculoRecuperaValores(Vendas vendas) {
        cbCondPagto.setSelectedItem(vendas.getCondPagto());
        tfJuros.setText(Double.toString(vendas.getJuros()));
        try {
            juros();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        calculoValorRestante();
        double valorRestante = Double.parseDouble(tfValorRestante.getText().replace("R$ ", "").replace(".", "").replace(",", "."));
        double parcelasMes = Double.parseDouble(tfParcelasMes.getText().replace("R$ ", "").replace(".", "").replace(",", "."));
        int parcelasRes = Integer.parseInt(tfParcelasRestante.getText());
        int condPagto = Integer.parseInt(tfCondPagto.getText().toString().substring(0, 1));
        parcelasRes = condPagto - parcelasRes;
        while (parcelasRes > 0) {
            valorRestante = Double.parseDouble(tfValorRestante.getText().replace("R$ ", "").replace(".", "").replace(",", "."));
            parcelasMes = Double.parseDouble(tfParcelasMes.getText().replace("R$ ", "").replace(".", "").replace(",", "."));
            tfValorRestante.setText(NumberFormat.getCurrencyInstance().format((valorRestante - parcelasMes)));
            parcelasRes--;
        }
    }

    private void pagar() throws Exception {
        double valorRestante = Double.parseDouble(tfValorRestante.getText().replace("R$ ", "").replace(".", "").replace(",", "."));
        double parcelasMes = Double.parseDouble(tfParcelasMes.getText().replace("R$ ", "").replace(".", "").replace(",", "."));
        int parcelasRes = Integer.parseInt(tfParcelasRestante.getText());
        try {
            double valorPago = Double.parseDouble(tfValorPago.getText());
            if (valorPago == parcelasMes) {
                parcelasRes--;
                tfParcelasRestante.setText(Integer.toString((parcelasRes)));
                tfValorRestante.setText(NumberFormat.getCurrencyInstance().format((valorRestante - parcelasMes)));
                if (parcelasRes == 0) {
                    tfSituacao.setText("Encerado - Pago");
                    tfValorPago.setEditable(false);
                    btPagar.setEnabled(false);
                }
                tfValorPago.setText("");
                controleVendas.updateParcelasResVenda(parcelasRes, Integer.parseInt(tfNumeroVenda.getText()), tfSituacao.getText());
                return;
            }
            throw new Exception("Informe um valor a ser pago conforme o valor das percelas");
        } catch (NumberFormatException ex) {
            throw new Exception("Informe um valor a ser pago conforme o valor das percelas");
        }
    }

    private boolean validaData() {
        if (!"  /  /    ".equals(ftfDataEntrega.getText())) {
            int dia = Integer.parseInt(ftfDataEntrega.getText().substring(0, 2));
            int mes = Integer.parseInt(ftfDataEntrega.getText().substring(3, 5));
            int ano = Integer.parseInt(ftfDataEntrega.getText().substring(6, 10));
            if (dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12 && ano >= 1900 && ano < 2100) {
                return true;
            }
            return false;
        }
        return true;
    }

    private void validaJFormattedTextField() {
        if (ftfDataEntrega.getText().split(" ").length > 1 || ftfDataEntrega.getText().trim().length() < ftfDataEntrega.getText().length()) {
            ftfDataEntrega.setText("");
        }
    }

    public void setHorarioSistema(String horario) {
        lbHorarioSistema.setText(horario);
    }

    private void sair() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente sair do sistema", "Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                con.close();
                System.exit(JFrame.EXIT_ON_CLOSE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão com o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cadasClientes() {
        CadasCliente cliente = new CadasCliente(con);
        cliente.setModal(true);
        cliente.setVisible(true);
    }

    private void cadasEnderecos() {
        CadasEndereco endereco = new CadasEndereco(con);
        endereco.setModal(true);
        endereco.setVisible(true);
    }

    private void cadasSetores() {
        try {
            CadasSetor setor = new CadasSetor(con, seguranca);
            setor.setModal(true);
            setor.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cadasDepartamentos() {
        CadasDepartamento departamento = new CadasDepartamento(con, seguranca);
        departamento.setModal(true);
        departamento.setVisible(true);
    }

    private void cadasUsuarios() {
        CadasUsuario usuario = new CadasUsuario(con, seguranca);
        usuario.setModal(true);
        usuario.setVisible(true);
    }

    private void cadasFuncionarios() {
        CadasFuncionario funcionario = new CadasFuncionario(con, seguranca);
        funcionario.setModal(true);
        funcionario.setVisible(true);
    }

    private void cadasFornecedores() {
        CadasFornecedor fornecedor = new CadasFornecedor(con, seguranca);
        fornecedor.setModal(true);
        fornecedor.setVisible(true);
    }

    private void cadasTransportadoras() {
        CadasTransportadora transportadora = new CadasTransportadora(con, seguranca);
        transportadora.setModal(true);
        transportadora.setVisible(true);
    }

    private void cadasProdutos() {
        CadasProduto produto = new CadasProduto(con);
        produto.setModal(true);
        produto.setVisible(true);
    }

    private void consUsuarios() throws Exception {
        UsuarioControl controle = new UsuarioControl(con);
        if (controle.isUsuarioVazio()) {
            throw new Exception("Não há usuários cadastrados");
        } else {
            ConsultaUsuario usuario = new ConsultaUsuario(controle);
            usuario.setModal(true);
            usuario.setVisible(true);
        }
    }

    private void consClientes() throws Exception {
        ClienteControl controle = new ClienteControl(con);
        if (controle.isClieVazio()) {
            throw new Exception("Não há clientes cadastrados");
        } else {
            ConsultaClie cliente = new ConsultaClie(controle);
            cliente.setModal(true);
            cliente.setVisible(true);
        }
    }

    private void consFuncionarios() throws Exception {
        FuncionarioControl controle = new FuncionarioControl(con);
        if (controle.isFuncVazio()) {
            throw new Exception("Não há funcionários cadastrados");
        } else {
            ConsultaFunc funcionario = new ConsultaFunc(controle);
            funcionario.setModal(true);
            funcionario.setVisible(true);
        }
    }

    private void consFornecedores() throws Exception {
        FornecedorControl controle = new FornecedorControl(con);
        if (controle.isFornVazio()) {
            throw new Exception("Não há fornecedores cadastrados");
        } else {
            ConsultaForn fornecedore = new ConsultaForn(controle);
            fornecedore.setModal(true);
            fornecedore.setVisible(true);
        }
    }

    private void consTransportadoras() throws Exception {
        TransportadoraControl controle = new TransportadoraControl(con);
        if (controle.isTransVazio()) {
            throw new Exception("Não há transportadoras cadastradas");
        } else {
            ConsultaTrans transportadora = new ConsultaTrans(controle);
            transportadora.setModal(true);
            transportadora.setVisible(true);
        }
    }

    private void consProdutos() throws Exception {
        ProdutoControl controle = new ProdutoControl(con);
        if (controle.isProdVazio()) {
            throw new Exception("Não há produtos cadastrados");
        } else {
            ConsultaProduto produto = new ConsultaProduto(controle);
            produto.setModal(true);
            produto.setVisible(true);
        }
    }

    private void certificado() {
        CertificadoDigital certificadoDigital = new CertificadoDigital(seguranca);
        certificadoDigital.setModal(true);
        certificadoDigital.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == miSair) {
            sair();
        }
        if (evento.getSource() == miCadasClientes) {
            cadasClientes();
        }
        if (evento.getSource() == miCadasEndereco) {
            cadasEnderecos();
        }
        if (evento.getSource() == miCadasSetor) {
            cadasSetores();
        }
        if (evento.getSource() == miCadasDepartamento) {
            cadasDepartamentos();
        }
        if (evento.getSource() == miCadasUsuario) {
            cadasUsuarios();
        }
        if (evento.getSource() == miCadasFuncionario) {
            cadasFuncionarios();
        }
        if (evento.getSource() == miCadasFornecedores) {
            cadasFornecedores();
        }
        if (evento.getSource() == miCadasTransportadora) {
            cadasTransportadoras();
        }
        if (evento.getSource() == miCadasProdutos) {
            cadasProdutos();
        }
        if (evento.getSource() == miConsUsuario) {
            try {
                consUsuarios();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miConsClientes) {
            try {
                consClientes();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miConsFuncionario) {
            try {
                consFuncionarios();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miConsFornecedor) {
            try {
                consFornecedores();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miConsTransportadora) {
            try {
                consTransportadoras();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miConsProduto) {
            try {
                consProdutos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == miArqCertificado) {
            certificado();
        }
        if (evento.getSource() == btConsultaClie) {
            try {
                consultaClie();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaFunc) {
            try {
                consultaFunc();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaProd) {
            try {
                consultaProd();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsulta) {
            try {
                consultaVendas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btConfirmar) {
            try {
                novaVenda();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOk) {
            try {
                gravarItemVenda();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btEncerar) {
            try {
                encerar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btPagar) {
            try {
                pagar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfNumeroVenda) {
            tfNumeroVenda.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDataEmissao) {
            tfDataEmissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSituacao) {
            tfSituacao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodFunc) {
            tfCodFunc.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeFunc) {
            tfNomeFunc.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfCondPagto) {
            tfCondPagto.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTipo) {
            tfTipo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfFormaPagto) {
            tfFormaPagto.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfJuros) {
            tfJuros.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfParcelasMes) {
            tfParcelasMes.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodCliente) {
            tfCodCliente.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeClie) {
            tfNomeClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCpfCnpjClie) {
            tfCpfCnpjClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEnderecoClie) {
            tfEnderecoClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBairroClie) {
            tfBairroClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumeroClie) {
            tfNumeroClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCidadeClie) {
            tfCidadeClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEstadoClie) {
            tfEstadoClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfFoneClie) {
            tfFoneClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfFrete) {
            tfFrete.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorPago) {
            tfValorPago.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorRestante) {
            tfValorRestante.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfParcelasRestante) {
            tfParcelasRestante.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfProduto) {
            tfProduto.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricaoProd) {
            tfDescricaoProd.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfQtdadeProd) {
            tfQtdadeProd.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorUnitProd) {
            tfValorUnitProd.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorTotalProd) {
            tfValorTotalProd.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataEntrega) {
            ftfDataEntrega.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == taDescricao) {
            taDescricao.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfNumeroVenda.setBackground(Color.WHITE);
        tfDataEmissao.setBackground(Color.WHITE);
        tfSituacao.setBackground(Color.WHITE);
        tfCodFunc.setBackground(Color.WHITE);
        tfNomeFunc.setBackground(Color.WHITE);
        tfSubTotal.setBackground(Color.WHITE);
        if (evento.getSource() == tfDescontos) {
            try {
                descontos();
                juros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            tfDescontos.setBackground(Color.WHITE);
        }
        tfTotal.setBackground(Color.WHITE);
        tfCondPagto.setBackground(Color.WHITE);
        tfTipo.setBackground(Color.WHITE);
        tfFormaPagto.setBackground(Color.WHITE);
        if (evento.getSource() == tfJuros) {
            tfJuros.setBackground(Color.WHITE);
            try {
                juros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfParcelasMes.setBackground(Color.WHITE);
        tfCodCliente.setBackground(Color.WHITE);
        tfNomeClie.setBackground(Color.WHITE);
        tfCpfCnpjClie.setBackground(Color.WHITE);
        tfEnderecoClie.setBackground(Color.WHITE);
        tfBairroClie.setBackground(Color.WHITE);
        tfNumeroClie.setBackground(Color.WHITE);
        tfCidadeClie.setBackground(Color.WHITE);
        tfEstadoClie.setBackground(Color.WHITE);
        tfFoneClie.setBackground(Color.WHITE);
        tfFrete.setBackground(Color.WHITE);
        tfValorPago.setBackground(Color.WHITE);
        tfValorRestante.setBackground(Color.WHITE);
        tfParcelasRestante.setBackground(Color.WHITE);
        tfProduto.setBackground(Color.WHITE);
        tfDescricaoProd.setBackground(Color.WHITE);
        if (evento.getSource() == tfQtdadeProd) {
            tfQtdadeProd.setBackground(Color.WHITE);
            valorTotalProd();
        }
        tfValorUnitProd.setBackground(Color.WHITE);
        tfValorTotalProd.setBackground(Color.WHITE);
        if (evento.getSource() == ftfDataEntrega) {
            validaJFormattedTextField();
            if (!validaData()) {
                JOptionPane.showMessageDialog(null, "Data inválida entre com uma data válida", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            ftfDataEntrega.setBackground(Color.WHITE);
        }
        taDescricao.setBackground(Color.WHITE);
        if (evento.getSource() == cbCondPagto) {
            try {
                juros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

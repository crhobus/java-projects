package Vendas;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Clientes.ClienteControl;
import Clientes.ListenerCliente;
import Clientes.PesquisaClientes;
import Formatacao.CamposInt;
import Formatacao.ObjGraficos;
import Fornecedores.FornecedorControl;
import Fornecedores.ListenerFornecedor;
import Fornecedores.PesquisaFornecedores;
import Funcionarios.FuncionarioControl;
import Modelo.Cliente;
import Modelo.Fornecedor;
import Modelo.ItensVenda;
import Modelo.Produto;
import Modelo.Venda;
import OrdensServicos.RenderizadoraItens;
import Produtos.ListenerProduto;
import Produtos.PesquisaProdutos;
import Produtos.ProdutoControl;

public class NovaVenda extends ObjGraficos implements ActionListener, FocusListener {

    private static final long serialVersionUID = 720088034715399173L;
    private JTextField tfNumeroVen, tfDataEmissao, tfSituacao, tfVendedor,
            tfEmpresa, tfCNPJFornecedor, tfNomeFornecedor, tfSubTotal,
            tfDescontos, tfTotal, tfJuros, tfParcelasMes, tfCpfCnpjClie,
            tfNomeClie, tfEnderecoClie, tfBairroClie, tfNumeroClie,
            tfCidadeClie, tfUFClie, tfFoneClie, tfCelularClie, tfEMailClie,
            tfFrete, tfProduto, tfDescricao, tfUN, tfValorUnit, tfValorTotal,
            tfValorPago, tfValorRestante, tfParcelasRestante, tfTipo,
            tfCondPagto, tfFormaPagto;
    private JFormattedTextField ftfFoneEmpresa, ftfCNPJ, ftfIE, ftfDataEntrega;
    private JComboBox cbTipo, cbCondPagto, cbFormaPagto;
    private JTextArea taDescricao;
    private JButton btPesquisa, btPesquisaCliente, btOk, btConfirmar,
            btCancelar, btPesquisaFor, btPagar, btPesquisaProd, btGerar,
            btEncerar;
    private JLabel lbNomeVenObrig, lbTipoObrig, lbCPFCNPJClieObrig,
            lbNomeProdObrig, lbUNProdObrig;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Connection con;
    private TableModelItensVenda tableModel;
    private NovaVendaControl controleNovaVenda;
    private ItensVendaControl controleItemVenda;
    private RenderizadoraItens rendener;

    public NovaVenda(Connection con) {
        this.con = con;
        controleNovaVenda = new NovaVendaControl(con);
        controleItemVenda = new ItensVendaControl(con);
        tableModel = new TableModelItensVenda(controleItemVenda);
        rendener = new RenderizadoraItens();
        try {
            initComponents();
        } catch (ParseException ex) {
        }
    }

    private void initComponents() throws ParseException {
        List<String> itensCombo = new ArrayList<String>();
        setTitle("Nova Venda");
        Container tela = getContentPane();
        tela.setLayout(null);
        tela.setBackground(new Color(248, 248, 248));

        JPanel panelVenda = getJPanelTitledBorder("Dados Venda", 10, 10, 410, 290);
        tela.add(panelVenda);

        JPanel panelCliente = getJPanelTitledBorder("Dados Clientes", 425, 10, 410, 200);
        tela.add(panelCliente);

        JPanel panelInfGerais = getJPanelTitledBorder("", 425, 210, 410, 88);
        tela.add(panelInfGerais);

        JPanel panelItens = getJPanelTitledBorder("", 12, 303, 823, 50);
        tela.add(panelItens);

        JPanel panelConfirmacao = getJPanelTitledBorder("", 12, 527, 815, 95);
        tela.add(panelConfirmacao);

        panelVenda.add(getJLabel("Número", 20, 30, 80, 14));
        tfNumeroVen = getJTextField(20, 48, 80, 20);
        try {
            tfNumeroVen.setText(Integer.toString(controleNovaVenda.getProxNumVen()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        tfNumeroVen.setEditable(false);
        tfNumeroVen.setBackground(Color.WHITE);
        tfNumeroVen.addFocusListener(this);
        panelVenda.add(tfNumeroVen);

        btPesquisa = getJButton("...", 106, 44, 31, 24);
        btPesquisa.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisa.setToolTipText("Consulta Vendas");
        btPesquisa.addActionListener(this);
        panelVenda.add(btPesquisa);

        panelVenda.add(getJLabel("Emissão", 143, 30, 90, 14));
        tfDataEmissao = getJTextField(143, 48, 120, 20);
        tfDataEmissao.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataEmissao.setEditable(false);
        tfDataEmissao.setBackground(Color.WHITE);
        tfDataEmissao.addFocusListener(this);
        panelVenda.add(tfDataEmissao);

        panelVenda.add(getJLabel("Situacao", 270, 30, 100, 14));
        tfSituacao = getJTextField(270, 48, 120, 20);
        tfSituacao.setEditable(false);
        tfSituacao.setBackground(Color.WHITE);
        tfSituacao.addFocusListener(this);
        panelVenda.add(tfSituacao);

        panelVenda.add(getJLabel("Vendedor", 20, 70, 120, 14));
        lbNomeVenObrig = getJLabel("", 78, 73, 10, 14);
        lbNomeVenObrig.setForeground(Color.RED);
        panelVenda.add(lbNomeVenObrig);
        tfVendedor = getJTextField(20, 88, 190, 20);
        tfVendedor.setBackground(Color.WHITE);
        tfVendedor.addFocusListener(this);
        panelVenda.add(tfVendedor);

        panelVenda.add(getJLabel("Empresa", 220, 70, 100, 14));
        tfEmpresa = getJTextField(220, 88, 170, 20);
        tfEmpresa.addFocusListener(this);
        panelVenda.add(tfEmpresa);

        panelVenda.add(getJLabel("Fone Empresa", 20, 110, 90, 14));
        ftfFoneEmpresa = getJFormattedTextField("(##)####-####", 20, 128, 110, 20);
        ftfFoneEmpresa.addFocusListener(this);
        panelVenda.add(ftfFoneEmpresa);

        panelVenda.add(getJLabel("CNPJ", 140, 110, 80, 14));
        ftfCNPJ = getJFormattedTextField("##.###.###/####-##", 140, 128, 125, 20);
        ftfCNPJ.addFocusListener(this);
        panelVenda.add(ftfCNPJ);

        panelVenda.add(getJLabel("I.E.", 275, 110, 110, 14));
        ftfIE = getJFormattedTextField("###.###.###", 275, 128, 115, 20);
        ftfIE.addFocusListener(this);
        panelVenda.add(ftfIE);

        panelVenda.add(getJLabel("Tipo", 20, 150, 90, 14));
        lbTipoObrig = getJLabel("", 50, 153, 10, 14);
        lbTipoObrig.setForeground(Color.RED);
        panelVenda.add(lbTipoObrig);
        itensCombo.add("Orçamento");
        itensCombo.add("Nova Venda");
        cbTipo = getJComboBox(itensCombo, 20, 168, 95, 20);
        cbTipo.addFocusListener(this);
        panelVenda.add(cbTipo);
        tfTipo = getJTextField(20, 168, 95, 20);
        tfTipo.setEditable(false);
        tfTipo.setBackground(Color.WHITE);
        tfTipo.addFocusListener(this);
        tfTipo.setVisible(false);
        panelVenda.add(tfTipo);

        panelVenda.add(getJLabel("CNPJ Fornecedor", 125, 150, 100, 14));
        tfCNPJFornecedor = getJTextField(125, 168, 120, 20);
        tfCNPJFornecedor.setEditable(false);
        tfCNPJFornecedor.setBackground(Color.WHITE);
        tfCNPJFornecedor.addFocusListener(this);
        panelVenda.add(tfCNPJFornecedor);

        btPesquisaFor = getJButton("...", 250, 164, 31, 24);
        btPesquisaFor.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisaFor.setToolTipText("Consulta Fornecedores");
        btPesquisaFor.addActionListener(this);
        panelVenda.add(btPesquisaFor);

        panelVenda.add(getJLabel("Nome Fornecedor", 290, 150, 120, 14));
        tfNomeFornecedor = getJTextField(290, 168, 100, 20);
        tfNomeFornecedor.setEditable(false);
        tfNomeFornecedor.setBackground(Color.WHITE);
        tfNomeFornecedor.addFocusListener(this);
        panelVenda.add(tfNomeFornecedor);

        panelVenda.add(getJLabel("Sub-Total", 20, 190, 120, 14));
        tfSubTotal = getJTextField(20, 208, 115, 20);
        tfSubTotal.setText("R$ 0,00");
        tfSubTotal.setEditable(false);
        tfSubTotal.setBackground(Color.WHITE);
        tfSubTotal.addFocusListener(this);
        panelVenda.add(tfSubTotal);

        panelVenda.add(getJLabel("Descontos", 145, 190, 120, 14));
        tfDescontos = getJTextField(145, 208, 105, 20);
        tfDescontos.setBackground(Color.WHITE);
        tfDescontos.addFocusListener(this);
        panelVenda.add(tfDescontos);
        panelVenda.add(getJLabel("%", 253, 211, 10, 14));

        panelVenda.add(getJLabel("Total", 270, 190, 120, 14));
        tfTotal = getJTextField(270, 208, 120, 20);
        tfTotal.setText("R$ 0,00");
        tfTotal.setEditable(false);
        tfTotal.setBackground(Color.WHITE);
        tfTotal.addFocusListener(this);
        panelVenda.add(tfTotal);

        panelVenda.add(getJLabel("Cond. Pagto", 20, 230, 90, 14));
        itensCombo.clear();
        itensCombo.add("Á Vista");
        itensCombo.add("1X");
        itensCombo.add("2X");
        itensCombo.add("3X");
        itensCombo.add("4X");
        itensCombo.add("5X");
        itensCombo.add("6X");
        itensCombo.add("7X");
        itensCombo.add("8X");
        cbCondPagto = getJComboBox(itensCombo, 20, 248, 90, 20);
        cbCondPagto.addFocusListener(this);
        cbCondPagto.addActionListener(this);
        panelVenda.add(cbCondPagto);
        tfCondPagto = getJTextField(20, 248, 90, 20);
        tfCondPagto.setEditable(false);
        tfCondPagto.setBackground(Color.WHITE);
        tfCondPagto.addFocusListener(this);
        tfCondPagto.setVisible(false);
        panelVenda.add(tfCondPagto);

        panelVenda.add(getJLabel("Forma. Pagto", 120, 230, 90, 14));
        itensCombo.clear();
        itensCombo.add("Dinheiro");
        itensCombo.add("Cheque");
        itensCombo.add("Cartão");
        cbFormaPagto = getJComboBox(itensCombo, 120, 248, 90, 20);
        cbFormaPagto.addFocusListener(this);
        panelVenda.add(cbFormaPagto);
        tfFormaPagto = getJTextField(120, 248, 90, 20);
        tfFormaPagto.setEditable(false);
        tfFormaPagto.setBackground(Color.WHITE);
        tfFormaPagto.addFocusListener(this);
        tfFormaPagto.setVisible(false);
        panelVenda.add(tfFormaPagto);

        panelVenda.add(getJLabel("Juros", 220, 230, 120, 14));
        tfJuros = getJTextField(220, 248, 72, 20);
        tfJuros.setBackground(Color.WHITE);
        tfJuros.addFocusListener(this);
        panelVenda.add(tfJuros);
        panelVenda.add(getJLabel("%", 296, 251, 10, 14));

        panelVenda.add(getJLabel("Parcelas Mês", 310, 230, 120, 14));
        tfParcelasMes = getJTextField(310, 248, 80, 20);
        tfParcelasMes.setText("R$ 0,00");
        tfParcelasMes.setEditable(false);
        tfParcelasMes.setBackground(Color.WHITE);
        tfParcelasMes.addFocusListener(this);
        panelVenda.add(tfParcelasMes);

        panelCliente.add(getJLabel("CPF / CNPJ", 20, 30, 120, 14));
        lbCPFCNPJClieObrig = getJLabel("", 85, 33, 10, 14);
        lbCPFCNPJClieObrig.setForeground(Color.RED);
        panelCliente.add(lbCPFCNPJClieObrig);
        tfCpfCnpjClie = getJTextField(20, 48, 130, 20);
        tfCpfCnpjClie.setEditable(false);
        tfCpfCnpjClie.setBackground(Color.WHITE);
        tfCpfCnpjClie.addFocusListener(this);
        panelCliente.add(tfCpfCnpjClie);

        btPesquisaCliente = getJButton("...", 156, 44, 31, 24);
        btPesquisaCliente.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisaCliente.setToolTipText("Consulta Clientes");
        btPesquisaCliente.addActionListener(this);
        panelCliente.add(btPesquisaCliente);

        panelCliente.add(getJLabel("Nome", 193, 30, 120, 14));
        tfNomeClie = getJTextField(193, 48, 200, 20);
        tfNomeClie.setEditable(false);
        tfNomeClie.setBackground(Color.WHITE);
        tfNomeClie.addFocusListener(this);
        panelCliente.add(tfNomeClie);

        panelCliente.add(getJLabel("Endereco", 20, 70, 120, 14));
        tfEnderecoClie = getJTextField(20, 88, 140, 20);
        tfEnderecoClie.setEditable(false);
        tfEnderecoClie.setBackground(Color.WHITE);
        tfEnderecoClie.addFocusListener(this);
        panelCliente.add(tfEnderecoClie);

        panelCliente.add(getJLabel("Bairro", 170, 70, 120, 14));
        tfBairroClie = getJTextField(170, 88, 130, 20);
        tfBairroClie.setEditable(false);
        tfBairroClie.setBackground(Color.WHITE);
        tfBairroClie.addFocusListener(this);
        panelCliente.add(tfBairroClie);

        panelCliente.add(getJLabel("Número", 310, 70, 120, 14));
        tfNumeroClie = getJTextField(310, 88, 80, 20);
        tfNumeroClie.setEditable(false);
        tfNumeroClie.setBackground(Color.WHITE);
        tfNumeroClie.addFocusListener(this);
        panelCliente.add(tfNumeroClie);

        panelCliente.add(getJLabel("Cidade", 20, 110, 120, 14));
        tfCidadeClie = getJTextField(20, 128, 130, 20);
        tfCidadeClie.setEditable(false);
        tfCidadeClie.setBackground(Color.WHITE);
        tfCidadeClie.addFocusListener(this);
        panelCliente.add(tfCidadeClie);

        panelCliente.add(getJLabel("UF", 160, 110, 50, 14));
        tfUFClie = getJTextField(160, 128, 110, 20);
        tfUFClie.setEditable(false);
        tfUFClie.setBackground(Color.WHITE);
        tfUFClie.addFocusListener(this);
        panelCliente.add(tfUFClie);

        panelCliente.add(getJLabel("Fone", 280, 110, 80, 14));
        tfFoneClie = getJTextField(280, 128, 110, 20);
        tfFoneClie.setEditable(false);
        tfFoneClie.setBackground(Color.WHITE);
        tfFoneClie.addFocusListener(this);
        panelCliente.add(tfFoneClie);

        panelCliente.add(getJLabel("Celular", 20, 150, 70, 14));
        tfCelularClie = getJTextField(20, 168, 110, 20);
        tfCelularClie.setEditable(false);
        tfCelularClie.setBackground(Color.WHITE);
        tfCelularClie.addFocusListener(this);
        panelCliente.add(tfCelularClie);

        panelCliente.add(getJLabel("E-Mail", 140, 150, 90, 14));
        tfEMailClie = getJTextField(140, 168, 250, 20);
        tfEMailClie.setEditable(false);
        tfEMailClie.setBackground(Color.WHITE);
        tfEMailClie.addFocusListener(this);
        panelCliente.add(tfEMailClie);

        panelInfGerais.add(getJLabel("Data Entrega", 20, 5, 90, 14));
        ftfDataEntrega = getJFormattedTextField("##/##/####", 20, 23, 80, 20);
        ftfDataEntrega.addFocusListener(this);
        panelInfGerais.add(ftfDataEntrega);

        panelInfGerais.add(getJLabel("Frete", 110, 5, 70, 14));
        tfFrete = getJTextField(110, 23, 90, 20);
        tfFrete.addFocusListener(this);
        panelInfGerais.add(tfFrete);

        panelInfGerais.add(getJLabel("Valor Pago", 210, 5, 70, 14));
        tfValorPago = getJTextField(210, 23, 80, 20);
        tfValorPago.setEditable(false);
        tfValorPago.setBackground(Color.WHITE);
        tfValorPago.addFocusListener(this);
        panelInfGerais.add(tfValorPago);

        panelInfGerais.add(getJLabel("Valor Restante", 300, 5, 90, 14));
        tfValorRestante = getJTextField(300, 23, 90, 20);
        tfValorRestante.setText("R$ 0,00");
        tfValorRestante.setEditable(false);
        tfValorRestante.setBackground(Color.WHITE);
        tfValorRestante.addFocusListener(this);
        panelInfGerais.add(tfValorRestante);

        panelInfGerais.add(getJLabel("Parcelas Restantes", 20, 45, 115, 14));
        tfParcelasRestante = getJTextField(20, 63, 110, 20);
        tfParcelasRestante.setEditable(false);
        tfParcelasRestante.setBackground(Color.WHITE);
        tfParcelasRestante.addFocusListener(this);
        panelInfGerais.add(tfParcelasRestante);

        btPagar = getJButton("Pagar", 145, 55, 68, 26);
        btPagar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btPagar.setToolTipText("Pagar Parcela");
        btPagar.setEnabled(false);
        btPagar.addActionListener(this);
        panelInfGerais.add(btPagar);

        btGerar = getJButton("Gerar", 230, 55, 68, 26);
        btGerar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btGerar.setToolTipText("Gerar Relatório");
        btGerar.setEnabled(false);
        btGerar.addActionListener(this);
        panelInfGerais.add(btGerar);

        btEncerar = getJButton("Encerar", 310, 55, 79, 26);
        btEncerar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btEncerar.setToolTipText("Encerar Venda");
        btEncerar.setEnabled(false);
        btEncerar.addActionListener(this);
        panelInfGerais.add(btEncerar);

        panelItens.add(getJLabel("Produto", 6, 15, 50, 14));
        lbNomeProdObrig = getJLabel("", 53, 18, 10, 14);
        lbNomeProdObrig.setForeground(Color.RED);
        panelItens.add(lbNomeProdObrig);
        tfProduto = getJTextField(60, 13, 120, 20);
        tfProduto.setEditable(false);
        tfProduto.setBackground(Color.WHITE);
        tfProduto.addFocusListener(this);
        panelItens.add(tfProduto);

        btPesquisaProd = getJButton("...", 185, 10, 31, 24);
        btPesquisaProd.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisaProd.setToolTipText("Consulta Produtos");
        btPesquisaProd.setEnabled(false);
        btPesquisaProd.addActionListener(this);
        panelItens.add(btPesquisaProd);

        panelItens.add(getJLabel("Descrição", 220, 15, 80, 14));
        tfDescricao = getJTextField(283, 13, 130, 20);
        tfDescricao.setEditable(false);
        tfDescricao.setBackground(Color.WHITE);
        tfDescricao.addFocusListener(this);
        panelItens.add(tfDescricao);

        panelItens.add(getJLabel("UN", 417, 15, 40, 14));
        lbUNProdObrig = getJLabel("", 434, 18, 10, 14);
        lbUNProdObrig.setForeground(Color.RED);
        panelItens.add(lbUNProdObrig);
        tfUN = getJTextField(440, 13, 40, 20);
        tfUN.setDocument(new CamposInt());
        tfUN.addFocusListener(this);
        panelItens.add(tfUN);

        panelItens.add(getJLabel("Valor Unit", 485, 15, 80, 14));
        tfValorUnit = getJTextField(547, 13, 75, 20);
        tfValorUnit.setText("R$ 0,00");
        tfValorUnit.setEditable(false);
        tfValorUnit.setBackground(Color.WHITE);
        tfValorUnit.addFocusListener(this);
        panelItens.add(tfValorUnit);

        panelItens.add(getJLabel("Valor Total", 625, 15, 80, 14));
        tfValorTotal = getJTextField(690, 13, 75, 20);
        tfValorTotal.setText("R$ 0,00");
        tfValorTotal.setEditable(false);
        tfValorTotal.setBackground(Color.WHITE);
        tfValorTotal.addFocusListener(this);
        panelItens.add(tfValorTotal);

        btOk = getJButton("OK", 767, 10, 51, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("OK");
        btOk.setEnabled(false);
        btOk.addActionListener(this);
        panelItens.add(btOk);

        JTable tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(0).setMinWidth(50);
        tabela.getColumnModel().getColumn(1).setMinWidth(211);
        tabela.getColumnModel().getColumn(2).setMinWidth(315);
        tabela.getColumnModel().getColumn(3).setMinWidth(110);
        tabela.getColumnModel().getColumn(4).setMinWidth(110);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(12, 358, 823, 165);
        tela.add(scroll);

        panelConfirmacao.add(getJLabel("Descrição", 10, 4, 110, 14));
        taDescricao = getJTextArea(panelConfirmacao, 10, 20, 690, 68);
        taDescricao.setBackground(Color.WHITE);
        taDescricao.addFocusListener(this);

        btConfirmar = getJButton("Confirmar", 710, 15, 91, 26);
        btConfirmar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConfirmar.setToolTipText("Confirmar Venda");
        btConfirmar.addActionListener(this);
        panelConfirmacao.add(btConfirmar);

        btCancelar = getJButton("Cancelar", 710, 50, 91, 26);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        panelConfirmacao.add(btCancelar);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panelVenda.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panelVenda.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);
        panelCliente.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);
        panelInfGerais.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);
        panelItens.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);
        panelConfirmacao.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(855, 660);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfNumeroVen.setText(Integer.toString(controleNovaVenda.getProxNumVen()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataEmissao.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfSituacao.setText("");
        tfVendedor.setText("");
        tfEmpresa.setText("");
        ftfFoneEmpresa.setText("");
        ftfCNPJ.setText("");
        ftfIE.setText("");
        cbTipo.setSelectedItem("Selecione");
        tfSubTotal.setText("R$ 0,00");
        tfDescontos.setText("");
        tfTotal.setText("R$ 0,00");
        tfCondPagto.setText("");
        tfCondPagto.setVisible(false);
        cbCondPagto.setVisible(true);
        cbCondPagto.setSelectedItem("Selecione");
        tfFormaPagto.setText("");
        tfFormaPagto.setVisible(false);
        cbFormaPagto.setVisible(true);
        cbFormaPagto.setSelectedItem("Selecione");
        tfJuros.setText("");
        tfParcelasMes.setText("R$ 0,00");
        ftfDataEntrega.setText("");
        tfFrete.setText("");
        tfValorPago.setText("");
        tfValorRestante.setText("R$ 0,00");
        tfParcelasRestante.setText("");
        taDescricao.setText("");
        limparTelaFornecedor();
        limparTelaCliente();
        limparTelaProduto();
        lbNomeVenObrig.setText("");
        lbTipoObrig.setText("");
        lbCPFCNPJClieObrig.setText("");
        lbNomeProdObrig.setText("");
        lbUNProdObrig.setText("");
        btConfirmar.setEnabled(true);
        btOk.setEnabled(false);
        btPesquisaFor.setEnabled(true);
        btPesquisaCliente.setEnabled(true);
        btPesquisaProd.setEnabled(false);
        btEncerar.setEnabled(false);
        tfVendedor.setEditable(true);
        cbTipo.setVisible(true);
        tfTipo.setVisible(false);
        tfTipo.setText("");
        tfJuros.setEditable(true);
        tfDescontos.setEditable(true);
        btGerar.setEnabled(false);
        btPagar.setEnabled(false);
        tfValorPago.setEditable(false);
        controleItemVenda.limparLista();
        tableModel.fireTableDataChanged();
    }

    private void limparTelaFornecedor() {
        tfCNPJFornecedor.setText("");
        tfNomeFornecedor.setText("");
    }

    private void limparTelaCliente() {
        tfCpfCnpjClie.setText("");
        tfNomeClie.setText("");
        tfEnderecoClie.setText("");
        tfBairroClie.setText("");
        tfNumeroClie.setText("");
        tfCidadeClie.setText("");
        tfUFClie.setText("");
        tfFoneClie.setText("");
        tfCelularClie.setText("");
        tfEMailClie.setText("");
    }

    private void limparTelaProduto() {
        tfProduto.setText("");
        tfDescricao.setText("");
        tfUN.setText("");
        tfValorUnit.setText("R$ 0,00");
        tfValorTotal.setText("R$ 0,00");
    }

    private void recuperarCliente() throws Exception {
        ClienteControl controlClie = new ClienteControl(con);
        if (controlClie.isClieVazio()) {
            throw new Exception("Não há clientes cadastrados");
        }
        JTextField entrada = new JTextField();
        final Object[] mensagem = {"Informe o CPF / CNPJ do cliente", entrada};
        final String[] opcoes = {"OK", "Consultar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Cliente", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (result) {
            case 0: // Ok
                recuperarCliente(controlClie, entrada.getText());
                break;
            case 1: // Consultar
                PesquisaClientes pesquisaClie = new PesquisaClientes(controlClie);
                pesquisaClie.setListener(new ListenerCliente() {

                    @Override
                    public void exibeCliente(Cliente cliente) {
                        mostrarCliente(cliente);
                    }
                });
                pesquisaClie.setModal(true);
                pesquisaClie.setVisible(true);
                break;
            case 2: // Cancelar
                break;
        }
    }

    private void recuperarCliente(ClienteControl controlClie, String cpfCnpj) throws Exception {
        Cliente cliente = controlClie.selectCliente(cpfCnpj);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mostrarCliente(cliente);
        }
    }

    private void mostrarCliente(Cliente cliente) {
        limparTelaCliente();
        tfCpfCnpjClie.setText(cliente.getCpfCNPJ());
        tfNomeClie.setText(cliente.getNome());
        tfEnderecoClie.setText(cliente.getEndereco());
        tfBairroClie.setText(cliente.getBairro());
        tfNumeroClie.setText(Integer.toString(cliente.getNumero()));
        tfCidadeClie.setText(cliente.getCidade());
        tfUFClie.setText(cliente.getUf());
        tfFoneClie.setText(cliente.getFone());
        tfCelularClie.setText(cliente.getCelular1());
        tfEMailClie.setText(cliente.geteMail());
    }

    private void recuperarFornecedor() throws Exception {
        FornecedorControl controlForn = new FornecedorControl(con);
        if (controlForn.isFornVazio()) {
            throw new Exception("Não há fornecedores cadastrados");
        }
        JFormattedTextField entrada = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
        final Object[] mensagem = {"Informe CNPJ do fornecedor", entrada};
        final String[] opcoes = {"OK", "Consultar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Fornecedor", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (result) {
            case 0: // Ok
                recuperarFornecedor(controlForn, entrada.getText());
                break;
            case 1: // Consultar
                PesquisaFornecedores pesquisaForn = new PesquisaFornecedores(controlForn);
                pesquisaForn.setListener(new ListenerFornecedor() {

                    @Override
                    public void exibeFornecedor(Fornecedor fornecedor) {
                        mostrarFornecedor(fornecedor);
                    }
                });
                pesquisaForn.setModal(true);
                pesquisaForn.setVisible(true);
                break;
            case 2: // Cancelar
                break;
        }
    }

    private void recuperarFornecedor(FornecedorControl controlForn, String cnpj) throws Exception {
        Fornecedor fornecedor = controlForn.selectFornecedor(cnpj);
        if (fornecedor == null) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mostrarFornecedor(fornecedor);
        }
    }

    private void mostrarFornecedor(Fornecedor fornecedor) {
        limparTelaFornecedor();
        tfCNPJFornecedor.setText(fornecedor.getCnpj());
        tfNomeFornecedor.setText(fornecedor.getNome());
    }

    private void recuperarProduto() throws Exception {
        ProdutoControl controlProd = new ProdutoControl(con);
        if (controlProd.isProdVazio()) {
            throw new Exception("Não há produtos cadastrados");
        }
        JTextField entrada = new JTextField();
        final Object[] mensagem = {"Informe o nome do produto", entrada};
        final String[] opcoes = {"OK", "Consultar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Produto", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (result) {
            case 0: // Ok
                recuperarProduto(controlProd, entrada.getText());
                break;
            case 1: // Consultar
                PesquisaProdutos pesquisaProd = new PesquisaProdutos(controlProd);
                pesquisaProd.setListener(new ListenerProduto() {

                    @Override
                    public void exibeProduto(Produto produto) {
                        mostrarProduto(produto);

                    }
                });
                pesquisaProd.setModal(true);
                pesquisaProd.setVisible(true);
                break;
            case 2: // Cancelar
                break;
        }
    }

    private void recuperarProduto(ProdutoControl controlProd, String nome) throws Exception {
        Produto produto = controlProd.selectProduto(nome);
        if (produto == null) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado", "Produto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mostrarProduto(produto);
        }
    }

    private void mostrarProduto(Produto produto) {
        limparTelaProduto();
        tfProduto.setText(produto.getNome());
        tfDescricao.setText(produto.getMarca() + " - " + produto.getModelo() + " - " + produto.getDescricao());
        tfValorUnit.setText(NumberFormat.getCurrencyInstance().format(produto.getPrecoUnitVenda()));
    }

    private void verificaFuncionario(JTextField tf) throws Exception {
        if (!"".equals(tf.getText().trim())) {
            FuncionarioControl controlFunc = new FuncionarioControl(con);
            if (controlFunc.isFuncCadas(tf.getText()) == false) {
                tf.setText("");
                tf.grabFocus();
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void calculaValorTotalItem() throws Exception {
        if (!"".equals(tfUN.getText()) && !"R$ 0,00".equals(tfValorUnit.getText())) {
            try {
                tfValorTotal.setText(NumberFormat.getCurrencyInstance().format(Double.parseDouble(tfUN.getText()) * Double.parseDouble(tfValorUnit.getText().replace("R$ ", "").replace(".", "").replace(",", "."))));
            } catch (NumberFormatException ex) {
                tfValorTotal.setText("R$ 0,00");
                throw new Exception("Caracter inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
            }
        } else {
            tfValorTotal.setText("R$ 0,00");
        }
    }

    private void gravarNovaVenda() throws Exception {
        if ("".equals(tfVendedor.getText().trim())) {
            lbNomeVenObrig.setText("*");
        } else {
            lbNomeVenObrig.setText("");
        }
        if ("Selecione".equals(cbTipo.getSelectedItem())) {
            lbTipoObrig.setText("*");
        } else {
            lbTipoObrig.setText("");
        }
        if ("".equals(tfCpfCnpjClie.getText())) {
            lbCPFCNPJClieObrig.setText("*");
        } else {
            lbCPFCNPJClieObrig.setText("");
        }
        if (!"".equals(tfVendedor.getText().trim()) && !"Selecione".equals(cbTipo.getSelectedItem()) && !"".equals(tfCpfCnpjClie.getText())) {
            tfSituacao.setText("Aberto");
            if (controleNovaVenda.insertVenda(novaVenda())) {
                btConfirmar.setEnabled(false);
                btOk.setEnabled(true);
                btPesquisaFor.setEnabled(false);
                btPesquisaCliente.setEnabled(false);
                btPesquisaProd.setEnabled(true);
                tfVendedor.setEditable(false);
                cbTipo.setVisible(false);
                tfTipo.setVisible(true);
                tfTipo.setText((String) cbTipo.getSelectedItem());
                JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso", "Venda", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private Venda novaVenda() throws Exception {
        Venda venda = new Venda();
        venda.setNumeroVen(Integer.parseInt(tfNumeroVen.getText()));
        venda.setParcelasRes(verificaInt(tfParcelasRestante, "parcelas restante"));
        venda.setSituacao(tfSituacao.getText());
        venda.setVendedor(tfVendedor.getText());
        venda.setEmpresa(tfEmpresa.getText());
        venda.setFoneEmpresa(ftfFoneEmpresa.getText());
        venda.setCnpj(ftfCNPJ.getText());
        venda.setIe(ftfIE.getText());
        if (cbTipo.isVisible()) {
            venda.setTipo((String) cbTipo.getSelectedItem());
        } else {
            venda.setTipo(tfTipo.getText());
        }
        venda.setCnpjFornecedor(tfCNPJFornecedor.getText());
        if (cbCondPagto.isVisible()) {
            venda.setCondPagto((String) cbCondPagto.getSelectedItem());
        } else {
            venda.setCondPagto(tfCondPagto.getText());
        }
        if (cbFormaPagto.isVisible()) {
            venda.setFormaPagto((String) cbFormaPagto.getSelectedItem());
        } else {
            venda.setFormaPagto(tfFormaPagto.getText());
        }
        venda.setCpfCnpjClie(tfCpfCnpjClie.getText());
        venda.setDescricao(taDescricao.getText());
        venda.setSubTotal(Double.parseDouble(tfSubTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
        venda.setDescontos(verificaDouble(tfDescontos, "descontos"));
        venda.setTotal(Double.parseDouble(tfTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
        venda.setJuros(verificaDouble(tfJuros, "juros"));
        venda.setParcelasMes(Double.parseDouble(tfParcelasMes.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
        venda.setFrete(verificaDouble(tfFrete, "frete"));
        venda.setValorRestante(Double.parseDouble(tfValorRestante.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
        venda.setDataEmissao(formatDateHora.parse(tfDataEmissao.getText().replace(" as ", " ").concat(":00")));
        venda.setDataEntrega(verificaDate(ftfDataEntrega, "Data Entrega"));
        return venda;
    }

    private void recuperarVenda() throws Exception {
        if (controleNovaVenda.isVendaVazio()) {
            throw new Exception("Não há vendas cadastradas");
        }
        JTextField entrada = new JTextField();
        entrada.setDocument(new CamposInt());
        final Object[] mensagem = {"Entre com  número da venda", entrada};
        final String[] opcoes = {"OK", "Consultar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Venda", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (result) {
            case 0: // Ok
                if (!"".equals(entrada.getText())) {
                    recuperarVenda(Integer.parseInt(entrada.getText()));
                    break;
                }
                break;
            case 1: // Consultar
                PesquisaVendas pesquisaVendas = new PesquisaVendas(controleNovaVenda);
                pesquisaVendas.setListener(new ListenerVenda() {

                    @Override
                    public void exibeVenda(Venda venda) {
                        try {
                            mostrarVenda(venda);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                pesquisaVendas.setModal(true);
                pesquisaVendas.setVisible(true);
                break;
            case 2: // Cancelar
                break;
        }
    }

    private void recuperarVenda(int numero) throws Exception {
        Venda venda = controleNovaVenda.selectVenda(numero);
        if (venda != null) {
            mostrarVenda(venda);
            JOptionPane.showMessageDialog(null, "Venda recuperada com sucesso", "Venda", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Venda não encontrada", "Venda", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarVenda(Venda venda) throws Exception {
        limparTela();
        tfNumeroVen.setText(Integer.toString(venda.getNumeroVen()));
        tfParcelasRestante.setText(recuperaCampoStr(venda.getParcelasRes()));
        tfSituacao.setText(venda.getSituacao());
        tfVendedor.setText(venda.getVendedor());
        tfEmpresa.setText(venda.getEmpresa());
        ftfFoneEmpresa.setText(venda.getFoneEmpresa());
        ftfCNPJ.setText(venda.getCnpj());
        ftfIE.setText(venda.getIe());
        tfTipo.setText(venda.getTipo());
        if (!"".equals(venda.getCnpjFornecedor())) {
            FornecedorControl controlForn = new FornecedorControl(con);
            recuperarFornecedor(controlForn, venda.getCnpjFornecedor());
        }
        cbCondPagto.setSelectedItem(venda.getCondPagto());
        cbFormaPagto.setSelectedItem(venda.getFormaPagto());
        ClienteControl controlClie = new ClienteControl(con);
        recuperarCliente(controlClie, venda.getCpfCnpjClie());
        taDescricao.setText(venda.getDescricao());
        tfSubTotal.setText(NumberFormat.getCurrencyInstance().format(venda.getSubTotal()));
        tfDescontos.setText(recuperaCampoStr(venda.getDescontos()));
        tfTotal.setText(NumberFormat.getCurrencyInstance().format(venda.getTotal()));
        tfJuros.setText(recuperaCampoStr(venda.getJuros()));
        tfParcelasMes.setText(NumberFormat.getCurrencyInstance().format(venda.getParcelasMes()));
        tfFrete.setText(recuperaCampoStr(venda.getFrete()));
        tfValorRestante.setText(NumberFormat.getCurrencyInstance().format(venda.getValorRestante()));
        tfDataEmissao.setText(formatDate.format(venda.getDataEmissao()) + " as " + formatHora.format(venda.getDataEmissao()));
        ftfDataEntrega.setText(recuperaCampoDate(venda.getDataEntrega()));
        btConfirmar.setEnabled(false);
        cbTipo.setVisible(false);
        tfTipo.setVisible(true);
        tfVendedor.setEditable(false);
        btPesquisaFor.setEnabled(false);
        btPesquisaCliente.setEnabled(false);
        if ("Á Vista".equals(cbCondPagto.getSelectedItem())) {
            tfJuros.setText("");
            tfParcelasMes.setText("R$ 0,00");
            tfJuros.setEditable(false);
        }
        if ("Aberto".equals(venda.getSituacao())) {
            btOk.setEnabled(true);
            btPesquisaProd.setEnabled(true);
            btEncerar.setEnabled(true);
            controleItemVenda.listarItens(Integer.parseInt(tfNumeroVen.getText()));
            tableModel.fireTableDataChanged();
        } else {
            btOk.setEnabled(false);
            btPesquisaProd.setEnabled(false);
            btGerar.setEnabled(true);
            btEncerar.setEnabled(false);
            tfDescontos.setEditable(false);
            tfJuros.setEditable(false);
            cbCondPagto.setVisible(false);
            tfCondPagto.setVisible(true);
            tfCondPagto.setText(venda.getCondPagto());
            cbFormaPagto.setVisible(false);
            tfFormaPagto.setVisible(true);
            tfFormaPagto.setText(venda.getFormaPagto());
            if ("Encerado - Aberto".equals(venda.getSituacao())) {
                btPagar.setEnabled(true);
                tfValorPago.setEditable(true);
                controleItemVenda.listarItens(Integer.parseInt(tfNumeroVen.getText()));
                tableModel.fireTableDataChanged();
            } else {
                if ("Encerado - Pago".equals(venda.getSituacao())) {
                    btPagar.setEnabled(false);
                    controleItemVenda.listarItens(Integer.parseInt(tfNumeroVen.getText()));
                    tableModel.fireTableDataChanged();
                }
            }
        }
    }

    private void novoItem() throws Exception {
        if ("".equals(tfProduto.getText())) {
            lbNomeProdObrig.setText("*");
        } else {
            lbNomeProdObrig.setText("");
        }
        if ("".equals(tfUN.getText())) {
            lbUNProdObrig.setText("*");
        } else {
            lbUNProdObrig.setText("");
        }
        if (!"".equals(tfProduto.getText()) && !"".equals(tfUN.getText())) {
            ProdutoControl controlProd = new ProdutoControl(con);
            Produto produto = controlProd.selectProduto(tfProduto.getText());
            if (Integer.parseInt(tfUN.getText()) > produto.getQuantidade()) {
                tfUN.grabFocus();
                throw new Exception("Quantidade não disponível");
            }
            produto.setQuantidade(produto.getQuantidade() - Integer.parseInt(tfUN.getText()));
            controlProd.insertProduto(produto);
            try {
                tfSubTotal.setText(NumberFormat.getCurrencyInstance().format((Double.parseDouble(tfValorTotal.getText().replace("R$ ", "").replace(".", "").replace(",", "."))) + controleItemVenda.getTotalItensVenda()));
            } catch (Exception ex) {
                tfSubTotal.setText("R$ 0,00");
                tfTotal.setText("R$ 0,00");
                tfParcelasMes.setText("R$ 0,00");
                throw new Exception("Caracter inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
            }
            ItensVenda itemVenda = new ItensVenda();
            itemVenda.setCodigo(controleItemVenda.getProxCodItemVenda());
            itemVenda.setCodVenda(Integer.parseInt(tfNumeroVen.getText()));
            itemVenda.setUnidade(Integer.parseInt(tfUN.getText()));
            itemVenda.setNomeProd(tfProduto.getText());
            itemVenda.setDescricao(tfDescricao.getText());
            itemVenda.setValorUnit(Double.parseDouble(tfValorUnit.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
            itemVenda.setValorTotal(Double.parseDouble(tfValorTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
            descontos();
            juros();
            controleNovaVenda.updateVenda(novaVenda());
            if (controleItemVenda.insertItensVenda(itemVenda)) {
                tableModel.fireTableDataChanged();
                btEncerar.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso", "Venda", JOptionPane.INFORMATION_MESSAGE);
                limparTelaProduto();
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private void descontos() throws Exception {
        if (!"".equals(tfSubTotal.getText()) && !"".equals(tfDescontos.getText())) {
            try {
                double sub = Double.parseDouble(tfSubTotal.getText().replace("R$ ", "").replace(".", "").replace(",", "."));
                sub -= sub * (Double.parseDouble(tfDescontos.getText()) / 100);
                tfTotal.setText(NumberFormat.getCurrencyInstance().format(sub));
            } catch (NumberFormatException ex) {
                tfTotal.setText("R$ 0,00");
                throw new Exception("Valor inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
            }
        } else {
            tfTotal.setText("R$ 0,00");
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
                throw new Exception("Caracter inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
            }
        } else {
            tfParcelasMes.setText("R$ 0,00");
        }
    }

    private void frete() throws Exception {
        if (!"".equals(tfFrete.getText())) {
            try {
                tfSubTotal.setText(NumberFormat.getCurrencyInstance().format(Double.parseDouble(tfSubTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")) + Double.parseDouble(tfFrete.getText())));
            } catch (NumberFormatException ex) {
                tfFrete.setText("0.0");
                tfFrete.grabFocus();
                throw new Exception("Valor inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
            }
        } else {
            tfFrete.setText("0.0");
        }
    }

    private void encerar() throws Exception {
        if ("R$ 0,00".equals(tfSubTotal.getText())) {
            throw new Exception("cadastre no mínimo um item");
        }
        if ("".equals(tfDescontos.getText().trim())) {
            throw new Exception("Informe os descontos");
        }
        if ("R$ 0,00".equals(tfTotal.getText())) {
            throw new Exception("Informe o total da venda");
        }
        if ("Selecione".equals(cbCondPagto.getSelectedItem())) {
            throw new Exception("Selecione a condição de pagamento");
        }
        if ("Selecione".equals(cbFormaPagto.getSelectedItem())) {
            throw new Exception("Selecione a forma de pagamento");
        }
        if (!"Á Vista".equals(cbCondPagto.getSelectedItem())) {
            if ("".equals(tfJuros.getText().trim())) {
                throw new Exception("Campo júros inválido");
            }
            if ("R$ 0,00".equals(tfParcelasMes.getText())) {
                throw new Exception("Informe o valor da parcelas");
            }
            frete();
            descontos();
            juros();
            tfParcelasRestante.setText((String) cbCondPagto.getSelectedItem().toString().substring(0, 1));
            int condpagto = Integer.parseInt(cbCondPagto.getSelectedItem().toString().substring(0, 1));
            double resultTotal = Double.parseDouble(tfTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")) / condpagto;
            resultTotal += resultTotal * (Double.parseDouble(tfJuros.getText()) / 100);
            resultTotal = resultTotal * condpagto;
            tfValorRestante.setText(NumberFormat.getCurrencyInstance().format(resultTotal));
            btPagar.setEnabled(true);
            tfValorPago.setEditable(true);
            tfSituacao.setText("Encerado - Aberto");
        } else {
            frete();
            descontos();
            juros();
            btPagar.setEnabled(false);
            tfSituacao.setText("Encerado - Pago");
        }
        cbCondPagto.setVisible(false);
        tfCondPagto.setVisible(true);
        tfCondPagto.setText((String) cbCondPagto.getSelectedItem());
        cbFormaPagto.setVisible(false);
        tfFormaPagto.setVisible(true);
        tfFormaPagto.setText((String) cbFormaPagto.getSelectedItem());
        tfDescontos.setEditable(false);
        tfJuros.setEditable(false);
        if (controleNovaVenda.updateVenda(novaVenda())) {
            btOk.setEnabled(false);
            btPesquisaProd.setEnabled(false);
            btGerar.setEnabled(true);
            btEncerar.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Venda encerada com sucesso", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void pagar() throws Exception {
        try {
            if (Double.parseDouble(tfValorPago.getText()) == Double.parseDouble(tfParcelasMes.getText().replace("R$ ", "").replace(".", "").replace(",", "."))) {
                tfValorRestante.setText(NumberFormat.getCurrencyInstance().format(Double.parseDouble(tfValorRestante.getText().replace("R$ ", "").replace(".", "").replace(",", ".")) - Double.parseDouble(tfValorPago.getText())));
                tfParcelasRestante.setText(Integer.toString(Integer.parseInt(tfParcelasRestante.getText()) - 1));
                tfValorPago.setText("");
                if ("0".equals(tfParcelasRestante.getText())) {
                    tfValorRestante.setText("R$ 0,00");
                }
                if ("R$ 0,00".equals(tfValorRestante.getText()) && "0".equals(tfParcelasRestante.getText())) {
                    tfSituacao.setText("Encerado - Pago");
                    btPagar.setEnabled(false);
                    tfValorPago.setEditable(false);
                }
                if (controleNovaVenda.pagarVenda(Integer.parseInt(tfNumeroVen.getText()), Double.parseDouble(tfValorRestante.getText().replace("R$ ", "").replace(".", "").replace(",", ".")), Integer.parseInt(tfParcelasRestante.getText()), tfSituacao.getText())) {
                    JOptionPane.showMessageDialog(null, "Parcela paga com sucesso", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Informe um valor correspondente com as parcelas", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            tfValorPago.grabFocus();
            throw new Exception("Valor inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btPesquisaCliente) {
            try {
                recuperarCliente();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btPesquisaFor) {
            try {
                recuperarFornecedor();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btPesquisaProd) {
            try {
                recuperarProduto();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConfirmar) {
            try {
                gravarNovaVenda();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btPesquisa) {
            try {
                recuperarVenda();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOk) {
            try {
                novoItem();
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
        if (evento.getSource() == btGerar) {
        }
        if (evento.getSource() == cbCondPagto) {
            if ("Á Vista".equals(cbCondPagto.getSelectedItem())) {
                tfJuros.setText("");
                tfParcelasMes.setText("R$ 0,00");
                tfJuros.setEditable(false);
            } else {
                tfJuros.setEditable(true);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfNumeroVen) {
            tfNumeroVen.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDataEmissao) {
            tfDataEmissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSituacao) {
            tfSituacao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfVendedor) {
            tfVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmpresa) {
            tfEmpresa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCNPJFornecedor) {
            tfCNPJFornecedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeFornecedor) {
            tfNomeFornecedor.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfJuros) {
            tfJuros.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfParcelasMes) {
            tfParcelasMes.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCpfCnpjClie) {
            tfCpfCnpjClie.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfNumeroClie) {
            tfNumeroClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCidadeClie) {
            tfCidadeClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUFClie) {
            tfUFClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfFoneClie) {
            tfFoneClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCelularClie) {
            tfCelularClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEMailClie) {
            tfEMailClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfFrete) {
            tfFrete.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfProduto) {
            tfProduto.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUN) {
            tfUN.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorUnit) {
            tfValorUnit.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorTotal) {
            tfValorTotal.setBackground(Color.YELLOW);
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
        if (evento.getSource() == ftfFoneEmpresa) {
            ftfFoneEmpresa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCNPJ) {
            ftfCNPJ.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfIE) {
            ftfIE.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataEntrega) {
            ftfDataEntrega.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbTipo) {
            cbTipo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbCondPagto) {
            cbCondPagto.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbFormaPagto) {
            cbFormaPagto.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == taDescricao) {
            taDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTipo) {
            tfTipo.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfNumeroVen.setBackground(Color.WHITE);
        tfDataEmissao.setBackground(Color.WHITE);
        tfSituacao.setBackground(Color.WHITE);
        if (evento.getSource() == tfVendedor) {
            tfVendedor.setBackground(Color.WHITE);
            try {
                verificaFuncionario(tfVendedor);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfEmpresa.setBackground(Color.WHITE);
        tfCNPJFornecedor.setBackground(Color.WHITE);
        tfNomeFornecedor.setBackground(Color.WHITE);
        tfSubTotal.setBackground(Color.WHITE);
        if (evento.getSource() == tfDescontos) {
            tfDescontos.setBackground(Color.WHITE);
            try {
                descontos();
                juros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfTotal.setBackground(Color.WHITE);
        if (evento.getSource() == tfJuros) {
            tfJuros.setBackground(Color.WHITE);
            try {
                juros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfParcelasMes.setBackground(Color.WHITE);
        tfCpfCnpjClie.setBackground(Color.WHITE);
        tfNomeClie.setBackground(Color.WHITE);
        tfEnderecoClie.setBackground(Color.WHITE);
        tfBairroClie.setBackground(Color.WHITE);
        tfNumeroClie.setBackground(Color.WHITE);
        tfCidadeClie.setBackground(Color.WHITE);
        tfUFClie.setBackground(Color.WHITE);
        tfFoneClie.setBackground(Color.WHITE);
        tfCelularClie.setBackground(Color.WHITE);
        tfEMailClie.setBackground(Color.WHITE);
        tfFrete.setBackground(Color.WHITE);
        tfProduto.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        if (evento.getSource() == tfUN) {
            tfUN.setBackground(Color.WHITE);
            try {
                calculaValorTotalItem();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfValorUnit.setBackground(Color.WHITE);
        tfValorTotal.setBackground(Color.WHITE);
        tfValorPago.setBackground(Color.WHITE);
        tfValorRestante.setBackground(Color.WHITE);
        tfParcelasRestante.setBackground(Color.WHITE);
        ftfFoneEmpresa.setBackground(Color.WHITE);
        ftfCNPJ.setBackground(Color.WHITE);
        ftfIE.setBackground(Color.WHITE);
        ftfDataEntrega.setBackground(Color.WHITE);
        cbTipo.setBackground(Color.WHITE);
        if (evento.getSource() == cbCondPagto) {
            cbCondPagto.setBackground(Color.WHITE);
            try {
                juros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        cbFormaPagto.setBackground(Color.WHITE);
        taDescricao.setBackground(Color.WHITE);
        tfTipo.setBackground(Color.WHITE);
    }
}

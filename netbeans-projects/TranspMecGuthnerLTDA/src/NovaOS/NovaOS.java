package NovaOS;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Container;
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Clientes.ClienteControl;
import Clientes.PesquisaClientes;
import DAOFactory.DAOFactory;
import FormatacaoCampos.CamposInt;
import FormatacaoCampos.CriarObjGrafico;
import FormatacaoCampos.LetrasMaiusculas;
import FormatacaoCampos.VerificaCampos;
import Funcionario.FuncionarioControl;
import Modelo.Cliente;
import Modelo.ItensOS;
import Modelo.OrdemServico;
import Modelo.Veiculo;
import Veiculos.PesquisaVeiculo;
import Veiculos.VeiculoControl;

public class NovaOS extends JDialog implements ActionListener, FocusListener {

    private JTextField tfCodigo, tfDataGerar, tfUltAlteracao, tfVendedor,
            tfSituacao, tfPlacaVei, tfNomeVei, tfMarcaVei, tfAnoVei,
            tfModeloVei, tfCorVei, tfPotenciaVei, tfVavulasVei, tfCavalosVei,
            tfCilindrosVei, tfCombustivelVei, tfTipoVei, tfChassiVei,
            tfRenavamVei, tfSubTotal, tfTotal, tfDescontos, tfJuros,
            tfParcelasMes, tfCpfCnpjClie, tfNomeClie, tfEnderecoClie,
            tfBairroClie, tfNumeroClie, tfCidadeClie, tfUFClie, tfFoneClie,
            tfCelularClie, tfEMailClie, tfNomeMecanico, tfTempo, tfValorHora,
            tfUN, tfItem, tfDescricao, tfValorUnit, tfValorTotal;
    private JFormattedTextField ftfDataRea, ftfHoraInicial, ftfHoraFinal;
    private JComboBox cbCondPagto, cbFormaPagto;
    private JTextArea taDescricao;
    private JButton btPesquisa, btPesquisaPlaca, btPesquisaCliente, btAssumir,
            btGerar, btFinalizar, btOk, btConfirmar, btCancelar, btCancelarOS;
    private JLabel lbNomeVendObrig, lbPlacaVeiObrig, lbCPFCNPJClieObrig,
            lbUNItemObrig, lbItemObrig, lbValorUnitObrig, lbNomeMecObrig,
            lbHoraIniObrig, lbHoraFimObrig, lbValorHoraObrig, lbTempoObrig,
            lbDescricaoObrig;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private DAOFactory daoFactory;
    private Connection con;
    private NovaOSControl controleNovaOS;
    private ItemOSControl controleItemOS;
    private TableModelItens tableModel;
    private RenderizadoraItens rendener;

    public NovaOS(DAOFactory daoFactory, Connection con) {
        this.daoFactory = daoFactory;
        this.con = con;
        controleNovaOS = new NovaOSControl(daoFactory, con);
        controleItemOS = new ItemOSControl(daoFactory, con);
        tableModel = new TableModelItens(controleItemOS);
        rendener = new RenderizadoraItens();
        try {
            initComponents();
        } catch (ParseException ex) {
        }
    }

    private void initComponents() throws ParseException {
        List<String> itensCombo = new ArrayList<String>();
        setTitle("Nova Ordem de Serviço");
        Container tela = getContentPane();
        tela.setLayout(null);
        tela.setBackground(new Color(248, 248, 248));

        JPanel panelOS = CriarObjGrafico.criarJPanelTitulo("Dados Ordem Serviço", 10, 10, 410, 370);
        tela.add(panelOS);

        JPanel panelCliente = CriarObjGrafico.criarJPanelTitulo("Dados Clientes", 425, 10, 410, 210);
        tela.add(panelCliente);

        JPanel panelSituacao = CriarObjGrafico.criarJPanelTitulo("Situação OS", 425, 217, 410, 163);
        tela.add(panelSituacao);

        JPanel panelItens = CriarObjGrafico.criarJPanelTitulo("", 12, 383, 823, 50);
        tela.add(panelItens);

        JPanel panelConfirmacao = CriarObjGrafico.criarJPanelTitulo("", 12, 607, 815, 95);
        tela.add(panelConfirmacao);

        panelOS.add(CriarObjGrafico.criarJLabel("Número", 20, 30, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(20, 48, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controleNovaOS.getProxCodOS()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        tfCodigo.addFocusListener(this);
        panelOS.add(tfCodigo);

        btPesquisa = CriarObjGrafico.criarJButton("...", 106, 44, 31, 24);
        btPesquisa.addActionListener(this);
        panelOS.add(btPesquisa);

        panelOS.add(CriarObjGrafico.criarJLabel("Gerado em", 143, 30, 90, 14));
        tfDataGerar = CriarObjGrafico.criarJTextField(143, 48, 120, 20);
        tfDataGerar.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataGerar.setEditable(false);
        tfDataGerar.setBackground(Color.WHITE);
        tfDataGerar.addFocusListener(this);
        panelOS.add(tfDataGerar);

        panelOS.add(CriarObjGrafico.criarJLabel("Última Alteração", 270, 30, 100, 14));
        tfUltAlteracao = CriarObjGrafico.criarJTextField(270, 48, 120, 20);
        tfUltAlteracao.setEditable(false);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfUltAlteracao.addFocusListener(this);
        panelOS.add(tfUltAlteracao);

        panelOS.add(CriarObjGrafico.criarJLabel("Vendedor", 20, 70, 120, 14));
        lbNomeVendObrig = CriarObjGrafico.criarJLabel("", 80, 73, 10, 14);
        lbNomeVendObrig.setForeground(Color.RED);
        panelOS.add(lbNomeVendObrig);
        tfVendedor = CriarObjGrafico.criarJTextField(20, 88, 230, 20);
        tfVendedor.setBackground(Color.WHITE);
        tfVendedor.addFocusListener(this);
        panelOS.add(tfVendedor);

        panelOS.add(CriarObjGrafico.criarJLabel("Situação", 260, 70, 120, 14));
        tfSituacao = CriarObjGrafico.criarJTextField(260, 88, 130, 20);
        tfSituacao.setEditable(false);
        tfSituacao.setBackground(Color.WHITE);
        tfSituacao.addFocusListener(this);
        panelOS.add(tfSituacao);

        panelOS.add(CriarObjGrafico.criarJLabel("Placa", 20, 110, 120, 14));
        lbPlacaVeiObrig = CriarObjGrafico.criarJLabel("", 55, 113, 10, 14);
        lbPlacaVeiObrig.setForeground(Color.RED);
        panelOS.add(lbPlacaVeiObrig);
        tfPlacaVei = CriarObjGrafico.criarJTextField(20, 128, 80, 20);
        tfPlacaVei.setEditable(false);
        tfPlacaVei.setBackground(Color.WHITE);
        tfPlacaVei.addFocusListener(this);
        panelOS.add(tfPlacaVei);

        btPesquisaPlaca = CriarObjGrafico.criarJButton("...", 106, 124, 31, 24);
        btPesquisaPlaca.addActionListener(this);
        panelOS.add(btPesquisaPlaca);

        panelOS.add(CriarObjGrafico.criarJLabel("Nome", 143, 110, 120, 14));
        tfNomeVei = CriarObjGrafico.criarJTextField(143, 128, 130, 20);
        tfNomeVei.setEditable(false);
        tfNomeVei.setBackground(Color.WHITE);
        tfNomeVei.addFocusListener(this);
        panelOS.add(tfNomeVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Marca", 280, 110, 120, 14));
        tfMarcaVei = CriarObjGrafico.criarJTextField(280, 128, 110, 20);
        tfMarcaVei.setEditable(false);
        tfMarcaVei.setBackground(Color.WHITE);
        tfMarcaVei.addFocusListener(this);
        panelOS.add(tfMarcaVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Ano", 20, 150, 120, 14));
        tfAnoVei = CriarObjGrafico.criarJTextField(20, 168, 80, 20);
        tfAnoVei.setEditable(false);
        tfAnoVei.setBackground(Color.WHITE);
        tfAnoVei.addFocusListener(this);
        panelOS.add(tfAnoVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Modelo", 110, 150, 120, 14));
        tfModeloVei = CriarObjGrafico.criarJTextField(110, 168, 80, 20);
        tfModeloVei.addFocusListener(this);
        tfModeloVei.setEditable(false);
        tfModeloVei.setBackground(Color.WHITE);
        panelOS.add(tfModeloVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Cor", 200, 150, 120, 14));
        tfCorVei = CriarObjGrafico.criarJTextField(200, 168, 100, 20);
        tfCorVei.setEditable(false);
        tfCorVei.setBackground(Color.WHITE);
        tfCorVei.addFocusListener(this);
        panelOS.add(tfCorVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Potência", 310, 150, 120, 14));
        tfPotenciaVei = CriarObjGrafico.criarJTextField(310, 168, 80, 20);
        tfPotenciaVei.setEditable(false);
        tfPotenciaVei.setBackground(Color.WHITE);
        tfPotenciaVei.addFocusListener(this);
        panelOS.add(tfPotenciaVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Vávulas", 20, 190, 120, 14));
        tfVavulasVei = CriarObjGrafico.criarJTextField(20, 208, 80, 20);
        tfVavulasVei.setEditable(false);
        tfVavulasVei.setBackground(Color.WHITE);
        tfVavulasVei.addFocusListener(this);
        panelOS.add(tfVavulasVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Cavalos", 110, 190, 120, 14));
        tfCavalosVei = CriarObjGrafico.criarJTextField(110, 208, 80, 20);
        tfCavalosVei.setEditable(false);
        tfCavalosVei.setBackground(Color.WHITE);
        tfCavalosVei.addFocusListener(this);
        panelOS.add(tfCavalosVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Cilíndros", 200, 190, 120, 14));
        tfCilindrosVei = CriarObjGrafico.criarJTextField(200, 208, 80, 20);
        tfCilindrosVei.setEditable(false);
        tfCilindrosVei.setBackground(Color.WHITE);
        tfCilindrosVei.addFocusListener(this);
        panelOS.add(tfCilindrosVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Combustível", 290, 190, 120, 14));
        tfCombustivelVei = CriarObjGrafico.criarJTextField(290, 208, 100, 20);
        tfCombustivelVei.setEditable(false);
        tfCombustivelVei.setBackground(Color.WHITE);
        tfCombustivelVei.addFocusListener(this);
        panelOS.add(tfCombustivelVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Tipo", 20, 230, 120, 14));
        tfTipoVei = CriarObjGrafico.criarJTextField(20, 248, 130, 20);
        tfTipoVei.setEditable(false);
        tfTipoVei.setBackground(Color.WHITE);
        tfTipoVei.addFocusListener(this);
        panelOS.add(tfTipoVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Chassi", 160, 230, 120, 14));
        tfChassiVei = CriarObjGrafico.criarJTextField(160, 248, 110, 20);
        tfChassiVei.setEditable(false);
        tfChassiVei.setBackground(Color.WHITE);
        tfChassiVei.addFocusListener(this);
        panelOS.add(tfChassiVei);

        panelOS.add(CriarObjGrafico.criarJLabel("RENAVAM", 280, 230, 120, 14));
        tfRenavamVei = CriarObjGrafico.criarJTextField(280, 248, 110, 20);
        tfRenavamVei.setEditable(false);
        tfRenavamVei.setBackground(Color.WHITE);
        tfRenavamVei.addFocusListener(this);
        panelOS.add(tfRenavamVei);

        panelOS.add(CriarObjGrafico.criarJLabel("Sub-Total", 20, 270, 120, 14));
        tfSubTotal = CriarObjGrafico.criarJTextField(20, 288, 115, 20);
        tfSubTotal.setText("R$ 0,00");
        tfSubTotal.setEditable(false);
        tfSubTotal.setBackground(Color.WHITE);
        tfSubTotal.addFocusListener(this);
        panelOS.add(tfSubTotal);

        panelOS.add(CriarObjGrafico.criarJLabel("Descontos", 145, 270, 120, 14));
        tfDescontos = CriarObjGrafico.criarJTextField(145, 288, 105, 20);
        tfDescontos.setBackground(Color.WHITE);
        tfDescontos.addFocusListener(this);
        panelOS.add(tfDescontos);
        panelOS.add(CriarObjGrafico.criarJLabel("%", 253, 291, 10, 14));

        panelOS.add(CriarObjGrafico.criarJLabel("Total", 270, 270, 120, 14));
        tfTotal = CriarObjGrafico.criarJTextField(270, 288, 120, 20);
        tfTotal.setText("R$ 0,00");
        tfTotal.setEditable(false);
        tfTotal.setBackground(Color.WHITE);
        tfTotal.addFocusListener(this);
        panelOS.add(tfTotal);

        panelOS.add(CriarObjGrafico.criarJLabel("Cond. Pagto", 20, 310, 90, 14));
        itensCombo.add("Á Vista");
        itensCombo.add("1X");
        itensCombo.add("2X");
        itensCombo.add("3X");
        itensCombo.add("4X");
        itensCombo.add("5X");
        itensCombo.add("6X");
        itensCombo.add("7X");
        itensCombo.add("8X");
        cbCondPagto = CriarObjGrafico.criarJComboBox(itensCombo, 20, 328, 90, 20);
        cbCondPagto.addFocusListener(this);
        cbCondPagto.addActionListener(this);
        panelOS.add(cbCondPagto);

        panelOS.add(CriarObjGrafico.criarJLabel("Forma. Pagto", 120, 310, 90, 14));
        itensCombo.clear();
        itensCombo.add("Dinheiro");
        itensCombo.add("Cheque");
        itensCombo.add("Cartão");
        cbFormaPagto = CriarObjGrafico.criarJComboBox(itensCombo, 120, 328, 90, 20);
        cbFormaPagto.addFocusListener(this);
        panelOS.add(cbFormaPagto);

        panelOS.add(CriarObjGrafico.criarJLabel("Juros", 220, 310, 120, 14));
        tfJuros = CriarObjGrafico.criarJTextField(220, 328, 72, 20);
        tfJuros.setBackground(Color.WHITE);
        tfJuros.addFocusListener(this);
        panelOS.add(tfJuros);
        panelOS.add(CriarObjGrafico.criarJLabel("%", 296, 331, 10, 14));

        panelOS.add(CriarObjGrafico.criarJLabel("Parcelas Mês", 310, 310, 120, 14));
        tfParcelasMes = CriarObjGrafico.criarJTextField(310, 328, 80, 20);
        tfParcelasMes.setText("R$ 0,00");
        tfParcelasMes.setEditable(false);
        tfParcelasMes.setBackground(Color.WHITE);
        tfParcelasMes.addFocusListener(this);
        panelOS.add(tfParcelasMes);

        panelCliente.add(CriarObjGrafico.criarJLabel("CPF / CNPJ", 20, 30, 120, 14));
        lbCPFCNPJClieObrig = CriarObjGrafico.criarJLabel("", 85, 33, 10, 14);
        lbCPFCNPJClieObrig.setForeground(Color.RED);
        panelCliente.add(lbCPFCNPJClieObrig);
        tfCpfCnpjClie = CriarObjGrafico.criarJTextField(20, 48, 130, 20);
        tfCpfCnpjClie.setEditable(false);
        tfCpfCnpjClie.setBackground(Color.WHITE);
        tfCpfCnpjClie.addFocusListener(this);
        panelCliente.add(tfCpfCnpjClie);

        btPesquisaCliente = CriarObjGrafico.criarJButton("...", 156, 44, 31, 24);
        btPesquisaCliente.addActionListener(this);
        panelCliente.add(btPesquisaCliente);

        panelCliente.add(CriarObjGrafico.criarJLabel("Nome", 193, 30, 120, 14));
        tfNomeClie = CriarObjGrafico.criarJTextField(193, 48, 200, 20);
        tfNomeClie.setEditable(false);
        tfNomeClie.setBackground(Color.WHITE);
        tfNomeClie.addFocusListener(this);
        panelCliente.add(tfNomeClie);

        panelCliente.add(CriarObjGrafico.criarJLabel("Endereco", 20, 70, 120, 14));
        tfEnderecoClie = CriarObjGrafico.criarJTextField(20, 88, 140, 20);
        tfEnderecoClie.setEditable(false);
        tfEnderecoClie.setBackground(Color.WHITE);
        tfEnderecoClie.addFocusListener(this);
        panelCliente.add(tfEnderecoClie);

        panelCliente.add(CriarObjGrafico.criarJLabel("Bairro", 170, 70, 120, 14));
        tfBairroClie = CriarObjGrafico.criarJTextField(170, 88, 130, 20);
        tfBairroClie.setEditable(false);
        tfBairroClie.setBackground(Color.WHITE);
        tfBairroClie.addFocusListener(this);
        panelCliente.add(tfBairroClie);

        panelCliente.add(CriarObjGrafico.criarJLabel("Número", 310, 70, 120, 14));
        tfNumeroClie = CriarObjGrafico.criarJTextField(310, 88, 80, 20);
        tfNumeroClie.setEditable(false);
        tfNumeroClie.setBackground(Color.WHITE);
        tfNumeroClie.addFocusListener(this);
        panelCliente.add(tfNumeroClie);

        panelCliente.add(CriarObjGrafico.criarJLabel("Cidade", 20, 110, 120, 14));
        tfCidadeClie = CriarObjGrafico.criarJTextField(20, 128, 130, 20);
        tfCidadeClie.setEditable(false);
        tfCidadeClie.setBackground(Color.WHITE);
        tfCidadeClie.addFocusListener(this);
        panelCliente.add(tfCidadeClie);

        panelCliente.add(CriarObjGrafico.criarJLabel("UF", 160, 110, 120, 14));
        tfUFClie = CriarObjGrafico.criarJTextField(160, 128, 110, 20);
        tfUFClie.setEditable(false);
        tfUFClie.setBackground(Color.WHITE);
        tfUFClie.addFocusListener(this);
        panelCliente.add(tfUFClie);

        panelCliente.add(CriarObjGrafico.criarJLabel("Fone", 280, 110, 120, 14));
        tfFoneClie = CriarObjGrafico.criarJTextField(280, 128, 110, 20);
        tfFoneClie.setEditable(false);
        tfFoneClie.setBackground(Color.WHITE);
        tfFoneClie.addFocusListener(this);
        panelCliente.add(tfFoneClie);

        panelCliente.add(CriarObjGrafico.criarJLabel("Celular", 20, 150, 120, 14));
        tfCelularClie = CriarObjGrafico.criarJTextField(20, 168, 110, 20);
        tfCelularClie.setEditable(false);
        tfCelularClie.setBackground(Color.WHITE);
        tfCelularClie.addFocusListener(this);
        panelCliente.add(tfCelularClie);

        panelCliente.add(CriarObjGrafico.criarJLabel("E-Mail", 140, 150, 120, 14));
        tfEMailClie = CriarObjGrafico.criarJTextField(140, 168, 250, 20);
        tfEMailClie.setEditable(false);
        tfEMailClie.setBackground(Color.WHITE);
        tfEMailClie.addFocusListener(this);
        panelCliente.add(tfEMailClie);

        panelSituacao.add(CriarObjGrafico.criarJLabel("Operação Realizada Por", 20, 30, 150, 14));
        lbNomeMecObrig = CriarObjGrafico.criarJLabel("", 160, 33, 10, 14);
        lbNomeMecObrig.setForeground(Color.RED);
        panelSituacao.add(lbNomeMecObrig);
        tfNomeMecanico = CriarObjGrafico.criarJTextField(20, 48, 170, 20);
        tfNomeMecanico.setEditable(false);
        tfNomeMecanico.setBackground(Color.WHITE);
        tfNomeMecanico.addFocusListener(this);
        panelSituacao.add(tfNomeMecanico);

        panelSituacao.add(CriarObjGrafico.criarJLabel("Data", 200, 30, 100, 14));
        ftfDataRea = CriarObjGrafico.criarJFormattedTextField("##/##/####", 200, 48, 100, 20);
        ftfDataRea.setEditable(false);
        ftfDataRea.setBackground(Color.WHITE);
        ftfDataRea.addFocusListener(this);
        panelSituacao.add(ftfDataRea);

        panelSituacao.add(CriarObjGrafico.criarJLabel("Hora Inicial", 310, 30, 100, 14));
        lbHoraIniObrig = CriarObjGrafico.criarJLabel("", 378, 33, 10, 14);
        lbHoraIniObrig.setForeground(Color.RED);
        panelSituacao.add(lbHoraIniObrig);
        ftfHoraInicial = CriarObjGrafico.criarJFormattedTextField("##:##", 310, 48, 80, 20);
        ftfHoraInicial.setEditable(false);
        ftfHoraInicial.setBackground(Color.WHITE);
        ftfHoraInicial.addFocusListener(this);
        panelSituacao.add(ftfHoraInicial);

        panelSituacao.add(CriarObjGrafico.criarJLabel("Hora Final", 20, 70, 100, 14));
        lbHoraFimObrig = CriarObjGrafico.criarJLabel("", 80, 73, 10, 14);
        lbHoraFimObrig.setForeground(Color.RED);
        panelSituacao.add(lbHoraFimObrig);
        ftfHoraFinal = CriarObjGrafico.criarJFormattedTextField("##:##", 20, 88, 80, 20);
        ftfHoraFinal.setEditable(false);
        ftfHoraFinal.setBackground(Color.WHITE);
        ftfHoraFinal.addFocusListener(this);
        panelSituacao.add(ftfHoraFinal);

        panelSituacao.add(CriarObjGrafico.criarJLabel("Tempo", 110, 70, 120, 14));
        lbTempoObrig = CriarObjGrafico.criarJLabel("", 154, 73, 10, 14);
        lbTempoObrig.setForeground(Color.RED);
        panelSituacao.add(lbTempoObrig);
        tfTempo = CriarObjGrafico.criarJTextField(110, 88, 100, 20);
        tfTempo.setEditable(false);
        tfTempo.setBackground(Color.WHITE);
        tfTempo.addFocusListener(this);
        panelSituacao.add(tfTempo);

        panelSituacao.add(CriarObjGrafico.criarJLabel("Valor Por Hora", 220, 70, 120, 14));
        lbValorHoraObrig = CriarObjGrafico.criarJLabel("", 308, 73, 10, 14);
        lbValorHoraObrig.setForeground(Color.RED);
        panelSituacao.add(lbValorHoraObrig);
        tfValorHora = CriarObjGrafico.criarJTextField(220, 88, 100, 20);
        tfValorHora.setEditable(false);
        tfValorHora.setBackground(Color.WHITE);
        tfValorHora.addFocusListener(this);
        panelSituacao.add(tfValorHora);

        btAssumir = CriarObjGrafico.criarJButton("Assumir", 10, 120, 82, 26);
        btAssumir.setEnabled(false);
        btAssumir.addActionListener(this);
        panelSituacao.add(btAssumir);

        btGerar = CriarObjGrafico.criarJButton("Gerar", 105, 120, 82, 26);
        btGerar.addActionListener(this);
        btGerar.setEnabled(false);
        panelSituacao.add(btGerar);

        btFinalizar = CriarObjGrafico.criarJButton("Finalizar", 200, 120, 82, 26);
        btFinalizar.addActionListener(this);
        btFinalizar.setEnabled(false);
        panelSituacao.add(btFinalizar);

        btCancelarOS = CriarObjGrafico.criarJButton("Cancelar OS", 295, 120, 105, 26);
        btCancelarOS.addActionListener(this);
        btCancelarOS.setEnabled(false);
        panelSituacao.add(btCancelarOS);

        panelItens.add(CriarObjGrafico.criarJLabel("UN", 20, 15, 20, 14));
        lbUNItemObrig = CriarObjGrafico.criarJLabel("", 37, 18, 10, 14);
        lbUNItemObrig.setForeground(Color.RED);
        panelItens.add(lbUNItemObrig);
        tfUN = CriarObjGrafico.criarJTextField(45, 13, 40, 20);
        tfUN.setDocument(new CamposInt());
        tfUN.addFocusListener(this);
        panelItens.add(tfUN);

        panelItens.add(CriarObjGrafico.criarJLabel("Item", 90, 15, 80, 14));
        lbItemObrig = CriarObjGrafico.criarJLabel("", 115, 18, 10, 14);
        lbItemObrig.setForeground(Color.RED);
        panelItens.add(lbItemObrig);
        tfItem = CriarObjGrafico.criarJTextField(120, 13, 120, 20);
        tfItem.addFocusListener(this);
        panelItens.add(tfItem);

        panelItens.add(CriarObjGrafico.criarJLabel("Descrição", 245, 15, 80, 14));
        tfDescricao = CriarObjGrafico.criarJTextField(310, 13, 170, 20);
        tfDescricao.addFocusListener(this);
        panelItens.add(tfDescricao);

        panelItens.add(CriarObjGrafico.criarJLabel("Valor Unit", 485, 15, 80, 14));
        lbValorUnitObrig = CriarObjGrafico.criarJLabel("", 541, 18, 10, 14);
        lbValorUnitObrig.setForeground(Color.RED);
        panelItens.add(lbValorUnitObrig);
        tfValorUnit = CriarObjGrafico.criarJTextField(547, 13, 65, 20);
        tfValorUnit.addFocusListener(this);
        panelItens.add(tfValorUnit);

        panelItens.add(CriarObjGrafico.criarJLabel("Valor Total", 617, 15, 80, 14));
        tfValorTotal = CriarObjGrafico.criarJTextField(682, 13, 80, 20);
        tfValorTotal.setText("R$ 0,00");
        tfValorTotal.setEditable(false);
        tfValorTotal.setBackground(Color.WHITE);
        tfValorTotal.addFocusListener(this);
        panelItens.add(tfValorTotal);

        btOk = CriarObjGrafico.criarJButton("OK", 765, 10, 51, 26);
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
        scroll.setBounds(12, 438, 823, 165);
        tela.add(scroll);

        panelConfirmacao.add(CriarObjGrafico.criarJLabel("Descrição / Defeito", 10, 4, 110, 14));
        lbDescricaoObrig = CriarObjGrafico.criarJLabel("", 122, 7, 10, 14);
        lbDescricaoObrig.setForeground(Color.RED);
        panelConfirmacao.add(lbDescricaoObrig);
        taDescricao = CriarObjGrafico.criarJTextArea(panelConfirmacao, 10, 20, 690, 68);
        taDescricao.addFocusListener(this);

        btConfirmar = CriarObjGrafico.criarJButton("Confirmar", 710, 15, 91, 26);
        btConfirmar.addActionListener(this);
        panelConfirmacao.add(btConfirmar);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 710, 50, 91, 26);
        btCancelar.addActionListener(this);
        panelConfirmacao.add(btCancelar);

        HashSet conj = new HashSet(panelOS.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panelOS.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        panelCliente.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        panelSituacao.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        panelItens.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        panelConfirmacao.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(855, 740);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controleNovaOS.getProxCodOS()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataGerar.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfVendedor.setText("");
        tfSituacao.setText("");
        tfSubTotal.setText("R$ 0,00");
        tfTotal.setText("R$ 0,00");
        tfDescontos.setText("");
        tfJuros.setText("");
        tfParcelasMes.setText("R$ 0,00");
        tfNomeMecanico.setText("");
        tfTempo.setText("");
        tfValorHora.setText("");
        ftfDataRea.setText("");
        ftfHoraInicial.setText("");
        ftfHoraFinal.setText("");
        cbCondPagto.setSelectedItem("Selecione");
        cbFormaPagto.setSelectedItem("Selecione");
        taDescricao.setText("");
        lbNomeVendObrig.setText("");
        lbPlacaVeiObrig.setText("");
        lbCPFCNPJClieObrig.setText("");
        lbNomeMecObrig.setText("");
        limparTelaVei();
        limparTelaClie();
        limparTelaItem();
        btConfirmar.setEnabled(true);
        btAssumir.setEnabled(false);
        btGerar.setEnabled(false);
        btFinalizar.setEnabled(false);
        btCancelarOS.setEnabled(false);
        btOk.setEnabled(false);
        btPesquisaPlaca.setEnabled(true);
        btPesquisaCliente.setEnabled(true);
        tfNomeMecanico.setEditable(false);
        ftfHoraInicial.setEditable(false);
        ftfHoraFinal.setEditable(false);
        tfValorHora.setEditable(false);
        tfDescontos.setEditable(true);
        tfJuros.setEditable(true);
        tfVendedor.setEditable(true);
        controleItemOS.limparLista();
        tableModel.fireTableDataChanged();
    }

    private void limparTelaItem() {
        tfUN.setText("");
        tfItem.setText("");
        tfDescricao.setText("");
        tfValorUnit.setText("");
        tfValorTotal.setText("R$ 0,00");
        lbUNItemObrig.setText("");
        lbItemObrig.setText("");
        lbValorUnitObrig.setText("");
        lbHoraIniObrig.setText("");
        lbHoraFimObrig.setText("");
        lbValorHoraObrig.setText("");
        lbTempoObrig.setText("");
    }

    private void recuperaVei() throws Exception {
        VeiculoControl controlVei = new VeiculoControl(daoFactory, con);
        if (controlVei.isVeiVazio()) {
            throw new Exception("Não há veículos cadastrados");
        }
        JTextField entrada = new JTextField();
        entrada.setDocument(new LetrasMaiusculas());
        final Object[] mensagem = {"Informe a placa do veículo", entrada};
        final String[] opcoes = {"OK", "Consultar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Veículo", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (result) {
            case 0: // Ok
                recuperaVeiculo(controlVei, entrada.getText());
                break;
            case 1: // Consultar
                PesquisaVeiculo pvl = new PesquisaVeiculo(controlVei);
                pvl.setModal(true);
                pvl.setVisible(true);
                break;
            case 2: // Cancelar
                break;
        }
    }

    private void recuperaVeiculo(VeiculoControl controlVei, String placa) throws Exception {
        Veiculo veiculo = controlVei.getVeiculo(placa);
        if (veiculo == null) {
            JOptionPane.showMessageDialog(null, "Veículo não encontrado", "Veículo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            limparTelaVei();
            tfPlacaVei.setText(veiculo.getPlaca());
            tfNomeVei.setText(veiculo.getNome());
            tfMarcaVei.setText(veiculo.getMarca());
            tfAnoVei.setText(VerificaCampos.recuperaCampoStr(veiculo.getAno()));
            tfModeloVei.setText(VerificaCampos.recuperaCampoStr(veiculo.getModelo()));
            tfCorVei.setText(veiculo.getCor());
            tfPotenciaVei.setText(VerificaCampos.recuperaCampoStr(veiculo.getPotencia()));
            tfVavulasVei.setText(VerificaCampos.recuperaCampoStr(veiculo.getVavulas()));
            tfCavalosVei.setText(VerificaCampos.recuperaCampoStr(veiculo.getCavalos()));
            tfCilindrosVei.setText(VerificaCampos.recuperaCampoStr(veiculo.getCilindros()));
            tfCombustivelVei.setText(veiculo.getCombustivel());
            tfTipoVei.setText(veiculo.getTipo());
            tfChassiVei.setText(veiculo.getChassi());
            tfRenavamVei.setText(veiculo.getNome());
        }
    }

    private void limparTelaVei() {
        tfPlacaVei.setText("");
        tfNomeVei.setText("");
        tfMarcaVei.setText("");
        tfAnoVei.setText("");
        tfModeloVei.setText("");
        tfCorVei.setText("");
        tfPotenciaVei.setText("");
        tfVavulasVei.setText("");
        tfCavalosVei.setText("");
        tfCilindrosVei.setText("");
        tfCombustivelVei.setText("");
        tfTipoVei.setText("");
        tfChassiVei.setText("");
        tfRenavamVei.setText("");
    }

    private void recuperaClie() throws Exception {
        ClienteControl controlClie = new ClienteControl(daoFactory, con);
        if (controlClie.isClieVazio()) {
            throw new Exception("Não há clientes cadastrados");
        }
        JTextField entrada = new JTextField();
        final Object[] mensagem = {"Informe o CPF / CNPJ do cliente", entrada};
        final String[] opcoes = {"OK", "Consultar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Cliente", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (result) {
            case 0: // Ok
                recuperaCliente(controlClie, entrada.getText());
                break;
            case 1: // Consultar
                PesquisaClientes pcl = new PesquisaClientes(controlClie);
                pcl.setModal(true);
                pcl.setVisible(true);
                break;
            case 2: // Cancelar
                break;
        }
    }

    private void recuperaCliente(ClienteControl controlClie, String cpfCnpj) throws Exception {
        Cliente cliente = controlClie.getCliente(cpfCnpj);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
        } else {
            limparTelaClie();
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
    }

    private void limparTelaClie() {
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

    private void verificaFuncionario(JTextField tf) throws Exception {
        if (!"".equals(tf.getText())) {
            FuncionarioControl controlFunc = new FuncionarioControl(daoFactory, con);
            if (controlFunc.verificaFuncCadas(tf.getText()) == false) {
                tf.setText("");
                tf.grabFocus();
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void gravarNovaOS() throws Exception {
        if ("".equals(tfVendedor.getText())) {
            lbNomeVendObrig.setText("*");
        } else {
            lbNomeVendObrig.setText("");
        }
        if ("".equals(tfPlacaVei.getText())) {
            lbPlacaVeiObrig.setText("*");
        } else {
            lbPlacaVeiObrig.setText("");
        }
        if ("".equals(tfCpfCnpjClie.getText())) {
            lbCPFCNPJClieObrig.setText("*");
        } else {
            lbCPFCNPJClieObrig.setText("");
        }
        if ("".equals(taDescricao.getText().trim())) {
            lbDescricaoObrig.setText("*");
        } else {
            lbDescricaoObrig.setText("");
        }
        if (!"".equals(tfVendedor.getText()) && !"".equals(tfPlacaVei.getText()) && !"".equals(tfCpfCnpjClie.getText()) && !"".equals(taDescricao.getText().trim())) {
            tfSituacao.setText("Aberto");
            if (controleNovaOS.setOrdemServico(novaOS())) {
                btConfirmar.setEnabled(false);
                btAssumir.setEnabled(true);
                btGerar.setEnabled(true);
                btPesquisaPlaca.setEnabled(false);
                btPesquisaCliente.setEnabled(false);
                tfNomeMecanico.setEditable(true);
                tfVendedor.setEditable(false);
                tfSituacao.setText("Aberto");
                JOptionPane.showMessageDialog(null, "Ordem de serviço cadastrada com sucesso", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private OrdemServico novaOS() throws Exception {
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setCodigo(Integer.parseInt(tfCodigo.getText()));
        ordemServico.setNomeVendedor(tfVendedor.getText());
        ordemServico.setSituacao(tfSituacao.getText());
        ordemServico.setPlacaVei(tfPlacaVei.getText());
        ordemServico.setCondPagto((String) cbCondPagto.getSelectedItem());
        ordemServico.setFormaPagto((String) cbFormaPagto.getSelectedItem());
        ordemServico.setCpfCnpjClie(tfCpfCnpjClie.getText());
        ordemServico.setNomeOPRealizada(tfNomeMecanico.getText());
        ordemServico.setHoraInicial(ftfHoraInicial.getText());
        ordemServico.setHoraFinal(ftfHoraFinal.getText());
        ordemServico.setTempo(tfTempo.getText());
        ordemServico.setDescricao(taDescricao.getText());
        ordemServico.setDataGeracao(formatDateHora.parse(tfDataGerar.getText().replace(" as ", " ").concat(":00")));
        ordemServico.setUltAlteracao(new Date());
        ordemServico.setData(VerificaCampos.verificaDate(ftfDataRea, "data"));
        ordemServico.setSubTotal(Double.parseDouble(tfSubTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
        ordemServico.setDescontos(VerificaCampos.verificaDouble(tfDescontos, "descontos"));
        ordemServico.setTotal(Double.parseDouble(tfTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
        ordemServico.setJuros(VerificaCampos.verificaDouble(tfJuros, "juros"));
        ordemServico.setParcelasMes(Double.parseDouble(tfParcelasMes.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
        ordemServico.setValorPorHora(VerificaCampos.verificaDouble(tfValorHora, "valor por hora"));
        return ordemServico;
    }

    private void recuperarOS() throws Exception {
        if (controleNovaOS.isOSVazio()) {
            throw new Exception("Não há ordens de serviços cadastradas");
        }
        JTextField entrada = new JTextField();
        entrada.setDocument(new CamposInt());
        final Object[] mensagem = {"Entre com  número da ordem de serviço", entrada};
        final String[] opcoes = {"OK", "Gerar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Ordem de Servico", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (result) {
            case 0: // Ok
                if (!"".equals(entrada.getText())) {
                    recuperaOrdemServico(Integer.parseInt(entrada.getText()));
                    break;
                }
                break;
            case 1: // Gerar
                if (!"".equals(entrada.getText())) {
                    abrirRelatorio(Integer.parseInt(entrada.getText()));
                    break;
                }
                break;
            case 2: // Cancelar
                break;
        }
    }

    private void recuperaOrdemServico(int numero) throws Exception {
        OrdemServico ordemServico = controleNovaOS.getOrdemServico(numero);
        if (ordemServico != null) {
            limparTela();
            tfCodigo.setText(Integer.toString(ordemServico.getCodigo()));
            tfVendedor.setText(ordemServico.getNomeVendedor());
            tfSituacao.setText(ordemServico.getSituacao());
            VeiculoControl controlVei = new VeiculoControl(daoFactory, con);
            recuperaVeiculo(controlVei, ordemServico.getPlacaVei());
            cbCondPagto.setSelectedItem(ordemServico.getCondPagto());
            cbFormaPagto.setSelectedItem(ordemServico.getFormaPagto());
            ClienteControl controlClie = new ClienteControl(daoFactory, con);
            recuperaCliente(controlClie, ordemServico.getCpfCnpjClie());
            tfNomeMecanico.setText(ordemServico.getNomeOPRealizada());
            ftfHoraInicial.setText(ordemServico.getHoraInicial());
            ftfHoraFinal.setText(ordemServico.getHoraFinal());
            tfTempo.setText(ordemServico.getTempo());
            taDescricao.setText(ordemServico.getDescricao());
            tfDataGerar.setText(formatDate.format(ordemServico.getDataGeracao()) + " as " + formatHora.format(ordemServico.getDataGeracao()));
            tfUltAlteracao.setText(formatDate.format(ordemServico.getUltAlteracao()) + " as " + formatHora.format(ordemServico.getUltAlteracao()));
            ftfDataRea.setText(VerificaCampos.recuperaCampoDate(ordemServico.getData()));
            tfSubTotal.setText(NumberFormat.getCurrencyInstance().format(ordemServico.getSubTotal()));
            tfDescontos.setText(Double.toString(ordemServico.getDescontos()));
            tfTotal.setText(NumberFormat.getCurrencyInstance().format(ordemServico.getTotal()));
            tfJuros.setText(Double.toString(ordemServico.getJuros()));
            tfParcelasMes.setText(NumberFormat.getCurrencyInstance().format(ordemServico.getParcelasMes()));
            tfValorHora.setText(VerificaCampos.recuperaCampoStr(ordemServico.getValorPorHora()));
            tfVendedor.setEditable(false);
            if ("Á Vista".equals(cbCondPagto.getSelectedItem())) {
                tfJuros.setText("");
                tfParcelasMes.setText("R$ 0,00");
                tfJuros.setEditable(false);
            }
            if ("Aberto".equals(ordemServico.getSituacao())) {
                btConfirmar.setEnabled(false);
                btAssumir.setEnabled(true);
                btPesquisaPlaca.setEnabled(false);
                btPesquisaCliente.setEnabled(false);
                btCancelarOS.setEnabled(true);
                tfNomeMecanico.setEditable(true);
                btGerar.setEnabled(true);
            } else {
                if ("Em Atendimento".equals(ordemServico.getSituacao())) {
                    btConfirmar.setEnabled(false);
                    btPesquisaPlaca.setEnabled(false);
                    btPesquisaCliente.setEnabled(false);
                    btOk.setEnabled(true);
                    btCancelarOS.setEnabled(true);
                    btFinalizar.setEnabled(true);
                    ftfHoraInicial.setEditable(true);
                    ftfHoraFinal.setEditable(true);
                    tfValorHora.setEditable(true);
                    controleItemOS.listaItem(Integer.parseInt(tfCodigo.getText()));
                    tableModel.fireTableDataChanged();
                } else {
                    if ("Finalizado".equals(ordemServico.getSituacao())) {
                        btGerar.setEnabled(true);
                        btConfirmar.setEnabled(false);
                        btPesquisaPlaca.setEnabled(false);
                        btPesquisaCliente.setEnabled(false);
                        tfDescontos.setEditable(false);
                        tfJuros.setEditable(false);
                        controleItemOS.listaItem(Integer.parseInt(tfCodigo.getText()));
                        tableModel.fireTableDataChanged();
                    } else {
                        if ("Cancelado".equals(ordemServico.getSituacao())) {
                            btGerar.setEnabled(true);
                            btConfirmar.setEnabled(false);
                            btPesquisaPlaca.setEnabled(false);
                            btPesquisaCliente.setEnabled(false);
                            tfDescontos.setEditable(false);
                            tfJuros.setEditable(false);
                            controleItemOS.listaItem(Integer.parseInt(tfCodigo.getText()));
                            tableModel.fireTableDataChanged();
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Ordem de serviço recuperada com sucesso", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Ordem de serviço não encontrada", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
    }

    private void abrirRelatorio(int numero) throws Exception {
        OrdemServico ordemServico = controleNovaOS.getOrdemServico(numero);
        if (ordemServico != null) {
            if (ordemServico.getSituacao().equals("Aberto") || ordemServico.getSituacao().equals("Finalizado") || ordemServico.getSituacao().equals("Cancelado")) {
                ///
                RelatorioOS relatorio = new RelatorioOS(ordemServico, null);
                relatorio.setModal(true);
                relatorio.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "A ordem de serviço está em atendimento", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ordem de serviço nçao encontrada", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateNovaOS() throws Exception {
        if ("".equals(tfNomeMecanico.getText())) {
            lbNomeMecObrig.setText("*");
        } else {
            lbNomeMecObrig.setText("");
        }
        if (!"".equals(tfNomeMecanico.getText())) {
            tfSituacao.setText("Em Atendimento");
            if (controleNovaOS.updateOrdemServico(novaOS())) {
                btAssumir.setEnabled(false);
                btOk.setEnabled(true);
                btFinalizar.setEnabled(true);
                btCancelarOS.setEnabled(true);
                tfNomeMecanico.setEditable(false);
                ftfHoraInicial.setEditable(true);
                ftfHoraFinal.setEditable(true);
                tfValorHora.setEditable(true);
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private void calculaValorTotalItem() throws Exception {
        if (!"".equals(tfUN.getText()) && !"".equals(tfValorUnit.getText())) {
            try {
                tfValorTotal.setText(NumberFormat.getCurrencyInstance().format(Double.parseDouble(tfUN.getText()) * Double.parseDouble(tfValorUnit.getText())));
            } catch (NumberFormatException ex) {
                tfValorTotal.setText("R$ 0,00");
                throw new Exception("Caracter inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
            }
        } else {
            tfValorTotal.setText("R$ 0,00");
        }
    }

    private void calculaTempo() {
        if (!"  :  ".equals(ftfHoraInicial.getText()) && !"  :  ".equals(ftfHoraFinal.getText())) {
            if (Integer.parseInt(ftfHoraInicial.getText().substring(0, 2)) < 24 && Integer.parseInt(ftfHoraInicial.getText().substring(3, 5)) < 60) {
                if (Integer.parseInt(ftfHoraFinal.getText().substring(0, 2)) < 24 && Integer.parseInt(ftfHoraFinal.getText().substring(3, 5)) < 60) {
                    tfTempo.setText(Integer.toString(((Integer.parseInt(ftfHoraFinal.getText().substring(0, 2)) * 60) + Integer.parseInt(ftfHoraFinal.getText().substring(3, 5))) - ((Integer.parseInt(ftfHoraInicial.getText().substring(0, 2)) * 60) + Integer.parseInt(ftfHoraInicial.getText().substring(3, 5)))));
                } else {
                    tfTempo.setText("");
                }
            } else {
                tfTempo.setText("");
            }
        } else {
            tfTempo.setText("");
        }
    }

    private void novoItem() throws Exception {
        if ("".equals(tfUN.getText())) {
            lbUNItemObrig.setText("*");
        } else {
            lbUNItemObrig.setText("");
        }
        if ("".equals(tfItem.getText())) {
            lbItemObrig.setText("*");
        } else {
            lbItemObrig.setText("");
        }
        if ("".equals(tfValorUnit.getText())) {
            lbValorUnitObrig.setText("*");
        } else {
            lbValorUnitObrig.setText("");
        }
        if ("  :  ".equals(ftfHoraInicial.getText())) {
            lbHoraIniObrig.setText("*");
        } else {
            lbHoraIniObrig.setText("");
        }
        if ("  :  ".equals(ftfHoraFinal.getText())) {
            lbHoraFimObrig.setText("*");
        } else {
            lbHoraFimObrig.setText("");
        }
        if ("".equals(tfValorHora.getText())) {
            lbValorHoraObrig.setText("*");
        } else {
            lbValorHoraObrig.setText("");
        }
        if ("".equals(tfTempo.getText())) {
            lbTempoObrig.setText("*");
        } else {
            lbTempoObrig.setText("");
        }
        if (!"".equals(tfUN.getText()) && !"".equals(tfItem.getText()) && !"".equals(tfValorUnit.getText()) && !"  :  ".equals(ftfHoraInicial.getText()) && !"  :  ".equals(ftfHoraFinal.getText()) && !"".equals(tfValorHora.getText()) && !"".equals(tfTempo.getText())) {
            try {
                tfSubTotal.setText(NumberFormat.getCurrencyInstance().format((((Integer.parseInt(tfTempo.getText()) / 60) * Double.parseDouble(tfValorHora.getText())) + Double.parseDouble(tfValorTotal.getText().replace("R$ ", "").replace(".", "").replace(",", "."))) + controleItemOS.getTotalItensOS()));
            } catch (Exception ex) {
                tfSubTotal.setText("R$ 0,00");
                tfTotal.setText("R$ 0,00");
                tfParcelasMes.setText("R$ 0,00");
                throw new Exception("Caracter inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
            }
            ftfDataRea.setText(formatDate.format(new Date()));
            ItensOS itensOS = new ItensOS();
            itensOS.setCodigo(controleItemOS.getProxCodItemOS());
            itensOS.setCodOrdemServico(Integer.parseInt(tfCodigo.getText()));
            itensOS.setUnidade(Integer.parseInt(tfUN.getText()));
            itensOS.setNomeItem(tfItem.getText());
            itensOS.setDescricao(tfDescricao.getText());
            try {
                itensOS.setValorUnit(Double.parseDouble(tfValorUnit.getText()));
            } catch (Exception ex) {
                throw new Exception("Campo valor unitário inválido");
            }
            itensOS.setValorTotal(Double.parseDouble(tfValorTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
            itensOS.setData(new Date());
            descontos();
            juros();
            controleNovaOS.updateOrdemServico(novaOS());
            if (controleItemOS.setItensOS(itensOS)) {
                tableModel.fireTableDataChanged();
                tfNomeMecanico.setEditable(false);
                btFinalizar.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
                limparTelaItem();
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
                throw new Exception("Caracter inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
            }
        } else {
            tfTotal.setText("R$ 0,00");
        }
    }

    private void juros() throws Exception {
        if ("Á Vista".equals(cbCondPagto.getSelectedItem())) {
            return;
        }
        if (!"Selecione".equals(cbCondPagto.getSelectedItem()) && !"Selecione".equals(cbFormaPagto.getSelectedItem()) && !"".equals(tfJuros.getText()) && !"R$ 0,00".equals(tfTotal.getText())) {
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

    private void finalizar() throws Exception {
        if ("R$ 0,00".equals(tfSubTotal.getText())) {
            throw new Exception("cadastre no mínimo um item");
        }
        if ("".equals(tfDescontos.getText())) {
            throw new Exception("Informe os descontos");
        }
        if ("R$ 0,00".equals(tfTotal.getText())) {
            throw new Exception("Informe o total da ordem de serviço");
        }
        if ("Selecione".equals(cbCondPagto.getSelectedItem())) {
            throw new Exception("Selecione a condição de pagamento");
        }
        if ("Selecione".equals(cbFormaPagto.getSelectedItem())) {
            throw new Exception("Selecione a forma de pagamento");
        }
        if (!"Á Vista".equals(cbCondPagto.getSelectedItem())) {
            if ("".equals(tfJuros.getText())) {
                throw new Exception("Campo júros inválido");
            }
            if ("R$ 0,00".equals(tfParcelasMes.getText())) {
                throw new Exception("Informe o valor da parcelas");
            }
        }
        tfSituacao.setText("Finalizado");
        if (controleNovaOS.updateOrdemServico(novaOS())) {
            btAssumir.setEnabled(false);
            btGerar.setEnabled(true);
            btFinalizar.setEnabled(false);
            btCancelarOS.setEnabled(false);
            btOk.setEnabled(false);
            btConfirmar.setEnabled(false);
            btPesquisaPlaca.setEnabled(false);
            btPesquisaCliente.setEnabled(false);
            tfNomeMecanico.setEditable(false);
            ftfHoraInicial.setEditable(false);
            ftfHoraFinal.setEditable(false);
            tfValorHora.setEditable(false);
            tfDescontos.setEditable(false);
            tfJuros.setEditable(false);
            JOptionPane.showMessageDialog(null, "Ordem de serviço finalizada com sucesso", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cancelarOS() throws Exception {
        tfSituacao.setText("Cancelado");
        if (controleNovaOS.updateOrdemServico(novaOS())) {
            btAssumir.setEnabled(false);
            btGerar.setEnabled(true);
            btFinalizar.setEnabled(false);
            btCancelarOS.setEnabled(false);
            btOk.setEnabled(false);
            btConfirmar.setEnabled(false);
            ftfHoraInicial.setEditable(false);
            ftfHoraFinal.setEditable(false);
            tfValorHora.setEditable(false);
            tfDescontos.setEditable(false);
            tfJuros.setEditable(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btPesquisaPlaca) {
            try {
                recuperaVei();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btPesquisaCliente) {
            try {
                recuperaClie();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConfirmar) {
            try {
                gravarNovaOS();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btPesquisa) {
            try {
                recuperarOS();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btAssumir) {
            try {
                updateNovaOS();
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
        if (evento.getSource() == btCancelarOS) {
            try {
                cancelarOS();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btFinalizar) {
            try {
                finalizar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
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
        if (evento.getSource() == btGerar) {
            try {
                abrirRelatorio(Integer.parseInt(tfCodigo.getText()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDataGerar) {
            tfDataGerar.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUltAlteracao) {
            tfUltAlteracao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfVendedor) {
            tfVendedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSituacao) {
            tfSituacao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPlacaVei) {
            tfPlacaVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeVei) {
            tfNomeVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMarcaVei) {
            tfMarcaVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfAnoVei) {
            tfAnoVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfModeloVei) {
            tfModeloVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCorVei) {
            tfCorVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPotenciaVei) {
            tfPotenciaVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfVavulasVei) {
            tfVavulasVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCavalosVei) {
            tfCavalosVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCilindrosVei) {
            tfCilindrosVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCombustivelVei) {
            tfCombustivelVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTipoVei) {
            tfTipoVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfChassiVei) {
            tfChassiVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfRenavamVei) {
            tfRenavamVei.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSubTotal) {
            tfSubTotal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTotal) {
            tfTotal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescontos) {
            tfDescontos.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfNomeMecanico) {
            tfNomeMecanico.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTempo) {
            tfTempo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorHora) {
            tfValorHora.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUN) {
            tfUN.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfItem) {
            tfItem.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorUnit) {
            tfValorUnit.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorTotal) {
            tfValorTotal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataRea) {
            ftfDataRea.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfHoraInicial) {
            ftfHoraInicial.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfHoraFinal) {
            ftfHoraFinal.setBackground(Color.YELLOW);
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
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataGerar.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        if (evento.getSource() == tfVendedor) {
            tfVendedor.setBackground(Color.WHITE);
            try {
                verificaFuncionario(tfVendedor);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfSituacao.setBackground(Color.WHITE);
        tfPlacaVei.setBackground(Color.WHITE);
        tfNomeVei.setBackground(Color.WHITE);
        tfMarcaVei.setBackground(Color.WHITE);
        tfAnoVei.setBackground(Color.WHITE);
        tfModeloVei.setBackground(Color.WHITE);
        tfCorVei.setBackground(Color.WHITE);
        tfPotenciaVei.setBackground(Color.WHITE);
        tfVavulasVei.setBackground(Color.WHITE);
        tfCavalosVei.setBackground(Color.WHITE);
        tfCilindrosVei.setBackground(Color.WHITE);
        tfCombustivelVei.setBackground(Color.WHITE);
        tfTipoVei.setBackground(Color.WHITE);
        tfChassiVei.setBackground(Color.WHITE);
        tfRenavamVei.setBackground(Color.WHITE);
        tfSubTotal.setBackground(Color.WHITE);
        tfTotal.setBackground(Color.WHITE);
        if (evento.getSource() == tfDescontos) {
            tfDescontos.setBackground(Color.WHITE);
            try {
                descontos();
                juros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
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
        if (evento.getSource() == tfNomeMecanico) {
            tfNomeMecanico.setBackground(Color.WHITE);
            try {
                verificaFuncionario(tfNomeMecanico);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfTempo.setBackground(Color.WHITE);
        tfValorHora.setBackground(Color.WHITE);
        if (evento.getSource() == tfUN) {
            tfUN.setBackground(Color.WHITE);
            try {
                calculaValorTotalItem();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfItem.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        if (evento.getSource() == tfValorUnit) {
            tfValorUnit.setBackground(Color.WHITE);
            try {
                calculaValorTotalItem();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfValorTotal.setBackground(Color.WHITE);
        ftfDataRea.setBackground(Color.WHITE);
        if (evento.getSource() == ftfHoraInicial) {
            ftfHoraInicial.setBackground(Color.WHITE);
            try {
                calculaTempo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == ftfHoraFinal) {
            ftfHoraFinal.setBackground(Color.WHITE);
            try {
                calculaTempo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
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
    }
}

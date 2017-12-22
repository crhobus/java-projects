package OrdensServicos;

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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Clientes.ClienteControl;
import Clientes.ListenerCliente;
import Clientes.PesquisaClientes;
import Formatacao.CamposInt;
import Formatacao.ObjGraficos;
import Funcionarios.FuncionarioControl;
import Modelo.Cliente;
import Modelo.ItensOS;
import Modelo.OrdemServico;

public class NovaOS extends ObjGraficos implements ActionListener, FocusListener {

    private static final long serialVersionUID = 411298793883202237L;
    private JTextField tfNumeroOS, tfDataGerar, tfUltAlteracao, tfAtendente,
            tfSituacao, tfNomeApa, tfMarcaApa, tfModeloApa, tfCorApa,
            tfNumeroSerieApa, tfSubTotal, tfTotal, tfDescontos, tfJuros,
            tfParcelasMes, tfCpfCnpjClie, tfNomeClie, tfEnderecoClie,
            tfBairroClie, tfNumeroClie, tfCidadeClie, tfUFClie, tfFoneClie,
            tfCelularClie, tfEMailClie, tfNomeTecnico, tfTempo, tfValorHora,
            tfUN, tfItem, tfDescricao, tfValorUnit, tfValorTotal,
            tfAssistencia;
    private JFormattedTextField ftfDataRea, ftfHoraInicial, ftfHoraFinal;
    private JComboBox cbCondPagto, cbFormaPagto, cbAssistencia;
    private JTextArea taDescricao, taAcessorios;
    private JButton btPesquisa, btPesquisaCliente, btAssumir, btGerar,
            btFinalizar, btOk, btConfirmar, btCancelar, btCancelarOS,
            btRetirar;
    private JLabel lbNomeAtenObrig, lbNomeApaObrig, lbMarcaApaObrig,
            lbModeloApaObrig, lbAssisApaObrig, lbCPFCNPJClieObrig,
            lbUNItemObrig, lbItemObrig, lbValorUnitObrig, lbNomeTecObrig,
            lbHoraIniObrig, lbHoraFimObrig, lbValorHoraObrig, lbTempoObrig,
            lbDescricaoObrig;
    private JCheckBox cbxOrcamento;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Connection con;
    private NovaOSControl controleNovaOS;
    private ItensOSControl controleItensOS;
    private TableModelItensOS tableModel;
    private RenderizadoraItens rendener;

    public NovaOS(Connection con) {
        this.con = con;
        controleNovaOS = new NovaOSControl(con);
        controleItensOS = new ItensOSControl(con);
        tableModel = new TableModelItensOS(controleItensOS);
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

        JPanel panelOS = getJPanelTitledBorder("Dados Ordem Serviço", 10, 10, 410, 370);
        tela.add(panelOS);

        JPanel panelCliente = getJPanelTitledBorder("Dados Clientes", 425, 10, 410, 210);
        tela.add(panelCliente);

        JPanel panelSituacao = getJPanelTitledBorder("Situação OS", 425, 217, 410, 163);
        tela.add(panelSituacao);

        JPanel panelItens = getJPanelTitledBorder("", 12, 383, 823, 50);
        tela.add(panelItens);

        JPanel panelConfirmacao = getJPanelTitledBorder("", 12, 607, 815, 95);
        tela.add(panelConfirmacao);

        panelOS.add(getJLabel("Número", 20, 30, 80, 14));
        tfNumeroOS = getJTextField(20, 48, 80, 20);
        try {
            tfNumeroOS.setText(Integer.toString(controleNovaOS.getProxNumOS()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfNumeroOS.setEditable(false);
        tfNumeroOS.setBackground(Color.WHITE);
        tfNumeroOS.addFocusListener(this);
        panelOS.add(tfNumeroOS);

        btPesquisa = getJButton("...", 106, 44, 31, 24);
        btPesquisa.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisa.setToolTipText("Consulta Ordens de Serviços");
        btPesquisa.addActionListener(this);
        panelOS.add(btPesquisa);

        panelOS.add(getJLabel("Gerado em", 143, 30, 90, 14));
        tfDataGerar = getJTextField(143, 48, 120, 20);
        tfDataGerar.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataGerar.setEditable(false);
        tfDataGerar.setBackground(Color.WHITE);
        tfDataGerar.addFocusListener(this);
        panelOS.add(tfDataGerar);

        panelOS.add(getJLabel("Última Alteração", 270, 30, 100, 14));
        tfUltAlteracao = getJTextField(270, 48, 120, 20);
        tfUltAlteracao.setEditable(false);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfUltAlteracao.addFocusListener(this);
        panelOS.add(tfUltAlteracao);

        panelOS.add(getJLabel("Atendente", 20, 70, 120, 14));
        lbNomeAtenObrig = getJLabel("", 82, 73, 10, 14);
        lbNomeAtenObrig.setForeground(Color.RED);
        panelOS.add(lbNomeAtenObrig);
        tfAtendente = getJTextField(20, 88, 230, 20);
        tfAtendente.setBackground(Color.WHITE);
        tfAtendente.addFocusListener(this);
        panelOS.add(tfAtendente);

        panelOS.add(getJLabel("Situação", 260, 70, 120, 14));
        tfSituacao = getJTextField(260, 88, 130, 20);
        tfSituacao.setEditable(false);
        tfSituacao.setBackground(Color.WHITE);
        tfSituacao.addFocusListener(this);
        panelOS.add(tfSituacao);

        panelOS.add(getJLabel("Nome / Aparelho", 20, 110, 120, 14));
        lbNomeApaObrig = getJLabel("", 118, 113, 10, 14);
        lbNomeApaObrig.setForeground(Color.RED);
        panelOS.add(lbNomeApaObrig);
        tfNomeApa = getJTextField(20, 128, 150, 20);
        tfNomeApa.setBackground(Color.WHITE);
        tfNomeApa.addFocusListener(this);
        panelOS.add(tfNomeApa);

        panelOS.add(getJLabel("Marca", 180, 110, 120, 14));
        lbMarcaApaObrig = getJLabel("", 219, 113, 10, 14);
        lbMarcaApaObrig.setForeground(Color.RED);
        panelOS.add(lbMarcaApaObrig);
        tfMarcaApa = getJTextField(180, 128, 100, 20);
        tfMarcaApa.setBackground(Color.WHITE);
        tfMarcaApa.addFocusListener(this);
        panelOS.add(tfMarcaApa);

        panelOS.add(getJLabel("Modelo", 290, 110, 120, 14));
        lbModeloApaObrig = getJLabel("", 335, 113, 10, 14);
        lbModeloApaObrig.setForeground(Color.RED);
        panelOS.add(lbModeloApaObrig);
        tfModeloApa = getJTextField(290, 128, 100, 20);
        tfModeloApa.setBackground(Color.WHITE);
        tfModeloApa.addFocusListener(this);
        panelOS.add(tfModeloApa);

        panelOS.add(getJLabel("Cor", 20, 150, 120, 14));
        tfCorApa = getJTextField(20, 168, 110, 20);
        tfCorApa.setBackground(Color.WHITE);
        tfCorApa.addFocusListener(this);
        panelOS.add(tfCorApa);

        panelOS.add(getJLabel("Número Série", 140, 150, 120, 14));
        tfNumeroSerieApa = getJTextField(140, 168, 110, 20);
        tfNumeroSerieApa.setBackground(Color.WHITE);
        tfNumeroSerieApa.setDocument(new CamposInt());
        tfNumeroSerieApa.addFocusListener(this);
        panelOS.add(tfNumeroSerieApa);

        panelOS.add(getJLabel("Assistência", 260, 150, 90, 14));
        lbAssisApaObrig = getJLabel("", 332, 153, 10, 14);
        lbAssisApaObrig.setForeground(Color.RED);
        panelOS.add(lbAssisApaObrig);
        itensCombo.add("Com Garantia");
        itensCombo.add("Sem Garantia");
        cbAssistencia = getJComboBox(itensCombo, 260, 168, 130, 20);
        cbAssistencia.addFocusListener(this);
        panelOS.add(cbAssistencia);
        tfAssistencia = getJTextField(260, 168, 130, 20);
        tfAssistencia.setEditable(false);
        tfAssistencia.setBackground(Color.WHITE);
        tfAssistencia.addFocusListener(this);
        tfAssistencia.setVisible(false);
        panelOS.add(tfAssistencia);

        panelOS.add(getJLabel("Acessorios", 20, 190, 120, 14));
        taAcessorios = getJTextArea(panelOS, 20, 208, 370, 60);
        taAcessorios.setBackground(Color.WHITE);
        taAcessorios.addFocusListener(this);

        panelOS.add(getJLabel("Orçamento", 125, 190, 90, 14));
        cbxOrcamento = getJCheckBox(100, 187, 20, 20);
        panelOS.add(cbxOrcamento);

        panelOS.add(getJLabel("Sub-Total", 20, 270, 120, 14));
        tfSubTotal = getJTextField(20, 288, 115, 20);
        tfSubTotal.setText("R$ 0,00");
        tfSubTotal.setEditable(false);
        tfSubTotal.setBackground(Color.WHITE);
        tfSubTotal.addFocusListener(this);
        panelOS.add(tfSubTotal);

        panelOS.add(getJLabel("Descontos", 145, 270, 120, 14));
        tfDescontos = getJTextField(145, 288, 105, 20);
        tfDescontos.setBackground(Color.WHITE);
        tfDescontos.addFocusListener(this);
        panelOS.add(tfDescontos);
        panelOS.add(getJLabel("%", 253, 291, 10, 14));

        panelOS.add(getJLabel("Total", 270, 270, 120, 14));
        tfTotal = getJTextField(270, 288, 120, 20);
        tfTotal.setText("R$ 0,00");
        tfTotal.setEditable(false);
        tfTotal.setBackground(Color.WHITE);
        tfTotal.addFocusListener(this);
        panelOS.add(tfTotal);

        panelOS.add(getJLabel("Cond. Pagto", 20, 310, 90, 14));
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
        cbCondPagto = getJComboBox(itensCombo, 20, 328, 90, 20);
        cbCondPagto.addFocusListener(this);
        cbCondPagto.addActionListener(this);
        panelOS.add(cbCondPagto);

        panelOS.add(getJLabel("Forma. Pagto", 120, 310, 90, 14));
        itensCombo.clear();
        itensCombo.add("Dinheiro");
        itensCombo.add("Cheque");
        itensCombo.add("Cartão");
        cbFormaPagto = getJComboBox(itensCombo, 120, 328, 90, 20);
        cbFormaPagto.addFocusListener(this);
        panelOS.add(cbFormaPagto);

        panelOS.add(getJLabel("Juros", 220, 310, 120, 14));
        tfJuros = getJTextField(220, 328, 72, 20);
        tfJuros.setBackground(Color.WHITE);
        tfJuros.addFocusListener(this);
        panelOS.add(tfJuros);
        panelOS.add(getJLabel("%", 296, 331, 10, 14));

        panelOS.add(getJLabel("Parcelas Mês", 310, 310, 120, 14));
        tfParcelasMes = getJTextField(310, 328, 80, 20);
        tfParcelasMes.setText("R$ 0,00");
        tfParcelasMes.setEditable(false);
        tfParcelasMes.setBackground(Color.WHITE);
        tfParcelasMes.addFocusListener(this);
        panelOS.add(tfParcelasMes);

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

        panelCliente.add(getJLabel("UF", 160, 110, 120, 14));
        tfUFClie = getJTextField(160, 128, 110, 20);
        tfUFClie.setEditable(false);
        tfUFClie.setBackground(Color.WHITE);
        tfUFClie.addFocusListener(this);
        panelCliente.add(tfUFClie);

        panelCliente.add(getJLabel("Fone", 280, 110, 120, 14));
        tfFoneClie = getJTextField(280, 128, 110, 20);
        tfFoneClie.setEditable(false);
        tfFoneClie.setBackground(Color.WHITE);
        tfFoneClie.addFocusListener(this);
        panelCliente.add(tfFoneClie);

        panelCliente.add(getJLabel("Celular", 20, 150, 120, 14));
        tfCelularClie = getJTextField(20, 168, 110, 20);
        tfCelularClie.setEditable(false);
        tfCelularClie.setBackground(Color.WHITE);
        tfCelularClie.addFocusListener(this);
        panelCliente.add(tfCelularClie);

        panelCliente.add(getJLabel("E-Mail", 140, 150, 120, 14));
        tfEMailClie = getJTextField(140, 168, 250, 20);
        tfEMailClie.setEditable(false);
        tfEMailClie.setBackground(Color.WHITE);
        tfEMailClie.addFocusListener(this);
        panelCliente.add(tfEMailClie);

        panelSituacao.add(getJLabel("Operação Realizada Por", 20, 30, 150, 14));
        lbNomeTecObrig = getJLabel("", 160, 33, 10, 14);
        lbNomeTecObrig.setForeground(Color.RED);
        panelSituacao.add(lbNomeTecObrig);
        tfNomeTecnico = getJTextField(20, 48, 170, 20);
        tfNomeTecnico.setEditable(false);
        tfNomeTecnico.setBackground(Color.WHITE);
        tfNomeTecnico.addFocusListener(this);
        panelSituacao.add(tfNomeTecnico);

        panelSituacao.add(getJLabel("Data", 200, 30, 100, 14));
        ftfDataRea = getJFormattedTextField("##/##/####", 200, 48, 100, 20);
        ftfDataRea.setEditable(false);
        ftfDataRea.setBackground(Color.WHITE);
        ftfDataRea.addFocusListener(this);
        panelSituacao.add(ftfDataRea);

        panelSituacao.add(getJLabel("Hora Inicial", 310, 30, 100, 14));
        lbHoraIniObrig = getJLabel("", 378, 33, 10, 14);
        lbHoraIniObrig.setForeground(Color.RED);
        panelSituacao.add(lbHoraIniObrig);
        ftfHoraInicial = getJFormattedTextField("##:##", 310, 48, 80, 20);
        ftfHoraInicial.setEditable(false);
        ftfHoraInicial.setBackground(Color.WHITE);
        ftfHoraInicial.addFocusListener(this);
        panelSituacao.add(ftfHoraInicial);

        panelSituacao.add(getJLabel("Hora Final", 20, 70, 100, 14));
        lbHoraFimObrig = getJLabel("", 80, 73, 10, 14);
        lbHoraFimObrig.setForeground(Color.RED);
        panelSituacao.add(lbHoraFimObrig);
        ftfHoraFinal = getJFormattedTextField("##:##", 20, 88, 80, 20);
        ftfHoraFinal.setEditable(false);
        ftfHoraFinal.setBackground(Color.WHITE);
        ftfHoraFinal.addFocusListener(this);
        panelSituacao.add(ftfHoraFinal);

        panelSituacao.add(getJLabel("Tempo", 110, 70, 120, 14));
        lbTempoObrig = getJLabel("", 154, 73, 10, 14);
        lbTempoObrig.setForeground(Color.RED);
        panelSituacao.add(lbTempoObrig);
        tfTempo = getJTextField(110, 88, 100, 20);
        tfTempo.setEditable(false);
        tfTempo.setBackground(Color.WHITE);
        tfTempo.addFocusListener(this);
        panelSituacao.add(tfTempo);

        panelSituacao.add(getJLabel("Valor Por Hora", 220, 70, 120, 14));
        lbValorHoraObrig = getJLabel("", 308, 73, 10, 14);
        lbValorHoraObrig.setForeground(Color.RED);
        panelSituacao.add(lbValorHoraObrig);
        tfValorHora = getJTextField(220, 88, 100, 20);
        tfValorHora.setEditable(false);
        tfValorHora.setBackground(Color.WHITE);
        tfValorHora.addFocusListener(this);
        panelSituacao.add(tfValorHora);

        btAssumir = getJButton("Assumir", 8, 120, 82, 26);
        btAssumir.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btAssumir.setToolTipText("Assumir Ordem de Serviço");
        btAssumir.setEnabled(false);
        btAssumir.addActionListener(this);
        panelSituacao.add(btAssumir);

        btGerar = getJButton("Gerar", 95, 120, 66, 26);
        btGerar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btGerar.setToolTipText("Gerar Relatório");
        btGerar.addActionListener(this);
        btGerar.setEnabled(false);
        panelSituacao.add(btGerar);

        btFinalizar = getJButton("Finalizar", 165, 120, 81, 26);
        btFinalizar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btFinalizar.setToolTipText("Finalizar Ordem de serviço");
        btFinalizar.addActionListener(this);
        btFinalizar.setEnabled(false);
        panelSituacao.add(btFinalizar);

        btCancelarOS = getJButton("Cancelar OS", 250, 120, 105, 26);
        btCancelarOS.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelarOS.setToolTipText("Cancelar Ordem de Serviço");
        btCancelarOS.addActionListener(this);
        btCancelarOS.setEnabled(false);
        panelSituacao.add(btCancelarOS);

        btRetirar = getJButton("...", 358, 120, 44, 26);
        btRetirar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btRetirar.setToolTipText("Retirar o Aparelho");
        btRetirar.addActionListener(this);
        btRetirar.setEnabled(false);
        panelSituacao.add(btRetirar);

        panelItens.add(getJLabel("UN", 20, 15, 20, 14));
        lbUNItemObrig = getJLabel("", 37, 18, 10, 14);
        lbUNItemObrig.setForeground(Color.RED);
        panelItens.add(lbUNItemObrig);
        tfUN = getJTextField(45, 13, 40, 20);
        tfUN.setDocument(new CamposInt());
        tfUN.addFocusListener(this);
        panelItens.add(tfUN);

        panelItens.add(getJLabel("Item", 90, 15, 80, 14));
        lbItemObrig = getJLabel("", 115, 18, 10, 14);
        lbItemObrig.setForeground(Color.RED);
        panelItens.add(lbItemObrig);
        tfItem = getJTextField(120, 13, 120, 20);
        tfItem.addFocusListener(this);
        panelItens.add(tfItem);

        panelItens.add(getJLabel("Descrição", 245, 15, 80, 14));
        tfDescricao = getJTextField(310, 13, 170, 20);
        tfDescricao.addFocusListener(this);
        panelItens.add(tfDescricao);

        panelItens.add(getJLabel("Valor Unit", 485, 15, 80, 14));
        lbValorUnitObrig = getJLabel("", 541, 18, 10, 14);
        lbValorUnitObrig.setForeground(Color.RED);
        panelItens.add(lbValorUnitObrig);
        tfValorUnit = getJTextField(547, 13, 65, 20);
        tfValorUnit.addFocusListener(this);
        panelItens.add(tfValorUnit);

        panelItens.add(getJLabel("Valor Total", 617, 15, 80, 14));
        tfValorTotal = getJTextField(682, 13, 80, 20);
        tfValorTotal.setText("R$ 0,00");
        tfValorTotal.setEditable(false);
        tfValorTotal.setBackground(Color.WHITE);
        tfValorTotal.addFocusListener(this);
        panelItens.add(tfValorTotal);

        btOk = getJButton("OK", 765, 10, 51, 26);
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
        scroll.setBounds(12, 438, 823, 165);
        tela.add(scroll);

        panelConfirmacao.add(getJLabel("Descrição / Defeito", 10, 4, 110, 14));
        lbDescricaoObrig = getJLabel("", 122, 7, 10, 14);
        lbDescricaoObrig.setForeground(Color.RED);
        panelConfirmacao.add(lbDescricaoObrig);
        taDescricao = getJTextArea(panelConfirmacao, 10, 20, 690, 68);
        taDescricao.setBackground(Color.WHITE);
        taDescricao.addFocusListener(this);

        btConfirmar = getJButton("Confirmar", 710, 15, 91, 26);
        btConfirmar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConfirmar.setToolTipText("Confirmar");
        btConfirmar.addActionListener(this);
        panelConfirmacao.add(btConfirmar);

        btCancelar = getJButton("Cancelar", 710, 50, 91, 26);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        panelConfirmacao.add(btCancelar);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panelOS.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panelOS.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);
        panelCliente.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);
        panelSituacao.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);
        panelItens.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);
        panelConfirmacao.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(855, 740);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfNumeroOS.setText(Integer.toString(controleNovaOS.getProxNumOS()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataGerar.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfAtendente.setText("");
        tfSituacao.setText("");
        tfSubTotal.setText("R$ 0,00");
        tfTotal.setText("R$ 0,00");
        tfDescontos.setText("");
        tfJuros.setText("");
        tfParcelasMes.setText("R$ 0,00");
        tfNomeTecnico.setText("");
        tfTempo.setText("");
        tfValorHora.setText("");
        ftfDataRea.setText("");
        ftfHoraInicial.setText("");
        ftfHoraFinal.setText("");
        cbCondPagto.setSelectedItem("Selecione");
        cbFormaPagto.setSelectedItem("Selecione");
        taDescricao.setText("");
        tfNomeApa.setText("");
        tfMarcaApa.setText("");
        tfModeloApa.setText("");
        tfCorApa.setText("");
        tfNumeroSerieApa.setText("");
        cbAssistencia.setSelectedItem("Selecione");
        taAcessorios.setText("");
        cbxOrcamento.setSelected(false);
        lbNomeAtenObrig.setText("");
        lbNomeApaObrig.setText("");
        lbMarcaApaObrig.setText("");
        lbModeloApaObrig.setText("");
        lbAssisApaObrig.setText("");
        lbCPFCNPJClieObrig.setText("");
        lbNomeTecObrig.setText("");
        limparTelaClie();
        limparTelaItem();
        btConfirmar.setEnabled(true);
        btAssumir.setEnabled(false);
        btGerar.setEnabled(false);
        btFinalizar.setEnabled(false);
        btCancelarOS.setEnabled(false);
        btRetirar.setEnabled(false);
        btOk.setEnabled(false);
        btPesquisaCliente.setEnabled(true);
        tfNomeTecnico.setEditable(false);
        ftfHoraInicial.setEditable(false);
        ftfHoraFinal.setEditable(false);
        tfValorHora.setEditable(false);
        tfDescontos.setEditable(true);
        tfJuros.setEditable(true);
        tfAtendente.setEditable(true);
        tfNomeApa.setEditable(true);
        tfMarcaApa.setEditable(true);
        tfModeloApa.setEditable(true);
        tfCorApa.setEditable(true);
        tfNumeroSerieApa.setEditable(true);
        cbAssistencia.setVisible(true);
        tfAssistencia.setVisible(false);
        tfAssistencia.setText("");
        cbxOrcamento.setEnabled(true);
        taAcessorios.setEditable(true);
        taDescricao.setEditable(true);
        controleItensOS.limparLista();
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

    private void recuperarClie() throws Exception {
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

    private void recuperarCliente(ClienteControl controlClie, String cpfCNPJ) throws Exception {
        Cliente cliente = controlClie.selectCliente(cpfCNPJ);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mostrarCliente(cliente);
        }
    }

    private void mostrarCliente(Cliente cliente) {
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

    private void verificaFuncionario(JTextField tf) throws Exception {
        if (!"".equals(tf.getText().trim())) {
            FuncionarioControl controlFunc = new FuncionarioControl(con);
            if (!controlFunc.isFuncCadas(tf.getText())) {
                tf.setText("");
                tf.grabFocus();
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void gravarNovaOS() throws Exception {
        if ("".equals(tfAtendente.getText().trim())) {
            lbNomeAtenObrig.setText("*");
        } else {
            lbNomeAtenObrig.setText("");
        }
        if ("".equals(tfNomeApa.getText().trim())) {
            lbNomeApaObrig.setText("*");
        } else {
            lbNomeApaObrig.setText("");
        }
        if ("".equals(tfMarcaApa.getText().trim())) {
            lbMarcaApaObrig.setText("*");
        } else {
            lbMarcaApaObrig.setText("");
        }
        if ("".equals(tfModeloApa.getText().trim())) {
            lbModeloApaObrig.setText("*");
        } else {
            lbModeloApaObrig.setText("");
        }
        if ("Selecione".equals(cbAssistencia.getSelectedItem())) {
            lbAssisApaObrig.setText("*");
        } else {
            lbAssisApaObrig.setText("");
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
        if (!"".equals(tfAtendente.getText().trim()) && !"".equals(tfNomeApa.getText().trim()) && !"".equals(tfMarcaApa.getText().trim()) && !"".equals(tfModeloApa.getText().trim()) && !"Selecione".equals(cbAssistencia.getSelectedItem()) && !"".equals(tfCpfCnpjClie.getText()) && !"".equals(taDescricao.getText().trim())) {
            tfSituacao.setText("Aberto");
            if (controleNovaOS.insertOrdemServico(novaOS())) {
                btConfirmar.setEnabled(false);
                btAssumir.setEnabled(true);
                btGerar.setEnabled(true);
                btPesquisaCliente.setEnabled(false);
                tfNomeTecnico.setEditable(true);
                tfAtendente.setEditable(false);
                tfNomeApa.setEditable(false);
                tfMarcaApa.setEditable(false);
                tfModeloApa.setEditable(false);
                tfCorApa.setEditable(false);
                tfNumeroSerieApa.setEditable(false);
                cbAssistencia.setVisible(false);
                tfAssistencia.setVisible(true);
                tfAssistencia.setText((String) cbAssistencia.getSelectedItem());
                cbxOrcamento.setEnabled(false);
                taAcessorios.setEditable(false);
                taDescricao.setEditable(false);
                JOptionPane.showMessageDialog(null, "Ordem de serviço cadastrada com sucesso", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private OrdemServico novaOS() throws Exception {
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setNumeroOs(Integer.parseInt(tfNumeroOS.getText()));
        ordemServico.setNumeroSerieApa(verificaInt(tfNumeroSerieApa, "número série"));
        ordemServico.setTempo(verificaInt(tfTempo, "tempo"));
        ordemServico.setNomeAtendente(tfAtendente.getText());
        ordemServico.setSituacao(tfSituacao.getText());
        ordemServico.setNomeApa(tfNomeApa.getText());
        ordemServico.setMarcaApa(tfMarcaApa.getText());
        ordemServico.setModeloApa(tfModeloApa.getText());
        ordemServico.setCorApa(tfCorApa.getText());
        if (cbAssistencia.isVisible()) {
            ordemServico.setAssistenciaApa((String) cbAssistencia.getSelectedItem());
        } else {
            ordemServico.setAssistenciaApa(tfAssistencia.getText());
        }
        ordemServico.setAcessoriosApa(taAcessorios.getText());
        ordemServico.setCondPagto((String) cbCondPagto.getSelectedItem());
        ordemServico.setFormaPagto((String) cbFormaPagto.getSelectedItem());
        ordemServico.setCpfCnpjClie(tfCpfCnpjClie.getText());
        ordemServico.setNomeOPRealizada(tfNomeTecnico.getText());
        ordemServico.setHoraInicial(ftfHoraInicial.getText());
        ordemServico.setHoraFinal(ftfHoraFinal.getText());
        ordemServico.setDescricaoApa(taDescricao.getText());
        ordemServico.setDataGeracao(formatDateHora.parse(tfDataGerar.getText().replace(" as ", " ").concat(":00")));
        ordemServico.setUltAlteracao(new Date());
        ordemServico.setData(verificaDate(ftfDataRea, "data"));
        ordemServico.setSubTotal(Double.parseDouble(tfSubTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
        ordemServico.setDescontos(verificaDouble(tfDescontos, "descontos"));
        ordemServico.setTotal(Double.parseDouble(tfTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
        ordemServico.setJuros(verificaDouble(tfJuros, "juros"));
        ordemServico.setParcelasMes(Double.parseDouble(tfParcelasMes.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
        ordemServico.setValorPorHora(verificaDouble(tfValorHora, "valor por hora"));
        if (cbxOrcamento.isSelected() == true) {
            ordemServico.setOrcamentoApa(true);
        }
        return ordemServico;
    }

    private void recuperarOS() throws Exception {
        if (controleNovaOS.isOSVazio()) {
            throw new Exception("Não há ordens de serviços cadastradas");
        }
        JTextField entrada = new JTextField();
        entrada.setDocument(new CamposInt());
        final Object[] mensagem = {"Entre com  número da ordem de serviço", entrada};
        final String[] opcoes = {"OK", "Consultar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Ordem de Servico", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (result) {
            case 0: // Ok
                if (!"".equals(entrada.getText())) {
                    recuperarOS(Integer.parseInt(entrada.getText()));
                    break;
                }
                break;
            case 1: // Consultar
                PesquisaOrdemServico pesquisaOS = new PesquisaOrdemServico(controleNovaOS);
                pesquisaOS.setListener(new ListenerOS() {

                    @Override
                    public void exibeOrdemServico(OrdemServico ordemServico) {
                        try {
                            mostraOrdemServico(ordemServico);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                pesquisaOS.setModal(true);
                pesquisaOS.setVisible(true);
                break;
            case 2: // Cancelar
                break;
        }
    }

    private void recuperarOS(int numero) throws Exception {
        OrdemServico ordemServico = controleNovaOS.selectOrdemServico(numero);
        if (ordemServico != null) {
            mostraOrdemServico(ordemServico);
            JOptionPane.showMessageDialog(null, "Ordem de serviço recuperada com sucesso", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Ordem de serviço não encontrada", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostraOrdemServico(OrdemServico ordemServico) throws Exception {
        limparTela();
        tfNumeroOS.setText(Integer.toString(ordemServico.getNumeroOs()));
        tfNumeroSerieApa.setText(recuperaCampoStr(ordemServico.getNumeroSerieApa()));
        tfTempo.setText(Integer.toString(ordemServico.getTempo()));
        tfAtendente.setText(ordemServico.getNomeAtendente());
        tfSituacao.setText(ordemServico.getSituacao());
        tfNomeApa.setText(ordemServico.getNomeApa());
        tfMarcaApa.setText(ordemServico.getMarcaApa());
        tfModeloApa.setText(ordemServico.getModeloApa());
        tfCorApa.setText(ordemServico.getCorApa());
        tfAssistencia.setText(ordemServico.getAssistenciaApa());
        taAcessorios.setText(ordemServico.getAcessoriosApa());
        cbCondPagto.setSelectedItem(ordemServico.getCondPagto());
        cbFormaPagto.setSelectedItem(ordemServico.getFormaPagto());
        ClienteControl controlClie = new ClienteControl(con);
        recuperarCliente(controlClie, ordemServico.getCpfCnpjClie());
        tfNomeTecnico.setText(ordemServico.getNomeOPRealizada());
        ftfHoraInicial.setText(ordemServico.getHoraInicial());
        ftfHoraFinal.setText(ordemServico.getHoraFinal());
        taDescricao.setText(ordemServico.getDescricaoApa());
        tfDataGerar.setText(formatDate.format(ordemServico.getDataGeracao()) + " as " + formatHora.format(ordemServico.getDataGeracao()));
        tfUltAlteracao.setText(formatDate.format(ordemServico.getUltAlteracao()) + " as " + formatHora.format(ordemServico.getUltAlteracao()));
        ftfDataRea.setText(recuperaCampoDate(ordemServico.getData()));
        tfSubTotal.setText(NumberFormat.getCurrencyInstance().format(ordemServico.getSubTotal()));
        tfDescontos.setText(recuperaCampoStr(ordemServico.getDescontos()));
        tfTotal.setText(NumberFormat.getCurrencyInstance().format(ordemServico.getTotal()));
        tfJuros.setText(recuperaCampoStr(ordemServico.getJuros()));
        tfParcelasMes.setText(NumberFormat.getCurrencyInstance().format(ordemServico.getParcelasMes()));
        tfValorHora.setText(recuperaCampoStr(ordemServico.getValorPorHora()));
        if (ordemServico.isOrcamentoApa()) {
            cbxOrcamento.setSelected(true);
        }
        tfAtendente.setEditable(false);
        tfNomeApa.setEditable(false);
        tfMarcaApa.setEditable(false);
        tfModeloApa.setEditable(false);
        tfCorApa.setEditable(false);
        tfNumeroSerieApa.setEditable(false);
        cbAssistencia.setVisible(false);
        tfAssistencia.setVisible(true);
        cbxOrcamento.setEnabled(false);
        taAcessorios.setEditable(false);
        taDescricao.setEditable(false);
        if ("Á Vista".equals(cbCondPagto.getSelectedItem())) {
            tfJuros.setText("");
            tfParcelasMes.setText("R$ 0,00");
            tfJuros.setEditable(false);
        }
        if ("Aberto".equals(ordemServico.getSituacao())) {
            btConfirmar.setEnabled(false);
            btAssumir.setEnabled(true);
            btPesquisaCliente.setEnabled(false);
            btCancelarOS.setEnabled(true);
            tfNomeTecnico.setEditable(true);
            btGerar.setEnabled(true);
        } else {
            controleItensOS.listarItens(Integer.parseInt(tfNumeroOS.getText()));
            tableModel.fireTableDataChanged();
            btPesquisaCliente.setEnabled(false);
            if ("Em Atendimento".equals(ordemServico.getSituacao())) {
                btConfirmar.setEnabled(false);
                btOk.setEnabled(true);
                btCancelarOS.setEnabled(true);
                btFinalizar.setEnabled(true);
                ftfHoraInicial.setEditable(true);
                ftfHoraFinal.setEditable(true);
                tfValorHora.setEditable(true);
            } else {
                btConfirmar.setEnabled(false);
                tfDescontos.setEditable(false);
                tfJuros.setEditable(false);
                if ("Finalizado".equals(ordemServico.getSituacao())) {
                    btGerar.setEnabled(true);
                    btRetirar.setEnabled(true);
                } else {
                    if ("Cancelado".equals(ordemServico.getSituacao())) {
                        btGerar.setEnabled(true);
                        btRetirar.setEnabled(true);
                    }
                }
            }
        }
    }

    private void updateNovaOS() throws Exception {
        if ("".equals(tfNomeTecnico.getText())) {
            lbNomeTecObrig.setText("*");
        } else {
            lbNomeTecObrig.setText("");
        }
        if (!"".equals(tfNomeTecnico.getText())) {
            tfSituacao.setText("Em Atendimento");
            if (controleNovaOS.updateOrdemServico(novaOS())) {
                btAssumir.setEnabled(false);
                btOk.setEnabled(true);
                btFinalizar.setEnabled(true);
                btCancelarOS.setEnabled(true);
                btGerar.setEnabled(false);
                tfNomeTecnico.setEditable(false);
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
            subTotal();
            ftfDataRea.setText(formatDate.format(new Date()));
            ItensOS itensOS = new ItensOS();
            itensOS.setCodigo(controleItensOS.getProxCodItemOS());
            itensOS.setCodOrdemServico(Integer.parseInt(tfNumeroOS.getText()));
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
            if (controleItensOS.insertItensOS(itensOS)) {
                tableModel.fireTableDataChanged();
                tfNomeTecnico.setEditable(false);
                btFinalizar.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso", "Ordem de Serviço", JOptionPane.INFORMATION_MESSAGE);
                limparTelaItem();
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private void subTotal() throws Exception {
        try {
            tfSubTotal.setText(NumberFormat.getCurrencyInstance().format(((Double.parseDouble(tfTempo.getText()) / 60) * (Double.parseDouble(tfValorHora.getText()))) + Double.parseDouble(tfValorTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")) + controleItensOS.getTotalItensOS()));
        } catch (Exception ex) {
            tfSubTotal.setText("R$ 0,00");
            tfTotal.setText("R$ 0,00");
            tfParcelasMes.setText("R$ 0,00");
            throw new Exception("Caracter inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
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
        if ("".equals(tfDescontos.getText().trim())) {
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
            if ("".equals(tfJuros.getText().trim())) {
                throw new Exception("Campo júros inválido");
            }
            if ("R$ 0,00".equals(tfParcelasMes.getText())) {
                throw new Exception("Informe o valor da parcelas");
            }
        }
        subTotal();
        descontos();
        juros();
        tfSituacao.setText("Finalizado");
        if (controleNovaOS.updateOrdemServico(novaOS())) {
            btAssumir.setEnabled(false);
            btGerar.setEnabled(true);
            btFinalizar.setEnabled(false);
            btCancelarOS.setEnabled(false);
            btRetirar.setEnabled(true);
            btOk.setEnabled(false);
            btConfirmar.setEnabled(false);
            btPesquisaCliente.setEnabled(false);
            tfNomeTecnico.setEditable(false);
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
            btRetirar.setEnabled(true);
            btOk.setEnabled(false);
            btConfirmar.setEnabled(false);
            ftfHoraInicial.setEditable(false);
            ftfHoraFinal.setEditable(false);
            tfValorHora.setEditable(false);
            tfDescontos.setEditable(false);
            tfJuros.setEditable(false);
        }
    }

    private void retirarAparelho() throws Exception {
        String situacao = controleNovaOS.selectOrdemServico(Integer.parseInt(tfNumeroOS.getText())).getSituacao();
        if (situacao.equals("Finalizado")) {
            if (controleNovaOS.updateOrdemServicoSituacao("Finalizado - Retirado", Integer.parseInt(tfNumeroOS.getText()))) {
                tfSituacao.setText("Finalizado - Retirado");
                btRetirar.setEnabled(false);
                btGerar.setEnabled(false);
            }
        } else {
            if (controleNovaOS.updateOrdemServicoSituacao("Cancelado - Retirado", Integer.parseInt(tfNumeroOS.getText()))) {
                tfSituacao.setText("Cancelado - Retirado");
                btRetirar.setEnabled(false);
                btGerar.setEnabled(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btPesquisaCliente) {
            try {
                recuperarClie();
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
        if (evento.getSource() == btRetirar) {
            try {
                retirarAparelho();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btGerar) {
            try {
                // abrirRelatorio(Integer.parseInt(tfCodigo.getText()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfNumeroOS) {
            tfNumeroOS.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDataGerar) {
            tfDataGerar.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUltAlteracao) {
            tfUltAlteracao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfAtendente) {
            tfAtendente.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSituacao) {
            tfSituacao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeApa) {
            tfNomeApa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMarcaApa) {
            tfMarcaApa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfModeloApa) {
            tfModeloApa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCorApa) {
            tfCorApa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumeroSerieApa) {
            tfNumeroSerieApa.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfNomeTecnico) {
            tfNomeTecnico.setBackground(Color.YELLOW);
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
        if (evento.getSource() == cbAssistencia) {
            cbAssistencia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == taAcessorios) {
            taAcessorios.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfAssistencia) {
            tfAssistencia.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfNumeroOS.setBackground(Color.WHITE);
        tfDataGerar.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        if (evento.getSource() == tfAtendente) {
            tfAtendente.setBackground(Color.WHITE);
            try {
                verificaFuncionario(tfAtendente);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfSituacao.setBackground(Color.WHITE);
        tfNomeApa.setBackground(Color.WHITE);
        tfMarcaApa.setBackground(Color.WHITE);
        tfModeloApa.setBackground(Color.WHITE);
        tfCorApa.setBackground(Color.WHITE);
        tfNumeroSerieApa.setBackground(Color.WHITE);
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
        if (evento.getSource() == tfNomeTecnico) {
            tfNomeTecnico.setBackground(Color.WHITE);
            try {
                verificaFuncionario(tfNomeTecnico);
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
        cbAssistencia.setBackground(Color.WHITE);
        taAcessorios.setBackground(Color.WHITE);
        tfAssistencia.setBackground(Color.WHITE);
    }
}

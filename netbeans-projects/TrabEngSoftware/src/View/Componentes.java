package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;

public abstract class Componentes extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    JSpinner spTipoDadosALI, spTipoRegistroALI, spTipoDadosAIE,
            spTipoRegistroAIE, spTipoDadosEE, spArquivosReferenciadosEE,
            spTipoDadosCE, spArquivosReferenciadosCE, spTipoDadosSE,
            spArquivosReferenciadosSE;
    JTextField tfDescricaoALI, tfDescricaoAIE, tfDescricaoEE, tfDescricaoCE,
            tfDescricaoSE, tfPFB, tfFA, tfPFA, tfPrazo, tfPontosFunc, tfDescricaoAdd,
            tfCFP, tfVAFA, tfVAFB, tfUFPB, tfCHGB;
    JTextArea taResultadoALI, taResultadoAIE, taResultadoEE, taResultadoCE,
            taResultadoSE, taResultadoNI, taResultadoPRJ;
    JComboBox cbComuDados, cbProsDistr, cbPerformance, cbConfAltUlt,
            cbTaxTrans, cbEntDadosOn, cbEfUsuFinal, cbAtuaOn, cbComProc,
            cbReutilizacao, cbFacInstal, cbFacOperacao, cbMultLocal,
            cbFacMudancas, cbTipoAdd, cbComplexidadeAdd, cbArquivosDelALI, cbArquivosDelAIE,
            cbEntradasDelEE, cbConsultasCE, cbSaidasSE, cbArquivosAltALI, cbComplexidadeAltALI,
            cbArquivosAltAIE, cbComplexidadeAltAIE, cbArquivosAltEE, cbComplexidadeAltEE,
            cbArquivosAltCE, cbComplexidadeAltCE, cbArquivosAltSE, cbComplexidadeAltSE;
    JButton btDescricaoALI, btAddALI, btCalcularALI, btCancelarALI,
            btDescricaoAIE, btAddAIE, btCalcularAIE, btCancelarAIE,
            btDescricaoEE, btAddEE, btCalcularEE, btCancelarEE, btDescricaoCE,
            btAddCE, btCalcularCE, btCancelarCE, btDescricaoSE, btAddSE,
            btCalcularSE, btCancelarSE, btDescricaoNI, btCalcularNI,
            btCancelarNI, btMostrar, btOK, btNovo, btDescricaoPrj, btAdd, btDel, btAltALI,
            btAltAIE, btAltEE, btAltCE, btAltSE, btCalcularPRJ, btCancelarPRJ;
    JTabbedPane tabPanels;

    public Componentes() {
        super("Calculadora de FPA e projeto de melhoria");
        initialize();
    }

    private void initialize() {
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(770, 405);
        this.setContentPane(getTela());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanel getTela() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));
        panel.add(getTabbedPane(), null);
        return panel;
    }

    private JTabbedPane getTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(13, 15, 728, 350);
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);
        tabbedPane.setBorder(BorderFactory.createEtchedBorder());
        tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
        tabbedPane.setBackground(new Color(255, 255, 255));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addTab("ALI", getALI());
        tabbedPane.addTab("AIE", getAIE());
        tabbedPane.addTab("EE", getEE());
        tabbedPane.addTab("CE", getCE());
        tabbedPane.addTab("SE", getSE());
        tabbedPane.addTab("NI", getNI());
        tabbedPane.addTab("RES", getResultado());
        tabbedPane.addTab("PRJ", getPrj());
        tabPanels = tabbedPane;
        return tabbedPane;
    }

    private JPanel getALI() {
        JPanel panelALI = getJPanel("Arquivo Lógico Interno");

        panelALI.add(getTitulo("Arquivo Lógico Interno", 50, 30, 230));

        btDescricaoALI = getJButton("?", 275, 27, 35, 26, "Descrição do arquivo lógico interno");
        panelALI.add(btDescricaoALI);

        JPanel panel = getJPanelInterno();

        panel.add(getJLabel("Tipos de Dados (TD)", 40, 20, 120, 20));

        spTipoDadosALI = getJSpinner(40, 43, 225, 30);
        panel.add(spTipoDadosALI);

        panel.add(getJLabel("Tipos de Registros (TR)", 40, 77, 160, 20));

        spTipoRegistroALI = getJSpinner(40, 100, 225, 30);
        panel.add(spTipoRegistroALI);

        panel.add(getJLabel("Descrição", 40, 134, 60, 20));

        tfDescricaoALI = getJTextField(40, 157, 176, 30);
        panel.add(tfDescricaoALI);

        btAddALI = getJButton("Add", 220, 158, 49, 28, "Adicionar");
        panel.add(btAddALI);

        btCalcularALI = getJButton("Calcular", 105, 195, 74, 28, "Calcular");
        panel.add(btCalcularALI);

        panelALI.add(panel);

        btCancelarALI = getJButton("Cancelar", 190, 195, 78, 28, "Cancelar");
        panel.add(btCancelarALI);

        final String[] colunas = {"", "     < 20", "   20 - 50", "     > 50"};
        final String[][] linhas = {
            {"1", "Baixa", "Baixa", "Média"},
            {"2 - 5", "Baixa", "Média", "Alta"},
            {"> 5", "Média", "Alta", "Alta"}
        };
        panelALI.add(getTable(new TableModel(linhas, colunas, 3, 4), 350, 30, 290, 76));

        taResultadoALI = new JTextArea();
        taResultadoALI.setEditable(false);
        panelALI.add(getJScrollPane(taResultadoALI));

        return panelALI;
    }

    private JPanel getAIE() {
        JPanel panelAIE = getJPanel("Arquivo de Interface Externa");

        panelAIE.add(getTitulo("Arquivo de Interface Externa", 20, 30, 270));

        btDescricaoAIE = getJButton("?", 290, 27, 35, 26, "Descrição do arquivo de interface externa");
        panelAIE.add(btDescricaoAIE);

        JPanel panel = getJPanelInterno();

        panel.add(getJLabel("Tipos de Dados (TD)", 40, 20, 120, 20));

        spTipoDadosAIE = getJSpinner(40, 43, 225, 30);
        panel.add(spTipoDadosAIE);

        panel.add(getJLabel("Tipos de Registros (TR)", 40, 77, 160, 20));

        spTipoRegistroAIE = getJSpinner(40, 100, 225, 30);
        panel.add(spTipoRegistroAIE);

        panel.add(getJLabel("Descrição", 40, 134, 60, 20));

        tfDescricaoAIE = getJTextField(40, 157, 176, 30);
        panel.add(tfDescricaoAIE);

        btAddAIE = getJButton("Add", 220, 158, 49, 28, "Adicionar");
        panel.add(btAddAIE);

        btCalcularAIE = getJButton("Calcular", 105, 195, 74, 28, "Calcular");
        panel.add(btCalcularAIE);

        btCancelarAIE = getJButton("Cancelar", 190, 195, 78, 28, "Cancelar");
        panel.add(btCancelarAIE);

        panelAIE.add(panel);

        final String[] colunas = {"", "     < 20", "   20 - 50", "     > 50"};
        final String[][] linhas = {
            {"1", "Baixa", "Baixa", "Média"},
            {"2 - 5", "Baixa", "Média", "Alta"},
            {"> 5", "Média", "Alta", "Alta"}
        };
        panelAIE.add(getTable(new TableModel(linhas, colunas, 3, 4), 350, 30, 290, 76));

        taResultadoAIE = new JTextArea();
        taResultadoAIE.setEditable(false);
        panelAIE.add(getJScrollPane(taResultadoAIE));

        return panelAIE;
    }

    private JPanel getEE() {
        JPanel panelEE = getJPanel("Entrada Externa");

        panelEE.add(getTitulo("Entrada Externa", 85, 30, 150));

        btDescricaoEE = getJButton("?", 250, 27, 35, 26, "Descrição da entrada externa");
        panelEE.add(btDescricaoEE);

        JPanel panel = getJPanelInterno();

        panel.add(getJLabel("Tipos de Dados (TD)", 40, 20, 120, 20));

        spTipoDadosEE = getJSpinner(40, 43, 225, 30);
        panel.add(spTipoDadosEE);

        panel.add(getJLabel("Arquivos Referenciados (AR)", 40, 77, 160, 20));

        spArquivosReferenciadosEE = getJSpinner(40, 100, 225, 30);
        panel.add(spArquivosReferenciadosEE);

        panel.add(getJLabel("Descrição", 40, 134, 60, 20));

        tfDescricaoEE = getJTextField(40, 157, 176, 30);
        panel.add(tfDescricaoEE);

        btAddEE = getJButton("Add", 220, 158, 49, 28, "Adicionar");
        panel.add(btAddEE);

        btCalcularEE = getJButton("Calcular", 105, 195, 74, 28, "Calcular");
        panel.add(btCalcularEE);

        btCancelarEE = getJButton("Cancelar", 190, 195, 78, 28, "Cancelar");
        panel.add(btCancelarEE);

        panelEE.add(panel);

        final String[] colunas = {"", "      < 5", "    5 - 15", "      > 15"};
        final String[][] linhas = {
            {"< 2", "Baixa", "Baixa", "Média"},
            {"2", "Baixa", "Média", "Alta"},
            {"> 2", "Média", "Alta", "Alta"}
        };
        panelEE.add(getTable(new TableModel(linhas, colunas, 3, 4), 350, 30, 290, 76));

        taResultadoEE = new JTextArea();
        taResultadoEE.setEditable(false);
        panelEE.add(getJScrollPane(taResultadoEE));

        return panelEE;
    }

    private JPanel getCE() {
        JPanel panelCE = getJPanel("Consulta Externa");

        panelCE.add(getTitulo("Consulta Externa", 75, 30, 170));

        btDescricaoCE = getJButton("?", 250, 27, 35, 26, "Descrição da consulta externa");
        panelCE.add(btDescricaoCE);

        JPanel panel = getJPanelInterno();

        panel.add(getJLabel("Tipos de Dados (TD)", 40, 20, 120, 20));

        spTipoDadosCE = getJSpinner(40, 43, 225, 30);
        panel.add(spTipoDadosCE);

        panel.add(getJLabel("Arquivos Referenciados (AR)", 40, 77, 160, 20));

        spArquivosReferenciadosCE = getJSpinner(40, 100, 225, 30);
        panel.add(spArquivosReferenciadosCE);

        panel.add(getJLabel("Descrição", 40, 134, 60, 20));

        tfDescricaoCE = getJTextField(40, 157, 176, 30);
        panel.add(tfDescricaoCE);

        btAddCE = getJButton("Add", 220, 158, 49, 28, "Adicionar");
        panel.add(btAddCE);

        btCalcularCE = getJButton("Calcular", 105, 195, 74, 28, "Calcular");
        panel.add(btCalcularCE);

        btCancelarCE = getJButton("Cancelar", 190, 195, 78, 28, "Cancelar");
        panel.add(btCancelarCE);

        panelCE.add(panel);

        final String[] colunas = {"", "      < 6", "    6 - 19", "      > 19"};
        final String[][] linhas = {
            {"< 2", "Baixa", "Baixa", "Média"},
            {"2 - 3", "Baixa", "Média", "Alta"},
            {"> 3", "Média", "Alta", "Alta"}
        };
        panelCE.add(getTable(new TableModel(linhas, colunas, 3, 4), 350, 30, 290, 76));

        taResultadoCE = new JTextArea();
        taResultadoCE.setEditable(false);
        panelCE.add(getJScrollPane(taResultadoCE));

        return panelCE;
    }

    private JPanel getSE() {
        JPanel panelSE = getJPanel("Saída Externa");

        panelSE.add(getTitulo("Saída Externa", 85, 30, 170));

        btDescricaoSE = getJButton("?", 250, 27, 35, 26, "Descrição da saída externa");
        panelSE.add(btDescricaoSE);

        JPanel panel = getJPanelInterno();

        panel.add(getJLabel("Tipos de Dados (TD)", 40, 20, 120, 20));

        spTipoDadosSE = getJSpinner(40, 43, 225, 30);
        panel.add(spTipoDadosSE);

        panel.add(getJLabel("Arquivos Referenciados (AR)", 40, 77, 160, 20));

        spArquivosReferenciadosSE = getJSpinner(40, 100, 225, 30);
        panel.add(spArquivosReferenciadosSE);

        panel.add(getJLabel("Descrição", 40, 134, 60, 20));

        tfDescricaoSE = getJTextField(40, 157, 176, 30);
        panel.add(tfDescricaoSE);

        btAddSE = getJButton("Add", 220, 158, 49, 28, "Adicionar");
        panel.add(btAddSE);

        btCalcularSE = getJButton("Calcular", 105, 195, 74, 28, "Calcular");
        panel.add(btCalcularSE);

        btCancelarSE = getJButton("Cancelar", 190, 195, 78, 28, "Cancelar");
        panel.add(btCancelarSE);

        panelSE.add(panel);

        final String[] colunas = {"", "      < 6", "    6 - 19", "      > 19"};
        final String[][] linhas = {
            {"< 2", "Baixa", "Baixa", "Média"},
            {"2 - 3", "Baixa", "Média", "Alta"},
            {"> 3", "Média", "Alta", "Alta"}
        };
        panelSE.add(getTable(new TableModel(linhas, colunas, 3, 4), 350, 30, 290, 76));

        taResultadoSE = new JTextArea();
        taResultadoSE.setEditable(false);
        panelSE.add(getJScrollPane(taResultadoSE));

        return panelSE;
    }

    private JPanel getNI() {
        JPanel panelNI = getJPanel("Nível de Influência");

        panelNI.add(getTitulo("Nível de Influência", 100, 30, 220));

        btDescricaoNI = getJButton("?", 300, 27, 35, 26, "Descrição do nível de influência");
        panelNI.add(btDescricaoNI);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setPreferredSize(new Dimension(0, 620));

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBounds(10, 70, 430, 260);
        scroll.setBorder(BorderFactory.createTitledBorder(""));

        panel.add(getJLabel("[01] Comunicação de Dados", 20, 10, 160, 20));

        cbComuDados = getJComboBoxInflu(7);
        panel.add(cbComuDados);

        panel.add(getJLabel("[02] Processamento Distribuído", 20, 50, 180, 20));

        cbProsDistr = getJComboBoxInflu(47);
        panel.add(cbProsDistr);

        panel.add(getJLabel("[03] Performance", 20, 90, 180, 20));

        cbPerformance = getJComboBoxInflu(87);
        panel.add(cbPerformance);

        panel.add(getJLabel("[04] Configuração Altamente Utilizada", 20, 130, 210, 20));

        cbConfAltUlt = getJComboBoxInflu(127);
        panel.add(cbConfAltUlt);

        panel.add(getJLabel("[05] Taxa de transações", 20, 170, 210, 20));

        cbTaxTrans = getJComboBoxInflu(167);
        panel.add(cbTaxTrans);

        panel.add(getJLabel("[06] Entrada de dados on-line", 20, 210, 210, 20));

        cbEntDadosOn = getJComboBoxInflu(207);
        panel.add(cbEntDadosOn);

        panel.add(getJLabel("[07] Eficiência do usuário final", 20, 250, 210, 20));

        cbEfUsuFinal = getJComboBoxInflu(247);
        panel.add(cbEfUsuFinal);

        panel.add(getJLabel("[08] Atualização on-line", 20, 290, 210, 20));

        cbAtuaOn = getJComboBoxInflu(287);
        panel.add(cbAtuaOn);

        panel.add(getJLabel("[09] Complexidade de processamento", 20, 330, 210, 20));

        cbComProc = getJComboBoxInflu(327);
        panel.add(cbComProc);

        panel.add(getJLabel("[10] Reutilização", 20, 370, 210, 20));

        cbReutilizacao = getJComboBoxInflu(367);
        panel.add(cbReutilizacao);

        panel.add(getJLabel("[11] Facilidade de Instalação", 20, 410, 210, 20));

        cbFacInstal = getJComboBoxInflu(407);
        panel.add(cbFacInstal);

        panel.add(getJLabel("[12] Facilidade de Operação", 20, 450, 210, 20));

        cbFacOperacao = getJComboBoxInflu(447);
        panel.add(cbFacOperacao);

        panel.add(getJLabel("[13] Múltiplas Localidades", 20, 490, 210, 20));

        cbMultLocal = getJComboBoxInflu(487);
        panel.add(cbMultLocal);

        panel.add(getJLabel("[14] Facilidade de Mudanças", 20, 530, 210, 20));

        cbFacMudancas = getJComboBoxInflu(527);
        panel.add(cbFacMudancas);

        btCalcularNI = getJButton("Calcular", 195, 570, 74, 28, "Calcular");
        panel.add(btCalcularNI);

        btCancelarNI = getJButton("Cancelar", 280, 570, 78, 28, "Cancelar");
        panel.add(btCancelarNI);

        panelNI.add(scroll);

        JTextArea taNivelInfluencia = new JTextArea("Níveis de Influência\n0 - Sem\n1 - Baixa/Mínima\n"
                + "2 - Moderada\n3 - Média\n4 - Significativa\n5 - Grande/Alta");
        taNivelInfluencia.setBounds(450, 27, 190, 125);
        taNivelInfluencia.setEditable(false);
        panelNI.add(taNivelInfluencia);

        taResultadoNI = new JTextArea();
        taResultadoNI.setBounds(450, 160, 190, 165);
        taResultadoNI.setEditable(false);
        panelNI.add(taResultadoNI);

        return panelNI;
    }

    private JPanel getResultado() {
        JPanel panelRes = getJPanel("Resultado Final");

        panelRes.add(getTitulo("Sub Totais", 120, 30, 100));

        JPanel panel = getJPanelInterno();

        panel.add(getJLabel("PFB", 40, 20, 40, 20));

        tfPFB = getJTextField(40, 43, 225, 30);
        tfPFB.setText("ALI + AIE + EE + CE + SE: ");
        tfPFB.setEditable(false);
        panel.add(tfPFB);

        panel.add(getJLabel("FA", 40, 77, 40, 20));

        tfFA = getJTextField(40, 100, 225, 30);
        tfFA.setText("(NI * 0,01) + 0,65: ");
        tfFA.setEditable(false);
        panel.add(tfFA);

        panel.add(getJLabel("PFA", 40, 134, 40, 20));

        tfPFA = getJTextField(40, 157, 225, 30);
        tfPFA.setText("PFB * FA: ");
        tfPFA.setEditable(false);
        panel.add(tfPFA);

        btMostrar = getJButton("Mostrar", 195, 195, 70, 28, "Mostrar");
        panel.add(btMostrar);

        panelRes.add(panel);

        final String[] colunas = {"      Tipo", "     Baixa", "     Média", "      Alta"};
        final String[][] linhas = {
            {"ALI", "7", "10", "15"},
            {"AIE", "5", "7", "10"},
            {"EE", "3", "4", "6"},
            {"SE", "4", "5", "7"},
            {"CE", "3", "4", "6"}
        };
        panelRes.add(getTable(new TableModel(linhas, colunas, 5, 4), 350, 30, 280, 108));

        panelRes.add(getTitulo("Total", 470, 145, 50));

        panelRes.add(getJLabel("Pontos de função (horas)", 355, 170, 140, 20));

        tfPontosFunc = getJTextField(355, 193, 280, 30);
        panelRes.add(tfPontosFunc);

        panelRes.add(getJLabel("Prazo em horas", 355, 227, 140, 20));

        tfPrazo = getJTextField(355, 250, 280, 30);
        tfPrazo.setText("PFA * PF: ");
        tfPrazo.setEditable(false);
        panelRes.add(tfPrazo);

        btOK = getJButton("OK", 515, 288, 45, 28, "Calcular o prazo em horas");
        panelRes.add(btOK);

        btNovo = getJButton("Novo", 570, 288, 65, 28, "Novo Cálculo");
        panelRes.add(btNovo);

        return panelRes;
    }

    private JPanel getPrj() {
        JPanel panelProj = getJPanel("Projeto de melhoria e pós melhoria");

        panelProj.add(getTitulo("Projeto de Melhoria e Pós Melhoria", 110, 30, 320));

        btDescricaoPrj = getJButton("?", 440, 27, 35, 26, "Descrição do projeto de melhoria");
        panelProj.add(btDescricaoPrj);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setPreferredSize(new Dimension(0, 1120));

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBounds(10, 70, 630, 260);
        scroll.setBorder(BorderFactory.createTitledBorder(""));

        panel.add(getJSeparator(JSeparator.VERTICAL, 303, 0, 3, 1118));

        panel.add(getJLabel("Descrição", 20, 17, 60, 20));

        tfDescricaoAdd = getJTextField(20, 38, 140, 30);
        panel.add(tfDescricaoAdd);

        panel.add(getJLabel("Tipo", 170, 17, 60, 20));

        cbTipoAdd = getJComboBoxTipo(170, 38, 100, 30);
        panel.add(cbTipoAdd);

        panel.add(getJLabel("Complexidade", 20, 70, 80, 20));

        cbComplexidadeAdd = getJComboBoxComplexidade(20, 91, 90, 30);
        panel.add(cbComplexidadeAdd);

        btAdd = getJButton("Add", 120, 91, 49, 30, "Adicionar");
        panel.add(btAdd);

        panel.add(getJSeparator(JSeparator.HORIZONTAL, 0, 135, 305, 3));

        panel.add(getJLabel("Arquivos ALI", 20, 145, 80, 20));

        cbArquivosDelALI = getJComboBox(20, 166, 255, 30);
        panel.add(cbArquivosDelALI);

        panel.add(getJLabel("Arquivos AIE", 20, 198, 80, 20));

        cbArquivosDelAIE = getJComboBox(20, 219, 255, 30);
        panel.add(cbArquivosDelAIE);

        panel.add(getJLabel("Entradas EE", 20, 251, 80, 20));

        cbEntradasDelEE = getJComboBox(20, 272, 255, 30);
        panel.add(cbEntradasDelEE);

        panel.add(getJLabel("Consultas CE", 20, 304, 80, 20));

        cbConsultasCE = getJComboBox(20, 325, 255, 30);
        panel.add(cbConsultasCE);

        panel.add(getJLabel("Saídas SE", 20, 357, 80, 20));

        cbSaidasSE = getJComboBox(20, 378, 255, 30);
        panel.add(cbSaidasSE);

        btDel = getJButton("Deletar", 20, 415, 70, 30, "Excluir");
        panel.add(btDel);

        panel.add(getJSeparator(JSeparator.HORIZONTAL, 0, 459, 305, 3));

        panel.add(getJLabel("Arquivos ALI", 20, 469, 80, 20));

        cbArquivosAltALI = getJComboBox(20, 490, 255, 30);
        panel.add(cbArquivosAltALI);

        panel.add(getJLabel("Complexidade", 20, 522, 80, 20));

        cbComplexidadeAltALI = getJComboBoxComplexidade(20, 543, 120, 30);
        panel.add(cbComplexidadeAltALI);

        btAltALI = getJButton("Alterar", 150, 543, 63, 30, "Alterar Complexidade");
        panel.add(btAltALI);

        panel.add(getJSeparator(JSeparator.HORIZONTAL, 0, 587, 305, 3));

        panel.add(getJLabel("Arquivos AIE", 20, 597, 80, 20));

        cbArquivosAltAIE = getJComboBox(20, 618, 255, 30);
        panel.add(cbArquivosAltAIE);

        panel.add(getJLabel("Complexidade", 20, 650, 80, 20));

        cbComplexidadeAltAIE = getJComboBoxComplexidade(20, 671, 120, 30);
        panel.add(cbComplexidadeAltAIE);

        btAltAIE = getJButton("Alterar", 150, 671, 63, 30, "Alterar Complexidade");
        panel.add(btAltAIE);

        panel.add(getJSeparator(JSeparator.HORIZONTAL, 0, 715, 305, 3));

        panel.add(getJLabel("Arquivos EE", 20, 725, 80, 20));

        cbArquivosAltEE = getJComboBox(20, 746, 255, 30);
        panel.add(cbArquivosAltEE);

        panel.add(getJLabel("Complexidade", 20, 778, 80, 20));

        cbComplexidadeAltEE = getJComboBoxComplexidade(20, 799, 120, 30);
        panel.add(cbComplexidadeAltEE);

        btAltEE = getJButton("Alterar", 150, 799, 63, 30, "Alterar Complexidade");
        panel.add(btAltEE);

        panel.add(getJSeparator(JSeparator.HORIZONTAL, 0, 843, 305, 3));

        panel.add(getJLabel("Arquivos CE", 20, 853, 80, 20));

        cbArquivosAltCE = getJComboBox(20, 874, 255, 30);
        panel.add(cbArquivosAltCE);

        panel.add(getJLabel("Complexidade", 20, 906, 80, 20));

        cbComplexidadeAltCE = getJComboBoxComplexidade(20, 927, 120, 30);
        panel.add(cbComplexidadeAltCE);

        btAltCE = getJButton("Alterar", 150, 927, 63, 30, "Alterar Complexidade");
        panel.add(btAltCE);

        panel.add(getJSeparator(JSeparator.HORIZONTAL, 0, 971, 305, 3));

        panel.add(getJLabel("Arquivos SE", 20, 981, 80, 20));

        cbArquivosAltSE = getJComboBox(20, 1002, 255, 30);
        panel.add(cbArquivosAltSE);

        panel.add(getJLabel("Complexidade", 20, 1034, 80, 20));

        cbComplexidadeAltSE = getJComboBoxComplexidade(20, 1055, 120, 30);
        panel.add(cbComplexidadeAltSE);

        btAltSE = getJButton("Alterar", 150, 1055, 63, 30, "Alterar Complexidade");
        panel.add(btAltSE);

        panel.add(getJLabel("CFP", 320, 17, 50, 20));

        tfCFP = getJTextField(320, 38, 100, 30);
        panel.add(tfCFP);

        panel.add(getJLabel("VAFA", 430, 17, 50, 20));

        tfVAFA = getJTextField(430, 38, 100, 30);
        panel.add(tfVAFA);

        panel.add(getJLabel("VAFB", 320, 70, 50, 20));

        tfVAFB = getJTextField(320, 91, 100, 30);
        panel.add(tfVAFB);

        panel.add(getJLabel("UFPB", 430, 70, 50, 20));

        tfUFPB = getJTextField(430, 91, 100, 30);
        panel.add(tfUFPB);

        panel.add(getJLabel("CHGB", 320, 123, 50, 20));

        tfCHGB = getJTextField(320, 144, 90, 30);
        panel.add(tfCHGB);

        btCalcularPRJ = getJButton("Calcular", 415, 144, 74, 30, "Calcular");
        panel.add(btCalcularPRJ);

        btCancelarPRJ = getJButton("Cancelar", 495, 144, 78, 30, "Cancelar");
        panel.add(btCancelarPRJ);

        panel.add(getJSeparator(JSeparator.HORIZONTAL, 303, 188, 282, 3));

        taResultadoPRJ = new JTextArea();
        taResultadoPRJ.setEditable(false);
        JScrollPane scrollRes = new JScrollPane(taResultadoPRJ);
        scrollRes.setBounds(320, 198, 250, 890);
        panel.add(scrollRes);

        panelProj.add(scroll);

        return panelProj;
    }

    private JPanel getJPanel(String toolTipText) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));
        panel.setToolTipText(toolTipText);
        panel.setBorder(BorderFactory.createEtchedBorder());
        return panel;
    }

    private JPanel getJPanelInterno() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(20, 70, 305, 240);
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEtchedBorder());
        return panel;
    }

    private JScrollPane getJScrollPane(JTextArea textArea) {
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(350, 120, 290, 190);
        return scroll;
    }

    private JButton getJButton(String titulo, int x, int y, int j, int z, String toolTipText) {
        JButton button = new JButton(titulo);
        button.setBounds(x, y, j, z);
        button.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
        return button;
    }

    private JScrollPane getTable(AbstractTableModel tableModel, int x, int y, int j, int z) {
        Renderizadora rendener = new Renderizadora();
        JTable tabela = new JTable(tableModel);
        for (int i = 0; i < tabela.getColumnModel().getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(rendener);
        }
        tabela.getTableHeader().setEnabled(false);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(x, y, j, z);
        return scroll;
    }

    private JLabel getTitulo(String titulo, int x, int y, int j) {
        JLabel lbTitulo = new JLabel(titulo);
        lbTitulo.setBounds(x, y, j, 20);
        lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        return lbTitulo;
    }

    private JLabel getJLabel(String titulo, int x, int y, int j, int z) {
        JLabel label = new JLabel(titulo);
        label.setBounds(x, y, j, z);
        return label;
    }

    private JSpinner getJSpinner(int x, int y, int j, int z) {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
        spinner.setBounds(x, y, j, z);
        return spinner;
    }

    private JTextField getJTextField(int x, int y, int j, int z) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, j, z);
        return textField;
    }

    private JComboBox getJComboBoxInflu(int y) {
        JComboBox comboBox = getJComboBox(240, y, 120, 30);
        comboBox.addItem("Sem");
        comboBox.addItem("Baixa/Mínima");
        comboBox.addItem("Moderada");
        comboBox.addItem("Média");
        comboBox.addItem("Significante");
        comboBox.addItem("Grande/Alta");
        return comboBox;
    }

    private JComboBox getJComboBoxTipo(int x, int y, int j, int z) {
        JComboBox comboBox = getJComboBox(x, y, j, z);
        comboBox.addItem("ALI");
        comboBox.addItem("AIE");
        comboBox.addItem("EE");
        comboBox.addItem("CE");
        comboBox.addItem("SE");
        return comboBox;
    }

    private JComboBox getJComboBoxComplexidade(int x, int y, int j, int z) {
        JComboBox comboBox = getJComboBox(x, y, j, z);
        comboBox.addItem("Baixa");
        comboBox.addItem("Média");
        comboBox.addItem("Alta");
        return comboBox;
    }

    private JComboBox getJComboBox(int x, int y, int j, int z) {
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(x, y, j, z);
        comboBox.addItem("Selecione");
        return comboBox;
    }

    private JSeparator getJSeparator(int forma, int x, int y, int j, int z) {
        JSeparator separator = new JSeparator(forma);
        separator.setBounds(x, y, j, z);
        return separator;
    }

    public void habilitaTabProjeto(boolean flag) {
        tabPanels.setEnabledAt(7, flag);
    }
}

package AssistenciaTecRandom;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

public class InterfaceGrafica extends JFrame implements ActionListener, FocusListener, ItemListener {

    private JTextField tfCodigo, tfNumero, tfCliente, tfCidade, tfEstado, tfBairro, tfEndereco, tfAtendente, tfMarca, tfModelo, tfEquipamento, tfPesquisaClie, tfPesquisaAssis, tfCodCliente;
    private JFormattedTextField ftfFone, ftfCelular, ftfCEP, ftfCPF, ftfRG;
    private JTextArea taDescricao, taAnexo;
    private JComboBox cbSolicitante, cbFormaPagto, cbCondPagto, cbTipoServico, cbSetor, cbPesquisarPorClie, cbPesquisarPorAssis, cbSituacao;
    private JTable tabelaClie, tabelaAssis;
    private JButton btOk, btCancela, btSalvar, btPesquisaClie, btPesquisaAssis;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private ClienteControl controlClie;
    private TableModelClie tableModelClie;
    private RenderizadoraClie rendenerClie;
    private AssistenciaControl controlAssis;
    private TableModelAssis tableModelAssis;
    RenderizadoraAssis rendenerAssis;

    public InterfaceGrafica() {
        super("Assistência Técnica");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(10, 10, 425, 230);
        tela.add(painel1);
        painel1.setBorder(BorderFactory.createTitledBorder("Assistência"));
        JPanel painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBounds(11, 240, 423, 400);
        tela.add(painel2);
        painel2.setBorder(BorderFactory.createTitledBorder(""));
        JPanel painel3 = new JPanel();
        painel3.setLayout(null);
        painel3.setBounds(436, 17, 550, 345);
        tela.add(painel3);
        painel3.setBorder(BorderFactory.createTitledBorder(""));
        JPanel painel4 = new JPanel();
        painel4.setLayout(null);
        painel4.setBounds(436, 365, 550, 276);
        tela.add(painel4);
        painel4.setBorder(BorderFactory.createTitledBorder(""));
        controlClie = new ClienteControl();
        tableModelClie = new TableModelClie(controlClie);
        controlAssis = new AssistenciaControl(controlClie);
        tableModelAssis = new TableModelAssis(controlAssis);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 40, 80, 14);
        lbCodigo.setFont(fonte);
        painel1.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 58, 80, 20);
        painel1.add(tfCodigo);
        tfCodigo.setDocument(new CampoNumero());
        tfCodigo.setText(Integer.toString(controlClie.ultimoCadasCod() + 1));
        tfCodigo.addFocusListener(this);

        JLabel lbClinete = new JLabel("Cliente");
        lbClinete.setBounds(110, 40, 80, 14);
        lbClinete.setFont(fonte);
        painel1.add(lbClinete);

        tfCliente = new JTextField();
        tfCliente.setBounds(110, 58, 280, 20);
        painel1.add(tfCliente);
        tfCliente.addFocusListener(this);

        JLabel lbEndereco = new JLabel("Endereco");
        lbEndereco.setBounds(20, 78, 80, 14);
        lbEndereco.setFont(fonte);
        painel1.add(lbEndereco);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(20, 94, 190, 20);
        painel1.add(tfEndereco);
        tfEndereco.addFocusListener(this);

        JLabel lbBairro = new JLabel("Bairro");
        lbBairro.setBounds(220, 78, 80, 14);
        lbBairro.setFont(fonte);
        painel1.add(lbBairro);

        tfBairro = new JTextField();
        tfBairro.setBounds(220, 94, 169, 20);
        painel1.add(tfBairro);
        tfBairro.addFocusListener(this);

        JLabel lbNumero = new JLabel("Número");
        lbNumero.setBounds(20, 114, 80, 14);
        lbNumero.setFont(fonte);
        painel1.add(lbNumero);

        tfNumero = new JTextField();
        tfNumero.setBounds(20, 130, 105, 20);
        painel1.add(tfNumero);
        tfNumero.setDocument(new CampoNumero());
        tfNumero.addFocusListener(this);

        JLabel lbCidade = new JLabel("Cidade");
        lbCidade.setBounds(132, 114, 110, 14);
        lbCidade.setFont(fonte);
        painel1.add(lbCidade);

        tfCidade = new JTextField();
        tfCidade.setBounds(132, 130, 125, 20);
        painel1.add(tfCidade);
        tfCidade.addFocusListener(this);

        JLabel lbEstado = new JLabel("Estado");
        lbEstado.setBounds(265, 114, 80, 14);
        lbEstado.setFont(fonte);
        painel1.add(lbEstado);

        tfEstado = new JTextField();
        tfEstado.setBounds(265, 130, 123, 20);
        painel1.add(tfEstado);
        tfEstado.addFocusListener(this);

        JLabel lbCEP = new JLabel("CEP");
        lbCEP.setBounds(20, 150, 80, 14);
        lbCEP.setFont(fonte);
        painel1.add(lbCEP);

        MaskFormatter mascaraCep = null;
        try {
            mascaraCep = new MaskFormatter("#####-###");
        } catch (ParseException excp) {
        }
        ftfCEP = new JFormattedTextField(mascaraCep);
        ftfCEP.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCEP.setBounds(20, 166, 112, 20);
        painel1.add(ftfCEP);
        ftfCEP.addFocusListener(this);

        JLabel lbFone = new JLabel("Fone");
        lbFone.setBounds(142, 150, 80, 14);
        lbFone.setFont(fonte);
        painel1.add(lbFone);

        MaskFormatter mascaraFone = null;
        try {
            mascaraFone = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfFone = new JFormattedTextField(mascaraFone);
        ftfFone.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfFone.setBounds(142, 166, 115, 20);
        painel1.add(ftfFone);
        ftfFone.addFocusListener(this);

        JLabel lbCelular = new JLabel("Celular");
        lbCelular.setBounds(267, 150, 80, 14);
        lbCelular.setFont(fonte);
        painel1.add(lbCelular);

        MaskFormatter mascaraCelular = null;
        try {
            mascaraCelular = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfCelular = new JFormattedTextField(mascaraCelular);
        ftfCelular.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCelular.setBounds(267, 166, 121, 20);
        painel1.add(ftfCelular);
        ftfCelular.addFocusListener(this);

        JLabel lbSolicitante = new JLabel("Solicitante");
        lbSolicitante.setBounds(20, 185, 80, 14);
        lbSolicitante.setFont(fonte);
        painel1.add(lbSolicitante);

        cbSolicitante = new JComboBox();
        cbSolicitante.addItem("Pessoa Física");
        cbSolicitante.addItem("Pessoa Jurídica");
        cbSolicitante.setBackground(cor);
        cbSolicitante.setFont(fonte);
        cbSolicitante.setBounds(20, 200, 115, 20);
        painel1.add(cbSolicitante);
        cbSolicitante.addFocusListener(this);

        JLabel lbRG = new JLabel("RG");
        lbRG.setBounds(142, 185, 80, 14);
        lbRG.setFont(fonte);
        painel1.add(lbRG);

        MaskFormatter mascaraRG = null;
        try {
            mascaraRG = new MaskFormatter("###.###.###");
        } catch (ParseException excp) {
        }
        ftfRG = new JFormattedTextField(mascaraRG);
        ftfRG.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfRG.setBounds(142, 200, 110, 20);
        painel1.add(ftfRG);
        ftfRG.addFocusListener(this);

        JLabel lbCPF = new JLabel("CPF");
        lbCPF.setBounds(267, 185, 80, 14);
        lbCPF.setFont(fonte);
        painel1.add(lbCPF);

        //Este faz com que so pode digitar numeros e o campo ja vem com as separações
        MaskFormatter mascaraCPF = null;
        try {
            mascaraCPF = new MaskFormatter("###.###.###-##");
        } catch (ParseException excp) {
        }
        ftfCPF = new JFormattedTextField(mascaraCPF);
        ftfCPF.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCPF.setBounds(267, 200, 110, 20);
        painel1.add(ftfCPF);
        ftfCPF.addFocusListener(this);

        JLabel lbAtendente = new JLabel("Atendente");
        lbAtendente.setBounds(20, 4, 80, 14);
        lbAtendente.setFont(fonte);
        painel2.add(lbAtendente);

        tfAtendente = new JTextField();
        tfAtendente.setBounds(20, 20, 180, 20);
        painel2.add(tfAtendente);
        tfAtendente.setDocument(new LimiteCampo(40));
        tfAtendente.addFocusListener(this);

        JLabel lbTipoServico = new JLabel("Tipo Serviço");
        lbTipoServico.setBounds(210, 4, 80, 14);
        lbTipoServico.setFont(fonte);
        painel2.add(lbTipoServico);

        cbTipoServico = new JComboBox();
        cbTipoServico.addItem("Assistência sem Garantia");
        cbTipoServico.addItem("Assistência com Garantia");
        cbTipoServico.setBackground(cor);
        cbTipoServico.setFont(fonte);
        cbTipoServico.setBounds(210, 20, 175, 20);
        painel2.add(cbTipoServico);
        cbTipoServico.addFocusListener(this);

        JLabel lbEquipamento = new JLabel("Equipamento");
        lbEquipamento.setBounds(20, 40, 80, 14);
        lbEquipamento.setFont(fonte);
        painel2.add(lbEquipamento);

        tfEquipamento = new JTextField();
        tfEquipamento.setBounds(20, 56, 105, 20);
        painel2.add(tfEquipamento);
        tfEquipamento.setDocument(new LimiteCampo(25));
        tfEquipamento.addFocusListener(this);

        JLabel lbMarca = new JLabel("Marca");
        lbMarca.setBounds(132, 40, 110, 14);
        lbMarca.setFont(fonte);
        painel2.add(lbMarca);

        tfMarca = new JTextField();
        tfMarca.setBounds(132, 56, 125, 20);
        painel2.add(tfMarca);
        tfMarca.setDocument(new LimiteCampo(25));
        tfMarca.addFocusListener(this);

        JLabel lbModelo = new JLabel("Modelo");
        lbModelo.setBounds(265, 40, 80, 14);
        lbModelo.setFont(fonte);
        painel2.add(lbModelo);

        tfModelo = new JTextField();
        tfModelo.setBounds(265, 56, 123, 20);
        painel2.add(tfModelo);
        tfModelo.setDocument(new LimiteCampo(25));
        tfModelo.addFocusListener(this);

        JLabel lbSetor = new JLabel("Setor");
        lbSetor.setBounds(20, 76, 110, 14);
        lbSetor.setFont(fonte);
        painel2.add(lbSetor);

        cbSetor = new JComboBox();
        cbSetor.addItem("Selecione");
        cbSetor.addItem("Informática");
        cbSetor.addItem("Eletônica");
        cbSetor.setBackground(cor);
        cbSetor.setFont(fonte);
        cbSetor.setBounds(20, 92, 105, 20);
        painel2.add(cbSetor);
        cbSetor.addFocusListener(this);

        JLabel lbCodCliente = new JLabel("Cod. Cliente");
        lbCodCliente.setBounds(133, 76, 80, 14);
        lbCodCliente.setFont(fonte);
        painel2.add(lbCodCliente);

        tfCodCliente = new JTextField();
        tfCodCliente.setBounds(133, 92, 105, 20);
        painel2.add(tfCodCliente);
        tfCodCliente.setDocument(new CampoNumero());
        tfCodCliente.addFocusListener(this);

        btSalvar = new JButton("Salvar");
        btSalvar.setBounds(260, 88, 90, 26);
        painel2.add(btSalvar);
        btSalvar.addActionListener(this);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(20, 112, 110, 14);
        lbDescricao.setFont(fonte);
        painel2.add(lbDescricao);

        taDescricao = new JTextArea();
        taDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 0));//Borda em volta no JTextArea
        taDescricao.setDocument(new LimiteCampo(250));
        taDescricao.addFocusListener(this);
        JScrollPane scrollDesc = new JScrollPane(taDescricao);
        scrollDesc.setBounds(20, 128, 370, 140);
        painel2.add(scrollDesc);

        JLabel lbAnexo = new JLabel("Anexo");
        lbAnexo.setBounds(20, 270, 110, 14);
        lbAnexo.setFont(fonte);
        painel2.add(lbAnexo);

        taAnexo = new JTextArea();
        taAnexo.setBorder(BorderFactory.createLineBorder(Color.darkGray, 0));//Borda em volta no JTextArea
        taAnexo.setDocument(new LimiteCampo(250));
        taAnexo.addFocusListener(this);
        JScrollPane scrollAnexo = new JScrollPane(taAnexo);
        scrollAnexo.setBounds(20, 286, 370, 100);
        painel2.add(scrollAnexo);

        rbNovo = new JRadioButton("Novo");
        rbNovo.setBounds(20, 20, 80, 20);
        rbNovo.setFont(fonte);
        painel3.add(rbNovo);
        rbNovo.addItemListener(this);

        rbAlterar = new JRadioButton("Alterar");
        rbAlterar.setBounds(20, 41, 80, 20);
        rbAlterar.setFont(fonte);
        painel3.add(rbAlterar);
        rbAlterar.addItemListener(this);

        rbExcluir = new JRadioButton("Excluir");
        rbExcluir.setBounds(20, 62, 80, 20);
        rbExcluir.setFont(fonte);
        painel3.add(rbExcluir);
        rbExcluir.addItemListener(this);

        btOk = new JButton("OK");
        btOk.setBounds(108, 22, 90, 26);
        painel3.add(btOk);
        btOk.addActionListener(this);

        btCancela = new JButton("Cancelar");
        btCancela.setBounds(108, 62, 90, 26);
        painel3.add(btCancela);
        btCancela.addActionListener(this);

        JLabel lbPesquisarPorClie = new JLabel("Pesquisar Por");
        lbPesquisarPorClie.setBounds(20, 96, 110, 14);
        lbPesquisarPorClie.setFont(fonte);
        painel3.add(lbPesquisarPorClie);

        cbPesquisarPorClie = new JComboBox();
        cbPesquisarPorClie.addItem("Selecione");
        cbPesquisarPorClie.addItem("Codigo");
        cbPesquisarPorClie.addItem("Nome");
        cbPesquisarPorClie.setBackground(cor);
        cbPesquisarPorClie.setFont(fonte);
        cbPesquisarPorClie.setBounds(20, 112, 105, 20);
        painel3.add(cbPesquisarPorClie);
        cbPesquisarPorClie.addFocusListener(this);

        JLabel lbPesquisaClie = new JLabel("Pesquisa");
        lbPesquisaClie.setBounds(140, 96, 110, 14);
        lbPesquisaClie.setFont(fonte);
        painel3.add(lbPesquisaClie);

        tfPesquisaClie = new JTextField();
        tfPesquisaClie.setBounds(140, 112, 210, 20);
        painel3.add(tfPesquisaClie);
        tfPesquisaClie.addFocusListener(this);
        tfPesquisaClie.addActionListener(this);

        btPesquisaClie = new JButton("Pesquisar");
        btPesquisaClie.setBounds(365, 107, 92, 26);
        painel3.add(btPesquisaClie);
        btPesquisaClie.addActionListener(this);

        rendenerClie = new RenderizadoraClie(tfPesquisaClie.getText(), (String) cbPesquisarPorClie.getSelectedItem());
        tabelaClie = new JTable(tableModelClie);
        for (int x = 0; x < tabelaClie.getColumnModel().getColumnCount(); x++) {
            tabelaClie.getColumnModel().getColumn(x).setCellRenderer(rendenerClie);
        }
        tabelaClie.getColumnModel().getColumn(0).setMinWidth(50);
        tabelaClie.getColumnModel().getColumn(1).setMinWidth(200);
        tabelaClie.getColumnModel().getColumn(2).setMinWidth(150);
        tabelaClie.getColumnModel().getColumn(3).setMinWidth(145);
        tabelaClie.getColumnModel().getColumn(4).setMinWidth(60);
        tabelaClie.getColumnModel().getColumn(5).setMinWidth(130);
        tabelaClie.getColumnModel().getColumn(6).setMinWidth(130);
        tabelaClie.getColumnModel().getColumn(7).setMinWidth(80);
        tabelaClie.getColumnModel().getColumn(8).setMinWidth(95);
        tabelaClie.getColumnModel().getColumn(9).setMinWidth(95);
        tabelaClie.getColumnModel().getColumn(10).setMinWidth(150);
        tabelaClie.getColumnModel().getColumn(11).setMinWidth(80);
        tabelaClie.getColumnModel().getColumn(12).setMinWidth(100);
        tabelaClie.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollClie = new JScrollPane(tabelaClie);
        scrollClie.setBounds(20, 140, 520, 200);
        painel3.add(scrollClie);

        JLabel lbPesquisarPorAssis = new JLabel("Pesquisar Por");
        lbPesquisarPorAssis.setBounds(20, 5, 110, 14);
        lbPesquisarPorAssis.setFont(fonte);
        painel4.add(lbPesquisarPorAssis);

        cbPesquisarPorAssis = new JComboBox();
        cbPesquisarPorAssis.addItem("Selecione");
        cbPesquisarPorAssis.addItem("Codigo");
        cbPesquisarPorAssis.addItem("Cod. Clie");
        cbPesquisarPorAssis.setBackground(cor);
        cbPesquisarPorAssis.setFont(fonte);
        cbPesquisarPorAssis.setBounds(20, 23, 105, 20);
        painel4.add(cbPesquisarPorAssis);
        cbPesquisarPorAssis.addFocusListener(this);

        JLabel lbPesquisaAssis = new JLabel("Pesquisa");
        lbPesquisaAssis.setBounds(140, 5, 110, 14);
        lbPesquisaAssis.setFont(fonte);
        painel4.add(lbPesquisaAssis);

        tfPesquisaAssis = new JTextField();
        tfPesquisaAssis.setBounds(140, 23, 210, 20);
        painel4.add(tfPesquisaAssis);
        tfPesquisaAssis.addFocusListener(this);
        tfPesquisaAssis.addActionListener(this);

        btPesquisaAssis = new JButton("Pesquisar");
        btPesquisaAssis.setBounds(365, 18, 92, 26);
        painel4.add(btPesquisaAssis);
        btPesquisaAssis.addActionListener(this);

        cbCondPagto = new JComboBox();
        cbCondPagto.addItem("1X");
        cbCondPagto.addItem("2X");
        cbCondPagto.addItem("3X");
        cbCondPagto.addItem("4X");
        cbCondPagto.addItem("5X");
        cbCondPagto.addItem("6X");
        cbCondPagto.addItem("7X");
        cbCondPagto.addItem("8X");

        cbFormaPagto = new JComboBox();
        cbFormaPagto.addItem("Dinheiro");
        cbFormaPagto.addItem("Cheque");
        cbFormaPagto.addItem("Cartão");

        cbSituacao = new JComboBox();
        cbSituacao.addItem("Em Aberto");
        cbSituacao.addItem("Assumir");
        cbSituacao.addItem("Encerar");

        rendenerAssis = new RenderizadoraAssis(tfPesquisaAssis.getText(), (String) cbPesquisarPorAssis.getSelectedItem());
        tabelaAssis = new JTable(tableModelAssis);
        for (int x = 0; x < tabelaAssis.getColumnModel().getColumnCount(); x++) {
            tabelaAssis.getColumnModel().getColumn(x).setCellRenderer(rendenerAssis);
        }
        tabelaAssis.getColumnModel().getColumn(11).setCellEditor(new EditorOrcamento());
        tabelaAssis.getColumnModel().getColumn(17).setCellEditor(new DefaultCellEditor(cbCondPagto));
        tabelaAssis.getColumnModel().getColumn(18).setCellEditor(new DefaultCellEditor(cbFormaPagto));
        tabelaAssis.getColumnModel().getColumn(19).setCellEditor(new DefaultCellEditor(cbSituacao));
        tabelaAssis.getColumnModel().getColumn(20).setCellEditor(new EditorSalvar());
        tabelaAssis.getColumnModel().getColumn(0).setMinWidth(50);
        tabelaAssis.getColumnModel().getColumn(1).setMinWidth(50);
        tabelaAssis.getColumnModel().getColumn(2).setMinWidth(210);
        tabelaAssis.getColumnModel().getColumn(3).setMinWidth(210);
        tabelaAssis.getColumnModel().getColumn(4).setMinWidth(180);
        tabelaAssis.getColumnModel().getColumn(5).setMinWidth(180);
        tabelaAssis.getColumnModel().getColumn(6).setMinWidth(100);
        tabelaAssis.getColumnModel().getColumn(7).setMinWidth(100);
        tabelaAssis.getColumnModel().getColumn(8).setMinWidth(100);
        tabelaAssis.getColumnModel().getColumn(9).setMinWidth(800);
        tabelaAssis.getColumnModel().getColumn(10).setMinWidth(800);
        tabelaAssis.getColumnModel().getColumn(11).setMinWidth(90);
        tabelaAssis.getColumnModel().getColumn(12).setMinWidth(600);
        tabelaAssis.getColumnModel().getColumn(13).setMinWidth(150);
        tabelaAssis.getColumnModel().getColumn(14).setMinWidth(110);
        tabelaAssis.getColumnModel().getColumn(15).setMinWidth(110);
        tabelaAssis.getColumnModel().getColumn(16).setMinWidth(110);
        tabelaAssis.getColumnModel().getColumn(17).setMinWidth(120);
        tabelaAssis.getColumnModel().getColumn(18).setMinWidth(120);
        tabelaAssis.getColumnModel().getColumn(19).setMinWidth(120);
        tabelaAssis.getColumnModel().getColumn(20).setMinWidth(120);
        tabelaAssis.setRowHeight(26);
        tabelaAssis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollAssis = new JScrollPane(tabelaAssis);
        scrollAssis.setBounds(20, 50, 520, 216);
        painel4.add(scrollAssis);

        addWindowListener(new WindowListener() {

            public void windowClosing(WindowEvent e) {
                controlAssis.fecharArquivo();
            }

            public void windowOpened(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }
        });

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        painel2.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    private void okClie() throws Exception {
        if (controlClie.vazio() == true) {
            try {
                gravarClie();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            boolean encontrado = false;
            if (controlClie.clienteCadasCod(Integer.parseInt(tfCodigo.getText())) == true) {
                encontrado = true;
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "O cliente com o codigo " + tfCodigo.getText() + " ja esta cadastrado deseja Substituilo? ", "Cliente", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    controlClie.removerCod(Integer.parseInt(tfCodigo.getText()));
                    try {
                        gravarClie();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                try {
                    gravarClie();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void gravarClie() throws Exception {
        if ("".equals(tfCodigo.getText()) || Integer.parseInt(tfCodigo.getText()) <= 0) {
            tfCodigo.grabFocus();
            throw new Exception("Campo codigo inválido");
        } else {
            if ("".equals(tfCliente.getText())) {
                tfCliente.grabFocus();
                throw new Exception("Campo cliente inválido");
            } else {
                if ("".equals(tfEndereco.getText())) {
                    tfEndereco.grabFocus();
                    throw new Exception("Campo endereço inválido");
                } else {
                    if ("".equals(tfBairro.getText())) {
                        tfBairro.grabFocus();
                        throw new Exception("Campo bairro inválido");
                    } else {
                        if ("".equals(tfNumero.getText()) || Integer.parseInt(tfNumero.getText()) <= 0) {
                            tfNumero.grabFocus();
                            throw new Exception("Campo número inválido");
                        } else {
                            if ("".equals(tfCidade.getText())) {
                                tfCidade.grabFocus();
                                throw new Exception("Campo cidade inválido");
                            } else {
                                if ("".equals(tfEstado.getText())) {
                                    tfEstado.grabFocus();
                                    throw new Exception("Campo estado inválido");
                                } else {
                                    if ("     -   ".equals(ftfCEP.getText())) {
                                        ftfCEP.grabFocus();
                                        throw new Exception("Campo cep inválido");
                                    } else {
                                        if ("(  )    -    ".equals(ftfFone.getText())) {
                                            ftfFone.grabFocus();
                                            throw new Exception("Campo fone inválido");
                                        } else {
                                            if ("(  )    -    ".equals(ftfCelular.getText())) {
                                                ftfCelular.grabFocus();
                                                throw new Exception("Campo celular inválido");
                                            } else {
                                                if ("   .   .   ".equals(ftfRG.getText())) {
                                                    ftfRG.grabFocus();
                                                    throw new Exception("Campo rg inválido");
                                                } else {
                                                    if ("   .   .   -  ".equals(ftfCPF.getText())) {
                                                        ftfCPF.grabFocus();
                                                        throw new Exception("Campo cpf inválido");
                                                    } else {
                                                        controlClie.adicionar(Integer.parseInt(tfCodigo.getText()), Integer.parseInt(tfNumero.getText()), tfCliente.getText(), tfEndereco.getText(), tfBairro.getText(), tfCidade.getText(), tfEstado.getText(), ftfCEP.getText(), ftfFone.getText(), ftfCelular.getText(), (String) cbSolicitante.getSelectedItem(), ftfRG.getText(), ftfCPF.getText());
                                                        JOptionPane.showMessageDialog(null, "Clientes cadastrado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE);
                                                        tableModelClie.fireTableDataChanged();
                                                        limparTelaClie();
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

    private void recuperarClie() throws Exception {
        if (controlClie.vazio() == true) {
            throw new Exception("Não há clientes cadastrados");
        } else {
            boolean erro;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Insira codigo: ", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (controlClie.recuperar(codigo) == true) {
                            tfCodigo.setText(Integer.toString(controlClie.getCodigo()));
                            tfNumero.setText(Integer.toString(controlClie.getNumero()));
                            tfCliente.setText(controlClie.getNome());
                            tfEndereco.setText(controlClie.getEndereco());
                            tfBairro.setText(controlClie.getBairro());
                            tfCidade.setText(controlClie.getCidade());
                            tfEstado.setText(controlClie.getEstado());
                            ftfCEP.setText(controlClie.getCep());
                            ftfFone.setText(controlClie.getFone());
                            ftfCelular.setText(controlClie.getCelular());
                            cbSolicitante.setSelectedItem(controlClie.getSolicitante());
                            ftfRG.setText(controlClie.getRg());
                            ftfCPF.setText(controlClie.getCpf());
                            JOptionPane.showMessageDialog(null, "Cliente encontrado e recuperado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
                        }
                        erro = false;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Entre com um numero válido", "Erro", JOptionPane.ERROR_MESSAGE);
                        erro = true;
                    }
                }
            } while (erro);
        }
    }

    private void excluirClie() throws Exception {
        if (controlClie.vazio() == true) {
            throw new Exception("Não há clientes cadastrados");
        } else {
            boolean erro = false;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Informe o codigo do cliente a ser excluído:", "Saída de dados", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (controlClie.clienteCadasCod(codigo) == true) {
                            controlClie.removerCod(codigo);
                            JOptionPane.showMessageDialog(null, "Cliente removido com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                            limparTelaClie();
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                        }
                        erro = false;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Entre com um numero válido", "Erro", JOptionPane.ERROR_MESSAGE);
                        erro = true;
                    }
                }
            } while (erro);
        }
    }

    private void limparTelaClie() {
        tfCodigo.setText(Integer.toString(controlClie.ultimoCadasCod() + 1));
        tfCliente.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfCidade.setText("");
        tfEstado.setText("");
        ftfCEP.setText("");
        ftfFone.setText("");
        ftfCelular.setText("");
        cbSolicitante.setSelectedItem("Pessoa Física");
        ftfRG.setText("");
        ftfCPF.setText("");
        cbPesquisarPorClie.setSelectedItem("Selecione");
        tfPesquisaClie.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void codigoClie() {
        if ("".equals(tfCodigo.getText())) {
            limparTelaClie();
        } else {
            if (controlClie.vazio() == true) {
            } else {
                int n = Integer.parseInt(tfCodigo.getText());
                boolean encontrado = false;
                if (controlClie.clienteCadasCod(n) == true) {
                    encontrado = true;
                }
                if (encontrado == true) {
                    limparTelaClie();
                }
            }
        }
    }

    private void gravarAssis() throws Exception {
        if ("".equals(tfAtendente.getText())) {
            tfAtendente.grabFocus();
            throw new Exception("Campo atendente inválido");
        } else {
            if ("".equals(tfEquipamento.getText())) {
                tfEquipamento.grabFocus();
                throw new Exception("Campo equipamento inválido");
            } else {
                if ("".equals(tfMarca.getText())) {
                    tfMarca.grabFocus();
                    throw new Exception("Campo marca inválido");
                } else {
                    if ("".equals(tfModelo.getText())) {
                        tfModelo.grabFocus();
                        throw new Exception("Campo modelo inválido");
                    } else {
                        if ("Selecione".equals(cbSetor.getSelectedItem())) {
                            cbSetor.grabFocus();
                            throw new Exception("Campo setor inválido");
                        } else {
                            if ("".equals(tfCodCliente.getText()) || Integer.parseInt(tfCodCliente.getText()) <= 0) {
                                tfCodCliente.grabFocus();
                                throw new Exception("Campo codigo inválido");
                            } else {
                                if ("".equals(taDescricao.getText())) {
                                    taDescricao.grabFocus();
                                    throw new Exception("Campo descrição inválido");
                                } else {
                                    if ("".equals(taAnexo.getText())) {
                                        taAnexo.grabFocus();
                                        throw new Exception("Campo anexo inválido");
                                    } else {
                                        controlAssis.adicionar(Integer.parseInt(tfCodCliente.getText()), tfAtendente.getText(), (String) cbTipoServico.getSelectedItem(), tfEquipamento.getText(), tfMarca.getText(), tfModelo.getText(), (String) cbSetor.getSelectedItem(), taDescricao.getText(), taAnexo.getText());
                                        JOptionPane.showMessageDialog(null, "Assistência cadastrada com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE);
                                        tableModelAssis.fireTableDataChanged();
                                        limparTelaAssis();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void recuperarClieCod() throws Exception {
        if (!"".equals(tfCodCliente.getText())) {
            if (controlClie.vazio() == true) {
                throw new Exception("Não há clientes cadastrados");
            } else {
                if (controlClie.clienteCadasCod(Integer.parseInt(tfCodCliente.getText())) == true) {
                    if (controlClie.recuperar(Integer.parseInt(tfCodCliente.getText())) == true) {
                        tfCodigo.setText(Integer.toString(controlClie.getCodigo()));
                        tfNumero.setText(Integer.toString(controlClie.getNumero()));
                        tfCliente.setText(controlClie.getNome());
                        tfEndereco.setText(controlClie.getEndereco());
                        tfBairro.setText(controlClie.getBairro());
                        tfCidade.setText(controlClie.getCidade());
                        tfEstado.setText(controlClie.getEstado());
                        ftfCEP.setText(controlClie.getCep());
                        ftfFone.setText(controlClie.getFone());
                        ftfCelular.setText(controlClie.getCelular());
                        cbSolicitante.setSelectedItem(controlClie.getSolicitante());
                        ftfRG.setText(controlClie.getRg());
                        ftfCPF.setText(controlClie.getCpf());
                    }
                } else {
                    limparTelaClie();
                    tfCodCliente.grabFocus();
                    tfCodCliente.setText("");
                    throw new Exception("Cliente não cadastrado, tente novamente");
                }
            }
        }
    }

    private void limparTelaAssis() {
        tfAtendente.setText("");
        cbTipoServico.setSelectedItem("Assistência sem Garantia");
        tfEquipamento.setText("");
        tfMarca.setText("");
        tfModelo.setText("");
        cbSetor.setSelectedItem("Selecione");
        tfCodCliente.setText("");
        taDescricao.setText("");
        taAnexo.setText("");
        cbPesquisarPorAssis.setSelectedItem("Selecione");
        tfPesquisaAssis.setText("");
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancela) {
            limparTelaClie();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Cliente", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rbNovo.isSelected() == true) {//Verifica se JRadioButton esta ou não selecionado
                    try {
                        okClie();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbAlterar.isSelected() == true) {
                    try {
                        recuperarClie();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbExcluir.isSelected() == true) {
                    try {
                        excluirClie();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (evento.getSource() == btPesquisaClie || evento.getSource() == tfPesquisaClie) {
            if ("Selecione".equals(cbPesquisarPorClie.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "Campo pesquisar por invalido", "Erro", JOptionPane.ERROR_MESSAGE);
                cbPesquisarPorClie.grabFocus();
            } else {
                if ("".equals(tfPesquisaClie.getText())) {
                    JOptionPane.showMessageDialog(null, "Campo pesquisa invalido", "Erro", JOptionPane.ERROR_MESSAGE);
                    tfPesquisaClie.grabFocus();
                } else {
                    rendenerClie = new RenderizadoraClie(tfPesquisaClie.getText(), (String) cbPesquisarPorClie.getSelectedItem());
                    for (int x = 0; x < tabelaClie.getColumnModel().getColumnCount(); x++) {
                        tabelaClie.getColumnModel().getColumn(x).setCellRenderer(rendenerClie);
                    }
                    tabelaClie.repaint();
                }
            }
        }
        if (evento.getSource() == btPesquisaAssis || evento.getSource() == tfPesquisaAssis) {
            if ("Selecione".equals(cbPesquisarPorAssis.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "Campo pesquisar por invalido", "Erro", JOptionPane.ERROR_MESSAGE);
                cbPesquisarPorAssis.grabFocus();
            } else {
                if ("".equals(tfPesquisaAssis.getText())) {
                    JOptionPane.showMessageDialog(null, "Campo pesquisa invalido", "Erro", JOptionPane.ERROR_MESSAGE);
                    tfPesquisaAssis.grabFocus();
                } else {
                    rendenerAssis = new RenderizadoraAssis(tfPesquisaAssis.getText(), (String) cbPesquisarPorAssis.getSelectedItem());
                    for (int x = 0; x < tabelaAssis.getColumnModel().getColumnCount(); x++) {
                        tabelaAssis.getColumnModel().getColumn(x).setCellRenderer(rendenerAssis);
                    }
                    tabelaAssis.repaint();
                }
            }
        }
        if (evento.getSource() == btSalvar) {
            try {
                gravarAssis();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCliente) {
            tfCliente.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEndereco) {
            tfEndereco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBairro) {
            tfBairro.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumero) {
            tfNumero.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCidade) {
            tfCidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEstado) {
            tfEstado.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCEP) {
            ftfCEP.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFone) {
            ftfFone.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCelular) {
            ftfCelular.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbSolicitante) {
            cbSolicitante.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfRG) {
            ftfRG.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCPF) {
            ftfCPF.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfAtendente) {
            tfAtendente.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbTipoServico) {
            cbTipoServico.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEquipamento) {
            tfEquipamento.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMarca) {
            tfMarca.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfModelo) {
            tfModelo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbSetor) {
            cbSetor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodCliente) {
            tfCodCliente.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == taDescricao) {
            taDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == taAnexo) {
            taAnexo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbPesquisarPorClie) {
            cbPesquisarPorClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPesquisaClie) {
            tfPesquisaClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbPesquisarPorAssis) {
            cbPesquisarPorAssis.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPesquisaAssis) {
            tfPesquisaAssis.setBackground(Color.YELLOW);
        }
    }

    public void focusLost(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.WHITE);
            codigoClie();
        }
        tfCliente.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfEstado.setBackground(Color.WHITE);
        ftfCEP.setBackground(Color.WHITE);
        ftfFone.setBackground(Color.WHITE);
        ftfCelular.setBackground(Color.WHITE);
        cbSolicitante.setBackground(Color.WHITE);
        ftfRG.setBackground(Color.WHITE);
        ftfCPF.setBackground(Color.WHITE);
        tfAtendente.setBackground(Color.WHITE);
        cbTipoServico.setBackground(Color.WHITE);
        tfEquipamento.setBackground(Color.WHITE);
        tfMarca.setBackground(Color.WHITE);
        tfModelo.setBackground(Color.WHITE);
        cbSetor.setBackground(Color.WHITE);
        if (evento.getSource() == tfCodCliente) {
            tfCodCliente.setBackground(Color.WHITE);
            try {
                recuperarClieCod();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        taDescricao.setBackground(Color.WHITE);
        taAnexo.setBackground(Color.WHITE);
        cbPesquisarPorClie.setBackground(Color.WHITE);
        tfPesquisaClie.setBackground(Color.WHITE);
        cbPesquisarPorAssis.setBackground(Color.WHITE);
        tfPesquisaAssis.setBackground(Color.WHITE);
    }

    public void itemStateChanged(ItemEvent evento) {
        if (evento.getSource() == rbNovo) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbAlterar.setSelected(false);
                rbExcluir.setSelected(false);
            }
        }
        if (evento.getSource() == rbAlterar) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbNovo.setSelected(false);
                rbExcluir.setSelected(false);
            }
        }
        if (evento.getSource() == rbExcluir) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbNovo.setSelected(false);
                rbAlterar.setSelected(false);
            }
        }
    }
}

package Fornecedor;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Cidade.CidadeControl;
import DAOFactory.DAOFactory;
import FormatacaoCampos.CamposInt;
import FormatacaoCampos.CriarObjGrafico;
import FormatacaoCampos.VerificaCampos;
import Modelo.Cidade;
import Modelo.Fornecedor;

public class CadasFornecedores extends JDialog implements ActionListener, ItemListener {

    private JTextField tfCodigo, tfUltAlteracao, tfDataCadas, tfNome, tfSigla,
            tfEndereco, tfBairro, tfNumero, tfPais, tfReferencia, tfEmail,
            tfMsn, tfSkype, tfDescricao, tfHomePage, tfEmpresa, tfComissao,
            tfCompraMinima, tfCompraMaxima, tfValorFrete, tfIcms, tfCofins,
            tfIpi, tfJuros, tfNomeFantasia, tfTipoFornecedor, tfBanco,
            tfRegiao, tfDescontos;
    private JFormattedTextField ftfCnpj, ftfInscricaoEstadual, ftfCEP, ftfFone,
            ftfCelular1, ftfCelular2, ftfFax, ftfFoneEmpresa,
            ftfInscricaoMunicipal, ftfCaixaPostal, ftfAgencia, ftfConta,
            ftfDigitoConta;
    private JComboBox cbCidade, cbEstado, cbTipoPessoa, cbContribuinte,
            cbExportador;
    private JTextArea taObservacoes;
    private JButton btVer, btOk, btCancelar;
    private JLabel lbNomeObrig, lbEnderecoObrig, lbBairroObrig, lbNumeroObrig,
            lbCidadeObrig, lbEstadoObrig, lbFoneObrig;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private FornecedorControl controle;

    public CadasFornecedores(DAOFactory daoFactory) {
        controle = new FornecedorControl(daoFactory);
        initComponents(daoFactory);
    }

    private void initComponents(DAOFactory daoFactory) {
        Set<String> itensCombo = new HashSet<String>();
        setTitle("Cadastro de Fornecedor");
        setLayout(null);
        JPanel panel = CriarObjGrafico.criarJPanel(10, 10, 865, 530);
        add(panel);

        panel.add(CriarObjGrafico.criarJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodForn()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        panel.add(tfCodigo);

        btVer = CriarObjGrafico.criarJButton("...", 106, 34, 31, 24);
        btVer.addActionListener(this);
        panel.add(btVer);

        panel.add(CriarObjGrafico.criarJLabel("Cadastrado em", 143, 20, 90, 14));
        tfDataCadas = CriarObjGrafico.criarJTextField(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.setEditable(false);
        tfDataCadas.setBackground(Color.WHITE);
        panel.add(tfDataCadas);

        panel.add(CriarObjGrafico.criarJLabel("Última Alteração", 270, 20, 100, 14));
        tfUltAlteracao = CriarObjGrafico.criarJTextField(270, 38, 120, 20);
        panel.add(tfUltAlteracao);

        panel.add(CriarObjGrafico.criarJLabel("Nome", 398, 20, 80, 14));
        lbNomeObrig = CriarObjGrafico.criarJLabel("", 435, 23, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel.add(lbNomeObrig);
        tfNome = CriarObjGrafico.criarJTextField(398, 38, 325, 20);
        panel.add(tfNome);

        panel.add(CriarObjGrafico.criarJLabel("Sigla", 730, 20, 110, 14));
        tfSigla = CriarObjGrafico.criarJTextField(730, 38, 120, 20);
        panel.add(tfSigla);

        panel.add(CriarObjGrafico.criarJLabel("Nome Fantasia", 20, 60, 110, 14));
        tfNomeFantasia = CriarObjGrafico.criarJTextField(20, 78, 180, 20);
        panel.add(tfNomeFantasia);

        panel.add(CriarObjGrafico.criarJLabel("Inscrição Estadual", 210, 60, 110, 14));
        ftfInscricaoEstadual = CriarObjGrafico.criarJFormattedTextField("###.###.###", 210, 78, 110, 20);
        panel.add(ftfInscricaoEstadual);

        panel.add(CriarObjGrafico.criarJLabel("CNPJ", 330, 60, 90, 14));
        ftfCnpj = CriarObjGrafico.criarJFormattedTextField("###.###.###/####-##", 330, 78, 125, 20);
        panel.add(ftfCnpj);

        panel.add(CriarObjGrafico.criarJLabel("Inscrição Municipal", 465, 60, 110, 14));
        ftfInscricaoMunicipal = CriarObjGrafico.criarJFormattedTextField("##.##.##", 465, 78, 110, 20);
        panel.add(ftfInscricaoMunicipal);

        panel.add(CriarObjGrafico.criarJLabel("Tipo Pessoa", 585, 60, 90, 14));
        itensCombo.add("Física");
        itensCombo.add("Jurídica");
        cbTipoPessoa = CriarObjGrafico.criarJComboBox(itensCombo, 585, 78, 120, 20);
        panel.add(cbTipoPessoa);

        panel.add(CriarObjGrafico.criarJLabel("Contribuinte", 715, 60, 90, 14));
        itensCombo.clear();
        itensCombo.add("Sim");
        itensCombo.add("Não");
        cbContribuinte = CriarObjGrafico.criarJComboBox(itensCombo, 715, 78, 120, 20);
        panel.add(cbContribuinte);

        panel.add(CriarObjGrafico.criarJLabel("Exportador", 20, 100, 90, 14));
        itensCombo.clear();
        itensCombo.add("Sim");
        itensCombo.add("Não");
        cbExportador = CriarObjGrafico.criarJComboBox(itensCombo, 20, 118, 110, 20);
        panel.add(cbExportador);

        panel.add(CriarObjGrafico.criarJLabel("Tipo Fornecedor", 140, 100, 110, 14));
        tfTipoFornecedor = CriarObjGrafico.criarJTextField(140, 118, 200, 20);
        panel.add(tfTipoFornecedor);

        panel.add(CriarObjGrafico.criarJLabel("Empresa", 350, 100, 80, 14));
        tfEmpresa = CriarObjGrafico.criarJTextField(350, 118, 210, 20);
        panel.add(tfEmpresa);

        panel.add(CriarObjGrafico.criarJLabel("Fone Empresa", 570, 100, 90, 14));
        ftfFoneEmpresa = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 570, 118, 120, 20);
        panel.add(ftfFoneEmpresa);

        panel.add(CriarObjGrafico.criarJLabel("Banco", 700, 100, 130, 14));
        tfBanco = CriarObjGrafico.criarJTextField(700, 118, 150, 20);
        panel.add(tfBanco);

        panel.add(CriarObjGrafico.criarJLabel("Agência", 20, 140, 130, 14));
        ftfAgencia = CriarObjGrafico.criarJFormattedTextField("####-#", 20, 158, 100, 20);
        panel.add(ftfAgencia);

        panel.add(CriarObjGrafico.criarJLabel("Conta", 130, 140, 130, 14));
        ftfConta = CriarObjGrafico.criarJFormattedTextField("#######", 130, 158, 120, 20);
        panel.add(ftfConta);

        panel.add(CriarObjGrafico.criarJLabel("Dígito Conta", 260, 140, 130, 14));
        ftfDigitoConta = CriarObjGrafico.criarJFormattedTextField("#", 260, 158, 100, 20);
        panel.add(ftfDigitoConta);

        panel.add(CriarObjGrafico.criarJLabel("Caixa Postal", 370, 140, 100, 14));
        ftfCaixaPostal = CriarObjGrafico.criarJFormattedTextField("######", 370, 158, 120, 20);
        panel.add(ftfCaixaPostal);

        panel.add(CriarObjGrafico.criarJLabel("Endereço", 500, 140, 100, 14));
        lbEnderecoObrig = CriarObjGrafico.criarJLabel("", 560, 143, 10, 14);
        lbEnderecoObrig.setForeground(Color.RED);
        panel.add(lbEnderecoObrig);
        tfEndereco = CriarObjGrafico.criarJTextField(500, 158, 350, 20);
        panel.add(tfEndereco);

        panel.add(CriarObjGrafico.criarJLabel("Bairro", 20, 180, 100, 14));
        lbBairroObrig = CriarObjGrafico.criarJLabel("", 60, 183, 10, 14);
        lbBairroObrig.setForeground(Color.RED);
        panel.add(lbBairroObrig);
        tfBairro = CriarObjGrafico.criarJTextField(20, 198, 200, 20);
        panel.add(tfBairro);

        panel.add(CriarObjGrafico.criarJLabel("Número", 230, 180, 100, 14));
        lbNumeroObrig = CriarObjGrafico.criarJLabel("", 280, 183, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel.add(lbNumeroObrig);
        tfNumero = CriarObjGrafico.criarJTextField(230, 198, 110, 20);
        tfNumero.setDocument(new CamposInt());
        panel.add(tfNumero);

        panel.add(CriarObjGrafico.criarJLabel("Cidade", 350, 180, 90, 14));
        lbCidadeObrig = CriarObjGrafico.criarJLabel("", 395, 183, 10, 14);
        lbCidadeObrig.setForeground(Color.RED);
        panel.add(lbCidadeObrig);
        CidadeControl cidadeControl = new CidadeControl(daoFactory);
        itensCombo.clear();
        List<Cidade> listaCidade = null;
        try {
            listaCidade = cidadeControl.getLista();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        for (Cidade cidade : listaCidade) {
            itensCombo.add(cidade.getCidade());
        }
        cbCidade = CriarObjGrafico.criarJComboBox(itensCombo, 350, 198, 140, 20);
        panel.add(cbCidade);

        panel.add(CriarObjGrafico.criarJLabel("Estado", 500, 180, 90, 14));
        lbEstadoObrig = CriarObjGrafico.criarJLabel("", 545, 183, 10, 14);
        lbEstadoObrig.setForeground(Color.RED);
        panel.add(lbEstadoObrig);
        itensCombo.clear();
        for (Cidade estado : listaCidade) {
            itensCombo.add(estado.getEstado());
        }
        cbEstado = CriarObjGrafico.criarJComboBox(itensCombo, 500, 198, 140, 20);
        panel.add(cbEstado);

        panel.add(CriarObjGrafico.criarJLabel("Região", 650, 180, 100, 14));
        tfRegiao = CriarObjGrafico.criarJTextField(650, 198, 200, 20);
        panel.add(tfRegiao);

        panel.add(CriarObjGrafico.criarJLabel("País", 20, 220, 100, 14));
        tfPais = CriarObjGrafico.criarJTextField(20, 238, 140, 20);
        panel.add(tfPais);

        panel.add(CriarObjGrafico.criarJLabel("CEP", 170, 220, 80, 14));
        ftfCEP = CriarObjGrafico.criarJFormattedTextField("#####-###", 170, 238, 100, 20);
        panel.add(ftfCEP);

        panel.add(CriarObjGrafico.criarJLabel("Referência", 280, 220, 80, 14));
        tfReferencia = CriarObjGrafico.criarJTextField(280, 238, 290, 20);
        panel.add(tfReferencia);

        panel.add(CriarObjGrafico.criarJLabel("Fone", 580, 220, 70, 14));
        lbFoneObrig = CriarObjGrafico.criarJLabel("", 610, 223, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel.add(lbFoneObrig);
        ftfFone = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 580, 238, 125, 20);
        panel.add(ftfFone);

        panel.add(CriarObjGrafico.criarJLabel("Celular 1", 715, 220, 70, 14));
        ftfCelular1 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 715, 238, 125, 20);
        panel.add(ftfCelular1);

        panel.add(CriarObjGrafico.criarJLabel("Celular 2", 20, 260, 70, 14));
        ftfCelular2 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 20, 278, 110, 20);
        panel.add(ftfCelular2);

        panel.add(CriarObjGrafico.criarJLabel("Fax", 140, 260, 60, 14));
        ftfFax = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 140, 278, 120, 20);
        panel.add(ftfFax);

        panel.add(CriarObjGrafico.criarJLabel("E-Mail", 270, 260, 60, 14));
        tfEmail = CriarObjGrafico.criarJTextField(270, 278, 310, 20);
        panel.add(tfEmail);

        panel.add(CriarObjGrafico.criarJLabel("MSN", 590, 260, 60, 14));
        tfMsn = CriarObjGrafico.criarJTextField(590, 278, 260, 20);
        panel.add(tfMsn);

        panel.add(CriarObjGrafico.criarJLabel("Skype", 20, 300, 60, 14));
        tfSkype = CriarObjGrafico.criarJTextField(20, 318, 300, 20);
        panel.add(tfSkype);

        panel.add(CriarObjGrafico.criarJLabel("Descrição", 330, 300, 60, 14));
        tfDescricao = CriarObjGrafico.criarJTextField(330, 318, 520, 20);
        panel.add(tfDescricao);

        panel.add(CriarObjGrafico.criarJLabel("Home Page", 20, 340, 90, 14));
        tfHomePage = CriarObjGrafico.criarJTextField(20, 358, 300, 20);
        panel.add(tfHomePage);

        panel.add(CriarObjGrafico.criarJLabel("Comissão", 330, 340, 80, 14));
        tfComissao = CriarObjGrafico.criarJTextField(330, 358, 120, 20);
        tfComissao.setText("0.00");
        panel.add(tfComissao);
        panel.add(CriarObjGrafico.criarJLabel("%", 455, 361, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("Compra Mímina", 470, 340, 100, 14));
        tfCompraMinima = CriarObjGrafico.criarJTextField(470, 358, 110, 20);
        panel.add(tfCompraMinima);

        panel.add(CriarObjGrafico.criarJLabel("Compra Máxima", 590, 340, 100, 14));
        tfCompraMaxima = CriarObjGrafico.criarJTextField(590, 358, 110, 20);
        panel.add(tfCompraMaxima);

        panel.add(CriarObjGrafico.criarJLabel("Valor Frete", 710, 340, 100, 14));
        tfValorFrete = CriarObjGrafico.criarJTextField(710, 358, 120, 20);
        tfValorFrete.setText("0.00");
        panel.add(tfValorFrete);
        panel.add(CriarObjGrafico.criarJLabel("%", 835, 361, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("ICMS", 20, 380, 100, 14));
        tfIcms = CriarObjGrafico.criarJTextField(20, 398, 110, 20);
        tfIcms.setText("0.00");
        panel.add(tfIcms);
        panel.add(CriarObjGrafico.criarJLabel("%", 132, 401, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("COFINS", 145, 380, 100, 14));
        tfCofins = CriarObjGrafico.criarJTextField(145, 398, 110, 20);
        tfCofins.setText("0.00");
        panel.add(tfCofins);
        panel.add(CriarObjGrafico.criarJLabel("%", 257, 401, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("IPI", 270, 380, 100, 14));
        tfIpi = CriarObjGrafico.criarJTextField(270, 398, 110, 20);
        tfIpi.setText("0.00");
        panel.add(tfIpi);
        panel.add(CriarObjGrafico.criarJLabel("%", 382, 401, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("Juros a.m.", 400, 380, 100, 14));
        tfJuros = CriarObjGrafico.criarJTextField(400, 398, 110, 20);
        tfJuros.setText("0.00");
        panel.add(tfJuros);
        panel.add(CriarObjGrafico.criarJLabel("%", 512, 401, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("Descontos", 530, 380, 100, 14));
        tfDescontos = CriarObjGrafico.criarJTextField(530, 398, 110, 20);
        tfDescontos.setText("0.00");
        panel.add(tfDescontos);
        panel.add(CriarObjGrafico.criarJLabel("%", 645, 401, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("Observações", 20, 420, 130, 14));
        taObservacoes = CriarObjGrafico.criarJTextArea(panel, 20, 438, 640, 75);

        btOk = CriarObjGrafico.criarJButton("OK", 764, 445, 86, 26);
        btOk.addActionListener(this);
        panel.add(btOk);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 764, 475, 86, 26);
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        rbNovo = CriarObjGrafico.criarJRadioButton("Novo", 672, 443, 80, 20);
        rbNovo.addItemListener(this);
        panel.add(rbNovo);

        rbAlterar = CriarObjGrafico.criarJRadioButton("Alterar", 672, 462, 80, 20);
        rbAlterar.addItemListener(this);
        panel.add(rbAlterar);

        rbExcluir = CriarObjGrafico.criarJRadioButton("Excluir", 672, 482, 80, 20);
        rbExcluir.addItemListener(this);
        panel.add(rbExcluir);

        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(900, 580);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodForn()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfNome.setText("");
        tfSigla.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfPais.setText("");
        tfReferencia.setText("");
        tfEmail.setText("");
        tfMsn.setText("");
        tfSkype.setText("");
        tfDescricao.setText("");
        tfHomePage.setText("");
        tfEmpresa.setText("");
        tfComissao.setText("0.00");
        tfCompraMinima.setText("");
        tfCompraMaxima.setText("");
        tfValorFrete.setText("0.00");
        tfIcms.setText("0.00");
        tfCofins.setText("0.00");
        tfIpi.setText("0.00");
        tfJuros.setText("0.00");
        tfNomeFantasia.setText("");
        tfTipoFornecedor.setText("");
        tfBanco.setText("");
        tfRegiao.setText("");
        tfDescontos.setText("0.00");
        ftfCnpj.setText("");
        ftfInscricaoEstadual.setText("");
        ftfCEP.setText("");
        ftfFone.setText("");
        ftfCelular1.setText("");
        ftfCelular2.setText("");
        ftfFax.setText("");
        ftfFoneEmpresa.setText("");
        ftfInscricaoMunicipal.setText("");
        ftfCaixaPostal.setText("");
        ftfAgencia.setText("");
        ftfConta.setText("");
        ftfDigitoConta.setText("");
        lbNomeObrig.setText("");
        lbEnderecoObrig.setText("");
        lbBairroObrig.setText("");
        lbNumeroObrig.setText("");
        lbCidadeObrig.setText("");
        lbEstadoObrig.setText("");
        lbFoneObrig.setText("");
        cbCidade.setSelectedItem("Selecione");
        cbEstado.setSelectedItem("Selecione");
        cbTipoPessoa.setSelectedItem("Selecione");
        cbContribuinte.setSelectedItem("Selecione");
        cbExportador.setSelectedItem("Selecione");
        taObservacoes.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void gravar() throws Exception {
        if ("".equals(tfNome.getText())) {
            lbNomeObrig.setText("*");
        } else {
            lbNomeObrig.setText("");
        }
        if ("".equals(tfEndereco.getText())) {
            lbEnderecoObrig.setText("*");
        } else {
            lbEnderecoObrig.setText("");
        }
        if ("".equals(tfBairro.getText())) {
            lbBairroObrig.setText("*");
        } else {
            lbBairroObrig.setText("");
        }
        if ("".equals(tfNumero.getText())) {
            lbNumeroObrig.setText("*");
        } else {
            lbNumeroObrig.setText("");
        }
        if ("Selecione".equals(cbCidade.getSelectedItem())) {
            lbCidadeObrig.setText("*");
        } else {
            lbCidadeObrig.setText("");
        }
        if ("Selecione".equals(cbEstado.getSelectedItem())) {
            lbEstadoObrig.setText("*");
        } else {
            lbEstadoObrig.setText("");
        }
        if ("(  )    -    ".equals(ftfFone.getText())) {
            lbFoneObrig.setText("*");
        } else {
            lbFoneObrig.setText("");
        }
        if (!"".equals(tfNome.getText()) && !"".equals(tfEndereco.getText()) && !"".equals(tfBairro.getText()) && !"".equals(tfNumero.getText()) && !"Selecione".equals(cbCidade.getSelectedItem()) && !"Selecione".equals(cbEstado.getSelectedItem()) && !"(  )    -    ".equals(ftfFone.getText())) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setCodigo(Integer.parseInt(tfCodigo.getText()));
            fornecedor.setDigitoConta(VerificaCampos.verificaDigitoConta(ftfDigitoConta));
            fornecedor.setNumero(VerificaCampos.verificaInt(tfNumero, "número"));
            fornecedor.setNome(tfNome.getText());
            fornecedor.setSigla(tfSigla.getText());
            fornecedor.setNomeFantasia(tfNomeFantasia.getText());
            fornecedor.setInscricaoEstadual(ftfInscricaoEstadual.getText());
            fornecedor.setCnpj(ftfCnpj.getText());
            fornecedor.setInscricaoMunicipal(ftfInscricaoMunicipal.getText());
            fornecedor.setTipoPessoa((String) cbTipoPessoa.getSelectedItem());
            fornecedor.setContribuinte((String) cbContribuinte.getSelectedItem());
            fornecedor.setExportador((String) cbExportador.getSelectedItem());
            fornecedor.setTipoFornecedor(tfTipoFornecedor.getText());
            fornecedor.setEmpresa(tfEmpresa.getText());
            fornecedor.setFoneEmpresa(ftfFoneEmpresa.getText());
            fornecedor.setBanco(tfBanco.getText());
            fornecedor.setAgencia(ftfAgencia.getText());
            fornecedor.setConta(ftfConta.getText());
            fornecedor.setCaixaPostal(ftfCaixaPostal.getText());
            fornecedor.setEndereco(tfEndereco.getText());
            fornecedor.setBairro(tfBairro.getText());
            fornecedor.setCidade((String) cbCidade.getSelectedItem());
            fornecedor.setEstado((String) cbEstado.getSelectedItem());
            fornecedor.setRegiao(tfRegiao.getText());
            fornecedor.setPais(tfPais.getText());
            fornecedor.setCep(ftfCEP.getText());
            fornecedor.setReferencia(tfReferencia.getText());
            fornecedor.setFone(ftfFone.getText());
            fornecedor.setCelular1(ftfCelular1.getText());
            fornecedor.setCelular2(ftfCelular2.getText());
            fornecedor.setFax(ftfFax.getText());
            fornecedor.setEmail(tfEmail.getText());
            fornecedor.setMsn(tfMsn.getText());
            fornecedor.setSkype(tfSkype.getText());
            fornecedor.setDescricao(tfDescricao.getText());
            fornecedor.setHomePage(tfHomePage.getText());
            fornecedor.setObservacoes(taObservacoes.getText());
            fornecedor.setDataCadas(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            fornecedor.setUltAlteracao(new Date());
            fornecedor.setComissao(VerificaCampos.verificaDouble(tfComissao, "comissão"));
            fornecedor.setCompraMinima(VerificaCampos.verificaDouble(tfCompraMinima, "compra mínima"));
            fornecedor.setCompraMaxima(VerificaCampos.verificaDouble(tfCompraMaxima, "compra máxima"));
            fornecedor.setValorFrete(VerificaCampos.verificaDouble(tfValorFrete, "valor frete"));
            fornecedor.setIcms(VerificaCampos.verificaDouble(tfIcms, "icms"));
            fornecedor.setCofins(VerificaCampos.verificaDouble(tfCofins, "cofins"));
            fornecedor.setIpi(VerificaCampos.verificaDouble(tfIpi, "ipi"));
            fornecedor.setJuros(VerificaCampos.verificaDouble(tfJuros, "juros a.m."));
            fornecedor.setDescontos(VerificaCampos.verificaDouble(tfDescontos, "descontos"));
            Fornecedor fornecedorLido = controle.getFornecedor(tfNome.getText());
            if (fornecedorLido != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "O fornecedor " + fornecedor.getNome() + " ja esta cadastrado deseja substituilo? ", "Fornecedor", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    fornecedor.setCodigo(fornecedorLido.getCodigo());
                    if (controle.setFornecedor(fornecedor)) {
                        JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                } else {
                    return;
                }
            } else {
                if (controle.setFornecedor(fornecedor)) {
                    JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                }
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private void excluir() throws Exception {
        if (controle.arqFornVazio()) {
            JOptionPane.showMessageDialog(null, "Não há fornecedores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String nome = JOptionPane.showInputDialog(null, "Informe o nome do fornecedor a ser excluído:", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
            if (nome != null) {
                if (controle.removeFornecedor(nome)) {
                    JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Fornecedor não encontrado", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.arqFornVazio()) {
            JOptionPane.showMessageDialog(null, "Não há fornecedores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String nome = JOptionPane.showInputDialog(null, "Informe o nome do fornecedor a ser recuperado:", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
            if (nome != null) {
                Fornecedor fornecedor = controle.getFornecedor(nome);
                if (fornecedor != null) {
                    limparTela();
                    tfCodigo.setText(Integer.toString(fornecedor.getCodigo()));
                    ftfDigitoConta.setText(VerificaCampos.recuperaCampoStr(fornecedor.getDigitoConta()));
                    tfNumero.setText(VerificaCampos.recuperaCampoStr(fornecedor.getNumero()));
                    tfNome.setText(fornecedor.getNome());
                    tfSigla.setText(fornecedor.getSigla());
                    tfNomeFantasia.setText(fornecedor.getNomeFantasia());
                    ftfInscricaoEstadual.setText(fornecedor.getInscricaoEstadual());
                    ftfCnpj.setText(fornecedor.getCnpj());
                    ftfInscricaoMunicipal.setText(fornecedor.getInscricaoMunicipal());
                    cbTipoPessoa.setSelectedItem(fornecedor.getTipoPessoa());
                    cbContribuinte.setSelectedItem(fornecedor.getContribuinte());
                    cbExportador.setSelectedItem(fornecedor.getExportador());
                    tfTipoFornecedor.setText(fornecedor.getTipoFornecedor());
                    tfEmpresa.setText(fornecedor.getEmpresa());
                    ftfFoneEmpresa.setText(fornecedor.getFoneEmpresa());
                    tfBanco.setText(fornecedor.getBanco());
                    ftfAgencia.setText(fornecedor.getAgencia());
                    ftfConta.setText(fornecedor.getConta());
                    ftfCaixaPostal.setText(fornecedor.getCaixaPostal());
                    tfEndereco.setText(fornecedor.getEndereco());
                    tfBairro.setText(fornecedor.getBairro());
                    cbCidade.setSelectedItem(fornecedor.getCidade());
                    cbEstado.setSelectedItem(fornecedor.getEstado());
                    tfRegiao.setText(fornecedor.getRegiao());
                    tfPais.setText(fornecedor.getPais());
                    ftfCEP.setText(fornecedor.getCep());
                    tfReferencia.setText(fornecedor.getReferencia());
                    ftfFone.setText(fornecedor.getFone());
                    ftfCelular1.setText(fornecedor.getCelular1());
                    ftfCelular2.setText(fornecedor.getCelular2());
                    ftfFax.setText(fornecedor.getFax());
                    tfEmail.setText(fornecedor.getEmail());
                    tfMsn.setText(fornecedor.getMsn());
                    tfSkype.setText(fornecedor.getSkype());
                    tfDescricao.setText(fornecedor.getDescricao());
                    tfHomePage.setText(fornecedor.getHomePage());
                    taObservacoes.setText(fornecedor.getObservacoes());
                    tfDataCadas.setText(formatDate.format(fornecedor.getDataCadas()) + " as " + formatHora.format(fornecedor.getDataCadas()));
                    tfUltAlteracao.setText(formatDate.format(fornecedor.getUltAlteracao()) + " as " + formatHora.format(fornecedor.getUltAlteracao()));
                    tfComissao.setText(Double.toString(fornecedor.getComissao()));
                    tfCompraMinima.setText(Double.toString(fornecedor.getCompraMinima()));
                    tfCompraMaxima.setText(Double.toString(fornecedor.getCompraMaxima()));
                    tfValorFrete.setText(Double.toString(fornecedor.getValorFrete()));
                    tfIcms.setText(Double.toString(fornecedor.getIcms()));
                    tfCofins.setText(Double.toString(fornecedor.getCofins()));
                    tfIpi.setText(Double.toString(fornecedor.getIpi()));
                    tfJuros.setText(Double.toString(fornecedor.getJuros()));
                    tfDescontos.setText(Double.toString(fornecedor.getDescontos()));
                    JOptionPane.showMessageDialog(null, "Fornecedor recuperado com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Fornecedor não encontrado", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void abrirPesquisaForn() throws Exception {
        if (controle.arqFornVazio()) {
            JOptionPane.showMessageDialog(null, "Não há fornecedores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            PesquisaFornecedor pfr = new PesquisaFornecedor(controle);
            pfr.setModal(true);
            pfr.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Fornecedor", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rbNovo.isSelected() == true) {
                    try {
                        gravar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbAlterar.isSelected() == true) {
                    try {
                        recuperar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbExcluir.isSelected() == true) {
                    try {
                        excluir();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (evento.getSource() == btVer) {
            try {
                abrirPesquisaForn();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
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

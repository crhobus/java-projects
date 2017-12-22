package Fornecedores;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
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
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Formatacao.CamposInt;
import Formatacao.ObjGraficos;
import Modelo.Fornecedor;

public class CadasFornecedores extends ObjGraficos implements ActionListener, ItemListener, FocusListener {

    private static final long serialVersionUID = 7747624304101672986L;
    private JTextField tfCodigo, tfUltAlteracao, tfDataCadas, tfNome, tfSigla,
            tfEndereco, tfBairro, tfNumero, tfPais, tfReferencia, tfEmail,
            tfMsn, tfSkype, tfDescricao, tfHomePage, tfEmpresa, tfComissao,
            tfCompraMinima, tfCompraMaxima, tfValorFrete, tfIcms, tfCofins,
            tfIpi, tfJuros, tfNomeFantasia, tfTipoFornecedor, tfBanco,
            tfRegiao, tfDescontos, tfCidade, tfUF;
    private JFormattedTextField ftfCnpj, ftfIE, ftfCEP, ftfFone, ftfCelular1,
            ftfCelular2, ftfFax, ftfFoneEmpresa, ftfInscricaoMunicipal,
            ftfCaixaPostal, ftfAgencia, ftfConta, ftfDigitoConta;
    private JComboBox cbTipoPessoa, cbContribuinte, cbExportador;
    private JTextArea taObservacoes;
    private JButton btVer, btOk, btCancelar;
    private JLabel lbNomeObrig, lbEnderecoObrig, lbBairroObrig, lbNumeroObrig,
            lbCidadeObrig, lbUFObrig, lbFoneObrig, lbCNPJObrig;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private FornecedorControl controle;

    public CadasFornecedores(Connection con) {
        controle = new FornecedorControl(con);
        try {
            initComponents();
        } catch (ParseException ex) {
        }
    }

    private void initComponents() throws ParseException {
        List<String> itensCombo = new ArrayList<String>();
        setTitle("Cadastro de Fornecedores");
        setLayout(null);
        JPanel panel = getJPanelLineBorder(10, 10, 775, 530);
        add(panel);

        panel.add(getJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = getJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodForn()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        tfCodigo.addFocusListener(this);
        panel.add(tfCodigo);

        btVer = getJButton("...", 106, 34, 31, 24);
        btVer.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btVer.setToolTipText("Consulta Fornecedores");
        btVer.addActionListener(this);
        panel.add(btVer);

        panel.add(getJLabel("Cadastrado em", 143, 20, 90, 14));
        tfDataCadas = getJTextField(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.setEditable(false);
        tfDataCadas.setBackground(Color.WHITE);
        tfDataCadas.addFocusListener(this);
        panel.add(tfDataCadas);

        panel.add(getJLabel("Última Alteração", 270, 20, 100, 14));
        tfUltAlteracao = getJTextField(270, 38, 120, 20);
        tfUltAlteracao.setEditable(false);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfUltAlteracao.addFocusListener(this);
        panel.add(tfUltAlteracao);

        panel.add(getJLabel("Nome", 398, 20, 80, 14));
        lbNomeObrig = getJLabel("", 435, 23, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel.add(lbNomeObrig);
        tfNome = getJTextField(398, 38, 230, 20);
        tfNome.addFocusListener(this);
        panel.add(tfNome);

        panel.add(getJLabel("Sigla", 635, 20, 110, 14));
        tfSigla = getJTextField(635, 38, 120, 20);
        tfSigla.addFocusListener(this);
        panel.add(tfSigla);

        panel.add(getJLabel("Nome Fantasia", 20, 60, 110, 14));
        tfNomeFantasia = getJTextField(20, 78, 170, 20);
        tfNomeFantasia.addFocusListener(this);
        panel.add(tfNomeFantasia);

        panel.add(getJLabel("IE", 200, 60, 110, 14));
        ftfIE = getJFormattedTextField("###.###.###", 200, 78, 100, 20);
        ftfIE.addFocusListener(this);
        panel.add(ftfIE);

        panel.add(getJLabel("CNPJ", 310, 60, 90, 14));
        lbCNPJObrig = getJLabel("", 345, 63, 10, 14);
        lbCNPJObrig.setForeground(Color.RED);
        panel.add(lbCNPJObrig);
        ftfCnpj = getJFormattedTextField("##.###.###/####-##", 310, 78, 125, 20);
        ftfCnpj.addFocusListener(this);
        panel.add(ftfCnpj);

        panel.add(getJLabel("Inscrição Municipal", 445, 60, 110, 14));
        ftfInscricaoMunicipal = getJFormattedTextField("##.##.##", 445, 78, 110, 20);
        ftfInscricaoMunicipal.addFocusListener(this);
        panel.add(ftfInscricaoMunicipal);

        panel.add(getJLabel("Tipo Pessoa", 565, 60, 90, 14));
        itensCombo.add("Física");
        itensCombo.add("Jurídica");
        cbTipoPessoa = getJComboBox(itensCombo, 565, 78, 90, 20);
        cbTipoPessoa.addFocusListener(this);
        panel.add(cbTipoPessoa);

        panel.add(getJLabel("Contribuinte", 665, 60, 90, 14));
        itensCombo.clear();
        itensCombo.add("Sim");
        itensCombo.add("Não");
        cbContribuinte = getJComboBox(itensCombo, 665, 78, 90, 20);
        cbContribuinte.addFocusListener(this);
        panel.add(cbContribuinte);

        panel.add(getJLabel("Exportador", 20, 100, 90, 14));
        itensCombo.clear();
        itensCombo.add("Sim");
        itensCombo.add("Não");
        cbExportador = getJComboBox(itensCombo, 20, 118, 90, 20);
        cbExportador.addFocusListener(this);
        panel.add(cbExportador);

        panel.add(getJLabel("Tipo Fornecedor", 120, 100, 110, 14));
        tfTipoFornecedor = getJTextField(120, 118, 180, 20);
        tfTipoFornecedor.addFocusListener(this);
        panel.add(tfTipoFornecedor);

        panel.add(getJLabel("Empresa", 310, 100, 80, 14));
        tfEmpresa = getJTextField(310, 118, 180, 20);
        tfEmpresa.addFocusListener(this);
        panel.add(tfEmpresa);

        panel.add(getJLabel("Fone Empresa", 500, 100, 90, 14));
        ftfFoneEmpresa = getJFormattedTextField("(##)####-####", 500, 118, 120, 20);
        ftfFoneEmpresa.addFocusListener(this);
        panel.add(ftfFoneEmpresa);

        panel.add(getJLabel("Banco", 630, 100, 130, 14));
        tfBanco = getJTextField(630, 118, 125, 20);
        tfBanco.addFocusListener(this);
        panel.add(tfBanco);

        panel.add(getJLabel("Agência", 20, 140, 130, 14));
        ftfAgencia = getJFormattedTextField("####-#", 20, 158, 100, 20);
        ftfAgencia.addFocusListener(this);
        panel.add(ftfAgencia);

        panel.add(getJLabel("Conta", 130, 140, 130, 14));
        ftfConta = getJFormattedTextField("#######", 130, 158, 120, 20);
        ftfConta.addFocusListener(this);
        panel.add(ftfConta);

        panel.add(getJLabel("Dígito Conta", 260, 140, 130, 14));
        ftfDigitoConta = getJFormattedTextField("#", 260, 158, 100, 20);
        ftfDigitoConta.addFocusListener(this);
        panel.add(ftfDigitoConta);

        panel.add(getJLabel("Caixa Postal", 370, 140, 100, 14));
        ftfCaixaPostal = getJFormattedTextField("######", 370, 158, 120, 20);
        ftfCaixaPostal.addFocusListener(this);
        panel.add(ftfCaixaPostal);

        panel.add(getJLabel("Endereço", 500, 140, 100, 14));
        lbEnderecoObrig = getJLabel("", 560, 143, 10, 14);
        lbEnderecoObrig.setForeground(Color.RED);
        panel.add(lbEnderecoObrig);
        tfEndereco = getJTextField(500, 158, 255, 20);
        tfEndereco.addFocusListener(this);
        panel.add(tfEndereco);

        panel.add(getJLabel("Bairro", 20, 180, 100, 14));
        lbBairroObrig = getJLabel("", 60, 183, 10, 14);
        lbBairroObrig.setForeground(Color.RED);
        panel.add(lbBairroObrig);
        tfBairro = getJTextField(20, 198, 170, 20);
        tfBairro.addFocusListener(this);
        panel.add(tfBairro);

        panel.add(getJLabel("Número", 200, 180, 100, 14));
        lbNumeroObrig = getJLabel("", 250, 183, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel.add(lbNumeroObrig);
        tfNumero = getJTextField(200, 198, 90, 20);
        tfNumero.setDocument(new CamposInt());
        tfNumero.addFocusListener(this);
        panel.add(tfNumero);

        panel.add(getJLabel("Cidade", 300, 180, 90, 14));
        lbCidadeObrig = getJLabel("", 345, 183, 10, 14);
        lbCidadeObrig.setForeground(Color.RED);
        panel.add(lbCidadeObrig);
        tfCidade = getJTextField(300, 198, 160, 20);
        tfCidade.addFocusListener(this);
        panel.add(tfCidade);

        panel.add(getJLabel("UF", 470, 180, 90, 14));
        lbUFObrig = getJLabel("", 490, 183, 10, 14);
        lbUFObrig.setForeground(Color.RED);
        panel.add(lbUFObrig);
        tfUF = getJTextField(470, 198, 120, 20);
        tfUF.addFocusListener(this);
        panel.add(tfUF);

        panel.add(getJLabel("Região", 600, 180, 100, 14));
        tfRegiao = getJTextField(600, 198, 155, 20);
        tfRegiao.addFocusListener(this);
        panel.add(tfRegiao);

        panel.add(getJLabel("País", 20, 220, 100, 14));
        tfPais = getJTextField(20, 238, 140, 20);
        tfPais.addFocusListener(this);
        panel.add(tfPais);

        panel.add(getJLabel("CEP", 170, 220, 80, 14));
        ftfCEP = getJFormattedTextField("#####-###", 170, 238, 100, 20);
        ftfCEP.addFocusListener(this);
        panel.add(ftfCEP);

        panel.add(getJLabel("Referência", 280, 220, 80, 14));
        tfReferencia = getJTextField(280, 238, 220, 20);
        tfReferencia.addFocusListener(this);
        panel.add(tfReferencia);

        panel.add(getJLabel("Fone", 510, 220, 70, 14));
        lbFoneObrig = getJLabel("", 540, 223, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel.add(lbFoneObrig);
        ftfFone = getJFormattedTextField("(##)####-####", 510, 238, 120, 20);
        ftfFone.addFocusListener(this);
        panel.add(ftfFone);

        panel.add(getJLabel("Celular 1", 640, 220, 70, 14));
        ftfCelular1 = getJFormattedTextField("(##)####-####", 640, 238, 115, 20);
        ftfCelular1.addFocusListener(this);
        panel.add(ftfCelular1);

        panel.add(getJLabel("Celular 2", 20, 260, 70, 14));
        ftfCelular2 = getJFormattedTextField("(##)####-####", 20, 278, 110, 20);
        ftfCelular2.addFocusListener(this);
        panel.add(ftfCelular2);

        panel.add(getJLabel("Fax", 140, 260, 60, 14));
        ftfFax = getJFormattedTextField("(##)####-####", 140, 278, 110, 20);
        ftfFax.addFocusListener(this);
        panel.add(ftfFax);

        panel.add(getJLabel("E-Mail", 260, 260, 60, 14));
        tfEmail = getJTextField(260, 278, 245, 20);
        tfEmail.addFocusListener(this);
        panel.add(tfEmail);

        panel.add(getJLabel("MSN", 515, 260, 60, 14));
        tfMsn = getJTextField(515, 278, 240, 20);
        tfMsn.addFocusListener(this);
        panel.add(tfMsn);

        panel.add(getJLabel("Skype", 20, 300, 60, 14));
        tfSkype = getJTextField(20, 318, 260, 20);
        tfSkype.addFocusListener(this);
        panel.add(tfSkype);

        panel.add(getJLabel("Descrição", 290, 300, 60, 14));
        tfDescricao = getJTextField(290, 318, 465, 20);
        tfDescricao.addFocusListener(this);
        panel.add(tfDescricao);

        panel.add(getJLabel("Home Page", 20, 340, 90, 14));
        tfHomePage = getJTextField(20, 358, 270, 20);
        tfHomePage.addFocusListener(this);
        panel.add(tfHomePage);

        panel.add(getJLabel("Comissão", 300, 340, 80, 14));
        tfComissao = getJTextField(300, 358, 100, 20);
        tfComissao.setText("0.00");
        panel.add(tfComissao);
        tfComissao.addFocusListener(this);
        panel.add(getJLabel("%", 405, 361, 20, 14));

        panel.add(getJLabel("Compra Mímina", 420, 340, 100, 14));
        tfCompraMinima = getJTextField(420, 358, 100, 20);
        tfCompraMinima.addFocusListener(this);
        panel.add(tfCompraMinima);

        panel.add(getJLabel("Compra Máxima", 530, 340, 100, 14));
        tfCompraMaxima = getJTextField(530, 358, 100, 20);
        tfCompraMaxima.addFocusListener(this);
        panel.add(tfCompraMaxima);

        panel.add(getJLabel("Valor Frete", 640, 340, 100, 14));
        tfValorFrete = getJTextField(640, 358, 100, 20);
        tfValorFrete.setText("0.00");
        panel.add(tfValorFrete);
        tfValorFrete.addFocusListener(this);
        panel.add(getJLabel("%", 745, 361, 20, 14));

        panel.add(getJLabel("ICMS", 20, 380, 100, 14));
        tfIcms = getJTextField(20, 398, 110, 20);
        tfIcms.setText("0.00");
        panel.add(tfIcms);
        tfIcms.addFocusListener(this);
        panel.add(getJLabel("%", 132, 401, 20, 14));

        panel.add(getJLabel("COFINS", 145, 380, 100, 14));
        tfCofins = getJTextField(145, 398, 110, 20);
        tfCofins.setText("0.00");
        panel.add(tfCofins);
        tfCofins.addFocusListener(this);
        panel.add(getJLabel("%", 257, 401, 20, 14));

        panel.add(getJLabel("IPI", 270, 380, 100, 14));
        tfIpi = getJTextField(270, 398, 110, 20);
        tfIpi.setText("0.00");
        panel.add(tfIpi);
        tfIpi.addFocusListener(this);
        panel.add(getJLabel("%", 382, 401, 20, 14));

        panel.add(getJLabel("Juros a.m.", 400, 380, 100, 14));
        tfJuros = getJTextField(400, 398, 110, 20);
        tfJuros.setText("0.00");
        panel.add(tfJuros);
        tfJuros.addFocusListener(this);
        panel.add(getJLabel("%", 512, 401, 20, 14));

        panel.add(getJLabel("Descontos", 530, 380, 100, 14));
        tfDescontos = getJTextField(530, 398, 110, 20);
        tfDescontos.setText("0.00");
        panel.add(tfDescontos);
        tfDescontos.addFocusListener(this);
        panel.add(getJLabel("%", 645, 401, 20, 14));

        panel.add(getJLabel("Observações", 20, 420, 130, 14));
        taObservacoes = getJTextArea(panel, 20, 438, 540, 75);
        taObservacoes.addFocusListener(this);

        btOk = getJButton("OK", 664, 445, 86, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("OK");
        btOk.addActionListener(this);
        panel.add(btOk);

        btCancelar = getJButton("Cancelar", 664, 475, 86, 26);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        rbNovo = getJRadioButton("Novo", 572, 443, 80, 20);
        rbNovo.setBackground(new Color(248, 248, 248));
        rbNovo.addItemListener(this);
        panel.add(rbNovo);

        rbAlterar = getJRadioButton("Alterar", 572, 462, 80, 20);
        rbAlterar.setBackground(new Color(248, 248, 248));
        rbAlterar.addItemListener(this);
        panel.add(rbAlterar);

        rbExcluir = getJRadioButton("Excluir", 572, 482, 80, 20);
        rbExcluir.setBackground(new Color(248, 248, 248));
        rbExcluir.addItemListener(this);
        panel.add(rbExcluir);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(803, 580);
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
        tfCidade.setText("");
        tfUF.setText("");
        ftfCnpj.setText("");
        ftfIE.setText("");
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
        lbUFObrig.setText("");
        lbFoneObrig.setText("");
        lbCNPJObrig.setText("");
        cbTipoPessoa.setSelectedItem("Selecione");
        cbContribuinte.setSelectedItem("Selecione");
        cbExportador.setSelectedItem("Selecione");
        taObservacoes.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void gravar() throws Exception {
        if ("".equals(tfNome.getText().trim())) {
            lbNomeObrig.setText("*");
        } else {
            lbNomeObrig.setText("");
        }
        if ("  .   .   /    -  ".equals(ftfCnpj.getText())) {
            lbCNPJObrig.setText("*");
        } else {
            lbCNPJObrig.setText("");
        }
        if ("".equals(tfEndereco.getText().trim())) {
            lbEnderecoObrig.setText("*");
        } else {
            lbEnderecoObrig.setText("");
        }
        if ("".equals(tfBairro.getText().trim())) {
            lbBairroObrig.setText("*");
        } else {
            lbBairroObrig.setText("");
        }
        if ("".equals(tfNumero.getText())) {
            lbNumeroObrig.setText("*");
        } else {
            lbNumeroObrig.setText("");
        }
        if ("".equals(tfCidade.getText().trim())) {
            lbCidadeObrig.setText("*");
        } else {
            lbCidadeObrig.setText("");
        }
        if ("".equals(tfUF.getText().trim())) {
            lbUFObrig.setText("*");
        } else {
            lbUFObrig.setText("");
        }
        if ("(  )    -    ".equals(ftfFone.getText())) {
            lbFoneObrig.setText("*");
        } else {
            lbFoneObrig.setText("");
        }
        if (!"".equals(tfNome.getText().trim()) && !"".equals(tfEndereco.getText().trim()) && !"".equals(tfBairro.getText().trim()) && !"".equals(tfNumero.getText()) && !"".equals(tfCidade.getText().trim()) && !"".equals(tfUF.getText().trim()) && !"(  )    -    ".equals(ftfFone.getText()) && !"  .   .   /    -  ".equals(ftfCnpj.getText())) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setCodigo(Integer.parseInt(tfCodigo.getText()));
            fornecedor.setDigitoConta(verificaDigitoConta(ftfDigitoConta));
            fornecedor.setNumero(Integer.parseInt(tfNumero.getText()));
            fornecedor.setNome(tfNome.getText());
            fornecedor.setSigla(tfSigla.getText());
            fornecedor.setNomeFantasia(tfNomeFantasia.getText());
            fornecedor.setIe(ftfIE.getText());
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
            fornecedor.setCidade(tfCidade.getText());
            fornecedor.setUf(tfUF.getText());
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
            fornecedor.setComissao(verificaDouble(tfComissao, "comissão"));
            fornecedor.setCompraMinima(verificaDouble(tfCompraMinima, "compra mínima"));
            fornecedor.setCompraMaxima(verificaDouble(tfCompraMaxima, "compra máxima"));
            fornecedor.setValorFrete(verificaDouble(tfValorFrete, "valor frete"));
            fornecedor.setIcms(verificaDouble(tfIcms, "icms"));
            fornecedor.setCofins(verificaDouble(tfCofins, "cofins"));
            fornecedor.setIpi(verificaDouble(tfIpi, "ipi"));
            fornecedor.setJuros(verificaDouble(tfJuros, "juros a.m."));
            fornecedor.setDescontos(verificaDouble(tfDescontos, "descontos"));
            Fornecedor fornecedorLido = controle.selectFornecedor(ftfCnpj.getText());
            if (fornecedorLido != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "O fornecedor " + fornecedor.getNome() + " com o CNPJ " + fornecedor.getCnpj() + " ja esta cadastrado deseja substituilo? ", "Fornecedor", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    fornecedor.setCodigo(fornecedorLido.getCodigo());
                    if (controle.updateFornecedor(fornecedor)) {
                        JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                } else {
                    return;
                }
            } else {
                if (controle.insertFornecedor(fornecedor)) {
                    JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                }
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private void excluir() throws Exception {
        if (controle.isFornVazio()) {
            throw new Exception("Não há fornecedores cadastrados");
        }
        String cnpj = JOptionPane.showInputDialog(null, "Informe o CNPJ do fornecedor a ser excluído:", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
        if (cnpj != null) {
            if (controle.deleteFornecedor(cnpj)) {
                JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            } else {
                JOptionPane.showMessageDialog(null, "Fornecedor não encontrado", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.isFornVazio()) {
            throw new Exception("Não há fornecedores cadastrados");
        }
        String cnpj = JOptionPane.showInputDialog(null, "Informe o CNPJ do fornecedor a ser recuperado:", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
        if (cnpj != null) {
            Fornecedor fornecedor = controle.selectFornecedor(cnpj);
            if (fornecedor != null) {
                mostrarFornecedor(fornecedor);
                JOptionPane.showMessageDialog(null, "Fornecedor recuperado com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Fornecedor não encontrado", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void mostrarFornecedor(Fornecedor fornecedor) {
        limparTela();
        tfCodigo.setText(Integer.toString(fornecedor.getCodigo()));
        ftfDigitoConta.setText(recuperaCampoStr(fornecedor.getDigitoConta()));
        tfNumero.setText(Integer.toString(fornecedor.getNumero()));
        tfNome.setText(fornecedor.getNome());
        tfSigla.setText(fornecedor.getSigla());
        tfNomeFantasia.setText(fornecedor.getNomeFantasia());
        ftfIE.setText(fornecedor.getIe());
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
        tfCidade.setText(fornecedor.getCidade());
        tfUF.setText(fornecedor.getUf());
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
        tfComissao.setText(recuperaCampoStr(fornecedor.getComissao()));
        tfCompraMinima.setText(recuperaCampoStr(fornecedor.getCompraMinima()));
        tfCompraMaxima.setText(recuperaCampoStr(fornecedor.getCompraMaxima()));
        tfValorFrete.setText(recuperaCampoStr(fornecedor.getValorFrete()));
        tfIcms.setText(recuperaCampoStr(fornecedor.getIcms()));
        tfCofins.setText(recuperaCampoStr(fornecedor.getCofins()));
        tfIpi.setText(recuperaCampoStr(fornecedor.getIpi()));
        tfJuros.setText(recuperaCampoStr(fornecedor.getJuros()));
        tfDescontos.setText(recuperaCampoStr(fornecedor.getDescontos()));
    }

    private void abrirPesquisaForn() throws Exception {
        if (controle.isFornVazio()) {
            JOptionPane.showMessageDialog(null, "Não há fornecedores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            PesquisaFornecedores pesquisaForn = new PesquisaFornecedores(
                    controle);
            pesquisaForn.setListener(new ListenerFornecedor() {

                @Override
                public void exibeFornecedor(Fornecedor fornecedor) {
                    mostrarFornecedor(fornecedor);
                }
            });
            pesquisaForn.setModal(true);
            pesquisaForn.setVisible(true);
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

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUltAlteracao) {
            tfUltAlteracao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDataCadas) {
            tfDataCadas.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNome) {
            tfNome.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSigla) {
            tfSigla.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfPais) {
            tfPais.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfReferencia) {
            tfReferencia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmail) {
            tfEmail.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMsn) {
            tfMsn.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSkype) {
            tfSkype.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfHomePage) {
            tfHomePage.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmpresa) {
            tfEmpresa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfComissao) {
            tfComissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCompraMinima) {
            tfCompraMinima.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCompraMaxima) {
            tfCompraMaxima.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorFrete) {
            tfValorFrete.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfIcms) {
            tfIcms.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCofins) {
            tfCofins.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfIpi) {
            tfIpi.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfJuros) {
            tfJuros.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeFantasia) {
            tfNomeFantasia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTipoFornecedor) {
            tfTipoFornecedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBanco) {
            tfBanco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfRegiao) {
            tfRegiao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescontos) {
            tfDescontos.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCidade) {
            tfCidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUF) {
            tfUF.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCnpj) {
            ftfCnpj.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfIE) {
            ftfIE.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCEP) {
            ftfCEP.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFone) {
            ftfFone.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCelular1) {
            ftfCelular1.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCelular2) {
            ftfCelular2.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFax) {
            ftfFax.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFoneEmpresa) {
            ftfFoneEmpresa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfInscricaoMunicipal) {
            ftfInscricaoMunicipal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCaixaPostal) {
            ftfCaixaPostal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfAgencia) {
            ftfAgencia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfConta) {
            ftfConta.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDigitoConta) {
            ftfDigitoConta.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbTipoPessoa) {
            cbTipoPessoa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbContribuinte) {
            cbContribuinte.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbExportador) {
            cbExportador.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == taObservacoes) {
            taObservacoes.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfNome.setBackground(Color.WHITE);
        tfSigla.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfPais.setBackground(Color.WHITE);
        tfReferencia.setBackground(Color.WHITE);
        tfEmail.setBackground(Color.WHITE);
        tfMsn.setBackground(Color.WHITE);
        tfSkype.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        tfHomePage.setBackground(Color.WHITE);
        tfEmpresa.setBackground(Color.WHITE);
        tfComissao.setBackground(Color.WHITE);
        tfCompraMinima.setBackground(Color.WHITE);
        tfCompraMaxima.setBackground(Color.WHITE);
        tfValorFrete.setBackground(Color.WHITE);
        tfIcms.setBackground(Color.WHITE);
        tfCofins.setBackground(Color.WHITE);
        tfIpi.setBackground(Color.WHITE);
        tfJuros.setBackground(Color.WHITE);
        tfNomeFantasia.setBackground(Color.WHITE);
        tfTipoFornecedor.setBackground(Color.WHITE);
        tfBanco.setBackground(Color.WHITE);
        tfRegiao.setBackground(Color.WHITE);
        tfDescontos.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfUF.setBackground(Color.WHITE);
        ftfCnpj.setBackground(Color.WHITE);
        ftfIE.setBackground(Color.WHITE);
        ftfCEP.setBackground(Color.WHITE);
        ftfFone.setBackground(Color.WHITE);
        ftfCelular1.setBackground(Color.WHITE);
        ftfCelular2.setBackground(Color.WHITE);
        ftfFax.setBackground(Color.WHITE);
        ftfFoneEmpresa.setBackground(Color.WHITE);
        ftfInscricaoMunicipal.setBackground(Color.WHITE);
        ftfCaixaPostal.setBackground(Color.WHITE);
        ftfAgencia.setBackground(Color.WHITE);
        ftfConta.setBackground(Color.WHITE);
        ftfDigitoConta.setBackground(Color.WHITE);
        cbTipoPessoa.setBackground(Color.WHITE);
        cbContribuinte.setBackground(Color.WHITE);
        cbExportador.setBackground(Color.WHITE);
        taObservacoes.setBackground(Color.WHITE);
    }
}

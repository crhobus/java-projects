package Fornecedor;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Endereco.ConsultaEndereco;
import Endereco.EnderecoControl;
import Endereco.ListenerEndereco;
import Modelo.Endereco;
import Modelo.Fornecedor;
import Principal.CamposInt;
import Principal.Controle;
import Seguranca.Seguranca;

public class CadasFornecedor extends Controle implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome, tfSigla,
            tfTipoFornecedor, tfCodEndereco, tfEndereco, tfBairro, tfNumero,
            tfCep, tfCidade, tfEstado, tfRegiao, tfReferencia, tfComissao,
            tfEmail, tfMSN, tfHomePage, tfNomeContato, tfCompraMinima,
            tfCompraMaxima, tfDescricao;
    private JFormattedTextField ftfCnpj, ftfIe, ftfInscMun, ftfFoneComercial,
            ftfFax, ftfFoneContato;
    private JButton btConsulta, btConsultaEndereco, btOk, btCancelar,
            btExcluir, btSair;
    private JLabel lbNomeObrig, lbCnpjObrig, lbIeObrig, lbInscMunObrig,
            lbTipoForneObrig, lbCodEnderecoObrig, lbNumeroObrig,
            lbFoneComercialObrig, lbNomeContatoObrig, lbFoneContatoObrig;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;
    private SimpleDateFormat formatDateHora;
    private FornecedorControl controleForn;
    private EnderecoControl controleEndereco;
    private Seguranca seguranca;

    public CadasFornecedor(Connection con, Seguranca seguranca) {
        this.seguranca = seguranca;
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        controleForn = new FornecedorControl(con);
        controleEndereco = new EnderecoControl(con);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Fornecedores");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 672, 390);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        add(panel);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 20, 60, 14);
        panel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controleForn.getProxCodForn()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        tfCodigo.addFocusListener(this);
        panel.add(tfCodigo);

        btConsulta = new JButton("...");
        btConsulta.setBounds(106, 35, 31, 26);
        btConsulta.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsulta.setToolTipText("Consulta Fornecedores");
        btConsulta.addActionListener(this);
        panel.add(btConsulta);

        JLabel lbDataCdas = new JLabel("Cadastro em");
        lbDataCdas.setBounds(143, 20, 90, 14);
        panel.add(lbDataCdas);

        tfDataCadas = new JTextField();
        tfDataCadas.setBounds(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.setEditable(false);
        tfDataCadas.setBackground(Color.WHITE);
        tfDataCadas.addFocusListener(this);
        panel.add(tfDataCadas);

        JLabel lbUltAlteracao = new JLabel("Última Alteração");
        lbUltAlteracao.setBounds(270, 20, 100, 14);
        panel.add(lbUltAlteracao);

        tfUltAlteracao = new JTextField();
        tfUltAlteracao.setBounds(270, 38, 120, 20);
        tfUltAlteracao.setEditable(false);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfUltAlteracao.addFocusListener(this);
        panel.add(tfUltAlteracao);

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(400, 20, 50, 14);
        panel.add(lbNome);

        lbNomeObrig = new JLabel("");
        lbNomeObrig.setBounds(430, 23, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel.add(lbNomeObrig);

        tfNome = new JTextField();
        tfNome.setBounds(400, 38, 250, 20);
        tfNome.addFocusListener(this);
        panel.add(tfNome);

        JLabel lbSigla = new JLabel("Sigla");
        lbSigla.setBounds(20, 63, 50, 14);
        panel.add(lbSigla);

        tfSigla = new JTextField();
        tfSigla.setBounds(20, 81, 100, 20);
        tfSigla.addFocusListener(this);
        panel.add(tfSigla);

        JLabel lbCnpj = new JLabel("CNPJ");
        lbCnpj.setBounds(130, 63, 80, 14);
        panel.add(lbCnpj);

        lbCnpjObrig = new JLabel("");
        lbCnpjObrig.setBounds(158, 66, 10, 14);
        lbCnpjObrig.setForeground(Color.RED);
        panel.add(lbCnpjObrig);

        try {
            ftfCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
        } catch (ParseException ex) {
        }
        ftfCnpj.setBounds(130, 81, 120, 20);
        ftfCnpj.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCnpj.addFocusListener(this);
        panel.add(ftfCnpj);

        JLabel lbIe = new JLabel("IE");
        lbIe.setBounds(260, 63, 90, 14);
        panel.add(lbIe);

        lbIeObrig = new JLabel("");
        lbIeObrig.setBounds(275, 66, 10, 14);
        lbIeObrig.setForeground(Color.RED);
        panel.add(lbIeObrig);

        try {
            ftfIe = new JFormattedTextField(new MaskFormatter("###.###.###"));
        } catch (ParseException ex) {
        }
        ftfIe.setBounds(260, 81, 100, 20);
        ftfIe.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfIe.addFocusListener(this);
        panel.add(ftfIe);

        JLabel lbInscMun = new JLabel("Inscrição Municipal");
        lbInscMun.setBounds(370, 63, 110, 14);
        panel.add(lbInscMun);

        lbInscMunObrig = new JLabel("");
        lbInscMunObrig.setBounds(461, 66, 10, 14);
        lbInscMunObrig.setForeground(Color.RED);
        panel.add(lbInscMunObrig);

        try {
            ftfInscMun = new JFormattedTextField(new MaskFormatter("##.##.##"));
        } catch (ParseException ex) {
        }
        ftfInscMun.setBounds(370, 81, 100, 20);
        ftfInscMun.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfInscMun.addFocusListener(this);
        panel.add(ftfInscMun);

        JLabel lbTipoForne = new JLabel("Tipo Fornecedor");
        lbTipoForne.setBounds(480, 63, 110, 14);
        panel.add(lbTipoForne);

        lbTipoForneObrig = new JLabel("");
        lbTipoForneObrig.setBounds(558, 66, 10, 14);
        lbTipoForneObrig.setForeground(Color.RED);
        panel.add(lbTipoForneObrig);

        tfTipoFornecedor = new JTextField();
        tfTipoFornecedor.setBounds(480, 81, 170, 20);
        tfTipoFornecedor.addFocusListener(this);
        panel.add(tfTipoFornecedor);

        JLabel lbCodEndereco = new JLabel("Cod. Endereço");
        lbCodEndereco.setBounds(20, 105, 90, 14);
        panel.add(lbCodEndereco);

        lbCodEnderecoObrig = new JLabel("");
        lbCodEnderecoObrig.setBounds(93, 108, 10, 14);
        lbCodEnderecoObrig.setForeground(Color.RED);
        panel.add(lbCodEnderecoObrig);

        tfCodEndereco = new JTextField();
        tfCodEndereco.setBounds(20, 123, 80, 20);
        tfCodEndereco.setEditable(false);
        tfCodEndereco.setBackground(Color.WHITE);
        tfCodEndereco.addFocusListener(this);
        panel.add(tfCodEndereco);

        btConsultaEndereco = new JButton("...");
        btConsultaEndereco.setBounds(105, 120, 31, 26);
        btConsultaEndereco.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaEndereco.setToolTipText("Consulta Endereço");
        btConsultaEndereco.addActionListener(this);
        panel.add(btConsultaEndereco);

        JLabel lbEndereco = new JLabel("Endereço");
        lbEndereco.setBounds(145, 105, 60, 14);
        panel.add(lbEndereco);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(145, 123, 165, 20);
        tfEndereco.setEditable(false);
        tfEndereco.setBackground(Color.WHITE);
        tfEndereco.addFocusListener(this);
        panel.add(tfEndereco);

        JLabel lbBairro = new JLabel("Bairro");
        lbBairro.setBounds(318, 105, 60, 14);
        panel.add(lbBairro);

        tfBairro = new JTextField();
        tfBairro.setBounds(318, 123, 155, 20);
        tfBairro.setEditable(false);
        tfBairro.setBackground(Color.WHITE);
        tfBairro.addFocusListener(this);
        panel.add(tfBairro);

        JLabel lbNumero = new JLabel("Número");
        lbNumero.setBounds(482, 105, 55, 14);
        panel.add(lbNumero);

        lbNumeroObrig = new JLabel("");
        lbNumeroObrig.setBounds(520, 108, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel.add(lbNumeroObrig);

        tfNumero = new JTextField();
        tfNumero.setBounds(482, 123, 70, 20);
        tfNumero.setDocument(new CamposInt());
        tfNumero.addFocusListener(this);
        panel.add(tfNumero);

        JLabel lbCep = new JLabel("CEP");
        lbCep.setBounds(560, 105, 80, 14);
        panel.add(lbCep);

        tfCep = new JTextField();
        tfCep.setBounds(560, 123, 90, 20);
        tfCep.setEditable(false);
        tfCep.setBackground(Color.WHITE);
        tfCep.addFocusListener(this);
        panel.add(tfCep);

        JLabel lbCidade = new JLabel("Cidade");
        lbCidade.setBounds(20, 147, 70, 14);
        panel.add(lbCidade);

        tfCidade = new JTextField();
        tfCidade.setBounds(20, 165, 140, 20);
        tfCidade.setEditable(false);
        tfCidade.setBackground(Color.WHITE);
        tfCidade.addFocusListener(this);
        panel.add(tfCidade);

        JLabel lbEstado = new JLabel("Estado");
        lbEstado.setBounds(170, 147, 70, 14);
        panel.add(lbEstado);

        tfEstado = new JTextField();
        tfEstado.setBounds(170, 165, 140, 20);
        tfEstado.setEditable(false);
        tfEstado.setBackground(Color.WHITE);
        tfEstado.addFocusListener(this);
        panel.add(tfEstado);

        JLabel lbRegiao = new JLabel("Região");
        lbRegiao.setBounds(320, 147, 70, 14);
        panel.add(lbRegiao);

        tfRegiao = new JTextField();
        tfRegiao.setBounds(320, 165, 95, 20);
        tfRegiao.setEditable(false);
        tfRegiao.setBackground(Color.WHITE);
        tfRegiao.addFocusListener(this);
        panel.add(tfRegiao);

        JLabel lbReferencia = new JLabel("Referência");
        lbReferencia.setBounds(425, 147, 70, 14);
        panel.add(lbReferencia);

        tfReferencia = new JTextField();
        tfReferencia.setBounds(425, 165, 120, 20);
        tfReferencia.addFocusListener(this);
        panel.add(tfReferencia);

        JLabel lbComissao = new JLabel("Comissão");
        lbComissao.setBounds(555, 147, 70, 14);
        panel.add(lbComissao);

        tfComissao = new JTextField();
        tfComissao.setBounds(555, 165, 80, 20);
        tfComissao.addFocusListener(this);
        panel.add(tfComissao);

        JLabel lbPerceComis = new JLabel("%");
        lbPerceComis.setBounds(640, 167, 15, 14);
        panel.add(lbPerceComis);

        JLabel lbEmail = new JLabel("E-mail");
        lbEmail.setBounds(20, 190, 70, 14);
        panel.add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(20, 208, 165, 20);
        tfEmail.addFocusListener(this);
        panel.add(tfEmail);

        JLabel lbMSN = new JLabel("MSN");
        lbMSN.setBounds(195, 190, 70, 14);
        panel.add(lbMSN);

        tfMSN = new JTextField();
        tfMSN.setBounds(195, 208, 165, 20);
        tfMSN.addFocusListener(this);
        panel.add(tfMSN);

        JLabel lbHomePage = new JLabel("Home Page");
        lbHomePage.setBounds(370, 190, 70, 14);
        panel.add(lbHomePage);

        tfHomePage = new JTextField();
        tfHomePage.setBounds(370, 208, 170, 20);
        tfHomePage.addFocusListener(this);
        panel.add(tfHomePage);

        JLabel lbFoneComercial = new JLabel("Fone Comercial");
        lbFoneComercial.setBounds(550, 190, 90, 14);
        panel.add(lbFoneComercial);

        lbFoneComercialObrig = new JLabel("");
        lbFoneComercialObrig.setBounds(625, 193, 10, 14);
        lbFoneComercialObrig.setForeground(Color.RED);
        panel.add(lbFoneComercialObrig);

        try {
            ftfFoneComercial = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfFoneComercial.setBounds(550, 208, 100, 20);
        ftfFoneComercial.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfFoneComercial.addFocusListener(this);
        panel.add(ftfFoneComercial);

        JLabel lbFax = new JLabel("Fax");
        lbFax.setBounds(20, 233, 50, 14);
        panel.add(lbFax);

        try {
            ftfFax = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfFax.setBounds(20, 251, 100, 20);
        ftfFax.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfFax.addFocusListener(this);
        panel.add(ftfFax);

        JLabel lbNomeContato = new JLabel("Nome Contato");
        lbNomeContato.setBounds(130, 233, 70, 14);
        panel.add(lbNomeContato);

        lbNomeContatoObrig = new JLabel("");
        lbNomeContatoObrig.setBounds(201, 236, 10, 14);
        lbNomeContatoObrig.setForeground(Color.RED);
        panel.add(lbNomeContatoObrig);

        tfNomeContato = new JTextField();
        tfNomeContato.setBounds(130, 251, 190, 20);
        tfNomeContato.addFocusListener(this);
        panel.add(tfNomeContato);

        JLabel lbFoneContato = new JLabel("Fone Contato");
        lbFoneContato.setBounds(330, 233, 90, 14);
        panel.add(lbFoneContato);

        lbFoneContatoObrig = new JLabel("");
        lbFoneContatoObrig.setBounds(399, 236, 10, 14);
        lbFoneContatoObrig.setForeground(Color.RED);
        panel.add(lbFoneContatoObrig);

        try {
            ftfFoneContato = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfFoneContato.setBounds(330, 251, 100, 20);
        ftfFoneContato.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfFoneContato.addFocusListener(this);
        panel.add(ftfFoneContato);

        JLabel lbCompraMinima = new JLabel("Compra Mínima");
        lbCompraMinima.setBounds(440, 233, 80, 14);
        panel.add(lbCompraMinima);

        tfCompraMinima = new JTextField();
        tfCompraMinima.setBounds(440, 251, 100, 20);
        tfCompraMinima.addFocusListener(this);
        panel.add(tfCompraMinima);

        JLabel lbCompraMaxima = new JLabel("Compra Máxima");
        lbCompraMaxima.setBounds(550, 233, 80, 14);
        panel.add(lbCompraMaxima);

        tfCompraMaxima = new JTextField();
        tfCompraMaxima.setBounds(550, 251, 100, 20);
        tfCompraMaxima.addFocusListener(this);
        panel.add(tfCompraMaxima);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(20, 276, 80, 14);
        panel.add(lbDescricao);

        tfDescricao = new JTextField();
        tfDescricao.setBounds(20, 294, 630, 20);
        tfDescricao.addFocusListener(this);
        panel.add(tfDescricao);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 335, 672, 3);
        panel.add(separator);

        Icon icOk = new ImageIcon("OK.png");
        btOk = new JButton("OK", icOk);
        btOk.setBounds(140, 350, 70, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("Confirma Operação");
        btOk.addActionListener(this);
        panel.add(btOk);

        Icon icCancelar = new ImageIcon("Cancelar.png");
        btCancelar = new JButton("Cancelar", icCancelar);
        btCancelar.setBounds(250, 350, 70, 26);
        btCancelar.setMargin(new Insets(0, 0, 0, 0));
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Limpar os Campos");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        Icon icExcluir = new ImageIcon("Excluir.png");
        btExcluir = new JButton("Excluir", icExcluir);
        btExcluir.setBounds(360, 350, 70, 26);
        btExcluir.setMargin(new Insets(0, 0, 0, 0));
        btExcluir.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.setToolTipText("Excluir Registro");
        btExcluir.addActionListener(this);
        panel.add(btExcluir);

        Icon icSair = new ImageIcon("Sair.png");
        btSair = new JButton("Sair", icSair);
        btSair.setBounds(470, 350, 70, 26);
        btSair.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btSair.setToolTipText("Fechar");
        btSair.addActionListener(this);
        panel.add(btSair);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(700, 440);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controleForn.getProxCodForn()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfNome.setText("");
        tfSigla.setText("");
        tfTipoFornecedor.setText("");
        tfCodEndereco.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfCep.setText("");
        tfCidade.setText("");
        tfEstado.setText("");
        tfRegiao.setText("");
        tfReferencia.setText("");
        tfComissao.setText("");
        tfEmail.setText("");
        tfMSN.setText("");
        tfHomePage.setText("");
        tfNomeContato.setText("");
        tfCompraMinima.setText("");
        tfCompraMaxima.setText("");
        tfDescricao.setText("");
        ftfCnpj.setText("");
        ftfIe.setText("");
        ftfInscMun.setText("");
        ftfFoneComercial.setText("");
        ftfFax.setText("");
        ftfFoneContato.setText("");
        lbNomeObrig.setText("");
        lbCnpjObrig.setText("");
        lbIeObrig.setText("");
        lbInscMunObrig.setText("");
        lbTipoForneObrig.setText("");
        lbCodEnderecoObrig.setText("");
        lbNumeroObrig.setText("");
        lbFoneComercialObrig.setText("");
        lbNomeContatoObrig.setText("");
        lbFoneContatoObrig.setText("");
    }

    private void gravar() throws Exception {
        boolean flag = false;
        if ("".equals(tfNome.getText().trim())) {
            lbNomeObrig.setText("*");
            flag = true;
        } else {
            lbNomeObrig.setText("");
        }
        if ("  .   .   /    -  ".equals(ftfCnpj.getText())) {
            lbCnpjObrig.setText("*");
            flag = true;
        } else {
            lbCnpjObrig.setText("");
        }
        if ("   .   .   ".equals(ftfIe.getText())) {
            lbIeObrig.setText("*");
            flag = true;
        } else {
            lbIeObrig.setText("");
        }
        if ("  .  .  ".equals(ftfInscMun.getText())) {
            lbInscMunObrig.setText("*");
            flag = true;
        } else {
            lbInscMunObrig.setText("");
        }
        if ("".equals(tfTipoFornecedor.getText().trim())) {
            lbTipoForneObrig.setText("*");
            flag = true;
        } else {
            lbTipoForneObrig.setText("");
        }
        if ("".equals(tfCodEndereco.getText())) {
            lbCodEnderecoObrig.setText("*");
            flag = true;
        } else {
            lbCodEnderecoObrig.setText("");
        }
        if ("".equals(tfNumero.getText())) {
            lbNumeroObrig.setText("*");
            flag = true;
        } else {
            lbNumeroObrig.setText("");
        }
        if ("(  )    -    ".equals(ftfFoneComercial.getText())) {
            lbFoneComercialObrig.setText("*");
            flag = true;
        } else {
            lbFoneComercialObrig.setText("");
        }
        if ("".equals(tfNomeContato.getText().trim())) {
            lbNomeContatoObrig.setText("*");
            flag = true;
        } else {
            lbNomeContatoObrig.setText("");
        }
        if ("(  )    -    ".equals(ftfFoneContato.getText())) {
            lbFoneContatoObrig.setText("*");
            flag = true;
        } else {
            lbFoneContatoObrig.setText("");
        }
        if (!flag) {
            if (!"".equals(tfComissao.getText().trim())) {
                validaDouble(tfComissao, "Campo comissão inválido", "Comissão deve conter valor positivo", 0);
            }
            if (!"".equals(tfCompraMinima.getText().trim())) {
                validaDouble(tfCompraMinima, "Campo compra mínima inválido", "Compra mínima deve conter valor positivo", 0);
            }
            if (!"".equals(tfCompraMaxima.getText().trim())) {
                validaDouble(tfCompraMaxima, "Campo compra máxima inválido", "Compra máxima deve conter valor positivo", 0);
            }
            Fornecedor fornecedor = new Fornecedor();

            try {
                fornecedor.setSigla(seguranca.encriptarAssimetricamente(tfSigla.getText().getBytes(), true));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            fornecedor.setCodForn(Integer.parseInt(tfCodigo.getText()));
            fornecedor.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            fornecedor.setUltAlteracao(new Date());
            fornecedor.setNome(tfNome.getText());
            fornecedor.setCpfCnpj(ftfCnpj.getText());
            fornecedor.setRgIe(ftfIe.getText());
            fornecedor.setInscMunicipal(ftfInscMun.getText());
            fornecedor.setTipoFornecedor(tfTipoFornecedor.getText());
            fornecedor.setCodEndereco(Integer.parseInt(tfCodEndereco.getText()));
            fornecedor.setNumero(Integer.parseInt(tfNumero.getText()));
            fornecedor.setReferencia(tfReferencia.getText());
            if ("".equals(tfComissao.getText().trim())) {
                fornecedor.setComissao(-1);
            } else {
                fornecedor.setComissao(Double.parseDouble(tfComissao.getText()));
            }
            fornecedor.setEmail(tfEmail.getText());
            fornecedor.setMsn(tfMSN.getText());
            fornecedor.setHomePage(tfHomePage.getText());
            fornecedor.setFone(ftfFoneComercial.getText());
            fornecedor.setFax(ftfFax.getText());
            fornecedor.setNomeContato(tfNomeContato.getText());
            fornecedor.setFoneContato(ftfFoneContato.getText());
            if ("".equals(tfCompraMinima.getText().trim())) {
                fornecedor.setCompraMinima(-1);
            } else {
                fornecedor.setCompraMinima(Double.parseDouble(tfCompraMinima.getText()));
            }
            if ("".equals(tfCompraMaxima.getText().trim())) {
                fornecedor.setCompraMaxima(-1);
            } else {
                fornecedor.setCompraMaxima(Double.parseDouble(tfCompraMaxima.getText()));
            }
            fornecedor.setDescricao(tfDescricao.getText());
            int codFornCadas = controleForn.getFornCadastrado(ftfCnpj.getText(), Integer.parseInt(tfCodigo.getText()));// verifica se fornecedor já cadastrado
            if (codFornCadas != -1) {
                if (JOptionPane.showConfirmDialog(null, "Confirmar alteração nos dados", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (controleForn.updateForn(fornecedor)) {
                        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            } else {
                codFornCadas = controleForn.getFornCadastrado(ftfCnpj.getText());// verifica se pessoa já cadastrada
                if (codFornCadas != -1) {
                    JOptionPane.showMessageDialog(null, "Este CNPJ ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (controleForn.insertForn(fornecedor)) {
                        JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            }
        } else {
            throw new Exception("Campos * obrigatórios inválidos");
        }
    }

    private void consultaForn() throws Exception {
        if (controleForn.isFornVazio()) {
            throw new Exception("Não há fornecedores cadastrados");
        }
        ConsultaForn consulta = new ConsultaForn(controleForn);
        consulta.setListener(new ListenerForn() {
            @Override
            public void exibeForn(Fornecedor fornecedor) {
                limparTela();

                try {
                    tfSigla.setText(new String(seguranca.decriptarAssimetricamente(fornecedor.getSigla(), true)));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                tfCodigo.setText(Integer.toString(fornecedor.getCodForn()));
                tfDataCadas.setText(formatDate.format(fornecedor.getDataCadastro()) + " as " + formatHora.format(fornecedor.getDataCadastro()));
                tfUltAlteracao.setText(formatDate.format(fornecedor.getUltAlteracao()) + " as " + formatHora.format(fornecedor.getUltAlteracao()));
                tfNome.setText(fornecedor.getNome());
                ftfCnpj.setText(fornecedor.getCpfCnpj());
                ftfIe.setText(fornecedor.getRgIe());
                ftfInscMun.setText(fornecedor.getInscMunicipal());
                tfTipoFornecedor.setText(fornecedor.getTipoFornecedor());
                tfCodEndereco.setText(Integer.toString(fornecedor.getCodEndereco()));
                tfEndereco.setText(fornecedor.getEndereco());
                tfBairro.setText(fornecedor.getBairro());
                tfNumero.setText(Integer.toString(fornecedor.getNumero()));
                tfCep.setText(fornecedor.getCep());
                tfCidade.setText(fornecedor.getCidade());
                tfEstado.setText(fornecedor.getEstado());
                tfRegiao.setText(fornecedor.getRegiao());
                tfReferencia.setText(fornecedor.getReferencia());
                if (fornecedor.getComissao() != -1) {
                    tfComissao.setText(Double.toString(fornecedor.getComissao()));
                }
                tfEmail.setText(fornecedor.getEmail());
                tfMSN.setText(fornecedor.getMsn());
                tfHomePage.setText(fornecedor.getHomePage());
                ftfFoneComercial.setText(fornecedor.getFone());
                ftfFax.setText(fornecedor.getFax());
                tfNomeContato.setText(fornecedor.getNomeContato());
                ftfFoneContato.setText(fornecedor.getFoneContato());
                if (fornecedor.getCompraMinima() != -1) {
                    tfCompraMinima.setText(Double.toString(fornecedor.getCompraMinima()));
                }
                if (fornecedor.getCompraMaxima() != -1) {
                    tfCompraMaxima.setText(Double.toString(fornecedor.getCompraMaxima()));
                }
                tfDescricao.setText(fornecedor.getDescricao());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (controleForn.isFornVazio()) {
            throw new Exception("Não há fornecedores cadastrados");
        }
        if (!controleForn.isFornCadastrado(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione um fornecedor");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse fornecedor", "Fornecedor", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (controleForn.deleteForn(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            }
        }
    }

    private void consultaEndereco() throws Exception {
        if (controleEndereco.isEnderecoVazio()) {
            throw new Exception("Não há endereços cadastrados");
        }
        ConsultaEndereco consulta = new ConsultaEndereco(controleEndereco);
        consulta.setListener(new ListenerEndereco() {
            @Override
            public void exibeEndereco(Endereco endereco) {
                tfCodEndereco.setText(Integer.toString(endereco.getCodEndereco()));
                tfEndereco.setText(endereco.getEndereco());
                tfBairro.setText(endereco.getBairro());
                tfCep.setText(endereco.getCep());
                tfCidade.setText(endereco.getCidade());
                tfEstado.setText(endereco.getEstado());
                tfRegiao.setText(endereco.getRegiao());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOk) {
            try {
                gravar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btExcluir) {
            try {
                excluir();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btSair) {
            this.dispose();
        }
        if (evento.getSource() == btConsulta) {
            try {
                consultaForn();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaEndereco) {
            try {
                consultaEndereco();
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
        if (evento.getSource() == tfDataCadas) {
            tfDataCadas.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUltAlteracao) {
            tfUltAlteracao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNome) {
            tfNome.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSigla) {
            tfSigla.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTipoFornecedor) {
            tfTipoFornecedor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodEndereco) {
            tfCodEndereco.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfCep) {
            tfCep.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCidade) {
            tfCidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEstado) {
            tfEstado.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfRegiao) {
            tfRegiao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfReferencia) {
            tfReferencia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfComissao) {
            tfComissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmail) {
            tfEmail.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMSN) {
            tfMSN.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfHomePage) {
            tfHomePage.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeContato) {
            tfNomeContato.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCompraMinima) {
            tfCompraMinima.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCompraMaxima) {
            tfCompraMaxima.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCnpj) {
            ftfCnpj.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfIe) {
            ftfIe.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfInscMun) {
            ftfInscMun.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFoneComercial) {
            ftfFoneComercial.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFax) {
            ftfFax.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFoneContato) {
            ftfFoneContato.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfNome.setBackground(Color.WHITE);
        tfSigla.setBackground(Color.WHITE);
        tfTipoFornecedor.setBackground(Color.WHITE);
        tfCodEndereco.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfCep.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfEstado.setBackground(Color.WHITE);
        tfRegiao.setBackground(Color.WHITE);
        tfReferencia.setBackground(Color.WHITE);
        tfComissao.setBackground(Color.WHITE);
        tfEmail.setBackground(Color.WHITE);
        tfMSN.setBackground(Color.WHITE);
        tfHomePage.setBackground(Color.WHITE);
        tfNomeContato.setBackground(Color.WHITE);
        tfCompraMinima.setBackground(Color.WHITE);
        tfCompraMaxima.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        if (evento.getSource() == ftfCnpj) {
            ftfCnpj.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfCnpj);
        }
        if (evento.getSource() == ftfIe) {
            ftfIe.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfIe);
        }
        if (evento.getSource() == ftfInscMun) {
            ftfInscMun.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfInscMun);
        }
        if (evento.getSource() == ftfFoneComercial) {
            ftfFoneComercial.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfFoneComercial);
        }
        if (evento.getSource() == ftfFax) {
            ftfFax.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfFax);
        }
        if (evento.getSource() == ftfFoneContato) {
            ftfFoneContato.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfFoneContato);
        }
    }
}

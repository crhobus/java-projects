package Cliente;

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
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import Endereco.ConsultaEndereco;
import Endereco.EnderecoControl;
import Endereco.ListenerEndereco;
import Endereco.Cidade.CidadeControl;
import Endereco.Cidade.ConsultaCidade;
import Endereco.Cidade.ListenerCidade;
import Modelo.Cidade;
import Modelo.Cliente;
import Modelo.Endereco;
import Principal.CamposInt;
import Principal.Controle;

public class CadasCliente extends Controle implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome,
            tfCodEndereco, tfEndereco, tfBairro, tfNaturalidade,
            tfNacionalidade, tfIdade, tfNumero, tfComplemento, tfCidade, tfCep,
            tfEstado, tfRegiao, tfReferencia, tfEmpresa, tfProfissao, tfRenda,
            tfEmail, tfMsn, tfDescricao;
    private JFormattedTextField ftfCpfCnpj, ftfRgIe, ftfDataNasc,
            ftfFoneEmpresa, ftfFone, ftfFax, ftfCelular1, ftfCelular2;
    private JComboBox cbTipoPessoa, cbSexo, cbEstadoCivil;
    private JButton btConsulta, btConsultaEndereco, btConsultaCid, btOk,
            btCancelar, btExcluir, btSair;
    private JLabel lbNomeObrig, lbTipoPessoaObrig, lbCpfCnpjObrig, lbRgIeObrig,
            lbCodEnderecoObrig, lbNumeroObrig, lbFoneObrig;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;
    private SimpleDateFormat formatDateHora;
    private int codCidade = -1;
    private ClienteControl controleClie;
    private CidadeControl controleCidade;
    private EnderecoControl controleEndereco;

    public CadasCliente(Connection con) {
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        controleClie = new ClienteControl(con);
        controleCidade = new CidadeControl(con);
        controleEndereco = new EnderecoControl(con);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Clientes");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 750, 390);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        add(panel);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 20, 60, 14);
        panel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controleClie.getProxCodClie()));
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
        btConsulta.setToolTipText("Consulta Clientes");
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

        JLabel lbNome = new JLabel("Nome / Razão Social");
        lbNome.setBounds(400, 20, 120, 14);
        panel.add(lbNome);

        lbNomeObrig = new JLabel("");
        lbNomeObrig.setBounds(500, 23, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel.add(lbNomeObrig);

        tfNome = new JTextField();
        tfNome.setBounds(400, 38, 230, 20);
        tfNome.addFocusListener(this);
        panel.add(tfNome);

        JLabel lbTipoPessoa = new JLabel("Tipo Pessoa");
        lbTipoPessoa.setBounds(640, 20, 70, 14);
        panel.add(lbTipoPessoa);

        lbTipoPessoaObrig = new JLabel("");
        lbTipoPessoaObrig.setBounds(700, 23, 10, 14);
        lbTipoPessoaObrig.setForeground(Color.RED);
        panel.add(lbTipoPessoaObrig);

        cbTipoPessoa = new JComboBox();
        cbTipoPessoa.setBounds(640, 38, 90, 20);
        cbTipoPessoa.addItem("Selecione");
        cbTipoPessoa.addItem("Física");
        cbTipoPessoa.addItem("Jurídica");
        cbTipoPessoa.addFocusListener(this);
        cbTipoPessoa.addActionListener(this);
        panel.add(cbTipoPessoa);

        JLabel lbCpfCnpj = new JLabel("CPF / CNPJ");
        lbCpfCnpj.setBounds(20, 63, 80, 14);
        panel.add(lbCpfCnpj);

        lbCpfCnpjObrig = new JLabel("");
        lbCpfCnpjObrig.setBounds(77, 66, 10, 14);
        lbCpfCnpjObrig.setForeground(Color.RED);
        panel.add(lbCpfCnpjObrig);

        ftfCpfCnpj = new JFormattedTextField();
        ftfCpfCnpj.setBounds(20, 81, 115, 20);
        ftfCpfCnpj.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCpfCnpj.setEditable(false);
        ftfCpfCnpj.setBackground(Color.WHITE);
        ftfCpfCnpj.addFocusListener(this);
        panel.add(ftfCpfCnpj);

        JLabel lbRgIe = new JLabel("RG / IE");
        lbRgIe.setBounds(145, 63, 80, 14);
        panel.add(lbRgIe);

        lbRgIeObrig = new JLabel("");
        lbRgIeObrig.setBounds(180, 66, 10, 14);
        lbRgIeObrig.setForeground(Color.RED);
        panel.add(lbRgIeObrig);

        ftfRgIe = new JFormattedTextField();
        ftfRgIe.setBounds(145, 81, 85, 20);
        ftfRgIe.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfRgIe.setEditable(false);
        ftfRgIe.setBackground(Color.WHITE);
        ftfRgIe.addFocusListener(this);
        panel.add(ftfRgIe);

        JLabel lbSexo = new JLabel("Sexo");
        lbSexo.setBounds(240, 63, 50, 14);
        panel.add(lbSexo);

        cbSexo = new JComboBox();
        cbSexo.setBounds(240, 81, 80, 20);
        cbSexo.addItem("Selecione");
        cbSexo.addItem("Masculino");
        cbSexo.addItem("Feminino");
        cbSexo.addFocusListener(this);
        panel.add(cbSexo);

        JLabel lbEstadoCivil = new JLabel("Estado Civil");
        lbEstadoCivil.setBounds(330, 63, 80, 14);
        panel.add(lbEstadoCivil);

        cbEstadoCivil = new JComboBox();
        cbEstadoCivil.setBounds(330, 81, 80, 20);
        cbEstadoCivil.addItem("Selecione");
        cbEstadoCivil.addItem("Solteiro");
        cbEstadoCivil.addItem("Casado");
        cbEstadoCivil.addItem("Separado");
        cbEstadoCivil.addItem("Divorciado");
        cbEstadoCivil.addItem("Viúvo");
        cbEstadoCivil.addFocusListener(this);
        panel.add(cbEstadoCivil);

        JLabel lbNaturalidade = new JLabel("Naturalidade");
        lbNaturalidade.setBounds(420, 63, 80, 14);
        panel.add(lbNaturalidade);

        tfNaturalidade = new JTextField();
        tfNaturalidade.setBounds(420, 81, 135, 20);
        tfNaturalidade.setEditable(false);
        tfNaturalidade.setBackground(Color.WHITE);
        tfNaturalidade.addFocusListener(this);
        panel.add(tfNaturalidade);

        btConsultaCid = new JButton("...");
        btConsultaCid.setBounds(563, 78, 31, 26);
        btConsultaCid.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaCid.setToolTipText("Consulta Cidade");
        btConsultaCid.addActionListener(this);
        panel.add(btConsultaCid);

        JLabel lbNacionalidade = new JLabel("Nacionalidade");
        lbNacionalidade.setBounds(601, 63, 70, 14);
        panel.add(lbNacionalidade);

        tfNacionalidade = new JTextField();
        tfNacionalidade.setBounds(601, 81, 130, 20);
        tfNacionalidade.setEditable(false);
        tfNacionalidade.setBackground(Color.WHITE);
        tfNacionalidade.addFocusListener(this);
        panel.add(tfNacionalidade);

        JLabel lbDataNasc = new JLabel("Data Nascimento");
        lbDataNasc.setBounds(20, 105, 90, 14);
        panel.add(lbDataNasc);

        try {
            ftfDataNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException ex) {
        }
        ftfDataNasc.setBounds(20, 123, 100, 20);
        ftfDataNasc.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfDataNasc.addFocusListener(this);
        panel.add(ftfDataNasc);

        JLabel lbIdade = new JLabel("Idade");
        lbIdade.setBounds(130, 105, 50, 14);
        panel.add(lbIdade);

        tfIdade = new JTextField();
        tfIdade.setBounds(130, 123, 70, 20);
        tfIdade.setEditable(false);
        tfIdade.setBackground(Color.WHITE);
        tfIdade.addFocusListener(this);
        panel.add(tfIdade);

        JLabel lbCodEndereco = new JLabel("Cod. Endereço");
        lbCodEndereco.setBounds(210, 105, 80, 14);
        panel.add(lbCodEndereco);

        lbCodEnderecoObrig = new JLabel("");
        lbCodEnderecoObrig.setBounds(283, 108, 10, 14);
        lbCodEnderecoObrig.setForeground(Color.RED);
        panel.add(lbCodEnderecoObrig);

        tfCodEndereco = new JTextField();
        tfCodEndereco.setBounds(210, 123, 85, 20);
        tfCodEndereco.setEditable(false);
        tfCodEndereco.setBackground(Color.WHITE);
        tfCodEndereco.addFocusListener(this);
        panel.add(tfCodEndereco);

        btConsultaEndereco = new JButton("...");
        btConsultaEndereco.setBounds(303, 120, 31, 26);
        btConsultaEndereco.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaEndereco.setToolTipText("Consulta Endereço");
        btConsultaEndereco.addActionListener(this);
        panel.add(btConsultaEndereco);

        JLabel lbEndereco = new JLabel("Endereço");
        lbEndereco.setBounds(345, 105, 60, 14);
        panel.add(lbEndereco);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(345, 123, 195, 20);
        tfEndereco.setEditable(false);
        tfEndereco.setBackground(Color.WHITE);
        tfEndereco.addFocusListener(this);
        panel.add(tfEndereco);

        JLabel lbBairro = new JLabel("Bairro");
        lbBairro.setBounds(550, 105, 60, 14);
        panel.add(lbBairro);

        tfBairro = new JTextField();
        tfBairro.setBounds(550, 123, 180, 20);
        tfBairro.setEditable(false);
        tfBairro.setBackground(Color.WHITE);
        tfBairro.addFocusListener(this);
        panel.add(tfBairro);

        JLabel lbNumero = new JLabel("Número");
        lbNumero.setBounds(20, 147, 55, 14);
        panel.add(lbNumero);

        lbNumeroObrig = new JLabel("");
        lbNumeroObrig.setBounds(59, 150, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel.add(lbNumeroObrig);

        tfNumero = new JTextField();
        tfNumero.setBounds(20, 165, 70, 20);
        tfNumero.setDocument(new CamposInt());
        tfNumero.addFocusListener(this);
        panel.add(tfNumero);

        JLabel lbComplemento = new JLabel("Complemento");
        lbComplemento.setBounds(100, 147, 80, 14);
        panel.add(lbComplemento);

        tfComplemento = new JTextField();
        tfComplemento.setBounds(100, 165, 100, 20);
        tfComplemento.addFocusListener(this);
        panel.add(tfComplemento);

        JLabel lbCep = new JLabel("CEP");
        lbCep.setBounds(210, 147, 80, 14);
        panel.add(lbCep);

        tfCep = new JTextField();
        tfCep.setBounds(210, 165, 100, 20);
        tfCep.setEditable(false);
        tfCep.setBackground(Color.WHITE);
        tfCep.addFocusListener(this);
        panel.add(tfCep);

        JLabel lbCidade = new JLabel("Cidade");
        lbCidade.setBounds(320, 147, 70, 14);
        panel.add(lbCidade);

        tfCidade = new JTextField();
        tfCidade.setBounds(320, 165, 140, 20);
        tfCidade.setEditable(false);
        tfCidade.setBackground(Color.WHITE);
        tfCidade.addFocusListener(this);
        panel.add(tfCidade);

        JLabel lbEstado = new JLabel("Estado");
        lbEstado.setBounds(470, 147, 70, 14);
        panel.add(lbEstado);

        tfEstado = new JTextField();
        tfEstado.setBounds(470, 165, 140, 20);
        tfEstado.setEditable(false);
        tfEstado.setBackground(Color.WHITE);
        tfEstado.addFocusListener(this);
        panel.add(tfEstado);

        JLabel lbRegiao = new JLabel("Região");
        lbRegiao.setBounds(620, 147, 70, 14);
        panel.add(lbRegiao);

        tfRegiao = new JTextField();
        tfRegiao.setBounds(620, 165, 110, 20);
        tfRegiao.setEditable(false);
        tfRegiao.setBackground(Color.WHITE);
        tfRegiao.addFocusListener(this);
        panel.add(tfRegiao);

        JLabel lbReferencia = new JLabel("Referência");
        lbReferencia.setBounds(20, 190, 70, 14);
        panel.add(lbReferencia);

        tfReferencia = new JTextField();
        tfReferencia.setBounds(20, 208, 190, 20);
        tfReferencia.addFocusListener(this);
        panel.add(tfReferencia);

        JLabel lbEmpresa = new JLabel("Empresa");
        lbEmpresa.setBounds(220, 190, 70, 14);
        panel.add(lbEmpresa);

        tfEmpresa = new JTextField();
        tfEmpresa.setBounds(220, 208, 140, 20);
        tfEmpresa.addFocusListener(this);
        panel.add(tfEmpresa);

        JLabel lbFoneEmpresa = new JLabel("Fone Empresa");
        lbFoneEmpresa.setBounds(370, 190, 90, 14);
        panel.add(lbFoneEmpresa);

        try {
            ftfFoneEmpresa = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfFoneEmpresa.setBounds(370, 208, 100, 20);
        ftfFoneEmpresa.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfFoneEmpresa.addFocusListener(this);
        panel.add(ftfFoneEmpresa);

        JLabel lbProfissao = new JLabel("Profissão");
        lbProfissao.setBounds(480, 190, 70, 14);
        panel.add(lbProfissao);

        tfProfissao = new JTextField();
        tfProfissao.setBounds(480, 208, 140, 20);
        tfProfissao.addFocusListener(this);
        panel.add(tfProfissao);

        JLabel lbRenda = new JLabel("Renda");
        lbRenda.setBounds(630, 190, 60, 14);
        panel.add(lbRenda);

        tfRenda = new JTextField();
        tfRenda.setBounds(630, 208, 100, 20);
        tfRenda.addFocusListener(this);
        panel.add(tfRenda);

        JLabel lbFone = new JLabel("Fone");
        lbFone.setBounds(20, 233, 40, 14);
        panel.add(lbFone);

        lbFoneObrig = new JLabel("");
        lbFoneObrig.setBounds(45, 236, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel.add(lbFoneObrig);

        try {
            ftfFone = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfFone.setBounds(20, 251, 100, 20);
        ftfFone.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfFone.addFocusListener(this);
        panel.add(ftfFone);

        JLabel lbFax = new JLabel("Fax");
        lbFax.setBounds(130, 233, 50, 14);
        panel.add(lbFax);

        try {
            ftfFax = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfFax.setBounds(130, 251, 100, 20);
        ftfFax.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfFax.addFocusListener(this);
        panel.add(ftfFax);

        JLabel lbCelular1 = new JLabel("Celular 1");
        lbCelular1.setBounds(240, 233, 60, 14);
        panel.add(lbCelular1);

        try {
            ftfCelular1 = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfCelular1.setBounds(240, 251, 100, 20);
        ftfCelular1.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCelular1.addFocusListener(this);
        panel.add(ftfCelular1);

        JLabel lbCelular2 = new JLabel("Celular 2");
        lbCelular2.setBounds(350, 233, 60, 14);
        panel.add(lbCelular2);

        try {
            ftfCelular2 = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfCelular2.setBounds(350, 251, 100, 20);
        ftfCelular2.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCelular2.addFocusListener(this);
        panel.add(ftfCelular2);

        JLabel lbEmail = new JLabel("E-Mail");
        lbEmail.setBounds(460, 233, 50, 14);
        panel.add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(460, 251, 270, 20);
        tfEmail.addFocusListener(this);
        panel.add(tfEmail);

        JLabel lbMsn = new JLabel("MSN");
        lbMsn.setBounds(20, 276, 40, 14);
        panel.add(lbMsn);

        tfMsn = new JTextField();
        tfMsn.setBounds(20, 294, 270, 20);
        tfMsn.addFocusListener(this);
        panel.add(tfMsn);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(300, 276, 55, 14);
        panel.add(lbDescricao);

        tfDescricao = new JTextField();
        tfDescricao.setBounds(300, 294, 430, 20);
        tfDescricao.addFocusListener(this);
        panel.add(tfDescricao);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 335, 750, 3);
        panel.add(separator);

        Icon icOk = new ImageIcon("OK.png");
        btOk = new JButton("OK", icOk);
        btOk.setBounds(160, 350, 70, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("Confirma Operação");
        btOk.addActionListener(this);
        panel.add(btOk);

        Icon icCancelar = new ImageIcon("Cancelar.png");
        btCancelar = new JButton("Cancelar", icCancelar);
        btCancelar.setBounds(270, 350, 70, 26);
        btCancelar.setMargin(new Insets(0, 0, 0, 0));
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Limpar os Campos");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        Icon icExcluir = new ImageIcon("Excluir.png");
        btExcluir = new JButton("Excluir", icExcluir);
        btExcluir.setBounds(380, 350, 70, 26);
        btExcluir.setMargin(new Insets(0, 0, 0, 0));
        btExcluir.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.setToolTipText("Excluir Registro");
        btExcluir.addActionListener(this);
        panel.add(btExcluir);

        Icon icSair = new ImageIcon("Sair.png");
        btSair = new JButton("Sair", icSair);
        btSair.setBounds(490, 350, 70, 26);
        btSair.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btSair.setToolTipText("Fechar");
        btSair.addActionListener(this);
        panel.add(btSair);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(778, 440);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controleClie.getProxCodClie()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfNome.setText("");
        cbTipoPessoa.setSelectedItem("Selecione");
        cbSexo.setSelectedItem("Selecione");
        cbEstadoCivil.setSelectedItem("Selecione");
        tfCodEndereco.setText("");
        tfNaturalidade.setText("");
        tfNacionalidade.setText("");
        ftfDataNasc.setText("");
        tfIdade.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfComplemento.setText("");
        tfCep.setText("");
        tfCidade.setText("");
        tfEstado.setText("");
        tfRegiao.setText("");
        tfReferencia.setText("");
        tfEmpresa.setText("");
        ftfFoneEmpresa.setText("");
        tfProfissao.setText("");
        tfRenda.setText("");
        ftfFone.setText("");
        ftfFax.setText("");
        ftfCelular1.setText("");
        ftfCelular2.setText("");
        tfEmail.setText("");
        tfMsn.setText("");
        tfDescricao.setText("");
        lbNomeObrig.setText("");
        lbTipoPessoaObrig.setText("");
        lbCpfCnpjObrig.setText("");
        lbRgIeObrig.setText("");
        lbCodEnderecoObrig.setText("");
        lbNumeroObrig.setText("");
        lbFoneObrig.setText("");
        codCidade = -1;
    }

    private void gravar() throws Exception {
        boolean flag = false;
        if ("".equals(tfNome.getText().trim())) {
            lbNomeObrig.setText("*");
            flag = true;
        } else {
            lbNomeObrig.setText("");
        }
        if ("Selecione".equals(cbTipoPessoa.getSelectedItem())) {
            lbTipoPessoaObrig.setText("*");
            lbCpfCnpjObrig.setText("");
            lbRgIeObrig.setText("");
            flag = true;
        } else {
            lbTipoPessoaObrig.setText("");
            if ("  .   .   /    -  ".equals(ftfCpfCnpj.getText()) || "   .   .   -  ".equals(ftfCpfCnpj.getText())) {
                lbCpfCnpjObrig.setText("*");
                flag = true;
            } else {
                lbCpfCnpjObrig.setText("");
            }
            if ("   .   .   ".equals(ftfRgIe.getText())) {
                lbRgIeObrig.setText("*");
                flag = true;
            } else {
                lbRgIeObrig.setText("");
            }
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
        if ("(  )    -    ".equals(ftfFone.getText())) {
            lbFoneObrig.setText("*");
            flag = true;
        } else {
            lbFoneObrig.setText("");
        }
        if (!flag) {
            if (!"".equals(tfRenda.getText().trim())) {
                validaDouble(tfRenda, "Campo renda inválido", "Valor da renda deve ser superior a 99", 100);
            }
            if (!validaData(ftfDataNasc)) {
                throw new Exception("Campo data nascimento inválido");
            }
            Cliente cliente = new Cliente();
            cliente.setCodClie(Integer.parseInt(tfCodigo.getText()));
            cliente.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            cliente.setUltAlteracao(new Date());
            cliente.setNome(tfNome.getText());
            cliente.setTipoPessoa((String) cbTipoPessoa.getSelectedItem());
            cliente.setCpfCnpj(ftfCpfCnpj.getText());
            cliente.setRgIe(ftfRgIe.getText());
            cliente.setSexo((String) cbSexo.getSelectedItem());
            cliente.setEstadoCivil((String) cbEstadoCivil.getSelectedItem());
            Cidade cidade = new Cidade();
            cidade.setCodCidade(codCidade);
            cliente.setCidad(cidade);
            if (!"  /  /    ".equals(ftfDataNasc.getText())) {
                cliente.setDataNascimento(formatDate.parse(ftfDataNasc.getText()));
            }
            cliente.setCodEndereco(Integer.parseInt(tfCodEndereco.getText()));
            cliente.setNumero(Integer.parseInt(tfNumero.getText()));
            cliente.setComplemento(tfComplemento.getText());
            cliente.setReferencia(tfReferencia.getText());
            cliente.setEmpresa(tfEmpresa.getText());
            cliente.setFoneEmpresa(ftfFoneEmpresa.getText());
            cliente.setProfissao(tfProfissao.getText());
            if ("".equals(tfRenda.getText().trim())) {
                cliente.setRenda(-1);
            } else {
                cliente.setRenda(Double.parseDouble(tfRenda.getText()));
            }
            cliente.setFone(ftfFone.getText());
            cliente.setFax(ftfFax.getText());
            cliente.setCelular1(ftfCelular1.getText());
            cliente.setCelular2(ftfCelular2.getText());
            cliente.setEmail(tfEmail.getText());
            cliente.setMsn(tfMsn.getText());
            cliente.setDescricao(tfDescricao.getText());
            int codClieCadas = controleClie.getClieCadastrado(ftfCpfCnpj.getText(), Integer.parseInt(tfCodigo.getText()));// verifica se cliente já cadastrado
            if (codClieCadas != -1) {
                if (JOptionPane.showConfirmDialog(null, "Confirmar alteração nos dados", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (controleClie.updateCliente(cliente)) {
                        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            } else {
                codClieCadas = controleClie.getClieCadastrado(ftfCpfCnpj.getText());// verifica se pessoa já cadastrada
                if (codClieCadas != -1) {
                    JOptionPane.showMessageDialog(null, "Este CPF / CNPJ ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (controleClie.insertClie(cliente)) {
                        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            }
        } else {
            throw new Exception("Campos * obrigatórios inválidos");
        }
    }

    private void consultaClie() throws Exception {
        if (controleClie.isClieVazio()) {
            throw new Exception("Não há clientes cadastrados");
        }
        ConsultaClie consulta = new ConsultaClie(controleClie);
        consulta.setListener(new ListenerClie() {

            @Override
            public void exibeClie(Cliente cliente) {
                limparTela();
                tfCodigo.setText(Integer.toString(cliente.getCodClie()));
                tfDataCadas.setText(formatDate.format(cliente.getDataCadastro()) + " as " + formatHora.format(cliente.getDataCadastro()));
                tfUltAlteracao.setText(formatDate.format(cliente.getUltAlteracao()) + " as " + formatHora.format(cliente.getUltAlteracao()));
                tfNome.setText(cliente.getNome());
                cbTipoPessoa.setSelectedItem(cliente.getTipoPessoa());
                ftfCpfCnpj.setText(cliente.getCpfCnpj());
                ftfRgIe.setText(cliente.getRgIe());
                cbSexo.setSelectedItem(cliente.getSexo());
                cbEstadoCivil.setSelectedItem(cliente.getEstadoCivil());
                codCidade = cliente.getCidad().getCodCidade();
                if (codCidade != -1) {
                    tfNaturalidade.setText(cliente.getCidad().getCidade());
                    tfNacionalidade.setText(cliente.getCidad().getPais());
                }
                if (cliente.getDataNascimento() != null) {
                    ftfDataNasc.setText(formatDate.format(cliente.getDataNascimento()));
                }
                tfCodEndereco.setText(Integer.toString(cliente.getCodEndereco()));
                tfEndereco.setText(cliente.getEndereco());
                tfBairro.setText(cliente.getBairro());
                tfNumero.setText(Integer.toString(cliente.getNumero()));
                tfComplemento.setText(cliente.getComplemento());
                tfCep.setText(cliente.getCep());
                tfCidade.setText(cliente.getCidade());
                tfEstado.setText(cliente.getEstado());
                tfRegiao.setText(cliente.getRegiao());
                tfReferencia.setText(cliente.getReferencia());
                tfEmpresa.setText(cliente.getEmpresa());
                ftfFoneEmpresa.setText(cliente.getFoneEmpresa());
                tfProfissao.setText(cliente.getProfissao());
                if (cliente.getRenda() != -1) {
                    tfRenda.setText(Double.toString(cliente.getRenda()));
                }
                ftfFone.setText(cliente.getFone());
                ftfFax.setText(cliente.getFax());
                ftfCelular1.setText(cliente.getCelular1());
                ftfCelular2.setText(cliente.getCelular2());
                tfEmail.setText(cliente.getEmail());
                tfMsn.setText(cliente.getMsn());
                tfDescricao.setText(cliente.getDescricao());
                tfIdade.setText(calculoIdade(ftfDataNasc, formatDate));
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (controleClie.isClieVazio()) {
            throw new Exception("Não há clientes cadastrados");
        }
        if (!controleClie.isClieCadastrado(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione um cliente");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse cliente", "Cliente", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (controleClie.deleteClie(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            }
        }
    }

    private void naturalidadeNacionalidade() throws Exception {
        if (controleCidade.isCidadeVazio()) {
            throw new Exception("Não há cidades cadastradas");
        }
        ConsultaCidade consulta = new ConsultaCidade(controleCidade);
        consulta.setListener(new ListenerCidade() {

            @Override
            public void exibeCidade(Cidade cidade) {
                codCidade = cidade.getCodCidade();
                tfNaturalidade.setText(cidade.getCidade());
                tfNacionalidade.setText(cidade.getPais());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
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

    private void alteraMascara() {
        if (!"Selecione".equals(cbTipoPessoa.getSelectedItem())) {
            try {
                ftfRgIe.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###"))); // configura e aplica uma nova mascara
                ftfRgIe.setValue(null);
                ftfRgIe.setEditable(true);
            } catch (ParseException ex) {
            }
            if ("Física".equals(cbTipoPessoa.getSelectedItem())) {
                try {
                    ftfCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##"))); // configura e aplica uma nova mascara
                    ftfCpfCnpj.setValue(null);
                    ftfCpfCnpj.setEditable(true);
                } catch (ParseException ex) {
                }
            } else {
                try {
                    ftfCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##"))); // configura e aplica uma nova mascara
                    ftfCpfCnpj.setValue(null);
                    ftfCpfCnpj.setEditable(true);
                } catch (ParseException ex) {
                }
            }
        } else {
            ftfCpfCnpj.setFormatterFactory(null); // desabilita a mascara aplicada
            ftfRgIe.setFormatterFactory(null); // desabilita a mascara aplicada
            ftfCpfCnpj.setText("");
            ftfRgIe.setText("");
            ftfCpfCnpj.setEditable(false);
            ftfRgIe.setEditable(false);
        }
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
                consultaClie();
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
        if (evento.getSource() == btConsultaCid) {
            try {
                naturalidadeNacionalidade();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == cbTipoPessoa) {
            alteraMascara();
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
        if (evento.getSource() == cbTipoPessoa) {
            cbTipoPessoa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCpfCnpj) {
            ftfCpfCnpj.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfRgIe) {
            ftfRgIe.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbSexo) {
            cbSexo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbEstadoCivil) {
            cbEstadoCivil.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodEndereco) {
            tfCodEndereco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNaturalidade) {
            tfNaturalidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNacionalidade) {
            tfNacionalidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataNasc) {
            ftfDataNasc.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfIdade) {
            tfIdade.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfComplemento) {
            tfComplemento.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfEmpresa) {
            tfEmpresa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFoneEmpresa) {
            ftfFoneEmpresa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfProfissao) {
            tfProfissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfRenda) {
            tfRenda.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFone) {
            ftfFone.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFax) {
            ftfFax.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCelular1) {
            ftfCelular1.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCelular2) {
            ftfCelular2.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmail) {
            tfEmail.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMsn) {
            tfMsn.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfNome.setBackground(Color.WHITE);
        cbTipoPessoa.setBackground(Color.WHITE);
        if (evento.getSource() == ftfCpfCnpj) {
            ftfCpfCnpj.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfCpfCnpj);
        }
        if (evento.getSource() == ftfRgIe) {
            ftfRgIe.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfRgIe);
        }
        cbSexo.setBackground(Color.WHITE);
        cbEstadoCivil.setBackground(Color.WHITE);
        tfCodEndereco.setBackground(Color.WHITE);
        tfNaturalidade.setBackground(Color.WHITE);
        tfNacionalidade.setBackground(Color.WHITE);
        if (evento.getSource() == ftfDataNasc) {
            ftfDataNasc.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfDataNasc);
            tfIdade.setText(calculoIdade(ftfDataNasc, formatDate));
        }
        tfIdade.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfComplemento.setBackground(Color.WHITE);
        tfCep.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfEstado.setBackground(Color.WHITE);
        tfRegiao.setBackground(Color.WHITE);
        tfReferencia.setBackground(Color.WHITE);
        tfEmpresa.setBackground(Color.WHITE);
        if (evento.getSource() == ftfFoneEmpresa) {
            ftfFoneEmpresa.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfFoneEmpresa);
        }
        tfProfissao.setBackground(Color.WHITE);
        tfRenda.setBackground(Color.WHITE);
        if (evento.getSource() == ftfFone) {
            ftfFone.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfFone);
        }
        if (evento.getSource() == ftfFax) {
            ftfFax.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfFax);
        }
        if (evento.getSource() == ftfCelular1) {
            ftfCelular1.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfCelular1);
        }
        if (evento.getSource() == ftfCelular2) {
            ftfCelular2.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfCelular2);
        }
        tfEmail.setBackground(Color.WHITE);
        tfMsn.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
    }
}

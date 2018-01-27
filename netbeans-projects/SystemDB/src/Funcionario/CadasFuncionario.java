package Funcionario;

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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Departamento.ConsultaDepartamentos;
import Departamento.DepartamentoControl;
import Departamento.ListenerDepartamento;
import Endereco.ConsultaEndereco;
import Endereco.EnderecoControl;
import Endereco.ListenerEndereco;
import Endereco.Cidade.CidadeControl;
import Endereco.Cidade.ConsultaCidade;
import Endereco.Cidade.ListenerCidade;
import Modelo.Cidade;
import Modelo.Departamento;
import Modelo.Endereco;
import Modelo.Funcionario;
import Modelo.Setor;
import Principal.CamposInt;
import Principal.Controle;
import Seguranca.Seguranca;
import Setor.ConsultaSetores;
import Setor.ListenerSetor;
import Setor.SetorControl;

public class CadasFuncionario extends Controle implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome, tfIdade,
            tfNaturalidade, tfNacionalidade, tfCodEndereco, tfNomePai,
            tfNomeMae, tfEndereco, tfBairro, tfNumero, tfComplemento, tfCep,
            tfCidade, tfEstado, tfRegiao, tfReferencia, tfEmail, tfMsn,
            tfSkype, tfNumContrato, tfTipoContrato, tfCodSetor, tfSetor,
            tfCodDepart, tfDepartamento, tfCargo, tfBanco, tfSalarioBase,
            tfDescricao;
    private JFormattedTextField ftfCpf, ftfRg, ftfDataNasc, ftfFone,
            ftfCelular1, ftfCelular2, ftfDataAdmissao, ftfNumPis,
            ftfNumTituloEleitor, ftfNumCartHabilitacao, ftfNumCartTrab,
            ftfNumCertReservista, ftfHoraIni, ftfHoraFim, ftfDiaPagto,
            ftfContaBanco, ftfDataDemissao;
    private JComboBox cbSexo, cbEstadoCivil, cbTurno;
    private JButton btConsulta, btConsultaCid, btConsultaEndereco,
            btConsultaSetor, btConsultaDepart, btOk, btCancelar, btExcluir,
            btSair;
    private JLabel lbNomeObrig, lbCpfObrig, lbRgObrig, lbSexoObrig,
            lbEstadoCivilObrig, lbDataNascObrig, lbNaturalidadeObrig,
            lbCodEnderecoObrig, lbNumeroObrig, lbComplementoObrig, lbFoneObrig,
            lbDataAdmissaoObrig, lbNumCartTrabObrig, lbCodSetorObrig,
            lbCodDepartObrig, lbCargoObrig, lbTurnoObrig, lbHoraIniObrig,
            lbHoraFimObrig, lbDiaPagtoObrig, lbSalarioBaseObrig, lbAtivoObrig;
    private JCheckBox chAtivo;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;
    private SimpleDateFormat formatDateHora;
    private int codCidade = -1;
    private FuncionarioControl controleFunc;
    private CidadeControl controleCidade;
    private EnderecoControl controleEndereco;
    private SetorControl controleSetor;
    private DepartamentoControl controleDep;
    private Seguranca seguranca;

    public CadasFuncionario(Connection con, Seguranca seguranca) {
        this.seguranca = seguranca;
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        controleFunc = new FuncionarioControl(con);
        controleCidade = new CidadeControl(con);
        controleEndereco = new EnderecoControl(con);
        controleSetor = new SetorControl(con);
        controleDep = new DepartamentoControl(con, seguranca);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Funcionários");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 870, 475);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        add(panel);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 20, 60, 14);
        panel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controleFunc.getProxCodFunc()));
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
        btConsulta.setToolTipText("Consulta Funcionários");
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
        tfNome.setBounds(400, 38, 230, 20);
        tfNome.addFocusListener(this);
        panel.add(tfNome);

        JLabel lbCpf = new JLabel("CPF");
        lbCpf.setBounds(640, 20, 40, 14);
        panel.add(lbCpf);

        lbCpfObrig = new JLabel("");
        lbCpfObrig.setBounds(661, 23, 10, 14);
        lbCpfObrig.setForeground(Color.RED);
        panel.add(lbCpfObrig);

        try {
            ftfCpf = new JFormattedTextField(
                    new MaskFormatter("###.###.###-##"));
        } catch (ParseException ex) {
        }
        ftfCpf.setBounds(640, 38, 100, 20);
        ftfCpf.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCpf.addFocusListener(this);
        panel.add(ftfCpf);

        JLabel lbRg = new JLabel("RG");
        lbRg.setBounds(750, 20, 40, 14);
        panel.add(lbRg);

        lbRgObrig = new JLabel("");
        lbRgObrig.setBounds(766, 23, 10, 14);
        lbRgObrig.setForeground(Color.RED);
        panel.add(lbRgObrig);

        try {
            ftfRg = new JFormattedTextField(new MaskFormatter("###.###.###"));
        } catch (ParseException ex) {
        }
        ftfRg.setBounds(750, 38, 100, 20);
        ftfRg.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfRg.addFocusListener(this);
        panel.add(ftfRg);

        JLabel lbSexo = new JLabel("Sexo");
        lbSexo.setBounds(20, 63, 50, 14);
        panel.add(lbSexo);

        lbSexoObrig = new JLabel("");
        lbSexoObrig.setBounds(47, 66, 10, 14);
        lbSexoObrig.setForeground(Color.RED);
        panel.add(lbSexoObrig);

        cbSexo = new JComboBox();
        cbSexo.setBounds(20, 81, 90, 20);
        cbSexo.addItem("Selecione");
        cbSexo.addItem("Masculino");
        cbSexo.addItem("Feminino");
        cbSexo.addFocusListener(this);
        panel.add(cbSexo);

        JLabel lbEstadoCivil = new JLabel("Estado Civil");
        lbEstadoCivil.setBounds(120, 63, 80, 14);
        panel.add(lbEstadoCivil);

        lbEstadoCivilObrig = new JLabel("");
        lbEstadoCivilObrig.setBounds(177, 66, 10, 14);
        lbEstadoCivilObrig.setForeground(Color.RED);
        panel.add(lbEstadoCivilObrig);

        cbEstadoCivil = new JComboBox();
        cbEstadoCivil.setBounds(120, 81, 90, 20);
        cbEstadoCivil.addItem("Selecione");
        cbEstadoCivil.addItem("Solteiro");
        cbEstadoCivil.addItem("Casado");
        cbEstadoCivil.addItem("Separado");
        cbEstadoCivil.addItem("Divorciado");
        cbEstadoCivil.addItem("Viúvo");
        cbEstadoCivil.addFocusListener(this);
        panel.add(cbEstadoCivil);

        JLabel lbDataNasc = new JLabel("Data Nascimento");
        lbDataNasc.setBounds(220, 63, 90, 14);
        panel.add(lbDataNasc);

        lbDataNascObrig = new JLabel("");
        lbDataNascObrig.setBounds(304, 66, 10, 14);
        lbDataNascObrig.setForeground(Color.RED);
        panel.add(lbDataNascObrig);

        try {
            ftfDataNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException ex) {
        }
        ftfDataNasc.setBounds(220, 81, 100, 20);
        ftfDataNasc.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfDataNasc.addFocusListener(this);
        panel.add(ftfDataNasc);

        JLabel lbIdade = new JLabel("Idade");
        lbIdade.setBounds(330, 63, 50, 14);
        panel.add(lbIdade);

        tfIdade = new JTextField();
        tfIdade.setBounds(330, 81, 70, 20);
        tfIdade.setEditable(false);
        tfIdade.setBackground(Color.WHITE);
        tfIdade.addFocusListener(this);
        panel.add(tfIdade);

        JLabel lbNaturalidade = new JLabel("Naturalidade");
        lbNaturalidade.setBounds(410, 63, 70, 14);
        panel.add(lbNaturalidade);

        lbNaturalidadeObrig = new JLabel("");
        lbNaturalidadeObrig.setBounds(474, 66, 10, 14);
        lbNaturalidadeObrig.setForeground(Color.RED);
        panel.add(lbNaturalidadeObrig);

        tfNaturalidade = new JTextField();
        tfNaturalidade.setBounds(410, 81, 140, 20);
        tfNaturalidade.setEditable(false);
        tfNaturalidade.setBackground(Color.WHITE);
        tfNaturalidade.addFocusListener(this);
        panel.add(tfNaturalidade);

        btConsultaCid = new JButton("...");
        btConsultaCid.setBounds(557, 78, 31, 26);
        btConsultaCid.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaCid.setToolTipText("Consulta Cidade");
        btConsultaCid.addActionListener(this);
        panel.add(btConsultaCid);

        JLabel lbNacionalidade = new JLabel("Nacionalidade");
        lbNacionalidade.setBounds(595, 63, 70, 14);
        panel.add(lbNacionalidade);

        tfNacionalidade = new JTextField();
        tfNacionalidade.setBounds(595, 81, 130, 20);
        tfNacionalidade.setEditable(false);
        tfNacionalidade.setBackground(Color.WHITE);
        tfNacionalidade.addFocusListener(this);
        panel.add(tfNacionalidade);

        JLabel lbCodEndereco = new JLabel("Cod. Endereço");
        lbCodEndereco.setBounds(734, 63, 75, 14);
        panel.add(lbCodEndereco);

        lbCodEnderecoObrig = new JLabel("");
        lbCodEnderecoObrig.setBounds(809, 66, 10, 14);
        lbCodEnderecoObrig.setForeground(Color.RED);
        panel.add(lbCodEnderecoObrig);

        tfCodEndereco = new JTextField();
        tfCodEndereco.setBounds(734, 81, 80, 20);
        tfCodEndereco.setEditable(false);
        tfCodEndereco.setBackground(Color.WHITE);
        tfCodEndereco.addFocusListener(this);
        panel.add(tfCodEndereco);

        btConsultaEndereco = new JButton("...");
        btConsultaEndereco.setBounds(820, 78, 31, 26);
        btConsultaEndereco.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaEndereco.setToolTipText("Consulta Endereço");
        btConsultaEndereco.addActionListener(this);
        panel.add(btConsultaEndereco);

        JLabel lbNomePai = new JLabel("Nome Pai");
        lbNomePai.setBounds(20, 105, 70, 14);
        panel.add(lbNomePai);

        tfNomePai = new JTextField();
        tfNomePai.setBounds(20, 123, 230, 20);
        tfNomePai.addFocusListener(this);
        panel.add(tfNomePai);

        JLabel lbNomeMae = new JLabel("Nome Mãe");
        lbNomeMae.setBounds(260, 105, 70, 14);
        panel.add(lbNomeMae);

        tfNomeMae = new JTextField();
        tfNomeMae.setBounds(260, 123, 230, 20);
        tfNomeMae.addFocusListener(this);
        panel.add(tfNomeMae);

        JLabel lbEndereco = new JLabel("Endereço");
        lbEndereco.setBounds(500, 105, 60, 14);
        panel.add(lbEndereco);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(500, 123, 180, 20);
        tfEndereco.setEditable(false);
        tfEndereco.setBackground(Color.WHITE);
        tfEndereco.addFocusListener(this);
        panel.add(tfEndereco);

        JLabel lbBairro = new JLabel("Bairro");
        lbBairro.setBounds(690, 105, 60, 14);
        panel.add(lbBairro);

        tfBairro = new JTextField();
        tfBairro.setBounds(690, 123, 160, 20);
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

        lbComplementoObrig = new JLabel("");
        lbComplementoObrig.setBounds(167, 150, 10, 14);
        lbComplementoObrig.setForeground(Color.RED);
        panel.add(lbComplementoObrig);

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
        tfRegiao.setBounds(620, 165, 100, 20);
        tfRegiao.setEditable(false);
        tfRegiao.setBackground(Color.WHITE);
        tfRegiao.addFocusListener(this);
        panel.add(tfRegiao);

        JLabel lbReferencia = new JLabel("Referência");
        lbReferencia.setBounds(730, 147, 70, 14);
        panel.add(lbReferencia);

        tfReferencia = new JTextField();
        tfReferencia.setBounds(730, 165, 120, 20);
        tfReferencia.addFocusListener(this);
        panel.add(tfReferencia);

        JLabel lbFone = new JLabel("Fone");
        lbFone.setBounds(20, 190, 40, 14);
        panel.add(lbFone);

        lbFoneObrig = new JLabel("");
        lbFoneObrig.setBounds(46, 193, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel.add(lbFoneObrig);

        try {
            ftfFone = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfFone.setBounds(20, 208, 100, 20);
        ftfFone.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfFone.addFocusListener(this);
        panel.add(ftfFone);

        JLabel lbCelular1 = new JLabel("Celular 1");
        lbCelular1.setBounds(130, 190, 60, 14);
        panel.add(lbCelular1);

        try {
            ftfCelular1 = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfCelular1.setBounds(130, 208, 100, 20);
        ftfCelular1.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCelular1.addFocusListener(this);
        panel.add(ftfCelular1);

        JLabel lbCelular2 = new JLabel("Celular 2");
        lbCelular2.setBounds(240, 190, 60, 14);
        panel.add(lbCelular2);

        try {
            ftfCelular2 = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfCelular2.setBounds(240, 208, 100, 20);
        ftfCelular2.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCelular2.addFocusListener(this);
        panel.add(ftfCelular2);

        JLabel lbEmail = new JLabel("E-Mail");
        lbEmail.setBounds(350, 190, 50, 14);
        panel.add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(350, 208, 245, 20);
        tfEmail.addFocusListener(this);
        panel.add(tfEmail);

        JLabel lbMsn = new JLabel("MSN");
        lbMsn.setBounds(605, 190, 40, 14);
        panel.add(lbMsn);

        tfMsn = new JTextField();
        tfMsn.setBounds(605, 208, 245, 20);
        tfMsn.addFocusListener(this);
        panel.add(tfMsn);

        JLabel lbSkype = new JLabel("Skype");
        lbSkype.setBounds(20, 233, 40, 14);
        panel.add(lbSkype);

        tfSkype = new JTextField();
        tfSkype.setBounds(20, 251, 245, 20);
        tfSkype.addFocusListener(this);
        panel.add(tfSkype);

        JLabel lbDataAdmissao = new JLabel("Data Admissão");
        lbDataAdmissao.setBounds(275, 233, 100, 14);
        panel.add(lbDataAdmissao);

        lbDataAdmissaoObrig = new JLabel("");
        lbDataAdmissaoObrig.setBounds(350, 236, 10, 14);
        lbDataAdmissaoObrig.setForeground(Color.RED);
        panel.add(lbDataAdmissaoObrig);

        try {
            ftfDataAdmissao = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException ex) {
        }
        ftfDataAdmissao.setBounds(275, 251, 100, 20);
        ftfDataAdmissao.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfDataAdmissao.addFocusListener(this);
        panel.add(ftfDataAdmissao);

        JLabel lbNumContrato = new JLabel("N° Contrato");
        lbNumContrato.setBounds(385, 233, 60, 14);
        panel.add(lbNumContrato);

        tfNumContrato = new JTextField();
        tfNumContrato.setBounds(385, 251, 110, 20);
        tfNumContrato.addFocusListener(this);
        panel.add(tfNumContrato);

        JLabel lbTipoContrato = new JLabel("Tipo Contrato");
        lbTipoContrato.setBounds(505, 233, 75, 14);
        panel.add(lbTipoContrato);

        tfTipoContrato = new JTextField();
        tfTipoContrato.setBounds(505, 251, 110, 20);
        tfTipoContrato.addFocusListener(this);
        panel.add(tfTipoContrato);

        JLabel lbNumPis = new JLabel("N° PIS");
        lbNumPis.setBounds(625, 233, 75, 14);
        panel.add(lbNumPis);

        try {
            ftfNumPis = new JFormattedTextField(new MaskFormatter("###.#####.##-#"));
        } catch (ParseException ex) {
        }
        ftfNumPis.setBounds(625, 251, 100, 20);
        ftfNumPis.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfNumPis.addFocusListener(this);
        panel.add(ftfNumPis);

        JLabel lbNumCartHabilitacao = new JLabel("N° Carteira Habilitação");
        lbNumCartHabilitacao.setBounds(735, 233, 110, 14);
        panel.add(lbNumCartHabilitacao);

        try {
            ftfNumCartHabilitacao = new JFormattedTextField(new MaskFormatter("###########"));
        } catch (ParseException ex) {
        }
        ftfNumCartHabilitacao.setBounds(735, 251, 115, 20);
        ftfNumCartHabilitacao.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfNumCartHabilitacao.addFocusListener(this);
        panel.add(ftfNumCartHabilitacao);

        JLabel lbNumTituloEleitor = new JLabel("N° Título Eleitor / Zona / Seção");
        lbNumTituloEleitor.setBounds(20, 276, 150, 14);
        panel.add(lbNumTituloEleitor);

        try {
            ftfNumTituloEleitor = new JFormattedTextField(new MaskFormatter("####.####.####/###/###"));
        } catch (ParseException ex) {
        }
        ftfNumTituloEleitor.setBounds(20, 294, 160, 20);
        ftfNumTituloEleitor.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfNumTituloEleitor.addFocusListener(this);
        panel.add(ftfNumTituloEleitor);

        JLabel lbNumCartTrab = new JLabel("N° Cart. Trab. / Série");
        lbNumCartTrab.setBounds(190, 276, 140, 14);
        panel.add(lbNumCartTrab);

        lbNumCartTrabObrig = new JLabel("");
        lbNumCartTrabObrig.setBounds(298, 279, 10, 14);
        lbNumCartTrabObrig.setForeground(Color.RED);
        panel.add(lbNumCartTrabObrig);

        try {
            ftfNumCartTrab = new JFormattedTextField(new MaskFormatter("#######/###-#"));
        } catch (ParseException ex) {
        }
        ftfNumCartTrab.setBounds(190, 294, 140, 20);
        ftfNumCartTrab.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfNumCartTrab.addFocusListener(this);
        panel.add(ftfNumCartTrab);

        JLabel lbNumCertReservista = new JLabel("N° Cert. Reservista");
        lbNumCertReservista.setBounds(340, 276, 100, 14);
        panel.add(lbNumCertReservista);

        try {
            ftfNumCertReservista = new JFormattedTextField(new MaskFormatter("######"));
        } catch (ParseException ex) {
        }
        ftfNumCertReservista.setBounds(340, 294, 100, 20);
        ftfNumCertReservista.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfNumCertReservista.addFocusListener(this);
        panel.add(ftfNumCertReservista);

        JLabel lbCodSetor = new JLabel("Cod. Setor");
        lbCodSetor.setBounds(450, 276, 61, 14);
        panel.add(lbCodSetor);

        lbCodSetorObrig = new JLabel("");
        lbCodSetorObrig.setBounds(503, 279, 10, 14);
        lbCodSetorObrig.setForeground(Color.RED);
        panel.add(lbCodSetorObrig);

        tfCodSetor = new JTextField();
        tfCodSetor.setBounds(450, 294, 60, 20);
        tfCodSetor.setEditable(false);
        tfCodSetor.setBackground(Color.WHITE);
        tfCodSetor.addFocusListener(this);
        panel.add(tfCodSetor);

        btConsultaSetor = new JButton("...");
        btConsultaSetor.setBounds(517, 291, 31, 26);
        btConsultaSetor.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaSetor.setToolTipText("Consulta Setores");
        btConsultaSetor.addActionListener(this);
        panel.add(btConsultaSetor);

        JLabel lbSetor = new JLabel("Setor");
        lbSetor.setBounds(555, 276, 50, 14);
        panel.add(lbSetor);

        tfSetor = new JTextField();
        tfSetor.setBounds(555, 294, 180, 20);
        tfSetor.setEditable(false);
        tfSetor.setBackground(Color.WHITE);
        tfSetor.addFocusListener(this);
        panel.add(tfSetor);

        JLabel lbCodDepart = new JLabel("Cod. Depart.");
        lbCodDepart.setBounds(745, 276, 70, 14);
        panel.add(lbCodDepart);

        lbCodDepartObrig = new JLabel("");
        lbCodDepartObrig.setBounds(809, 279, 10, 14);
        lbCodDepartObrig.setForeground(Color.RED);
        panel.add(lbCodDepartObrig);

        tfCodDepart = new JTextField();
        tfCodDepart.setBounds(745, 294, 70, 20);
        tfCodDepart.setEditable(false);
        tfCodDepart.setBackground(Color.WHITE);
        tfCodDepart.addFocusListener(this);
        panel.add(tfCodDepart);

        btConsultaDepart = new JButton("...");
        btConsultaDepart.setBounds(820, 291, 31, 26);
        btConsultaDepart.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaDepart.setToolTipText("Consulta Departamentos");
        btConsultaDepart.addActionListener(this);
        panel.add(btConsultaDepart);

        JLabel lbDepartamento = new JLabel("Departamento");
        lbDepartamento.setBounds(20, 319, 70, 14);
        panel.add(lbDepartamento);

        tfDepartamento = new JTextField();
        tfDepartamento.setBounds(20, 337, 180, 20);
        tfDepartamento.setEditable(false);
        tfDepartamento.setBackground(Color.WHITE);
        tfDepartamento.addFocusListener(this);
        panel.add(tfDepartamento);

        JLabel lbCargo = new JLabel("Cargo");
        lbCargo.setBounds(210, 319, 50, 14);
        panel.add(lbCargo);

        lbCargoObrig = new JLabel("");
        lbCargoObrig.setBounds(241, 322, 10, 14);
        lbCargoObrig.setForeground(Color.RED);
        panel.add(lbCargoObrig);

        tfCargo = new JTextField();
        tfCargo.setBounds(210, 337, 180, 20);
        tfCargo.addFocusListener(this);
        panel.add(tfCargo);

        JLabel lbTurno = new JLabel("Turno");
        lbTurno.setBounds(400, 319, 50, 14);
        panel.add(lbTurno);

        lbTurnoObrig = new JLabel("");
        lbTurnoObrig.setBounds(430, 322, 10, 14);
        lbTurnoObrig.setForeground(Color.RED);
        panel.add(lbTurnoObrig);

        cbTurno = new JComboBox();
        cbTurno.setBounds(400, 337, 80, 20);
        cbTurno.addItem("Selecione");
        cbTurno.addItem("Geral");
        cbTurno.addItem("Primeiro");
        cbTurno.addItem("Segundo");
        cbTurno.addItem("Terceiro");
        cbTurno.addItem("Quarto");
        cbTurno.addItem("Quinto");
        cbTurno.addFocusListener(this);
        panel.add(cbTurno);

        JLabel lbHoraIni = new JLabel("Hora Inicial");
        lbHoraIni.setBounds(490, 319, 65, 14);
        panel.add(lbHoraIni);

        lbHoraIniObrig = new JLabel("");
        lbHoraIniObrig.setBounds(545, 322, 10, 14);
        lbHoraIniObrig.setForeground(Color.RED);
        panel.add(lbHoraIniObrig);

        try {
            ftfHoraIni = new JFormattedTextField(new MaskFormatter("##:##"));
        } catch (ParseException ex) {
        }
        ftfHoraIni.setBounds(490, 337, 80, 20);
        ftfHoraIni.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfHoraIni.addFocusListener(this);
        panel.add(ftfHoraIni);

        JLabel lbHoraFim = new JLabel("Hora Final");
        lbHoraFim.setBounds(580, 319, 65, 14);
        panel.add(lbHoraFim);

        lbHoraFimObrig = new JLabel("");
        lbHoraFimObrig.setBounds(630, 322, 10, 14);
        lbHoraFimObrig.setForeground(Color.RED);
        panel.add(lbHoraFimObrig);

        try {
            ftfHoraFim = new JFormattedTextField(new MaskFormatter("##:##"));
        } catch (ParseException ex) {
        }
        ftfHoraFim.setBounds(580, 337, 80, 20);
        ftfHoraFim.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfHoraFim.addFocusListener(this);
        panel.add(ftfHoraFim);

        JLabel lbDiaPagto = new JLabel("Dia Pagamento");
        lbDiaPagto.setBounds(670, 319, 80, 14);
        panel.add(lbDiaPagto);

        lbDiaPagtoObrig = new JLabel("");
        lbDiaPagtoObrig.setBounds(744, 322, 10, 14);
        lbDiaPagtoObrig.setForeground(Color.RED);
        panel.add(lbDiaPagtoObrig);

        try {
            ftfDiaPagto = new JFormattedTextField(new MaskFormatter("##"));
        } catch (ParseException ex) {
        }
        ftfDiaPagto.setBounds(670, 337, 80, 20);
        ftfDiaPagto.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfDiaPagto.addFocusListener(this);
        panel.add(ftfDiaPagto);

        JLabel lbBanco = new JLabel("Banco");
        lbBanco.setBounds(760, 319, 40, 14);
        panel.add(lbBanco);

        tfBanco = new JTextField();
        tfBanco.setBounds(760, 337, 92, 20);
        tfBanco.addFocusListener(this);
        panel.add(tfBanco);

        JLabel lbContaBanco = new JLabel("Agência / Conta / Dígito Conta");
        lbContaBanco.setBounds(20, 362, 150, 14);
        panel.add(lbContaBanco);

        try {
            ftfContaBanco = new JFormattedTextField(new MaskFormatter("####-#/#######/#"));
        } catch (ParseException ex) {
        }
        ftfContaBanco.setBounds(20, 380, 150, 20);
        ftfContaBanco.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfContaBanco.addFocusListener(this);
        panel.add(ftfContaBanco);

        JLabel lbSalarioBase = new JLabel("Salário Base");
        lbSalarioBase.setBounds(180, 362, 60, 14);
        panel.add(lbSalarioBase);

        lbSalarioBaseObrig = new JLabel("");
        lbSalarioBaseObrig.setBounds(240, 365, 10, 14);
        lbSalarioBaseObrig.setForeground(Color.RED);
        panel.add(lbSalarioBaseObrig);

        tfSalarioBase = new JTextField();
        tfSalarioBase.setBounds(180, 380, 100, 20);
        tfSalarioBase.addFocusListener(this);
        panel.add(tfSalarioBase);

        JLabel lbDescricao = new JLabel("Descricao");
        lbDescricao.setBounds(290, 362, 55, 14);
        panel.add(lbDescricao);

        tfDescricao = new JTextField();
        tfDescricao.setBounds(290, 380, 410, 20);
        tfDescricao.addFocusListener(this);
        panel.add(tfDescricao);

        JLabel lbDataDemissao = new JLabel("Data Demissão");
        lbDataDemissao.setBounds(710, 362, 100, 14);
        panel.add(lbDataDemissao);

        try {
            ftfDataDemissao = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException ex) {
        }
        ftfDataDemissao.setBounds(710, 380, 100, 20);
        ftfDataDemissao.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfDataDemissao.addFocusListener(this);
        panel.add(ftfDataDemissao);

        JLabel lbAtivo = new JLabel("Ativo");
        lbAtivo.setBounds(824, 365, 50, 14);
        panel.add(lbAtivo);

        lbAtivoObrig = new JLabel("");
        lbAtivoObrig.setBounds(847, 385, 10, 14);
        lbAtivoObrig.setForeground(Color.RED);
        panel.add(lbAtivoObrig);

        chAtivo = new JCheckBox();
        chAtivo.setBounds(824, 380, 20, 20);
        chAtivo.setBackground(new Color(248, 248, 248));
        panel.add(chAtivo);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 421, 870, 3);
        panel.add(separator);

        Icon icOk = new ImageIcon("OK.png");
        btOk = new JButton("OK", icOk);
        btOk.setBounds(140, 436, 70, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("Confirma Operação");
        btOk.addActionListener(this);
        panel.add(btOk);

        Icon icCancelar = new ImageIcon("Cancelar.png");
        btCancelar = new JButton("Cancelar", icCancelar);
        btCancelar.setBounds(300, 436, 70, 26);
        btCancelar.setMargin(new Insets(0, 0, 0, 0));
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Limpar os Campos");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        Icon icExcluir = new ImageIcon("Excluir.png");
        btExcluir = new JButton("Excluir", icExcluir);
        btExcluir.setBounds(470, 436, 70, 26);
        btExcluir.setMargin(new Insets(0, 0, 0, 0));
        btExcluir.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.setToolTipText("Excluir Registro");
        btExcluir.addActionListener(this);
        panel.add(btExcluir);

        Icon icSair = new ImageIcon("Sair.png");
        btSair = new JButton("Sair", icSair);
        btSair.setBounds(640, 436, 70, 26);
        btSair.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btSair.setToolTipText("Fechar");
        btSair.addActionListener(this);
        panel.add(btSair);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(898, 525);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controleFunc.getProxCodFunc()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfNome.setText("");
        tfIdade.setText("");
        tfNaturalidade.setText("");
        tfNacionalidade.setText("");
        tfCodEndereco.setText("");
        tfNomePai.setText("");
        tfNomeMae.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfComplemento.setText("");
        tfCep.setText("");
        tfCidade.setText("");
        tfEstado.setText("");
        tfRegiao.setText("");
        tfReferencia.setText("");
        tfEmail.setText("");
        tfMsn.setText("");
        tfSkype.setText("");
        tfNumContrato.setText("");
        tfTipoContrato.setText("");
        tfCodSetor.setText("");
        tfSetor.setText("");
        tfCodDepart.setText("");
        tfDepartamento.setText("");
        tfCargo.setText("");
        tfBanco.setText("");
        tfSalarioBase.setText("");
        tfDescricao.setText("");
        ftfDataDemissao.setText("");
        ftfCpf.setText("");
        ftfRg.setText("");
        ftfDataNasc.setText("");
        ftfFone.setText("");
        ftfCelular1.setText("");
        ftfCelular2.setText("");
        ftfDataAdmissao.setText("");
        ftfNumPis.setText("");
        ftfNumTituloEleitor.setText("");
        ftfNumCartHabilitacao.setText("");
        ftfNumCartTrab.setText("");
        ftfNumCertReservista.setText("");
        ftfHoraIni.setText("");
        ftfHoraFim.setText("");
        ftfDiaPagto.setText("");
        ftfContaBanco.setText("");
        cbSexo.setSelectedItem("Selecione");
        cbEstadoCivil.setSelectedItem("Selecione");
        cbTurno.setSelectedItem("Selecione");
        lbNomeObrig.setText("");
        lbCpfObrig.setText("");
        lbRgObrig.setText("");
        lbSexoObrig.setText("");
        lbEstadoCivilObrig.setText("");
        lbDataNascObrig.setText("");
        lbNaturalidadeObrig.setText("");
        lbCodEnderecoObrig.setText("");
        lbNumeroObrig.setText("");
        lbComplementoObrig.setText("");
        lbFoneObrig.setText("");
        lbDataAdmissaoObrig.setText("");
        lbNumCartTrabObrig.setText("");
        lbCodSetorObrig.setText("");
        lbCodDepartObrig.setText("");
        lbCargoObrig.setText("");
        lbTurnoObrig.setText("");
        lbHoraIniObrig.setText("");
        lbHoraFimObrig.setText("");
        lbDiaPagtoObrig.setText("");
        lbSalarioBaseObrig.setText("");
        lbAtivoObrig.setText("");
        chAtivo.setSelected(false);
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
        if ("   .   .   -  ".equals(ftfCpf.getText())) {
            lbCpfObrig.setText("*");
            flag = true;
        } else {
            lbCpfObrig.setText("");
        }
        if ("   .   .   ".equals(ftfRg.getText())) {
            lbRgObrig.setText("*");
            flag = true;
        } else {
            lbRgObrig.setText("");
        }
        if ("Selecione".equals(cbSexo.getSelectedItem())) {
            lbSexoObrig.setText("*");
            flag = true;
        } else {
            lbSexoObrig.setText("");
        }
        if ("Selecione".equals(cbEstadoCivil.getSelectedItem())) {
            lbEstadoCivilObrig.setText("*");
            flag = true;
        } else {
            lbEstadoCivilObrig.setText("");
        }
        if ("  /  /    ".equals(ftfDataNasc.getText())) {
            lbDataNascObrig.setText("*");
            flag = true;
        } else {
            lbDataNascObrig.setText("");
        }
        if ("".equals(tfNaturalidade.getText().trim())) {
            lbNaturalidadeObrig.setText("*");
            flag = true;
        } else {
            lbNaturalidadeObrig.setText("");
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
        if ("".equals(tfComplemento.getText().trim())) {
            lbComplementoObrig.setText("*");
            flag = true;
        } else {
            lbComplementoObrig.setText("");
        }
        if ("(  )    -    ".equals(ftfFone.getText())) {
            lbFoneObrig.setText("*");
            flag = true;
        } else {
            lbFoneObrig.setText("");
        }
        if ("  /  /    ".equals(ftfDataAdmissao.getText())) {
            lbDataAdmissaoObrig.setText("*");
            flag = true;
        } else {
            lbDataAdmissaoObrig.setText("");
        }
        if ("       /   - ".equals(ftfNumCartTrab.getText())) {
            lbNumCartTrabObrig.setText("*");
            flag = true;
        } else {
            lbNumCartTrabObrig.setText("");
        }
        if ("".equals(tfCodSetor.getText())) {
            lbCodSetorObrig.setText("*");
            flag = true;
        } else {
            lbCodSetorObrig.setText("");
        }
        if ("".equals(tfCodDepart.getText())) {
            lbCodDepartObrig.setText("*");
            flag = true;
        } else {
            lbCodDepartObrig.setText("");
        }
        if ("".equals(tfCargo.getText().trim())) {
            lbCargoObrig.setText("*");
            flag = true;
        } else {
            lbCargoObrig.setText("");
        }
        if ("Selecione".equals(cbTurno.getSelectedItem())) {
            lbTurnoObrig.setText("*");
            flag = true;
        } else {
            lbTurnoObrig.setText("");
        }
        if ("  :  ".equals(ftfHoraIni.getText())) {
            lbHoraIniObrig.setText("*");
            flag = true;
        } else {
            lbHoraIniObrig.setText("");
        }
        if ("  :  ".equals(ftfHoraFim.getText())) {
            lbHoraFimObrig.setText("*");
            flag = true;
        } else {
            lbHoraFimObrig.setText("");
        }
        if ("  ".equals(ftfDiaPagto.getText())) {
            lbDiaPagtoObrig.setText("*");
            flag = true;
        } else {
            lbDiaPagtoObrig.setText("");
        }
        if ("".equals(tfSalarioBase.getText().trim())) {
            lbSalarioBaseObrig.setText("*");
            flag = true;
        } else {
            lbSalarioBaseObrig.setText("");
        }
        if (!flag) {
            if (!"  /  /    ".equals(ftfDataDemissao.getText()) && chAtivo.isSelected()) {
                lbAtivoObrig.setText("*");
                throw new Exception("Funcionário ainda ativo, por favor altere o status *");
            } else {
                if ("  /  /    ".equals(ftfDataDemissao.getText()) && !chAtivo.isSelected()) {
                    lbAtivoObrig.setText("*");
                    throw new Exception("Por favor ative status * do funcionário");
                } else {
                    lbAtivoObrig.setText("");
                }
            }
            try {
                if (Double.parseDouble(tfSalarioBase.getText()) < 100) {
                    throw new Exception("Campo salário base inválido");
                }
            } catch (NumberFormatException ex) {
                throw new Exception("Campo salário base inválido");
            }
            if (!validaData(ftfDataNasc)) {
                throw new Exception("Campo data nascimento inválido");
            }
            if (!validaData(ftfDataAdmissao)) {
                throw new Exception("Campo data admissão inválido");
            }
            if (!validaData(ftfDataDemissao)) {
                throw new Exception("Campo data demissão inválido");
            }
            if (Integer.parseInt(ftfDiaPagto.getText()) > 31) {
                throw new Exception("Entre com um dia válido");
            }
            if (Integer.parseInt(tfIdade.getText()) < 16 || Integer.parseInt(tfIdade.getText()) > 100) {
                throw new Exception("Campo data de nascimento inválido");
            }
            if (!validaHorario(ftfHoraIni)) {
                throw new Exception("Campo hora inicial inválido");
            }
            if (!validaHorario(ftfHoraFim)) {
                throw new Exception("Campo hora final inválido");
            }
            Funcionario func = new Funcionario();
            func.setCodFunc(Integer.parseInt(tfCodigo.getText()));
            func.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            func.setUltAlteracao(new Date());
            func.setNome(tfNome.getText());
            func.setCpfCnpj(ftfCpf.getText());
            func.setRgIe(ftfRg.getText());
            func.setSexo((String) cbSexo.getSelectedItem());
            func.setEstadoCivil((String) cbEstadoCivil.getSelectedItem());
            func.setDataNascimento(formatDate.parse(ftfDataNasc.getText()));
            Cidade cidade = new Cidade();
            cidade.setCodCidade(codCidade);
            func.setCidad(cidade);
            func.setCodEndereco(Integer.parseInt(tfCodEndereco.getText()));
            func.setNomePai(tfNomePai.getText());
            func.setNomeMae(tfNomeMae.getText());
            func.setNumero(Integer.parseInt(tfNumero.getText()));
            func.setComplemento(tfComplemento.getText());
            func.setReferencia(tfReferencia.getText());
            func.setFone(ftfFone.getText());
            func.setCelular1(ftfCelular1.getText());
            func.setCelular2(ftfCelular2.getText());
            func.setEmail(tfEmail.getText());
            func.setMsn(tfMsn.getText());
            func.setSkype(tfSkype.getText());
            func.setDataAdmissao(formatDate.parse(ftfDataAdmissao.getText()));
            func.setNumContrato(tfNumContrato.getText());
            func.setTipoContrato(tfTipoContrato.getText());
            func.setNumPis(ftfNumPis.getText());
            func.setNumCartHabilitacao(ftfNumCartHabilitacao.getText());
            func.setTituloEleitor(ftfNumTituloEleitor.getText());
            func.setNumCartTrabalho(ftfNumCartTrab.getText());
            func.setCertReservista(ftfNumCertReservista.getText());
            Setor setor = new Setor();
            setor.setCodSetor(Integer.parseInt(tfCodSetor.getText()));
            func.setSetor(setor);
            Departamento departamento = new Departamento();
            departamento.setCodDepartamento(Integer.parseInt(tfCodDepart.getText()));
            func.setDepartamento(departamento);
            func.setCargo(tfCargo.getText());
            func.setTurno((String) cbTurno.getSelectedItem());
            func.setHoraInicial(ftfHoraIni.getText());
            func.setHoraFinal(ftfHoraFim.getText());
            func.setDiaPagto(Integer.parseInt(ftfDiaPagto.getText()));
            func.setBanco(tfBanco.getText());
            func.setNumConta(ftfContaBanco.getText());
            func.setSalario(Double.parseDouble(tfSalarioBase.getText()));
            func.setDescricao(tfDescricao.getText());
            if (!"  /  /    ".equals(ftfDataDemissao.getText())) {
                func.setDataDemissao(formatDate.parse(ftfDataDemissao.getText()));
            }
            if (chAtivo.isSelected()) {
                func.setAtivo(true);
            }
            int codFuncCadas = controleFunc.getFuncCadastrado(ftfCpf.getText(), Integer.parseInt(tfCodigo.getText()));//verifica se funcionário já cadastrado
            if (codFuncCadas != -1) {
                if (JOptionPane.showConfirmDialog(null, "Confirmar alteração nos dados", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (controleFunc.updateFunc(func)) {
                        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            } else {
                codFuncCadas = controleFunc.getFuncCadastrado(ftfCpf.getText());//verifica se pessoa já cadastrado
                if (codFuncCadas != -1) {
                    JOptionPane.showMessageDialog(null, "Este CPF ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (controleFunc.insertFunc(func)) {
                        JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            }
        } else {
            throw new Exception("Campos * obrigatórios inválidos");
        }
    }

    private void consultaFunc() throws Exception {
        if (controleFunc.isFuncVazio()) {
            throw new Exception("Não há funcionários cadastrados");
        }
        ConsultaFunc consulta = new ConsultaFunc(controleFunc);
        consulta.setListener(new ListenerFunc() {
            @Override
            public void exibeFunc(Funcionario funcionario) {
                limparTela();
                try {
                    tfDepartamento.setText(new String(seguranca.decriptarAssimetricamente(funcionario.getDepartamento().getDepartamento(), false)));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                tfCodigo.setText(Integer.toString(funcionario.getCodFunc()));
                tfDataCadas.setText(formatDate.format(funcionario.getDataCadastro()) + " as " + formatHora.format(funcionario.getDataCadastro()));
                tfUltAlteracao.setText(formatDate.format(funcionario.getUltAlteracao()) + " as " + formatHora.format(funcionario.getUltAlteracao()));
                tfNome.setText(funcionario.getNome());
                ftfCpf.setText(funcionario.getCpfCnpj());
                ftfRg.setText(funcionario.getRgIe());
                cbSexo.setSelectedItem(funcionario.getSexo());
                cbEstadoCivil.setSelectedItem(funcionario.getEstadoCivil());
                ftfDataNasc.setText(formatDate.format(funcionario.getDataNascimento()));
                codCidade = funcionario.getCidad().getCodCidade();
                tfNaturalidade.setText(funcionario.getCidad().getCidade());
                tfNacionalidade.setText(funcionario.getCidad().getPais());
                tfCodEndereco.setText(Integer.toString(funcionario.getCodEndereco()));
                tfNomePai.setText(funcionario.getNomePai());
                tfNomeMae.setText(funcionario.getNomeMae());
                tfEndereco.setText(funcionario.getEndereco());
                tfBairro.setText(funcionario.getBairro());
                tfNumero.setText(Integer.toString(funcionario.getNumero()));
                tfComplemento.setText(funcionario.getComplemento());
                tfCep.setText(funcionario.getCep());
                tfCidade.setText(funcionario.getCidade());
                tfEstado.setText(funcionario.getEstado());
                tfRegiao.setText(funcionario.getRegiao());
                tfReferencia.setText(funcionario.getReferencia());
                ftfFone.setText(funcionario.getFone());
                ftfCelular1.setText(funcionario.getCelular1());
                ftfCelular2.setText(funcionario.getCelular2());
                tfEmail.setText(funcionario.getEmail());
                tfMsn.setText(funcionario.getMsn());
                tfSkype.setText(funcionario.getSkype());
                ftfDataAdmissao.setText(formatDate.format(funcionario.getDataAdmissao()));
                tfNumContrato.setText(funcionario.getNumContrato());
                tfTipoContrato.setText(funcionario.getTipoContrato());
                ftfNumPis.setText(funcionario.getNumPis());
                ftfNumCartHabilitacao.setText(funcionario.getNumCartHabilitacao());
                ftfNumTituloEleitor.setText(funcionario.getTituloEleitor());
                ftfNumCartTrab.setText(funcionario.getNumCartTrabalho());
                ftfNumCertReservista.setText(funcionario.getCertReservista());
                tfCodSetor.setText(Integer.toString(funcionario.getSetor().getCodSetor()));
                tfSetor.setText(funcionario.getSetor().getSetor());
                tfCodDepart.setText(Integer.toString(funcionario.getDepartamento().getCodDepartamento()));
                tfCargo.setText(funcionario.getCargo());
                cbTurno.setSelectedItem(funcionario.getTurno());
                ftfHoraIni.setText(funcionario.getHoraInicial());
                ftfHoraFim.setText(funcionario.getHoraFinal());
                String diaPagto = Integer.toString(funcionario.getDiaPagto());
                if (diaPagto.length() >= 1 && diaPagto.length() <= 9) {
                    ftfDiaPagto.setText("0" + diaPagto);
                } else {
                    ftfDiaPagto.setText(diaPagto);
                }
                tfBanco.setText(funcionario.getBanco());
                ftfContaBanco.setText(funcionario.getNumConta());
                tfSalarioBase.setText(Double.toString(funcionario.getSalario()));
                tfDescricao.setText(funcionario.getDescricao());
                if (funcionario.getDataDemissao() != null) {
                    ftfDataDemissao.setText(formatDate.format(funcionario.getDataDemissao()));
                }
                chAtivo.setSelected(funcionario.isAtivo());
                tfIdade.setText(calculoIdade(ftfDataNasc, formatDate));
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (controleFunc.isFuncVazio()) {
            throw new Exception("Não há funcionários cadastrados");
        }
        if (!controleFunc.isFuncCadastrado(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione um funcionário");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse funcionário", "Funcionário", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (controleFunc.deleteFunc(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
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

    private void consultaSetor() throws Exception {
        if (controleSetor.isSetorVazio()) {
            throw new Exception("Não há setores cadastrados");
        }
        ConsultaSetores consulta = new ConsultaSetores(controleSetor);
        consulta.setListener(new ListenerSetor() {
            @Override
            public void exibeSetor(Setor setor) {
                tfCodSetor.setText(Integer.toString(setor.getCodSetor()));
                tfSetor.setText(setor.getSetor());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void consultaDep() throws Exception {
        if (controleDep.isDepVazio()) {
            throw new Exception("Não há departamentos cadastrados");
        }
        ConsultaDepartamentos consulta = new ConsultaDepartamentos(controleDep);
        consulta.setListener(new ListenerDepartamento() {
            @Override
            public void exibeDep(Departamento departamento) {
                try {
                    tfDepartamento.setText(new String(seguranca.decriptarAssimetricamente(departamento.getDepartamento(), false)));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                tfCodDepart.setText(Integer.toString(departamento.getCodDepartamento()));
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
                consultaFunc();
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
        if (evento.getSource() == btConsultaEndereco) {
            try {
                consultaEndereco();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaSetor) {
            try {
                consultaSetor();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaDepart) {
            try {
                consultaDep();
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
        if (evento.getSource() == tfIdade) {
            tfIdade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNaturalidade) {
            tfNaturalidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNacionalidade) {
            tfNacionalidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodEndereco) {
            tfCodEndereco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomePai) {
            tfNomePai.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeMae) {
            tfNomeMae.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfEmail) {
            tfEmail.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMsn) {
            tfMsn.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSkype) {
            tfSkype.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumContrato) {
            tfNumContrato.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTipoContrato) {
            tfTipoContrato.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodSetor) {
            tfCodSetor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSetor) {
            tfSetor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodDepart) {
            tfCodDepart.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDepartamento) {
            tfDepartamento.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCargo) {
            tfCargo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBanco) {
            tfBanco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSalarioBase) {
            tfSalarioBase.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCpf) {
            ftfCpf.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfRg) {
            ftfRg.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataNasc) {
            ftfDataNasc.setBackground(Color.YELLOW);
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
        if (evento.getSource() == ftfDataAdmissao) {
            ftfDataAdmissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfNumPis) {
            ftfNumPis.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfNumTituloEleitor) {
            ftfNumTituloEleitor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfNumCartHabilitacao) {
            ftfNumCartHabilitacao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfNumCartTrab) {
            ftfNumCartTrab.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfNumCertReservista) {
            ftfNumCertReservista.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfHoraIni) {
            ftfHoraIni.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfHoraFim) {
            ftfHoraFim.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDiaPagto) {
            ftfDiaPagto.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfContaBanco) {
            ftfContaBanco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbSexo) {
            cbSexo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbEstadoCivil) {
            cbEstadoCivil.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbTurno) {
            cbTurno.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataDemissao) {
            ftfDataDemissao.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfNome.setBackground(Color.WHITE);
        tfIdade.setBackground(Color.WHITE);
        tfNaturalidade.setBackground(Color.WHITE);
        tfNacionalidade.setBackground(Color.WHITE);
        tfCodEndereco.setBackground(Color.WHITE);
        tfNomePai.setBackground(Color.WHITE);
        tfNomeMae.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfComplemento.setBackground(Color.WHITE);
        tfCep.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfEstado.setBackground(Color.WHITE);
        tfRegiao.setBackground(Color.WHITE);
        tfReferencia.setBackground(Color.WHITE);
        tfEmail.setBackground(Color.WHITE);
        tfMsn.setBackground(Color.WHITE);
        tfSkype.setBackground(Color.WHITE);
        tfNumContrato.setBackground(Color.WHITE);
        tfTipoContrato.setBackground(Color.WHITE);
        tfCodSetor.setBackground(Color.WHITE);
        tfSetor.setBackground(Color.WHITE);
        tfCodDepart.setBackground(Color.WHITE);
        tfDepartamento.setBackground(Color.WHITE);
        tfCargo.setBackground(Color.WHITE);
        tfBanco.setBackground(Color.WHITE);
        tfSalarioBase.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        if (evento.getSource() == ftfCpf) {
            ftfCpf.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfCpf);
        }
        if (evento.getSource() == ftfRg) {
            ftfRg.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfRg);
        }
        if (evento.getSource() == ftfDataNasc) {
            ftfDataNasc.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfDataNasc);
            tfIdade.setText(calculoIdade(ftfDataNasc, formatDate));
        }
        if (evento.getSource() == ftfFone) {
            ftfFone.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfFone);
        }
        if (evento.getSource() == ftfCelular1) {
            ftfCelular1.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfCelular1);
        }
        if (evento.getSource() == ftfCelular2) {
            ftfCelular2.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfCelular2);
        }
        if (evento.getSource() == ftfDataAdmissao) {
            ftfDataAdmissao.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfDataAdmissao);
        }
        if (evento.getSource() == ftfNumPis) {
            ftfNumPis.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfNumPis);
        }
        if (evento.getSource() == ftfNumTituloEleitor) {
            ftfNumTituloEleitor.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfNumTituloEleitor);
        }
        if (evento.getSource() == ftfNumCartHabilitacao) {
            ftfNumCartHabilitacao.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfNumCartHabilitacao);
        }
        if (evento.getSource() == ftfNumCartTrab) {
            ftfNumCartTrab.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfNumCartTrab);
        }
        if (evento.getSource() == ftfNumCertReservista) {
            ftfNumCertReservista.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfNumCertReservista);
        }
        if (evento.getSource() == ftfHoraIni) {
            ftfHoraIni.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfHoraIni);
        }
        if (evento.getSource() == ftfHoraFim) {
            ftfHoraFim.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfHoraFim);
        }
        if (evento.getSource() == ftfDiaPagto) {
            ftfDiaPagto.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfDiaPagto);
        }
        if (evento.getSource() == ftfContaBanco) {
            ftfContaBanco.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfContaBanco);
        }
        if (evento.getSource() == ftfDataDemissao) {
            ftfDataDemissao.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfDataDemissao);
        }
        cbSexo.setBackground(Color.WHITE);
        cbEstadoCivil.setBackground(Color.WHITE);
        cbTurno.setBackground(Color.WHITE);
    }
}

package Funcionario;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DAOFactory.DAOFactory;
import FormatacaoCampos.CamposInt;
import FormatacaoCampos.CriarObjGrafico;
import FormatacaoCampos.VerificaCampos;
import Modelo.Funcionario;

public class CadasFuncionario extends JDialog implements ActionListener, ItemListener, FocusListener {

    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome,
            tfNumeroContrato, tfTipoContrato, tfBairro, tfEndereco, tfNumero,
            tfPais, tfNaturalidade, tfIdade, tfComplemento, tfReferencia,
            tfEmpresa, tfEmail, tfMsn, tfSkype, tfNomePai, tfNomeMae,
            tfDescricao, tfCorRaca, tfChefe, tfCargo, tfCracha, tfBanco,
            tfSaldoFGTS, tfSalarioInicial, tfAumento, tfDescontoINSS,
            tfValeAlimentacao, tfValeTransporte, tfPlanoSaude,
            tfSalarioLiquido, tfSeguroVida, tfCidade, tfUF, tfSetor;
    private JButton btVer, btOk, btCancelar, btCalculaSalario;
    private JRadioButton rbNovo, rbAlterar, rbExcluir, rbAtivo, rbInativo;
    private JLabel lbNomeObrig, lbRGObrig, lbCPFObrig, lbEnderecoObrig,
            lbBairroObrig, lbNumeroObrig, lbCidadeObrig, lbUFObrig,
            lbDataNascObrig, lbFoneObrig, lbCartTrabObrig, lbSalarioLiqObrig,
            lbDataContDemObrig, lbDataPagtoObrig;
    private JFormattedTextField ftfRG, ftfCPF, ftfDataContrDemissao, ftfCEP,
            ftfDataNasc, ftfFone, ftfCelular1, ftfCelular2, ftfFoneEmpresa,
            ftfFax, ftfTituloEleitor, ftfZona, ftfSecao, ftfDataPagamento,
            ftfHoraInicial, ftfHoraFinal, ftfHorasSemanais, ftfHorasMensais,
            ftfIE, ftfCnpj, ftfPis, ftfCarteiraTrabalho, ftfCertifiReservista,
            ftfInscricaoINSS, ftfCarteiraHabilitacao, ftfAgencia, ftfConta,
            ftfDigitoConta, ftfContaFGTS;
    private JComboBox cbSexo, cbEstadoCivil, cbTipoPessoa, cbInsentoIR,
            cbContribuicaoSindical;
    private JTextArea taOutrasConsidereções;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private FuncionarioControl controle;

    public CadasFuncionario(DAOFactory daoFactory, Connection con) {
        controle = new FuncionarioControl(daoFactory, con);
        try {
            initComponents(daoFactory);
        } catch (ParseException ex) {
        }
    }

    private void initComponents(DAOFactory daoFactory) throws ParseException {
        List<String> itensCombo = new ArrayList<String>();
        setTitle("Cadastro de Funcionario");
        setLayout(null);

        JPanel panel = CriarObjGrafico.criarJPanel(10, 10, 865, 605);
        add(panel);

        panel.add(CriarObjGrafico.criarJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodFunc()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        tfCodigo.addFocusListener(this);
        panel.add(tfCodigo);

        btVer = CriarObjGrafico.criarJButton("...", 106, 34, 31, 24);
        btVer.addActionListener(this);
        panel.add(btVer);

        panel.add(CriarObjGrafico.criarJLabel("Cadastrado em", 143, 20, 90, 14));
        tfDataCadas = CriarObjGrafico.criarJTextField(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.setEditable(false);
        tfDataCadas.setBackground(Color.WHITE);
        tfDataCadas.addFocusListener(this);
        panel.add(tfDataCadas);

        panel.add(CriarObjGrafico.criarJLabel("Última Alteração", 270, 20, 100, 14));
        tfUltAlteracao = CriarObjGrafico.criarJTextField(270, 38, 120, 20);
        tfUltAlteracao.addFocusListener(this);
        panel.add(tfUltAlteracao);

        panel.add(CriarObjGrafico.criarJLabel("Data Admis/Demis", 398, 20, 120, 14));
        lbDataContDemObrig = CriarObjGrafico.criarJLabel("", 503, 23, 10, 14);
        lbDataContDemObrig.setForeground(Color.RED);
        panel.add(lbDataContDemObrig);
        ftfDataContrDemissao = CriarObjGrafico.criarJFormattedTextField("##/##/####", 400, 38, 100, 20);
        ftfDataContrDemissao.addFocusListener(this);
        panel.add(ftfDataContrDemissao);

        panel.add(CriarObjGrafico.criarJLabel("Número Contrato", 510, 20, 100, 14));
        tfNumeroContrato = CriarObjGrafico.criarJTextField(510, 38, 100, 20);
        tfNumeroContrato.setDocument(new CamposInt());
        tfNumeroContrato.addFocusListener(this);
        panel.add(tfNumeroContrato);

        panel.add(CriarObjGrafico.criarJLabel("Tipo Contrato", 630, 20, 100, 14));
        tfTipoContrato = CriarObjGrafico.criarJTextField(630, 38, 100, 20);
        tfTipoContrato.addFocusListener(this);
        panel.add(tfTipoContrato);

        panel.add(CriarObjGrafico.criarJLabel("PIS", 740, 20, 100, 14));
        ftfPis = CriarObjGrafico.criarJFormattedTextField("###.#####.##-#", 740, 38, 105, 20);
        ftfPis.addFocusListener(this);
        panel.add(ftfPis);

        panel.add(CriarObjGrafico.criarJLabel("Nome", 20, 60, 80, 14));
        lbNomeObrig = CriarObjGrafico.criarJLabel("", 60, 63, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel.add(lbNomeObrig);
        tfNome = CriarObjGrafico.criarJTextField(20, 78, 244, 20);
        tfNome.addFocusListener(this);
        panel.add(tfNome);

        panel.add(CriarObjGrafico.criarJLabel("RG", 270, 60, 80, 14));
        lbRGObrig = CriarObjGrafico.criarJLabel("", 295, 63, 10, 14);
        lbRGObrig.setForeground(Color.RED);
        panel.add(lbRGObrig);
        ftfRG = CriarObjGrafico.criarJFormattedTextField("###.###.###", 270, 78, 100, 20);
        ftfRG.addFocusListener(this);
        panel.add(ftfRG);

        panel.add(CriarObjGrafico.criarJLabel("CPF", 375, 60, 80, 14));
        lbCPFObrig = CriarObjGrafico.criarJLabel("", 405, 63, 10, 14);
        lbCPFObrig.setForeground(Color.RED);
        panel.add(lbCPFObrig);
        ftfCPF = CriarObjGrafico.criarJFormattedTextField("###.###.###-##", 375, 78, 120, 20);
        ftfCPF.addFocusListener(this);
        panel.add(ftfCPF);

        panel.add(CriarObjGrafico.criarJLabel("Sexo", 500, 60, 80, 14));
        itensCombo.add("Masculino");
        itensCombo.add("Feminino");
        cbSexo = CriarObjGrafico.criarJComboBox(itensCombo, 500, 78, 110, 20);
        cbSexo.addFocusListener(this);
        panel.add(cbSexo);

        panel.add(CriarObjGrafico.criarJLabel("Estado Civil", 617, 60, 120, 14));
        itensCombo.clear();
        itensCombo.add("Casado");
        itensCombo.add("Solteiro");
        cbEstadoCivil = CriarObjGrafico.criarJComboBox(itensCombo, 617, 78, 110, 20);
        cbEstadoCivil.addFocusListener(this);
        panel.add(cbEstadoCivil);

        panel.add(CriarObjGrafico.criarJLabel("Tipo Pessoa", 735, 60, 90, 14));
        itensCombo.clear();
        itensCombo.add("Física");
        itensCombo.add("Jurídica");
        cbTipoPessoa = CriarObjGrafico.criarJComboBox(itensCombo, 735, 78, 110, 20);
        cbTipoPessoa.addFocusListener(this);
        panel.add(cbTipoPessoa);

        panel.add(CriarObjGrafico.criarJLabel("Endereço", 20, 100, 100, 14));
        lbEnderecoObrig = CriarObjGrafico.criarJLabel("", 80, 103, 10, 14);
        lbEnderecoObrig.setForeground(Color.RED);
        panel.add(lbEnderecoObrig);
        tfEndereco = CriarObjGrafico.criarJTextField(20, 118, 170, 20);
        tfEndereco.addFocusListener(this);
        panel.add(tfEndereco);

        panel.add(CriarObjGrafico.criarJLabel("Bairro", 200, 100, 100, 14));
        lbBairroObrig = CriarObjGrafico.criarJLabel("", 240, 103, 10, 14);
        lbBairroObrig.setForeground(Color.RED);
        panel.add(lbBairroObrig);
        tfBairro = CriarObjGrafico.criarJTextField(200, 118, 140, 20);
        tfBairro.addFocusListener(this);
        panel.add(tfBairro);

        panel.add(CriarObjGrafico.criarJLabel("Número", 350, 100, 100, 14));
        lbNumeroObrig = CriarObjGrafico.criarJLabel("", 400, 103, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel.add(lbNumeroObrig);
        tfNumero = CriarObjGrafico.criarJTextField(350, 118, 100, 20);
        tfNumero.setDocument(new CamposInt());
        tfNumero.addFocusListener(this);
        panel.add(tfNumero);

        panel.add(CriarObjGrafico.criarJLabel("Cidade", 460, 100, 90, 14));
        lbCidadeObrig = CriarObjGrafico.criarJLabel("", 510, 103, 10, 14);
        lbCidadeObrig.setForeground(Color.RED);
        panel.add(lbCidadeObrig);
        tfCidade = CriarObjGrafico.criarJTextField(455, 118, 110, 20);
        tfCidade.addFocusListener(this);
        panel.add(tfCidade);

        panel.add(CriarObjGrafico.criarJLabel("UF", 580, 100, 90, 14));
        lbUFObrig = CriarObjGrafico.criarJLabel("", 600, 103, 10, 14);
        lbUFObrig.setForeground(Color.RED);
        panel.add(lbUFObrig);
        tfUF = CriarObjGrafico.criarJTextField(580, 118, 110, 20);
        tfUF.addFocusListener(this);
        panel.add(tfUF);

        panel.add(CriarObjGrafico.criarJLabel("País", 700, 100, 100, 14));
        tfPais = CriarObjGrafico.criarJTextField(700, 118, 145, 20);
        tfPais.addFocusListener(this);
        panel.add(tfPais);

        panel.add(CriarObjGrafico.criarJLabel("CEP", 20, 140, 80, 14));
        ftfCEP = CriarObjGrafico.criarJFormattedTextField("#####-###", 20, 158, 100, 20);
        ftfCEP.addFocusListener(this);
        panel.add(ftfCEP);

        panel.add(CriarObjGrafico.criarJLabel("Complemento", 130, 140, 80, 14));
        tfComplemento = CriarObjGrafico.criarJTextField(130, 158, 100, 20);
        tfComplemento.addFocusListener(this);
        panel.add(tfComplemento);

        panel.add(CriarObjGrafico.criarJLabel("Naturalidade", 240, 140, 80, 14));
        tfNaturalidade = CriarObjGrafico.criarJTextField(240, 158, 130, 20);
        tfNaturalidade.addFocusListener(this);
        panel.add(tfNaturalidade);

        panel.add(CriarObjGrafico.criarJLabel("Data Nascimento", 380, 140, 100, 14));
        lbDataNascObrig = CriarObjGrafico.criarJLabel("", 480, 143, 10, 14);
        lbDataNascObrig.setForeground(Color.RED);
        panel.add(lbDataNascObrig);
        ftfDataNasc = CriarObjGrafico.criarJFormattedTextField("##/##/####", 380, 158, 100, 20);
        ftfDataNasc.addFocusListener(this);
        panel.add(ftfDataNasc);

        panel.add(CriarObjGrafico.criarJLabel("Idade", 490, 140, 80, 14));
        tfIdade = CriarObjGrafico.criarJTextField(490, 158, 100, 20);
        tfIdade.setDocument(new CamposInt());
        tfIdade.addFocusListener(this);
        panel.add(tfIdade);

        panel.add(CriarObjGrafico.criarJLabel("Referência", 600, 140, 80, 14));
        tfReferencia = CriarObjGrafico.criarJTextField(600, 158, 245, 20);
        tfReferencia.addFocusListener(this);
        panel.add(tfReferencia);

        panel.add(CriarObjGrafico.criarJLabel("Fone", 20, 180, 70, 14));
        lbFoneObrig = CriarObjGrafico.criarJLabel("", 53, 183, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel.add(lbFoneObrig);
        ftfFone = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 20, 198, 110, 20);
        ftfFone.addFocusListener(this);
        panel.add(ftfFone);

        panel.add(CriarObjGrafico.criarJLabel("Celular 1", 140, 180, 70, 14));
        ftfCelular1 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 140, 198, 110, 20);
        ftfCelular1.addFocusListener(this);
        panel.add(ftfCelular1);

        panel.add(CriarObjGrafico.criarJLabel("Celular 2", 260, 180, 70, 14));
        ftfCelular2 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 260, 198, 110, 20);
        ftfCelular2.addFocusListener(this);
        panel.add(ftfCelular2);

        panel.add(CriarObjGrafico.criarJLabel("Empresa", 380, 180, 80, 14));
        tfEmpresa = CriarObjGrafico.criarJTextField(380, 198, 200, 20);
        tfEmpresa.addFocusListener(this);
        panel.add(tfEmpresa);

        panel.add(CriarObjGrafico.criarJLabel("Fone Empresa", 590, 180, 90, 14));
        ftfFoneEmpresa = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 590, 198, 120, 20);
        ftfFoneEmpresa.addFocusListener(this);
        panel.add(ftfFoneEmpresa);

        panel.add(CriarObjGrafico.criarJLabel("Fax", 720, 180, 60, 14));
        ftfFax = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 720, 198, 125, 20);
        ftfFax.addFocusListener(this);
        panel.add(ftfFax);

        panel.add(CriarObjGrafico.criarJLabel("E-Mail", 20, 220, 60, 14));
        tfEmail = CriarObjGrafico.criarJTextField(20, 238, 285, 20);
        tfEmail.addFocusListener(this);
        panel.add(tfEmail);

        panel.add(CriarObjGrafico.criarJLabel("MSN", 310, 220, 60, 14));
        tfMsn = CriarObjGrafico.criarJTextField(310, 238, 285, 20);
        tfMsn.addFocusListener(this);
        panel.add(tfMsn);

        panel.add(CriarObjGrafico.criarJLabel("Skype", 600, 220, 60, 14));
        tfSkype = CriarObjGrafico.criarJTextField(600, 238, 245, 20);
        tfSkype.addFocusListener(this);
        panel.add(tfSkype);

        panel.add(CriarObjGrafico.criarJLabel("Nome Pai", 20, 260, 60, 14));
        tfNomePai = CriarObjGrafico.criarJTextField(20, 278, 120, 20);
        tfNomePai.addFocusListener(this);
        panel.add(tfNomePai);

        panel.add(CriarObjGrafico.criarJLabel("Nome Mãe", 150, 260, 60, 14));
        tfNomeMae = CriarObjGrafico.criarJTextField(150, 278, 120, 20);
        tfNomeMae.addFocusListener(this);
        panel.add(tfNomeMae);

        panel.add(CriarObjGrafico.criarJLabel("Descrição", 280, 260, 60, 14));
        tfDescricao = CriarObjGrafico.criarJTextField(280, 278, 565, 20);
        tfDescricao.addFocusListener(this);
        panel.add(tfDescricao);

        panel.add(CriarObjGrafico.criarJLabel("Título Eleitor", 20, 300, 90, 14));
        ftfTituloEleitor = CriarObjGrafico.criarJFormattedTextField("####-####-####", 20, 318, 110, 20);
        ftfTituloEleitor.addFocusListener(this);
        panel.add(ftfTituloEleitor);

        panel.add(CriarObjGrafico.criarJLabel("Zona", 140, 300, 90, 14));
        ftfZona = CriarObjGrafico.criarJFormattedTextField("###", 140, 318, 70, 20);
        ftfZona.addFocusListener(this);
        panel.add(ftfZona);

        panel.add(CriarObjGrafico.criarJLabel("Seção", 220, 300, 90, 14));
        ftfSecao = CriarObjGrafico.criarJFormattedTextField("####", 220, 318, 80, 20);
        ftfSecao.addFocusListener(this);
        panel.add(ftfSecao);

        panel.add(CriarObjGrafico.criarJLabel("Cor\\Raça", 310, 300, 90, 14));
        tfCorRaca = CriarObjGrafico.criarJTextField(310, 318, 100, 20);
        tfCorRaca.addFocusListener(this);
        panel.add(tfCorRaca);

        panel.add(CriarObjGrafico.criarJLabel("Carteira Habilitação", 420, 300, 120, 14));
        ftfCarteiraHabilitacao = CriarObjGrafico.criarJFormattedTextField("###########", 420, 318, 120, 20);
        ftfCarteiraHabilitacao.addFocusListener(this);
        panel.add(ftfCarteiraHabilitacao);

        panel.add(CriarObjGrafico.criarJLabel("N° Cart. Trab. / Série", 550, 300, 160, 14));
        lbCartTrabObrig = CriarObjGrafico.criarJLabel("", 667, 303, 10, 14);
        lbCartTrabObrig.setForeground(Color.RED);
        panel.add(lbCartTrabObrig);
        ftfCarteiraTrabalho = CriarObjGrafico.criarJFormattedTextField("#######-/-###-#", 550, 318, 140, 20);
        ftfCarteiraTrabalho.addFocusListener(this);
        panel.add(ftfCarteiraTrabalho);

        panel.add(CriarObjGrafico.criarJLabel("Certificado Reservista", 700, 300, 130, 14));
        ftfCertifiReservista = CriarObjGrafico.criarJFormattedTextField("######", 700, 318, 145, 20);
        ftfCertifiReservista.addFocusListener(this);
        panel.add(ftfCertifiReservista);

        panel.add(CriarObjGrafico.criarJLabel("Inscrição INSS", 20, 340, 130, 14));
        ftfInscricaoINSS = CriarObjGrafico.criarJFormattedTextField("##.###.#####/##", 20, 358, 120, 20);
        ftfInscricaoINSS.addFocusListener(this);
        panel.add(ftfInscricaoINSS);

        panel.add(CriarObjGrafico.criarJLabel("Isento IR", 150, 340, 130, 14));
        itensCombo.clear();
        itensCombo.add("Sim");
        itensCombo.add("Não");
        cbInsentoIR = CriarObjGrafico.criarJComboBox(itensCombo, 150, 358, 120, 20);
        cbInsentoIR.addFocusListener(this);
        panel.add(cbInsentoIR);

        panel.add(CriarObjGrafico.criarJLabel("Contribuicao Sindical", 280, 340, 130, 14));
        itensCombo.clear();
        itensCombo.add("Sim");
        itensCombo.add("Não");
        cbContribuicaoSindical = CriarObjGrafico.criarJComboBox(itensCombo, 280, 358, 120, 20);
        cbContribuicaoSindical.addFocusListener(this);
        panel.add(cbContribuicaoSindical);

        panel.add(CriarObjGrafico.criarJLabel("Chefe", 410, 340, 130, 14));
        tfChefe = CriarObjGrafico.criarJTextField(410, 358, 160, 20);
        tfChefe.addFocusListener(this);
        panel.add(tfChefe);

        panel.add(CriarObjGrafico.criarJLabel("Cargo", 580, 340, 130, 14));
        tfCargo = CriarObjGrafico.criarJTextField(580, 358, 140, 20);
        tfCargo.addFocusListener(this);
        panel.add(tfCargo);

        panel.add(CriarObjGrafico.criarJLabel("Setor", 730, 340, 90, 14));
        tfSetor = CriarObjGrafico.criarJTextField(730, 358, 115, 20);
        tfSetor.addFocusListener(this);
        panel.add(tfSetor);

        panel.add(CriarObjGrafico.criarJLabel("Crachá", 20, 380, 130, 14));
        tfCracha = CriarObjGrafico.criarJTextField(20, 398, 100, 20);
        tfCracha.setDocument(new CamposInt());
        tfCracha.addFocusListener(this);
        panel.add(tfCracha);

        panel.add(CriarObjGrafico.criarJLabel("Banco", 130, 380, 130, 14));
        tfBanco = CriarObjGrafico.criarJTextField(130, 398, 120, 20);
        tfBanco.addFocusListener(this);
        panel.add(tfBanco);

        panel.add(CriarObjGrafico.criarJLabel("Agência", 260, 380, 130, 14));
        ftfAgencia = CriarObjGrafico.criarJFormattedTextField("####-#", 260, 398, 130, 20);
        ftfAgencia.addFocusListener(this);
        panel.add(ftfAgencia);

        panel.add(CriarObjGrafico.criarJLabel("Conta", 400, 380, 130, 14));
        ftfConta = CriarObjGrafico.criarJFormattedTextField("#######", 400, 398, 120, 20);
        ftfConta.addFocusListener(this);
        panel.add(ftfConta);

        panel.add(CriarObjGrafico.criarJLabel("Dígito Conta", 530, 380, 130, 14));
        ftfDigitoConta = CriarObjGrafico.criarJFormattedTextField("#", 530, 398, 100, 20);
        ftfDigitoConta.addFocusListener(this);
        panel.add(ftfDigitoConta);

        panel.add(CriarObjGrafico.criarJLabel("Conta FGTS", 640, 380, 130, 14));
        ftfContaFGTS = CriarObjGrafico.criarJFormattedTextField("######", 640, 398, 95, 20);
        ftfContaFGTS.addFocusListener(this);
        panel.add(ftfContaFGTS);

        panel.add(CriarObjGrafico.criarJLabel("Data Pagamento", 745, 380, 100, 14));
        lbDataPagtoObrig = CriarObjGrafico.criarJLabel("", 842, 383, 10, 14);
        lbDataPagtoObrig.setForeground(Color.RED);
        panel.add(lbDataPagtoObrig);
        ftfDataPagamento = CriarObjGrafico.criarJFormattedTextField("##/##/####", 745, 398, 100, 20);
        ftfDataPagamento.addFocusListener(this);
        panel.add(ftfDataPagamento);

        panel.add(CriarObjGrafico.criarJLabel("Saldo FGTS", 20, 420, 130, 14));
        tfSaldoFGTS = CriarObjGrafico.criarJTextField(20, 438, 90, 20);
        tfSaldoFGTS.addFocusListener(this);
        panel.add(tfSaldoFGTS);

        panel.add(CriarObjGrafico.criarJLabel("Hora Inicial", 120, 420, 100, 14));
        ftfHoraInicial = CriarObjGrafico.criarJFormattedTextField("##:##", 120, 438, 90, 20);
        ftfHoraInicial.addFocusListener(this);
        panel.add(ftfHoraInicial);

        panel.add(CriarObjGrafico.criarJLabel("Hora Final", 220, 420, 100, 14));
        ftfHoraFinal = CriarObjGrafico.criarJFormattedTextField("##:##", 220, 438, 90, 20);
        ftfHoraFinal.addFocusListener(this);
        panel.add(ftfHoraFinal);

        panel.add(CriarObjGrafico.criarJLabel("Horas Semanais", 320, 420, 100, 14));
        ftfHorasSemanais = CriarObjGrafico.criarJFormattedTextField("##:##", 320, 438, 90, 20);
        ftfHorasSemanais.addFocusListener(this);
        panel.add(ftfHorasSemanais);

        panel.add(CriarObjGrafico.criarJLabel("Horas Mensais", 420, 420, 100, 14));
        ftfHorasMensais = CriarObjGrafico.criarJFormattedTextField("###:##", 420, 438, 90, 20);
        ftfHorasMensais.addFocusListener(this);
        panel.add(ftfHorasMensais);

        panel.add(CriarObjGrafico.criarJLabel("Salário Inicial", 520, 420, 130, 14));
        tfSalarioInicial = CriarObjGrafico.criarJTextField(520, 438, 100, 20);
        tfSalarioInicial.addFocusListener(this);
        panel.add(tfSalarioInicial);

        panel.add(CriarObjGrafico.criarJLabel("Aumento", 630, 420, 130, 14));
        tfAumento = CriarObjGrafico.criarJTextField(630, 438, 90, 20);
        tfAumento.setText("0.00");
        panel.add(tfAumento);
        tfAumento.addFocusListener(this);
        panel.add(CriarObjGrafico.criarJLabel("%", 725, 441, 130, 14));

        panel.add(CriarObjGrafico.criarJLabel("Desconto INSS", 740, 420, 130, 14));
        tfDescontoINSS = CriarObjGrafico.criarJTextField(740, 438, 90, 20);
        panel.add(tfDescontoINSS);
        tfDescontoINSS.addFocusListener(this);
        panel.add(CriarObjGrafico.criarJLabel("%", 835, 441, 130, 14));

        panel.add(CriarObjGrafico.criarJLabel("Vale Alimentação", 20, 460, 130, 14));
        tfValeAlimentacao = CriarObjGrafico.criarJTextField(20, 478, 90, 20);
        tfValeAlimentacao.setText("0.00");
        tfValeAlimentacao.addFocusListener(this);
        panel.add(tfValeAlimentacao);

        panel.add(CriarObjGrafico.criarJLabel("Vale Transporte", 125, 460, 130, 14));
        tfValeTransporte = CriarObjGrafico.criarJTextField(125, 478, 90, 20);
        tfValeTransporte.setText("0.00");
        tfValeTransporte.addFocusListener(this);
        panel.add(tfValeTransporte);

        panel.add(CriarObjGrafico.criarJLabel("Plano Saúde", 225, 460, 130, 14));
        tfPlanoSaude = CriarObjGrafico.criarJTextField(225, 478, 90, 20);
        tfPlanoSaude.setText("0.00");
        tfPlanoSaude.addFocusListener(this);
        panel.add(tfPlanoSaude);

        panel.add(CriarObjGrafico.criarJLabel("Seguro Vida", 325, 460, 130, 14));
        tfSeguroVida = CriarObjGrafico.criarJTextField(325, 478, 90, 20);
        tfSeguroVida.setText("0.00");
        tfSeguroVida.addFocusListener(this);
        panel.add(tfSeguroVida);

        btCalculaSalario = CriarObjGrafico.criarJButton("Calcular", 425, 473, 81, 26);
        btCalculaSalario.addActionListener(this);
        panel.add(btCalculaSalario);

        panel.add(CriarObjGrafico.criarJLabel("Salário Líquido", 515, 460, 130, 14));
        lbSalarioLiqObrig = CriarObjGrafico.criarJLabel("", 605, 463, 10, 14);
        lbSalarioLiqObrig.setForeground(Color.RED);
        panel.add(lbSalarioLiqObrig);
        tfSalarioLiquido = CriarObjGrafico.criarJTextField(515, 478, 90, 20);
        tfSalarioLiquido.setEditable(false);
        tfSalarioLiquido.setBackground(Color.WHITE);
        tfSalarioLiquido.addFocusListener(this);
        panel.add(tfSalarioLiquido);

        panel.add(CriarObjGrafico.criarJLabel("I.E.", 615, 460, 110, 14));
        ftfIE = CriarObjGrafico.criarJFormattedTextField("###.###.###", 615, 478, 90, 20);
        ftfIE.addFocusListener(this);
        panel.add(ftfIE);

        panel.add(CriarObjGrafico.criarJLabel("CNPJ", 715, 460, 90, 14));
        ftfCnpj = CriarObjGrafico.criarJFormattedTextField("##.###.###/####-##", 715, 478, 130, 20);
        ftfCnpj.addFocusListener(this);
        panel.add(ftfCnpj);

        panel.add(CriarObjGrafico.criarJLabel("Outras Considerações", 20, 500, 130, 14));
        taOutrasConsidereções = CriarObjGrafico.criarJTextArea(panel, 20, 518, 640, 75);
        taOutrasConsidereções.addFocusListener(this);

        btOk = CriarObjGrafico.criarJButton("OK", 764, 525, 86, 26);
        btOk.addActionListener(this);
        panel.add(btOk);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 764, 555, 86, 26);
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        rbAtivo = CriarObjGrafico.criarJRadioButton("Ativo", 685, 500, 80, 20);
        rbAtivo.setBackground(new Color(248, 248, 248));
        rbAtivo.addItemListener(this);
        panel.add(rbAtivo);

        rbInativo = CriarObjGrafico.criarJRadioButton("Inativo", 763, 500, 80, 20);
        rbInativo.setBackground(new Color(248, 248, 248));
        rbInativo.addItemListener(this);
        panel.add(rbInativo);

        rbNovo = CriarObjGrafico.criarJRadioButton("Novo", 672, 523, 80, 20);
        rbNovo.setBackground(new Color(248, 248, 248));
        rbNovo.addItemListener(this);
        panel.add(rbNovo);

        rbAlterar = CriarObjGrafico.criarJRadioButton("Alterar", 672, 542, 80, 20);
        rbAlterar.setBackground(new Color(248, 248, 248));
        rbAlterar.addItemListener(this);
        panel.add(rbAlterar);

        rbExcluir = CriarObjGrafico.criarJRadioButton("Excluir", 672, 562, 80, 20);
        rbExcluir.setBackground(new Color(248, 248, 248));
        rbExcluir.addItemListener(this);
        panel.add(rbExcluir);

        HashSet conj = new HashSet(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(900, 655);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodFunc()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        lbNomeObrig.setText("");
        lbRGObrig.setText("");
        lbCPFObrig.setText("");
        lbEnderecoObrig.setText("");
        lbBairroObrig.setText("");
        lbNumeroObrig.setText("");
        lbCidadeObrig.setText("");
        lbUFObrig.setText("");
        lbDataNascObrig.setText("");
        lbFoneObrig.setText("");
        lbCartTrabObrig.setText("");
        lbSalarioLiqObrig.setText("");
        lbDataContDemObrig.setText("");
        lbDataPagtoObrig.setText("");
        tfUltAlteracao.setText("");
        tfNome.setText("");
        tfNumeroContrato.setText("");
        tfTipoContrato.setText("");
        tfBairro.setText("");
        tfEndereco.setText("");
        tfNumero.setText("");
        tfPais.setText("");
        tfNaturalidade.setText("");
        tfIdade.setText("");
        tfComplemento.setText("");
        tfReferencia.setText("");
        tfEmpresa.setText("");
        tfEmail.setText("");
        tfMsn.setText("");
        tfSkype.setText("");
        tfNomePai.setText("");
        tfNomeMae.setText("");
        tfDescricao.setText("");
        tfCorRaca.setText("");
        tfChefe.setText("");
        tfCargo.setText("");
        tfCracha.setText("");
        tfBanco.setText("");
        tfSaldoFGTS.setText("");
        tfSalarioInicial.setText("");
        tfAumento.setText("0.00");
        tfDescontoINSS.setText("");
        tfValeAlimentacao.setText("0.00");
        tfValeTransporte.setText("0.00");
        tfPlanoSaude.setText("0.00");
        tfSalarioLiquido.setText("");
        tfSeguroVida.setText("0.00");
        ftfRG.setText("");
        ftfCPF.setText("");
        ftfDataContrDemissao.setText("");
        ftfCEP.setText("");
        ftfDataNasc.setText("");
        ftfFone.setText("");
        ftfCelular1.setText("");
        ftfCelular2.setText("");
        ftfFoneEmpresa.setText("");
        ftfFax.setText("");
        ftfTituloEleitor.setText("");
        ftfZona.setText("");
        ftfSecao.setText("");
        ftfDataPagamento.setText("");
        ftfHoraInicial.setText("");
        ftfHoraFinal.setText("");
        ftfHorasSemanais.setText("");
        ftfHorasMensais.setText("");
        ftfIE.setText("");
        ftfCnpj.setText("");
        ftfPis.setText("");
        ftfCarteiraTrabalho.setText("");
        ftfCertifiReservista.setText("");
        ftfInscricaoINSS.setText("");
        ftfCarteiraHabilitacao.setText("");
        ftfAgencia.setText("");
        ftfConta.setText("");
        ftfDigitoConta.setText("");
        ftfContaFGTS.setText("");
        taOutrasConsidereções.setText("");
        cbSexo.setSelectedItem("Selecione");
        cbEstadoCivil.setSelectedItem("Selecione");
        cbTipoPessoa.setSelectedItem("Selecione");
        tfCidade.setText("");
        tfUF.setText("");
        tfSetor.setText("");
        cbInsentoIR.setSelectedItem("Selecione");
        cbContribuicaoSindical.setSelectedItem("Selecione");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
        rbAtivo.setSelected(false);
        rbInativo.setSelected(false);
    }

    private void calculoSalario() {
        double salarioInicial, aumento, descontoINSS, valeAlimentacao, valeTransporte, planoSaude, seguroVida;
        try {
            salarioInicial = Double.parseDouble(tfSalarioInicial.getText());
            aumento = Double.parseDouble(tfAumento.getText());
            descontoINSS = Double.parseDouble(tfDescontoINSS.getText());
            valeAlimentacao = Double.parseDouble(tfValeAlimentacao.getText());
            valeTransporte = Double.parseDouble(tfValeTransporte.getText());
            planoSaude = Double.parseDouble(tfPlanoSaude.getText());
            seguroVida = Double.parseDouble(tfSeguroVida.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Caracter inválido certifique se que os campos foram digitado corretamente. ex: 0.00", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        salarioInicial -= valeAlimentacao;
        salarioInicial -= valeTransporte;
        salarioInicial -= planoSaude;
        salarioInicial -= seguroVida;
        salarioInicial -= salarioInicial * (descontoINSS / 100);
        salarioInicial += salarioInicial * (aumento / 100);
        tfSalarioLiquido.setText(NumberFormat.getCurrencyInstance().format(salarioInicial));
    }

    private void gravar() throws Exception {
        if (!rbAtivo.isSelected() && !rbInativo.isSelected()) {
            JOptionPane.showMessageDialog(null, "Escolha uma opção: Ativo ou Inativo", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ("".equals(tfNome.getText())) {
            lbNomeObrig.setText("*");
        } else {
            lbNomeObrig.setText("");
        }
        if ("   .   .   ".equals(ftfRG.getText())) {
            lbRGObrig.setText("*");
        } else {
            lbRGObrig.setText("");
        }
        if ("   .   .   -  ".equals(ftfCPF.getText())) {
            lbCPFObrig.setText("*");
        } else {
            lbCPFObrig.setText("");
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
        if ("".equals(tfCidade.getText())) {
            lbCidadeObrig.setText("*");
        } else {
            lbCidadeObrig.setText("");
        }
        if ("".equals(tfUF.getText())) {
            lbUFObrig.setText("*");
        } else {
            lbUFObrig.setText("");
        }
        if ("  /  /    ".equals(ftfDataNasc.getText())) {
            lbDataNascObrig.setText("*");
        } else {
            lbDataNascObrig.setText("");
        }
        if ("(  )    -    ".equals(ftfFone.getText())) {
            lbFoneObrig.setText("*");
        } else {
            lbFoneObrig.setText("");
        }
        if ("       -/-   - ".equals(ftfCarteiraTrabalho.getText())) {
            lbCartTrabObrig.setText("*");
        } else {
            lbCartTrabObrig.setText("");
        }
        if ("".equals(tfSalarioLiquido.getText())) {
            lbSalarioLiqObrig.setText("*");
        } else {
            lbSalarioLiqObrig.setText("");
        }
        if ("  /  /    ".equals(ftfDataContrDemissao.getText())) {
            lbDataContDemObrig.setText("*");
        } else {
            lbDataContDemObrig.setText("");
        }
        if ("  /  /    ".equals(ftfDataPagamento.getText())) {
            lbDataPagtoObrig.setText("*");
        } else {
            lbDataPagtoObrig.setText("");
        }
        if (!"".equals(tfNome.getText()) && !"   .   .   ".equals(ftfRG.getText()) && !"   .   .   -  ".equals(ftfCPF.getText()) && !"".equals(tfEndereco.getText()) && !"".equals(tfBairro.getText()) && !"".equals(tfNumero.getText()) && !"".equals(tfCidade.getText()) && !"".equals(tfUF.getText()) && !"  /  /    ".equals(ftfDataNasc.getText()) && !"(  )    -    ".equals(ftfFone.getText()) && !"         /     - ".equals(ftfCarteiraTrabalho.getText()) && !"".equals(tfSalarioLiquido.getText()) && !"  /  /    ".equals(ftfDataContrDemissao.getText()) && !"  /  /    ".equals(ftfDataPagamento.getText())) {
            Funcionario funcionario = new Funcionario();
            funcionario.setCodigo(Integer.parseInt(tfCodigo.getText()));
            funcionario.setDigitoConta(VerificaCampos.verificaDigitoConta(ftfDigitoConta));
            funcionario.setCracha(VerificaCampos.verificaInt(tfCracha, "crachá"));
            funcionario.setDescontoINSS(VerificaCampos.verificaInt(tfDescontoINSS, "desconto INSS"));
            funcionario.setNumero(VerificaCampos.verificaInt(tfNumero, "número"));
            funcionario.setIdade(VerificaCampos.verificaInt(tfIdade, "idade"));
            funcionario.setConta(VerificaCampos.verificaConta(ftfConta));
            funcionario.setContaFGTS(VerificaCampos.verificaContaFGTS(ftfContaFGTS));
            funcionario.setZona(VerificaCampos.verificaZona(ftfZona));
            funcionario.setSecao(VerificaCampos.verificaSecao(ftfSecao));
            funcionario.setNome(tfNome.getText());
            funcionario.setRg(ftfRG.getText());
            funcionario.setCpf(ftfCPF.getText());
            funcionario.setSexo((String) cbSexo.getSelectedItem());
            funcionario.setEstadoCivil((String) cbEstadoCivil.getSelectedItem());
            funcionario.setTipoPessoa((String) cbTipoPessoa.getSelectedItem());
            funcionario.setEndereco(tfEndereco.getText());
            funcionario.setBairro(tfBairro.getText());
            funcionario.setComplemento(tfComplemento.getText());
            funcionario.setReferencia(tfReferencia.getText());
            funcionario.setCidade(tfCidade.getText());
            funcionario.setUf(tfUF.getText());
            funcionario.setEmail(tfEmail.getText());
            funcionario.setFone(ftfFone.getText());
            funcionario.setCelular1(ftfCelular1.getText());
            funcionario.setCelular2(ftfCelular2.getText());
            funcionario.setDescricao(tfDescricao.getText());
            funcionario.setEmpresa(tfEmpresa.getText());
            funcionario.setFoneEmpresa(ftfFoneEmpresa.getText());
            funcionario.setFax(ftfFax.getText());
            funcionario.setMsn(tfMsn.getText());
            funcionario.setSkype(tfSkype.getText());
            funcionario.setNaturalidade(tfNaturalidade.getText());
            funcionario.setBanco(tfBanco.getText());
            funcionario.setAgencia(ftfAgencia.getText());
            funcionario.setCarteiraHabilitacao(ftfCarteiraHabilitacao.getText());
            funcionario.setCarteiraTrabalho(ftfCarteiraTrabalho.getText());
            funcionario.setCertificadoReservista(ftfCertifiReservista.getText());
            funcionario.setTituloEleitor(ftfTituloEleitor.getText());
            funcionario.setNomePai(tfNomePai.getText());
            funcionario.setNomeMae(tfNomeMae.getText());
            funcionario.setPais(tfPais.getText());
            funcionario.setChefe(tfChefe.getText());
            funcionario.setCargo(tfCargo.getText());
            funcionario.setSetor(tfSetor.getText());
            funcionario.setCep(ftfCEP.getText());
            funcionario.setRacaCor(tfCorRaca.getText());
            funcionario.setHorasSemanais(ftfHorasSemanais.getText());
            funcionario.setHorasMesais(ftfHorasMensais.getText());
            funcionario.setHoraInicial(ftfHoraInicial.getText());
            funcionario.setHoraFinal(ftfHoraFinal.getText());
            funcionario.setIe(ftfIE.getText());
            funcionario.setCnpj(ftfCnpj.getText());
            funcionario.setOutrasConsideracoes(taOutrasConsidereções.getText());
            funcionario.setTipoContrato(tfTipoContrato.getText());
            funcionario.setNumeroContrato(tfNumeroContrato.getText());
            funcionario.setInscricaoINSS(ftfInscricaoINSS.getText());
            funcionario.setPis(ftfPis.getText());
            funcionario.setDataNasc(formatDate.parse(ftfDataNasc.getText()));
            funcionario.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            funcionario.setUltAlteracao(new Date());
            funcionario.setDataContratacaoDemissao(formatDate.parse(ftfDataContrDemissao.getText()));
            funcionario.setDataPagamento(formatDate.parse(ftfDataPagamento.getText()));
            funcionario.setSalarioInicial(VerificaCampos.verificaDouble(tfSalarioInicial, "salário inicial"));
            funcionario.setAumento(VerificaCampos.verificaDouble(tfAumento, "aumento"));
            funcionario.setSalarioLiquido(Double.parseDouble(tfSalarioLiquido.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
            funcionario.setValeAlimentacao(VerificaCampos.verificaDouble(tfValeAlimentacao, "vale alimentação"));
            funcionario.setValeTransporte(VerificaCampos.verificaDouble(tfValeTransporte, "vale transporte"));
            funcionario.setPlanoSaude(VerificaCampos.verificaDouble(tfPlanoSaude, "plano saúde"));
            funcionario.setSaldoFGTS(VerificaCampos.verificaDouble(tfSaldoFGTS, "saldo FGTS"));
            funcionario.setSeguroVida(VerificaCampos.verificaDouble(tfSeguroVida, "seguro de vida"));
            funcionario.setInsentoIR((String) cbInsentoIR.getSelectedItem());
            funcionario.setContribuicaoSindical((String) cbContribuicaoSindical.getSelectedItem());
            if (rbAtivo.isSelected() == true) {
                funcionario.setAtivo(true);
            }
            Funcionario funcionarioLido = controle.getFuncionario(ftfCPF.getText());
            if (funcionarioLido != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "O funcionário " + funcionario.getNome() + " com o cpf " + funcionario.getCpf() + " ja esta cadastrado deseja substituilo? ", "Funcionário", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    funcionario.setCodigo(funcionarioLido.getCodigo());
                    if (controle.setFuncionario(funcionario)) {
                        JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                } else {
                    return;
                }
            } else {
                if (controle.setFuncionario(funcionario)) {
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                }
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private void excluir() throws Exception {
        if (controle.isFuncVazio()) {
            JOptionPane.showMessageDialog(null, "Não há funcionários cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String cpf = JOptionPane.showInputDialog(null, "Informe o cpf do funcionário a ser excluído:", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            if (cpf != null) {
                if (controle.removeFuncionario(cpf)) {
                    JOptionPane.showMessageDialog(null, "Funcionário excluido com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.isFuncVazio()) {
            JOptionPane.showMessageDialog(null, "Não há funcionários cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String cpf = JOptionPane.showInputDialog(null, "Informe o cpf do funcionário a ser recuperado:", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            if (cpf != null) {
                Funcionario funcionario = controle.getFuncionario(cpf);
                if (funcionario != null) {
                    limparTela();
                    tfCodigo.setText(Integer.toString(funcionario.getCodigo()));
                    ftfDigitoConta.setText(VerificaCampos.recuperaCampoStr(funcionario.getDigitoConta()));
                    tfCracha.setText(VerificaCampos.recuperaCampoStr(funcionario.getCracha()));
                    tfDescontoINSS.setText(Integer.toString(funcionario.getDescontoINSS()));
                    tfNumero.setText(VerificaCampos.recuperaCampoStr(funcionario.getNumero()));
                    tfIdade.setText(VerificaCampos.recuperaCampoStr(funcionario.getIdade()));
                    ftfConta.setText(VerificaCampos.recuperaCampoStr(funcionario.getConta()));
                    ftfContaFGTS.setText(VerificaCampos.recuperaCampoStr(funcionario.getContaFGTS()));
                    ftfZona.setText(VerificaCampos.recuperaCampoStr(funcionario.getZona()));
                    ftfSecao.setText(VerificaCampos.recuperaCampoStr(funcionario.getSecao()));
                    tfNome.setText(funcionario.getNome());
                    ftfRG.setText(funcionario.getRg());
                    ftfCPF.setText(funcionario.getCpf());
                    cbSexo.setSelectedItem(funcionario.getSexo());
                    cbEstadoCivil.setSelectedItem(funcionario.getEstadoCivil());
                    cbTipoPessoa.setSelectedItem(funcionario.getTipoPessoa());
                    tfEndereco.setText(funcionario.getEndereco());
                    tfBairro.setText(funcionario.getBairro());
                    tfComplemento.setText(funcionario.getComplemento());
                    tfReferencia.setText(funcionario.getReferencia());
                    tfCidade.setText(funcionario.getCidade());
                    tfUF.setText(funcionario.getUf());
                    tfEmail.setText(funcionario.getEmail());
                    ftfFone.setText(funcionario.getFone());
                    ftfCelular1.setText(funcionario.getCelular1());
                    ftfCelular2.setText(funcionario.getCelular2());
                    tfDescricao.setText(funcionario.getDescricao());
                    tfEmpresa.setText(funcionario.getEmpresa());
                    ftfFoneEmpresa.setText(funcionario.getFoneEmpresa());
                    ftfFax.setText(funcionario.getFax());
                    tfMsn.setText(funcionario.getMsn());
                    tfSkype.setText(funcionario.getSkype());
                    tfNaturalidade.setText(funcionario.getNaturalidade());
                    tfBanco.setText(funcionario.getBanco());
                    ftfAgencia.setText(funcionario.getAgencia());
                    ftfCarteiraHabilitacao.setText(funcionario.getCarteiraHabilitacao());
                    ftfCarteiraTrabalho.setText(funcionario.getCarteiraTrabalho());
                    ftfCertifiReservista.setText(funcionario.getCertificadoReservista());
                    ftfTituloEleitor.setText(funcionario.getTituloEleitor());
                    tfNomePai.setText(funcionario.getNomePai());
                    tfNomeMae.setText(funcionario.getNomeMae());
                    tfPais.setText(funcionario.getPais());
                    tfChefe.setText(funcionario.getChefe());
                    tfCargo.setText(funcionario.getCargo());
                    tfSetor.setText(funcionario.getSetor());
                    ftfCEP.setText(funcionario.getCep());
                    tfCorRaca.setText(funcionario.getRacaCor());
                    ftfHorasSemanais.setText(funcionario.getHorasSemanais());
                    ftfHorasMensais.setText(funcionario.getHorasMesais());
                    ftfHoraInicial.setText(funcionario.getHoraInicial());
                    ftfHoraFinal.setText(funcionario.getHoraFinal());
                    ftfIE.setText(funcionario.getIe());
                    ftfCnpj.setText(funcionario.getCnpj());
                    taOutrasConsidereções.setText(funcionario.getOutrasConsideracoes());
                    tfTipoContrato.setText(funcionario.getTipoContrato());
                    tfNumeroContrato.setText(funcionario.getNumeroContrato());
                    ftfInscricaoINSS.setText(funcionario.getInscricaoINSS());
                    ftfPis.setText(funcionario.getPis());
                    ftfDataNasc.setText(formatDate.format(funcionario.getDataNasc()));
                    tfDataCadas.setText(formatDate.format(funcionario.getDataCadastro()) + " as " + formatHora.format(funcionario.getDataCadastro()));
                    tfUltAlteracao.setText(formatDate.format(funcionario.getUltAlteracao()) + " as " + formatHora.format(funcionario.getUltAlteracao()));
                    ftfDataContrDemissao.setText(formatDate.format(funcionario.getDataContratacaoDemissao()));
                    ftfDataPagamento.setText(formatDate.format(funcionario.getDataPagamento()));
                    tfSalarioInicial.setText(Double.toString(funcionario.getSalarioInicial()));
                    tfAumento.setText(Double.toString(funcionario.getAumento()));
                    tfSalarioLiquido.setText(NumberFormat.getCurrencyInstance().format(funcionario.getSalarioLiquido()));
                    tfValeAlimentacao.setText(Double.toString(funcionario.getValeAlimentacao()));
                    tfValeTransporte.setText(Double.toString(funcionario.getValeTransporte()));
                    tfPlanoSaude.setText(Double.toString(funcionario.getPlanoSaude()));
                    tfSaldoFGTS.setText(Double.toString(funcionario.getSaldoFGTS()));
                    tfSeguroVida.setText(Double.toString(funcionario.getSeguroVida()));
                    cbInsentoIR.setSelectedItem(funcionario.getInsentoIR());
                    cbContribuicaoSindical.setSelectedItem(funcionario.getContribuicaoSindical());
                    if (funcionario.isAtivo()) {
                        rbAtivo.setSelected(true);
                    } else {
                        rbInativo.setSelected(true);
                    }
                    JOptionPane.showMessageDialog(null, "Funcionário recuperado com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void abrirPesquisaFunc() throws Exception {
        if (controle.isFuncVazio()) {
            JOptionPane.showMessageDialog(null, "Não há funcionários cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            PesquisaFuncionario pfn = new PesquisaFuncionario(controle);
            pfn.setModal(true);
            pfn.setVisible(true);
        }
    }

    private String calculoIdade() {
        if (!"  /  /    ".equals(ftfDataNasc.getText())) {
            try {
                Calendar calendar = Calendar.getInstance();

                calendar.setTime(new Date());
                int dia1 = calendar.get(Calendar.DAY_OF_YEAR);
                int ano1 = calendar.get(Calendar.YEAR);

                calendar.setTime(formatDate.parse(ftfDataNasc.getText()));
                int dia2 = calendar.get(Calendar.DAY_OF_YEAR);
                int ano2 = calendar.get(Calendar.YEAR);

                int idade = ano1 - ano2;

                if (dia1 < dia2) {
                    idade--; //Ainda não completou aniversario esse ano.
                }
                return Integer.toString(idade);
            } catch (ParseException ex) {
                return "";
            }
        }
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Funcionario", JOptionPane.ERROR_MESSAGE);
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
                abrirPesquisaFunc();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCalculaSalario) {
            calculoSalario();
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
        if (evento.getSource() == rbAtivo) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbInativo.setSelected(false);
            }
        }
        if (evento.getSource() == rbInativo) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbAtivo.setSelected(false);
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
        if (evento.getSource() == tfNumeroContrato) {
            tfNumeroContrato.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTipoContrato) {
            tfTipoContrato.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBairro) {
            tfBairro.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEndereco) {
            tfEndereco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumero) {
            tfNumero.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPais) {
            tfPais.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNaturalidade) {
            tfNaturalidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfIdade) {
            tfIdade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfComplemento) {
            tfComplemento.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfReferencia) {
            tfReferencia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmpresa) {
            tfEmpresa.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfNomePai) {
            tfNomePai.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeMae) {
            tfNomeMae.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCorRaca) {
            tfCorRaca.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfChefe) {
            tfChefe.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCargo) {
            tfCargo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCracha) {
            tfCracha.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBanco) {
            tfBanco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSaldoFGTS) {
            tfSaldoFGTS.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSalarioInicial) {
            tfSalarioInicial.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfAumento) {
            tfAumento.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescontoINSS) {
            tfDescontoINSS.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValeAlimentacao) {
            tfValeAlimentacao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValeTransporte) {
            tfValeTransporte.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPlanoSaude) {
            tfPlanoSaude.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSalarioLiquido) {
            tfSalarioLiquido.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSeguroVida) {
            tfSeguroVida.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCidade) {
            tfCidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUF) {
            tfUF.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSetor) {
            tfSetor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfRG) {
            ftfRG.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCPF) {
            ftfCPF.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataContrDemissao) {
            ftfDataContrDemissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCEP) {
            ftfCEP.setBackground(Color.YELLOW);
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
        if (evento.getSource() == ftfFoneEmpresa) {
            ftfFoneEmpresa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFax) {
            ftfFax.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfTituloEleitor) {
            ftfTituloEleitor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfZona) {
            ftfZona.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfSecao) {
            ftfSecao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataPagamento) {
            ftfDataPagamento.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfHoraInicial) {
            ftfHoraInicial.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfHoraFinal) {
            ftfHoraFinal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfHorasSemanais) {
            ftfHorasSemanais.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfHorasMensais) {
            ftfHorasMensais.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfIE) {
            ftfIE.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCnpj) {
            ftfCnpj.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfPis) {
            ftfPis.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCarteiraTrabalho) {
            ftfCarteiraTrabalho.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCertifiReservista) {
            ftfCertifiReservista.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfInscricaoINSS) {
            ftfInscricaoINSS.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCarteiraHabilitacao) {
            ftfCarteiraHabilitacao.setBackground(Color.YELLOW);
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
        if (evento.getSource() == ftfContaFGTS) {
            ftfContaFGTS.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbSexo) {
            cbSexo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbEstadoCivil) {
            cbEstadoCivil.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbTipoPessoa) {
            cbTipoPessoa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbInsentoIR) {
            cbInsentoIR.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbContribuicaoSindical) {
            cbContribuicaoSindical.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == taOutrasConsidereções) {
            taOutrasConsidereções.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfNome.setBackground(Color.WHITE);
        tfNumeroContrato.setBackground(Color.WHITE);
        tfTipoContrato.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfPais.setBackground(Color.WHITE);
        tfNaturalidade.setBackground(Color.WHITE);
        tfIdade.setBackground(Color.WHITE);
        tfComplemento.setBackground(Color.WHITE);
        tfReferencia.setBackground(Color.WHITE);
        tfEmpresa.setBackground(Color.WHITE);
        tfEmail.setBackground(Color.WHITE);
        tfMsn.setBackground(Color.WHITE);
        tfSkype.setBackground(Color.WHITE);
        tfNomePai.setBackground(Color.WHITE);
        tfNomeMae.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        tfCorRaca.setBackground(Color.WHITE);
        tfChefe.setBackground(Color.WHITE);
        tfCargo.setBackground(Color.WHITE);
        tfCracha.setBackground(Color.WHITE);
        tfBanco.setBackground(Color.WHITE);
        tfSaldoFGTS.setBackground(Color.WHITE);
        tfSalarioInicial.setBackground(Color.WHITE);
        tfAumento.setBackground(Color.WHITE);
        tfDescontoINSS.setBackground(Color.WHITE);
        tfValeAlimentacao.setBackground(Color.WHITE);
        tfValeTransporte.setBackground(Color.WHITE);
        tfPlanoSaude.setBackground(Color.WHITE);
        tfSalarioLiquido.setBackground(Color.WHITE);
        tfSeguroVida.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfUF.setBackground(Color.WHITE);
        tfSetor.setBackground(Color.WHITE);
        ftfRG.setBackground(Color.WHITE);
        ftfCPF.setBackground(Color.WHITE);
        ftfDataContrDemissao.setBackground(Color.WHITE);
        ftfCEP.setBackground(Color.WHITE);
        if (evento.getSource() == ftfDataNasc) {
            ftfDataNasc.setBackground(Color.WHITE);
            tfIdade.setText(calculoIdade());
        }
        ftfFone.setBackground(Color.WHITE);
        ftfCelular1.setBackground(Color.WHITE);
        ftfCelular2.setBackground(Color.WHITE);
        ftfFoneEmpresa.setBackground(Color.WHITE);
        ftfFax.setBackground(Color.WHITE);
        ftfTituloEleitor.setBackground(Color.WHITE);
        ftfZona.setBackground(Color.WHITE);
        ftfSecao.setBackground(Color.WHITE);
        ftfDataPagamento.setBackground(Color.WHITE);
        ftfHoraInicial.setBackground(Color.WHITE);
        ftfHoraFinal.setBackground(Color.WHITE);
        ftfHorasSemanais.setBackground(Color.WHITE);
        ftfHorasMensais.setBackground(Color.WHITE);
        ftfIE.setBackground(Color.WHITE);
        ftfCnpj.setBackground(Color.WHITE);
        ftfPis.setBackground(Color.WHITE);
        ftfCarteiraTrabalho.setBackground(Color.WHITE);
        ftfCertifiReservista.setBackground(Color.WHITE);
        ftfInscricaoINSS.setBackground(Color.WHITE);
        ftfCarteiraHabilitacao.setBackground(Color.WHITE);
        ftfAgencia.setBackground(Color.WHITE);
        ftfConta.setBackground(Color.WHITE);
        ftfDigitoConta.setBackground(Color.WHITE);
        ftfContaFGTS.setBackground(Color.WHITE);
        cbSexo.setBackground(Color.WHITE);
        cbEstadoCivil.setBackground(Color.WHITE);
        cbTipoPessoa.setBackground(Color.WHITE);
        cbInsentoIR.setBackground(Color.WHITE);
        cbContribuicaoSindical.setBackground(Color.WHITE);
        taOutrasConsidereções.setBackground(Color.WHITE);
    }
}

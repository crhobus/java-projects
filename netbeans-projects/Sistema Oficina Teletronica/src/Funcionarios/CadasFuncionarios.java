package Funcionarios;

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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import Modelo.Funcionario;

public class CadasFuncionarios extends ObjGraficos implements ActionListener, ItemListener, FocusListener {

    private static final long serialVersionUID = 6164548187865879083L;
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

    public CadasFuncionarios(Connection con) {
        controle = new FuncionarioControl(con);
        try {
            initComponents();
        } catch (ParseException ex) {
        }
    }

    private void initComponents() throws ParseException {
        List<String> itensCombo = new ArrayList<String>();
        setTitle("Cadastro de Funcionario");
        setLayout(null);

        JPanel panel = getJPanelLineBorder(10, 10, 865, 605);
        add(panel);

        panel.add(getJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = getJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodFunc()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        tfCodigo.addFocusListener(this);
        panel.add(tfCodigo);

        btVer = getJButton("...", 106, 34, 31, 24);
        btVer.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btVer.setToolTipText("Consulta Funcionários");
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

        panel.add(getJLabel("Data Admis/Demis", 398, 20, 120, 14));
        lbDataContDemObrig = getJLabel("", 503, 23, 10, 14);
        lbDataContDemObrig.setForeground(Color.RED);
        panel.add(lbDataContDemObrig);
        ftfDataContrDemissao = getJFormattedTextField("##/##/####", 400, 38, 100, 20);
        ftfDataContrDemissao.addFocusListener(this);
        panel.add(ftfDataContrDemissao);

        panel.add(getJLabel("Número Contrato", 510, 20, 100, 14));
        tfNumeroContrato = getJTextField(510, 38, 100, 20);
        tfNumeroContrato.setDocument(new CamposInt());
        tfNumeroContrato.addFocusListener(this);
        panel.add(tfNumeroContrato);

        panel.add(getJLabel("Tipo Contrato", 630, 20, 100, 14));
        tfTipoContrato = getJTextField(630, 38, 100, 20);
        tfTipoContrato.addFocusListener(this);
        panel.add(tfTipoContrato);

        panel.add(getJLabel("PIS", 740, 20, 100, 14));
        ftfPis = getJFormattedTextField("###.#####.##-#", 740, 38, 105, 20);
        ftfPis.addFocusListener(this);
        panel.add(ftfPis);

        panel.add(getJLabel("Nome", 20, 60, 80, 14));
        lbNomeObrig = getJLabel("", 60, 63, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel.add(lbNomeObrig);
        tfNome = getJTextField(20, 78, 244, 20);
        tfNome.addFocusListener(this);
        panel.add(tfNome);

        panel.add(getJLabel("RG", 270, 60, 80, 14));
        lbRGObrig = getJLabel("", 295, 63, 10, 14);
        lbRGObrig.setForeground(Color.RED);
        panel.add(lbRGObrig);
        ftfRG = getJFormattedTextField("###.###.###", 270, 78, 100, 20);
        ftfRG.addFocusListener(this);
        panel.add(ftfRG);

        panel.add(getJLabel("CPF", 375, 60, 80, 14));
        lbCPFObrig = getJLabel("", 405, 63, 10, 14);
        lbCPFObrig.setForeground(Color.RED);
        panel.add(lbCPFObrig);
        ftfCPF = getJFormattedTextField("###.###.###-##", 375, 78, 120, 20);
        ftfCPF.addFocusListener(this);
        panel.add(ftfCPF);

        panel.add(getJLabel("Sexo", 500, 60, 80, 14));
        itensCombo.add("Masculino");
        itensCombo.add("Feminino");
        cbSexo = getJComboBox(itensCombo, 500, 78, 110, 20);
        cbSexo.addFocusListener(this);
        panel.add(cbSexo);

        panel.add(getJLabel("Estado Civil", 617, 60, 120, 14));
        itensCombo.clear();
        itensCombo.add("Casado");
        itensCombo.add("Solteiro");
        cbEstadoCivil = getJComboBox(itensCombo, 617, 78, 110, 20);
        cbEstadoCivil.addFocusListener(this);
        panel.add(cbEstadoCivil);

        panel.add(getJLabel("Tipo Pessoa", 735, 60, 90, 14));
        itensCombo.clear();
        itensCombo.add("Física");
        itensCombo.add("Jurídica");
        cbTipoPessoa = getJComboBox(itensCombo, 735, 78, 110, 20);
        cbTipoPessoa.addFocusListener(this);
        panel.add(cbTipoPessoa);

        panel.add(getJLabel("Endereço", 20, 100, 100, 14));
        lbEnderecoObrig = getJLabel("", 80, 103, 10, 14);
        lbEnderecoObrig.setForeground(Color.RED);
        panel.add(lbEnderecoObrig);
        tfEndereco = getJTextField(20, 118, 170, 20);
        tfEndereco.addFocusListener(this);
        panel.add(tfEndereco);

        panel.add(getJLabel("Bairro", 200, 100, 100, 14));
        lbBairroObrig = getJLabel("", 240, 103, 10, 14);
        lbBairroObrig.setForeground(Color.RED);
        panel.add(lbBairroObrig);
        tfBairro = getJTextField(200, 118, 140, 20);
        tfBairro.addFocusListener(this);
        panel.add(tfBairro);

        panel.add(getJLabel("Número", 350, 100, 100, 14));
        lbNumeroObrig = getJLabel("", 400, 103, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel.add(lbNumeroObrig);
        tfNumero = getJTextField(350, 118, 100, 20);
        tfNumero.setDocument(new CamposInt());
        tfNumero.addFocusListener(this);
        panel.add(tfNumero);

        panel.add(getJLabel("Cidade", 460, 100, 90, 14));
        lbCidadeObrig = getJLabel("", 510, 103, 10, 14);
        lbCidadeObrig.setForeground(Color.RED);
        panel.add(lbCidadeObrig);
        tfCidade = getJTextField(455, 118, 110, 20);
        tfCidade.addFocusListener(this);
        panel.add(tfCidade);

        panel.add(getJLabel("UF", 580, 100, 90, 14));
        lbUFObrig = getJLabel("", 600, 103, 10, 14);
        lbUFObrig.setForeground(Color.RED);
        panel.add(lbUFObrig);
        tfUF = getJTextField(580, 118, 110, 20);
        tfUF.addFocusListener(this);
        panel.add(tfUF);

        panel.add(getJLabel("País", 700, 100, 100, 14));
        tfPais = getJTextField(700, 118, 145, 20);
        tfPais.addFocusListener(this);
        panel.add(tfPais);

        panel.add(getJLabel("CEP", 20, 140, 80, 14));
        ftfCEP = getJFormattedTextField("#####-###", 20, 158, 100, 20);
        ftfCEP.addFocusListener(this);
        panel.add(ftfCEP);

        panel.add(getJLabel("Complemento", 130, 140, 80, 14));
        tfComplemento = getJTextField(130, 158, 100, 20);
        tfComplemento.addFocusListener(this);
        panel.add(tfComplemento);

        panel.add(getJLabel("Naturalidade", 240, 140, 80, 14));
        tfNaturalidade = getJTextField(240, 158, 130, 20);
        tfNaturalidade.addFocusListener(this);
        panel.add(tfNaturalidade);

        panel.add(getJLabel("Data Nascimento", 380, 140, 100, 14));
        lbDataNascObrig = getJLabel("", 480, 143, 10, 14);
        lbDataNascObrig.setForeground(Color.RED);
        panel.add(lbDataNascObrig);
        ftfDataNasc = getJFormattedTextField("##/##/####", 380, 158, 100, 20);
        ftfDataNasc.addFocusListener(this);
        panel.add(ftfDataNasc);

        panel.add(getJLabel("Idade", 490, 140, 80, 14));
        tfIdade = getJTextField(490, 158, 100, 20);
        tfIdade.setDocument(new CamposInt());
        tfIdade.addFocusListener(this);
        panel.add(tfIdade);

        panel.add(getJLabel("Referência", 600, 140, 80, 14));
        tfReferencia = getJTextField(600, 158, 245, 20);
        tfReferencia.addFocusListener(this);
        panel.add(tfReferencia);

        panel.add(getJLabel("Fone", 20, 180, 70, 14));
        lbFoneObrig = getJLabel("", 53, 183, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel.add(lbFoneObrig);
        ftfFone = getJFormattedTextField("(##)####-####", 20, 198, 110, 20);
        ftfFone.addFocusListener(this);
        panel.add(ftfFone);

        panel.add(getJLabel("Celular 1", 140, 180, 70, 14));
        ftfCelular1 = getJFormattedTextField("(##)####-####", 140, 198, 110, 20);
        ftfCelular1.addFocusListener(this);
        panel.add(ftfCelular1);

        panel.add(getJLabel("Celular 2", 260, 180, 70, 14));
        ftfCelular2 = getJFormattedTextField("(##)####-####", 260, 198, 110, 20);
        ftfCelular2.addFocusListener(this);
        panel.add(ftfCelular2);

        panel.add(getJLabel("Empresa", 380, 180, 80, 14));
        tfEmpresa = getJTextField(380, 198, 200, 20);
        tfEmpresa.addFocusListener(this);
        panel.add(tfEmpresa);

        panel.add(getJLabel("Fone Empresa", 590, 180, 90, 14));
        ftfFoneEmpresa = getJFormattedTextField("(##)####-####", 590, 198, 120, 20);
        ftfFoneEmpresa.addFocusListener(this);
        panel.add(ftfFoneEmpresa);

        panel.add(getJLabel("Fax", 720, 180, 60, 14));
        ftfFax = getJFormattedTextField("(##)####-####", 720, 198, 125, 20);
        ftfFax.addFocusListener(this);
        panel.add(ftfFax);

        panel.add(getJLabel("E-Mail", 20, 220, 60, 14));
        tfEmail = getJTextField(20, 238, 285, 20);
        tfEmail.addFocusListener(this);
        panel.add(tfEmail);

        panel.add(getJLabel("MSN", 310, 220, 60, 14));
        tfMsn = getJTextField(310, 238, 285, 20);
        tfMsn.addFocusListener(this);
        panel.add(tfMsn);

        panel.add(getJLabel("Skype", 600, 220, 60, 14));
        tfSkype = getJTextField(600, 238, 245, 20);
        tfSkype.addFocusListener(this);
        panel.add(tfSkype);

        panel.add(getJLabel("Nome Pai", 20, 260, 60, 14));
        tfNomePai = getJTextField(20, 278, 120, 20);
        tfNomePai.addFocusListener(this);
        panel.add(tfNomePai);

        panel.add(getJLabel("Nome Mãe", 150, 260, 60, 14));
        tfNomeMae = getJTextField(150, 278, 120, 20);
        tfNomeMae.addFocusListener(this);
        panel.add(tfNomeMae);

        panel.add(getJLabel("Descrição", 280, 260, 60, 14));
        tfDescricao = getJTextField(280, 278, 565, 20);
        tfDescricao.addFocusListener(this);
        panel.add(tfDescricao);

        panel.add(getJLabel("Título Eleitor", 20, 300, 90, 14));
        ftfTituloEleitor = getJFormattedTextField("####-####-####", 20, 318, 110, 20);
        ftfTituloEleitor.addFocusListener(this);
        panel.add(ftfTituloEleitor);

        panel.add(getJLabel("Zona", 140, 300, 90, 14));
        ftfZona = getJFormattedTextField("###", 140, 318, 70, 20);
        ftfZona.addFocusListener(this);
        panel.add(ftfZona);

        panel.add(getJLabel("Seção", 220, 300, 90, 14));
        ftfSecao = getJFormattedTextField("####", 220, 318, 80, 20);
        ftfSecao.addFocusListener(this);
        panel.add(ftfSecao);

        panel.add(getJLabel("Cor\\Raça", 310, 300, 90, 14));
        tfCorRaca = getJTextField(310, 318, 100, 20);
        tfCorRaca.addFocusListener(this);
        panel.add(tfCorRaca);

        panel.add(getJLabel("Carteira Habilitação", 420, 300, 120, 14));
        ftfCarteiraHabilitacao = getJFormattedTextField("###########", 420, 318, 120, 20);
        ftfCarteiraHabilitacao.addFocusListener(this);
        panel.add(ftfCarteiraHabilitacao);

        panel.add(getJLabel("N° Cart. Trab. / Série", 550, 300, 160, 14));
        lbCartTrabObrig = getJLabel("", 667, 303, 10, 14);
        lbCartTrabObrig.setForeground(Color.RED);
        panel.add(lbCartTrabObrig);
        ftfCarteiraTrabalho = getJFormattedTextField("#######-/-###-#", 550, 318, 140, 20);
        ftfCarteiraTrabalho.addFocusListener(this);
        panel.add(ftfCarteiraTrabalho);

        panel.add(getJLabel("Certificado Reservista", 700, 300, 130, 14));
        ftfCertifiReservista = getJFormattedTextField("######", 700, 318, 145, 20);
        ftfCertifiReservista.addFocusListener(this);
        panel.add(ftfCertifiReservista);

        panel.add(getJLabel("Inscrição INSS", 20, 340, 130, 14));
        ftfInscricaoINSS = getJFormattedTextField("##.###.#####/##", 20, 358, 120, 20);
        ftfInscricaoINSS.addFocusListener(this);
        panel.add(ftfInscricaoINSS);

        panel.add(getJLabel("Isento IR", 150, 340, 130, 14));
        itensCombo.clear();
        itensCombo.add("Sim");
        itensCombo.add("Não");
        cbInsentoIR = getJComboBox(itensCombo, 150, 358, 120, 20);
        cbInsentoIR.addFocusListener(this);
        panel.add(cbInsentoIR);

        panel.add(getJLabel("Contribuicao Sindical", 280, 340, 130, 14));
        itensCombo.clear();
        itensCombo.add("Sim");
        itensCombo.add("Não");
        cbContribuicaoSindical = getJComboBox(itensCombo, 280, 358, 120, 20);
        cbContribuicaoSindical.addFocusListener(this);
        panel.add(cbContribuicaoSindical);

        panel.add(getJLabel("Chefe", 410, 340, 130, 14));
        tfChefe = getJTextField(410, 358, 160, 20);
        tfChefe.addFocusListener(this);
        panel.add(tfChefe);

        panel.add(getJLabel("Cargo", 580, 340, 130, 14));
        tfCargo = getJTextField(580, 358, 140, 20);
        tfCargo.addFocusListener(this);
        panel.add(tfCargo);

        panel.add(getJLabel("Setor", 730, 340, 90, 14));
        tfSetor = getJTextField(730, 358, 115, 20);
        tfSetor.addFocusListener(this);
        panel.add(tfSetor);

        panel.add(getJLabel("Crachá", 20, 380, 130, 14));
        tfCracha = getJTextField(20, 398, 100, 20);
        tfCracha.setDocument(new CamposInt());
        tfCracha.addFocusListener(this);
        panel.add(tfCracha);

        panel.add(getJLabel("Banco", 130, 380, 130, 14));
        tfBanco = getJTextField(130, 398, 120, 20);
        tfBanco.addFocusListener(this);
        panel.add(tfBanco);

        panel.add(getJLabel("Agência", 260, 380, 130, 14));
        ftfAgencia = getJFormattedTextField("####-#", 260, 398, 130, 20);
        ftfAgencia.addFocusListener(this);
        panel.add(ftfAgencia);

        panel.add(getJLabel("Conta", 400, 380, 130, 14));
        ftfConta = getJFormattedTextField("#######", 400, 398, 120, 20);
        ftfConta.addFocusListener(this);
        panel.add(ftfConta);

        panel.add(getJLabel("Dígito Conta", 530, 380, 130, 14));
        ftfDigitoConta = getJFormattedTextField("#", 530, 398, 100, 20);
        ftfDigitoConta.addFocusListener(this);
        panel.add(ftfDigitoConta);

        panel.add(getJLabel("Conta FGTS", 640, 380, 130, 14));
        ftfContaFGTS = getJFormattedTextField("######", 640, 398, 95, 20);
        ftfContaFGTS.addFocusListener(this);
        panel.add(ftfContaFGTS);

        panel.add(getJLabel("Data Pagamento", 745, 380, 100, 14));
        lbDataPagtoObrig = getJLabel("", 842, 383, 10, 14);
        lbDataPagtoObrig.setForeground(Color.RED);
        panel.add(lbDataPagtoObrig);
        ftfDataPagamento = getJFormattedTextField("##/##/####", 745, 398, 100, 20);
        ftfDataPagamento.addFocusListener(this);
        panel.add(ftfDataPagamento);

        panel.add(getJLabel("Saldo FGTS", 20, 420, 130, 14));
        tfSaldoFGTS = getJTextField(20, 438, 90, 20);
        tfSaldoFGTS.addFocusListener(this);
        panel.add(tfSaldoFGTS);

        panel.add(getJLabel("Hora Inicial", 120, 420, 100, 14));
        ftfHoraInicial = getJFormattedTextField("##:##", 120, 438, 90, 20);
        ftfHoraInicial.addFocusListener(this);
        panel.add(ftfHoraInicial);

        panel.add(getJLabel("Hora Final", 220, 420, 100, 14));
        ftfHoraFinal = getJFormattedTextField("##:##", 220, 438, 90, 20);
        ftfHoraFinal.addFocusListener(this);
        panel.add(ftfHoraFinal);

        panel.add(getJLabel("Horas Semanais", 320, 420, 100, 14));
        ftfHorasSemanais = getJFormattedTextField("##:##", 320, 438, 90, 20);
        ftfHorasSemanais.addFocusListener(this);
        panel.add(ftfHorasSemanais);

        panel.add(getJLabel("Horas Mensais", 420, 420, 100, 14));
        ftfHorasMensais = getJFormattedTextField("###:##", 420, 438, 90, 20);
        ftfHorasMensais.addFocusListener(this);
        panel.add(ftfHorasMensais);

        panel.add(getJLabel("Salário Inicial", 520, 420, 130, 14));
        tfSalarioInicial = getJTextField(520, 438, 100, 20);
        tfSalarioInicial.addFocusListener(this);
        panel.add(tfSalarioInicial);

        panel.add(getJLabel("Aumento", 630, 420, 130, 14));
        tfAumento = getJTextField(630, 438, 90, 20);
        tfAumento.setText("0.00");
        panel.add(tfAumento);
        tfAumento.addFocusListener(this);
        panel.add(getJLabel("%", 725, 441, 130, 14));

        panel.add(getJLabel("Desconto INSS", 740, 420, 130, 14));
        tfDescontoINSS = getJTextField(740, 438, 90, 20);
        panel.add(tfDescontoINSS);
        tfDescontoINSS.addFocusListener(this);
        panel.add(getJLabel("%", 835, 441, 130, 14));

        panel.add(getJLabel("Vale Alimentação", 20, 460, 130, 14));
        tfValeAlimentacao = getJTextField(20, 478, 90, 20);
        tfValeAlimentacao.setText("0.00");
        tfValeAlimentacao.addFocusListener(this);
        panel.add(tfValeAlimentacao);

        panel.add(getJLabel("Vale Transporte", 125, 460, 130, 14));
        tfValeTransporte = getJTextField(125, 478, 90, 20);
        tfValeTransporte.setText("0.00");
        tfValeTransporte.addFocusListener(this);
        panel.add(tfValeTransporte);

        panel.add(getJLabel("Plano Saúde", 225, 460, 130, 14));
        tfPlanoSaude = getJTextField(225, 478, 90, 20);
        tfPlanoSaude.setText("0.00");
        tfPlanoSaude.addFocusListener(this);
        panel.add(tfPlanoSaude);

        panel.add(getJLabel("Seguro Vida", 325, 460, 130, 14));
        tfSeguroVida = getJTextField(325, 478, 90, 20);
        tfSeguroVida.setText("0.00");
        tfSeguroVida.addFocusListener(this);
        panel.add(tfSeguroVida);

        btCalculaSalario = getJButton("Calcular", 425, 473, 81, 26);
        btCalculaSalario.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCalculaSalario.setToolTipText("Calcula o valor do salário");
        btCalculaSalario.addActionListener(this);
        panel.add(btCalculaSalario);

        panel.add(getJLabel("Salário Líquido", 515, 460, 130, 14));
        lbSalarioLiqObrig = getJLabel("", 605, 463, 10, 14);
        lbSalarioLiqObrig.setForeground(Color.RED);
        panel.add(lbSalarioLiqObrig);
        tfSalarioLiquido = getJTextField(515, 478, 90, 20);
        tfSalarioLiquido.setText("R$ 0,00");
        tfSalarioLiquido.setEditable(false);
        tfSalarioLiquido.setBackground(Color.WHITE);
        tfSalarioLiquido.addFocusListener(this);
        panel.add(tfSalarioLiquido);

        panel.add(getJLabel("I.E.", 615, 460, 110, 14));
        ftfIE = getJFormattedTextField("###.###.###", 615, 478, 90, 20);
        ftfIE.addFocusListener(this);
        panel.add(ftfIE);

        panel.add(getJLabel("CNPJ", 715, 460, 90, 14));
        ftfCnpj = getJFormattedTextField("##.###.###/####-##", 715, 478, 130, 20);
        ftfCnpj.addFocusListener(this);
        panel.add(ftfCnpj);

        panel.add(getJLabel("Outras Considerações", 20, 500, 130, 14));
        taOutrasConsidereções = getJTextArea(panel, 20, 518, 640, 75);
        taOutrasConsidereções.addFocusListener(this);

        btOk = getJButton("OK", 764, 525, 86, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("OK");
        btOk.addActionListener(this);
        panel.add(btOk);

        btCancelar = getJButton("Cancelar", 764, 555, 86, 26);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        rbAtivo = getJRadioButton("Ativo", 685, 500, 80, 20);
        rbAtivo.setBackground(new Color(248, 248, 248));
        rbAtivo.addItemListener(this);
        panel.add(rbAtivo);

        rbInativo = getJRadioButton("Inativo", 763, 500, 80, 20);
        rbInativo.setBackground(new Color(248, 248, 248));
        rbInativo.addItemListener(this);
        panel.add(rbInativo);

        rbNovo = getJRadioButton("Novo", 672, 523, 80, 20);
        rbNovo.setBackground(new Color(248, 248, 248));
        rbNovo.addItemListener(this);
        panel.add(rbNovo);

        rbAlterar = getJRadioButton("Alterar", 672, 542, 80, 20);
        rbAlterar.setBackground(new Color(248, 248, 248));
        rbAlterar.addItemListener(this);
        panel.add(rbAlterar);

        rbExcluir = getJRadioButton("Excluir", 672, 562, 80, 20);
        rbExcluir.setBackground(new Color(248, 248, 248));
        rbExcluir.addItemListener(this);
        panel.add(rbExcluir);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

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
        tfSalarioLiquido.setText("R$ 0,00");
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

    private void calculoSalario() throws Exception {
        try {
            double salarioInicial = Double.parseDouble(tfSalarioInicial.getText());
            salarioInicial -= Double.parseDouble(tfValeAlimentacao.getText());
            salarioInicial -= Double.parseDouble(tfValeTransporte.getText());
            salarioInicial -= Double.parseDouble(tfPlanoSaude.getText());
            salarioInicial -= Double.parseDouble(tfSeguroVida.getText());
            salarioInicial -= salarioInicial * (Double.parseDouble(tfDescontoINSS.getText()) / 100);
            salarioInicial += salarioInicial * (Double.parseDouble(tfAumento.getText()) / 100);
            tfSalarioLiquido.setText(NumberFormat.getCurrencyInstance().format(salarioInicial));
        } catch (NumberFormatException ex) {
            tfSalarioLiquido.setText("R$ 0,00");
            throw new Exception("Caracter inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
        }
    }

    private void gravar() throws Exception {
        if (!rbAtivo.isSelected() && !rbInativo.isSelected()) {
            JOptionPane.showMessageDialog(null, "Escolha uma opção: Ativo ou Inativo", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ("".equals(tfNome.getText().trim())) {
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
        if ("R$ 0,00".equals(tfSalarioLiquido.getText())) {
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
        if (!"".equals(tfNome.getText().trim()) && !"   .   .   ".equals(ftfRG.getText()) && !"   .   .   -  ".equals(ftfCPF.getText()) && !"".equals(tfEndereco.getText().trim()) && !"".equals(tfBairro.getText().trim()) && !"".equals(tfNumero.getText()) && !"".equals(tfCidade.getText().trim()) && !"".equals(tfUF.getText().trim()) && !"  /  /    ".equals(ftfDataNasc.getText()) && !"(  )    -    ".equals(ftfFone.getText()) && !"         /     - ".equals(ftfCarteiraTrabalho.getText()) && !"R$ 0,00".equals(tfSalarioLiquido.getText()) && !"  /  /    ".equals(ftfDataContrDemissao.getText()) && !"  /  /    ".equals(ftfDataPagamento.getText())) {
            Funcionario funcionario = new Funcionario();
            funcionario.setCodigo(Integer.parseInt(tfCodigo.getText()));
            funcionario.setDigitoConta(verificaDigitoConta(ftfDigitoConta));
            funcionario.setCracha(verificaInt(tfCracha, "crachá"));
            funcionario.setDescontoINSS(verificaInt(tfDescontoINSS, "desconto INSS"));
            funcionario.setNumero(Integer.parseInt(tfNumero.getText()));
            funcionario.setIdade(verificaInt(tfIdade, "idade"));
            funcionario.setConta(verificaConta(ftfConta));
            funcionario.setContaFGTS(verificaContaFGTS(ftfContaFGTS));
            funcionario.setZona(verificaZona(ftfZona));
            funcionario.setSecao(verificaSecao(ftfSecao));
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
            funcionario.setSalarioInicial(verificaDouble(tfSalarioInicial, "salário inicial"));
            funcionario.setAumento(verificaDouble(tfAumento, "aumento"));
            funcionario.setSalarioLiquido(Double.parseDouble(tfSalarioLiquido.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
            funcionario.setValeAlimentacao(verificaDouble(tfValeAlimentacao, "vale alimentação"));
            funcionario.setValeTransporte(verificaDouble(tfValeTransporte, "vale transporte"));
            funcionario.setPlanoSaude(verificaDouble(tfPlanoSaude, "plano saúde"));
            funcionario.setSaldoFGTS(verificaDouble(tfSaldoFGTS, "saldo FGTS"));
            funcionario.setSeguroVida(verificaDouble(tfSeguroVida, "seguro de vida"));
            funcionario.setInsentoIR((String) cbInsentoIR.getSelectedItem());
            funcionario.setContribuicaoSindical((String) cbContribuicaoSindical.getSelectedItem());
            if (rbAtivo.isSelected() == true) {
                funcionario.setAtivo(true);
            }
            Funcionario funcionarioLido = controle.selectFuncionario(ftfCPF.getText());
            if (funcionarioLido != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "O funcionário " + funcionario.getNome() + " com o cpf " + funcionario.getCpf() + " ja esta cadastrado deseja substituilo? ", "Funcionário", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    funcionario.setCodigo(funcionarioLido.getCodigo());
                    if (controle.updateFuncionario(funcionario)) {
                        JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                } else {
                    return;
                }
            } else {
                if (controle.insertFuncionario(funcionario)) {
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
            throw new Exception("Não há funcionários cadastrados");
        }
        String cpf = JOptionPane.showInputDialog(null, "Informe o cpf do funcionário a ser excluído:", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
        if (cpf != null) {
            if (controle.deleteFuncionario(cpf)) {
                JOptionPane.showMessageDialog(null, "Funcionário excluido com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.isFuncVazio()) {
            throw new Exception("Não há funcionários cadastrados");
        }
        String cpf = JOptionPane.showInputDialog(null, "Informe o cpf do funcionário a ser recuperado:", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
        if (cpf != null) {
            Funcionario funcionario = controle.selectFuncionario(cpf);
            if (funcionario != null) {
                mostrarFuncionario(funcionario);
                JOptionPane.showMessageDialog(null, "Funcionário recuperado com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void mostrarFuncionario(Funcionario funcionario) {
        limparTela();
        tfCodigo.setText(Integer.toString(funcionario.getCodigo()));
        ftfDigitoConta.setText(recuperaCampoStr(funcionario.getDigitoConta()));
        tfCracha.setText(recuperaCampoStr(funcionario.getCracha()));
        tfDescontoINSS.setText(Integer.toString(funcionario.getDescontoINSS()));
        tfNumero.setText(recuperaCampoStr(funcionario.getNumero()));
        tfIdade.setText(recuperaCampoStr(funcionario.getIdade()));
        ftfConta.setText(recuperaCampoStr(funcionario.getConta()));
        ftfContaFGTS.setText(recuperaCampoStr(funcionario.getContaFGTS()));
        ftfZona.setText(recuperaCampoStr(funcionario.getZona()));
        ftfSecao.setText(recuperaCampoStr(funcionario.getSecao()));
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
        tfSalarioInicial.setText(recuperaCampoStr(funcionario.getSalarioInicial()));
        tfAumento.setText(recuperaCampoStr(funcionario.getAumento()));
        tfSalarioLiquido.setText(NumberFormat.getCurrencyInstance().format(funcionario.getSalarioLiquido()));
        tfValeAlimentacao.setText(recuperaCampoStr(funcionario.getValeAlimentacao()));
        tfValeTransporte.setText(recuperaCampoStr(funcionario.getValeTransporte()));
        tfPlanoSaude.setText(recuperaCampoStr(funcionario.getPlanoSaude()));
        tfSaldoFGTS.setText(recuperaCampoStr(funcionario.getSaldoFGTS()));
        tfSeguroVida.setText(recuperaCampoStr(funcionario.getSeguroVida()));
        cbInsentoIR.setSelectedItem(funcionario.getInsentoIR());
        cbContribuicaoSindical.setSelectedItem(funcionario.getContribuicaoSindical());
        if (funcionario.isAtivo()) {
            rbAtivo.setSelected(true);
        } else {
            rbInativo.setSelected(true);
        }
    }

    private void abrirPesquisaFunc() throws Exception {
        if (controle.isFuncVazio()) {
            throw new Exception("há funcionários cadastrados");
        }
        PesquisaFuncionarios pesquisaFunc = new PesquisaFuncionarios(controle);
        pesquisaFunc.setListener(new ListenerFuncionario() {

            @Override
            public void exibeFuncionario(Funcionario funcionario) {
                mostrarFuncionario(funcionario);
            }
        });
        pesquisaFunc.setModal(true);
        pesquisaFunc.setVisible(true);
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
                    idade--; // Ainda não completou aniversario esse ano.
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
            try {
                calculoSalario();
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

package Funcionario;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
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
import Modelo.Funcionario;
import Modelo.Setor;
import Setor.SetorControl;

public class CadasFuncionario extends JDialog implements ActionListener, ItemListener {

    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome,
            tfNumeroContrato, tfTipoContrato, tfBairro, tfEndereco, tfNumero,
            tfPais, tfNaturalidade, tfIdade, tfComplemento, tfReferencia,
            tfEmpresa, tfEmail, tfMsn, tfSkype, tfNomePai, tfNomeMae,
            tfDescricao, tfCorRaca, tfDocumentoEstrangeiro, tfEstabilidade,
            tfCentroCusto, tfChefe, tfCargo, tfCracha, tfBanco, tfDependenteIR,
            tfClasseContribuicaoIR, tfSaldoFGTS, tfSalarioInicial, tfAumento,
            tfDescontoINSS, tfValeAlimentacao, tfValeTransporte, tfPlanoSaude,
            tfSalarioLiquido, tfSeguroVida;
    private JButton btVer, btOk, btCancelar, btCalculaSalario;
    private JRadioButton rbNovo, rbAlterar, rbExcluir, rbAtivo, rbInativo;
    private JLabel lbNomeObrig, lbRGObrig, lbCPFObrig, lbEnderecoObrig,
            lbBairroObrig, lbNumeroObrig, lbCidadeObrig, lbEstadoObrig,
            lbDataNascObrig, lbFoneObrig, lbCartTrabObrig, lbSalarioLiqObrig, lbDataContDem, lbDataPagto;
    private JFormattedTextField ftfRG, ftfCPF, ftfDataContrDemissao, ftfCEP,
            ftfDataNasc, ftfFone, ftfCelular1, ftfCelular2, ftfFoneEmpresa,
            ftfFax, ftfTituloEleitor, ftfZona, ftfSecao, ftfDataPagamento,
            ftfHoraInicial, ftfHoraFinal, ftfHorasSemanais, ftfHorasMensais,
            ftfInscricaoEstadual, ftfCnpj, ftfPis, ftfCarteiraTrabalho,
            ftfCertifiReservista, ftfInscricaoINSS, ftfCarteiraHabilitacao,
            ftfAgencia, ftfConta, ftfDigitoConta, ftfContaFGTS;
    private JComboBox cbSexo, cbEstadoCivil, cbTipoPessoa, cbCidade, cbEstado,
            cbSetor, cbInsentoIR, cbContribuicaoSindical;
    private JTextArea taOutrasConsidereções;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private FuncionarioControl controle;

    public CadasFuncionario(DAOFactory daoFactory) {
        controle = new FuncionarioControl(daoFactory);
        initComponents(daoFactory);
    }

    private void initComponents(DAOFactory daoFactory) {
        Set<String> itensCombo = new HashSet<String>();
        setTitle("Cadastro de Funcionario");
        setLayout(null);

        JPanel panel1 = CriarObjGrafico.criarJPanel(10, 10, 865, 640);
        add(panel1);

        panel1.add(CriarObjGrafico.criarJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodFunc()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        panel1.add(tfCodigo);

        btVer = CriarObjGrafico.criarJButton("...", 106, 34, 31, 24);
        btVer.addActionListener(this);
        panel1.add(btVer);

        panel1.add(CriarObjGrafico.criarJLabel("Cadastrado em", 143, 20, 90, 14));
        tfDataCadas = CriarObjGrafico.criarJTextField(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.setEditable(false);
        tfDataCadas.setBackground(Color.WHITE);
        panel1.add(tfDataCadas);

        panel1.add(CriarObjGrafico.criarJLabel("Última Alteração", 270, 20, 100, 14));
        tfUltAlteracao = CriarObjGrafico.criarJTextField(270, 38, 120, 20);
        panel1.add(tfUltAlteracao);

        panel1.add(CriarObjGrafico.criarJLabel("Data Admis/Demis", 398, 20, 120, 14));
        lbDataContDem = CriarObjGrafico.criarJLabel("", 503, 23, 10, 14);
        lbDataContDem.setForeground(Color.RED);
        panel1.add(lbDataContDem);
        ftfDataContrDemissao = CriarObjGrafico.criarJFormattedTextField("##/##/####", 400, 38, 100, 20);
        panel1.add(ftfDataContrDemissao);

        panel1.add(CriarObjGrafico.criarJLabel("Número Contrato", 510, 20, 100, 14));
        tfNumeroContrato = CriarObjGrafico.criarJTextField(510, 38, 100, 20);
        tfNumeroContrato.setDocument(new CamposInt());
        panel1.add(tfNumeroContrato);

        panel1.add(CriarObjGrafico.criarJLabel("Tipo Contrato", 630, 20, 100, 14));
        tfTipoContrato = CriarObjGrafico.criarJTextField(630, 38, 100, 20);
        panel1.add(tfTipoContrato);

        panel1.add(CriarObjGrafico.criarJLabel("PIS", 740, 20, 100, 14));
        ftfPis = CriarObjGrafico.criarJFormattedTextField("###.#####.##-#", 740, 38, 105, 20);
        panel1.add(ftfPis);

        panel1.add(CriarObjGrafico.criarJLabel("Nome", 20, 60, 80, 14));
        lbNomeObrig = CriarObjGrafico.criarJLabel("", 60, 63, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel1.add(lbNomeObrig);
        tfNome = CriarObjGrafico.criarJTextField(20, 78, 244, 20);
        panel1.add(tfNome);

        panel1.add(CriarObjGrafico.criarJLabel("RG", 270, 60, 80, 14));
        lbRGObrig = CriarObjGrafico.criarJLabel("", 295, 63, 10, 14);
        lbRGObrig.setForeground(Color.RED);
        panel1.add(lbRGObrig);
        ftfRG = CriarObjGrafico.criarJFormattedTextField("###.###.###", 270, 78, 100, 20);
        panel1.add(ftfRG);

        panel1.add(CriarObjGrafico.criarJLabel("CPF", 375, 60, 80, 14));
        lbCPFObrig = CriarObjGrafico.criarJLabel("", 405, 63, 10, 14);
        lbCPFObrig.setForeground(Color.RED);
        panel1.add(lbCPFObrig);
        ftfCPF = CriarObjGrafico.criarJFormattedTextField("###.###.###-##", 375, 78, 120, 20);
        panel1.add(ftfCPF);

        panel1.add(CriarObjGrafico.criarJLabel("Sexo", 500, 60, 80, 14));
        itensCombo.add("Masculino");
        itensCombo.add("Feminino");
        cbSexo = CriarObjGrafico.criarJComboBox(itensCombo, 500, 78, 110, 20);
        panel1.add(cbSexo);

        panel1.add(CriarObjGrafico.criarJLabel("Estado Civil", 617, 60, 120, 14));
        itensCombo.clear();
        itensCombo.add("Casado");
        itensCombo.add("Solteiro");
        cbEstadoCivil = CriarObjGrafico.criarJComboBox(itensCombo, 617, 78, 110, 20);
        panel1.add(cbEstadoCivil);

        panel1.add(CriarObjGrafico.criarJLabel("Tipo Pessoa", 735, 60, 90, 14));
        itensCombo.clear();
        itensCombo.add("Física");
        itensCombo.add("Jurídica");
        cbTipoPessoa = CriarObjGrafico.criarJComboBox(itensCombo, 735, 78, 110, 20);
        panel1.add(cbTipoPessoa);

        panel1.add(CriarObjGrafico.criarJLabel("Endereço", 20, 100, 100, 14));
        lbEnderecoObrig = CriarObjGrafico.criarJLabel("", 80, 103, 10, 14);
        lbEnderecoObrig.setForeground(Color.RED);
        panel1.add(lbEnderecoObrig);
        tfEndereco = CriarObjGrafico.criarJTextField(20, 118, 170, 20);
        panel1.add(tfEndereco);

        panel1.add(CriarObjGrafico.criarJLabel("Bairro", 200, 100, 100, 14));
        lbBairroObrig = CriarObjGrafico.criarJLabel("", 240, 103, 10, 14);
        lbBairroObrig.setForeground(Color.RED);
        panel1.add(lbBairroObrig);
        tfBairro = CriarObjGrafico.criarJTextField(200, 118, 140, 20);
        panel1.add(tfBairro);

        panel1.add(CriarObjGrafico.criarJLabel("Número", 350, 100, 100, 14));
        lbNumeroObrig = CriarObjGrafico.criarJLabel("", 400, 103, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel1.add(lbNumeroObrig);
        tfNumero = CriarObjGrafico.criarJTextField(350, 118, 100, 20);
        tfNumero.setDocument(new CamposInt());
        panel1.add(tfNumero);

        panel1.add(CriarObjGrafico.criarJLabel("Cidade", 460, 100, 90, 14));
        lbCidadeObrig = CriarObjGrafico.criarJLabel("", 510, 103, 10, 14);
        lbCidadeObrig.setForeground(Color.RED);
        panel1.add(lbCidadeObrig);
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
        cbCidade = CriarObjGrafico.criarJComboBox(itensCombo, 455, 118, 110, 20);
        panel1.add(cbCidade);

        panel1.add(CriarObjGrafico.criarJLabel("Estado", 580, 100, 90, 14));
        lbEstadoObrig = CriarObjGrafico.criarJLabel("", 625, 103, 10, 14);
        lbEstadoObrig.setForeground(Color.RED);
        panel1.add(lbEstadoObrig);
        itensCombo.clear();
        for (Cidade estado : listaCidade) {
            itensCombo.add(estado.getEstado());
        }
        cbEstado = CriarObjGrafico.criarJComboBox(itensCombo, 580, 118, 110, 20);
        panel1.add(cbEstado);

        panel1.add(CriarObjGrafico.criarJLabel("País", 700, 100, 100, 14));
        tfPais = CriarObjGrafico.criarJTextField(700, 118, 145, 20);
        panel1.add(tfPais);

        panel1.add(CriarObjGrafico.criarJLabel("CEP", 20, 140, 80, 14));
        ftfCEP = CriarObjGrafico.criarJFormattedTextField("#####-###", 20, 158, 100, 20);
        panel1.add(ftfCEP);

        panel1.add(CriarObjGrafico.criarJLabel("Complemento", 130, 140, 80, 14));
        tfComplemento = CriarObjGrafico.criarJTextField(130, 158, 100, 20);
        panel1.add(tfComplemento);

        panel1.add(CriarObjGrafico.criarJLabel("Naturalidade", 240, 140, 80, 14));
        tfNaturalidade = CriarObjGrafico.criarJTextField(240, 158, 130, 20);
        panel1.add(tfNaturalidade);

        panel1.add(CriarObjGrafico.criarJLabel("Idade", 380, 140, 80, 14));
        tfIdade = CriarObjGrafico.criarJTextField(380, 158, 100, 20);
        tfIdade.setDocument(new CamposInt());
        panel1.add(tfIdade);

        panel1.add(CriarObjGrafico.criarJLabel("Data Nascimento", 490, 140, 100, 14));
        lbDataNascObrig = CriarObjGrafico.criarJLabel("", 590, 143, 10, 14);
        lbDataNascObrig.setForeground(Color.RED);
        panel1.add(lbDataNascObrig);
        ftfDataNasc = CriarObjGrafico.criarJFormattedTextField("##/##/####", 490, 158, 100, 20);
        panel1.add(ftfDataNasc);

        panel1.add(CriarObjGrafico.criarJLabel("Referência", 600, 140, 80, 14));
        tfReferencia = CriarObjGrafico.criarJTextField(600, 158, 245, 20);
        panel1.add(tfReferencia);

        panel1.add(CriarObjGrafico.criarJLabel("Fone", 20, 180, 70, 14));
        lbFoneObrig = CriarObjGrafico.criarJLabel("", 53, 183, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel1.add(lbFoneObrig);
        ftfFone = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 20, 198, 110, 20);
        panel1.add(ftfFone);

        panel1.add(CriarObjGrafico.criarJLabel("Celular 1", 140, 180, 70, 14));
        ftfCelular1 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 140, 198, 110, 20);
        panel1.add(ftfCelular1);

        panel1.add(CriarObjGrafico.criarJLabel("Celular 2", 260, 180, 70, 14));
        ftfCelular2 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 260, 198, 110, 20);
        panel1.add(ftfCelular2);

        panel1.add(CriarObjGrafico.criarJLabel("Empresa", 380, 180, 80, 14));
        tfEmpresa = CriarObjGrafico.criarJTextField(380, 198, 190, 20);
        panel1.add(tfEmpresa);

        panel1.add(CriarObjGrafico.criarJLabel("Fone Empresa", 580, 180, 90, 14));
        ftfFoneEmpresa = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 580, 198, 120, 20);
        panel1.add(ftfFoneEmpresa);

        panel1.add(CriarObjGrafico.criarJLabel("Fax", 710, 180, 60, 14));
        ftfFax = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 710, 198, 120, 20);
        panel1.add(ftfFax);

        panel1.add(CriarObjGrafico.criarJLabel("E-Mail", 20, 220, 60, 14));
        tfEmail = CriarObjGrafico.criarJTextField(20, 238, 285, 20);
        panel1.add(tfEmail);

        panel1.add(CriarObjGrafico.criarJLabel("MSN", 310, 220, 60, 14));
        tfMsn = CriarObjGrafico.criarJTextField(310, 238, 285, 20);
        panel1.add(tfMsn);

        panel1.add(CriarObjGrafico.criarJLabel("Skype", 600, 220, 60, 14));
        tfSkype = CriarObjGrafico.criarJTextField(600, 238, 245, 20);
        panel1.add(tfSkype);

        panel1.add(CriarObjGrafico.criarJLabel("Nome Pai", 20, 260, 60, 14));
        tfNomePai = CriarObjGrafico.criarJTextField(20, 278, 120, 20);
        panel1.add(tfNomePai);

        panel1.add(CriarObjGrafico.criarJLabel("Nome Mãe", 150, 260, 60, 14));
        tfNomeMae = CriarObjGrafico.criarJTextField(150, 278, 120, 20);
        panel1.add(tfNomeMae);

        panel1.add(CriarObjGrafico.criarJLabel("Descrição", 280, 260, 60, 14));
        tfDescricao = CriarObjGrafico.criarJTextField(280, 278, 565, 20);
        panel1.add(tfDescricao);

        panel1.add(CriarObjGrafico.criarJLabel("Título Eleitor", 20, 300, 90, 14));
        ftfTituloEleitor = CriarObjGrafico.criarJFormattedTextField("#### #### ####", 20, 318, 110, 20);
        panel1.add(ftfTituloEleitor);

        panel1.add(CriarObjGrafico.criarJLabel("Zona", 140, 300, 90, 14));
        ftfZona = CriarObjGrafico.criarJFormattedTextField("###", 140, 318, 70, 20);
        panel1.add(ftfZona);

        panel1.add(CriarObjGrafico.criarJLabel("Seção", 220, 300, 90, 14));
        ftfSecao = CriarObjGrafico.criarJFormattedTextField("####", 220, 318, 80, 20);
        panel1.add(ftfSecao);

        panel1.add(CriarObjGrafico.criarJLabel("Cor\\Raça", 310, 300, 90, 14));
        tfCorRaca = CriarObjGrafico.criarJTextField(310, 318, 100, 20);
        panel1.add(tfCorRaca);

        panel1.add(CriarObjGrafico.criarJLabel("Carteira Habilitação", 420, 300, 120, 14));
        ftfCarteiraHabilitacao = CriarObjGrafico.criarJFormattedTextField("###########", 420, 318, 120, 20);
        panel1.add(ftfCarteiraHabilitacao);

        panel1.add(CriarObjGrafico.criarJLabel("N° Cart. Trab. / Série", 550, 300, 160, 14));
        lbCartTrabObrig = CriarObjGrafico.criarJLabel("", 667, 303, 10, 14);
        lbCartTrabObrig.setForeground(Color.RED);
        panel1.add(lbCartTrabObrig);
        ftfCarteiraTrabalho = CriarObjGrafico.criarJFormattedTextField("#######  /  ###-#", 550, 318, 120, 20);
        panel1.add(ftfCarteiraTrabalho);

        panel1.add(CriarObjGrafico.criarJLabel("Certificado Reservista", 680, 300, 130, 14));
        ftfCertifiReservista = CriarObjGrafico.criarJFormattedTextField("######", 680, 318, 130, 20);
        panel1.add(ftfCertifiReservista);

        panel1.add(CriarObjGrafico.criarJLabel("Documento Estangeiro", 20, 340, 130, 14));
        tfDocumentoEstrangeiro = CriarObjGrafico.criarJTextField(20, 358, 130, 20);
        panel1.add(tfDocumentoEstrangeiro);

        panel1.add(CriarObjGrafico.criarJLabel("Inscrição INSS", 160, 340, 130, 14));
        ftfInscricaoINSS = CriarObjGrafico.criarJFormattedTextField("##.###.#####/##", 160, 358, 120, 20);
        panel1.add(ftfInscricaoINSS);

        panel1.add(CriarObjGrafico.criarJLabel("Isento IR", 290, 340, 130, 14));
        itensCombo.clear();
        itensCombo.add("Sim");
        itensCombo.add("Não");
        cbInsentoIR = CriarObjGrafico.criarJComboBox(itensCombo, 290, 358, 120, 20);
        panel1.add(cbInsentoIR);

        panel1.add(CriarObjGrafico.criarJLabel("Contribuicao Sindical", 420, 340, 130, 14));
        itensCombo.clear();
        itensCombo.add("Sim");
        itensCombo.add("Não");
        cbContribuicaoSindical = CriarObjGrafico.criarJComboBox(itensCombo, 420, 358, 120, 20);
        panel1.add(cbContribuicaoSindical);

        panel1.add(CriarObjGrafico.criarJLabel("Estabilidade", 550, 340, 130, 14));
        tfEstabilidade = CriarObjGrafico.criarJTextField(550, 358, 120, 20);
        tfEstabilidade.setDocument(new CamposInt());
        panel1.add(tfEstabilidade);

        panel1.add(CriarObjGrafico.criarJLabel("Centro Custo", 680, 340, 130, 14));
        tfCentroCusto = CriarObjGrafico.criarJTextField(680, 358, 130, 20);
        tfCentroCusto.setDocument(new CamposInt());
        panel1.add(tfCentroCusto);

        panel1.add(CriarObjGrafico.criarJLabel("Chefe", 20, 380, 130, 14));
        tfChefe = CriarObjGrafico.criarJTextField(20, 398, 150, 20);
        panel1.add(tfChefe);

        panel1.add(CriarObjGrafico.criarJLabel("Cargo", 180, 380, 130, 14));
        tfCargo = CriarObjGrafico.criarJTextField(180, 398, 130, 20);
        panel1.add(tfCargo);

        panel1.add(CriarObjGrafico.criarJLabel("Setor", 320, 380, 90, 14));
        SetorControl setorControl = new SetorControl(daoFactory);
        itensCombo.clear();
        List<Setor> listaSetor = null;
        try {
            listaSetor = setorControl.getLista();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        for (Setor setor : listaSetor) {
            itensCombo.add(setor.getNome());
        }
        cbSetor = CriarObjGrafico.criarJComboBox(itensCombo, 320, 398, 110, 20);
        panel1.add(cbSetor);

        panel1.add(CriarObjGrafico.criarJLabel("Crachá", 440, 380, 130, 14));
        tfCracha = CriarObjGrafico.criarJTextField(440, 398, 100, 20);
        tfCracha.setDocument(new CamposInt());
        panel1.add(tfCracha);

        panel1.add(CriarObjGrafico.criarJLabel("Banco", 550, 380, 130, 14));
        tfBanco = CriarObjGrafico.criarJTextField(550, 398, 120, 20);
        panel1.add(tfBanco);

        panel1.add(CriarObjGrafico.criarJLabel("Agência", 680, 380, 130, 14));
        ftfAgencia = CriarObjGrafico.criarJFormattedTextField("####-#", 680, 398, 130, 20);
        panel1.add(ftfAgencia);

        panel1.add(CriarObjGrafico.criarJLabel("Conta", 20, 420, 130, 14));
        ftfConta = CriarObjGrafico.criarJFormattedTextField("#######", 20, 438, 120, 20);
        panel1.add(ftfConta);

        panel1.add(CriarObjGrafico.criarJLabel("Dígito Conta", 150, 420, 130, 14));
        ftfDigitoConta = CriarObjGrafico.criarJFormattedTextField("#", 150, 438, 100, 20);
        panel1.add(ftfDigitoConta);

        panel1.add(CriarObjGrafico.criarJLabel("Conta FGTS", 260, 420, 130, 14));
        ftfContaFGTS = CriarObjGrafico.criarJFormattedTextField("######", 260, 438, 100, 20);
        panel1.add(ftfContaFGTS);

        panel1.add(CriarObjGrafico.criarJLabel("Data Pagamento", 370, 420, 100, 14));
        lbDataPagto = CriarObjGrafico.criarJLabel("", 467, 423, 10, 14);
        lbDataPagto.setForeground(Color.RED);
        panel1.add(lbDataPagto);
        ftfDataPagamento = CriarObjGrafico.criarJFormattedTextField("##/##/####", 370, 438, 100, 20);
        panel1.add(ftfDataPagamento);

        panel1.add(CriarObjGrafico.criarJLabel("Dependente IR", 480, 420, 130, 14));
        tfDependenteIR = CriarObjGrafico.criarJTextField(480, 438, 100, 20);
        tfDependenteIR.setDocument(new CamposInt());
        panel1.add(tfDependenteIR);

        panel1.add(CriarObjGrafico.criarJLabel("Classe Contribuicao IR", 590, 420, 130, 14));
        tfClasseContribuicaoIR = CriarObjGrafico.criarJTextField(590, 438, 130, 20);
        tfClasseContribuicaoIR.setDocument(new CamposInt());
        panel1.add(tfClasseContribuicaoIR);

        panel1.add(CriarObjGrafico.criarJLabel("Saldo FGTS", 730, 420, 130, 14));
        tfSaldoFGTS = CriarObjGrafico.criarJTextField(730, 438, 110, 20);
        panel1.add(tfSaldoFGTS);

        panel1.add(CriarObjGrafico.criarJLabel("Hora Inicial", 20, 460, 100, 14));
        ftfHoraInicial = CriarObjGrafico.criarJFormattedTextField("##:##", 20, 478, 100, 20);
        panel1.add(ftfHoraInicial);

        panel1.add(CriarObjGrafico.criarJLabel("Hora Final", 130, 460, 100, 14));
        ftfHoraFinal = CriarObjGrafico.criarJFormattedTextField("##:##", 130, 478, 100, 20);
        panel1.add(ftfHoraFinal);

        panel1.add(CriarObjGrafico.criarJLabel("Horas Semanais", 240, 460, 100, 14));
        ftfHorasSemanais = CriarObjGrafico.criarJFormattedTextField("##:##", 240, 478, 100, 20);
        panel1.add(ftfHorasSemanais);

        panel1.add(CriarObjGrafico.criarJLabel("Horas Mensais", 360, 460, 100, 14));
        ftfHorasMensais = CriarObjGrafico.criarJFormattedTextField("##:##", 360, 478, 100, 20);
        panel1.add(ftfHorasMensais);

        panel1.add(CriarObjGrafico.criarJLabel("Salário Inicial", 470, 460, 130, 14));
        tfSalarioInicial = CriarObjGrafico.criarJTextField(470, 478, 110, 20);
        panel1.add(tfSalarioInicial);

        panel1.add(CriarObjGrafico.criarJLabel("Aumento", 590, 460, 130, 14));
        tfAumento = CriarObjGrafico.criarJTextField(590, 478, 110, 20);
        tfAumento.setText("0.00");
        panel1.add(tfAumento);
        panel1.add(CriarObjGrafico.criarJLabel("%", 702, 481, 130, 14));

        panel1.add(CriarObjGrafico.criarJLabel("Desconto INSS", 713, 460, 130, 14));
        tfDescontoINSS = CriarObjGrafico.criarJTextField(713, 478, 110, 20);
        panel1.add(tfDescontoINSS);
        panel1.add(CriarObjGrafico.criarJLabel("%", 825, 481, 130, 14));

        panel1.add(CriarObjGrafico.criarJLabel("Vale Alimentação", 20, 500, 130, 14));
        tfValeAlimentacao = CriarObjGrafico.criarJTextField(20, 518, 90, 20);
        tfValeAlimentacao.setText("0.00");
        panel1.add(tfValeAlimentacao);

        panel1.add(CriarObjGrafico.criarJLabel("Vale Transporte", 130, 500, 130, 14));
        tfValeTransporte = CriarObjGrafico.criarJTextField(130, 518, 90, 20);
        tfValeTransporte.setText("0.00");
        panel1.add(tfValeTransporte);

        panel1.add(CriarObjGrafico.criarJLabel("Plano Saúde", 240, 500, 130, 14));
        tfPlanoSaude = CriarObjGrafico.criarJTextField(240, 518, 80, 20);
        tfPlanoSaude.setText("0.00");
        panel1.add(tfPlanoSaude);

        panel1.add(CriarObjGrafico.criarJLabel("Seguro Vida", 340, 500, 130, 14));
        tfSeguroVida = CriarObjGrafico.criarJTextField(340, 518, 90, 20);
        tfSeguroVida.setText("0.00");
        panel1.add(tfSeguroVida);

        btCalculaSalario = CriarObjGrafico.criarJButton("Calcular", 437, 513, 86, 26);
        btCalculaSalario.addActionListener(this);
        panel1.add(btCalculaSalario);

        panel1.add(CriarObjGrafico.criarJLabel("Salário Líquido", 527, 500, 130, 14));
        lbSalarioLiqObrig = CriarObjGrafico.criarJLabel("", 613, 503, 10, 14);
        lbSalarioLiqObrig.setForeground(Color.RED);
        panel1.add(lbSalarioLiqObrig);
        tfSalarioLiquido = CriarObjGrafico.criarJTextField(530, 518, 80, 20);
        tfSalarioLiquido.setEditable(false);
        tfSalarioLiquido.setBackground(Color.WHITE);
        panel1.add(tfSalarioLiquido);

        panel1.add(CriarObjGrafico.criarJLabel("Inscrição Estadual", 620, 500, 110, 14));
        ftfInscricaoEstadual = CriarObjGrafico.criarJFormattedTextField("###.###.###", 620, 518, 100, 20);
        panel1.add(ftfInscricaoEstadual);

        panel1.add(CriarObjGrafico.criarJLabel("CNPJ", 730, 500, 90, 14));
        ftfCnpj = CriarObjGrafico.criarJFormattedTextField("###.###.###/####-##", 730, 518, 125, 20);
        panel1.add(ftfCnpj);

        panel1.add(CriarObjGrafico.criarJLabel("Outras Considerações", 20, 540, 130, 14));
        taOutrasConsidereções = CriarObjGrafico.criarJTextArea(panel1, 20, 558, 640, 75);

        btOk = CriarObjGrafico.criarJButton("OK", 764, 565, 86, 26);
        btOk.addActionListener(this);
        panel1.add(btOk);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 764, 595, 86, 26);
        btCancelar.addActionListener(this);
        panel1.add(btCancelar);

        rbAtivo = CriarObjGrafico.criarJRadioButton("Ativo", 685, 540, 80, 20);
        rbAtivo.addItemListener(this);
        panel1.add(rbAtivo);

        rbInativo = CriarObjGrafico.criarJRadioButton("Inativo", 763, 540, 80, 20);
        rbInativo.addItemListener(this);
        panel1.add(rbInativo);

        rbNovo = CriarObjGrafico.criarJRadioButton("Novo", 672, 563, 80, 20);
        rbNovo.addItemListener(this);
        panel1.add(rbNovo);

        rbAlterar = CriarObjGrafico.criarJRadioButton("Alterar", 672, 582, 80, 20);
        rbAlterar.addItemListener(this);
        panel1.add(rbAlterar);

        rbExcluir = CriarObjGrafico.criarJRadioButton("Excluir", 672, 602, 80, 20);
        rbExcluir.addItemListener(this);
        panel1.add(rbExcluir);

        HashSet conj = new HashSet(panel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(900, 690);
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
        lbEstadoObrig.setText("");
        lbDataNascObrig.setText("");
        lbFoneObrig.setText("");
        lbCartTrabObrig.setText("");
        lbSalarioLiqObrig.setText("");
        lbDataContDem.setText("");
        lbDataPagto.setText("");
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
        tfDocumentoEstrangeiro.setText("");
        tfEstabilidade.setText("");
        tfCentroCusto.setText("");
        tfChefe.setText("");
        tfCargo.setText("");
        tfCracha.setText("");
        tfBanco.setText("");
        tfDependenteIR.setText("");
        tfClasseContribuicaoIR.setText("");
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
        ftfInscricaoEstadual.setText("");
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
        cbCidade.setSelectedItem("Selecione");
        cbEstado.setSelectedItem("Selecione");
        cbSetor.setSelectedItem("Selecione");
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
        if ("         /     - ".equals(ftfCarteiraTrabalho.getText())) {
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
            lbDataContDem.setText("*");
        } else {
            lbDataContDem.setText("");
        }
        if ("  /  /    ".equals(ftfDataPagamento.getText())) {
            lbDataPagto.setText("*");
        } else {
            lbDataPagto.setText("");
        }
        if (!"".equals(tfNome.getText()) && !"   .   .   ".equals(ftfRG.getText()) && !"   .   .   -  ".equals(ftfCPF.getText()) && !"".equals(tfEndereco.getText()) && !"".equals(tfBairro.getText()) && !"".equals(tfNumero.getText()) && !"Selecione".equals(cbCidade.getSelectedItem()) && !"Selecione".equals(cbEstado.getSelectedItem()) && !"  /  /    ".equals(ftfDataNasc.getText()) && !"(  )    -    ".equals(ftfFone.getText()) && !"         /     - ".equals(ftfCarteiraTrabalho.getText()) && !"".equals(tfSalarioLiquido.getText()) && !"  /  /    ".equals(ftfDataContrDemissao.getText()) && !"  /  /    ".equals(ftfDataPagamento.getText())) {
            Funcionario funcionario = new Funcionario();
            funcionario.setCodigo(Integer.parseInt(tfCodigo.getText()));
            funcionario.setDigitoConta(VerificaCampos.verificaDigitoConta(ftfDigitoConta));
            funcionario.setCentroCusto(VerificaCampos.verificaInt(tfCentroCusto, "centro custo"));
            funcionario.setCracha(VerificaCampos.verificaInt(tfCracha, "crachá"));
            funcionario.setEstabilidade(VerificaCampos.verificaInt(tfEstabilidade, "estabilidade"));
            funcionario.setDescontoINSS(VerificaCampos.verificaInt(tfDescontoINSS, "desconto INSS"));
            funcionario.setDependentesIR(VerificaCampos.verificaInt(tfDependenteIR, "dependente IR"));
            funcionario.setClasseContribuicaoIR(VerificaCampos.verificaInt(tfClasseContribuicaoIR, "classe contribuição IR"));
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
            funcionario.setCidade((String) cbCidade.getSelectedItem());
            funcionario.setEstado((String) cbEstado.getSelectedItem());
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
            funcionario.setSetor((String) cbSetor.getSelectedItem());
            funcionario.setCep(ftfCEP.getText());
            funcionario.setRacaCor(tfCorRaca.getText());
            funcionario.setHorasSemanais(ftfHorasSemanais.getText());
            funcionario.setHorasMesais(ftfHorasMensais.getText());
            funcionario.setHoraInicial(ftfHoraInicial.getText());
            funcionario.setHoraFinal(ftfHoraFinal.getText());
            funcionario.setInscricaoEstadual(ftfInscricaoEstadual.getText());
            funcionario.setCnpj(ftfCnpj.getText());
            funcionario.setOutrasConsideracoes(taOutrasConsidereções.getText());
            funcionario.setTipoContrato(tfTipoContrato.getText());
            funcionario.setNumeroContrato(tfNumeroContrato.getText());
            funcionario.setDocumentoEstrangeiro(tfDocumentoEstrangeiro.getText());
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
        if (controle.arqFuncVazio()) {
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
        if (controle.arqFuncVazio()) {
            JOptionPane.showMessageDialog(null, "Não há funcionários cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String cpf = JOptionPane.showInputDialog(null, "Informe o cpf do funcionário a ser recuperado:", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            if (cpf != null) {
                Funcionario funcionario = controle.getFuncionario(cpf);
                if (funcionario != null) {
                    limparTela();
                    tfCodigo.setText(Integer.toString(funcionario.getCodigo()));
                    ftfDigitoConta.setText(VerificaCampos.recuperaCampoStr(funcionario.getDigitoConta()));
                    tfCentroCusto.setText(VerificaCampos.recuperaCampoStr(funcionario.getCentroCusto()));
                    tfCracha.setText(VerificaCampos.recuperaCampoStr(funcionario.getCracha()));
                    tfEstabilidade.setText(VerificaCampos.recuperaCampoStr(funcionario.getEstabilidade()));
                    tfDescontoINSS.setText(VerificaCampos.recuperaCampoStr(funcionario.getDescontoINSS()));
                    tfDependenteIR.setText(VerificaCampos.recuperaCampoStr(funcionario.getDependentesIR()));
                    tfClasseContribuicaoIR.setText(VerificaCampos.recuperaCampoStr(funcionario.getClasseContribuicaoIR()));
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
                    cbCidade.setSelectedItem(funcionario.getCidade());
                    cbEstado.setSelectedItem(funcionario.getEstado());
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
                    cbSetor.setSelectedItem(funcionario.getSetor());
                    ftfCEP.setText(funcionario.getCep());
                    tfCorRaca.setText(funcionario.getRacaCor());
                    ftfHorasSemanais.setText(funcionario.getHorasSemanais());
                    ftfHorasMensais.setText(funcionario.getHorasMesais());
                    ftfHoraInicial.setText(funcionario.getHoraInicial());
                    ftfHoraFinal.setText(funcionario.getHoraFinal());
                    ftfInscricaoEstadual.setText(funcionario.getInscricaoEstadual());
                    ftfCnpj.setText(funcionario.getCnpj());
                    taOutrasConsidereções.setText(funcionario.getOutrasConsideracoes());
                    tfTipoContrato.setText(funcionario.getTipoContrato());
                    tfNumeroContrato.setText(funcionario.getNumeroContrato());
                    tfDocumentoEstrangeiro.setText(funcionario.getDocumentoEstrangeiro());
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
        if (controle.arqFuncVazio()) {
            JOptionPane.showMessageDialog(null, "Não há funcionários cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            PesquisaFuncionario pfn = new PesquisaFuncionario(controle);
            pfn.setModal(true);
            pfn.setVisible(true);
        }
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
}

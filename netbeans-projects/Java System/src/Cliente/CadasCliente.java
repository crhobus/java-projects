package Cliente;

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
import javax.swing.JTextField;

import Cidade.CidadeControl;
import DAOFactory.DAOFactory;
import FormatacaoCampos.CamposInt;
import FormatacaoCampos.CriarObjGrafico;
import FormatacaoCampos.VerificaCampos;
import Modelo.Cidade;
import Modelo.Cliente;

public class CadasCliente extends JDialog implements ActionListener, ItemListener {

    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfOpRealizada,
            tfCredito, tfDebito, tfNome, tfProfissao, tfRenda, tfEmpresa,
            tfNaturalidade, tfIdade, tfAnoTemTrab, tfMesTemTrab, tfEndereco,
            tfBairro, tfNumero, tfComplemento, tfReferencia, tfEmail, tfMSN,
            tfSkype, tfDescricao, tfNomePai, tfProfissaoPai, tfRendaPai,
            tfEmpresaPai, tfAnoTemTrabPai, tfMesTemTrabPai, tfNomeMae,
            tfProfissaoMae, tfRendaMae, tfEmpresaMae, tfAnoTemTrabMae,
            tfMesTemTrabMae;
    private JFormattedTextField ftfRG, ftfCPF, ftfFoneEmpresa, ftfDataNasc,
            ftfCEP, ftfFone, ftfCelular1, ftfCelular2, ftfFax, ftfRGPai,
            ftfCPFPai, ftfFoneEmpresaPai, ftfContato1Pai, ftfContato2Pai,
            ftfRGMae, ftfCPFMae, ftfFoneEmpresaMae, ftfContato1Mae,
            ftfContato2Mae;
    private JComboBox cbSexo, cbEstadoCivil, cbTipoPessoa, cbCidade, cbEstado,
            cbEstadoCivilPai, cbTipoPessoaPai, cbEstadoCivilMae,
            cbTipoPessoaMae;
    private JButton btOk, btCancelar, btVer;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private JLabel lbNomeObrig, lbCPFObrig, lbEnderecoObrig, lbBairroObrig,
            lbNumeroObrig, lbCidadeObrig, lbEstadoObrig, lbFoneObrig,
            lbDataNasc;
    private ClienteControl controle;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public CadasCliente(DAOFactory daoFactory) {
        controle = new ClienteControl(daoFactory);
        initComponents(daoFactory);
    }

    private void initComponents(DAOFactory daoFactory) {
        Set<String> itensCombo = new HashSet<String>();
        setTitle("Cadastro de Clientes");
        setLayout(null);
        JPanel panel1 = CriarObjGrafico.criarJPanel(10, 10, 865, 620);
        add(panel1);
        JPanel panel2 = CriarObjGrafico.criarJPanel(0, 310, 865, 115);
        panel1.add(panel2);

        panel1.add(CriarObjGrafico.criarJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodClie()));
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
        tfUltAlteracao.addActionListener(this);
        panel1.add(tfUltAlteracao);

        panel1.add(CriarObjGrafico.criarJLabel("Operação Realizada", 395, 20, 130, 14));
        tfOpRealizada = CriarObjGrafico.criarJTextField(395, 38, 240, 20);
        panel1.add(tfOpRealizada);

        panel1.add(CriarObjGrafico.criarJLabel("Crédito", 640, 20, 90, 14));
        tfCredito = CriarObjGrafico.criarJTextField(640, 38, 100, 20);
        tfCredito.setText("0.0");
        tfCredito.setEditable(false);
        tfCredito.setBackground(Color.WHITE);
        panel1.add(tfCredito);

        panel1.add(CriarObjGrafico.criarJLabel("Débito", 745, 20, 90, 14));
        tfDebito = CriarObjGrafico.criarJTextField(745, 38, 100, 20);
        tfDebito.setText("0.0");
        tfDebito.setEditable(false);
        tfDebito.setBackground(Color.WHITE);
        panel1.add(tfDebito);

        panel1.add(CriarObjGrafico.criarJLabel("Nome", 20, 60, 80, 14));
        lbNomeObrig = CriarObjGrafico.criarJLabel("", 60, 63, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel1.add(lbNomeObrig);
        tfNome = CriarObjGrafico.criarJTextField(20, 78, 244, 20);
        panel1.add(tfNome);

        panel1.add(CriarObjGrafico.criarJLabel("RG", 270, 60, 80, 14));
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

        panel1.add(CriarObjGrafico.criarJLabel("Profissão", 20, 100, 90, 14));
        tfProfissao = CriarObjGrafico.criarJTextField(20, 118, 120, 20);
        panel1.add(tfProfissao);

        panel1.add(CriarObjGrafico.criarJLabel("Renda", 148, 100, 80, 14));
        tfRenda = CriarObjGrafico.criarJTextField(148, 118, 100, 20);
        panel1.add(tfRenda);

        panel1.add(CriarObjGrafico.criarJLabel("Empresa", 255, 100, 80, 14));
        tfEmpresa = CriarObjGrafico.criarJTextField(255, 118, 100, 20);
        panel1.add(tfEmpresa);

        panel1.add(CriarObjGrafico.criarJLabel("Fone Empresa", 365, 100, 100, 14));
        ftfFoneEmpresa = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 365, 118, 120, 20);
        panel1.add(ftfFoneEmpresa);

        panel1.add(CriarObjGrafico.criarJLabel("Tempo Trab.", 494, 120, 100, 14));
        panel1.add(CriarObjGrafico.criarJLabel("Anos", 574, 100, 100, 14));
        tfAnoTemTrab = CriarObjGrafico.criarJTextField(572, 118, 40, 20);
        tfAnoTemTrab.setDocument(new CamposInt());
        panel1.add(tfAnoTemTrab);

        panel1.add(CriarObjGrafico.criarJLabel("Meses", 620, 100, 100, 14));
        tfMesTemTrab = CriarObjGrafico.criarJTextField(620, 118, 40, 20);
        tfMesTemTrab.setDocument(new CamposInt());
        panel1.add(tfMesTemTrab);

        panel1.add(CriarObjGrafico.criarJLabel("Data Nascimento", 675, 100, 100, 14));
        lbDataNasc = CriarObjGrafico.criarJLabel("", 780, 103, 10, 14);
        lbDataNasc.setForeground(Color.RED);
        panel1.add(lbDataNasc);
        ftfDataNasc = CriarObjGrafico.criarJFormattedTextField("##/##/####", 675, 118, 120, 20);
        panel1.add(ftfDataNasc);

        panel1.add(CriarObjGrafico.criarJLabel("Naturalidade", 20, 140, 90, 14));
        tfNaturalidade = CriarObjGrafico.criarJTextField(20, 158, 130, 20);
        panel1.add(tfNaturalidade);

        panel1.add(CriarObjGrafico.criarJLabel("Idade", 157, 140, 80, 14));
        tfIdade = CriarObjGrafico.criarJTextField(157, 158, 100, 20);
        tfIdade.setDocument(new CamposInt());
        panel1.add(tfIdade);

        panel1.add(CriarObjGrafico.criarJLabel("CEP", 266, 140, 80, 14));
        ftfCEP = CriarObjGrafico.criarJFormattedTextField("#####-###", 266, 158, 120, 20);
        panel1.add(ftfCEP);

        panel1.add(CriarObjGrafico.criarJLabel("Endereço", 395, 140, 80, 14));
        lbEnderecoObrig = CriarObjGrafico.criarJLabel("", 455, 143, 10, 14);
        lbEnderecoObrig.setForeground(Color.RED);
        panel1.add(lbEnderecoObrig);
        tfEndereco = CriarObjGrafico.criarJTextField(395, 158, 160, 20);
        panel1.add(tfEndereco);

        panel1.add(CriarObjGrafico.criarJLabel("Bairro", 560, 140, 80, 14));
        lbBairroObrig = CriarObjGrafico.criarJLabel("", 600, 143, 10, 14);
        lbBairroObrig.setForeground(Color.RED);
        panel1.add(lbBairroObrig);
        tfBairro = CriarObjGrafico.criarJTextField(560, 158, 150, 20);
        panel1.add(tfBairro);

        panel1.add(CriarObjGrafico.criarJLabel("Numero", 720, 140, 80, 14));
        lbNumeroObrig = CriarObjGrafico.criarJLabel("", 770, 143, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel1.add(lbNumeroObrig);
        tfNumero = CriarObjGrafico.criarJTextField(720, 158, 100, 20);
        tfNumero.setDocument(new CamposInt());
        panel1.add(tfNumero);

        panel1.add(CriarObjGrafico.criarJLabel("Complemento", 20, 180, 90, 14));
        tfComplemento = CriarObjGrafico.criarJTextField(20, 198, 110, 20);
        panel1.add(tfComplemento);

        panel1.add(CriarObjGrafico.criarJLabel("Cidade", 135, 180, 80, 14));
        lbCidadeObrig = CriarObjGrafico.criarJLabel("", 180, 183, 10, 14);
        lbCidadeObrig.setForeground(Color.RED);
        panel1.add(lbCidadeObrig);
        CidadeControl cidadeControl = new CidadeControl(daoFactory);
        itensCombo.clear();
        List<Cidade> listaCidade = null;
        try {
            listaCidade = cidadeControl.getLista();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        for (Cidade cidade : listaCidade) {
            itensCombo.add(cidade.getCidade());
        }
        cbCidade = CriarObjGrafico.criarJComboBox(itensCombo, 135, 198, 120, 20);
        panel1.add(cbCidade);

        panel1.add(CriarObjGrafico.criarJLabel("Estado", 263, 180, 80, 14));
        lbEstadoObrig = CriarObjGrafico.criarJLabel("", 310, 183, 10, 14);
        lbEstadoObrig.setForeground(Color.RED);
        panel1.add(lbEstadoObrig);
        itensCombo.clear();
        for (Cidade estado : listaCidade) {
            itensCombo.add(estado.getEstado());
        }
        cbEstado = CriarObjGrafico.criarJComboBox(itensCombo, 263, 198, 120, 20);
        panel1.add(cbEstado);

        panel1.add(CriarObjGrafico.criarJLabel("Referência", 390, 180, 80, 14));
        tfReferencia = CriarObjGrafico.criarJTextField(390, 198, 170, 20);
        panel1.add(tfReferencia);

        panel1.add(CriarObjGrafico.criarJLabel("Fone", 570, 180, 70, 14));
        lbFoneObrig = CriarObjGrafico.criarJLabel("", 605, 183, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel1.add(lbFoneObrig);
        ftfFone = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 570, 198, 120, 20);
        panel1.add(ftfFone);

        panel1.add(CriarObjGrafico.criarJLabel("Celular 1", 697, 180, 80, 14));
        ftfCelular1 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 697, 198, 120, 20);
        panel1.add(ftfCelular1);

        panel1.add(CriarObjGrafico.criarJLabel("Celular 2", 20, 220, 80, 14));
        ftfCelular2 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 20, 238, 120, 20);
        panel1.add(ftfCelular2);

        panel1.add(CriarObjGrafico.criarJLabel("Fax", 145, 220, 60, 14));
        ftfFax = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 145, 238, 120, 20);
        panel1.add(ftfFax);

        panel1.add(CriarObjGrafico.criarJLabel("E-Mail", 275, 220, 70, 14));
        tfEmail = CriarObjGrafico.criarJTextField(275, 238, 280, 20);
        panel1.add(tfEmail);

        panel1.add(CriarObjGrafico.criarJLabel("MSN", 560, 220, 70, 14));
        tfMSN = CriarObjGrafico.criarJTextField(560, 238, 285, 20);
        panel1.add(tfMSN);

        panel1.add(CriarObjGrafico.criarJLabel("Skype", 20, 260, 80, 14));
        tfSkype = CriarObjGrafico.criarJTextField(20, 278, 280, 20);
        panel1.add(tfSkype);

        panel1.add(CriarObjGrafico.criarJLabel("Descrição", 307, 260, 80, 14));
        tfDescricao = CriarObjGrafico.criarJTextField(307, 278, 537, 20);
        panel1.add(tfDescricao);

        panel2.add(CriarObjGrafico.criarJLabel("Dados Pai", 10, 5, 80, 14));

        panel2.add(CriarObjGrafico.criarJLabel("Nome", 20, 25, 90, 14));
        tfNomePai = CriarObjGrafico.criarJTextField(20, 43, 230, 20);
        panel2.add(tfNomePai);

        panel2.add(CriarObjGrafico.criarJLabel("RG", 256, 25, 80, 14));
        ftfRGPai = CriarObjGrafico.criarJFormattedTextField("###.###.###", 256, 43, 100, 20);
        panel2.add(ftfRGPai);

        panel2.add(CriarObjGrafico.criarJLabel("CPF", 361, 25, 80, 14));
        ftfCPFPai = CriarObjGrafico.criarJFormattedTextField("###.###.###-##", 361, 43, 120, 20);
        panel2.add(ftfCPFPai);

        panel2.add(CriarObjGrafico.criarJLabel("Estado Civil", 488, 25, 120, 14));
        itensCombo.clear();
        itensCombo.add("Casado");
        itensCombo.add("Solteiro");
        cbEstadoCivilPai = CriarObjGrafico.criarJComboBox(itensCombo, 488, 43, 110, 20);
        panel2.add(cbEstadoCivilPai);

        panel2.add(CriarObjGrafico.criarJLabel("Tipo Pessoa", 611, 25, 90, 14));
        itensCombo.clear();
        itensCombo.add("Física");
        itensCombo.add("Jurídica");
        cbTipoPessoaPai = CriarObjGrafico.criarJComboBox(itensCombo, 611, 43, 110, 20);
        panel2.add(cbTipoPessoaPai);

        panel2.add(CriarObjGrafico.criarJLabel("Profissão", 730, 25, 90, 14));
        tfProfissaoPai = CriarObjGrafico.criarJTextField(730, 43, 120, 20);
        panel2.add(tfProfissaoPai);

        panel2.add(CriarObjGrafico.criarJLabel("Renda", 20, 65, 80, 14));
        tfRendaPai = CriarObjGrafico.criarJTextField(20, 83, 100, 20);
        panel2.add(tfRendaPai);

        panel2.add(CriarObjGrafico.criarJLabel("Empresa", 127, 65, 80, 14));
        tfEmpresaPai = CriarObjGrafico.criarJTextField(127, 83, 100, 20);
        panel2.add(tfEmpresaPai);

        panel2.add(CriarObjGrafico.criarJLabel("Fone Empresa", 240, 65, 100, 14));
        ftfFoneEmpresaPai = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 240, 83, 120, 20);
        panel2.add(ftfFoneEmpresaPai);

        panel2.add(CriarObjGrafico.criarJLabel("Tempo Trab.", 365, 85, 100, 14));
        panel2.add(CriarObjGrafico.criarJLabel("Anos", 443, 65, 100, 14));
        tfAnoTemTrabPai = CriarObjGrafico.criarJTextField(443, 83, 40, 20);
        tfAnoTemTrabPai.setDocument(new CamposInt());
        panel2.add(tfAnoTemTrabPai);

        panel2.add(CriarObjGrafico.criarJLabel("Meses", 490, 65, 100, 14));
        tfMesTemTrabPai = CriarObjGrafico.criarJTextField(490, 83, 40, 20);
        tfMesTemTrabPai.setDocument(new CamposInt());
        panel2.add(tfMesTemTrabPai);

        panel2.add(CriarObjGrafico.criarJLabel("Contato 1", 540, 65, 80, 14));
        ftfContato1Pai = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 540, 83, 120, 20);
        panel2.add(ftfContato1Pai);

        panel2.add(CriarObjGrafico.criarJLabel("Contato 2", 670, 65, 80, 14));
        ftfContato2Pai = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 670, 83, 120, 20);
        panel2.add(ftfContato2Pai);

        panel1.add(CriarObjGrafico.criarJLabel("Dados Mãe", 10, 430, 80, 14));

        panel1.add(CriarObjGrafico.criarJLabel("Nome", 20, 455, 90, 14));
        tfNomeMae = CriarObjGrafico.criarJTextField(20, 473, 230, 20);
        panel1.add(tfNomeMae);

        panel1.add(CriarObjGrafico.criarJLabel("RG", 256, 455, 80, 14));
        ftfRGMae = CriarObjGrafico.criarJFormattedTextField("###.###.###", 256, 473, 100, 20);
        panel1.add(ftfRGMae);

        panel1.add(CriarObjGrafico.criarJLabel("CPF", 361, 455, 80, 14));
        ftfCPFMae = CriarObjGrafico.criarJFormattedTextField("###.###.###-##", 361, 473, 120, 20);
        panel1.add(ftfCPFMae);

        panel1.add(CriarObjGrafico.criarJLabel("Estado Civil", 488, 455, 120, 14));
        itensCombo.clear();
        itensCombo.add("Casado");
        itensCombo.add("Solteiro");
        cbEstadoCivilMae = CriarObjGrafico.criarJComboBox(itensCombo, 488, 473, 110, 20);
        panel1.add(cbEstadoCivilMae);

        panel1.add(CriarObjGrafico.criarJLabel("Tipo Pessoa", 611, 455, 90, 14));
        itensCombo.clear();
        itensCombo.add("Física");
        itensCombo.add("Jurídica");
        cbTipoPessoaMae = CriarObjGrafico.criarJComboBox(itensCombo, 611, 473, 110, 20);
        panel1.add(cbTipoPessoaMae);

        panel1.add(CriarObjGrafico.criarJLabel("Profissão", 730, 455, 90, 14));
        tfProfissaoMae = CriarObjGrafico.criarJTextField(730, 473, 120, 20);
        panel1.add(tfProfissaoMae);

        panel1.add(CriarObjGrafico.criarJLabel("Renda", 20, 495, 80, 14));
        tfRendaMae = CriarObjGrafico.criarJTextField(20, 513, 100, 20);
        panel1.add(tfRendaMae);

        panel1.add(CriarObjGrafico.criarJLabel("Empresa", 127, 495, 80, 14));
        tfEmpresaMae = CriarObjGrafico.criarJTextField(127, 513, 100, 20);
        panel1.add(tfEmpresaMae);

        panel1.add(CriarObjGrafico.criarJLabel("Fone Empresa", 240, 495, 100, 14));
        ftfFoneEmpresaMae = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 240, 513, 120, 20);
        panel1.add(ftfFoneEmpresaMae);

        panel1.add(CriarObjGrafico.criarJLabel("Tempo Trab.", 365, 515, 100, 14));
        panel1.add(CriarObjGrafico.criarJLabel("Anos", 443, 495, 100, 14));
        tfAnoTemTrabMae = CriarObjGrafico.criarJTextField(443, 513, 40, 20);
        tfAnoTemTrabMae.setDocument(new CamposInt());
        panel1.add(tfAnoTemTrabMae);

        panel1.add(CriarObjGrafico.criarJLabel("Meses", 490, 495, 100, 14));
        tfMesTemTrabMae = CriarObjGrafico.criarJTextField(490, 513, 40, 20);
        tfMesTemTrabMae.setDocument(new CamposInt());
        panel1.add(tfMesTemTrabMae);

        panel1.add(CriarObjGrafico.criarJLabel("Contato 1", 540, 495, 80, 14));
        ftfContato1Mae = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 540, 513, 120, 20);
        panel1.add(ftfContato1Mae);

        panel1.add(CriarObjGrafico.criarJLabel("Contato 2", 670, 495, 80, 14));
        ftfContato2Mae = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 670, 513, 120, 20);
        panel1.add(ftfContato2Mae);

        btOk = CriarObjGrafico.criarJButton("OK", 764, 545, 86, 26);
        btOk.addActionListener(this);
        panel1.add(btOk);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 764, 575, 86, 26);
        btCancelar.addActionListener(this);
        panel1.add(btCancelar);

        rbNovo = CriarObjGrafico.criarJRadioButton("Novo", 672, 543, 80, 20);
        rbNovo.addItemListener(this);
        panel1.add(rbNovo);

        rbAlterar = CriarObjGrafico.criarJRadioButton("Alterar", 672, 562, 80, 20);
        rbAlterar.addItemListener(this);
        panel1.add(rbAlterar);

        rbExcluir = CriarObjGrafico.criarJRadioButton("Excluir", 672, 582, 80, 20);
        rbExcluir.addItemListener(this);
        panel1.add(rbExcluir);

        HashSet conj = new HashSet(panel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        panel2.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(900, 675);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodClie()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfOpRealizada.setText("");
        tfCredito.setText("0.0");
        tfDebito.setText("0.0");
        tfNome.setText("");
        tfProfissao.setText("");
        tfRenda.setText("");
        tfEmpresa.setText("");
        tfNaturalidade.setText("");
        tfIdade.setText("");
        tfAnoTemTrab.setText("");
        tfMesTemTrab.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfComplemento.setText("");
        tfReferencia.setText("");
        tfEmail.setText("");
        tfMSN.setText("");
        tfSkype.setText("");
        tfDescricao.setText("");
        tfNomePai.setText("");
        tfProfissaoPai.setText("");
        tfRendaPai.setText("");
        tfEmpresaPai.setText("");
        tfAnoTemTrabPai.setText("");
        tfMesTemTrabPai.setText("");
        tfNomeMae.setText("");
        tfProfissaoMae.setText("");
        tfRendaMae.setText("");
        tfEmpresaMae.setText("");
        tfAnoTemTrabMae.setText("");
        tfMesTemTrabMae.setText("");
        ftfRG.setText("");
        ftfCPF.setText("");
        ftfFoneEmpresa.setText("");
        ftfDataNasc.setText("");
        ftfCEP.setText("");
        ftfFone.setText("");
        ftfCelular1.setText("");
        ftfCelular2.setText("");
        ftfFax.setText("");
        ftfRGPai.setText("");
        ftfCPFPai.setText("");
        ftfFoneEmpresaPai.setText("");
        ftfContato1Pai.setText("");
        ftfContato2Pai.setText("");
        ftfRGMae.setText("");
        ftfCPFMae.setText("");
        ftfFoneEmpresaMae.setText("");
        ftfContato1Mae.setText("");
        ftfContato2Mae.setText("");
        cbSexo.setSelectedItem("Selecione");
        cbEstadoCivil.setSelectedItem("Selecione");
        cbTipoPessoa.setSelectedItem("Selecione");
        cbCidade.setSelectedItem("Selecione");
        cbEstado.setSelectedItem("Selecione");
        cbEstadoCivilPai.setSelectedItem("Selecione");
        cbTipoPessoaPai.setSelectedItem("Selecione");
        cbEstadoCivilMae.setSelectedItem("Selecione");
        cbTipoPessoaMae.setSelectedItem("Selecione");
        lbNomeObrig.setText("");
        lbCPFObrig.setText("");
        lbEnderecoObrig.setText("");
        lbBairroObrig.setText("");
        lbNumeroObrig.setText("");
        lbCidadeObrig.setText("");
        lbEstadoObrig.setText("");
        lbFoneObrig.setText("");
        lbDataNasc.setText("");
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
        if ("(  )    -    ".equals(ftfFone.getText())) {
            lbFoneObrig.setText("*");
        } else {
            lbFoneObrig.setText("");
        }
        if ("  /  /    ".equals(ftfDataNasc.getText())) {
            lbDataNasc.setText("*");
        } else {
            lbDataNasc.setText("");
        }
        if (!"".equals(tfNome.getText()) && !"   .   .   -  ".equals(ftfCPF.getText()) && !"".equals(tfEndereco.getText()) && !"".equals(tfBairro.getText()) && !"".equals(tfNumero.getText()) && !"Selecione".equals(cbCidade.getSelectedItem()) && !"Selecione".equals(cbEstado.getSelectedItem()) && !"(  )    -    ".equals(ftfFone.getText()) && !"  /  /    ".equals(ftfDataNasc.getText())) {
            Cliente cliente = new Cliente();
            cliente.setNome(tfNome.getText());
            cliente.setRg(ftfRG.getText());
            cliente.setCpf(ftfCPF.getText());
            cliente.setSexo((String) cbSexo.getSelectedItem());
            cliente.setEstadoCivil((String) cbEstadoCivil.getSelectedItem());
            cliente.setTipoPessoa((String) cbTipoPessoa.getSelectedItem());
            cliente.setProfissao(tfProfissao.getText());
            cliente.setCep(ftfCEP.getText());
            cliente.setEndereco(tfEndereco.getText());
            cliente.setBairro(tfBairro.getText());
            cliente.setComplemento(tfComplemento.getText());
            cliente.setReferencia(tfReferencia.getText());
            cliente.setCidade((String) cbCidade.getSelectedItem());
            cliente.setEstado((String) cbEstado.getSelectedItem());
            cliente.setEmail(tfEmail.getText());
            cliente.setFone(ftfFone.getText());
            cliente.setCelular1(ftfCelular1.getText());
            cliente.setCelular2(ftfCelular2.getText());
            cliente.setDescricao(tfDescricao.getText());
            cliente.setEmpresa(tfEmpresa.getText());
            cliente.setFoneEmpresa(ftfFoneEmpresa.getText());
            cliente.setFax(ftfFax.getText());
            cliente.setMsn(tfMSN.getText());
            cliente.setSkype(tfSkype.getText());
            cliente.setNaturalidade(tfNaturalidade.getText());
            cliente.setNomePai(tfNomePai.getText());
            cliente.setRgPai(ftfRGPai.getText());
            cliente.setCpfPai(ftfCPFPai.getText());
            cliente.setEstadoCivilPai((String) cbEstadoCivilPai.getSelectedItem());
            cliente.setTipoPessoaPai((String) cbTipoPessoaPai.getSelectedItem());
            cliente.setProfissaoPai(tfProfissaoPai.getText());
            cliente.setContato1Pai(ftfContato1Pai.getText());
            cliente.setContato2Pai(ftfContato2Pai.getText());
            cliente.setEmpresaPai(tfEmpresaPai.getText());
            cliente.setFoneEmpresaPai(ftfFoneEmpresaPai.getText());
            cliente.setNomeMae(tfNomeMae.getText());
            cliente.setRgMae(ftfRGMae.getText());
            cliente.setCpfMae(ftfCPFMae.getText());
            cliente.setEstadoCivilMae((String) cbEstadoCivilMae.getSelectedItem());
            cliente.setTipoPessoaMae((String) cbTipoPessoaMae.getSelectedItem());
            cliente.setProfissaoMae(tfProfissaoMae.getText());
            cliente.setContato1Mae(ftfContato1Mae.getText());
            cliente.setContato2Mae(ftfContato2Mae.getText());
            cliente.setEmpresaMae(tfEmpresaMae.getText());
            cliente.setFoneEmpresaMae(ftfFoneEmpresaMae.getText());
            cliente.setOpRealizada(tfOpRealizada.getText());
            cliente.setCodigo(Integer.parseInt(tfCodigo.getText()));
            cliente.setNumero(VerificaCampos.verificaInt(tfNumero, "número"));
            cliente.setIdade(VerificaCampos.verificaInt(tfIdade, "idade"));
            cliente.setAnoTempoTrab(VerificaCampos.verificaInt(tfAnoTemTrab, "ano tempo trabalho"));
            cliente.setMesTempoTrab(VerificaCampos.verificaInt(tfMesTemTrab, "mes tempo trabalho"));
            cliente.setAnoTempoTrabPai(VerificaCampos.verificaInt(tfAnoTemTrabPai, "ano tempo trabalho pai"));
            cliente.setMesTempoTrabPai(VerificaCampos.verificaInt(tfMesTemTrabPai, "mes tempo trabalho pai"));
            cliente.setAnoTempoTrabMae(VerificaCampos.verificaInt(tfAnoTemTrabMae, "ano tempo trabalho mãe"));
            cliente.setMesTempoTrabMae(VerificaCampos.verificaInt(tfMesTemTrabMae, "mes tempo trabalho mãe"));
            cliente.setDataNasc(formatDate.parse(ftfDataNasc.getText()));
            cliente.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            cliente.setUltAlteracao(new Date());
            cliente.setRenda(VerificaCampos.verificaDouble(tfRenda, "renda"));
            cliente.setRendaPai(VerificaCampos.verificaDouble(tfRendaPai, "renda pai"));
            cliente.setCredito(Double.parseDouble(tfCredito.getText()));
            cliente.setDebito(Double.parseDouble(tfDebito.getText()));
            cliente.setRendaMae(VerificaCampos.verificaDouble(tfRendaMae, "renda mae"));
            Cliente clienteLido = controle.getCliente(ftfCPF.getText());
            if (clienteLido != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "O cliente " + cliente.getNome() + " com o cpf " + cliente.getCpf() + " ja esta cadastrado deseja substituilo? ", "Cliente", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    cliente.setCodigo(clienteLido.getCodigo());
                    if (controle.setCliente(cliente)) {
                        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                } else {
                    return;
                }
            } else {
                if (controle.setCliente(cliente)) {
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                }
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private void excluir() throws Exception {
        if (controle.arqClieVazio()) {
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String cpf = JOptionPane.showInputDialog(null, "Informe o cpf do cliente a ser excluído:", "Cliente", JOptionPane.INFORMATION_MESSAGE);
            if (cpf != null) {
                if (controle.removeCliente(cpf)) {
                    JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.arqClieVazio()) {
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String s = JOptionPane.showInputDialog(null, "Informe o cpf do cliente a ser recuperado:", "Cliente", JOptionPane.INFORMATION_MESSAGE);
            if (s != null) {
                Cliente cliente = controle.getCliente(s);
                if (cliente != null) {
                    limparTela();
                    tfNome.setText(cliente.getNome());
                    ftfRG.setText(cliente.getRg());
                    ftfCPF.setText(cliente.getCpf());
                    cbSexo.setSelectedItem(cliente.getSexo());
                    cbEstadoCivil.setSelectedItem(cliente.getEstadoCivil());
                    cbTipoPessoa.setSelectedItem(cliente.getTipoPessoa());
                    tfProfissao.setText(cliente.getProfissao());
                    ftfCEP.setText(cliente.getCep());
                    tfEndereco.setText(cliente.getEndereco());
                    tfBairro.setText(cliente.getBairro());
                    tfComplemento.setText(cliente.getComplemento());
                    tfReferencia.setText(cliente.getReferencia());
                    cbCidade.setSelectedItem(cliente.getCidade());
                    cbEstado.setSelectedItem(cliente.getEstado());
                    tfEmail.setText(cliente.getEmail());
                    ftfFone.setText(cliente.getFone());
                    ftfCelular1.setText(cliente.getCelular1());
                    ftfCelular2.setText(cliente.getCelular2());
                    tfDescricao.setText(cliente.getDescricao());
                    tfEmpresa.setText(cliente.getEmpresa());
                    ftfFoneEmpresa.setText(cliente.getFoneEmpresa());
                    ftfFax.setText(cliente.getFax());
                    tfMSN.setText(cliente.getMsn());
                    tfSkype.setText(cliente.getSkype());
                    tfNaturalidade.setText(cliente.getNaturalidade());
                    tfNomePai.setText(cliente.getNomePai());
                    ftfRGPai.setText(cliente.getRgPai());
                    ftfCPFPai.setText(cliente.getCpfPai());
                    cbEstadoCivilPai.setSelectedItem(cliente.getEstadoCivilPai());
                    cbTipoPessoaPai.setSelectedItem(cliente.getTipoPessoaPai());
                    tfProfissaoPai.setText(cliente.getProfissaoPai());
                    ftfContato1Pai.setText(cliente.getContato1Pai());
                    ftfContato2Pai.setText(cliente.getContato2Pai());
                    tfEmpresaPai.setText(cliente.getEmpresaPai());
                    ftfFoneEmpresaPai.setText(cliente.getFoneEmpresaPai());
                    tfNomeMae.setText(cliente.getNomeMae());
                    ftfRGMae.setText(cliente.getRgMae());
                    ftfCPFMae.setText(cliente.getCpfMae());
                    cbEstadoCivilMae.setSelectedItem(cliente.getEstadoCivilMae());
                    cbTipoPessoaMae.setSelectedItem(cliente.getTipoPessoaMae());
                    tfProfissaoMae.setText(cliente.getProfissaoMae());
                    ftfContato1Mae.setText(cliente.getContato1Mae());
                    ftfContato2Mae.setText(cliente.getContato2Mae());
                    tfEmpresaMae.setText(cliente.getEmpresaMae());
                    ftfFoneEmpresaMae.setText(cliente.getFoneEmpresaMae());
                    tfOpRealizada.setText(cliente.getOpRealizada());
                    tfCodigo.setText(Integer.toString(cliente.getCodigo()));
                    tfNumero.setText(VerificaCampos.recuperaCampoStr(cliente.getNumero()));
                    tfIdade.setText(VerificaCampos.recuperaCampoStr(cliente.getIdade()));
                    tfAnoTemTrab.setText(VerificaCampos.recuperaCampoStr(cliente.getAnoTempoTrab()));
                    tfMesTemTrab.setText(VerificaCampos.recuperaCampoStr(cliente.getMesTempoTrab()));
                    tfAnoTemTrabPai.setText(VerificaCampos.recuperaCampoStr(cliente.getAnoTempoTrabPai()));
                    tfMesTemTrabPai.setText(VerificaCampos.recuperaCampoStr(cliente.getMesTempoTrabPai()));
                    tfAnoTemTrabMae.setText(VerificaCampos.recuperaCampoStr(cliente.getAnoTempoTrabMae()));
                    tfMesTemTrabMae.setText(VerificaCampos.recuperaCampoStr(cliente.getMesTempoTrabMae()));
                    ftfDataNasc.setText(formatDate.format(cliente.getDataNasc()));
                    tfDataCadas.setText(formatDate.format(cliente.getDataCadastro()) + " as " + formatHora.format(cliente.getDataCadastro()));
                    tfUltAlteracao.setText(formatDate.format(cliente.getUltAlteracao()) + " as " + formatHora.format(cliente.getUltAlteracao()));
                    tfRenda.setText(Double.toString(cliente.getRenda()));
                    tfRendaPai.setText(Double.toString(cliente.getRendaPai()));
                    tfCredito.setText(Double.toString(cliente.getCredito()));
                    tfDebito.setText(Double.toString(cliente.getDebito()));
                    tfRendaMae.setText(Double.toString(cliente.getRendaMae()));
                    JOptionPane.showMessageDialog(null, "Cliente recuperado com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void abrirPesquisaClie() throws Exception {
        if (controle.arqClieVazio()) {
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            PesquisaClie pcl = new PesquisaClie(controle);
            pcl.setModal(true);
            pcl.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Cliente", JOptionPane.ERROR_MESSAGE);
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
                abrirPesquisaClie();
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

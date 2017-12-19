package Transportadora;

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
import Modelo.Transportadora;

public class CadasTransportadora extends JDialog implements ActionListener, ItemListener {

    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome, tfNomeFantasia, tfEndereco, tfBairro,
            tfNumero, tfRegiao, tfPais, tfReferencia, tfEmail, tfMsn, tfBanco, tfDescricao, tfValorFrete, tfIcms,
            tfCofins, tfIpi, tfJuros, tfDescontos;
    private JFormattedTextField ftfInscricaoEstadual, ftfCnpj, ftfInscricaoMunicipal, ftfCEP, ftfFone, ftfCelular1,
            ftfCelular2, ftfFax, ftfCaixaPostal, ftfAgencia, ftfConta, ftfDigitoConta;
    private JComboBox cbTipoPessoa, cbCidade, cbEstado;
    private JTextArea taObservacoes;
    private JLabel lbNomeObrig, lbEnderecoObrig, lbBairroObrig, lbNumeroObrig, lbCidadeObrig, lbEstadoObrig,
            lbFoneObrig;
    private JButton btVer, btOk, btCancelar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private TransportadoraControl controle;

    public CadasTransportadora(DAOFactory daoFactory) {
        controle = new TransportadoraControl(daoFactory);
        initComponents(daoFactory);
    }

    private void initComponents(DAOFactory daoFactory) {
        Set<String> itensCombo = new HashSet<String>();
        setTitle("Cadastro de Transportadora");
        setLayout(null);
        JPanel panel = CriarObjGrafico.criarJPanel(10, 10, 755, 450);
        add(panel);

        panel.add(CriarObjGrafico.criarJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodTrans()));
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
        tfNome = CriarObjGrafico.criarJTextField(398, 38, 335, 20);
        panel.add(tfNome);

        panel.add(CriarObjGrafico.criarJLabel("Nome Fantasia", 20, 60, 110, 14));
        tfNomeFantasia = CriarObjGrafico.criarJTextField(20, 78, 200, 20);
        panel.add(tfNomeFantasia);

        panel.add(CriarObjGrafico.criarJLabel("Inscrição Estadual", 230, 60, 110, 14));
        ftfInscricaoEstadual = CriarObjGrafico.criarJFormattedTextField("###.###.###", 230, 78, 110, 20);
        panel.add(ftfInscricaoEstadual);

        panel.add(CriarObjGrafico.criarJLabel("CNPJ", 350, 60, 90, 14));
        ftfCnpj = CriarObjGrafico.criarJFormattedTextField("###.###.###/####-##", 350, 78, 125, 20);
        panel.add(ftfCnpj);

        panel.add(CriarObjGrafico.criarJLabel("Inscrição Municipal", 485, 60, 110, 14));
        ftfInscricaoMunicipal = CriarObjGrafico.criarJFormattedTextField("##.##.##", 485, 78, 110, 20);
        panel.add(ftfInscricaoMunicipal);

        panel.add(CriarObjGrafico.criarJLabel("Tipo Pessoa", 605, 60, 90, 14));
        itensCombo.add("Física");
        itensCombo.add("Jurídica");
        cbTipoPessoa = CriarObjGrafico.criarJComboBox(itensCombo, 605, 78, 125, 20);
        panel.add(cbTipoPessoa);

        panel.add(CriarObjGrafico.criarJLabel("Endereço", 20, 100, 100, 14));
        lbEnderecoObrig = CriarObjGrafico.criarJLabel("", 80, 103, 10, 14);
        lbEnderecoObrig.setForeground(Color.RED);
        panel.add(lbEnderecoObrig);
        tfEndereco = CriarObjGrafico.criarJTextField(20, 118, 280, 20);
        panel.add(tfEndereco);

        panel.add(CriarObjGrafico.criarJLabel("Bairro", 308, 100, 100, 14));
        lbBairroObrig = CriarObjGrafico.criarJLabel("", 350, 103, 10, 14);
        lbBairroObrig.setForeground(Color.RED);
        panel.add(lbBairroObrig);
        tfBairro = CriarObjGrafico.criarJTextField(308, 118, 170, 20);
        panel.add(tfBairro);

        panel.add(CriarObjGrafico.criarJLabel("Número", 485, 100, 100, 14));
        lbNumeroObrig = CriarObjGrafico.criarJLabel("", 535, 103, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel.add(lbNumeroObrig);
        tfNumero = CriarObjGrafico.criarJTextField(485, 118, 100, 20);
        tfNumero.setDocument(new CamposInt());
        panel.add(tfNumero);

        panel.add(CriarObjGrafico.criarJLabel("Cidade", 595, 100, 90, 14));
        lbCidadeObrig = CriarObjGrafico.criarJLabel("", 640, 103, 10, 14);
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
        cbCidade = CriarObjGrafico.criarJComboBox(itensCombo, 595, 118, 135, 20);
        panel.add(cbCidade);

        panel.add(CriarObjGrafico.criarJLabel("Estado", 20, 140, 90, 14));
        lbEstadoObrig = CriarObjGrafico.criarJLabel("", 65, 143, 10, 14);
        lbEstadoObrig.setForeground(Color.RED);
        panel.add(lbEstadoObrig);
        itensCombo.clear();
        for (Cidade estado : listaCidade) {
            itensCombo.add(estado.getEstado());
        }
        cbEstado = CriarObjGrafico.criarJComboBox(itensCombo, 20, 158, 135, 20);
        panel.add(cbEstado);

        panel.add(CriarObjGrafico.criarJLabel("Região", 165, 140, 100, 14));
        tfRegiao = CriarObjGrafico.criarJTextField(165, 158, 180, 20);
        panel.add(tfRegiao);

        panel.add(CriarObjGrafico.criarJLabel("País", 353, 140, 100, 14));
        tfPais = CriarObjGrafico.criarJTextField(353, 158, 125, 20);
        panel.add(tfPais);

        panel.add(CriarObjGrafico.criarJLabel("CEP", 485, 140, 80, 14));
        ftfCEP = CriarObjGrafico.criarJFormattedTextField("#####-###", 485, 158, 100, 20);
        panel.add(ftfCEP);

        panel.add(CriarObjGrafico.criarJLabel("Fone", 595, 140, 70, 14));
        lbFoneObrig = CriarObjGrafico.criarJLabel("", 630, 143, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel.add(lbFoneObrig);
        ftfFone = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 595, 158, 135, 20);
        panel.add(ftfFone);

        panel.add(CriarObjGrafico.criarJLabel("Celular 1", 20, 180, 70, 14));
        ftfCelular1 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 20, 198, 120, 20);
        panel.add(ftfCelular1);

        panel.add(CriarObjGrafico.criarJLabel("Celular 2", 148, 180, 70, 14));
        ftfCelular2 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 148, 198, 120, 20);
        panel.add(ftfCelular2);

        panel.add(CriarObjGrafico.criarJLabel("Fax", 275, 180, 60, 14));
        ftfFax = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 275, 198, 120, 20);
        panel.add(ftfFax);

        panel.add(CriarObjGrafico.criarJLabel("Caixa Postal", 400, 180, 100, 14));
        ftfCaixaPostal = CriarObjGrafico.criarJFormattedTextField("######", 400, 198, 100, 20);
        panel.add(ftfCaixaPostal);

        panel.add(CriarObjGrafico.criarJLabel("Referência", 508, 180, 80, 14));
        tfReferencia = CriarObjGrafico.criarJTextField(508, 198, 223, 20);
        panel.add(tfReferencia);

        panel.add(CriarObjGrafico.criarJLabel("E-Mail", 20, 220, 60, 14));
        tfEmail = CriarObjGrafico.criarJTextField(20, 238, 310, 20);
        panel.add(tfEmail);

        panel.add(CriarObjGrafico.criarJLabel("MSN", 340, 220, 60, 14));
        tfMsn = CriarObjGrafico.criarJTextField(340, 238, 260, 20);
        panel.add(tfMsn);

        panel.add(CriarObjGrafico.criarJLabel("Banco", 610, 220, 130, 14));
        tfBanco = CriarObjGrafico.criarJTextField(610, 238, 120, 20);
        panel.add(tfBanco);

        panel.add(CriarObjGrafico.criarJLabel("Agência", 20, 260, 130, 14));
        ftfAgencia = CriarObjGrafico.criarJFormattedTextField("####-#", 20, 278, 100, 20);
        panel.add(ftfAgencia);

        panel.add(CriarObjGrafico.criarJLabel("Conta", 130, 260, 130, 14));
        ftfConta = CriarObjGrafico.criarJFormattedTextField("#######", 130, 278, 100, 20);
        panel.add(ftfConta);

        panel.add(CriarObjGrafico.criarJLabel("Dígito Conta", 240, 260, 130, 14));
        ftfDigitoConta = CriarObjGrafico.criarJFormattedTextField("#", 240, 278, 100, 20);
        panel.add(ftfDigitoConta);

        panel.add(CriarObjGrafico.criarJLabel("Descrição", 350, 260, 60, 14));
        tfDescricao = CriarObjGrafico.criarJTextField(350, 278, 380, 20);
        panel.add(tfDescricao);

        panel.add(CriarObjGrafico.criarJLabel("Valor Frete", 20, 300, 100, 14));
        tfValorFrete = CriarObjGrafico.criarJTextField(20, 318, 100, 20);
        tfValorFrete.setText("0.00");
        panel.add(tfValorFrete);
        panel.add(CriarObjGrafico.criarJLabel("%", 125, 321, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("ICMS", 140, 300, 100, 14));
        tfIcms = CriarObjGrafico.criarJTextField(140, 318, 100, 20);
        tfIcms.setText("0.00");
        panel.add(tfIcms);
        panel.add(CriarObjGrafico.criarJLabel("%", 243, 321, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("COFINS", 255, 300, 100, 14));
        tfCofins = CriarObjGrafico.criarJTextField(255, 318, 100, 20);
        tfCofins.setText("0.00");
        panel.add(tfCofins);
        panel.add(CriarObjGrafico.criarJLabel("%", 358, 321, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("IPI", 375, 300, 100, 14));
        tfIpi = CriarObjGrafico.criarJTextField(375, 318, 100, 20);
        tfIpi.setText("0.00");
        panel.add(tfIpi);
        panel.add(CriarObjGrafico.criarJLabel("%", 478, 321, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("Juros a.m.", 496, 300, 100, 14));
        tfJuros = CriarObjGrafico.criarJTextField(496, 318, 100, 20);
        tfJuros.setText("0.00");
        panel.add(tfJuros);
        panel.add(CriarObjGrafico.criarJLabel("%", 600, 321, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("Descontos", 615, 300, 100, 14));
        tfDescontos = CriarObjGrafico.criarJTextField(615, 318, 100, 20);
        tfDescontos.setText("0.00");
        panel.add(tfDescontos);
        panel.add(CriarObjGrafico.criarJLabel("%", 720, 321, 20, 14));

        panel.add(CriarObjGrafico.criarJLabel("Observações", 20, 340, 130, 14));
        taObservacoes = CriarObjGrafico.criarJTextArea(panel, 20, 358, 540, 80);

        btOk = CriarObjGrafico.criarJButton("OK", 654, 365, 86, 26);
        btOk.addActionListener(this);
        panel.add(btOk);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 654, 395, 86, 26);
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        rbNovo = CriarObjGrafico.criarJRadioButton("Novo", 572, 363, 80, 20);
        rbNovo.addItemListener(this);
        panel.add(rbNovo);

        rbAlterar = CriarObjGrafico.criarJRadioButton("Alterar", 572, 382, 80, 20);
        rbAlterar.addItemListener(this);
        panel.add(rbAlterar);

        rbExcluir = CriarObjGrafico.criarJRadioButton("Excluir", 572, 402, 80, 20);
        rbExcluir.addItemListener(this);
        panel.add(rbExcluir);

        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(790, 500);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodTrans()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfNome.setText("");
        tfNomeFantasia.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfRegiao.setText("");
        tfPais.setText("");
        tfReferencia.setText("");
        tfEmail.setText("");
        tfMsn.setText("");
        tfBanco.setText("");
        tfDescricao.setText("");
        tfValorFrete.setText("0.00");
        tfIcms.setText("0.00");
        tfCofins.setText("0.00");
        tfIpi.setText("0.00");
        tfJuros.setText("0.00");
        tfDescontos.setText("0.00");
        ftfInscricaoEstadual.setText("");
        ftfCnpj.setText("");
        ftfInscricaoMunicipal.setText("");
        ftfCEP.setText("");
        ftfFone.setText("");
        ftfCelular1.setText("");
        ftfCelular2.setText("");
        ftfFax.setText("");
        ftfCaixaPostal.setText("");
        ftfAgencia.setText("");
        ftfConta.setText("");
        ftfDigitoConta.setText("");
        cbTipoPessoa.setSelectedItem("Selecione");
        cbCidade.setSelectedItem("Selecione");
        cbEstado.setSelectedItem("Selecione");
        lbNomeObrig.setText("");
        lbEnderecoObrig.setText("");
        lbBairroObrig.setText("");
        lbNumeroObrig.setText("");
        lbCidadeObrig.setText("");
        lbEstadoObrig.setText("");
        lbFoneObrig.setText("");
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
            Transportadora transportadora = new Transportadora();
            transportadora.setCodigo(Integer.parseInt(tfCodigo.getText()));
            transportadora.setNumero(VerificaCampos.verificaInt(tfNumero, "número"));
            transportadora.setDigitoConta(VerificaCampos.verificaDigitoConta(ftfDigitoConta));
            transportadora.setNome(tfNome.getText());
            transportadora.setNomeFantasia(tfNomeFantasia.getText());
            transportadora.setInscricaoEstadual(ftfInscricaoEstadual.getText());
            transportadora.setCnpj(ftfCnpj.getText());
            transportadora.setInscricaoMunicipal(ftfInscricaoMunicipal.getText());
            transportadora.setTipoPessoa((String) cbTipoPessoa.getSelectedItem());
            transportadora.setEndereco(tfEndereco.getText());
            transportadora.setBairro(tfBairro.getText());
            transportadora.setCidade((String) cbCidade.getSelectedItem());
            transportadora.setEstado((String) cbEstado.getSelectedItem());
            transportadora.setRegiao(tfRegiao.getText());
            transportadora.setPais(tfPais.getText());
            transportadora.setCep(ftfCEP.getText());
            transportadora.setFone(ftfFone.getText());
            transportadora.setCelular1(ftfCelular1.getText());
            transportadora.setCelular2(ftfCelular2.getText());
            transportadora.setFax(ftfFax.getText());
            transportadora.setCaixaPostal(ftfCaixaPostal.getText());
            transportadora.setReferencia(tfReferencia.getText());
            transportadora.setEmail(tfEmail.getText());
            transportadora.setMsn(tfMsn.getText());
            transportadora.setBanco(tfBanco.getText());
            transportadora.setAgencia(ftfAgencia.getText());
            transportadora.setConta(ftfConta.getText());
            transportadora.setDescricao(tfDescricao.getText());
            transportadora.setObservacoes(taObservacoes.getText());
            transportadora.setValorFrete(VerificaCampos.verificaDouble(tfValorFrete, "valor frete"));
            transportadora.setIcms(VerificaCampos.verificaDouble(tfIcms, "icms"));
            transportadora.setCofins(VerificaCampos.verificaDouble(tfCofins, "cofins"));
            transportadora.setIpi(VerificaCampos.verificaDouble(tfIpi, "ipi"));
            transportadora.setJuros(VerificaCampos.verificaDouble(tfJuros, "juros a.m."));
            transportadora.setDescontos(VerificaCampos.verificaDouble(tfDescontos, "descontos"));
            transportadora.setDataCadas(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            transportadora.setUltAlteracao(new Date());
            Transportadora transportadoraLida = controle.getTransportadora(tfNome.getText());
            if (transportadoraLida != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "A transportadora " + transportadora.getNome() + " ja esta cadastrada deseja substituila? ", "Transportadora", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    transportadora.setCodigo(transportadoraLida.getCodigo());
                    if (controle.setTransportadora(transportadora)) {
                        JOptionPane.showMessageDialog(null, "Transportadora cadastrada com sucesso", "Transportadora", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                } else {
                    return;
                }
            } else {
                if (controle.setTransportadora(transportadora)) {
                    JOptionPane.showMessageDialog(null, "Transportadora cadastrada com sucesso", "Transportadora", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                }
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private void excluir() throws Exception {
        if (controle.arqTransVazio()) {
            JOptionPane.showMessageDialog(null, "Não há transportadoras cadastradas", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String nome = JOptionPane.showInputDialog(null, "Informe o nome da transportadora a ser excluída:", "Transportadora", JOptionPane.INFORMATION_MESSAGE);
            if (nome != null) {
                if (controle.removeTransportadora(nome)) {
                    JOptionPane.showMessageDialog(null, "Transportadora excluida com sucesso", "Transportadora", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Transportadora não encontrada", "Transportadora", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.arqTransVazio()) {
            JOptionPane.showMessageDialog(null, "Não há transportadoras cadastradas", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String nome = JOptionPane.showInputDialog(null, "Informe o nome da transportadora a ser recuperada:", "Transportadora", JOptionPane.INFORMATION_MESSAGE);
            if (nome != null) {
                Transportadora transportadora = controle.getTransportadora(nome);
                if (transportadora != null) {
                    limparTela();
                    tfCodigo.setText(Integer.toString(transportadora.getCodigo()));
                    tfNumero.setText(VerificaCampos.recuperaCampoStr(transportadora.getNumero()));
                    ftfDigitoConta.setText(VerificaCampos.recuperaCampoStr(transportadora.getDigitoConta()));
                    tfNome.setText(transportadora.getNome());
                    tfNomeFantasia.setText(transportadora.getNomeFantasia());
                    ftfInscricaoEstadual.setText(transportadora.getInscricaoEstadual());
                    ftfCnpj.setText(transportadora.getCnpj());
                    ftfInscricaoMunicipal.setText(transportadora.getInscricaoMunicipal());
                    cbTipoPessoa.setSelectedItem(transportadora.getTipoPessoa());
                    tfEndereco.setText(transportadora.getEndereco());
                    tfBairro.setText(transportadora.getBairro());
                    cbCidade.setSelectedItem(transportadora.getCidade());
                    cbEstado.setSelectedItem(transportadora.getEstado());
                    tfRegiao.setText(transportadora.getRegiao());
                    tfPais.setText(transportadora.getPais());
                    ftfCEP.setText(transportadora.getCep());
                    ftfFone.setText(transportadora.getFone());
                    ftfCelular1.setText(transportadora.getCelular1());
                    ftfCelular2.setText(transportadora.getCelular2());
                    ftfFax.setText(transportadora.getFax());
                    ftfCaixaPostal.setText(transportadora.getCaixaPostal());
                    tfReferencia.setText(transportadora.getReferencia());
                    tfEmail.setText(transportadora.getEmail());
                    tfMsn.setText(transportadora.getMsn());
                    tfBanco.setText(transportadora.getBanco());
                    ftfAgencia.setText(transportadora.getAgencia());
                    ftfConta.setText(transportadora.getConta());
                    tfDescricao.setText(transportadora.getDescricao());
                    taObservacoes.setText(transportadora.getObservacoes());
                    tfValorFrete.setText(Double.toString(transportadora.getValorFrete()));
                    tfIcms.setText(Double.toString(transportadora.getIcms()));
                    tfCofins.setText(Double.toString(transportadora.getCofins()));
                    tfIpi.setText(Double.toString(transportadora.getIpi()));
                    tfJuros.setText(Double.toString(transportadora.getJuros()));
                    tfDescontos.setText(Double.toString(transportadora.getDescontos()));
                    tfDataCadas.setText(formatDate.format(transportadora.getDataCadas()) + " as " + formatHora.format(transportadora.getDataCadas()));
                    tfUltAlteracao.setText(formatDate.format(transportadora.getUltAlteracao()) + " as " + formatHora.format(transportadora.getUltAlteracao()));
                    JOptionPane.showMessageDialog(null, "Transportadora recuperada com sucesso", "Transportadora", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Transportadora não encontrada", "Transportadora", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void abrirPesquisaTrans() throws Exception {
        if (controle.arqTransVazio()) {
            JOptionPane.showMessageDialog(null, "Não há transportadoras cadastradas", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            //PesquisaFornecedor pfr = new PesquisaFornecedor(controle);
            //pfr.setModal(true);
            //pfr.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Transportadora", JOptionPane.ERROR_MESSAGE);
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
                abrirPesquisaTrans();
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

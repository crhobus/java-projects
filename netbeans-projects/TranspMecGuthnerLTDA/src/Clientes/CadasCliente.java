package Clientes;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import Modelo.Cliente;

public class CadasCliente extends JDialog implements ActionListener, ItemListener, FocusListener {

    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome,
            tfEndereco, tfBairro, tfNumero, tfComplemento, tfCidade, tfUF,
            tfReferencia, tfEmpresa, tfProfissao, tfEmail, tfMSN;
    private JFormattedTextField ftfRG, ftfCPF, ftfFone, ftfCelular1,
            ftfCelular2, ftfDataNasc, ftfFoneEmpresa, ftfCEP,
            ftfInscricaoEstadual, ftfCNPJ;
    private JComboBox cbSexo, cbEstadoCivil, cbTipoPessoa;
    private JTextArea taDescricao;
    private JButton btVer, btOk, btCancelar, btPesquisar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private JLabel lbNomeObrig, lbTipoPessoa, lbCPFCNPJObrig, lbEnderecoObrig,
            lbBairroObrig, lbNumeroObrig, lbCidadeObrig, lbUFObrig,
            lbFoneObrig;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private ClienteControl controle;

    public CadasCliente(DAOFactory daoFactory, Connection con) {
        controle = new ClienteControl(daoFactory, con);
        try {
            initComponents();
        } catch (ParseException e) {
        }
    }

    private void initComponents() throws ParseException {
        List<String> itensCombo = new ArrayList<String>();
        setTitle("Cadastro de Clientes");
        setLayout(null);
        JPanel panel = CriarObjGrafico.criarJPanel(10, 10, 650, 400);
        add(panel);

        panel.add(CriarObjGrafico.criarJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodClie()));
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
        tfUltAlteracao.setEditable(false);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfUltAlteracao.addFocusListener(this);
        panel.add(tfUltAlteracao);

        panel.add(CriarObjGrafico.criarJLabel("Nome / Razão Social", 400, 20, 120, 14));
        lbNomeObrig = CriarObjGrafico.criarJLabel("", 520, 23, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel.add(lbNomeObrig);
        tfNome = CriarObjGrafico.criarJTextField(400, 38, 230, 20);
        tfNome.addFocusListener(this);
        panel.add(tfNome);

        panel.add(CriarObjGrafico.criarJLabel("Tipo Pessoa", 20, 60, 90, 14));
        lbTipoPessoa = CriarObjGrafico.criarJLabel("", 95, 63, 10, 14);
        lbTipoPessoa.setForeground(Color.RED);
        panel.add(lbTipoPessoa);
        itensCombo.add("Física");
        itensCombo.add("Jurídica");
        cbTipoPessoa = CriarObjGrafico.criarJComboBox(itensCombo, 20, 78, 85, 20);
        cbTipoPessoa.addFocusListener(this);
        cbTipoPessoa.addActionListener(this);
        panel.add(cbTipoPessoa);

        panel.add(CriarObjGrafico.criarJLabel("CPF / CNPJ", 115, 60, 80, 14));
        lbCPFCNPJObrig = CriarObjGrafico.criarJLabel("", 180, 63, 10, 14);
        lbCPFCNPJObrig.setForeground(Color.RED);
        panel.add(lbCPFCNPJObrig);
        ftfCPF = CriarObjGrafico.criarJFormattedTextField("###.###.###-##", 115, 78, 120, 20);
        ftfCPF.addFocusListener(this);
        ftfCPF.setEditable(false);
        ftfCPF.setBackground(Color.WHITE);
        panel.add(ftfCPF);

        ftfCNPJ = CriarObjGrafico.criarJFormattedTextField("##.###.###/####-##", 115, 78, 120, 20);
        ftfCNPJ.addFocusListener(this);
        ftfCNPJ.setVisible(false);
        panel.add(ftfCNPJ);

        panel.add(CriarObjGrafico.criarJLabel("RG", 245, 60, 80, 14));
        ftfRG = CriarObjGrafico.criarJFormattedTextField("###.###.###", 245, 78, 90, 20);
        ftfRG.addFocusListener(this);
        ftfRG.setEditable(false);
        ftfRG.setBackground(Color.WHITE);
        panel.add(ftfRG);

        panel.add(CriarObjGrafico.criarJLabel("I.E.", 345, 60, 110, 14));
        ftfInscricaoEstadual = CriarObjGrafico.criarJFormattedTextField("###.###.###", 345, 78, 85, 20);
        ftfInscricaoEstadual.addFocusListener(this);
        ftfInscricaoEstadual.setEditable(false);
        ftfInscricaoEstadual.setBackground(Color.WHITE);
        panel.add(ftfInscricaoEstadual);

        panel.add(CriarObjGrafico.criarJLabel("Sexo", 440, 60, 80, 14));
        itensCombo.clear();
        itensCombo.add("Masculino");
        itensCombo.add("Feminino");
        cbSexo = CriarObjGrafico.criarJComboBox(itensCombo, 440, 78, 90, 20);
        cbSexo.addFocusListener(this);
        panel.add(cbSexo);

        panel.add(CriarObjGrafico.criarJLabel("Estado Civil", 540, 60, 120, 14));
        itensCombo.clear();
        itensCombo.add("Casado");
        itensCombo.add("Solteiro");
        cbEstadoCivil = CriarObjGrafico.criarJComboBox(itensCombo, 540, 78, 90, 20);
        cbEstadoCivil.addFocusListener(this);
        panel.add(cbEstadoCivil);

        panel.add(CriarObjGrafico.criarJLabel("Endereço", 20, 100, 80, 14));
        lbEnderecoObrig = CriarObjGrafico.criarJLabel("", 80, 103, 10, 14);
        lbEnderecoObrig.setForeground(Color.RED);
        panel.add(lbEnderecoObrig);
        tfEndereco = CriarObjGrafico.criarJTextField(20, 118, 170, 20);
        tfEndereco.addFocusListener(this);
        panel.add(tfEndereco);

        panel.add(CriarObjGrafico.criarJLabel("Bairro", 200, 100, 80, 14));
        lbBairroObrig = CriarObjGrafico.criarJLabel("", 240, 103, 10, 14);
        lbBairroObrig.setForeground(Color.RED);
        panel.add(lbBairroObrig);
        tfBairro = CriarObjGrafico.criarJTextField(200, 118, 160, 20);
        tfBairro.addFocusListener(this);
        panel.add(tfBairro);

        panel.add(CriarObjGrafico.criarJLabel("Numero", 370, 100, 80, 14));
        lbNumeroObrig = CriarObjGrafico.criarJLabel("", 420, 103, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel.add(lbNumeroObrig);
        tfNumero = CriarObjGrafico.criarJTextField(370, 118, 100, 20);
        tfNumero.setDocument(new CamposInt());
        tfNumero.addFocusListener(this);
        panel.add(tfNumero);

        panel.add(CriarObjGrafico.criarJLabel("Complemento", 480, 100, 90, 14));
        tfComplemento = CriarObjGrafico.criarJTextField(480, 118, 150, 20);
        tfComplemento.addFocusListener(this);
        panel.add(tfComplemento);

        panel.add(CriarObjGrafico.criarJLabel("Cidade", 20, 140, 80, 14));
        lbCidadeObrig = CriarObjGrafico.criarJLabel("", 65, 143, 10, 14);
        lbCidadeObrig.setForeground(Color.RED);
        panel.add(lbCidadeObrig);
        tfCidade = CriarObjGrafico.criarJTextField(20, 158, 140, 20);
        tfCidade.addFocusListener(this);
        panel.add(tfCidade);

        panel.add(CriarObjGrafico.criarJLabel("UF", 170, 140, 80, 14));
        lbUFObrig = CriarObjGrafico.criarJLabel("", 190, 143, 10, 14);
        lbUFObrig.setForeground(Color.RED);
        panel.add(lbUFObrig);
        tfUF = CriarObjGrafico.criarJTextField(170, 158, 140, 20);
        tfUF.addFocusListener(this);
        panel.add(tfUF);

        panel.add(CriarObjGrafico.criarJLabel("Referência", 320, 140, 80, 14));
        tfReferencia = CriarObjGrafico.criarJTextField(320, 158, 180, 20);
        tfReferencia.addFocusListener(this);
        panel.add(tfReferencia);

        panel.add(CriarObjGrafico.criarJLabel("CEP", 510, 140, 80, 14));
        ftfCEP = CriarObjGrafico.criarJFormattedTextField("#####-###", 510, 158, 120, 20);
        ftfCEP.addFocusListener(this);
        panel.add(ftfCEP);

        panel.add(CriarObjGrafico.criarJLabel("Fone", 20, 180, 70, 14));
        lbFoneObrig = CriarObjGrafico.criarJLabel("", 50, 183, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel.add(lbFoneObrig);
        ftfFone = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 20, 198, 100, 20);
        ftfFone.addFocusListener(this);
        panel.add(ftfFone);

        panel.add(CriarObjGrafico.criarJLabel("Celular 1", 130, 180, 80, 14));
        ftfCelular1 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 130, 198, 100, 20);
        ftfCelular1.addFocusListener(this);
        panel.add(ftfCelular1);

        panel.add(CriarObjGrafico.criarJLabel("Celular 2", 240, 180, 80, 14));
        ftfCelular2 = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 240, 198, 110, 20);
        ftfCelular2.addFocusListener(this);
        panel.add(ftfCelular2);

        panel.add(CriarObjGrafico.criarJLabel("Data Nascimento", 360, 180, 100, 14));
        ftfDataNasc = CriarObjGrafico.criarJFormattedTextField("##/##/####", 360, 198, 100, 20);
        ftfDataNasc.addFocusListener(this);
        panel.add(ftfDataNasc);

        panel.add(CriarObjGrafico.criarJLabel("Empresa", 470, 180, 80, 14));
        tfEmpresa = CriarObjGrafico.criarJTextField(470, 198, 160, 20);
        tfEmpresa.addFocusListener(this);
        panel.add(tfEmpresa);

        panel.add(CriarObjGrafico.criarJLabel("Fone Empresa", 20, 220, 100, 14));
        ftfFoneEmpresa = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 20, 238, 100, 20);
        ftfFoneEmpresa.addFocusListener(this);
        panel.add(ftfFoneEmpresa);

        panel.add(CriarObjGrafico.criarJLabel("Profissão", 130, 220, 90, 14));
        tfProfissao = CriarObjGrafico.criarJTextField(130, 238, 120, 20);
        tfProfissao.addFocusListener(this);
        panel.add(tfProfissao);

        panel.add(CriarObjGrafico.criarJLabel("E-Mail", 260, 220, 70, 14));
        tfEmail = CriarObjGrafico.criarJTextField(260, 238, 180, 20);
        tfEmail.addFocusListener(this);
        panel.add(tfEmail);

        panel.add(CriarObjGrafico.criarJLabel("MSN", 450, 220, 70, 14));
        tfMSN = CriarObjGrafico.criarJTextField(450, 238, 180, 20);
        tfMSN.addFocusListener(this);
        panel.add(tfMSN);

        panel.add(CriarObjGrafico.criarJLabel("Descrição", 20, 260, 130, 14));
        taDescricao = CriarObjGrafico.criarJTextArea(panel, 20, 278, 610, 100);
        taDescricao.addFocusListener(this);

        btPesquisar = CriarObjGrafico.criarJButton("Pesquisar", 710, 70, 95, 26);
        btPesquisar.addActionListener(this);
        add(btPesquisar);

        btOk = CriarObjGrafico.criarJButton("OK", 710, 210, 86, 26);
        btOk.addActionListener(this);
        add(btOk);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 710, 250, 86, 26);
        btCancelar.addActionListener(this);
        add(btCancelar);

        rbNovo = CriarObjGrafico.criarJRadioButton("Novo", 715, 120, 80, 20);
        rbNovo.addItemListener(this);
        add(rbNovo);

        rbAlterar = CriarObjGrafico.criarJRadioButton("Alterar", 715, 140, 80, 20);
        rbAlterar.addItemListener(this);
        add(rbAlterar);

        rbExcluir = CriarObjGrafico.criarJRadioButton("Excluir", 715, 160, 80, 20);
        rbExcluir.addItemListener(this);
        add(rbExcluir);

        HashSet conj = new HashSet(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(850, 450);
        setResizable(false);
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
        tfNome.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfComplemento.setText("");
        tfCidade.setText("");
        tfUF.setText("");
        tfReferencia.setText("");
        tfEmpresa.setText("");
        tfProfissao.setText("");
        tfEmail.setText("");
        tfMSN.setText("");
        ftfRG.setText("");
        ftfCPF.setText("");
        ftfFone.setText("");
        ftfCelular1.setText("");
        ftfCelular2.setText("");
        ftfDataNasc.setText("");
        ftfFoneEmpresa.setText("");
        ftfCEP.setText("");
        cbSexo.setSelectedItem("Selecione");
        cbEstadoCivil.setSelectedItem("Selecione");
        cbTipoPessoa.setSelectedItem("Selecione");
        taDescricao.setText("");
        ftfCNPJ.setText("");
        ftfInscricaoEstadual.setText("");
        lbNomeObrig.setText("");
        lbTipoPessoa.setText("");
        lbCPFCNPJObrig.setText("");
        lbEnderecoObrig.setText("");
        lbBairroObrig.setText("");
        lbNumeroObrig.setText("");
        lbCidadeObrig.setText("");
        lbUFObrig.setText("");
        lbFoneObrig.setText("");
        ftfCNPJ.setVisible(false);
        ftfCPF.setVisible(true);
        ftfCPF.setEditable(false);
        ftfRG.setEditable(false);
        ftfInscricaoEstadual.setEditable(false);
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
        if ("Selecione".equals(cbTipoPessoa.getSelectedItem())) {
            lbTipoPessoa.setText("*");
        } else {
            lbTipoPessoa.setText("");
        }
        if ("Jurídica".equals(cbTipoPessoa.getSelectedItem())) {
            if ("  .   .   /    -  ".equals(ftfCNPJ.getText())) {
                lbCPFCNPJObrig.setText("*");
            } else {
                lbCPFCNPJObrig.setText("");
            }
        } else {
            if ("   .   .   -  ".equals(ftfCPF.getText())) {
                lbCPFCNPJObrig.setText("*");
            } else {
                lbCPFCNPJObrig.setText("");
            }
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
        if ("(  )    -    ".equals(ftfFone.getText())) {
            lbFoneObrig.setText("*");
        } else {
            lbFoneObrig.setText("");
        }
        if ("Jurídica".equals(cbTipoPessoa.getSelectedItem())) {
            ftfCPF.setText("");
            if ("  .   .   /    -  ".equals(ftfCNPJ.getText())) {
                throw new Exception("Campos * obrigatórios inválidos");
            }
        } else {
            ftfCNPJ.setText("");
            if ("   .   .   -  ".equals(ftfCPF.getText())) {
                throw new Exception("Campos * obrigatórios inválidos");
            }
        }
        if (!"".equals(tfNome.getText()) && !"Selecione".equals(cbTipoPessoa.getSelectedItem()) && !"".equals(tfEndereco.getText()) && !"".equals(tfBairro.getText()) && !"".equals(tfNumero.getText()) && !"".equals(tfCidade.getText()) && !"".equals(tfUF.getText()) && !"(  )    -    ".equals(ftfFone.getText())) {
            String cpfCNPJ;
            if (!"   .   .   -  ".equals(ftfCPF.getText())) {
                cpfCNPJ = ftfCPF.getText();
            } else {
                cpfCNPJ = ftfCNPJ.getText();
            }
            Cliente cliente = new Cliente();
            cliente.setCodigo(Integer.parseInt(tfCodigo.getText()));
            cliente.setNumero(Integer.parseInt(tfNumero.getText()));
            cliente.setNome(tfNome.getText());
            cliente.setRg(ftfRG.getText());
            cliente.setCpfCNPJ(cpfCNPJ);
            cliente.setIe(ftfInscricaoEstadual.getText());
            cliente.setSexo((String) cbSexo.getSelectedItem());
            cliente.setEstadoCivil((String) cbEstadoCivil.getSelectedItem());
            cliente.setTipoPessoa((String) cbTipoPessoa.getSelectedItem());
            cliente.setProfissao(tfProfissao.getText());
            cliente.setEmpresa(tfEmpresa.getText());
            cliente.setFoneEmpresa(ftfFoneEmpresa.getText());
            cliente.setCep(ftfCEP.getText());
            cliente.setEndereco(tfEndereco.getText());
            cliente.setBairro(tfBairro.getText());
            cliente.setComplemento(tfComplemento.getText());
            cliente.setCidade(tfCidade.getText());
            cliente.setUf(tfUF.getText());
            cliente.setReferencia(tfReferencia.getText());
            cliente.setFone(ftfFone.getText());
            cliente.setCelular1(ftfCelular1.getText());
            cliente.setCelular2(ftfCelular2.getText());
            cliente.seteMail(tfEmail.getText());
            cliente.setMsn(tfMSN.getText());
            cliente.setDescricao(taDescricao.getText());
            cliente.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            cliente.setUltAlteracao(new Date());
            cliente.setDataNascimento(VerificaCampos.verificaDate(ftfDataNasc, "Data Nascimento"));
            Cliente clienteLido = controle.getCliente(cpfCNPJ);
            if (clienteLido != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "O cliente  com o CPF /  CNPJ " + cliente.getCpfCNPJ() + " ja esta cadastrado deseja substituilo? ", "Cliente", JOptionPane.YES_NO_OPTION);
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
            throw new Exception("Campos * obrigatórios inválidos");
        }
    }

    private void excluir() throws Exception {
        if (controle.isClieVazio()) {
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String cpfCNPJ = JOptionPane.showInputDialog(null, "Informe o CPF / CNPJ do cliente a ser excluído:", "Cliente", JOptionPane.INFORMATION_MESSAGE);
            if (cpfCNPJ != null) {
                if (controle.removeCliente(cpfCNPJ)) {
                    JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.isClieVazio()) {
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String cpfCNPJ = JOptionPane.showInputDialog(null, "Informe o CPF / CNPJ do cliente a ser recuperado:", "Cliente", JOptionPane.INFORMATION_MESSAGE);
            if (cpfCNPJ != null) {
                Cliente cliente = controle.getCliente(cpfCNPJ);
                if (cliente != null) {
                    limparTela();
                    tfCodigo.setText(Integer.toString(cliente.getCodigo()));
                    tfNumero.setText(Integer.toString(cliente.getNumero()));
                    tfNome.setText(cliente.getNome());
                    cbTipoPessoa.setSelectedItem(cliente.getTipoPessoa());
                    if (cliente.getCpfCNPJ().length() == 14) {
                        ftfCNPJ.setVisible(false);
                        ftfCPF.setVisible(true);
                        ftfCPF.setEditable(true);
                        ftfCPF.setText(cliente.getCpfCNPJ());
                        ftfRG.setEditable(true);
                        ftfRG.setText(cliente.getRg());
                    } else {
                        ftfCPF.setVisible(false);
                        ftfCNPJ.setVisible(true);
                        ftfCNPJ.setEditable(true);
                        ftfCNPJ.setText(cliente.getCpfCNPJ());
                        ftfInscricaoEstadual.setEditable(true);
                        ftfInscricaoEstadual.setText(cliente.getIe());
                    }
                    cbSexo.setSelectedItem(cliente.getSexo());
                    cbEstadoCivil.setSelectedItem(cliente.getEstadoCivil());
                    tfProfissao.setText(cliente.getProfissao());
                    tfEmpresa.setText(cliente.getEmpresa());
                    ftfFoneEmpresa.setText(cliente.getFoneEmpresa());
                    ftfCEP.setText(cliente.getCep());
                    tfEndereco.setText(cliente.getEndereco());
                    tfBairro.setText(cliente.getBairro());
                    tfComplemento.setText(cliente.getComplemento());
                    tfCidade.setText(cliente.getCidade());
                    tfUF.setText(cliente.getUf());
                    tfReferencia.setText(cliente.getReferencia());
                    ftfFone.setText(cliente.getFone());
                    ftfCelular1.setText(cliente.getCelular1());
                    ftfCelular2.setText(cliente.getCelular2());
                    tfEmail.setText(cliente.geteMail());
                    tfMSN.setText(cliente.getMsn());
                    taDescricao.setText(cliente.getDescricao());
                    tfDataCadas.setText(formatDate.format(cliente.getDataCadastro()) + " as " + formatHora.format(cliente.getDataCadastro()));
                    tfUltAlteracao.setText(formatDate.format(cliente.getUltAlteracao()) + " as " + formatHora.format(cliente.getUltAlteracao()));
                    ftfDataNasc.setText(VerificaCampos.recuperaCampoDate(cliente.getDataNascimento()));
                    JOptionPane.showMessageDialog(null, "Cliente recuperado com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void abrirPesquisaClie() throws Exception {
        if (controle.isClieVazio()) {
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            PesquisaClientes pcl = new PesquisaClientes(controle);
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
        if (evento.getSource() == btPesquisar) {
            try {
                // abrirPesquisaClie();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == cbTipoPessoa) {
            if ("Física".equals((String) cbTipoPessoa.getSelectedItem())) {
                ftfCNPJ.setVisible(false);
                ftfCPF.setVisible(true);
                ftfCPF.setEditable(true);
                ftfCPF.setText("");
                ftfRG.setEditable(true);
                ftfRG.setText("");
                ftfInscricaoEstadual.setEditable(false);
                ftfInscricaoEstadual.setText("");
            } else {
                if ("Jurídica".equals((String) cbTipoPessoa.getSelectedItem())) {
                    ftfCPF.setVisible(false);
                    ftfCNPJ.setVisible(true);
                    ftfCNPJ.setText("");
                    ftfRG.setEditable(false);
                    ftfRG.setText("");
                    ftfInscricaoEstadual.setEditable(true);
                    ftfInscricaoEstadual.setText("");
                } else {
                    ftfCNPJ.setVisible(false);
                    ftfCNPJ.setText("");
                    ftfCPF.setVisible(true);
                    ftfCPF.setEditable(false);
                    ftfCPF.setText("");
                    ftfRG.setEditable(false);
                    ftfRG.setText("");
                    ftfInscricaoEstadual.setEditable(false);
                    ftfInscricaoEstadual.setText("");
                }
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
        if (evento.getSource() == tfDataCadas) {
            tfDataCadas.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUltAlteracao) {
            tfUltAlteracao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNome) {
            tfNome.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfCidade) {
            tfCidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUF) {
            tfUF.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfReferencia) {
            tfReferencia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmpresa) {
            tfEmpresa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfProfissao) {
            tfProfissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmail) {
            tfEmail.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMSN) {
            tfMSN.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfRG) {
            ftfRG.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCPF) {
            ftfCPF.setBackground(Color.YELLOW);
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
        if (evento.getSource() == ftfDataNasc) {
            ftfDataNasc.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFoneEmpresa) {
            ftfFoneEmpresa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCEP) {
            ftfCEP.setBackground(Color.YELLOW);
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
        if (evento.getSource() == taDescricao) {
            taDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfInscricaoEstadual) {
            ftfInscricaoEstadual.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCNPJ) {
            ftfCNPJ.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfNome.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfComplemento.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfUF.setBackground(Color.WHITE);
        tfReferencia.setBackground(Color.WHITE);
        tfEmpresa.setBackground(Color.WHITE);
        tfProfissao.setBackground(Color.WHITE);
        tfEmail.setBackground(Color.WHITE);
        tfMSN.setBackground(Color.WHITE);
        ftfRG.setBackground(Color.WHITE);
        ftfCPF.setBackground(Color.WHITE);
        ftfFone.setBackground(Color.WHITE);
        ftfCelular1.setBackground(Color.WHITE);
        ftfCelular2.setBackground(Color.WHITE);
        ftfDataNasc.setBackground(Color.WHITE);
        ftfFoneEmpresa.setBackground(Color.WHITE);
        ftfCEP.setBackground(Color.WHITE);
        cbSexo.setBackground(Color.WHITE);
        cbEstadoCivil.setBackground(Color.WHITE);
        cbTipoPessoa.setBackground(Color.WHITE);
        taDescricao.setBackground(Color.WHITE);
        ftfInscricaoEstadual.setBackground(Color.WHITE);
        ftfCNPJ.setBackground(Color.WHITE);
    }
}

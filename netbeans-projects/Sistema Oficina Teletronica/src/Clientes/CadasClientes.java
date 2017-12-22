package Clientes;

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

import ConsultaHistoricoOS.AbrirHistorico;
import Formatacao.CamposInt;
import Formatacao.ObjGraficos;
import Modelo.Cliente;

public class CadasClientes extends ObjGraficos implements ActionListener, ItemListener, FocusListener {

    private static final long serialVersionUID = 8300549908408848628L;
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
    private Connection con;
    private ClienteControl controle;

    public CadasClientes(Connection con) {
        this.con = con;
        controle = new ClienteControl(con);
        try {
            initComponents();
        } catch (ParseException ex) {
        }
    }

    private void initComponents() throws ParseException {
        List<String> itensCombo = new ArrayList<String>();
        setTitle("Cadastro de Clientes");
        setLayout(null);
        JPanel panel = getJPanelLineBorder(10, 10, 650, 400);
        add(panel);

        panel.add(getJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = getJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodClie()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        tfCodigo.addFocusListener(this);
        panel.add(tfCodigo);

        btVer = getJButton("...", 106, 34, 31, 24);
        btVer.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btVer.setToolTipText("Consulta Clientes");
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

        panel.add(getJLabel("Nome / Razão Social", 400, 20, 120, 14));
        lbNomeObrig = getJLabel("", 520, 23, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel.add(lbNomeObrig);
        tfNome = getJTextField(400, 38, 230, 20);
        tfNome.addFocusListener(this);
        panel.add(tfNome);

        panel.add(getJLabel("Tipo Pessoa", 20, 60, 90, 14));
        lbTipoPessoa = getJLabel("", 95, 63, 10, 14);
        lbTipoPessoa.setForeground(Color.RED);
        panel.add(lbTipoPessoa);
        itensCombo.add("Física");
        itensCombo.add("Jurídica");
        cbTipoPessoa = getJComboBox(itensCombo, 20, 78, 85, 20);
        cbTipoPessoa.addFocusListener(this);
        cbTipoPessoa.addActionListener(this);
        panel.add(cbTipoPessoa);

        panel.add(getJLabel("CPF / CNPJ", 115, 60, 80, 14));
        lbCPFCNPJObrig = getJLabel("", 180, 63, 10, 14);
        lbCPFCNPJObrig.setForeground(Color.RED);
        panel.add(lbCPFCNPJObrig);
        ftfCPF = getJFormattedTextField("###.###.###-##", 115, 78, 120, 20);
        ftfCPF.addFocusListener(this);
        ftfCPF.setEditable(false);
        ftfCPF.setBackground(Color.WHITE);
        panel.add(ftfCPF);

        ftfCNPJ = getJFormattedTextField("##.###.###/####-##", 115, 78, 120, 20);
        ftfCNPJ.addFocusListener(this);
        ftfCNPJ.setVisible(false);
        panel.add(ftfCNPJ);

        panel.add(getJLabel("RG", 245, 60, 80, 14));
        ftfRG = getJFormattedTextField("###.###.###", 245, 78, 90, 20);
        ftfRG.addFocusListener(this);
        ftfRG.setEditable(false);
        ftfRG.setBackground(Color.WHITE);
        panel.add(ftfRG);

        panel.add(getJLabel("I.E.", 345, 60, 110, 14));
        ftfInscricaoEstadual = getJFormattedTextField("###.###.###", 345, 78, 85, 20);
        ftfInscricaoEstadual.addFocusListener(this);
        ftfInscricaoEstadual.setEditable(false);
        ftfInscricaoEstadual.setBackground(Color.WHITE);
        panel.add(ftfInscricaoEstadual);

        panel.add(getJLabel("Sexo", 440, 60, 80, 14));
        itensCombo.clear();
        itensCombo.add("Masculino");
        itensCombo.add("Feminino");
        cbSexo = getJComboBox(itensCombo, 440, 78, 90, 20);
        cbSexo.addFocusListener(this);
        panel.add(cbSexo);

        panel.add(getJLabel("Estado Civil", 540, 60, 120, 14));
        itensCombo.clear();
        itensCombo.add("Casado");
        itensCombo.add("Solteiro");
        cbEstadoCivil = getJComboBox(itensCombo, 540, 78, 90, 20);
        cbEstadoCivil.addFocusListener(this);
        panel.add(cbEstadoCivil);

        panel.add(getJLabel("Endereço", 20, 100, 80, 14));
        lbEnderecoObrig = getJLabel("", 80, 103, 10, 14);
        lbEnderecoObrig.setForeground(Color.RED);
        panel.add(lbEnderecoObrig);
        tfEndereco = getJTextField(20, 118, 170, 20);
        tfEndereco.addFocusListener(this);
        panel.add(tfEndereco);

        panel.add(getJLabel("Bairro", 200, 100, 80, 14));
        lbBairroObrig = getJLabel("", 240, 103, 10, 14);
        lbBairroObrig.setForeground(Color.RED);
        panel.add(lbBairroObrig);
        tfBairro = getJTextField(200, 118, 160, 20);
        tfBairro.addFocusListener(this);
        panel.add(tfBairro);

        panel.add(getJLabel("Numero", 370, 100, 80, 14));
        lbNumeroObrig = getJLabel("", 420, 103, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel.add(lbNumeroObrig);
        tfNumero = getJTextField(370, 118, 100, 20);
        tfNumero.setDocument(new CamposInt());
        tfNumero.addFocusListener(this);
        panel.add(tfNumero);

        panel.add(getJLabel("Complemento", 480, 100, 90, 14));
        tfComplemento = getJTextField(480, 118, 150, 20);
        tfComplemento.addFocusListener(this);
        panel.add(tfComplemento);

        panel.add(getJLabel("Cidade", 20, 140, 80, 14));
        lbCidadeObrig = getJLabel("", 65, 143, 10, 14);
        lbCidadeObrig.setForeground(Color.RED);
        panel.add(lbCidadeObrig);
        tfCidade = getJTextField(20, 158, 140, 20);
        tfCidade.addFocusListener(this);
        panel.add(tfCidade);

        panel.add(getJLabel("UF", 170, 140, 80, 14));
        lbUFObrig = getJLabel("", 190, 143, 10, 14);
        lbUFObrig.setForeground(Color.RED);
        panel.add(lbUFObrig);
        tfUF = getJTextField(170, 158, 140, 20);
        tfUF.addFocusListener(this);
        panel.add(tfUF);

        panel.add(getJLabel("Referência", 320, 140, 80, 14));
        tfReferencia = getJTextField(320, 158, 180, 20);
        tfReferencia.addFocusListener(this);
        panel.add(tfReferencia);

        panel.add(getJLabel("CEP", 510, 140, 80, 14));
        ftfCEP = getJFormattedTextField("#####-###", 510, 158, 120, 20);
        ftfCEP.addFocusListener(this);
        panel.add(ftfCEP);

        panel.add(getJLabel("Fone", 20, 180, 70, 14));
        lbFoneObrig = getJLabel("", 50, 183, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel.add(lbFoneObrig);
        ftfFone = getJFormattedTextField("(##)####-####", 20, 198, 100, 20);
        ftfFone.addFocusListener(this);
        panel.add(ftfFone);

        panel.add(getJLabel("Celular 1", 130, 180, 80, 14));
        ftfCelular1 = getJFormattedTextField("(##)####-####", 130, 198, 100, 20);
        ftfCelular1.addFocusListener(this);
        panel.add(ftfCelular1);

        panel.add(getJLabel("Celular 2", 240, 180, 80, 14));
        ftfCelular2 = getJFormattedTextField("(##)####-####", 240, 198, 110, 20);
        ftfCelular2.addFocusListener(this);
        panel.add(ftfCelular2);

        panel.add(getJLabel("Data Nascimento", 360, 180, 100, 14));
        ftfDataNasc = getJFormattedTextField("##/##/####", 360, 198, 100, 20);
        ftfDataNasc.addFocusListener(this);
        panel.add(ftfDataNasc);

        panel.add(getJLabel("Empresa", 470, 180, 80, 14));
        tfEmpresa = getJTextField(470, 198, 160, 20);
        tfEmpresa.addFocusListener(this);
        panel.add(tfEmpresa);

        panel.add(getJLabel("Fone Empresa", 20, 220, 100, 14));
        ftfFoneEmpresa = getJFormattedTextField("(##)####-####", 20, 238, 100, 20);
        ftfFoneEmpresa.addFocusListener(this);
        panel.add(ftfFoneEmpresa);

        panel.add(getJLabel("Profissão", 130, 220, 90, 14));
        tfProfissao = getJTextField(130, 238, 120, 20);
        tfProfissao.addFocusListener(this);
        panel.add(tfProfissao);

        panel.add(getJLabel("E-Mail", 260, 220, 70, 14));
        tfEmail = getJTextField(260, 238, 180, 20);
        tfEmail.addFocusListener(this);
        panel.add(tfEmail);

        panel.add(getJLabel("MSN", 450, 220, 70, 14));
        tfMSN = getJTextField(450, 238, 180, 20);
        tfMSN.addFocusListener(this);
        panel.add(tfMSN);

        panel.add(getJLabel("Descrição", 20, 260, 130, 14));
        taDescricao = getJTextArea(panel, 20, 278, 610, 100);
        taDescricao.addFocusListener(this);

        btPesquisar = getJButton("Pesquisar", 710, 70, 95, 26);
        btPesquisar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.setToolTipText("Consulta Histórico das Ordens de Serviços");
        btPesquisar.addActionListener(this);
        add(btPesquisar);

        btOk = getJButton("OK", 710, 210, 86, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("OK");
        btOk.addActionListener(this);
        add(btOk);

        btCancelar = getJButton("Cancelar", 710, 250, 86, 26);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        add(btCancelar);

        rbNovo = getJRadioButton("Novo", 715, 120, 80, 20);
        rbNovo.addItemListener(this);
        add(rbNovo);

        rbAlterar = getJRadioButton("Alterar", 715, 140, 80, 20);
        rbAlterar.addItemListener(this);
        add(rbAlterar);

        rbExcluir = getJRadioButton("Excluir", 715, 160, 80, 20);
        rbExcluir.addItemListener(this);
        add(rbExcluir);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

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
        if ("".equals(tfNome.getText().trim())) {
            lbNomeObrig.setText("*");
        } else {
            lbNomeObrig.setText("");
        }
        if ("Selecione".equals(cbTipoPessoa.getSelectedItem())) {
            lbTipoPessoa.setText("*");
        } else {
            lbTipoPessoa.setText("");
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
        if ("Jurídica".equals(cbTipoPessoa.getSelectedItem())) {
            ftfCPF.setText("");
            if ("  .   .   /    -  ".equals(ftfCNPJ.getText())) {
                lbCPFCNPJObrig.setText("*");
                throw new Exception("Campos * obrigatórios inválidos");
            } else {
                lbCPFCNPJObrig.setText("");
            }
        } else {
            ftfCNPJ.setText("");
            if ("   .   .   -  ".equals(ftfCPF.getText())) {
                lbCPFCNPJObrig.setText("*");
                throw new Exception("Campos * obrigatórios inválidos");
            } else {
                lbCPFCNPJObrig.setText("");
            }
        }
        if (!"".equals(tfNome.getText().trim()) && !"Selecione".equals(cbTipoPessoa.getSelectedItem()) && !"".equals(tfEndereco.getText().trim()) && !"".equals(tfBairro.getText().trim()) && !"".equals(tfNumero.getText()) && !"".equals(tfCidade.getText().trim()) && !"".equals(tfUF.getText().trim()) && !"(  )    -    ".equals(ftfFone.getText())) {
            String cpfCNPJ;
            if (!"   .   .   -  ".equals(ftfCPF.getText())) {
                cpfCNPJ = ftfCPF.getText();
            } else {
                cpfCNPJ = ftfCNPJ.getText();
            }
            Cliente cliente = new Cliente();
            cliente.setCodigo(Integer.parseInt(tfCodigo.getText()));
            cliente.setNumero(verificaInt(tfNumero, "número"));
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
            cliente.setDataNascimento(verificaDate(ftfDataNasc, "data nascimento"));
            Cliente clienteLido = controle.selectCliente(cpfCNPJ);
            if (clienteLido != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "O cliente com o CPF /  CNPJ " + cliente.getCpfCNPJ() + " ja esta cadastrado deseja substituilo? ", "Cliente", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    cliente.setCodigo(clienteLido.getCodigo());
                    if (controle.updateCliente(cliente)) {
                        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                } else {
                    return;
                }
            } else {
                if (controle.insertCliente(cliente)) {
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
            throw new Exception("Não há clientes cadastrados");
        }
        String cpfCNPJ = JOptionPane.showInputDialog(null, "Informe o CPF / CNPJ do cliente a ser excluído:", "Cliente", JOptionPane.INFORMATION_MESSAGE);
        if (cpfCNPJ != null) {
            if (controle.deleteCliente(cpfCNPJ)) {
                JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.isClieVazio()) {
            throw new Exception("Não há clientes cadastrados");
        }
        String cpfCNPJ = JOptionPane.showInputDialog(null, "Informe o CPF / CNPJ do cliente a ser recuperado:", "Cliente", JOptionPane.INFORMATION_MESSAGE);
        if (cpfCNPJ != null) {
            Cliente cliente = controle.selectCliente(cpfCNPJ);
            if (cliente != null) {
                mostrarCliente(cliente);
                JOptionPane.showMessageDialog(null, "Cliente recuperado com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void mostrarCliente(Cliente cliente) {
        limparTela();
        tfCodigo.setText(Integer.toString(cliente.getCodigo()));
        tfNumero.setText(recuperaCampoStr(cliente.getNumero()));
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
        ftfDataNasc.setText(recuperaCampoDate(cliente.getDataNascimento()));
    }

    private void abrirPesquisaClie() throws Exception {
        if (controle.isClieVazio()) {
            throw new Exception("Não há clientes cadastrados");
        }
        PesquisaClientes pesquisaClie = new PesquisaClientes(controle);
        pesquisaClie.setListener(new ListenerCliente() {

            @Override
            public void exibeCliente(Cliente cliente) {
                mostrarCliente(cliente);
            }
        });
        pesquisaClie.setModal(true);
        pesquisaClie.setVisible(true);
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
                new AbrirHistorico(con);
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

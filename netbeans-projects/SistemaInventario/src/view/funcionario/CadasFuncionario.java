package view.funcionario;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import model.Endereco;
import model.Funcionario;

import dbOracle.EnderecoDAO;
import dbOracle.FuncionarioDAO;

import view.CamposInt;
import view.PanelComponentes;
import view.ValidaObjeto;
import view.endereco.ConsultaEndereco;
import view.endereco.ListenerEndereco;

public class CadasFuncionario extends JDialog implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome, tfIdade,
            tfCodEndereco, tfEndereco, tfBairro, tfNumero, tfCep, tfCidade,
            tfUsuario, tfSenha, tfCargo, tfComplemento, tfSalario;
    private JFormattedTextField ftfCpf, ftfRg, ftfDataNasc, ftfContato,
            ftfNumCartTrab, ftfDataAdmissao, ftfDataDemissao;
    private JComboBox cbPermissao, cbSexo, cbEstadoCivil;
    private JButton btConsulta, btConsultaEnd, btOk, btCancelar, btExcluir,
            btSair;
    private JLabel lbNomeObrig, lbCpfObrig, lbRgObrig, lbSexoObrig,
            lbEstCivilObrig, lbDataNascObrig, lbCodEnderecoObrig,
            lbNumeroObrig, lbComplementoObrig, lbContatoObrig,
            lbNumCartTrabObrig, lbCargoObrig, lbSalarioObrig, lbUsuarioObrig,
            lbSenhaObrig, lbPermissaoObrig, lbDataAdmissaoObrig, lbAtivoObrig;
    private JCheckBox chAtivo;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;
    private SimpleDateFormat formatDateHora;
    private EnderecoDAO enderecoDAO;
    private FuncionarioDAO funcionarioDAO;
    private ValidaObjeto validaObjeto;

    public CadasFuncionario(Connection con) {
        enderecoDAO = new EnderecoDAO(con);
        funcionarioDAO = new FuncionarioDAO(con);
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        validaObjeto = new ValidaObjeto();
        try {
            initComponents();
        } catch (ParseException ex) {
        }
    }

    private void initComponents() throws ParseException {
        setTitle("Cadastro de Funcionários");
        setLayout(null);

        PanelComponentes panel = new PanelComponentes(5, 5, 570, 310);
        this.add(panel);

        panel.addJLabel("Codigo", 20, 20, 50, 14);

        tfCodigo = panel.addJTextFieldNaoEdit(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(funcionarioDAO.getProxCodFunc()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.addFocusListener(this);

        btConsulta = panel.addJButton("...", "Consulta Funcionários", 105, 34, 31, 26);
        btConsulta.addActionListener(this);

        panel.addJLabel("Cadastro em", 143, 20, 90, 14);

        tfDataCadas = panel.addJTextFieldNaoEdit(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.addFocusListener(this);

        panel.addJLabel("Última Alteração", 270, 20, 100, 14);

        tfUltAlteracao = panel.addJTextFieldNaoEdit(270, 38, 120, 20);
        tfUltAlteracao.addFocusListener(this);

        panel.addJLabel("Nome", 400, 20, 50, 14);

        lbNomeObrig = panel.addJLabelRED(430, 23, 10, 14);

        tfNome = panel.addJTextField(400, 38, 150, 20);
        tfNome.addFocusListener(this);

        panel.addJLabel("CPF", 20, 63, 50, 14);

        lbCpfObrig = panel.addJLabelRED(40, 66, 10, 14);

        ftfCpf = panel.addJFormattedTextField("###.###.###-##", 20, 81, 90, 20);
        ftfCpf.addFocusListener(this);

        panel.addJLabel("RG", 120, 63, 50, 14);

        lbRgObrig = panel.addJLabelRED(137, 66, 10, 14);

        ftfRg = panel.addJFormattedTextField("#.###.###", 120, 81, 75, 20);
        ftfRg.addFocusListener(this);

        panel.addJLabel("Sexo", 205, 63, 50, 14);

        lbSexoObrig = panel.addJLabelRED(232, 66, 10, 14);

        final String[] itensSexo = {"Masculino", "Feminino"};
        cbSexo = panel.addJComboBox(itensSexo, 205, 81, 80, 20);
        cbSexo.addFocusListener(this);

        panel.addJLabel("Estado Civil", 295, 63, 60, 14);

        lbEstCivilObrig = panel.addJLabelRED(352, 66, 10, 14);

        final String[] itensEstCivil = {"Solteiro", "Casado", "Separado", "Divorciado", "Viúvo"};
        cbEstadoCivil = panel.addJComboBox(itensEstCivil, 295, 81, 80, 20);
        cbEstadoCivil.addFocusListener(this);

        panel.addJLabel("Data Nascimento", 385, 63, 85, 14);

        lbDataNascObrig = panel.addJLabelRED(468, 66, 10, 14);

        ftfDataNasc = panel.addJFormattedTextField("##/##/####", 385, 81, 90, 20);
        ftfDataNasc.addFocusListener(this);

        panel.addJLabel("Idade", 485, 63, 85, 14);

        tfIdade = panel.addJTextFieldNaoEdit(485, 81, 65, 20);
        tfIdade.addFocusListener(this);

        panel.addJLabel("Cod. Endereço", 20, 105, 80, 14);

        lbCodEnderecoObrig = panel.addJLabelRED(94, 108, 10, 14);

        tfCodEndereco = panel.addJTextFieldNaoEdit(20, 123, 80, 20);
        tfCodEndereco.addFocusListener(this);

        btConsultaEnd = panel.addJButton("...", "Consulta Endereço", 105, 119, 31, 26);
        btConsultaEnd.addActionListener(this);

        panel.addJLabel("Endereço", 143, 105, 60, 14);

        tfEndereco = panel.addJTextFieldNaoEdit(143, 123, 125, 20);
        tfEndereco.addFocusListener(this);

        panel.addJLabel("Bairro", 275, 105, 50, 14);

        tfBairro = panel.addJTextFieldNaoEdit(275, 123, 110, 20);
        tfBairro.addFocusListener(this);

        panel.addJLabel("Número", 392, 105, 50, 14);

        lbNumeroObrig = panel.addJLabelRED(431, 108, 10, 14);

        tfNumero = panel.addJTextField(392, 123, 60, 20);
        tfNumero.setDocument(new CamposInt());
        tfNumero.addFocusListener(this);

        panel.addJLabel("Complemento", 460, 105, 65, 14);

        lbComplementoObrig = panel.addJLabelRED(527, 108, 10, 14);

        tfComplemento = panel.addJTextField(460, 123, 90, 20);
        tfComplemento.addFocusListener(this);

        panel.addJLabel("CEP", 20, 147, 50, 14);

        tfCep = panel.addJTextFieldNaoEdit(20, 165, 80, 20);
        tfCep.addFocusListener(this);

        panel.addJLabel("Cidade", 108, 147, 50, 14);

        tfCidade = panel.addJTextFieldNaoEdit(108, 165, 100, 20);
        tfCidade.addFocusListener(this);

        panel.addJLabel("Contato", 216, 147, 60, 14);

        lbContatoObrig = panel.addJLabelRED(257, 150, 10, 14);

        ftfContato = panel.addJFormattedTextField("(##)####-####", 216, 165, 90, 20);
        ftfContato.addFocusListener(this);

        panel.addJLabel("N° Cart. Trab. / Série", 316, 147, 110, 14);

        lbNumCartTrabObrig = panel.addJLabelRED(420, 150, 10, 14);

        ftfNumCartTrab = panel.addJFormattedTextField("#######/###-#", 316, 165, 105, 20);
        ftfNumCartTrab.addFocusListener(this);

        panel.addJLabel("Cargo", 430, 147, 50, 14);

        lbCargoObrig = panel.addJLabelRED(462, 150, 10, 14);

        tfCargo = panel.addJTextField(430, 165, 120, 20);
        tfCargo.addFocusListener(this);

        panel.addJLabel("Salário", 20, 190, 50, 14);

        lbSalarioObrig = panel.addJLabelRED(54, 193, 10, 14);

        tfSalario = panel.addJTextField(20, 208, 60, 20);
        tfSalario.addFocusListener(this);

        panel.addJLabel("Usuário", 87, 190, 50, 14);

        lbUsuarioObrig = panel.addJLabelRED(126, 193, 10, 14);

        tfUsuario = panel.addJTextField(87, 208, 80, 20);
        tfUsuario.addFocusListener(this);

        panel.addJLabel("Senha", 173, 190, 50, 14);

        lbSenhaObrig = panel.addJLabelRED(205, 193, 10, 14);

        tfSenha = panel.addJTextField(173, 208, 80, 20);
        tfSenha.addFocusListener(this);

        panel.addJLabel("Permissão", 262, 190, 60, 14);

        lbPermissaoObrig = panel.addJLabelRED(312, 193, 10, 14);

        final String[] itensPer = {"Administrador", "Suporte", "Outros"};
        cbPermissao = panel.addJComboBox(itensPer, 262, 208, 95, 20);
        cbPermissao.addFocusListener(this);

        panel.addJLabel("Data Admissão", 365, 190, 85, 14);

        lbDataAdmissaoObrig = panel.addJLabelRED(438, 193, 10, 14);

        ftfDataAdmissao = panel.addJFormattedTextField("##/##/####", 365, 208, 75, 20);
        ftfDataAdmissao.addFocusListener(this);

        panel.addJLabel("Data Demissão", 448, 190, 85, 14);

        ftfDataDemissao = panel.addJFormattedTextField("##/##/####", 448, 208, 75, 20);
        ftfDataDemissao.addFocusListener(this);

        panel.addJLabel("Ativo", 530, 193, 50, 14);

        lbAtivoObrig = panel.addJLabelRED(556, 196, 10, 14);

        chAtivo = panel.addJCheckBox(530, 208, 20, 20);

        panel.addJSeparator(JSeparator.HORIZONTAL, 0, 243, 568, 3);

        btOk = panel.addJButtonOK(60, 263, 70, 26);
        btOk.addActionListener(this);

        btCancelar = panel.addJButtonCancelar(180, 263, 70, 26);
        btCancelar.addActionListener(this);

        btExcluir = panel.addJButtonExcuir(300, 263, 70, 26);
        btExcluir.addActionListener(this);

        btSair = panel.addJButtonSair(420, 263, 71, 26);
        btSair.addActionListener(this);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(586, 347);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(funcionarioDAO.getProxCodFunc()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfNome.setText("");
        tfIdade.setText("");
        tfCodEndereco.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfCep.setText("");
        tfCidade.setText("");
        tfUsuario.setText("");
        tfSenha.setText("");
        tfCargo.setText("");
        tfComplemento.setText("");
        tfSalario.setText("");
        ftfCpf.setText("");
        ftfRg.setText("");
        ftfDataNasc.setText("");
        ftfContato.setText("");
        ftfNumCartTrab.setText("");
        ftfDataAdmissao.setText("");
        ftfDataDemissao.setText("");
        cbPermissao.setSelectedItem("Selecione");
        cbSexo.setSelectedItem("Selecione");
        cbEstadoCivil.setSelectedItem("Selecione");
        chAtivo.setSelected(false);
        lbNomeObrig.setText("");
        lbCpfObrig.setText("");
        lbRgObrig.setText("");
        lbSexoObrig.setText("");
        lbEstCivilObrig.setText("");
        lbDataNascObrig.setText("");
        lbCodEnderecoObrig.setText("");
        lbNumeroObrig.setText("");
        lbComplementoObrig.setText("");
        lbContatoObrig.setText("");
        lbNumCartTrabObrig.setText("");
        lbCargoObrig.setText("");
        lbSalarioObrig.setText("");
        lbUsuarioObrig.setText("");
        lbSenhaObrig.setText("");
        lbPermissaoObrig.setText("");
        lbDataAdmissaoObrig.setText("");
        lbAtivoObrig.setText("");
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
        if (" .   .   ".equals(ftfRg.getText())) {
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
            lbEstCivilObrig.setText("*");
            flag = true;
        } else {
            lbEstCivilObrig.setText("");
        }
        if ("  /  /    ".equals(ftfDataNasc.getText())) {
            lbDataNascObrig.setText("*");
            flag = true;
        } else {
            lbDataNascObrig.setText("");
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
        if ("(  )    -    ".equals(ftfContato.getText())) {
            lbContatoObrig.setText("*");
            flag = true;
        } else {
            lbContatoObrig.setText("");
        }
        if ("       /   - ".equals(ftfNumCartTrab.getText())) {
            lbNumCartTrabObrig.setText("*");
            flag = true;
        } else {
            lbNumCartTrabObrig.setText("");
        }
        if ("".equals(tfCargo.getText().trim())) {
            lbCargoObrig.setText("*");
            flag = true;
        } else {
            lbCargoObrig.setText("");
        }
        if ("".equals(tfSalario.getText().trim())) {
            lbSalarioObrig.setText("*");
            flag = true;
        } else {
            lbSalarioObrig.setText("");
        }
        if ("".equals(tfUsuario.getText().trim())) {
            lbUsuarioObrig.setText("*");
            flag = true;
        } else {
            lbUsuarioObrig.setText("");
        }
        if ("".equals(tfSenha.getText().trim())) {
            lbSenhaObrig.setText("*");
            flag = true;
        } else {
            lbSenhaObrig.setText("");
        }
        if ("Selecione".equals(cbPermissao.getSelectedItem())) {
            lbPermissaoObrig.setText("*");
            flag = true;
        } else {
            lbPermissaoObrig.setText("");
        }
        if ("  /  /    ".equals(ftfDataAdmissao.getText())) {
            lbDataAdmissaoObrig.setText("*");
            flag = true;
        } else {
            lbDataAdmissaoObrig.setText("");
        }
        if (!flag) {
            if (!"  /  /    ".equals(ftfDataDemissao.getText()) && chAtivo.isSelected()) {
                lbAtivoObrig.setText("*");
                throw new Exception(
                        "Funcionário ainda ativo, por favor altere o status *");
            } else {
                if ("  /  /    ".equals(ftfDataDemissao.getText()) && !chAtivo.isSelected()) {
                    lbAtivoObrig.setText("*");
                    throw new Exception("Por favor ative status * do funcionário");
                } else {
                    lbAtivoObrig.setText("");
                }
            }
            validaObjeto.validaDouble(tfSalario, "Campo salário inválido", "Salário deve ser superior a 100", 100);
            if (!validaObjeto.validaData(ftfDataNasc)) {
                throw new Exception("Campo data nascimento inválido");
            }
            if (!validaObjeto.validaData(ftfDataAdmissao)) {
                throw new Exception("Campo data admissão inválido");
            }
            if (!validaObjeto.validaData(ftfDataDemissao)) {
                throw new Exception("Campo data demissão inválido");
            }
            if (Integer.parseInt(tfIdade.getText()) < 16 || Integer.parseInt(tfIdade.getText()) > 100) {
                throw new Exception("Campo data de nascimento inválido\nA idade do funcionário deve ser entre 16 e 100 anos");
            }
            if (funcionarioDAO.isUsuarioCadastrado(tfUsuario.getText(), Integer.parseInt(tfCodigo.getText()))) {
                throw new Exception("Este usuário já se encontra cadastrado\nEntre com outro nome de usuário");
            }
            Funcionario func = new Funcionario();
            func.setCodigo(Integer.parseInt(tfCodigo.getText()));
            func.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            func.setUltAlteracao(new Date());
            func.setNome(tfNome.getText());
            func.setCpfCnpj(ftfCpf.getText());
            func.setRgIe(ftfRg.getText());
            func.setSexo((String) cbSexo.getSelectedItem());
            func.setEstadoCivil((String) cbEstadoCivil.getSelectedItem());
            func.setDataNascimento(formatDate.parse(ftfDataNasc.getText()));
            Endereco endereco = new Endereco();
            endereco.setCodigo(Integer.parseInt(tfCodEndereco.getText()));
            func.setEndereco(endereco);
            func.setNumero(Integer.parseInt(tfNumero.getText()));
            func.setComplemento(tfComplemento.getText());
            func.setContato(ftfContato.getText());
            func.setNumCartTrabalho(ftfNumCartTrab.getText());
            func.setCargo(tfCargo.getText());
            func.setSalario(Double.parseDouble(tfSalario.getText()));
            func.setUsuario(tfUsuario.getText());
            func.setSenha(tfSenha.getText());
            func.setPermissao(cbPermissao.getSelectedIndex());
            func.setDataAdmissao(formatDate.parse(ftfDataAdmissao.getText()));
            if (!"  /  /    ".equals(ftfDataDemissao.getText())) {
                func.setDataDemissao(formatDate.parse(ftfDataDemissao.getText()));
            }
            int codFuncCadas = funcionarioDAO.getFuncCadastrado(ftfCpf.getText(), Integer.parseInt(tfCodigo.getText()));// verifica se funcionário já cadastrado
            if (codFuncCadas != -1) {
                if (JOptionPane.showConfirmDialog(null, "Confirmar alteração nos dados", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (funcionarioDAO.updateFunc(func)) {
                        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            } else {
                codFuncCadas = funcionarioDAO.getFuncCadastrado(ftfCpf.getText());// verifica se pessoa já cadastrado
                if (codFuncCadas != -1) {
                    JOptionPane.showMessageDialog(null, "Este CPF ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (funcionarioDAO.insertFunc(func)) {
                        JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            }
        } else {
            throw new Exception("Campos * obrigatórios não preenchidos");
        }
    }

    private void consultaFunc() throws Exception {
        if (funcionarioDAO.isFuncVazio()) {
            throw new Exception("Não há funcionários cadastrados\nCadastre um funcionário primeiro");
        }
        ConsultaFuncionario consulta = new ConsultaFuncionario(funcionarioDAO);
        consulta.setListener(new ListenerFuncionario() {

            @Override
            public void exibeFuncionario(Funcionario funcionario) {
                limparTela();
                tfCodigo.setText(Integer.toString(funcionario.getCodigo()));
                tfDataCadas.setText(formatDate.format(funcionario.getDataCadastro()) + " as " + formatHora.format(funcionario.getDataCadastro()));
                tfUltAlteracao.setText(formatDate.format(funcionario.getUltAlteracao()) + " as " + formatHora.format(funcionario.getUltAlteracao()));
                tfNome.setText(funcionario.getNome());
                ftfCpf.setText(funcionario.getCpfCnpj());
                ftfRg.setText(funcionario.getRgIe());
                cbSexo.setSelectedItem(funcionario.getSexo());
                cbEstadoCivil.setSelectedItem(funcionario.getEstadoCivil());
                ftfDataNasc.setText(formatDate.format(funcionario.getDataNascimento()));
                tfIdade.setText(calculoIdade());
                tfCodEndereco.setText(Integer.toString(funcionario.getEndereco().getCodigo()));
                tfEndereco.setText(funcionario.getEndereco().getEndereco());
                tfBairro.setText(funcionario.getEndereco().getBairro().getBairro());
                tfNumero.setText(Integer.toString(funcionario.getNumero()));
                tfComplemento.setText(funcionario.getComplemento());
                tfCep.setText(funcionario.getEndereco().getBairro().getCep().getCep());
                tfCidade.setText(funcionario.getEndereco().getBairro().getCep().getCidade().getCidade());
                ftfContato.setText(funcionario.getContato());
                ftfNumCartTrab.setText(funcionario.getNumCartTrabalho());
                tfCargo.setText(funcionario.getCargo());
                tfSalario.setText(Double.toString(funcionario.getSalario()));
                tfUsuario.setText(funcionario.getUsuario());
                tfSenha.setText(funcionario.getSenha());
                cbPermissao.setSelectedIndex(funcionario.getPermissao());
                ftfDataAdmissao.setText(formatDate.format(funcionario.getDataAdmissao()));
                if (funcionario.getDataDemissao() != null) {
                    ftfDataDemissao.setText(formatDate.format(funcionario.getDataDemissao()));
                } else {
                    chAtivo.setSelected(true);
                }
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (funcionarioDAO.isFuncVazio()) {
            throw new Exception("Não há funcionários cadastrados\nCadastre um funcionário primeiro");
        }
        if (!funcionarioDAO.isFuncCadastrado(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione um funcionário");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse funcionário", "Funcionário", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (funcionarioDAO.deleteFunc(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            }
        }
    }

    private void consultaEnderecos() throws Exception {
        if (enderecoDAO.isEnderecoVazio()) {
            throw new Exception("Não há endereços cadastrados\nCadastre um endereço primeiro");
        }
        ConsultaEndereco consulta = new ConsultaEndereco(enderecoDAO);
        consulta.setListener(new ListenerEndereco() {

            @Override
            public void exibeEndereco(Endereco endereco) {
                tfCodEndereco.setText(Integer.toString(endereco.getCodigo()));
                tfEndereco.setText(endereco.getEndereco());
                tfBairro.setText(endereco.getBairro().getBairro());
                tfCep.setText(endereco.getBairro().getCep().getCep());
                tfCidade.setText(endereco.getBairro().getCep().getCidade().getCidade());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    public String calculoIdade() {
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
        if (evento.getSource() == btOk) {
            try {
                gravar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btExcluir) {
            try {
                excluir();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btSair) {
            this.dispose();
        }
        if (evento.getSource() == btConsulta) {
            try {
                consultaFunc();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaEnd) {
            try {
                consultaEnderecos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
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
        if (evento.getSource() == ftfCpf) {
            ftfCpf.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfRg) {
            ftfRg.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataNasc) {
            ftfDataNasc.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfIdade) {
            tfIdade.setBackground(Color.YELLOW);
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
        if (evento.getSource() == ftfContato) {
            ftfContato.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUsuario) {
            tfUsuario.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSenha) {
            tfSenha.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbPermissao) {
            cbPermissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCargo) {
            tfCargo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfComplemento) {
            tfComplemento.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSalario) {
            tfSalario.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfNumCartTrab) {
            ftfNumCartTrab.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataAdmissao) {
            ftfDataAdmissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataDemissao) {
            ftfDataDemissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbSexo) {
            cbSexo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbEstadoCivil) {
            cbEstadoCivil.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfNome.setBackground(Color.WHITE);
        ftfCpf.setBackground(Color.WHITE);
        ftfRg.setBackground(Color.WHITE);
        if (evento.getSource() == ftfDataNasc) {
            tfIdade.setText(calculoIdade());
            ftfDataNasc.setBackground(Color.WHITE);
        }
        tfIdade.setBackground(Color.WHITE);
        tfCodEndereco.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfCep.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        ftfContato.setBackground(Color.WHITE);
        tfUsuario.setBackground(Color.WHITE);
        tfSenha.setBackground(Color.WHITE);
        cbPermissao.setBackground(Color.WHITE);
        tfCargo.setBackground(Color.WHITE);
        tfComplemento.setBackground(Color.WHITE);
        tfSalario.setBackground(Color.WHITE);
        ftfNumCartTrab.setBackground(Color.WHITE);
        ftfDataAdmissao.setBackground(Color.WHITE);
        ftfDataDemissao.setBackground(Color.WHITE);
        cbSexo.setBackground(Color.WHITE);
        cbEstadoCivil.setBackground(Color.WHITE);
    }
}

package View.Cliente;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.Cliente.ClienteControl;
import Control.Cliente.ListenerClie;
import Control.Endereco.EnderecoControl;
import Control.Endereco.ListenerEndereco;
import Model.Cliente;
import Model.Endereco;
import View.CamposInt;
import View.Componentes;
import View.Endereco.ConsultaEndereco;

public class CadasCliente extends Componentes {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome, tfIdade,
            tfCodEndereco, tfEndereco, tfBairro, tfNumero, tfComplemento,
            tfCEP, tfCidade, tfEstado, tfReferencia, tfEmail;
    private JFormattedTextField ftfCPF, ftfRG, ftfDataNasc, ftfFone,
            ftfCelular1, ftfCelular2;
    private JComboBox cbSexo, cbEstadoCivil;
    private JButton btConsulta, btConsultaEnd, btOk, btCancelar, btExcluir,
            btSair;
    private JLabel lbNomeObrig, lbCPFObrig, lbRGObrig, lbDataNascObrig,
            lbCodEnderecoObrig, lbNumeroObrig, lbComplementoObrig, lbFoneObrig;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;
    private SimpleDateFormat formatDateHora;
    private EnderecoControl controleEndereco;
    private ClienteControl controleClie;

    public CadasCliente(Connection con) {
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        controleEndereco = new EnderecoControl(con);
        controleClie = new ClienteControl(con);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Clientes");
        setLayout(null);

        JPanel panel = getJPanel(10, 10, 680, 355);

        panel.add(getJLabel("Codigo", 20, 20, 70, 14));

        tfCodigo = getJTextFieldNaoEdit(20, 38, 80, 28);
        try {
            tfCodigo.setText(Integer.toString(controleClie.getProxCodClie()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        panel.add(tfCodigo);

        btConsulta = getJButton("...", "Consulta clientes", 106, 37, 35, 30);
        panel.add(btConsulta);

        panel.add(getJLabel("Cadastro em", 146, 20, 75, 14));

        tfDataCadas = getJTextFieldNaoEdit(146, 38, 130, 28);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        panel.add(tfDataCadas);

        panel.add(getJLabel("Última Alteração", 282, 20, 90, 14));

        tfUltAlteracao = getJTextFieldNaoEdit(282, 38, 130, 28);
        panel.add(tfUltAlteracao);

        panel.add(getJLabel("Nome", 417, 20, 60, 14));

        lbNomeObrig = getJLabelVermelho(454, 23, 10, 14);
        panel.add(lbNomeObrig);

        tfNome = getJTextField(417, 38, 240, 28);
        panel.add(tfNome);

        panel.add(getJLabel("CPF", 20, 70, 60, 14));

        lbCPFObrig = getJLabelVermelho(46, 73, 10, 14);
        panel.add(lbCPFObrig);

        ftfCPF = getJFormattedTextField("###.###.###-##", 20, 88, 120, 28);
        panel.add(ftfCPF);

        panel.add(getJLabel("RG", 147, 70, 60, 14));

        lbRGObrig = getJLabelVermelho(168, 73, 10, 14);
        panel.add(lbRGObrig);

        ftfRG = getJFormattedTextField("###.###.###", 147, 88, 100, 28);
        panel.add(ftfRG);

        panel.add(getJLabel("Sexo", 255, 70, 60, 14));

        cbSexo = getJComboBox(255, 88, 100, 28);
        cbSexo.addItem("Selecione");
        cbSexo.addItem("Masculino");
        cbSexo.addItem("Feminino");
        panel.add(cbSexo);

        panel.add(getJLabel("Estado Civil", 363, 70, 80, 14));

        cbEstadoCivil = getJComboBox(363, 88, 100, 28);
        cbEstadoCivil.addItem("Selecione");
        cbEstadoCivil.addItem("Solteiro");
        cbEstadoCivil.addItem("Casado");
        cbEstadoCivil.addItem("Separado");
        cbEstadoCivil.addItem("Divorciado");
        cbEstadoCivil.addItem("Viúvo");
        panel.add(cbEstadoCivil);

        panel.add(getJLabel("Data Nascimento", 470, 70, 97, 14));

        lbDataNascObrig = getJLabelVermelho(568, 73, 10, 14);
        panel.add(lbDataNascObrig);

        ftfDataNasc = getJFormattedTextField("##/##/####", 470, 88, 100, 28);
        panel.add(ftfDataNasc);

        panel.add(getJLabel("Idade", 577, 70, 70, 14));

        tfIdade = getJTextFieldNaoEdit(577, 88, 80, 28);
        panel.add(tfIdade);

        panel.add(getJLabel("Cod. Endereço", 20, 120, 85, 14));

        lbCodEnderecoObrig = getJLabelVermelho(105, 123, 10, 14);
        panel.add(lbCodEnderecoObrig);

        tfCodEndereco = getJTextFieldNaoEdit(20, 138, 80, 28);
        panel.add(tfCodEndereco);

        btConsultaEnd = getJButton("...", "Consulta endereços", 106, 137, 35, 30);
        panel.add(btConsultaEnd);

        panel.add(getJLabel("Endereço", 146, 120, 70, 14));

        tfEndereco = getJTextFieldNaoEdit(146, 138, 170, 28);
        panel.add(tfEndereco);

        panel.add(getJLabel("Bairro", 323, 120, 70, 14));

        tfBairro = getJTextFieldNaoEdit(323, 138, 140, 28);
        panel.add(tfBairro);

        panel.add(getJLabel("Numero", 470, 120, 70, 14));

        lbNumeroObrig = getJLabelVermelho(518, 123, 10, 14);
        panel.add(lbNumeroObrig);

        tfNumero = getJTextField(470, 138, 80, 28);
        tfNumero.setDocument(new CamposInt());
        panel.add(tfNumero);

        panel.add(getJLabel("Complemento", 557, 120, 85, 14));

        lbComplementoObrig = getJLabelVermelho(639, 123, 10, 14);
        panel.add(lbComplementoObrig);

        tfComplemento = getJTextField(557, 138, 100, 28);
        panel.add(tfComplemento);

        panel.add(getJLabel("CEP", 20, 170, 70, 14));

        tfCEP = getJTextFieldNaoEdit(20, 188, 100, 28);
        panel.add(tfCEP);

        panel.add(getJLabel("Cidade", 127, 170, 70, 14));

        tfCidade = getJTextFieldNaoEdit(127, 188, 150, 28);
        panel.add(tfCidade);

        panel.add(getJLabel("Estado", 283, 170, 70, 14));

        tfEstado = getJTextFieldNaoEdit(283, 188, 130, 28);
        panel.add(tfEstado);

        panel.add(getJLabel("Referencia", 420, 170, 70, 14));

        tfReferencia = getJTextField(420, 188, 235, 28);
        panel.add(tfReferencia);

        panel.add(getJLabel("Fone", 20, 220, 60, 14));

        lbFoneObrig = getJLabelVermelho(50, 223, 10, 14);
        panel.add(lbFoneObrig);

        ftfFone = getJFormattedTextField("(##)####-####", 20, 238, 100, 28);
        panel.add(ftfFone);

        panel.add(getJLabel("Celular 1", 127, 220, 60, 14));

        ftfCelular1 = getJFormattedTextField("(##)####-####", 127, 238, 100, 28);
        panel.add(ftfCelular1);

        panel.add(getJLabel("Celular 2", 234, 220, 60, 14));

        ftfCelular2 = getJFormattedTextField("(##)####-####", 234, 238, 100, 28);
        panel.add(ftfCelular2);

        panel.add(getJLabel("E-Mail", 341, 220, 60, 14));

        tfEmail = getJTextField(341, 238, 315, 28);
        panel.add(tfEmail);

        panel.add(getJSeparator(0, 280, 679, 3));

        btOk = getJButtonOK("Confirma Operação", 70, 300, 64, 30);
        panel.add(btOk);

        btCancelar = getJButtonCancelar("Limpar os Campos", 200, 300, 97, 30);
        panel.add(btCancelar);

        btExcluir = getJButtonExcluir("Excluir Registro", 360, 300, 83, 30);
        panel.add(btExcluir);

        btSair = getJButtonSair("Fechar", 500, 300, 70, 30);
        panel.add(btSair);

        add(panel);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(708, 405);
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
        ftfCPF.setText("");
        ftfRG.setText("");
        cbSexo.setSelectedItem("Selecione");
        cbEstadoCivil.setSelectedItem("Selecione");
        tfCodEndereco.setText("");
        ftfDataNasc.setText("");
        tfIdade.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfComplemento.setText("");
        tfCEP.setText("");
        tfCidade.setText("");
        tfEstado.setText("");
        tfReferencia.setText("");
        ftfFone.setText("");
        ftfCelular1.setText("");
        ftfCelular2.setText("");
        tfEmail.setText("");
        lbNomeObrig.setText("");
        lbCPFObrig.setText("");
        lbRGObrig.setText("");
        lbDataNascObrig.setText("");
        lbCodEnderecoObrig.setText("");
        lbNumeroObrig.setText("");
        lbComplementoObrig.setText("");
        lbFoneObrig.setText("");
    }

    private void gravar() throws Exception {
        boolean flag = false;
        if ("".equals(tfNome.getText().trim())) {
            lbNomeObrig.setText("*");
            flag = true;
        } else {
            lbNomeObrig.setText("");
        }
        if ("   .   .   -  ".equals(ftfCPF.getText())) {
            lbCPFObrig.setText("*");
            flag = true;
        } else {
            lbCPFObrig.setText("");
        }
        if ("   .   .   ".equals(ftfRG.getText())) {
            lbRGObrig.setText("*");
            flag = true;
        } else {
            lbRGObrig.setText("");
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
        if ("(  )    -    ".equals(ftfFone.getText())) {
            lbFoneObrig.setText("*");
            flag = true;
        } else {
            lbFoneObrig.setText("");
        }
        if (!flag) {
            if (!validaData(ftfDataNasc)) {
                throw new Exception("Campo data nascimento inválido");
            }
            if (Integer.parseInt(tfIdade.getText()) < 16) {
                throw new Exception("Idade inválida para realizar o cadastro. A idade de ser superior a 15");
            }
            Cliente cliente = new Cliente();
            cliente.setCodigo(Integer.parseInt(tfCodigo.getText()));
            Endereco endereco = new Endereco();
            endereco.setCodigo(Integer.parseInt(tfCodEndereco.getText()));
            cliente.setEndereco(endereco);
            cliente.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            cliente.setUltAlteracao(new Date());
            cliente.setNome(tfNome.getText());
            cliente.setCpf(ftfCPF.getText());
            cliente.setRg(ftfRG.getText());
            cliente.setSexo((String) cbSexo.getSelectedItem());
            cliente.setEstadoCivil((String) cbEstadoCivil.getSelectedItem());
            cliente.setDataNascimento(formatDate.parse(ftfDataNasc.getText()));
            cliente.setNumero(Integer.parseInt(tfNumero.getText()));
            cliente.setComplemento(tfComplemento.getText());
            cliente.setReferencia(tfReferencia.getText());
            cliente.setFone(ftfFone.getText());
            cliente.setCelular1(ftfCelular1.getText());
            cliente.setCelular2(ftfCelular2.getText());
            cliente.setEmail(tfEmail.getText());
            int codClieCadas = controleClie.getClieCadastrado(ftfCPF.getText(), Integer.parseInt(tfCodigo.getText()));// verifica se cliente já cadastrado
            if (codClieCadas != -1) {
                if (JOptionPane.showConfirmDialog(null, "Confirmar alteração nos dados", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (controleClie.updateCliente(cliente)) {
                        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            } else {
                codClieCadas = controleClie.getClieCadastrado(ftfCPF.getText());// verifica se há um cliente já cadastrado com esse CPF
                if (codClieCadas != -1) {
                    JOptionPane.showMessageDialog(null, "Este CPF ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
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
                tfCodigo.setText(Integer.toString(cliente.getCodigo()));
                tfDataCadas.setText(formatDate.format(cliente.getDataCadastro()) + " as " + formatHora.format(cliente.getDataCadastro()));
                tfUltAlteracao.setText(formatDate.format(cliente.getUltAlteracao()) + " as " + formatHora.format(cliente.getUltAlteracao()));
                tfNome.setText(cliente.getNome());
                ftfCPF.setText(cliente.getCpf());
                ftfRG.setText(cliente.getRg());
                cbSexo.setSelectedItem(cliente.getSexo());
                cbEstadoCivil.setSelectedItem(cliente.getEstadoCivil());
                ftfDataNasc.setText(formatDate.format(cliente.getDataNascimento()));
                tfCodEndereco.setText(Integer.toString(cliente.getEndereco().getCodigo()));
                tfEndereco.setText(cliente.getEndereco().getEndereco());
                tfBairro.setText(cliente.getEndereco().getBairro().getBairro());
                tfNumero.setText(Integer.toString(cliente.getNumero()));
                tfComplemento.setText(cliente.getComplemento());
                tfCEP.setText(cliente.getEndereco().getBairro().getCep().getCep());
                tfCidade.setText(cliente.getEndereco().getBairro().getCep().getCidade().getCidade());
                tfEstado.setText(cliente.getEndereco().getBairro().getCep().getCidade().getEstado().getEstado());
                tfReferencia.setText(cliente.getReferencia());
                ftfFone.setText(cliente.getFone());
                ftfCelular1.setText(cliente.getCelular1());
                ftfCelular2.setText(cliente.getCelular2());
                tfEmail.setText(cliente.getEmail());
                tfIdade.setText(calculoIdade());
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

    private void consultaEndereco() throws Exception {
        if (controleEndereco.isEnderecoVazio()) {
            throw new Exception("Não há endereços cadastrados");
        }
        ConsultaEndereco consulta = new ConsultaEndereco(controleEndereco);
        consulta.setListener(new ListenerEndereco() {

            @Override
            public void exibeEndereco(Endereco endereco) {
                tfCodEndereco.setText(Integer.toString(endereco.getCodigo()));
                tfEndereco.setText(endereco.getEndereco());
                tfBairro.setText(endereco.getBairro().getBairro());
                tfCEP.setText(endereco.getBairro().getCep().getCep());
                tfCidade.setText(endereco.getBairro().getCep().getCidade().getCidade());
                tfEstado.setText(endereco.getBairro().getCep().getCidade().getEstado().getEstado());
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
        if (evento.getSource() == btConsultaEnd) {
            try {
                consultaEndereco();
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
        if (evento.getSource() == ftfCPF) {
            ftfCPF.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfRG) {
            ftfRG.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodEndereco) {
            tfCodEndereco.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfCEP) {
            tfCEP.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCidade) {
            tfCidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEstado) {
            tfEstado.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfReferencia) {
            tfReferencia.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfEmail) {
            tfEmail.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfNome.setBackground(Color.WHITE);
        ftfCPF.setBackground(Color.WHITE);
        ftfRG.setBackground(Color.WHITE);
        tfCodEndereco.setBackground(Color.WHITE);
        if (evento.getSource() == ftfDataNasc) {
            ftfDataNasc.setBackground(Color.WHITE);
            tfIdade.setText(calculoIdade());
        }
        tfIdade.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfComplemento.setBackground(Color.WHITE);
        tfCEP.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfEstado.setBackground(Color.WHITE);
        tfReferencia.setBackground(Color.WHITE);
        ftfFone.setBackground(Color.WHITE);
        ftfCelular1.setBackground(Color.WHITE);
        ftfCelular2.setBackground(Color.WHITE);
        tfEmail.setBackground(Color.WHITE);
    }
}

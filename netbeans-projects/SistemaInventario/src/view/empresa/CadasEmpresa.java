package view.empresa;

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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import model.Empresa;
import model.Endereco;

import dbOracle.EmpresaDAO;
import dbOracle.EnderecoDAO;

import view.CamposInt;
import view.PanelComponentes;
import view.endereco.ConsultaEndereco;
import view.endereco.ListenerEndereco;

public class CadasEmpresa extends JDialog implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome,
            tfCodEndereco, tfEndereco, tfBairro, tfNumero, tfCep, tfCidade,
            tfEmail;
    private JFormattedTextField ftfCnpj, ftfIe, ftfContato;
    private JButton btConsulta, btConsultaEnd, btOk, btCancelar, btExcluir,
            btSair;
    private JLabel lbNomeObrig, lbCnpjObrig, lbIeObrig, lbCodEnderecoObrig,
            lbNumeroObrig, lbContatoObrig;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;
    private SimpleDateFormat formatDateHora;
    private EnderecoDAO enderecoDAO;
    private EmpresaDAO empresaDAO;

    public CadasEmpresa(Connection con) {
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        enderecoDAO = new EnderecoDAO(con);
        empresaDAO = new EmpresaDAO(con);
        try {
            initComponents();
        } catch (ParseException ex) {
        }
    }

    private void initComponents() throws ParseException {
        setTitle("Cadastro de Empresas");
        setLayout(null);

        PanelComponentes panel = new PanelComponentes(5, 5, 410, 307);
        this.add(panel);

        panel.addJLabel("Codigo", 20, 20, 50, 14);

        tfCodigo = panel.addJTextFieldNaoEdit(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(empresaDAO.getProxCodEmpresa()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.addFocusListener(this);

        btConsulta = panel.addJButton("...", "Consulta Empresas", 105, 34, 31, 26);
        btConsulta.addActionListener(this);

        panel.addJLabel("Cadastro em", 143, 20, 90, 14);

        tfDataCadas = panel.addJTextFieldNaoEdit(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.addFocusListener(this);

        panel.addJLabel("Última Alteração", 270, 20, 100, 14);

        tfUltAlteracao = panel.addJTextFieldNaoEdit(270, 38, 120, 20);
        tfUltAlteracao.addFocusListener(this);

        panel.addJLabel("Nome", 20, 63, 50, 14);

        lbNomeObrig = panel.addJLabelRED(50, 66, 10, 14);

        tfNome = panel.addJTextField(20, 81, 150, 20);
        tfNome.addFocusListener(this);

        panel.addJLabel("CNPJ", 180, 63, 50, 14);

        lbCnpjObrig = panel.addJLabelRED(207, 66, 10, 14);

        ftfCnpj = panel.addJFormattedTextField("##.###.###/####-##", 180, 81, 110, 20);
        ftfCnpj.addFocusListener(this);

        panel.addJLabel("IE", 300, 63, 50, 14);

        lbIeObrig = panel.addJLabelRED(313, 66, 10, 14);

        ftfIe = panel.addJFormattedTextField("###.###.###", 300, 81, 90, 20);
        ftfIe.addFocusListener(this);

        panel.addJLabel("Cod. Endereço", 20, 105, 80, 14);

        lbCodEnderecoObrig = panel.addJLabelRED(94, 108, 10, 14);

        tfCodEndereco = panel.addJTextFieldNaoEdit(20, 123, 80, 20);
        tfCodEndereco.addFocusListener(this);

        btConsultaEnd = panel.addJButton("...", "Consulta Endereços", 108, 119, 31, 26);
        btConsultaEnd.addActionListener(this);

        panel.addJLabel("Endereço", 150, 105, 70, 14);

        tfEndereco = panel.addJTextFieldNaoEdit(150, 123, 240, 20);
        tfEndereco.addFocusListener(this);

        panel.addJLabel("Bairro", 20, 147, 60, 14);

        tfBairro = panel.addJTextFieldNaoEdit(20, 165, 180, 20);
        tfBairro.addFocusListener(this);

        panel.addJLabel("Número", 210, 147, 55, 14);

        lbNumeroObrig = panel.addJLabelRED(250, 150, 10, 14);

        tfNumero = panel.addJTextField(210, 165, 80, 20);
        tfNumero.setDocument(new CamposInt());
        tfNumero.addFocusListener(this);

        panel.addJLabel("CEP", 300, 147, 80, 14);

        tfCep = panel.addJTextFieldNaoEdit(300, 165, 90, 20);
        tfCep.addFocusListener(this);

        panel.addJLabel("Cidade", 20, 190, 70, 14);

        tfCidade = panel.addJTextFieldNaoEdit(20, 208, 130, 20);
        tfCidade.addFocusListener(this);

        panel.addJLabel("Contato", 157, 190, 60, 14);

        lbContatoObrig = panel.addJLabelRED(198, 193, 10, 14);

        ftfContato = panel.addJFormattedTextField("(##)####-####", 157, 208, 80, 20);
        ftfContato.addFocusListener(this);

        panel.addJLabel("E-Mail", 243, 190, 55, 14);

        tfEmail = panel.addJTextField(243, 208, 147, 20);
        tfEmail.addFocusListener(this);

        panel.addJSeparator(JSeparator.HORIZONTAL, 0, 243, 408, 3);

        btOk = panel.addJButtonOK(20, 263, 70, 26);
        btOk.addActionListener(this);

        btCancelar = panel.addJButtonCancelar(118, 263, 70, 26);
        btCancelar.addActionListener(this);

        btExcluir = panel.addJButtonExcuir(213, 263, 70, 26);
        btExcluir.addActionListener(this);

        btSair = panel.addJButtonSair(310, 263, 71, 26);
        btSair.addActionListener(this);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(425, 345);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(empresaDAO.getProxCodEmpresa()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfNome.setText("");
        ftfCnpj.setText("");
        ftfIe.setText("");
        tfCodEndereco.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfCep.setText("");
        tfCidade.setText("");
        ftfContato.setText("");
        tfEmail.setText("");
        lbNomeObrig.setText("");
        lbCnpjObrig.setText("");
        lbIeObrig.setText("");
        lbCodEnderecoObrig.setText("");
        lbNumeroObrig.setText("");
        lbContatoObrig.setText("");
    }

    private void gravar() throws Exception {
        boolean flag = false;
        if ("".equals(tfNome.getText().trim())) {
            lbNomeObrig.setText("*");
            flag = true;
        } else {
            lbNomeObrig.setText("");
        }
        if ("  .   .   /    -  ".equals(ftfCnpj.getText())) {
            lbCnpjObrig.setText("*");
            flag = true;
        } else {
            lbCnpjObrig.setText("");
        }
        if ("   .   .   ".equals(ftfIe.getText())) {
            lbIeObrig.setText("*");
            flag = true;
        } else {
            lbIeObrig.setText("");
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
        if ("(  )    -    ".equals(ftfContato.getText())) {
            lbContatoObrig.setText("*");
            flag = true;
        } else {
            lbContatoObrig.setText("");
        }
        if (!flag) {
            Empresa empresa = new Empresa();
            empresa.setCodigo(Integer.parseInt(tfCodigo.getText()));
            empresa.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            empresa.setUltAlteracao(new Date());
            empresa.setNome(tfNome.getText());
            empresa.setCpfCnpj(ftfCnpj.getText());
            empresa.setRgIe(ftfIe.getText());
            Endereco endereco = new Endereco();
            endereco.setCodigo(Integer.parseInt(tfCodEndereco.getText()));
            empresa.setEndereco(endereco);
            empresa.setNumero(Integer.parseInt(tfNumero.getText()));
            empresa.setContato(ftfContato.getText());
            empresa.setEmail(tfEmail.getText());
            int codEmpCadas = empresaDAO.getEmpresaCadastrada(ftfCnpj.getText(), Integer.parseInt(tfCodigo.getText()));// verifica se empresa já está cadastrado
            if (codEmpCadas != -1) {
                if (JOptionPane.showConfirmDialog(null, "Confirmar alteração nos dados", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (empresaDAO.updateEmpresa(empresa)) {
                        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "Empresa", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            } else {
                codEmpCadas = empresaDAO.getEmpresaCadastrada(ftfCnpj.getText());// verifica se pessoa já está cadastrada
                if (codEmpCadas != -1) {
                    JOptionPane.showMessageDialog(null, "Este CNPJ ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (empresaDAO.insertEmpresa(empresa)) {
                        JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso", "Empresa", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            }
        } else {
            throw new Exception("Campos * obrigatórios não preenchidos");
        }
    }

    private void consultaEmp() throws Exception {
        if (empresaDAO.isEmpresaVazio()) {
            throw new Exception("Não há empresas cadastradas\nCadastre uma empresa primeiro");
        }
        ConsultaEmpresa consulta = new ConsultaEmpresa(empresaDAO);
        consulta.setListener(new ListenerEmpresa() {

            @Override
            public void exibeEmpresa(Empresa empresa) {
                limparTela();
                tfCodigo.setText(Integer.toString(empresa.getCodigo()));
                tfDataCadas.setText(formatDate.format(empresa.getDataCadastro()) + " as " + formatHora.format(empresa.getDataCadastro()));
                tfUltAlteracao.setText(formatDate.format(empresa.getUltAlteracao()) + " as " + formatHora.format(empresa.getUltAlteracao()));
                tfNome.setText(empresa.getNome());
                ftfCnpj.setText(empresa.getCpfCnpj());
                ftfIe.setText(empresa.getRgIe());
                tfCodEndereco.setText(Integer.toString(empresa.getEndereco().getCodigo()));
                tfEndereco.setText(empresa.getEndereco().getEndereco());
                tfBairro.setText(empresa.getEndereco().getBairro().getBairro());
                tfNumero.setText(Integer.toString(empresa.getNumero()));
                tfCep.setText(empresa.getEndereco().getBairro().getCep().getCep());
                tfCidade.setText(empresa.getEndereco().getBairro().getCep().getCidade().getCidade());
                ftfContato.setText(empresa.getContato());
                tfEmail.setText(empresa.getEmail());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (empresaDAO.isEmpresaVazio()) {
            throw new Exception("Não há empresas cadastradas\nCadastre uma empresa primeiro");
        }
        if (!empresaDAO.isEmpresaCadastrada(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione uma empresa");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir essa empresa", "Empresa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (empresaDAO.deleteEmpresa(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Empresa excluída com sucesso", "Empresa", JOptionPane.INFORMATION_MESSAGE);
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
                consultaEmp();
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
        if (evento.getSource() == ftfCnpj) {
            ftfCnpj.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfIe) {
            ftfIe.setBackground(Color.YELLOW);
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
        ftfCnpj.setBackground(Color.WHITE);
        ftfIe.setBackground(Color.WHITE);
        tfCodEndereco.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfCep.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        ftfContato.setBackground(Color.WHITE);
        tfEmail.setBackground(Color.WHITE);
    }
}

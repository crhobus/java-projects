package view.fornecedor;

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

import model.Endereco;
import model.Fornecedor;

import dbOracle.EnderecoDAO;
import dbOracle.FornecedorDAO;

import view.CamposInt;
import view.PanelComponentes;
import view.endereco.ConsultaEndereco;
import view.endereco.ListenerEndereco;

public class CadasFornecedor extends JDialog implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome,
            tfCodEndereco, tfEndereco, tfBairro, tfNumero, tfCep, tfCidade,
            tfEmail, tfHomePage;
    private JFormattedTextField ftfCnpj, ftfIe, ftfContato;
    private JButton btConsulta, btConsultaEnd, btOk, btCancelar, btExcluir,
            btSair;
    private JLabel lbNomeObrig, lbCnpjObrig, lbIeObrig, lbCodEnderecoObrig,
            lbNumeroObrig, lbContatoObrig;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;
    private SimpleDateFormat formatDateHora;
    private EnderecoDAO enderecoDAO;
    private FornecedorDAO fornecedorDAO;

    public CadasFornecedor(Connection con) {
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        enderecoDAO = new EnderecoDAO(con);
        fornecedorDAO = new FornecedorDAO(con);
        try {
            initComponents();
        } catch (ParseException ex) {
        }
    }

    private void initComponents() throws ParseException {
        setTitle("Cadastro de Fornecedores");
        setLayout(null);

        PanelComponentes panel = new PanelComponentes(5, 5, 570, 270);
        this.add(panel);

        panel.addJLabel("Codigo", 20, 20, 50, 14);

        tfCodigo = panel.addJTextFieldNaoEdit(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(fornecedorDAO.getProxCodForn()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.addFocusListener(this);

        btConsulta = panel.addJButton("...", "Consulta Fornecedores", 105, 34, 31, 26);
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

        panel.addJLabel("CNPJ", 20, 63, 50, 14);

        lbCnpjObrig = panel.addJLabelRED(47, 66, 10, 14);

        ftfCnpj = panel.addJFormattedTextField("##.###.###/####-##", 20, 81, 110, 20);
        ftfCnpj.addFocusListener(this);

        panel.addJLabel("IE", 140, 63, 50, 14);

        lbIeObrig = panel.addJLabelRED(154, 66, 10, 14);

        ftfIe = panel.addJFormattedTextField("###.###.###", 140, 81, 85, 20);
        ftfIe.addFocusListener(this);

        panel.addJLabel("Cod. Endereço", 235, 63, 80, 14);

        lbCodEnderecoObrig = panel.addJLabelRED(308, 66, 10, 14);

        tfCodEndereco = panel.addJTextFieldNaoEdit(235, 81, 85, 20);
        tfCodEndereco.addFocusListener(this);

        btConsultaEnd = panel.addJButton("...", "Consulta Endereço", 327, 76, 31, 26);
        btConsultaEnd.addActionListener(this);

        panel.addJLabel("Endereço", 365, 63, 60, 14);

        tfEndereco = panel.addJTextFieldNaoEdit(365, 81, 185, 20);
        tfEndereco.addFocusListener(this);

        panel.addJLabel("Bairro", 20, 105, 60, 14);

        tfBairro = panel.addJTextFieldNaoEdit(20, 123, 170, 20);
        tfBairro.addFocusListener(this);

        panel.addJLabel("Número", 200, 105, 55, 14);

        lbNumeroObrig = panel.addJLabelRED(240, 108, 10, 14);

        tfNumero = panel.addJTextField(200, 123, 80, 20);
        tfNumero.setDocument(new CamposInt());
        tfNumero.addFocusListener(this);

        panel.addJLabel("CEP", 290, 105, 80, 14);

        tfCep = panel.addJTextFieldNaoEdit(290, 123, 90, 20);
        tfCep.addFocusListener(this);

        panel.addJLabel("Cidade", 390, 105, 70, 14);

        tfCidade = panel.addJTextFieldNaoEdit(390, 123, 160, 20);
        tfCidade.addFocusListener(this);

        panel.addJLabel("Contato", 20, 147, 60, 14);

        lbContatoObrig = panel.addJLabelRED(62, 150, 10, 14);

        ftfContato = panel.addJFormattedTextField("(##)####-####", 20, 165, 90, 20);
        ftfContato.addFocusListener(this);

        panel.addJLabel("E-Mail", 120, 147, 60, 14);

        tfEmail = panel.addJTextField(120, 165, 210, 20);
        tfEmail.addFocusListener(this);

        panel.addJLabel("Home Page", 340, 147, 60, 14);

        tfHomePage = panel.addJTextField(340, 165, 210, 20);
        tfHomePage.addFocusListener(this);

        panel.addJSeparator(JSeparator.HORIZONTAL, 0, 200, 568, 3);

        btOk = panel.addJButtonOK(60, 220, 70, 26);
        btOk.addActionListener(this);

        btCancelar = panel.addJButtonCancelar(180, 220, 70, 26);
        btCancelar.addActionListener(this);

        btExcluir = panel.addJButtonExcuir(300, 220, 70, 26);
        btExcluir.addActionListener(this);

        btSair = panel.addJButtonSair(420, 220, 71, 26);
        btSair.addActionListener(this);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(586, 306);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(fornecedorDAO.getProxCodForn()));
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
        tfHomePage.setText("");
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
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setCodigo(Integer.parseInt(tfCodigo.getText()));
            fornecedor.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            fornecedor.setUltAlteracao(new Date());
            fornecedor.setNome(tfNome.getText());
            fornecedor.setCpfCnpj(ftfCnpj.getText());
            fornecedor.setRgIe(ftfIe.getText());
            Endereco endereco = new Endereco();
            endereco.setCodigo(Integer.parseInt(tfCodEndereco.getText()));
            fornecedor.setEndereco(endereco);
            fornecedor.setNumero(Integer.parseInt(tfNumero.getText()));
            fornecedor.setContato(ftfContato.getText());
            fornecedor.setEmail(tfEmail.getText());
            fornecedor.setHomePage(tfHomePage.getText());
            int codFornCadas = fornecedorDAO.getFornCadastrado(ftfCnpj.getText(), Integer.parseInt(tfCodigo.getText()));// verifica se fornecedor já cadastrado
            if (codFornCadas != -1) {
                if (JOptionPane.showConfirmDialog(null, "Confirmar alteração nos dados", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (fornecedorDAO.updateForn(fornecedor)) {
                        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            } else {
                codFornCadas = fornecedorDAO.getFornCadastrado(ftfCnpj.getText());// verifica se pessoa já cadastrada
                if (codFornCadas != -1) {
                    JOptionPane.showMessageDialog(null, "Este CNPJ ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (fornecedorDAO.insertForn(fornecedor)) {
                        JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            }
        } else {
            throw new Exception("Campos * obrigatórios não preenchidos");
        }
    }

    private void consultaForn() throws Exception {
        if (fornecedorDAO.isFornVazio()) {
            throw new Exception("Não há fornecedores cadastrados\nCadastre um fornecedor primeiro");
        }
        ConsultaFornecedor consulta = new ConsultaFornecedor(fornecedorDAO);
        consulta.setListener(new ListenerFornecedor() {

            @Override
            public void exibeFornecedor(Fornecedor fornecedor) {
                limparTela();
                tfCodigo.setText(Integer.toString(fornecedor.getCodigo()));
                tfDataCadas.setText(formatDate.format(fornecedor.getDataCadastro()) + " as " + formatHora.format(fornecedor.getDataCadastro()));
                tfUltAlteracao.setText(formatDate.format(fornecedor.getUltAlteracao()) + " as " + formatHora.format(fornecedor.getUltAlteracao()));
                tfNome.setText(fornecedor.getNome());
                ftfCnpj.setText(fornecedor.getCpfCnpj());
                ftfIe.setText(fornecedor.getRgIe());
                tfCodEndereco.setText(Integer.toString(fornecedor.getEndereco().getCodigo()));
                tfEndereco.setText(fornecedor.getEndereco().getEndereco());
                tfBairro.setText(fornecedor.getEndereco().getBairro().getBairro());
                tfNumero.setText(Integer.toString(fornecedor.getNumero()));
                tfCep.setText(fornecedor.getEndereco().getBairro().getCep().getCep());
                tfCidade.setText(fornecedor.getEndereco().getBairro().getCep().getCidade().getCidade());
                ftfContato.setText(fornecedor.getContato());
                tfEmail.setText(fornecedor.getEmail());
                tfHomePage.setText(fornecedor.getHomePage());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (fornecedorDAO.isFornVazio()) {
            throw new Exception("Não há fornecedores cadastrados\nCadastre um fornecedor primeiro");
        }
        if (!fornecedorDAO.isFornCadastrado(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione um fornecedor");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse fornecedor", "Fornecedor", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (fornecedorDAO.deleteForn(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
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
                consultaForn();
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
        if (evento.getSource() == tfHomePage) {
            tfHomePage.setBackground(Color.YELLOW);
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
        tfHomePage.setBackground(Color.WHITE);
    }
}

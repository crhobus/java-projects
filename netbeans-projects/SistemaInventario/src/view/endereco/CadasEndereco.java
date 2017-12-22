package view.endereco;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import model.Bairro;
import model.Cep;
import model.Cidade;
import model.Endereco;

import dbOracle.BairroDAO;
import dbOracle.CepDAO;
import dbOracle.CidadeDAO;
import dbOracle.EnderecoDAO;

import view.PanelComponentes;
import view.endereco.bairro.ConsultaBairro;
import view.endereco.bairro.ListenerBairro;
import view.endereco.cep.ConsultaCep;
import view.endereco.cep.ListenerCep;
import view.endereco.cidade.ConsultaCidade;
import view.endereco.cidade.ListenerCidade;

public class CadasEndereco extends JDialog implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodCidade, tfCidade, tfCodCep, tfCodBairro, tfBairro,
            tfCodEnd, tfEndereco;
    private JFormattedTextField ftfCep;
    private JButton btConsultaCid, btOkCid, btConsultaCep, btOkCep,
            btConsultaBai, btOkBai, btConsultaEnd, btOkEnd, btNovo, btSair;
    private CidadeDAO cidadeDAO;
    private CepDAO cepDAO;
    private BairroDAO bairroDAO;
    private EnderecoDAO enderecoDAO;

    public CadasEndereco(Connection con) {
        cidadeDAO = new CidadeDAO(con);
        cepDAO = new CepDAO(con);
        bairroDAO = new BairroDAO(con);
        enderecoDAO = new EnderecoDAO(con);
        try {
            initComponents();
        } catch (ParseException ex) {
        }
    }

    private void initComponents() throws ParseException {
        setTitle("Cadastro de Endereços");
        setLayout(null);

        PanelComponentes panel = new PanelComponentes(5, 5, 415, 325);
        this.add(panel);

        panel.addJLabel("Cod. Cidade", 20, 20, 60, 14);

        tfCodCidade = panel.addJTextFieldNaoEdit(20, 38, 80, 20);
        try {
            tfCodCidade.setText(Integer.toString(cidadeDAO.getProxCodCidade()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfCodCidade.addFocusListener(this);

        btConsultaCid = panel.addJButton("...", "Consulta Cidades", 105, 35, 31, 26);
        btConsultaCid.addActionListener(this);

        panel.addJLabel("Cidade", 143, 20, 90, 14);

        tfCidade = panel.addJTextField(143, 38, 200, 20);
        tfCidade.addFocusListener(this);

        btOkCid = panel.addJButtonOK(350, 35, 50, 26);
        btOkCid.setMargin(new Insets(0, 0, 0, 0));
        btOkCid.addActionListener(this);

        panel.addJSeparator(JSeparator.HORIZONTAL, 0, 75, 414, 3);

        panel.addJLabel("Cod. CEP", 20, 85, 60, 14);

        tfCodCep = panel.addJTextFieldNaoEdit(20, 103, 80, 20);
        try {
            tfCodCep.setText(Integer.toString(cepDAO.getProxCodCep()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfCodCep.addFocusListener(this);

        btConsultaCep = panel.addJButton("...", "Consulta CEP", 105, 100, 31, 26);
        btConsultaCep.addActionListener(this);

        panel.addJLabel("CEP", 143, 85, 90, 14);

        ftfCep = panel.addJFormattedTextField("#####-###", 143, 103, 200, 20);
        ftfCep.addFocusListener(this);

        btOkCep = panel.addJButtonOK(350, 100, 50, 26);
        btOkCep.setMargin(new Insets(0, 0, 0, 0));
        btOkCep.addActionListener(this);

        panel.addJSeparator(JSeparator.HORIZONTAL, 0, 140, 414, 3);

        panel.addJLabel("Cod. Bairro", 20, 150, 80, 14);

        tfCodBairro = panel.addJTextFieldNaoEdit(20, 168, 80, 20);
        try {
            tfCodBairro.setText(Integer.toString(bairroDAO.getProxCodBairro()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfCodBairro.addFocusListener(this);

        btConsultaBai = panel.addJButton("...", "Consulta Bairros", 105, 165, 31, 26);
        btConsultaBai.addActionListener(this);

        panel.addJLabel("Bairro", 143, 150, 90, 14);

        tfBairro = panel.addJTextField(143, 168, 200, 20);
        tfBairro.addFocusListener(this);

        btOkBai = panel.addJButtonOK(350, 165, 50, 26);
        btOkBai.setMargin(new Insets(0, 0, 0, 0));
        btOkBai.addActionListener(this);

        panel.addJSeparator(JSeparator.HORIZONTAL, 0, 205, 414, 3);

        panel.addJLabel("Cod. Endereço", 20, 215, 80, 14);

        tfCodEnd = panel.addJTextFieldNaoEdit(20, 233, 80, 20);
        try {
            tfCodEnd.setText(Integer.toString(enderecoDAO.getProxCodEndereco()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfCodEnd.addFocusListener(this);

        btConsultaEnd = panel.addJButton("...", "Consulta Endereços", 105, 230, 31, 26);
        btConsultaEnd.addActionListener(this);

        panel.addJLabel("Endereço", 143, 215, 90, 14);

        tfEndereco = panel.addJTextField(143, 233, 200, 20);
        tfEndereco.addFocusListener(this);

        btOkEnd = panel.addJButtonOK(350, 230, 50, 26);
        btOkEnd.setMargin(new Insets(0, 0, 0, 0));
        btOkEnd.addActionListener(this);

        panel.addJSeparator(JSeparator.HORIZONTAL, 0, 270, 414, 3);

        btNovo = panel.addJButtonNovo(95, 285, 70, 26);
        btNovo.addActionListener(this);

        btSair = panel.addJButtonSair(245, 285, 71, 26);
        btSair.addActionListener(this);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(431, 362);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void gravarCidade() throws Exception {
        if ("".equals(tfCidade.getText().trim())) {
            throw new Exception("Campo cidade vazio, entre com uma cidade");
        }
        Cidade cidade = new Cidade();
        cidade.setCodigo(Integer.parseInt(tfCodCidade.getText()));
        cidade.setCidade(tfCidade.getText());
        int codCidadeCadas = cidadeDAO.getCidadeCadastrada(tfCidade.getText());
        if (codCidadeCadas != -1) {
            JOptionPane.showMessageDialog(null, "Esta cidade ja está cadastrada", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (cidadeDAO.insertCidade(cidade)) {
                JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso", "Cidade", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void limparTelaCidade() {
        try {
            tfCodCidade.setText(Integer.toString(cidadeDAO.getProxCodCidade()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCidade.setText("");
    }

    private void consultaCidades() throws Exception {
        if (cidadeDAO.isCidadeVazio()) {
            throw new Exception("Não há cidades cadastradas");
        }
        ConsultaCidade consulta = new ConsultaCidade(cidadeDAO);
        consulta.setListener(new ListenerCidade() {

            @Override
            public void exibeCidade(Cidade cidade) {
                limparTelaCidade();
                tfCodCidade.setText(Integer.toString(cidade.getCodigo()));
                tfCidade.setText(cidade.getCidade());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void gravarCep() throws Exception {
        if (!cidadeDAO.isCidadeCadastrada(Integer.parseInt(tfCodCidade.getText()))) {
            throw new Exception("Selecione uma cidade");
        }
        if ("     -   ".equals(ftfCep.getText())) {
            throw new Exception("Campo cep vazio, entre com um cep");
        }
        Cep cep = new Cep();
        cep.setCodigo(Integer.parseInt(tfCodCep.getText()));
        cep.setCep(ftfCep.getText());
        Cidade cidade = new Cidade();
        cidade.setCodigo(Integer.parseInt(tfCodCidade.getText()));
        cep.setCidade(cidade);
        int codCepCadas = cepDAO.getCepCadastrado(ftfCep.getText());
        if (codCepCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este CEP ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (cepDAO.insertCep(cep)) {
                JOptionPane.showMessageDialog(null, "CEP cadastrado com sucesso", "CEP", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void limparTelaCep() {
        try {
            tfCodCep.setText(Integer.toString(cepDAO.getProxCodCep()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        ftfCep.setText("");
        limparTelaCidade();
    }

    private void consultaCeps() throws Exception {
        if (cepDAO.isCepVazio()) {
            throw new Exception("Não há CEPs cadastrados");
        }
        ConsultaCep consulta = new ConsultaCep(cepDAO);
        consulta.setListener(new ListenerCep() {

            @Override
            public void exibeCep(Cep cep) {
                limparTelaCep();
                tfCodCep.setText(Integer.toString(cep.getCodigo()));
                ftfCep.setText(cep.getCep());
                tfCodCidade.setText(Integer.toString(cep.getCidade().getCodigo()));
                tfCidade.setText(cep.getCidade().getCidade());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void gravarBairro() throws Exception {
        if (!cepDAO.isCepCadastrado(Integer.parseInt(tfCodCep.getText()))) {
            throw new Exception("Selecione um CEP");
        }
        if ("".equals(tfBairro.getText().trim())) {
            throw new Exception("Campo bairro vazio, entre com um bairro");
        }
        Bairro bairro = new Bairro();
        bairro.setCodigo(Integer.parseInt(tfCodBairro.getText()));
        bairro.setBairro(tfBairro.getText());
        Cep cep = new Cep();
        cep.setCodigo(Integer.parseInt(tfCodCep.getText()));
        bairro.setCep(cep);
        int codBairroCadas = bairroDAO.getBairroCadastrado(tfBairro.getText(), Integer.parseInt(tfCodCep.getText()));
        if (codBairroCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este Bairro ja está cadastrado para este CEP", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (bairroDAO.insertBairro(bairro)) {
                JOptionPane.showMessageDialog(null, "Bairro cadastrado com sucesso", "Bairro", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void limparTelaBairro() {
        try {
            tfCodBairro.setText(Integer.toString(bairroDAO.getProxCodBairro()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfBairro.setText("");
        limparTelaCep();
    }

    private void consultaBairros() throws Exception {
        if (bairroDAO.isBairroVazio()) {
            throw new Exception("Não há bairros cadastrados");
        }
        ConsultaBairro consulta = new ConsultaBairro(bairroDAO);
        consulta.setListener(new ListenerBairro() {

            @Override
            public void exibeBairro(Bairro bairro) {
                limparTelaBairro();
                tfCodBairro.setText(Integer.toString(bairro.getCodigo()));
                tfBairro.setText(bairro.getBairro());
                tfCodCep.setText(Integer.toString(bairro.getCep().getCodigo()));
                ftfCep.setText(bairro.getCep().getCep());
                tfCodCidade.setText(Integer.toString(bairro.getCep().getCidade().getCodigo()));
                tfCidade.setText(bairro.getCep().getCidade().getCidade());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void gravarEndereco() throws Exception {
        if (!bairroDAO.isBairroCadastrado(Integer.parseInt(tfCodBairro.getText()))) {
            throw new Exception("Selecione um bairro");
        }
        if ("".equals(tfEndereco.getText().trim())) {
            throw new Exception("Campo endereço vazio, entre com um endereço");
        }
        Endereco endereco = new Endereco();
        endereco.setCodigo(Integer.parseInt(tfCodEnd.getText()));
        endereco.setEndereco(tfEndereco.getText());
        Bairro bairro = new Bairro();
        bairro.setCodigo(Integer.parseInt(tfCodBairro.getText()));
        endereco.setBairro(bairro);
        int codEnderecoCadas = enderecoDAO.getEnderecoCadastrado(tfEndereco.getText(), Integer.parseInt(tfCodBairro.getText()));
        if (codEnderecoCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este Endereço ja está cadastrado para este Bairro", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (enderecoDAO.insertEndereco(endereco)) {
                JOptionPane.showMessageDialog(null, "Endereço cadastrado com sucesso", "Endereço", JOptionPane.INFORMATION_MESSAGE);
                limparTelaEndereco();
            }
        }
    }

    private void limparTelaEndereco() {
        try {
            tfCodEnd.setText(Integer.toString(enderecoDAO.getProxCodEndereco()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfEndereco.setText("");
        limparTelaBairro();
    }

    private void consultaEnderecos() throws Exception {
        if (enderecoDAO.isEnderecoVazio()) {
            throw new Exception("Não há endereços cadastrados");
        }
        ConsultaEndereco consulta = new ConsultaEndereco(enderecoDAO);
        consulta.setListener(new ListenerEndereco() {

            @Override
            public void exibeEndereco(Endereco endereco) {
                limparTelaEndereco();
                tfCodEnd.setText(Integer.toString(endereco.getCodigo()));
                tfEndereco.setText(endereco.getEndereco());
                tfCodBairro.setText(Integer.toString(endereco.getBairro().getCodigo()));
                tfBairro.setText(endereco.getBairro().getBairro());
                tfCodCep.setText(Integer.toString(endereco.getBairro().getCep().getCodigo()));
                ftfCep.setText(endereco.getBairro().getCep().getCep());
                tfCodCidade.setText(Integer.toString(endereco.getBairro().getCep().getCidade().getCodigo()));
                tfCidade.setText(endereco.getBairro().getCep().getCidade().getCidade());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOkCid) {
            try {
                gravarCidade();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOkBai) {
            try {
                gravarBairro();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOkEnd) {
            try {
                gravarEndereco();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOkCep) {
            try {
                gravarCep();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btNovo) {
            limparTelaEndereco();
        }
        if (evento.getSource() == btConsultaCid) {
            try {
                consultaCidades();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaBai) {
            try {
                consultaBairros();
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
        if (evento.getSource() == btConsultaCep) {
            try {
                consultaCeps();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btSair) {
            this.dispose();
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodCidade) {
            tfCodCidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCidade) {
            tfCidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodBairro) {
            tfCodBairro.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBairro) {
            tfBairro.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodEnd) {
            tfCodEnd.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEndereco) {
            tfEndereco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodCep) {
            tfCodCep.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCep) {
            ftfCep.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodCidade.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfCodBairro.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfCodEnd.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfCodCep.setBackground(Color.WHITE);
        ftfCep.setBackground(Color.WHITE);
    }
}

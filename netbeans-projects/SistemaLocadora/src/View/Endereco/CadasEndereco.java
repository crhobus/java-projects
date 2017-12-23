package View.Endereco;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.Endereco.EnderecoControl;
import Control.Endereco.ListenerEndereco;
import Control.Endereco.Bairro.BairroControl;
import Control.Endereco.Bairro.ListenerBairro;
import Control.Endereco.CEP.CEPControl;
import Control.Endereco.CEP.ListenerCEP;
import Control.Endereco.Cidade.CidadeControl;
import Control.Endereco.Cidade.ListenerCidade;
import Control.Endereco.Estado.EstadoControl;
import Control.Endereco.Estado.ListenerEstado;
import Model.Bairro;
import Model.CEP;
import Model.Cidade;
import Model.Endereco;
import Model.Estado;
import View.Componentes;
import View.Endereco.Bairro.ConsultaBairro;
import View.Endereco.CEP.ConsultaCEP;
import View.Endereco.Cidade.ConsultaCidade;
import View.Endereco.Estado.ConsultaEstado;

public class CadasEndereco extends Componentes {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodEstado, tfEstado, tfCodCidade, tfCidade, tfCodCEP,
            tfCodBairro, tfBairro, tfCodEndereco, tfEndereco;
    private JFormattedTextField ftfCEP;
    private JButton btConsultaEstado, btOkEstado, btConsultaCidade, btOkCidade,
            btConsultaCEP, btOkCEP, btConsultaBairro, btOkBairro,
            btConsultaEndereco, btOkEndereco, btNovo, btSair;
    private JLabel lbEstadoObrig, lbCidadeObrig, lbCEPObrig, lbBairroObrig,
            lbEnderecoObrig;
    private EstadoControl controleEstado;
    private CidadeControl controleCidade;
    private CEPControl controleCEP;
    private BairroControl controleBairro;
    private EnderecoControl controleEndereco;

    public CadasEndereco(Connection con) {
        controleEstado = new EstadoControl(con);
        controleCidade = new CidadeControl(con);
        controleCEP = new CEPControl(con);
        controleBairro = new BairroControl(con);
        controleEndereco = new EnderecoControl(con);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Endereços");
        setLayout(null);

        JPanel panel = getJPanel(10, 10, 435, 460);

        panel.add(getJLabel("Cod. Estado", 20, 20, 70, 14));

        tfCodEstado = getJTextFieldNaoEdit(20, 38, 80, 28);
        try {
            tfCodEstado.setText(Integer.toString(controleEstado.getProxCodEstado()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        panel.add(tfCodEstado);

        btConsultaEstado = getJButton("...", "Consulta estados", 106, 37, 35, 30);
        panel.add(btConsultaEstado);

        panel.add(getJLabel("Estado", 146, 20, 70, 14));

        lbEstadoObrig = getJLabelVermelho(187, 23, 10, 14);
        panel.add(lbEstadoObrig);

        tfEstado = getJTextField(146, 38, 200, 28);
        panel.add(tfEstado);

        btOkEstado = getJButtonOK("Gravar estado", 350, 37, 64, 30);
        panel.add(btOkEstado);

        panel.add(getJSeparator(0, 80, 434, 3));

        // #######################################################

        panel.add(getJLabel("Cod. Cidade", 20, 95, 70, 14));

        tfCodCidade = getJTextFieldNaoEdit(20, 113, 80, 28);
        try {
            tfCodCidade.setText(Integer.toString(controleCidade.getProxCodCidade()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        panel.add(tfCodCidade);

        btConsultaCidade = getJButton("...", "Consulta cidades", 106, 112, 35, 30);
        panel.add(btConsultaCidade);

        panel.add(getJLabel("Cidade", 146, 95, 70, 14));

        lbCidadeObrig = getJLabelVermelho(188, 98, 10, 14);
        panel.add(lbCidadeObrig);

        tfCidade = getJTextField(146, 113, 200, 28);
        panel.add(tfCidade);

        btOkCidade = getJButtonOK("Gravar cidade", 350, 112, 64, 30);
        panel.add(btOkCidade);

        panel.add(getJSeparator(0, 155, 434, 3));

        // #######################################################

        panel.add(getJLabel("Cod. CEP", 20, 170, 70, 14));

        tfCodCEP = getJTextFieldNaoEdit(20, 188, 80, 28);
        try {
            tfCodCEP.setText(Integer.toString(controleCEP.getProxCodCep()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        panel.add(tfCodCEP);

        btConsultaCEP = getJButton("...", "Consulta CEPs", 106, 187, 35, 30);
        panel.add(btConsultaCEP);

        panel.add(getJLabel("CEP", 146, 170, 70, 14));

        lbCEPObrig = getJLabelVermelho(174, 173, 10, 14);
        panel.add(lbCEPObrig);

        ftfCEP = getJFormattedTextField("#####-###", 146, 188, 200, 28);
        panel.add(ftfCEP);

        btOkCEP = getJButtonOK("Gravar CEP", 350, 187, 64, 30);
        panel.add(btOkCEP);

        panel.add(getJSeparator(0, 230, 434, 3));

        // #######################################################

        panel.add(getJLabel("Cod. Bairro", 20, 245, 70, 14));

        tfCodBairro = getJTextFieldNaoEdit(20, 263, 80, 28);
        try {
            tfCodBairro.setText(Integer.toString(controleBairro.getProxCodBairro()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        panel.add(tfCodBairro);

        btConsultaBairro = getJButton("...", "Consulta bairros", 106, 262, 35, 30);
        panel.add(btConsultaBairro);

        panel.add(getJLabel("Bairro", 146, 245, 70, 14));

        lbBairroObrig = getJLabelVermelho(182, 248, 10, 14);
        panel.add(lbBairroObrig);

        tfBairro = getJTextField(146, 263, 200, 28);
        panel.add(tfBairro);

        btOkBairro = getJButtonOK("Gravar bairro", 350, 262, 64, 30);
        panel.add(btOkBairro);

        panel.add(getJSeparator(0, 305, 434, 3));

        // #######################################################

        panel.add(getJLabel("Cod. Endereço", 20, 320, 90, 14));

        tfCodEndereco = getJTextFieldNaoEdit(20, 338, 80, 28);
        try {
            tfCodEndereco.setText(Integer.toString(controleEndereco.getProxCodEndereco()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        panel.add(tfCodEndereco);

        btConsultaEndereco = getJButton("...", "Consulta endereços", 106, 337, 35, 30);
        panel.add(btConsultaEndereco);

        panel.add(getJLabel("Endereço", 146, 320, 70, 14));

        lbEnderecoObrig = getJLabelVermelho(202, 323, 10, 14);
        panel.add(lbEnderecoObrig);

        tfEndereco = getJTextField(146, 338, 200, 28);
        panel.add(tfEndereco);

        btOkEndereco = getJButtonOK("Gravar endereço", 350, 337, 64, 30);
        panel.add(btOkEndereco);

        panel.add(getJSeparator(0, 380, 434, 3));

        btNovo = getJButtonNovo("Novo", 95, 400, 75, 30);
        panel.add(btNovo);

        btSair = getJButtonSair("Sair", 255, 400, 70, 30);
        panel.add(btSair);

        add(panel);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(463, 510);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void gravarEstado() throws Exception {
        if ("".equals(tfEstado.getText().trim())) {
            lbEstadoObrig.setText("*");
            throw new Exception("Campos * obrigatórios inválidos");
        }
        lbEstadoObrig.setText("");
        Estado estado = new Estado();
        estado.setCodigo(Integer.parseInt(tfCodEstado.getText()));
        estado.setEstado(tfEstado.getText());
        int codEstadoCadas = controleEstado.getEstadoCadastrado(tfEstado.getText());
        if (codEstadoCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este estado ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controleEstado.insertEstado(estado)) {
                JOptionPane.showMessageDialog(null, "Estado cadastrado com sucesso", "Estado", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void consultaEstados() throws Exception {
        if (controleEstado.isEstadoVazio()) {
            throw new Exception("Não há estados cadastrados");
        }
        ConsultaEstado consulta = new ConsultaEstado(controleEstado);
        consulta.setListener(new ListenerEstado() {

            @Override
            public void exibeEstado(Estado estado) {
                limparTelaEstado();
                tfCodEstado.setText(Integer.toString(estado.getCodigo()));
                tfEstado.setText(estado.getEstado());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void limparTelaEstado() {
        try {
            tfCodEstado.setText(Integer.toString(controleEstado.getProxCodEstado()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfEstado.setText("");
        lbEstadoObrig.setText("");
    }

    private void gravarCidade() throws Exception {
        if (!controleEstado.isEstadoCadastrado(Integer.parseInt(tfCodEstado.getText()))) {
            throw new Exception("Selecione um estado");
        }
        if ("".equals(tfCidade.getText().trim())) {
            lbCidadeObrig.setText("*");
            throw new Exception("Campos * obrigatórios inválidos");
        }
        lbCidadeObrig.setText("");
        Cidade cidade = new Cidade();
        cidade.setCodigo(Integer.parseInt(tfCodCidade.getText()));
        cidade.setCidade(tfCidade.getText());
        Estado estado = new Estado();
        estado.setCodigo(Integer.parseInt(tfCodEstado.getText()));
        cidade.setEstado(estado);
        int codCidadeCadas = controleCidade.getCidadeCadastrada(tfCidade.getText(), Integer.parseInt(tfCodEstado.getText()));
        if (codCidadeCadas != -1) {
            JOptionPane.showMessageDialog(null, "Ésta cidade ja está cadastrada para este estado", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controleCidade.insertCidade(cidade)) {
                JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso", "Cidade", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void consultaCidades() throws Exception {
        if (controleCidade.isCidadeVazio()) {
            throw new Exception("Não há cidades cadastradas");
        }
        ConsultaCidade consulta = new ConsultaCidade(controleCidade);
        consulta.setListener(new ListenerCidade() {

            @Override
            public void exibeCidade(Cidade cidade) {
                limparTelaCidade();
                tfCodCidade.setText(Integer.toString(cidade.getCodigo()));
                tfCidade.setText(cidade.getCidade());
                tfCodEstado.setText(Integer.toString(cidade.getEstado().getCodigo()));
                tfEstado.setText(cidade.getEstado().getEstado());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void limparTelaCidade() {
        try {
            tfCodCidade.setText(Integer.toString(controleCidade.getProxCodCidade()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCidade.setText("");
        lbCidadeObrig.setText("");
        limparTelaEstado();
    }

    private void gravarCEP() throws Exception {
        if (!controleCidade.isCidadeCadastrada(Integer.parseInt(tfCodCidade.getText()))) {
            throw new Exception("Selecione uma cidade");
        }
        if ("     -   ".equals(ftfCEP.getText())) {
            lbCEPObrig.setText("*");
            throw new Exception("Campos * obrigatórios inválidos");
        }
        lbCEPObrig.setText("");
        CEP cep = new CEP();
        cep.setCodigo(Integer.parseInt(tfCodCEP.getText()));
        Cidade cidade = new Cidade();
        cidade.setCodigo(Integer.parseInt(tfCodCidade.getText()));
        cep.setCidade(cidade);
        cep.setCep(ftfCEP.getText());
        int codCepCadas = controleCEP.getCepCadastrado(ftfCEP.getText());
        if (codCepCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este CEP ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controleCEP.insertCep(cep)) {
                JOptionPane.showMessageDialog(null, "CEP cadastrado com sucesso", "CEP", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void consultaCEPs() throws Exception {
        if (controleCEP.isCepVazio()) {
            throw new Exception("Não há CEPs cadastrado");
        }
        ConsultaCEP consulta = new ConsultaCEP(controleCEP);
        consulta.setListener(new ListenerCEP() {

            @Override
            public void exibeCep(CEP cep) {
                limparTelaCEP();
                tfCodCEP.setText(Integer.toString(cep.getCodigo()));
                ftfCEP.setText(cep.getCep());
                tfCodCidade.setText(Integer.toString(cep.getCidade().getCodigo()));
                tfCidade.setText(cep.getCidade().getCidade());
                tfCodEstado.setText(Integer.toString(cep.getCidade().getEstado().getCodigo()));
                tfEstado.setText(cep.getCidade().getEstado().getEstado());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void limparTelaCEP() {
        try {
            tfCodCEP.setText(Integer.toString(controleCEP.getProxCodCep()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        ftfCEP.setText("");
        lbCEPObrig.setText("");
        limparTelaCidade();
    }

    private void gravarBairro() throws Exception {
        if (!controleCEP.isCepCadastrado(Integer.parseInt(tfCodCEP.getText()))) {
            throw new Exception("Selecione um CEP");
        }
        if ("".equals(tfBairro.getText().trim())) {
            lbBairroObrig.setText("*");
            throw new Exception("Campos * obrigatórios inválidos");
        }
        lbBairroObrig.setText("");
        Bairro bairro = new Bairro();
        bairro.setCodigo(Integer.parseInt(tfCodBairro.getText()));
        bairro.setBairro(tfBairro.getText());
        CEP cep = new CEP();
        cep.setCodigo(Integer.parseInt(tfCodCEP.getText()));
        bairro.setCep(cep);
        int codBairroCadas = controleBairro.getBairroCadastrado(tfBairro.getText(), Integer.parseInt(tfCodCEP.getText()));
        if (codBairroCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este bairro ja está cadastrado para este CEP", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controleBairro.insertBairro(bairro)) {
                JOptionPane.showMessageDialog(null, "Bairro cadastrado com sucesso", "Bairro", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void consultaBairros() throws Exception {
        if (controleBairro.isBairroVazio()) {
            throw new Exception("Não há bairros cadastrados");
        }
        ConsultaBairro consulta = new ConsultaBairro(controleBairro);
        consulta.setListener(new ListenerBairro() {

            @Override
            public void exibeBairro(Bairro bairro) {
                limparTelaBairro();
                tfCodBairro.setText(Integer.toString(bairro.getCodigo()));
                tfBairro.setText(bairro.getBairro());
                tfCodCEP.setText(Integer.toString(bairro.getCep().getCodigo()));
                ftfCEP.setText(bairro.getCep().getCep());
                tfCodCidade.setText(Integer.toString(bairro.getCep().getCidade().getCodigo()));
                tfCidade.setText(bairro.getCep().getCidade().getCidade());
                tfCodEstado.setText(Integer.toString(bairro.getCep().getCidade().getEstado().getCodigo()));
                tfEstado.setText(bairro.getCep().getCidade().getEstado().getEstado());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void limparTelaBairro() {
        try {
            tfCodBairro.setText(Integer.toString(controleBairro.getProxCodBairro()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfBairro.setText("");
        lbBairroObrig.setText("");
        limparTelaCEP();
    }

    private void gravarEndereco() throws Exception {
        if (!controleBairro.isBairroCadastrado(Integer.parseInt(tfCodBairro.getText()))) {
            throw new Exception("Selecione um bairro");
        }
        if ("".equals(tfEndereco.getText().trim())) {
            lbEnderecoObrig.setText("*");
            throw new Exception("Campos * obrigatórios inválidos");
        }
        lbEnderecoObrig.setText("");
        Endereco endereco = new Endereco();
        endereco.setCodigo(Integer.parseInt(tfCodEndereco.getText()));
        endereco.setEndereco(tfEndereco.getText());
        Bairro bairro = new Bairro();
        bairro.setCodigo(Integer.parseInt(tfCodBairro.getText()));
        endereco.setBairro(bairro);
        int codEnderecoCadas = controleEndereco.getEnderecoCadastrado(tfEndereco.getText(), Integer.parseInt(tfCodBairro.getText()));
        if (codEnderecoCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este endereço ja está cadastrado para este bairro com este CEP", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controleEndereco.insertEndereco(endereco)) {
                JOptionPane.showMessageDialog(null, "Endereço cadastrado com sucesso", "Endereço", JOptionPane.INFORMATION_MESSAGE);
                limparTelaEndereco();
            }
        }
    }

    private void consultaEnderecos() throws Exception {
        if (controleEndereco.isEnderecoVazio()) {
            throw new Exception("Não há endereços cadastrados");
        }
        ConsultaEndereco consulta = new ConsultaEndereco(controleEndereco);
        consulta.setListener(new ListenerEndereco() {

            @Override
            public void exibeEndereco(Endereco endereco) {
                limparTelaEndereco();
                tfCodEndereco.setText(Integer.toString(endereco.getCodigo()));
                tfEndereco.setText(endereco.getEndereco());
                tfCodBairro.setText(Integer.toString(endereco.getBairro().getCodigo()));
                tfBairro.setText(endereco.getBairro().getBairro());
                tfCodCEP.setText(Integer.toString(endereco.getBairro().getCep().getCodigo()));
                ftfCEP.setText(endereco.getBairro().getCep().getCep());
                tfCodCidade.setText(Integer.toString(endereco.getBairro().getCep().getCidade().getCodigo()));
                tfCidade.setText(endereco.getBairro().getCep().getCidade().getCidade());
                tfCodEstado.setText(Integer.toString(endereco.getBairro().getCep().getCidade().getEstado().getCodigo()));
                tfEstado.setText(endereco.getBairro().getCep().getCidade().getEstado().getEstado());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void limparTelaEndereco() {
        try {
            tfCodEndereco.setText(Integer.toString(controleEndereco.getProxCodEndereco()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfEndereco.setText("");
        lbEnderecoObrig.setText("");
        limparTelaBairro();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOkEstado) {
            try {
                gravarEstado();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOkCidade) {
            try {
                gravarCidade();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOkBairro) {
            try {
                gravarBairro();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOkEndereco) {
            try {
                gravarEndereco();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOkCEP) {
            try {
                gravarCEP();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btNovo) {
            limparTelaEndereco();
        }
        if (evento.getSource() == btConsultaEstado) {
            try {
                consultaEstados();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaCidade) {
            try {
                consultaCidades();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaBairro) {
            try {
                consultaBairros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaEndereco) {
            try {
                consultaEnderecos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaCEP) {
            try {
                consultaCEPs();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btSair) {
            this.dispose();
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodEstado) {
            tfCodEstado.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEstado) {
            tfEstado.setBackground(Color.YELLOW);
        }
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
        if (evento.getSource() == tfCodEndereco) {
            tfCodEndereco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEndereco) {
            tfEndereco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodCEP) {
            tfCodCEP.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCEP) {
            ftfCEP.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodEstado.setBackground(Color.WHITE);
        tfEstado.setBackground(Color.WHITE);
        tfCodCidade.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfCodBairro.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfCodEndereco.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfCodCEP.setBackground(Color.WHITE);
        ftfCEP.setBackground(Color.WHITE);
    }
}

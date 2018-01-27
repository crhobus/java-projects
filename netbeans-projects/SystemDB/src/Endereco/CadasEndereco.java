package Endereco;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Cursor;
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

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Endereco.Bairro.BairroControl;
import Endereco.Bairro.ConsultaBairro;
import Endereco.Bairro.ListenerBairro;
import Endereco.CEP.CepControl;
import Endereco.CEP.ConsultaCep;
import Endereco.CEP.ListenerCep;
import Endereco.Cidade.CidadeControl;
import Endereco.Cidade.ConsultaCidade;
import Endereco.Cidade.ListenerCidade;
import Endereco.Estado.ConsultaEstado;
import Endereco.Estado.EstadoControl;
import Endereco.Estado.ListenerEstado;
import Endereco.Pais.ConsultaPaises;
import Endereco.Pais.ListenerPais;
import Endereco.Pais.PaisControl;
import Endereco.Regiao.ConsultaRegiao;
import Endereco.Regiao.ListenerRegiao;
import Endereco.Regiao.RegiaoControl;
import Modelo.Bairro;
import Modelo.Cep;
import Modelo.Cidade;
import Modelo.Endereco;
import Modelo.Estado;
import Modelo.Pais;
import Modelo.Regiao;

public class CadasEndereco extends JDialog implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodPais, tfPais, tfCodRegiao, tfRegiao, tfCodEstado,
            tfEstado, tfCodCidade, tfCidade, tfCodBairro, tfBairro,
            tfCodEndereco, tfEndereco, tfCodCep;
    private JFormattedTextField ftfCep;
    private JButton btConsultaPais, btOkPais, btConsultaRegiao, btOkRegiao,
            btConsultaEstado, btOkEstado, btConsultaCidade, btOkCidade,
            btConsultaBairro, btOkBairro, btConsultaEndereco, btOkEndereco,
            btConsultaCep, btOkCep, btNovo, btSair;
    private JLabel lbPaisObrig, lbRegiaoObrig, lbEstadoObrig, lbCidadeObrig,
            lbBairroObrig, lbEnderecoObrig, lbCepObrig;
    private PaisControl controlePais;
    private RegiaoControl controleRegiao;
    private EstadoControl controleEstado;
    private CidadeControl controleCidade;
    private CepControl controleCep;
    private BairroControl controleBairro;
    private EnderecoControl controleEndereco;

    public CadasEndereco(Connection con) {
        controlePais = new PaisControl(con);
        controleRegiao = new RegiaoControl(con);
        controleEstado = new EstadoControl(con);
        controleCidade = new CidadeControl(con);
        controleCep = new CepControl(con);
        controleBairro = new BairroControl(con);
        controleEndereco = new EnderecoControl(con);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Endereços");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 415, 520);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        add(panel);

        JLabel lbCodPais = new JLabel("Cod. Pais");
        lbCodPais.setBounds(20, 20, 60, 14);
        panel.add(lbCodPais);

        tfCodPais = new JTextField();
        tfCodPais.setBounds(20, 38, 80, 20);
        try {
            tfCodPais.setText(Integer.toString(controlePais.getProxCodPais()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodPais.setEditable(false);
        tfCodPais.setBackground(Color.WHITE);
        tfCodPais.addFocusListener(this);
        panel.add(tfCodPais);

        btConsultaPais = new JButton("...");
        btConsultaPais.setBounds(106, 35, 31, 26);
        btConsultaPais.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaPais.setToolTipText("Consulta Países");
        btConsultaPais.addActionListener(this);
        panel.add(btConsultaPais);

        JLabel lbPais = new JLabel("Pais");
        lbPais.setBounds(143, 20, 60, 14);
        panel.add(lbPais);

        lbPaisObrig = new JLabel("");
        lbPaisObrig.setBounds(165, 23, 10, 14);
        lbPaisObrig.setForeground(Color.RED);
        panel.add(lbPaisObrig);

        tfPais = new JTextField();
        tfPais.setBounds(143, 38, 200, 20);
        tfPais.addFocusListener(this);
        panel.add(tfPais);

        Icon icOk = new ImageIcon("OK.png");
        btOkPais = new JButton("OK", icOk);
        btOkPais.setBounds(350, 35, 50, 26);
        btOkPais.setMargin(new Insets(0, 0, 0, 0));
        btOkPais.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOkPais.setToolTipText("Confirma Operação");
        btOkPais.addActionListener(this);
        panel.add(btOkPais);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(0, 75, 415, 3);
        panel.add(separator1);

        JLabel lbCodRegiao = new JLabel("Cod. Região");
        lbCodRegiao.setBounds(20, 85, 60, 14);
        panel.add(lbCodRegiao);

        tfCodRegiao = new JTextField();
        tfCodRegiao.setBounds(20, 103, 80, 20);
        try {
            tfCodRegiao.setText(Integer.toString(controleRegiao.getProxCodRegiao()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodRegiao.setEditable(false);
        tfCodRegiao.setBackground(Color.WHITE);
        tfCodRegiao.addFocusListener(this);
        panel.add(tfCodRegiao);

        btConsultaRegiao = new JButton("...");
        btConsultaRegiao.setBounds(106, 100, 31, 26);
        btConsultaRegiao.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaRegiao.setToolTipText("Consulta Regiões");
        btConsultaRegiao.addActionListener(this);
        panel.add(btConsultaRegiao);

        JLabel lbRegiao = new JLabel("Região");
        lbRegiao.setBounds(143, 85, 90, 14);
        panel.add(lbRegiao);

        lbRegiaoObrig = new JLabel("");
        lbRegiaoObrig.setBounds(178, 88, 10, 14);
        lbRegiaoObrig.setForeground(Color.RED);
        panel.add(lbRegiaoObrig);

        tfRegiao = new JTextField();
        tfRegiao.setBounds(143, 103, 200, 20);
        tfRegiao.addFocusListener(this);
        panel.add(tfRegiao);

        btOkRegiao = new JButton("OK", icOk);
        btOkRegiao.setBounds(350, 100, 50, 26);
        btOkRegiao.setMargin(new Insets(0, 0, 0, 0));
        btOkRegiao.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOkRegiao.setToolTipText("Confirma Operação");
        btOkRegiao.addActionListener(this);
        panel.add(btOkRegiao);

        JSeparator separator2 = new JSeparator();
        separator2.setBounds(0, 140, 415, 3);
        panel.add(separator2);

        JLabel lbCodEstado = new JLabel("Cod. Estado");
        lbCodEstado.setBounds(20, 150, 60, 14);
        panel.add(lbCodEstado);

        tfCodEstado = new JTextField();
        tfCodEstado.setBounds(20, 168, 80, 20);
        try {
            tfCodEstado.setText(Integer.toString(controleEstado.getProxCodEstado()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodEstado.setEditable(false);
        tfCodEstado.setBackground(Color.WHITE);
        tfCodEstado.addFocusListener(this);
        panel.add(tfCodEstado);

        btConsultaEstado = new JButton("...");
        btConsultaEstado.setBounds(106, 165, 31, 26);
        btConsultaEstado.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaEstado.setToolTipText("Consulta Estados");
        btConsultaEstado.addActionListener(this);
        panel.add(btConsultaEstado);

        JLabel lbEstado = new JLabel("Estado");
        lbEstado.setBounds(143, 150, 90, 14);
        panel.add(lbEstado);

        lbEstadoObrig = new JLabel("");
        lbEstadoObrig.setBounds(178, 153, 10, 14);
        lbEstadoObrig.setForeground(Color.RED);
        panel.add(lbEstadoObrig);

        tfEstado = new JTextField();
        tfEstado.setBounds(143, 168, 200, 20);
        tfEstado.addFocusListener(this);
        panel.add(tfEstado);

        btOkEstado = new JButton("OK", icOk);
        btOkEstado.setBounds(350, 165, 50, 26);
        btOkEstado.setMargin(new Insets(0, 0, 0, 0));
        btOkEstado.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOkEstado.setToolTipText("Confirma Operação");
        btOkEstado.addActionListener(this);
        panel.add(btOkEstado);

        JSeparator separator3 = new JSeparator();
        separator3.setBounds(0, 205, 415, 3);
        panel.add(separator3);

        JLabel lbCodCidade = new JLabel("Cod. Cidade");
        lbCodCidade.setBounds(20, 215, 60, 14);
        panel.add(lbCodCidade);

        tfCodCidade = new JTextField();
        tfCodCidade.setBounds(20, 233, 80, 20);
        try {
            tfCodCidade.setText(Integer.toString(controleCidade.getProxCodCidade()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodCidade.setEditable(false);
        tfCodCidade.setBackground(Color.WHITE);
        tfCodCidade.addFocusListener(this);
        panel.add(tfCodCidade);

        btConsultaCidade = new JButton("...");
        btConsultaCidade.setBounds(106, 230, 31, 26);
        btConsultaCidade.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaCidade.setToolTipText("Consulta Cidades");
        btConsultaCidade.addActionListener(this);
        panel.add(btConsultaCidade);

        JLabel lbCidade = new JLabel("Cidade");
        lbCidade.setBounds(143, 215, 90, 14);
        panel.add(lbCidade);

        lbCidadeObrig = new JLabel("");
        lbCidadeObrig.setBounds(178, 218, 10, 14);
        lbCidadeObrig.setForeground(Color.RED);
        panel.add(lbCidadeObrig);

        tfCidade = new JTextField();
        tfCidade.setBounds(143, 233, 200, 20);
        tfCidade.addFocusListener(this);
        panel.add(tfCidade);

        btOkCidade = new JButton("OK", icOk);
        btOkCidade.setBounds(350, 230, 50, 26);
        btOkCidade.setMargin(new Insets(0, 0, 0, 0));
        btOkCidade.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOkCidade.setToolTipText("Confirma Operação");
        btOkCidade.addActionListener(this);
        panel.add(btOkCidade);

        JSeparator separator4 = new JSeparator();
        separator4.setBounds(0, 270, 415, 3);
        panel.add(separator4);

        JLabel lbCodCep = new JLabel("Cod. CEP");
        lbCodCep.setBounds(20, 280, 60, 14);
        panel.add(lbCodCep);

        tfCodCep = new JTextField();
        tfCodCep.setBounds(20, 298, 80, 20);
        try {
            tfCodCep.setText(Integer.toString(controleCep.getProxCodCep()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodCep.setEditable(false);
        tfCodCep.setBackground(Color.WHITE);
        tfCodCep.addFocusListener(this);
        panel.add(tfCodCep);

        btConsultaCep = new JButton("...");
        btConsultaCep.setBounds(106, 295, 31, 26);
        btConsultaCep.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaCep.setToolTipText("Consulta CEP");
        btConsultaCep.addActionListener(this);
        panel.add(btConsultaCep);

        JLabel lbCep = new JLabel("CEP");
        lbCep.setBounds(143, 280, 90, 14);
        panel.add(lbCep);

        lbCepObrig = new JLabel("");
        lbCepObrig.setBounds(165, 283, 10, 14);
        lbCepObrig.setForeground(Color.RED);
        panel.add(lbCepObrig);

        try {
            ftfCep = new JFormattedTextField(new MaskFormatter("#####-###"));
        } catch (ParseException ex) {
        }
        ftfCep.setBounds(143, 298, 200, 20);
        ftfCep.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCep.addFocusListener(this);
        panel.add(ftfCep);

        btOkCep = new JButton("OK", icOk);
        btOkCep.setBounds(350, 295, 50, 26);
        btOkCep.setMargin(new Insets(0, 0, 0, 0));
        btOkCep.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOkCep.setToolTipText("Confirma Operação");
        btOkCep.addActionListener(this);
        panel.add(btOkCep);

        JSeparator separator5 = new JSeparator();
        separator5.setBounds(0, 335, 415, 3);
        panel.add(separator5);

        JLabel lbCodBairro = new JLabel("Cod. Bairro");
        lbCodBairro.setBounds(20, 345, 80, 14);
        panel.add(lbCodBairro);

        tfCodBairro = new JTextField();
        tfCodBairro.setBounds(20, 363, 80, 20);
        try {
            tfCodBairro.setText(Integer.toString(controleBairro.getProxCodBairro()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodBairro.setEditable(false);
        tfCodBairro.setBackground(Color.WHITE);
        tfCodBairro.addFocusListener(this);
        panel.add(tfCodBairro);

        btConsultaBairro = new JButton("...");
        btConsultaBairro.setBounds(106, 360, 31, 26);
        btConsultaBairro.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaBairro.setToolTipText("Consulta Bairros");
        btConsultaBairro.addActionListener(this);
        panel.add(btConsultaBairro);

        JLabel lbBairro = new JLabel("Bairro");
        lbBairro.setBounds(143, 345, 90, 14);
        panel.add(lbBairro);

        lbBairroObrig = new JLabel("");
        lbBairroObrig.setBounds(173, 348, 10, 14);
        lbBairroObrig.setForeground(Color.RED);
        panel.add(lbBairroObrig);

        tfBairro = new JTextField();
        tfBairro.setBounds(143, 363, 200, 20);
        tfBairro.addFocusListener(this);
        panel.add(tfBairro);

        btOkBairro = new JButton("OK", icOk);
        btOkBairro.setBounds(350, 360, 50, 26);
        btOkBairro.setMargin(new Insets(0, 0, 0, 0));
        btOkBairro.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOkBairro.setToolTipText("Confirma Operação");
        btOkBairro.addActionListener(this);
        panel.add(btOkBairro);

        JSeparator separator6 = new JSeparator();
        separator6.setBounds(0, 400, 415, 3);
        panel.add(separator6);

        JLabel lbCodEndereco = new JLabel("Cod. Endereço");
        lbCodEndereco.setBounds(20, 410, 80, 14);
        panel.add(lbCodEndereco);

        tfCodEndereco = new JTextField();
        tfCodEndereco.setBounds(20, 428, 80, 20);
        try {
            tfCodEndereco.setText(Integer.toString(controleEndereco.getProxCodEndereco()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodEndereco.setEditable(false);
        tfCodEndereco.setBackground(Color.WHITE);
        tfCodEndereco.addFocusListener(this);
        panel.add(tfCodEndereco);

        btConsultaEndereco = new JButton("...");
        btConsultaEndereco.setBounds(106, 425, 31, 26);
        btConsultaEndereco.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaEndereco.setToolTipText("Consulta Endereços");
        btConsultaEndereco.addActionListener(this);
        panel.add(btConsultaEndereco);

        JLabel lbEndereco = new JLabel("Endereço");
        lbEndereco.setBounds(143, 410, 90, 14);
        panel.add(lbEndereco);

        lbEnderecoObrig = new JLabel("");
        lbEnderecoObrig.setBounds(165, 413, 10, 14);
        lbEnderecoObrig.setForeground(Color.RED);
        panel.add(lbEnderecoObrig);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(143, 428, 200, 20);
        tfEndereco.addFocusListener(this);
        panel.add(tfEndereco);

        btOkEndereco = new JButton("OK", icOk);
        btOkEndereco.setBounds(350, 425, 50, 26);
        btOkEndereco.setMargin(new Insets(0, 0, 0, 0));
        btOkEndereco.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOkEndereco.setToolTipText("Confirma Operação");
        btOkEndereco.addActionListener(this);
        panel.add(btOkEndereco);

        JSeparator separator7 = new JSeparator();
        separator7.setBounds(0, 465, 415, 3);
        panel.add(separator7);

        Icon icCancelar = new ImageIcon("Novo.png");
        btNovo = new JButton("Novo", icCancelar);
        btNovo.setBounds(95, 480, 70, 26);
        btNovo.setMargin(new Insets(0, 0, 0, 0));
        btNovo.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btNovo.setToolTipText("Novo");
        btNovo.addActionListener(this);
        panel.add(btNovo);

        Icon icSair = new ImageIcon("Sair.png");
        btSair = new JButton("Sair", icSair);
        btSair.setBounds(245, 480, 70, 26);
        btSair.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btSair.setToolTipText("Fechar");
        btSair.addActionListener(this);
        panel.add(btSair);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(443, 570);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void gravarPais() throws Exception {
        if ("".equals(tfPais.getText().trim())) {
            lbPaisObrig.setText("*");
            throw new Exception("Campos * obrigatórios inválidos");
        }
        lbPaisObrig.setText("");
        Pais pais = new Pais();
        pais.setCodPais(Integer.parseInt(tfCodPais.getText()));
        pais.setPais(tfPais.getText());
        int codPaisCadas = controlePais.getPaisCadastrado(tfPais.getText());
        if (codPaisCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este país ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controlePais.insertPais(pais)) {
                JOptionPane.showMessageDialog(null, "Pais cadastrado com sucesso", "Pais", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void limparTelaPais() {
        try {
            tfCodPais.setText(Integer.toString(controlePais.getProxCodPais()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfPais.setText("");
        lbPaisObrig.setText("");
    }

    private void consultaPaises() throws Exception {
        if (controlePais.isPaisVazio()) {
            throw new Exception("Não há países cadastrados");
        }
        ConsultaPaises consulta = new ConsultaPaises(controlePais);
        consulta.setListener(new ListenerPais() {

            @Override
            public void exibePais(Pais pais) {
                limparTelaPais();
                tfCodPais.setText(Integer.toString(pais.getCodPais()));
                tfPais.setText(pais.getPais());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void gravarRegiao() throws Exception {
        if (!controlePais.isPaisCadastrado(Integer.parseInt(tfCodPais.getText()))) {
            throw new Exception("Selecione um país");
        }
        if ("".equals(tfRegiao.getText().trim())) {
            lbRegiaoObrig.setText("*");
            throw new Exception("Campos * obrigatórios inválidos");
        }
        lbRegiaoObrig.setText("");
        Regiao regiao = new Regiao();
        regiao.setCodRegiao(Integer.parseInt(tfCodRegiao.getText()));
        regiao.setCodPais(Integer.parseInt(tfCodPais.getText()));
        regiao.setRegiao(tfRegiao.getText());
        int codRegiaoCadas = controleRegiao.getRegiaoCadastrada(tfRegiao.getText(), Integer.parseInt(tfCodPais.getText()));
        if (codRegiaoCadas != -1) {
            JOptionPane.showMessageDialog(null, "Ésta região ja está cadastrada para este país", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controleRegiao.insertRegiao(regiao)) {
                JOptionPane.showMessageDialog(null, "Região cadastrada com sucesso", "Região", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void limparTelaRegiao() {
        try {
            tfCodRegiao.setText(Integer.toString(controleRegiao.getProxCodRegiao()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfRegiao.setText("");
        lbRegiaoObrig.setText("");
        limparTelaPais();
    }

    private void consultaRegioes() throws Exception {
        if (controleRegiao.isRegiaoVazio()) {
            throw new Exception("Não há regiões cadastradas");
        }
        ConsultaRegiao consulta = new ConsultaRegiao(controleRegiao);
        consulta.setListener(new ListenerRegiao() {

            @Override
            public void exibeRegiao(Regiao regiao) {
                limparTelaRegiao();
                tfCodRegiao.setText(Integer.toString(regiao.getCodRegiao()));
                tfRegiao.setText(regiao.getRegiao());
                tfCodPais.setText(Integer.toString(regiao.getCodPais()));
                tfPais.setText(regiao.getPais());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void gravarEstado() throws Exception {
        if (!controleRegiao.isRegiaoCadastrado(Integer.parseInt(tfCodRegiao.getText()))) {
            throw new Exception("Selecione uma região");
        }
        if ("".equals(tfEstado.getText().trim())) {
            lbEstadoObrig.setText("*");
            throw new Exception("Campos * obrigatórios inválidos");
        }
        lbEstadoObrig.setText("");
        Estado estado = new Estado();
        estado.setCodEstado(Integer.parseInt(tfCodEstado.getText()));
        estado.setCodRegiao(Integer.parseInt(tfCodRegiao.getText()));
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

    private void limparTelaEstado() {
        try {
            tfCodEstado.setText(Integer.toString(controleEstado.getProxCodEstado()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfEstado.setText("");
        lbEstadoObrig.setText("");
        limparTelaRegiao();
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
                tfCodEstado.setText(Integer.toString(estado.getCodEstado()));
                tfEstado.setText(estado.getEstado());
                tfCodRegiao.setText(Integer.toString(estado.getCodRegiao()));
                tfRegiao.setText(estado.getRegiao());
                tfCodPais.setText(Integer.toString(estado.getCodPais()));
                tfPais.setText(estado.getPais());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
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
        cidade.setCodCidade(Integer.parseInt(tfCodCidade.getText()));
        cidade.setCodEstado(Integer.parseInt(tfCodEstado.getText()));
        cidade.setCidade(tfCidade.getText());
        int codCidadeCadas = controleCidade.getCidadeCadastrada(tfCidade.getText(), Integer.parseInt(tfCodEstado.getText()));
        if (codCidadeCadas != -1) {
            JOptionPane.showMessageDialog(null, "Ésta cidade ja está cadastrada para este estado", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controleCidade.insertCidade(cidade)) {
                JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso", "Cidade", JOptionPane.INFORMATION_MESSAGE);
            }
        }
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

    private void consultaCidades() throws Exception {
        if (controleCidade.isCidadeVazio()) {
            throw new Exception("Não há cidades cadastradas");
        }
        ConsultaCidade consulta = new ConsultaCidade(controleCidade);
        consulta.setListener(new ListenerCidade() {

            @Override
            public void exibeCidade(Cidade cidade) {
                limparTelaCidade();
                tfCodCidade.setText(Integer.toString(cidade.getCodCidade()));
                tfCidade.setText(cidade.getCidade());
                tfCodEstado.setText(Integer.toString(cidade.getCodEstado()));
                tfEstado.setText(cidade.getEstado());
                tfCodRegiao.setText(Integer.toString(cidade.getCodRegiao()));
                tfRegiao.setText(cidade.getRegiao());
                tfCodPais.setText(Integer.toString(cidade.getCodPais()));
                tfPais.setText(cidade.getPais());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void gravarCep() throws Exception {
        if (!controleCidade.isCidadeCadastrada(Integer.parseInt(tfCodCidade.getText()))) {
            throw new Exception("Selecione uma cidade");
        }
        if ("     -   ".equals(ftfCep.getText())) {
            lbCepObrig.setText("*");
            throw new Exception("Campos * obrigatórios inválidos");
        }
        lbCepObrig.setText("");
        Cep cep = new Cep();
        cep.setCodCep(Integer.parseInt(tfCodCep.getText()));
        cep.setCodCidade(Integer.parseInt(tfCodCidade.getText()));
        cep.setCep(ftfCep.getText());
        int codCepCadas = controleCep.getCepCadastrado(ftfCep.getText());
        if (codCepCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este CEP ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controleCep.insertCep(cep)) {
                JOptionPane.showMessageDialog(null, "CEP cadastrado com sucesso", "CEP", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void limparTelaCep() {
        try {
            tfCodCep.setText(Integer.toString(controleCep.getProxCodCep()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        ftfCep.setText("");
        lbCepObrig.setText("");
        limparTelaCidade();
    }

    private void consultaCeps() throws Exception {
        if (controleCep.isCepVazio()) {
            throw new Exception("Não há CEP cadastrado");
        }
        ConsultaCep consulta = new ConsultaCep(controleCep);
        consulta.setListener(new ListenerCep() {

            @Override
            public void exibeCep(Cep cep) {
                limparTelaCep();
                tfCodCep.setText(Integer.toString(cep.getCodCep()));
                ftfCep.setText(cep.getCep());
                tfCodCidade.setText(Integer.toString(cep.getCodCidade()));
                tfCidade.setText(cep.getCidade());
                tfCodEstado.setText(Integer.toString(cep.getCodEstado()));
                tfEstado.setText(cep.getEstado());
                tfCodRegiao.setText(Integer.toString(cep.getCodRegiao()));
                tfRegiao.setText(cep.getRegiao());
                tfCodPais.setText(Integer.toString(cep.getCodPais()));
                tfPais.setText(cep.getPais());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void gravarBairro() throws Exception {
        if (!controleCep.isCepCadastrado(Integer.parseInt(tfCodCep.getText()))) {
            throw new Exception("Selecione um CEP");
        }
        if ("".equals(tfBairro.getText().trim())) {
            lbBairroObrig.setText("*");
            throw new Exception("Campos * obrigatórios inválidos");
        }
        lbBairroObrig.setText("");
        Bairro bairro = new Bairro();
        bairro.setCodBairro(Integer.parseInt(tfCodBairro.getText()));
        bairro.setCodCep(Integer.parseInt(tfCodCep.getText()));
        bairro.setBairro(tfBairro.getText());
        int codBairroCadas = controleBairro.getBairroCadastrado(tfBairro.getText(), Integer.parseInt(tfCodCep.getText()));
        if (codBairroCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este Bairro ja está cadastrado para este CEP", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controleBairro.insertBairro(bairro)) {
                JOptionPane.showMessageDialog(null, "Bairro cadastrado com sucesso", "Bairro", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void limparTelaBairro() {
        try {
            tfCodBairro.setText(Integer.toString(controleBairro.getProxCodBairro()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfBairro.setText("");
        lbBairroObrig.setText("");
        limparTelaCep();
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
                tfCodBairro.setText(Integer.toString(bairro.getCodBairro()));
                tfBairro.setText(bairro.getBairro());
                tfCodCep.setText(Integer.toString(bairro.getCodCep()));
                ftfCep.setText(bairro.getCep());
                tfCodCidade.setText(Integer.toString(bairro.getCodCidade()));
                tfCidade.setText(bairro.getCidade());
                tfCodEstado.setText(Integer.toString(bairro.getCodEstado()));
                tfEstado.setText(bairro.getEstado());
                tfCodRegiao.setText(Integer.toString(bairro.getCodRegiao()));
                tfRegiao.setText(bairro.getRegiao());
                tfCodPais.setText(Integer.toString(bairro.getCodPais()));
                tfPais.setText(bairro.getPais());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
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
        endereco.setCodEndereco(Integer.parseInt(tfCodEndereco.getText()));
        endereco.setCodBairro(Integer.parseInt(tfCodBairro.getText()));
        endereco.setEndereco(tfEndereco.getText());
        int codEnderecoCadas = controleEndereco.getEnderecoCadastrado(tfEndereco.getText(), Integer.parseInt(tfCodBairro.getText()));
        if (codEnderecoCadas != -1) {
            JOptionPane.showMessageDialog(null, "Este Endereço ja está cadastrado para este Bairro", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (controleEndereco.insertEndereco(endereco)) {
                JOptionPane.showMessageDialog(null, "Endereço cadastrado com sucesso", "Endereço", JOptionPane.INFORMATION_MESSAGE);
                limparTelaEndereco();
            }
        }
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

    private void consultaEnderecos() throws Exception {
        if (controleEndereco.isEnderecoVazio()) {
            throw new Exception("Não há endereços cadastrados");
        }
        ConsultaEndereco consulta = new ConsultaEndereco(controleEndereco);
        consulta.setListener(new ListenerEndereco() {

            @Override
            public void exibeEndereco(Endereco endereco) {
                limparTelaEndereco();
                tfCodEndereco.setText(Integer.toString(endereco.getCodEndereco()));
                tfEndereco.setText(endereco.getEndereco());
                tfCodBairro.setText(Integer.toString(endereco.getCodBairro()));
                tfBairro.setText(endereco.getBairro());
                tfCodCep.setText(Integer.toString(endereco.getCodCep()));
                ftfCep.setText(endereco.getCep());
                tfCodCidade.setText(Integer.toString(endereco.getCodCidade()));
                tfCidade.setText(endereco.getCidade());
                tfCodEstado.setText(Integer.toString(endereco.getCodEstado()));
                tfEstado.setText(endereco.getEstado());
                tfCodRegiao.setText(Integer.toString(endereco.getCodRegiao()));
                tfRegiao.setText(endereco.getRegiao());
                tfCodPais.setText(Integer.toString(endereco.getCodPais()));
                tfPais.setText(endereco.getPais());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void validaJFormattedTextField(JFormattedTextField ftf) {
        if (ftf.getText().split(" ").length > 1 || ftf.getText().trim().length() < ftf.getText().length()) {
            ftf.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOkPais) {
            try {
                gravarPais();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOkRegiao) {
            try {
                gravarRegiao();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
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
        if (evento.getSource() == btOkCep) {
            try {
                gravarCep();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btNovo) {
            limparTelaEndereco();
        }
        if (evento.getSource() == btConsultaPais) {
            try {
                consultaPaises();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaRegiao) {
            try {
                consultaRegioes();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
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
        if (evento.getSource() == btConsultaCep) {
            try {
                consultaCeps();
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
        if (evento.getSource() == tfCodPais) {
            tfCodPais.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPais) {
            tfPais.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodRegiao) {
            tfCodRegiao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfRegiao) {
            tfRegiao.setBackground(Color.YELLOW);
        }
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
        if (evento.getSource() == tfCodCep) {
            tfCodCep.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCep) {
            ftfCep.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodPais.setBackground(Color.WHITE);
        tfPais.setBackground(Color.WHITE);
        tfCodRegiao.setBackground(Color.WHITE);
        tfRegiao.setBackground(Color.WHITE);
        tfCodEstado.setBackground(Color.WHITE);
        tfEstado.setBackground(Color.WHITE);
        tfCodCidade.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfCodBairro.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfCodEndereco.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfCodCep.setBackground(Color.WHITE);
        if (evento.getSource() == ftfCep) {
            ftfCep.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfCep);
        }
    }
}

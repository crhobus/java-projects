package Transportadora;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Endereco.ConsultaEndereco;
import Endereco.EnderecoControl;
import Endereco.ListenerEndereco;
import Modelo.Endereco;
import Modelo.Transportadora;
import Principal.CamposInt;
import Principal.Controle;
import Seguranca.Seguranca;

public class CadasTransportadora extends Controle implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome,
            tfCodEndereco, tfEndereco, tfBairro, tfNumero, tfCep, tfCidade,
            tfEstado, tfRegiao, tfReferencia, tfEmail, tfMsn, tfFrete,
            tfDescricao;
    private JFormattedTextField ftfCnpj, ftfIe, ftfInscMun, ftfFone, ftfFax;
    private JButton btConsulta, btConsultaEndereco, btOk, btCancelar,
            btExcluir, btSair;
    private JLabel lbNomeObrig, lbCnpjObrig, lbIeObrig, lbInscMunObrig,
            lbCodEnderecoObrig, lbNumeroObrig, lbFoneObrig, lbFreteObrig;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;
    private SimpleDateFormat formatDateHora;
    private TransportadoraControl controleTrans;
    private EnderecoControl controleEndereco;
    private Seguranca seguranca;

    public CadasTransportadora(Connection con, Seguranca seguranca) {
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        controleTrans = new TransportadoraControl(con);
        controleEndereco = new EnderecoControl(con);
        this.seguranca = seguranca;
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Transportadoras");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 672, 304);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        add(panel);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 20, 60, 14);
        panel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controleTrans.getProxCodTrans()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        tfCodigo.addFocusListener(this);
        panel.add(tfCodigo);

        btConsulta = new JButton("...");
        btConsulta.setBounds(106, 35, 31, 26);
        btConsulta.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsulta.setToolTipText("Consulta Transportadoras");
        btConsulta.addActionListener(this);
        panel.add(btConsulta);

        JLabel lbDataCdas = new JLabel("Cadastro em");
        lbDataCdas.setBounds(143, 20, 90, 14);
        panel.add(lbDataCdas);

        tfDataCadas = new JTextField();
        tfDataCadas.setBounds(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.setEditable(false);
        tfDataCadas.setBackground(Color.WHITE);
        tfDataCadas.addFocusListener(this);
        panel.add(tfDataCadas);

        JLabel lbUltAlteracao = new JLabel("Última Alteração");
        lbUltAlteracao.setBounds(270, 20, 100, 14);
        panel.add(lbUltAlteracao);

        tfUltAlteracao = new JTextField();
        tfUltAlteracao.setBounds(270, 38, 120, 20);
        tfUltAlteracao.setEditable(false);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfUltAlteracao.addFocusListener(this);
        panel.add(tfUltAlteracao);

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(400, 20, 50, 14);
        panel.add(lbNome);

        lbNomeObrig = new JLabel("");
        lbNomeObrig.setBounds(430, 23, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel.add(lbNomeObrig);

        tfNome = new JTextField();
        tfNome.setBounds(400, 38, 250, 20);
        tfNome.addFocusListener(this);
        panel.add(tfNome);

        JLabel lbCnpj = new JLabel("CNPJ");
        lbCnpj.setBounds(20, 63, 80, 14);
        panel.add(lbCnpj);

        lbCnpjObrig = new JLabel("");
        lbCnpjObrig.setBounds(48, 66, 10, 14);
        lbCnpjObrig.setForeground(Color.RED);
        panel.add(lbCnpjObrig);

        try {
            ftfCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
        } catch (ParseException ex) {
        }
        ftfCnpj.setBounds(20, 81, 120, 20);
        ftfCnpj.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCnpj.addFocusListener(this);
        panel.add(ftfCnpj);

        JLabel lbIe = new JLabel("IE");
        lbIe.setBounds(150, 63, 90, 14);
        panel.add(lbIe);

        lbIeObrig = new JLabel("");
        lbIeObrig.setBounds(164, 66, 10, 14);
        lbIeObrig.setForeground(Color.RED);
        panel.add(lbIeObrig);

        try {
            ftfIe = new JFormattedTextField(new MaskFormatter("###.###.###"));
        } catch (ParseException ex) {
        }
        ftfIe.setBounds(150, 81, 100, 20);
        ftfIe.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfIe.addFocusListener(this);
        panel.add(ftfIe);

        JLabel lbInscMun = new JLabel("Inscrição Municipal");
        lbInscMun.setBounds(260, 63, 110, 14);
        panel.add(lbInscMun);

        lbInscMunObrig = new JLabel("");
        lbInscMunObrig.setBounds(353, 66, 10, 14);
        lbInscMunObrig.setForeground(Color.RED);
        panel.add(lbInscMunObrig);

        try {
            ftfInscMun = new JFormattedTextField(new MaskFormatter("##.##.##"));
        } catch (ParseException ex) {
        }
        ftfInscMun.setBounds(260, 81, 100, 20);
        ftfInscMun.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfInscMun.addFocusListener(this);
        panel.add(ftfInscMun);

        JLabel lbCodEndereco = new JLabel("Cod. Endereço");
        lbCodEndereco.setBounds(370, 63, 90, 14);
        panel.add(lbCodEndereco);

        lbCodEnderecoObrig = new JLabel("");
        lbCodEnderecoObrig.setBounds(444, 66, 10, 14);
        lbCodEnderecoObrig.setForeground(Color.RED);
        panel.add(lbCodEnderecoObrig);

        tfCodEndereco = new JTextField();
        tfCodEndereco.setBounds(370, 81, 80, 20);
        tfCodEndereco.setEditable(false);
        tfCodEndereco.setBackground(Color.WHITE);
        tfCodEndereco.addFocusListener(this);
        panel.add(tfCodEndereco);

        btConsultaEndereco = new JButton("...");
        btConsultaEndereco.setBounds(455, 78, 31, 26);
        btConsultaEndereco.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaEndereco.setToolTipText("Consulta Endereço");
        btConsultaEndereco.addActionListener(this);
        panel.add(btConsultaEndereco);

        JLabel lbEndereco = new JLabel("Endereço");
        lbEndereco.setBounds(493, 63, 60, 14);
        panel.add(lbEndereco);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(493, 81, 158, 20);
        tfEndereco.setEditable(false);
        tfEndereco.setBackground(Color.WHITE);
        tfEndereco.addFocusListener(this);
        panel.add(tfEndereco);

        JLabel lbBairro = new JLabel("Bairro");
        lbBairro.setBounds(20, 105, 60, 14);
        panel.add(lbBairro);

        tfBairro = new JTextField();
        tfBairro.setBounds(20, 123, 150, 20);
        tfBairro.setEditable(false);
        tfBairro.setBackground(Color.WHITE);
        tfBairro.addFocusListener(this);
        panel.add(tfBairro);

        JLabel lbNumero = new JLabel("Número");
        lbNumero.setBounds(180, 105, 55, 14);
        panel.add(lbNumero);

        lbNumeroObrig = new JLabel("");
        lbNumeroObrig.setBounds(220, 108, 10, 14);
        lbNumeroObrig.setForeground(Color.RED);
        panel.add(lbNumeroObrig);

        tfNumero = new JTextField();
        tfNumero.setBounds(180, 123, 80, 20);
        tfNumero.setDocument(new CamposInt());
        tfNumero.addFocusListener(this);
        panel.add(tfNumero);

        JLabel lbCep = new JLabel("CEP");
        lbCep.setBounds(270, 105, 80, 14);
        panel.add(lbCep);

        tfCep = new JTextField();
        tfCep.setBounds(270, 123, 90, 20);
        tfCep.setEditable(false);
        tfCep.setBackground(Color.WHITE);
        tfCep.addFocusListener(this);
        panel.add(tfCep);

        JLabel lbCidade = new JLabel("Cidade");
        lbCidade.setBounds(370, 105, 70, 14);
        panel.add(lbCidade);

        tfCidade = new JTextField();
        tfCidade.setBounds(370, 123, 140, 20);
        tfCidade.setEditable(false);
        tfCidade.setBackground(Color.WHITE);
        tfCidade.addFocusListener(this);
        panel.add(tfCidade);

        JLabel lbEstado = new JLabel("Estado");
        lbEstado.setBounds(518, 105, 70, 14);
        panel.add(lbEstado);

        tfEstado = new JTextField();
        tfEstado.setBounds(518, 123, 132, 20);
        tfEstado.setEditable(false);
        tfEstado.setBackground(Color.WHITE);
        tfEstado.addFocusListener(this);
        panel.add(tfEstado);

        JLabel lbRegiao = new JLabel("Região");
        lbRegiao.setBounds(20, 147, 70, 14);
        panel.add(lbRegiao);

        tfRegiao = new JTextField();
        tfRegiao.setBounds(20, 165, 100, 20);
        tfRegiao.setEditable(false);
        tfRegiao.setBackground(Color.WHITE);
        tfRegiao.addFocusListener(this);
        panel.add(tfRegiao);

        JLabel lbReferencia = new JLabel("Referência");
        lbReferencia.setBounds(130, 147, 70, 14);
        panel.add(lbReferencia);

        tfReferencia = new JTextField();
        tfReferencia.setBounds(130, 165, 130, 20);
        tfReferencia.addFocusListener(this);
        panel.add(tfReferencia);

        JLabel lbEmail = new JLabel("E-mail");
        lbEmail.setBounds(270, 147, 70, 14);
        panel.add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(270, 165, 185, 20);
        tfEmail.addFocusListener(this);
        panel.add(tfEmail);

        JLabel lbMSN = new JLabel("MSN");
        lbMSN.setBounds(465, 147, 70, 14);
        panel.add(lbMSN);

        tfMsn = new JTextField();
        tfMsn.setBounds(465, 165, 185, 20);
        tfMsn.addFocusListener(this);
        panel.add(tfMsn);

        JLabel lbFone = new JLabel("Fone");
        lbFone.setBounds(20, 190, 50, 14);
        panel.add(lbFone);

        lbFoneObrig = new JLabel("");
        lbFoneObrig.setBounds(46, 193, 10, 14);
        lbFoneObrig.setForeground(Color.RED);
        panel.add(lbFoneObrig);

        try {
            ftfFone = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfFone.setBounds(20, 208, 100, 20);
        ftfFone.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfFone.addFocusListener(this);
        panel.add(ftfFone);

        JLabel lbFax = new JLabel("Fax");
        lbFax.setBounds(130, 190, 50, 14);
        panel.add(lbFax);

        try {
            ftfFax = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException ex) {
        }
        ftfFax.setBounds(130, 208, 100, 20);
        ftfFax.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfFax.addFocusListener(this);
        panel.add(ftfFax);

        JLabel lbFrete = new JLabel("Valor Frete Km");
        lbFrete.setBounds(240, 190, 110, 14);
        panel.add(lbFrete);

        lbFreteObrig = new JLabel("");
        lbFreteObrig.setBounds(312, 193, 10, 14);
        lbFreteObrig.setForeground(Color.RED);
        panel.add(lbFreteObrig);

        tfFrete = new JTextField();
        tfFrete.setBounds(240, 208, 100, 20);
        tfFrete.addFocusListener(this);
        panel.add(tfFrete);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(350, 190, 70, 14);
        panel.add(lbDescricao);

        tfDescricao = new JTextField();
        tfDescricao.setBounds(350, 208, 300, 20);
        tfDescricao.addFocusListener(this);
        panel.add(tfDescricao);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 249, 672, 3);
        panel.add(separator);

        Icon icOk = new ImageIcon("OK.png");
        btOk = new JButton("OK", icOk);
        btOk.setBounds(140, 264, 70, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("Confirma Operação");
        btOk.addActionListener(this);
        panel.add(btOk);

        Icon icCancelar = new ImageIcon("Cancelar.png");
        btCancelar = new JButton("Cancelar", icCancelar);
        btCancelar.setBounds(250, 264, 70, 26);
        btCancelar.setMargin(new Insets(0, 0, 0, 0));
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Limpar os Campos");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        Icon icExcluir = new ImageIcon("Excluir.png");
        btExcluir = new JButton("Excluir", icExcluir);
        btExcluir.setBounds(360, 264, 70, 26);
        btExcluir.setMargin(new Insets(0, 0, 0, 0));
        btExcluir.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.setToolTipText("Excluir Registro");
        btExcluir.addActionListener(this);
        panel.add(btExcluir);

        Icon icSair = new ImageIcon("Sair.png");
        btSair = new JButton("Sair", icSair);
        btSair.setBounds(470, 264, 70, 26);
        btSair.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btSair.setToolTipText("Fechar");
        btSair.addActionListener(this);
        panel.add(btSair);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(700, 354);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controleTrans.getProxCodTrans()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfNome.setText("");
        tfCodEndereco.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfCep.setText("");
        tfCidade.setText("");
        tfEstado.setText("");
        tfRegiao.setText("");
        tfReferencia.setText("");
        tfEmail.setText("");
        tfMsn.setText("");
        tfFrete.setText("");
        tfDescricao.setText("");
        ftfCnpj.setText("");
        ftfIe.setText("");
        ftfInscMun.setText("");
        ftfFone.setText("");
        ftfFax.setText("");
        lbNomeObrig.setText("");
        lbCnpjObrig.setText("");
        lbIeObrig.setText("");
        lbInscMunObrig.setText("");
        lbCodEnderecoObrig.setText("");
        lbNumeroObrig.setText("");
        lbFoneObrig.setText("");
        lbFreteObrig.setText("");
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
        if ("  .  .  ".equals(ftfInscMun.getText())) {
            lbInscMunObrig.setText("*");
            flag = true;
        } else {
            lbInscMunObrig.setText("");
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
        if ("(  )    -    ".equals(ftfFone.getText())) {
            lbFoneObrig.setText("*");
            flag = true;
        } else {
            lbFoneObrig.setText("");
        }
        if ("".equals(tfFrete.getText().trim())) {
            lbFreteObrig.setText("*");
            flag = true;
        } else {
            lbFreteObrig.setText("");
        }
        if (!flag) {

            validaDouble(tfFrete, "Campo frete inválido", "Frete deve conter valor positivo", 0);
            Transportadora transportadora = new Transportadora();

            try {
                transportadora.setFone(new String(seguranca.encriptarSimetricamente(ftfFone.getText().getBytes())));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            transportadora.setCodTrans(Integer.parseInt(tfCodigo.getText()));
            transportadora.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            transportadora.setUltAlteracao(new Date());
            transportadora.setNome(tfNome.getText());
            transportadora.setCpfCnpj(ftfCnpj.getText());
            transportadora.setRgIe(ftfIe.getText());
            transportadora.setInscMunicipal(ftfInscMun.getText());
            transportadora.setCodEndereco(Integer.parseInt(tfCodEndereco.getText()));
            transportadora.setNumero(Integer.parseInt(tfNumero.getText()));
            transportadora.setReferencia(tfReferencia.getText());
            transportadora.setEmail(tfEmail.getText());
            transportadora.setMsn(tfMsn.getText());
            transportadora.setFax(ftfFax.getText());
            transportadora.setFrete(Double.parseDouble(tfFrete.getText()));
            transportadora.setDescricao(tfDescricao.getText());
            int codTransCadas = controleTrans.getTransCadastrado(ftfCnpj.getText(), Integer.parseInt(tfCodigo.getText()));// verifica se transportadora já cadastrada
            if (codTransCadas != -1) {
                if (JOptionPane.showConfirmDialog(null, "Confirmar alteração nos dados", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (controleTrans.updateTrans(transportadora)) {
                        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "Transportadora", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            } else {
                codTransCadas = controleTrans.getTransCadastrado(ftfCnpj.getText());// verifica se pessoa já cadastrada
                if (codTransCadas != -1) {
                    JOptionPane.showMessageDialog(null, "Este CNPJ ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (controleTrans.insertTrans(transportadora)) {
                        JOptionPane.showMessageDialog(null, "Transportadora cadastrada com sucesso", "Transportadora", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                }
            }
        } else {
            throw new Exception("Campos * obrigatórios inválidos");
        }
    }

    private void consultaTrans() throws Exception {
        if (controleTrans.isTransVazio()) {
            throw new Exception("Não há transportadoras cadastradas");
        }
        ConsultaTrans consulta = new ConsultaTrans(controleTrans);
        consulta.setListener(new ListenerTrans() {
            @Override
            public void exibeTrans(Transportadora transportadora) {
                limparTela();

                try {
                    ftfFone.setText(new String(seguranca.decriptarSimetricamente(transportadora.getFone().getBytes())));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                tfCodigo.setText(Integer.toString(transportadora.getCodTrans()));
                tfDataCadas.setText(formatDate.format(transportadora.getDataCadastro()) + " as " + formatHora.format(transportadora.getDataCadastro()));
                tfUltAlteracao.setText(formatDate.format(transportadora.getUltAlteracao()) + " as " + formatHora.format(transportadora.getUltAlteracao()));
                tfNome.setText(transportadora.getNome());
                ftfCnpj.setText(transportadora.getCpfCnpj());
                ftfIe.setText(transportadora.getRgIe());
                ftfInscMun.setText(transportadora.getInscMunicipal());
                tfCodEndereco.setText(Integer.toString(transportadora.getCodEndereco()));
                tfEndereco.setText(transportadora.getEndereco());
                tfBairro.setText(transportadora.getBairro());
                tfNumero.setText(Integer.toString(transportadora.getNumero()));
                tfCep.setText(transportadora.getCep());
                tfCidade.setText(transportadora.getCidade());
                tfEstado.setText(transportadora.getEstado());
                tfRegiao.setText(transportadora.getRegiao());
                tfReferencia.setText(transportadora.getReferencia());
                tfEmail.setText(transportadora.getEmail());
                tfMsn.setText(transportadora.getMsn());
                ftfFax.setText(transportadora.getFax());
                tfFrete.setText(Double.toString(transportadora.getFrete()));
                tfDescricao.setText(transportadora.getDescricao());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (controleTrans.isTransVazio()) {
            throw new Exception("Não há transportadoras cadastradas");
        }
        if (!controleTrans.isTransCadastrado(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione uma transportadora");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esta transportadora", "Transportadora", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (controleTrans.deleteTrans(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Transportadora excluída com sucesso", "Transportadora", JOptionPane.INFORMATION_MESSAGE);
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
                tfCodEndereco.setText(Integer.toString(endereco.getCodEndereco()));
                tfEndereco.setText(endereco.getEndereco());
                tfBairro.setText(endereco.getBairro());
                tfCep.setText(endereco.getCep());
                tfCidade.setText(endereco.getCidade());
                tfEstado.setText(endereco.getEstado());
                tfRegiao.setText(endereco.getRegiao());
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
                consultaTrans();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaEndereco) {
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
        if (evento.getSource() == tfEstado) {
            tfEstado.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfRegiao) {
            tfRegiao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfReferencia) {
            tfReferencia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmail) {
            tfEmail.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMsn) {
            tfMsn.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfFrete) {
            tfFrete.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCnpj) {
            ftfCnpj.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfIe) {
            ftfIe.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfInscMun) {
            ftfInscMun.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFone) {
            ftfFone.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFax) {
            ftfFax.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfNome.setBackground(Color.WHITE);
        tfCodEndereco.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfCep.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfEstado.setBackground(Color.WHITE);
        tfRegiao.setBackground(Color.WHITE);
        tfReferencia.setBackground(Color.WHITE);
        tfEmail.setBackground(Color.WHITE);
        tfMsn.setBackground(Color.WHITE);
        tfFrete.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        if (evento.getSource() == ftfCnpj) {
            ftfCnpj.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfCnpj);
        }
        if (evento.getSource() == ftfIe) {
            ftfIe.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfIe);
        }
        if (evento.getSource() == ftfInscMun) {
            ftfInscMun.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfInscMun);
        }
        if (evento.getSource() == ftfFone) {
            ftfFone.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfFone);
        }
        if (evento.getSource() == ftfFax) {
            ftfFax.setBackground(Color.WHITE);
            validaJFormattedTextField(ftfFax);
        }
    }
}

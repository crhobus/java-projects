package view.mail;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import control.ConexaoDAO;

import model.Conexao;

import view.CamposInt;
import view.PanelComponentes;

/*Essa classe exibe uma caixa de diálogo para inserir
configurações de conexão de e-mail com o servidor*/
public class ConfigurarConexao extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfNome, tfServidorPop3Imap, tfUsuario, tfServidorSMTP,
            tfPortaPop3Imap, tfPortaSMTP, tfEmail;
    private JPasswordField pfSenha;
    private JComboBox cbTipo;
    private JCheckBox cxPadroes;
    private JRadioButton rbAutenSim, rbAutenNao, rbConecSegSim, rbConecSegNao;
    private JButton btConectar, btCancelar;
    private ConexaoDAO conexaoDAO;// Objeto que conecta as configurações de conexão ao banco de dados
    private Conexao conexao;// Objeto de configurações de conexão
    private boolean fechou;// Flag utilizado para verificar quando a caixa de diálogo é fechada no botão 'X'

    public ConfigurarConexao(Frame principal, boolean encerar, ConexaoDAO conexaoDAO) {
        super(principal, true);// Chama o superconstrutor, especificando que essa caixa de diálogo é modal
        this.conexaoDAO = conexaoDAO;
        initComponents(principal, encerar);
    }

    // Cria os componentes gráficos
    private void initComponents(Frame principal, final boolean encerar) {
        this.setTitle("Conexões com o servidor");
        this.setLayout(null);

        PanelComponentes panel = new PanelComponentes(5, 5, 425, 450);
        this.add(panel);

        panel.addJLabel("Tipo:", 59, 40, 50, 14);

        final String[] itensTipo = {"pop3", "imap"};
        cbTipo = panel.addJComboBox(itensTipo, 90, 37, 70, 20);
        cbTipo.addActionListener(this);

        panel.addJLabel("Nome:", 52, 70, 50, 14);

        tfNome = panel.addJTextField(90, 67, 300, 20);

        panel.addJLabel("Servidor:", 39, 100, 50, 14);

        tfServidorPop3Imap = panel.addJTextField(90, 97, 300, 20);

        panel.addJLabel("Usuário:", 43, 130, 50, 14);

        tfUsuario = panel.addJTextField(90, 127, 300, 20);

        panel.addJLabel("Senha:", 49, 160, 50, 14);

        pfSenha = panel.addJPasswordField(90, 157, 300, 20);

        panel.addJLabel("Servidor SMTP:", 10, 190, 75, 14);

        tfServidorSMTP = panel.addJTextField(90, 187, 300, 20);

        panel.addJLabel("E-Mail:", 51, 220, 75, 14);

        tfEmail = panel.addJTextField(90, 217, 300, 20);

        PanelComponentes panelPorta = new PanelComponentes("Portas", 20, 250, 180, 120);
        panel.add(panelPorta);

        panelPorta.addJLabel("POP3 / IMAP:", 10, 30, 70, 14);

        tfPortaPop3Imap = panelPorta.addJTextField(83, 27, 80, 20);
        tfPortaPop3Imap.setDocument(new CamposInt());
        tfPortaPop3Imap.setText("110");
        tfPortaPop3Imap.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent evento) {
                // verifica se é pop3 ou imap
                if ("pop3".equals(cbTipo.getSelectedItem())) {
                    // Desabilita modo padrão se campo for diferente de 110
                    if (!"110".equals(tfPortaPop3Imap.getText())) {
                        cxPadroes.setSelected(false);
                    }
                } else {
                    // Desabilita modo padrão se campo for diferente de 143
                    if (!"143".equals(tfPortaPop3Imap.getText())) {
                        cxPadroes.setSelected(false);
                    }
                }
            }
        });

        panelPorta.addJLabel("SMTP:", 45, 60, 50, 14);

        tfPortaSMTP = panelPorta.addJTextField(83, 57, 80, 20);
        tfPortaSMTP.setDocument(new CamposInt());
        tfPortaSMTP.setText("25");
        tfPortaSMTP.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent evento) {
                // Desabilita modo padrão se campo for diferente de 25
                if (!"25".equals(tfPortaSMTP.getText())) {
                    cxPadroes.setSelected(false);
                }
            }
        });

        panelPorta.addJLabel("Usar Padrões", 80, 85, 70, 14);

        cxPadroes = panelPorta.addJCheckBox(146, 86, 17, 13);
        cxPadroes.setSelected(true);
        cxPadroes.addActionListener(this);

        PanelComponentes panelAutenticacao = new PanelComponentes("Autenticação Segurança", 205, 250, 185, 120);
        panel.add(panelAutenticacao);

        panelAutenticacao.addJLabel("Meu servidor requer autenticação", 10, 30, 170, 14);

        rbAutenSim = panelAutenticacao.addJRadioButton("Sim", 10, 50, 45, 14);
        rbAutenSim.addActionListener(this);

        rbAutenNao = panelAutenticacao.addJRadioButton("Não", 70, 50, 45, 14);
        rbAutenNao.setSelected(true);
        rbAutenNao.addActionListener(this);

        panelAutenticacao.addJLabel("Conexão Segura", 10, 70, 100, 14);

        rbConecSegSim = panelAutenticacao.addJRadioButton("Sim", 10, 90, 45, 14);
        rbConecSegSim.addActionListener(this);

        rbConecSegNao = panelAutenticacao.addJRadioButton("Não", 70, 90, 45, 14);
        rbConecSegNao.setSelected(true);
        rbConecSegNao.addActionListener(this);

        panel.addJSeparator(1, 380, 422, 3);

        btConectar = panel.addJButton("Conectar", "Conectar-se com o servidor", 90, 400, 76, 26);
        btConectar.addActionListener(this);

        btCancelar = panel.addJButton("Cancelar", "Limpar os Campos", 250, 400, 76, 26);
        btCancelar.addActionListener(this);

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evento) {
                if (encerar) {// Verifica se é para encerar ou não o programa
                    System.exit(0);// Cancela a conexão e sai do programa
                }
                fechou = true;
            }
        });
        this.setSize(440, 487);
        this.setResizable(false);
        this.setLocationRelativeTo(principal);// Centraliza a caixa de diálogo na aplicação
    }

    // Atualiza todos os campos para modo original
    private void limparTela() {
        tfNome.setText("");
        tfServidorPop3Imap.setText("");
        tfUsuario.setText("");
        tfServidorSMTP.setText("");
        tfPortaPop3Imap.setText("110");
        tfPortaSMTP.setText("25");
        tfEmail.setText("");
        pfSenha.setText("");
        cbTipo.setSelectedItem("pop3");
        cxPadroes.setSelected(true);
        rbAutenSim.setSelected(false);
        rbAutenNao.setSelected(true);
        rbConecSegSim.setSelected(false);
        rbConecSegNao.setSelected(true);
    }

    // Valida configurações de conexão e fecha a caixa de diálogo
    private void conectar() throws Exception {
        if ("".equals(tfNome.getText().trim())) {
            throw new Exception("Campo nome obrigatório não preenchido\nentre com o nome");
        }
        if ("".equals(tfServidorPop3Imap.getText().trim())) {
            throw new Exception("Campo servidor obrigatório não preenchido\nentre com o servidor conforme o tipo");
        }
        if ("".equals(tfUsuario.getText().trim())) {
            throw new Exception("Campo usuário obrigatório não preenchido\nentre com o nome de usuario");
        }
        if (pfSenha.getPassword().length < 1) {
            throw new Exception("Campo senha obrigatório não preenchido\nentre com a senha");
        }
        if ("".equals(tfServidorSMTP.getText().trim())) {
            throw new Exception("Campo servidor SMTP obrigatório não preenchido\nentre com o servidor SMTP");
        }
        if ("".equals(tfEmail.getText().trim())) {
            throw new Exception("Campo e-mail obrigatório não preenchido\nentre com o e-mail");
        }
        if ("".equals(tfPortaPop3Imap.getText())) {
            throw new Exception("Campo porta POP3 / IMAP obrigatório não preenchido\nentre com a respectiva porta");
        }
        if ("".equals(tfPortaSMTP.getText())) {
            throw new Exception("Campo porta SMTP obrigatório não preenchido\nentre com a respectiva porta");
        }
        // Cria um objeto conexão conforme os parâmetro da tela
        conexao = new Conexao();
        conexao.setTipo((String) cbTipo.getSelectedItem());
        conexao.setNome(tfNome.getText());
        conexao.setServidorPop3Imap(tfServidorPop3Imap.getText());
        conexao.setUsuario(tfUsuario.getText());
        conexao.setSenha(new String(pfSenha.getPassword()));
        conexao.setServidorSmtp(tfServidorSMTP.getText());
        conexao.setEmail(tfEmail.getText());
        conexao.setPortaPop3Imap(Integer.parseInt(tfPortaPop3Imap.getText()));
        conexao.setPortaSmtp(Integer.parseInt(tfPortaSMTP.getText()));
        if (rbAutenSim.isSelected()) {
            conexao.setAutenticacao(true);
        }
        if (rbConecSegSim.isSelected()) {
            conexao.setConSegura(true);
        }
        conexaoDAO.insertConexao(conexao);// Grava no banco de dados
        // Fecha a caixa de diálogo.
        this.dispose();
    }

    public Conexao getConexao() {
        return conexao;
    }

    // Seta a conexão atual na tela
    public void setConexao(Conexao conexao) {
        cbTipo.setSelectedItem(conexao.getTipo());
        tfNome.setText(conexao.getNome());
        tfServidorPop3Imap.setText(conexao.getServidorPop3Imap());
        tfUsuario.setText(conexao.getUsuario());
        pfSenha.setText(conexao.getSenha());
        tfServidorSMTP.setText(conexao.getServidorSmtp());
        tfEmail.setText(conexao.getEmail());
        tfPortaPop3Imap.setText(Integer.toString(conexao.getPortaPop3Imap()));
        tfPortaSMTP.setText(Integer.toString(conexao.getPortaSmtp()));
        if (conexao.isAutenticacao()) {
            rbAutenSim.setSelected(true);
            rbAutenNao.setSelected(false);
        } else {
            rbAutenNao.setSelected(true);
            rbAutenSim.setSelected(false);
        }
        if (conexao.isConSegura()) {
            rbConecSegSim.setSelected(true);
            rbConecSegNao.setSelected(false);
        } else {
            rbConecSegNao.setSelected(true);
            rbConecSegSim.setSelected(false);
        }
        if (conexao.getPortaPop3Imap() != 110 || conexao.getPortaSmtp() != 25) {
            cxPadroes.setSelected(false);
        }
    }

    // Retorna verdadeiro de foi fechado no botão 'X'
    public boolean isFechou() {
        return fechou;
    }

    // Trata eventos
    @Override
    public void actionPerformed(ActionEvent evento) {
        // desabilita autenticação modo não
        if (evento.getSource() == rbAutenSim) {
            rbAutenNao.setSelected(false);
            // se modo sim tiver desabilitado, habilita-o
            if (!rbAutenSim.isSelected()) {
                rbAutenSim.setSelected(true);
            }
        }
        // desabilita autenticação modo sim
        if (evento.getSource() == rbAutenNao) {
            rbAutenSim.setSelected(false);
            // se modo não estiver desabilitado, habilita-o
            if (!rbAutenNao.isSelected()) {
                rbAutenNao.setSelected(true);
            }
        }
        // desabilita conexão segura modo não
        if (evento.getSource() == rbConecSegSim) {
            rbConecSegNao.setSelected(false);
            if (!rbConecSegSim.isSelected()) {
                rbConecSegSim.setSelected(true);
            }
        }
        // desabilita conexão segura modo sim
        if (evento.getSource() == rbConecSegNao) {
            rbConecSegSim.setSelected(false);
            if (!rbConecSegNao.isSelected()) {
                rbConecSegNao.setSelected(true);
            }
        }
        // Atualiza todos os campos para modo original
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        // Valida configurações de conexão
        if (evento.getSource() == btConectar) {
            try {
                conectar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro nas configurações", JOptionPane.ERROR_MESSAGE);
            }
        }
        // atualiza para o padrão conforme o tipo selecionado
        if (evento.getSource() == cbTipo) {
            if ("pop3".equals(cbTipo.getSelectedItem())) {
                // padrão pop3
                tfPortaPop3Imap.setText("110");
            } else {
                // padrão imap
                tfPortaPop3Imap.setText("143");
            }
            // Padrão SMTP
            tfPortaSMTP.setText("25");
        }
        // Usa tipo padrão
        if (evento.getSource() == cxPadroes) {
            // verifica o tipo selecionado
            if ("pop3".equals(cbTipo.getSelectedItem())) {
                tfPortaPop3Imap.setText("110");
            } else {
                tfPortaPop3Imap.setText("143");
            }
            // Padrão SMTP
            tfPortaSMTP.setText("25");
        }
    }
}

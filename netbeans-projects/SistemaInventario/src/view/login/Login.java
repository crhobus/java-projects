package view.login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Funcionario;

import dbOracle.FuncionarioDAO;

import view.PanelComponentes;
import view.Principal;

public class Login extends JFrame implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfUsuario;
    private JPasswordField pfSenha;
    private JButton btOk, btCancelar;
    private Connection con;
    private FuncionarioDAO funcionarioDAO;

    public Login(Connection con) {
        super("Login - Sistema Inventário");
        this.con = con;
        funcionarioDAO = new FuncionarioDAO(con);
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        PanelComponentes panel = new PanelComponentes(10, 10, 295, 195);
        this.add(panel);

        panel.addJLabel("Usuário", 40, 45, 50, 14);

        tfUsuario = panel.addJTextField(85, 43, 160, 20);
        tfUsuario.addFocusListener(this);

        panel.addJLabel("Senha", 40, 90, 50, 14);

        pfSenha = panel.addJPasswordField(85, 87, 160, 20);
        pfSenha.addFocusListener(this);
        pfSenha.addActionListener(this);

        btOk = panel.addJButtonOK(100, 120, 70, 26);
        btOk.setToolTipText("Logar");
        btOk.addActionListener(this);

        btCancelar = panel.addJButtonCancelar(180, 120, 70, 26);
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evento) {
                try {
                    con.close();
                    System.exit(JFrame.EXIT_ON_CLOSE);
                } catch (SQLException ex) {
                }
            }
        });

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(320, 240);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void limparTela() {
        tfUsuario.setText("");
        pfSenha.setText("");
    }

    @SuppressWarnings("deprecation")
    private void logar() throws Exception {
        if ("".equals(tfUsuario.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Entre com o usuário", "Atenção", JOptionPane.WARNING_MESSAGE);
            tfUsuario.grabFocus();
            return;
        }
        if ("".equals(pfSenha.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Entre com a senha", "Atenção", JOptionPane.WARNING_MESSAGE);
            pfSenha.grabFocus();
            return;
        }
        if ("admin".equals(tfUsuario.getText())) {
            if ("key50100".equals(pfSenha.getText())) {
                new Principal(con, tfUsuario.getText(), "Administrador");
                this.dispose();
            } else {
                pfSenha.setText("");
                pfSenha.grabFocus();
                throw new Exception("Senha inválida");
            }
        } else {
            Funcionario funcionario = funcionarioDAO.getAutenticacao(tfUsuario.getText());
            if (funcionario == null) {
                tfUsuario.grabFocus();
                throw new Exception("Usuário inválido");
            }
            if (funcionario.getUsuario().equals(tfUsuario.getText())) {
                if (funcionario.getSenha().equals(pfSenha.getText())) {
                    String permissao;
                    if (funcionario.getPermissao() == 1) {
                        permissao = "Administrador";
                    } else {
                        if (funcionario.getPermissao() == 2) {
                            permissao = "Suporte";
                        } else {
                            permissao = "Outros";
                        }
                    }
                    new Principal(con, funcionario.getUsuario(), permissao);
                    this.dispose();
                } else {
                    pfSenha.setText("");
                    pfSenha.grabFocus();
                    throw new Exception("Senha inválida");
                }
            } else {
                tfUsuario.grabFocus();
                throw new Exception("Usuário inválido");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOk || evento.getSource() == pfSenha) {
            try {
                logar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfUsuario) {
            tfUsuario.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == pfSenha) {
            pfSenha.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfUsuario.setBackground(Color.WHITE);
        pfSenha.setBackground(Color.WHITE);
    }
}

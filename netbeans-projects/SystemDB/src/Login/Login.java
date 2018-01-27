package Login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Principal.Principal;
import Usuario.UsuarioControl;
import Modelo.Usuario;
import Seguranca.Seguranca;

public class Login extends JFrame implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfUsuario;
    private JPasswordField pfSenha, pfChave;
    private JButton btOk, btCancelar;
    private UsuarioControl controleUsuario;
    private Connection con;
    private Seguranca seguranca;

    public Login(Connection con) {
        super("Login - System DB");
        controleUsuario = new UsuarioControl(con);
        this.con = con;
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 295, 195);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createEtchedBorder());
        add(panel);

        JLabel lbUsuario = new JLabel("Usuário");
        lbUsuario.setBounds(40, 45, 50, 14);
        panel.add(lbUsuario);

        tfUsuario = new JTextField();
        tfUsuario.setBounds(85, 43, 160, 20);
        tfUsuario.addFocusListener(this);
        panel.add(tfUsuario);

        JLabel lbSenha = new JLabel("Senha");
        lbSenha.setBounds(40, 75, 50, 14);
        panel.add(lbSenha);

        pfSenha = new JPasswordField();
        pfSenha.setBounds(85, 73, 160, 20);
        pfSenha.addFocusListener(this);
        panel.add(pfSenha);

        JLabel lbChave = new JLabel("Chave");
        lbChave.setBounds(40, 105, 50, 14);
        panel.add(lbChave);

        pfChave = new JPasswordField();
        pfChave.setBounds(85, 103, 160, 20);
        pfChave.addFocusListener(this);
        panel.add(pfChave);

        Icon icOk = new ImageIcon("OK.png");
        btOk = new JButton("OK", icOk);
        btOk.setBounds(100, 135, 70, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("Logar");
        btOk.addActionListener(this);
        panel.add(btOk);

        Icon icCancelar = new ImageIcon("Cancelar.png");
        btCancelar = new JButton("Cancelar", icCancelar);
        btCancelar.setBounds(180, 135, 70, 26);
        btCancelar.setMargin(new Insets(0, 0, 0, 0));
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(320, 240);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void limparTela() {
        tfUsuario.setText("");
        pfSenha.setText("");
        pfChave.setText("");
    }

    private void logar() throws Exception {
        if ("".equals(tfUsuario.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Entre com o usuário", "Atenção", JOptionPane.WARNING_MESSAGE);
            tfUsuario.grabFocus();
            return;
        }
        if ("".equals(new String(pfSenha.getPassword()).trim())) {
            JOptionPane.showMessageDialog(null, "Entre com a senha", "Atenção", JOptionPane.WARNING_MESSAGE);
            pfSenha.grabFocus();
            return;
        }
        if ("".equals(new String(pfChave.getPassword()).trim())) {
            JOptionPane.showMessageDialog(null, "Entre com a chave de segurança", "Atenção", JOptionPane.WARNING_MESSAGE);
            pfChave.grabFocus();
            return;
        }
        seguranca = new Seguranca(new String(pfChave.getPassword()).getBytes());
        Usuario usuario = controleUsuario.getUsuario(tfUsuario.getText());
        if (usuario != null
                && usuario.getUsuario().equals(tfUsuario.getText())
                && seguranca.verificarHash(usuario.getSenha(), new String(pfSenha.getPassword()))) {
            new Principal(con, usuario.getUsuario(), usuario.getPermissao(), seguranca);
            this.dispose();
        } else {
            pfSenha.setText("");
            throw new Exception("Usuário/Senha inválido");
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOk) {
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
        if (evento.getSource() == pfChave) {
            pfChave.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfUsuario.setBackground(Color.WHITE);
        pfSenha.setBackground(Color.WHITE);
        pfChave.setBackground(Color.WHITE);
    }
}

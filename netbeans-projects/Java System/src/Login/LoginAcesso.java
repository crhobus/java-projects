package Login;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import FormaPersistencia.FormaPersistencia;
import FormatacaoCampos.CriarObjGrafico;
import Modelo.Usuario;
import Principal.Principal;
import Usuario.UsuarioControl;

public class LoginAcesso extends JFrame implements ActionListener {

    private JTextField tfUsuario;
    private JPasswordField pfSenha;
    private JButton btOK;

    public LoginAcesso() {
        super("Java System");
        iniComponents();
    }

    private void iniComponents() {
        setBackground(new Color(248, 248, 248));
        setLayout(null);
        JPanel panel = CriarObjGrafico.criarJPanelTitulo("LOGIN DE ACESSO", 15, 15, 300, 190);
        add(panel);

        panel.add(CriarObjGrafico.criarJLabel("USUÁRIO", 30, 60, 110, 20));
        tfUsuario = CriarObjGrafico.criarJTextField(102, 60, 160, 20);
        panel.add(tfUsuario);

        panel.add(CriarObjGrafico.criarJLabel("SENHA", 30, 110, 110, 20));
        pfSenha = CriarObjGrafico.criarJPasswordField(102, 110, 160, 20);
        pfSenha.addActionListener(this);
        panel.add(pfSenha);

        btOK = CriarObjGrafico.criarJButton("OK", 209, 136, 51, 22);
        btOK.addActionListener(this);
        panel.add(btOK);

        HashSet conj = new HashSet(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(350, 258);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void validaLogon() throws Exception {
        FormaPersistencia persistencia = new FormaPersistencia();
        int tipo = persistencia.ler();
        if ("".equals(tfUsuario.getText())) {
            JOptionPane.showMessageDialog(null, "Usuário inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            tfUsuario.grabFocus();
            return;
        }
        if ("".equals(pfSenha.getText())) {
            JOptionPane.showMessageDialog(null, "Senha inválida", "Erro", JOptionPane.ERROR_MESSAGE);
            pfSenha.grabFocus();
            return;
        }
        if ("Administrador".equals(tfUsuario.getText())) {
            if ("50100".equals(pfSenha.getText())) {
                if (tipo == -1) {
                    persistencia.criarDAO(1);
                    new Principal("Administrador", 1, persistencia);
                    setVisible(false);
                } else {
                    persistencia.criarDAO(tipo);
                    new Principal("Administrador", 1, persistencia);
                    setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senha Invalida", "Logon no Sistema", JOptionPane.ERROR_MESSAGE);
                pfSenha.setText("");
                pfSenha.grabFocus();
            }
        } else {
            if (tipo != -1) {
                persistencia.criarDAO(tipo);
                UsuarioControl controle = new UsuarioControl(persistencia.getDaoFactory());
                Usuario usuario = controle.getUsuario(tfUsuario.getText());
                if (usuario != null) {
                    if (tfUsuario.getText().equals(usuario.getNome())) {
                        if (pfSenha.getText().equals(usuario.getSenha())) {
                            new Principal(usuario.getNome(), usuario.getPermissao(), persistencia);
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Senha Invalida", "Logon no Sistema", JOptionPane.ERROR_MESSAGE);
                            pfSenha.setText("");
                            pfSenha.grabFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                        tfUsuario.setText("");
                        pfSenha.setText("");
                        tfUsuario.grabFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                    tfUsuario.setText("");
                    pfSenha.setText("");
                    tfUsuario.grabFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                tfUsuario.setText("");
                pfSenha.setText("");
                tfUsuario.grabFocus();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOK || evento.getSource() == pfSenha) {
            try {
                validaLogon();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

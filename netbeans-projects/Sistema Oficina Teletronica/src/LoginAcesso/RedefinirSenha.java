package LoginAcesso;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import Formatacao.ObjGraficos;

public class RedefinirSenha extends ObjGraficos implements ActionListener, FocusListener {

    private static final long serialVersionUID = 4416584935676075669L;
    private JPasswordField pfSenhaAtual, pfNovaSenha, pfConfirmaNovaSenha;
    private JButton btOk, btCancelar;
    private Connection con;

    public RedefinirSenha(Connection con) {
        this.con = con;
        initComponents();
    }

    private void initComponents() {
        setTitle("Redefinir Senha");
        setLayout(null);
        JPanel panel = getJPanelLineBorder(5, 5, 413, 222);
        add(panel);

        panel.add(getJLabel("Senha Atual", 30, 30, 80, 14));
        pfSenhaAtual = getJPasswordField(160, 28, 130, 20);
        pfSenhaAtual.addFocusListener(this);
        panel.add(pfSenhaAtual);

        panel.add(getJLabel("Nova Senha", 30, 70, 80, 14));
        pfNovaSenha = getJPasswordField(160, 68, 130, 20);
        pfNovaSenha.addFocusListener(this);
        panel.add(pfNovaSenha);

        panel.add(getJLabel("Confirmar Nova Senha", 30, 110, 130, 14));
        pfConfirmaNovaSenha = getJPasswordField(160, 108, 130, 20);
        pfConfirmaNovaSenha.addFocusListener(this);
        panel.add(pfConfirmaNovaSenha);

        btOk = getJButton("OK", 190, 150, 51, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("OK");
        btOk.addActionListener(this);
        panel.add(btOk);

        btCancelar = getJButton("Cancelar", 255, 150, 86, 26);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(430, 260);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        pfSenhaAtual.setText("");
        pfNovaSenha.setText("");
        pfConfirmaNovaSenha.setText("");
    }

    @SuppressWarnings("deprecation")
    private void ok() throws Exception {
        if ("".equals(pfSenhaAtual.getText().trim())) {
            pfSenhaAtual.grabFocus();
            throw new Exception("Campo senha atual inválido");
        }
        if ("".equals(pfNovaSenha.getText().trim())) {
            pfNovaSenha.grabFocus();
            throw new Exception("Campo nova senha inválido");
        }
        if ("".equals(pfConfirmaNovaSenha.getText().trim())) {
            pfConfirmaNovaSenha.grabFocus();
            throw new Exception("Campo confirma senha inválido");
        }
        if (pfNovaSenha.getText().trim().length() < 6) {
            throw new Exception("Entre com no mínimo 6 caracteres");
        }
        if (pfNovaSenha.getText().equals(pfConfirmaNovaSenha.getText())) {
            LoginControl loginControl = new LoginControl(con);
            if (loginControl.selectSenha().equals(pfSenhaAtual.getText())) {
                loginControl.updateSenha(pfNovaSenha.getText());
                JOptionPane.showMessageDialog(null, "Nova senha cadastrada com sucesso", "Nova Senha", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            } else {
                throw new Exception("Informe a senha atual");
            }
        } else {
            throw new Exception("Confirme a senha");
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            try {
                ok();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == pfSenhaAtual) {
            pfSenhaAtual.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == pfNovaSenha) {
            pfNovaSenha.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == pfConfirmaNovaSenha) {
            pfConfirmaNovaSenha.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        pfSenhaAtual.setBackground(Color.WHITE);
        pfNovaSenha.setBackground(Color.WHITE);
        if (evento.getSource() == pfConfirmaNovaSenha) {
            pfConfirmaNovaSenha.setBackground(Color.WHITE);
            try {
                ok();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

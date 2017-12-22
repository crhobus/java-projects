package LoginAcesso;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Principal.Principal;

public class LoginAcesso extends JFrame implements ActionListener {

    private static final long serialVersionUID = 2933253294010401981L;
    private JPasswordField pfSenha;
    private JButton btOk;
    private LoginControl controle;
    private Connection con;

    public LoginAcesso(LoginControl controle, Connection con) {
        super("OFICINA E COMÉRCIO TELETRÔNICA LTDA");
        this.controle = controle;
        this.con = con;
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(248, 248, 248));
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(7, 7, 340, 200);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder("LOGIN DE ACESSO"));
        add(panel);

        JLabel lbUsuario = new JLabel("USUÁRIO");
        lbUsuario.setBounds(30, 60, 110, 20);
        lbUsuario.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(lbUsuario);

        JTextField textField = new JTextField();
        textField.setBounds(102, 60, 160, 20);
        textField.setText("Administrador");
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        panel.add(textField);

        JLabel lbSenha = new JLabel("SENHA");
        lbSenha.setBounds(30, 110, 110, 20);
        lbSenha.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(lbSenha);

        pfSenha = new JPasswordField();
        pfSenha.setBounds(102, 110, 160, 20);
        pfSenha.addActionListener(this);
        panel.add(pfSenha);

        btOk = new JButton("OK");
        btOk.setBounds(209, 136, 51, 22);
        btOk.setFont(new Font("Arial", Font.BOLD, 12));
        btOk.setBackground(new Color(238, 207, 161));
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("OK");
        btOk.addActionListener(this);
        panel.add(btOk);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(370, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOk || evento.getSource() == pfSenha) {
            if (!"".equals(pfSenha.getText().trim())) {
                try {
                    if (pfSenha.getText().equals(controle.selectSenha())) {
                        new Principal(con);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha inválida", "Erro", JOptionPane.ERROR_MESSAGE);
                        pfSenha.setText("");
                        pfSenha.grabFocus();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senha inválida", "Erro", JOptionPane.ERROR_MESSAGE);
                pfSenha.setText("");
                pfSenha.grabFocus();
            }
        }
    }
}

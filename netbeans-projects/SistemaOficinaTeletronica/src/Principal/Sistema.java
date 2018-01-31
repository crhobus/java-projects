package Principal;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;

import LoginAcesso.LoginAcesso;
import LoginAcesso.LoginControl;

public class Sistema {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            String local = "jdbc:firebirdsql:localhost/3050:C:/Users/Caio/Dropbox/NetbeansProject/Sistema Oficina Teletronica/Banco de Dados/TELETRONICA.FDB";
            String usuario = "SYSDBA";
            String senha = "masterkey";
            Connection con = DriverManager.getConnection(local, usuario, senha);
            //UIManager.put("ToolTip.background", new ColorUIResource(Color.WHITE)); // altera a cor do ToolTipText
            LoginControl loginControl = new LoginControl(con);
            try {
                if (loginControl.selectSenha() == null) {
                    JPasswordField entrada1 = new JPasswordField();
                    final Object[] mensagem1 = {"Informe uma senha para acessar o sistema", entrada1};
                    final String[] opcoes = {"OK", "Cancelar"};
                    int result;
                    do {
                        result = JOptionPane.showOptionDialog(null, mensagem1, "LOGIN DE ACESSO", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
                        if (result == 0) {
                            if (!"".equals(entrada1.getText().trim())) {
                                if (entrada1.getText().trim().length() < 6) {
                                    JOptionPane.showMessageDialog(null, "Entre com no mínimo 6 caracteres", "Erro", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    break;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Entre com no mínimo 6 caracteres", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            System.exit(1);
                        }
                        entrada1.setText("");
                    } while (true);
                    JPasswordField entrada2 = new JPasswordField();
                    final Object[] mensagem2 = {"Informe novamente a mesma senha", entrada2};
                    do {
                        result = JOptionPane.showOptionDialog(null, mensagem2, "LOGIN DE ACESSO", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
                        if (result == 0) {
                            if (!"".equals(entrada2.getText().trim())) {
                                if (entrada1.getText().equals(entrada2.getText())) {
                                    loginControl.insertSenha(entrada1.getText());
                                    new Principal(con);
                                    break;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Informe a mesma senha nos dois campos", "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Entre com no mínimo 6 caracteres", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            System.exit(1);
                        }
                        entrada2.setText("");
                    } while (true);
                } else {
                    new LoginAcesso(loginControl, con);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer uma conexão com o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Senha extends JFrame {

    private ArrayList<ArrayUsuario> lista = new ArrayList();
    private JTextField Usuario;
    private JPasswordField Camposenha;
    private JButton Ok;
    private String TextoSenha;

    Senha() {
        super("Sistema Java");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 13);
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(15, 15, 300, 190);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("LOGIN DE ACESSO"));
        TrataEventos trata = new TrataEventos();

        JLabel lb_Usuario = new JLabel("USUÁRIO");
        lb_Usuario.setBounds(30, 60, 110, 20);
        lb_Usuario.setFont(fonte);
        Painel1.add(lb_Usuario);

        JLabel lb_Senha = new JLabel("SENHA");
        lb_Senha.setBounds(30, 110, 110, 20);
        lb_Senha.setFont(fonte);
        Painel1.add(lb_Senha);

        Usuario = new JTextField();
        Usuario.setBounds(102, 60, 160, 20);
        Painel1.add(Usuario);

        Camposenha = new JPasswordField();
        Camposenha.setBounds(102, 110, 160, 20);
        Painel1.add(Camposenha);

        Ok = new JButton("OK");
        Ok.setBounds(209, 136, 51, 22);
        Painel1.add(Ok);
        Ok.addActionListener(trata);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(Painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        Painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        LerArquivo();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(350, 258);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setVisible(true);
    }

    private void OK() {
        if ("Administrador".equals(Usuario.getText())) {
            if ("50100".equals(Camposenha.getText())) {
                Principal meu = new Principal();
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Senha Invalida", "Logon no Sistema", JOptionPane.ERROR_MESSAGE);
                Camposenha.setText("");
                Camposenha.grabFocus();
            }
        } else {
            String Texto = Usuario.getText();
            boolean usuarioencontrado = false;
            boolean senhaencontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayUsuario aux = lista.get(i);
                if (Texto.equalsIgnoreCase(aux.getNome())) {//Não importa se texto digitado esta em maiusculo ou minusculo
                    usuarioencontrado = true;
                    int TextoINTSenha = aux.getSenha();
                    TextoSenha = Integer.toString(TextoINTSenha);//Conversão int para String
                }
                if (Camposenha.getText().equals(TextoSenha)) {//Não importa se texto digitado esta em maiusculo ou minusculo
                    senhaencontrado = true;
                }
            }
            if (usuarioencontrado == true) {
                if (senhaencontrado == true) {
                    Principal meu = new Principal();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Senha Invalida", "Logon no Sistema", JOptionPane.ERROR_MESSAGE);
                    Camposenha.setText("");
                    Camposenha.grabFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senha ou Usuario Invalido", "Logon no Sistema", JOptionPane.ERROR_MESSAGE);
                Usuario.setText("");
                Camposenha.setText("");
                Usuario.grabFocus();
            }
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Usuario.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista = (ArrayList<ArrayUsuario>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == Ok) {
                OK();
            }
        }
    }
}

package Cliente;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Formatacao.CamposInt;

public class Cliente extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfNome, tfIP, tfPorta;
    private JTextArea taMsgServ, taMsgClie;
    private JButton btConexao, btEnviar;
    private ThreadCliente thread;

    public Cliente() {
        super("Sistema Chat - Versão Cliente 1.0");
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 770, 502);
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEtchedBorder());

        JLabel lbMsgServ = new JLabel("Mensagens no Servidor");
        lbMsgServ.setBounds(100, 10, 130, 14);
        panel.add(lbMsgServ);

        taMsgServ = new JTextArea();
        taMsgServ.setEditable(false);
        JScrollPane scrollServ = new JScrollPane(taMsgServ);
        scrollServ.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollServ.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollServ.setBounds(10, 28, 350, 460);
        panel.add(scrollServ);

        JLabel lbMsgClie = new JLabel("Mensagens no Cliente");
        lbMsgClie.setBounds(480, 10, 130, 14);
        panel.add(lbMsgClie);

        taMsgClie = new JTextArea();
        JScrollPane scrollClie = new JScrollPane(taMsgClie);
        scrollClie.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollClie.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollClie.setBounds(380, 28, 350, 350);
        panel.add(scrollClie);

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(380, 390, 50, 14);
        panel.add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(380, 408, 210, 28);
        panel.add(tfNome);

        JLabel lbIP = new JLabel("IP");
        lbIP.setBounds(600, 390, 40, 14);
        panel.add(lbIP);

        tfIP = new JTextField();
        tfIP.setBounds(600, 408, 130, 28);
        panel.add(tfIP);

        JLabel lbPorta = new JLabel("Porta");
        lbPorta.setBounds(380, 440, 50, 14);
        panel.add(lbPorta);

        tfPorta = new JTextField();
        tfPorta.setBounds(380, 458, 90, 28);
        tfPorta.setDocument(new CamposInt());
        panel.add(tfPorta);

        btConexao = new JButton("Conectar");
        btConexao.setBounds(490, 456, 78, 30);
        btConexao.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConexao.setToolTipText("Conectar-se com o servidor");
        btConexao.addActionListener(this);
        panel.add(btConexao);

        btEnviar = new JButton("Enviar");
        btEnviar.setBounds(580, 456, 65, 30);
        btEnviar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btEnviar.setToolTipText("Enviar mensagem para o servidor");
        btEnviar.setEnabled(false);
        btEnviar.addActionListener(this);
        panel.add(btEnviar);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void conectar() throws Exception {
        if ("".equals(tfIP.getText().trim())) {
            throw new Exception("Entre com o IP do servidor");
        }
        if ("".equals(tfPorta.getText())) {
            throw new Exception("Entre com a porta do servidor");
        }
        thread = new ThreadCliente(tfIP.getText(), Integer.parseInt(tfPorta.getText()), this);
        thread.start();
    }

    public void atualizaConexao() {
        btConexao.setEnabled(false);
        btConexao.setToolTipText("Conexão já estabelecida");
        btEnviar.setEnabled(true);
        tfPorta.setEditable(false);
        tfIP.setEditable(false);
    }

    public void atualizaDesconexao() {
        tfPorta.setEditable(true);
        tfIP.setEditable(true);
        btConexao.setEnabled(true);
        btConexao.setToolTipText("Conectar-se com o servidor");
        btEnviar.setEnabled(false);
    }

    public void mostrarMsgServidor(String msg) {
        taMsgServ.append(msg + "\n");
        taMsgServ.setCaretPosition(taMsgServ.getText().length());
    }

    private void enviar() throws Exception {
        if ("".equals(tfNome.getText().trim())) {
            throw new Exception("Entre com o seu o seu nome");
        }
        if ("".equals(taMsgClie.getText().trim())) {
            throw new Exception("Entre com uma mensagem");
        }
        thread.getPrintStream().println("Id: " + tfNome.getText() + "\nMsg: " + taMsgClie.getText() + "\n");
        taMsgClie.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btConexao) {
            try {
                conectar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro inesperado", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btEnviar) {
            try {
                enviar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

package Servidor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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

public class Servidor extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfPorta;
    private JTextArea taMsgServ;
    private JButton btStart;
    private List<PrintWriter> msgClientes;

    public Servidor() {
        super("Sistema Chat - Versão Servidor 1.0");
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 422, 502);
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEtchedBorder());

        JLabel lbMsgServ = new JLabel("Mensagens no Servidor");
        lbMsgServ.setBounds(140, 10, 130, 14);
        panel.add(lbMsgServ);

        taMsgServ = new JTextArea();
        taMsgServ.setEditable(false);
        JScrollPane scrollServ = new JScrollPane(taMsgServ);
        scrollServ.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollServ.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollServ.setBounds(10, 28, 400, 405);
        panel.add(scrollServ);

        JLabel lbIP = new JLabel("Porta");
        lbIP.setBounds(20, 466, 120, 14);
        try {
            lbIP.setText("IP: " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar-se com a internet", "Erro inesperado", JOptionPane.ERROR_MESSAGE);
        }
        panel.add(lbIP);

        JLabel lbPorta = new JLabel("Porta");
        lbPorta.setBounds(140, 440, 50, 14);
        panel.add(lbPorta);

        tfPorta = new JTextField();
        tfPorta.setBounds(140, 458, 90, 28);
        tfPorta.setDocument(new CamposInt());
        panel.add(tfPorta);

        btStart = new JButton("Start");
        btStart.setBounds(240, 456, 80, 30);
        btStart.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btStart.setToolTipText("Iniciar o servidor");
        btStart.addActionListener(this);
        panel.add(btStart);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evento) {
                fecharConexoes();
            }
        });

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(450, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void start() throws Exception {
        if ("".equals(tfPorta.getText())) {
            throw new Exception("Campo porta inválido");
        }
        btStart.setEnabled(false);
        tfPorta.setEditable(false);
        msgClientes = new ArrayList<PrintWriter>();
        ThreadServidor thread = new ThreadServidor(this, Integer.parseInt(tfPorta.getText()));
        thread.start();
        btStart.setToolTipText("Servidor já iniciado");
    }

    public void addMsgCliente(PrintWriter writer) {
        msgClientes.add(writer);
    }

    public void enviarMsgCliente(String msg) {
        taMsgServ.setText(taMsgServ.getText() + msg + "\n");
        if (!"".equals(msg.trim())) {
            msg = msg.substring(msg.indexOf(">>  ") + 4, msg.length());
        } else {
            msg = "";
        }
        for (PrintWriter writer : msgClientes) {
            writer.println(msg); // envia messagem para um cliente da lista
            writer.flush(); // esvazia o buffer e envia todas msgs para o destino
        }
        taMsgServ.setCaretPosition(taMsgServ.getText().length());
    }

    public void desconectar() {
        tfPorta.setEditable(true);
        taMsgServ.setText("");
        btStart.setEnabled(true);
        btStart.setToolTipText("Iniciar o servidor");
    }

    public void fecharConexoes() {
        if (msgClientes != null && !msgClientes.isEmpty()) {
            for (PrintWriter writer : msgClientes) {
                writer.close();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btStart) {
            try {
                start();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

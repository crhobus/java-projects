package Seguranca;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class CertificadoDigital extends JDialog implements ActionListener, FocusListener {

    private JTextField tfArquivoKeyStore, tfArquivoCertificado, tfAliasKeyStore;
    private JPasswordField pfSenhaKeyStore;
    private JButton btOk, btCancelar, btArquivoKeyStore, btArquivoCertificado;
    private Seguranca seguranca;

    public CertificadoDigital(Seguranca seguranca) {
        this.seguranca = seguranca;
        initComponents();
        setInfo();
    }

    private void initComponents() {
        setTitle("Certificado Digital");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 473, 262);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        add(panel);

        JLabel lbArquivoCertificado = new JLabel("Certificado");
        lbArquivoCertificado.setBounds(20, 20, 60, 14);
        panel.add(lbArquivoCertificado);

        tfArquivoCertificado = new JTextField();
        tfArquivoCertificado.setBounds(20, 38, 400, 20);
        tfArquivoCertificado.setEditable(false);
        tfArquivoCertificado.setBackground(Color.WHITE);
        tfArquivoCertificado.addFocusListener(this);
        panel.add(tfArquivoCertificado);

        btArquivoCertificado = new JButton("...");
        btArquivoCertificado.setBounds(426, 35, 31, 26);
        btArquivoCertificado.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btArquivoCertificado.setToolTipText("Arquivo Certificado");
        btArquivoCertificado.addActionListener(this);
        panel.add(btArquivoCertificado);

        JLabel lbArquivoKeyStore = new JLabel("Key Store");
        lbArquivoKeyStore.setBounds(20, 63, 50, 14);
        panel.add(lbArquivoKeyStore);

        tfArquivoKeyStore = new JTextField();
        tfArquivoKeyStore.setBounds(20, 81, 400, 20);
        tfArquivoKeyStore.setEditable(false);
        tfArquivoKeyStore.setBackground(Color.WHITE);
        tfArquivoKeyStore.addFocusListener(this);
        panel.add(tfArquivoKeyStore);

        btArquivoKeyStore = new JButton("...");
        btArquivoKeyStore.setBounds(426, 78, 31, 26);
        btArquivoKeyStore.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btArquivoKeyStore.setToolTipText("Arquivo Key Store");
        btArquivoKeyStore.addActionListener(this);
        panel.add(btArquivoKeyStore);

        JLabel lbAliasKeyStore = new JLabel("Alias");
        lbAliasKeyStore.setBounds(20, 105, 50, 14);
        panel.add(lbAliasKeyStore);

        tfAliasKeyStore = new JTextField();
        tfAliasKeyStore.setBounds(20, 123, 180, 20);
        tfAliasKeyStore.addFocusListener(this);
        panel.add(tfAliasKeyStore);

        JLabel lbSenhaKeyStore = new JLabel("Senha");
        lbSenhaKeyStore.setBounds(20, 147, 50, 14);
        panel.add(lbSenhaKeyStore);

        pfSenhaKeyStore = new JPasswordField();
        pfSenhaKeyStore.setBounds(20, 165, 120, 20);
        pfSenhaKeyStore.addFocusListener(this);
        panel.add(pfSenhaKeyStore);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 206, 750, 3);
        panel.add(separator);

        Icon icOk = new ImageIcon("OK.png");
        btOk = new JButton("OK", icOk);
        btOk.setBounds(105, 221, 70, 26);
        btOk.setMargin(new Insets(0, 0, 0, 0));
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("OK");
        btOk.addActionListener(this);
        panel.add(btOk);

        Icon icCancelar = new ImageIcon("Cancelar.png");
        btCancelar = new JButton("Cancelar", icCancelar);
        btCancelar.setBounds(285, 221, 70, 26);
        btCancelar.setMargin(new Insets(0, 0, 0, 0));
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        setSize(490, 300);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void setInfo() {
        tfArquivoCertificado.setText(seguranca.getArquivoCertificado());
        tfArquivoKeyStore.setText(seguranca.getArquivoKeyStore());
        tfAliasKeyStore.setText(seguranca.getAliasKeyStore());
        pfSenhaKeyStore.setText(seguranca.getSenhaKeyStore());
    }

    private void setInfoSeguranca() throws Exception {
        if ("".equals(tfArquivoCertificado.getText().trim())) {
            tfArquivoCertificado.requestFocus();
            throw new Exception("Campo certificado deve estar preenchido");
        }
        if ("".equals(tfArquivoKeyStore.getText().trim())) {
            tfArquivoKeyStore.requestFocus();
            throw new Exception("Campo key store deve estar preenchido");
        }
        if ("".equals(tfAliasKeyStore.getText().trim())) {
            tfAliasKeyStore.requestFocus();
            throw new Exception("Campo alias deve estar preenchido");
        }
        if ("".equals(new String(pfSenhaKeyStore.getPassword()).trim())) {
            pfSenhaKeyStore.requestFocus();
            throw new Exception("Campo senha deve estar preenchido");
        }
        seguranca.setArquivoCertificado(tfArquivoCertificado.getText());
        seguranca.setArquivoKeyStore(tfArquivoKeyStore.getText());
        seguranca.setAliasKeyStore(tfAliasKeyStore.getText());
        seguranca.setSenhaKeyStore(new String(pfSenhaKeyStore.getPassword()));
    }

    private void carregarArquivo(boolean certificado) {
        JFileChooser fileChooser = new JFileChooser();
        FiltroArquivos filtro;
        if (certificado) {
            filtro = new FiltroArquivos("DER (.cer, .crt)");
            filtro.addExtencao("cer");
            filtro.addExtencao("crt");
        } else {
            filtro = new FiltroArquivos("JKS (.jks, .ks)");
            filtro.addExtencao("jks");
        }
        fileChooser.addChoosableFileFilter(filtro);
        fileChooser.setFileFilter(filtro);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Abrir");
        fileChooser.setApproveButtonText("Abrir");
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            if (certificado) {
                tfArquivoCertificado.setText(arquivo.getPath());
            } else {
                tfArquivoKeyStore.setText(arquivo.getPath());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btOk) {
            try {
                setInfoSeguranca();
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (evt.getSource() == btCancelar) {
            this.dispose();
        }
        if (evt.getSource() == btArquivoCertificado) {
            carregarArquivo(true);
        }
        if (evt.getSource() == btArquivoKeyStore) {
            carregarArquivo(false);
        }
    }

    @Override
    public void focusGained(FocusEvent evt) {
        if (evt.getSource() == tfArquivoCertificado) {
            tfArquivoCertificado.setBackground(Color.YELLOW);
        }
        if (evt.getSource() == tfArquivoKeyStore) {
            tfArquivoKeyStore.setBackground(Color.YELLOW);
        }
        if (evt.getSource() == tfAliasKeyStore) {
            tfAliasKeyStore.setBackground(Color.YELLOW);
        }
        if (evt.getSource() == pfSenhaKeyStore) {
            pfSenhaKeyStore.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evtf) {
        tfArquivoCertificado.setBackground(Color.WHITE);
        tfArquivoKeyStore.setBackground(Color.WHITE);
        tfAliasKeyStore.setBackground(Color.WHITE);
        pfSenhaKeyStore.setBackground(Color.WHITE);
    }
}

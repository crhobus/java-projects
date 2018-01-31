package view.login;

import control.Servidor;
import control.funcoes.ExceptionError;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.entidades.Usuario;
import view.componentes.document.CampoMaiusculoMinusculo;
import view.desktop.SisComPizzaria;

public class Login extends JFrame {

    private Servidor serv;

    public Login(Servidor serv) {
        this.serv = serv;
        initComponents();
    }

    public void ativar() {
        setLocationRelativeTo(null);
        setVisible(true);
        tfUsuario.setText("admin");
        pfSenha.setText("key50100");
        btOk.grabFocus();
    }

    private void autenticacao() {
        try {
            Usuario usuario = serv.getUsuarioAction().autenticacao(panelDados.getDadosPanel(null));
            SisComPizzaria sisCom = new SisComPizzaria(serv, usuario);
            sisCom.ativar();
            this.dispose();
        } catch (ExceptionError ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
            panelDados.setFocusCampo(ex.getDsCampoFocar());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDados = new view.componentes.TPanel();
        pfSenha = new javax.swing.JPasswordField();
        tfUsuario = new javax.swing.JTextField();
        lbUsuario = new javax.swing.JLabel();
        lbSenha = new javax.swing.JLabel();
        separador = new javax.swing.JSeparator();
        btOk = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        lbFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pizza Nostra - Login");
        setPreferredSize(new java.awt.Dimension(493, 275));
        setResizable(false);
        getContentPane().setLayout(null);

        panelDados.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelDados.setLayout(null);

        pfSenha.setName("DS_SENHA");
        pfSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfSenhaActionPerformed(evt);
            }
        });
        panelDados.add(pfSenha);
        pfSenha.setBounds(125, 100, 260, 30);

        tfUsuario.setDocument(new CampoMaiusculoMinusculo(false));
        tfUsuario.setName("NM_USUARIO");
        panelDados.add(tfUsuario);
        tfUsuario.setBounds(125, 50, 260, 30);

        lbUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lbUsuario.setText("Usuário");
        panelDados.add(lbUsuario);
        lbUsuario.setBounds(55, 57, 60, 14);

        lbSenha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbSenha.setForeground(new java.awt.Color(255, 255, 255));
        lbSenha.setText("Senha");
        panelDados.add(lbSenha);
        lbSenha.setBounds(64, 107, 50, 14);
        panelDados.add(separador);
        separador.setBounds(0, 155, 482, 5);

        btOk.setText("OK");
        btOk.setToolTipText("Entrar no sistema");
        btOk.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });
        panelDados.add(btOk);
        btOk.setBounds(98, 178, 100, 40);

        btCancelar.setText("Cancelar");
        btCancelar.setToolTipText("Sair");
        btCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        panelDados.add(btCancelar);
        btCancelar.setBounds(280, 178, 100, 40);

        lbFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Fundo login.png"))); // NOI18N
        panelDados.add(lbFundo);
        lbFundo.setBounds(0, 0, 482, 242);

        getContentPane().add(panelDados);
        panelDados.setBounds(2, 2, 484, 244);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pfSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfSenhaActionPerformed
        autenticacao();
    }//GEN-LAST:event_pfSenhaActionPerformed

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        autenticacao();
    }//GEN-LAST:event_btOkActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btCancelarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btOk;
    private javax.swing.JLabel lbFundo;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbUsuario;
    private view.componentes.TPanel panelDados;
    private javax.swing.JPasswordField pfSenha;
    private javax.swing.JSeparator separador;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}

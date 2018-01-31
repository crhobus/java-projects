package view.desktop.usuario;

import control.Servidor;
import control.funcoes.Excecao;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.entidades.Usuario;
import view.desktop.SisComPizzaria;

public class TrocarSenhaUsuario extends JDialog {

    private SisComPizzaria sisCom;

    public TrocarSenhaUsuario(SisComPizzaria sisCom, boolean modal) {
        super(sisCom, modal);
        this.sisCom = sisCom;
        initComponents();
    }

    public void ativar() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Servidor getServidor() {
        return sisCom.getServidor();
    }

    public Usuario getUsuario() {
        return sisCom.getUsuario();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnCentro = new view.componentes.TPanel();
        pfConfirmaSenha = new javax.swing.JPasswordField();
        lbConfirmaSenha = new javax.swing.JLabel();
        pfNovaSenha = new javax.swing.JPasswordField();
        lbNovaSenha = new javax.swing.JLabel();
        pfSenhaAtual = new javax.swing.JPasswordField();
        lbSenhaAtual = new javax.swing.JLabel();
        pnBotoes = new javax.swing.JPanel();
        btOk = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Trocar Senha");
        setModal(true);
        setPreferredSize(new java.awt.Dimension(350, 215));
        setResizable(false);

        pnCentro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnCentro.setLayout(null);

        pfConfirmaSenha.setName("DS_CONFIRMA_SENHA");
        pnCentro.add(pfConfirmaSenha);
        pfConfirmaSenha.setBounds(100, 86, 150, 20);

        lbConfirmaSenha.setText("Confirma Senha");
        pnCentro.add(lbConfirmaSenha);
        lbConfirmaSenha.setBounds(18, 88, 76, 14);

        pfNovaSenha.setName("DS_NOVA_SENHA");
        pnCentro.add(pfNovaSenha);
        pfNovaSenha.setBounds(100, 58, 150, 20);

        lbNovaSenha.setText("Nova Senha");
        pnCentro.add(lbNovaSenha);
        lbNovaSenha.setBounds(36, 60, 60, 14);

        pfSenhaAtual.setName("DS_SENHA_ATUAL");
        pnCentro.add(pfSenhaAtual);
        pfSenhaAtual.setBounds(100, 30, 150, 20);

        lbSenhaAtual.setText("Senha Atual");
        pnCentro.add(lbSenhaAtual);
        lbSenhaAtual.setBounds(35, 32, 60, 14);

        getContentPane().add(pnCentro, java.awt.BorderLayout.CENTER);

        pnBotoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnBotoes.setPreferredSize(new java.awt.Dimension(0, 50));
        pnBotoes.setLayout(null);

        btOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/OK.png"))); // NOI18N
        btOk.setText("OK");
        btOk.setToolTipText("OK");
        btOk.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btOk.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });
        pnBotoes.add(btOk);
        btOk.setBounds(65, 10, 80, 30);

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Cancelar.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.setToolTipText("Cancelar");
        btCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btCancelar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        pnBotoes.add(btCancelar);
        btCancelar.setBounds(195, 10, 80, 30);

        getContentPane().add(pnBotoes, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        Excecao excecao = null;
        try {
            if (getServidor().getUsuarioAction().alterarSenhaUsuario(pnCentro.getDadosPanel(null), getUsuario())) {
                this.dispose();
            }
        } catch (ExceptionInfo ex) {
            excecao = ex;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Pizza Nostra - Usuário", JOptionPane.INFORMATION_MESSAGE);
        } catch (ExceptionError ex) {
            excecao = ex;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
        if (excecao != null) {
            pnCentro.setFocusCampo(excecao.getDsCampoFocar());
        }
    }//GEN-LAST:event_btOkActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btOk;
    private javax.swing.JLabel lbConfirmaSenha;
    private javax.swing.JLabel lbNovaSenha;
    private javax.swing.JLabel lbSenhaAtual;
    private javax.swing.JPasswordField pfConfirmaSenha;
    private javax.swing.JPasswordField pfNovaSenha;
    private javax.swing.JPasswordField pfSenhaAtual;
    private javax.swing.JPanel pnBotoes;
    private view.componentes.TPanel pnCentro;
    // End of variables declaration//GEN-END:variables
}

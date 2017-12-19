package exercicios.empresaPromocoesCulturais.apresentacao;

import exercicios.empresaPromocoesCulturais.negocio.Obra;
import exercicios.empresaPromocoesCulturais.negocio.Parecer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author CaioRenan
 */
public class Apresentacao extends javax.swing.JFrame {

    private Obra obra;
    private HashMap<String, Obra> acervo;

    public Apresentacao() {
        initComponents();
        acervo = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTituloObra = new javax.swing.JLabel();
        lbAutor = new javax.swing.JLabel();
        tfTituloObra = new javax.swing.JTextField();
        tfAutor = new javax.swing.JTextField();
        btCadastrar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lbNmParecerista = new javax.swing.JLabel();
        tfNmParecerista = new javax.swing.JTextField();
        blData = new javax.swing.JLabel();
        tfData = new javax.swing.JTextField();
        lbConteudo = new javax.swing.JLabel();
        spConteudo = new javax.swing.JScrollPane();
        taConteudo = new javax.swing.JTextArea();
        btIncluirObra = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lbPesquisar = new javax.swing.JLabel();
        tfPesquisar = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(430, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        lbTituloObra.setText("Título da obra");
        getContentPane().add(lbTituloObra);
        lbTituloObra.setBounds(20, 30, 70, 14);

        lbAutor.setText("Autor");
        getContentPane().add(lbAutor);
        lbAutor.setBounds(20, 60, 70, 14);
        getContentPane().add(tfTituloObra);
        tfTituloObra.setBounds(90, 27, 250, 20);
        getContentPane().add(tfAutor);
        tfAutor.setBounds(90, 57, 250, 20);

        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(btCadastrar);
        btCadastrar.setBounds(260, 90, 81, 30);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(5, 130, 412, 5);

        lbNmParecerista.setText("Nome Pareceristas");
        getContentPane().add(lbNmParecerista);
        lbNmParecerista.setBounds(20, 145, 90, 14);
        getContentPane().add(tfNmParecerista);
        tfNmParecerista.setBounds(114, 142, 226, 20);

        blData.setText("Data");
        getContentPane().add(blData);
        blData.setBounds(20, 175, 90, 14);
        getContentPane().add(tfData);
        tfData.setBounds(114, 172, 226, 20);

        lbConteudo.setText("Conteudo");
        getContentPane().add(lbConteudo);
        lbConteudo.setBounds(20, 205, 90, 14);

        taConteudo.setColumns(20);
        taConteudo.setRows(5);
        spConteudo.setViewportView(taConteudo);

        getContentPane().add(spConteudo);
        spConteudo.setBounds(114, 202, 226, 100);

        btIncluirObra.setText("Incluir Obra");
        btIncluirObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirObraActionPerformed(evt);
            }
        });
        getContentPane().add(btIncluirObra);
        btIncluirObra.setBounds(251, 315, 90, 30);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(5, 355, 412, 5);

        lbPesquisar.setText("Pesquisar Obra");
        getContentPane().add(lbPesquisar);
        lbPesquisar.setBounds(20, 370, 80, 14);
        getContentPane().add(tfPesquisar);
        tfPesquisar.setBounds(114, 367, 226, 20);

        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisar);
        btPesquisar.setBounds(251, 400, 90, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        try {
            obra = new Obra(tfTituloObra.getText(), tfAutor.getText());
            acervo.put(tfTituloObra.getText(), obra);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Obra", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btIncluirObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirObraActionPerformed
        try {
            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(tfData.getText());
            Parecer p = new Parecer(tfNmParecerista.getText(), data, taConteudo.getText());
            obra.addParecer(p);
            JOptionPane.showMessageDialog(this, "Parecer incluído", "Obra", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Data digitada é inválida", "Obra", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Cadastre ou pesquise uma obra antes de incluir um parecer", "Obra", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btIncluirObraActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        Obra obraPesquisada = acervo.get(tfPesquisar.getText());
        if (obraPesquisada == null) {
            JOptionPane.showMessageDialog(this, "Obra não encontrada", "Obra", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, obraPesquisada.toString(), "Obra", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btPesquisarActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Apresentacao apresentacao = new Apresentacao();
                apresentacao.setLocationRelativeTo(null);
                apresentacao.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel blData;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btIncluirObra;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbAutor;
    private javax.swing.JLabel lbConteudo;
    private javax.swing.JLabel lbNmParecerista;
    private javax.swing.JLabel lbPesquisar;
    private javax.swing.JLabel lbTituloObra;
    private javax.swing.JScrollPane spConteudo;
    private javax.swing.JTextArea taConteudo;
    private javax.swing.JTextField tfAutor;
    private javax.swing.JTextField tfData;
    private javax.swing.JTextField tfNmParecerista;
    private javax.swing.JTextField tfPesquisar;
    private javax.swing.JTextField tfTituloObra;
    // End of variables declaration//GEN-END:variables
}

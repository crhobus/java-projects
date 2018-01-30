package trabalhoclusterizacao;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/*Equipe: Caio Renan Hobus
          Matheus Bortolon*/
public final class Sistema extends javax.swing.JFrame {

    DisplayTwoSynchronizedImages duas;
    Legenda legenda;
    BufferedImage image;

    public Sistema() throws IOException {
        initComponents();
        File file = this.escolheArquivo();
        BufferedImage imagem = ImageIO.read(file);
        BufferedImage resultado = ImageIO.read(file);
        TrabalhoClusterizacao t = new TrabalhoClusterizacao();

        //exibe
        this.setTitle("Identificação de áreas urbanas");
        this.setLayout(new FlowLayout());
        this.duas = new DisplayTwoSynchronizedImages(imagem, resultado);
        this.add(duas);
        this.legenda = new Legenda(duas, file, t, this);
        this.legenda.setLegenda1("Vegetação");
        this.legenda.setLegenda2("Domicílios");
        this.legenda.setLegenda3("Indústrias");
        this.add(legenda);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //fim exibe            
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setImage(BufferedImage image) {
        this.image = image;
        this.duas.setImage2(image);
        this.atualiza(image);
    }

    public File escolheArquivo() {
        JFileChooser f = new JFileChooser("D:/Documetos/_Facul/NetBeansProjects/TrabalhoClusterizacao/src/Blumenau.jpg");
        f.setFileFilter(new javax.swing.filechooser.FileFilter() {

            public boolean accept(File f) {
                return (f.getName().toUpperCase().endsWith(".JPG")) || f.isDirectory();
            }

            public String getDescription() {
                return "*.jpg";
            }
        });
        f.setControlButtonsAreShown(false);
        int result = f.showOpenDialog(f);
        if (result != JFileChooser.APPROVE_OPTION) {
            System.exit(0);
        }
        return f.getSelectedFile();
    }

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new Sistema().setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public BufferedImage getImage() {
        return image;
    }

    public void atualiza(BufferedImage image2) {
        this.duas.setImage1(image);
        this.duas.setImage2(image2);
        this.pack();
        this.revalidate();
        this.setLocationRelativeTo(null);
    }
}

package trabalhoclusterizacao;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public final class Legenda extends javax.swing.JPanel {

    private TrabalhoClusterizacao t;
    private Sistema s;
    private AtualizadorStatus a;

    public Legenda(DisplayTwoSynchronizedImages duas, File imagem, TrabalhoClusterizacao t, Sistema s) throws IOException {
        initComponents();
        this.setCor1(new Color(80, 100, 70));
        this.setCor2(new Color(167, 150, 100));
        this.setCor3(Color.white);
        this.PreRealce.setEnabled(false);
        this.PosRealce.setEnabled(false);
        this.t = t;
        this.s = s;
        this.setImagem(ImageIO.read(imagem));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Cor1 = new javax.swing.JLabel();
        Cor2 = new javax.swing.JLabel();
        Cor3 = new javax.swing.JLabel();
        Legenda1 = new javax.swing.JLabel();
        Legenda2 = new javax.swing.JLabel();
        Legenda3 = new javax.swing.JLabel();
        Legenda4 = new javax.swing.JLabel();
        Status = new javax.swing.JLabel();
        Tempo = new javax.swing.JLabel();
        Imagem = new javax.swing.JButton();
        Calcular = new javax.swing.JButton();
        Realce = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        SigmaS = new javax.swing.JTextField();
        SigmaR = new javax.swing.JTextField();
        MinRegiao = new javax.swing.JTextField();
        PosRealce = new javax.swing.JRadioButton();
        PreRealce = new javax.swing.JRadioButton();

        Cor1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Cor1.setMaximumSize(new java.awt.Dimension(10, 10));
        Cor1.setMinimumSize(new java.awt.Dimension(10, 10));
        Cor1.setOpaque(true);
        Cor1.setPreferredSize(new java.awt.Dimension(20, 20));

        Cor2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Cor2.setMaximumSize(new java.awt.Dimension(10, 10));
        Cor2.setMinimumSize(new java.awt.Dimension(10, 10));
        Cor2.setOpaque(true);
        Cor2.setPreferredSize(new java.awt.Dimension(20, 20));

        Cor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Cor3.setMaximumSize(new java.awt.Dimension(10, 10));
        Cor3.setMinimumSize(new java.awt.Dimension(10, 10));
        Cor3.setOpaque(true);
        Cor3.setPreferredSize(new java.awt.Dimension(20, 20));

        Legenda1.setFont(new java.awt.Font("Tahoma", 0, 10));
        Legenda1.setMaximumSize(new java.awt.Dimension(100, 100));
        Legenda1.setMinimumSize(new java.awt.Dimension(100, 10));
        Legenda1.setPreferredSize(new java.awt.Dimension(100, 20));

        Legenda2.setFont(new java.awt.Font("Tahoma", 0, 10));
        Legenda2.setMaximumSize(new java.awt.Dimension(100, 100));
        Legenda2.setMinimumSize(new java.awt.Dimension(100, 10));
        Legenda2.setPreferredSize(new java.awt.Dimension(100, 20));

        Legenda3.setFont(new java.awt.Font("Tahoma", 0, 10));
        Legenda3.setMaximumSize(new java.awt.Dimension(100, 100));
        Legenda3.setMinimumSize(new java.awt.Dimension(100, 10));
        Legenda3.setPreferredSize(new java.awt.Dimension(100, 20));

        Legenda4.setFont(new java.awt.Font("Tahoma", 1, 11));
        Legenda4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Legenda4.setText("Legenda");
        Legenda4.setMaximumSize(new java.awt.Dimension(100, 100));
        Legenda4.setMinimumSize(new java.awt.Dimension(100, 10));
        Legenda4.setPreferredSize(new java.awt.Dimension(100, 20));

        Status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Status.setMaximumSize(new java.awt.Dimension(100, 100));
        Status.setMinimumSize(new java.awt.Dimension(100, 10));
        Status.setPreferredSize(new java.awt.Dimension(100, 20));

        Tempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tempo.setMaximumSize(new java.awt.Dimension(100, 100));
        Tempo.setMinimumSize(new java.awt.Dimension(100, 10));
        Tempo.setPreferredSize(new java.awt.Dimension(100, 20));

        Imagem.setText("Alterar Imagem");
        Imagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImagemActionPerformed(evt);
            }
        });

        Calcular.setText("Processar Imagem");
        Calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalcularActionPerformed(evt);
            }
        });

        Realce.setText("Realce");
        Realce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealceActionPerformed(evt);
            }
        });

        jLabel1.setText("SigmaS:");

        jLabel2.setText("SigmaR:");

        jLabel3.setText("MinRegiao:");

        SigmaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SigmaSActionPerformed(evt);
            }
        });

        MinRegiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MinRegiaoActionPerformed(evt);
            }
        });

        PosRealce.setText("pós");
        PosRealce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PosRealceActionPerformed(evt);
            }
        });

        PreRealce.setText("pré");
        PreRealce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreRealceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Realce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PreRealce, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PosRealce, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cor1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cor3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cor2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Legenda1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(Legenda2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(Legenda3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                    .addComponent(Legenda4, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(Imagem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SigmaS, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                    .addComponent(Tempo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(Status, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(Calcular, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MinRegiao, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SigmaR, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Legenda4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Legenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Legenda2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Legenda3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Imagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Realce)
                    .addComponent(PreRealce)
                    .addComponent(PosRealce))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(SigmaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SigmaR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MinRegiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Calcular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImagemActionPerformed
        try {
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
            if (result == JFileChooser.APPROVE_OPTION) {
                this.setImagem(ImageIO.read(f.getSelectedFile()));
            }
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_ImagemActionPerformed

    public void setImagem(BufferedImage image) {
        s.setImage(image);
    }

    private void CalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalcularActionPerformed
        SimpleDateFormat formater = new SimpleDateFormat("mm:ss");
        this.setTempo("Tempo : " + formater.format(new Date().getTime() - new Date().getTime()));
        a = new AtualizadorStatus(getThis());
        a.start();
        calcular();
    }//GEN-LAST:event_CalcularActionPerformed
    public Legenda getThis() {
        return this;
    }

    public void calcular() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                setStatus("Iniciando");
                int ss = ((!SigmaS.getText().isEmpty()) ? Integer.parseInt(SigmaS.getText()) : 0);
                float sr = ((!SigmaR.getText().isEmpty()) ? Float.parseFloat(SigmaR.getText()) : 0f);
                int mr = ((!MinRegiao.getText().isEmpty()) ? Integer.parseInt(MinRegiao.getText()) : 0);
                BufferedImage temp = s.getImage();

                if (Realce.isSelected() && PreRealce.isSelected()) {
                    setStatus("Convertendo");
                    temp = t.converter(temp);
                    s.atualiza(temp);

                    setStatus("Realcar");
                    temp = t.realcar(temp);
                    s.atualiza(temp);

                    setStatus("Reconvertendo");
                    temp = t.reconverter(temp);
                    s.atualiza(temp);
                }

                setStatus("Aplicando Meanshift");
                temp = t.aplicaMeanShift(temp, ss, sr, mr);

                if (Realce.isSelected() && PosRealce.isSelected()) {
                    setStatus("Convertendo");
                    temp = t.converter(temp);
                    s.atualiza(temp);

                    setStatus("Realcar");
                    temp = t.realcar(temp);
                    s.atualiza(temp);

                    setStatus("Reconvertendo");
                    temp = t.reconverter(temp);
                    s.atualiza(temp);
                }


                SimpleDateFormat d = new SimpleDateFormat("mm:ss");
                setStatus("Concluído");
                s.atualiza(temp);
                a.interrupt();
            }
        });
        thread.start();

    }

    private void MinRegiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MinRegiaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MinRegiaoActionPerformed

    private void SigmaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SigmaSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SigmaSActionPerformed

    private void PreRealceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreRealceActionPerformed
        if (this.PreRealce.isSelected()) {
            this.PosRealce.setSelected(false);
        }
    }//GEN-LAST:event_PreRealceActionPerformed

    private void PosRealceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PosRealceActionPerformed
        if (this.PosRealce.isSelected()) {
            this.PreRealce.setSelected(false);
        }
    }//GEN-LAST:event_PosRealceActionPerformed

    private void RealceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealceActionPerformed
        if (this.Realce.isSelected()) {
            this.PreRealce.setEnabled(true);
            this.PreRealce.setSelected(true);
            this.PosRealce.setEnabled(true);
            this.setCor1(Color.green);
            this.setCor2(Color.red);
            this.setCor3(Color.white);
            this.repaint();
        } else {
            this.PreRealce.setEnabled(false);
            this.PreRealce.setSelected(false);
            this.PosRealce.setEnabled(false);
            this.PosRealce.setSelected(false);
            this.setCor1(new Color(80, 100, 70));
            this.setCor2(new Color(167, 150, 100));
            this.setCor3(Color.white);
            this.repaint();
        }
    }//GEN-LAST:event_RealceActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Calcular;
    private javax.swing.JLabel Cor1;
    private javax.swing.JLabel Cor2;
    private javax.swing.JLabel Cor3;
    private javax.swing.JButton Imagem;
    private javax.swing.JLabel Legenda1;
    private javax.swing.JLabel Legenda2;
    private javax.swing.JLabel Legenda3;
    private javax.swing.JLabel Legenda4;
    private javax.swing.JTextField MinRegiao;
    private javax.swing.JRadioButton PosRealce;
    private javax.swing.JRadioButton PreRealce;
    private javax.swing.JCheckBox Realce;
    private javax.swing.JTextField SigmaR;
    private javax.swing.JTextField SigmaS;
    private javax.swing.JLabel Status;
    private javax.swing.JLabel Tempo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    public void setCor1(Color Cor) {
        this.Cor1.setBackground(Cor);
    }

    public void setCor2(Color Cor) {
        this.Cor2.setBackground(Cor);
    }

    public void setCor3(Color Cor) {
        this.Cor3.setBackground(Cor);
    }

    public void setLegenda1(String Legenda) {
        this.Legenda1.setText(Legenda);
    }

    public void setLegenda2(String Legenda) {
        this.Legenda2.setText(Legenda);
    }

    public void setLegenda3(String Legenda) {
        this.Legenda3.setText(Legenda);
    }

    public void setLegenda4(String Legenda) {
        this.Legenda4.setText(Legenda);
    }

    public void setStatus(String status) {
        this.Status.setText(status);
    }

    public void setTempo(String tempo) {
        this.Tempo.setText(tempo);
    }

    String getStatus() {
        return this.Status.getText();
    }
}

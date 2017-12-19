package exercicios.sindicatoJogadoresFutebol;

import javax.swing.JOptionPane;

public class Apresentacao extends javax.swing.JFrame {

    private Sindicato sind = new Sindicato();

    public Apresentacao() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jNomeClubeTF = new javax.swing.JTextField();
        jCidadeClubeTF = new javax.swing.JTextField();
        jIncluirClubeButton = new javax.swing.JButton();
        jExibirClubeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jClubeTA = new javax.swing.JTextArea();
        jClubeExibirComboBox = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jClubeComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jNomeJogadorTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSituacaoComboBox = new javax.swing.JComboBox();
        jSalarioJogadorTF = new javax.swing.JTextField();
        jIncluirJogadorButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jMenorSalarioButton = new javax.swing.JButton();
        jAcimaMediaButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jConsultaTA = new javax.swing.JTextArea();
        jTotaisButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Exercício 4 - Lista 2");

        jLabel1.setText("Nome do clube:");

        jLabel2.setText("Cidade-sede:");

        jIncluirClubeButton.setText("Incluir Clube");
        jIncluirClubeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIncluirClubeButtonActionPerformed(evt);
            }
        });

        jExibirClubeButton.setText("Exibir dados do clube");
        jExibirClubeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExibirClubeButtonActionPerformed(evt);
            }
        });

        jClubeTA.setColumns(20);
        jClubeTA.setRows(5);
        jScrollPane1.setViewportView(jClubeTA);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jNomeClubeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCidadeClubeTF))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jClubeExibirComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140)))
                        .addContainerGap(200, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jExibirClubeButton)
                        .addGap(18, 18, 18))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jIncluirClubeButton)
                .addContainerGap(282, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jNomeClubeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCidadeClubeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jIncluirClubeButton)
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jExibirClubeButton)
                    .addComponent(jClubeExibirComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Clube", jPanel1);

        jLabel3.setText("Clube:");

        jLabel4.setText("Nome:");

        jLabel5.setText("Situação:");

        jLabel6.setText("Salário atual: R$");

        jSituacaoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Titular", "Reserva" }));

        jIncluirJogadorButton.setText("Incluir jogador");
        jIncluirJogadorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIncluirJogadorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jSituacaoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jNomeJogadorTF, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jClubeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jIncluirJogadorButton)
                                    .addComponent(jSalarioJogadorTF, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jClubeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jNomeJogadorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSituacaoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jSalarioJogadorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jIncluirJogadorButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Jogador", jPanel2);

        jMenorSalarioButton.setText("Jogador com menor salário");
        jMenorSalarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenorSalarioButtonActionPerformed(evt);
            }
        });

        jAcimaMediaButton.setText("Jogadores com salário acima da média");
        jAcimaMediaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAcimaMediaButtonActionPerformed(evt);
            }
        });

        jConsultaTA.setColumns(20);
        jConsultaTA.setRows(5);
        jScrollPane2.setViewportView(jConsultaTA);

        jTotaisButton.setText("Totais de salários");
        jTotaisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTotaisButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Participação % por situação");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jMenorSalarioButton)
                            .addComponent(jAcimaMediaButton))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jTotaisButton))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMenorSalarioButton)
                    .addComponent(jTotaisButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jAcimaMediaButton)
                    .addComponent(jButton1))
                .addGap(77, 77, 77)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consultas", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jIncluirJogadorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIncluirJogadorButtonActionPerformed
        try {
            Jogador j = new Jogador();
            j.setNome(jNomeJogadorTF.getText());
            j.setSalarioAtual(Float.parseFloat(jSalarioJogadorTF.getText()));
            j.setSituacao(jSituacaoComboBox.getSelectedItem().toString().charAt(0));
            ((Clube) jClubeExibirComboBox.getSelectedItem()).addJogador(j);
            JOptionPane.showMessageDialog(this, j); // chamada implícita a toString()
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(this, iae.getMessage());
        }
    }//GEN-LAST:event_jIncluirJogadorButtonActionPerformed

    private void jIncluirClubeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIncluirClubeButtonActionPerformed
        Clube c = new Clube();
        c.setNome(jNomeClubeTF.getText());
        c.setCidade(jCidadeClubeTF.getText());
        sind.insereClube(c);
        jClubeComboBox.addItem(c);
        jClubeExibirComboBox.addItem(c);
    }//GEN-LAST:event_jIncluirClubeButtonActionPerformed

    private void jExibirClubeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExibirClubeButtonActionPerformed
        Clube c = (Clube) jClubeExibirComboBox.getSelectedItem();
        StringBuilder sb = new StringBuilder();
        sb.append("Dados do clube " + c.getNome() + " de " + c.getCidade());
        sb.append("\nMédia salarial = R$" + c.getMediaSalarial());
        sb.append("\nQuantidade de jogadores = " + c.getQtdeJogadores());
        sb.append("\nTotal de salários atuais = R$ " + c.getTotalSalariosAtuais());
        sb.append("\nTotal de novos salários = R$ " + c.getTotalSalariosNovos());
        jClubeTA.setText(sb.toString());
    }//GEN-LAST:event_jExibirClubeButtonActionPerformed

    private void jAcimaMediaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAcimaMediaButtonActionPerformed
        jConsultaTA.setText("Jogadores com salário acima da média");
        for (Jogador j : sind.getJogadoresAcimaMediaSalarial()) {
            jConsultaTA.append("\n" + j);
        }
    }//GEN-LAST:event_jAcimaMediaButtonActionPerformed

    private void jMenorSalarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenorSalarioButtonActionPerformed
        Jogador j = sind.getJogadorMenorSalario();
        String str = "Jogador com menor salário é " + j.getNome() + " do clube " + j.getClube().getNome()
                + "\ncom salário atual de R$ " + j.getSalarioAtual();
        jConsultaTA.setText(str);
    }//GEN-LAST:event_jMenorSalarioButtonActionPerformed

    private void jTotaisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTotaisButtonActionPerformed
        String str = "Total dos salários atuais = R$ " + sind.getTotalSalariosAtuais()
                + "\nTotal dos novos salários = R$ " + sind.getTotalSalariosNovos();
        jConsultaTA.setText(str);
    }//GEN-LAST:event_jTotaisButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        float total = sind.getTotalSalariosAtuais();
        float soma = sind.getTotalSalariosAtuais('T');
        jConsultaTA.setText("Total dos salários atuais = R$ " + total
                + "\nParticipação dos Titulares (R$ " + soma + ") = " + (soma / total * 100) + "%");

        soma = sind.getTotalSalariosAtuais('R');
        jConsultaTA.append("\nParticipação dos Reservas (R$ " + soma + ") = " + (soma / total * 100) + "%");

        total = sind.getTotalSalariosNovos();
        soma = sind.getTotalSalariosNovos('T');
        jConsultaTA.append("\n\nTotal dos novos salários = R$ " + total
                + "\nParticipação dos Titulares (R$ " + soma + ") = " + (soma / total * 100) + "%");

        soma = sind.getTotalSalariosNovos('R');
        jConsultaTA.append("\nParticipação dos Reservas (R$ " + soma + ") = " + (soma / total * 100) + "%");
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Apresentacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAcimaMediaButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jCidadeClubeTF;
    private javax.swing.JComboBox jClubeComboBox;
    private javax.swing.JComboBox jClubeExibirComboBox;
    private javax.swing.JTextArea jClubeTA;
    private javax.swing.JTextArea jConsultaTA;
    private javax.swing.JButton jExibirClubeButton;
    private javax.swing.JButton jIncluirClubeButton;
    private javax.swing.JButton jIncluirJogadorButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jMenorSalarioButton;
    private javax.swing.JTextField jNomeClubeTF;
    private javax.swing.JTextField jNomeJogadorTF;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jSalarioJogadorTF;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox jSituacaoComboBox;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jTotaisButton;
    // End of variables declaration//GEN-END:variables
}

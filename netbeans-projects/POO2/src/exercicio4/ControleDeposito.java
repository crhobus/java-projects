package exercicio4;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ControleDeposito extends JFrame implements ListenerDeposito, ActionListener {

    private JButton jButton1, jButton2, jButton3;
    private JLabel jLabel5;
    private JList jList1;
    private JTextField jTextField1, jTextField2, jTextField3;
    private Deposito deposito;
    private DepositoListModel depositoListModel;

    public ControleDeposito() {
        depositoListModel = new DepositoListModel();
        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton1) {
            this.deposito = Deposito.getInstancia(Integer.parseInt(jTextField1.getText()), Integer.parseInt(jTextField2.getText()));
            deposito.addListener(this);
            jTextField1.setEditable(false);
            jTextField2.setEditable(false);
            jButton1.setEnabled(false);
        } else if (e.getSource() == jButton2) {
            try {
                deposito.add(Integer.parseInt(jTextField3.getText()));
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Tentativa de armazenar mais que o permitido.");
            }
        } else if (e.getSource() == jButton3) {
            deposito.retirar(Integer.parseInt(jTextField3.getText()));
        }
    }

    @Override
    public void chegouCritico() {
        depositoListModel.addTexto("Atenção, comprar mais!");
    }

    @Override
    public void foiAdd(int qtdAdd) {
        depositoListModel.addTexto("Adicionado: " + qtdAdd);
        jTextField3.setText("");
    }

    @Override
    public void foiRet(int qtdRet) {
        depositoListModel.addTexto("Retirado: " + qtdRet);
        jTextField3.setText("");
        JOptionPane.showMessageDialog(null, "Foi retirado: " + qtdRet);
    }

    @Override
    public void foiModificadoqtdAtual(int qtd) {
        jLabel5.setText(qtd + " ");
    }

    private void initComponents() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Controle de deposito");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();

        JLabel jLabel1 = new JLabel("Qtdade maxima :");
        JLabel jLabel2 = new JLabel("Qtdade critica :");
        JLabel jLabel3 = new JLabel("Qtdade :");
        JLabel jLabel4 = new JLabel("Qtdade atual :");
        jLabel5 = new JLabel("0");
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        JLabel jLabel6 = new JLabel("Fluxo :");

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();

        jButton1 = new JButton("Criar deposito");
        jButton1.addActionListener(this);
        jButton2 = new JButton("Add");
        jButton2.addActionListener(this);
        jButton3 = new JButton("Retirar");
        jButton3.addActionListener(this);

        jList1 = new JList(depositoListModel);
        JScrollPane jScrollPane1 = new JScrollPane(jList1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do deposito"));
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
                jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(
                jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel2).addComponent(jLabel1)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
                jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jTextField1,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                121,
                javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                jTextField2,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                121,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addComponent(
                jButton1).addContainerGap(61,
                Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel1Layout.createSequentialGroup().addGroup(
                jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel1Layout.createSequentialGroup().addGroup(
                jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                jLabel1).addComponent(
                jTextField1,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
                jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                jLabel2).addComponent(
                jTextField2,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(
                jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(
                jButton1))).addContainerGap(
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Fluxo"));
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
                jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel2Layout.createSequentialGroup().addGroup(
                jPanel2Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel2Layout.createSequentialGroup().addComponent(
                jLabel3).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
                jTextField3,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                121,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(
                jPanel2Layout.createSequentialGroup().addGap(
                22,
                22,
                22).addComponent(
                jButton2).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
                jButton3))).addContainerGap(
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel2Layout.createSequentialGroup().addGroup(
                jPanel2Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(
                jTextField3,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                jPanel2Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton3).addComponent(jButton2)).addContainerGap(96, Short.MAX_VALUE)));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Log"));
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
                jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel3Layout.createSequentialGroup().addComponent(jLabel4).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel5).addContainerGap(
                114, Short.MAX_VALUE)).addComponent(jScrollPane1,
                javax.swing.GroupLayout.DEFAULT_SIZE, 197,
                Short.MAX_VALUE).addGroup(
                jPanel3Layout.createSequentialGroup().addComponent(jLabel6).addContainerGap()));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                jPanel3Layout.createSequentialGroup().addGroup(
                jPanel3Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(jLabel5)).addGap(5, 5, 5).addComponent(jLabel6).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
                jScrollPane1,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                106, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel1,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addGroup(
                layout.createSequentialGroup().addComponent(
                jPanel2,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(
                jPanel3,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addComponent(
                jPanel1,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING,
                false).addComponent(
                jPanel2,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addComponent(
                jPanel3,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)).addContainerGap(
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private class DepositoListModel extends AbstractListModel {

        private ArrayList<String> array = new ArrayList<String>();

        public int getSize() {
            return array.size();
        }

        public void addTexto(String texto) {
            this.array.add(texto);
            fireContentsChanged(this, array.size() - 1, array.size());
        }

        public Object getElementAt(int index) {
            return array.get(index);
        }
    }

    public static void main(String args[]) {
        new ControleDeposito().setVisible(true);
    }
}

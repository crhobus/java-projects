package view.desktop.pedido;

import control.Servidor;
import control.funcoes.ExceptionError;
import java.awt.CardLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JPanel;
import model.entidades.Cliente;
import view.desktop.AcaoListener;
import view.desktop.ClickListener;
import view.desktop.SisComPizzaria;

public class Pesquisa extends JPanel implements AcaoListener {

    private JDialog dialog;
    private PedidoSis pedido;
    private SisComPizzaria sisCom;
    private ClickListener clickListener;
    private TableModelClientePesquisa tableModelClientePesquisa;
    private Cliente cliente;
    private String cardVisivel;
    private int linhaAtual;

    public Pesquisa(SisComPizzaria sisCom, JDialog dialog, PedidoSis pedido) {
        this.pedido = pedido;
        this.dialog = dialog;
        this.sisCom = sisCom;
        this.clickListener = sisCom;
        this.sisCom.setAcaoListener(this);
        this.tableModelClientePesquisa = new TableModelClientePesquisa(this);
        initComponents();
        ativar();
    }

    private void ativar() {
        // table grig
        tbPedido.inicializaTable();
        tbPedido.getTabela().getColumnModel().getColumn(0).setMinWidth(140);
        tbPedido.getTabela().getColumnModel().getColumn(1).setMinWidth(270);
        tbPedido.getTabela().getColumnModel().getColumn(2).setMinWidth(80);
        tbPedido.getTabela().getColumnModel().getColumn(3).setMinWidth(80);
        //    tbPedido.getTabela().getColumnModel().getColumn(4).setMinWidth(100);

        pnDados.inicializaPanel();

        pnDados.setPermiteAlterarDados(sisCom.isPermiteAlterarDados(1));
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if (tableModelClientePesquisa.possuiRegistros()) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        }
        cardVisivel = "detalhe";
        linhaAtual = 0;
    }

    public Servidor getServidor() {
        return sisCom.getServidor();
    }

    @Override
    public void onAlternarGridDetalhe(boolean grid) {
        if (grid) {
            ((CardLayout) pnCard.getLayout()).show(pnCard, "grid");
            cardVisivel = "grid";
            if (tableModelClientePesquisa.possuiRegistros()) {
                tbPedido.selecionarLinha(tableModelClientePesquisa.getLinhaSelecionada());
            }
        } else {
            ((CardLayout) pnCard.getLayout()).show(pnCard, "detalhe");
            cardVisivel = "detalhe";
            linhaAtual = tbPedido.getTabela().getSelectedRow();
        }
    }

    @Override
    public void onNovo() {
        clickListener.onSalvar(true);
        clickListener.onDesfazer(true);
        clickListener.onNovo(false);
        clickListener.onExcluir(false);
        sisCom.acaoBtGridDetalhe(true);
        ((CardLayout) pnCard.getLayout()).show(pnCard, "detalhe");
        cardVisivel = "detalhe";
        pnDados.setEdicao(true);
        pnDados.limparCampos();
        cliente = null;
        tfNmCidade.requestFocus();
    }

    @Override
    public void onSalvar() {
    }

    @Override
    public void onDesfazer() {

    }

    private void mostraRegistro() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void desfazEdicao() {
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelClientePesquisa.possuiRegistros()) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else if (cliente != null) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        }
        pnDados.setEdicao(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnCard = new javax.swing.JPanel();
        pnGrid = new javax.swing.JPanel();
        tbPedido = new view.componentes.table.TTable();
        pnDetalhe = new javax.swing.JPanel();
        pnDados = new view.componentes.TPanel();
        lbNmCliente = new javax.swing.JLabel();
        tfNmCliente = new view.componentes.TTextField();
        lbDsEndereco = new javax.swing.JLabel();
        tfDsEndereco = new view.componentes.TTextField();
        lbNmCidade = new javax.swing.JLabel();
        tfNmCidade = new view.componentes.TTextField();
        lbNrTelefone = new javax.swing.JLabel();
        lbNrCelular = new javax.swing.JLabel();
        tfDsEmail = new view.componentes.TTextField();
        lbDsEmail = new javax.swing.JLabel();
        ftfNrTelefone = new view.componentes.TTextField();
        ftfNrCelular = new view.componentes.TTextField();
        pnBotoes = new javax.swing.JPanel();
        btSalvar = new javax.swing.JButton();
        btDesfazer = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setMaximumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(500, 350));
        setLayout(new java.awt.BorderLayout());

        pnCard.setLayout(new java.awt.CardLayout());

        pnGrid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnGrid.setLayout(new java.awt.BorderLayout());

        tbPedido.setConfigurar(true);
        tbPedido.setTableModel(tableModelClientePesquisa);
        pnGrid.add(tbPedido, java.awt.BorderLayout.CENTER);

        pnCard.add(pnGrid, "grid");

        pnDetalhe.setLayout(null);

        pnDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnDados.setClickListener(clickListener);
        pnDados.setListenerkeyPressed(true);
        pnDados.setTemCamposObrigatorios(true);
        pnDados.setValidaCamposPanel(true);
        pnDados.setLayout(null);

        lbNmCliente.setText("Nome");
        pnDados.add(lbNmCliente);
        lbNmCliente.setBounds(20, 10, 30, 14);

        tfNmCliente.setName("NM_CLIENTE");
        pnDados.add(tfNmCliente);
        tfNmCliente.setBounds(70, 10, 320, 20);

        lbDsEndereco.setText("Endereço");
        pnDados.add(lbDsEndereco);
        lbDsEndereco.setBounds(20, 40, 45, 14);

        tfDsEndereco.setName("DS_ENDERECO");
        pnDados.add(tfDsEndereco);
        tfDsEndereco.setBounds(70, 40, 320, 20);

        lbNmCidade.setText("Cidade");
        pnDados.add(lbNmCidade);
        lbNmCidade.setBounds(20, 70, 35, 14);

        tfNmCidade.setName("NM_CIDADE");
        pnDados.add(tfNmCidade);
        tfNmCidade.setBounds(70, 70, 320, 20);

        lbNrTelefone.setText("Telefone");
        pnDados.add(lbNrTelefone);
        lbNrTelefone.setBounds(20, 100, 45, 14);

        lbNrCelular.setText("Celular");
        pnDados.add(lbNrCelular);
        lbNrCelular.setBounds(210, 100, 35, 14);

        tfDsEmail.setName("DS_EMAIL");
        pnDados.add(tfDsEmail);
        tfDsEmail.setBounds(70, 130, 320, 20);

        lbDsEmail.setText("Email");
        pnDados.add(lbDsEmail);
        lbDsEmail.setBounds(20, 130, 25, 20);

        ftfNrTelefone.setName("DS_EMAIL");
        pnDados.add(ftfNrTelefone);
        ftfNrTelefone.setBounds(70, 100, 130, 20);

        ftfNrCelular.setName("DS_EMAIL");
        pnDados.add(ftfNrCelular);
        ftfNrCelular.setBounds(260, 100, 130, 20);

        pnDetalhe.add(pnDados);
        pnDados.setBounds(100, 60, 420, 190);

        pnCard.add(pnDetalhe, "detalhe");

        add(pnCard, java.awt.BorderLayout.CENTER);

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Salvar.png"))); // NOI18N
        btSalvar.setText("Confirmar");
        btSalvar.setToolTipText("Confirmar");
        btSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btSalvar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btDesfazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Desfazer.png"))); // NOI18N
        btDesfazer.setText("Cancelar");
        btDesfazer.setToolTipText("Desfazer a alteração");
        btDesfazer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btDesfazer.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btDesfazer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDesfazerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnBotoesLayout = new javax.swing.GroupLayout(pnBotoes);
        pnBotoes.setLayout(pnBotoesLayout);
        pnBotoesLayout.setHorizontalGroup(
            pnBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBotoesLayout.createSequentialGroup()
                .addContainerGap(451, Short.MAX_VALUE)
                .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btDesfazer, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnBotoesLayout.setVerticalGroup(
            pnBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBotoesLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btDesfazer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(pnBotoes, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (cardVisivel.equals("detalhe")) {
            List<Cliente> clientes;
            try {
                clientes = getServidor().getClienteAction().getClientes(tfNmCliente.getText(),ftfNrTelefone.getText(),ftfNrCelular.getText());
                for (Cliente c : clientes) {
                    this.tableModelClientePesquisa.addCliente(c);
                }
            } catch (ExceptionError ex) {
                Logger.getLogger(Pesquisa.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.onAlternarGridDetalhe(true);
        } else {
            linhaAtual = tbPedido.getTabela().getSelectedRow();

            if (tableModelClientePesquisa.possuiRegistros()) {
                cliente = tableModelClientePesquisa.getCliente(tbPedido.getTabela().getSelectedRow());
                pedido.addCliente(cliente);
            }
            dialog.dispose();
        }

    }//GEN-LAST:event_btSalvarActionPerformed

    private void btDesfazerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesfazerActionPerformed
        dialog.dispose();
    }//GEN-LAST:event_btDesfazerActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDesfazer;
    private javax.swing.JButton btSalvar;
    private view.componentes.TTextField ftfNrCelular;
    private view.componentes.TTextField ftfNrTelefone;
    private javax.swing.JLabel lbDsEmail;
    private javax.swing.JLabel lbDsEndereco;
    private javax.swing.JLabel lbNmCidade;
    private javax.swing.JLabel lbNmCliente;
    private javax.swing.JLabel lbNrCelular;
    private javax.swing.JLabel lbNrTelefone;
    private javax.swing.JPanel pnBotoes;
    private javax.swing.JPanel pnCard;
    private view.componentes.TPanel pnDados;
    private javax.swing.JPanel pnDetalhe;
    private javax.swing.JPanel pnGrid;
    private view.componentes.table.TTable tbPedido;
    private view.componentes.TTextField tfDsEmail;
    private view.componentes.TTextField tfDsEndereco;
    private view.componentes.TTextField tfNmCidade;
    private view.componentes.TTextField tfNmCliente;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onExcluir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

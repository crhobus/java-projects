package view.desktop.pedido;

import control.Servidor;
import control.funcoes.Excecao;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import model.entidades.*;
import view.desktop.AcaoListener;
import view.desktop.ClickListener;
import view.desktop.SisComPizzaria;

public class PedidoSis extends JPanel implements AcaoListener {

    private SisComPizzaria sisCom;
    private ClickListener clickListener;
    private TableModelPedido tableModelPedido;
    private TableModelItemPedido tableModelItemPedido;
    private Pedido pedido;
    private String cardVisivel;
    private int linhaAtual;

    public PedidoSis(SisComPizzaria sisCom) {
        this.sisCom = sisCom;
        this.clickListener = sisCom;
        this.sisCom.setAcaoListener(this);
        this.tableModelPedido = new TableModelPedido(this);
        this.tableModelItemPedido = new TableModelItemPedido(new ArrayList<ItemPedido>());

        initComponents();
        ativar();
    }

    private void ativar() {
        // table grig
        tbPedido.inicializaTable();
        tbPedido.getTabela().getColumnModel().getColumn(0).setMinWidth(50);
        tbPedido.getTabela().getColumnModel().getColumn(1).setMinWidth(120);
        tbPedido.getTabela().getColumnModel().getColumn(2).setMinWidth(400);
        tbPedido.getTabela().getColumnModel().getColumn(3).setMinWidth(250);
        tbPedido.getTabela().getColumnModel().getColumn(4).setMinWidth(100);

        tbItemPedido.inicializaTable();
        tbItemPedido.getTabela().getColumnModel().getColumn(0).setMinWidth(150);
        tbItemPedido.getTabela().getColumnModel().getColumn(1).setMinWidth(300);
        tbItemPedido.getTabela().getColumnModel().getColumn(2).setMinWidth(100);

        comboBoxTamanho.setDados(this.getServidor());
        comboBoxSabores1.setDados(this.getServidor());
        comboBoxSabores2.setDados(this.getServidor());
        comboBoxSabores3.setDados(this.getServidor());
        comboBoxSabores4.setDados(this.getServidor());

        //  tableModelItemPedido = new TableModelItemPedido();

        pnDados.inicializaPanel();

        pnDados.setPermiteAlterarDados(sisCom.isPermiteAlterarDados(1));
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if (tableModelPedido.possuiRegistros()) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        }
        cardVisivel = "grid";
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
            desfazEdicao();
            if (tableModelPedido.possuiRegistros()) {
                tbPedido.selecionarLinha(tableModelPedido.getLinhaSelecionada());
            }
        } else {
            ((CardLayout) pnCard.getLayout()).show(pnCard, "detalhe");
            cardVisivel = "detalhe";
            linhaAtual = tbPedido.getTabela().getSelectedRow();
            mostraRegistro();
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
        pedido = new Pedido();
        //   tfNmSabor.requestFocus();
    }

    @Override
    public void onSalvar() {
        //     if (pnDados.verificaCamposObrigatoriosPreenchidos()) {
        Excecao excecao = null;
        try {
            boolean novoPedido = false;
            if (pedido == null) {
                novoPedido = true;
            }

            if (pedido.getCliente() == null) {
                JOptionPane.showMessageDialog(null, "Cliente não informado!");
            }

            System.out.println("Testset");

            pedido = getServidor().getPedidoAction().salvar(pnDados.getDadosPanel(null), pedido);
            if (pedido != null) {
                if (novoPedido) {
                    tableModelPedido.addPedido(pedido);
                }
                tableModelPedido.atualizaTabela();
                tfCdPedido.setText(Integer.toString(pedido.getCdPedido()));
                desfazEdicao();

            }
        } catch (ExceptionInfo ex) {
            excecao = ex;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Pizza Nostra - Usuário", JOptionPane.INFORMATION_MESSAGE);
        } catch (ExceptionError ex) {
            excecao = ex;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
        if (excecao != null) {
            pnDados.setFocusCampo(excecao.getDsCampoFocar());
        }
        //      }
    }

    @Override
    public void onDesfazer() {
        if (pnDados.isEdicao()) {
            pnDados.limparCampos();
        }
        if (pedido != null) {
            mostraRegistro();
        }
        desfazEdicao();
    }

    @Override
    public void onExcluir() {
        pnDados.setEdicao(false);
        try {
            if ("detalhe".equalsIgnoreCase(cardVisivel)) {
                pnDados.limparCampos();
                getServidor().getPedidoAction().excluirPedido(tableModelPedido.removerPedido(pedido).getCdPedido());
            } else {
                getServidor().getPedidoAction().excluirPedido(tableModelPedido.removerPedido(tbPedido.getTabela().getSelectedRow()).getCdPedido());
            }
        } catch (ExceptionError ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
        pedido = null;
        tableModelPedido.atualizaTabela();
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelPedido.possuiRegistros()) {
            tbPedido.selecionarLinha(tableModelPedido.getLinhaSelecionada());
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else {
            clickListener.onExcluir(false);
        }
    }

    private void mostraRegistro() {
        if (tableModelPedido.possuiRegistros()) {
            pnDados.setKeyPressed(false);
            pedido = tableModelPedido.getPedido(tbPedido.getTabela().getSelectedRow());

            tfCdPedido.setText(Integer.toString(pedido.getCdPedido()));
            tfNmCliente.setText(pedido.getCliente().getNmCliente());
            tfNmCliente.setText(pedido.getCliente().getNmCliente());
            tfObservacao.setText(pedido.getDsObservacao());

            List<ItemPedido> lista = pedido.getItens();
            for (ItemPedido item : lista) {
                tableModelItemPedido.addItemPedido(item);
            }

            pnDados.setKeyPressed(true);
        } else {
            pnDados.limparCampos();
            //         tfCdCliente.requestFocus();
        }
    }

    public Pedido getPedido() {
        return pedido;
    }

    private void desfazEdicao() {
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelPedido.possuiRegistros()) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else if (pedido != null) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        }
        pnDados.setEdicao(false);
    }

    public void addCliente(Cliente cli) {
        if (pedido == null) {
            pedido = new Pedido();
        }
        this.pedido.setCliente(cli);
        tfNmCliente.setText(pedido.getCliente().getNmCliente());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTitulo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        pnCard = new javax.swing.JPanel();
        pnGrid = new javax.swing.JPanel();
        tbPedido = new view.componentes.table.TTable();
        pnDetalhe = new javax.swing.JPanel();
        pnDados = new view.componentes.TPanel();
        lbCdPedido = new javax.swing.JLabel();
        tfCdPedido = new view.componentes.TTextField();
        lbCdCliente = new javax.swing.JLabel();
        tfNmCliente = new javax.swing.JTextField();
        tfDtPedido = new view.componentes.TTextField();
        lbDtPedido = new javax.swing.JLabel();
        lbObservacao = new javax.swing.JLabel();
        tfObservacao = new javax.swing.JTextField();
        btPesquisaCli = new javax.swing.JButton();
        itens = new javax.swing.JPanel();
        tbItemPedido = new view.componentes.table.TTable();
        comboBoxTamanho = new view.componentes.ComboBoxTamanho();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboBoxSabores1 = new view.componentes.ComboBoxSabores();
        comboBoxSabores2 = new view.componentes.ComboBoxSabores();
        comboBoxSabores3 = new view.componentes.ComboBoxSabores();
        comboBoxSabores4 = new view.componentes.ComboBoxSabores();
        descricao1 = new javax.swing.JTextField();
        descricao2 = new javax.swing.JTextField();
        descricao3 = new javax.swing.JTextField();
        descricao4 = new javax.swing.JTextField();
        tfVlPedido1 = new view.componentes.TTextField();
        valor = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setLayout(new java.awt.BorderLayout());

        pnTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnTitulo.setPreferredSize(new java.awt.Dimension(0, 30));

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTitulo.setText("Pedidos");
        pnTitulo.add(lbTitulo);

        add(pnTitulo, java.awt.BorderLayout.NORTH);

        pnCard.setLayout(new java.awt.CardLayout());

        pnGrid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnGrid.setLayout(new java.awt.BorderLayout());

        tbPedido.setConfigurar(true);
        tbPedido.setTableModel(tableModelPedido);
        pnGrid.add(tbPedido, java.awt.BorderLayout.CENTER);

        pnCard.add(pnGrid, "grid");

        pnDetalhe.setLayout(null);

        pnDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnDados.setClickListener(clickListener);
        pnDados.setListenerkeyPressed(true);
        pnDados.setTemCamposObrigatorios(true);
        pnDados.setValidaCamposPanel(true);
        pnDados.setLayout(null);

        lbCdPedido.setText("Pedido");
        pnDados.add(lbCdPedido);
        lbCdPedido.setBounds(10, 10, 40, 14);

        tfCdPedido.setEditable(false);
        tfCdPedido.setName("CD_SABOR");
        pnDados.add(tfCdPedido);
        tfCdPedido.setBounds(100, 10, 70, 20);

        lbCdCliente.setText("Cliente");
        pnDados.add(lbCdCliente);
        lbCdCliente.setBounds(220, 10, 40, 14);

        tfNmCliente.setEditable(false);
        pnDados.add(tfNmCliente);
        tfNmCliente.setBounds(360, 10, 290, 20);

        tfDtPedido.setEditable(false);
        tfDtPedido.setName("CD_SABOR");
        pnDados.add(tfDtPedido);
        tfDtPedido.setBounds(100, 40, 70, 20);

        lbDtPedido.setText("Data do Pedido");
        pnDados.add(lbDtPedido);
        lbDtPedido.setBounds(10, 40, 90, 14);

        lbObservacao.setText("Observações");
        pnDados.add(lbObservacao);
        lbObservacao.setBounds(220, 40, 70, 14);

        tfObservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfObservacaoActionPerformed(evt);
            }
        });
        pnDados.add(tfObservacao);
        tfObservacao.setBounds(290, 40, 360, 20);

        btPesquisaCli.setBackground(new java.awt.Color(255, 255, 255));
        btPesquisaCli.setText("Pesquisar");
        btPesquisaCli.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btPesquisaCli.setSelected(true);
        btPesquisaCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisaCliActionPerformed(evt);
            }
        });
        pnDados.add(btPesquisaCli);
        btPesquisaCli.setBounds(290, 10, 60, 20);

        itens.setBorder(javax.swing.BorderFactory.createTitledBorder("Ítens do pedido"));

        tbItemPedido.setConfigurar(true);
        tbItemPedido.setTableModel(tableModelItemPedido);

        javax.swing.GroupLayout itensLayout = new javax.swing.GroupLayout(itens);
        itens.setLayout(itensLayout);
        itensLayout.setHorizontalGroup(
            itensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbItemPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );
        itensLayout.setVerticalGroup(
            itensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbItemPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
        );

        pnDados.add(itens);
        itens.setBounds(10, 70, 640, 150);

        comboBoxTamanho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTamanhoActionPerformed(evt);
            }
        });
        pnDados.add(comboBoxTamanho);
        comboBoxTamanho.setBounds(10, 250, 160, 20);

        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnDados.add(jButton1);
        jButton1.setBounds(560, 420, 80, 40);

        jLabel1.setText("Tamanho");
        pnDados.add(jLabel1);
        jLabel1.setBounds(10, 230, 50, 14);

        jLabel2.setText("Sabores");
        pnDados.add(jLabel2);
        jLabel2.setBounds(10, 280, 40, 14);

        comboBoxSabores1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSabores1ActionPerformed(evt);
            }
        });
        pnDados.add(comboBoxSabores1);
        comboBoxSabores1.setBounds(10, 300, 160, 20);

        comboBoxSabores2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSabores2ActionPerformed(evt);
            }
        });
        pnDados.add(comboBoxSabores2);
        comboBoxSabores2.setBounds(10, 330, 160, 20);

        comboBoxSabores3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSabores3ActionPerformed(evt);
            }
        });
        pnDados.add(comboBoxSabores3);
        comboBoxSabores3.setBounds(10, 360, 160, 20);

        comboBoxSabores4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSabores4ActionPerformed(evt);
            }
        });
        pnDados.add(comboBoxSabores4);
        comboBoxSabores4.setBounds(10, 390, 160, 20);

        descricao1.setEditable(false);
        pnDados.add(descricao1);
        descricao1.setBounds(180, 300, 460, 20);

        descricao2.setEditable(false);
        pnDados.add(descricao2);
        descricao2.setBounds(180, 330, 460, 20);

        descricao3.setEditable(false);
        pnDados.add(descricao3);
        descricao3.setBounds(180, 360, 460, 20);

        descricao4.setEditable(false);
        pnDados.add(descricao4);
        descricao4.setBounds(180, 390, 460, 20);

        tfVlPedido1.setName("CD_SABOR");
        pnDados.add(tfVlPedido1);
        tfVlPedido1.setBounds(460, 430, 80, 20);

        valor.setText("Valor");
        pnDados.add(valor);
        valor.setBounds(420, 430, 30, 14);

        pnDetalhe.add(pnDados);
        pnDados.setBounds(180, 10, 670, 470);

        pnCard.add(pnDetalhe, "detalhe");

        add(pnCard, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btPesquisaCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisaCliActionPerformed
        JDialog dialog = new JDialog();
        Pesquisa pesquisa = new Pesquisa(this.sisCom, dialog, this);
        pesquisa.onAlternarGridDetalhe(false);


        dialog.setTitle("Pesquisa de clientes");
        dialog.setLayout(new BorderLayout());
        dialog.setModal(true);
        dialog.add(pesquisa, BorderLayout.CENTER);
        dialog.setSize(650, 400);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }//GEN-LAST:event_btPesquisaCliActionPerformed

    private void tfObservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfObservacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfObservacaoActionPerformed

    private void comboBoxTamanhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTamanhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTamanhoActionPerformed

    private void comboBoxSabores1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSabores1ActionPerformed
        descricao1.setText(comboBoxSabores1.getSabor());
    }//GEN-LAST:event_comboBoxSabores1ActionPerformed

    private void comboBoxSabores2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSabores2ActionPerformed
        descricao2.setText(comboBoxSabores2.getSabor());
    }//GEN-LAST:event_comboBoxSabores2ActionPerformed

    private void comboBoxSabores3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSabores3ActionPerformed
        descricao3.setText(comboBoxSabores3.getSabor());
    }//GEN-LAST:event_comboBoxSabores3ActionPerformed

    private void comboBoxSabores4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSabores4ActionPerformed
        descricao4.setText(comboBoxSabores4.getSabor());
    }//GEN-LAST:event_comboBoxSabores4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ItemPedido item = new ItemPedido();

        Sabor a = comboBoxSabores1.getTodoSabor();
        Sabor b = comboBoxSabores2.getTodoSabor();
        Sabor c = comboBoxSabores3.getTodoSabor();
        Sabor d = comboBoxSabores4.getTodoSabor();

        Tamanho tam = comboBoxTamanho.getTamanho();

        if (tam == null) {
            JOptionPane.showMessageDialog(null, "Selecione um tamanho!");
            return;
        } else {
            item.setTamanho(tam);
        }

        if (a == null && b == null && c == null && d == null) {
            JOptionPane.showMessageDialog(null, "Selecione ao menos um sabor!");
            return;
        }

        if (a != null) {
            item.addSabor(a);
        }
        if (b != null) {
            item.addSabor(b);
        }
        if (c != null) {
            item.addSabor(c);
        }
        if (d != null) {
            item.addSabor(d);
        }



        try {
            if (Double.parseDouble(tfVlPedido1.getText()) > 0) {
                item.setVlPedido(Double.parseDouble(tfVlPedido1.getText()));
            }
        } catch (Exception e) {
            item.setVlPedido(0.00d);
        }

        if (pedido == null) {
            System.out.println("Criando novo pedido");
            pedido = new Pedido();
        }

        pedido.addItem(item);
        tableModelItemPedido.addItemPedido(item);

        comboBoxTamanho.limpar();
        comboBoxSabores1.limpar();
        comboBoxSabores2.limpar();
        comboBoxSabores3.limpar();
        comboBoxSabores4.limpar();

        tfVlPedido1.setText("");

    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPesquisaCli;
    private view.componentes.ComboBoxSabores comboBoxSabores1;
    private view.componentes.ComboBoxSabores comboBoxSabores2;
    private view.componentes.ComboBoxSabores comboBoxSabores3;
    private view.componentes.ComboBoxSabores comboBoxSabores4;
    private view.componentes.ComboBoxTamanho comboBoxTamanho;
    private javax.swing.JTextField descricao1;
    private javax.swing.JTextField descricao2;
    private javax.swing.JTextField descricao3;
    private javax.swing.JTextField descricao4;
    private javax.swing.JPanel itens;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbCdCliente;
    private javax.swing.JLabel lbCdPedido;
    private javax.swing.JLabel lbDtPedido;
    private javax.swing.JLabel lbObservacao;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnCard;
    private view.componentes.TPanel pnDados;
    private javax.swing.JPanel pnDetalhe;
    private javax.swing.JPanel pnGrid;
    private javax.swing.JPanel pnTitulo;
    private view.componentes.table.TTable tbItemPedido;
    private view.componentes.table.TTable tbPedido;
    private view.componentes.TTextField tfCdPedido;
    private view.componentes.TTextField tfDtPedido;
    private javax.swing.JTextField tfNmCliente;
    private javax.swing.JTextField tfObservacao;
    private view.componentes.TTextField tfVlPedido1;
    private javax.swing.JLabel valor;
    // End of variables declaration//GEN-END:variables
}

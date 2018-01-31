package view.desktop.produto;

import control.Servidor;
import control.funcoes.Excecao;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.entidades.Sabor;
import model.entidades.Tamanho;
import view.desktop.AcaoListener;
import view.desktop.ClickListener;
import view.desktop.SisComPizzaria;

public class CadasTamanho extends JPanel implements AcaoListener {

    private SisComPizzaria sisCom;
    private ClickListener clickListener;
    private TableModelTamanho tableModelTamanho;
    private Tamanho tamanho;
    private String cardVisivel;
    private int linhaAtual;

    public CadasTamanho(SisComPizzaria sisCom) {
        this.sisCom = sisCom;
        this.clickListener = sisCom;
        this.sisCom.setAcaoListener(this);
        this.tableModelTamanho = new TableModelTamanho(this);
        initComponents();
        ativar();
    }

    private void ativar() {
        //table grig
        tbSabor.inicializaTable();
        tbSabor.getTabela().getColumnModel().getColumn(0).setMinWidth(50);
        tbSabor.getTabela().getColumnModel().getColumn(1).setMinWidth(150);
        tbSabor.getTabela().getColumnModel().getColumn(2).setMinWidth(30);

        pnDados.inicializaPanel();

        pnDados.setPermiteAlterarDados(sisCom.isPermiteAlterarDados(1));
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if (tableModelTamanho.possuiRegistros()) {
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
            desfazEdicao();
            if (tableModelTamanho.possuiRegistros()) {
                tbSabor.selecionarLinha(tableModelTamanho.getLinhaSelecionada());
            }
        } else {
            ((CardLayout) pnCard.getLayout()).show(pnCard, "detalhe");
            cardVisivel = "detalhe";
            linhaAtual = tbSabor.getTabela().getSelectedRow();
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
        tmAtivo.setSelected(true);
        tamanho = null;
        tfDsTamanho.requestFocus();
    }

    @Override
    public void onSalvar() {
        if (pnDados.verificaCamposObrigatoriosPreenchidos()) {
            Excecao excecao = null;
            try {
                boolean novoSabor = false;
                if (tamanho == null) {
                    novoSabor = true;
                }
                tamanho = getServidor().getTamanhoAction().salvar(pnDados.getDadosPanel(null), tamanho);
                if (tamanho != null) {
                    if (novoSabor) {
                        tableModelTamanho.addTamanho(tamanho);
                    }
                    tableModelTamanho.atualizaTabela();
                    tfCdTamanho.setText(Integer.toString(tamanho.getCdTamanho()));
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
                ex.printStackTrace();
            }
            if (excecao != null) {
                pnDados.setFocusCampo(excecao.getDsCampoFocar());
            }
        }
    }

    @Override
    public void onDesfazer() {
        if (pnDados.isEdicao()) {
            pnDados.limparCampos();
        }
        if (tamanho != null) {
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

                getServidor().getTamanhoAction().excluirTamanho(tableModelTamanho.removerTamanho(tamanho).getCdTamanho());
            } else {
                getServidor().getTamanhoAction().excluirTamanho(tableModelTamanho.removerTamanho(tbSabor.getTabela().getSelectedRow()).getCdTamanho());
            }
        } catch (ExceptionError ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
        tamanho = null;
        tableModelTamanho.atualizaTabela();
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelTamanho.possuiRegistros()) {
            tbSabor.selecionarLinha(tableModelTamanho.getLinhaSelecionada());
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else {
            clickListener.onExcluir(false);
        }
    }

    private void mostraRegistro() {
        if (tableModelTamanho.possuiRegistros()) {
            pnDados.setKeyPressed(false);
            tamanho = tableModelTamanho.getTamanho(tbSabor.getTabela().getSelectedRow());
            
            tfCdTamanho.setText(Integer.toString(tamanho.getCdTamanho()));
            tfDsTamanho.setText(tamanho.getDsTamanho());
            tmAtivo.setSelected(tamanho.getTmAtivo() == 1);

            pnDados.setKeyPressed(true);
        } else {
            pnDados.limparCampos();
            tfDsTamanho.requestFocus();
        }
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    private void desfazEdicao() {
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelTamanho.possuiRegistros()) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else if (tamanho != null) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        }
        pnDados.setEdicao(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTitulo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        pnCard = new javax.swing.JPanel();
        pnGrid = new javax.swing.JPanel();
        tbSabor = new view.componentes.table.TTable();
        pnDetalhe = new javax.swing.JPanel();
        pnDados = new view.componentes.TPanel();
        lbCdTamanho = new javax.swing.JLabel();
        tfCdTamanho = new view.componentes.TTextField();
        lbDsTamanho = new javax.swing.JLabel();
        tfDsTamanho = new view.componentes.TTextField();
        tmAtivo = new javax.swing.JCheckBox();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setLayout(new java.awt.BorderLayout());

        pnTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnTitulo.setPreferredSize(new java.awt.Dimension(0, 30));

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTitulo.setText("Sabores");
        pnTitulo.add(lbTitulo);

        add(pnTitulo, java.awt.BorderLayout.NORTH);

        pnCard.setLayout(new java.awt.CardLayout());

        pnGrid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnGrid.setLayout(new java.awt.BorderLayout());

        tbSabor.setConfigurar(true);
        tbSabor.setTableModel(tableModelTamanho);
        pnGrid.add(tbSabor, java.awt.BorderLayout.CENTER);

        pnCard.add(pnGrid, "grid");

        pnDetalhe.setLayout(null);

        pnDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnDados.setClickListener(clickListener);
        pnDados.setListenerkeyPressed(true);
        pnDados.setTemCamposObrigatorios(true);
        pnDados.setValidaCamposPanel(true);
        pnDados.setLayout(null);

        lbCdTamanho.setText("Código");
        pnDados.add(lbCdTamanho);
        lbCdTamanho.setBounds(10, 10, 40, 14);

        tfCdTamanho.setEditable(false);
        tfCdTamanho.setName("CD_TAMANHO");
        pnDados.add(tfCdTamanho);
        tfCdTamanho.setBounds(70, 10, 70, 20);

        lbDsTamanho.setText("Tamanho");
        pnDados.add(lbDsTamanho);
        lbDsTamanho.setBounds(10, 40, 60, 14);

        tfDsTamanho.setName("DS_TAMANHO");
        tfDsTamanho.setObrigatorio(true);
        tfDsTamanho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDsTamanhoActionPerformed(evt);
            }
        });
        pnDados.add(tfDsTamanho);
        tfDsTamanho.setBounds(70, 40, 270, 20);

        tmAtivo.setSelected(true);
        tmAtivo.setText("Ativo");
        tmAtivo.setName("TM_ATIVO");
        pnDados.add(tmAtivo);
        tmAtivo.setBounds(290, 10, 50, 23);

        pnDetalhe.add(pnDados);
        pnDados.setBounds(380, 200, 370, 80);

        pnCard.add(pnDetalhe, "detalhe");

        add(pnCard, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tfDsTamanhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDsTamanhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDsTamanhoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbCdTamanho;
    private javax.swing.JLabel lbDsTamanho;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnCard;
    private view.componentes.TPanel pnDados;
    private javax.swing.JPanel pnDetalhe;
    private javax.swing.JPanel pnGrid;
    private javax.swing.JPanel pnTitulo;
    private view.componentes.table.TTable tbSabor;
    private view.componentes.TTextField tfCdTamanho;
    private view.componentes.TTextField tfDsTamanho;
    private javax.swing.JCheckBox tmAtivo;
    // End of variables declaration//GEN-END:variables
}

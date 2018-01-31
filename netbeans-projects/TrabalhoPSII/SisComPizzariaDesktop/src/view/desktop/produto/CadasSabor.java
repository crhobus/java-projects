package view.desktop.produto;

import control.Servidor;
import control.funcoes.Excecao;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.entidades.Sabor;
import view.desktop.AcaoListener;
import view.desktop.ClickListener;
import view.desktop.SisComPizzaria;

public class CadasSabor extends JPanel implements AcaoListener {

    private SisComPizzaria sisCom;
    private ClickListener clickListener;
    private TableModelSabor tableModelSabor;
    private Sabor sabor;
    private String cardVisivel;
    private int linhaAtual;

    public CadasSabor(SisComPizzaria sisCom) {
        this.sisCom = sisCom;
        this.clickListener = sisCom;
        this.sisCom.setAcaoListener(this);
        this.tableModelSabor = new TableModelSabor(this);
        initComponents();
        ativar();
    }

    private void ativar() {
        //table grig
        tbSabor.inicializaTable();
        tbSabor.getTabela().getColumnModel().getColumn(0).setMinWidth(50);
        tbSabor.getTabela().getColumnModel().getColumn(1).setMinWidth(120);
        tbSabor.getTabela().getColumnModel().getColumn(2).setMinWidth(500);

        pnDados.inicializaPanel();

        pnDados.setPermiteAlterarDados(sisCom.isPermiteAlterarDados(1));
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if (tableModelSabor.possuiRegistros()) {
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
            if (tableModelSabor.possuiRegistros()) {
                tbSabor.selecionarLinha(tableModelSabor.getLinhaSelecionada());
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
        sabor = null;
        tfNmSabor.requestFocus();
    }

    @Override
    public void onSalvar() {
        if (pnDados.verificaCamposObrigatoriosPreenchidos()) {
            Excecao excecao = null;
            try {
                boolean novoSabor = false;
                if (sabor == null) {
                    novoSabor = true;
                }
                sabor = getServidor().getSaborAction().salvar(pnDados.getDadosPanel(null), sabor);
                if (sabor != null) {
                    if (novoSabor) {
                        tableModelSabor.addSabor(sabor);
                    }
                    tableModelSabor.atualizaTabela();
                    tfCdSabor.setText(Integer.toString(sabor.getCdSabor()));
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
        if (sabor != null) {
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
                getServidor().getSaborAction().excluirSabor(tableModelSabor.removerSabor(sabor).getCdSabor());
            } else {
                getServidor().getSaborAction().excluirSabor(tableModelSabor.removerSabor(tbSabor.getTabela().getSelectedRow()).getCdSabor());
            }
        } catch (ExceptionError ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
        sabor = null;
        tableModelSabor.atualizaTabela();
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelSabor.possuiRegistros()) {
            tbSabor.selecionarLinha(tableModelSabor.getLinhaSelecionada());
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else {
            clickListener.onExcluir(false);
        }
    }

    private void mostraRegistro() {
        if (tableModelSabor.possuiRegistros()) {
            pnDados.setKeyPressed(false);
            sabor = tableModelSabor.getSabor(tbSabor.getTabela().getSelectedRow());
            
            tfCdSabor.setText(Integer.toString(sabor.getCdSabor()));
            tfNmSabor.setText(sabor.getNmSabor());
            tfDsSabor.setText(sabor.getDsSabor());
            pnDados.setKeyPressed(true);
        } else {
            pnDados.limparCampos();
            tfNmSabor.requestFocus();
        }
    }

    public Sabor getSabor() {
        return sabor;
    }

    private void desfazEdicao() {
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelSabor.possuiRegistros()) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else if (sabor != null) {
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
        lbCdSabor = new javax.swing.JLabel();
        tfCdSabor = new view.componentes.TTextField();
        lbNmSabor = new javax.swing.JLabel();
        tfNmSabor = new view.componentes.TTextField();
        lbDsSabor = new javax.swing.JLabel();
        tfDsSabor = new view.componentes.TTextField();

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
        tbSabor.setTableModel(tableModelSabor);
        pnGrid.add(tbSabor, java.awt.BorderLayout.CENTER);

        pnCard.add(pnGrid, "grid");

        pnDetalhe.setLayout(null);

        pnDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnDados.setClickListener(clickListener);
        pnDados.setListenerkeyPressed(true);
        pnDados.setTemCamposObrigatorios(true);
        pnDados.setValidaCamposPanel(true);
        pnDados.setLayout(null);

        lbCdSabor.setText("Código");
        pnDados.add(lbCdSabor);
        lbCdSabor.setBounds(10, 10, 40, 14);

        tfCdSabor.setEditable(false);
        tfCdSabor.setName("CD_SABOR");
        pnDados.add(tfCdSabor);
        tfCdSabor.setBounds(70, 10, 70, 20);

        lbNmSabor.setText("Sabor");
        pnDados.add(lbNmSabor);
        lbNmSabor.setBounds(160, 10, 30, 14);

        tfNmSabor.setName("NM_SABOR");
        tfNmSabor.setObrigatorio(true);
        pnDados.add(tfNmSabor);
        tfNmSabor.setBounds(200, 10, 370, 20);

        lbDsSabor.setText("Elementos");
        pnDados.add(lbDsSabor);
        lbDsSabor.setBounds(10, 40, 50, 14);

        tfDsSabor.setName("DS_SABOR");
        tfDsSabor.setObrigatorio(true);
        pnDados.add(tfDsSabor);
        tfDsSabor.setBounds(70, 40, 500, 20);

        pnDetalhe.add(pnDados);
        pnDados.setBounds(250, 200, 590, 80);

        pnCard.add(pnDetalhe, "detalhe");

        add(pnCard, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbCdSabor;
    private javax.swing.JLabel lbDsSabor;
    private javax.swing.JLabel lbNmSabor;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnCard;
    private view.componentes.TPanel pnDados;
    private javax.swing.JPanel pnDetalhe;
    private javax.swing.JPanel pnGrid;
    private javax.swing.JPanel pnTitulo;
    private view.componentes.table.TTable tbSabor;
    private view.componentes.TTextField tfCdSabor;
    private view.componentes.TTextField tfDsSabor;
    private view.componentes.TTextField tfNmSabor;
    // End of variables declaration//GEN-END:variables
}

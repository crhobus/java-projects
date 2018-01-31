package view.desktop.produto;

import control.Servidor;
import control.funcoes.Excecao;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import control.funcoes.Funcoes;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.entidades.Produto;
import model.entidades.Usuario;
import view.componentes.mascara.MaskFormater;
import view.componentes.table.TableRenderer;
import view.desktop.AcaoListener;
import view.desktop.ClickListener;
import view.desktop.SisComPizzaria;

public class CadasProduto extends JPanel implements AcaoListener {

    private SisComPizzaria sisCom;
    private ClickListener clickListener;
    private TableModelProduto tableModelProduto;
    private Produto produto;
    private String cardVisivel;
    private int linhaAtual;

    public CadasProduto(SisComPizzaria sisCom) {
        this.sisCom = sisCom;
        this.clickListener = sisCom;
        this.sisCom.setAcaoListener(this);
        this.tableModelProduto = new TableModelProduto(this);
        initComponents();
        ativar();
    }

    private void ativar() {
        //table grig
        tbProduto.inicializaTable();
        tbProduto.getTabela().getColumnModel().getColumn(0).setMinWidth(40);
        tbProduto.getTabela().getColumnModel().getColumn(1).setMinWidth(250);
        tbProduto.getTabela().getColumnModel().getColumn(2).setMinWidth(35);
        tbProduto.getTabela().getColumnModel().getColumn(3).setMinWidth(35);
        tbProduto.getTabela().getColumnModel().getColumn(4).setMinWidth(90);
        tbProduto.getTabela().getColumnModel().getColumn(5).setMinWidth(90);
        tbProduto.getTabela().getColumnModel().getColumn(6).setMinWidth(230);
        tbProduto.getTabela().getColumnModel().getColumn(7).setMinWidth(80);
        tbProduto.getTabela().getColumnModel().getColumn(8).setMinWidth(80);
        pnDados.inicializaPanel();

        pnDados.setPermiteAlterarDados(sisCom.isPermiteAlterarDados(1));
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if (tableModelProduto.possuiRegistros()) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        }
        cardVisivel = "grid";
        linhaAtual = 0;
    }

    public Servidor getServidor() {
        return sisCom.getServidor();
    }

    public Usuario getUsuario() {
        return sisCom.getUsuario();
    }

    @Override
    public void onAlternarGridDetalhe(boolean grid) {
        if (grid) {
            ((CardLayout) pnCard.getLayout()).show(pnCard, "grid");
            cardVisivel = "grid";
            desfazEdicao();
            if (tableModelProduto.possuiRegistros()) {
                tbProduto.selecionarLinha(tableModelProduto.getLinhaSelecionada());
            }
        } else {
            ((CardLayout) pnCard.getLayout()).show(pnCard, "detalhe");
            cardVisivel = "detalhe";
            linhaAtual = tbProduto.getTabela().getSelectedRow();
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
        produto = null;
        tfDsProduto.requestFocus();
    }

    @Override
    public void onSalvar() {
        if (pnDados.verificaCamposObrigatoriosPreenchidos()) {
            Excecao excecao = null;
            try {
                boolean novoProduto = false;
                if (produto == null) {
                    novoProduto = true;
                }
                produto = getServidor().getProdutoAction().salvar(pnDados.getDadosPanel(null), produto);
                if (produto != null) {
                    if (novoProduto) {
                        tableModelProduto.addProduto(produto);
                    }
                    tableModelProduto.atualizaTabela();
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
        if (produto != null) {
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
                getServidor().getProdutoAction().excluirProduto(tableModelProduto.removerProduto(produto).getCdProduto());
            } else {
                getServidor().getProdutoAction().excluirProduto(tableModelProduto.removerProduto(tbProduto.getTabela().getSelectedRow()).getCdProduto());
            }
        } catch (ExceptionError ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
        produto = null;
        tableModelProduto.atualizaTabela();
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelProduto.possuiRegistros()) {
            tbProduto.selecionarLinha(tableModelProduto.getLinhaSelecionada());
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else {
            clickListener.onExcluir(false);
        }
    }

    private void mostraRegistro() {
        if (tableModelProduto.possuiRegistros()) {
            pnDados.setKeyPressed(false);
            produto = tableModelProduto.getProduto(tbProduto.getTabela().getSelectedRow());
            tfCdProduto.setText(Integer.toString(produto.getCdProduto()));
            tfDsProduto.setText(produto.getDsProduto());
            tfVlProduto.setText(Double.toString(produto.getVlProduto()));
            tfDsMarca.setText(produto.getDsMarca());
            tfVlEstoque.setText(Double.toString(produto.getVlEstoque()));
            tfDsObservacao.setText(produto.getDsObservacao());
            tfDsTipo.setText(produto.getDsTipo());
         //   tfDtValidade.setText(Funcoes.dateToString(produto.getDtValidade(), 3));
         //   tfDtCadastro.setText(Funcoes.dateToString(produto.getDtValidade(), 3));
            
            System.out.println(Funcoes.dateToString(produto.getDtValidade(), 3));
            pnDados.setKeyPressed(true);
        } else {
            pnDados.limparCampos();
            tfDsProduto.requestFocus();
        }
    }

    public Produto getProduto() {
        return produto;
    }

    private void desfazEdicao() {
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelProduto.possuiRegistros()) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else if (produto != null) {
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
        tbProduto = new view.componentes.table.TTable();
        pnDetalhe = new javax.swing.JPanel();
        pnDados = new view.componentes.TPanel();
        lbCdProduto = new javax.swing.JLabel();
        tfCdProduto = new view.componentes.TTextField();
        lbDsProduto = new javax.swing.JLabel();
        tfDsProduto = new view.componentes.TFormattedTextField();
        lbVlProduto = new javax.swing.JLabel();
        tfVlProduto = new view.componentes.TFormattedTextField();
        lbDsMarca = new javax.swing.JLabel();
        tfDsMarca = new view.componentes.TTextField();
        lbVlEstoque = new javax.swing.JLabel();
        tfVlEstoque = new view.componentes.TFormattedTextField();
        lbDsObservacao = new javax.swing.JLabel();
        tfDsObservacao = new view.componentes.TTextField();
        lbDsTipo = new javax.swing.JLabel();
        tfDsTipo = new view.componentes.TTextField();
        lbDtCadastro = new javax.swing.JLabel();
        tfDtValidade = new view.componentes.TFormattedTextField();
        lbDtValidade = new javax.swing.JLabel();
        tfDtCadastro = new view.componentes.TFormattedTextField();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setLayout(new java.awt.BorderLayout());

        pnTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnTitulo.setPreferredSize(new java.awt.Dimension(0, 30));

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTitulo.setText("Produtos");
        pnTitulo.add(lbTitulo);

        add(pnTitulo, java.awt.BorderLayout.NORTH);

        pnCard.setLayout(new java.awt.CardLayout());

        pnGrid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnGrid.setLayout(new java.awt.BorderLayout());

        tbProduto.setConfigurar(true);
        tbProduto.setTableModel(tableModelProduto);
        pnGrid.add(tbProduto, java.awt.BorderLayout.CENTER);

        pnCard.add(pnGrid, "grid");

        pnDetalhe.setLayout(null);
        pnCard.add(pnDetalhe, "detalhe");

        pnDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnDados.setClickListener(clickListener);
        pnDados.setListenerkeyPressed(true);
        pnDados.setTemCamposObrigatorios(true);
        pnDados.setValidaCamposPanel(true);
        pnDados.setLayout(null);

        lbCdProduto.setText("Produto");
        pnDados.add(lbCdProduto);
        lbCdProduto.setBounds(30, 20, 50, 14);

        tfCdProduto.setEditable(false);
        tfCdProduto.setName("CD_PRODUTO");
        pnDados.add(tfCdProduto);
        tfCdProduto.setBounds(100, 20, 70, 20);

        lbDsProduto.setText("Descrição");
        pnDados.add(lbDsProduto);
        lbDsProduto.setBounds(200, 20, 50, 14);

        tfDsProduto.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        tfDsProduto.setName("DS_PRODUTO");
        tfDsProduto.setObrigatorio(true);
        pnDados.add(tfDsProduto);
        tfDsProduto.setBounds(260, 20, 300, 20);

        lbVlProduto.setText("Preço");
        pnDados.add(lbVlProduto);
        lbVlProduto.setBounds(30, 80, 40, 14);

        tfVlProduto.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        tfVlProduto.setName("VL_PRODUTO");
        pnDados.add(tfVlProduto);
        tfVlProduto.setBounds(100, 80, 80, 20);

        lbDsMarca.setText("Marca");
        pnDados.add(lbDsMarca);
        lbDsMarca.setBounds(30, 50, 40, 14);

        tfDsMarca.setName("DS_MARCA");
        pnDados.add(tfDsMarca);
        tfDsMarca.setBounds(100, 50, 100, 20);

        lbVlEstoque.setText("Estoque");
        pnDados.add(lbVlEstoque);
        lbVlEstoque.setBounds(400, 50, 40, 14);

        tfVlEstoque.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        tfVlEstoque.setName("VL_ESTOQUE");
        pnDados.add(tfVlEstoque);
        tfVlEstoque.setBounds(480, 50, 80, 20);

        lbDsObservacao.setText("Observações");
        pnDados.add(lbDsObservacao);
        lbDsObservacao.setBounds(30, 110, 70, 14);

        tfDsObservacao.setName("DS_OBSERVACAO");
        pnDados.add(tfDsObservacao);
        tfDsObservacao.setBounds(100, 110, 460, 20);

        lbDsTipo.setText("Tipo");
        pnDados.add(lbDsTipo);
        lbDsTipo.setBounds(230, 50, 20, 14);

        tfDsTipo.setName("DS_TIPO");
        pnDados.add(tfDsTipo);
        tfDsTipo.setBounds(260, 50, 120, 20);

        lbDtCadastro.setText("Data de cadastro");
        pnDados.add(lbDtCadastro);
        lbDtCadastro.setBounds(360, 80, 90, 14);

        tfDtValidade.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        tfDtValidade.setFormatterFactory(MaskFormater.getMaskFormater(1));
        tfDtValidade.setName("DT_VALIDADE");
        pnDados.add(tfDtValidade);
        tfDtValidade.setBounds(240, 80, 110, 20);

        lbDtValidade.setText("Validade");
        pnDados.add(lbDtValidade);
        lbDtValidade.setBounds(190, 80, 50, 14);

        tfDtCadastro.setEditable(false);
        tfDtCadastro.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        tfDtCadastro.setFormatterFactory(MaskFormater.getMaskFormater(1));
        tfDtCadastro.setName("DT_CADASTRO");
        pnDados.add(tfDtCadastro);
        tfDtCadastro.setBounds(450, 80, 110, 20);

        pnCard.add(pnDados, "detalhe");

        add(pnCard, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbCdProduto;
    private javax.swing.JLabel lbDsMarca;
    private javax.swing.JLabel lbDsObservacao;
    private javax.swing.JLabel lbDsProduto;
    private javax.swing.JLabel lbDsTipo;
    private javax.swing.JLabel lbDtCadastro;
    private javax.swing.JLabel lbDtValidade;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbVlEstoque;
    private javax.swing.JLabel lbVlProduto;
    private javax.swing.JPanel pnCard;
    private view.componentes.TPanel pnDados;
    private javax.swing.JPanel pnDetalhe;
    private javax.swing.JPanel pnGrid;
    private javax.swing.JPanel pnTitulo;
    private view.componentes.table.TTable tbProduto;
    private view.componentes.TTextField tfCdProduto;
    private view.componentes.TTextField tfDsMarca;
    private view.componentes.TTextField tfDsObservacao;
    private view.componentes.TFormattedTextField tfDsProduto;
    private view.componentes.TTextField tfDsTipo;
    private view.componentes.TFormattedTextField tfDtCadastro;
    private view.componentes.TFormattedTextField tfDtValidade;
    private view.componentes.TFormattedTextField tfVlEstoque;
    private view.componentes.TFormattedTextField tfVlProduto;
    // End of variables declaration//GEN-END:variables
}

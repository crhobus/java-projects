package view.desktop.cliente;

import control.Servidor;
import control.funcoes.Excecao;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.entidades.Cliente;
import model.entidades.Usuario;
import view.componentes.document.CampoValor;
import view.componentes.mascara.MaskFormater;
import view.componentes.table.TableRenderer;
import view.desktop.AcaoListener;
import view.desktop.ClickListener;
import view.desktop.SisComPizzaria;

public class CadasCliente extends JPanel implements AcaoListener {

    private SisComPizzaria sisCom;
    private ClickListener clickListener;
    private TableModelCliente tableModelCliente;
    private Cliente cliente;
    private String cardVisivel;
    private int linhaAtual;

    public CadasCliente(SisComPizzaria sisCom) {
        this.sisCom = sisCom;
        this.clickListener = sisCom;
        this.sisCom.setAcaoListener(this);
        this.tableModelCliente = new TableModelCliente(this);
        initComponents();
        ativar();
    }

    private void ativar() {
        //table grig
        tbCliente.inicializaTable();
        tbCliente.getTabela().getColumnModel().getColumn(0).setMinWidth(170);
        tbCliente.getTabela().getColumnModel().getColumn(1).setMinWidth(200);
        tbCliente.getTabela().getColumnModel().getColumn(2).setMinWidth(150);
        tbCliente.getTabela().getColumnModel().getColumn(3).setMinWidth(70);
        tbCliente.getTabela().getColumnModel().getColumn(4).setMinWidth(140);
        tbCliente.getTabela().getColumnModel().getColumn(5).setMinWidth(95);
        tbCliente.getTabela().getColumnModel().getColumn(6).setMinWidth(235);
        pnDados.inicializaPanel();
        pnDados.setPermiteAlterarDados(sisCom.isPermiteAlterarDados(1));
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if (tableModelCliente.possuiRegistros()) {
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
            if (tableModelCliente.possuiRegistros()) {
                tbCliente.selecionarLinha(tableModelCliente.getLinhaSelecionada());
            }
        } else {
            ((CardLayout) pnCard.getLayout()).show(pnCard, "detalhe");
            cardVisivel = "detalhe";
            linhaAtual = tbCliente.getTabela().getSelectedRow();
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
        cliente = null;
        tfNmCliente.requestFocus();
    }

    @Override
    public void onSalvar() {
        if (pnDados.verificaCamposObrigatoriosPreenchidos()) {
            Excecao excecao = null;
            try {
                boolean novoCliente = false;
                if (cliente == null) {
                    novoCliente = true;
                }
                cliente = getServidor().getClienteAction().salvar(pnDados.getDadosPanel(null), cliente);
                if (cliente != null) {
                    if (novoCliente) {
                        tableModelCliente.addCliente(cliente);
                    }
                    tableModelCliente.atualizaTabela();
                    desfazEdicao();
                }
            } catch (ExceptionInfo ex) {
                excecao = ex;
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Pizza Nostra - Usuário", JOptionPane.INFORMATION_MESSAGE);
            } catch (ExceptionError ex) {
                excecao = ex;
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
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
        if (cliente != null) {
            mostraRegistro();
        }
        desfazEdicao();
    }

    @Override
    public void onExcluir() {
        pnDados.setEdicao(false);
        try {
            if ("detalhe".equalsIgnoreCase(cardVisivel)) {
                getServidor().getClienteAction().excluirCliente(tableModelCliente.removerCliente(cliente).getCdCliente());
                pnDados.limparCampos();
            } else {
                getServidor().getClienteAction().excluirCliente(tableModelCliente.removerCliente(tbCliente.getTabela().getSelectedRow()).getCdCliente());
            }
        } catch (ExceptionError ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
        cliente = null;
        tableModelCliente.atualizaTabela();
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelCliente.possuiRegistros()) {
            tbCliente.selecionarLinha(tableModelCliente.getLinhaSelecionada());
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else {
            clickListener.onExcluir(false);
        }
    }

    private void mostraRegistro() {
        if (tableModelCliente.possuiRegistros()) {
            pnDados.setKeyPressed(false);
            cliente = tableModelCliente.getCliente(linhaAtual);
            tfNmCliente.setText(cliente.getNmCliente());
            tfNmUsuario.setText(cliente.getUsuario().getNmUsuario());
            tfDsEndereco.setText(cliente.getEndereco().getDsEndereco());
            tfNmBairro.setText(cliente.getEndereco().getNmBairro());
            ftfNrCep.setText(cliente.getEndereco().getNrCep());
            tfNrResidencia.setText(Integer.toString(cliente.getEndereco().getNrResidencia()));
            tfNmCidade.setText(cliente.getEndereco().getNmCidade());
            tfDsReferencia.setText(cliente.getEndereco().getDsReferencia());
            ftfNrTelefone.setText(cliente.getNrTefefone());
            ftfNrCelular.setText(cliente.getNrCelular());
            tfDsEmail.setText(cliente.getUsuario().getDsEmail());
            tfDsObservacao.setText(cliente.getDsObservacao());
            pfDsSenha.setText("");
            pfDsConfirmaSenha.setText("");
            tfNmCliente.requestFocus();
            pnDados.setKeyPressed(true);
        } else {
            pnDados.limparCampos();
            tfNmCliente.requestFocus();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void desfazEdicao() {
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelCliente.possuiRegistros()) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else if (cliente != null) {
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
        tbCliente = new view.componentes.table.TTable();
        pnDetalhe = new javax.swing.JPanel();
        pnDados = new view.componentes.TPanel();
        lbNmCliente = new javax.swing.JLabel();
        tfNmCliente = new view.componentes.TTextField();
        lbNmUsuario = new javax.swing.JLabel();
        tfNmUsuario = new view.componentes.TTextField();
        lbDsSenha = new javax.swing.JLabel();
        pfDsSenha = new view.componentes.TPasswordField();
        lbDsConfirmaSenha = new javax.swing.JLabel();
        pfDsConfirmaSenha = new view.componentes.TPasswordField();
        lbDsEndereco = new javax.swing.JLabel();
        tfDsEndereco = new view.componentes.TTextField();
        lbNmBairro = new javax.swing.JLabel();
        tfNmBairro = new view.componentes.TTextField();
        lbNrCep = new javax.swing.JLabel();
        ftfNrCep = new view.componentes.TFormattedTextField();
        lbNrResidencia = new javax.swing.JLabel();
        tfNrResidencia = new view.componentes.TTextField();
        lbNmCidade = new javax.swing.JLabel();
        tfNmCidade = new view.componentes.TTextField();
        lbDsReferencia = new javax.swing.JLabel();
        tfDsReferencia = new view.componentes.TTextField();
        lbNrCelular = new javax.swing.JLabel();
        ftfNrCelular = new view.componentes.TFormattedTextField();
        lbNrTelefone = new javax.swing.JLabel();
        ftfNrTelefone = new view.componentes.TFormattedTextField();
        lbDsEmail = new javax.swing.JLabel();
        tfDsEmail = new view.componentes.TTextField();
        lbDsObservacao = new javax.swing.JLabel();
        tfDsObservacao = new view.componentes.TTextField();

        setLayout(new java.awt.BorderLayout());

        pnTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnTitulo.setPreferredSize(new java.awt.Dimension(0, 30));

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTitulo.setText("Clientes");
        pnTitulo.add(lbTitulo);

        add(pnTitulo, java.awt.BorderLayout.NORTH);

        pnCard.setLayout(new java.awt.CardLayout());

        pnGrid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnGrid.setLayout(new java.awt.BorderLayout());

        tbCliente.setConfigurar(true);
        tbCliente.setRendener(new TableRenderer(false, 0));
        tbCliente.setTableModel(tableModelCliente);
        pnGrid.add(tbCliente, java.awt.BorderLayout.CENTER);

        pnCard.add(pnGrid, "grid");

        pnDetalhe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnDetalhe.setLayout(null);

        pnDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnDados.setClickListener(clickListener);
        pnDados.setListenerkeyPressed(true);
        pnDados.setTemCamposObrigatorios(true);
        pnDados.setValidaCamposPanel(true);
        pnDados.setLayout(null);

        lbNmCliente.setText("Nome");
        pnDados.add(lbNmCliente);
        lbNmCliente.setBounds(55, 20, 30, 14);

        tfNmCliente.setName("NM_CLIENTE");
        tfNmCliente.setObrigatorio(true);
        pnDados.add(tfNmCliente);
        tfNmCliente.setBounds(90, 18, 200, 20);

        lbNmUsuario.setText("Usuário");
        pnDados.add(lbNmUsuario);
        lbNmUsuario.setBounds(295, 20, 40, 14);

        tfNmUsuario.setName("NM_USUARIO");
        tfNmUsuario.setObrigatorio(true);
        tfNmUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNmUsuarioFocusGained(evt);
            }
        });
        pnDados.add(tfNmUsuario);
        tfNmUsuario.setBounds(335, 18, 100, 20);

        lbDsSenha.setText("Senha");
        pnDados.add(lbDsSenha);
        lbDsSenha.setBounds(440, 20, 30, 14);

        pfDsSenha.setName("DS_SENHA");
        pfDsSenha.setObrigatorio(true);
        pnDados.add(pfDsSenha);
        pfDsSenha.setBounds(475, 18, 100, 20);

        lbDsConfirmaSenha.setText("Confirma Senha");
        pnDados.add(lbDsConfirmaSenha);
        lbDsConfirmaSenha.setBounds(5, 48, 80, 14);

        pfDsConfirmaSenha.setName("DS_CONFIRMA_SENHA");
        pnDados.add(pfDsConfirmaSenha);
        pfDsConfirmaSenha.setBounds(90, 46, 100, 20);

        lbDsEndereco.setText("Endereço");
        pnDados.add(lbDsEndereco);
        lbDsEndereco.setBounds(195, 48, 45, 14);

        tfDsEndereco.setName("DS_ENDERECO");
        tfDsEndereco.setObrigatorio(true);
        pnDados.add(tfDsEndereco);
        tfDsEndereco.setBounds(245, 46, 330, 20);

        lbNmBairro.setText("Bairro");
        pnDados.add(lbNmBairro);
        lbNmBairro.setBounds(53, 76, 30, 14);

        tfNmBairro.setName("DS_BAIRRO");
        tfNmBairro.setObrigatorio(true);
        pnDados.add(tfNmBairro);
        tfNmBairro.setBounds(90, 74, 200, 20);

        lbNrCep.setText("Cep");
        pnDados.add(lbNrCep);
        lbNrCep.setBounds(295, 76, 20, 14);

        ftfNrCep.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfNrCep.setFormatterFactory(MaskFormater.getMaskFormater(3));
        ftfNrCep.setName("NR_CEP");
        ftfNrCep.setObrigatorio(true);
        pnDados.add(ftfNrCep);
        ftfNrCep.setBounds(320, 74, 110, 20);

        lbNrResidencia.setText("Número");
        pnDados.add(lbNrResidencia);
        lbNrResidencia.setBounds(435, 76, 40, 14);

        tfNrResidencia.setDocument(new CampoValor(false, false));
        tfNrResidencia.setName("NR_RESIDENCIA");
        tfNrResidencia.setObrigatorio(true);
        pnDados.add(tfNrResidencia);
        tfNrResidencia.setBounds(480, 74, 95, 20);

        lbNmCidade.setText("Cidade");
        pnDados.add(lbNmCidade);
        lbNmCidade.setBounds(47, 104, 35, 14);

        tfNmCidade.setName("NM_CIDADE");
        tfNmCidade.setObrigatorio(true);
        pnDados.add(tfNmCidade);
        tfNmCidade.setBounds(90, 102, 160, 20);

        lbDsReferencia.setText("Referência");
        pnDados.add(lbDsReferencia);
        lbDsReferencia.setBounds(255, 104, 55, 14);

        tfDsReferencia.setName("DS_REFERENCIA");
        pnDados.add(tfDsReferencia);
        tfDsReferencia.setBounds(315, 102, 260, 20);

        lbNrCelular.setText("Celular");
        pnDados.add(lbNrCelular);
        lbNrCelular.setBounds(195, 132, 35, 14);

        ftfNrCelular.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfNrCelular.setFormatterFactory(MaskFormater.getMaskFormater(2));
        ftfNrCelular.setName("NR_CELULAR");
        pnDados.add(ftfNrCelular);
        ftfNrCelular.setBounds(233, 130, 100, 20);

        lbNrTelefone.setText("Telefone");
        pnDados.add(lbNrTelefone);
        lbNrTelefone.setBounds(38, 132, 45, 14);

        ftfNrTelefone.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfNrTelefone.setFormatterFactory(MaskFormater.getMaskFormater(2));
        ftfNrTelefone.setName("NR_TELEFONE");
        pnDados.add(ftfNrTelefone);
        ftfNrTelefone.setBounds(90, 130, 100, 20);

        lbDsEmail.setText("Email");
        pnDados.add(lbDsEmail);
        lbDsEmail.setBounds(340, 132, 25, 20);

        tfDsEmail.setName("DS_EMAIL");
        pnDados.add(tfDsEmail);
        tfDsEmail.setBounds(370, 130, 205, 20);

        lbDsObservacao.setText("Observação");
        pnDados.add(lbDsObservacao);
        lbDsObservacao.setBounds(22, 160, 60, 20);

        tfDsObservacao.setName("DS_OBSERVACAO");
        pnDados.add(tfDsObservacao);
        tfDsObservacao.setBounds(90, 158, 483, 20);

        pnDetalhe.add(pnDados);
        pnDados.setBounds(227, 150, 665, 195);

        pnCard.add(pnDetalhe, "detalhe");

        add(pnCard, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tfNmUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNmUsuarioFocusGained
        String[] s = tfNmCliente.getText().toLowerCase().split(" ");
        String nmUsuario = "";
        String str;
        for (int i = 0; i < s.length; i++) {
            str = s[i];
            if (i == s.length - 1) {
                nmUsuario += str;
            } else {
                nmUsuario += str.substring(0, 1);
            }
        }
        tfNmUsuario.setText(nmUsuario);
    }//GEN-LAST:event_tfNmUsuarioFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.componentes.TFormattedTextField ftfNrCelular;
    private view.componentes.TFormattedTextField ftfNrCep;
    private view.componentes.TFormattedTextField ftfNrTelefone;
    private javax.swing.JLabel lbDsConfirmaSenha;
    private javax.swing.JLabel lbDsEmail;
    private javax.swing.JLabel lbDsEndereco;
    private javax.swing.JLabel lbDsObservacao;
    private javax.swing.JLabel lbDsReferencia;
    private javax.swing.JLabel lbDsSenha;
    private javax.swing.JLabel lbNmBairro;
    private javax.swing.JLabel lbNmCidade;
    private javax.swing.JLabel lbNmCliente;
    private javax.swing.JLabel lbNmUsuario;
    private javax.swing.JLabel lbNrCelular;
    private javax.swing.JLabel lbNrCep;
    private javax.swing.JLabel lbNrResidencia;
    private javax.swing.JLabel lbNrTelefone;
    private javax.swing.JLabel lbTitulo;
    private view.componentes.TPasswordField pfDsConfirmaSenha;
    private view.componentes.TPasswordField pfDsSenha;
    private javax.swing.JPanel pnCard;
    private view.componentes.TPanel pnDados;
    private javax.swing.JPanel pnDetalhe;
    private javax.swing.JPanel pnGrid;
    private javax.swing.JPanel pnTitulo;
    private view.componentes.table.TTable tbCliente;
    private view.componentes.TTextField tfDsEmail;
    private view.componentes.TTextField tfDsEndereco;
    private view.componentes.TTextField tfDsObservacao;
    private view.componentes.TTextField tfDsReferencia;
    private view.componentes.TTextField tfNmBairro;
    private view.componentes.TTextField tfNmCidade;
    private view.componentes.TTextField tfNmCliente;
    private view.componentes.TTextField tfNmUsuario;
    private view.componentes.TTextField tfNrResidencia;
    // End of variables declaration//GEN-END:variables
}

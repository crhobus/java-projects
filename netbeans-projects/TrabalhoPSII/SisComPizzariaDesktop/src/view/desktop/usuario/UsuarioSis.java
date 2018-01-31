package view.desktop.usuario;

import control.Servidor;
import control.funcoes.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.entidades.Funcao;
import model.entidades.Usuario;
import view.componentes.table.TableRenderer;
import view.desktop.AcaoListener;
import view.desktop.ClickListener;
import view.desktop.SisComPizzaria;

public class UsuarioSis extends JPanel implements AcaoListener {

    private SisComPizzaria sisCom;
    private ClickListener clickListener;
    private TableModelUsuario tableModelUsuario;
    private TableModelFuncoes tableModelFuncoes;
    private Usuario usuario;
    private List<Funcao> funcoes;

    public UsuarioSis(SisComPizzaria sisCom) {
        this.sisCom = sisCom;
        this.clickListener = sisCom;
        this.sisCom.setAcaoListener(this);
        this.tableModelUsuario = new TableModelUsuario(this);
        this.tableModelFuncoes = new TableModelFuncoes(this);
        initComponents();
        ativar();
    }

    private void ativar() {
        //table grig
        tbUsuario.inicializaTable();
        tbUsuario.getTabela().getColumnModel().getColumn(0).setMinWidth(80);
        tbUsuario.getTabela().getColumnModel().getColumn(1).setMinWidth(155);
        tbUsuario.getTabela().getColumnModel().getColumn(2).setMinWidth(310);
        tbUsuario.getTabela().getColumnModel().getColumn(3).setMinWidth(130);
        tbUsuario.getTabela().getColumnModel().getColumn(4).setMinWidth(0);
        tbUsuario.getTabela().getColumnModel().getColumn(4).setPreferredWidth(0);
        tbUsuario.getTabela().getColumnModel().getColumn(4).setMaxWidth(0);
        //table funções
        tbFuncoes.inicializaTable();
        tbFuncoes.getTabela().getColumnModel().getColumn(0).setMinWidth(198);
        tbFuncoes.getTabela().getColumnModel().getColumn(1).setMinWidth(80);
        tbFuncoes.getTabela().getColumnModel().getColumn(2).setMinWidth(40);
        tbFuncoes.getTabela().getColumnModel().getColumn(2).setCellEditor(new EditorPerfil(this));
        pnDetalhe.inicializaPanel();
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
            desfazEdicao();
        } else {
            ((CardLayout) pnCard.getLayout()).show(pnCard, "detalhe");
            mostraRegistro();
        }
    }

    @Override
    public void onNovo() {
        //
    }

    @Override
    public void onSalvar() {
        Dados dados = new Dados();
        dados.addInfo("NR_PERMISSAO_USUARIO_LOGADO", Integer.toString(getUsuario().getTipoUsuario().getCdTipoUsuario()));
        dados.addInfo("NM_USUARIO_LOGADO", getUsuario().getNmUsuario());
        Excecao excecao = null;
        try {
            if (getServidor().getUsuarioAction().alterarDadosUsuario(pnDetalhe.getDadosPanel(dados), usuario)) {
                tfDtAtualizacao.setText(Funcoes.dateToString(usuario.getDtAtualizacao(), 1));
                if (usuario.getCdUsuario() == getUsuario().getCdUsuario()) {
                    getUsuario().setDsEmail(usuario.getDsEmail());
                    getUsuario().setDtAtualizacao(usuario.getDtAtualizacao());
                    getUsuario().setDsSenha(usuario.getDsSenha());
                }
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
            pnDetalhe.setFocusCampo(excecao.getDsCampoFocar());
        }
    }

    @Override
    public void onDesfazer() {
        mostraRegistro();
        desfazEdicao();
    }

    @Override
    public void onExcluir() {
        //
    }

    private void mostraRegistro() {
        pnDetalhe.setKeyPressed(false);
        usuario = tableModelUsuario.getUsuario(tbUsuario.getTabela().getSelectedRow());
        tfCdUsuario.setText(Integer.toString(usuario.getCdUsuario()));
        tfNmUsuario.setText(usuario.getNmUsuario());
        tfDtAtualizacao.setText(Funcoes.dateToString(usuario.getDtAtualizacao(), 1));
        tfDsEmail.setText(usuario.getDsEmail());
        tfNmTipoUsuario.setText(usuario.getTipoUsuario().getNmTipoUsuario());
        pfDsSenhaAtual.setText("");
        pfDsNovaSenha.setText("");
        pfDsConfirmaSenha.setText("");
        tableModelFuncoes.setPerfis(usuario.getPerfis());
        listarFuncoes();
        if (usuario.getNmUsuario().equals(getUsuario().getNmUsuario())) {
            pfDsSenhaAtual.setEditable(true);
            pfDsNovaSenha.setEditable(true);
            pfDsConfirmaSenha.setEditable(true);
            tfDsEmail.setEditable(true);
            tfDsEmail.requestFocus();
        } else {
            pfDsSenhaAtual.setEditable(false);
            pfDsNovaSenha.setEditable(false);
            pfDsConfirmaSenha.setEditable(false);
            tfDsEmail.setEditable(false);
        }
        if (("Administração".equals(getUsuario().getTipoUsuario().getNmTipoUsuario())
                || "Gerência".equals(getUsuario().getTipoUsuario().getNmTipoUsuario()))
                && !("Administração".equals(usuario.getTipoUsuario().getNmTipoUsuario())
                || "Gerência".equals(usuario.getTipoUsuario().getNmTipoUsuario())
                || "Cliente".equals(usuario.getTipoUsuario().getNmTipoUsuario()))) {
            btConfirmar.setEnabled(true);
        } else {
            btConfirmar.setEnabled(false);
        }
        if ("Administração".equals(getUsuario().getTipoUsuario().getNmTipoUsuario())) {
            pfDsNovaSenha.setEditable(true);
        }
        pnDetalhe.setKeyPressed(true);
    }

    private void listarFuncoes() {
        try {
            cbDsFuncao.removeAllItems();
            funcoes = getServidor().getUsuarioAction().getFuncoes(usuario.getCdUsuario());
            for (Funcao funcao : funcoes) {
                cbDsFuncao.addItem(funcao.getNmFuncao());
            }
            cbDsFuncao.setSelectedItem(null);
        } catch (ExceptionError ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Usuario getUsuarioExibicao() {
        return usuario;
    }

    public void removeFuncaoPerfil(int linha) {
        try {
            if (getServidor().getUsuarioAction().removePerfilFuncao(usuario, tableModelFuncoes.getPerfil(linha))) {
                tableModelFuncoes.atualizaTabela();
                listarFuncoes();
            }
        } catch (ExceptionError ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void desfazEdicao() {
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTitulo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        pnCard = new javax.swing.JPanel();
        pnGrid = new javax.swing.JPanel();
        tbUsuario = new view.componentes.table.TTable();
        pnLegenda = new javax.swing.JPanel();
        pnAdministracao = new javax.swing.JPanel();
        lbAdministracao = new javax.swing.JLabel();
        pnGerencia = new javax.swing.JPanel();
        lbGerencia = new javax.swing.JLabel();
        pnFuncionario = new javax.swing.JPanel();
        lbFuncionario = new javax.swing.JLabel();
        pnCliente = new javax.swing.JPanel();
        lbCliente = new javax.swing.JLabel();
        pnDetalhe = new view.componentes.TPanel();
        lbCdUsuario = new javax.swing.JLabel();
        tfCdUsuario = new javax.swing.JTextField();
        lbNmUsuario = new javax.swing.JLabel();
        tfNmUsuario = new javax.swing.JTextField();
        lbDsEmail = new javax.swing.JLabel();
        tfDsEmail = new javax.swing.JTextField();
        lbDtAtualizacao = new javax.swing.JLabel();
        tfDtAtualizacao = new javax.swing.JTextField();
        lbDsSenhaAtual = new javax.swing.JLabel();
        pfDsSenhaAtual = new javax.swing.JPasswordField();
        lbDsConfirmaSenha = new javax.swing.JLabel();
        pfDsConfirmaSenha = new javax.swing.JPasswordField();
        lbNmTipoUsuario = new javax.swing.JLabel();
        tfNmTipoUsuario = new javax.swing.JTextField();
        lbNmPessoa = new javax.swing.JLabel();
        tfNmPessoa = new javax.swing.JTextField();
        lbDsNovaSenha = new javax.swing.JLabel();
        pfDsNovaSenha = new javax.swing.JPasswordField();
        pnFuncoes = new view.componentes.TPanel();
        lbDsFuncao = new javax.swing.JLabel();
        cbDsFuncao = new javax.swing.JComboBox();
        btConfirmar = new javax.swing.JButton();
        tbFuncoes = new view.componentes.table.TTable();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setLayout(new java.awt.BorderLayout());

        pnTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnTitulo.setPreferredSize(new java.awt.Dimension(0, 30));

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTitulo.setText("Usuários");
        pnTitulo.add(lbTitulo);

        add(pnTitulo, java.awt.BorderLayout.NORTH);

        pnCard.setLayout(new java.awt.CardLayout());

        pnGrid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnGrid.setLayout(new java.awt.BorderLayout());

        tbUsuario.setConfigurar(true);
        tbUsuario.setRendener(new TableRenderer(true, 1));
        tbUsuario.setTableModel(tableModelUsuario);
        pnGrid.add(tbUsuario, java.awt.BorderLayout.CENTER);

        pnLegenda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnLegenda.setPreferredSize(new java.awt.Dimension(0, 35));
        pnLegenda.setLayout(null);

        pnAdministracao.setBackground(new Color(0, 191, 255));
        pnAdministracao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnAdministracao.setLayout(null);

        lbAdministracao.setText("Administração");
        pnAdministracao.add(lbAdministracao);
        lbAdministracao.setBounds(12, 5, 70, 14);

        pnLegenda.add(pnAdministracao);
        pnAdministracao.setBounds(10, 5, 90, 25);

        pnGerencia.setBackground(new Color(84, 255, 159));
        pnGerencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnGerencia.setLayout(null);

        lbGerencia.setText("Gerência");
        pnGerencia.add(lbGerencia);
        lbGerencia.setBounds(23, 5, 45, 14);

        pnLegenda.add(pnGerencia);
        pnGerencia.setBounds(110, 5, 90, 25);

        pnFuncionario.setBackground(new Color(238, 197, 145));
        pnFuncionario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnFuncionario.setLayout(null);

        lbFuncionario.setText("Funcionário");
        pnFuncionario.add(lbFuncionario);
        lbFuncionario.setBounds(18, 5, 55, 14);

        pnLegenda.add(pnFuncionario);
        pnFuncionario.setBounds(210, 5, 90, 25);

        pnCliente.setBackground(new Color(207, 207, 207));
        pnCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnCliente.setLayout(null);

        lbCliente.setText("Cliente");
        pnCliente.add(lbCliente);
        lbCliente.setBounds(28, 5, 35, 14);

        pnLegenda.add(pnCliente);
        pnCliente.setBounds(310, 5, 90, 25);

        pnGrid.add(pnLegenda, java.awt.BorderLayout.SOUTH);

        pnCard.add(pnGrid, "grid");

        pnDetalhe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnDetalhe.setClickListener(clickListener);
        pnDetalhe.setListenerkeyPressed(true);
        pnDetalhe.setLayout(null);

        lbCdUsuario.setText("Código");
        pnDetalhe.add(lbCdUsuario);
        lbCdUsuario.setBounds(355, 92, 35, 14);

        tfCdUsuario.setBackground(new Color(255, 255, 255));
        tfCdUsuario.setEditable(false);
        tfCdUsuario.setName("CD_USUARIO");
        pnDetalhe.add(tfCdUsuario);
        tfCdUsuario.setBounds(395, 90, 80, 20);

        lbNmUsuario.setText("Usuário");
        pnDetalhe.add(lbNmUsuario);
        lbNmUsuario.setBounds(352, 120, 40, 14);

        tfNmUsuario.setBackground(new Color(255, 255, 255));
        tfNmUsuario.setEditable(false);
        tfNmUsuario.setName("NM_USUARIO");
        pnDetalhe.add(tfNmUsuario);
        tfNmUsuario.setBounds(395, 118, 90, 20);

        lbDsEmail.setText("Email");
        pnDetalhe.add(lbDsEmail);
        lbDsEmail.setBounds(363, 176, 25, 14);

        tfDsEmail.setBackground(new Color(255, 255, 255));
        tfDsEmail.setEditable(false);
        tfDsEmail.setName("DS_EMAIL");
        pnDetalhe.add(tfDsEmail);
        tfDsEmail.setBounds(395, 174, 325, 20);

        lbDtAtualizacao.setText("Data Atualização");
        pnDetalhe.add(lbDtAtualizacao);
        lbDtAtualizacao.setBounds(513, 92, 82, 14);

        tfDtAtualizacao.setBackground(new Color(255, 255, 255));
        tfDtAtualizacao.setEditable(false);
        tfDtAtualizacao.setName("DT_ATUALIZACAO");
        pnDetalhe.add(tfDtAtualizacao);
        tfDtAtualizacao.setBounds(600, 90, 120, 20);

        lbDsSenhaAtual.setText("Senha Atual");
        pnDetalhe.add(lbDsSenhaAtual);
        lbDsSenhaAtual.setBounds(535, 120, 60, 14);

        pfDsSenhaAtual.setBackground(new Color(255, 255, 255));
        pfDsSenhaAtual.setEditable(false);
        pfDsSenhaAtual.setName("DS_SENHA_ATUAL");
        pnDetalhe.add(pfDsSenhaAtual);
        pfDsSenhaAtual.setBounds(600, 118, 120, 20);

        lbDsConfirmaSenha.setText("Confirma Senha");
        pnDetalhe.add(lbDsConfirmaSenha);
        lbDsConfirmaSenha.setBounds(518, 148, 80, 14);

        pfDsConfirmaSenha.setBackground(new Color(255, 255, 255));
        pfDsConfirmaSenha.setEditable(false);
        pfDsConfirmaSenha.setName("DS_CONFIRMA_SENHA");
        pnDetalhe.add(pfDsConfirmaSenha);
        pfDsConfirmaSenha.setBounds(600, 146, 120, 20);

        lbNmTipoUsuario.setText("Tipo Usuário");
        pnDetalhe.add(lbNmTipoUsuario);
        lbNmTipoUsuario.setBounds(330, 204, 60, 14);

        tfNmTipoUsuario.setBackground(new Color(255, 255, 255));
        tfNmTipoUsuario.setEditable(false);
        tfNmTipoUsuario.setName("NM_TIPO_PESSOA");
        pnDetalhe.add(tfNmTipoUsuario);
        tfNmTipoUsuario.setBounds(395, 202, 120, 20);

        lbNmPessoa.setText("Pessoa");
        pnDetalhe.add(lbNmPessoa);
        lbNmPessoa.setBounds(521, 204, 35, 14);

        tfNmPessoa.setBackground(new Color(255, 255, 255));
        tfNmPessoa.setEditable(false);
        tfNmPessoa.setName("NM_PESSOA");
        pnDetalhe.add(tfNmPessoa);
        tfNmPessoa.setBounds(560, 202, 160, 20);

        lbDsNovaSenha.setText("Nova Senha");
        pnDetalhe.add(lbDsNovaSenha);
        lbDsNovaSenha.setBounds(330, 148, 60, 14);

        pfDsNovaSenha.setBackground(new Color(255, 255, 255));
        pfDsNovaSenha.setEditable(false);
        pfDsNovaSenha.setName("DS_NOVA_SENHA");
        pnDetalhe.add(pfDsNovaSenha);
        pfDsNovaSenha.setBounds(395, 146, 120, 20);

        pnFuncoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Funções Liberadas"));
        pnFuncoes.setLayout(null);

        lbDsFuncao.setText("Função");
        pnFuncoes.add(lbDsFuncao);
        lbDsFuncao.setBounds(23, 22, 38, 14);

        cbDsFuncao.setName("DS_FUNCAO");
        cbDsFuncao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbDsFuncaoKeyPressed(evt);
            }
        });
        pnFuncoes.add(cbDsFuncao);
        cbDsFuncao.setBounds(65, 20, 235, 20);

        btConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Confirmar.png"))); // NOI18N
        btConfirmar.setText("Confirmar");
        btConfirmar.setToolTipText("Liberar função");
        btConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btConfirmar.setEnabled(false);
        btConfirmar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfirmarActionPerformed(evt);
            }
        });
        pnFuncoes.add(btConfirmar);
        btConfirmar.setBounds(307, 15, 80, 30);

        tbFuncoes.setConfigurar(true);
        tbFuncoes.setRendener(new TableRenderer(false, 0));
        tbFuncoes.setTableModel(tableModelFuncoes);
        pnFuncoes.add(tbFuncoes);
        tbFuncoes.setBounds(4, 50, 402, 135);

        pnDetalhe.add(pnFuncoes);
        pnFuncoes.setBounds(330, 225, 410, 190);

        pnCard.add(pnDetalhe, "detalhe");

        add(pnCard, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cbDsFuncaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbDsFuncaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cbDsFuncao.setSelectedItem(null);
        }
    }//GEN-LAST:event_cbDsFuncaoKeyPressed

    private void btConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmarActionPerformed
        if (cbDsFuncao.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Selecione uma função", "Pizza Nostra - Usuário", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                getServidor().getUsuarioAction().addPerfilUsuario(usuario, funcoes.get(cbDsFuncao.getSelectedIndex()));
                tableModelFuncoes.atualizaTabela();
                cbDsFuncao.removeItemAt(cbDsFuncao.getSelectedIndex());
                cbDsFuncao.setSelectedItem(null);
            } catch (ExceptionError ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btConfirmarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConfirmar;
    private javax.swing.JComboBox cbDsFuncao;
    private javax.swing.JLabel lbAdministracao;
    private javax.swing.JLabel lbCdUsuario;
    private javax.swing.JLabel lbCliente;
    private javax.swing.JLabel lbDsConfirmaSenha;
    private javax.swing.JLabel lbDsEmail;
    private javax.swing.JLabel lbDsFuncao;
    private javax.swing.JLabel lbDsNovaSenha;
    private javax.swing.JLabel lbDsSenhaAtual;
    private javax.swing.JLabel lbDtAtualizacao;
    private javax.swing.JLabel lbFuncionario;
    private javax.swing.JLabel lbGerencia;
    private javax.swing.JLabel lbNmPessoa;
    private javax.swing.JLabel lbNmTipoUsuario;
    private javax.swing.JLabel lbNmUsuario;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPasswordField pfDsConfirmaSenha;
    private javax.swing.JPasswordField pfDsNovaSenha;
    private javax.swing.JPasswordField pfDsSenhaAtual;
    private javax.swing.JPanel pnAdministracao;
    private javax.swing.JPanel pnCard;
    private javax.swing.JPanel pnCliente;
    private view.componentes.TPanel pnDetalhe;
    private javax.swing.JPanel pnFuncionario;
    private view.componentes.TPanel pnFuncoes;
    private javax.swing.JPanel pnGerencia;
    private javax.swing.JPanel pnGrid;
    private javax.swing.JPanel pnLegenda;
    private javax.swing.JPanel pnTitulo;
    private view.componentes.table.TTable tbFuncoes;
    private view.componentes.table.TTable tbUsuario;
    private javax.swing.JTextField tfCdUsuario;
    private javax.swing.JTextField tfDsEmail;
    private javax.swing.JTextField tfDtAtualizacao;
    private javax.swing.JTextField tfNmPessoa;
    private javax.swing.JTextField tfNmTipoUsuario;
    private javax.swing.JTextField tfNmUsuario;
    // End of variables declaration//GEN-END:variables
}

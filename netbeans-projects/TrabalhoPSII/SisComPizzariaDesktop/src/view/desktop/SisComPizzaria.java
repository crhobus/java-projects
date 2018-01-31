package view.desktop;

import control.Servidor;
import control.funcoes.Funcoes;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.entidades.Perfil;
import model.entidades.Usuario;
import view.desktop.cliente.CadasCliente;
import view.desktop.funcionario.CadasFuncionario;
import view.desktop.pedido.PedidoSis;
import view.desktop.produto.CadasProduto;
import view.desktop.produto.CadasSabor;
import view.desktop.produto.CadasTamanho;
import view.desktop.usuario.TrocarSenhaUsuario;
import view.desktop.usuario.UsuarioSis;
import view.principal.Principal;

public class SisComPizzaria extends JFrame implements ClickListener {

    private Servidor serv;
    private Usuario usuario;
    private AcaoListener acaoListener;

    public SisComPizzaria(Servidor serv, Usuario usuario) {
        this.serv = serv;
        this.usuario = usuario;
        initComponents();
    }

    public void ativar() {
        lbUsuario.setText(lbUsuario.getText() + usuario.getNmUsuario());
        if (usuario.getTipoUsuario().getCdTipoUsuario() == 1) {
            lbPermissao.setText(lbPermissao.getText() + "Administração");
        } else if (usuario.getTipoUsuario().getCdTipoUsuario() == 2) {
            lbPermissao.setText(lbPermissao.getText() + "Gerência");
        } else if (usuario.getTipoUsuario().getCdTipoUsuario() == 3) {
            lbPermissao.setText(lbPermissao.getText() + "Funcionário");
        } else {
            lbPermissao.setText(lbPermissao.getText() + "Cliente");
        }
        TempoAtividadeClient tempo = new TempoAtividadeClient(this);
        tempo.start();
        alterarData(Funcoes.dateToString(new Date(), 3));
        setVisibleItensMenu();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setVisibleItensMenu() {
        if ("Administração".equals(usuario.getTipoUsuario().getNmTipoUsuario())
                || "Gerência".equals(usuario.getTipoUsuario().getNmTipoUsuario())) {
            miClientes.setEnabled(true);
            miFuncionarios.setEnabled(true);
//            miProdutos.setEnabled(true);
            miUsuario.setEnabled(true);
        } else {
            for (Perfil perfil : usuario.getPerfis()) {
                switch (perfil.getFuncao().getCdFuncao()) {
                    case 1:
                        miClientes.setEnabled(true);
                        break;
                    case 2:
                        miFuncionarios.setEnabled(true);
                        break;
                    case 3:
//                        miProdutos.setEnabled(true);
//                        break;
                    case 4:
                        miUsuario.setEnabled(true);
                        break;
                }
            }
        }
    }

    public boolean isPermiteAlterarDados(int cdFuncao) {
        if ("Administração".equals(usuario.getTipoUsuario().getNmTipoUsuario())
                || "Gerência".equals(usuario.getTipoUsuario().getNmTipoUsuario())) {
            return true;
        }
        for (Perfil perfil : usuario.getPerfis()) {
            if (perfil.getFuncao().getCdFuncao() == cdFuncao) {
                return "S".equalsIgnoreCase(perfil.getIeAlterarDados());
            }
        }
        return false;
    }

    public void alterarData(String data) {
        lbData.setText(data);
    }

    private void fechar() {
        pnCentro.removeAll();
        repaint();
        this.setTitle("Pizza Nostra");
        pnCentro.add(pnImagem, BorderLayout.CENTER);
        validate();
        btGridDetalhe.setEnabled(false);
        btNovo.setEnabled(false);
        btSalvar.setEnabled(false);
        btDesfazer.setEnabled(false);
        btExcluir.setEnabled(false);
        btFechar.setEnabled(false);
        if ("Grid".equals(btGridDetalhe.getText())) {
            btGridDetalhe.setText("Detalhe");
            btGridDetalhe.setToolTipText("Detalhe");
        }
    }

    private void sair() {
        try {
            if (JOptionPane.showConfirmDialog(this, "Deseja realmente sair do sistema?", "Pizza Nostra", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION) {
                serv.desconectar();
                System.exit(JFrame.EXIT_ON_CLOSE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
            System.exit(JFrame.EXIT_ON_CLOSE);
        }
    }

    private void addJPanel(int nrFuncao, String titulo) {
        btGridDetalhe.setEnabled(true);
        btNovo.setEnabled(false);
        btSalvar.setEnabled(false);
        btDesfazer.setEnabled(false);
        btExcluir.setEnabled(false);
        btFechar.setEnabled(true);
        JPanel panel = null;
        switch (nrFuncao) {
            case 1:
                panel = new CadasCliente(this);
                break;
            case 2:
                panel = new CadasFuncionario(this);
                break;
            case 3:
                panel = new CadasProduto(this);
                break;
            case 4:
                panel = new UsuarioSis(this);
                break;
            case 5:
                panel = new PedidoSis(this);
                break;
            case 6:
                panel = new CadasSabor(this);
                break;
            case 7:
                panel = new CadasTamanho(this);
                break;
        }
        pnCentro.removeAll();
        repaint();
        this.setTitle("Pizza Nostra - " + titulo);
        pnCentro.add(panel, BorderLayout.CENTER);
        validate();
        if ("Grid".equals(btGridDetalhe.getText())) {
            btGridDetalhe.setText("Detalhe");
            btGridDetalhe.setToolTipText("Detalhe");
        }
    }

    public void acaoBtGridDetalhe(boolean acaoFuncao) {
        if (acaoFuncao) {
            btGridDetalhe.setText("Grid");
            btGridDetalhe.setToolTipText("Grid");
        } else if ("Grid".equals(btGridDetalhe.getText())) {
            btGridDetalhe.setText("Detalhe");
            btGridDetalhe.setToolTipText("Detalhe");
            acaoListener.onAlternarGridDetalhe(true);
        } else {
            btGridDetalhe.setText("Grid");
            btGridDetalhe.setToolTipText("Grid");
            acaoListener.onAlternarGridDetalhe(false);
        }
    }

    public void setAcaoListener(AcaoListener acaoListener) {
        this.acaoListener = acaoListener;
    }

    public Servidor getServidor() {
        return serv;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public void onGridDetalhe(boolean gridDetalhe) {
        btGridDetalhe.setEnabled(gridDetalhe);
    }

    @Override
    public void onNovo(boolean novo) {
        btNovo.setEnabled(novo);
    }

    @Override
    public void onSalvar(boolean salvar) {
        btSalvar.setEnabled(salvar);
    }

    @Override
    public void onDesfazer(boolean desfazer) {
        btDesfazer.setEnabled(desfazer);
    }

    @Override
    public void onExcluir(boolean excluir) {
        btExcluir.setEnabled(excluir);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTitulo = new javax.swing.JPanel();
        pnCentro = new javax.swing.JPanel();
        pnImagem = new view.componentes.TPanelImagem();
        pnBarraStatus = new javax.swing.JPanel();
        lbUsuario = new javax.swing.JLabel();
        separator1 = new javax.swing.JSeparator();
        lbPermissao = new javax.swing.JLabel();
        separator2 = new javax.swing.JSeparator();
        lbData = new javax.swing.JLabel();
        pnBotoes = new javax.swing.JPanel();
        pnInterno = new javax.swing.JPanel();
        btFechar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btDesfazer = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btGridDetalhe = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        mnSistema = new javax.swing.JMenu();
        miTrocarSenha = new javax.swing.JMenuItem();
        miTrocarUsuario = new javax.swing.JMenuItem();
        miUsuario = new javax.swing.JMenuItem();
        miFechar = new javax.swing.JMenuItem();
        separator = new javax.swing.JPopupMenu.Separator();
        miSair = new javax.swing.JMenuItem();
        mnCadastros = new javax.swing.JMenu();
        miClientes = new javax.swing.JMenuItem();
        miFuncionarios = new javax.swing.JMenuItem();
        mnPedidos = new javax.swing.JMenu();
        miNovoPedido = new javax.swing.JMenuItem();
        mnCardapio = new javax.swing.JMenu();
        miPizzaPronta = new javax.swing.JMenuItem();
        miSabores = new javax.swing.JMenuItem();
        miTamanhos = new javax.swing.JMenuItem();
        mnSobre = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Pizza Nostra");
        setMinimumSize(new Dimension(1241, 665));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnTitulo.setPreferredSize(new java.awt.Dimension(0, 50));
        pnTitulo.setLayout(null);
        getContentPane().add(pnTitulo, java.awt.BorderLayout.NORTH);

        pnCentro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnCentro.setLayout(new java.awt.BorderLayout());

        pnImagem.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnImagem.setDirImagem("..\\\\SisComPizzariaDesktop\\\\src\\\\view\\\\imagens\\\\LogoCliente.png");
        pnImagem.setLayout(new java.awt.BorderLayout());
        pnCentro.add(pnImagem, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnCentro, java.awt.BorderLayout.CENTER);

        pnBarraStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnBarraStatus.setPreferredSize(new java.awt.Dimension(0, 20));
        pnBarraStatus.setLayout(null);

        lbUsuario.setText("Usuário: ");
        pnBarraStatus.add(lbUsuario);
        lbUsuario.setBounds(10, 3, 160, 14);

        separator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnBarraStatus.add(separator1);
        separator1.setBounds(155, 1, 3, 17);

        lbPermissao.setText("Permissão: ");
        pnBarraStatus.add(lbPermissao);
        lbPermissao.setBounds(163, 3, 170, 14);

        separator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnBarraStatus.add(separator2);
        separator2.setBounds(340, 1, 3, 17);
        pnBarraStatus.add(lbData);
        lbData.setBounds(345, 3, 70, 14);

        getContentPane().add(pnBarraStatus, java.awt.BorderLayout.SOUTH);

        pnBotoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnBotoes.setPreferredSize(new java.awt.Dimension(100, 0));
        pnBotoes.setLayout(new java.awt.BorderLayout());

        pnInterno.setPreferredSize(new java.awt.Dimension(0, 300));
        pnInterno.setLayout(null);

        btFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Fechar.png"))); // NOI18N
        btFechar.setText("Fechar");
        btFechar.setToolTipText("Fechar janela interna");
        btFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btFechar.setEnabled(false);
        btFechar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFecharActionPerformed(evt);
            }
        });
        pnInterno.add(btFechar);
        btFechar.setBounds(10, 250, 80, 40);

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Excluir.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.setToolTipText("Excluir registro");
        btExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btExcluir.setEnabled(false);
        btExcluir.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        pnInterno.add(btExcluir);
        btExcluir.setBounds(10, 205, 80, 40);

        btDesfazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Desfazer.png"))); // NOI18N
        btDesfazer.setText("Desfazer");
        btDesfazer.setToolTipText("Desfazer a alteração");
        btDesfazer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btDesfazer.setEnabled(false);
        btDesfazer.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btDesfazer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDesfazerActionPerformed(evt);
            }
        });
        pnInterno.add(btDesfazer);
        btDesfazer.setBounds(10, 160, 80, 40);

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Salvar.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setToolTipText("Salvar");
        btSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btSalvar.setEnabled(false);
        btSalvar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        pnInterno.add(btSalvar);
        btSalvar.setBounds(10, 115, 80, 40);

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Novo.png"))); // NOI18N
        btNovo.setText("Novo");
        btNovo.setToolTipText("Novo registro");
        btNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btNovo.setEnabled(false);
        btNovo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });
        pnInterno.add(btNovo);
        btNovo.setBounds(10, 70, 80, 40);

        btGridDetalhe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Grid.png"))); // NOI18N
        btGridDetalhe.setText("Detalhe");
        btGridDetalhe.setToolTipText("Detalhe");
        btGridDetalhe.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btGridDetalhe.setEnabled(false);
        btGridDetalhe.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btGridDetalhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGridDetalheActionPerformed(evt);
            }
        });
        pnInterno.add(btGridDetalhe);
        btGridDetalhe.setBounds(10, 25, 80, 40);

        pnBotoes.add(pnInterno, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnBotoes, java.awt.BorderLayout.EAST);

        mnSistema.setText("Sistema");

        miTrocarSenha.setText("Trocar senha");
        miTrocarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miTrocarSenhaActionPerformed(evt);
            }
        });
        mnSistema.add(miTrocarSenha);

        miTrocarUsuario.setText("Trocar usuário");
        miTrocarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miTrocarUsuarioActionPerformed(evt);
            }
        });
        mnSistema.add(miTrocarUsuario);

        miUsuario.setText("Usuário");
        miUsuario.setEnabled(false);
        miUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miUsuarioActionPerformed(evt);
            }
        });
        mnSistema.add(miUsuario);

        miFechar.setText("Fechar");
        miFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miFecharActionPerformed(evt);
            }
        });
        mnSistema.add(miFechar);
        mnSistema.add(separator);

        miSair.setText("Sair");
        miSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSairActionPerformed(evt);
            }
        });
        mnSistema.add(miSair);

        menuBar.add(mnSistema);

        mnCadastros.setText("Cadastros");

        miClientes.setText("Clientes");
        miClientes.setEnabled(false);
        miClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miClientesActionPerformed(evt);
            }
        });
        mnCadastros.add(miClientes);

        miFuncionarios.setText("Funcionários");
        miFuncionarios.setEnabled(false);
        miFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miFuncionariosActionPerformed(evt);
            }
        });
        mnCadastros.add(miFuncionarios);

        menuBar.add(mnCadastros);

        mnPedidos.setText("Pedidos");

        miNovoPedido.setText("Gerência de Pedidos");
        miNovoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNovoPedidoActionPerformed(evt);
            }
        });
        mnPedidos.add(miNovoPedido);

        menuBar.add(mnPedidos);

        mnCardapio.setText("Cardapio");

        miPizzaPronta.setText("Pizza Pronta");
        mnCardapio.add(miPizzaPronta);

        miSabores.setText("Sabores");
        miSabores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSaboresActionPerformed(evt);
            }
        });
        mnCardapio.add(miSabores);

        miTamanhos.setText("Tamanhos");
        miTamanhos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miTamanhosActionPerformed(evt);
            }
        });
        mnCardapio.add(miTamanhos);

        menuBar.add(mnCardapio);

        mnSobre.setText("Sobre");
        menuBar.add(mnSobre);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miTrocarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miTrocarSenhaActionPerformed
        fechar();
        TrocarSenhaUsuario trocarSenhaUsuario = new TrocarSenhaUsuario(this, true);
        trocarSenhaUsuario.ativar();
    }//GEN-LAST:event_miTrocarSenhaActionPerformed

    private void miTrocarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miTrocarUsuarioActionPerformed
        this.dispose();
        try {
            serv.desconectar();
        } catch (Exception ex) {
        }
        new Principal();
    }//GEN-LAST:event_miTrocarUsuarioActionPerformed

    private void miUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miUsuarioActionPerformed
        addJPanel(4, "Usuários");
    }//GEN-LAST:event_miUsuarioActionPerformed

    private void miFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miFecharActionPerformed
        fechar();
    }//GEN-LAST:event_miFecharActionPerformed

    private void miSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSairActionPerformed
        sair();
    }//GEN-LAST:event_miSairActionPerformed

    private void miClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miClientesActionPerformed
        addJPanel(1, "Clientes");
    }//GEN-LAST:event_miClientesActionPerformed

    private void miFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miFuncionariosActionPerformed
        addJPanel(2, "Funcionários");
    }//GEN-LAST:event_miFuncionariosActionPerformed

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed
        fechar();
    }//GEN-LAST:event_btFecharActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        acaoListener.onExcluir();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btDesfazerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesfazerActionPerformed
        acaoListener.onDesfazer();
    }//GEN-LAST:event_btDesfazerActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        acaoListener.onSalvar();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        acaoListener.onNovo();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btGridDetalheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGridDetalheActionPerformed
        acaoBtGridDetalhe(false);
    }//GEN-LAST:event_btGridDetalheActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        sair();
    }//GEN-LAST:event_formWindowClosing

    private void miNovoPedidoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miNovoPedidoActionPerformed
    {//GEN-HEADEREND:event_miNovoPedidoActionPerformed
        addJPanel(5, "Pedidos");
    }//GEN-LAST:event_miNovoPedidoActionPerformed

    private void miSaboresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSaboresActionPerformed
        addJPanel(6, "Sabores");
    }//GEN-LAST:event_miSaboresActionPerformed

    private void miTamanhosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miTamanhosActionPerformed
        addJPanel(7, "Tamanhos");
    }//GEN-LAST:event_miTamanhosActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDesfazer;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btGridDetalhe;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSalvar;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbPermissao;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem miClientes;
    private javax.swing.JMenuItem miFechar;
    private javax.swing.JMenuItem miFuncionarios;
    private javax.swing.JMenuItem miNovoPedido;
    private javax.swing.JMenuItem miPizzaPronta;
    private javax.swing.JMenuItem miSabores;
    private javax.swing.JMenuItem miSair;
    private javax.swing.JMenuItem miTamanhos;
    private javax.swing.JMenuItem miTrocarSenha;
    private javax.swing.JMenuItem miTrocarUsuario;
    private javax.swing.JMenuItem miUsuario;
    private javax.swing.JMenu mnCadastros;
    private javax.swing.JMenu mnCardapio;
    private javax.swing.JMenu mnPedidos;
    private javax.swing.JMenu mnSistema;
    private javax.swing.JMenu mnSobre;
    private javax.swing.JPanel pnBarraStatus;
    private javax.swing.JPanel pnBotoes;
    private javax.swing.JPanel pnCentro;
    private view.componentes.TPanelImagem pnImagem;
    private javax.swing.JPanel pnInterno;
    private javax.swing.JPanel pnTitulo;
    private javax.swing.JPopupMenu.Separator separator;
    private javax.swing.JSeparator separator1;
    private javax.swing.JSeparator separator2;
    // End of variables declaration//GEN-END:variables
}

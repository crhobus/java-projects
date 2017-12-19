package Usuario;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DAOFactory.DAOFactory;
import FormatacaoCampos.CamposInt;
import FormatacaoCampos.CriarObjGrafico;
import FormatacaoCampos.LimiteCampoLetMinus;
import Modelo.Usuario;

public class CadasUsuario extends JDialog implements ActionListener, ItemListener {

    private JTextField tfCodigo, tfNome, tfPermissao;
    private JPasswordField pfSenha, pfConfirmaSenha;
    private JButton btVer, btOk, btCancelar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private UsuarioControl controle;

    public CadasUsuario(DAOFactory daoFactory) {
        controle = new UsuarioControl(daoFactory);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Usuário");
        setLayout(null);
        JPanel panel1 = CriarObjGrafico.criarJPanel(10, 20, 425, 170);
        add(panel1);

        panel1.add(CriarObjGrafico.criarJLabel("Codigo", 20, 40, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(20, 58, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodUsu()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        panel1.add(tfCodigo);

        btVer = CriarObjGrafico.criarJButton("...", 104, 56, 31, 24);
        btVer.addActionListener(this);
        panel1.add(btVer);

        panel1.add(CriarObjGrafico.criarJLabel("Nome", 140, 40, 80, 14));
        tfNome = CriarObjGrafico.criarJTextField(140, 58, 243, 20);
        tfNome.setDocument(new LimiteCampoLetMinus(15));
        panel1.add(tfNome);

        panel1.add(CriarObjGrafico.criarJLabel("Senha", 20, 78, 90, 14));
        pfSenha = CriarObjGrafico.criarJPasswordField(20, 94, 110, 20);
        panel1.add(pfSenha);

        panel1.add(CriarObjGrafico.criarJLabel("Permissão", 20, 114, 80, 14));
        tfPermissao = CriarObjGrafico.criarJTextField(20, 130, 110, 20);
        tfPermissao.setDocument(new CamposInt());
        panel1.add(tfPermissao);

        panel1.add(CriarObjGrafico.criarJLabel("Confirme sua senha", 195, 70, 130, 50));
        pfConfirmaSenha = CriarObjGrafico.criarJPasswordField(195, 120, 120, 20);
        panel1.add(pfConfirmaSenha);

        JLabel lbObrigatorio = CriarObjGrafico.criarJLabel("*", 180, 120, 10, 10);
        lbObrigatorio.setForeground(Color.RED);
        panel1.add(lbObrigatorio);

        btOk = CriarObjGrafico.criarJButton("OK", 455, 110, 92, 26);
        btOk.addActionListener(this);
        add(btOk);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 455, 150, 92, 26);
        btCancelar.addActionListener(this);
        add(btCancelar);

        rbNovo = CriarObjGrafico.criarJRadioButton("Novo", 458, 30, 80, 20);
        rbNovo.addItemListener(this);
        rbNovo.setBackground(null);
        add(rbNovo);

        rbAlterar = CriarObjGrafico.criarJRadioButton("Alterar", 458, 50, 80, 20);
        rbAlterar.addItemListener(this);
        rbAlterar.setBackground(null);
        add(rbAlterar);

        rbExcluir = CriarObjGrafico.criarJRadioButton("Excluir", 458, 70, 80, 20);
        rbExcluir.addItemListener(this);
        rbExcluir.setBackground(null);
        add(rbExcluir);

        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(585, 255);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodUsu()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfNome.setText("");
        pfSenha.setText("");
        tfPermissao.setText("");
        pfConfirmaSenha.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void gravar() throws Exception {
        if ("".equals(tfNome.getText())) {
            tfNome.grabFocus();
            throw new Exception("Campo nome inválido");
        } else {
            if ("".equals(pfSenha.getText())) {
                pfSenha.grabFocus();
                throw new Exception("Campo senha inválido");
            } else {
                if ("".equals(tfPermissao.getText()) || Integer.parseInt(tfPermissao.getText()) <= 0) {
                    tfPermissao.grabFocus();
                    throw new Exception("Campo permissão inválido");
                } else {
                    if ("".equals(pfConfirmaSenha.getText())) {
                        pfConfirmaSenha.grabFocus();
                        throw new Exception("Campo confirma senha inválido");
                    } else {
                        if ((pfConfirmaSenha.getText()).equals(pfSenha.getText())) {
                            Usuario usuario = new Usuario();
                            usuario.setCodigo(Integer.parseInt(tfCodigo.getText()));
                            usuario.setNome(tfNome.getText());
                            usuario.setSenha(pfSenha.getText());
                            usuario.setPermissao(Integer.parseInt(tfPermissao.getText()));
                            usuario.setData(new Date());
                            Usuario usuarioLido = controle.getUsuario(usuario.getNome());
                            if (usuarioLido != null) {
                                int resposta = JOptionPane.showConfirmDialog(null, "O usuário " + usuario.getNome() + " ja esta cadastrado deseja substituilo? ", "Usuário", JOptionPane.YES_NO_OPTION);
                                if (resposta == 0) {
                                    usuario.setCodigo(usuarioLido.getCodigo());
                                    if (controle.setUsuario(usuario)) {
                                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Usuário", JOptionPane.INFORMATION_MESSAGE);
                                        limparTela();
                                    }
                                } else {
                                    return;
                                }
                            } else {
                                if (controle.setUsuario(usuario)) {
                                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Usuário", JOptionPane.INFORMATION_MESSAGE);
                                    limparTela();
                                }
                            }
                        } else {
                            throw new Exception("Confirma sua senha");
                        }
                    }
                }
            }
        }
    }

    private void excluir() throws Exception {
        if (controle.arqUsuVazio()) {
            JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String nome = JOptionPane.showInputDialog(null, "Informe o nome do usuário a ser excluído:", "Usuário", JOptionPane.INFORMATION_MESSAGE);
            if (nome != null) {
                if (controle.removeUsu(nome)) {
                    JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso", "Usuário", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Usuário", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.arqUsuVazio()) {
            JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String s = JOptionPane.showInputDialog(null, "Informe o nome do usuário a ser recuperado:", "Usuário", JOptionPane.INFORMATION_MESSAGE);
            if (s != null) {
                Usuario usuario = controle.getUsuario(s);
                if (usuario != null) {
                    limparTela();
                    tfCodigo.setText(Integer.toString(usuario.getCodigo()));
                    tfNome.setText(usuario.getNome());
                    pfSenha.setText(usuario.getSenha());
                    tfPermissao.setText(Integer.toString(usuario.getPermissao()));
                    JOptionPane.showMessageDialog(null, "Usuário recuperado com sucesso", "Usuário", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Usuário", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void abrirPesquisaUsu() throws Exception {
        if (controle.arqUsuVazio()) {
            JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            PesquisaUsuario pcl = new PesquisaUsuario(controle);
            pcl.setModal(true);
            pcl.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Usuário", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rbNovo.isSelected() == true) {
                    try {
                        gravar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbAlterar.isSelected() == true) {
                    try {
                        recuperar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbExcluir.isSelected() == true) {
                    try {
                        excluir();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (evento.getSource() == btVer) {
            try {
                abrirPesquisaUsu();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent evento) {
        if (evento.getSource() == rbNovo) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbAlterar.setSelected(false);
                rbExcluir.setSelected(false);
            }
        }
        if (evento.getSource() == rbAlterar) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbNovo.setSelected(false);
                rbExcluir.setSelected(false);
            }
        }
        if (evento.getSource() == rbExcluir) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbNovo.setSelected(false);
                rbAlterar.setSelected(false);
            }
        }
    }
}

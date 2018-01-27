package Usuario;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import Funcionario.ConsultaFunc;
import Funcionario.FuncionarioControl;
import Funcionario.ListenerFunc;
import Modelo.Funcionario;
import Modelo.Usuario;
import Principal.CamposInt;
import SSL.EchoClient;
import Seguranca.Seguranca;

public class CadasUsuario extends JDialog implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfCodFunc,
            tfNomeFunc, tfUsuario, tfPermissao, tfSetor, tfDepartamento,
            tfCargo;
    private JPasswordField pfSenha, pfConSenha;
    private JButton btConsulta, btConsultaFunc, btOk, btCancelar, btExcluir,
            btSair;
    private JLabel lbCodFuncObrig, lbUsuarioObrig, lbSenhaObrig,
            lbPermissaoObrig, lbConSenhaObrig;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;
    private SimpleDateFormat formatDateHora;
    private UsuarioControl controleUsuario;
    private FuncionarioControl controleFunc;
    private Seguranca seguranca;
    // Objeto de conexao com o servidor.
    private EchoClient echoClient = new EchoClient();

    public CadasUsuario(Connection con, Seguranca seguranca) {
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        controleUsuario = new UsuarioControl(con);
        controleFunc = new FuncionarioControl(con);
        this.seguranca = seguranca;
        echoClient = new EchoClient();
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Usuários");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 410, 303);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        add(panel);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 20, 60, 14);
        panel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controleUsuario.getProxCodUsuario()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        tfCodigo.addFocusListener(this);
        panel.add(tfCodigo);

        btConsulta = new JButton("...");
        btConsulta.setBounds(106, 35, 31, 26);
        btConsulta.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsulta.setToolTipText("Consulta Usuários");
        btConsulta.addActionListener(this);
        panel.add(btConsulta);

        JLabel lbDataCdas = new JLabel("Cadastro em");
        lbDataCdas.setBounds(143, 20, 90, 14);
        panel.add(lbDataCdas);

        tfDataCadas = new JTextField();
        tfDataCadas.setBounds(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.setEditable(false);
        tfDataCadas.setBackground(Color.WHITE);
        tfDataCadas.addFocusListener(this);
        panel.add(tfDataCadas);

        JLabel lbUltAlteracao = new JLabel("Última Alteração");
        lbUltAlteracao.setBounds(270, 20, 100, 14);
        panel.add(lbUltAlteracao);

        tfUltAlteracao = new JTextField();
        tfUltAlteracao.setBounds(270, 38, 120, 20);
        tfUltAlteracao.setEditable(false);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfUltAlteracao.addFocusListener(this);
        panel.add(tfUltAlteracao);

        JLabel lbCodFunc = new JLabel("Cod. Funcionario");
        lbCodFunc.setBounds(20, 63, 100, 14);
        panel.add(lbCodFunc);

        lbCodFuncObrig = new JLabel("");
        lbCodFuncObrig.setBounds(102, 66, 10, 14);
        lbCodFuncObrig.setForeground(Color.RED);
        panel.add(lbCodFuncObrig);

        tfCodFunc = new JTextField();
        tfCodFunc.setBounds(20, 81, 80, 20);
        tfCodFunc.setEditable(false);
        tfCodFunc.setBackground(Color.WHITE);
        tfCodFunc.addFocusListener(this);
        panel.add(tfCodFunc);

        btConsultaFunc = new JButton("...");
        btConsultaFunc.setBounds(106, 78, 31, 26);
        btConsultaFunc.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btConsultaFunc.setToolTipText("Consulta Funcionários");
        btConsultaFunc.addActionListener(this);
        panel.add(btConsultaFunc);

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(145, 63, 40, 14);
        panel.add(lbNome);

        tfNomeFunc = new JTextField();
        tfNomeFunc.setBounds(145, 81, 245, 20);
        tfNomeFunc.setEditable(false);
        tfNomeFunc.setBackground(Color.WHITE);
        tfNomeFunc.addFocusListener(this);
        panel.add(tfNomeFunc);

        JLabel lbSetor = new JLabel("Setor");
        lbSetor.setBounds(20, 105, 50, 14);
        panel.add(lbSetor);

        tfSetor = new JTextField();
        tfSetor.setBounds(20, 123, 180, 20);
        tfSetor.setEditable(false);
        tfSetor.setBackground(Color.WHITE);
        tfSetor.addFocusListener(this);
        panel.add(tfSetor);

        JLabel lbDepartamento = new JLabel("Departamento");
        lbDepartamento.setBounds(210, 105, 80, 14);
        panel.add(lbDepartamento);

        tfDepartamento = new JTextField();
        tfDepartamento.setBounds(210, 123, 180, 20);
        tfDepartamento.setEditable(false);
        tfDepartamento.setBackground(Color.WHITE);
        tfDepartamento.addFocusListener(this);
        panel.add(tfDepartamento);

        JLabel lbCargo = new JLabel("Cargo");
        lbCargo.setBounds(20, 147, 80, 14);
        panel.add(lbCargo);

        tfCargo = new JTextField();
        tfCargo.setBounds(20, 165, 180, 20);
        tfCargo.setEditable(false);
        tfCargo.setBackground(Color.WHITE);
        tfCargo.addFocusListener(this);
        panel.add(tfCargo);

        JLabel lbPermissao = new JLabel("Permissão");
        lbPermissao.setBounds(210, 147, 50, 14);
        panel.add(lbPermissao);

        lbPermissaoObrig = new JLabel("");
        lbPermissaoObrig.setBounds(260, 150, 10, 14);
        lbPermissaoObrig.setForeground(Color.RED);
        panel.add(lbPermissaoObrig);

        tfPermissao = new JTextField();
        tfPermissao.setBounds(210, 165, 55, 20);
        tfPermissao.setDocument(new CamposInt());
        tfPermissao.addFocusListener(this);
        panel.add(tfPermissao);

        JLabel lbUsuario = new JLabel("Usuário");
        lbUsuario.setBounds(272, 147, 50, 14);
        panel.add(lbUsuario);

        lbUsuarioObrig = new JLabel("");
        lbUsuarioObrig.setBounds(310, 150, 10, 14);
        lbUsuarioObrig.setForeground(Color.RED);
        panel.add(lbUsuarioObrig);

        tfUsuario = new JTextField();
        tfUsuario.setBounds(272, 165, 117, 20);
        tfUsuario.addFocusListener(this);
        panel.add(tfUsuario);

        JLabel lbSenha = new JLabel("Senha");
        lbSenha.setBounds(20, 190, 50, 14);
        panel.add(lbSenha);

        lbSenhaObrig = new JLabel("");
        lbSenhaObrig.setBounds(51, 193, 10, 14);
        lbSenhaObrig.setForeground(Color.RED);
        panel.add(lbSenhaObrig);

        pfSenha = new JPasswordField();
        pfSenha.setBounds(20, 208, 125, 20);
        pfSenha.addFocusListener(this);
        panel.add(pfSenha);

        JLabel lbConSenha = new JLabel("Confirma Senha");
        lbConSenha.setBounds(153, 190, 80, 14);
        panel.add(lbConSenha);

        lbConSenhaObrig = new JLabel("");
        lbConSenhaObrig.setBounds(230, 193, 10, 14);
        lbConSenhaObrig.setForeground(Color.RED);
        panel.add(lbConSenhaObrig);

        pfConSenha = new JPasswordField();
        pfConSenha.setBounds(153, 208, 125, 20);
        pfConSenha.addFocusListener(this);
        panel.add(pfConSenha);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 248, 410, 3);
        panel.add(separator);

        Icon icOk = new ImageIcon("OK.png");
        btOk = new JButton("OK", icOk);
        btOk.setBounds(30, 263, 70, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("Confirma Operação");
        btOk.addActionListener(this);
        panel.add(btOk);

        Icon icCancelar = new ImageIcon("Cancelar.png");
        btCancelar = new JButton("Cancelar", icCancelar);
        btCancelar.setBounds(120, 263, 70, 26);
        btCancelar.setMargin(new Insets(0, 0, 0, 0));
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Limpar os Campos");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        Icon icExcluir = new ImageIcon("Excluir.png");
        btExcluir = new JButton("Excluir", icExcluir);
        btExcluir.setBounds(210, 263, 70, 26);
        btExcluir.setMargin(new Insets(0, 0, 0, 0));
        btExcluir.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.setToolTipText("Excluir Registro");
        btExcluir.addActionListener(this);
        panel.add(btExcluir);

        Icon icSair = new ImageIcon("Sair.png");
        btSair = new JButton("Sair", icSair);
        btSair.setBounds(300, 263, 70, 26);
        btSair.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btSair.setToolTipText("Fechar");
        btSair.addActionListener(this);
        panel.add(btSair);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(438, 353);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controleUsuario.getProxCodUsuario()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfCodFunc.setText("");
        tfNomeFunc.setText("");
        tfSetor.setText("");
        tfDepartamento.setText("");
        tfCargo.setText("");
        tfPermissao.setText("");
        tfUsuario.setText("");
        pfSenha.setText("");
        pfConSenha.setText("");
        lbCodFuncObrig.setText("");
        lbUsuarioObrig.setText("");
        lbSenhaObrig.setText("");
        lbPermissaoObrig.setText("");
        lbConSenhaObrig.setText("");
    }

    @SuppressWarnings("deprecation")
    private void gravar() throws Exception {
        boolean flag = false;
        if ("".equals(tfCodFunc.getText())) {
            lbCodFuncObrig.setText("*");
            flag = true;
        } else {
            lbCodFuncObrig.setText("");
        }
        if ("".equals(tfPermissao.getText())) {
            lbPermissaoObrig.setText("*");
            flag = true;
        } else {
            lbPermissaoObrig.setText("");
        }
        if ("".equals(tfUsuario.getText().trim())) {
            lbUsuarioObrig.setText("*");
            flag = true;
        } else {
            lbUsuarioObrig.setText("");
        }
        if ("".equals(pfSenha.getText().trim())) {
            lbSenhaObrig.setText("*");
            flag = true;
        } else {
            lbSenhaObrig.setText("");
        }
        if ("".equals(pfConSenha.getText().trim())) {
            lbConSenhaObrig.setText("*");
            flag = true;
        } else {
            lbConSenhaObrig.setText("");
        }
        if (!flag) {
            if (!pfSenha.getText().equals(pfConSenha.getText())) {
                lbConSenhaObrig.setText("*");
                throw new Exception("Confirme sua senha");
            } else {
                lbConSenhaObrig.setText("");
                Usuario usuario = new Usuario();

                try {
                    usuario.setSenha(seguranca.gerarHash(new String(pfSenha.getPassword())));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                usuario.setCodUsuario(Integer.parseInt(tfCodigo.getText()));
                usuario.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
                usuario.setUltAlteracao(new Date());
                usuario.setCodFunc(Integer.parseInt(tfCodFunc.getText()));
                usuario.setPermissao(Integer.parseInt(tfPermissao.getText()));
                usuario.setUsuario(tfUsuario.getText());

                echoClient.conectar(false);
                echoClient.mandarRequisicao(usuario);
                echoClient.desconectar();

                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Usuário", JOptionPane.INFORMATION_MESSAGE);
                limparTela();

//                int codUsuarioCadas = controleUsuario.getUsuarioCadastrado(tfUsuario.getText(), Integer.parseInt(tfCodFunc.getText()));// verifica se usuários já cadastrado para este funcionário
//                if (codUsuarioCadas != -1) {
//                    if (JOptionPane.showConfirmDialog(null, "Confirmar alteração nos dados", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                        if (controleUsuario.updateUsuario(usuario)) {
//                            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "Usuário", JOptionPane.INFORMATION_MESSAGE);
//                            limparTela();
//                        }
//                    }
//                } else {
//                    codUsuarioCadas = controleUsuario.getJaCadastrado(tfUsuario.getText(), Integer.parseInt(tfCodFunc.getText()));// verifica se usuário já cadastrado, ou funcionário já tem um usuário
//                    if (codUsuarioCadas != -1) {
//                        JOptionPane.showMessageDialog(null, "Este usuário ou funcionário ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
//                    } else {
//                        if (controleUsuario.insertUsuario(usuario)) {
//                            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Usuário", JOptionPane.INFORMATION_MESSAGE);
//                            limparTela();
//                        }
//                    }
//                }
            }
        } else {
            throw new Exception("Campos * obrigatórios inválidos");
        }
    }

    private void consultaUsuario() throws Exception {
        if (controleUsuario.isUsuarioVazio()) {
            throw new Exception("Não há usuários cadastrados");
        }
        ConsultaUsuario consulta = new ConsultaUsuario(controleUsuario);
        consulta.setListener(new ListenerUsuario() {
            @Override
            public void exibeUsuario(Usuario usuario) {
                limparTela();
                try {
                    tfDepartamento.setText(new String(seguranca.decriptarAssimetricamente(usuario.getDepartamento().getDepartamento(), false)));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                tfCodigo.setText(Integer.toString(usuario.getCodUsuario()));
                tfDataCadas.setText(formatDate.format(usuario.getDataCadastro()) + " as " + formatHora.format(usuario.getDataCadastro()));
                tfUltAlteracao.setText(formatDate.format(usuario.getUltAlteracao()) + " as " + formatHora.format(usuario.getUltAlteracao()));
                tfCodFunc.setText(Integer.toString(usuario.getCodFunc()));
                tfNomeFunc.setText(usuario.getNome());
                tfSetor.setText(usuario.getSetor().getSetor());
                tfCargo.setText(usuario.getCargo());
                tfPermissao.setText(Integer.toString(usuario.getPermissao()));
                tfUsuario.setText(usuario.getUsuario());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (controleUsuario.isUsuarioVazio()) {
            throw new Exception("Não há usuários cadastrados");
        }
        if (!controleUsuario.isUsuarioCadastrado(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione um usuário");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse usuário", "Usuário", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (controleUsuario.deleteUsuario(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso", "Usuário", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            }
        }
    }

    private void consultaFunc() throws Exception {
        if (controleFunc.isFuncVazio()) {
            throw new Exception("Não há funcionários cadastrados");
        }
        ConsultaFunc consulta = new ConsultaFunc(controleFunc);
        consulta.setListener(new ListenerFunc() {
            @Override
            public void exibeFunc(Funcionario funcionario) {
                try {
                    tfDepartamento.setText(new String(seguranca.decriptarAssimetricamente(funcionario.getDepartamento().getDepartamento(), false)));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                tfCodFunc.setText(Integer.toString(funcionario.getCodFunc()));
                tfNomeFunc.setText(funcionario.getNome());
                tfSetor.setText(funcionario.getSetor().getSetor());
                tfCargo.setText(funcionario.getCargo());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btOk) {
            try {
                gravar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btExcluir) {
            try {
                excluir();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btSair) {
            this.dispose();
        }
        if (evento.getSource() == btConsulta) {
            try {
                consultaUsuario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaFunc) {
            try {
                consultaFunc();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDataCadas) {
            tfDataCadas.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUltAlteracao) {
            tfUltAlteracao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodFunc) {
            tfCodFunc.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeFunc) {
            tfNomeFunc.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSetor) {
            tfSetor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDepartamento) {
            tfDepartamento.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCargo) {
            tfCargo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPermissao) {
            tfPermissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUsuario) {
            tfUsuario.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == pfSenha) {
            pfSenha.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == pfConSenha) {
            pfConSenha.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfCodFunc.setBackground(Color.WHITE);
        tfNomeFunc.setBackground(Color.WHITE);
        tfSetor.setBackground(Color.WHITE);
        tfDepartamento.setBackground(Color.WHITE);
        tfCargo.setBackground(Color.WHITE);
        tfPermissao.setBackground(Color.WHITE);
        tfUsuario.setBackground(Color.WHITE);
        pfSenha.setBackground(Color.WHITE);
        pfConSenha.setBackground(Color.WHITE);
    }
}

package Visao.Usuario;

import Arquivos.*;
import ClassPadrao.MeuDocument;
import Controle.UsuarioControl;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class CadasUsuario extends JDialog implements ActionListener, FocusListener, ItemListener {

    private JTextField tfCodigo, tfNome, tfPermissao;
    private JPasswordField tfSenha, tfConfirmaSenha;
    private JButton btVer, btPesquisar, btOk, btCancelar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private UsuarioControl control;
    private Arquivo arquivo;
    private LerArquivo lerArquivo;

    public CadasUsuario() {
        setTitle("Cadastro de Usuário");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte1 = new Font("Arial", Font.PLAIN, 12);
        Font fonte2 = new Font("Arial Black", Font.BOLD, 11);
        JPanel painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(10, 20, 425, 170);
        tela.add(painel1);
        painel1.setBorder(BorderFactory.createTitledBorder("Usuário"));
        control = new UsuarioControl();
        arquivo = new Arquivo();
        lerArquivo = new LerArquivo();
        control.lerArquivo(lerArquivo);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 40, 80, 14);
        lbCodigo.setFont(fonte1);
        painel1.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 58, 80, 20);
        tfCodigo.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        painel1.add(tfCodigo);
        tfCodigo.addFocusListener(this);

        btVer = new JButton("...");
        btVer.setBounds(104, 56, 31, 24);
        painel1.add(btVer);
        btVer.addActionListener(this);

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(140, 40, 80, 14);
        lbNome.setFont(fonte1);
        painel1.add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(140, 58, 243, 20);
        painel1.add(tfNome);
        tfNome.addFocusListener(this);

        JLabel lbSenha = new JLabel("Senha");
        lbSenha.setBounds(20, 78, 90, 14);
        lbSenha.setFont(fonte1);
        painel1.add(lbSenha);

        tfSenha = new JPasswordField();
        tfSenha.setBounds(20, 94, 110, 20);
        tfSenha.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel1.add(tfSenha);
        tfSenha.addFocusListener(this);

        JLabel lbPermissao = new JLabel("Permissão");
        lbPermissao.setBounds(20, 114, 80, 14);
        lbPermissao.setFont(fonte1);
        painel1.add(lbPermissao);

        tfPermissao = new JTextField();
        tfPermissao.setBounds(20, 130, 110, 20);
        tfPermissao.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel1.add(tfPermissao);
        tfPermissao.addFocusListener(this);

        JLabel lbConfirmaSenha = new JLabel("Confirme sua senha");
        lbConfirmaSenha.setBounds(195, 70, 130, 50);
        lbConfirmaSenha.setFont(fonte1);
        painel1.add(lbConfirmaSenha);

        tfConfirmaSenha = new JPasswordField();
        tfConfirmaSenha.setBounds(195, 120, 120, 20);
        tfConfirmaSenha.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel1.add(tfConfirmaSenha);
        tfConfirmaSenha.addFocusListener(this);

        JLabel lbAsteristico = new JLabel("*");
        lbAsteristico.setBounds(180, 120, 10, 10);
        lbAsteristico.setForeground(Color.RED);//Cor Vermelha no lbAsteristico
        lbAsteristico.setFont(fonte2);
        painel1.add(lbAsteristico);

        btPesquisar = new JButton("Pesquisar");
        btPesquisar.setBounds(455, 25, 92, 26);
        tela.add(btPesquisar);
        btPesquisar.addActionListener(this);

        rbNovo = new JRadioButton("Novo");
        rbNovo.setBounds(458, 70, 80, 20);
        rbNovo.setFont(fonte1);
        tela.add(rbNovo);
        rbNovo.addItemListener(this);

        rbAlterar = new JRadioButton("Alterar");
        rbAlterar.setBounds(458, 90, 80, 20);
        rbAlterar.setFont(fonte1);
        tela.add(rbAlterar);
        rbAlterar.addItemListener(this);

        rbExcluir = new JRadioButton("Excluir");
        rbExcluir.setBounds(458, 110, 80, 20);
        rbExcluir.setFont(fonte1);
        tela.add(rbExcluir);
        rbExcluir.addItemListener(this);

        btOk = new JButton("OK");
        btOk.setBounds(455, 150, 92, 26);
        tela.add(btOk);
        btOk.addActionListener(this);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(455, 190, 92, 26);
        tela.add(btCancelar);
        btCancelar.addActionListener(this);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setResizable(false);
        setSize(585, 255);
        setLocationRelativeTo(null);//Posiciona no centro da tela
    }

    private void gravar() throws Exception {
        String nome;
        int codigo, permissao, senha;
        Date data;

        if ("".equals(tfCodigo.getText()) || Integer.parseInt(tfCodigo.getText()) <= 0) {
            tfCodigo.grabFocus();
            throw new Exception("Campo codigo inválido");
        } else {
            codigo = Integer.parseInt(tfCodigo.getText());
            if ("".equals(tfNome.getText())) {
                tfNome.grabFocus();
                throw new Exception("Campo nome inválido");
            } else {
                nome = tfNome.getText();
                if ("".equals(tfSenha.getText()) || Integer.parseInt(tfSenha.getText()) <= 0) {
                    tfSenha.grabFocus();
                    throw new Exception("Campo senha inválido");
                } else {
                    senha = Integer.parseInt(tfSenha.getText());
                    if ("".equals(tfPermissao.getText()) || Integer.parseInt(tfPermissao.getText()) <= 0) {
                        tfPermissao.grabFocus();
                        throw new Exception("Campo permissão inválido");
                    } else {
                        permissao = Integer.parseInt(tfPermissao.getText());
                        if ("".equals(tfConfirmaSenha.getText()) || Integer.parseInt(tfConfirmaSenha.getText()) <= 0) {
                            tfConfirmaSenha.grabFocus();
                            throw new Exception("Campo confirma senha inválido");
                        } else {
                            if ((tfConfirmaSenha.getText()).equals(tfSenha.getText())) {
                                data = new Date();
                                control.adicionar(nome, senha, codigo, permissao, data);
                                JOptionPane.showMessageDialog(null, "Numeros de usuários cadastrados " + control.tamanho(), "Informação", JOptionPane.INFORMATION_MESSAGE);
                                limparTela();
                            } else {
                                throw new Exception("Confirma sua senha");
                            }
                        }
                    }
                }
            }
        }
    }

    private void ok() throws Exception {
        if (control.vazio() == true) {
            try {
                gravar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            boolean encontrado = false;
            if (control.usarioCadasNome(tfNome.getText()) == true) {
                encontrado = true;
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "O usuário " + tfNome.getText() + " ja esta cadastrado deseja Substituilo? ", "Usuário", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    control.removerNome(tfNome.getText());
                    try {
                        gravar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                try {
                    gravar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há usuários cadastrados");
        } else {
            String texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Usuário", JOptionPane.INFORMATION_MESSAGE);
            if (texto == null) {
            } else {
                boolean encontrado = false;
                if (control.recuperar(texto) == true) {
                    tfCodigo.setText(Integer.toString(control.codUsuarioCads()));
                    tfNome.setText(control.nomUsuarioCads());
                    tfSenha.setText(Integer.toString(control.senUsuarioCads()));
                    tfPermissao.setText(Integer.toString(control.permUsuarioCadas()));
                    encontrado = true;
                }
                if (encontrado == true) {
                    JOptionPane.showMessageDialog(null, "Usuario encontrado e recuperado", "Usuário", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void excluir() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há usuários cadastrados");
        } else {
            boolean erro = false;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Informe o codigo do usuário a ser Excluído:", "Saída de dados", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.usuarioCadasCod(codigo) == true) {
                            control.removerCod(codigo);
                            JOptionPane.showMessageDialog(null, "Usuário Removido com sucesso", "Usuario", JOptionPane.INFORMATION_MESSAGE);
                            limparTela();
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Usuario", JOptionPane.INFORMATION_MESSAGE);
                        }
                        erro = false;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Entre com um numero válido", "Erro", JOptionPane.ERROR_MESSAGE);
                        erro = true;
                    }
                }
            } while (erro);
        }
    }

    private void pesquisar() throws Exception {
        int codigo = 0;
        if (control.vazio() == true) {
            throw new Exception("Não há usuários cadastrados");
        } else {
            String texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Usuário", JOptionPane.INFORMATION_MESSAGE);
            if (texto == null) {
            } else {
                boolean encontrado = false;
                if (control.usarioCadasNome(texto) == true) {
                    codigo = control.codUsuarioCads();
                    encontrado = true;
                }
                if (encontrado == true) {
                    JOptionPane.showMessageDialog(null, "O usuário " + texto + " esta inserido com o codigo " + codigo, "Usuário", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    throw new Exception("Usuário " + texto + " não encontrado");
                }
            }
        }
    }

    private void limparTela() {
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        tfNome.setText("");
        tfSenha.setText("");
        tfPermissao.setText("");
        tfConfirmaSenha.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void codigo() {
        if ("".equals(tfCodigo.getText())) {
            limparTela();
        } else {
            if (control.vazio() == true) {
            } else {
                int n = Integer.parseInt(tfCodigo.getText());
                boolean encontrado = false;
                if (control.usuarioCadasCod(n) == true) {
                    encontrado = true;
                }
                if (encontrado == true) {
                    limparTela();
                }
            }
        }
    }

    private void verTodosCadastrados() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há usuários cadastrados");
        } else {
            VerTodosCadas ver = new VerTodosCadas(control);
            ver.setModal(true);
            ver.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Usuário", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rbNovo.isSelected() == true) {//Verifica se JRadioButton esta ou não selecionado
                    try {
                        ok();
                        control.arquivo(arquivo);
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
                        control.arquivo(arquivo);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (evento.getSource() == btPesquisar) {
            try {
                pesquisar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btVer) {
            try {
                verTodosCadastrados();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void focusGained(FocusEvent evento) {//Ganha Focus
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNome) {
            tfNome.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSenha) {
            tfSenha.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfConfirmaSenha) {
            tfConfirmaSenha.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPermissao) {
            tfPermissao.setBackground(Color.YELLOW);
        }
    }

    public void focusLost(FocusEvent evento) {//Perde Focus
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.WHITE);
            codigo();
        }
        tfNome.setBackground(Color.WHITE);
        tfSenha.setBackground(Color.WHITE);
        tfConfirmaSenha.setBackground(Color.WHITE);
        tfPermissao.setBackground(Color.WHITE);
    }

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

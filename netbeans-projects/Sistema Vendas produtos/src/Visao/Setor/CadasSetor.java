package Visao.Setor;

import Arquivos.*;
import ClassPadrao.MeuDocument;
import Controle.SetorControl;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class CadasSetor extends JDialog implements ActionListener, FocusListener, ItemListener {

    private JTextField tfCodigo, tfNome;
    private JButton btVer, btPesquisar, btOk, btCancela;
    private JRadioButton rbNovo, rbExcluir, rbAlterar;
    private SetorControl control;
    private Arquivo arquivo;
    private LerArquivo lerArquivo;

    public CadasSetor() {
        setTitle("Cadastro de Setor");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(20, 30, 310, 140);
        tela.add(painel);
        painel.setBorder(BorderFactory.createTitledBorder("Setor"));
        control = new SetorControl();
        arquivo = new Arquivo();
        lerArquivo = new LerArquivo();
        control.lerArquivo(lerArquivo);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(40, 50, 80, 14);
        lbCodigo.setFont(fonte);
        painel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(85, 48, 110, 20);
        painel.add(tfCodigo);
        tfCodigo.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        tfCodigo.addFocusListener(this);

        btVer = new JButton("...");
        btVer.setBounds(199, 46, 31, 24);
        painel.add(btVer);
        btVer.addActionListener(this);

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(40, 95, 80, 14);
        lbNome.setFont(fonte);
        painel.add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(85, 93, 145, 20);
        painel.add(tfNome);
        tfNome.addFocusListener(this);

        btPesquisar = new JButton("Pesquisar");
        btPesquisar.setBounds(365, 15, 92, 26);
        tela.add(btPesquisar);
        btPesquisar.addActionListener(this);

        rbNovo = new JRadioButton("Novo");
        rbNovo.setBounds(368, 48, 80, 20);
        rbNovo.setFont(fonte);
        tela.add(rbNovo);
        rbNovo.addItemListener(this);

        rbAlterar = new JRadioButton("Alterar");
        rbAlterar.setBounds(368, 68, 80, 20);
        rbAlterar.setFont(fonte);
        tela.add(rbAlterar);
        rbAlterar.addItemListener(this);

        rbExcluir = new JRadioButton("Excluir");
        rbExcluir.setBounds(368, 88, 80, 20);
        rbExcluir.setFont(fonte);
        tela.add(rbExcluir);
        rbExcluir.addItemListener(this);

        btOk = new JButton("OK");
        btOk.setBounds(365, 122, 92, 26);
        tela.add(btOk);
        btOk.addActionListener(this);

        btCancela = new JButton("Cancelar");
        btCancela.setBounds(365, 162, 92, 26);
        tela.add(btCancela);
        btCancela.addActionListener(this);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(525, 225);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
    }

    private void gravar() throws Exception {
        String nome;
        int codigo;

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
                control.adicionar(nome, codigo);
                JOptionPane.showMessageDialog(null, "Numeros de setores cadastrados " + control.tamanho(), "Informação", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
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
            if (control.setorCadasNome(tfNome.getText()) == true) {
                encontrado = true;
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "O setor " + tfNome.getText() + " ja esta cadastrado deseja Substituilo? ", "Setor", JOptionPane.YES_NO_OPTION);
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
            throw new Exception("Não há setores cadastrados");
        } else {
            String texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Setor", JOptionPane.INFORMATION_MESSAGE);
            if (texto == null) {
            } else {
                boolean encontrado = false;
                if (control.recuperar(texto) == true) {
                    tfCodigo.setText(Integer.toString(control.codSetorCads()));
                    tfNome.setText(control.nomSetorCads());
                    encontrado = true;
                }
                if (encontrado == true) {
                    JOptionPane.showMessageDialog(null, "Setor encontrado e recuperado", "Setor", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Setor não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void excluir() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há setores cadastrados");
        } else {
            boolean erro = false;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Informe o codigo do setor a ser excluído:", "Saída de dados", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.setorCadasCod(codigo) == true) {
                            control.removerCod(codigo);
                            JOptionPane.showMessageDialog(null, "Setor removido com sucesso", "Setor", JOptionPane.INFORMATION_MESSAGE);
                            limparTela();
                        } else {
                            JOptionPane.showMessageDialog(null, "Setor não encontrado", "Setor", JOptionPane.INFORMATION_MESSAGE);
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
            throw new Exception("Não há setores cadastrados");
        } else {
            String texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Setor", JOptionPane.INFORMATION_MESSAGE);
            if (texto == null) {
            } else {
                boolean encontrado = false;
                if (control.setorCadasNome(texto) == true) {
                    codigo = control.codSetorCads();
                    encontrado = true;
                }
                if (encontrado == true) {
                    JOptionPane.showMessageDialog(null, "O setor " + texto + " esta inserido com o codigo " + codigo, "Setor", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    throw new Exception("Setor " + texto + " não encontrado");
                }
            }
        }
    }

    private void limparTela() {
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        tfNome.setText("");
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
                if (control.setorCadasCod(n) == true) {
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
            throw new Exception("Não há setores cadastrados");
        } else {
            VerTodosCadas ver = new VerTodosCadas(control);
            ver.setModal(true);
            ver.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancela) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Setor", JOptionPane.ERROR_MESSAGE);
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

    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNome) {
            tfNome.setBackground(Color.YELLOW);
        }
    }

    public void focusLost(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.WHITE);
            codigo();
        }
        tfNome.setBackground(Color.WHITE);
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

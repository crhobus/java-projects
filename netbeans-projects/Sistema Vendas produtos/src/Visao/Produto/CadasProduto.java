package Visao.Produto;

import Arquivos.*;
import ClassPadrao.*;
import Controle.ProdutoControl;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class CadasProduto extends JDialog implements ActionListener, FocusListener, ItemListener {

    private JTextField tfCodigo, tfNome, tfDescricao, tfQuantidade, tfValorUnitario, tfIPI, tfDescontos, tfValorTotal, tfNumeroSerie, tfModelo;
    private JButton btOk, btCancela, btVer, btPesquisar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private ProdutoControl control;
    private JTextFieldDouble jTextFieldDouble;
    private Arquivo arquivo;
    private LerArquivo lerArquivo;

    public CadasProduto() {
        setTitle("Cadastro de Produtos");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(10, 10, 425, 200);
        tela.add(painel);
        painel.setBorder(BorderFactory.createTitledBorder("Produtos"));
        control = new ProdutoControl();
        jTextFieldDouble = new JTextFieldDouble();
        arquivo = new Arquivo();
        lerArquivo = new LerArquivo();
        control.lerArquivo(lerArquivo);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 40, 80, 14);
        lbCodigo.setFont(fonte);
        painel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 58, 80, 20);
        painel.add(tfCodigo);
        tfCodigo.setDocument(new MeuDocument());
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        tfCodigo.addFocusListener(this);

        btVer = new JButton("...");
        btVer.setBounds(104, 56, 31, 24);
        painel.add(btVer);
        btVer.addActionListener(this);

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(140, 40, 80, 14);
        lbNome.setFont(fonte);
        painel.add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(140, 58, 243, 20);
        painel.add(tfNome);
        tfNome.addFocusListener(this);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(20, 78, 90, 14);
        lbDescricao.setFont(fonte);
        painel.add(lbDescricao);

        tfDescricao = new JTextField();
        tfDescricao.setBounds(20, 94, 110, 20);
        painel.add(tfDescricao);
        tfDescricao.addFocusListener(this);

        JLabel lbNumeroSerie = new JLabel("Numero Série");
        lbNumeroSerie.setBounds(140, 78, 80, 14);
        lbNumeroSerie.setFont(fonte);
        painel.add(lbNumeroSerie);

        tfNumeroSerie = new JTextField();
        tfNumeroSerie.setBounds(140, 94, 110, 20);
        painel.add(tfNumeroSerie);
        tfNumeroSerie.setDocument(new MeuDocument());
        tfNumeroSerie.addFocusListener(this);

        JLabel lbModelo = new JLabel("Modelo");
        lbModelo.setBounds(260, 78, 80, 14);
        lbModelo.setFont(fonte);
        painel.add(lbModelo);

        tfModelo = new JTextField();
        tfModelo.setBounds(260, 94, 123, 20);
        painel.add(tfModelo);
        tfModelo.addFocusListener(this);

        JLabel lb_Quantidade = new JLabel("Quantidade");
        lb_Quantidade.setBounds(20, 114, 80, 14);
        lb_Quantidade.setFont(fonte);
        painel.add(lb_Quantidade);

        tfQuantidade = new JTextField();
        tfQuantidade.setEditable(false);
        tfQuantidade.setBackground(cor);
        tfQuantidade.setText("1");
        tfQuantidade.setBounds(20, 130, 110, 20);
        painel.add(tfQuantidade);
        tfQuantidade.addFocusListener(this);

        JLabel lbValorUnitario = new JLabel("Valor Uniário");
        lbValorUnitario.setBounds(140, 114, 110, 14);
        lbValorUnitario.setFont(fonte);
        painel.add(lbValorUnitario);

        tfValorUnitario = new JTextField();
        tfValorUnitario.setBounds(140, 130, 110, 20);
        painel.add(tfValorUnitario);
        tfValorUnitario.addFocusListener(this);

        JLabel lbDescontos = new JLabel("Descontos");
        lbDescontos.setBounds(260, 114, 80, 14);
        lbDescontos.setFont(fonte);
        painel.add(lbDescontos);

        tfDescontos = new JTextField();
        tfDescontos.setBounds(260, 130, 120, 20);
        painel.add(tfDescontos);
        tfDescontos.addFocusListener(this);

        JLabel lbIPI = new JLabel("IPI");
        lbIPI.setBounds(20, 150, 80, 14);
        lbIPI.setFont(fonte);
        painel.add(lbIPI);

        tfIPI = new JTextField();
        tfIPI.setBounds(20, 166, 110, 20);
        painel.add(tfIPI);
        tfIPI.addFocusListener(this);

        JLabel lbValorTotal = new JLabel("Valor Total");
        lbValorTotal.setBounds(140, 150, 80, 14);
        lbValorTotal.setFont(fonte);
        painel.add(lbValorTotal);

        tfValorTotal = new JTextField();
        tfValorTotal.setBounds(140, 166, 110, 20);
        painel.add(tfValorTotal);
        tfValorTotal.addFocusListener(this);

        btPesquisar = new JButton("Pesquisar");
        btPesquisar.setBounds(455, 25, 100, 26);
        tela.add(btPesquisar);
        btPesquisar.addActionListener(this);

        rbNovo = new JRadioButton("Novo");
        rbNovo.setBounds(458, 70, 80, 20);
        rbNovo.setFont(fonte);
        tela.add(rbNovo);
        rbNovo.addItemListener(this);

        rbAlterar = new JRadioButton("Alterar");
        rbAlterar.setBounds(458, 90, 80, 20);
        rbAlterar.setFont(fonte);
        tela.add(rbAlterar);
        rbAlterar.addItemListener(this);

        rbExcluir = new JRadioButton("Excluir");
        rbExcluir.setBounds(458, 110, 80, 20);
        rbExcluir.setFont(fonte);
        tela.add(rbExcluir);
        rbExcluir.addItemListener(this);

        btOk = new JButton("OK");
        btOk.setBounds(455, 150, 100, 26);
        tela.add(btOk);
        btOk.addActionListener(this);

        btCancela = new JButton("Cancelar");
        btCancela.setBounds(455, 190, 100, 26);
        tela.add(btCancela);
        btCancela.addActionListener(this);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(600, 258);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
    }

    private void gravar() throws Exception {
        String nome, descricao, modelo;
        int codigo, quantidade, numeroSerie;
        double valorUnitario, ipi, descontos, valorTotal;

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
                if ("".equals(tfDescricao.getText())) {
                    tfDescricao.grabFocus();
                    throw new Exception("Campo descrição inválido");
                } else {
                    descricao = tfDescricao.getText();
                    if ("".equals(tfNumeroSerie.getText()) || Integer.parseInt(tfNumeroSerie.getText()) <= 0) {
                        tfNumeroSerie.grabFocus();
                        throw new Exception("Campo número série inválido");
                    } else {
                        numeroSerie = Integer.parseInt(tfNumeroSerie.getText());
                        if ("".equals(tfModelo.getText())) {
                            tfModelo.grabFocus();
                            throw new Exception("Campo modelo inválido");
                        } else {
                            modelo = tfModelo.getText();
                            if ("".equals(tfValorUnitario.getText()) || Double.parseDouble(tfValorUnitario.getText()) <= 0) {
                                tfValorUnitario.grabFocus();
                                throw new Exception("Campo valor unitário inválido");
                            } else {
                                valorUnitario = Double.parseDouble(tfValorUnitario.getText());
                                if ("".equals(tfDescontos.getText()) || Double.parseDouble(tfDescontos.getText()) <= 0) {
                                    tfDescontos.grabFocus();
                                    throw new Exception("Campo descontos inválido");
                                } else {
                                    descontos = Double.parseDouble(tfDescontos.getText());
                                    if ("".equals(tfIPI.getText()) || Double.parseDouble(tfIPI.getText()) <= 0) {
                                        tfIPI.grabFocus();
                                        throw new Exception("Campo ipi inválido");
                                    } else {
                                        ipi = Double.parseDouble(tfIPI.getText());
                                        if ("".equals(tfValorTotal.getText()) || Double.parseDouble(tfValorTotal.getText()) <= 0) {
                                            tfValorTotal.grabFocus();
                                            throw new Exception("Campo valor total inválido");
                                        } else {
                                            valorTotal = Double.parseDouble(tfValorTotal.getText());
                                            quantidade = Integer.parseInt(tfQuantidade.getText());
                                            control.adicionar(nome, descricao, numeroSerie, modelo, codigo, quantidade, valorUnitario, ipi, descontos, valorTotal);
                                            JOptionPane.showMessageDialog(null, "Numeros de produtos cadastrados " + control.tamanho(), "Informação", JOptionPane.INFORMATION_MESSAGE);
                                            limparTela();
                                        }
                                    }
                                }
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
            if (control.produtoCadasCod(Integer.parseInt(tfCodigo.getText())) == true) {
                encontrado = true;
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "O produto com o codigo " + tfCodigo.getText() + " ja esta cadastrado deseja Substituilo? ", "Produto", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    control.removerCod(Integer.parseInt(tfCodigo.getText()));
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
            throw new Exception("Não há produtos cadastrados");
        } else {
            boolean erro;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Insira codigo: ", "Produto", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.recuperar(codigo) == true) {
                            tfNome.setText(control.getNome());
                            tfDescricao.setText(control.getDescricao());
                            tfModelo.setText(control.getModelo());
                            tfCodigo.setText(Integer.toString(control.getCodigo()));
                            tfQuantidade.setText(Integer.toString(control.getQuantidade()));
                            tfNumeroSerie.setText(Integer.toString(control.getNumeroSerie()));
                            tfValorUnitario.setText(Double.toString(control.getValorUnitario()));
                            tfIPI.setText(Double.toString(control.getIpi()));
                            tfDescontos.setText(Double.toString(control.getDescontos()));
                            tfValorTotal.setText(Double.toString(control.getValorTotal()));
                            JOptionPane.showMessageDialog(null, "Produto encontrado e recuperado", "Produto", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Produto não encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
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

    private void excluir() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há produtos cadastrados");
        } else {
            boolean erro = false;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Informe o codigo do produto a ser excluído:", "Saída de dados", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.produtoCadasCod(codigo) == true) {
                            control.removerCod(codigo);
                            JOptionPane.showMessageDialog(null, "Produto removido com sucesso", "Produto", JOptionPane.INFORMATION_MESSAGE);
                            limparTela();
                        } else {
                            JOptionPane.showMessageDialog(null, "Produto não encontrado", "Produto", JOptionPane.INFORMATION_MESSAGE);
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
        String texto = "";
        if (control.vazio() == true) {
            throw new Exception("Não há produtos cadastrados");
        } else {
            texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Produto", JOptionPane.INFORMATION_MESSAGE);
            if (texto == null) {
            } else {
                boolean encontrado = false;
                if (control.produtoCadasNome(texto) == true) {
                    codigo = control.getCodigo();
                    encontrado = true;
                }
                if (encontrado == true) {
                    JOptionPane.showMessageDialog(null, "O produto " + texto + " esta inserido com o codigo " + codigo, "Produto", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    throw new Exception("Produto " + texto + " não encontrado");
                }
            }
        }
    }

    private void limparTela() {
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        tfNome.setText("");
        tfDescricao.setText("");
        tfValorUnitario.setText("");
        tfIPI.setText("");
        tfDescontos.setText("");
        tfValorTotal.setText("");
        tfNumeroSerie.setText("");
        tfModelo.setText("");
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
                if (control.produtoCadasCod(n) == true) {
                    encontrado = true;
                }
                if (encontrado == true) {
                    limparTela();
                }
            }
        }
    }

    private void calcula() throws Exception {
        try {
            if (!"".equals(tfQuantidade.getText()) & !"".equals(tfValorUnitario.getText()) & !"".equals(tfIPI.getText()) & !"".equals(tfDescontos.getText())) {
                int quantidade = Integer.parseInt(tfQuantidade.getText());//Conversão de String para Integer
                double valorUnitario = Double.parseDouble(tfValorUnitario.getText());//Conversão de String para Float
                double descontos = Double.parseDouble(tfDescontos.getText());//Conversão de String para Float
                double ipi = Double.parseDouble(tfIPI.getText());//Conversão de String para Float

                double valorTotal1 = ((quantidade * valorUnitario) + ipi);
                double valorTotal2 = (valorTotal1 / 100) * descontos;
                double valorTotal3 = valorTotal1 - valorTotal2;
                tfValorTotal.setText(Double.toString(valorTotal3));
            }
        } catch (NumberFormatException ex) {
            jTextFieldDouble.validaCampo(tfQuantidade);
            jTextFieldDouble.validaCampo(tfValorUnitario);
            jTextFieldDouble.validaCampo(tfIPI);
            jTextFieldDouble.validaCampo(tfDescontos);
        }
    }

    public void verTodosCadastrados() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há produtos cadastrados");
        } else {
            VerTodosCadasProd ver = new VerTodosCadasProd(control);
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
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Produto", JOptionPane.ERROR_MESSAGE);
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
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumeroSerie) {
            tfNumeroSerie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfModelo) {
            tfModelo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfQuantidade) {
            tfQuantidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorUnitario) {
            tfValorUnitario.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescontos) {
            tfDescontos.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfIPI) {
            tfIPI.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorTotal) {
            tfValorTotal.setBackground(Color.YELLOW);
            try {
                calcula();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void focusLost(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.WHITE);
            codigo();
        }
        tfNome.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        tfNumeroSerie.setBackground(Color.WHITE);
        tfModelo.setBackground(Color.WHITE);
        tfQuantidade.setBackground(Color.WHITE);
        tfValorUnitario.setBackground(Color.WHITE);
        tfDescontos.setBackground(Color.WHITE);
        tfIPI.setBackground(Color.WHITE);
        tfValorTotal.setBackground(Color.WHITE);
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

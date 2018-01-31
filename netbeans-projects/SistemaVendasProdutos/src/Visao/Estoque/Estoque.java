package Visao.Estoque;

import Arquivos.LerArquivo;
import Controle.ProdutoControl;
import Visao.Produto.TableModelProd;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Estoque extends JDialog implements ActionListener, FocusListener, ItemListener {

    private JTextField tfCodigo, tfNome, tfDescricao, tfModelo, tfQuantidade, tfValorUnitario, tfTotal, tfQuantidadeTotal, tfValorTotal;
    private JButton btPesquisaAvan, btOk, btCancelar;
    private JRadioButton rbNovos, rbAssistencia;
    private LerArquivo lerArquivo;
    private ProdutoControl controlProduto;
    private TableModelProd tableModel;
    private Renderizadora rendener;

    public Estoque() {
        setTitle("Estoque");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(10, 10, 430, 200);
        tela.add(painel1);
        painel1.setBorder(BorderFactory.createTitledBorder("Estoque"));
        JPanel painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBounds(10, 207, 585, 230);
        tela.add(painel2);
        painel2.setBorder(BorderFactory.createTitledBorder("Detalhes"));
        controlProduto = new ProdutoControl();
        lerArquivo = new LerArquivo();
        controlProduto.lerArquivo(lerArquivo);
        tableModel = new TableModelProd(controlProduto);
        rendener = new Renderizadora();

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 40, 80, 14);
        lbCodigo.setFont(fonte);
        painel1.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 58, 80, 20);
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(cor);
        painel1.add(tfCodigo);
        tfCodigo.addFocusListener(this);

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(112, 40, 80, 14);
        lbNome.setFont(fonte);
        painel1.add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(112, 58, 270, 20);
        tfNome.setEditable(false);
        tfNome.setBackground(cor);
        painel1.add(tfNome);
        tfNome.addFocusListener(this);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(20, 78, 90, 14);
        lbDescricao.setFont(fonte);
        painel1.add(lbDescricao);

        tfDescricao = new JTextField();
        tfDescricao.setBounds(20, 94, 110, 20);
        tfDescricao.setEditable(false);
        tfDescricao.setBackground(cor);
        painel1.add(tfDescricao);
        tfDescricao.addFocusListener(this);

        JLabel lbModelo = new JLabel("Modelo");
        lbModelo.setBounds(140, 78, 80, 14);
        lbModelo.setFont(fonte);
        painel1.add(lbModelo);

        tfModelo = new JTextField();
        tfModelo.setBounds(140, 94, 110, 20);
        tfModelo.setEditable(false);
        tfModelo.setBackground(cor);
        painel1.add(tfModelo);
        tfModelo.addFocusListener(this);

        JLabel lbQuantidade = new JLabel("Quantidade");
        lbQuantidade.setBounds(260, 78, 80, 14);
        lbQuantidade.setFont(fonte);
        painel1.add(lbQuantidade);

        tfQuantidade = new JTextField();
        tfQuantidade.setBounds(260, 94, 123, 20);
        tfQuantidade.setEditable(false);
        tfQuantidade.setBackground(cor);
        painel1.add(tfQuantidade);
        tfQuantidade.addFocusListener(this);

        JLabel lbValorUnitario = new JLabel("Valor Unitário");
        lbValorUnitario.setBounds(20, 114, 80, 14);
        lbValorUnitario.setFont(fonte);
        painel1.add(lbValorUnitario);

        tfValorUnitario = new JTextField();
        tfValorUnitario.setBounds(20, 130, 110, 20);
        tfValorUnitario.setEditable(false);
        tfValorUnitario.setBackground(cor);
        painel1.add(tfValorUnitario);
        tfValorUnitario.addFocusListener(this);

        JLabel lbTotal = new JLabel("Total");
        lbTotal.setBounds(140, 114, 110, 14);
        lbTotal.setFont(fonte);
        painel1.add(lbTotal);

        tfTotal = new JTextField();
        tfTotal.setBounds(140, 130, 110, 20);
        tfTotal.setEditable(false);
        tfTotal.setBackground(cor);
        painel1.add(tfTotal);
        tfTotal.addFocusListener(this);

        JLabel lbQuantidadeTotal = new JLabel("Quantidade Total");
        lbQuantidadeTotal.setBounds(260, 114, 120, 14);
        lbQuantidadeTotal.setFont(fonte);
        painel1.add(lbQuantidadeTotal);

        tfQuantidadeTotal = new JTextField();
        tfQuantidadeTotal.setBounds(260, 130, 120, 20);
        tfQuantidadeTotal.setEditable(false);
        tfQuantidadeTotal.setBackground(cor);
        painel1.add(tfQuantidadeTotal);
        tfQuantidadeTotal.addFocusListener(this);

        JLabel lbValorTotal = new JLabel("Valor Total");
        lbValorTotal.setBounds(20, 150, 80, 14);
        lbValorTotal.setFont(fonte);
        painel1.add(lbValorTotal);

        tfValorTotal = new JTextField();
        tfValorTotal.setBounds(20, 166, 110, 20);
        tfValorTotal.setEditable(false);
        tfValorTotal.setBackground(cor);
        painel1.add(tfValorTotal);
        tfValorTotal.addFocusListener(this);

        JTable tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        tabela.getColumnModel().getColumn(0).setMinWidth(50);
        tabela.getColumnModel().getColumn(1).setMinWidth(400);
        tabela.getColumnModel().getColumn(2).setMinWidth(170);
        tabela.getColumnModel().getColumn(3).setMinWidth(110);
        tabela.getColumnModel().getColumn(4).setMinWidth(150);
        tabela.getColumnModel().getColumn(5).setMinWidth(100);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(5, 25, 576, 194);
        painel2.add(scroll);

        btPesquisaAvan = new JButton("Busca Avançada");
        btPesquisaAvan.setBounds(455, 25, 129, 26);
        tela.add(btPesquisaAvan);
        btPesquisaAvan.addActionListener(this);

        rbNovos = new JRadioButton("Novo");
        rbNovos.setBounds(458, 65, 80, 20);
        rbNovos.setFont(fonte);
        tela.add(rbNovos);
        rbNovos.addItemListener(this);

        rbAssistencia = new JRadioButton("Assistência");
        rbAssistencia.setBounds(458, 87, 110, 20);
        rbAssistencia.setFont(fonte);
        tela.add(rbAssistencia);
        rbAssistencia.addItemListener(this);

        btOk = new JButton("OK");
        btOk.setBounds(455, 130, 100, 26);
        tela.add(btOk);
        btOk.addActionListener(this);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(455, 175, 100, 26);
        tela.add(btCancelar);
        btCancelar.addActionListener(this);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(610, 485);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
    }

    private void limparTela() {
        tfCodigo.setText("");
        tfNome.setText("");
        tfDescricao.setText("");
        tfModelo.setText("");
        tfQuantidade.setText("");
        tfValorUnitario.setText("");
        tfTotal.setText("");
        tfQuantidadeTotal.setText("");
        tfValorTotal.setText("");
        rbNovos.setSelected(false);
        rbAssistencia.setSelected(false);
    }

    private void pesquisaAvancada() throws Exception {
        int codigo = 0;
        String texto = "";
        if (controlProduto.vazio() == true) {
            throw new Exception("Não há produtos cadastrados");
        } else {
            texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Produto", JOptionPane.INFORMATION_MESSAGE);
            if (texto == null) {
            } else {
                boolean encontrado = false;
                if (controlProduto.produtoCadasNome(texto) == true) {
                    codigo = controlProduto.getCodigo();
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

    private void recuperarProdutos() throws Exception {
        if (controlProduto.vazio() == true) {
            throw new Exception("Não há produtos cadastrados");
        } else {
            boolean erro = false;
            int codigo = 0;
            do {
                String s = JOptionPane.showInputDialog(null, "Insira codigo: ", "Produto", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (controlProduto.recuperar(codigo) == true) {
                            tfCodigo.setText(Integer.toString(controlProduto.getCodigo()));
                            tfNome.setText(controlProduto.getNome());
                            tfDescricao.setText(controlProduto.getDescricao());
                            tfModelo.setText(controlProduto.getModelo());
                            tfValorUnitario.setText(Double.toString(controlProduto.getValorUnitario()));
                            tfQuantidadeTotal.setText(Integer.toString(controlProduto.quantidadeTotal()));
                            tfValorTotal.setText(Double.toString(controlProduto.valorTotal()));
                            tfQuantidade.setText(Integer.toString(controlProduto.quantidadeProd(controlProduto.getNome(), controlProduto.getDescricao())));
                            tfTotal.setText(Double.toString(controlProduto.valorProd(controlProduto.getNome(), controlProduto.getDescricao())));
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

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btPesquisaAvan) {
            try {
                pesquisaAvancada();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOk) {
            if (rbNovos.isSelected() == false & rbAssistencia.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Estoque", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rbNovos.isSelected() == true) {//Verifica se JRadioButton esta ou não selecionado
                    try {
                        recuperarProdutos();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbAssistencia.isSelected() == true) {
                    //Recuperar();
                }
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTela();
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
        if (evento.getSource() == tfModelo) {
            tfModelo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfQuantidade) {
            tfQuantidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorUnitario) {
            tfValorUnitario.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTotal) {
            tfTotal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfQuantidadeTotal) {
            tfQuantidadeTotal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorTotal) {
            tfValorTotal.setBackground(Color.YELLOW);
        }
    }

    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfNome.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        tfModelo.setBackground(Color.WHITE);
        tfQuantidade.setBackground(Color.WHITE);
        tfValorUnitario.setBackground(Color.WHITE);
        tfTotal.setBackground(Color.WHITE);
        tfQuantidadeTotal.setBackground(Color.WHITE);
        tfValorTotal.setBackground(Color.WHITE);
    }

    public void itemStateChanged(ItemEvent evento) {
        if (evento.getSource() == rbNovos) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbAssistencia.setSelected(false);
            }
        }
        if (evento.getSource() == rbAssistencia) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbNovos.setSelected(false);
            }
        }
    }
}

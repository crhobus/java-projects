package Visao.Pagamentos;

import Arquivos.*;
import ClassPadrao.MeuDocument;
import Controle.ClienteControl;
import Controle.ItemVendaAdiciona;
import Controle.PagamentosControl;
import Controle.VendasControl;
import Visao.Vendas.ConsultaVendas;
import Visao.Vendas.RenderizadoraItemVenda;
import Visao.Vendas.TableModelConsulta;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

public class NovoPagamento extends JDialog implements ActionListener, FocusListener, ItemListener {

    private JTextField tfCodigo, tfCodigoVenda, tfNomeCliente, tfCondPagto, tfDescontos, tfTotal, tfParcelas, tfPagamentos, tfValorPag, tfParcelasRes, tfSituacao;
    private JFormattedTextField ftfDataEmissao;
    private MaskFormatter mascaraDataEmissao;
    private JButton btVer, btPesquisaVenda, btPagar, btCancelar, btOk, btPesquisa;
    private JRadioButton rbNovo, rbAlterar, rbGerar;
    private PagamentosControl control;
    private VendasControl controlVendas;
    private ClienteControl controlCliente;
    private Arquivo arquivo;
    private LerArquivo lerArquivo;
    private TableModelConsulta tableModel;
    private ItemVendaAdiciona controlItemVenda;
    private RenderizadoraItemVenda rendener;

    public NovoPagamento() {
        setTitle("Pagamentos");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(10, 10, 485, 385);
        tela.add(painel);
        painel.setBorder(BorderFactory.createTitledBorder("Pagamentos"));
        control = new PagamentosControl();
        controlVendas = new VendasControl();
        controlCliente = new ClienteControl();
        controlItemVenda = new ItemVendaAdiciona();
        arquivo = new Arquivo();
        lerArquivo = new LerArquivo();
        controlVendas.lerArquivo(lerArquivo);
        controlCliente.lerArquivo(lerArquivo);
        controlItemVenda.lerArquivo(lerArquivo);
        control.lerArquivo(lerArquivo);
        tableModel = new TableModelConsulta(controlItemVenda);
        rendener = new RenderizadoraItemVenda();

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 40, 80, 14);
        lbCodigo.setFont(fonte);
        painel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 58, 80, 20);
        tfCodigo.setDocument(new MeuDocument());
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        painel.add(tfCodigo);
        tfCodigo.addFocusListener(this);

        btVer = new JButton("...");
        btVer.setBounds(104, 56, 31, 24);
        painel.add(btVer);
        btVer.addActionListener(this);

        JLabel lbCodVenda = new JLabel("Codigo Venda");
        lbCodVenda.setBounds(140, 40, 80, 14);
        lbCodVenda.setFont(fonte);
        painel.add(lbCodVenda);

        tfCodigoVenda = new JTextField();
        tfCodigoVenda.setBounds(140, 58, 80, 20);
        painel.add(tfCodigoVenda);
        tfCodigoVenda.setDocument(new MeuDocument());
        tfCodigoVenda.addFocusListener(this);

        btPesquisaVenda = new JButton("...");
        btPesquisaVenda.setBounds(225, 56, 31, 24);
        painel.add(btPesquisaVenda);
        btPesquisaVenda.addActionListener(this);

        JLabel lbNomeCliente = new JLabel("Cliente");
        lbNomeCliente.setBounds(262, 40, 80, 14);
        lbNomeCliente.setFont(fonte);
        painel.add(lbNomeCliente);

        tfNomeCliente = new JTextField();
        tfNomeCliente.setBounds(262, 58, 194, 20);
        tfNomeCliente.setEditable(false);
        tfNomeCliente.setBackground(cor);
        painel.add(tfNomeCliente);
        tfNomeCliente.addFocusListener(this);

        JLabel lbDataEmissao = new JLabel("Emissão");
        lbDataEmissao.setBounds(20, 78, 90, 14);
        lbDataEmissao.setFont(fonte);
        painel.add(lbDataEmissao);

        try {
            mascaraDataEmissao = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
        }
        ftfDataEmissao = new JFormattedTextField(mascaraDataEmissao);
        ftfDataEmissao.setBounds(20, 94, 110, 20);
        painel.add(ftfDataEmissao);
        ftfDataEmissao.setEditable(false);
        ftfDataEmissao.setBackground(cor);
        ftfDataEmissao.addFocusListener(this);

        JLabel lbCondPagto = new JLabel("Cond. Pagto.");
        lbCondPagto.setBounds(140, 78, 80, 14);
        lbCondPagto.setFont(fonte);
        painel.add(lbCondPagto);

        tfCondPagto = new JTextField();
        tfCondPagto.setBounds(140, 94, 110, 20);
        painel.add(tfCondPagto);
        tfCondPagto.setEditable(false);
        tfCondPagto.setBackground(cor);
        tfCondPagto.addFocusListener(this);

        JLabel lbDescontos = new JLabel("Descontos");
        lbDescontos.setBounds(262, 78, 80, 14);
        lbDescontos.setFont(fonte);
        painel.add(lbDescontos);

        tfDescontos = new JTextField();
        tfDescontos.setBounds(262, 94, 120, 20);
        painel.add(tfDescontos);
        tfDescontos.setEditable(false);
        tfDescontos.setBackground(cor);
        tfDescontos.addFocusListener(this);

        JLabel lbTotal = new JLabel("Total");
        lbTotal.setBounds(20, 114, 80, 14);
        lbTotal.setFont(fonte);
        painel.add(lbTotal);

        tfTotal = new JTextField();
        tfTotal.setEditable(false);
        tfTotal.setBackground(cor);
        tfTotal.setBounds(20, 130, 110, 20);
        painel.add(tfTotal);
        tfTotal.addFocusListener(this);

        JLabel lb_Parcelas = new JLabel("Valor Parcelas");
        lb_Parcelas.setBounds(140, 114, 110, 14);
        lb_Parcelas.setFont(fonte);
        painel.add(lb_Parcelas);

        tfParcelas = new JTextField();
        tfParcelas.setBounds(140, 130, 110, 20);
        painel.add(tfParcelas);
        tfParcelas.setEditable(false);
        tfParcelas.setBackground(cor);
        tfParcelas.addFocusListener(this);

        JLabel lbPagamentos = new JLabel("Pagamentos");
        lbPagamentos.setBounds(260, 114, 80, 14);
        lbPagamentos.setFont(fonte);
        painel.add(lbPagamentos);

        tfPagamentos = new JTextField();
        tfPagamentos.setBounds(260, 130, 120, 20);
        painel.add(tfPagamentos);
        tfPagamentos.addFocusListener(this);

        btPagar = new JButton("Pagar");
        btPagar.setBounds(387, 128, 68, 24);
        painel.add(btPagar);
        btPagar.addActionListener(this);

        JLabel lbvalorPag = new JLabel("Valor a ser Pago");
        lbvalorPag.setBounds(20, 150, 100, 14);
        lbvalorPag.setFont(fonte);
        painel.add(lbvalorPag);

        tfValorPag = new JTextField();
        tfValorPag.setBounds(20, 166, 110, 20);
        painel.add(tfValorPag);
        tfValorPag.setEditable(false);
        tfValorPag.setBackground(cor);
        tfValorPag.addFocusListener(this);

        JLabel lbParcelasRes = new JLabel("Parcelas Restantes");
        lbParcelasRes.setBounds(140, 150, 115, 14);
        lbParcelasRes.setFont(fonte);
        painel.add(lbParcelasRes);

        tfParcelasRes = new JTextField();
        tfParcelasRes.setBounds(140, 166, 110, 20);
        painel.add(tfParcelasRes);
        tfParcelasRes.setEditable(false);
        tfParcelasRes.setBackground(cor);
        tfParcelasRes.addFocusListener(this);

        JLabel lbSituacao = new JLabel("Situação");
        lbSituacao.setBounds(260, 150, 115, 14);
        lbSituacao.setFont(fonte);
        painel.add(lbSituacao);

        tfSituacao = new JTextField();
        tfSituacao.setBounds(260, 166, 120, 20);
        painel.add(tfSituacao);
        tfSituacao.setEditable(false);
        tfSituacao.setBackground(cor);
        tfSituacao.addFocusListener(this);

        JTable tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(5, 200, 474, 178);
        painel.add(scroll);

        btPesquisa = new JButton("Pesquisar");
        btPesquisa.setBounds(520, 35, 100, 26);
        tela.add(btPesquisa);
        btPesquisa.addActionListener(this);

        rbNovo = new JRadioButton("Novo");
        rbNovo.setBounds(520, 80, 80, 20);
        rbNovo.setFont(fonte);
        tela.add(rbNovo);
        rbNovo.addItemListener(this);

        rbAlterar = new JRadioButton("Alterar");
        rbAlterar.setBounds(520, 100, 80, 20);
        rbAlterar.setFont(fonte);
        tela.add(rbAlterar);
        rbAlterar.addItemListener(this);

        rbGerar = new JRadioButton("Gerar");
        rbGerar.setBounds(520, 120, 80, 20);
        rbGerar.setFont(fonte);
        tela.add(rbGerar);
        rbGerar.addItemListener(this);

        btOk = new JButton("OK");
        btOk.setBounds(520, 160, 100, 26);
        tela.add(btOk);
        btOk.addActionListener(this);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(520, 200, 100, 26);
        tela.add(btCancelar);
        btCancelar.addActionListener(this);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(700, 430);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
    }

    private void gravar() throws Exception {
        String nomeClie, dataEmissao, condPagto, ParcelasRes, Situacao;
        int codigo, codigoVen;
        double descontos, total, valorParcelas, valorPago;

        if ("".equals(tfCodigo.getText()) || Integer.parseInt(tfCodigo.getText()) <= 0) {
            tfCodigo.grabFocus();
            throw new Exception("Campo codigo inválido");
        } else {
            codigo = Integer.parseInt(tfCodigo.getText());
            if ("".equals(tfCodigoVenda.getText()) || Integer.parseInt(tfCodigoVenda.getText()) <= 0) {
                tfCodigoVenda.grabFocus();
                throw new Exception("Campo codigo venda inválido");
            } else {
                codigoVen = Integer.parseInt(tfCodigoVenda.getText());
                if ("".equals(tfNomeCliente.getText())) {
                    tfNomeCliente.grabFocus();
                    throw new Exception("Campo nome cliente inválido");
                } else {
                    nomeClie = tfNomeCliente.getText();
                    if ("".equals(ftfDataEmissao.getText())) {
                        ftfDataEmissao.grabFocus();
                        throw new Exception("Campo data emissão inválido");
                    } else {
                        dataEmissao = ftfDataEmissao.getText();
                        if ("".equals(tfCondPagto.getText())) {
                            tfCondPagto.grabFocus();
                            throw new Exception("Campo condição pagamento. inválido");
                        } else {
                            condPagto = tfCondPagto.getText();
                            if ("".equals(tfDescontos.getText()) || Double.parseDouble(tfDescontos.getText()) < 0) {
                                tfDescontos.grabFocus();
                                throw new Exception("Campo descontos inválido");
                            } else {
                                descontos = Double.parseDouble(tfDescontos.getText());
                                if ("".equals(tfTotal.getText()) || Double.parseDouble(tfTotal.getText()) < 0) {
                                    tfTotal.grabFocus();
                                    throw new Exception("Campo total inválido");
                                } else {
                                    total = Double.parseDouble(tfTotal.getText());
                                    if ("".equals(tfParcelas.getText()) || Double.parseDouble(tfParcelas.getText()) < 0) {
                                        tfParcelas.grabFocus();
                                        throw new Exception("Campo valor parcelas inválido");
                                    } else {
                                        valorParcelas = Double.parseDouble(tfParcelas.getText());
                                        if ("".equals(tfValorPag.getText()) || Double.parseDouble(tfValorPag.getText()) < 0) {
                                            tfValorPag.grabFocus();
                                            throw new Exception("Campo valor a ser pago inválido");
                                        } else {
                                            valorPago = Double.parseDouble(tfValorPag.getText());
                                            if ("".equals(tfParcelasRes.getText())) {
                                                tfParcelasRes.grabFocus();
                                                throw new Exception("Campo parcelas restantes inválido");
                                            } else {
                                                ParcelasRes = tfParcelasRes.getText();
                                                if ("".equals(tfSituacao.getText())) {
                                                    tfSituacao.grabFocus();
                                                    throw new Exception("Campo situação inválido");
                                                } else {
                                                    Situacao = tfSituacao.getText();
                                                    control.adicionar(nomeClie, dataEmissao, condPagto, ParcelasRes, Situacao, codigo, codigoVen, descontos, total, valorParcelas, valorPago);
                                                    JOptionPane.showMessageDialog(null, "Numeros de pagamentos cadastrados " + control.tamanho(), "Informação", JOptionPane.INFORMATION_MESSAGE);
                                                    limparTelaPagamento();
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
        }
    }

    private void ok() {
        if (control.vazio() == true) {
            try {
                gravar();
                tfCodigoVenda.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            boolean encontradoCodigo = false;
            boolean encontradoCodVenda = false;
            boolean n = false;
            if (control.verificaCodigo(Integer.parseInt(tfCodigo.getText())) == true) {
                encontradoCodigo = true;
            }
            if (!"".equals(tfCodigoVenda.getText())) {
                if (control.verificaCodigoVenda(Integer.parseInt(tfCodigoVenda.getText())) == true) {
                    encontradoCodVenda = true;
                }
            }
            if (encontradoCodVenda == true) {
                if (encontradoCodigo == true) {
                    int respostaCodigo = JOptionPane.showConfirmDialog(null, "O pagamento " + tfCodigo.getText() + " ja foi realizado deseja realiza-lo novamente?", "Pagamentos", JOptionPane.OK_OPTION);
                    if (respostaCodigo == 0) {
                        control.removerCod(Integer.parseInt(tfCodigo.getText()));
                        try {
                            gravar();
                            tfCodigoVenda.setEnabled(true);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        n = true;
                    } else {
                        return;
                    }
                }
                if (n == false) {
                    JOptionPane.showMessageDialog(null, "O pagamento da venda " + tfCodigoVenda.getText() + " ja foi realizado e não podera ser substituido", "Pagamentos", JOptionPane.OK_OPTION);
                }
            } else {
                try {
                    gravar();
                    tfCodigoVenda.setEnabled(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void recuperarPagamentos() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há pagamentos cadastrados");
        } else {
            boolean erro;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Insira codigo: ", "Pagamento", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.recuperar(codigo) == true) {
                            tfNomeCliente.setText(control.getNomeClie());
                            ftfDataEmissao.setText(control.getDataEmissao());
                            tfCondPagto.setText(control.getCondPagto());
                            tfParcelasRes.setText(control.getParcelasRes());
                            tfSituacao.setText(control.getSituacao());
                            tfCodigo.setText(Integer.toString(control.getCodigo()));
                            tfCodigoVenda.setText(Integer.toString(control.getCodigoVen()));
                            tfDescontos.setText(Double.toString(control.getDescontos()));
                            tfTotal.setText(Double.toString(control.getTotal()));
                            tfParcelas.setText(Double.toString(control.getValorParcelas()));
                            tfValorPag.setText(Double.toString(control.getValorPago()));
                            recuperarItemVendas();
                            tfCodigoVenda.setEnabled(false);
                            JOptionPane.showMessageDialog(null, "Pagamento encontrado e recuperado", "Pagamento", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Pagamento não encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
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

    private void recuperarCliente() throws Exception {
        int codigoClie = 0;
        if (controlCliente.vazio() == true) {
            throw new Exception("Não há clientes cadastrados");
        } else {
            if (controlVendas.recuperar(Integer.parseInt(tfCodigoVenda.getText()))) {
                codigoClie = controlVendas.getCodigoCliente();
            }
            if (controlCliente.recuperar(codigoClie)) {
                tfNomeCliente.setText(controlCliente.getNome());
            }
        }
    }

    private void recuperarVendas() throws Exception {
        if (!"".equals(tfCodigoVenda.getText())) {
            if (controlVendas.vazio() == true) {
                throw new Exception("Não há vendas cadastradas");
            } else {
                if (controlVendas.recuperar(Integer.parseInt(tfCodigoVenda.getText())) == true) {
                    ftfDataEmissao.setText(controlVendas.getDataEmissao());
                    tfCondPagto.setText(controlVendas.getCondPagto());
                    tfDescontos.setText(Double.toString(controlVendas.getDescontos()));
                    tfTotal.setText(Double.toString(controlVendas.getTotal()));
                    recuperarCliente();
                    valorParcelas();
                    recuperarItemVendas();
                }
            }
        }
    }

    private void recuperarItemVendas() {
        tableModel.limparTabela();
        tableModel.adicionaTabela(Integer.parseInt(tfCodigoVenda.getText()));
    }

    private void valorParcelas() {
        if ("1X".equals(tfCondPagto.getText())) {
            tfParcelas.setText(Double.toString(Double.parseDouble(tfTotal.getText()) / 1));
        }
        if ("2X".equals(tfCondPagto.getText())) {
            tfParcelas.setText(Double.toString(Double.parseDouble(tfTotal.getText()) / 2));
        }
        if ("3X".equals(tfCondPagto.getText())) {
            tfParcelas.setText(Double.toString(Double.parseDouble(tfTotal.getText()) / 3));
        }
        if ("4X".equals(tfCondPagto.getText())) {
            tfParcelas.setText(Double.toString(Double.parseDouble(tfTotal.getText()) / 4));
        }
        if ("5X".equals(tfCondPagto.getText())) {
            tfParcelas.setText(Double.toString(Double.parseDouble(tfTotal.getText()) / 5));
        }
        if ("6X".equals(tfCondPagto.getText())) {
            tfParcelas.setText(Double.toString(Double.parseDouble(tfTotal.getText()) / 6));
        }
        if ("7X".equals(tfCondPagto.getText())) {
            tfParcelas.setText(Double.toString(Double.parseDouble(tfTotal.getText()) / 7));
        }
        if ("8X".equals(tfCondPagto.getText())) {
            tfParcelas.setText(Double.toString(Double.parseDouble(tfTotal.getText()) / 8));
        }
    }

    private void parcelasRestantes() {
        if ("".equals(tfParcelasRes.getText())) {
            if ("1X".equals(tfCondPagto.getText())) {
                tfParcelasRes.setText("0");
            }
            if ("2X".equals(tfCondPagto.getText())) {
                tfParcelasRes.setText("1");
            }
            if ("3X".equals(tfCondPagto.getText())) {
                tfParcelasRes.setText("2");
            }
            if ("4X".equals(tfCondPagto.getText())) {
                tfParcelasRes.setText("3");
            }
            if ("5X".equals(tfCondPagto.getText())) {
                tfParcelasRes.setText("4");
            }
            if ("6X".equals(tfCondPagto.getText())) {
                tfParcelasRes.setText("5");
            }
            if ("7X".equals(tfCondPagto.getText())) {
                tfParcelasRes.setText("6");
            }
            if ("8X".equals(tfCondPagto.getText())) {
                tfParcelasRes.setText("7");
            }
        } else {
            if ("1".equals(tfParcelasRes.getText())) {
                tfParcelasRes.setText("0");
            }
            if ("2".equals(tfParcelasRes.getText())) {
                tfParcelasRes.setText("1");
            }
            if ("3".equals(tfParcelasRes.getText())) {
                tfParcelasRes.setText("2");
            }
            if ("4".equals(tfParcelasRes.getText())) {
                tfParcelasRes.setText("3");
            }
            if ("5".equals(tfParcelasRes.getText())) {
                tfParcelasRes.setText("4");
            }
            if ("6".equals(tfParcelasRes.getText())) {
                tfParcelasRes.setText("5");
            }
            if ("7".equals(tfParcelasRes.getText())) {
                tfParcelasRes.setText("6");
            }
            if ("8".equals(tfParcelasRes.getText())) {
                tfParcelasRes.setText("7");
            }
        }
    }

    private void pagar() throws Exception {
        if ("".equals(tfPagamentos.getText())) {
            throw new Exception("Campo pagamento inválido");
        } else {
            if (tfParcelas.getText().equals(tfPagamentos.getText())) {
                if ("".equals(tfValorPag.getText())) {
                    double pagamento = Double.parseDouble(tfPagamentos.getText());//Conversão de String para float
                    double total = Double.parseDouble(tfTotal.getText());//Conversão de String para float
                    double resultado = total - pagamento;
                    tfValorPag.setText(Double.toString(resultado));

                    double valorPag = Double.parseDouble(tfValorPag.getText());
                    if (valorPag < 1) {
                        tfSituacao.setText("Pago");
                        tfValorPag.setText("0");
                    } else {
                        tfSituacao.setText("Aberto");
                    }
                    parcelasRestantes();
                } else {
                    double pagamento = Double.parseDouble(tfPagamentos.getText());//Conversão de String para float
                    double valorPago = Float.parseFloat(tfValorPag.getText());//Conversão de String para float
                    double resultado = valorPago - pagamento;
                    tfValorPag.setText(Double.toString(resultado));
                    double valPag = Double.parseDouble(tfValorPag.getText());
                    if (valPag < 1) {
                        tfSituacao.setText("Pago");
                        tfValorPag.setText("0");
                    } else {
                        tfSituacao.setText("Aberto");
                    }
                    parcelasRestantes();
                }
            }
        }
    }

    private void limparCamposOperacao() {
        tfPagamentos.setText("");
        tfValorPag.setText("");
        tfParcelasRes.setText("");
        tfSituacao.setText("");
    }

    private void limparTelaVendas() {
        tfNomeCliente.setText("");
        ftfDataEmissao.setText("");
        tfCondPagto.setText("");
        tfDescontos.setText("");
        tfTotal.setText("");
        tableModel.limparTabela();
        tableModel.fireTableDataChanged();
    }

    private void limparTelaPagamento() {
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        tfCodigoVenda.setText("");
        tfParcelas.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbGerar.setSelected(false);
        tfCodigoVenda.setEnabled(true);
        limparTelaVendas();
        limparCamposOperacao();
    }

    private void codigo() {
        if ("".equals(tfCodigo.getText())) {
            limparTelaPagamento();
        } else {
            if (control.vazio() == false) {
                if (control.verificaCodigo(Integer.parseInt(tfCodigo.getText())) == true) {
                    limparTelaPagamento();
                }
            }
        }
    }

    private void pesquisar() throws Exception {
        int codigo = 0;
        String texto = "";
        if (controlVendas.vazio() == true) {
            throw new Exception("Não há vendas cadastradas");
        } else {
            if (controlCliente.vazio() == true) {
                throw new Exception("Não há clientes cadastrados");
            } else {
                texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                if (texto == null) {
                } else {
                    boolean encontrado = false;
                    if (controlCliente.clienteCadasNome(texto) == true) {
                        codigo = controlCliente.getCodigo();
                        encontrado = true;
                    }
                    if (encontrado == true) {
                        if (controlVendas.vendasCadasCodPag(codigo) == true) {
                            int cont = controlVendas.verificaSituacaoClie(codigo);
                            if (cont == 0) {
                                JOptionPane.showMessageDialog(null, "O cliente " + texto + " não tem compras em aberto", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "O cliente " + texto + " ja realizou " + cont + " compras", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                                if (cont > 0) {
                                    cont = cont - control.verificaSituacaoPagamento(texto);
                                    JOptionPane.showMessageDialog(null, "O cliente " + texto + " tem " + cont + " compras em aberto", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "O cliente " + texto + " não realizou nenhuma compra", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "O cliente " + texto + " não realizou nenhuma compra", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }

    private void verTodosCadastrados() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há pagamentos cadastrados");
        } else {
            VerTodosPag ver = new VerTodosPag(control);
            ver.setModal(true);
            ver.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btPesquisa) {
            try {
                pesquisar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbGerar.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Pagamentos", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rbNovo.isSelected() == true) {
                    ok();
                    control.arquivo(arquivo);
                }
                if (rbAlterar.isSelected() == true) {
                    try {
                        recuperarPagamentos();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (rbGerar.isSelected() == true) {
                    //***//
                }
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTelaPagamento();
        }
        if (evento.getSource() == btPagar) {
            try {
                pagar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btPesquisaVenda) {
            ConsultaVendas consulta = new ConsultaVendas();
            consulta.setModal(true);
            consulta.setVisible(true);
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
        if (evento.getSource() == tfCodigoVenda) {
            tfCodigoVenda.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeCliente) {
            tfNomeCliente.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataEmissao) {
            ftfDataEmissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCondPagto) {
            tfCondPagto.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescontos) {
            tfDescontos.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTotal) {
            tfTotal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfParcelas) {
            tfParcelas.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPagamentos) {
            tfPagamentos.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorPag) {
            tfValorPag.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfParcelasRes) {
            tfParcelasRes.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSituacao) {
            tfSituacao.setBackground(Color.YELLOW);
        }
    }

    public void focusLost(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.WHITE);
            codigo();
        }
        if (evento.getSource() == tfCodigoVenda) {
            tfCodigoVenda.setBackground(Color.WHITE);
            try {
                recuperarVendas();
                limparCamposOperacao();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfNomeCliente.setBackground(Color.WHITE);
        ftfDataEmissao.setBackground(Color.WHITE);
        tfCondPagto.setBackground(Color.WHITE);
        tfDescontos.setBackground(Color.WHITE);
        tfTotal.setBackground(Color.WHITE);
        tfParcelas.setBackground(Color.WHITE);
        tfPagamentos.setBackground(Color.WHITE);
        tfValorPag.setBackground(Color.WHITE);
        tfParcelasRes.setBackground(Color.WHITE);
        tfSituacao.setBackground(Color.WHITE);
    }

    public void itemStateChanged(ItemEvent evento) {
        if (evento.getSource() == rbNovo) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbAlterar.setSelected(false);
                rbGerar.setSelected(false);
            }
        }
        if (evento.getSource() == rbAlterar) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbNovo.setSelected(false);
                rbGerar.setSelected(false);
            }
        }
        if (evento.getSource() == rbGerar) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                rbNovo.setSelected(false);
                rbAlterar.setSelected(false);
            }
        }
    }
}

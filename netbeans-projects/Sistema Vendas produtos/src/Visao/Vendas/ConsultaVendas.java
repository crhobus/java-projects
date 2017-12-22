package Visao.Vendas;

import Arquivos.*;
import ClassPadrao.MeuDocument;
import Controle.ClienteControl;
import Controle.ItemVendaAdiciona;
import Controle.PagamentosControl;
import Controle.VendasControl;
import Controle.VendedorControl;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class ConsultaVendas extends JDialog implements ActionListener, FocusListener {

    private JTextField tfCodigo, tfDataEmissao, tfCodVendedor, tfNomeVendedor, tfCodCliente, tfNomeCliente, tfSituacao, tfFormaPagto, tfCondPagto, tfParcelasRes, tfValorRes, tfDescricao, tfEndereco, tfNumero, tfBairro, tfCidade, tfEstado, tfContato, tfSubTotal, tfDescontos, tfTotal;
    private JButton btGerar, btCancelar;
    private JTable tabela;
    private TableModelConsulta tableModel;
    private LerArquivo lerArquivo;
    private ClienteControl controlCliente;
    private VendedorControl controlVendedor;
    private ItemVendaAdiciona controlItemVenda;
    private VendasControl controlVenda;
    private PagamentosControl controlPagamento;
    private RenderizadoraItemVenda rendener;
    private NumberFormat nRealf = NumberFormat.getCurrencyInstance();

    public ConsultaVendas() {
        setTitle("Consulta Vendas");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(10, 10, 585, 650);
        tela.add(painel1);
        painel1.setBorder(BorderFactory.createTitledBorder("Consulta Vendas"));
        lerArquivo = new LerArquivo();
        controlItemVenda = new ItemVendaAdiciona();
        controlCliente = new ClienteControl();
        controlVendedor = new VendedorControl();
        controlVenda = new VendasControl();
        controlPagamento = new PagamentosControl();
        controlCliente.lerArquivo(lerArquivo);
        controlVendedor.lerArquivo(lerArquivo);
        controlVenda.lerArquivo(lerArquivo);
        controlItemVenda.lerArquivo(lerArquivo);
        controlPagamento.lerArquivo(lerArquivo);
        tableModel = new TableModelConsulta(controlItemVenda);
        rendener = new RenderizadoraItemVenda();

        JPanel painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBounds(10, 250, 564, 120);
        painel1.add(painel2);
        painel2.setBorder(BorderFactory.createTitledBorder("Dados Entrega"));

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 52, 80, 14);
        lbCodigo.setFont(fonte);
        painel1.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(68, 48, 110, 20);
        tfCodigo.setDocument(new MeuDocument());
        painel1.add(tfCodigo);
        tfCodigo.addFocusListener(this);

        btGerar = new JButton("Ok");
        btGerar.setBounds(194, 46, 85, 23);
        painel1.add(btGerar);
        btGerar.addActionListener(this);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(304, 46, 85, 23);
        painel1.add(btCancelar);
        btCancelar.addActionListener(this);

        JLabel lbDataEmissao = new JLabel("Data Emissão");
        lbDataEmissao.setBounds(20, 95, 110, 14);
        lbDataEmissao.setFont(fonte);
        painel1.add(lbDataEmissao);

        tfDataEmissao = new JTextField();
        tfDataEmissao.setBounds(20, 112, 120, 20);
        tfDataEmissao.setEditable(false);
        tfDataEmissao.setBackground(cor);
        painel1.add(tfDataEmissao);

        JLabel lbCodVendedor = new JLabel("Vendedor");
        lbCodVendedor.setBounds(160, 95, 110, 14);
        lbCodVendedor.setFont(fonte);
        painel1.add(lbCodVendedor);

        tfCodVendedor = new JTextField();
        tfCodVendedor.setBounds(160, 112, 120, 20);
        tfCodVendedor.setEditable(false);
        tfCodVendedor.setBackground(cor);
        painel1.add(tfCodVendedor);

        JLabel lbNomeVendedor = new JLabel("Nome");
        lbNomeVendedor.setBounds(300, 95, 110, 14);
        lbNomeVendedor.setFont(fonte);
        painel1.add(lbNomeVendedor);

        tfNomeVendedor = new JTextField();
        tfNomeVendedor.setBounds(300, 112, 120, 20);
        tfNomeVendedor.setEditable(false);
        tfNomeVendedor.setBackground(cor);
        painel1.add(tfNomeVendedor);

        JLabel lbCodCliente = new JLabel("Cliente");
        lbCodCliente.setBounds(20, 133, 110, 14);
        lbCodCliente.setFont(fonte);
        painel1.add(lbCodCliente);

        tfCodCliente = new JTextField();
        tfCodCliente.setBounds(20, 150, 120, 20);
        tfCodCliente.setEditable(false);
        tfCodCliente.setBackground(cor);
        painel1.add(tfCodCliente);

        JLabel lbNomeCliente = new JLabel("Nome");
        lbNomeCliente.setBounds(160, 133, 110, 14);
        lbNomeCliente.setFont(fonte);
        painel1.add(lbNomeCliente);

        tfNomeCliente = new JTextField();
        tfNomeCliente.setBounds(160, 150, 120, 20);
        tfNomeCliente.setEditable(false);
        tfNomeCliente.setBackground(cor);
        painel1.add(tfNomeCliente);

        JLabel lbSituacao = new JLabel("Situação");
        lbSituacao.setBounds(300, 133, 110, 14);
        lbSituacao.setFont(fonte);
        painel1.add(lbSituacao);

        tfSituacao = new JTextField();
        tfSituacao.setBounds(300, 150, 120, 20);
        tfSituacao.setEditable(false);
        tfSituacao.setBackground(cor);
        painel1.add(tfSituacao);

        JLabel lbFormaPagto = new JLabel("Forma Pagto");
        lbFormaPagto.setBounds(20, 170, 110, 14);
        lbFormaPagto.setFont(fonte);
        painel1.add(lbFormaPagto);

        tfFormaPagto = new JTextField();
        tfFormaPagto.setBounds(20, 186, 120, 20);
        tfFormaPagto.setEditable(false);
        tfFormaPagto.setBackground(cor);
        painel1.add(tfFormaPagto);

        JLabel lbCondPagto = new JLabel("Cond Pagto");
        lbCondPagto.setBounds(160, 170, 110, 14);
        lbCondPagto.setFont(fonte);
        painel1.add(lbCondPagto);

        tfCondPagto = new JTextField();
        tfCondPagto.setBounds(160, 186, 120, 20);
        tfCondPagto.setEditable(false);
        tfCondPagto.setBackground(cor);
        painel1.add(tfCondPagto);

        JLabel lbParcelasRes = new JLabel("Parcelas Restantes");
        lbParcelasRes.setBounds(300, 170, 125, 14);
        lbParcelasRes.setFont(fonte);
        painel1.add(lbParcelasRes);

        tfParcelasRes = new JTextField();
        tfParcelasRes.setBounds(300, 186, 120, 20);
        tfParcelasRes.setEditable(false);
        tfParcelasRes.setBackground(cor);
        painel1.add(tfParcelasRes);

        JLabel lbValorRes = new JLabel("Total Restante");
        lbValorRes.setBounds(20, 205, 125, 14);
        lbValorRes.setFont(fonte);
        painel1.add(lbValorRes);

        tfValorRes = new JTextField();
        tfValorRes.setBounds(20, 220, 120, 20);
        tfValorRes.setEditable(false);
        tfValorRes.setBackground(cor);
        painel1.add(tfValorRes);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(160, 205, 110, 14);
        lbDescricao.setFont(fonte);
        painel1.add(lbDescricao);

        tfDescricao = new JTextField();
        tfDescricao.setBounds(160, 220, 260, 20);
        tfDescricao.setEditable(false);
        tfDescricao.setBackground(cor);
        painel1.add(tfDescricao);

        JLabel lbEndereco = new JLabel("Endereco");
        lbEndereco.setBounds(20, 30, 110, 14);
        lbEndereco.setFont(fonte);
        painel2.add(lbEndereco);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(20, 45, 120, 20);
        tfEndereco.setEditable(false);
        tfEndereco.setBackground(cor);
        painel2.add(tfEndereco);

        JLabel lbNumero = new JLabel("Numero");
        lbNumero.setBounds(160, 30, 110, 14);
        lbNumero.setFont(fonte);
        painel2.add(lbNumero);

        tfNumero = new JTextField();
        tfNumero.setBounds(160, 45, 120, 20);
        tfNumero.setEditable(false);
        tfNumero.setBackground(cor);
        painel2.add(tfNumero);

        JLabel lbBairro = new JLabel("Bairro");
        lbBairro.setBounds(300, 30, 110, 14);
        lbBairro.setFont(fonte);
        painel2.add(lbBairro);

        tfBairro = new JTextField();
        tfBairro.setBounds(300, 45, 120, 20);
        tfBairro.setEditable(false);
        tfBairro.setBackground(cor);
        painel2.add(tfBairro);

        JLabel lb_Cidade = new JLabel("Cidade");
        lb_Cidade.setBounds(20, 66, 110, 14);
        lb_Cidade.setFont(fonte);
        painel2.add(lb_Cidade);

        tfCidade = new JTextField();
        tfCidade.setBounds(20, 81, 120, 20);
        tfCidade.setEditable(false);
        tfCidade.setBackground(cor);
        painel2.add(tfCidade);

        JLabel lbEstado = new JLabel("Estado");
        lbEstado.setBounds(160, 66, 110, 14);
        lbEstado.setFont(fonte);
        painel2.add(lbEstado);

        tfEstado = new JTextField();
        tfEstado.setBounds(160, 81, 120, 20);
        tfEstado.setEditable(false);
        tfEstado.setBackground(cor);
        painel2.add(tfEstado);

        JLabel lbContato = new JLabel("Contato");
        lbContato.setBounds(300, 66, 110, 14);
        lbContato.setFont(fonte);
        painel2.add(lbContato);

        tfContato = new JTextField();
        tfContato.setBounds(300, 81, 120, 20);
        tfContato.setEditable(false);
        tfContato.setBackground(cor);
        painel2.add(tfContato);

        tabela = new JTable(tableModel);
        for (int x = 0; x < tabela.getColumnModel().getColumnCount(); x++) {
            tabela.getColumnModel().getColumn(x).setCellRenderer(rendener);
        }
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(10, 377, 560, 178);
        painel1.add(scroll);

        JLabel lbSubTotal = new JLabel("Sub Total R$");
        lbSubTotal.setBounds(394, 567, 125, 14);
        lbSubTotal.setFont(fonte);
        painel1.add(lbSubTotal);

        tfSubTotal = new JTextField();
        tfSubTotal.setBounds(470, 565, 100, 20);
        tfSubTotal.setEditable(false);
        tfSubTotal.setBackground(cor);
        painel1.add(tfSubTotal);

        JLabel lbDescontos = new JLabel("Descontos %");
        lbDescontos.setBounds(394, 593, 125, 14);
        lbDescontos.setFont(fonte);
        painel1.add(lbDescontos);

        tfDescontos = new JTextField();
        tfDescontos.setBounds(470, 591, 100, 20);
        tfDescontos.setEditable(false);
        tfDescontos.setBackground(cor);
        painel1.add(tfDescontos);

        JLabel lbTotal = new JLabel("Total R$:");
        lbTotal.setBounds(394, 619, 125, 14);
        lbTotal.setFont(fonte);
        painel1.add(lbTotal);

        tfTotal = new JTextField();
        tfTotal.setBounds(470, 617, 100, 20);
        tfTotal.setEditable(false);
        tfTotal.setBackground(cor);
        painel1.add(tfTotal);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(610, 700);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
    }

    private void recuperarCliente() throws Exception {
        if (controlCliente.vazio() == true) {
            throw new Exception("Não há clientes cadastrados");
        } else {
            if (controlCliente.recuperar(Integer.parseInt(tfCodCliente.getText())) == true) {
                tfNomeCliente.setText(controlCliente.getNome());
            }
        }
    }

    private void recuperarVendedor() throws Exception {
        if (controlVendedor.vazio() == true) {
            throw new Exception("Não há vendedores cadastrados");
        } else {
            if (controlVendedor.recuperar(Integer.parseInt(tfCodVendedor.getText())) == true) {
                tfNomeVendedor.setText(controlVendedor.getNome());
            }
        }
    }

    private void recuperarItemVendas() {
        tableModel.limparTabela();
        tableModel.adicionaTabela(Integer.parseInt(tfCodigo.getText()));
    }

    private void recuperaPagamento() {
        if (!"".equals(tfCodigo.getText())) {
            if (controlPagamento.vazio() == true) {
            } else {
                if (controlPagamento.recuperarVen(Integer.parseInt(tfCodigo.getText())) == true) {
                    tfParcelasRes.setText(controlPagamento.getParcelasRes());
                    tfValorRes.setText(Double.toString(controlPagamento.getValorPago()));
                    tfSituacao.setText(controlPagamento.getSituacao());
                } else {
                    if (controlVenda.recuperar(Integer.parseInt(tfCodigo.getText())) == true) {
                        tfParcelasRes.setText(controlVenda.getCondPagto());
                        tfValorRes.setText(Double.toString(controlVenda.getTotal()));
                    }
                }
            }
        }
    }

    private void recuperarVendas() throws Exception {
        if ("".equals(tfCodigo.getText())) {
            throw new Exception("Campo codigo invalido");
        } else {
            if (!"".equals(tfCodigo.getText())) {
                if (controlVenda.vazio() == true) {
                    throw new Exception("Não há vendas cadastradas");
                } else {
                    if (controlVenda.recuperar(Integer.parseInt(tfCodigo.getText())) == true) {
                        tfDataEmissao.setText(controlVenda.getDataEmissao());
                        tfCodVendedor.setText(Integer.toString(controlVenda.getCodigoVendedor()));
                        tfCodCliente.setText(Integer.toString(controlVenda.getCodigoCliente()));
                        tfSituacao.setText(controlVenda.getSituacao());
                        tfFormaPagto.setText(controlVenda.getFormaPagto());
                        tfCondPagto.setText(controlVenda.getCondPagto());
                        tfDescricao.setText(controlVenda.getDescricao());
                        tfEndereco.setText(controlVenda.getEndedrecoEnt());
                        tfNumero.setText(Integer.toString(controlVenda.getNumeroEnt()));
                        tfBairro.setText(controlVenda.getBairroEnt());
                        tfCidade.setText(controlVenda.getCidadeEnt());
                        tfEstado.setText(controlVenda.getEstadoEnt());
                        tfContato.setText(controlVenda.getContatoEnt());
                        Object valorSubTotal = controlVenda.getSubTotal();
                        tfSubTotal.setText(nRealf.format(valorSubTotal));
                        tfDescontos.setText(Double.toString(controlVenda.getDescontos()));
                        Object valorDescontos = controlVenda.getTotal();
                        tfTotal.setText(nRealf.format(valorDescontos));
                        recuperarCliente();
                        recuperarVendedor();
                        recuperarItemVendas();
                        recuperaPagamento();
                    }
                }
            }
        }
    }

    private void limparTela() {
        tfCodigo.setText("");
        tfDataEmissao.setText("");
        tfCodVendedor.setText("");
        tfNomeVendedor.setText("");
        tfCodCliente.setText("");
        tfNomeCliente.setText("");
        tfSituacao.setText("");
        tfFormaPagto.setText("");
        tfCondPagto.setText("");
        tfParcelasRes.setText("");
        tfValorRes.setText("");
        tfDescricao.setText("");
        tfEndereco.setText("");
        tfNumero.setText("");
        tfBairro.setText("");
        tfCidade.setText("");
        tfEstado.setText("");
        tfContato.setText("");
        tfSubTotal.setText("");
        tfDescontos.setText("");
        tfTotal.setText("");
        tableModel.limparTabela();
        tableModel.fireTableDataChanged();
    }

    private void ok() throws Exception {
        if (!"".equals(tfDataEmissao.getText())) {
            String Texto = tfCodigo.getText();
            limparTela();
            tfCodigo.setText(Texto);
            recuperarVendas();
        } else {
            recuperarVendas();
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btGerar) {
            try {
                ok();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
    }

    public void focusLost(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.WHITE);
            try {
                if (!"".equals(tfCodigo.getText())) {
                    ok();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

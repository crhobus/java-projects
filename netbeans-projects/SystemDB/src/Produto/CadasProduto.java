package Produto;

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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Modelo.Produto;
import Principal.CamposInt;
import Principal.Controle;
import SSL.EchoClient;

public class CadasProduto extends Controle implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNomeProd,
            tfMarca, tfModelo, tfDescricao, tfIdentificacao, tfCor, tfUnidade,
            tfMesGarantia, tfQuantidade, tfPrecoUnitCompra, tfPercentualLucro,
            tfIpi, tfDesconto, tfPrecoUnitVenda, tfValorTotal, tfObservacoes;
    private JTextArea taAcessorios;
    private JButton btConsulta, btOk, btCancelar, btExcluir, btSair,
            btCalcular;
    private JLabel lbNomeProdObrig, lbMarcaObrig, lbModeloObrig,
            lbUnidadeObrig, lbQtdadeObrig, lbPrecoUnitCompraObrig,
            lbPercentualLucroObrig, lbIpiObrig, lbDescontoObrig,
            lbPrecoUnitVendaObrig, lbIdentificacaoObrig;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;
    private SimpleDateFormat formatDateHora;
    private ProdutoControl controle;
    // Objeto de conexao com o servidor.
    private EchoClient echoClient = new EchoClient();

    public CadasProduto(Connection con) {
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        controle = new ProdutoControl(con);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Produtos");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 672, 390);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        add(panel);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 20, 60, 14);
        panel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodProd()));
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
        btConsulta.setToolTipText("Consulta Produtos");
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

        JLabel lbNomeProd = new JLabel("Nome Produto");
        lbNomeProd.setBounds(400, 20, 70, 14);
        panel.add(lbNomeProd);

        lbNomeProdObrig = new JLabel("");
        lbNomeProdObrig.setBounds(470, 23, 10, 14);
        lbNomeProdObrig.setForeground(Color.RED);
        panel.add(lbNomeProdObrig);

        tfNomeProd = new JTextField();
        tfNomeProd.setBounds(400, 38, 250, 20);
        tfNomeProd.addFocusListener(this);
        panel.add(tfNomeProd);

        JLabel lbMarca = new JLabel("Marca");
        lbMarca.setBounds(20, 63, 50, 14);
        panel.add(lbMarca);

        lbMarcaObrig = new JLabel("");
        lbMarcaObrig.setBounds(51, 66, 10, 14);
        lbMarcaObrig.setForeground(Color.RED);
        panel.add(lbMarcaObrig);

        tfMarca = new JTextField();
        tfMarca.setBounds(20, 81, 140, 20);
        tfMarca.addFocusListener(this);
        panel.add(tfMarca);

        JLabel lbModelo = new JLabel("Modelo");
        lbModelo.setBounds(170, 63, 50, 14);
        panel.add(lbModelo);

        lbModeloObrig = new JLabel("");
        lbModeloObrig.setBounds(206, 66, 10, 14);
        lbModeloObrig.setForeground(Color.RED);
        panel.add(lbModeloObrig);

        tfModelo = new JTextField();
        tfModelo.setBounds(170, 81, 110, 20);
        tfModelo.addFocusListener(this);
        panel.add(tfModelo);

        JLabel lbDescricao = new JLabel("Descrição");
        lbDescricao.setBounds(290, 63, 50, 14);
        panel.add(lbDescricao);

        tfDescricao = new JTextField();
        tfDescricao.setBounds(290, 81, 230, 20);
        tfDescricao.addFocusListener(this);
        panel.add(tfDescricao);

        JLabel lbNumSerie = new JLabel("Identificação");
        lbNumSerie.setBounds(530, 63, 70, 14);
        panel.add(lbNumSerie);

        lbIdentificacaoObrig = new JLabel("");
        lbIdentificacaoObrig.setBounds(594, 66, 10, 14);
        lbIdentificacaoObrig.setForeground(Color.RED);
        panel.add(lbIdentificacaoObrig);

        tfIdentificacao = new JTextField();
        tfIdentificacao.setBounds(530, 81, 120, 20);
        tfIdentificacao.addFocusListener(this);
        panel.add(tfIdentificacao);

        JLabel lbCor = new JLabel("Cor");
        lbCor.setBounds(20, 105, 40, 14);
        panel.add(lbCor);

        tfCor = new JTextField();
        tfCor.setBounds(20, 123, 110, 20);
        tfCor.addFocusListener(this);
        panel.add(tfCor);

        JLabel lbUnidade = new JLabel("Unidade");
        lbUnidade.setBounds(140, 105, 40, 14);
        panel.add(lbUnidade);

        lbUnidadeObrig = new JLabel("");
        lbUnidadeObrig.setBounds(181, 108, 10, 14);
        lbUnidadeObrig.setForeground(Color.RED);
        panel.add(lbUnidadeObrig);

        tfUnidade = new JTextField();
        tfUnidade.setBounds(140, 123, 90, 20);
        tfUnidade.addFocusListener(this);
        panel.add(tfUnidade);

        JLabel lbMesGarantia = new JLabel("Meses Garantia");
        lbMesGarantia.setBounds(240, 105, 80, 14);
        panel.add(lbMesGarantia);

        tfMesGarantia = new JTextField();
        tfMesGarantia.setBounds(240, 123, 90, 20);
        tfMesGarantia.setDocument(new CamposInt());
        tfMesGarantia.addFocusListener(this);
        panel.add(tfMesGarantia);

        JLabel lbQuantidade = new JLabel("Quantidade");
        lbQuantidade.setBounds(340, 105, 70, 14);
        panel.add(lbQuantidade);

        lbQtdadeObrig = new JLabel("");
        lbQtdadeObrig.setBounds(398, 108, 10, 14);
        lbQtdadeObrig.setForeground(Color.RED);
        panel.add(lbQtdadeObrig);

        tfQuantidade = new JTextField();
        tfQuantidade.setBounds(340, 123, 90, 20);
        tfQuantidade.setDocument(new CamposInt());
        tfQuantidade.addFocusListener(this);
        panel.add(tfQuantidade);

        JLabel lbPrecoUnitCompra = new JLabel("Preço Unit. Compra");
        lbPrecoUnitCompra.setBounds(440, 105, 110, 14);
        panel.add(lbPrecoUnitCompra);

        lbPrecoUnitCompraObrig = new JLabel("");
        lbPrecoUnitCompraObrig.setBounds(535, 108, 10, 14);
        lbPrecoUnitCompraObrig.setForeground(Color.RED);
        panel.add(lbPrecoUnitCompraObrig);

        tfPrecoUnitCompra = new JTextField();
        tfPrecoUnitCompra.setBounds(440, 123, 100, 20);
        tfPrecoUnitCompra.addFocusListener(this);
        panel.add(tfPrecoUnitCompra);

        JLabel lbPercentualLucro = new JLabel("Percentual Lucro Unit.");
        lbPercentualLucro.setBounds(550, 105, 110, 14);
        panel.add(lbPercentualLucro);

        lbPercentualLucroObrig = new JLabel("");
        lbPercentualLucroObrig.setBounds(656, 108, 10, 14);
        lbPercentualLucroObrig.setForeground(Color.RED);
        panel.add(lbPercentualLucroObrig);

        tfPercentualLucro = new JTextField();
        tfPercentualLucro.setBounds(550, 123, 90, 20);
        tfPercentualLucro.addFocusListener(this);
        panel.add(tfPercentualLucro);

        JLabel lbPorcentagem = new JLabel("%");
        lbPorcentagem.setBounds(643, 126, 30, 14);
        panel.add(lbPorcentagem);

        JLabel lbIpi = new JLabel("I.P.I. Unit.");
        lbIpi.setBounds(20, 147, 70, 14);
        panel.add(lbIpi);

        lbIpiObrig = new JLabel("");
        lbIpiObrig.setBounds(72, 150, 10, 14);
        lbIpiObrig.setForeground(Color.RED);
        panel.add(lbIpiObrig);

        tfIpi = new JTextField();
        tfIpi.setBounds(20, 165, 70, 20);
        tfIpi.addFocusListener(this);
        panel.add(tfIpi);

        lbPorcentagem = new JLabel("%");
        lbPorcentagem.setBounds(95, 168, 30, 14);
        panel.add(lbPorcentagem);

        JLabel lbDesconto = new JLabel("Descontos Unit.");
        lbDesconto.setBounds(110, 147, 80, 14);
        panel.add(lbDesconto);

        lbDescontoObrig = new JLabel("");
        lbDescontoObrig.setBounds(186, 150, 10, 14);
        lbDescontoObrig.setForeground(Color.RED);
        panel.add(lbDescontoObrig);

        tfDesconto = new JTextField();
        tfDesconto.setBounds(110, 165, 70, 20);
        tfDesconto.addFocusListener(this);
        panel.add(tfDesconto);

        lbPorcentagem = new JLabel("%");
        lbPorcentagem.setBounds(184, 168, 30, 14);
        panel.add(lbPorcentagem);

        btCalcular = new JButton("Calcular");
        btCalcular.setBounds(198, 162, 50, 26);
        btCalcular.setMargin(new Insets(0, 0, 0, 0));
        btCalcular.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCalcular.setToolTipText("Calcular o valor do produto");
        btCalcular.addActionListener(this);
        panel.add(btCalcular);

        JLabel lbPrecoUnitVenda = new JLabel("Preço Unit. Venda");
        lbPrecoUnitVenda.setBounds(255, 147, 110, 14);
        panel.add(lbPrecoUnitVenda);

        lbPrecoUnitVendaObrig = new JLabel("");
        lbPrecoUnitVendaObrig.setBounds(344, 150, 10, 14);
        lbPrecoUnitVendaObrig.setForeground(Color.RED);
        panel.add(lbPrecoUnitVendaObrig);

        tfPrecoUnitVenda = new JTextField();
        tfPrecoUnitVenda.setBounds(255, 165, 90, 20);
        tfPrecoUnitVenda.setText("R$ 0,00");
        tfPrecoUnitVenda.setEditable(false);
        tfPrecoUnitVenda.setBackground(Color.WHITE);
        tfPrecoUnitVenda.addFocusListener(this);
        panel.add(tfPrecoUnitVenda);

        JLabel lbValorTotal = new JLabel("Valor Total");
        lbValorTotal.setBounds(355, 147, 70, 14);
        panel.add(lbValorTotal);

        tfValorTotal = new JTextField();
        tfValorTotal.setBounds(355, 165, 75, 20);
        tfValorTotal.setText("R$ 0,00");
        tfValorTotal.setEditable(false);
        tfValorTotal.setBackground(Color.WHITE);
        tfValorTotal.addFocusListener(this);
        panel.add(tfValorTotal);

        JLabel lbObservacoes = new JLabel("Observações");
        lbObservacoes.setBounds(440, 147, 70, 14);
        panel.add(lbObservacoes);

        tfObservacoes = new JTextField();
        tfObservacoes.setBounds(440, 165, 210, 20);
        tfObservacoes.addFocusListener(this);
        panel.add(tfObservacoes);

        JLabel lbAcessorios = new JLabel("Acessórios");
        lbAcessorios.setBounds(20, 190, 70, 14);
        panel.add(lbAcessorios);

        taAcessorios = new JTextArea();
        taAcessorios.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        taAcessorios.addFocusListener(this);
        JScrollPane scrollAcessorios = new JScrollPane(taAcessorios);
        scrollAcessorios.setBounds(20, 208, 630, 100);
        panel.add(scrollAcessorios);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 330, 672, 3);
        panel.add(separator);

        Icon icOk = new ImageIcon("OK.png");
        btOk = new JButton("OK", icOk);
        btOk.setBounds(140, 345, 70, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("Confirma Operação");
        btOk.addActionListener(this);
        panel.add(btOk);

        Icon icCancelar = new ImageIcon("Cancelar.png");
        btCancelar = new JButton("Cancelar", icCancelar);
        btCancelar.setBounds(250, 345, 70, 26);
        btCancelar.setMargin(new Insets(0, 0, 0, 0));
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Limpar os Campos");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        Icon icExcluir = new ImageIcon("Excluir.png");
        btExcluir = new JButton("Excluir", icExcluir);
        btExcluir.setBounds(360, 345, 70, 26);
        btExcluir.setMargin(new Insets(0, 0, 0, 0));
        btExcluir.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluir.setToolTipText("Excluir Registro");
        btExcluir.addActionListener(this);
        panel.add(btExcluir);

        Icon icSair = new ImageIcon("Sair.png");
        btSair = new JButton("Sair", icSair);
        btSair.setBounds(470, 345, 70, 26);
        btSair.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btSair.setToolTipText("Fechar");
        btSair.addActionListener(this);
        panel.add(btSair);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(700, 440);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodProd()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfNomeProd.setText("");
        tfMarca.setText("");
        tfModelo.setText("");
        tfDescricao.setText("");
        tfIdentificacao.setText("");
        tfCor.setText("");
        tfUnidade.setText("");
        tfMesGarantia.setText("");
        tfQuantidade.setText("");
        tfPrecoUnitCompra.setText("");
        tfPercentualLucro.setText("");
        tfIpi.setText("");
        tfDesconto.setText("");
        tfPrecoUnitVenda.setText("R$ 0,00");
        tfValorTotal.setText("R$ 0,00");
        tfObservacoes.setText("");
        taAcessorios.setText("");
        lbNomeProdObrig.setText("");
        lbMarcaObrig.setText("");
        lbModeloObrig.setText("");
        lbUnidadeObrig.setText("");
        lbQtdadeObrig.setText("");
        lbPrecoUnitCompraObrig.setText("");
        lbPercentualLucroObrig.setText("");
        lbIpiObrig.setText("");
        lbDescontoObrig.setText("");
        lbPrecoUnitVendaObrig.setText("");
        lbIdentificacaoObrig.setText("");
    }

    private void gravar() throws Exception {
        boolean flag = false;
        if ("".equals(tfNomeProd.getText().trim())) {
            lbNomeProdObrig.setText("*");
            flag = true;
        } else {
            lbNomeProdObrig.setText("");
        }
        if ("".equals(tfMarca.getText().trim())) {
            lbMarcaObrig.setText("*");
            flag = true;
        } else {
            lbMarcaObrig.setText("");
        }
        if ("".equals(tfModelo.getText().trim())) {
            lbModeloObrig.setText("*");
            flag = true;
        } else {
            lbModeloObrig.setText("");
        }
        if ("".equals(tfUnidade.getText().trim())) {
            lbUnidadeObrig.setText("*");
            flag = true;
        } else {
            lbUnidadeObrig.setText("");
        }
        if ("".equals(tfQuantidade.getText())) {
            lbQtdadeObrig.setText("*");
            flag = true;
        } else {
            lbQtdadeObrig.setText("");
        }
        if ("".equals(tfPrecoUnitCompra.getText().trim())) {
            lbPrecoUnitCompraObrig.setText("*");
            flag = true;
        } else {
            lbPrecoUnitCompraObrig.setText("");
        }
        if ("".equals(tfPercentualLucro.getText().trim())) {
            lbPercentualLucroObrig.setText("*");
            flag = true;
        } else {
            lbPercentualLucroObrig.setText("");
        }
        if ("".equals(tfIpi.getText().trim())) {
            lbIpiObrig.setText("*");
            flag = true;
        } else {
            lbIpiObrig.setText("");
        }
        if ("".equals(tfDesconto.getText().trim())) {
            lbDescontoObrig.setText("*");
            flag = true;
        } else {
            lbDescontoObrig.setText("");
        }
        if ("R$ 0,00".equals(tfPrecoUnitVenda.getText())) {
            lbPrecoUnitVendaObrig.setText("*");
            flag = true;
        } else {
            lbPrecoUnitVendaObrig.setText("");
        }
        if ("".equals(tfIdentificacao.getText().trim())) {
            lbIdentificacaoObrig.setText("*");
            flag = true;
        } else {
            lbIdentificacaoObrig.setText("");
        }
        if (!flag) {
            if (!"".equals(tfMesGarantia.getText().trim())) {
                validaInteger(tfMesGarantia, "Campo meses garantia inválido", "Meses de garantia deve ser superior a 0", 1);
            }
            validaDouble(tfPrecoUnitCompra, "Campo preço unitário de compra inválido", "Preço unitário deve conter valor positivo", 0);
            validaDouble(tfPercentualLucro, "Campo percentual de lucro inválido", "Percentual de lucro deve conter valor positivo", 0);
            validaDouble(tfIpi, "Campo I.P.I inválido", "I.P.I. deve conter valor positivo", 0);
            validaDouble(tfDesconto, "Campo descontos inválido", "Descontos deve conter valor positivo", 0);
            Produto produto = new Produto();
            produto.setCodProduto(Integer.parseInt(tfCodigo.getText()));
            produto.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            produto.setUltAlteracao(new Date());
            produto.setProduto(tfNomeProd.getText());
            produto.setMarca(tfMarca.getText());
            produto.setModelo(tfModelo.getText());
            produto.setDescricao(tfDescricao.getText());
            produto.setIdentificacao(tfIdentificacao.getText());
            produto.setCor(tfCor.getText());
            produto.setUnidade(tfUnidade.getText());
            if (!"".equals(tfMesGarantia.getText().trim())) {
                produto.setMesesGarantia(Integer.parseInt(tfMesGarantia.getText()));
            } else {
                produto.setMesesGarantia(-1);
            }
            produto.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
            produto.setPrecoCompra(Double.parseDouble(tfPrecoUnitCompra.getText()));
            produto.setPercentualLucro(Double.parseDouble(tfPercentualLucro.getText()));
            produto.setIpi(Double.parseDouble(tfIpi.getText()));
            produto.setDescontos(Double.parseDouble(tfDesconto.getText()));
            produto.setObservacoes(tfObservacoes.getText());
            produto.setAscessorios(taAcessorios.getText());

            echoClient.conectar(true);
            echoClient.mandarRequisicao(produto);
            echoClient.desconectar();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "Produto", JOptionPane.INFORMATION_MESSAGE);
            limparTela();

//            int codProdCadas = controle.getProdCadastrado(tfNomeProd.getText(), tfMarca.getText(), tfModelo.getText());// verifica se produto já cadastrado
//            if (codProdCadas != -1) {
//                codProdCadas = controle.getProdCadastrado(tfIdentificacao.getText());// verifica se essa identificação já cadastrada
//                if (codProdCadas != -1 && codProdCadas != Integer.parseInt(tfCodigo.getText())) {
//                    JOptionPane.showMessageDialog(null, "Já contém produto(s) com esta identificação", "Atenção", JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
//                if (JOptionPane.showConfirmDialog(null, "Confirmar alteração nos dados", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                    if (controle.updateProduto(produto)) {
//                        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "Produto", JOptionPane.INFORMATION_MESSAGE);
//                        limparTela();
//                    }
//                }
//            } else {
//                codProdCadas = controle.getProdCadastrado(tfIdentificacao.getText());// verifica se essa identificação já cadastrada
//                if (codProdCadas != -1 && codProdCadas != Integer.parseInt(tfCodigo.getText())) {
//                    JOptionPane.showMessageDialog(null, "Já contém produto(s) com esta identificação", "Atenção", JOptionPane.WARNING_MESSAGE);
//                } else {
//                    if (controle.insertProduto(produto)) {
//                        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "Produto", JOptionPane.INFORMATION_MESSAGE);
//                        limparTela();
//                    }
//                }
//            }
        } else {
            throw new Exception("Campos * obrigatórios inválidos");
        }
    }

    private void consulta() throws Exception {
        if (controle.isProdVazio()) {
            throw new Exception("Não há produtos cadastrados");
        }
        ConsultaProduto consulta = new ConsultaProduto(controle);
        consulta.setListener(new ListenerProduto() {
            @Override
            public void exibeProduto(Produto produto) {
                limparTela();
                tfCodigo.setText(Integer.toString(produto.getCodProduto()));
                tfDataCadas.setText(formatDate.format(produto.getDataCadastro()) + " as " + formatHora.format(produto.getDataCadastro()));
                tfUltAlteracao.setText(formatDate.format(produto.getUltAlteracao()) + " as " + formatHora.format(produto.getUltAlteracao()));
                tfNomeProd.setText(produto.getProduto());
                tfMarca.setText(produto.getMarca());
                tfModelo.setText(produto.getModelo());
                tfDescricao.setText(produto.getDescricao());
                tfIdentificacao.setText(produto.getIdentificacao());
                tfCor.setText(produto.getCor());
                tfUnidade.setText(produto.getUnidade());
                if (produto.getMesesGarantia() != -1) {
                    tfMesGarantia.setText(Integer.toString(produto.getMesesGarantia()));
                }
                tfQuantidade.setText(Integer.toString(produto.getQuantidade()));
                tfPrecoUnitCompra.setText(Double.toString(produto.getPrecoCompra()));
                tfPercentualLucro.setText(Double.toString(produto.getPercentualLucro()));
                tfIpi.setText(Double.toString(produto.getIpi()));
                tfDesconto.setText(Double.toString(produto.getDescontos()));
                tfObservacoes.setText(produto.getObservacoes());
                taAcessorios.setText(produto.getAscessorios());
                try {
                    calcularValorProduto();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void excluir() throws Exception {
        if (controle.isProdVazio()) {
            throw new Exception("Não há produtos cadastrados");
        }
        if (!controle.isProdutoCadastrado(Integer.parseInt(tfCodigo.getText()))) {
            throw new Exception("Selecione um produto");
        }
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse produto", "Produto", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (controle.deleteProd(Integer.parseInt(tfCodigo.getText()))) {
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso", "Produto", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            }
        }
    }

    private void calcularValorProduto() throws Exception {
        validaInteger(tfQuantidade, "Campo Quantidade inválido", "Quantidade deve conter valor positivo", 0);
        validaDouble(tfPrecoUnitCompra, "Campo preço unitário de compra inválido", "Preço unitário deve conter valor positivo", 0);
        validaDouble(tfPercentualLucro, "Campo percentual de lucro inválido", "Percentual de lucro deve conter valor positivo", 0);
        validaDouble(tfIpi, "Campo I.P.I inválido", "I.P.I. deve conter valor positivo", 0);
        validaDouble(tfDesconto, "Campo descontos inválido", "Descontos deve conter valor positivo", 0);
        double precoUnitCom = Double.parseDouble(tfPrecoUnitCompra.getText());
        precoUnitCom += precoUnitCom * (Double.parseDouble(tfPercentualLucro.getText()) / 100);
        precoUnitCom += precoUnitCom * (Double.parseDouble(tfIpi.getText()) / 100);
        precoUnitCom -= precoUnitCom * (Double.parseDouble(tfDesconto.getText()) / 100);
        tfPrecoUnitVenda.setText(NumberFormat.getCurrencyInstance().format(precoUnitCom));
        tfValorTotal.setText(NumberFormat.getCurrencyInstance().format(precoUnitCom * Integer.parseInt(tfQuantidade.getText())));
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
                consulta();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCalcular) {
            try {
                calcularValorProduto();
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
        if (evento.getSource() == tfNomeProd) {
            tfNomeProd.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMarca) {
            tfMarca.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfModelo) {
            tfModelo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfIdentificacao) {
            tfIdentificacao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCor) {
            tfCor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUnidade) {
            tfUnidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMesGarantia) {
            tfMesGarantia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfQuantidade) {
            tfQuantidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPrecoUnitCompra) {
            tfPrecoUnitCompra.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPercentualLucro) {
            tfPercentualLucro.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfIpi) {
            tfIpi.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDesconto) {
            tfDesconto.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPrecoUnitVenda) {
            tfPrecoUnitVenda.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorTotal) {
            tfValorTotal.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfObservacoes) {
            tfObservacoes.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == taAcessorios) {
            taAcessorios.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfNomeProd.setBackground(Color.WHITE);
        tfMarca.setBackground(Color.WHITE);
        tfModelo.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        tfIdentificacao.setBackground(Color.WHITE);
        tfCor.setBackground(Color.WHITE);
        tfUnidade.setBackground(Color.WHITE);
        tfMesGarantia.setBackground(Color.WHITE);
        tfQuantidade.setBackground(Color.WHITE);
        tfPrecoUnitCompra.setBackground(Color.WHITE);
        tfPercentualLucro.setBackground(Color.WHITE);
        tfIpi.setBackground(Color.WHITE);
        tfDesconto.setBackground(Color.WHITE);
        tfPrecoUnitVenda.setBackground(Color.WHITE);
        tfValorTotal.setBackground(Color.WHITE);
        tfObservacoes.setBackground(Color.WHITE);
        taAcessorios.setBackground(Color.WHITE);
    }
}

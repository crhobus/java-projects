package Produtos;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Formatacao.CamposInt;
import Formatacao.ObjGraficos;
import Modelo.Produto;

public class CadasProdutos extends ObjGraficos implements ActionListener, ItemListener, FocusListener {

    private static final long serialVersionUID = 6893506478672499043L;
    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome,
            tfDescricao, tfMarca, tfModelo, tfCor, tfNumeroSerie,
            tfMesesGarantia, tfQuantidade, tfPrecoUnitCompra, tfPercetualLucro,
            tfImpostosUnit, tfDescontos, tfPrecoUnitVenda, tfValorTotal;
    private JTextArea taAcessorios;
    private JLabel lbNomeObrig, lbMarcaObrig, lbModeloObrig,
            lbPrecoUnitVenObrig, lbMesesGarantiaObrig;
    private JButton btVer, btPesquisar, btOk, btCancelar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private ProdutoControl controle;

    public CadasProdutos(Connection con) {
        controle = new ProdutoControl(con);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Produtos");
        setLayout(null);
        JPanel panel = getJPanelLineBorder(10, 10, 610, 310);
        add(panel);

        panel.add(getJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = getJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodProd()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        tfCodigo.addFocusListener(this);
        panel.add(tfCodigo);

        btVer = getJButton("...", 106, 34, 31, 24);
        btVer.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btVer.setToolTipText("Consulta Produtos");
        btVer.addActionListener(this);
        panel.add(btVer);

        panel.add(getJLabel("Cadastrado em", 143, 20, 90, 14));
        tfDataCadas = getJTextField(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.setEditable(false);
        tfDataCadas.setBackground(Color.WHITE);
        tfDataCadas.addFocusListener(this);
        panel.add(tfDataCadas);

        panel.add(getJLabel("Última Alteração", 270, 20, 100, 14));
        tfUltAlteracao = getJTextField(270, 38, 120, 20);
        tfUltAlteracao.setEditable(false);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfUltAlteracao.addFocusListener(this);
        panel.add(tfUltAlteracao);

        panel.add(getJLabel("Nome", 400, 20, 120, 14));
        lbNomeObrig = getJLabel("", 435, 23, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel.add(lbNomeObrig);
        tfNome = getJTextField(400, 38, 180, 20);
        tfNome.addFocusListener(this);
        panel.add(tfNome);

        panel.add(getJLabel("Descrição", 20, 60, 120, 14));
        tfDescricao = getJTextField(20, 78, 255, 20);
        tfDescricao.addFocusListener(this);
        panel.add(tfDescricao);

        panel.add(getJLabel("Marca", 282, 60, 120, 14));
        lbMarcaObrig = getJLabel("", 321, 63, 10, 14);
        lbMarcaObrig.setForeground(Color.RED);
        panel.add(lbMarcaObrig);
        tfMarca = getJTextField(282, 78, 145, 20);
        tfMarca.addFocusListener(this);
        panel.add(tfMarca);

        panel.add(getJLabel("Modelo", 435, 60, 120, 14));
        lbModeloObrig = getJLabel("", 480, 63, 10, 14);
        lbModeloObrig.setForeground(Color.RED);
        panel.add(lbModeloObrig);
        tfModelo = getJTextField(435, 78, 145, 20);
        tfModelo.addFocusListener(this);
        panel.add(tfModelo);

        panel.add(getJLabel("Cor", 20, 100, 120, 14));
        tfCor = getJTextField(20, 118, 110, 20);
        tfCor.addFocusListener(this);
        panel.add(tfCor);

        panel.add(getJLabel("Número Série", 140, 100, 120, 14));
        tfNumeroSerie = getJTextField(140, 118, 95, 20);
        tfNumeroSerie.addFocusListener(this);
        tfNumeroSerie.setDocument(new CamposInt());
        panel.add(tfNumeroSerie);

        panel.add(getJLabel("Meses Garantia", 245, 100, 120, 14));
        lbMesesGarantiaObrig = getJLabel("", 337, 103, 10, 14);
        lbMesesGarantiaObrig.setForeground(Color.RED);
        panel.add(lbMesesGarantiaObrig);
        tfMesesGarantia = getJTextField(245, 118, 95, 20);
        tfMesesGarantia.addFocusListener(this);
        tfMesesGarantia.setDocument(new CamposInt());
        panel.add(tfMesesGarantia);

        panel.add(getJLabel("Quantidade", 350, 100, 120, 14));
        tfQuantidade = getJTextField(350, 118, 95, 20);
        tfQuantidade.addFocusListener(this);
        tfQuantidade.setDocument(new CamposInt());
        tfQuantidade.setText("");
        panel.add(tfQuantidade);

        panel.add(getJLabel("Preço Unit. Compra", 455, 100, 120, 14));
        tfPrecoUnitCompra = getJTextField(455, 118, 125, 20);
        tfPrecoUnitCompra.setText("");
        tfPrecoUnitCompra.addFocusListener(this);
        panel.add(tfPrecoUnitCompra);

        panel.add(getJLabel("Percentual Lucro", 20, 140, 120, 14));
        tfPercetualLucro = getJTextField(20, 158, 100, 20);
        tfPercetualLucro.setText("");
        tfPercetualLucro.addFocusListener(this);
        panel.add(tfPercetualLucro);
        panel.add(getJLabel("%", 122, 161, 10, 14));

        panel.add(getJLabel("Impostos Unit.", 140, 140, 120, 14));
        tfImpostosUnit = getJTextField(140, 158, 100, 20);
        tfImpostosUnit.setText("");
        tfImpostosUnit.addFocusListener(this);
        panel.add(tfImpostosUnit);
        panel.add(getJLabel("%", 242, 161, 10, 14));

        panel.add(getJLabel("Descontos Unit", 260, 140, 120, 14));
        tfDescontos = getJTextField(260, 158, 95, 20);
        tfDescontos.setText("");
        tfDescontos.addFocusListener(this);
        panel.add(tfDescontos);
        panel.add(getJLabel("%", 358, 161, 10, 14));

        panel.add(getJLabel("Preço Unit Venda", 378, 140, 120, 14));
        lbPrecoUnitVenObrig = getJLabel("", 477, 143, 10, 14);
        lbPrecoUnitVenObrig.setForeground(Color.RED);
        panel.add(lbPrecoUnitVenObrig);
        tfPrecoUnitVenda = getJTextField(378, 158, 95, 20);
        tfPrecoUnitVenda.setText("R$ 0,00");
        tfPrecoUnitVenda.setEditable(false);
        tfPrecoUnitVenda.setBackground(Color.WHITE);
        tfPrecoUnitVenda.addFocusListener(this);
        panel.add(tfPrecoUnitVenda);

        panel.add(getJLabel("Valor Total", 485, 140, 120, 14));
        tfValorTotal = getJTextField(485, 158, 95, 20);
        tfValorTotal.setText("R$ 0,00");
        tfValorTotal.setEditable(false);
        tfValorTotal.setBackground(Color.WHITE);
        tfValorTotal.addFocusListener(this);
        panel.add(tfValorTotal);

        panel.add(getJLabel("Acessórios", 20, 180, 130, 14));
        taAcessorios = getJTextArea(panel, 20, 198, 560, 100);
        taAcessorios.addFocusListener(this);

        btPesquisar = getJButton("Pesquisar", 670, 50, 95, 26);
        btPesquisar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.setToolTipText("Verifica quantidade de produtos disponível");
        btPesquisar.addActionListener(this);
        add(btPesquisar);

        btOk = getJButton("OK", 670, 190, 86, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("OK");
        btOk.addActionListener(this);
        add(btOk);

        btCancelar = getJButton("Cancelar", 670, 230, 86, 26);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        add(btCancelar);

        rbNovo = getJRadioButton("Novo", 675, 100, 80, 20);
        rbNovo.addItemListener(this);
        add(rbNovo);

        rbAlterar = getJRadioButton("Alterar", 675, 120, 80, 20);
        rbAlterar.addItemListener(this);
        add(rbAlterar);

        rbExcluir = getJRadioButton("Excluir", 675, 140, 80, 20);
        rbExcluir.addItemListener(this);
        add(rbExcluir);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(810, 360);
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
        tfNome.setText("");
        tfDescricao.setText("");
        tfMarca.setText("");
        tfModelo.setText("");
        tfCor.setText("");
        tfNumeroSerie.setText("");
        tfMesesGarantia.setText("");
        tfQuantidade.setText("");
        tfPrecoUnitCompra.setText("");
        tfPercetualLucro.setText("");
        tfImpostosUnit.setText("");
        tfDescontos.setText("");
        tfPrecoUnitVenda.setText("R$ 0,00");
        tfValorTotal.setText("R$ 0,00");
        taAcessorios.setText("");
        lbNomeObrig.setText("");
        lbMarcaObrig.setText("");
        lbModeloObrig.setText("");
        lbPrecoUnitVenObrig.setText("");
        lbMesesGarantiaObrig.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void calculoValor() throws Exception {
        if ("".equals(tfQuantidade.getText()) || "".equals(tfPrecoUnitCompra.getText().trim()) || "".equals(tfPercetualLucro.getText().trim()) || "".equals(tfImpostosUnit.getText().trim()) || "".equals(tfDescontos.getText().trim())) {
            tfPrecoUnitVenda.setText("R$ 0,00");
            return;
        }
        try {
            double precoUnitCom = Double.parseDouble(tfPrecoUnitCompra.getText());
            precoUnitCom += precoUnitCom * (Double.parseDouble(tfPercetualLucro.getText()) / 100);
            precoUnitCom += precoUnitCom * (Double.parseDouble(tfImpostosUnit.getText()) / 100);
            precoUnitCom -= precoUnitCom * (Double.parseDouble(tfDescontos.getText()) / 100);
            tfPrecoUnitVenda.setText(NumberFormat.getCurrencyInstance().format(precoUnitCom));
            tfValorTotal.setText(NumberFormat.getCurrencyInstance().format(precoUnitCom * Integer.parseInt(tfQuantidade.getText())));
        } catch (Exception ex) {
            tfPrecoUnitVenda.setText("R$ 0,00");
            tfValorTotal.setText("R$ 0,00");
            throw new Exception("Caracter inválido certifique se que os campos foram digitado corretamente. ex: 0.00");
        }
    }

    private void gravar() throws Exception {
        if ("".equals(tfNome.getText().trim())) {
            lbNomeObrig.setText("*");
        } else {
            lbNomeObrig.setText("");
        }
        if ("".equals(tfMarca.getText().trim())) {
            lbMarcaObrig.setText("*");
        } else {
            lbMarcaObrig.setText("");
        }
        if ("".equals(tfModelo.getText().trim())) {
            lbModeloObrig.setText("*");
        } else {
            lbModeloObrig.setText("");
        }
        if ("R$ 0,00".equals(tfPrecoUnitVenda.getText())) {
            lbPrecoUnitVenObrig.setText("*");
        } else {
            lbPrecoUnitVenObrig.setText("");
        }
        if ("".equals(tfMesesGarantia.getText())) {
            lbMesesGarantiaObrig.setText("*");
        } else {
            lbMesesGarantiaObrig.setText("");
        }
        if (!"".equals(tfNome.getText().trim()) && !"".equals(tfMarca.getText().trim()) && !"".equals(tfModelo.getText().trim()) && !"R$ 0,00".equals(tfPrecoUnitVenda.getText()) && !"".equals(tfMesesGarantia.getText())) {
            Produto produto = new Produto();
            produto.setCodigo(Integer.parseInt(tfCodigo.getText()));
            produto.setNumeroSerie(verificaInt(tfNumeroSerie, "número série"));
            produto.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
            produto.setMesGarantia(Integer.parseInt(tfMesesGarantia.getText()));
            produto.setNome(tfNome.getText());
            produto.setMarca(tfMarca.getText());
            produto.setModelo(tfModelo.getText());
            produto.setDescricao(tfDescricao.getText());
            produto.setAcessorios(taAcessorios.getText());
            produto.setCor(tfCor.getText());
            produto.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            produto.setUltAlteracao(new Date());
            produto.setPrecoUnitCompra(Double.parseDouble(tfPrecoUnitCompra.getText()));
            produto.setPrecoUnitVenda(Double.parseDouble(tfPrecoUnitVenda.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
            produto.setPercentualLucro(Double.parseDouble(tfPercetualLucro.getText()));
            produto.setImpostosUnit(Double.parseDouble(tfImpostosUnit.getText()));
            produto.setValorTotal(Double.parseDouble(tfValorTotal.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
            produto.setDescontos(Double.parseDouble(tfDescontos.getText()));
            Produto produtoLido = controle.selectProduto(tfNome.getText());
            if (produtoLido != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "O produto " + produto.getNome() + " ja esta cadastrado deseja substituilo? ", "Produto", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    produto.setCodigo(produtoLido.getCodigo());
                    if (controle.updateProduto(produto)) {
                        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "Produto", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                } else {
                    return;
                }
            } else {
                if (controle.insertProduto(produto)) {
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "Produto", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                }
            }
        } else {
            throw new Exception("Campos * obrigatórios inválidos");
        }
    }

    private void excluir() throws Exception {
        if (controle.isProdVazio()) {
            throw new Exception("Não há produtos cadastrados");
        }
        String nome = JOptionPane.showInputDialog(null, "Informe o nome do produto a ser excluído:", "Produto", JOptionPane.INFORMATION_MESSAGE);
        if (nome != null) {
            if (controle.deleteProduto(nome)) {
                JOptionPane.showMessageDialog(null, "Produto excluido com sucesso", "Produto", JOptionPane.INFORMATION_MESSAGE);
                limparTela();
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado", "Produto", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.isProdVazio()) {
            throw new Exception("Não há produtos cadastrados");
        }
        String nome = JOptionPane.showInputDialog(null, "Informe o nome do produto a ser recuperado:", "Produto", JOptionPane.INFORMATION_MESSAGE);
        if (nome != null) {
            Produto produto = controle.selectProduto(nome);
            if (produto != null) {
                mostrarProduto(produto);
                JOptionPane.showMessageDialog(null, "Produto recuperado com sucesso", "Produto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado", "Produto", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void mostrarProduto(Produto produto) {
        limparTela();
        tfCodigo.setText(Integer.toString(produto.getCodigo()));
        tfNumeroSerie.setText(recuperaCampoStr(produto.getNumeroSerie()));
        tfQuantidade.setText(Integer.toString(produto.getQuantidade()));
        tfMesesGarantia.setText(Integer.toString(produto.getMesGarantia()));
        tfNome.setText(produto.getNome());
        tfMarca.setText(produto.getMarca());
        tfModelo.setText(produto.getModelo());
        tfDescricao.setText(produto.getDescricao());
        taAcessorios.setText(produto.getAcessorios());
        tfCor.setText(produto.getCor());
        tfDataCadas.setText(formatDate.format(produto.getDataCadastro()) + " as " + formatHora.format(produto.getDataCadastro()));
        tfUltAlteracao.setText(formatDate.format(produto.getUltAlteracao()) + " as " + formatHora.format(produto.getUltAlteracao()));
        tfPrecoUnitCompra.setText(Double.toString(produto.getPrecoUnitCompra()));
        tfPrecoUnitVenda.setText(NumberFormat.getCurrencyInstance().format(produto.getPrecoUnitVenda()));
        tfPercetualLucro.setText(Double.toString(produto.getPercentualLucro()));
        tfImpostosUnit.setText(Double.toString(produto.getImpostosUnit()));
        tfValorTotal.setText(NumberFormat.getCurrencyInstance().format(produto.getValorTotal()));
        tfDescontos.setText(Double.toString(produto.getDescontos()));
    }

    private void abrirPesquisaPord() throws Exception {
        if (controle.isProdVazio()) {
            throw new Exception("Não há produtos cadastrados");
        }
        PesquisaProdutos pesquisaProd = new PesquisaProdutos(controle);
        pesquisaProd.setListener(new ListenerProduto() {

            @Override
            public void exibeProduto(Produto produto) {
                mostrarProduto(produto);
            }
        });
        pesquisaProd.setModal(true);
        pesquisaProd.setVisible(true);
    }

    private void verificaQtdadeProdCadas() throws Exception {
        if (controle.isProdVazio()) {
            throw new Exception("Não há produtos cadastrados");
        }
        String nome = JOptionPane.showInputDialog(null, "Informe o nome do produto:", "Produto", JOptionPane.INFORMATION_MESSAGE);
        if (nome != null) {
            Produto produto = controle.selectProduto(nome);
            if (produto != null) {
                JOptionPane.showMessageDialog(null, "Quantidade de " + nome + ": " + produto.getQuantidade(), "Produto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado", "Produto", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Produto", JOptionPane.ERROR_MESSAGE);
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
                abrirPesquisaPord();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btPesquisar) {
            try {
                verificaQtdadeProdCadas();
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
        if (evento.getSource() == tfNome) {
            tfNome.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMarca) {
            tfMarca.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfModelo) {
            tfModelo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCor) {
            tfCor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumeroSerie) {
            tfNumeroSerie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMesesGarantia) {
            tfMesesGarantia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfQuantidade) {
            tfQuantidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPrecoUnitCompra) {
            tfPrecoUnitCompra.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPercetualLucro) {
            tfPercetualLucro.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfImpostosUnit) {
            tfImpostosUnit.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescontos) {
            tfDescontos.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPrecoUnitVenda) {
            tfPrecoUnitVenda.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValorTotal) {
            tfValorTotal.setBackground(Color.YELLOW);
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
        tfNome.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        tfMarca.setBackground(Color.WHITE);
        tfModelo.setBackground(Color.WHITE);
        tfCor.setBackground(Color.WHITE);
        tfNumeroSerie.setBackground(Color.WHITE);
        tfMesesGarantia.setBackground(Color.WHITE);
        if (evento.getSource() == tfQuantidade) {
            tfQuantidade.setBackground(Color.WHITE);
            try {
                calculoValor();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == tfPrecoUnitCompra) {
            tfPrecoUnitCompra.setBackground(Color.WHITE);
            try {
                calculoValor();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == tfPercetualLucro) {
            tfPercetualLucro.setBackground(Color.WHITE);
            try {
                calculoValor();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == tfImpostosUnit) {
            tfImpostosUnit.setBackground(Color.WHITE);
            try {
                calculoValor();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (evento.getSource() == tfDescontos) {
            tfDescontos.setBackground(Color.WHITE);
            try {
                calculoValor();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        tfPrecoUnitVenda.setBackground(Color.WHITE);
        tfValorTotal.setBackground(Color.WHITE);
        taAcessorios.setBackground(Color.WHITE);
    }
}

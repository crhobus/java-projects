
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.ParseException;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;

public class Pagamentos extends JFrame {

    private ArrayList<ArrayPagamentos> lista1 = new ArrayList();
    private ArrayList<ArrayNovaVenda> lista2 = new ArrayList();
    private ArrayList<ArrayItenVenda> lista3 = new ArrayList();
    private ArrayList<ArrayCliente> lista4 = new ArrayList();
    private JTextField tf_Codigo, tf_CodigoVenda, tf_NomeCliente, tf_CondPagto, tf_Descontos, tf_Total, tf_Parcelas, tf_Pagamentos, tf_ValorPag, tf_ParcelasRes, tf_Situacao;
    private JFormattedTextField DataEmissao;
    private MaskFormatter mascaraDataEmissao;
    private JButton bt_PesquisaNota, bt_PesquisaVenda, bt_Pagar, bt_Cancelar, bt_Ok, bt_Pesquisa;
    private final DefaultTableModel Modelo;
    private JRadioButton Novo, Alterar, Gerar;
    private int INTNovo = 0, INTAlterar = 0, INTGerar = 0;

    public Pagamentos() {
        super("Pagamentos");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(10, 10, 485, 385);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("Pagamentos"));
        TrataEventos trata = new TrataEventos();
        Manipula manuseia = new Manipula();
        TrataCores cores = new TrataCores();
        LerArquivoVendas();
        LerArquivoItemVenda();
        LerArquivoCliente();

        JLabel lb_Codigo = new JLabel("Codigo");
        lb_Codigo.setBounds(20, 40, 80, 14);
        lb_Codigo.setFont(fonte);
        Painel1.add(lb_Codigo);

        tf_Codigo = new JTextField();
        tf_Codigo.setBounds(20, 58, 80, 20);
        int codigoint = lista1.size();
        String codigoString = Integer.toString(codigoint + 1);
        tf_Codigo.setText(codigoString);
        Painel1.add(tf_Codigo);
        tf_Codigo.setDocument(new MeuDocument());
        tf_Codigo.addFocusListener(cores);
        tf_Codigo.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Codigo();
            }
        });

        bt_PesquisaNota = new JButton("...");
        bt_PesquisaNota.setBounds(104, 56, 31, 24);
        Painel1.add(bt_PesquisaNota);
        bt_PesquisaNota.addActionListener(trata);

        JLabel lb_CodVenda = new JLabel("Codigo Venda");
        lb_CodVenda.setBounds(140, 40, 80, 14);
        lb_CodVenda.setFont(fonte);
        Painel1.add(lb_CodVenda);

        tf_CodigoVenda = new JTextField();
        tf_CodigoVenda.setBounds(140, 58, 80, 20);
        Painel1.add(tf_CodigoVenda);
        tf_CodigoVenda.addFocusListener(cores);
        tf_CodigoVenda.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Recuperar();
            }
        });

        bt_PesquisaVenda = new JButton("...");
        bt_PesquisaVenda.setBounds(225, 56, 31, 24);
        Painel1.add(bt_PesquisaVenda);
        bt_PesquisaVenda.addActionListener(trata);

        JLabel lb_NomeCliente = new JLabel("Cliente");
        lb_NomeCliente.setBounds(262, 40, 80, 14);
        lb_NomeCliente.setFont(fonte);
        Painel1.add(lb_NomeCliente);

        tf_NomeCliente = new JTextField();
        tf_NomeCliente.setBounds(262, 58, 194, 20);
        tf_NomeCliente.setEditable(false);
        tf_NomeCliente.setBackground(cor);
        Painel1.add(tf_NomeCliente);
        tf_NomeCliente.addFocusListener(cores);

        JLabel lb_DataEmissao = new JLabel("Emissão");
        lb_DataEmissao.setBounds(20, 78, 90, 14);
        lb_DataEmissao.setFont(fonte);
        Painel1.add(lb_DataEmissao);

        try {
            mascaraDataEmissao = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
        }
        DataEmissao = new JFormattedTextField(mascaraDataEmissao);
        DataEmissao.setBounds(20, 94, 110, 20);
        Painel1.add(DataEmissao);
        DataEmissao.setEditable(false);
        DataEmissao.setBackground(cor);
        DataEmissao.addFocusListener(cores);

        JLabel lb_CondPagto = new JLabel("Cond. Pagto.");
        lb_CondPagto.setBounds(140, 78, 80, 14);
        lb_CondPagto.setFont(fonte);
        Painel1.add(lb_CondPagto);

        tf_CondPagto = new JTextField();
        tf_CondPagto.setBounds(140, 94, 110, 20);
        Painel1.add(tf_CondPagto);
        tf_CondPagto.setEditable(false);
        tf_CondPagto.setBackground(cor);
        tf_CondPagto.addFocusListener(cores);

        JLabel lb_Descontos = new JLabel("Descontos");
        lb_Descontos.setBounds(262, 78, 80, 14);
        lb_Descontos.setFont(fonte);
        Painel1.add(lb_Descontos);

        tf_Descontos = new JTextField();
        tf_Descontos.setBounds(262, 94, 120, 20);
        Painel1.add(tf_Descontos);
        tf_Descontos.setEditable(false);
        tf_Descontos.setBackground(cor);
        tf_Descontos.addFocusListener(cores);

        JLabel lb_Total = new JLabel("Total");
        lb_Total.setBounds(20, 114, 80, 14);
        lb_Total.setFont(fonte);
        Painel1.add(lb_Total);

        tf_Total = new JTextField();
        tf_Total.setEditable(false);
        tf_Total.setBackground(cor);
        tf_Total.setBounds(20, 130, 110, 20);
        Painel1.add(tf_Total);
        tf_Total.addFocusListener(cores);

        JLabel lb_Parcelas = new JLabel("Valor Parcelas");
        lb_Parcelas.setBounds(140, 114, 110, 14);
        lb_Parcelas.setFont(fonte);
        Painel1.add(lb_Parcelas);

        tf_Parcelas = new JTextField();
        tf_Parcelas.setBounds(140, 130, 110, 20);
        Painel1.add(tf_Parcelas);
        tf_Parcelas.setEditable(false);
        tf_Parcelas.setBackground(cor);
        tf_Parcelas.addFocusListener(cores);

        JLabel lb_Pagamentos = new JLabel("Pagamentos");
        lb_Pagamentos.setBounds(260, 114, 80, 14);
        lb_Pagamentos.setFont(fonte);
        Painel1.add(lb_Pagamentos);

        tf_Pagamentos = new JTextField();
        tf_Pagamentos.setBounds(260, 130, 120, 20);
        Painel1.add(tf_Pagamentos);
        tf_Pagamentos.addFocusListener(cores);

        bt_Pagar = new JButton("Pagar");
        bt_Pagar.setBounds(387, 128, 68, 24);
        Painel1.add(bt_Pagar);
        bt_Pagar.addActionListener(trata);

        JLabel lb_valorPag = new JLabel("Valor a ser Pago");
        lb_valorPag.setBounds(20, 150, 100, 14);
        lb_valorPag.setFont(fonte);
        Painel1.add(lb_valorPag);

        tf_ValorPag = new JTextField();
        tf_ValorPag.setBounds(20, 166, 110, 20);
        Painel1.add(tf_ValorPag);
        tf_ValorPag.setEditable(false);
        tf_ValorPag.setBackground(cor);
        tf_ValorPag.addFocusListener(cores);

        JLabel lb_ParcelasRes = new JLabel("Parcelas Restantes");
        lb_ParcelasRes.setBounds(140, 150, 115, 14);
        lb_ParcelasRes.setFont(fonte);
        Painel1.add(lb_ParcelasRes);

        tf_ParcelasRes = new JTextField();
        tf_ParcelasRes.setBounds(140, 166, 110, 20);
        Painel1.add(tf_ParcelasRes);
        tf_ParcelasRes.setEditable(false);
        tf_ParcelasRes.setBackground(cor);
        tf_ParcelasRes.addFocusListener(cores);

        JLabel lb_Situacao = new JLabel("Situação");
        lb_Situacao.setBounds(260, 150, 115, 14);
        lb_Situacao.setFont(fonte);
        Painel1.add(lb_Situacao);

        tf_Situacao = new JTextField();
        tf_Situacao.setBounds(260, 166, 120, 20);
        Painel1.add(tf_Situacao);
        tf_Situacao.setEditable(false);
        tf_Situacao.setBackground(cor);
        tf_Situacao.addFocusListener(cores);

        Modelo = new DefaultTableModel();
        JTable Tabela = new JTable(Modelo);//Constrói a tabela
        Modelo.addColumn("Codigo");//Cria colunas
        Modelo.addColumn("Produto");
        Modelo.addColumn("Descrição");
        Modelo.addColumn("Modelo");
        Modelo.addColumn("Total");
        JScrollPane rolagem = new JScrollPane(Tabela);
        rolagem.setBounds(5, 200, 474, 178);
        Painel1.add(rolagem);

        bt_Pesquisa = new JButton("Pesquisar");
        bt_Pesquisa.setBounds(520, 35, 100, 26);
        tela.add(bt_Pesquisa);
        bt_Pesquisa.addActionListener(trata);

        Novo = new JRadioButton("Novo");
        Novo.setBounds(520, 80, 80, 20);
        Novo.setFont(fonte);
        tela.add(Novo);
        Novo.addItemListener(manuseia);

        Alterar = new JRadioButton("Alterar");
        Alterar.setBounds(520, 100, 80, 20);
        Alterar.setFont(fonte);
        tela.add(Alterar);
        Alterar.addItemListener(manuseia);

        Gerar = new JRadioButton("Gerar");
        Gerar.setBounds(520, 120, 80, 20);
        Gerar.setFont(fonte);
        tela.add(Gerar);
        Gerar.addItemListener(manuseia);

        bt_Ok = new JButton("OK");
        bt_Ok.setBounds(520, 160, 100, 26);
        tela.add(bt_Ok);
        bt_Ok.addActionListener(trata);

        bt_Cancelar = new JButton("Cancelar");
        bt_Cancelar.setBounds(520, 200, 100, 26);
        tela.add(bt_Cancelar);
        bt_Cancelar.addActionListener(trata);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(Painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        Painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        LerArquivo();
        setSize(700, 430);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    private void RecuperarPagamentos() {
        int INTTexto = 0;
        if (lista1.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Pagamentos Realizados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean deuErro;
            do {
                try {
                    deuErro = false;
                    String StringTexto = JOptionPane.showInputDialog(null, "Insira codigo: ", "Pagamento", JOptionPane.WARNING_MESSAGE);
                    INTTexto = Integer.parseInt(StringTexto);//Converção String para Integer
                } catch (NumberFormatException e) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Entre com o numero", "Valor Invalido", JOptionPane.ERROR_MESSAGE);
                }
            } while (deuErro);
            boolean encontrado = false;
            for (int i = 0; i < lista1.size(); i++) {
                ArrayPagamentos aux = lista1.get(i);
                if (INTTexto == aux.getCodigo()) {//Não importa se texto digitado esta em maiusculo ou minusculo
                    int TextoINTCodigo = aux.getCodigo();
                    String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                    tf_Codigo.setText(TextoCodigo);
                    int TextoINTCodigoVenda = aux.getCodigoVen();
                    String TextoCodigoVenda = Integer.toString(TextoINTCodigoVenda);//Conversão int para String
                    tf_CodigoVenda.setText(TextoCodigoVenda);
                    String TextoValorPago = aux.getValorPago();
                    tf_ValorPag.setText(TextoValorPago);
                    String TextoParcelasRes = aux.getParcelasRes();
                    tf_ParcelasRes.setText(TextoParcelasRes);
                    String TextoSituacao = aux.getSituacao();
                    tf_Situacao.setText(TextoSituacao);
                    Recuperar();
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Pagamento Encontrado e Recuperado da Lista", "Pagamento", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Pagamento não encontrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Recuperar() {
        if (!"".equals(DataEmissao.getText())) {
            String Texto = tf_Codigo.getText();
            Cancelar();
            tf_Codigo.setText(Texto);
            RecuperarVendas();
        } else {
            RecuperarVendas();
        }
    }

    private void RecuperarCliente() {
        String TextoCodClie = "";
        if (lista4.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Clientes cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto1 = tf_CodigoVenda.getText();
            int n1 = Integer.parseInt(Texto1);//Conversão String para Integer
            for (int i = 0; i < lista2.size(); i++) {
                ArrayNovaVenda aux = lista2.get(i);
                if (n1 == (aux.getCodigo())) {
                    int TextoINTCodClie = aux.getCodigoCliente();
                    TextoCodClie = Integer.toString(TextoINTCodClie);//Conversão int para String
                }
            }
            String Texto2 = TextoCodClie;
            int n2 = Integer.parseInt(Texto2);//Conversão String para Integer
            for (int i = 0; i < lista4.size(); i++) {
                ArrayCliente aux = lista4.get(i);
                if (n2 == (aux.getCodigo())) {
                    String TextoNome = aux.getNome();
                    tf_NomeCliente.setText(TextoNome);
                }
            }
        }
    }

    private void RecuperarItemVendas() {
        String CodigoString = tf_CodigoVenda.getText();
        int CodigoInt = Integer.parseInt(CodigoString);
        for (int i = 0; i < lista3.size(); i++) {
            ArrayItenVenda n = lista3.get(i);
            if (n.getCodIten() == n.getCodVenda()) {
                if (CodigoInt == n.getCodVenda()) {
                    Modelo.addRow(new Object[]{n.getCodProduto(), n.getNome(), n.getDescricao(), n.getModelo(), n.getTotal()});//Adiciona linha na tabela
                }
            }
        }
    }

    private void RecuperarVendas() {
        if (!"".equals(tf_CodigoVenda.getText())) {
            if (lista2.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "Não há Vendas cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                String Texto = tf_CodigoVenda.getText();
                int n = Integer.parseInt(Texto);//Conversão String para Integer
                for (int i = 0; i < lista2.size(); i++) {
                    ArrayNovaVenda aux = lista2.get(i);
                    if (n == (aux.getCodigo())) {
                        String TextoDataEmissao = aux.getDataEmissao();
                        DataEmissao.setText(TextoDataEmissao);
                        String TextoCondPagto = aux.getCondPagto();
                        tf_CondPagto.setText(TextoCondPagto);
                        float TextoFLOATDescontos = aux.getDescontos();
                        String TextoDescontos = Float.toString(TextoFLOATDescontos);//Conversão float para String
                        tf_Descontos.setText(TextoDescontos);
                        float TextoFLOATTotal = aux.getTotal();
                        String TextoTotal = Float.toString(TextoFLOATTotal);//Conversão float para String
                        tf_Total.setText(TextoTotal);
                        RecuperarCliente();
                        RecuperarItemVendas();
                        ValorParcelas();
                    }
                }
            }
        }
    }

    private void Codigo() {
        if ("".equals(tf_Codigo.getText())) {
            LimparTela();
        } else {
            if (lista1.isEmpty() == true) {
            } else {
                String t = tf_Codigo.getText();
                int n = Integer.parseInt(t);
                boolean encontrado = false;
                for (int i = 0; i < lista1.size(); i++) {
                    ArrayPagamentos aux = lista1.get(i);
                    if (n == (aux.getCodigo())) {
                        encontrado = true;
                    }
                }
                if (encontrado == true) {
                    LimparTela();
                }
            }
        }
    }

    private void Ok() {
        int INTTextoCodigo = 0, INTTextoCodVendas = 0;
        if (lista1.isEmpty() == true) {
            Gravar();
        } else {
            boolean encontradoCodigo = false;
            boolean encontradoCodVenda = false;
            boolean n = false;
            for (int i = 0; i < lista1.size(); i++) {
                ArrayPagamentos aux = lista1.get(i);
                String TextoCodigo = tf_Codigo.getText();
                INTTextoCodigo = Integer.parseInt(TextoCodigo);
                String TextoCodVendas = tf_CodigoVenda.getText();
                if ("".equals(TextoCodVendas)) {
                    TextoCodVendas = "0";
                }
                INTTextoCodVendas = Integer.parseInt(TextoCodVendas);
                if (INTTextoCodigo == aux.getCodigo()) {
                    encontradoCodigo = true;
                }
                if (INTTextoCodVendas == aux.getCodigoVen()) {
                    encontradoCodVenda = true;
                }
            }
            if (encontradoCodVenda == true) {
                if (encontradoCodigo == true) {
                    int respostaCodigo = JOptionPane.showConfirmDialog(null, "O Pagamentos " + tf_Codigo.getText() + " ja realizado deseja realizar novamente?", "Pagamentos", JOptionPane.YES_NO_OPTION);
                    if (respostaCodigo == 0) {
                        for (int i = 0; i < lista1.size(); i++) {
                            ArrayPagamentos aux = lista1.get(i);
                            if (INTTextoCodigo == aux.getCodigo()) {
                                lista1.remove(aux);
                            }
                        }
                        Gravar();
                        n = true;
                    }
                }
                if (n == false) {
                    int respostaCodVenda = JOptionPane.showConfirmDialog(null, "O Pagamentos da venda" + tf_CodigoVenda.getText() + " ja realizado e não podera ser subbstituido", "Pagamentos", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                Gravar();
            }
        }
    }

    private void Gravar() {
        String textocod, textoven, InserValorPago = "", InserParcelasRes = "", InserSituacao = "";
        int InserCodigo = 0, InserVen = 0;

        if (!"".equals(tf_Codigo.getText())) {//Se campo não esta vazio
            textocod = tf_Codigo.getText();
            InserCodigo = Integer.parseInt(textocod);//Conversão de String para int
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Codigo.setText("");//Limpa o campo
            tf_Codigo.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_CodigoVenda.getText())) {//Se campo não esta vazio
            textoven = tf_CodigoVenda.getText();
            InserVen = Integer.parseInt(textoven);//Conversão String para int
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Codigo Venda esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_CodigoVenda.setText("");//Limpa o campo
            tf_CodigoVenda.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_ValorPag.getText())) {//Se campo não esta vazio
            InserValorPago = tf_ValorPag.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Valor a ser Pago esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_ValorPag.setText("");//Limpa o campo
            tf_ValorPag.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_ParcelasRes.getText())) {//Se campo não esta vazio
            InserParcelasRes = tf_ParcelasRes.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Parcelas Restantes esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_ParcelasRes.setText("");//Limpa o campo
            tf_ParcelasRes.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Situacao.getText())) {//Se campo não esta vazio
            InserSituacao = tf_Situacao.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Situação esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Situacao.setText("");//Limpa o campo
            tf_Situacao.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Codigo.getText()) & !"".equals(tf_CodigoVenda.getText()) & !"".equals(tf_ValorPag.getText()) & !"".equals(tf_ParcelasRes.getText()) & !"".equals(tf_Situacao.getText())) {
            ArrayPagamentos aux = new ArrayPagamentos(InserValorPago, InserParcelasRes, InserSituacao, InserCodigo, InserVen);
            lista1.add(aux);
            JOptionPane.showMessageDialog(null, "Número de Pagamentos Realizados: " + lista1.size(), "Entrada de Dados", JOptionPane.WARNING_MESSAGE);
            LimparTela();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel executar operação no sistema", "Erro Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Cancelar() {
        tf_NomeCliente.setText("");
        DataEmissao.setText("");
        tf_CondPagto.setText("");
        tf_Descontos.setText("");
        tf_Total.setText("");
        if (Modelo.getRowCount() == 0) {//Verifica se há linhas na tabela
        } else {
            if (Modelo.getRowCount() >= 1) {
                for (int i = 0; i <= Modelo.getRowCount(); i++) {//Contador
                    Modelo.setNumRows(0);//Exclui Todas as linhas
                }
            }
        }
    }

    private void LimparTela() {
        int codigoint = lista1.size();
        String codigoString = Integer.toString(codigoint + 1);
        tf_Codigo.setText(codigoString);
        tf_CodigoVenda.setText("");
        tf_Parcelas.setText("");
        tf_Pagamentos.setText("");
        tf_ValorPag.setText("");
        tf_ParcelasRes.setText("");
        tf_Situacao.setText("");
        Novo.setSelected(false);
        Alterar.setSelected(false);
        Gerar.setSelected(false);
        INTNovo = 0;
        INTAlterar = 0;
        INTGerar = 0;
        Cancelar();
    }

    private void ValorParcelas() {
        if ("1X".equals(tf_CondPagto.getText())) {
            String Texto1 = tf_Total.getText();
            float FLOATTexto1 = Float.parseFloat(Texto1);//Conversão String para float
            float Resultado1 = FLOATTexto1 / 1;
            String RecebeResult = Float.toString(Resultado1);//Conversão float para String
            tf_Parcelas.setText(RecebeResult);
        }
        if ("2X".equals(tf_CondPagto.getText())) {
            String Texto1 = tf_Total.getText();
            float FLOATTexto1 = Float.parseFloat(Texto1);//Conversão String para float
            float Resultado1 = FLOATTexto1 / 2;
            String RecebeResult = Float.toString(Resultado1);//Conversão float para String
            tf_Parcelas.setText(RecebeResult);
        }
        if ("3X".equals(tf_CondPagto.getText())) {
            String Texto1 = tf_Total.getText();
            float FLOATTexto1 = Float.parseFloat(Texto1);//Conversão String para float
            float Resultado1 = FLOATTexto1 / 3;
            String RecebeResult = Float.toString(Resultado1);//Conversão float para String
            tf_Parcelas.setText(RecebeResult);
        }
        if ("4X".equals(tf_CondPagto.getText())) {
            String Texto1 = tf_Total.getText();
            float FLOATTexto1 = Float.parseFloat(Texto1);//Conversão String para float
            float Resultado1 = FLOATTexto1 / 4;
            String RecebeResult = Float.toString(Resultado1);//Conversão float para String
            tf_Parcelas.setText(RecebeResult);
        }
        if ("5X".equals(tf_CondPagto.getText())) {
            String Texto1 = tf_Total.getText();
            float FLOATTexto1 = Float.parseFloat(Texto1);//Conversão String para float
            float Resultado1 = FLOATTexto1 / 5;
            String RecebeResult = Float.toString(Resultado1);//Conversão float para String
            tf_Parcelas.setText(RecebeResult);
        }
        if ("6X".equals(tf_CondPagto.getText())) {
            String Texto1 = tf_Total.getText();
            float FLOATTexto1 = Float.parseFloat(Texto1);//Conversão String para float
            float Resultado1 = FLOATTexto1 / 6;
            String RecebeResult = Float.toString(Resultado1);//Conversão float para String
            tf_Parcelas.setText(RecebeResult);
        }
        if ("7X".equals(tf_CondPagto.getText())) {
            String Texto1 = tf_Total.getText();
            float FLOATTexto1 = Float.parseFloat(Texto1);//Conversão String para float
            float Resultado1 = FLOATTexto1 / 7;
            String RecebeResult = Float.toString(Resultado1);//Conversão float para String
            tf_Parcelas.setText(RecebeResult);
        }
        if ("8X".equals(tf_CondPagto.getText())) {
            String Texto1 = tf_Total.getText();
            float FLOATTexto1 = Float.parseFloat(Texto1);//Conversão String para float
            float Resultado1 = FLOATTexto1 / 8;
            String RecebeResult = Float.toString(Resultado1);//Conversão float para String
            tf_Parcelas.setText(RecebeResult);
        }
    }

    private void Pagar() {
        if ("".equals(tf_Pagamentos.getText())) {
            JOptionPane.showMessageDialog(null, "Campo Pagamento esta em branco", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            if (tf_Parcelas.getText().equals(tf_Pagamentos.getText())) {
                if ("".equals(tf_ValorPag.getText())) {
                    String TextoPag = tf_Pagamentos.getText();
                    float FLOATTextoPag = Float.parseFloat(TextoPag);//Conversão de String para float
                    String TextoTot = tf_Total.getText();
                    float FLOATTextoTot = Float.parseFloat(TextoTot);//Conversão de String para float
                    float Resultado = FLOATTextoTot - FLOATTextoPag;
                    String TextoResult = Float.toString(Resultado);//Converssao de float para String
                    tf_ValorPag.setText(TextoResult);

                    String ValorPag = tf_ValorPag.getText();
                    float FloatValorPag = Float.parseFloat(ValorPag);
                    if (FloatValorPag < 1) {
                        tf_Situacao.setText("Pago");
                        tf_ValorPag.setText("0");
                    } else {
                        tf_Situacao.setText("Aberto");
                    }
                    ParcelasRestantes();
                } else {
                    String TextoPag = tf_Pagamentos.getText();
                    float FLOATTextoPag = Float.parseFloat(TextoPag);//Conversão de String para float
                    String TextoValP = tf_ValorPag.getText();
                    float FLOATTextoTot = Float.parseFloat(TextoValP);//Conversão de String para float
                    float Resultado = FLOATTextoTot - FLOATTextoPag;
                    String TextoResult = Float.toString(Resultado);//Converssao de float para String
                    tf_ValorPag.setText(TextoResult);
                    String ValorPag = tf_ValorPag.getText();
                    float FloatValorPag = Float.parseFloat(ValorPag);
                    if (FloatValorPag < 1) {
                        tf_Situacao.setText("Pago");
                        tf_ValorPag.setText("0");
                    } else {
                        tf_Situacao.setText("Aberto");
                    }
                    ParcelasRestantes();
                }
            }
        }
    }

    private void ParcelasRestantes() {
        if ("".equals(tf_ParcelasRes.getText())) {
            if ("1X".equals(tf_CondPagto.getText())) {
                tf_ParcelasRes.setText("0");
            }
            if ("2X".equals(tf_CondPagto.getText())) {
                tf_ParcelasRes.setText("1");
            }
            if ("3X".equals(tf_CondPagto.getText())) {
                tf_ParcelasRes.setText("2");
            }
            if ("4X".equals(tf_CondPagto.getText())) {
                tf_ParcelasRes.setText("3");
            }
            if ("5X".equals(tf_CondPagto.getText())) {
                tf_ParcelasRes.setText("4");
            }
            if ("6X".equals(tf_CondPagto.getText())) {
                tf_ParcelasRes.setText("5");
            }
            if ("7X".equals(tf_CondPagto.getText())) {
                tf_ParcelasRes.setText("6");
            }
            if ("8X".equals(tf_CondPagto.getText())) {
                tf_ParcelasRes.setText("7");
            }
        } else {
            if ("1".equals(tf_ParcelasRes.getText())) {
                tf_ParcelasRes.setText("0");
            }
            if ("2".equals(tf_ParcelasRes.getText())) {
                tf_ParcelasRes.setText("1");
            }
            if ("3".equals(tf_ParcelasRes.getText())) {
                tf_ParcelasRes.setText("2");
            }
            if ("4".equals(tf_ParcelasRes.getText())) {
                tf_ParcelasRes.setText("3");
            }
            if ("5".equals(tf_ParcelasRes.getText())) {
                tf_ParcelasRes.setText("4");
            }
            if ("6".equals(tf_ParcelasRes.getText())) {
                tf_ParcelasRes.setText("5");
            }
            if ("7".equals(tf_ParcelasRes.getText())) {
                tf_ParcelasRes.setText("6");
            }
            if ("8".equals(tf_ParcelasRes.getText())) {
                tf_ParcelasRes.setText("7");
            }
        }
    }

    private void PesquisaVendas() {
        if (lista2.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Vendas cadastradas na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            ConsultaVenda aux = new ConsultaVenda();
        }
    }

    private void Arquivo() {
        try {
            FileOutputStream arqDados = new FileOutputStream("Pagamentos.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista1);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Pagamentos.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista1 = (ArrayList<ArrayPagamentos>) entra.readObject();
            entra.close();
            int codigoint = lista1.size();
            String codigoString = Integer.toString(codigoint + 1);
            tf_Codigo.setText(codigoString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoVendas() {
        try {
            FileInputStream Arq = new FileInputStream("Vendas.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista2 = (ArrayList<ArrayNovaVenda>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoItemVenda() {
        try {
            FileInputStream Arq = new FileInputStream("ItemVendas.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista3 = (ArrayList<ArrayItenVenda>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoCliente() {
        try {
            FileInputStream Arq = new FileInputStream("Cliente.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista4 = (ArrayList<ArrayCliente>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Manipula implements ItemListener {

        public void itemStateChanged(ItemEvent evento) {
            if (evento.getSource() == Novo) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    INTNovo = 1;
                    INTAlterar = 0;
                    INTGerar = 0;
                    Alterar.setSelected(false);
                    Gerar.setSelected(false);
                }
            }
            if (evento.getSource() == Alterar) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    INTNovo = 0;
                    INTAlterar = 1;
                    INTGerar = 0;
                    Novo.setSelected(false);
                    Gerar.setSelected(false);
                }
            }
            if (evento.getSource() == Gerar) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    INTNovo = 0;
                    INTAlterar = 0;
                    INTGerar = 1;
                    Novo.setSelected(false);
                    Alterar.setSelected(false);
                }
            }
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == bt_Pesquisa) {
                //Quem();
            }
            if (evento.getSource() == bt_Ok) {
                if (Novo.isSelected() == false & Alterar.isSelected() == false & Gerar.isSelected() == false) {
                    JOptionPane.showMessageDialog(null, "Selecione uma opção", "Fornecedor", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (Novo.isSelected() == true) {//Verifica se JRadioButton esta ou não selecionado
                        Ok();
                        Arquivo();
                        INTNovo = 0;
                        INTAlterar = 0;
                        INTGerar = 0;
                    }
                    if (Alterar.isSelected() == true) {
                        RecuperarPagamentos();
                        INTNovo = 0;
                        INTAlterar = 0;
                        INTGerar = 0;
                    }
                    if (Gerar.isSelected() == true) {
                        //Excluir();
                        //Arquivo();
                        INTNovo = 0;
                        INTAlterar = 0;
                        INTGerar = 0;
                    }
                }
            }
            if (evento.getSource() == bt_Cancelar) {
                LimparTela();
            }
            if (evento.getSource() == bt_Pagar) {
                Pagar();
            }
            if (evento.getSource() == bt_PesquisaVenda) {
                PesquisaVendas();
            }
        }
    }

    private class TrataCores implements FocusListener {

        public void focusGained(FocusEvent evento) {//Ganha Focus
            if (evento.getSource() == tf_Codigo) {
                tf_Codigo.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CodigoVenda) {
                tf_CodigoVenda.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_NomeCliente) {
                tf_NomeCliente.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == DataEmissao) {
                DataEmissao.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_CondPagto) {
                tf_CondPagto.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Descontos) {
                tf_Descontos.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Total) {
                tf_Total.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Parcelas) {
                tf_Parcelas.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Pagamentos) {
                tf_Pagamentos.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_ValorPag) {
                tf_ValorPag.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_ParcelasRes) {
                tf_ParcelasRes.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Situacao) {
                tf_Situacao.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tf_Codigo.setBackground(Color.WHITE);
            tf_CodigoVenda.setBackground(Color.WHITE);
            tf_NomeCliente.setBackground(Color.WHITE);
            DataEmissao.setBackground(Color.WHITE);
            tf_CondPagto.setBackground(Color.WHITE);
            tf_Descontos.setBackground(Color.WHITE);
            tf_Total.setBackground(Color.WHITE);
            tf_Parcelas.setBackground(Color.WHITE);
            tf_Pagamentos.setBackground(Color.WHITE);
            tf_ValorPag.setBackground(Color.WHITE);
            tf_ParcelasRes.setBackground(Color.WHITE);
            tf_Situacao.setBackground(Color.WHITE);
        }
    }
}


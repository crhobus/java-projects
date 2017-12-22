
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.table.*;

public class ConsultaVenda extends JFrame {

    private ArrayList<ArrayNovaVenda> lista1 = new ArrayList();
    private ArrayList<ArrayVendedor> lista2 = new ArrayList();
    private ArrayList<ArrayCliente> lista3 = new ArrayList();
    private ArrayList<ArrayItenVenda> lista4 = new ArrayList();
    private ArrayList<ArrayPagamentos> lista5 = new ArrayList();
    private JTextField tf_Codigo, tf_DataEmissao, tf_CodVendedor, tf_NomeVendedor, tf_CodCliente, tf_NomeCliente, tf_Situacao, tf_FormaPagto, tf_CondPagto, tf_ParcelasRes, tf_ValorRes, tf_Descricao, tf_Endereco, tf_Numero, tf_Bairro, tf_Cidade, tf_Estado, tf_Contato, tf_SubTotal, tf_Descontos, tf_Total;
    private JButton bt_Gerar, bt_Cancelar;
    private final DefaultTableModel Modelo;

    ConsultaVenda() {
        super("Consulta Vendas");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(10, 10, 585, 650);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("Consulta Vendas"));

        JPanel Painel2 = new JPanel();
        Painel2.setLayout(null);
        Painel2.setBounds(10, 250, 564, 120);
        Painel1.add(Painel2);
        Painel2.setBorder(BorderFactory.createTitledBorder("Dados Entrega"));
        TrataEventos trata = new TrataEventos();
        TrataCores cores = new TrataCores();

        JLabel lb_Codigo = new JLabel("Codigo");
        lb_Codigo.setBounds(20, 52, 80, 14);
        lb_Codigo.setFont(fonte);
        Painel1.add(lb_Codigo);

        tf_Codigo = new JTextField();
        tf_Codigo.setBounds(68, 48, 110, 20);
        tf_Codigo.setDocument(new MeuDocument());
        Painel1.add(tf_Codigo);
        tf_Codigo.addFocusListener(cores);
        tf_Codigo.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                if ("".equals(tf_Codigo.getText())) {
                } else {
                    RecuperarVendas();
                }
            }
        });

        bt_Gerar = new JButton("Ok");
        bt_Gerar.setBounds(194, 46, 85, 23);
        Painel1.add(bt_Gerar);
        bt_Gerar.addActionListener(trata);

        bt_Cancelar = new JButton("Cancelar");
        bt_Cancelar.setBounds(304, 46, 85, 23);
        Painel1.add(bt_Cancelar);
        bt_Cancelar.addActionListener(trata);

        JLabel lb_DataEmissao = new JLabel("Data Emissão");
        lb_DataEmissao.setBounds(20, 95, 110, 14);
        lb_DataEmissao.setFont(fonte);
        Painel1.add(lb_DataEmissao);

        tf_DataEmissao = new JTextField();
        tf_DataEmissao.setBounds(20, 112, 120, 20);
        tf_DataEmissao.setEditable(false);
        tf_DataEmissao.setBackground(cor);
        Painel1.add(tf_DataEmissao);

        JLabel lb_CodVendedor = new JLabel("Vendedor");
        lb_CodVendedor.setBounds(160, 95, 110, 14);
        lb_CodVendedor.setFont(fonte);
        Painel1.add(lb_CodVendedor);

        tf_CodVendedor = new JTextField();
        tf_CodVendedor.setBounds(160, 112, 120, 20);
        tf_CodVendedor.setEditable(false);
        tf_CodVendedor.setBackground(cor);
        Painel1.add(tf_CodVendedor);

        JLabel lb_NomeVendedor = new JLabel("Nome");
        lb_NomeVendedor.setBounds(300, 95, 110, 14);
        lb_NomeVendedor.setFont(fonte);
        Painel1.add(lb_NomeVendedor);

        tf_NomeVendedor = new JTextField();
        tf_NomeVendedor.setBounds(300, 112, 120, 20);
        tf_NomeVendedor.setEditable(false);
        tf_NomeVendedor.setBackground(cor);
        Painel1.add(tf_NomeVendedor);

        JLabel lb_CodCliente = new JLabel("Cliente");
        lb_CodCliente.setBounds(20, 133, 110, 14);
        lb_CodCliente.setFont(fonte);
        Painel1.add(lb_CodCliente);

        tf_CodCliente = new JTextField();
        tf_CodCliente.setBounds(20, 150, 120, 20);
        tf_CodCliente.setEditable(false);
        tf_CodCliente.setBackground(cor);
        Painel1.add(tf_CodCliente);

        JLabel lb_NomeCliente = new JLabel("Nome");
        lb_NomeCliente.setBounds(160, 133, 110, 14);
        lb_NomeCliente.setFont(fonte);
        Painel1.add(lb_NomeCliente);

        tf_NomeCliente = new JTextField();
        tf_NomeCliente.setBounds(160, 150, 120, 20);
        tf_NomeCliente.setEditable(false);
        tf_NomeCliente.setBackground(cor);
        Painel1.add(tf_NomeCliente);

        JLabel lb_Situacao = new JLabel("Situação");
        lb_Situacao.setBounds(300, 133, 110, 14);
        lb_Situacao.setFont(fonte);
        Painel1.add(lb_Situacao);

        tf_Situacao = new JTextField();
        tf_Situacao.setBounds(300, 150, 120, 20);
        tf_Situacao.setEditable(false);
        tf_Situacao.setBackground(cor);
        Painel1.add(tf_Situacao);

        JLabel lb_FormaPagto = new JLabel("Forma Pagto");
        lb_FormaPagto.setBounds(20, 170, 110, 14);
        lb_FormaPagto.setFont(fonte);
        Painel1.add(lb_FormaPagto);

        tf_FormaPagto = new JTextField();
        tf_FormaPagto.setBounds(20, 186, 120, 20);
        tf_FormaPagto.setEditable(false);
        tf_FormaPagto.setBackground(cor);
        Painel1.add(tf_FormaPagto);

        JLabel lb_CondPagto = new JLabel("Cond Pagto");
        lb_CondPagto.setBounds(160, 170, 110, 14);
        lb_CondPagto.setFont(fonte);
        Painel1.add(lb_CondPagto);

        tf_CondPagto = new JTextField();
        tf_CondPagto.setBounds(160, 186, 120, 20);
        tf_CondPagto.setEditable(false);
        tf_CondPagto.setBackground(cor);
        Painel1.add(tf_CondPagto);

        JLabel lb_ParcelasRes = new JLabel("Parcelas Restantes");
        lb_ParcelasRes.setBounds(300, 170, 125, 14);
        lb_ParcelasRes.setFont(fonte);
        Painel1.add(lb_ParcelasRes);

        tf_ParcelasRes = new JTextField();
        tf_ParcelasRes.setBounds(300, 186, 120, 20);
        tf_ParcelasRes.setEditable(false);
        tf_ParcelasRes.setBackground(cor);
        Painel1.add(tf_ParcelasRes);

        JLabel lb_ValorRes = new JLabel("Total Restante");
        lb_ValorRes.setBounds(20, 205, 125, 14);
        lb_ValorRes.setFont(fonte);
        Painel1.add(lb_ValorRes);

        tf_ValorRes = new JTextField();
        tf_ValorRes.setBounds(20, 220, 120, 20);
        tf_ValorRes.setEditable(false);
        tf_ValorRes.setBackground(cor);
        Painel1.add(tf_ValorRes);

        JLabel lb_Descricao = new JLabel("Descrição");
        lb_Descricao.setBounds(160, 205, 110, 14);
        lb_Descricao.setFont(fonte);
        Painel1.add(lb_Descricao);

        tf_Descricao = new JTextField();
        tf_Descricao.setBounds(160, 220, 260, 20);
        tf_Descricao.setEditable(false);
        tf_Descricao.setBackground(cor);
        Painel1.add(tf_Descricao);

        JLabel lb_Endereco = new JLabel("Endereco");
        lb_Endereco.setBounds(20, 30, 110, 14);
        lb_Endereco.setFont(fonte);
        Painel2.add(lb_Endereco);

        tf_Endereco = new JTextField();
        tf_Endereco.setBounds(20, 45, 120, 20);
        tf_Endereco.setEditable(false);
        tf_Endereco.setBackground(cor);
        Painel2.add(tf_Endereco);

        JLabel lb_Numero = new JLabel("Numero");
        lb_Numero.setBounds(160, 30, 110, 14);
        lb_Numero.setFont(fonte);
        Painel2.add(lb_Numero);

        tf_Numero = new JTextField();
        tf_Numero.setBounds(160, 45, 120, 20);
        tf_Numero.setEditable(false);
        tf_Numero.setBackground(cor);
        Painel2.add(tf_Numero);

        JLabel lb_Bairro = new JLabel("Bairro");
        lb_Bairro.setBounds(300, 30, 110, 14);
        lb_Bairro.setFont(fonte);
        Painel2.add(lb_Bairro);

        tf_Bairro = new JTextField();
        tf_Bairro.setBounds(300, 45, 120, 20);
        tf_Bairro.setEditable(false);
        tf_Bairro.setBackground(cor);
        Painel2.add(tf_Bairro);

        JLabel lb_Cidade = new JLabel("Cidade");
        lb_Cidade.setBounds(20, 66, 110, 14);
        lb_Cidade.setFont(fonte);
        Painel2.add(lb_Cidade);

        tf_Cidade = new JTextField();
        tf_Cidade.setBounds(20, 81, 120, 20);
        tf_Cidade.setEditable(false);
        tf_Cidade.setBackground(cor);
        Painel2.add(tf_Cidade);

        JLabel lb_Estado = new JLabel("Estado");
        lb_Estado.setBounds(160, 66, 110, 14);
        lb_Estado.setFont(fonte);
        Painel2.add(lb_Estado);

        tf_Estado = new JTextField();
        tf_Estado.setBounds(160, 81, 120, 20);
        tf_Estado.setEditable(false);
        tf_Estado.setBackground(cor);
        Painel2.add(tf_Estado);

        JLabel lb_Contato = new JLabel("Contato");
        lb_Contato.setBounds(300, 66, 110, 14);
        lb_Contato.setFont(fonte);
        Painel2.add(lb_Contato);

        tf_Contato = new JTextField();
        tf_Contato.setBounds(300, 81, 120, 20);
        tf_Contato.setEditable(false);
        tf_Contato.setBackground(cor);
        Painel2.add(tf_Contato);

        Modelo = new DefaultTableModel();
        JTable Tabela = new JTable(Modelo);//Constrói a tabela
        Modelo.addColumn("Codigo");//Cria colunas
        Modelo.addColumn("Produto");
        Modelo.addColumn("Descrição");
        Modelo.addColumn("Modelo");
        Modelo.addColumn("Total");
        JScrollPane rolagem = new JScrollPane(Tabela);
        rolagem.setBounds(10, 377, 560, 178);
        Painel1.add(rolagem);

        JLabel lb_SubTotal = new JLabel("Sub Total R$");
        lb_SubTotal.setBounds(394, 567, 125, 14);
        lb_SubTotal.setFont(fonte);
        Painel1.add(lb_SubTotal);

        tf_SubTotal = new JTextField();
        tf_SubTotal.setBounds(470, 565, 100, 20);
        tf_SubTotal.setEditable(false);
        tf_SubTotal.setBackground(cor);
        Painel1.add(tf_SubTotal);

        JLabel lb_Descontos = new JLabel("Descontos %");
        lb_Descontos.setBounds(394, 593, 125, 14);
        lb_Descontos.setFont(fonte);
        Painel1.add(lb_Descontos);

        tf_Descontos = new JTextField();
        tf_Descontos.setBounds(470, 591, 100, 20);
        tf_Descontos.setEditable(false);
        tf_Descontos.setBackground(cor);
        Painel1.add(tf_Descontos);

        JLabel lb_Total = new JLabel("Total R$:");
        lb_Total.setBounds(394, 619, 125, 14);
        lb_Total.setFont(fonte);
        Painel1.add(lb_Total);

        tf_Total = new JTextField();
        tf_Total.setBounds(470, 617, 100, 20);
        tf_Total.setEditable(false);
        tf_Total.setBackground(cor);
        Painel1.add(tf_Total);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(Painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        Painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        LerArquivoVendas();
        LerArquivoCliente();
        LerArquivoVendedor();
        LerArquivoItemVenda();
        LerArquivoPagamento();
        setSize(610, 700);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    private void RecuperarCliente() {
        if (lista3.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Clientes cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = tf_CodCliente.getText();
            int n = Integer.parseInt(Texto);//Conversão String para Integer
            for (int i = 0; i < lista3.size(); i++) {
                ArrayCliente aux = lista3.get(i);
                if (n == (aux.getCodigo())) {
                    String TextoNome = aux.getNome();
                    tf_NomeCliente.setText(TextoNome);
                }
            }
        }
    }

    private void RecuperarVendedor() {
        if (lista2.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Vendedores cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = tf_CodVendedor.getText();
            int n = Integer.parseInt(Texto);//Conversão String para Integer
            for (int i = 0; i < lista2.size(); i++) {
                ArrayVendedor aux = lista2.get(i);
                if (n == (aux.getCodigo())) {
                    String TextoNome = aux.getNome();
                    tf_NomeVendedor.setText(TextoNome);
                }
            }
        }
    }

    private void RecuperarItemVendas() {
        String CodigoString = tf_Codigo.getText();
        int CodigoInt = Integer.parseInt(CodigoString);
        for (int i = 0; i < lista4.size(); i++) {
            ArrayItenVenda n = lista4.get(i);
            if (n.getCodIten() == n.getCodVenda()) {
                if (CodigoInt == n.getCodVenda()) {
                    Modelo.addRow(new Object[]{n.getCodProduto(), n.getNome(), n.getDescricao(), n.getModelo(), n.getTotal()});//Adiciona linha na tabela
                }
            }
        }
    }

    private void RecuperaPagamento() {
        if (!"".equals(tf_Codigo.getText())) {
            if (lista5.isEmpty() == true) {
            } else {
                String Texto = tf_Codigo.getText();
                int n = Integer.parseInt(Texto);//Conversão String para Integer
                for (int i = 0; i < lista5.size(); i++) {
                    ArrayPagamentos aux = lista5.get(i);
                    if (n == (aux.getCodigoVen())) {
                        String TextoParcelasRes = aux.getParcelasRes();
                        tf_ParcelasRes.setText(TextoParcelasRes);
                        String TextoValorPago = aux.getValorPago();
                        tf_ValorRes.setText(TextoValorPago);
                        String TextoSituacao = aux.getSituacao();
                        tf_Situacao.setText(TextoSituacao);
                    }
                }
            }
        }
    }

    private void OK() {
        if (!"".equals(tf_DataEmissao.getText())) {
            String Texto = tf_Codigo.getText();
            Cancelar();
            tf_Codigo.setText(Texto);
            RecuperarVendas();
        } else {
            RecuperarVendas();
        }
    }

    private void RecuperarVendas() {
        if ("".equals(tf_Codigo.getText())) {
            JOptionPane.showMessageDialog(null, "Campo Código esta em branco", "Consulta Vendas", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!"".equals(tf_Codigo.getText())) {
                if (lista1.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Não há Vendas cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    String Texto = tf_Codigo.getText();
                    int n = Integer.parseInt(Texto);//Conversão String para Integer
                    for (int i = 0; i < lista1.size(); i++) {
                        ArrayNovaVenda aux = lista1.get(i);
                        if (n == (aux.getCodigo())) {
                            String TextoDataEmissao = aux.getDataEmissao();
                            tf_DataEmissao.setText(TextoDataEmissao);
                            int TextoINTCodVendedor = aux.getCodigoVendedor();
                            String TextoCodVendedor = Integer.toString(TextoINTCodVendedor);//Conversão int para String
                            tf_CodVendedor.setText(TextoCodVendedor);
                            int TextoINTCodClie = aux.getCodigoCliente();
                            String TextoCodClie = Integer.toString(TextoINTCodClie);//Conversão int para String
                            tf_CodCliente.setText(TextoCodClie);
                            String TextoSituacao = aux.getSituacao();
                            tf_Situacao.setText(TextoSituacao);
                            String TextoFormaPagto = aux.getFormaPagto();
                            tf_FormaPagto.setText(TextoFormaPagto);
                            String TextoCondPagto = aux.getCondPagto();
                            tf_CondPagto.setText(TextoCondPagto);
                            String TextoDescricao = aux.getDescricao();
                            tf_Descricao.setText(TextoDescricao);
                            String TextoEndereco = aux.getEndedrecoEnt();
                            tf_Endereco.setText(TextoEndereco);
                            int IntNumero = aux.getNumeroEnt();
                            String TextoNumero = Integer.toString(IntNumero);//Converssão int para String
                            tf_Numero.setText(TextoNumero);
                            String TextoBairro = aux.getBairroEnt();
                            tf_Bairro.setText(TextoBairro);
                            String TextoCidade = aux.getCidadeEnt();
                            tf_Cidade.setText(TextoCidade);
                            String TextoEstado = aux.getEstadoEnt();
                            tf_Estado.setText(TextoEstado);
                            String TextoContato = aux.getContatoEnt();
                            tf_Contato.setText(TextoContato);
                            float TextoFLOATSubTotal = aux.getSubTotal();
                            String TextoSubTotal = Float.toString(TextoFLOATSubTotal);//Conversão float para String
                            tf_SubTotal.setText(TextoSubTotal);
                            float TextoFLOATDescontos = aux.getDescontos();
                            String TextoDescontos = Float.toString(TextoFLOATDescontos);//Conversão float para String
                            tf_Descontos.setText(TextoDescontos);
                            float TextoFLOATTotal = aux.getTotal();
                            String TextoTotal = Float.toString(TextoFLOATTotal);//Conversão float para String
                            tf_Total.setText(TextoTotal);
                            RecuperarCliente();
                            RecuperarVendedor();
                            RecuperarItemVendas();
                            RecuperaPagamento();
                        }
                    }
                }
            }
        }
    }

    private void Cancelar() {
        tf_Codigo.setText("");
        tf_DataEmissao.setText("");
        tf_CodVendedor.setText("");
        tf_NomeVendedor.setText("");
        tf_CodCliente.setText("");
        tf_NomeCliente.setText("");
        tf_Situacao.setText("");
        tf_FormaPagto.setText("");
        tf_CondPagto.setText("");
        tf_ParcelasRes.setText("");
        tf_ValorRes.setText("");
        tf_Descricao.setText("");
        tf_Endereco.setText("");
        tf_Numero.setText("");
        tf_Bairro.setText("");
        tf_Cidade.setText("");
        tf_Estado.setText("");
        tf_Contato.setText("");
        tf_SubTotal.setText("");
        tf_Descontos.setText("");
        tf_Total.setText("");
        if (Modelo.getRowCount() != 0) {//Verifica se há linhas na tabela
            for (int i = 0; i < Modelo.getRowCount(); i++) {//Contador
                Modelo.removeRow(i);//Exclui linas
                i--;
            }
        }
    }

    private void LerArquivoCliente() {
        try {
            FileInputStream Arq = new FileInputStream("Cliente.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista3 = (ArrayList<ArrayCliente>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoVendas() {
        try {
            FileInputStream Arq = new FileInputStream("Vendas.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista1 = (ArrayList<ArrayNovaVenda>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoVendedor() {
        try {
            FileInputStream Arq = new FileInputStream("Vendedor.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista2 = (ArrayList<ArrayVendedor>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoItemVenda() {
        try {
            FileInputStream Arq = new FileInputStream("ItemVendas.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista4 = (ArrayList<ArrayItenVenda>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoPagamento() {
        try {
            FileInputStream Arq = new FileInputStream("Pagamentos.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista5 = (ArrayList<ArrayPagamentos>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == bt_Gerar) {
                OK();
            }
            if (evento.getSource() == bt_Cancelar) {
                Cancelar();
            }
        }
    }

    private class TrataCores implements FocusListener {

        public void focusGained(FocusEvent evento) {//Ganha Focus
            if (evento.getSource() == tf_Codigo) {
                tf_Codigo.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tf_Codigo.setBackground(Color.WHITE);
        }
    }
}

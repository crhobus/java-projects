
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Produtos extends JFrame {

    private ArrayList<ArrayProdutos> lista = new ArrayList();
    private JTextField tf_Codigo, tf_Nome, tf_Descricao, tf_Quantidade, tf_Valor_Unitario, tf_IPI, tf_Descontos, tf_Valor_Total, tf_Numero_Serie, tf_Modelo;
    private JButton Ok, Cancela, Ver, Pesquisar;
    private JRadioButton Novo, Alterar, Excluir;
    private int INTNovo = 0, INTAlterar = 0, INTExluir = 0;

    Produtos() {
        super("Cadastro de Produtos");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(10, 10, 425, 200);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("Produtos"));
        Manipula manuseia = new Manipula();
        TrataEventos trata = new TrataEventos();
        TrataCores cores = new TrataCores();

        JLabel lb_Codigo = new JLabel("Codigo");
        lb_Codigo.setBounds(20, 40, 80, 14);
        lb_Codigo.setFont(fonte);
        Painel1.add(lb_Codigo);

        tf_Codigo = new JTextField();
        tf_Codigo.setBounds(20, 58, 80, 20);
        int codigoint = lista.size();
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

        Ver = new JButton("...");
        Ver.setBounds(104, 56, 31, 24);
        Painel1.add(Ver);
        Ver.addActionListener(trata);

        JLabel lb_Nome = new JLabel("Nome");
        lb_Nome.setBounds(140, 40, 80, 14);
        lb_Nome.setFont(fonte);
        Painel1.add(lb_Nome);

        tf_Nome = new JTextField();
        tf_Nome.setBounds(140, 58, 243, 20);
        Painel1.add(tf_Nome);
        tf_Nome.addFocusListener(cores);

        JLabel lb_Descricao = new JLabel("Descrição");
        lb_Descricao.setBounds(20, 78, 90, 14);
        lb_Descricao.setFont(fonte);
        Painel1.add(lb_Descricao);

        tf_Descricao = new JTextField();
        tf_Descricao.setBounds(20, 94, 110, 20);
        Painel1.add(tf_Descricao);
        tf_Descricao.addFocusListener(cores);

        JLabel lb_Numero_Serie = new JLabel("Numero Série");
        lb_Numero_Serie.setBounds(140, 78, 80, 14);
        lb_Numero_Serie.setFont(fonte);
        Painel1.add(lb_Numero_Serie);

        tf_Numero_Serie = new JTextField();
        tf_Numero_Serie.setBounds(140, 94, 110, 20);
        Painel1.add(tf_Numero_Serie);
        tf_Numero_Serie.addFocusListener(cores);

        JLabel lb_Modelo = new JLabel("Modelo");
        lb_Modelo.setBounds(260, 78, 80, 14);
        lb_Modelo.setFont(fonte);
        Painel1.add(lb_Modelo);

        tf_Modelo = new JTextField();
        tf_Modelo.setBounds(260, 94, 123, 20);
        Painel1.add(tf_Modelo);
        tf_Modelo.addFocusListener(cores);

        JLabel lb_Quantidade = new JLabel("Quantidade");
        lb_Quantidade.setBounds(20, 114, 80, 14);
        lb_Quantidade.setFont(fonte);
        Painel1.add(lb_Quantidade);

        tf_Quantidade = new JTextField();
        tf_Quantidade.setEditable(false);
        tf_Quantidade.setBackground(cor);
        tf_Quantidade.setText("1");
        tf_Quantidade.setBounds(20, 130, 110, 20);
        Painel1.add(tf_Quantidade);
        tf_Quantidade.addFocusListener(cores);

        JLabel lb_Valor_Unitario = new JLabel("Valor Uniário");
        lb_Valor_Unitario.setBounds(140, 114, 110, 14);
        lb_Valor_Unitario.setFont(fonte);
        Painel1.add(lb_Valor_Unitario);

        tf_Valor_Unitario = new JTextField();
        tf_Valor_Unitario.setBounds(140, 130, 110, 20);
        Painel1.add(tf_Valor_Unitario);
        tf_Valor_Unitario.addFocusListener(cores);

        JLabel lb_Descontos = new JLabel("Descontos");
        lb_Descontos.setBounds(260, 114, 80, 14);
        lb_Descontos.setFont(fonte);
        Painel1.add(lb_Descontos);

        tf_Descontos = new JTextField();
        tf_Descontos.setBounds(260, 130, 120, 20);
        Painel1.add(tf_Descontos);
        tf_Descontos.addFocusListener(cores);

        JLabel lb_IPI = new JLabel("IPI");
        lb_IPI.setBounds(20, 150, 80, 14);
        lb_IPI.setFont(fonte);
        Painel1.add(lb_IPI);

        tf_IPI = new JTextField();
        tf_IPI.setBounds(20, 166, 110, 20);
        Painel1.add(tf_IPI);
        tf_IPI.addFocusListener(cores);

        JLabel lb_Valor_Total = new JLabel("Valor Total");
        lb_Valor_Total.setBounds(140, 150, 80, 14);
        lb_Valor_Total.setFont(fonte);
        Painel1.add(lb_Valor_Total);

        tf_Valor_Total = new JTextField();
        tf_Valor_Total.setBounds(140, 166, 110, 20);
        Painel1.add(tf_Valor_Total);
        tf_Valor_Total.addFocusListener(cores);
        tf_Valor_Total.addFocusListener(new FocusAdapter() {

            public void focusGained(FocusEvent evento) {
                Calcula();//Quando tf_Valor_Total estiver focalizado executa método Calcula()
            }
        });

        Pesquisar = new JButton("Pesquisar");
        Pesquisar.setBounds(455, 25, 100, 26);
        tela.add(Pesquisar);
        Pesquisar.addActionListener(trata);

        Novo = new JRadioButton("Novo");
        Novo.setBounds(458, 70, 80, 20);
        Novo.setFont(fonte);
        tela.add(Novo);
        Novo.addItemListener(manuseia);

        Alterar = new JRadioButton("Alterar");
        Alterar.setBounds(458, 90, 80, 20);
        Alterar.setFont(fonte);
        tela.add(Alterar);
        Alterar.addItemListener(manuseia);

        Excluir = new JRadioButton("Excluir");
        Excluir.setBounds(458, 110, 80, 20);
        Excluir.setFont(fonte);
        tela.add(Excluir);
        Excluir.addItemListener(manuseia);

        Ok = new JButton("OK");
        Ok.setBounds(455, 150, 100, 26);
        tela.add(Ok);
        Ok.addActionListener(trata);

        Cancela = new JButton("Cancelar");
        Cancela.setBounds(455, 190, 100, 26);
        tela.add(Cancela);
        Cancela.addActionListener(trata);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(Painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        Painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        LerArquivo();
        setSize(600, 258);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    private void Ok() {
        int INTTexto = 0;
        if (lista.isEmpty() == true) {
            Gravar();
        } else {
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayProdutos aux = lista.get(i);
                String Texto = tf_Codigo.getText();
                INTTexto = Integer.parseInt(Texto);
                if (INTTexto == aux.getCodigo()) {
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "O produto " + tf_Codigo.getText() + " ja esta inserido na lista deseja Substituilo? ", "Produtos", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    for (int i = 0; i < lista.size(); i++) {
                        ArrayProdutos aux = lista.get(i);
                        if (INTTexto == aux.getCodigo()) {
                            lista.remove(aux);
                        }
                    }
                    Gravar();
                }
            } else {
                Gravar();
            }
        }
    }

    private void Excluir() {
        int INTTexto = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Produtos cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean deuErro;
            do {
                try {
                    deuErro = false;
                    String StringTexto = JOptionPane.showInputDialog(null, "Informe o Codigo do Produto a ser Excluído:", "Saída de dados", JOptionPane.WARNING_MESSAGE);
                    INTTexto = Integer.parseInt(StringTexto);//Converção String para Integer
                } catch (NumberFormatException e) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Entre com o numero", "Valor Invalido", JOptionPane.ERROR_MESSAGE);
                }
            } while (deuErro);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayProdutos aux = lista.get(i);
                if (INTTexto == (aux.getCodigo())) {
                    lista.remove(aux);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Produto Removido com sucesso", "Produto", JOptionPane.INFORMATION_MESSAGE);
                LimparTela();
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void LimparTela() {
        int codigoint = lista.size();
        String codigoString = Integer.toString(codigoint + 1);
        tf_Codigo.setText(codigoString);
        tf_Nome.setText("");
        tf_Descricao.setText("");
        tf_Valor_Unitario.setText("");
        tf_IPI.setText("");
        tf_Descontos.setText("");
        tf_Valor_Total.setText("");
        tf_Numero_Serie.setText("");
        tf_Modelo.setText("");
        Novo.setSelected(false);
        Alterar.setSelected(false);
        Excluir.setSelected(false);
        INTNovo = 0;
        INTAlterar = 0;
        INTExluir = 0;
    }

    private void Recuperar() {
        int INTTexto = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Produtos cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean deuErro;
            do {
                try {
                    deuErro = false;
                    String StringTexto = JOptionPane.showInputDialog(null, "Insira codigo: ", "Produto", JOptionPane.WARNING_MESSAGE);
                    INTTexto = Integer.parseInt(StringTexto);//Converção String para Integer
                } catch (NumberFormatException e) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Entre com o numero", "Valor Invalido", JOptionPane.ERROR_MESSAGE);
                }
            } while (deuErro);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayProdutos aux = lista.get(i);
                if (INTTexto == aux.getCodigo()) {//Não importa se texto digitado esta em maiusculo ou minusculo
                    int TextoINTCodigo = aux.getCodigo();
                    String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                    tf_Codigo.setText(TextoCodigo);
                    String TextoNome = aux.getNome();
                    tf_Nome.setText(TextoNome);
                    String TextoDescricao = aux.getDescricao();
                    tf_Descricao.setText(TextoDescricao);
                    int TextoINTQuantidade = aux.getQuantidade();
                    String TextoQuantidade = Integer.toString(TextoINTQuantidade);//Conversão int para String
                    tf_Quantidade.setText(TextoQuantidade);
                    float TextoFLOATValor_Unitario = aux.getValor_Unitario();
                    String TextoValor_Unitario = Float.toString(TextoFLOATValor_Unitario);//Conversão float para String
                    tf_Valor_Unitario.setText(TextoValor_Unitario);
                    float TextoFLOATIPI = aux.getIPI();
                    String TextoIPI = Float.toString(TextoFLOATIPI);//Conversão float para String
                    tf_IPI.setText(TextoIPI);
                    float TextoFLOATDescontos = aux.getDescontos();
                    String TextoDescontos = Float.toString(TextoFLOATDescontos);//Conversão float para String
                    tf_Descontos.setText(TextoDescontos);
                    float TextoFLOATValor_Total = aux.getValor_Total();
                    String TextoValor_Total = Float.toString(TextoFLOATValor_Total);//Conversão float para String
                    tf_Valor_Total.setText(TextoValor_Total);
                    String TextoNumero_Serie = aux.getNumero_Serie();
                    tf_Numero_Serie.setText(TextoNumero_Serie);
                    String TextoModelo = aux.getModelo();
                    tf_Modelo.setText(TextoModelo);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Produto Encontrado e Recuperado da Lista", "Produto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Ver() {
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Produtos cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            VerProduto aux = new VerProduto();
        }
    }

    private void Quem() {
        int Codigo = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Produtos cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Produtos", JOptionPane.WARNING_MESSAGE);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayProdutos aux = lista.get(i);
                if (Texto.equalsIgnoreCase(aux.getNome())) {//Não importa se texto digitado esta em maiusculo ou minusculo
                    Codigo = aux.getCodigo();
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "O Produto " + Texto + " esta inserido com o codigo " + Codigo, "Produto", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "O Produto " + Texto + " não esta inserido", "Produto", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void Gravar() {
        String textocod, textoquantidade, textovalor_uni, textoipi, textodes, textovalor_tot, InserNome = "", InserDescricao = "", InserNumero_Serie = "", InserModelo = "";
        int InserCodigo = 0, InserQuantidade = 0;
        float InserValor_Unitario = 0, InserIPI = 0, InserDescontos = 0, InserValor_Total = 0;

        if (!"".equals(tf_Codigo.getText())) {//Se campo não esta vazio
            textocod = tf_Codigo.getText();
            InserCodigo = Integer.parseInt(textocod);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Codigo.setText("");//Limpa o campo
            tf_Codigo.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Nome.getText())) {//Se campo não esta vazio
            InserNome = tf_Nome.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Nome esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Nome.setText("");//Limpa o campo
            tf_Nome.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Descricao.getText())) {//Se campo não esta vazio
            InserDescricao = tf_Descricao.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Descrição esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Descricao.setText("");//Limpa o campo
            tf_Descricao.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Numero_Serie.getText())) {//Se campo não esta vazio
            InserNumero_Serie = tf_Numero_Serie.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Numero de Série esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Numero_Serie.setText("");//Limpa o campo
            tf_Numero_Serie.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Modelo.getText())) {//Se campo não esta vazio
            InserModelo = tf_Modelo.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Modelo esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Modelo.setText("");//Limpa o campo
            tf_Modelo.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Valor_Unitario.getText())) {//Se campo não esta vazio
            textovalor_uni = tf_Valor_Unitario.getText();
            InserValor_Unitario = Float.parseFloat(textovalor_uni);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Valor Unitário esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Valor_Unitario.setText("");//Limpa o campo
            tf_Valor_Unitario.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_IPI.getText())) {//Se campo não esta vazio
            textoipi = tf_IPI.getText();
            InserIPI = Float.parseFloat(textoipi);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo IPI esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_IPI.setText("");//Limpa o campo
            tf_IPI.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Quantidade.getText())) {//Se campo não esta vazio
            textoquantidade = tf_Quantidade.getText();
            InserQuantidade = Integer.parseInt(textoquantidade);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Quantidade esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Quantidade.setText("");//Limpa o campo
            tf_Quantidade.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Descontos.getText())) {//Se campo não esta vazio
            textodes = tf_Descontos.getText();
            InserDescontos = Float.parseFloat(textodes);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Descontos esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Descontos.setText("");//Limpa o campo
            tf_Descontos.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Valor_Total.getText())) {//Se campo não esta vazio
            textovalor_tot = tf_Valor_Total.getText();
            InserValor_Total = Float.parseFloat(textovalor_tot);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Valor Total esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Valor_Total.setText("");//Limpa o campo
            tf_Valor_Total.grabFocus();//Focaliza o campo
        }
        if (!"".equals(tf_Codigo.getText()) & !"".equals(tf_Nome.getText()) & !"".equals(tf_Descricao.getText()) & !"".equals(tf_Numero_Serie.getText()) & !"".equals(tf_Modelo.getText()) & !"".equals(tf_Valor_Unitario.getText()) & !"".equals(tf_IPI.getText()) & !"".equals(tf_Quantidade.getText()) & !"".equals(tf_Descontos.getText()) & !"".equals(tf_Valor_Total.getText())) {
            ArrayProdutos aux = new ArrayProdutos(InserNome, InserDescricao, InserNumero_Serie, InserModelo, InserCodigo, InserQuantidade, InserValor_Unitario, InserIPI, InserDescontos, InserValor_Total);
            lista.add(aux);
            JOptionPane.showMessageDialog(null, "Número de Produtos cadastrados: " + lista.size(), "Entrada de Dados", JOptionPane.WARNING_MESSAGE);
            LimparTela();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel executar operação no sistema", "Erro Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Arquivo() {
        try {
            FileOutputStream arqDados = new FileOutputStream("Produto.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Produto.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista = (ArrayList<ArrayProdutos>) entra.readObject();
            entra.close();
            int codigoint = lista.size();
            String codigoString = Integer.toString(codigoint + 1);
            tf_Codigo.setText(codigoString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Codigo() {
        if ("".equals(tf_Codigo.getText())) {
            LimparTela();
        } else {
            if (lista.isEmpty() == true) {
            } else {
                String t = tf_Codigo.getText();
                int n = Integer.parseInt(t);
                boolean encontrado = false;
                for (int i = 0; i < lista.size(); i++) {
                    ArrayProdutos aux = lista.get(i);
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

    private void Calcula() {
        String textoQuantidade, textoValor_Unitario, textoDescontos, textoIPI, textoValor_Total, texto = "";
        float Valor_Unitario = 0, Descontos = 0, IPI = 0, Valor_Total1 = 0, Valor_Total2 = 0, Valor_Total3 = 0;
        int Quantidade = 0;

        if (!"".equals(tf_Quantidade.getText()) & !"".equals(tf_Valor_Unitario.getText()) & !"".equals(tf_IPI.getText()) & !"".equals(tf_Descontos.getText())) {
            textoQuantidade = tf_Quantidade.getText();
            Quantidade = Integer.parseInt(textoQuantidade);//Conversão de String para Integer
            textoValor_Unitario = tf_Valor_Unitario.getText();
            Valor_Unitario = Float.parseFloat(textoValor_Unitario);//Conversão de String para Float
            textoDescontos = tf_Descontos.getText();
            Descontos = Float.parseFloat(textoDescontos);//Conversão de String para Float
            textoIPI = tf_IPI.getText();
            IPI = Float.parseFloat(textoIPI);//Conversão de String para Float

            Valor_Total1 = ((Quantidade * Valor_Unitario) + IPI);
            Valor_Total2 = (Valor_Total1 / 100) * Descontos;
            Valor_Total3 = Valor_Total1 - Valor_Total2;
            texto = Float.toString(Valor_Total3);//Conversão de float para String
            tf_Valor_Total.setText(texto);
        }
    }

    private class Manipula implements ItemListener {

        public void itemStateChanged(ItemEvent evento) {
            if (evento.getSource() == Novo) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    INTNovo = 1;
                    INTAlterar = 0;
                    INTExluir = 0;
                    Alterar.setSelected(false);
                    Excluir.setSelected(false);
                }
            }
            if (evento.getSource() == Alterar) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    INTNovo = 0;
                    INTAlterar = 1;
                    INTExluir = 0;
                    Novo.setSelected(false);
                    Excluir.setSelected(false);
                }
            }
            if (evento.getSource() == Excluir) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    INTNovo = 0;
                    INTAlterar = 0;
                    INTExluir = 1;
                    Novo.setSelected(false);
                    Alterar.setSelected(false);
                }
            }
        }
    }

    private class TrataEventos implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == Pesquisar) {
                Quem();
            }
            if (evento.getSource() == Ok) {
                if (Novo.isSelected() == false & Alterar.isSelected() == false & Excluir.isSelected() == false) {
                    JOptionPane.showMessageDialog(null, "Selecione uma opção", "Fornecedor", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (Novo.isSelected() == true) {//Verifica se JRadioButton esta ou não selecionado
                        Ok();
                        Arquivo();
                        INTNovo = 0;
                        INTAlterar = 0;
                        INTExluir = 0;
                    }
                    if (Alterar.isSelected() == true) {
                        Recuperar();
                        INTNovo = 0;
                        INTAlterar = 0;
                        INTExluir = 0;
                    }
                    if (Excluir.isSelected() == true) {
                        Excluir();
                        Arquivo();
                        INTNovo = 0;
                        INTAlterar = 0;
                        INTExluir = 0;
                    }
                }
            }
            if (evento.getSource() == Cancela) {
                LimparTela();
            }
            if (evento.getSource() == Ver) {
                Ver();
            }
        }
    }

    private class TrataCores implements FocusListener {

        public void focusGained(FocusEvent evento) {//Ganha Focus
            if (evento.getSource() == tf_Codigo) {
                tf_Codigo.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Nome) {
                tf_Nome.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Descricao) {
                tf_Descricao.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Numero_Serie) {
                tf_Numero_Serie.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Modelo) {
                tf_Modelo.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Quantidade) {
                tf_Quantidade.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Valor_Unitario) {
                tf_Valor_Unitario.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Descontos) {
                tf_Descontos.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_IPI) {
                tf_IPI.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Valor_Total) {
                tf_Valor_Total.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tf_Codigo.setBackground(Color.WHITE);
            tf_Nome.setBackground(Color.WHITE);
            tf_Descricao.setBackground(Color.WHITE);
            tf_Numero_Serie.setBackground(Color.WHITE);
            tf_Modelo.setBackground(Color.WHITE);
            tf_Quantidade.setBackground(Color.WHITE);
            tf_Valor_Unitario.setBackground(Color.WHITE);
            tf_Descontos.setBackground(Color.WHITE);
            tf_IPI.setBackground(Color.WHITE);
            tf_Valor_Total.setBackground(Color.WHITE);
        }
    }
}

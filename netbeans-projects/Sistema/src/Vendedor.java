
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.io.*;

public class Vendedor extends JFrame {

    private ArrayList<ArrayVendedor> lista1 = new ArrayList();
    private ArrayList<ArraySetor> lista2 = new ArrayList();
    private ArrayList<ArrayCidade> lista3 = new ArrayList();
    private JTextField tf_Codigo, tf_Nome, tf_Comissao, tf_Empresa, tf_Endereco, tf_Bairro, tf_Numero, tf_Email;
    private JFormattedTextField CEP, Fone, Fax, IE, CNPJ;
    private MaskFormatter mascaraCEP, mascaraFone, mascaraFax, mascaraCNPJ, mascaraIE;
    private JButton Ver, Pesquisar, Cancela, Ok;
    private JComboBox cb_Setor, cb_Cidade, cb_Estado;
    private JRadioButton Novo, Alterar, Excluir;
    private int INTNovo = 0, INTAlterar = 0, INTExluir = 0;

    Vendedor() {
        super("Cadastro de Vendedores");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(10, 10, 425, 280);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("Vendedores"));
        LerArquivoSetor();
        LerArquivoCidade();
        Manipula manuseia = new Manipula();
        TrataEventos trata = new TrataEventos();
        TrataCores cores = new TrataCores();

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
        tf_Codigo.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        tf_Codigo.addFocusListener(cores);
        tf_Codigo.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Codigo();
            }
        });

        Ver = new JButton("...");
        Ver.setBounds(104, 56, 31, 24);
        Painel1.add(Ver);
        //Ver.addActionListener(trata);

        JLabel lb_Nome = new JLabel("Nome");
        lb_Nome.setBounds(140, 40, 80, 14);
        lb_Nome.setFont(fonte);
        Painel1.add(lb_Nome);

        tf_Nome = new JTextField();
        tf_Nome.setBounds(140, 58, 243, 20);
        Painel1.add(tf_Nome);
        tf_Nome.addFocusListener(cores);

        JLabel lb_Setor = new JLabel("Setor");
        lb_Setor.setBounds(20, 78, 80, 14);
        lb_Setor.setFont(fonte);
        Painel1.add(lb_Setor);

        cb_Setor = new JComboBox();
        cb_Setor.setBounds(20, 94, 110, 20);
        cb_Setor.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        for (int i = 0; i < lista2.size(); i++) {
            ArraySetor aux = lista2.get(i);
            if (lista2.size() != 0) {//Se lista.size for diferente de zero
                String n = aux.getNome();
                cb_Setor.addItem(n);
            }
        }
        cb_Setor.setBackground(cor);
        cb_Setor.setFont(fonte);
        Painel1.add(cb_Setor);
        cb_Setor.addFocusListener(cores);

        JLabel lb_Comissao = new JLabel("Comissão");
        lb_Comissao.setBounds(140, 78, 80, 14);
        lb_Comissao.setFont(fonte);
        Painel1.add(lb_Comissao);

        tf_Comissao = new JTextField();
        tf_Comissao.setBounds(140, 94, 110, 20);
        Painel1.add(tf_Comissao);
        tf_Comissao.addFocusListener(cores);

        JLabel lb_Empresa = new JLabel("Empresa");
        lb_Empresa.setBounds(260, 78, 80, 14);
        lb_Empresa.setFont(fonte);
        Painel1.add(lb_Empresa);

        tf_Empresa = new JTextField();
        tf_Empresa.setBounds(260, 94, 123, 20);
        Painel1.add(tf_Empresa);
        tf_Empresa.addFocusListener(cores);

        JLabel lb_IE = new JLabel("IE");
        lb_IE.setBounds(20, 114, 80, 14);
        lb_IE.setFont(fonte);
        Painel1.add(lb_IE);

        try {
            mascaraIE = new MaskFormatter("###.###.###");
        } catch (ParseException excp) {
        }
        IE = new JFormattedTextField(mascaraIE);
        IE.setBounds(20, 130, 110, 20);
        Painel1.add(IE);
        IE.addFocusListener(cores);

        JLabel lb_CNPJ = new JLabel("CNPJ");
        lb_CNPJ.setBounds(140, 114, 110, 14);
        lb_CNPJ.setFont(fonte);
        Painel1.add(lb_CNPJ);

        try {
            mascaraCNPJ = new MaskFormatter("###.###.###/####-##");
        } catch (ParseException excp) {
        }
        CNPJ = new JFormattedTextField(mascaraCNPJ);
        CNPJ.setBounds(140, 130, 123, 20);
        Painel1.add(CNPJ);
        CNPJ.addFocusListener(cores);

        JLabel lb_Endereco = new JLabel("Endereço");
        lb_Endereco.setBounds(268, 114, 80, 14);
        lb_Endereco.setFont(fonte);
        Painel1.add(lb_Endereco);

        tf_Endereco = new JTextField();
        tf_Endereco.setBounds(268, 130, 114, 20);
        Painel1.add(tf_Endereco);
        tf_Endereco.addFocusListener(cores);

        JLabel lb_Bairro = new JLabel("Bairro");
        lb_Bairro.setBounds(20, 150, 80, 14);
        lb_Bairro.setFont(fonte);
        Painel1.add(lb_Bairro);

        tf_Bairro = new JTextField();
        tf_Bairro.setBounds(20, 166, 110, 20);
        Painel1.add(tf_Bairro);
        tf_Bairro.addFocusListener(cores);

        JLabel lb_Numero = new JLabel("Numero");
        lb_Numero.setBounds(140, 150, 80, 14);
        lb_Numero.setFont(fonte);
        Painel1.add(lb_Numero);

        tf_Numero = new JTextField();
        tf_Numero.setBounds(140, 166, 110, 20);
        Painel1.add(tf_Numero);
        tf_Numero.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        tf_Numero.addFocusListener(cores);

        JLabel lb_CEP = new JLabel("CEP");
        lb_CEP.setBounds(262, 150, 80, 14);
        lb_CEP.setFont(fonte);
        Painel1.add(lb_CEP);

        try {
            mascaraCEP = new MaskFormatter("#####-###");
        } catch (ParseException excp) {
        }
        CEP = new JFormattedTextField(mascaraCEP);
        CEP.setBounds(262, 166, 120, 20);
        Painel1.add(CEP);
        CEP.addFocusListener(cores);

        JLabel lb_Cidade = new JLabel("Cidade");
        lb_Cidade.setBounds(20, 185, 80, 14);
        lb_Cidade.setFont(fonte);
        Painel1.add(lb_Cidade);

        cb_Cidade = new JComboBox();
        cb_Cidade.setBounds(20, 200, 110, 20);
        cb_Cidade.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        for (int i = 0; i < lista3.size(); i++) {
            ArrayCidade aux = lista3.get(i);
            if (lista3.size() != 0) {//Se lista.size for diferente de zero
                String n = aux.getCidade();
                cb_Cidade.addItem(n);
            }
        }
        cb_Cidade.setBackground(cor);
        cb_Cidade.setFont(fonte);
        Painel1.add(cb_Cidade);
        cb_Cidade.addFocusListener(cores);

        JLabel lb_Estado = new JLabel("Estado");
        lb_Estado.setBounds(140, 185, 80, 14);
        lb_Estado.setFont(fonte);
        Painel1.add(lb_Estado);

        cb_Estado = new JComboBox();
        cb_Estado.setBounds(140, 200, 110, 20);
        cb_Estado.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        for (int i = 0; i < lista3.size(); i++) {
            ArrayCidade aux = lista3.get(i);
            if (lista3.size() != 0) {//Se lista.size for diferente de zero
                String n = aux.getEstado();
                cb_Estado.addItem(n);
            }
        }
        cb_Estado.setBackground(cor);
        cb_Estado.setFont(fonte);
        Painel1.add(cb_Estado);
        cb_Estado.addFocusListener(cores);

        JLabel lb_Fone = new JLabel("Fone");
        lb_Fone.setBounds(260, 185, 80, 14);
        lb_Fone.setFont(fonte);
        Painel1.add(lb_Fone);

        try {
            mascaraFone = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        Fone = new JFormattedTextField(mascaraFone);
        Fone.setBounds(260, 200, 122, 20);
        Painel1.add(Fone);
        Fone.addFocusListener(cores);

        JLabel lb_Fax = new JLabel("Fax");
        lb_Fax.setBounds(20, 220, 80, 14);
        lb_Fax.setFont(fonte);
        Painel1.add(lb_Fax);

        try {
            mascaraFax = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        Fax = new JFormattedTextField(mascaraFax);
        Fax.setBounds(20, 235, 110, 20);
        Painel1.add(Fax);
        Fax.addFocusListener(cores);

        JLabel lb_Email = new JLabel("E-mail");
        lb_Email.setBounds(140, 220, 80, 14);
        lb_Email.setFont(fonte);
        Painel1.add(lb_Email);

        tf_Email = new JTextField();
        tf_Email.setBounds(140, 235, 242, 20);
        Painel1.add(tf_Email);
        tf_Email.addFocusListener(cores);

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
        setSize(600, 340);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }

    private void Ok() {
        int INTTexto = 0;
        if (lista1.isEmpty() == true) {
            Gravar();
        } else {
            boolean encontrado = false;
            for (int i = 0; i < lista1.size(); i++) {
                ArrayVendedor aux = lista1.get(i);
                String Texto = tf_Codigo.getText();
                INTTexto = Integer.parseInt(Texto);
                if (INTTexto == aux.getCodigo()) {
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "O vendedor " + tf_Codigo.getText() + " ja esta inserido na lista deseja Substituilo? ", "Vendedor", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    for (int i = 0; i < lista1.size(); i++) {
                        ArrayVendedor aux = lista1.get(i);
                        if (INTTexto == aux.getCodigo()) {
                            lista1.remove(aux);
                        }
                    }
                    Gravar();
                }
            } else {
                Gravar();
            }
        }
    }

    private void LimparTela() {
        int codigoint = lista1.size();
        String codigoString = Integer.toString(codigoint + 1);
        tf_Codigo.setText(codigoString);
        tf_Nome.setText("");
        tf_Comissao.setText("");
        tf_Empresa.setText("");
        tf_Endereco.setText("");
        tf_Bairro.setText("");
        tf_Numero.setText("");
        tf_Email.setText("");
        CEP.setText("");
        Fone.setText("");
        Fax.setText("");
        IE.setText("");
        CNPJ.setText("");
        cb_Setor.setSelectedItem("Selecione");///Campo recebe Selecione
        cb_Cidade.setSelectedItem("Selecione");///Campo recebe Selecione
        cb_Estado.setSelectedItem("Selecione");///Campo recebe Selecione
        Novo.setSelected(false);
        Alterar.setSelected(false);
        Excluir.setSelected(false);
        INTNovo = 0;
        INTAlterar = 0;
        INTExluir = 0;
    }

    private void Excluir() {
        int INTTexto = 0;
        if (lista1.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Vendedores cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean deuErro;
            do {
                try {
                    deuErro = false;
                    String StringTexto = JOptionPane.showInputDialog(null, "Informe o Codigo do Vendedor a ser Excluído:", "Saída de dados", JOptionPane.WARNING_MESSAGE);
                    INTTexto = Integer.parseInt(StringTexto);//Converção String para Integer
                } catch (NumberFormatException e) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Entre com o numero", "Valor Invalido", JOptionPane.ERROR_MESSAGE);
                }
            } while (deuErro);
            boolean encontrado = false;
            for (int i = 0; i < lista1.size(); i++) {
                ArrayVendedor aux = lista1.get(i);
                if (INTTexto == aux.getCodigo()) {
                    lista1.remove(aux);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Vendedor Removido com sucesso", "Vendedor", JOptionPane.INFORMATION_MESSAGE);
                LimparTela();
            } else {
                JOptionPane.showMessageDialog(null, "Vendedor  não encontrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Recuperar() {
        int INTTexto = 0;
        if (lista1.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Vendedores cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean deuErro;
            do {
                try {
                    deuErro = false;
                    String Texto = JOptionPane.showInputDialog(null, "Insira codigo: ", "Vendedor", JOptionPane.WARNING_MESSAGE);
                    INTTexto = Integer.parseInt(Texto);//Converção String para Integer
                } catch (NumberFormatException e) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Entre com o numero", "Valor Invalido", JOptionPane.ERROR_MESSAGE);
                }
            } while (deuErro);
            boolean encontrado = false;
            for (int i = 0; i < lista1.size(); i++) {
                ArrayVendedor aux = lista1.get(i);
                if (INTTexto == aux.getCodigo()) {
                    int TextoINTCodigo = aux.getCodigo();
                    String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                    tf_Codigo.setText(TextoCodigo);
                    String TextoNome = aux.getNome();
                    tf_Nome.setText(TextoNome);
                    String TextoComissao = aux.getComissao();
                    tf_Comissao.setText(TextoComissao);
                    String TextoEmpresa = aux.getEmpresa();
                    tf_Empresa.setText(TextoEmpresa);
                    String TextoEndereco = aux.getEndereco();
                    tf_Endereco.setText(TextoEndereco);
                    String TextoBairro = aux.getBairro();
                    tf_Bairro.setText(TextoBairro);
                    String TextoEmail = aux.getEmail();
                    tf_Email.setText(TextoEmail);
                    String TextoCEP = aux.getCEP();
                    CEP.setText(TextoCEP);
                    String TextoFone = aux.getFone();
                    Fone.setText(TextoFone);
                    String TextoFax = aux.getFax();
                    Fax.setText(TextoFax);
                    String TextoIE = aux.getIE();
                    IE.setText(TextoIE);
                    String TextoCNPJ = aux.getCNPJ();
                    CNPJ.setText(TextoCNPJ);
                    String TextoSetor = aux.getSetor();
                    cb_Setor.setSelectedItem(TextoSetor);//Recupera do Arraylist e joga na JCombobox
                    String TextoCidade = aux.getCidade();
                    cb_Cidade.setSelectedItem(TextoCidade);//Recupera do Arraylist e joga na JCombobox
                    String TextoEstado = aux.getEstado();
                    cb_Estado.setSelectedItem(TextoEstado);//Recupera do Arraylist e joga na JCombobox
                    int TextoINTNumero = aux.getNumero();
                    String TextoNumero = Integer.toString(TextoINTNumero);//Conversão int para String
                    tf_Numero.setText(TextoNumero);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Vendedor Encontrado e Recuperado da Lista", "Vendedor", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Vendedor não encontrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Ver() {
        if (lista1.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Vendedores cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            //VerCliente aux = new VerCliente(lista);
        }
    }

    private void Quem() {
        int Codigo = 0;
        if (lista1.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Vendedores cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Vendedor", JOptionPane.WARNING_MESSAGE);
            boolean encontrado = false;
            for (int i = 0; i < lista1.size(); i++) {
                ArrayVendedor aux = lista1.get(i);
                if (Texto.equalsIgnoreCase(aux.getNome())) {
                    Codigo = aux.getCodigo();
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "O vendedor " + Texto + " esta inserido com o codigo " + Codigo, "Vendedor", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "O vendedor " + Texto + " não esta inserido", "Vendedor", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void Gravar() {
        String textocod, textonum, InserNome = "", InserComissao = "", InserEmpresa = "", InserEndereco = "", InserBairro = "", InserEmail = "", InserCep = "", InserFone = "", InserFax = "", InserIe = "", InserCnpj = "", InserSetor = "", InserCidade = "", InserEstado = "";
        int InserCodigo = 0, InserNumero = 0;

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

        if (!"".equals(tf_Comissao.getText())) {//Se campo não esta vazio
            InserComissao = tf_Comissao.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Comissão esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Comissao.setText("");//Limpa o campo
            tf_Comissao.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Empresa.getText())) {//Se campo não esta vazio
            InserEmpresa = tf_Empresa.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Empresa esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Empresa.setText("");//Limpa o campo
            tf_Empresa.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Endereco.getText())) {//Se campo não esta vazio
            InserEndereco = tf_Endereco.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Endereço esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Endereco.setText("");//Limpa o campo
            tf_Endereco.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Bairro.getText())) {//Se campo não esta vazio
            InserBairro = tf_Bairro.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Bairro esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Bairro.setText("");//Limpa o campo
            tf_Bairro.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Email.getText())) {//Se campo não esta vazio
            InserEmail = tf_Email.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo E-mail esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Email.setText("");//Limpa o campo
            tf_Email.grabFocus();//Focaliza o campo
        }

        if (!"     -   ".equals(CEP.getText())) {//Se campo não esta vazio
            InserCep = CEP.getText();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo CEP esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            CEP.setText("");//Campo recebe Selecione
            CEP.grabFocus();//Focaliza o campo
        }

        if (!"(  )    -    ".equals(Fone.getText())) {//Se campo não esta vazio
            InserFone = Fone.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Fone esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            Fone.setText("");//Limpa o campo
            Fone.grabFocus();//Focaliza o campo
        }

        if (!"".equals(Fax.getText())) {//Se campo não esta vazio
            InserFax = Fax.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Fax esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            Fax.setText("");//Limpa o campo
            Fax.grabFocus();//Focaliza o campo
        }

        if (!"   .   .   ".equals(IE.getText())) {//Se campo não esta vazio
            InserIe = IE.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo IE esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            IE.setText("");//Limpa o campo
            IE.grabFocus();//Focaliza o campo
        }

        if (!"   .   .   /    -  ".equals(CNPJ.getText())) {//Se campo não esta vazio
            InserCnpj = CNPJ.getText();//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo CNPJ esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            CNPJ.setText("");//Limpa o campo
            CNPJ.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(cb_Setor.getSelectedItem())) {//Se campo não esta vazio
            InserSetor = (String) cb_Setor.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Setor esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            cb_Setor.setSelectedItem("Selecione");//Campo recebe Selecione
            cb_Setor.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(cb_Cidade.getSelectedItem())) {//Se campo não esta vazio
            InserCidade = (String) cb_Cidade.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Cidade esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            cb_Cidade.setSelectedItem("Selecione");//Campo recebe Selecione
            cb_Cidade.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(cb_Estado.getSelectedItem())) {//Se campo não esta vazio
            InserEstado = (String) cb_Estado.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Estado esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            cb_Estado.setSelectedItem("Selecione");//Campo recebe Selecione
            cb_Estado.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Numero.getText())) {//Se campo não esta vazio
            textonum = tf_Numero.getText();
            InserNumero = Integer.parseInt(textonum);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Numero esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Numero.setText("");//Limpa o campo
            tf_Numero.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Codigo.getText()) & !"".equals(tf_Nome.getText()) & !"".equals(tf_Comissao.getText()) & !"".equals(tf_Empresa.getText()) & !"".equals(tf_Endereco.getText()) & !"".equals(tf_Bairro.getText()) & !"".equals(tf_Email.getText()) & !"     -   ".equals(CEP.getText()) & !"(  )    -    ".equals(Fone.getText()) & !"(  )    -    ".equals(Fax.getText()) & !"   .   .   ".equals(IE.getText()) & !"   .   .   /    -  ".equals(CNPJ.getText()) & !"Selecione".equals(cb_Setor.getSelectedItem()) & !"Selecione".equals(cb_Cidade.getSelectedItem()) & !"Selecione".equals(cb_Estado.getSelectedItem()) & !"".equals(tf_Numero.getText())) {
            ArrayVendedor aux = new ArrayVendedor(InserNome, InserComissao, InserEmpresa, InserEndereco, InserBairro, InserEmail, InserCep, InserFone, InserFax, InserIe, InserCnpj, InserSetor, InserCidade, InserEstado, InserCodigo, InserNumero);
            lista1.add(aux);
            JOptionPane.showMessageDialog(null, "Número de Vendedores cadastrados: " + lista1.size(), "Entrada de Dados", JOptionPane.WARNING_MESSAGE);
            LimparTela();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel executar operação no sistema", "Erro Fatal", JOptionPane.ERROR_MESSAGE);
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
                    ArrayVendedor aux = lista1.get(i);
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

    private void Arquivo() {
        try {
            FileOutputStream arqDados = new FileOutputStream("Vendedor.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista1);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Vendedor.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista1 = (ArrayList<ArrayVendedor>) entra.readObject();
            entra.close();
            int codigoint = lista1.size();
            String codigoString = Integer.toString(codigoint + 1);
            tf_Codigo.setText(codigoString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoSetor() {
        try {
            FileInputStream Arq = new FileInputStream("Setor.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista2 = (ArrayList<ArraySetor>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoCidade() {
        try {
            FileInputStream Arq = new FileInputStream("Cidade.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista3 = (ArrayList<ArrayCidade>) entra.readObject();
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
            if (evento.getSource() == cb_Setor) {
                cb_Setor.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Comissao) {
                tf_Comissao.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Empresa) {
                tf_Empresa.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == IE) {
                IE.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == CNPJ) {
                CNPJ.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Endereco) {
                tf_Endereco.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Bairro) {
                tf_Bairro.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Numero) {
                tf_Numero.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == CEP) {
                CEP.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == cb_Cidade) {
                cb_Cidade.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == cb_Estado) {
                cb_Estado.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == Fone) {
                Fone.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == Fax) {
                Fax.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Email) {
                tf_Email.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tf_Codigo.setBackground(Color.WHITE);
            tf_Nome.setBackground(Color.WHITE);
            cb_Setor.setBackground(Color.WHITE);
            tf_Comissao.setBackground(Color.WHITE);
            tf_Empresa.setBackground(Color.WHITE);
            IE.setBackground(Color.WHITE);
            CNPJ.setBackground(Color.WHITE);
            tf_Endereco.setBackground(Color.WHITE);
            tf_Bairro.setBackground(Color.WHITE);
            tf_Numero.setBackground(Color.WHITE);
            CEP.setBackground(Color.WHITE);
            cb_Cidade.setBackground(Color.WHITE);
            cb_Estado.setBackground(Color.WHITE);
            Fone.setBackground(Color.WHITE);
            Fax.setBackground(Color.WHITE);
            tf_Email.setBackground(Color.WHITE);
        }
    }
}

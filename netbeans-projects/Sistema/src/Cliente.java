
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.io.*;

public class Cliente extends JFrame {

    private ArrayList<ArrayCliente> lista = new ArrayList();
    private ArrayList<ArrayCidade> lista2 = new ArrayList();
    private JTextField tf_Codigo, tf_Nome, tf_Profissao, tf_Empresa, tf_Endereco, tf_Bairro, tf_Numero, tf_Complemento, tf_Email;
    private JComboBox cb_Sexo, cb_Cidade, cb_Estado;
    private JFormattedTextField RG, CPF, Fone_Empresa, CEP, Fone, Celular;
    private MaskFormatter mascaraRG, mascaraCPF, mascaraFone_Empresa, mascaraCEP, mascaraFone, mascaraCelular;
    private JCheckBox Seleciona;
    private String InserSeleciona = null, InserArrayDescricao = null;
    private JButton Ok, bt_Descricao, Cancela, Ver, Pesquisar;
    private JRadioButton Novo, Alterar, Excluir;
    private int INTNovo = 0, INTAlterar = 0, INTExluir = 0;

    Cliente() {
        super("Cadastro de Clientes");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(10, 10, 425, 340);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("Clientes"));
        Manipula manuseia = new Manipula();
        TrataEventos trata = new TrataEventos();
        TrataCores cores = new TrataCores();
        LerArquivoCidade();

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
        Ver.addActionListener(trata);

        JLabel lb_Nome = new JLabel("Nome");
        lb_Nome.setBounds(140, 40, 80, 14);
        lb_Nome.setFont(fonte);
        Painel1.add(lb_Nome);

        tf_Nome = new JTextField();
        tf_Nome.setBounds(140, 58, 243, 20);
        Painel1.add(tf_Nome);
        tf_Nome.addFocusListener(cores);

        JLabel lb_RG = new JLabel("RG");
        lb_RG.setBounds(20, 78, 80, 14);
        lb_RG.setFont(fonte);
        Painel1.add(lb_RG);

        try {
            mascaraRG = new MaskFormatter("###.###.###");
        } catch (ParseException excp) {
        }
        RG = new JFormattedTextField(mascaraRG);
        RG.setBounds(20, 94, 110, 20);
        Painel1.add(RG);
        RG.addFocusListener(cores);

        JLabel lb_CPF = new JLabel("CPF");
        lb_CPF.setBounds(140, 78, 80, 14);
        lb_CPF.setFont(fonte);
        Painel1.add(lb_CPF);

        //Este faz com que so pode digitar numeros e o campo ja vem com as separações
        try {
            mascaraCPF = new MaskFormatter("###.###.###-##");
        } catch (ParseException excp) {
        }
        CPF = new JFormattedTextField(mascaraCPF);
        CPF.setBounds(140, 94, 110, 20);
        Painel1.add(CPF);
        CPF.addFocusListener(cores);

        JLabel lb_Profissao = new JLabel("Profissão");
        lb_Profissao.setBounds(260, 78, 80, 14);
        lb_Profissao.setFont(fonte);
        Painel1.add(lb_Profissao);

        tf_Profissao = new JTextField();
        tf_Profissao.setBounds(260, 94, 123, 20);
        Painel1.add(tf_Profissao);
        tf_Profissao.addFocusListener(cores);

        JLabel lb_Empresa = new JLabel("Empresa");
        lb_Empresa.setBounds(20, 114, 80, 14);
        lb_Empresa.setFont(fonte);
        Painel1.add(lb_Empresa);

        tf_Empresa = new JTextField();
        tf_Empresa.setBounds(20, 130, 110, 20);
        Painel1.add(tf_Empresa);
        tf_Empresa.addFocusListener(cores);

        JLabel lb_Fone_Empresa = new JLabel("Fone Empresa");
        lb_Fone_Empresa.setBounds(140, 114, 110, 14);
        lb_Fone_Empresa.setFont(fonte);
        Painel1.add(lb_Fone_Empresa);

        try {
            mascaraFone_Empresa = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        Fone_Empresa = new JFormattedTextField(mascaraFone_Empresa);
        Fone_Empresa.setBounds(140, 130, 110, 20);
        Painel1.add(Fone_Empresa);
        Fone_Empresa.addFocusListener(cores);

        JLabel lb_Sexo = new JLabel("Sexo");
        lb_Sexo.setBounds(260, 114, 80, 14);
        lb_Sexo.setFont(fonte);
        Painel1.add(lb_Sexo);

        cb_Sexo = new JComboBox();
        cb_Sexo.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        cb_Sexo.addItem("Masculino");
        cb_Sexo.addItem("Feminino");
        cb_Sexo.setBackground(cor);
        cb_Sexo.setFont(fonte);
        cb_Sexo.setBounds(260, 130, 120, 20);
        Painel1.add(cb_Sexo);
        cb_Sexo.addFocusListener(cores);

        JLabel lb_CEP = new JLabel("CEP");
        lb_CEP.setBounds(20, 150, 80, 14);
        lb_CEP.setFont(fonte);
        Painel1.add(lb_CEP);

        try {
            mascaraCEP = new MaskFormatter("#####-###");
        } catch (ParseException excp) {
        }
        CEP = new JFormattedTextField(mascaraCEP);
        CEP.setBounds(20, 166, 110, 20);
        Painel1.add(CEP);
        CEP.addFocusListener(cores);

        JLabel lb_Endereco = new JLabel("Endereço");
        lb_Endereco.setBounds(140, 150, 80, 14);
        lb_Endereco.setFont(fonte);
        Painel1.add(lb_Endereco);

        tf_Endereco = new JTextField();
        tf_Endereco.setBounds(140, 166, 110, 20);
        Painel1.add(tf_Endereco);
        tf_Endereco.addFocusListener(cores);

        JLabel lb_Bairro = new JLabel("Bairro");
        lb_Bairro.setBounds(260, 150, 80, 14);
        lb_Bairro.setFont(fonte);
        Painel1.add(lb_Bairro);

        tf_Bairro = new JTextField();
        tf_Bairro.setBounds(260, 166, 120, 20);
        Painel1.add(tf_Bairro);
        tf_Bairro.addFocusListener(cores);

        JLabel lb_Numero = new JLabel("Numero");
        lb_Numero.setBounds(20, 185, 80, 14);
        lb_Numero.setFont(fonte);
        Painel1.add(lb_Numero);

        tf_Numero = new JTextField();
        tf_Numero.setBounds(20, 200, 110, 20);
        tf_Numero.setDocument(new MeuDocument());
        Painel1.add(tf_Numero);
        tf_Numero.addFocusListener(cores);

        JLabel lb_Complemento = new JLabel("Complemento");
        lb_Complemento.setBounds(140, 185, 80, 14);
        lb_Complemento.setFont(fonte);
        Painel1.add(lb_Complemento);

        tf_Complemento = new JTextField();
        tf_Complemento.setBounds(140, 200, 110, 20);
        Painel1.add(tf_Complemento);
        tf_Complemento.addFocusListener(cores);

        JLabel lb_Cidade = new JLabel("Cidade");
        lb_Cidade.setBounds(260, 185, 80, 14);
        lb_Cidade.setFont(fonte);
        Painel1.add(lb_Cidade);

        cb_Cidade = new JComboBox();
        cb_Cidade.setBounds(260, 200, 120, 20);
        cb_Cidade.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        for (int i = 0; i < lista2.size(); i++) {
            ArrayCidade aux = lista2.get(i);
            if (lista2.size() != 0) {//Se lista.size for diferente de zero
                String n = aux.getCidade();
                cb_Cidade.addItem(n);
            }
        }
        cb_Cidade.setBackground(cor);
        cb_Cidade.setFont(fonte);
        Painel1.add(cb_Cidade);
        cb_Cidade.addFocusListener(cores);

        JLabel lb_Estado = new JLabel("Estado");
        lb_Estado.setBounds(20, 218, 80, 14);
        lb_Estado.setFont(fonte);
        Painel1.add(lb_Estado);

        cb_Estado = new JComboBox();
        cb_Estado.setBounds(20, 233, 110, 20);
        cb_Estado.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        for (int i = 0; i < lista2.size(); i++) {
            ArrayCidade aux = lista2.get(i);
            if (lista2.size() != 0) {//Se lista.size for diferente de zero
                String n = aux.getEstado();
                cb_Estado.addItem(n);
            }
        }
        cb_Estado.setBackground(cor);
        cb_Estado.setFont(fonte);
        Painel1.add(cb_Estado);
        cb_Estado.addFocusListener(cores);

        JLabel lb_Fone = new JLabel("Fone");
        lb_Fone.setBounds(140, 218, 80, 14);
        lb_Fone.setFont(fonte);
        Painel1.add(lb_Fone);

        try {
            mascaraFone = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        Fone = new JFormattedTextField(mascaraFone);
        Fone.setBounds(140, 233, 110, 20);
        Painel1.add(Fone);
        Fone.addFocusListener(cores);

        JLabel lb_Celular = new JLabel("Celular");
        lb_Celular.setBounds(260, 218, 80, 14);
        lb_Celular.setFont(fonte);
        Painel1.add(lb_Celular);

        try {
            mascaraCelular = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        Celular = new JFormattedTextField(mascaraCelular);
        Celular.setBounds(260, 233, 120, 20);
        Painel1.add(Celular);
        Celular.addFocusListener(cores);

        JLabel lb_Email = new JLabel("E-mail");
        lb_Email.setBounds(20, 252, 80, 14);
        lb_Email.setFont(fonte);
        Painel1.add(lb_Email);

        tf_Email = new JTextField();
        tf_Email.setBounds(20, 268, 361, 20);
        Painel1.add(tf_Email);
        tf_Email.addFocusListener(cores);

        Seleciona = new JCheckBox();
        Seleciona.setBounds(384, 305, 22, 17);
        Painel1.add(Seleciona);
        Seleciona.addItemListener(manuseia);

        bt_Descricao = new JButton("-------------    Descrição - Cadastro de Clientes    -------------");
        bt_Descricao.setBounds(20, 300, 361, 26);
        Painel1.add(bt_Descricao);
        bt_Descricao.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                botaoAbrirPressionado();
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
        setSize(600, 400);
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
                ArrayCliente aux = lista.get(i);
                String Texto = tf_Codigo.getText();
                INTTexto = Integer.parseInt(Texto);
                if (INTTexto == aux.getCodigo()) {
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "O cliente " + tf_Codigo.getText() + " ja esta inserido na lista deseja Substituilo? ", "Cliente", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    for (int i = 0; i < lista.size(); i++) {
                        ArrayCliente aux = lista.get(i);
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

    private void LimparTela() {
        int codigoint = lista.size();
        String codigoString = Integer.toString(codigoint + 1);
        tf_Codigo.setText(codigoString);
        tf_Nome.setText("");
        RG.setText("");
        CPF.setText("");
        tf_Profissao.setText("");
        tf_Empresa.setText("");
        Fone_Empresa.setText("");
        cb_Sexo.setSelectedItem("Selecione");///Campo recebe Selecione
        CEP.setText("");
        tf_Endereco.setText("");
        tf_Bairro.setText("");
        tf_Numero.setText("");
        tf_Complemento.setText("");
        cb_Cidade.setSelectedItem("Selecione");///Campo recebe Selecione
        cb_Estado.setSelectedItem("Selecione");///Campo recebe Selecione
        Fone.setText("");
        Celular.setText("");
        tf_Email.setText("");
        Seleciona.setSelected(false);//Desmarca JCheckbox
        InserArrayDescricao = null;//Limpa Variavel
        Novo.setSelected(false);
        Alterar.setSelected(false);
        Excluir.setSelected(false);
        INTNovo = 0;
        INTAlterar = 0;
        INTExluir = 0;
    }

    private void Excluir() {
        int INTTexto = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Clientes cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean deuErro;
            do {
                try {
                    deuErro = false;
                    String StringTexto = JOptionPane.showInputDialog(null, "Informe o Codigo do Cliente a ser Excluído:", "Saída de dados", JOptionPane.WARNING_MESSAGE);
                    INTTexto = Integer.parseInt(StringTexto);//Converção String para Integer
                } catch (NumberFormatException e) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Entre com o numero", "Valor Invalido", JOptionPane.ERROR_MESSAGE);
                }
            } while (deuErro);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayCliente aux = lista.get(i);
                if (INTTexto == aux.getCodigo()) {
                    lista.remove(aux);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Cliente Removido com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                LimparTela();
            } else {
                JOptionPane.showMessageDialog(null, "Cliente  não encontrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Recuperar() {
        int INTTexto = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Clientes cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean deuErro;
            do {
                try {
                    deuErro = false;
                    String Texto = JOptionPane.showInputDialog(null, "Insira codigo: ", "Cliente", JOptionPane.WARNING_MESSAGE);
                    INTTexto = Integer.parseInt(Texto);//Converção String para Integer
                } catch (NumberFormatException e) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Entre com o numero", "Valor Invalido", JOptionPane.ERROR_MESSAGE);
                }
            } while (deuErro);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayCliente aux = lista.get(i);
                if (INTTexto == aux.getCodigo()) {
                    String TextoEmail = aux.getEmail();
                    tf_Email.setText(TextoEmail);
                    int TextoINTCodigo = aux.getCodigo();
                    String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                    tf_Codigo.setText(TextoCodigo);
                    String TextoNome = aux.getNome();
                    tf_Nome.setText(TextoNome);
                    String TextoRG = aux.getRg();
                    RG.setText(TextoRG);
                    String TextoCPF = aux.getCpf();
                    CPF.setText(TextoCPF);
                    String TextoProfissao = aux.getProfissao();
                    tf_Profissao.setText(TextoProfissao);
                    String TextoEmpresa = aux.getEmpresa();
                    tf_Empresa.setText(TextoEmpresa);
                    String TextoFoneEmpresa = aux.getFone_empresa();
                    Fone_Empresa.setText(TextoFoneEmpresa);
                    cb_Sexo.setSelectedItem(aux.getSexo());//Recupera do Arraylist e joga na JCombobox
                    String TextoCEP = aux.getCep();
                    CEP.setText(TextoCEP);
                    String TextoEndereco = aux.getEndereco();
                    tf_Endereco.setText(TextoEndereco);
                    String TextoBairro = aux.getBairro();
                    tf_Bairro.setText(TextoBairro);
                    int TextoINTNumero = aux.getNumero();
                    String TextoNumero = Integer.toString(TextoINTNumero);//Conversão int para String
                    tf_Numero.setText(TextoNumero);
                    String TextoComplemento = aux.getComplemento();
                    tf_Complemento.setText(TextoComplemento);
                    cb_Cidade.setSelectedItem(aux.getCidade());//Recupera do Arraylist e joga na JCombobox
                    cb_Estado.setSelectedItem(aux.getEstado());//Recupera do Arraylist e joga na JCombobox
                    String TextoFone = aux.getFone();
                    Fone.setText(TextoFone);
                    String TextoCelular = aux.getCelular();
                    Celular.setText(TextoCelular);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Cliente Encontrado e Recuperado da Lista", "Cliente", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Ver() {
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Clientes cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            VerCliente aux = new VerCliente(lista);
        }
    }

    private void Quem() {
        int Codigo = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Clientes cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Cliente", JOptionPane.WARNING_MESSAGE);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayCliente aux = lista.get(i);
                if (Texto.equalsIgnoreCase(aux.getNome())) {
                    Codigo = aux.getCodigo();
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "O cliente " + Texto + " esta inserido com o codigo " + Codigo, "Cliente", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "O cliente " + Texto + " não esta inserido", "Cliente", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void Gravar() {
        String textocod, textonumero, Insernome = "", Inserrg = "", Insercpf = "", Inserprofissao = "", Inserempresa = "", Inserfone_empresa = "", Insersexo = "", Insercep = "", Inserendereco = "", Inserbairro = "", Insercomplemento = "", Insercidade = "", Inserestado = "", Inserfone = "", Insercelular = "", Inseremail = "", Inserdescricao = null;
        int Insercodigo = 0, Insernumero = 0;

        if (!"".equals(tf_Codigo.getText())) {//Se campo não esta vazio
            textocod = tf_Codigo.getText();
            Insercodigo = Integer.parseInt(textocod);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Código esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Codigo.setText("");//Limpa o campo
            tf_Codigo.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Nome.getText())) {//Se campo não esta vazio
            Insernome = tf_Nome.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Nome esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Nome.setText("");//Limpa o campo
            tf_Nome.grabFocus();//Focaliza o campo
        }

        if (!"   .   .   ".equals(RG.getText())) {//Se campo não esta vazio
            Inserrg = RG.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo RG esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            RG.setText("");//Limpa o campo
            RG.grabFocus();//Focaliza o campo
        }

        if (!"   .   .   -  ".equals(CPF.getText())) {//Se campo não esta vazio
            Insercpf = CPF.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo CPF esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            CPF.setText("");//Limpa o campo
            CPF.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Profissao.getText())) {//Se campo não esta vazio
            Inserprofissao = tf_Profissao.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Profissão esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Profissao.setText("");//Limpa o campo
            tf_Profissao.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Empresa.getText())) {//Se campo não esta vazio
            Inserempresa = tf_Empresa.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Empresa esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Empresa.setText("");//Limpa o campo
            tf_Empresa.grabFocus();//Focaliza o campo
        }

        if (!"(  )    -    ".equals(Fone_Empresa.getText())) {//Se campo não esta vazio
            Inserfone_empresa = Fone_Empresa.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Fone Empresa esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            Fone_Empresa.setText("");//Limpa o campo
            Fone_Empresa.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(cb_Sexo.getSelectedItem())) {//Se campo não esta vazio
            Insersexo = (String) cb_Sexo.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Sexo esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            cb_Sexo.setSelectedItem("Selecione");//Campo recebe Selecione
            cb_Sexo.grabFocus();//Focaliza o campo
        }

        if (!"     -   ".equals(CEP.getText())) {//Se campo não esta vazio
            Insercep = CEP.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo CEP esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            CEP.setText("");//Limpa o campo
            CEP.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Endereco.getText())) {//Se campo não esta vazio
            Inserendereco = tf_Endereco.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Endereço esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Endereco.setText("");//Limpa o campo
            tf_Endereco.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Bairro.getText())) {//Se campo não esta vazio
            Inserbairro = tf_Bairro.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Bairro esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Bairro.setText("");//Limpa o campo
            tf_Bairro.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Numero.getText())) {//Se campo não esta vazio
            textonumero = tf_Numero.getText();
            Insernumero = Integer.parseInt(textonumero);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Número esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Numero.setText("");//Limpa o campo
            tf_Numero.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Complemento.getText())) {//Se campo não esta vazio
            Insercomplemento = tf_Complemento.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Complemento esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Complemento.setText("");//Limpa o campo
            tf_Complemento.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(cb_Cidade.getSelectedItem())) {//Se campo não esta vazio
            Insercidade = (String) cb_Cidade.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Cidade esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            cb_Cidade.setSelectedItem("Selecione");//Campo recebe Selecione
            cb_Cidade.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(cb_Estado.getSelectedItem())) {//Se campo não esta vazio
            Inserestado = (String) cb_Estado.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Estado esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            cb_Estado.setSelectedItem("Selecione");//Campo recebe Selecione
            cb_Estado.grabFocus();//Focaliza o campo
        }

        if (!"(  )    -    ".equals(Fone.getText())) {//Se campo não esta vazio
            Inserfone = Fone.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Fone esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            Fone.setText("");//Limpa o campo
            Fone.grabFocus();//Focaliza o campo
        }

        if (!"(  )    -    ".equals(Celular.getText())) {//Se campo não esta vazio
            Insercelular = Celular.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Celular esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            Celular.setText("");//Limpa o campo
            Celular.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Email.getText())) {//Se campo não esta vazio
            Inseremail = tf_Email.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo E-mail esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Email.setText("");//Limpa o campo
            tf_Email.grabFocus();//Focaliza o campo
        }

        if (InserSeleciona == null) {
            if (InserArrayDescricao == null) {
                JOptionPane.showMessageDialog(null, "Campo Descrição esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            } else {
                Inserdescricao = InserArrayDescricao;
            }
        } else {
            Inserdescricao = InserSeleciona;
        }

        if (!"".equals(tf_Codigo.getText()) & !"".equals(tf_Nome.getText()) & !"   .   .   ".equals(RG.getText()) & !"   .   .   -  ".equals(CPF.getText()) & !"".equals(tf_Profissao.getText()) & !"".equals(tf_Empresa.getText()) & !"(  )    -    ".equals(Fone_Empresa.getText()) & !"Selecione".equals(cb_Sexo.getSelectedItem()) & !"     -   ".equals(CEP.getText()) & !"".equals(tf_Endereco.getText()) & !"".equals(tf_Bairro.getText()) & !"".equals(tf_Numero.getText()) & !"".equals(tf_Complemento.getText()) & !"Selecione".equals(cb_Cidade.getSelectedItem()) & !"Selecione".equals(cb_Estado.getSelectedItem()) & !"(  )    -    ".equals(Fone.getText()) & !"(  )    -    ".equals(Celular.getText()) & !"".equals(tf_Email.getText()) & Inserdescricao != null) {
            ArrayCliente aux = new ArrayCliente(Insernome, Inserrg, Insercpf, Inserprofissao, Inserempresa, Inserfone_empresa, Insersexo, Insercep, Inserendereco, Inserbairro, Insercomplemento, Insercidade, Inserestado, Inseremail, Inserfone, Insercelular, Inserdescricao, Insercodigo, Insernumero);
            lista.add(aux);
            JOptionPane.showMessageDialog(null, "Número de Clientes cadastrados: " + lista.size(), "Entrada de Dados", JOptionPane.WARNING_MESSAGE);
            LimparTela();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel executar operação no sistema", "Erro Fatal", JOptionPane.ERROR_MESSAGE);
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
                    ArrayCliente aux = lista.get(i);
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
            FileOutputStream arqDados = new FileOutputStream("Cliente.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Cliente.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista = (ArrayList<ArrayCliente>) entra.readObject();
            entra.close();
            int codigoint = lista.size();
            String codigoString = Integer.toString(codigoint + 1);
            tf_Codigo.setText(codigoString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivoCidade() {
        try {
            FileInputStream Arq = new FileInputStream("Cidade.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista2 = (ArrayList<ArrayCidade>) entra.readObject();
            entra.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void botaoAbrirPressionado() {
        //instanciar a tela Descricao
        DescricaoCliente aux = new DescricaoCliente();
        //registrar um listener pra ela
        aux.setListener(new InterfaceClientes() {

            //invocado pela tela Descricao quando o botão dela for pressionado
            public void campoTextoAlterado(String novoTexto) {
                //atualizar o campo de texto desta tela com o texto vindo da outra
                InserArrayDescricao = novoTexto;
            }
        });
        //Abrir
        aux.setVisible(true);
    }

    private class Manipula implements ItemListener {

        public void itemStateChanged(ItemEvent evento) {
            if (evento.getSource() == Seleciona) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    InserSeleciona = " Sem Descrição";
                } else {
                    InserSeleciona = null;
                }
            }
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
            if (evento.getSource() == RG) {
                RG.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == CPF) {
                CPF.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Profissao) {
                tf_Profissao.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Empresa) {
                tf_Empresa.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == Fone_Empresa) {
                Fone_Empresa.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == cb_Sexo) {
                cb_Sexo.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == CEP) {
                CEP.setBackground(Color.YELLOW);
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
            if (evento.getSource() == tf_Complemento) {
                tf_Complemento.setBackground(Color.YELLOW);
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
            if (evento.getSource() == Celular) {
                Celular.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Email) {
                tf_Email.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tf_Codigo.setBackground(Color.WHITE);
            tf_Nome.setBackground(Color.WHITE);
            RG.setBackground(Color.WHITE);
            CPF.setBackground(Color.WHITE);
            tf_Profissao.setBackground(Color.WHITE);
            tf_Empresa.setBackground(Color.WHITE);
            Fone_Empresa.setBackground(Color.WHITE);
            cb_Sexo.setBackground(Color.WHITE);
            CEP.setBackground(Color.WHITE);
            tf_Endereco.setBackground(Color.WHITE);
            tf_Bairro.setBackground(Color.WHITE);
            tf_Numero.setBackground(Color.WHITE);
            tf_Complemento.setBackground(Color.WHITE);
            cb_Cidade.setBackground(Color.WHITE);
            cb_Estado.setBackground(Color.WHITE);
            Fone.setBackground(Color.WHITE);
            Celular.setBackground(Color.WHITE);
            tf_Email.setBackground(Color.WHITE);
        }
    }
}

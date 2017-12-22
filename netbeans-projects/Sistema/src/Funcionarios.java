
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class Funcionarios extends JFrame {

    private ArrayList<ArrayFuncionario> lista = new ArrayList();
    private ArrayList<ArraySetor> lista2 = new ArrayList();
    private ArrayList<ArrayCidade> lista3 = new ArrayList();
    private JTextField tf_Codigo, tf_Nome, tf_Endereco, tf_Numero, tf_Bairro, tf_Email, tf_SalarioInicial, tf_Aumento, tf_INSS, tf_Salariototal, tf_Complemento;
    private JButton Ok, Cancelar, Pesquisar, Ver;
    private JFormattedTextField Dataadmissao, CPF, RG, CEP, Fone, Celular, Datanascimento;
    private JComboBox cb_Sexo, cb_Funcao, cb_Cidade, cb_Estado;
    private MaskFormatter mascaraDataadmissao, mascaraCPF, mascaraRG, mascaraCEP, mascaraFone, mascaraCelular, mascaraDatanascimento;
    private JRadioButton Novo, Alterar, Excluir;
    private int INTNovo = 0, INTAlterar = 0, INTExluir = 0;

    Funcionarios() {
        super("Cadastro de Funcionarios");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        Manipula manuseia = new Manipula();
        TrataEventos trata = new TrataEventos();
        TrataCores cores = new TrataCores();
        JPanel Painel1 = new JPanel();
        Painel1.setLayout(null);
        Painel1.setBounds(10, 10, 425, 340);
        tela.add(Painel1);
        Painel1.setBorder(BorderFactory.createTitledBorder("Funcionários"));
        LerArquivoCidade();
        LerArquivoSetor();

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

        JLabel lb_dataadmissao = new JLabel("Data Admissão");
        lb_dataadmissao.setBounds(20, 78, 90, 14);
        lb_dataadmissao.setFont(fonte);
        Painel1.add(lb_dataadmissao);

        try {
            mascaraDataadmissao = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
        }
        Dataadmissao = new JFormattedTextField(mascaraDataadmissao);
        Dataadmissao.setBounds(20, 94, 110, 20);
        Painel1.add(Dataadmissao);
        Dataadmissao.addFocusListener(cores);

        JLabel lb_Funcao = new JLabel("Função");
        lb_Funcao.setBounds(140, 78, 80, 14);
        lb_Funcao.setFont(fonte);
        Painel1.add(lb_Funcao);

        cb_Funcao = new JComboBox();
        cb_Funcao.setBounds(140, 94, 110, 20);
        cb_Funcao.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        for (int i = 0; i < lista2.size(); i++) {
            ArraySetor aux = lista2.get(i);
            if (lista2.size() != 0) {//Se lista.size for diferente de zero
                String n = aux.getNome();
                cb_Funcao.addItem(n);
            }
        }
        cb_Funcao.setBackground(cor);
        cb_Funcao.setFont(fonte);
        Painel1.add(cb_Funcao);
        cb_Funcao.addFocusListener(cores);

        JLabel lb_CPF = new JLabel("CPF");
        lb_CPF.setBounds(260, 78, 80, 14);
        lb_CPF.setFont(fonte);
        Painel1.add(lb_CPF);

        try {
            mascaraCPF = new MaskFormatter("###.###.###-##");
        } catch (ParseException excp) {
        }
        CPF = new JFormattedTextField(mascaraCPF);
        CPF.setBounds(260, 94, 123, 20);
        Painel1.add(CPF);
        CPF.addFocusListener(cores);

        JLabel lb_RG = new JLabel("RG");
        lb_RG.setBounds(20, 114, 80, 14);
        lb_RG.setFont(fonte);
        Painel1.add(lb_RG);

        try {
            mascaraRG = new MaskFormatter("###.###.###");
        } catch (ParseException excp) {
        }
        RG = new JFormattedTextField(mascaraRG);
        RG.setBounds(20, 130, 110, 20);
        Painel1.add(RG);
        RG.addFocusListener(cores);

        JLabel lb_Sexo = new JLabel("Sexo");
        lb_Sexo.setBounds(140, 114, 110, 14);
        lb_Sexo.setFont(fonte);
        Painel1.add(lb_Sexo);

        cb_Sexo = new JComboBox();
        cb_Sexo.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        cb_Sexo.addItem("Masculino");
        cb_Sexo.addItem("Feminino");
        cb_Sexo.setBounds(140, 130, 110, 20);
        cb_Sexo.setFont(fonte);
        cb_Sexo.setBackground(cor);
        Painel1.add(cb_Sexo);
        cb_Sexo.addFocusListener(cores);

        JLabel lb_Endereco = new JLabel("Endereço");
        lb_Endereco.setBounds(260, 114, 80, 14);
        lb_Endereco.setFont(fonte);
        Painel1.add(lb_Endereco);

        tf_Endereco = new JTextField();
        tf_Endereco.setBounds(260, 130, 120, 20);
        Painel1.add(tf_Endereco);
        tf_Endereco.addFocusListener(cores);

        JLabel lb_Numero = new JLabel("Nº");
        lb_Numero.setBounds(20, 150, 80, 14);
        lb_Numero.setFont(fonte);
        Painel1.add(lb_Numero);

        tf_Numero = new JTextField();
        tf_Numero.setBounds(20, 166, 110, 20);
        tf_Numero.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        Painel1.add(tf_Numero);
        tf_Numero.addFocusListener(cores);

        JLabel lb_Bairro = new JLabel("Bairro");
        lb_Bairro.setBounds(140, 150, 80, 14);
        lb_Bairro.setFont(fonte);
        Painel1.add(lb_Bairro);

        tf_Bairro = new JTextField();
        tf_Bairro.setBounds(140, 166, 110, 20);
        Painel1.add(tf_Bairro);
        tf_Bairro.addFocusListener(cores);

        JLabel lb_CEP = new JLabel("CEP");
        lb_CEP.setBounds(260, 150, 80, 14);
        lb_CEP.setFont(fonte);
        Painel1.add(lb_CEP);

        try {
            mascaraCEP = new MaskFormatter("#####-###");
        } catch (ParseException excp) {
        }
        CEP = new JFormattedTextField(mascaraCEP);
        CEP.setBounds(260, 166, 120, 20);
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

        JLabel lb_Complemento = new JLabel("Complemento");
        lb_Complemento.setBounds(260, 185, 80, 14);
        lb_Complemento.setFont(fonte);
        Painel1.add(lb_Complemento);

        tf_Complemento = new JTextField();
        tf_Complemento.setBounds(260, 200, 120, 20);
        Painel1.add(tf_Complemento);
        tf_Complemento.addFocusListener(cores);

        JLabel lb_Fone = new JLabel("Fone");
        lb_Fone.setBounds(20, 218, 80, 14);
        lb_Fone.setFont(fonte);
        Painel1.add(lb_Fone);

        try {
            mascaraFone = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        Fone = new JFormattedTextField(mascaraFone);
        Fone.setBounds(20, 233, 110, 20);
        Painel1.add(Fone);
        Fone.addFocusListener(cores);

        JLabel lb_Celular = new JLabel("Celular");
        lb_Celular.setBounds(140, 218, 80, 14);
        lb_Celular.setFont(fonte);
        Painel1.add(lb_Celular);

        try {
            mascaraCelular = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        Celular = new JFormattedTextField(mascaraCelular);
        Celular.setBounds(140, 233, 110, 20);
        Painel1.add(Celular);
        Celular.addFocusListener(cores);

        JLabel lb_Datanascimento = new JLabel("Data Nascimento");
        lb_Datanascimento.setBounds(260, 218, 100, 14);
        lb_Datanascimento.setFont(fonte);
        Painel1.add(lb_Datanascimento);

        try {
            mascaraDatanascimento = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
        }
        Datanascimento = new JFormattedTextField(mascaraDatanascimento);
        Datanascimento.setBounds(260, 233, 120, 20);
        Painel1.add(Datanascimento);
        Datanascimento.addFocusListener(cores);

        JLabel lb_SalarioInicial = new JLabel("Salário Inicial");
        lb_SalarioInicial.setBounds(20, 252, 80, 14);
        lb_SalarioInicial.setFont(fonte);
        Painel1.add(lb_SalarioInicial);

        tf_SalarioInicial = new JTextField();
        tf_SalarioInicial.setBounds(20, 268, 110, 20);
        Painel1.add(tf_SalarioInicial);
        tf_SalarioInicial.addFocusListener(cores);

        JLabel lb_Aumento = new JLabel("Aumento");
        lb_Aumento.setBounds(140, 252, 80, 14);
        lb_Aumento.setFont(fonte);
        Painel1.add(lb_Aumento);

        tf_Aumento = new JTextField();
        tf_Aumento.setBounds(140, 268, 110, 20);
        Painel1.add(tf_Aumento);
        tf_Aumento.addFocusListener(cores);

        JLabel lb_INSS = new JLabel("INSS");
        lb_INSS.setBounds(260, 252, 80, 14);
        lb_INSS.setFont(fonte);
        Painel1.add(lb_INSS);

        tf_INSS = new JTextField();
        tf_INSS.setBounds(260, 268, 120, 20);
        Painel1.add(tf_INSS);
        tf_INSS.addFocusListener(cores);
        tf_INSS.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Calcula();
            }
        });

        JLabel lb_Salariototal = new JLabel("Salário Total");
        lb_Salariototal.setBounds(20, 287, 110, 14);
        lb_Salariototal.setFont(fonte);
        Painel1.add(lb_Salariototal);

        tf_Salariototal = new JTextField();
        tf_Salariototal.setBounds(20, 303, 110, 20);
        Painel1.add(tf_Salariototal);
        tf_Salariototal.addFocusListener(cores);

        JLabel lb_Email = new JLabel("E-mail");
        lb_Email.setBounds(140, 287, 110, 14);
        lb_Email.setFont(fonte);
        Painel1.add(lb_Email);

        tf_Email = new JTextField();
        tf_Email.setBounds(140, 303, 240, 20);
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

        Cancelar = new JButton("Cancelar");
        Cancelar.setBounds(455, 190, 100, 26);
        tela.add(Cancelar);
        Cancelar.addActionListener(trata);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(Painel1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        Painel1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        LerArquivo();
        setResizable(false);
        setSize(585, 402);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setVisible(true);
    }

    private void Ok() {
        int INTTexto = 0;
        if (lista.isEmpty() == true) {
            Gravar();
        } else {
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayFuncionario aux = lista.get(i);
                String Texto = tf_Codigo.getText();
                INTTexto = Integer.parseInt(Texto);
                if (INTTexto == aux.getCodigo()) {
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "O funcionário " + tf_Codigo.getText() + " ja esta inserido na lista deseja Substituilo? ", "Funcionário", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    for (int i = 0; i < lista.size(); i++) {
                        ArrayFuncionario aux = lista.get(i);
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
        cb_Funcao.setSelectedItem("Selecione");//Campo recebe Selecione
        tf_Endereco.setText("");
        Fone.setText("");
        tf_Numero.setText("");
        CEP.setText("");
        tf_Bairro.setText("");
        tf_SalarioInicial.setText("");
        Dataadmissao.setText("");
        cb_Cidade.setSelectedItem("Selecione");//Campo recebe Selecione
        cb_Estado.setSelectedItem("Selecione");//Campo recebe Selecione
        Datanascimento.setText("");
        Celular.setText("");
        tf_Email.setText("");
        tf_Aumento.setText("");
        tf_INSS.setText("");
        tf_Salariototal.setText("");
        tf_Complemento.setText("");
        cb_Sexo.setSelectedItem("Selecione");//Campo recebe Selecione
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
            JOptionPane.showMessageDialog(null, "Não há Funcionarios cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean deuErro;
            do {
                try {
                    deuErro = false;
                    String StringTexto = JOptionPane.showInputDialog(null, "Informe o Codigo do Funcionario a ser Excluído:", "Saída de dados", JOptionPane.WARNING_MESSAGE);
                    INTTexto = Integer.parseInt(StringTexto);//Converção String para Integer
                } catch (NumberFormatException e) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Entre com o numero", "Valor Invalido", JOptionPane.ERROR_MESSAGE);
                }
            } while (deuErro);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayFuncionario aux = lista.get(i);
                if (INTTexto == aux.getCodigo()) {
                    lista.remove(aux);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Funcionario Removido com sucesso", "Funcionario", JOptionPane.INFORMATION_MESSAGE);
                LimparTela();
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Recuperar() {
        int INTTexto = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Funcionarios cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean deuErro;
            do {
                try {
                    deuErro = false;
                    String Texto = JOptionPane.showInputDialog(null, "Insira codigo: ", "Funcionário", JOptionPane.WARNING_MESSAGE);
                    INTTexto = Integer.parseInt(Texto);//Converção String para Integer
                } catch (NumberFormatException e) {
                    deuErro = true;
                    JOptionPane.showMessageDialog(null, "Entre com o numero", "Valor Invalido", JOptionPane.ERROR_MESSAGE);
                }
            } while (deuErro);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayFuncionario aux = lista.get(i);
                if (INTTexto == aux.getCodigo()) {
                    String TextoEmail = aux.getEmail();
                    tf_Email.setText(TextoEmail);
                    int TextoINTCodigo = aux.getCodigo();
                    String TextoCodigo = Integer.toString(TextoINTCodigo);//Conversão int para String
                    tf_Codigo.setText(TextoCodigo);
                    String TextoNome = aux.getNome();
                    tf_Nome.setText(TextoNome);
                    String TextoRG = aux.getRG();
                    RG.setText(TextoRG);
                    String TextoCPF = aux.getCPF();
                    CPF.setText(TextoCPF);
                    cb_Funcao.setSelectedItem(aux.getFuncao());//Recupera do Arraylist e joga na JCombobox
                    String TextoDataAdmissao = aux.getDataAdmissao();
                    Dataadmissao.setText(TextoDataAdmissao);
                    float TextoFLOATAumento = aux.getAumento();
                    String TextoAumento = Float.toString(TextoFLOATAumento);//Coversão float para String
                    tf_Aumento.setText(TextoAumento);
                    cb_Sexo.setSelectedItem(aux.getSexo());//Recupera do Arraylist e joga na JCombobox
                    String TextoCEP = aux.getCEP();
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
                    float TextoFLOATSalarioInicial = aux.getSalarioIncial();
                    String TextoSalarioInicial = Float.toString(TextoFLOATSalarioInicial);//Coversão float para String
                    tf_SalarioInicial.setText(TextoSalarioInicial);
                    String TextoDataNascimento = aux.getDataNascimento();
                    Datanascimento.setText(TextoDataNascimento);
                    float TextoFLOATINSS = aux.getInss();
                    String TextoINSS = Float.toString(TextoFLOATINSS);//Coversão float para String
                    tf_INSS.setText(TextoINSS);
                    float TextoFLOATSalarioTotal = aux.getSalarioTotal();
                    String TextoSalarioTotal = Float.toString(TextoFLOATSalarioTotal);//Coversão float para String
                    tf_Salariototal.setText(TextoSalarioTotal);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "Funcionario Encontrado e Recuperado da Lista", "Funcionario", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Funcionario não encontrado na lista", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Ver() {
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Funcionarios cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            VerFuncionario aux = new VerFuncionario(lista);
        }
    }

    private void Quem() {
        int Codigo = 0;
        if (lista.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Não há Funcionarios cadastrados na lista", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String Texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Funcionario", JOptionPane.WARNING_MESSAGE);
            boolean encontrado = false;
            for (int i = 0; i < lista.size(); i++) {
                ArrayFuncionario aux = lista.get(i);
                if (Texto.equalsIgnoreCase(aux.getNome())) {//Não importa se texto digitado esta em maiusculo ou minusculo
                    Codigo = aux.getCodigo();
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                JOptionPane.showMessageDialog(null, "O Funcionario " + Texto + " esta inserido com o codigo " + Codigo, "Funcionario", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "O Funcionario " + Texto + " não esta inserido", "Funcionario", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void Gravar() {
        String textocod, textonum, textosalin, textoaum, textoins, textosalto, InserNome = "", InserFuncao = "", InserEndereco = "", InserBairro = "", InserCidade = "", InserEstado = "", InserEmail = "", InserDataAdmissao = "", InserCPF = "", InserRG = "", InserCEP = "", InserFone = "", InserCelular = "", InserDataNascimento = "", InserComplemento = "", InserSexo = "";
        int InserCodigo = 0, InserNumero = 0;
        float InserSalarioIncial = 0, InserAumento = 0, InserInss = 0, InserSalarioTotal = 0;

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

        if (!"   .   .   ".equals(RG.getText())) {//Se campo não esta vazio
            InserRG = RG.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo RG esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            RG.setText("");//Limpa o campo
            RG.grabFocus();//Focaliza o campo
        }

        if (!"   .   .   -  ".equals(CPF.getText())) {//Se campo não esta vazio
            InserCPF = CPF.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo CPF esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            CPF.setText("");//Limpa o campo
            CPF.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(cb_Funcao.getSelectedItem())) {//Se campo não esta vazio
            InserFuncao = (String) cb_Funcao.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Função esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            cb_Funcao.setSelectedItem("Selecione");//Campo recebe Selecione
            cb_Funcao.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Endereco.getText())) {//Se campo não esta vazio
            InserEndereco = tf_Endereco.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Endereco esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Endereco.setText("");//Limpa o campo
            tf_Endereco.grabFocus();//Focaliza o campo
        }

        if (!"(  )    -    ".equals(Fone.getText())) {//Se campo não esta vazio
            InserFone = Fone.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Fone esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            Fone.setText("");//Limpa o campo
            Fone.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Numero.getText())) {//Se campo não esta vazio
            textonum = tf_Numero.getText();
            InserNumero = Integer.parseInt(textonum);//Conversão de String para Integer
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Numero esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Numero.setText("");
            tf_Numero.grabFocus();//Focaliza o campo
        }

        if (!"     -   ".equals(CEP.getText())) {//Se campo não esta vazio
            InserCEP = CEP.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo CEP esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            CEP.setText("");//Limpa o campo
            CEP.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Bairro.getText())) {//Se campo não esta vazio
            InserBairro = tf_Bairro.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Bairro esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Bairro.setText("");//Limpa o campo
            tf_Bairro.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_SalarioInicial.getText())) {//Se campo não esta vazio
            textosalin = tf_SalarioInicial.getText();
            InserSalarioIncial = Float.parseFloat(textosalin);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Salário Inicial esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_SalarioInicial.setText("");//Limpa o campo
            tf_SalarioInicial.grabFocus();//Focaliza o campo
        }

        if (!"  /  /    ".equals(Dataadmissao.getText())) {//Se campo não esta vazio
            InserDataAdmissao = Dataadmissao.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Data Admissão esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            Dataadmissao.setText("");//Limpa o campo
            Dataadmissao.grabFocus();//Focaliza o campo
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

        if (!"  /  /    ".equals(Datanascimento.getText())) {//Se campo não esta vazio
            InserDataNascimento = Datanascimento.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Data de Nascimento esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            Datanascimento.setText("");//Limpa o campo
            Datanascimento.grabFocus();//Focaliza o campo
        }

        if (!"(  )    -    ".equals(Celular.getText())) {//Se campo não esta vazio
            InserCelular = Celular.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Celular esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            Celular.setText("");//Limpa o campo
            Celular.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Email.getText())) {//Se campo não esta vazio
            InserEmail = tf_Email.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo E-mail esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Email.setText("");//Limpa o campo
            tf_Email.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Aumento.getText())) {//Se campo não esta vazio
            textoaum = tf_Aumento.getText();
            InserAumento = Float.parseFloat(textoaum);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Aumento esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Aumento.setText("");//Limpa o campo
            tf_Aumento.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_INSS.getText())) {//Se campo não esta vazio
            textoins = tf_INSS.getText();
            InserInss = Float.parseFloat(textoins);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo INSS esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_INSS.setText("");//Limpa o campo
            tf_INSS.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Salariototal.getText())) {//Se campo não esta vazio
            textosalto = tf_Salariototal.getText();
            InserSalarioTotal = Float.parseFloat(textosalto);//Conversão de String para Float
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Salario Total esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Salariototal.setText("");//Limpa o campo
            tf_Salariototal.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Complemento.getText())) {//Se campo não esta vazio
            InserComplemento = tf_Complemento.getText();
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Complemento esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            tf_Complemento.setText("");//Limpa o campo
            tf_Complemento.grabFocus();//Focaliza o campo
        }

        if (!"Selecione".equals(cb_Sexo.getSelectedItem())) {//Se campo não esta vazio
            InserSexo = (String) cb_Sexo.getSelectedItem();//Converte para String e joga para Insersexo
        } else {//se estiver vazio
            JOptionPane.showMessageDialog(null, "Campo Sexo esta em branco", "Entrada de Dados", JOptionPane.ERROR_MESSAGE);
            cb_Sexo.setSelectedItem("Selecione");//Campo recebe Selecione
            cb_Sexo.grabFocus();//Focaliza o campo
        }

        if (!"".equals(tf_Codigo.getText()) & !"".equals(tf_Nome.getText()) & !"   .   .   ".equals(RG.getText()) & !"   .   .   -  ".equals(CPF.getText()) & !"Selecione".equals(cb_Funcao.getSelectedItem()) & !"".equals(tf_Endereco.getText()) & !"(  )    -    ".equals(Fone.getText()) & !"".equals(tf_Numero.getText()) & !"     -   ".equals(CEP.getText()) & !"".equals(tf_Bairro.getText()) & !"".equals(tf_SalarioInicial.getText()) & !"  /  /    ".equals(Dataadmissao.getText()) & !"Selecione".equals(cb_Cidade.getSelectedItem()) & !"Selecione".equals(cb_Estado.getSelectedItem()) & !"  /  /    ".equals(Datanascimento.getText()) & !"(  )    -    ".equals(Celular.getText()) & !"".equals(tf_Email.getText()) & !"".equals(tf_Aumento.getText()) & !"".equals(tf_INSS.getText()) & !"".equals(tf_Salariototal.getText()) & !"".equals(tf_Complemento.getText()) & !"Selecione".equals(cb_Sexo.getSelectedItem())) {
            ArrayFuncionario aux = new ArrayFuncionario(InserNome, InserFuncao, InserEndereco, InserBairro, InserCidade, InserEstado, InserEmail, InserDataAdmissao, InserCPF, InserRG, InserCEP, InserFone, InserCelular, InserComplemento, InserSexo, InserDataNascimento, InserCodigo, InserNumero, InserSalarioIncial, InserAumento, InserInss, InserSalarioTotal);
            lista.add(aux);
            JOptionPane.showMessageDialog(null, "Número de Funcionários cadastrados: " + lista.size(), "Entrada de Dados", JOptionPane.WARNING_MESSAGE);
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
                    ArrayFuncionario aux = lista.get(i);
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
        String textoSalarioInicial, textoSalarioInicial2, textoAumento, textoINSS, textoValor_Total, texto = "";
        float SalarioInicial = 0, SalarioInicial2 = 0, Aumento = 0, INSS = 0, Valor_Total1 = 0, Valor_Total2 = 0, Valor_Total3 = 0;

        if (!"".equals(tf_SalarioInicial.getText()) & !"".equals(tf_Aumento.getText()) & !"".equals(tf_INSS.getText())) {
            textoSalarioInicial = tf_SalarioInicial.getText();
            SalarioInicial = Float.parseFloat(textoSalarioInicial);//Conversão de String para Float
            textoAumento = tf_Aumento.getText();
            Aumento = Float.parseFloat(textoAumento);//Conversão de String para Float
            textoINSS = tf_INSS.getText();
            INSS = Float.parseFloat(textoINSS);//Conversão de String para Float

            Valor_Total1 = ((SalarioInicial / 100) * Aumento);
            textoSalarioInicial2 = tf_SalarioInicial.getText();
            SalarioInicial2 = Float.parseFloat(textoSalarioInicial2);//Conversão de String para Float
            Valor_Total2 = (SalarioInicial2 + Valor_Total1) - INSS;
            texto = Float.toString(Valor_Total2);//Conversão de float para String
            tf_Salariototal.setText(texto);
        }
    }

    private void Arquivo() {
        try {
            FileOutputStream arqDados = new FileOutputStream("Funcionario.dat");
            ObjectOutputStream ou = new ObjectOutputStream(arqDados);
            ou.writeObject(lista);
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LerArquivo() {
        try {
            FileInputStream Arq = new FileInputStream("Funcionario.dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista = (ArrayList<ArrayFuncionario>) entra.readObject();
            entra.close();
            int codigoint = lista.size();
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
            if (evento.getSource() == Cancelar) {
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
            if (evento.getSource() == Dataadmissao) {
                Dataadmissao.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == cb_Funcao) {
                cb_Funcao.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == CPF) {
                CPF.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == RG) {
                RG.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == cb_Sexo) {
                cb_Sexo.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Endereco) {
                tf_Endereco.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Numero) {
                tf_Numero.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Bairro) {
                tf_Bairro.setBackground(Color.YELLOW);
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
            if (evento.getSource() == tf_Complemento) {
                tf_Complemento.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == Fone) {
                Fone.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == Celular) {
                Celular.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == Datanascimento) {
                Datanascimento.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_SalarioInicial) {
                tf_SalarioInicial.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Aumento) {
                tf_Aumento.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_INSS) {
                tf_INSS.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Salariototal) {
                tf_Salariototal.setBackground(Color.YELLOW);
            }
            if (evento.getSource() == tf_Email) {
                tf_Email.setBackground(Color.YELLOW);
            }
        }

        public void focusLost(FocusEvent evento) {//Perde Focus
            tf_Codigo.setBackground(Color.WHITE);
            tf_Nome.setBackground(Color.WHITE);
            Dataadmissao.setBackground(Color.WHITE);
            cb_Funcao.setBackground(Color.WHITE);
            CPF.setBackground(Color.WHITE);
            RG.setBackground(Color.WHITE);
            cb_Sexo.setBackground(Color.WHITE);
            tf_Endereco.setBackground(Color.WHITE);
            tf_Numero.setBackground(Color.WHITE);
            tf_Bairro.setBackground(Color.WHITE);
            CEP.setBackground(Color.WHITE);
            cb_Cidade.setBackground(Color.WHITE);
            cb_Estado.setBackground(Color.WHITE);
            tf_Complemento.setBackground(Color.WHITE);
            Fone.setBackground(Color.WHITE);
            Celular.setBackground(Color.WHITE);
            Datanascimento.setBackground(Color.WHITE);
            tf_SalarioInicial.setBackground(Color.WHITE);
            tf_Aumento.setBackground(Color.WHITE);
            tf_INSS.setBackground(Color.WHITE);
            tf_Salariototal.setBackground(Color.WHITE);
            tf_Email.setBackground(Color.WHITE);
        }
    }
}

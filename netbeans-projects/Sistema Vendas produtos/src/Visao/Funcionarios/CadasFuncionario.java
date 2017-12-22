package Visao.Funcionarios;

import Arquivos.*;
import ClassPadrao.*;
import Controle.CidadeControl;
import Controle.FuncionarioControl;
import Controle.SetorControl;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

public class CadasFuncionario extends JDialog implements ActionListener, FocusListener, ItemListener {

    private JTextField tfCodigo, tfNome, tfEndereco, tfNumero, tfBairro, tfEmail, tfSalarioInicial, tfAumento, tfINSS, tfSalarioTotal, tfComplemento;
    private JButton btOk, btCancelar, btPesquisar, btVer;
    private JFormattedTextField ftfDataAdmissao, ftfCPF, ftfRG, ftfCEP, ftfFone, ftfCelular, ftfDatanascimento;
    private JComboBox cbSexo, cbFuncao, cbCidade, cbEstado;
    private MaskFormatter mascaraDataadmissao, mascaraCPF, mascaraRG, mascaraCEP, mascaraFone, mascaraCelular, mascaraDatanascimento;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private FuncionarioControl control;
    private Arquivo arquivo;
    private LerArquivo lerArquivo;
    private SetorControl controlSetor;
    private CidadeControl controlCidade;
    private JTextFieldDouble jTextFieldDouble;

    public CadasFuncionario() {
        setTitle("Cadastro de Funcionarios");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(10, 10, 425, 340);
        tela.add(painel);
        painel.setBorder(BorderFactory.createTitledBorder("Funcionários"));
        control = new FuncionarioControl();
        controlSetor = new SetorControl();
        controlCidade = new CidadeControl();
        arquivo = new Arquivo();
        lerArquivo = new LerArquivo();
        jTextFieldDouble = new JTextFieldDouble();
        controlSetor.lerArquivo(lerArquivo);
        controlCidade.lerArquivo(lerArquivo);
        control.lerArquivo(lerArquivo);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 40, 80, 14);
        lbCodigo.setFont(fonte);
        painel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 58, 80, 20);
        tfCodigo.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        painel.add(tfCodigo);
        tfCodigo.addFocusListener(this);

        btVer = new JButton("...");
        btVer.setBounds(104, 56, 31, 24);
        painel.add(btVer);
        btVer.addActionListener(this);

        JLabel lbNome = new JLabel("Nome");
        lbNome.setBounds(140, 40, 80, 14);
        lbNome.setFont(fonte);
        painel.add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(140, 58, 243, 20);
        painel.add(tfNome);
        tfNome.addFocusListener(this);

        JLabel lbDataAdmissao = new JLabel("Data Admissão");
        lbDataAdmissao.setBounds(20, 78, 90, 14);
        lbDataAdmissao.setFont(fonte);
        painel.add(lbDataAdmissao);

        try {
            mascaraDataadmissao = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
        }
        ftfDataAdmissao = new JFormattedTextField(mascaraDataadmissao);
        ftfDataAdmissao.setBounds(20, 94, 110, 20);
        painel.add(ftfDataAdmissao);
        ftfDataAdmissao.addFocusListener(this);

        JLabel lbFuncao = new JLabel("Função");
        lbFuncao.setBounds(140, 78, 80, 14);
        lbFuncao.setFont(fonte);
        painel.add(lbFuncao);

        cbFuncao = new JComboBox();
        cbFuncao.setBounds(140, 94, 110, 20);
        cbFuncao.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        ArrayList listaSetor = new ArrayList();
        listaSetor = controlSetor.setor();
        for (int i = 0; i < listaSetor.size(); i++) {
            String n1 = (String) listaSetor.get(i);
            cbFuncao.addItem(n1);
        }
        cbFuncao.setBackground(cor);
        cbFuncao.setFont(fonte);
        painel.add(cbFuncao);
        cbFuncao.addFocusListener(this);

        JLabel lbCPF = new JLabel("CPF");
        lbCPF.setBounds(260, 78, 80, 14);
        lbCPF.setFont(fonte);
        painel.add(lbCPF);

        try {
            mascaraCPF = new MaskFormatter("###.###.###-##");
        } catch (ParseException excp) {
        }
        ftfCPF = new JFormattedTextField(mascaraCPF);
        ftfCPF.setBounds(260, 94, 123, 20);
        painel.add(ftfCPF);
        ftfCPF.addFocusListener(this);

        JLabel lbRG = new JLabel("RG");
        lbRG.setBounds(20, 114, 80, 14);
        lbRG.setFont(fonte);
        painel.add(lbRG);

        try {
            mascaraRG = new MaskFormatter("###.###.###");
        } catch (ParseException excp) {
        }
        ftfRG = new JFormattedTextField(mascaraRG);
        ftfRG.setBounds(20, 130, 110, 20);
        painel.add(ftfRG);
        ftfRG.addFocusListener(this);

        JLabel lbSexo = new JLabel("Sexo");
        lbSexo.setBounds(140, 114, 110, 14);
        lbSexo.setFont(fonte);
        painel.add(lbSexo);

        cbSexo = new JComboBox();
        cbSexo.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        cbSexo.addItem("Masculino");
        cbSexo.addItem("Feminino");
        cbSexo.setBounds(140, 130, 110, 20);
        cbSexo.setFont(fonte);
        cbSexo.setBackground(cor);
        painel.add(cbSexo);
        cbSexo.addFocusListener(this);

        JLabel lbEndereco = new JLabel("Endereço");
        lbEndereco.setBounds(260, 114, 80, 14);
        lbEndereco.setFont(fonte);
        painel.add(lbEndereco);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(260, 130, 120, 20);
        painel.add(tfEndereco);
        tfEndereco.addFocusListener(this);

        JLabel lbNumero = new JLabel("Nº");
        lbNumero.setBounds(20, 150, 80, 14);
        lbNumero.setFont(fonte);
        painel.add(lbNumero);

        tfNumero = new JTextField();
        tfNumero.setBounds(20, 166, 110, 20);
        tfNumero.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        painel.add(tfNumero);
        tfNumero.addFocusListener(this);

        JLabel lbBairro = new JLabel("Bairro");
        lbBairro.setBounds(140, 150, 80, 14);
        lbBairro.setFont(fonte);
        painel.add(lbBairro);

        tfBairro = new JTextField();
        tfBairro.setBounds(140, 166, 110, 20);
        painel.add(tfBairro);
        tfBairro.addFocusListener(this);

        JLabel lbCEP = new JLabel("CEP");
        lbCEP.setBounds(260, 150, 80, 14);
        lbCEP.setFont(fonte);
        painel.add(lbCEP);

        try {
            mascaraCEP = new MaskFormatter("#####-###");
        } catch (ParseException excp) {
        }
        ftfCEP = new JFormattedTextField(mascaraCEP);
        ftfCEP.setBounds(260, 166, 120, 20);
        painel.add(ftfCEP);
        ftfCEP.addFocusListener(this);

        JLabel lbCidade = new JLabel("Cidade");
        lbCidade.setBounds(20, 185, 80, 14);
        lbCidade.setFont(fonte);
        painel.add(lbCidade);

        cbCidade = new JComboBox();
        cbCidade.setBounds(20, 200, 110, 20);
        cbCidade.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        ArrayList listaCidade = new ArrayList();
        listaCidade = controlCidade.cidade();
        for (int i = 0; i < listaCidade.size(); i++) {
            String n2 = (String) listaCidade.get(i);
            cbCidade.addItem(n2);
        }
        cbCidade.setBackground(cor);
        cbCidade.setFont(fonte);
        painel.add(cbCidade);
        cbCidade.addFocusListener(this);

        JLabel lbEstado = new JLabel("Estado");
        lbEstado.setBounds(140, 185, 80, 14);
        lbEstado.setFont(fonte);
        painel.add(lbEstado);

        cbEstado = new JComboBox();
        cbEstado.setBounds(140, 200, 110, 20);
        cbEstado.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        ArrayList listaEstado = new ArrayList();
        listaEstado = controlCidade.estado();
        for (int i = 0; i < listaEstado.size(); i++) {
            String n3 = (String) listaEstado.get(i);
            cbEstado.addItem(n3);
        }
        cbEstado.setBackground(cor);
        cbEstado.setFont(fonte);
        painel.add(cbEstado);
        cbEstado.addFocusListener(this);

        JLabel lbComplemento = new JLabel("Complemento");
        lbComplemento.setBounds(260, 185, 80, 14);
        lbComplemento.setFont(fonte);
        painel.add(lbComplemento);

        tfComplemento = new JTextField();
        tfComplemento.setBounds(260, 200, 120, 20);
        painel.add(tfComplemento);
        tfComplemento.addFocusListener(this);

        JLabel lbFone = new JLabel("Fone");
        lbFone.setBounds(20, 218, 80, 14);
        lbFone.setFont(fonte);
        painel.add(lbFone);

        try {
            mascaraFone = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfFone = new JFormattedTextField(mascaraFone);
        ftfFone.setBounds(20, 233, 110, 20);
        painel.add(ftfFone);
        ftfFone.addFocusListener(this);

        JLabel lbCelular = new JLabel("Celular");
        lbCelular.setBounds(140, 218, 80, 14);
        lbCelular.setFont(fonte);
        painel.add(lbCelular);

        try {
            mascaraCelular = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfCelular = new JFormattedTextField(mascaraCelular);
        ftfCelular.setBounds(140, 233, 110, 20);
        painel.add(ftfCelular);
        ftfCelular.addFocusListener(this);

        JLabel lbDatanascimento = new JLabel("Data Nascimento");
        lbDatanascimento.setBounds(260, 218, 100, 14);
        lbDatanascimento.setFont(fonte);
        painel.add(lbDatanascimento);

        try {
            mascaraDatanascimento = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
        }
        ftfDatanascimento = new JFormattedTextField(mascaraDatanascimento);
        ftfDatanascimento.setBounds(260, 233, 120, 20);
        painel.add(ftfDatanascimento);
        ftfDatanascimento.addFocusListener(this);

        JLabel lbSalarioInicial = new JLabel("Salário Inicial");
        lbSalarioInicial.setBounds(20, 252, 80, 14);
        lbSalarioInicial.setFont(fonte);
        painel.add(lbSalarioInicial);

        tfSalarioInicial = new JTextField();
        tfSalarioInicial.setBounds(20, 268, 110, 20);
        painel.add(tfSalarioInicial);
        tfSalarioInicial.addFocusListener(this);

        JLabel lbAumento = new JLabel("Aumento");
        lbAumento.setBounds(140, 252, 80, 14);
        lbAumento.setFont(fonte);
        painel.add(lbAumento);

        tfAumento = new JTextField();
        tfAumento.setBounds(140, 268, 110, 20);
        painel.add(tfAumento);
        tfAumento.addFocusListener(this);

        JLabel lbINSS = new JLabel("INSS");
        lbINSS.setBounds(260, 252, 80, 14);
        lbINSS.setFont(fonte);
        painel.add(lbINSS);

        tfINSS = new JTextField();
        tfINSS.setBounds(260, 268, 120, 20);
        painel.add(tfINSS);
        tfINSS.addFocusListener(this);

        JLabel lbSalariototal = new JLabel("Salário Total");
        lbSalariototal.setBounds(20, 287, 110, 14);
        lbSalariototal.setFont(fonte);
        painel.add(lbSalariototal);

        tfSalarioTotal = new JTextField();
        tfSalarioTotal.setBounds(20, 303, 110, 20);
        painel.add(tfSalarioTotal);
        tfSalarioTotal.addFocusListener(this);

        JLabel lbEmail = new JLabel("E-mail");
        lbEmail.setBounds(140, 287, 110, 14);
        lbEmail.setFont(fonte);
        painel.add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(140, 303, 240, 20);
        painel.add(tfEmail);
        tfEmail.addFocusListener(this);

        btPesquisar = new JButton("Pesquisar");
        btPesquisar.setBounds(455, 25, 100, 26);
        tela.add(btPesquisar);
        btPesquisar.addActionListener(this);

        rbNovo = new JRadioButton("Novo");
        rbNovo.setBounds(458, 70, 80, 20);
        rbNovo.setFont(fonte);
        tela.add(rbNovo);
        rbNovo.addItemListener(this);

        rbAlterar = new JRadioButton("Alterar");
        rbAlterar.setBounds(458, 90, 80, 20);
        rbAlterar.setFont(fonte);
        tela.add(rbAlterar);
        rbAlterar.addItemListener(this);

        rbExcluir = new JRadioButton("Excluir");
        rbExcluir.setBounds(458, 110, 80, 20);
        rbExcluir.setFont(fonte);
        tela.add(rbExcluir);
        rbExcluir.addItemListener(this);

        btOk = new JButton("OK");
        btOk.setBounds(455, 150, 100, 26);
        tela.add(btOk);
        btOk.addActionListener(this);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(455, 190, 100, 26);
        tela.add(btCancelar);
        btCancelar.addActionListener(this);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setResizable(false);
        setSize(585, 402);
        setLocationRelativeTo(null);//Posiciona no centro da tela
    }

    private void gravar() throws Exception {
        String nome, funcao, endereco, bairro, cidade, estado, email, dataAdmissao, cpf, rg, cep, fone, celular, dataNascimento, complemento, sexo;
        int codigo, numero;
        double salarioIncial, aumento, inss, salarioTotal;

        if ("".equals(tfCodigo.getText()) || Integer.parseInt(tfCodigo.getText()) <= 0) {
            tfCodigo.grabFocus();
            throw new Exception("Campo codigo inválido");
        } else {
            codigo = Integer.parseInt(tfCodigo.getText());
            if ("".equals(tfNome.getText())) {
                tfNome.grabFocus();
                throw new Exception("Campo nome inválido");
            } else {
                nome = tfNome.getText();
                if ("  /  /    ".equals(ftfDataAdmissao.getText())) {
                    ftfDataAdmissao.grabFocus();
                    throw new Exception("Campo data admissão inválido");
                } else {
                    dataAdmissao = ftfDataAdmissao.getText();
                    if ("Selecione".equals(cbFuncao.getSelectedItem())) {
                        cbFuncao.grabFocus();
                        throw new Exception("Campo função inválido");
                    } else {
                        funcao = (String) cbFuncao.getSelectedItem();
                        if ("   .   .   -  ".equals(ftfCPF.getText())) {
                            ftfCPF.grabFocus();
                            throw new Exception("Campo cpf inválido");
                        } else {
                            cpf = ftfCPF.getText();
                            if ("   .   .   ".equals(ftfRG.getText())) {
                                ftfRG.grabFocus();
                                throw new Exception("Campo rg inválido");
                            } else {
                                rg = ftfRG.getText();
                                if ("Selecione".equals(cbSexo.getSelectedItem())) {
                                    cbSexo.grabFocus();
                                    throw new Exception("Campo sexo inválido");
                                } else {
                                    sexo = (String) cbSexo.getSelectedItem();
                                    if ("".equals(tfEndereco.getText())) {
                                        tfEndereco.grabFocus();
                                        throw new Exception("Campo endereço inválido");
                                    } else {
                                        endereco = tfEndereco.getText();
                                        if ("".equals(tfNumero.getText()) || Integer.parseInt(tfNumero.getText()) <= 0) {
                                            tfNumero.grabFocus();
                                            throw new Exception("Campo número inválido");
                                        } else {
                                            numero = Integer.parseInt(tfNumero.getText());
                                            if ("".equals(tfBairro.getText())) {
                                                tfBairro.grabFocus();
                                                throw new Exception("Campo bairro inválido");
                                            } else {
                                                bairro = tfBairro.getText();
                                                if ("     -   ".equals(ftfCEP.getText())) {
                                                    ftfCEP.grabFocus();
                                                    throw new Exception("Campo cep inválido");
                                                } else {
                                                    cep = ftfCEP.getText();
                                                    if ("Selecione".equals(cbCidade.getSelectedItem())) {
                                                        cbCidade.grabFocus();
                                                        throw new Exception("Campo cidade inválido");
                                                    } else {
                                                        cidade = (String) cbCidade.getSelectedItem();
                                                        if ("Selecione".equals(cbEstado.getSelectedItem())) {
                                                            cbEstado.grabFocus();
                                                            throw new Exception("Campo estado inválido");
                                                        } else {
                                                            estado = (String) cbEstado.getSelectedItem();
                                                            if ("".equals(tfComplemento.getText())) {
                                                                tfComplemento.grabFocus();
                                                                throw new Exception("Campo complemento inválido");
                                                            } else {
                                                                complemento = tfComplemento.getText();
                                                                if ("(  )    -    ".equals(ftfFone.getText())) {
                                                                    ftfFone.grabFocus();
                                                                    throw new Exception("Campo fone inválido");
                                                                } else {
                                                                    fone = ftfFone.getText();
                                                                    if ("(  )    -    ".equals(ftfCelular.getText())) {
                                                                        ftfCelular.grabFocus();
                                                                        throw new Exception("Campo celular inválido");
                                                                    } else {
                                                                        celular = ftfCelular.getText();
                                                                        if ("  /  /    ".equals(ftfDatanascimento.getText())) {
                                                                            ftfDatanascimento.grabFocus();
                                                                            throw new Exception("Campo data nascimento inválido");
                                                                        } else {
                                                                            dataNascimento = ftfDatanascimento.getText();
                                                                            if ("".equals(tfSalarioInicial.getText()) || Double.parseDouble(tfSalarioInicial.getText()) <= 0) {
                                                                                tfSalarioInicial.grabFocus();
                                                                                throw new Exception("Campo salário inicial inválido");
                                                                            } else {
                                                                                salarioIncial = Double.parseDouble(tfSalarioInicial.getText());
                                                                                if ("".equals(tfAumento.getText()) || Double.parseDouble(tfAumento.getText()) <= 0) {
                                                                                    tfAumento.grabFocus();
                                                                                    throw new Exception("Campo aumento inválido");
                                                                                } else {
                                                                                    aumento = Double.parseDouble(tfAumento.getText());
                                                                                    if ("".equals(tfINSS.getText()) || Double.parseDouble(tfINSS.getText()) <= 0) {
                                                                                        tfINSS.grabFocus();
                                                                                        throw new Exception("Campo inss inválido");
                                                                                    } else {
                                                                                        inss = Double.parseDouble(tfINSS.getText());
                                                                                        if ("".equals(tfSalarioTotal.getText()) || Double.parseDouble(tfSalarioTotal.getText()) <= 0) {
                                                                                            tfSalarioTotal.grabFocus();
                                                                                            throw new Exception("Campo salário total inválido");
                                                                                        } else {
                                                                                            salarioTotal = Double.parseDouble(tfSalarioTotal.getText());
                                                                                            if ("".equals(tfEmail.getText())) {
                                                                                                tfEmail.grabFocus();
                                                                                                throw new Exception("Campo e-mail inválido");
                                                                                            } else {
                                                                                                email = tfEmail.getText();
                                                                                                control.adicionar(nome, funcao, endereco, bairro, cidade, estado, email, dataAdmissao, cpf, rg, cep, fone, celular, dataNascimento, complemento, sexo, codigo, numero, salarioIncial, aumento, inss, salarioTotal);
                                                                                                JOptionPane.showMessageDialog(null, "Numeros de funcionários cadastrados " + control.tamanho(), "Informação", JOptionPane.INFORMATION_MESSAGE);
                                                                                                limparTela();
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

    private void ok() throws Exception {
        if (control.vazio() == true) {
            try {
                gravar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            boolean encontrado = false;
            if (control.funcionarioCadasCod(Integer.parseInt(tfCodigo.getText())) == true) {
                encontrado = true;
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "O funcionário com o codigo " + tfCodigo.getText() + " ja esta cadastrado deseja Substituilo? ", "Funcionário", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    control.removerCod(Integer.parseInt(tfCodigo.getText()));
                    try {
                        gravar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                try {
                    gravar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há funcionários cadastrados");
        } else {
            boolean erro;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Insira codigo: ", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.recuperar(codigo) == true) {
                            tfNome.setText(control.getNome());
                            cbFuncao.setSelectedItem(control.getFuncao());
                            tfEndereco.setText(control.getEndereco());
                            tfBairro.setText(control.getBairro());
                            cbCidade.setSelectedItem(control.getCidade());
                            cbEstado.setSelectedItem(control.getEstado());
                            tfEmail.setText(control.getEmail());
                            ftfDataAdmissao.setText(control.getDataAdmissao());
                            ftfCPF.setText(control.getCpf());
                            ftfRG.setText(control.getRg());
                            ftfCEP.setText(control.getCep());
                            ftfFone.setText(control.getFone());
                            ftfCelular.setText(control.getCelular());
                            ftfDatanascimento.setText(control.getDataNascimento());
                            tfComplemento.setText(control.getComplemento());
                            cbSexo.setSelectedItem(control.getSexo());
                            tfCodigo.setText(Integer.toString(control.getCodigo()));
                            tfNumero.setText(Integer.toString(control.getNumero()));
                            tfSalarioInicial.setText(Double.toString(control.getSalarioIncial()));
                            tfAumento.setText(Double.toString(control.getAumento()));
                            tfINSS.setText(Double.toString(control.getInss()));
                            tfSalarioTotal.setText(Double.toString(control.getSalarioTotal()));
                            JOptionPane.showMessageDialog(null, "Funcionário encontrado e recuperado", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
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

    private void excluir() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há funcionários cadastrados");
        } else {
            boolean erro = false;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Informe o codigo do funcionário a ser excluído:", "Saída de dados", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.funcionarioCadasCod(codigo) == true) {
                            control.removerCod(codigo);
                            JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
                            limparTela();
                        } else {
                            JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Usuario", JOptionPane.INFORMATION_MESSAGE);
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

    private void pesquisar() throws Exception {
        int codigo = 0;
        String texto = "";
        if (control.vazio() == true) {
            throw new Exception("Não há funcionários cadastrados");
        } else {
            texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Funcionários", JOptionPane.INFORMATION_MESSAGE);
            if (texto == null) {
            } else {
                boolean encontrado = false;
                if (control.funcionarioCadasNome(texto) == true) {
                    codigo = control.getCodigo();
                    encontrado = true;
                }
                if (encontrado == true) {
                    JOptionPane.showMessageDialog(null, "O funcionário " + texto + " esta inserido com o codigo " + codigo, "Funcionários", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    throw new Exception("Funcionário " + texto + " não encontrado");
                }
            }
        }
    }

    private void limparTela() {
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        tfNome.setText("");
        ftfRG.setText("");
        ftfCPF.setText("");
        cbFuncao.setSelectedItem("Selecione");//Campo recebe Selecione
        tfEndereco.setText("");
        ftfFone.setText("");
        tfNumero.setText("");
        ftfCEP.setText("");
        tfBairro.setText("");
        tfSalarioInicial.setText("");
        ftfDataAdmissao.setText("");
        cbCidade.setSelectedItem("Selecione");//Campo recebe Selecione
        cbEstado.setSelectedItem("Selecione");//Campo recebe Selecione
        ftfDatanascimento.setText("");
        ftfCelular.setText("");
        tfEmail.setText("");
        tfAumento.setText("");
        tfINSS.setText("");
        tfSalarioTotal.setText("");
        tfComplemento.setText("");
        cbSexo.setSelectedItem("Selecione");//Campo recebe Selecione
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void codigo() {
        if ("".equals(tfCodigo.getText())) {
            limparTela();
        } else {
            if (control.vazio() == true) {
            } else {
                int n = Integer.parseInt(tfCodigo.getText());
                boolean encontrado = false;
                if (control.funcionarioCadasCod(n) == true) {
                    encontrado = true;
                }
                if (encontrado == true) {
                    limparTela();
                }
            }
        }
    }

    private void calcula() throws Exception {
        try {
            if (!"".equals(tfSalarioInicial.getText()) & !"".equals(tfAumento.getText()) & !"".equals(tfINSS.getText())) {
                double salarioInicial = Float.parseFloat(tfSalarioInicial.getText());//Conversão de String para Float
                double aumento = Float.parseFloat(tfAumento.getText());//Conversão de String para Float
                double inss = Float.parseFloat(tfINSS.getText());//Conversão de String para Float

                double valorTotal1 = ((salarioInicial / 100) * aumento);
                double salarioInicial2 = Float.parseFloat(tfSalarioInicial.getText());//Conversão de String para Float
                double valorTotal2 = (salarioInicial2 + valorTotal1) - inss;
                tfSalarioTotal.setText(Double.toString(valorTotal2));
            }
        } catch (NumberFormatException ex) {
            jTextFieldDouble.validaCampo(tfSalarioInicial);
            jTextFieldDouble.validaCampo(tfAumento);
            jTextFieldDouble.validaCampo(tfINSS);
            jTextFieldDouble.validaCampo(tfSalarioTotal);
        }
    }

    private void verTodosCadastrados() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há funcionários cadastrados");
        } else {
            VerTodosCadas ver = new VerTodosCadas(control);
            ver.setModal(true);
            ver.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Funcionário", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rbNovo.isSelected() == true) {//Verifica se JRadioButton esta ou não selecionado
                    try {
                        ok();
                        control.arquivo(arquivo);
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
                        control.arquivo(arquivo);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (evento.getSource() == btPesquisar) {
            try {
                pesquisar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
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
        if (evento.getSource() == tfNome) {
            tfNome.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDataAdmissao) {
            ftfDataAdmissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbFuncao) {
            cbFuncao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCPF) {
            ftfCPF.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfRG) {
            ftfRG.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbSexo) {
            cbSexo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEndereco) {
            tfEndereco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumero) {
            tfNumero.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBairro) {
            tfBairro.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCEP) {
            ftfCEP.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbCidade) {
            cbCidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbEstado) {
            cbEstado.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfComplemento) {
            tfComplemento.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFone) {
            ftfFone.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCelular) {
            ftfCelular.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfDatanascimento) {
            ftfDatanascimento.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSalarioInicial) {
            tfSalarioInicial.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfAumento) {
            tfAumento.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfINSS) {
            tfINSS.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSalarioTotal) {
            tfSalarioTotal.setBackground(Color.YELLOW);
            try {
                calcula();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == tfEmail) {
            tfEmail.setBackground(Color.YELLOW);
        }
    }

    public void focusLost(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.WHITE);
            codigo();
        }
        tfNome.setBackground(Color.WHITE);
        ftfDataAdmissao.setBackground(Color.WHITE);
        cbFuncao.setBackground(Color.WHITE);
        ftfCPF.setBackground(Color.WHITE);
        ftfRG.setBackground(Color.WHITE);
        cbSexo.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        ftfCEP.setBackground(Color.WHITE);
        cbCidade.setBackground(Color.WHITE);
        cbEstado.setBackground(Color.WHITE);
        tfComplemento.setBackground(Color.WHITE);
        ftfFone.setBackground(Color.WHITE);
        ftfCelular.setBackground(Color.WHITE);
        ftfDatanascimento.setBackground(Color.WHITE);
        tfSalarioInicial.setBackground(Color.WHITE);
        tfAumento.setBackground(Color.WHITE);
        tfINSS.setBackground(Color.WHITE);
        tfSalarioTotal.setBackground(Color.WHITE);
        tfEmail.setBackground(Color.WHITE);
    }

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
}

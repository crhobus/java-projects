package Visao.Cliente;

import Arquivos.*;
import ClassPadrao.MeuDocument;
import Controle.CidadeControl;
import Controle.ClienteControl;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

public class CadasCliente extends JDialog implements ActionListener, FocusListener, ItemListener {

    private JTextField tfCodigo, tfNome, tfProfissao, tfEmpresa, tfEndereco, tfBairro, tfNumero, tfComplemento, tfEmail;
    private JComboBox cbSexo, cbCidade, cbEstado;
    private JFormattedTextField ftfRG, ftfCPF, ftfFoneEmpresa, ftfCEP, ftfFone, ftfCelular;
    private MaskFormatter mascaraRG, mascaraCPF, mascaraFone_Empresa, mascaraCEP, mascaraFone, mascaraCelular;
    private JCheckBox checkSeleciona;
    private JButton btOk, btDescricao, btCancela, btVer, btPesquisar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private String descricao = null, semDescricao = null;
    private ClienteControl control;
    private Arquivo arquivo;
    private LerArquivo lerArquivo;
    private CidadeControl controlCidade;

    public CadasCliente() {
        setTitle("Cadastro de Clientes");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(10, 10, 425, 340);
        tela.add(painel);
        painel.setBorder(BorderFactory.createTitledBorder("Clientes"));
        control = new ClienteControl();
        controlCidade = new CidadeControl();
        arquivo = new Arquivo();
        lerArquivo = new LerArquivo();
        controlCidade.lerArquivo(lerArquivo);
        control.lerArquivo(lerArquivo);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(20, 40, 80, 14);
        lbCodigo.setFont(fonte);
        painel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 58, 80, 20);
        painel.add(tfCodigo);
        tfCodigo.setDocument(new MeuDocument());//Chama e executa classe MeuDocument
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
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

        JLabel lbRG = new JLabel("RG");
        lbRG.setBounds(20, 78, 80, 14);
        lbRG.setFont(fonte);
        painel.add(lbRG);

        try {
            mascaraRG = new MaskFormatter("###.###.###");
        } catch (ParseException excp) {
        }
        ftfRG = new JFormattedTextField(mascaraRG);
        ftfRG.setBounds(20, 94, 110, 20);
        painel.add(ftfRG);
        ftfRG.addFocusListener(this);

        JLabel lbCPF = new JLabel("CPF");
        lbCPF.setBounds(140, 78, 80, 14);
        lbCPF.setFont(fonte);
        painel.add(lbCPF);

        //Este faz com que so pode digitar numeros e o campo ja vem com as separações
        try {
            mascaraCPF = new MaskFormatter("###.###.###-##");
        } catch (ParseException excp) {
        }
        ftfCPF = new JFormattedTextField(mascaraCPF);
        ftfCPF.setBounds(140, 94, 110, 20);
        painel.add(ftfCPF);
        ftfCPF.addFocusListener(this);

        JLabel lbProfissao = new JLabel("Profissão");
        lbProfissao.setBounds(260, 78, 80, 14);
        lbProfissao.setFont(fonte);
        painel.add(lbProfissao);

        tfProfissao = new JTextField();
        tfProfissao.setBounds(260, 94, 123, 20);
        painel.add(tfProfissao);
        tfProfissao.addFocusListener(this);

        JLabel lbEmpresa = new JLabel("Empresa");
        lbEmpresa.setBounds(20, 114, 80, 14);
        lbEmpresa.setFont(fonte);
        painel.add(lbEmpresa);

        tfEmpresa = new JTextField();
        tfEmpresa.setBounds(20, 130, 110, 20);
        painel.add(tfEmpresa);
        tfEmpresa.addFocusListener(this);

        JLabel lbFoneEmpresa = new JLabel("Fone Empresa");
        lbFoneEmpresa.setBounds(140, 114, 110, 14);
        lbFoneEmpresa.setFont(fonte);
        painel.add(lbFoneEmpresa);

        try {
            mascaraFone_Empresa = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfFoneEmpresa = new JFormattedTextField(mascaraFone_Empresa);
        ftfFoneEmpresa.setBounds(140, 130, 110, 20);
        painel.add(ftfFoneEmpresa);
        ftfFoneEmpresa.addFocusListener(this);

        JLabel lbSexo = new JLabel("Sexo");
        lbSexo.setBounds(260, 114, 80, 14);
        lbSexo.setFont(fonte);
        painel.add(lbSexo);

        cbSexo = new JComboBox();
        cbSexo.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        cbSexo.addItem("Masculino");
        cbSexo.addItem("Feminino");
        cbSexo.setBackground(cor);
        cbSexo.setFont(fonte);
        cbSexo.setBounds(260, 130, 120, 20);
        painel.add(cbSexo);
        cbSexo.addFocusListener(this);

        JLabel lbCEP = new JLabel("CEP");
        lbCEP.setBounds(20, 150, 80, 14);
        lbCEP.setFont(fonte);
        painel.add(lbCEP);

        try {
            mascaraCEP = new MaskFormatter("#####-###");
        } catch (ParseException excp) {
        }
        ftfCEP = new JFormattedTextField(mascaraCEP);
        ftfCEP.setBounds(20, 166, 110, 20);
        painel.add(ftfCEP);
        ftfCEP.addFocusListener(this);

        JLabel lbEndereco = new JLabel("Endereço");
        lbEndereco.setBounds(140, 150, 80, 14);
        lbEndereco.setFont(fonte);
        painel.add(lbEndereco);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(140, 166, 110, 20);
        painel.add(tfEndereco);
        tfEndereco.addFocusListener(this);

        JLabel lbBairro = new JLabel("Bairro");
        lbBairro.setBounds(260, 150, 80, 14);
        lbBairro.setFont(fonte);
        painel.add(lbBairro);

        tfBairro = new JTextField();
        tfBairro.setBounds(260, 166, 120, 20);
        painel.add(tfBairro);
        tfBairro.addFocusListener(this);

        JLabel lbNumero = new JLabel("Numero");
        lbNumero.setBounds(20, 185, 80, 14);
        lbNumero.setFont(fonte);
        painel.add(lbNumero);

        tfNumero = new JTextField();
        tfNumero.setBounds(20, 200, 110, 20);
        tfNumero.setDocument(new MeuDocument());
        painel.add(tfNumero);
        tfNumero.addFocusListener(this);

        JLabel lbComplemento = new JLabel("Complemento");
        lbComplemento.setBounds(140, 185, 80, 14);
        lbComplemento.setFont(fonte);
        painel.add(lbComplemento);

        tfComplemento = new JTextField();
        tfComplemento.setBounds(140, 200, 110, 20);
        painel.add(tfComplemento);
        tfComplemento.addFocusListener(this);

        JLabel lbCidade = new JLabel("Cidade");
        lbCidade.setBounds(260, 185, 80, 14);
        lbCidade.setFont(fonte);
        painel.add(lbCidade);

        cbCidade = new JComboBox();
        cbCidade.setBounds(260, 200, 120, 20);
        cbCidade.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        ArrayList listaCidade = new ArrayList();
        listaCidade = controlCidade.cidade();
        for (int i = 0; i < listaCidade.size(); i++) {
            String n1 = (String) listaCidade.get(i);
            cbCidade.addItem(n1);
        }
        cbCidade.setBackground(cor);
        cbCidade.setFont(fonte);
        painel.add(cbCidade);
        cbCidade.addFocusListener(this);

        JLabel lbEstado = new JLabel("Estado");
        lbEstado.setBounds(20, 218, 80, 14);
        lbEstado.setFont(fonte);
        painel.add(lbEstado);

        cbEstado = new JComboBox();
        cbEstado.setBounds(20, 233, 110, 20);
        cbEstado.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        ArrayList listaEstado = new ArrayList();
        listaEstado = controlCidade.estado();
        for (int i = 0; i < listaEstado.size(); i++) {
            String n2 = (String) listaEstado.get(i);
            cbEstado.addItem(n2);
        }
        cbEstado.setBackground(cor);
        cbEstado.setFont(fonte);
        painel.add(cbEstado);
        cbEstado.addFocusListener(this);

        JLabel lbFone = new JLabel("Fone");
        lbFone.setBounds(140, 218, 80, 14);
        lbFone.setFont(fonte);
        painel.add(lbFone);

        try {
            mascaraFone = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfFone = new JFormattedTextField(mascaraFone);
        ftfFone.setBounds(140, 233, 110, 20);
        painel.add(ftfFone);
        ftfFone.addFocusListener(this);

        JLabel lbCelular = new JLabel("Celular");
        lbCelular.setBounds(260, 218, 80, 14);
        lbCelular.setFont(fonte);
        painel.add(lbCelular);

        try {
            mascaraCelular = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfCelular = new JFormattedTextField(mascaraCelular);
        ftfCelular.setBounds(260, 233, 120, 20);
        painel.add(ftfCelular);
        ftfCelular.addFocusListener(this);

        JLabel lbEmail = new JLabel("E-mail");
        lbEmail.setBounds(20, 252, 80, 14);
        lbEmail.setFont(fonte);
        painel.add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(20, 268, 361, 20);
        painel.add(tfEmail);
        tfEmail.addFocusListener(this);

        checkSeleciona = new JCheckBox();
        checkSeleciona.setBounds(384, 305, 22, 17);
        painel.add(checkSeleciona);
        checkSeleciona.addItemListener(this);

        btDescricao = new JButton("-------------    Descrição - Cadastro de Clientes    -------------");
        btDescricao.setBounds(20, 300, 361, 26);
        painel.add(btDescricao);
        btDescricao.addActionListener(this);

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

        btCancela = new JButton("Cancelar");
        btCancela.setBounds(455, 190, 100, 26);
        tela.add(btCancela);
        btCancela.addActionListener(this);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(600, 400);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
    }

    private void gravar() throws Exception {
        String nome, rg, cpf, profissao, empresa, foneEmpresa, sexo, cep, endereco, bairro, complemento, cidade, estado, email, fone, celular, descricao;
        int codigo, numero;

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
                if ("   .   .   ".equals(ftfRG.getText())) {
                    ftfRG.grabFocus();
                    throw new Exception("Campo rg inválido");
                } else {
                    rg = ftfRG.getText();
                    if ("   .   .   -  ".equals(ftfCPF.getText())) {
                        ftfCPF.grabFocus();
                        throw new Exception("Campo cpf inválido");
                    } else {
                        cpf = ftfCPF.getText();
                        if ("".equals(tfProfissao.getText())) {
                            tfProfissao.grabFocus();
                            throw new Exception("Campo profissão inválido");
                        } else {
                            profissao = tfProfissao.getText();
                            if ("".equals(tfEmpresa.getText())) {
                                tfEmpresa.grabFocus();
                                throw new Exception("Campo empresa inválido");
                            } else {
                                empresa = tfEmpresa.getText();
                                if ("(  )    -    ".equals(ftfFoneEmpresa.getText())) {
                                    ftfFoneEmpresa.grabFocus();
                                    throw new Exception("Campo fone empresa inválido");
                                } else {
                                    foneEmpresa = ftfFoneEmpresa.getText();
                                    if ("Selecione".equals(cbSexo.getSelectedItem())) {
                                        cbSexo.grabFocus();
                                        throw new Exception("Campo sexo inválido");
                                    } else {
                                        sexo = (String) cbSexo.getSelectedItem();
                                        if ("     -   ".equals(ftfCEP.getText())) {
                                            ftfCEP.grabFocus();
                                            throw new Exception("Campo cep inválido");
                                        } else {
                                            cep = ftfCEP.getText();
                                            if ("".equals(tfEndereco.getText())) {
                                                tfEndereco.grabFocus();
                                                throw new Exception("Campo endereço inválido");
                                            } else {
                                                endereco = tfEndereco.getText();
                                                if ("".equals(tfBairro.getText())) {
                                                    tfBairro.grabFocus();
                                                    throw new Exception("Campo bairro inválido");
                                                } else {
                                                    bairro = tfBairro.getText();
                                                    if ("".equals(tfNumero.getText()) || Integer.parseInt(tfNumero.getText()) <= 0) {
                                                        tfNumero.grabFocus();
                                                        throw new Exception("Campo número inválido");
                                                    } else {
                                                        numero = Integer.parseInt(tfNumero.getText());
                                                        if ("".equals(tfComplemento.getText())) {
                                                            tfComplemento.grabFocus();
                                                            throw new Exception("Campo complemento inválido");
                                                        } else {
                                                            complemento = tfComplemento.getText();
                                                            if ("Selecione".equals(cbCidade.getSelectedItem())) {
                                                                cbCidade.grabFocus();
                                                                throw new Exception("Campo cidade inválido");
                                                            } else {
                                                                cidade = (String) cbCidade.getSelectedItem();
                                                                if ("Selecione".equals(cbEstado.getSelectedItem())) {
                                                                    cbEstado.grabFocus();
                                                                    throw new Exception("Campo cidade inválido");
                                                                } else {
                                                                    estado = (String) cbEstado.getSelectedItem();
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
                                                                            if ("".equals(tfEmail.getText())) {
                                                                                tfEmail.grabFocus();
                                                                                throw new Exception("Campo e-mail inválido");
                                                                            } else {
                                                                                email = tfEmail.getText();
                                                                                if (semDescricao == null) {
                                                                                    if (this.descricao == null) {
                                                                                        throw new Exception("Campo descrição inválido");
                                                                                    } else {
                                                                                        descricao = this.descricao;
                                                                                    }
                                                                                } else {
                                                                                    descricao = semDescricao;
                                                                                }
                                                                                control.adicionar(nome, rg, cpf, profissao, empresa, foneEmpresa, sexo, cep, endereco, bairro, complemento, cidade, estado, email, fone, celular, descricao, codigo, numero);
                                                                                JOptionPane.showMessageDialog(null, "Numeros de clientes cadastrados " + control.tamanho(), "Informação", JOptionPane.INFORMATION_MESSAGE);
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

    private void ok() throws Exception {
        if (control.vazio() == true) {
            try {
                gravar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            boolean encontrado = false;
            if (control.clienteCadasCod(Integer.parseInt(tfCodigo.getText())) == true) {
                encontrado = true;
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "O cliente com o codigo " + tfCodigo.getText() + " ja esta cadastrado deseja Substituilo? ", "Cliente", JOptionPane.YES_NO_OPTION);
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
            throw new Exception("Não há clientes cadastrados");
        } else {
            boolean erro;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Insira codigo: ", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.recuperar(codigo) == true) {
                            tfNome.setText(control.getNome());
                            ftfRG.setText(control.getRg());
                            ftfCPF.setText(control.getCpf());
                            tfProfissao.setText(control.getProfissao());
                            tfEmpresa.setText(control.getEmpresa());
                            ftfFoneEmpresa.setText(control.getFoneEmpresa());
                            cbSexo.setSelectedItem(control.getSexo());
                            ftfCEP.setText(control.getCep());
                            tfEndereco.setText(control.getEndereco());
                            tfBairro.setText(control.getBairro());
                            tfComplemento.setText(control.getComplemento());
                            cbCidade.setSelectedItem(control.getCidade());
                            cbEstado.setSelectedItem(control.getEstado());
                            tfEmail.setText(control.getEmail());
                            ftfFone.setText(control.getFone());
                            ftfCelular.setText(control.getCelular());
                            tfCodigo.setText(Integer.toString(control.getCodigo()));
                            tfNumero.setText(Integer.toString(control.getNumero()));
                            JOptionPane.showMessageDialog(null, "Cliente encontrado e recuperado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
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
            throw new Exception("Não há clientes cadastrados");
        } else {
            boolean erro = false;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Informe o codigo do cliente a ser excluído:", "Saída de dados", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.clienteCadasCod(codigo) == true) {
                            control.removerCod(codigo);
                            JOptionPane.showMessageDialog(null, "Cliente removido com sucesso", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                            limparTela();
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
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
            throw new Exception("Não há clientes cadastrados");
        } else {
            texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Cliente", JOptionPane.INFORMATION_MESSAGE);
            if (texto == null) {
            } else {
                boolean encontrado = false;
                if (control.clienteCadasNome(texto) == true) {
                    codigo = control.getCodigo();
                    encontrado = true;
                }
                if (encontrado == true) {
                    JOptionPane.showMessageDialog(null, "O cliente " + texto + " esta inserido com o codigo " + codigo, "Cliente", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    throw new Exception("Cliente " + texto + " não encontrado");
                }
            }
        }
    }

    private void limparTela() {
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        tfNome.setText("");
        ftfRG.setText("");
        ftfCPF.setText("");
        tfProfissao.setText("");
        tfEmpresa.setText("");
        ftfFoneEmpresa.setText("");
        cbSexo.setSelectedItem("Selecione");///Campo recebe Selecione
        ftfCEP.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfComplemento.setText("");
        cbCidade.setSelectedItem("Selecione");///Campo recebe Selecione
        cbEstado.setSelectedItem("Selecione");///Campo recebe Selecione
        ftfFone.setText("");
        ftfCelular.setText("");
        tfEmail.setText("");
        checkSeleciona.setSelected(false);//Desmarca JCheckbox
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
        descricao = null;
    }

    private void codigo() {
        if ("".equals(tfCodigo.getText())) {
            limparTela();
        } else {
            if (control.vazio() == true) {
            } else {
                int n = Integer.parseInt(tfCodigo.getText());
                boolean encontrado = false;
                if (control.clienteCadasCod(n) == true) {
                    encontrado = true;
                }
                if (encontrado == true) {
                    limparTela();
                }
            }
        }
    }

    private void verTodosCadastrados() throws Exception {
        if (control.vazio() == true) {
            throw new Exception("Não há clientes cadastrados");
        } else {
            VerTodosCadasClie ver = new VerTodosCadasClie(control);
            ver.setModal(true);
            ver.setVisible(true);
        }
    }

    private void descricaoCliente() {
        DescricaoCliente descricaoCliente = new DescricaoCliente();
        descricaoCliente.setListener(new ListenerCliente() {

            public void campoAlterado(String texto) {
                descricao = texto;
            }
        });
        descricaoCliente.setModal(true);
        descricaoCliente.setVisible(true);
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancela) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Cliente", JOptionPane.ERROR_MESSAGE);
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
        if (evento.getSource() == btDescricao) {
            descricaoCliente();
        }
    }

    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNome) {
            tfNome.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfRG) {
            ftfRG.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCPF) {
            ftfCPF.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfProfissao) {
            tfProfissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmpresa) {
            tfEmpresa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFoneEmpresa) {
            ftfFoneEmpresa.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbSexo) {
            cbSexo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCEP) {
            ftfCEP.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEndereco) {
            tfEndereco.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfBairro) {
            tfBairro.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumero) {
            tfNumero.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfComplemento) {
            tfComplemento.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbCidade) {
            cbCidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbEstado) {
            cbEstado.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfFone) {
            ftfFone.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCelular) {
            ftfCelular.setBackground(Color.YELLOW);
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
        ftfRG.setBackground(Color.WHITE);
        ftfCPF.setBackground(Color.WHITE);
        tfProfissao.setBackground(Color.WHITE);
        tfEmpresa.setBackground(Color.WHITE);
        ftfFoneEmpresa.setBackground(Color.WHITE);
        cbSexo.setBackground(Color.WHITE);
        ftfCEP.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfComplemento.setBackground(Color.WHITE);
        cbCidade.setBackground(Color.WHITE);
        cbEstado.setBackground(Color.WHITE);
        ftfFone.setBackground(Color.WHITE);
        ftfCelular.setBackground(Color.WHITE);
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
        if (evento.getSource() == checkSeleciona) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                semDescricao = " Sem Descrição";
            } else {
                semDescricao = null;
            }
        }
    }
}

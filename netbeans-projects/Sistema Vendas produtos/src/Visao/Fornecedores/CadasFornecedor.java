package Visao.Fornecedores;

import Arquivos.*;
import ClassPadrao.MeuDocument;
import Controle.CidadeControl;
import Controle.FornecedorControl;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

public class CadasFornecedor extends JDialog implements ActionListener, FocusListener, ItemListener {

    private JTextField tfCodigo, tfNome, tfSigla, tfComissao, tfEndereco, tfNumero, tfBairro, tfEmail, tfEmpresa;
    private JComboBox cbCidade, cbEstado;
    private JFormattedTextField ftfCNPJ, ftfIE, ftfCEP, ftfFone, ftfCelular;
    private MaskFormatter mascaraCNPJ, mascaraIE, mascaraCEP, mascaraFone, mascaraCelular;
    private JButton btOk, btCancela, btVer, btPesquisar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private FornecedorControl control;
    private Arquivo arquivo;
    private LerArquivo lerArquivo;
    private CidadeControl controlCidade;

    public CadasFornecedor() {
        setTitle("Cadastro de Fornecedores");
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        Color cor = new Color(255, 255, 255);//Branco
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(10, 10, 418, 268);
        tela.add(painel);
        painel.setBorder(BorderFactory.createTitledBorder("Fornecedor"));
        control = new FornecedorControl();
        controlCidade = new CidadeControl();
        arquivo = new Arquivo();
        lerArquivo = new LerArquivo();
        controlCidade.lerArquivo(lerArquivo);
        control.lerArquivo(lerArquivo);

        JLabel lbCodigo = new JLabel("Codigo");
        lbCodigo.setFont(fonte);
        lbCodigo.setBounds(20, 40, 80, 14);
        painel.add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(20, 58, 80, 20);
        painel.add(tfCodigo);
        tfCodigo.setDocument(new MeuDocument());
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

        JLabel lbSigla = new JLabel("Sigla");
        lbSigla.setBounds(20, 78, 80, 14);
        lbSigla.setFont(fonte);
        painel.add(lbSigla);

        tfSigla = new JTextField();
        tfSigla.setBounds(20, 94, 110, 20);
        painel.add(tfSigla);
        tfSigla.addFocusListener(this);

        JLabel lbComissao = new JLabel("Comissão");
        lbComissao.setFont(fonte);
        lbComissao.setBounds(140, 78, 80, 14);
        painel.add(lbComissao);

        tfComissao = new JTextField();
        tfComissao.setBounds(140, 94, 110, 20);
        painel.add(tfComissao);
        tfComissao.addFocusListener(this);

        JLabel lbCNPJ = new JLabel("CNPJ");
        lbCNPJ.setBounds(260, 78, 80, 14);
        lbCNPJ.setFont(fonte);
        painel.add(lbCNPJ);

        //Este faz com que so pode digitar numeros e o campo ja vem com as separações
        try {
            mascaraCNPJ = new MaskFormatter("###.###.###/####-##");
        } catch (ParseException excp) {
        }
        ftfCNPJ = new JFormattedTextField(mascaraCNPJ);
        ftfCNPJ.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCNPJ.setBounds(260, 94, 123, 20);
        painel.add(ftfCNPJ);
        ftfCNPJ.addFocusListener(this);

        JLabel lbIE = new JLabel("IE");
        lbIE.setBounds(20, 114, 80, 14);
        lbIE.setFont(fonte);
        painel.add(lbIE);

        //Este faz com que so pode digitar numeros e o campo ja vem com as separações
        try {
            mascaraIE = new MaskFormatter("###.###.###");
        } catch (ParseException excp) {
        }
        ftfIE = new JFormattedTextField(mascaraIE);
        ftfIE.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfIE.setBounds(20, 130, 110, 20);
        painel.add(ftfIE);
        ftfIE.addFocusListener(this);

        JLabel lbEndereco = new JLabel("Endereço");
        lbEndereco.setBounds(140, 114, 110, 14);
        lbEndereco.setFont(fonte);
        painel.add(lbEndereco);

        tfEndereco = new JTextField();
        tfEndereco.setBounds(140, 130, 110, 20);
        painel.add(tfEndereco);
        tfEndereco.addFocusListener(this);

        JLabel lbNumero = new JLabel("Numero");
        lbNumero.setBounds(260, 114, 80, 14);
        lbNumero.setFont(fonte);
        painel.add(lbNumero);

        tfNumero = new JTextField();
        tfNumero.setBounds(260, 130, 120, 20);
        tfNumero.setDocument(new MeuDocument());
        painel.add(tfNumero);
        tfNumero.addFocusListener(this);

        JLabel lbBairro = new JLabel("Bairro");
        lbBairro.setBounds(20, 150, 80, 14);
        lbBairro.setFont(fonte);
        painel.add(lbBairro);

        tfBairro = new JTextField();
        tfBairro.setBounds(20, 166, 110, 20);
        painel.add(tfBairro);
        tfBairro.addFocusListener(this);

        JLabel lbCEP = new JLabel("CEP");
        lbCEP.setBounds(140, 150, 80, 14);
        lbCEP.setFont(fonte);
        painel.add(lbCEP);

        try {
            mascaraCEP = new MaskFormatter("#####-###");
        } catch (ParseException excp) {
        }
        ftfCEP = new JFormattedTextField(mascaraCEP);
        ftfCEP.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCEP.setBounds(140, 166, 110, 20);
        painel.add(ftfCEP);
        ftfCEP.addFocusListener(this);

        JLabel lbCidade = new JLabel("Cidade");
        lbCidade.setBounds(260, 150, 80, 14);
        lbCidade.setFont(fonte);
        painel.add(lbCidade);

        cbCidade = new JComboBox();
        cbCidade.setBounds(260, 166, 120, 20);
        cbCidade.addItem("Selecione");//addItem adiciona no JComboBox as opçoes
        ArrayList listaCidade = new ArrayList();
        listaCidade = controlCidade.cidade();
        for (int i = 0; i < listaCidade.size(); i++) {
            String n = (String) listaCidade.get(i);
            cbCidade.addItem(n);
        }
        cbCidade.setBackground(cor);
        cbCidade.setFont(fonte);
        painel.add(cbCidade);
        cbCidade.addFocusListener(this);

        JLabel lbEstado = new JLabel("Estado");
        lbEstado.setBounds(20, 185, 80, 14);
        lbEstado.setFont(fonte);
        painel.add(lbEstado);

        cbEstado = new JComboBox();
        cbEstado.setBounds(20, 200, 110, 20);
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

        JLabel lbEmpresa = new JLabel("Empresa");
        lbEmpresa.setBounds(140, 185, 80, 14);
        lbEmpresa.setFont(fonte);
        painel.add(lbEmpresa);

        tfEmpresa = new JTextField();
        tfEmpresa.setBounds(140, 200, 110, 20);
        painel.add(tfEmpresa);
        tfEmpresa.addFocusListener(this);

        JLabel lbFone = new JLabel("Fone");
        lbFone.setBounds(260, 185, 80, 14);
        lbFone.setFont(fonte);
        painel.add(lbFone);

        //Este faz com que so pode digitar numeros e o campo ja vem com as separações
        try {
            mascaraFone = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfFone = new JFormattedTextField(mascaraFone);
        ftfFone.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfFone.setBounds(260, 200, 120, 20);
        painel.add(ftfFone);
        ftfFone.addFocusListener(this);

        JLabel lbCelular = new JLabel("Celular");
        lbCelular.setBounds(20, 218, 80, 14);
        lbCelular.setFont(fonte);
        painel.add(lbCelular);

        //Este faz com que so pode digitar numeros e o campo ja vem com as separações
        try {
            mascaraCelular = new MaskFormatter("(##)####-####");
        } catch (ParseException excp) {
        }
        ftfCelular = new JFormattedTextField(mascaraCelular);
        ftfCelular.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ftfCelular.setBounds(20, 233, 110, 20);
        painel.add(ftfCelular);
        ftfCelular.addFocusListener(this);

        JLabel lbEmail = new JLabel("E-mail");
        lbEmail.setBounds(140, 218, 80, 14);
        lbEmail.setFont(fonte);
        painel.add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(140, 233, 242, 20);
        painel.add(tfEmail);
        tfEmail.addFocusListener(this);

        btPesquisar = new JButton("Pesquisar");
        btPesquisar.setBounds(445, 25, 100, 26);
        tela.add(btPesquisar);
        btPesquisar.addActionListener(this);

        rbNovo = new JRadioButton("Novo");
        rbNovo.setBounds(448, 70, 80, 20);
        rbNovo.setFont(fonte);
        tela.add(rbNovo);
        rbNovo.addItemListener(this);

        rbAlterar = new JRadioButton("Alterar");
        rbAlterar.setBounds(448, 90, 80, 20);
        rbAlterar.setFont(fonte);
        tela.add(rbAlterar);
        rbAlterar.addItemListener(this);

        rbExcluir = new JRadioButton("Excluir");
        rbExcluir.setBounds(448, 110, 80, 20);
        rbExcluir.setFont(fonte);
        tela.add(rbExcluir);
        rbExcluir.addItemListener(this);

        btOk = new JButton("OK");
        btOk.setBounds(445, 150, 100, 26);
        tela.add(btOk);
        btOk.addActionListener(this);

        btCancela = new JButton("Cancelar");
        btCancela.setBounds(445, 190, 100, 26);
        tela.add(btCancela);
        btCancela.addActionListener(this);

        //Este Habilita a função enter nos Campos
        HashSet conj = new HashSet(painel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        painel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(600, 323);
        setLocationRelativeTo(null);//Posiciona no centro da tela
        setResizable(false);//Maximizado tornase falso
    }

    private void gravar() throws Exception {
        String nome, sigla, endereco, bairro, cidade, estado, email, empresa, cnpj, ie, cep, fone, celular;
        int codigo, numero;
        double comissao;

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
                if ("".equals(tfSigla.getText())) {
                    tfSigla.grabFocus();
                    throw new Exception("Campo sigla inválido");
                } else {
                    sigla = tfSigla.getText();
                    if ("".equals(tfComissao.getText()) || Double.parseDouble(tfComissao.getText()) <= 0) {
                        tfComissao.grabFocus();
                        throw new Exception("Campo comissão inválido");
                    } else {
                        comissao = Double.parseDouble(tfComissao.getText());
                        if ("   .   .   /    -  ".equals(ftfCNPJ.getText())) {
                            ftfCNPJ.grabFocus();
                            throw new Exception("Campo cnpj inválido");
                        } else {
                            cnpj = ftfCNPJ.getText();
                            if ("   .   .   ".equals(ftfIE.getText())) {
                                ftfIE.grabFocus();
                                throw new Exception("Campo ie inválido");
                            } else {
                                ie = ftfIE.getText();
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
                                                        if ("".equals(tfEmpresa.getText())) {
                                                            tfEmpresa.grabFocus();
                                                            throw new Exception("Campo empresa inválido");
                                                        } else {
                                                            empresa = tfEmpresa.getText();
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
                                                                        control.adicionar(nome, sigla, endereco, bairro, cidade, estado, email, empresa, cnpj, ie, cep, fone, celular, codigo, numero, comissao);
                                                                        JOptionPane.showMessageDialog(null, "Numeros de fornecedores cadastrados " + control.tamanho(), "Informação", JOptionPane.INFORMATION_MESSAGE);
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

    private void ok() throws Exception {
        if (control.vazio() == true) {
            try {
                gravar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            boolean encontrado = false;
            if (control.fornecedorCadasCod(Integer.parseInt(tfCodigo.getText())) == true) {
                encontrado = true;
            }
            if (encontrado == true) {
                int resposta = JOptionPane.showConfirmDialog(null, "O fornecedor com o codigo " + tfCodigo.getText() + " ja esta cadastrado deseja Substituilo? ", "Fornecedor", JOptionPane.YES_NO_OPTION);
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
            throw new Exception("Não há fornecedores cadastrados");
        } else {
            boolean erro;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Insira codigo: ", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.recuperar(codigo) == true) {
                            tfNome.setText(control.getNome());
                            tfSigla.setText(control.getSigla());
                            tfEndereco.setText(control.getEndereco());
                            tfBairro.setText(control.getBairro());
                            cbCidade.setSelectedItem(control.getCidade());
                            cbEstado.setSelectedItem(control.getEstado());
                            tfEmail.setText(control.getEmail());
                            tfEmpresa.setText(control.getEmpresa());
                            ftfCNPJ.setText(control.getCnpj());
                            ftfIE.setText(control.getIe());
                            ftfCEP.setText(control.getCep());
                            ftfFone.setText(control.getFone());
                            ftfCelular.setText(control.getCelular());
                            tfCodigo.setText(Integer.toString(control.getCodigo()));
                            tfNumero.setText(Integer.toString(control.getNumero()));
                            tfComissao.setText(Double.toString(control.getComissao()));
                            JOptionPane.showMessageDialog(null, "Fornecedor encontrado e recuperado", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
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
            throw new Exception("Não há fornecedores cadastrados");
        } else {
            boolean erro = false;
            int codigo;
            do {
                String s = JOptionPane.showInputDialog(null, "Informe o codigo do fornecedor a ser excluído:", "Saída de dados", JOptionPane.INFORMATION_MESSAGE);
                if (s == null) {
                    erro = false;
                } else {
                    try {
                        codigo = Integer.parseInt(s);
                        if (control.fornecedorCadasCod(codigo) == true) {
                            control.removerCod(codigo);
                            JOptionPane.showMessageDialog(null, "Fornecedor removido com sucesso", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                            limparTela();
                        } else {
                            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
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
            throw new Exception("Não há fornecedores cadastrados");
        } else {
            texto = JOptionPane.showInputDialog(null, "Insira nome: ", "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
            if (texto == null) {
            } else {
                boolean encontrado = false;
                if (control.fornecedorCadasNome(texto) == true) {
                    codigo = control.getCodigo();
                    encontrado = true;
                }
                if (encontrado == true) {
                    JOptionPane.showMessageDialog(null, "O fornecedor " + texto + " esta inserido com o codigo " + codigo, "Fornecedor", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    throw new Exception("Fornecedor " + texto + " não encontrado");
                }
            }
        }
    }

    private void limparTela() {
        tfCodigo.setText(Integer.toString(control.ultimoCadasCod() + 1));
        tfNome.setText("");
        tfSigla.setText("");
        tfComissao.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        cbCidade.setSelectedItem("Selecione");///Campo recebe Selecione
        cbEstado.setSelectedItem("Selecione");///Campo recebe Selecione
        tfEmail.setText("");
        tfEmpresa.setText("");
        ftfCNPJ.setText("");
        tfNumero.setText("");
        ftfIE.setText("");
        ftfCEP.setText("");
        ftfFone.setText("");
        ftfCelular.setText("");
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
                if (control.fornecedorCadasCod(n) == true) {
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
            throw new Exception("Não há fornecedores cadastrados");
        } else {
            VerTodosCadas ver = new VerTodosCadas(control);
            ver.setModal(true);
            ver.setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancela) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Fornecedor", JOptionPane.ERROR_MESSAGE);
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
        if (evento.getSource() == tfSigla) {
            tfSigla.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfComissao) {
            tfComissao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfCNPJ) {
            ftfCNPJ.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == ftfIE) {
            ftfIE.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfEmpresa) {
            tfEmpresa.setBackground(Color.YELLOW);
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
        tfSigla.setBackground(Color.WHITE);
        tfComissao.setBackground(Color.WHITE);
        ftfCNPJ.setBackground(Color.WHITE);
        ftfIE.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        ftfCEP.setBackground(Color.WHITE);
        cbCidade.setBackground(Color.WHITE);
        cbEstado.setBackground(Color.WHITE);
        tfEmpresa.setBackground(Color.WHITE);
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
    }
}

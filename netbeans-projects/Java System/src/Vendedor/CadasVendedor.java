package Vendedor;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DAOFactory.DAOFactory;
import FormatacaoCampos.CriarObjGrafico;
import Funcionario.FuncionarioControl;
import Funcionario.PesquisaFuncionario;
import Modelo.Funcionario;
import Modelo.Vendedor;

public class CadasVendedor extends JDialog implements ActionListener, ItemListener {

    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfCPF, tfRG,
            tfNome, tfSexo, tfEndereco, tfBairro, tfNumero, tfCidade, tfEstado,
            tfComplemento, tfFone, tfCelular, tfCelularEmpresa, tfEmpresa,
            tfFoneEmpresa, tfEmail, tfMsn, tfSkype, tfEmailVendedor,
            tfDescricao, tfCarteiraHabilitacao, tfCarteiraTrabalho, tfSalario,
            tfComissao, tfSalarioLiquido, tfInscricaoEstadual, tfCnpj,
            tfHomePege;
    private JButton btVer, btPesquisaCPF, btCalculaSalario, btOk, btCancelar;
    private JTextArea taOutrasConsidereções;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private JLabel lbCPFObrig, lbSalarioObrig;
    private DAOFactory daoFactory;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private VendedorControl controle;

    public CadasVendedor(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        controle = new VendedorControl(daoFactory);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Vendedor");
        setLayout(null);
        JPanel panel = CriarObjGrafico.criarJPanel(10, 10, 845, 415);
        add(panel);

        panel.add(CriarObjGrafico.criarJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodVen()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        panel.add(tfCodigo);

        btVer = CriarObjGrafico.criarJButton("...", 106, 34, 31, 24);
        btVer.addActionListener(this);
        panel.add(btVer);

        panel.add(CriarObjGrafico.criarJLabel("Cadastrado em", 143, 20, 90, 14));
        tfDataCadas = CriarObjGrafico.criarJTextField(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.setEditable(false);
        tfDataCadas.setBackground(Color.WHITE);
        panel.add(tfDataCadas);

        panel.add(CriarObjGrafico.criarJLabel("Última Alteração", 270, 20, 100, 14));
        tfUltAlteracao = CriarObjGrafico.criarJTextField(270, 38, 120, 20);
        panel.add(tfUltAlteracao);

        panel.add(CriarObjGrafico.criarJLabel("CPF", 400, 20, 80, 14));
        lbCPFObrig = CriarObjGrafico.criarJLabel("", 425, 23, 10, 14);
        lbCPFObrig.setForeground(Color.RED);
        panel.add(lbCPFObrig);
        tfCPF = CriarObjGrafico.criarJTextField(400, 38, 120, 20);
        tfCPF.setEditable(false);
        tfCPF.setBackground(Color.WHITE);
        panel.add(tfCPF);

        btPesquisaCPF = CriarObjGrafico.criarJButton("...", 530, 34, 31, 24);
        btPesquisaCPF.addActionListener(this);
        panel.add(btPesquisaCPF);

        panel.add(CriarObjGrafico.criarJLabel("Nome", 570, 20, 80, 14));
        tfNome = CriarObjGrafico.criarJTextField(570, 38, 260, 20);
        tfNome.setEditable(false);
        tfNome.setBackground(Color.WHITE);
        panel.add(tfNome);

        panel.add(CriarObjGrafico.criarJLabel("RG", 20, 60, 80, 14));
        tfRG = CriarObjGrafico.criarJTextField(20, 78, 100, 20);
        tfRG.setEditable(false);
        tfRG.setBackground(Color.WHITE);
        panel.add(tfRG);

        panel.add(CriarObjGrafico.criarJLabel("Sexo", 130, 60, 80, 14));
        tfSexo = CriarObjGrafico.criarJTextField(130, 78, 100, 20);
        tfSexo.setEditable(false);
        tfSexo.setBackground(Color.WHITE);
        panel.add(tfSexo);

        panel.add(CriarObjGrafico.criarJLabel("Endereço", 240, 60, 80, 14));
        tfEndereco = CriarObjGrafico.criarJTextField(240, 78, 170, 20);
        tfEndereco.setEditable(false);
        tfEndereco.setBackground(Color.WHITE);
        panel.add(tfEndereco);

        panel.add(CriarObjGrafico.criarJLabel("Bairro", 420, 60, 80, 14));
        tfBairro = CriarObjGrafico.criarJTextField(420, 78, 140, 20);
        tfBairro.setEditable(false);
        tfBairro.setBackground(Color.WHITE);
        panel.add(tfBairro);

        panel.add(CriarObjGrafico.criarJLabel("Número", 570, 60, 80, 14));
        tfNumero = CriarObjGrafico.criarJTextField(570, 78, 100, 20);
        tfNumero.setEditable(false);
        tfNumero.setBackground(Color.WHITE);
        panel.add(tfNumero);

        panel.add(CriarObjGrafico.criarJLabel("Cidade", 680, 60, 80, 14));
        tfCidade = CriarObjGrafico.criarJTextField(680, 78, 150, 20);
        tfCidade.setEditable(false);
        tfCidade.setBackground(Color.WHITE);
        panel.add(tfCidade);

        panel.add(CriarObjGrafico.criarJLabel("Estado", 20, 100, 80, 14));
        tfEstado = CriarObjGrafico.criarJTextField(20, 118, 120, 20);
        tfEstado.setEditable(false);
        tfEstado.setBackground(Color.WHITE);
        panel.add(tfEstado);

        panel.add(CriarObjGrafico.criarJLabel("Complemento", 150, 100, 80, 14));
        tfComplemento = CriarObjGrafico.criarJTextField(150, 118, 100, 20);
        tfComplemento.setEditable(false);
        tfComplemento.setBackground(Color.WHITE);
        panel.add(tfComplemento);

        panel.add(CriarObjGrafico.criarJLabel("Fone", 260, 100, 80, 14));
        tfFone = CriarObjGrafico.criarJTextField(260, 118, 100, 20);
        tfFone.setEditable(false);
        tfFone.setBackground(Color.WHITE);
        panel.add(tfFone);

        panel.add(CriarObjGrafico.criarJLabel("Celular", 370, 100, 80, 14));
        tfCelular = CriarObjGrafico.criarJTextField(370, 118, 100, 20);
        tfCelular.setEditable(false);
        tfCelular.setBackground(Color.WHITE);
        panel.add(tfCelular);

        panel.add(CriarObjGrafico.criarJLabel("Celular Empresa", 480, 100, 100, 14));
        tfCelularEmpresa = CriarObjGrafico.criarJFormattedTextField("(##)####-####", 480, 118, 100, 20);
        panel.add(tfCelularEmpresa);

        panel.add(CriarObjGrafico.criarJLabel("Empresa", 590, 100, 80, 14));
        tfEmpresa = CriarObjGrafico.criarJTextField(590, 118, 130, 20);
        tfEmpresa.setEditable(false);
        tfEmpresa.setBackground(Color.WHITE);
        panel.add(tfEmpresa);

        panel.add(CriarObjGrafico.criarJLabel("Fone Empresa", 730, 100, 100, 14));
        tfFoneEmpresa = CriarObjGrafico.criarJTextField(730, 118, 100, 20);
        tfFoneEmpresa.setEditable(false);
        tfFoneEmpresa.setBackground(Color.WHITE);
        panel.add(tfFoneEmpresa);

        panel.add(CriarObjGrafico.criarJLabel("E-Mail", 20, 140, 60, 14));
        tfEmail = CriarObjGrafico.criarJTextField(20, 158, 285, 20);
        tfEmail.setEditable(false);
        tfEmail.setBackground(Color.WHITE);
        panel.add(tfEmail);

        panel.add(CriarObjGrafico.criarJLabel("MSN", 310, 140, 60, 14));
        tfMsn = CriarObjGrafico.criarJTextField(310, 158, 285, 20);
        tfMsn.setEditable(false);
        tfMsn.setBackground(Color.WHITE);
        panel.add(tfMsn);

        panel.add(CriarObjGrafico.criarJLabel("Skype", 600, 140, 60, 14));
        tfSkype = CriarObjGrafico.criarJTextField(600, 158, 230, 20);
        tfSkype.setEditable(false);
        tfSkype.setBackground(Color.WHITE);
        panel.add(tfSkype);

        panel.add(CriarObjGrafico.criarJLabel("E-Mail Vendedor", 20, 180, 100, 14));
        tfEmailVendedor = CriarObjGrafico.criarJTextField(20, 198, 285, 20);
        panel.add(tfEmailVendedor);

        panel.add(CriarObjGrafico.criarJLabel("Descrição", 315, 180, 80, 14));
        tfDescricao = CriarObjGrafico.criarJTextField(315, 198, 515, 20);
        panel.add(tfDescricao);

        panel.add(CriarObjGrafico.criarJLabel("Carteira Habilitação", 20, 220, 120, 14));
        tfCarteiraHabilitacao = CriarObjGrafico.criarJTextField(20, 238, 120, 20);
        tfCarteiraHabilitacao.setEditable(false);
        tfCarteiraHabilitacao.setBackground(Color.WHITE);
        panel.add(tfCarteiraHabilitacao);

        panel.add(CriarObjGrafico.criarJLabel("N° Cart. Trab. / Série", 150, 220, 160, 14));
        tfCarteiraTrabalho = CriarObjGrafico.criarJTextField(150, 238, 120, 20);
        tfCarteiraTrabalho.setEditable(false);
        tfCarteiraTrabalho.setBackground(Color.WHITE);
        panel.add(tfCarteiraTrabalho);

        panel.add(CriarObjGrafico.criarJLabel("Salário", 280, 220, 80, 14));
        tfSalario = CriarObjGrafico.criarJTextField(280, 238, 100, 20);
        tfSalario.setEditable(false);
        tfSalario.setBackground(Color.WHITE);
        panel.add(tfSalario);

        panel.add(CriarObjGrafico.criarJLabel("Comissão", 390, 220, 80, 14));
        tfComissao = CriarObjGrafico.criarJTextField(390, 238, 100, 20);
        tfComissao.setText("0.00");
        panel.add(tfComissao);

        btCalculaSalario = CriarObjGrafico.criarJButton("Calcular", 500, 233, 86, 26);
        btCalculaSalario.addActionListener(this);
        panel.add(btCalculaSalario);

        panel.add(CriarObjGrafico.criarJLabel("Salário Líquido", 595, 220, 100, 14));
        lbSalarioObrig = CriarObjGrafico.criarJLabel("", 685, 223, 10, 14);
        lbSalarioObrig.setForeground(Color.RED);
        panel.add(lbSalarioObrig);
        tfSalarioLiquido = CriarObjGrafico.criarJTextField(595, 238, 100, 20);
        tfSalarioLiquido.setEditable(false);
        tfSalarioLiquido.setBackground(Color.WHITE);
        panel.add(tfSalarioLiquido);

        panel.add(CriarObjGrafico.criarJLabel("Inscrição Estadual", 705, 220, 110, 14));
        tfInscricaoEstadual = CriarObjGrafico.criarJTextField(705, 238, 125, 20);
        tfInscricaoEstadual.setEditable(false);
        tfInscricaoEstadual.setBackground(Color.WHITE);
        panel.add(tfInscricaoEstadual);

        panel.add(CriarObjGrafico.criarJLabel("CNPJ", 20, 260, 90, 14));
        tfCnpj = CriarObjGrafico.criarJTextField(20, 278, 130, 20);
        tfCnpj.setEditable(false);
        tfCnpj.setBackground(Color.WHITE);
        panel.add(tfCnpj);

        panel.add(CriarObjGrafico.criarJLabel("Home Page", 160, 260, 90, 14));
        tfHomePege = CriarObjGrafico.criarJTextField(160, 278, 500, 20);
        panel.add(tfHomePege);

        panel.add(CriarObjGrafico.criarJLabel("Outras Considerações", 20, 300, 130, 14));
        taOutrasConsidereções = CriarObjGrafico.criarJTextArea(panel, 20, 318, 640, 75);

        btOk = CriarObjGrafico.criarJButton("OK", 752, 320, 86, 26);
        btOk.addActionListener(this);
        panel.add(btOk);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 752, 350, 86, 26);
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        rbNovo = CriarObjGrafico.criarJRadioButton("Novo", 672, 320, 80, 20);
        rbNovo.addItemListener(this);
        panel.add(rbNovo);

        rbAlterar = CriarObjGrafico.criarJRadioButton("Alterar", 672, 340, 80, 20);
        rbAlterar.addItemListener(this);
        panel.add(rbAlterar);

        rbExcluir = CriarObjGrafico.criarJRadioButton("Excluir", 672, 360, 80, 20);
        rbExcluir.addItemListener(this);
        panel.add(rbExcluir);

        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(870, 460);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodVen()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfCPF.setText("");
        tfRG.setText("");
        tfNome.setText("");
        tfSexo.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfCidade.setText("");
        tfEstado.setText("");
        tfComplemento.setText("");
        tfFone.setText("");
        tfCelular.setText("");
        tfCelularEmpresa.setText("");
        tfEmpresa.setText("");
        tfFoneEmpresa.setText("");
        tfEmail.setText("");
        tfMsn.setText("");
        tfSkype.setText("");
        tfEmailVendedor.setText("");
        tfDescricao.setText("");
        tfCarteiraHabilitacao.setText("");
        tfCarteiraTrabalho.setText("");
        tfSalario.setText("");
        tfComissao.setText("0.00");
        tfSalarioLiquido.setText("");
        tfInscricaoEstadual.setText("");
        tfCnpj.setText("");
        tfHomePege.setText("");
        taOutrasConsidereções.setText("");
        lbCPFObrig.setText("");
        lbSalarioObrig.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void recuperaFunc() throws Exception {
        FuncionarioControl controlFunc = new FuncionarioControl(daoFactory);
        if (controlFunc.arqFuncVazio()) {
            throw new Exception("Não há funcionários cadastrados");
        }
        JFormattedTextField entrada = CriarObjGrafico.criarJFormattedTextField("###.###.###-##", 0, 0, 0, 0);
        final Object[] mensagem = {"Informe o CPF do Funcionário", entrada};
        final String[] opcoes = {"OK", "Consultar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Vendedor", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (result) {
            case 0: // Ok
                recuperaFuncionario(controlFunc, entrada.getText());
                break;
            case 1: // Consultar
                PesquisaFuncionario pfn = new PesquisaFuncionario(controlFunc);
                pfn.setModal(true);
                pfn.setVisible(true);
                break;
            case 2: // Cancelar
                break;
        }
    }

    private void recuperaFuncionario(FuncionarioControl controlFunc, String cpf) throws Exception {
        Funcionario funcionario = controlFunc.getFuncionario(cpf);
        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
        } else {
            limparTela();
            tfCPF.setText(funcionario.getCpf());
            tfNome.setText(funcionario.getNome());
            tfRG.setText(funcionario.getRg());
            tfSexo.setText(funcionario.getSexo());
            tfEndereco.setText(funcionario.getEndereco());
            tfBairro.setText(funcionario.getBairro());
            tfNumero.setText(Integer.toString(funcionario.getNumero()));
            tfCidade.setText(funcionario.getCidade());
            tfEstado.setText(funcionario.getEstado());
            tfComplemento.setText(funcionario.getComplemento());
            tfFone.setText(funcionario.getFone());
            tfCelular.setText(funcionario.getCelular1());
            tfEmpresa.setText(funcionario.getEmpresa());
            tfFoneEmpresa.setText(funcionario.getFoneEmpresa());
            tfEmail.setText(funcionario.getEmail());
            tfMsn.setText(funcionario.getMsn());
            tfSkype.setText(funcionario.getSkype());
            tfCarteiraHabilitacao.setText(funcionario.getCarteiraHabilitacao());
            tfCarteiraTrabalho.setText(funcionario.getCarteiraTrabalho());
            tfSalario.setText(Double.toString(funcionario.getSalarioLiquido()));
            tfInscricaoEstadual.setText(funcionario.getInscricaoEstadual());
            tfCnpj.setText(funcionario.getCnpj());
        }
    }

    private void calculoSalario() {
        double salario, comissao;
        try {
            salario = Double.parseDouble(tfSalario.getText());
            comissao = Double.parseDouble(tfComissao.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Caracter inválido certifique se que os campos foram digitado corretamente. ex: 0.00", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        salario += salario * (comissao / 100);
        tfSalarioLiquido.setText(NumberFormat.getCurrencyInstance().format(salario));
    }

    private void gravar() throws Exception {
        if ("".equals(tfCPF.getText())) {
            lbCPFObrig.setText("*");
        } else {
            lbCPFObrig.setText("");
        }
        if ("".equals(tfSalarioLiquido.getText())) {
            lbSalarioObrig.setText("*");
        } else {
            lbSalarioObrig.setText("");
        }
        if (!"".equals(tfCPF.getText()) && !"".equals(tfSalarioLiquido.getText())) {
            Vendedor vendedor = new Vendedor();
            vendedor.setCodigo(Integer.parseInt(tfCodigo.getText()));
            vendedor.setCpf(tfCPF.getText());
            vendedor.setCelularEmpresa(tfCelularEmpresa.getText());
            vendedor.setEmailVendedor(tfEmailVendedor.getText());
            vendedor.setDescricao(tfDescricao.getText());
            vendedor.setHomePage(tfHomePege.getText());
            vendedor.setOutasConsideracoes(taOutrasConsidereções.getText());
            vendedor.setDataCadas(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            vendedor.setUltAlteracao(new Date());
            vendedor.setComissao(Double.parseDouble(tfComissao.getText()));
            vendedor.setSalarioLiquido(Double.parseDouble(tfSalarioLiquido.getText().replace("R$ ", "").replace(".", "").replace(",", ".")));
            Vendedor vendedorLido = controle.getVendedor(tfCPF.getText());
            if (vendedorLido != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "O vendedor com o cpf " + vendedor.getCpf() + " ja esta cadastrado deseja substituilo? ", "Vendedor", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    vendedor.setCodigo(vendedorLido.getCodigo());
                    if (controle.setVendedor(vendedor)) {
                        JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso", "Vendedor", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                } else {
                    return;
                }
            } else {
                if (controle.setVendedor(vendedor)) {
                    JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso", "Vendedor", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                }
            }
        } else {
            throw new Exception("Campos obrigatórios inválidos");
        }
    }

    private void excluir() throws Exception {
        if (controle.arqVenVazio()) {
            JOptionPane.showMessageDialog(null, "Não há vendedores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String cpf = JOptionPane.showInputDialog(null, "Informe o cpf do vendedor a ser excluído:", "Vendedor", JOptionPane.INFORMATION_MESSAGE);
            if (cpf != null) {
                if (controle.removeVendedor(cpf)) {
                    JOptionPane.showMessageDialog(null, "Vendedor excluido com sucesso", "Vendedor", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Vendedor não encontrado", "Vendedor", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.arqVenVazio()) {
            JOptionPane.showMessageDialog(null, "Não há vendedores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String cpf = JOptionPane.showInputDialog(null, "Informe o cpf do vendedor a ser recuperado:", "Vendedor", JOptionPane.INFORMATION_MESSAGE);
            if (cpf != null) {
                Vendedor vendedor = controle.getVendedor(cpf);
                if (vendedor != null) {
                    FuncionarioControl controlFunc = new FuncionarioControl(daoFactory);
                    recuperaFuncionario(controlFunc, cpf);
                    tfCodigo.setText(Integer.toString(vendedor.getCodigo()));
                    tfCPF.setText(vendedor.getCpf());
                    tfCelularEmpresa.setText(vendedor.getCelularEmpresa());
                    tfEmailVendedor.setText(vendedor.getEmailVendedor());
                    tfDescricao.setText(vendedor.getDescricao());
                    tfHomePege.setText(vendedor.getHomePage());
                    taOutrasConsidereções.setText(vendedor.getOutasConsideracoes());
                    tfDataCadas.setText(formatDate.format(vendedor.getDataCadas()) + " as " + formatHora.format(vendedor.getDataCadas()));
                    tfUltAlteracao.setText(formatDate.format(vendedor.getUltAlteracao()) + " as " + formatHora.format(vendedor.getUltAlteracao()));
                    tfComissao.setText(Double.toString(vendedor.getComissao()));
                    tfSalarioLiquido.setText(Double.toString(vendedor.getSalarioLiquido()));
                    JOptionPane.showMessageDialog(null, "Vendedor recuperado com sucesso", "Vendedor", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Vendedor não encontrado", "Vendedor", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void abrirPesquisaVen() throws Exception {
        if (controle.arqVenVazio()) {
            JOptionPane.showMessageDialog(null, "Não há vendedores cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            PesquisaVendedor pve = new PesquisaVendedor(controle);
            pve.setModal(true);
            pve.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Vendedor", JOptionPane.ERROR_MESSAGE);
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
                abrirPesquisaVen();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCalculaSalario) {
            calculoSalario();
        }
        if (evento.getSource() == btPesquisaCPF) {
            try {
                recuperaFunc();
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
}

package Veiculos;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Clientes.ClienteControl;
import Clientes.PesquisaClientes;
import DAOFactory.DAOFactory;
import FormatacaoCampos.CamposInt;
import FormatacaoCampos.CriarObjGrafico;
import FormatacaoCampos.LetrasMaiusculas;
import FormatacaoCampos.LimiteCampoLetMaius;
import FormatacaoCampos.VerificaCampos;
import Modelo.Cliente;
import Modelo.Veiculo;

public class CadasVeiculo extends JDialog implements ActionListener, ItemListener, FocusListener {

    private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfNome, tfMarca,
            tfAno, tfModelo, tfCor, tfPotencia, tfVavulas, tfCavalos,
            tfCilindros, tfRodas, tfPortas, tfLotacao, tfPlaca, tfChassi,
            tfNMotor, tfCpfCnpj, tfNomeClie, tfRenavam;
    private JComboBox cbCombustivel, cbTipo;
    private JTextArea taDescricao;
    private JButton btVer, btPesquisaClie, btPesquisar, btOk, btCancelar;
    private JRadioButton rbNovo, rbAlterar, rbExcluir;
    private JLabel lbNomeObrig, lbMarcaObrig, lbPlacaObrig, lbRenavamObrig,
            lbCpfCnpjClieObrig;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private VeiculoControl controle;
    private DAOFactory daoFactory;
    private Connection con;

    public CadasVeiculo(DAOFactory daoFactory, Connection con) {
        this.daoFactory = daoFactory;
        this.con = con;
        controle = new VeiculoControl(daoFactory, con);
        initComponents();
    }

    private void initComponents() {
        List<String> itensCombo = new ArrayList<String>();
        setTitle("Cadastro de Veículos");
        setLayout(null);
        JPanel panel = CriarObjGrafico.criarJPanel(10, 10, 610, 360);
        add(panel);

        panel.add(CriarObjGrafico.criarJLabel("Codigo", 20, 20, 80, 14));
        tfCodigo = CriarObjGrafico.criarJTextField(20, 38, 80, 20);
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodVei()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfCodigo.setEditable(false);
        tfCodigo.setBackground(Color.WHITE);
        tfCodigo.addFocusListener(this);
        panel.add(tfCodigo);

        btVer = CriarObjGrafico.criarJButton("...", 106, 34, 31, 24);
        btVer.addActionListener(this);
        panel.add(btVer);

        panel.add(CriarObjGrafico.criarJLabel("Cadastrado em", 143, 20, 90, 14));
        tfDataCadas = CriarObjGrafico.criarJTextField(143, 38, 120, 20);
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataCadas.setEditable(false);
        tfDataCadas.setBackground(Color.WHITE);
        tfDataCadas.addFocusListener(this);
        panel.add(tfDataCadas);

        panel.add(CriarObjGrafico.criarJLabel("Última Alteração", 270, 20, 100, 14));
        tfUltAlteracao = CriarObjGrafico.criarJTextField(270, 38, 120, 20);
        tfUltAlteracao.setEditable(false);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfUltAlteracao.addFocusListener(this);
        panel.add(tfUltAlteracao);

        panel.add(CriarObjGrafico.criarJLabel("Nome", 400, 20, 120, 14));
        lbNomeObrig = CriarObjGrafico.criarJLabel("", 435, 23, 10, 14);
        lbNomeObrig.setForeground(Color.RED);
        panel.add(lbNomeObrig);
        tfNome = CriarObjGrafico.criarJTextField(400, 38, 180, 20);
        tfNome.addFocusListener(this);
        panel.add(tfNome);

        panel.add(CriarObjGrafico.criarJLabel("Marca", 20, 60, 120, 14));
        lbMarcaObrig = CriarObjGrafico.criarJLabel("", 60, 63, 10, 14);
        lbMarcaObrig.setForeground(Color.RED);
        panel.add(lbMarcaObrig);
        tfMarca = CriarObjGrafico.criarJTextField(20, 78, 120, 20);
        tfMarca.addFocusListener(this);
        panel.add(tfMarca);

        panel.add(CriarObjGrafico.criarJLabel("Ano", 150, 60, 120, 14));
        tfAno = CriarObjGrafico.criarJTextField(150, 78, 80, 20);
        tfAno.addFocusListener(this);
        tfAno.setDocument(new CamposInt());
        panel.add(tfAno);

        panel.add(CriarObjGrafico.criarJLabel("Modelo", 240, 60, 120, 14));
        tfModelo = CriarObjGrafico.criarJTextField(240, 78, 80, 20);
        tfModelo.addFocusListener(this);
        tfModelo.setDocument(new CamposInt());
        panel.add(tfModelo);

        panel.add(CriarObjGrafico.criarJLabel("Cor", 330, 60, 120, 14));
        tfCor = CriarObjGrafico.criarJTextField(330, 78, 110, 20);
        tfCor.addFocusListener(this);
        panel.add(tfCor);

        panel.add(CriarObjGrafico.criarJLabel("Combustivel", 450, 60, 90, 14));
        itensCombo.add("Gasolina");
        itensCombo.add("Alcool");
        itensCombo.add("Flex");
        itensCombo.add("GNV");
        itensCombo.add("Diesel");
        cbCombustivel = CriarObjGrafico.criarJComboBox(itensCombo, 450, 78, 130, 20);
        cbCombustivel.addFocusListener(this);
        panel.add(cbCombustivel);

        panel.add(CriarObjGrafico.criarJLabel("Tipo", 20, 100, 90, 14));
        itensCombo.clear();
        itensCombo.add("Carro");
        itensCombo.add("Moto");
        itensCombo.add("Caminhão Simples");
        itensCombo.add("Caminhão Baú");
        itensCombo.add("Carreta");
        itensCombo.add("Bi-Trem");
        cbTipo = CriarObjGrafico.criarJComboBox(itensCombo, 20, 118, 140, 20);
        cbTipo.addFocusListener(this);
        panel.add(cbTipo);

        panel.add(CriarObjGrafico.criarJLabel("Potência", 170, 100, 120, 14));
        tfPotencia = CriarObjGrafico.criarJTextField(170, 118, 95, 20);
        tfPotencia.addFocusListener(this);
        panel.add(tfPotencia);

        panel.add(CriarObjGrafico.criarJLabel("Vávulas", 275, 100, 120, 14));
        tfVavulas = CriarObjGrafico.criarJTextField(275, 118, 95, 20);
        tfVavulas.addFocusListener(this);
        tfVavulas.setDocument(new CamposInt());
        panel.add(tfVavulas);

        panel.add(CriarObjGrafico.criarJLabel("Cavalos", 380, 100, 120, 14));
        tfCavalos = CriarObjGrafico.criarJTextField(380, 118, 95, 20);
        tfCavalos.addFocusListener(this);
        tfCavalos.setDocument(new CamposInt());
        panel.add(tfCavalos);

        panel.add(CriarObjGrafico.criarJLabel("Cilíndros", 485, 100, 120, 14));
        tfCilindros = CriarObjGrafico.criarJTextField(485, 118, 95, 20);
        tfCilindros.addFocusListener(this);
        tfCilindros.setDocument(new CamposInt());
        panel.add(tfCilindros);

        panel.add(CriarObjGrafico.criarJLabel("Rodas", 20, 140, 120, 14));
        tfRodas = CriarObjGrafico.criarJTextField(20, 158, 100, 20);
        tfRodas.addFocusListener(this);
        panel.add(tfRodas);

        panel.add(CriarObjGrafico.criarJLabel("Portas", 130, 140, 120, 14));
        tfPortas = CriarObjGrafico.criarJTextField(130, 158, 100, 20);
        tfPortas.addFocusListener(this);
        tfPortas.setDocument(new CamposInt());
        panel.add(tfPortas);

        panel.add(CriarObjGrafico.criarJLabel("Lotação", 240, 140, 120, 14));
        tfLotacao = CriarObjGrafico.criarJTextField(240, 158, 100, 20);
        tfLotacao.addFocusListener(this);
        tfLotacao.setDocument(new CamposInt());
        panel.add(tfLotacao);

        panel.add(CriarObjGrafico.criarJLabel("Placa", 350, 140, 80, 14));
        lbPlacaObrig = CriarObjGrafico.criarJLabel("", 385, 143, 10, 14);
        lbPlacaObrig.setForeground(Color.RED);
        panel.add(lbPlacaObrig);
        tfPlaca = CriarObjGrafico.criarJTextField(350, 158, 100, 20);
        tfPlaca.setDocument(new LimiteCampoLetMaius(8));
        tfPlaca.addFocusListener(this);
        panel.add(tfPlaca);

        panel.add(CriarObjGrafico.criarJLabel("Chassi", 460, 140, 80, 14));
        tfChassi = CriarObjGrafico.criarJTextField(460, 158, 120, 20);
        tfChassi.setDocument(new LetrasMaiusculas());
        tfChassi.addFocusListener(this);
        panel.add(tfChassi);

        panel.add(CriarObjGrafico.criarJLabel("RENAVAM", 20, 180, 80, 14));
        lbRenavamObrig = CriarObjGrafico.criarJLabel("", 80, 183, 10, 14);
        lbRenavamObrig.setForeground(Color.RED);
        panel.add(lbRenavamObrig);
        tfRenavam = CriarObjGrafico.criarJTextField(20, 198, 120, 20);
        tfRenavam.setDocument(new LetrasMaiusculas());
        tfRenavam.addFocusListener(this);
        panel.add(tfRenavam);

        panel.add(CriarObjGrafico.criarJLabel("N° Motor", 150, 180, 80, 14));
        tfNMotor = CriarObjGrafico.criarJTextField(150, 198, 100, 20);
        tfNMotor.addFocusListener(this);
        tfNMotor.setDocument(new CamposInt());
        panel.add(tfNMotor);

        panel.add(CriarObjGrafico.criarJLabel("CPF / CNPJ", 260, 180, 80, 14));
        lbCpfCnpjClieObrig = CriarObjGrafico.criarJLabel("", 330, 183, 10, 14);
        lbCpfCnpjClieObrig.setForeground(Color.RED);
        panel.add(lbCpfCnpjClieObrig);
        tfCpfCnpj = CriarObjGrafico.criarJTextField(260, 198, 130, 20);
        tfCpfCnpj.setEditable(false);
        tfCpfCnpj.setBackground(Color.WHITE);
        tfCpfCnpj.addFocusListener(this);
        panel.add(tfCpfCnpj);

        btPesquisaClie = CriarObjGrafico.criarJButton("...", 400, 194, 31, 24);
        btPesquisaClie.addActionListener(this);
        panel.add(btPesquisaClie);

        panel.add(CriarObjGrafico.criarJLabel("Nome Cliente", 440, 180, 80, 14));
        tfNomeClie = CriarObjGrafico.criarJTextField(440, 198, 140, 20);
        tfNomeClie.setEditable(false);
        tfNomeClie.setBackground(Color.WHITE);
        tfNomeClie.addFocusListener(this);
        panel.add(tfNomeClie);

        panel.add(CriarObjGrafico.criarJLabel("Descrição", 20, 220, 130, 14));
        taDescricao = CriarObjGrafico.criarJTextArea(panel, 20, 238, 560, 100);
        taDescricao.addFocusListener(this);

        btPesquisar = CriarObjGrafico.criarJButton("Pesquisar", 670, 70, 95, 26);
        btPesquisar.addActionListener(this);
        add(btPesquisar);

        btOk = CriarObjGrafico.criarJButton("OK", 670, 210, 86, 26);
        btOk.addActionListener(this);
        add(btOk);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 670, 250, 86, 26);
        btCancelar.addActionListener(this);
        add(btCancelar);

        rbNovo = CriarObjGrafico.criarJRadioButton("Novo", 675, 120, 80, 20);
        rbNovo.addItemListener(this);
        add(rbNovo);

        rbAlterar = CriarObjGrafico.criarJRadioButton("Alterar", 675, 140, 80, 20);
        rbAlterar.addItemListener(this);
        add(rbAlterar);

        rbExcluir = CriarObjGrafico.criarJRadioButton("Excluir", 675, 160, 80, 20);
        rbExcluir.addItemListener(this);
        add(rbExcluir);

        HashSet conj = new HashSet(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(810, 410);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfCodigo.setText(Integer.toString(controle.getProxCodVei()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfUltAlteracao.setText("");
        tfNome.setText("");
        tfMarca.setText("");
        tfAno.setText("");
        tfModelo.setText("");
        tfCor.setText("");
        tfPotencia.setText("");
        tfVavulas.setText("");
        tfCavalos.setText("");
        tfCilindros.setText("");
        tfRodas.setText("");
        tfPortas.setText("");
        tfLotacao.setText("");
        tfPlaca.setText("");
        tfChassi.setText("");
        tfNMotor.setText("");
        tfCpfCnpj.setText("");
        tfNomeClie.setText("");
        tfRenavam.setText("");
        cbCombustivel.setSelectedItem("Selecione");
        cbTipo.setSelectedItem("Selecione");
        taDescricao.setText("");
        lbNomeObrig.setText("");
        lbMarcaObrig.setText("");
        lbPlacaObrig.setText("");
        lbRenavamObrig.setText("");
        lbCpfCnpjClieObrig.setText("");
        rbNovo.setSelected(false);
        rbAlterar.setSelected(false);
        rbExcluir.setSelected(false);
    }

    private void gravar() throws Exception {
        if ("".equals(tfNome.getText())) {
            lbNomeObrig.setText("*");
        } else {
            lbNomeObrig.setText("");
        }
        if ("".equals(tfMarca.getText())) {
            lbMarcaObrig.setText("*");
        } else {
            lbMarcaObrig.setText("");
        }
        if ("".equals(tfPlaca.getText())) {
            lbPlacaObrig.setText("*");
        } else {
            lbPlacaObrig.setText("");
        }
        if ("".equals(tfRenavam.getText())) {
            lbRenavamObrig.setText("*");
        } else {
            lbRenavamObrig.setText("");
        }
        if ("".equals(tfCpfCnpj.getText())) {
            lbCpfCnpjClieObrig.setText("*");
        } else {
            lbCpfCnpjClieObrig.setText("");
        }
        if (!"".equals(tfNome.getText()) && !"".equals(tfMarca.getText()) && !"".equals(tfPlaca.getText()) && !"".equals(tfRenavam.getText()) && !"".equals(tfCpfCnpj.getText())) {
            Veiculo veiculo = new Veiculo();
            veiculo.setCodigo(Integer.parseInt(tfCodigo.getText()));
            veiculo.setNumeroMotor(VerificaCampos.verificaInt(tfNMotor, "número motor"));
            veiculo.setPortas(VerificaCampos.verificaInt(tfPortas, "portas"));
            veiculo.setAno(VerificaCampos.verificaInt(tfAno, "ano"));
            veiculo.setModelo(VerificaCampos.verificaInt(tfModelo, "modelo"));
            veiculo.setCilindros(VerificaCampos.verificaInt(tfCilindros, "cilíndros"));
            veiculo.setCavalos(VerificaCampos.verificaInt(tfCavalos, "cavalos"));
            veiculo.setLotacao(VerificaCampos.verificaInt(tfLotacao, "lotação"));
            veiculo.setVavulas(VerificaCampos.verificaInt(tfVavulas, "vávulas"));
            veiculo.setNome(tfNome.getText());
            veiculo.setMarca(tfMarca.getText());
            veiculo.setCor(tfCor.getText());
            veiculo.setRodas(tfRodas.getText());
            veiculo.setPlaca(tfPlaca.getText());
            veiculo.setChassi(tfChassi.getText());
            veiculo.setRenavam(tfRenavam.getText());
            veiculo.setCombustivel((String) cbCombustivel.getSelectedItem());
            veiculo.setTipo((String) cbTipo.getSelectedItem());
            veiculo.setCpfCNPJCliente(tfCpfCnpj.getText());
            veiculo.setDescricao(taDescricao.getText());
            veiculo.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
            veiculo.setUltAlteracao(new Date());
            veiculo.setPotencia(VerificaCampos.verificaDouble(tfPotencia, "potência"));
            Veiculo veiculoLido = controle.getVeiculo(tfPlaca.getText());
            if (veiculoLido != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "O veículo com a placa " + veiculo.getPlaca() + " ja esta cadastrado deseja substituilo? ", "Veículo", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    veiculo.setCodigo(veiculoLido.getCodigo());
                    if (controle.setVeiculo(veiculo)) {
                        JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso", "Veículo", JOptionPane.INFORMATION_MESSAGE);
                        limparTela();
                    }
                } else {
                    return;
                }
            } else {
                if (controle.setVeiculo(veiculo)) {
                    JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso", "Veículo", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                }
            }
        } else {
            throw new Exception("Campos * obrigatórios inválidos");
        }
    }

    private void excluir() throws Exception {
        if (controle.isVeiVazio()) {
            JOptionPane.showMessageDialog(null, "Não há veículos cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String placa = JOptionPane.showInputDialog(null, "Informe a placa do veículo a ser excluído:", "Veículo", JOptionPane.INFORMATION_MESSAGE);
            if (placa != null) {
                if (controle.removeVeiculo(placa)) {
                    JOptionPane.showMessageDialog(null, "Veículo excluido com sucesso", "Veículo", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Veículo não encontrado", "Veículo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void recuperar() throws Exception {
        if (controle.isVeiVazio()) {
            JOptionPane.showMessageDialog(null, "Não há veículos cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String placa = JOptionPane.showInputDialog(null, "Informe a placa do veículo a ser recuperado:", "Veículo", JOptionPane.INFORMATION_MESSAGE);
            if (placa != null) {
                Veiculo veiculo = controle.getVeiculo(placa);
                if (veiculo != null) {
                    limparTela();
                    tfCodigo.setText(Integer.toString(veiculo.getCodigo()));
                    tfNMotor.setText(VerificaCampos.recuperaCampoStr(veiculo.getNumeroMotor()));
                    tfPortas.setText(VerificaCampos.recuperaCampoStr(veiculo.getPortas()));
                    tfAno.setText(VerificaCampos.recuperaCampoStr(veiculo.getAno()));
                    tfModelo.setText(VerificaCampos.recuperaCampoStr(veiculo.getModelo()));
                    tfCilindros.setText(VerificaCampos.recuperaCampoStr(veiculo.getCilindros()));
                    tfCavalos.setText(VerificaCampos.recuperaCampoStr(veiculo.getCavalos()));
                    tfLotacao.setText(VerificaCampos.recuperaCampoStr(veiculo.getLotacao()));
                    tfVavulas.setText(VerificaCampos.recuperaCampoStr(veiculo.getVavulas()));
                    tfNome.setText(veiculo.getNome());
                    tfMarca.setText(veiculo.getMarca());
                    tfCor.setText(veiculo.getCor());
                    tfRodas.setText(veiculo.getRodas());
                    tfPlaca.setText(veiculo.getPlaca());
                    tfChassi.setText(veiculo.getChassi());
                    tfRenavam.setText(veiculo.getRenavam());
                    cbCombustivel.setSelectedItem(veiculo.getCombustivel());
                    cbTipo.setSelectedItem(veiculo.getTipo());
                    taDescricao.setText(veiculo.getDescricao());
                    tfDataCadas.setText(formatDate.format(veiculo.getDataCadastro()) + " as " + formatHora.format(veiculo.getDataCadastro()));
                    tfUltAlteracao.setText(formatDate.format(veiculo.getUltAlteracao()) + " as " + formatHora.format(veiculo.getUltAlteracao()));
                    tfPotencia.setText(VerificaCampos.recuperaCampoStr(veiculo.getPotencia()));
                    ClienteControl controlClie = new ClienteControl(daoFactory, con);
                    recuperaCliente(controlClie, veiculo.getCpfCNPJCliente());
                    JOptionPane.showMessageDialog(null, "Veículo recuperado com sucesso", "Veículo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Veículo não encontrado", "Veículo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void abrirPesquisaVei() throws Exception {
        if (controle.isVeiVazio()) {
            JOptionPane.showMessageDialog(null, "Não há veículos cadastrados", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            PesquisaVeiculo pve = new PesquisaVeiculo(controle);
            pve.setModal(true);
            pve.setVisible(true);
        }
    }

    private void recuperaClie() throws Exception {
        ClienteControl controlClie = new ClienteControl(daoFactory, con);
        if (controlClie.isClieVazio()) {
            throw new Exception("Não há clientes cadastrados");
        }
        JTextField entrada = new JTextField();
        final Object[] mensagem = {"Informe o CPF / CNPJ do cliente", entrada};
        final String[] opcoes = {"OK", "Consultar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, mensagem, "Cliente", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (result) {
            case 0: // Ok
                recuperaCliente(controlClie, entrada.getText());
                break;
            case 1: // Consultar
                PesquisaClientes pcl = new PesquisaClientes(controlClie);
                pcl.setModal(true);
                pcl.setVisible(true);
                break;
            case 2: // Cancelar
                break;
        }
    }

    private void recuperaCliente(ClienteControl controlClie, String cpfCNPJ) throws Exception {
        Cliente cliente = controlClie.getCliente(cpfCNPJ);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
        } else {
            tfCpfCnpj.setText(cliente.getCpfCNPJ());
            tfNomeClie.setText(cliente.getNome());
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            if (rbNovo.isSelected() == false & rbAlterar.isSelected() == false & rbExcluir.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Selecione uma opção", "Veículo", JOptionPane.ERROR_MESSAGE);
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
                abrirPesquisaVei();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btPesquisar) {
            try {
                // abrirPesquisaClie();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btPesquisaClie) {
            try {
                recuperaClie();
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

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCodigo) {
            tfCodigo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDataCadas) {
            tfDataCadas.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUltAlteracao) {
            tfUltAlteracao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNome) {
            tfNome.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfMarca) {
            tfMarca.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfAno) {
            tfAno.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfModelo) {
            tfModelo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCor) {
            tfCor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPotencia) {
            tfPotencia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfVavulas) {
            tfVavulas.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCavalos) {
            tfCavalos.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCilindros) {
            tfCilindros.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfRodas) {
            tfRodas.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPortas) {
            tfPortas.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfLotacao) {
            tfLotacao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPlaca) {
            tfPlaca.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfChassi) {
            tfChassi.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNMotor) {
            tfNMotor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCpfCnpj) {
            tfCpfCnpj.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeClie) {
            tfNomeClie.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbCombustivel) {
            cbCombustivel.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbTipo) {
            cbTipo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == taDescricao) {
            taDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfRenavam) {
            tfRenavam.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfCodigo.setBackground(Color.WHITE);
        tfDataCadas.setBackground(Color.WHITE);
        tfUltAlteracao.setBackground(Color.WHITE);
        tfNome.setBackground(Color.WHITE);
        tfMarca.setBackground(Color.WHITE);
        tfAno.setBackground(Color.WHITE);
        tfModelo.setBackground(Color.WHITE);
        tfCor.setBackground(Color.WHITE);
        tfPotencia.setBackground(Color.WHITE);
        tfVavulas.setBackground(Color.WHITE);
        tfCavalos.setBackground(Color.WHITE);
        tfCilindros.setBackground(Color.WHITE);
        tfRodas.setBackground(Color.WHITE);
        tfPortas.setBackground(Color.WHITE);
        tfLotacao.setBackground(Color.WHITE);
        tfPlaca.setBackground(Color.WHITE);
        tfChassi.setBackground(Color.WHITE);
        tfNMotor.setBackground(Color.WHITE);
        tfCpfCnpj.setBackground(Color.WHITE);
        tfNomeClie.setBackground(Color.WHITE);
        cbCombustivel.setBackground(Color.WHITE);
        cbTipo.setBackground(Color.WHITE);
        taDescricao.setBackground(Color.WHITE);
        tfRenavam.setBackground(Color.WHITE);
    }
}

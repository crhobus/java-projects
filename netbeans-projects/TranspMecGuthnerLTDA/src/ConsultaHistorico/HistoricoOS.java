package ConsultaHistorico;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import Veiculos.PesquisaVeiculo;
import Veiculos.VeiculoControl;

import Clientes.ClienteControl;
import Clientes.PesquisaClientes;
import DAOFactory.DAOFactory;
import FormatacaoCampos.CamposInt;
import FormatacaoCampos.CriarObjGrafico;
import FormatacaoCampos.LimiteCampoLetMaius;
import Modelo.Cliente;
import Modelo.ItensOS;
import Modelo.OrdemServico;
import Modelo.Veiculo;
import NovaOS.ItemOSControl;
import NovaOS.NovaOSControl;

public class HistoricoOS extends JDialog implements ActionListener, FocusListener {

    private JTextField tfCPFCNPJ, tfRGIE, tfNomeClie, tfEndereco, tfBairro, tfNumero, tfComplemento,
            tfCidade, tfUF, tfReferencia, tfCEP, tfFone, tfCelular, tfEmail, tfPlaca, tfNomeVei, tfMarca,
            tfAno, tfModelo, tfCor, tfPotencia, tfVavulas, tfCavalos, tfCilindros, tfCombustivel, tfTipo,
            tfChassi, tfRenavam;
    private JButton btVerClie, btVerVei, btCancelar;
    private DAOFactory daoFactory;
    private Connection con;
    private TableModel tableModel;

    public HistoricoOS(DAOFactory daoFactory, Connection con, int flag) {
        this.daoFactory = daoFactory;
        this.con = con;
        tableModel = new TableModel();
        initComponents();
    }

    private void initComponents() {
        setTitle("Histórico das Ordens de Serviço");
        setLayout(null);
        JPanel panel = CriarObjGrafico.criarJPanel(10, 10, 875, 655);
        add(panel);

        panel.add(CriarObjGrafico.criarJLabel("CPF / CNPJ", 20, 20, 80, 14));
        tfCPFCNPJ = CriarObjGrafico.criarJTextField(20, 38, 120, 20);
        tfCPFCNPJ.addFocusListener(this);
        panel.add(tfCPFCNPJ);

        btVerClie = CriarObjGrafico.criarJButton("...", 150, 34, 31, 24);
        btVerClie.addActionListener(this);
        panel.add(btVerClie);

        panel.add(CriarObjGrafico.criarJLabel("RG / IE", 190, 20, 80, 14));
        tfRGIE = CriarObjGrafico.criarJTextField(190, 38, 120, 20);
        tfRGIE.addFocusListener(this);
        tfRGIE.setEditable(false);
        tfRGIE.setBackground(Color.WHITE);
        panel.add(tfRGIE);

        panel.add(CriarObjGrafico.criarJLabel("Nome / Razão Social", 320, 20, 120, 14));
        tfNomeClie = CriarObjGrafico.criarJTextField(320, 38, 260, 20);
        tfNomeClie.addFocusListener(this);
        tfNomeClie.setEditable(false);
        tfNomeClie.setBackground(Color.WHITE);
        panel.add(tfNomeClie);

        panel.add(CriarObjGrafico.criarJLabel("Endereço", 590, 20, 80, 14));
        tfEndereco = CriarObjGrafico.criarJTextField(590, 38, 250, 20);
        tfEndereco.addFocusListener(this);
        tfEndereco.setEditable(false);
        tfEndereco.setBackground(Color.WHITE);
        panel.add(tfEndereco);

        panel.add(CriarObjGrafico.criarJLabel("Bairro", 20, 60, 80, 14));
        tfBairro = CriarObjGrafico.criarJTextField(20, 78, 200, 20);
        tfBairro.addFocusListener(this);
        tfBairro.setEditable(false);
        tfBairro.setBackground(Color.WHITE);
        panel.add(tfBairro);

        panel.add(CriarObjGrafico.criarJLabel("Número", 230, 60, 80, 14));
        tfNumero = CriarObjGrafico.criarJTextField(230, 78, 100, 20);
        tfNumero.addFocusListener(this);
        tfNumero.setEditable(false);
        tfNumero.setBackground(Color.WHITE);
        panel.add(tfNumero);

        panel.add(CriarObjGrafico.criarJLabel("Complemento", 340, 60, 90, 14));
        tfComplemento = CriarObjGrafico.criarJTextField(340, 78, 150, 20);
        tfComplemento.addFocusListener(this);
        tfComplemento.setEditable(false);
        tfComplemento.setBackground(Color.WHITE);
        panel.add(tfComplemento);

        panel.add(CriarObjGrafico.criarJLabel("Cidade", 500, 60, 80, 14));
        tfCidade = CriarObjGrafico.criarJTextField(500, 78, 170, 20);
        tfCidade.addFocusListener(this);
        tfCidade.setEditable(false);
        tfCidade.setBackground(Color.WHITE);
        panel.add(tfCidade);

        panel.add(CriarObjGrafico.criarJLabel("UF", 680, 60, 80, 14));
        tfUF = CriarObjGrafico.criarJTextField(680, 78, 160, 20);
        tfUF.addFocusListener(this);
        tfUF.setEditable(false);
        tfUF.setBackground(Color.WHITE);
        panel.add(tfUF);

        panel.add(CriarObjGrafico.criarJLabel("Referência", 20, 100, 80, 14));
        tfReferencia = CriarObjGrafico.criarJTextField(20, 118, 180, 20);
        tfReferencia.addFocusListener(this);
        tfReferencia.setEditable(false);
        tfReferencia.setBackground(Color.WHITE);
        panel.add(tfReferencia);

        panel.add(CriarObjGrafico.criarJLabel("CEP", 210, 100, 80, 14));
        tfCEP = CriarObjGrafico.criarJTextField(210, 118, 120, 20);
        tfCEP.addFocusListener(this);
        tfCEP.setEditable(false);
        tfCEP.setBackground(Color.WHITE);
        panel.add(tfCEP);

        panel.add(CriarObjGrafico.criarJLabel("Fone", 340, 100, 70, 14));
        tfFone = CriarObjGrafico.criarJTextField(340, 118, 100, 20);
        tfFone.addFocusListener(this);
        tfFone.setEditable(false);
        tfFone.setBackground(Color.WHITE);
        panel.add(tfFone);

        panel.add(CriarObjGrafico.criarJLabel("Celular", 450, 100, 80, 14));
        tfCelular = CriarObjGrafico.criarJTextField(450, 118, 100, 20);
        tfCelular.addFocusListener(this);
        tfCelular.setEditable(false);
        tfCelular.setBackground(Color.WHITE);
        panel.add(tfCelular);

        panel.add(CriarObjGrafico.criarJLabel("E-Mail", 560, 100, 70, 14));
        tfEmail = CriarObjGrafico.criarJTextField(560, 118, 280, 20);
        tfEmail.addFocusListener(this);
        tfEmail.setEditable(false);
        tfEmail.setBackground(Color.WHITE);
        panel.add(tfEmail);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(0, 170, 875, 1);
        separator1.setBackground(Color.BLACK);
        panel.add(separator1);

        panel.add(CriarObjGrafico.criarJLabel("Placa", 20, 200, 80, 14));
        tfPlaca = CriarObjGrafico.criarJTextField(20, 218, 100, 20);
        tfPlaca.setDocument(new LimiteCampoLetMaius(8));
        tfPlaca.addFocusListener(this);
        panel.add(tfPlaca);

        btVerVei = CriarObjGrafico.criarJButton("...", 130, 214, 31, 24);
        btVerVei.addActionListener(this);
        panel.add(btVerVei);

        panel.add(CriarObjGrafico.criarJLabel("Nome", 170, 200, 90, 14));
        tfNomeVei = CriarObjGrafico.criarJTextField(170, 218, 200, 20);
        tfNomeVei.addFocusListener(this);
        tfNomeVei.setEditable(false);
        tfNomeVei.setBackground(Color.WHITE);
        panel.add(tfNomeVei);

        panel.add(CriarObjGrafico.criarJLabel("Marca", 380, 200, 90, 14));
        tfMarca = CriarObjGrafico.criarJTextField(380, 218, 130, 20);
        tfMarca.setEditable(false);
        tfMarca.setBackground(Color.WHITE);
        tfMarca.addFocusListener(this);
        panel.add(tfMarca);

        panel.add(CriarObjGrafico.criarJLabel("Ano", 520, 200, 90, 14));
        tfAno = CriarObjGrafico.criarJTextField(520, 218, 90, 20);
        tfAno.setEditable(false);
        tfAno.setBackground(Color.WHITE);
        tfAno.addFocusListener(this);
        panel.add(tfAno);

        panel.add(CriarObjGrafico.criarJLabel("Modelo", 620, 200, 90, 14));
        tfModelo = CriarObjGrafico.criarJTextField(620, 218, 90, 20);
        tfModelo.addFocusListener(this);
        tfModelo.setEditable(false);
        tfModelo.setBackground(Color.WHITE);
        panel.add(tfModelo);

        panel.add(CriarObjGrafico.criarJLabel("Cor", 720, 200, 90, 14));
        tfCor = CriarObjGrafico.criarJTextField(720, 218, 120, 20);
        tfCor.setEditable(false);
        tfCor.setBackground(Color.WHITE);
        tfCor.addFocusListener(this);
        panel.add(tfCor);

        panel.add(CriarObjGrafico.criarJLabel("Potência", 20, 240, 90, 14));
        tfPotencia = CriarObjGrafico.criarJTextField(20, 258, 80, 20);
        tfPotencia.setEditable(false);
        tfPotencia.setBackground(Color.WHITE);
        tfPotencia.addFocusListener(this);
        panel.add(tfPotencia);

        panel.add(CriarObjGrafico.criarJLabel("Vávulas", 110, 240, 90, 14));
        tfVavulas = CriarObjGrafico.criarJTextField(110, 258, 80, 20);
        tfVavulas.setEditable(false);
        tfVavulas.setBackground(Color.WHITE);
        tfVavulas.addFocusListener(this);
        panel.add(tfVavulas);

        panel.add(CriarObjGrafico.criarJLabel("Cavalos", 200, 240, 90, 14));
        tfCavalos = CriarObjGrafico.criarJTextField(200, 258, 80, 20);
        tfCavalos.setEditable(false);
        tfCavalos.setBackground(Color.WHITE);
        tfCavalos.addFocusListener(this);
        panel.add(tfCavalos);

        panel.add(CriarObjGrafico.criarJLabel("Cilíndros", 290, 240, 120, 14));
        tfCilindros = CriarObjGrafico.criarJTextField(290, 258, 80, 20);
        tfCilindros.setEditable(false);
        tfCilindros.setBackground(Color.WHITE);
        tfCilindros.addFocusListener(this);
        panel.add(tfCilindros);

        panel.add(CriarObjGrafico.criarJLabel("Combustível", 380, 240, 120, 14));
        tfCombustivel = CriarObjGrafico.criarJTextField(380, 258, 100, 20);
        tfCombustivel.setEditable(false);
        tfCombustivel.setBackground(Color.WHITE);
        tfCombustivel.addFocusListener(this);
        panel.add(tfCombustivel);

        panel.add(CriarObjGrafico.criarJLabel("Tipo", 490, 240, 120, 14));
        tfTipo = CriarObjGrafico.criarJTextField(490, 258, 120, 20);
        tfTipo.setEditable(false);
        tfTipo.setBackground(Color.WHITE);
        tfTipo.addFocusListener(this);
        panel.add(tfTipo);

        panel.add(CriarObjGrafico.criarJLabel("Chassi", 620, 240, 120, 14));
        tfChassi = CriarObjGrafico.criarJTextField(620, 258, 105, 20);
        tfChassi.setEditable(false);
        tfChassi.setBackground(Color.WHITE);
        tfChassi.addFocusListener(this);
        panel.add(tfChassi);

        panel.add(CriarObjGrafico.criarJLabel("RENAVAM", 735, 240, 120, 14));
        tfRenavam = CriarObjGrafico.criarJTextField(735, 258, 105, 20);
        tfRenavam.setEditable(false);
        tfRenavam.setBackground(Color.WHITE);
        tfRenavam.addFocusListener(this);
        panel.add(tfRenavam);

        JSeparator separator2 = new JSeparator();
        separator2.setBounds(0, 310, 875, 1);
        separator2.setBackground(Color.BLACK);
        panel.add(separator2);

        JTable tabela = new JTable(tableModel);
        tabela.getColumnModel().getColumn(0).setMinWidth(50);
        tabela.getColumnModel().getColumn(1).setMinWidth(110);
        tabela.getColumnModel().getColumn(2).setMinWidth(120);
        tabela.getColumnModel().getColumn(3).setMinWidth(80);
        tabela.getColumnModel().getColumn(4).setMinWidth(250);
        tabela.getColumnModel().getColumn(5).setMinWidth(300);
        tabela.getColumnModel().getColumn(6).setMinWidth(110);
        tabela.getColumnModel().getColumn(7).setMinWidth(110);
        tabela.getColumnModel().getColumn(8).setMinWidth(130);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(10, 320, 855, 290);
        panel.add(scroll);

        btCancelar = CriarObjGrafico.criarJButton("Cancelar", 780, 620, 85, 24);
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        HashSet conj = new HashSet(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        setSize(900, 700);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        tfCPFCNPJ.setText("");
        tfRGIE.setText("");
        tfNomeClie.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");
        tfNumero.setText("");
        tfComplemento.setText("");
        tfCidade.setText("");
        tfUF.setText("");
        tfReferencia.setText("");
        tfCEP.setText("");
        tfFone.setText("");
        tfCelular.setText("");
        tfEmail.setText("");
        tfPlaca.setText("");
        tfNomeVei.setText("");
        tfMarca.setText("");
        tfAno.setText("");
        tfModelo.setText("");
        tfCor.setText("");
        tfPotencia.setText("");
        tfVavulas.setText("");
        tfCavalos.setText("");
        tfCilindros.setText("");
        tfCombustivel.setText("");
        tfTipo.setText("");
        tfChassi.setText("");
        tfRenavam.setText("");
        tableModel.limparLista();
        tableModel.fireTableDataChanged();
    }

    /*private void abrirPesquisaClie() throws Exception {
    ClienteControl controlClie = new ClienteControl(daoFactory, con);
    if (controlClie.isClieVazio()) {
    throw new Exception("Não há clientes cadastrados");
    }
    PesquisaClientes pcl = new PesquisaClientes(controlClie);
    pcl.setModal(true);
    pcl.setVisible(true);
    }
    
    private void abrirPesquisaVei() throws Exception {
    VeiculoControl controlVei = new VeiculoControl(daoFactory, con);
    if (controlVei.isVeiVazio()) {
    throw new Exception("Não há veículos cadastrados");
    }
    PesquisaVeiculo pve = new PesquisaVeiculo(controlVei);
    pve.setModal(true);
    pve.setVisible(true);
    }
    
    private void recuperarClie(String cpfCNPJ) throws Exception{
    ClienteControl controlClie = new ClienteControl(daoFactory, con);
    if (controlClie.isClieVazio()) {
    throw new Exception("Não há clientes cadastrados");
    }
    Cliente cliente = controlClie.getCliente(cpfCNPJ);
    if(cliente != null){
    tfCPFCNPJ.setText(cliente.getCpfCNPJ());
    if(cpfCNPJ.length() == 14){
    tfRGIE.setText(cliente.getRg());
    } else {
    tfRGIE.setText(cliente.getIe());
    }
    tfNomeClie.setText(cliente.getNome());
    tfEndereco.setText(cliente.getEndereco());
    tfBairro.setText(cliente.getBairro());
    tfNumero.setText(Integer.toString(cliente.getNumero()));
    tfComplemento.setText(cliente.getComplemento());
    tfCidade.setText(cliente.getCidade());
    tfUF.setText(cliente.getUf());
    tfReferencia.setText(cliente.getReferencia());
    tfCEP.setText(cliente.getCep());
    tfFone.setText(cliente.getFone());
    tfCelular.setText(cliente.getCelular1());
    tfEmail.setText(cliente.geteMail());
    } else {
    JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
    }
    }
    
    private void recuperarVei(String placa) throws Exception{
    VeiculoControl controlVei = new VeiculoControl(daoFactory, con);
    if (controlVei.isVeiVazio()) {
    throw new Exception("Não há veículos cadastrados");
    }
    Veiculo veiculo = controlVei.getVeiculo(placa);
    if(veiculo != null){
    tfPlaca.setText(veiculo.getPlaca());
    tfNomeVei.setText(veiculo.getNome());
    tfMarca.setText(veiculo.getMarca());
    tfAno.setText(Integer.toString(veiculo.getAno()));
    tfModelo.setText(Integer.toString(veiculo.getModelo()));
    tfCor.setText(veiculo.getCor());
    tfPotencia.setText(Double.toString(veiculo.getPotencia()));
    tfVavulas.setText(Integer.toString(veiculo.getVavulas()));
    tfCavalos.setText(Integer.toString(veiculo.getCavalos()));
    tfCilindros.setText(Integer.toString(veiculo.getCilindros()));
    tfCombustivel.setText(veiculo.getCombustivel());
    tfTipo.setText(veiculo.getTipo());
    tfChassi.setText(veiculo.getChassi());
    tfRenavam.setText(veiculo.getRenavam());
    } else {
    JOptionPane.showMessageDialog(null, "Veículo não encontrado", "Veículo", JOptionPane.INFORMATION_MESSAGE);
    }
    }
    
    private void recuperar(int flag) throws Exception {
    NovaOSControl controlNovaOS = new NovaOSControl(daoFactory, con);
    if (controlNovaOS.isOSVazio()) {
    throw new Exception("Não há ordens de serviços cadastradas");
    }
    ItemOSControl controlItensOS = new ItemOSControl(daoFactory, con);
    List<OrdemServico> listaOS;
    List<ItensOS> listaItensOS;
    listaOS = controlNovaOS.getLista();
    String s = "";
    if(flag == 0){
    for(OrdemServico ordemServico : listaOS){
    if(ordemServico.getCpfCnpjClie().equals(tfCPFCNPJ.getText())){
    s = ordemServico.getPlacaVei();
    listaItensOS = controlItensOS.getLista(ordemServico.getCodigo());
    for(ItensOS itensOS : listaItensOS){
    tableModel.add(itensOS.getCodOrdemServico(), itensOS.getUnidade(), ordemServico.getDataGeracao(), itensOS.getData(), ordemServico.getSituacao(), itensOS.getNomeItem(), itensOS.getDescricao(), itensOS.getValorUnit(), itensOS.getValorTotal());
    }
    }
    }
    recuperarClie(tfCPFCNPJ.getText());
    recuperarVei(s);
    } else {
    for(OrdemServico ordemServico : listaOS){
    if(ordemServico.getPlacaVei().equals(tfPlaca.getText())){
    s = ordemServico.getCpfCnpjClie();
    listaItensOS = controlItensOS.getLista(ordemServico.getCodigo());
    for(ItensOS itensOS : listaItensOS){
    tableModel.add(itensOS.getCodOrdemServico(), itensOS.getUnidade(), ordemServico.getDataGeracao(), itensOS.getData(), ordemServico.getSituacao(), itensOS.getNomeItem(), itensOS.getDescricao(), itensOS.getValorUnit(), itensOS.getValorTotal());
    }
    }
    }
    recuperarVei(tfPlaca.getText());
    recuperarClie(s);
    }
    tableModel.fireTableDataChanged();
    }*/
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btVerClie) {
            try {
                //abrirPesquisaClie();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btVerVei) {
            try {
                //abrirPesquisaVei();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfCPFCNPJ) {
            tfCPFCNPJ.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfRGIE) {
            tfRGIE.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeClie) {
            tfNomeClie.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfCidade) {
            tfCidade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfUF) {
            tfUF.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfReferencia) {
            tfReferencia.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCEP) {
            tfCEP.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfFone) {
            tfFone.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCelular) {
            tfCelular.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfEmail) {
            tfEmail.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPlaca) {
            tfPlaca.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNomeVei) {
            tfNomeVei.setBackground(Color.YELLOW);
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
        if (evento.getSource() == tfCombustivel) {
            tfCombustivel.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfTipo) {
            tfTipo.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfChassi) {
            tfChassi.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfRenavam) {
            tfRenavam.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        if (evento.getSource() == tfCPFCNPJ) {
            tfCPFCNPJ.setBackground(Color.WHITE);
            try {
                if (tfCPFCNPJ.getText().length() != 14 && tfCPFCNPJ.getText().length() != 18) {
                    return;
                }
                //recuperar(0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfRGIE.setBackground(Color.WHITE);
        tfNomeClie.setBackground(Color.WHITE);
        tfEndereco.setBackground(Color.WHITE);
        tfBairro.setBackground(Color.WHITE);
        tfNumero.setBackground(Color.WHITE);
        tfComplemento.setBackground(Color.WHITE);
        tfCidade.setBackground(Color.WHITE);
        tfUF.setBackground(Color.WHITE);
        tfReferencia.setBackground(Color.WHITE);
        tfCEP.setBackground(Color.WHITE);
        tfFone.setBackground(Color.WHITE);
        tfCelular.setBackground(Color.WHITE);
        tfEmail.setBackground(Color.WHITE);
        if (evento.getSource() == tfPlaca) {
            tfPlaca.setBackground(Color.WHITE);
            try {
                //recuperar(1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        tfNomeVei.setBackground(Color.WHITE);
        tfMarca.setBackground(Color.WHITE);
        tfAno.setBackground(Color.WHITE);
        tfModelo.setBackground(Color.WHITE);
        tfCor.setBackground(Color.WHITE);
        tfPotencia.setBackground(Color.WHITE);
        tfVavulas.setBackground(Color.WHITE);
        tfCavalos.setBackground(Color.WHITE);
        tfCilindros.setBackground(Color.WHITE);
        tfCombustivel.setBackground(Color.WHITE);
        tfTipo.setBackground(Color.WHITE);
        tfChassi.setBackground(Color.WHITE);
        tfRenavam.setBackground(Color.WHITE);
    }
}

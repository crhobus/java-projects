package view.chamada;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import model.Ambiente;
import model.Chamada;
import model.Defeito;
import model.Funcionario;
import model.ItemChamada;
import model.Patrimonio;

import dbOracle.AmbienteDAO;
import dbOracle.ChamadaDAO;
import dbOracle.DefeitoDAO;
import dbOracle.FuncionarioDAO;
import dbOracle.ItemChamadaDAO;
import dbOracle.PatrimonioDAO;

import view.CamposInt;
import view.PanelComponentes;
import view.ValidaObjeto;
import view.ambiente.ConsultaAmbiente;
import view.ambiente.ListenerAmbiente;
import view.defeito.ConsultaDefeito;
import view.defeito.ListenerDefeito;
import view.funcionario.ConsultaFuncionario;
import view.funcionario.ListenerFuncionario;
import view.patrimonio.ConsultaPatrimonio;
import view.patrimonio.ListenerPatrimonio;

public class NovaChamada extends JDialog implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;
    private JTextField tfNumeroChamada, tfDataRealizacao, tfSituacao,
            tfCodFuncionario, tfFuncionario, tfCodPatrimonio, tfPatrimonio,
            tfCodAmbiente, tfAmbiente, tfQtdade, tfDescricao, tfValor,
            tfNumOcorrencias, tfCodDefeito, tfDefeito;
    private JTextArea taDefeito;
    private JButton btConsulta, btConsultaFunc, btConsultaPatri, btConsultaAmb,
            btAddItem, btRemoveItem, btConfirmar, btCancelar, btConsultaDef;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatHora;
    private SimpleDateFormat formatDateHora;
    private FuncionarioDAO funcionarioDAO;
    private PatrimonioDAO patrimonioDAO;
    private AmbienteDAO ambienteDAO;
    private ChamadaDAO chamadaDAO;
    private DefeitoDAO defeitoDAO;
    private ItemChamadaDAO itemChamadaDAO;
    private TableModelItensChamada tableModel;
    private ValidaObjeto validaObjeto;

    public NovaChamada(Connection con) {
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatHora = new SimpleDateFormat("HH:mm");
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        funcionarioDAO = new FuncionarioDAO(con);
        patrimonioDAO = new PatrimonioDAO(con);
        ambienteDAO = new AmbienteDAO(con);
        defeitoDAO = new DefeitoDAO(con);
        chamadaDAO = new ChamadaDAO(con);
        itemChamadaDAO = new ItemChamadaDAO(con);
        tableModel = new TableModelItensChamada(itemChamadaDAO);
        validaObjeto = new ValidaObjeto();
        initComponents();
    }

    private void initComponents() {
        this.setTitle("Nova Chamada");
        this.setLayout(null);

        PanelComponentes panel = new PanelComponentes(5, 5, 830, 357);
        this.add(panel);

        panel.addJLabel("Número", 20, 20, 50, 14);

        tfNumeroChamada = panel.addJTextFieldNaoEdit(20, 38, 80, 20);
        try {
            tfNumeroChamada.setText(Integer.toString(chamadaDAO.getProxCodChamada()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfNumeroChamada.addFocusListener(this);

        btConsulta = panel.addJButton("...", "Consulta Chamadas", 105, 34, 31, 26);
        btConsulta.addActionListener(this);

        panel.addJLabel("Data Realização", 143, 20, 90, 14);

        tfDataRealizacao = panel.addJTextFieldNaoEdit(143, 38, 120, 20);
        tfDataRealizacao.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfDataRealizacao.addFocusListener(this);

        panel.addJLabel("Situação", 270, 20, 100, 14);

        tfSituacao = panel.addJTextFieldNaoEdit(270, 38, 140, 20);
        tfSituacao.addFocusListener(this);

        panel.addJLabel("Cod. Funcionário", 20, 63, 90, 14);

        tfCodFuncionario = panel.addJTextFieldNaoEdit(20, 81, 80, 20);
        tfCodFuncionario.addFocusListener(this);

        btConsultaFunc = panel.addJButton("...", "Consulta Funcionários", 105, 77, 31, 26);
        btConsultaFunc.addActionListener(this);

        panel.addJLabel("Funcionário", 143, 63, 60, 14);

        tfFuncionario = panel.addJTextFieldNaoEdit(143, 81, 267, 20);
        tfFuncionario.addFocusListener(this);

        panel.addJLabel("Cod. Patrimônio", 20, 105, 80, 14);

        tfCodPatrimonio = panel.addJTextFieldNaoEdit(20, 123, 80, 20);
        tfCodPatrimonio.addFocusListener(this);

        btConsultaPatri = panel.addJButton("...", "Consulta Patrimônios", 105, 119, 31, 26);
        btConsultaPatri.addActionListener(this);

        panel.addJLabel("Patrimônio", 143, 105, 60, 14);

        tfPatrimonio = panel.addJTextFieldNaoEdit(143, 123, 267, 20);
        tfPatrimonio.addFocusListener(this);

        panel.addJLabel("Cod. Ambiente", 20, 147, 80, 14);

        tfCodAmbiente = panel.addJTextFieldNaoEdit(20, 165, 80, 20);
        tfCodAmbiente.addFocusListener(this);

        btConsultaAmb = panel.addJButton("...", "Consulta Ambientes", 105, 161, 31, 26);
        btConsultaAmb.addActionListener(this);

        panel.addJLabel("Ambiente", 143, 147, 50, 14);

        tfAmbiente = panel.addJTextFieldNaoEdit(143, 165, 267, 20);
        tfAmbiente.addFocusListener(this);

        panel.addJLabel("Cod. Defeito", 20, 189, 80, 14);

        tfCodDefeito = panel.addJTextFieldNaoEdit(20, 207, 80, 20);
        tfCodDefeito.addFocusListener(this);

        btConsultaDef = panel.addJButton("...", "Consulta Defeitos", 105, 203, 31, 26);
        btConsultaDef.addActionListener(this);

        panel.addJLabel("Defeito", 143, 189, 50, 14);

        tfDefeito = panel.addJTextFieldNaoEdit(143, 207, 267, 20);
        tfDefeito.addFocusListener(this);

        panel.addJLabel("Histórico defeito", 20, 231, 80, 14);

        taDefeito = panel.addJTextArea(20, 249, 390, 100);
        taDefeito.setEditable(false);
        taDefeito.addFocusListener(this);

        panel.addJSeparator(JSeparator.VERTICAL, 430, 1, 3, 354);

        panel.addJLabel("Quantidade", 450, 20, 60, 14);

        tfQtdade = panel.addJTextField(450, 38, 80, 20);
        tfQtdade.setDocument(new CamposInt());
        tfQtdade.addFocusListener(this);

        panel.addJLabel("Descrição", 537, 20, 50, 14);

        tfDescricao = panel.addJTextField(537, 38, 200, 20);
        tfDescricao.addFocusListener(this);

        panel.addJLabel("Valor", 745, 20, 50, 14);

        tfValor = panel.addJTextField(745, 38, 70, 20);
        tfValor.addFocusListener(this);

        JTable tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getColumn(0).setMinWidth(80);
        tabela.getColumnModel().getColumn(1).setMinWidth(200);
        tabela.getColumnModel().getColumn(2).setMinWidth(110);
        tabela.getColumnModel().getColumn(3).setMinWidth(130);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(450, 70, 365, 230);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);

        panel.addJLabel("Nº Ocorrências", 450, 308, 80, 14);

        tfNumOcorrencias = panel.addJTextFieldNaoEdit(450, 326, 70, 20);
        tfNumOcorrencias.addFocusListener(this);

        btAddItem = panel.addJButton("Add Item", "Adicionar um item", 530, 322, 60, 26);
        btAddItem.setMargin(new Insets(0, 0, 0, 0));
        btAddItem.setEnabled(false);
        btAddItem.addActionListener(this);

        btRemoveItem = panel.addJButton("Remove Item", "Remover um item", 595, 322, 76, 26);
        btRemoveItem.setMargin(new Insets(0, 0, 0, 0));
        btRemoveItem.setEnabled(false);
        btRemoveItem.addActionListener(this);

        btConfirmar = panel.addJButton("Confirmar", "Confirma Operação", 677, 322, 60, 26);
        btConfirmar.setMargin(new Insets(0, 0, 0, 0));
        btConfirmar.addActionListener(this);

        btCancelar = panel.addJButton("Cancelar", "Limpar os Campos", 742, 322, 60, 26);
        btCancelar.setMargin(new Insets(0, 0, 0, 0));
        btCancelar.addActionListener(this);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        this.setSize(845, 395);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void limparTela() {
        try {
            tfNumeroChamada.setText(Integer.toString(chamadaDAO.getProxCodChamada()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
        tfDataRealizacao.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
        tfSituacao.setText("");
        tfCodFuncionario.setText("");
        tfFuncionario.setText("");
        tfCodPatrimonio.setText("");
        tfPatrimonio.setText("");
        tfCodAmbiente.setText("");
        tfAmbiente.setText("");
        tfQtdade.setText("");
        tfDescricao.setText("");
        tfValor.setText("");
        tfNumOcorrencias.setText("");
        taDefeito.setText("");
        tfCodDefeito.setText("");
        tfDefeito.setText("");
        btConsultaAmb.setEnabled(true);
        btConsultaPatri.setEnabled(true);
        btConsultaFunc.setEnabled(true);
        btAddItem.setEnabled(false);
        btConfirmar.setEnabled(true);
        btConfirmar.setText("Confirmar");
        btConfirmar.setToolTipText("Confirma Operação");
        btRemoveItem.setEnabled(false);
        btConsultaDef.setEnabled(true);
        itemChamadaDAO.limparMapa();
        tableModel.fireTableDataChanged();
    }

    private void okChamada() throws Exception {
        if ("".equals(tfSituacao.getText())) {
            if ("".equals(tfCodFuncionario.getText())) {
                throw new Exception("Selecione um funcionário");
            }
            if ("".equals(tfCodPatrimonio.getText())) {
                throw new Exception("Selecione um patrimonio");
            }
            if ("".equals(tfCodAmbiente.getText())) {
                throw new Exception("Selecione um ambiente");
            }
            if ("".equals(tfCodDefeito.getText().trim())) {
                throw new Exception("Selecione um defeito");
            }
            int numOcorrencias = Integer.parseInt(tfNumOcorrencias.getText());
            if (numOcorrencias == 3) {
                throw new Exception("Limite de ocorrencias deste patrimonio esgotado");
            }
            numOcorrencias++;
            tfNumOcorrencias.setText(Integer.toString(numOcorrencias));
            tfSituacao.setText("Em aberto");
            Chamada chamada = new Chamada();
            chamada.setNumeroCha(Integer.parseInt(tfNumeroChamada.getText()));
            chamada.setDataRealizacao(formatDateHora.parse(tfDataRealizacao.getText().replace(" as ", " ").concat(":00")));
            chamada.setSituacao(tfSituacao.getText());
            chamada.setNumOcorrencias(Integer.parseInt(tfNumOcorrencias.getText()));
            Funcionario funcionario = new Funcionario();
            funcionario.setCodigo(Integer.parseInt(tfCodFuncionario.getText()));
            chamada.setFuncionario(funcionario);
            Patrimonio patrimonio = new Patrimonio();
            patrimonio.setCodigo(Integer.parseInt(tfCodPatrimonio.getText()));
            chamada.setPatrimonio(patrimonio);
            Ambiente ambiente = new Ambiente();
            ambiente.setCodigo(Integer.parseInt(tfCodAmbiente.getText()));
            chamada.setAmbiente(ambiente);
            Defeito defeito = new Defeito();
            defeito.setCodigo(Integer.parseInt(tfCodDefeito.getText()));
            chamada.setDefeito(defeito);
            if (chamadaDAO.insertChamada(chamada)) {
                JOptionPane.showMessageDialog(null, "Nova chamada cadastrada", "Chamada", JOptionPane.INFORMATION_MESSAGE);
                btConsultaAmb.setEnabled(false);
                btConsultaPatri.setEnabled(false);
                btConsultaFunc.setEnabled(false);
                btConsultaDef.setEnabled(false);
                btAddItem.setEnabled(true);
                btConfirmar.setEnabled(false);
            }
        } else {
            if (itemChamadaDAO.isMapaVazio()) {
                throw new Exception("Cadastre no mínimo um item");
            }
            tfSituacao.setText("Encerado");
            if (chamadaDAO.updateSituacaoChamada(tfSituacao.getText(), Integer.parseInt(tfNumeroChamada.getText()))) {
                JOptionPane.showMessageDialog(null, "Chamada encerada", "Chamada", JOptionPane.INFORMATION_MESSAGE);
                btAddItem.setEnabled(false);
                btRemoveItem.setEnabled(false);
                btConfirmar.setEnabled(false);
                btConfirmar.setToolTipText("Chamada já finalizada");
            }
        }
    }

    private void addItem() throws Exception {
        if ("".equals(tfQtdade.getText())) {
            throw new Exception("Entre com a quantidade");
        }
        if ("".equals(tfDescricao.getText().trim())) {
            throw new Exception("Entre com a descrição");
        }
        if ("".equals(tfValor.getText())) {
            throw new Exception("Entre com o valor");
        }
        validaObjeto.validaDouble(tfValor, "Campo valor inválido", "Valor deve ser superior a 0", 0);
        ItemChamada item = new ItemChamada();
        item.setDescricao(tfDescricao.getText());
        item.setQtdade(Integer.parseInt(tfQtdade.getText()));
        item.setValor(Double.parseDouble(tfValor.getText()));
        item.setDataItem(new Date());
        Chamada chamada = new Chamada();
        chamada.setNumeroCha(Integer.parseInt(tfNumeroChamada.getText()));
        item.setChamada(chamada);
        if (itemChamadaDAO.insertItemChamada(item)) {
            JOptionPane.showMessageDialog(null, "Novo item cadastrado", "Chamada", JOptionPane.INFORMATION_MESSAGE);
            itemChamadaDAO.listItens(chamada.getNumeroCha());
            tableModel.fireTableDataChanged();
            btRemoveItem.setEnabled(true);
            btConfirmar.setEnabled(true);
            btConfirmar.setText("Encerar");
            btConfirmar.setToolTipText("Finalizar chamada");
            tfDescricao.setText("");
            tfValor.setText("");
            tfQtdade.setText("");
        }
    }

    private void removeItem() throws Exception {
        String result = JOptionPane.showInputDialog(null, "Entre com o número do item a ser removido:" + itemChamadaDAO.getItensChamada(Integer.parseInt(tfNumeroChamada.getText())), "Chamada", JOptionPane.OK_CANCEL_OPTION);
        if (result != null) {
            try {
                int num = Integer.parseInt(result);
                if (num >= 1 && num <= itemChamadaDAO.getQtdadeItensCadas()) {
                    if (itemChamadaDAO.deleteItem(num - 1)) {
                        JOptionPane.showMessageDialog(null, "Item excluído com sucesso", "Chamada", JOptionPane.INFORMATION_MESSAGE);
                        itemChamadaDAO.listItens(Integer.parseInt(tfNumeroChamada.getText()));
                        tableModel.fireTableDataChanged();
                        if (itemChamadaDAO.isMapaVazio()) {
                            btRemoveItem.setEnabled(false);
                        }
                    }
                } else {
                    throw new Exception("Item inválido, entre com um número do item válido");
                }
            } catch (NumberFormatException ex) {
                throw new Exception("Item inválido, entre com um número do item válido");
            }
        }
    }

    private void consultaChamada() throws Exception {
        if (chamadaDAO.isChamadaVazio()) {
            throw new Exception("Não há chamadas cadastradas\nCadastre uma chamada primeiro");
        }
        ConsultaChamada consulta = new ConsultaChamada(chamadaDAO);
        consulta.setListener(new ListenerChamada() {

            @Override
            public void exibeChamada(Chamada chamada) {
                limparTela();
                try {
                    itemChamadaDAO.listItens(chamada.getNumeroCha());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
                }
                tableModel.fireTableDataChanged();
                btConsultaAmb.setEnabled(false);
                btConsultaPatri.setEnabled(false);
                btConsultaDef.setEnabled(false);
                btConsultaFunc.setEnabled(false);
                btAddItem.setEnabled(true);
                btConfirmar.setEnabled(false);
                if (itemChamadaDAO.isMapaVazio()) {
                    btRemoveItem.setEnabled(false);
                } else {
                    btRemoveItem.setEnabled(true);
                    btConfirmar.setEnabled(true);
                    btConfirmar.setText("Encerar");
                    btConfirmar.setToolTipText("Finalizar chamada");
                }
                if ("Encerado".equals(chamada.getSituacao())) {
                    btConfirmar.setEnabled(false);
                    btAddItem.setEnabled(false);
                    btRemoveItem.setEnabled(false);
                    btConfirmar.setToolTipText("Chamada já finalizada");
                }
                tfNumeroChamada.setText(Integer.toString(chamada.getNumeroCha()));
                tfDataRealizacao.setText(formatDate.format(chamada.getDataRealizacao()) + " as " + formatHora.format(chamada.getDataRealizacao()));
                tfSituacao.setText(chamada.getSituacao());
                tfCodFuncionario.setText(Integer.toString(chamada.getFuncionario().getCodigo()));
                tfFuncionario.setText(chamada.getFuncionario().getNome());
                tfCodPatrimonio.setText(Integer.toString(chamada.getPatrimonio().getCodigo()));
                tfPatrimonio.setText(chamada.getPatrimonio().getDescricao());
                tfCodAmbiente.setText(Integer.toString(chamada.getAmbiente().getCodigo()));
                tfAmbiente.setText(chamada.getAmbiente().getAmbiente());
                tfNumOcorrencias.setText(Integer.toString(chamada.getNumOcorrencias()));
                try {
                    taDefeito.setText(chamadaDAO.getHistoricoDefeito(chamada.getPatrimonio().getCodigo()));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void consultaFunc() throws Exception {
        if (funcionarioDAO.isFuncVazio()) {
            throw new Exception("Não há funcionários cadastrados\nCadastre um funcionário primeiro");
        }
        ConsultaFuncionario consulta = new ConsultaFuncionario(funcionarioDAO);
        consulta.setListener(new ListenerFuncionario() {

            @Override
            public void exibeFuncionario(Funcionario funcionario) {
                tfCodFuncionario.setText(Integer.toString(funcionario.getCodigo()));
                tfFuncionario.setText(funcionario.getNome());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void consultaPatrimonio() throws Exception {
        if (patrimonioDAO.isPatrimonioVazio()) {
            throw new Exception("Não há patrimonios cadastrados\nCadastre um patrimonio primeiro");
        }
        ConsultaPatrimonio consulta = new ConsultaPatrimonio(patrimonioDAO);
        consulta.setListener(new ListenerPatrimonio() {

            @Override
            public void exibePatrimonio(Patrimonio patrimonio) {
                tfCodPatrimonio.setText(Integer.toString(patrimonio.getCodigo()));
                tfPatrimonio.setText(patrimonio.getDescricao());
                try {
                    tfNumOcorrencias.setText(Integer.toString(chamadaDAO.getQtdadePatrimonio(Integer.parseInt(tfCodPatrimonio.getText()))));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    taDefeito.setText(chamadaDAO.getHistoricoDefeito(patrimonio.getCodigo()));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void consultaAmbiente() throws Exception {
        if (ambienteDAO.isAmbienteVazio()) {
            throw new Exception("Não há ambientes cadastrados\nCadastre um ambiente primeiro");
        }
        ConsultaAmbiente consulta = new ConsultaAmbiente(ambienteDAO);
        consulta.setListener(new ListenerAmbiente() {

            @Override
            public void exibeAmbiente(Ambiente ambiente) {
                tfCodAmbiente.setText(Integer.toString(ambiente.getCodigo()));
                tfAmbiente.setText(ambiente.getAmbiente());
            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    private void consultaDefeito() throws Exception {
        if (defeitoDAO.isDefeitoVazio()) {
            throw new Exception("Não há defeitos cadastrados\nCadastre um defeito primeiro");
        }
        ConsultaDefeito consulta = new ConsultaDefeito(defeitoDAO);
        consulta.setListener(new ListenerDefeito() {

            @Override
            public void exibeDefeito(Defeito defeito) {
                tfCodDefeito.setText(Integer.toString(defeito.getCodigo()));
                tfDefeito.setText(defeito.getDefeito());

            }
        });
        consulta.setModal(true);
        consulta.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btConsulta) {
            try {
                consultaChamada();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaFunc) {
            try {
                consultaFunc();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaPatri) {
            try {
                consultaPatrimonio();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaAmb) {
            try {
                consultaAmbiente();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConsultaDef) {
            try {
                consultaDefeito();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btAddItem) {
            try {
                addItem();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btRemoveItem) {
            try {
                removeItem();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btConfirmar) {
            try {
                okChamada();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfNumeroChamada) {
            tfNumeroChamada.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDataRealizacao) {
            tfDataRealizacao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfSituacao) {
            tfSituacao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodFuncionario) {
            tfCodFuncionario.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfFuncionario) {
            tfFuncionario.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodPatrimonio) {
            tfCodPatrimonio.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfPatrimonio) {
            tfPatrimonio.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodAmbiente) {
            tfCodAmbiente.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfAmbiente) {
            tfAmbiente.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfQtdade) {
            tfQtdade.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDescricao) {
            tfDescricao.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfValor) {
            tfValor.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfNumOcorrencias) {
            tfNumOcorrencias.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == taDefeito) {
            taDefeito.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfCodDefeito) {
            tfCodDefeito.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == tfDefeito) {
            tfDefeito.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfNumeroChamada.setBackground(Color.WHITE);
        tfDataRealizacao.setBackground(Color.WHITE);
        tfSituacao.setBackground(Color.WHITE);
        tfCodFuncionario.setBackground(Color.WHITE);
        tfFuncionario.setBackground(Color.WHITE);
        tfCodPatrimonio.setBackground(Color.WHITE);
        tfPatrimonio.setBackground(Color.WHITE);
        tfCodAmbiente.setBackground(Color.WHITE);
        tfAmbiente.setBackground(Color.WHITE);
        tfQtdade.setBackground(Color.WHITE);
        tfDescricao.setBackground(Color.WHITE);
        tfValor.setBackground(Color.WHITE);
        tfNumOcorrencias.setBackground(Color.WHITE);
        taDefeito.setBackground(Color.WHITE);
        tfCodDefeito.setBackground(Color.WHITE);
        tfDefeito.setBackground(Color.WHITE);
    }
}

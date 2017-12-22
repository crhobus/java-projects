package Relatorios;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Formatacao.ObjGraficos;
import Funcionarios.FuncionarioControl;
import Modelo.OrdemServico;
import OrdensServicos.NovaOSControl;

public class RelatorioOS extends ObjGraficos implements ActionListener, FocusListener {

    private static final long serialVersionUID = 7204861071583210322L;
    private JTextField tfNomeFunc;
    private JComboBox cbMes, cbAno;
    private JButton btOk, btCancelar, btGerar;
    private JLabel lbFinalizados, lbValorTotal, lbHorasTrabalhadas,
            lbEmAtendimento, lbCancelados, lbRetirados;
    private TableModelRelOS tableModel;
    private NovaOSControl controleOS;
    private FuncionarioControl controleFunc;

    public RelatorioOS(NovaOSControl controleOS, FuncionarioControl controleFunc) {
        this.controleOS = controleOS;
        this.controleFunc = controleFunc;
        tableModel = new TableModelRelOS();
        initComponents();
    }

    private void initComponents() {
        List<String> itensCombo = new ArrayList<String>();
        setTitle("Relatório de Ordens de Serviços");
        setLayout(null);
        JPanel panel = getJPanelLineBorder(8, 8, 767, 606);
        add(panel);

        panel.add(getJLabel("Nome Func.", 20, 20, 95, 14));
        tfNomeFunc = getJTextField(95, 18, 230, 20);
        tfNomeFunc.addFocusListener(this);
        panel.add(tfNomeFunc);

        panel.add(getJLabel("Mês", 332, 20, 50, 14));
        itensCombo.add("Janeiro");
        itensCombo.add("Fevereiro");
        itensCombo.add("Março");
        itensCombo.add("Abril");
        itensCombo.add("Maio");
        itensCombo.add("Junho");
        itensCombo.add("Julho");
        itensCombo.add("Agosto");
        itensCombo.add("Setembro");
        itensCombo.add("Outubro");
        itensCombo.add("Novembro");
        itensCombo.add("Dezembro");
        cbMes = getJComboBox(itensCombo, 364, 18, 100, 20);
        cbMes.addFocusListener(this);
        panel.add(cbMes);

        panel.add(getJLabel("Ano", 470, 20, 50, 14));
        itensCombo.clear();
        itensCombo.add("2011");
        itensCombo.add("2012");
        itensCombo.add("2013");
        itensCombo.add("2014");
        itensCombo.add("2015");
        itensCombo.add("2016");
        itensCombo.add("2017");
        itensCombo.add("2018");
        itensCombo.add("2019");
        itensCombo.add("2020");
        itensCombo.add("2021");
        itensCombo.add("2022");
        itensCombo.add("2023");
        itensCombo.add("2024");
        itensCombo.add("2025");
        itensCombo.add("2026");
        itensCombo.add("2027");
        itensCombo.add("2028");
        itensCombo.add("2029");
        itensCombo.add("2030");
        itensCombo.add("2031");
        itensCombo.add("2032");
        itensCombo.add("2033");
        itensCombo.add("2034");
        itensCombo.add("2035");
        itensCombo.add("2036");
        itensCombo.add("2037");
        itensCombo.add("2038");
        cbAno = getJComboBox(itensCombo, 498, 18, 85, 20);
        cbAno.addFocusListener(this);
        panel.add(cbAno);

        btOk = getJButton("OK", 595, 15, 51, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("OK");
        btOk.addActionListener(this);
        panel.add(btOk);

        btCancelar = getJButton("Cancelar", 655, 15, 90, 26);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        JTable tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getColumn(0).setMinWidth(50);
        tabela.getColumnModel().getColumn(1).setMinWidth(130);
        tabela.getColumnModel().getColumn(2).setMinWidth(100);
        tabela.getColumnModel().getColumn(3).setMinWidth(125);
        tabela.getColumnModel().getColumn(4).setMinWidth(300);
        tabela.getColumnModel().getColumn(5).setMinWidth(150);
        tabela.getColumnModel().getColumn(6).setMinWidth(140);
        tabela.getColumnModel().getColumn(7).setMinWidth(120);
        tabela.getColumnModel().getColumn(8).setMinWidth(60);
        tabela.getColumnModel().getColumn(9).setMinWidth(110);
        tabela.getColumnModel().getColumn(10).setMinWidth(100);
        tabela.getColumnModel().getColumn(11).setMinWidth(100);
        tabela.getColumnModel().getColumn(12).setMinWidth(100);
        tabela.getColumnModel().getColumn(13).setMinWidth(120);
        tabela.getColumnModel().getColumn(14).setMinWidth(120);
        tabela.getColumnModel().getColumn(15).setMinWidth(120);
        tabela.getColumnModel().getColumn(16).setMinWidth(120);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 50, 725, 500);
        panel.add(scroll);

        panel.add(getJLabel("Finalizados:", 15, 570, 70, 14));
        lbFinalizados = getJLabel("0", 85, 570, 60, 14);
        panel.add(lbFinalizados);

        panel.add(getJLabel("Total:", 108, 570, 90, 14));
        lbValorTotal = getJLabel("R$ 0,00", 142, 570, 90, 14);
        panel.add(lbValorTotal);

        panel.add(getJLabel("Horas Trabalhadas:", 227, 570, 120, 14));
        lbHorasTrabalhadas = getJLabel("0", 340, 570, 80, 14);
        panel.add(lbHorasTrabalhadas);

        panel.add(getJLabel("Em Atendimento:", 381, 570, 100, 14));
        lbEmAtendimento = getJLabel("0", 480, 570, 60, 14);
        panel.add(lbEmAtendimento);

        panel.add(getJLabel("Cancelados:", 500, 570, 80, 14));
        lbCancelados = getJLabel("0", 573, 570, 60, 14);
        panel.add(lbCancelados);

        panel.add(getJLabel("Retirados:", 593, 570, 80, 14));
        lbRetirados = getJLabel("0", 655, 570, 60, 14);
        panel.add(lbRetirados);

        btGerar = getJButton("Gerar", 679, 565, 66, 26);
        btGerar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btGerar.setToolTipText("Gerar Relatório");
        btGerar.addActionListener(this);
        panel.add(btGerar);

        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

        setSize(790, 650);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void limparTela() {
        tfNomeFunc.setText("");
        cbMes.setSelectedItem("Selecione");
        cbAno.setSelectedItem("Selecione");
        lbFinalizados.setText("0");
        lbValorTotal.setText("R$ 0,00");
        lbHorasTrabalhadas.setText("0");
        lbEmAtendimento.setText("0");
        lbCancelados.setText("0");
        lbRetirados.setText("0");
        tableModel.limparLista();
        tableModel.fireTableDataChanged();
    }

    private void ok() throws Exception {
        if ("".equals(tfNomeFunc.getText().trim())) {
            throw new Exception("Campo nome do funcionário inválido");
        }
        if ("Selecione".equals(cbMes.getSelectedItem())) {
            throw new Exception("Campo mês inválido");
        }
        if ("Selecione".equals(cbAno.getSelectedItem())) {
            throw new Exception("Campo ano inválido");
        }
        if (!controleFunc.isFuncCadas(tfNomeFunc.getText())) {
            tfNomeFunc.setText("");
            tfNomeFunc.grabFocus();
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "Funcionário", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        List<OrdemServico> lista = controleOS.listaOSData(cbMes.getSelectedIndex(), Integer.parseInt((String) cbAno.getSelectedItem()), tfNomeFunc.getText());
        int finalizados = 0, emAtendimento = 0, cancelados = 0, retirados = 0;
        double valorTotal = 0, horasTrabalhadas = 0;
        for (OrdemServico ordemServico : lista) {
            if (ordemServico.getSituacao().equals("Finalizado") || ordemServico.getSituacao().equals("Finalizado - Retirado")) {
                finalizados++;
                valorTotal += ordemServico.getTotal();
                horasTrabalhadas += (double) ordemServico.getTempo() / 60;
            }
            if (ordemServico.getSituacao().equals("Em Atendimento")) {
                emAtendimento++;
            }
            if (ordemServico.getSituacao().equals("Cancelado") || ordemServico.getSituacao().equals("Cancelado - Retirado")) {
                cancelados++;
            }
            if (ordemServico.getSituacao().equals("Finalizado - Retirado") || ordemServico.getSituacao().equals("Cancelado - Retirado")) {
                retirados++;
            }
        }
        lbFinalizados.setText(Integer.toString(finalizados));
        lbValorTotal.setText(NumberFormat.getCurrencyInstance().format(valorTotal));
        lbHorasTrabalhadas.setText(NumberFormat.getNumberInstance().format(horasTrabalhadas));
        lbEmAtendimento.setText(Integer.toString(emAtendimento));
        lbCancelados.setText(Integer.toString(cancelados));
        lbRetirados.setText(Integer.toString(retirados));
        tableModel.addLista(lista);
        tableModel.fireTableDataChanged();
        if (!tableModel.isListaVazio()) {
            JOptionPane.showMessageDialog(null, "Ordens de serviço recuperadas com sucesso", "Ordem de Servico", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Ordens de serviço não encontradas", "Ordem de Servico", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCancelar) {
            limparTela();
        }
        if (evento.getSource() == btOk) {
            try {
                ok();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btGerar) {
        }
    }

    @Override
    public void focusGained(FocusEvent evento) {
        if (evento.getSource() == tfNomeFunc) {
            tfNomeFunc.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbMes) {
            cbMes.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbAno) {
            cbAno.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        tfNomeFunc.setBackground(Color.WHITE);
        cbMes.setBackground(Color.WHITE);
        cbAno.setBackground(Color.WHITE);
    }
}

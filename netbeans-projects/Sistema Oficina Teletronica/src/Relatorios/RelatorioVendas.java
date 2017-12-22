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
import java.sql.Connection;
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
import javax.swing.ListSelectionModel;

import Formatacao.ObjGraficos;
import Modelo.Venda;
import Vendas.ItensVendaControl;
import Vendas.NovaVendaControl;

public class RelatorioVendas extends ObjGraficos implements ActionListener, FocusListener {

    private static final long serialVersionUID = 3473884301983364202L;
    private JComboBox cbMes, cbAno;
    private JButton btOk, btCancelar, btGerar;
    private JLabel lbProdVendidos, lbValorTotal;
    private TableModelRelVendas tableModel;
    private NovaVendaControl controleVendas;
    private ItensVendaControl controleItensVenda;

    public RelatorioVendas(Connection con, NovaVendaControl controleVendas) {
        this.controleVendas = controleVendas;
        controleItensVenda = new ItensVendaControl(con);
        tableModel = new TableModelRelVendas();
        initComponents();
    }

    private void initComponents() {
        List<String> itensCombo = new ArrayList<String>();
        setTitle("Relatório de Vendas");
        setLayout(null);
        JPanel panel = getJPanelLineBorder(8, 8, 767, 606);
        add(panel);

        panel.add(getJLabel("Mês", 20, 20, 50, 14));
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
        cbMes = getJComboBox(itensCombo, 50, 18, 100, 20);
        cbMes.addFocusListener(this);
        panel.add(cbMes);

        panel.add(getJLabel("Ano", 160, 20, 50, 14));
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
        cbAno = getJComboBox(itensCombo, 187, 18, 85, 20);
        cbAno.addFocusListener(this);
        panel.add(cbAno);

        btOk = getJButton("OK", 285, 15, 51, 26);
        btOk.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btOk.setToolTipText("OK");
        btOk.addActionListener(this);
        panel.add(btOk);

        btCancelar = getJButton("Cancelar", 345, 15, 90, 26);
        btCancelar.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelar.setToolTipText("Cancelar");
        btCancelar.addActionListener(this);
        panel.add(btCancelar);

        JTable tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getColumn(0).setMinWidth(90);
        tabela.getColumnModel().getColumn(1).setMinWidth(130);
        tabela.getColumnModel().getColumn(2).setMinWidth(110);
        tabela.getColumnModel().getColumn(3).setMinWidth(300);
        tabela.getColumnModel().getColumn(4).setMinWidth(300);
        tabela.getColumnModel().getColumn(5).setMinWidth(120);
        tabela.getColumnModel().getColumn(6).setMinWidth(130);
        tabela.getColumnModel().getColumn(7).setMinWidth(110);
        tabela.getColumnModel().getColumn(8).setMinWidth(120);
        tabela.getColumnModel().getColumn(9).setMinWidth(110);
        tabela.getColumnModel().getColumn(10).setMinWidth(100);
        tabela.getColumnModel().getColumn(11).setMinWidth(120);
        tabela.getColumnModel().getColumn(12).setMinWidth(110);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 50, 725, 500);
        panel.add(scroll);

        panel.add(getJLabel("Produtos Vendidos:", 15, 570, 120, 14));
        lbProdVendidos = getJLabel("0", 130, 570, 60, 14);
        panel.add(lbProdVendidos);

        panel.add(getJLabel("Total:", 163, 570, 90, 14));
        lbValorTotal = getJLabel("R$ 0,00", 200, 570, 90, 14);
        panel.add(lbValorTotal);

        btGerar = getJButton("Gerar", 290, 564, 66, 26);
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
        cbMes.setSelectedItem("Selecione");
        cbAno.setSelectedItem("Selecione");
        lbProdVendidos.setText("0");
        lbValorTotal.setText("R$ 0,00");
        tableModel.limparLista();
        tableModel.fireTableDataChanged();
    }

    private void ok() throws Exception {
        if ("Selecione".equals(cbMes.getSelectedItem())) {
            throw new Exception("Campo mês inválido");
        }
        if ("Selecione".equals(cbAno.getSelectedItem())) {
            throw new Exception("Campo ano inválido");
        }
        List<Venda> lista = controleVendas.listaVendaData(cbMes.getSelectedIndex(), Integer.parseInt((String) cbAno.getSelectedItem()));
        int produtosVendidos = 0;
        double valorTotal = 0;
        for (Venda venda : lista) {
            valorTotal += venda.getTotal();
            produtosVendidos += controleItensVenda.getQtdadeItensVenda(venda.getNumeroVen());
        }
        lbProdVendidos.setText(Integer.toString(produtosVendidos));
        lbValorTotal.setText(NumberFormat.getCurrencyInstance().format(valorTotal));
        tableModel.addLista(lista);
        tableModel.fireTableDataChanged();
        if (!tableModel.isListaVazio()) {
            JOptionPane.showMessageDialog(null, "Vendas recuperadas com sucesso", "Vendas", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Vendas não encontradas", "Vendas", JOptionPane.INFORMATION_MESSAGE);
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
        if (evento.getSource() == cbMes) {
            cbMes.setBackground(Color.YELLOW);
        }
        if (evento.getSource() == cbAno) {
            cbAno.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void focusLost(FocusEvent evento) {
        cbMes.setBackground(Color.WHITE);
        cbAno.setBackground(Color.WHITE);
    }
}

package view.desktop.pedido;

import control.funcoes.Funcoes;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.entidades.ItemPedido;
import model.entidades.Pedido;
import model.entidades.Sabor;
import view.componentes.table.TableModel;

public class TableModelPedido extends TableModel {

    private List<Pedido> pedidos;
    private int index;

    public TableModelPedido(PedidoSis cadasPedido) {
        try {
            this.pedidos = cadasPedido.getServidor().getPedidoAction().getPedidos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Pedido pedido = pedidos.get(linha);
        switch (coluna) {
            case 0:
                return Funcoes.validaString(pedido.getCdPedido());
            case 1:
                return Funcoes.validaString(pedido.getCliente().getNmCliente());
            case 2:
                String sabores = "";
                for (ItemPedido item : pedido.getItens()) {
                   for (Sabor sabor : item.getSabor()) {
                        sabores += sabor.getNmSabor() + "| ";
                    }
                }
                return Funcoes.validaString(sabores);
            case 3:
               return pedido.getDsObservacao();
            default:
               return pedido.getDtCadastro();
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Código";
            case 1:
                return "Cliente";
            case 2:
                return "Sabores";
            case 3:
                return "Observações";
            default:
                return "Data do pedido";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                return Date.class;
        }
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
        index = pedidos.size() - 1;
    }

    public Pedido getPedido(int linha) {
        index = linha;
        return pedidos.get(linha);
    }

    public Pedido removerPedido(int linha) {
        index = linha;
        return pedidos.remove(linha);
    }

    public Pedido removerPedido(Pedido pedido) {
        Pedido ped;
        for (int i = 0; i < pedidos.size(); i++) {
            ped = pedidos.get(i);
            if (ped.getCdPedido() == pedido.getCdPedido()) {
                pedidos.remove(ped);
                index = i;
                return ped;
            }
        }
        return null;
    }

    public boolean possuiRegistros() {
        return !pedidos.isEmpty();
    }

    public void atualizaTabela() {
        fireTableDataChanged();
    }

    public int getLinhaSelecionada() {
        if (index > (pedidos.size() - 1)) {
            index--;
            if (index < 0) {
                index = 0;
            }
            return index;
        }
        return index;
    }
}

package view.desktop.pedido;


import control.funcoes.Funcoes;
import java.util.List;
import javax.swing.JOptionPane;
import model.entidades.Cliente;
import model.entidades.ItemPedido;
import model.entidades.Sabor;
import view.componentes.table.TableModel;

public class TableModelItemPedido extends TableModel {

    private List<ItemPedido> itens;
    private int index;

    public TableModelItemPedido(List itens) {
        this.itens = itens;
    }

    @Override
    public int getRowCount() {
        return itens.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        ItemPedido item = itens.get(linha);
        switch (coluna) {
            case 0:
                return Funcoes.validaString(item.getTamanho().getDsTamanho());
            case 1:
                String sabor = "";
                List<Sabor> sabores = item.getSabor();
                for(Sabor s: sabores){
                    sabor+=" - "+s.getNmSabor();
                    
                }        
                return Funcoes.validaString(sabor);
            default:
                return Funcoes.validaString(item.getVlPedido());
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Tamanho";
            case 1:
                return "Sabores";
            default:
                return "Valor";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            default:
                return String.class;
        }
    }

    public void addItemPedido(ItemPedido item) {
        itens.add(item);
        index = itens.size() - 1;
        atualizaTabela();
    }

    public ItemPedido getItemPedido(int linha) {
        index = linha;
        return itens.get(linha);
    }

    public ItemPedido removerItemPedido(int linha) {
        index = linha;
        return itens.remove(linha);
    }

    public ItemPedido removerItemPedido(ItemPedido itemPedido) {
        ItemPedido item;
        for (int i = 0; i < itens.size(); i++) {
            item = itens.get(i);
            if (item.getCodItem() == itemPedido.getCodItem()) {
                itens.remove(item);
                index = i;
                return item;
            }
        }
        return null;
    }

    public boolean possuiRegistros() {
        return !itens.isEmpty();
    }

    public void atualizaTabela() {
        fireTableDataChanged();
    }

    public int getLinhaSelecionada() {
        if (index > (itens.size() - 1)) {
            index--;
            if (index < 0) {
                index = 0;
            }
            return index;
        }
        return index;
    }
}

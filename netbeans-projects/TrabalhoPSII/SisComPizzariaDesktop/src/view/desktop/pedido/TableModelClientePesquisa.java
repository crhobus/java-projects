package view.desktop.pedido;

import control.funcoes.Funcoes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.entidades.Cliente;
import view.componentes.table.TableModel;

public class TableModelClientePesquisa extends TableModel {

    private List<Cliente> clientes;
    private int index;

    public TableModelClientePesquisa(Pesquisa pesquisa) {
        try {
            this.clientes = new ArrayList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Cliente cliente = clientes.get(linha);
        switch (coluna) {
            case 0:
                return Funcoes.validaString(cliente.getNmCliente());
            case 1:
                return Funcoes.validaString(cliente.getEndereco().getDsEndereco()
                        + ", " +cliente.getEndereco().getNrResidencia()
                        + " - Bairro "+cliente.getEndereco().getNmBairro());
            case 2:
                return Funcoes.validaString(cliente.getEndereco().getNmCidade());
            default:
                return Funcoes.validaString(cliente.getNrTefefone());
            
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Cliente";
            case 1:
                return "Endereço";
            case 2:
                return "Cidade";
           default:
                return "Telefone";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            default:
                return String.class;
        }
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
        index = clientes.size() - 1;
    }

    public Cliente getCliente(int linha) {
        index = linha;
        return clientes.get(linha);
    }

    public Cliente removerCliente(int linha) {
        index = linha;
        return clientes.remove(linha);
    }

    public Cliente removerCliente(Cliente cliente) {
        Cliente cli;
        for (int i = 0; i < clientes.size(); i++) {
            cli = clientes.get(i);
            if (cli.getCdCliente() == cliente.getCdCliente()) {
                clientes.remove(cli);
                index = i;
                return cli;
            }
        }
        return null;
    }

    public boolean possuiRegistros() {
        return !clientes.isEmpty();
    }

    public void atualizaTabela() {
        fireTableDataChanged();
    }

    public int getLinhaSelecionada() {
        if (index > (clientes.size() - 1)) {
            index--;
            if (index < 0) {
                index = 0;
            }
            return index;
        }
        return index;
    }
}

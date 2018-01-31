package view.desktop.produto;

import java.util.List;
import javax.swing.JOptionPane;
import model.entidades.Sabor;

import view.componentes.table.TableModel;

public class TableModelSabor extends TableModel {

    private List<Sabor> sabores;
    private int index;

    public TableModelSabor(CadasSabor saborSis) {
        try {
            this.sabores = saborSis.getServidor().getSaborAction().getSabores();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getRowCount() {
        return sabores.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Sabor sabor = sabores.get(linha);
        switch (coluna) {
            case 0:
                return sabor.getCdSabor();
            case 1:
                return sabor.getNmSabor();
            default:
                return sabor.getDsSabor();
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Código";
            case 1:
                return "Sabor";
            default:
                return "Elementos";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            default:
                return String.class;
        }
    }

    public void addSabor(Sabor sabor) {
        sabores.add(sabor);
        index = sabores.size() - 1;
    }

    public Sabor getSabor(int linha) {
        index = linha;
        return sabores.get(linha);
    }

    public Sabor removerSabor(int linha) {
        index = linha;
        return sabores.remove(linha);
    }

    public Sabor removerSabor(Sabor sabor) {
        Sabor sab;
        for (int i = 0; i < sabores.size(); i++) {
            sab = sabores.get(i);
            if (sab.getCdSabor() == sabor.getCdSabor()) {
                sabores.remove(sab);
                index = i;
                return sab;
            }
        }
        return null;
    }

    public boolean possuiRegistros() {
        return !sabores.isEmpty();
    }

    public void atualizaTabela() {
        fireTableDataChanged();
    }

    public int getLinhaSelecionada() {
        if (index > (sabores.size() - 1)) {
            index--;
            if (index < 0) {
                index = 0;
            }
            return index;
        }
        return index;
    }
}

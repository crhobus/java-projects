package view.desktop.produto;

import java.util.List;
import javax.swing.JOptionPane;
import model.entidades.Tamanho;

import view.componentes.table.TableModel;

public class TableModelTamanho extends TableModel {

    private List<Tamanho> tamanhos;
    private int index;

    public TableModelTamanho(CadasTamanho tamanhoSis) {
        try {
            this.tamanhos = tamanhoSis.getServidor().getTamanhoAction().getTamanhos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getRowCount() {
        return tamanhos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Tamanho tamanho = tamanhos.get(linha);
        switch (coluna) {
            case 0:
                return tamanho.getCdTamanho();
            case 1:
                return tamanho.getDsTamanho();
            default:
                if(tamanho.getTmAtivo() == 1){
                    return "Ativo";
                }else{
                    return "Inativo";
                }
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Código";
            case 1:
                return "Tamanho";
            default:
                return "Situação";
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

    public void addTamanho(Tamanho tamanho) {
        tamanhos.add(tamanho);
        index = tamanhos.size() - 1;
    }

    public Tamanho getTamanho(int linha) {
        index = linha;
        return tamanhos.get(linha);
    }

    public Tamanho removerTamanho(int linha) {
        index = linha;
        return tamanhos.remove(linha);
    }

    public Tamanho removerTamanho(Tamanho tamanho) {
        Tamanho tam;
        for (int i = 0; i < tamanhos.size(); i++) {
            tam = tamanhos.get(i);
            if (tam.getCdTamanho() == tamanho.getCdTamanho()) {
                tamanhos.remove(tam);
                index = i;
                return tam;
            }
        }
        return null;
    }

    public boolean possuiRegistros() {
        return !tamanhos.isEmpty();
    }

    public void atualizaTabela() {
        fireTableDataChanged();
    }

    public int getLinhaSelecionada() {
        if (index > (tamanhos.size() - 1)) {
            index--;
            if (index < 0) {
                index = 0;
            }
            return index;
        }
        return index;
    }
}

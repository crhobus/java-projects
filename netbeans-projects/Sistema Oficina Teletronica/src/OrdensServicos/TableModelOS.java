package OrdensServicos;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelOS extends AbstractTableModel {

    private static final long serialVersionUID = -7544949378587978683L;
    private NovaOSControl controle;

    public TableModelOS(NovaOSControl controle) {
        this.controle = controle;
        try {
            this.controle.limparLista();
            this.controle.listaTodos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 17;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeOSCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoTableModel(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Número OS";
            case 1:
                return "Situação";
            case 2:
                return "Data Geração";
            case 3:
                return "Cliente";
            case 4:
                return "CPF / CNPJ";
            case 5:
                return "Endereço";
            case 6:
                return "Bairro";
            case 7:
                return "Número";
            case 8:
                return "Cidade";
            case 9:
                return "UF";
            case 10:
                return "Fone";
            case 11:
                return "Celular";
            case 12:
                return "Nome Aparelho";
            case 13:
                return "Marca";
            case 14:
                return "Modelo";
            case 15:
                return "Assistência";
            default:
                return "Orçamento";
        }
    }
}

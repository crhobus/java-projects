package Usuario;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelUsuario extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private UsuarioControl controle;

    public TableModelUsuario(UsuarioControl controle) {
        this.controle = controle;
        try {
            controle.listarUsuario();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeUsuarioCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoTableModel(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Código";
            case 1:
                return "Usuário";
            case 2:
                return "Funcionário";
            case 3:
                return "Cargo";
            default:
                return "Permissão";
        }
    }
}

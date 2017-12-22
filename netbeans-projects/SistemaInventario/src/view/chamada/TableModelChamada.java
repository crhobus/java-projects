package view.chamada;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.ChamadaDAO;

public class TableModelChamada extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private ChamadaDAO chamadaDAO;

    public TableModelChamada(ChamadaDAO chamadaDAO) {
        this.chamadaDAO = chamadaDAO;
        try {
            chamadaDAO.listChamadas();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return chamadaDAO.getQtdadeChamadasCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return chamadaDAO.conteudoTableModel(linha, coluna);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Número";
            case 1:
                return "Situação";
            case 2:
                return "Funcionário";
            case 3:
                return "Patrimonio";
            case 4:
                return "Ambiente";
            default:
                return "Nº de Ocorrências";
        }
    }
}

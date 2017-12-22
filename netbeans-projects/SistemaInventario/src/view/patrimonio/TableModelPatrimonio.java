package view.patrimonio;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dbOracle.PatrimonioDAO;

public class TableModelPatrimonio extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private PatrimonioDAO patrimonioDAO;

	public TableModelPatrimonio(PatrimonioDAO patrimonioDAO) {
		this.patrimonioDAO = patrimonioDAO;
		try {
			patrimonioDAO.listPatrimonios();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		return patrimonioDAO.getQtdadePatrimoniosCadas();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		try {
			return patrimonioDAO.conteudoTableModel(linha, coluna);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	@Override
	public String getColumnName(int coluna) {
		switch (coluna) {
			case 0:
				return "Codigo";
			case 1:
				return "Patrimonio";
			case 2:
				return "Número Série";
			case 3:
				return "Ambiente";
			case 4:
				return "Valor";
			case 5:
				return "Fornecedor";
			case 6:
				return "Empresa";
			default:
				return "Situação Bem";
		}
	}
}

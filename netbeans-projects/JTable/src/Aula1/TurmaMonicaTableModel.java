package Aula1;

import javax.swing.table.AbstractTableModel;


public class TurmaMonicaTableModel extends AbstractTableModel {
	
	private String dados[][] = new String[][]{
			{"Monica", "F"},
			{"Cebolinha", "M"},
			{"Cascao", "M"}
	};
	
	private String titulos[] = new String[] {
		"Nome", "Sexo"	
	};
	
	public int getColumnCount() {
		return titulos.length;
	}

	public int getRowCount() {
		return dados.length;
	}

	public Object getValueAt(int row, int col) {
		return dados[row][col];
	}

	@Override
	public String getColumnName(int col) {
		return titulos[col];
	}
	
}












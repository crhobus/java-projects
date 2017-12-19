package Aula2;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;


public class TurmaMonicaTableModel extends AbstractTableModel {
	
	private Object dados[][] = new Object[][]{
			{"Monica", false, 80},
			{"Cebolinha", true, 9},
			{"Cascao", true, 40}
	};
	
	private String titulos[] = new String[] {
		"Nome", "Sexo Masc", "Idade", "Foto"	
	};
	
	@Override
	public Class<?> getColumnClass(int col) {
		switch (col) {
		case 0: return String.class;
		case 1: return Boolean.class;
		case 2: return Integer.class;
		default: return ImageIcon.class;
		}
	}
	
	public int getColumnCount() {
		return titulos.length;
	}

	public int getRowCount() {
		return dados.length;
	}

	public Object getValueAt(int row, int col) {
		if (col == 3) {
			ImageIcon imagem = 
				new ImageIcon(dados[row][0]+".png");
			return imagem;
		} else {
			return dados[row][col];
		}
	}

	@Override
	public String getColumnName(int col) {
		return titulos[col];
	}
	
	public void alterarNome(int posicao, String nome) {
		this.dados[posicao][0] = nome;

		// atualiza tudo
		//fireTableDataChanged();
		
		// atualiza linhas
		//fireTableRowsUpdated(posicao, posicao);
		
		// atualiza uma c�lula
		fireTableCellUpdated(posicao, 0);
		
	}
}












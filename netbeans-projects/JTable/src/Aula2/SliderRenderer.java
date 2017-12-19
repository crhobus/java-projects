package Aula2;

import java.awt.Component;

import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class SliderRenderer 
	extends JSlider 
	implements TableCellRenderer{

	public Component getTableCellRendererComponent(
			JTable tabela, Object valor,
			boolean selecionado, boolean foco,
			int linha, int coluna) {
		
		if (selecionado) {
			setBackground(tabela.
					getSelectionBackground());
		} else {
			setBackground(tabela.getBackground());
		}
		setValue((Integer)valor);
		
		return this;
	}

}

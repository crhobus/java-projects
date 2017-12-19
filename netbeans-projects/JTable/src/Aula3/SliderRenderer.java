package Aula3;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class SliderRenderer extends JSlider implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean foco, int linha, int coluna) {

        if (selecionado) {
            setBackground(tabela.getSelectionBackground());
        } else {
            setBackground(tabela.getBackground());
        }
        setValue((Integer) valor);
        return this;
    }
}

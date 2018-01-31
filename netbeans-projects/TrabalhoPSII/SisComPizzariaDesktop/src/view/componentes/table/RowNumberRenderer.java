package view.componentes.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class RowNumberRenderer extends DefaultTableCellRenderer {

    public RowNumberRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFocus, int linha, int coluna) {
        if (tabela != null) {
            JTableHeader header = tabela.getTableHeader();

            if (header != null) {
                setBackground(header.getBackground());
                setForeground(Color.RED);
            }
        }

        if (selecionado) {
            setFont(getFont().deriveFont(Font.BOLD));
        } else {
            setFont(getFont().deriveFont(Font.PLAIN));
        }

        setText((valor == null) ? "" : valor.toString());

        return this;
    }
}
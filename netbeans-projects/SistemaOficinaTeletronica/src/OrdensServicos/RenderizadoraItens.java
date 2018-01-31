package OrdensServicos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class RenderizadoraItens implements TableCellRenderer {

    private NumberFormat nRealf = NumberFormat.getCurrencyInstance();

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temfocus, int linha, int coluna) {
        JComponent jc = null;
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        JLabel label = new JLabel(valor.toString());
        label.setFont(fonte);
        label.setOpaque(true);
        jc = label;
        if (coluna == 0 || coluna == 3 || coluna == 4) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (valor.getClass() == Double.class) {
            label.setText(nRealf.format(valor).toString());
        }
        jc.setBackground(Color.WHITE);
        return jc;
    }
}

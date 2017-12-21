package JTable;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ImagemRenderer extends JPanel implements TableCellRenderer {

    private Image img;

    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFoco, int linha, int col) {
        this.img = ((ImageIcon) valor).getImage();
        return this;
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}

package Aula3;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class PainelIconeRenderer extends JPanel implements TableCellRenderer {

    private Image img;

    public Component getTableCellRendererComponent(
            JTable arg0, Object valor,
            boolean arg2, boolean arg3, int arg4, int arg5) {

        this.img = ((ImageIcon) valor).getImage();

        return this;
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(img, 0, 0, 50, 50, null);
    }
}










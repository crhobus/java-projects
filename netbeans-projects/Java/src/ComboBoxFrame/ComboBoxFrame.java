package ComboBoxFrame;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ComboBoxFrame extends JFrame {

    private JComboBox imgComboBox;
    private JLabel label;
    private String nomes[] = {"estrada.jpg", "flamengo.jpg", "imgterra.jpg", "montanhas.jpg", "natureza.jpg", "ponte.jpg"};
    private Icon icons[] = {new ImageIcon(nomes[0]),
        new ImageIcon(nomes[1]),
        new ImageIcon(nomes[2]),
        new ImageIcon(nomes[3]),
        new ImageIcon(nomes[4]),
        new ImageIcon(nomes[5]),};

    public ComboBoxFrame() {
        super("Testing JComboBox");
        setLayout(new FlowLayout());
        imgComboBox = new JComboBox(nomes);
        imgComboBox.setMaximumRowCount(5);
        imgComboBox.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent evento) {
                if (evento.getStateChange() == ItemEvent.SELECTED) {
                    label.setIcon(icons[imgComboBox.getSelectedIndex()]);
                }
            }
        });
        add(imgComboBox);
        label = new JLabel(icons[0]);
        add(label);
    }
}

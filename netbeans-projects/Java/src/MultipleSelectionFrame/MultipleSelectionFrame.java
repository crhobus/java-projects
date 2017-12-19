package MultipleSelectionFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MultipleSelectionFrame extends JFrame {

    private JList coresJList;
    private JList copiaJList;
    private JButton copiaJButton;
    private final String coresNome[] = {"Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Ligth Gray",
        "Magenta", "Orange", "Pink", "Red", "White", "Yellow"};

    public MultipleSelectionFrame() {
        super("Multiple Selection Lists");
        setLayout(new FlowLayout());
        coresJList = new JList(coresNome);
        coresJList.setVisibleRowCount(5);
        coresJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JScrollPane(coresJList));
        copiaJButton = new JButton("Copia >>>");
        copiaJButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evento) {
                copiaJList.setListData(coresJList.getSelectedValues());
            }
        });
        add(copiaJButton);
        copiaJList = new JList();
        copiaJList.setVisibleRowCount(5);
        copiaJList.setFixedCellWidth(100);
        copiaJList.setFixedCellHeight(15);
        copiaJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        add(new JScrollPane(copiaJList));
    }
}

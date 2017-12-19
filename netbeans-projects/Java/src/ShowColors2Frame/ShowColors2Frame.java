package ShowColors2Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShowColors2Frame extends JFrame {

    private JButton changeColorJButton;
    private Color color = Color.LIGHT_GRAY;
    private JPanel colorJPanel;

    public ShowColors2Frame() {
        super("Using JColorChooser");
        colorJPanel = new JPanel();
        colorJPanel.setBackground(color);
        changeColorJButton = new JButton("Change Color");
        changeColorJButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evento) {
                Color cor = JColorChooser.showDialog(ShowColors2Frame.this, "Choose a color", color);
                if (color != null) {
                    colorJPanel.setBackground(cor);
                }
            }
        });
        add(colorJPanel, BorderLayout.CENTER);
        add(changeColorJButton, BorderLayout.SOUTH);
        setSize(400, 130);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

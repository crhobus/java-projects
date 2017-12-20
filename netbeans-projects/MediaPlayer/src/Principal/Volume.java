package Principal;

import Controle.ExecutaMedia;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

public class Volume extends JDialog implements ActionListener {

    private JButton btAumenta, btDiminui;
    private ExecutaMedia executaMedia;

    public Volume(ExecutaMedia executaMedia) {
        this.executaMedia = executaMedia;
        initComponentes();
    }

    private void initComponentes() {
        setTitle("Volume");
        setLayout(null);

        btAumenta = new JButton("+");
        btAumenta.setBounds(25, 20, 50, 26);
        btAumenta.setBackground(new Color(248, 248, 248));
        add(btAumenta);
        btAumenta.addActionListener(this);

        btDiminui = new JButton("-");
        btDiminui.setBounds(100, 20, 50, 26);
        btDiminui.setBackground(new Color(248, 248, 248));
        add(btDiminui);
        btDiminui.addActionListener(this);

        setResizable(false);
        setSize(180, 100);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btAumenta) {
            executaMedia.aumentarVol();
        }
        if (evento.getSource() == btDiminui) {
            executaMedia.diminuirVol();
        }
    }
}

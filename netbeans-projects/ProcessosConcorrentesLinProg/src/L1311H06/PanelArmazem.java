package L1311H06;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Caio
 */
public class PanelArmazem extends JPanel {

    private JLabel lbInfo;
    private JLabel lbIniArm, lbFimArm;

    public PanelArmazem(JLabel lbIniArm, JLabel lbFimArm) {
        this.lbIniArm = lbIniArm;
        this.lbFimArm = lbFimArm;
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setBorder(BorderFactory.createEtchedBorder());

        lbInfo = new JLabel();
        lbInfo.setBounds(20, 12, 35, 14);
        add(lbInfo);
    }

    public void setInfo(int info) {
        lbInfo.setText(Integer.toString(info));
    }

    public void setIni(String str) {
        lbIniArm.setText(str);
    }

    public void setFim(String str) {
        lbFimArm.setText(str);
    }
}

package L1311H06;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author Caio
 */
public class PanelProdCons extends JPanel {

    public static final int PRODUTOR = 1;
    public static final int CONSUMIDOR = 2;
    private JButton btFinaliza;
    private JLabel lbTotalProdCons, lbProduto, lbTempoProdCons, lbStatus;
    private int tipoProdCons, nrLnSerie;
    private Produtor produtor;

    public PanelProdCons(int tipoProdCons, int nrLnSerie) {
        this.tipoProdCons = tipoProdCons;
        this.nrLnSerie = nrLnSerie;
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setBorder(BorderFactory.createEtchedBorder());

        JLabel lbInfo = new JLabel((tipoProdCons == PRODUTOR ? "PRODUTOR 1." + nrLnSerie : "CONSUMIDOR 2." + nrLnSerie));
        lbInfo.setBounds(50, 8, 100, 14);
        add(lbInfo);

        JSeparator separatorP = new JSeparator();
        separatorP.setBounds(1, 30, 177, 3);
        add(separatorP);

        lbTotalProdCons = new JLabel((tipoProdCons == PRODUTOR ? "Total Produzido..............:  0" : "Total Consumido.............:  0"));
        lbTotalProdCons.setBounds(10, 45, 168, 14);
        add(lbTotalProdCons);

        lbProduto = new JLabel("Produto.......................:  0");
        lbProduto.setBounds(10, 65, 168, 14);
        add(lbProduto);

        lbTempoProdCons = new JLabel((tipoProdCons == PRODUTOR ? "Tempo de Produção.........:  0" : "Tempo de Consumo.........:  0"));
        lbTempoProdCons.setBounds(10, 85, 168, 14);
        add(lbTempoProdCons);

        JSeparator separatorS = new JSeparator();
        separatorS.setBounds(1, 113, 177, 3);
        add(separatorS);

        lbStatus = new JLabel("PARADO");
        lbStatus.setBounds(57, 120, 168, 14);
        add(lbStatus);

        if (tipoProdCons == PRODUTOR) {

            JSeparator separatorT = new JSeparator();
            separatorT.setBounds(1, 140, 177, 3);
            add(separatorT);

            btFinaliza = new JButton("BOTÃO DE FINALIZAÇÃO");
            btFinaliza.setBounds(12, 151, 155, 26);
            btFinaliza.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
            btFinaliza.setToolTipText("Finaliza Operação");
            btFinaliza.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    btFinalizaActionPerformed(evt);
                }
            });
            add(btFinaliza);
        }
    }

    public void setTotalProdCons(int qtProdCons) {
        lbTotalProdCons.setText((tipoProdCons == PRODUTOR ? "Total Produzido..............:  " + qtProdCons : "Total Consumido.............:  " + qtProdCons));
    }

    public void setProduto(int produto) {
        lbProduto.setText("Produto.......................:  " + produto);
    }

    public void setTempoProdCons(int tempoProdCons) {
        lbTempoProdCons.setText((tipoProdCons == PRODUTOR ? "Tempo de Produção.........:  " + tempoProdCons : "Tempo de Consumo.........:  " + tempoProdCons));
    }

    public void setStatus(String status) {
        lbStatus.setText(status);
    }

    public void setProdutor(Produtor produtor) {
        this.produtor = produtor;
    }

    private void btFinalizaActionPerformed(ActionEvent evt) {
        produtor.terminar();
        btFinaliza.setEnabled(false);
    }
}

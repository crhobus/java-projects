package L102M2G03;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelConsumidor extends PanelPrincipal {

    private String totalCons = "Total consumido. . . . . . : ", prod = "Produto. . . . . . . . . . . . . . : ",
            temCons = "Tempo de Consumo. . : ", status = "status: ";
    private JLabel lbTotalCons, lbProd, lbTemCons, lbStatus;
    private Consumidor consumidor;
    private JPanel panelPri;
    private Font fonte;
    private int i;
    private int posicaoConsProd;

    public PanelConsumidor(JPanel panelPri, int posicaoConsProd, int i, Font fonte, Consumidor consumidor) {
        this.panelPri = panelPri;
        this.fonte = fonte;
        this.i = i;
        this.consumidor = consumidor;
        this.posicaoConsProd = posicaoConsProd;
        initComponents();
    }

    @Override
    public void initComponents() {
        this.setBackground(new Color(248, 248, 248));
        this.setLayout(null);
        this.setBounds(posicaoConsProd, 45 + (i * 200), 175, 160);
        this.setBorder(BorderFactory.createTitledBorder(""));
        panelPri.add(this);

        JLabel lbConsumidor = new JLabel("CONSUMIDOR 3." + (i + 1));
        lbConsumidor.setBounds(40, 10, 130, 14);
        lbConsumidor.setFont(fonte);
        this.add(lbConsumidor);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 248, 248));
        panel.setLayout(null);
        panel.setBounds(0, 30, 175, 90);
        panel.setBorder(BorderFactory.createTitledBorder(""));
        this.add(panel);

        lbTotalCons = new JLabel(totalCons + consumidor.getTotal());
        lbTotalCons.setBounds(20, 10, 160, 14);
        lbTotalCons.setFont(fonte);
        panel.add(lbTotalCons);

        lbProd = new JLabel(prod + consumidor.getProduto());
        lbProd.setBounds(20, 35, 160, 14);
        lbProd.setFont(fonte);
        panel.add(lbProd);

        lbTemCons = new JLabel(temCons + consumidor.getTempo());
        lbTemCons.setBounds(20, 60, 160, 14);
        lbTemCons.setFont(fonte);
        panel.add(lbTemCons);

        JPanel panelStatus = new JPanel();
        panelStatus.setBackground(new Color(248, 248, 248));
        panelStatus.setLayout(null);
        panelStatus.setBounds(0, 119, 175, 40);
        panelStatus.setBorder(BorderFactory.createTitledBorder(""));
        this.add(panelStatus);

        lbStatus = new JLabel(status + consumidor.getStatus());
        lbStatus.setBounds(30, 12, 130, 14);
        lbStatus.setFont(fonte);
        panelStatus.add(lbStatus);
    }

    @Override
    public void atualizarProcessos() {
        lbTotalCons.setText(totalCons + consumidor.getTotal());
        lbStatus.setText(status + consumidor.getStatus());
        lbProd.setText(prod + consumidor.getProduto());
        lbTemCons.setText(temCons + consumidor.getTempo());
    }
}

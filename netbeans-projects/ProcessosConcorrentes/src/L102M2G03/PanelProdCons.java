package L102M2G03;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelProdCons extends PanelPrincipal {

    private String totalConsProd = "Total cons./prod. . . . . : ", prod = "Produto. . . . . . . . . . . . . : ",
            temConsProd = "Tempo cons./prod. . . : ", status = "status: ";
    private JLabel lbTotalConsProd, lbConsProd, lbTemConsProd, lbStatus;
    private ProdutorConsumidor produtorConsumidor;
    private JPanel panelPri;
    private int i;
    private Font fonte;

    public PanelProdCons(JPanel panelPri, int i, Font fonte,
            ProdutorConsumidor produtorConsumidor) {
        this.produtorConsumidor = produtorConsumidor;
        this.panelPri = panelPri;
        this.i = i;
        this.fonte = fonte;
        initComponents();
    }

    @Override
    public void initComponents() {
        this.setBackground(new Color(248, 248, 248));
        this.setLayout(null);
        this.setBorder(BorderFactory.createTitledBorder(""));
        panelPri.add(this);

        JLabel lbConsProdutor = new JLabel("CONSUMIDOR/PROD. 2." + (i + 1));
        lbConsProdutor.setBounds(10, 10, 170, 14);
        lbConsProdutor.setFont(fonte);
        this.add(lbConsProdutor);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 248, 248));
        panel.setLayout(null);
        panel.setBounds(0, 30, 175, 90);
        panel.setBorder(BorderFactory.createTitledBorder(""));
        this.add(panel);

        lbTotalConsProd = new JLabel(totalConsProd + produtorConsumidor.getTotal());
        lbTotalConsProd.setBounds(20, 10, 160, 14);
        lbTotalConsProd.setFont(fonte);
        panel.add(lbTotalConsProd);

        lbConsProd = new JLabel(prod + produtorConsumidor.getProduto());
        lbConsProd.setBounds(20, 35, 160, 14);
        lbConsProd.setFont(fonte);
        panel.add(lbConsProd);

        lbTemConsProd = new JLabel(temConsProd + produtorConsumidor.getTempo());
        lbTemConsProd.setBounds(20, 60, 160, 14);
        lbTemConsProd.setFont(fonte);
        panel.add(lbTemConsProd);

        JPanel panelStatus = new JPanel();
        panelStatus.setBackground(new Color(248, 248, 248));
        panelStatus.setLayout(null);
        panelStatus.setBounds(0, 119, 175, 40);
        panelStatus.setBorder(BorderFactory.createTitledBorder(""));
        this.add(panelStatus);

        lbStatus = new JLabel(status + produtorConsumidor.getStatus());
        lbStatus.setBounds(30, 12, 150, 14);
        lbStatus.setFont(fonte);
        panelStatus.add(lbStatus);
    }

    @Override
    public void atualizarProcessos() {
        lbTotalConsProd.setText(totalConsProd + produtorConsumidor.getTotal());
        lbStatus.setText(status + produtorConsumidor.getStatus());
        lbConsProd.setText(prod + produtorConsumidor.getProduto());
        lbTemConsProd.setText(temConsProd + produtorConsumidor.getTempo());
    }
}

package L102M2G03;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelProdutor extends PanelPrincipal {

    private String totalProd = "Total produzido. . . . . : ", prod = "Produto. . . . . . . . . . . . : ",
            temProd = "Tempo de produção: ", status = "status: ";
    private JLabel lbTotalProd, lbProd, lbTemProd, lbStatus;
    private Produtor produtor;
    private JButton btFinalizacao;
    private JPanel panelPri;
    private Font fonte;
    private int i;

    public PanelProdutor(JPanel panelPri, Font fonte, int i, Produtor produtor) {
        this.panelPri = panelPri;
        this.fonte = fonte;
        this.i = i;
        this.produtor = produtor;
        initComponents();
    }

    @Override
    public void initComponents() {
        this.setBackground(new Color(248, 248, 248));
        this.setLayout(null);
        this.setBounds(20, 45 + (i * 200), 175, 186);
        this.setBorder(BorderFactory.createTitledBorder(""));
        panelPri.add(this);

        JLabel lbProdutor = new JLabel("PRODUTOR 1." + (i + 1));
        lbProdutor.setBounds(40, 10, 120, 14);
        lbProdutor.setFont(fonte);
        this.add(lbProdutor);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 248, 248));
        panel.setLayout(null);
        panel.setBounds(0, 30, 175, 90);
        panel.setBorder(BorderFactory.createTitledBorder(""));
        this.add(panel);

        lbTotalProd = new JLabel(totalProd + produtor.getTotal());
        lbTotalProd.setBounds(20, 10, 160, 14);
        lbTotalProd.setFont(fonte);
        panel.add(lbTotalProd);

        lbProd = new JLabel(prod + produtor.getProduto());
        lbProd.setBounds(20, 35, 160, 14);
        lbProd.setFont(fonte);
        panel.add(lbProd);

        lbTemProd = new JLabel(temProd + produtor.getTempo());
        lbTemProd.setBounds(20, 60, 160, 14);
        lbTemProd.setFont(fonte);
        panel.add(lbTemProd);

        JPanel panelStatus = new JPanel();
        panelStatus.setBackground(new Color(248, 248, 248));
        panelStatus.setLayout(null);
        panelStatus.setBounds(0, 119, 175, 40);
        panelStatus.setBorder(BorderFactory.createTitledBorder(""));
        this.add(panelStatus);

        lbStatus = new JLabel(status + produtor.getStatus());
        lbStatus.setBounds(30, 12, 130, 14);
        lbStatus.setFont(fonte);
        panelStatus.add(lbStatus);

        btFinalizacao = new JButton("BOTÃO DE FINALIZAÇÃO");
        btFinalizacao.setBounds(0, 159, 175, 26);
        this.add(btFinalizacao);
        btFinalizacao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evento) {
                produtor.terminar();
            }
        });
    }

    @Override
    public void atualizarProcessos() {
        lbTotalProd.setText(totalProd + produtor.getTotal());
        lbStatus.setText(status + produtor.getStatus());
        lbProd.setText(prod + produtor.getProduto());
        lbTemProd.setText(temProd + produtor.getTempo());
    }
}

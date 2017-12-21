package L1311H06;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Caio
 */
public class Principal extends JFrame {

    private int qtProdutores;
    private int tamArmazem;
    private int qtConsumidores;
    private Produtor[] produtores;
    private Consumidor[] consumidores;

    public Principal(int qtProdutores, int tamArmazem, int qtConsumidores) {
        super("Processos Concorrentes");
        this.qtProdutores = qtProdutores;
        this.tamArmazem = tamArmazem;
        this.qtConsumidores = qtConsumidores;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel pnInfo = new JPanel();
        pnInfo.setLayout(null);
        pnInfo.setPreferredSize(new Dimension(0, 40));
        pnInfo.setBorder(BorderFactory.createEtchedBorder());
        add(pnInfo, BorderLayout.NORTH);

        JLabel lbInfo = new JLabel("L1311H06 - Caio Renan Hobus");
        lbInfo.setBounds(10, 10, 150, 14);
        pnInfo.add(lbInfo);

        JPanel pnPrincipal = new JPanel();
        pnPrincipal.setLayout(null);
        pnPrincipal.setBorder(BorderFactory.createEtchedBorder());

        produtores = new Produtor[qtProdutores];
        consumidores = new Consumidor[qtConsumidores];

        JLabel lbArmazem = new JLabel("Armazem");
        lbArmazem.setBounds(370, 25, 75, 14);
        pnPrincipal.add(lbArmazem);

        PanelArmazem[] panelsArm = new PanelArmazem[tamArmazem];
        PanelArmazem pnArmazem;
        int tamArmY = 0;
        JLabel lbIniArm;
        JLabel lbFimArm;
        for (int i = 0; i < tamArmazem; i++) {

            lbIniArm = new JLabel();
            lbIniArm.setBounds(345, (62 + (i * 39)), 10, 14);
            pnPrincipal.add(lbIniArm);

            lbFimArm = new JLabel();
            lbFimArm.setBounds(430, (62 + (i * 39)), 10, 14);
            pnPrincipal.add(lbFimArm);

            pnArmazem = new PanelArmazem(lbIniArm, lbFimArm);
            pnArmazem.setBounds(360, (50 + (i * 39)), 65, 40);
            tamArmY = (i * 39) + 110;
            pnPrincipal.add(pnArmazem);
            panelsArm[i] = pnArmazem;

        }

        Armazem armazem = new Armazem(tamArmazem, panelsArm);

        JLabel lbLnSerieP = new JLabel("Linha de série 1");
        lbLnSerieP.setBounds(40, 25, 75, 14);
        pnPrincipal.add(lbLnSerieP);

        PanelProdCons pnProd;
        int tamProdY = 0;
        for (int i = 0; i < qtProdutores; i++) {
            pnProd = new PanelProdCons(PanelProdCons.PRODUTOR, (i + 1));
            pnProd.setBounds(40, (50 + (i * 210)), 180, 190);
            tamProdY = (i * 210) + 260;
            pnPrincipal.add(pnProd);
            produtores[i] = new Produtor((i + 1), armazem, pnProd);
            pnProd.setProdutor(produtores[i]);
        }

        JLabel lbLnSerieS = new JLabel("Linha de série 2");
        lbLnSerieS.setBounds(565, 25, 75, 14);
        pnPrincipal.add(lbLnSerieS);

        PanelProdCons pnCons;
        int tamConsY = 0;
        for (int i = 0; i < qtConsumidores; i++) {
            pnCons = new PanelProdCons(PanelProdCons.CONSUMIDOR, (i + 1));
            pnCons.setBounds(565, (50 + (i * 163)), 180, 143);
            tamConsY = (i * 163) + 213;
            pnPrincipal.add(pnCons);
            consumidores[i] = new Consumidor((i + 1), armazem, pnCons, produtores);
        }

        int tamY;
        if (tamProdY > tamConsY) {
            tamY = tamProdY;
        } else {
            tamY = tamConsY;
        }
        if (tamArmY > tamY) {
            tamY = tamArmY;
        }
        pnPrincipal.setPreferredSize(new Dimension(0, tamY));
        JScrollPane scroll = new JScrollPane(pnPrincipal);
        add(scroll, BorderLayout.CENTER);
    }

    public void ativar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 680);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Produtor[] getProdutores() {
        return produtores;
    }

    public Consumidor[] getConsumidores() {
        return consumidores;
    }
}

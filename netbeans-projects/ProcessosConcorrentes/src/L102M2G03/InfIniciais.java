package L102M2G03;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class InfIniciais extends JFrame {

    private List<Processo> listaProd;
    private List<Processo> listaProdCons[];
    private List<Processo> listaCons;

    public InfIniciais(int numProd, int numArmaProd, List<Integer> numConsProds, List<Integer> numArmaConsProds, int numCons) throws InterruptedException {
        super("Informações Iniciais");
        listaProdCons = new ArrayList[numConsProds.size()];
        listaProd = new ArrayList<Processo>();
        listaCons = new ArrayList<Processo>();
        initComponents(numProd, numArmaProd, numConsProds, numArmaConsProds, numCons);
        imprimeRelatorio();
    }

    private void imprimeRelatorio() {
        try {
            for (Processo p : listaProd) {
                p.join();
            }
            for (int i = 0; i < listaProdCons.length; i++) {
                for (Processo p : listaProdCons[i]) {
                    p.join();
                }
            }
            for (Processo p : listaCons) {
                p.join();
            }
            System.out.println("\n\nL102M2G03 - Caio Renan Hobus");
            for (Processo p : listaProd) {
                System.out.println("Produtos manuseados pelo PRODUTOR  1." + p.getIdentificador() + ": " + p.getTotal());
            }
            for (int i = 0; i < listaProdCons.length; i++) {
                for (Processo p : listaProdCons[i]) {
                    System.out.println("Produtos manuseados pelo CONSUMIDOR/PRODUTOR 2." + (p.getIdentificador() + 1) + ": " + p.getTotal());
                }
            }
            for (Processo p : listaCons) {
                System.out.println("Produtos manuseados pelo CONSUMIDOR 3." + (p.getIdentificador() + 1) + ": " + p.getTotal());
            }
        } catch (InterruptedException e) {
        }
    }

    private void initComponents(int numProd, int numArmaProd, List<Integer> numConsProds, List<Integer> numArmaConsProds, int numCons) throws InterruptedException {
        Container tela = getContentPane();
        tela.setLayout(null);
        Font fonte = new Font("Arial", Font.PLAIN, 12);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(248, 248, 248));
        panel1.setLayout(null);
        panel1.setBounds(10, 10, 1170, 650);
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        tela.add(panel1);

        JLabel lbNome = new JLabel("L102M2G03 - Caio Renan Hobus");
        lbNome.setBounds(10, 10, 190, 14);
        panel1.add(lbNome);

        JPanel panelPri = new JPanel();
        panelPri.setBackground(new Color(248, 248, 248));
        panelPri.setLayout(null);
        panelPri.setPreferredSize(new Dimension(100000, 100000));

        JScrollPane scroll = new JScrollPane(panelPri);
        scroll.setBounds(10, 35, 1145, 600);
        scroll.setBorder(BorderFactory.createTitledBorder(""));
        panel1.add(scroll);

        JLabel lblinhaProd1 = new JLabel("Linha de série 1");
        lblinhaProd1.setBounds(20, 15, 120, 14);
        lblinhaProd1.setFont(fonte);
        panelPri.add(lblinhaProd1);

        Armazem armazemProdutores = new Armazem(numArmaProd, numProd);
        PanelArmazem panelArmazem = new PanelArmazem(armazemProdutores, fonte);
        armazemProdutores.setPanel(panelArmazem);
        panelArmazem.setBackground(new Color(248, 248, 248));
        panelArmazem.setBounds(230, 80, 95, armazemProdutores.getTamanho() * 20);
        panelArmazem.setBorder(BorderFactory.createTitledBorder(""));
        panelPri.add(panelArmazem);

        for (int i = 0; i < numProd; i++) {
            Produtor produtor = new Produtor(i + 1, armazemProdutores);
            listaProd.add(produtor);
            PanelPrincipal panel = new PanelProdutor(panelPri, fonte, i, produtor);
            produtor.setPanel(panel);
        }

        JLabel lbArmazem1 = new JLabel("ARMAZEM 1");
        lbArmazem1.setBounds(230, 45, 100, 14);
        lbArmazem1.setFont(fonte);
        panelPri.add(lbArmazem1);

        Armazem armazemProdConsAnt = armazemProdutores;
        Armazem armazemProdConsProx = null;

        int posicaoConsProd = 365;

        int num = 0;
        for (int y = 0; y < numConsProds.size(); y++) {
            JLabel lblinhaProd2 = new JLabel("Linha de série " + (y + 2));
            lblinhaProd2.setBounds(posicaoConsProd, 15, 120, 14);
            lblinhaProd2.setFont(fonte);
            panelPri.add(lblinhaProd2);

            JLabel lbArmazem2 = new JLabel("ARMAZEM 2");
            posicaoConsProd += 230;
            lbArmazem2.setBounds(posicaoConsProd - 10, 45, 100, 14);
            lbArmazem2.setFont(fonte);
            panelPri.add(lbArmazem2);

            armazemProdConsProx = new Armazem(numArmaConsProds.get(y), numProd);
            PanelArmazem armazemPanelProdCons = new PanelArmazem(armazemProdConsProx, fonte);
            armazemProdConsProx.setPanel(armazemPanelProdCons);
            armazemPanelProdCons.setBounds(posicaoConsProd - 10, 80, 95, armazemProdConsProx.getTamanho() * 20);
            armazemPanelProdCons.setBackground(new Color(248, 248, 248));
            armazemPanelProdCons.setBorder(BorderFactory.createTitledBorder(""));
            panelPri.add(armazemPanelProdCons);

            listaProdCons[y] = new ArrayList<Processo>();
            for (int i = 0; i < numConsProds.get(y); i++) {
                ProdutorConsumidor prodCons = new ProdutorConsumidor(i, armazemProdConsAnt, armazemProdConsProx);
                listaProdCons[y].add(prodCons);
                PanelProdCons panelProdCons = new PanelProdCons(panelPri, i, fonte, prodCons);
                panelProdCons.setBounds(posicaoConsProd - 230, 45 + (i * 174), 175, 160);
                prodCons.setPanel(panelProdCons);
            }
            armazemProdConsAnt = armazemProdConsProx;
            posicaoConsProd += 130;
            num = y;
        }

        if (num == 0) {
            num += 2;
        } else {
            num += 3;
        }

        JLabel lblinhaProd3 = new JLabel("Linha de série " + num);
        lblinhaProd3.setBounds(posicaoConsProd, 15, 120, 14);
        lblinhaProd3.setFont(fonte);
        panelPri.add(lblinhaProd3);

        for (int i = 0; i < numCons; i++) {
            Consumidor consumidor = new Consumidor(i, armazemProdConsAnt);
            listaCons.add(consumidor);
            PanelConsumidor panelConsumidor = new PanelConsumidor(panelPri, posicaoConsProd, i, fonte, consumidor);
            consumidor.setPanel(panelConsumidor);
        }

        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}

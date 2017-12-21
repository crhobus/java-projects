package L1311H06;

import java.util.Random;

/**
 *
 * @author Caio
 */
public class Produtor extends Processo {

    private Random random = new Random();
    private PanelProdCons panelProdCons;
    private int qtProduzido;
    private boolean flagProdutorFinalizado;

    public Produtor(int identificador, Armazem armazem, PanelProdCons panelProdCons) {
        super(identificador, armazem);
        this.panelProdCons = panelProdCons;
    }

    private int produz() {
        return random.nextInt(151) + 50;
    }

    @Override
    public void run() {
        int produto;
        qtProduzido = 0;
        flagProdutorFinalizado = false;
        while (!isFlagTerminar()) {
            try {
                produto = produz();
                panelProdCons.setStatus("PRODUZINDO");
                panelProdCons.setProduto(produto);
                for (int i = 1; i <= produto; i++) {
                    panelProdCons.setTempoProdCons(i);
                    Thread.sleep(100);
                }
                qtProduzido++;
                panelProdCons.setTotalProdCons(qtProduzido);
                panelProdCons.setStatus("PARADO");
                getArmazem().coloca(produto);
            } catch (InterruptedException ex) {}
        }
        panelProdCons.setStatus("FINALIZADO");
        flagProdutorFinalizado = true;
    }

    public int getQtProduzido() {
        return qtProduzido;
    }

    public boolean isFlagProdutorFinalizado() {
        return flagProdutorFinalizado;
    }
}

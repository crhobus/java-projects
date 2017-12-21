package L1311H06;

/**
 *
 * @author Caio
 */
public class Consumidor extends Processo {

    private PanelProdCons panelProdCons;
    private int qtConsumido;
    private Produtor[] produtores;

    public Consumidor(int identificador, Armazem armazem, PanelProdCons panelProdCons, Produtor[] produtores) {
        super(identificador, armazem);
        this.panelProdCons = panelProdCons;
        this.produtores = produtores;
    }

    @Override
    public void run() {
        int produto;
        qtConsumido = 0;
        while (!isFlagTerminar()) {
            try {
                panelProdCons.setStatus("PARADO");
                produto = getArmazem().retira();
                if (produto != -1) {
                    panelProdCons.setStatus("CONSUMINDO");
                    panelProdCons.setProduto(produto);
                    for (int i = 1; i <= produto; i++) {
                        panelProdCons.setTempoProdCons(i);
                        Thread.sleep(100);
                    }
                    qtConsumido++;
                    panelProdCons.setTotalProdCons(qtConsumido);
                } else {
                    boolean flagFinalizar = true;
                    for (Produtor produtor : produtores) {
                        if (!produtor.isFlagProdutorFinalizado()) {
                            flagFinalizar = false;
                        }
                    }
                    if (flagFinalizar) {
                        terminar();
                    }
                }
            } catch (InterruptedException ex) {}
        }
        panelProdCons.setStatus("FINALIZADO");
    }

    public int getQtConsumido() {
        return qtConsumido;
    }
}

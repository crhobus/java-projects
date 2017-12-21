package L102M2G03;

public class ProdutorConsumidor extends Processo {

    public ProdutorConsumidor(int id, Armazem armazemProdutorConsumidorAnterior, Armazem armazemProdutorConsumidorProximo) throws InterruptedException {
        super(id, armazemProdutorConsumidorAnterior, armazemProdutorConsumidorProximo);
        setStatus("PARADO");
    }

    public void run() {
        while (!flagTerminar) {
            try {
                int prod = armazemRetirar.retira();
                if (produto != -1) {
                    setStatus("CONS/PRODUZ");
                    setProduto(prod);
                    atualizarPanel();
                    for (int i = 0; i < prod; i++) {
                        setTempo(i + 1);
                        Thread.sleep(150);
                        atualizarPanel();
                    }
                    setStatus("PARADO");
                    atualizarPanel();
                    armazemColocar.coloca(prod);
                    setTotal(getTotal() + 1);
                    atualizarPanel();
                } else {
                    terminar();
                }
            } catch (InterruptedException e) {
            }
        }
        setStatus("FINALIZADO");
        atualizarPanel();
    }

    private void atualizarPanel() {
        if (panelPrincipal != null) {
            panelPrincipal.atualizarProcessos();
        }
    }
}

package L102M2G03;

public class Consumidor extends Processo {

    public Consumidor(int id, Armazem armazem) throws InterruptedException {
        super(id, armazem, null);
        setStatus("PARADO");
    }

    public void run() {
        while (!flagTerminar) {
            try {
                setStatus("PARADO");
                atualizarPanel();
                int prod = armazemRetirar.retira();
                if (prod != -1) {
                    setStatus("CONSUMINDO");
                    setProduto(prod);
                    atualizarPanel();
                    for (int i = 0; i < prod; i++) {
                        setTempo(i + 1);
                        Thread.sleep(150);
                        atualizarPanel();
                    }
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

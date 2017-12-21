package L102M2G03;

public class Produtor extends Processo {

    public Produtor(int id, Armazem armazem) throws InterruptedException {
        super(id, null, armazem);
        setStatus("PRODUZINDO");
    }

    private int produz() {
        int num = 0;
        while (num < 50 || num > 200) {
            num = random.nextInt(201);
        }
        return num;
    }

    public void run() {
        while (!flagTerminar) {
            try {
                int prod = produz();
                setStatus("PRODUZINDO");
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

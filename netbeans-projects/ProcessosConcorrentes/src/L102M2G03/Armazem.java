package L102M2G03;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Armazem {

    private int armazem[];
    private int iniFila;
    private int fimFila;
    private final int TAMANHO;
    private int qtdProdutores;
    private PanelArmazem panel;
    private Semaphore exclusivo;
    private Semaphore naoCheio;
    private Semaphore naoVazio;

    public Armazem(int tam, int qtdProdutores) {
        this.TAMANHO = tam;
        this.qtdProdutores = qtdProdutores;
        this.armazem = new int[TAMANHO];
        this.iniFila = fimFila = 0;

        this.exclusivo = new Semaphore(1);
        this.naoCheio = new Semaphore(TAMANHO);
        this.naoVazio = new Semaphore(0);
    }

    public void coloca(int produto) throws InterruptedException {
        naoCheio.acquire();
        exclusivo.acquire();
        armazem[fimFila] = produto;
        fimFila = (fimFila + 1) % TAMANHO;
        exclusivo.release();
        naoVazio.release();
        atualizaPanel();
    }

    public int retira() throws InterruptedException {
        if (naoVazio.tryAcquire(200 * 200 * qtdProdutores, TimeUnit.MILLISECONDS)) {
            exclusivo.acquire();
            int produtoTemporario = armazem[iniFila];
            armazem[iniFila] = 0;
            iniFila = (iniFila + 1) % TAMANHO;
            exclusivo.release();
            naoCheio.release();
            atualizaPanel();
            return produtoTemporario;
        }
        atualizaPanel();
        return -1;
    }

    private void atualizaPanel() {
        if (panel != null) {
            panel.atualizar(armazem, iniFila, fimFila);
        }
    }

    public int getTamanho() {
        return TAMANHO;
    }

    public void setPanel(PanelArmazem armazemPanel) {
        this.panel = armazemPanel;
    }
}

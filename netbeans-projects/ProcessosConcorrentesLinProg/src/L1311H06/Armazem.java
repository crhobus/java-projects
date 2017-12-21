package L1311H06;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Caio
 */
public class Armazem {

    private int[] armazem;
    private int ini;
    private int fim;
    private int tamanho;
    private Semaphore exclusivo;
    private Semaphore naoCheio;
    private Semaphore naoVazio;
    private PanelArmazem[] panelsArm;

    public Armazem(int tamanho, PanelArmazem[] panelsArm) {
        this.tamanho = tamanho;
        this.panelsArm = panelsArm;
        this.ini = 0;
        this.fim = 0;
        this.armazem = new int[tamanho];
        this.exclusivo = new Semaphore(1);
        this.naoCheio = new Semaphore(tamanho);
        this.naoVazio = new Semaphore(0);
        atualizarArmazem();
    }

    public void coloca(int produto) throws InterruptedException {
        naoCheio.acquire();
        exclusivo.acquire();
        armazem[fim] = produto;
        fim = (fim + 1) % tamanho;
        atualizarArmazem();
        exclusivo.release();
        naoVazio.release();
    }

    public int retira() throws InterruptedException {
        if (naoVazio.tryAcquire(250, TimeUnit.MILLISECONDS)) {
            exclusivo.acquire();
            int produto = armazem[ini];
            ini = (ini + 1) % tamanho;
            atualizarArmazem();
            exclusivo.release();
            naoCheio.release();
            return produto;
        }
        return -1;
    }

    private void atualizarArmazem() {
        for (int i = 0; i < panelsArm.length; i++) {
            if (i == ini) {
                panelsArm[i].setIni(">");
            } else {
                panelsArm[i].setIni("");
            }
            if (i == fim) {
                panelsArm[i].setFim("<");
            } else {
                panelsArm[i].setFim("");
            }
            panelsArm[i].setInfo(armazem[i]);
        }
    }
}

package ProdConsumidor;

import Armazem.ArmazemPrincipal;

import com.primalworld.math.MathEvaluator;

public class BufferVariaveisExpressao {

    private Double valor = null;
    private String chave = null;
    private MathEvaluator resolveLinha;
    private int n;

    public BufferVariaveisExpressao(MathEvaluator resolveLinha, String[] variaveis) {
        this.resolveLinha = resolveLinha;
        n = variaveis.length;
    }

    public synchronized void set(String chave, ArmazemPrincipal armazemProd) {
        try {
            while (armazemProd.leitura(chave) == null) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
            this.chave = chave;
            this.valor = armazemProd.leitura(chave);
        } finally {
            notifyAll();
        }
    }

    public synchronized int addVariavel() {
        try {
            while (valor == null && chave == null) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
            resolveLinha.addVariable(chave, valor);
            valor = null;
            chave = null;
            n--;
            return n;
        } finally {
            notifyAll();
        }
    }
}

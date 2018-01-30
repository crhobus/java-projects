package LogTela;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VariaveisOrdTabela {

    private Lock lock = new ReentrantLock();
    private Condition podeOrdenar = lock.newCondition();
    private Condition podeAdicionar = lock.newCondition();
    private List<Expressao> listaOrdenacao = new ArrayList<Expressao>();
    private boolean esperaOrdenar, esperaAdicionar;

    public void insere(String variavel, String expressao, double valor) {
        lock.lock();
        try {
            while (esperaAdicionar) {
                try {
                    podeAdicionar.await();
                } catch (InterruptedException e) {
                }
            }
            esperaOrdenar = true;
            esperaAdicionar = true;
            Expressao lista = new Expressao();
            lista.setVariavel(variavel);
            lista.setExpressao(expressao);
            lista.setValor(valor);
            listaOrdenacao.add(lista);
            esperaOrdenar = false;
            podeOrdenar.signal();
        } finally {
            lock.unlock();
        }
    }

    public void ordena() {
        lock.lock();
        try {
            while (esperaOrdenar) {
                try {
                    podeOrdenar.await();
                } catch (InterruptedException ex) {
                }
            }
            esperaAdicionar = true;
            esperaOrdenar = true;
            Collections.sort(listaOrdenacao, new ComparadorVariaveis());
            esperaOrdenar = false;
            esperaAdicionar = false;
            podeAdicionar.signal();
        } finally {
            lock.unlock();
        }
    }

    public void cancelar() {
        listaOrdenacao = new ArrayList<Expressao>();
        esperaOrdenar = false;
        esperaAdicionar = false;
    }

    public int getTamanhoLista() {
        return listaOrdenacao.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Expressao expressao = listaOrdenacao.get(linha);
        switch (coluna) {
            case 0:
                return expressao.getVariavel();
            case 1:
                return expressao.getExpressao();
            default:
                return expressao.getValor();
        }
    }
}

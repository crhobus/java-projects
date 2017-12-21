package Prova1;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private List<Movimentacao> listaMov = new ArrayList<Movimentacao>();
    private List<ContaCorrente> listaConCor = new ArrayList<ContaCorrente>();

    public void novaConnta(ContaCorrente contaCorrente) {
        listaConCor.add(contaCorrente);
    }

    public void debitar(ContaCorrente conta, float valor) {
        boolean encontrado = false;
        for (ContaCorrente contaCorrente : listaConCor) {
            if (contaCorrente.equals(conta)) {
                if (contaCorrente.getSaldo() >= valor) {
                    float saldo = contaCorrente.getSaldo();
                    float novoValor = saldo - valor;
                    conta.setSaldo(novoValor);
                    encontrado = true;
                }
            }
        }
        if (encontrado) {
            listaConCor.add(conta);
            notifica(conta, valor);
        }
    }

    public void creditar(ContaCorrente conta, float valor) {
        boolean encontrado = false;
        for (ContaCorrente contaCorrente : listaConCor) {
            if (contaCorrente.equals(conta)) {
                float saldo = contaCorrente.getSaldo();
                float novoValor = saldo + valor;
                conta.setSaldo(novoValor);
                encontrado = true;
            }
        }
        if (encontrado) {
            listaConCor.add(conta);
            notifica(conta, valor);
        }
    }

    public void notifica(ContaCorrente contaCorrente, float valor) {
        for (Movimentacao lista : listaMov) {
            lista.transacaoEfetivada(contaCorrente, valor);
        }
    }

    public void addMovimentacao(Movimentacao movimentacao) {
        listaMov.add(movimentacao);
    }

    public void removeMovimentacao(Movimentacao movimentacao) {
        listaMov.remove(movimentacao);
    }
}

package Banco;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Banco {

    private Map<String, ContaCorrente> contaCorrentes = new HashMap<String, ContaCorrente>();
    private Map<String, Poupanca> contaPoupancas = new HashMap<String, Poupanca>();

    public ContaCorrente novaContaCorrente(String prop, String numero) throws Exception {
        if (prop != null || "".equals(prop)) {
            if (numero != null || "".equals(numero)) {
                try {
                    Integer.parseInt(numero);
                } catch (NumberFormatException ex) {
                    throw new Exception("Entre com um número válido");
                }
                if (Integer.parseInt(numero) < 1) {
                    throw new Exception("Número deve ser maior que 1");
                }
                if (contaCorrentes.containsKey(numero)) {
                    throw new Exception("Conta com esse número ja esta cadastrado");
                }
                ContaCorrente contaCorrente = new ContaCorrente(prop, numero);
                contaCorrentes.put(numero, contaCorrente);
                return contaCorrente;
            }
            throw new Exception("Entre com um número válido");
        }
        throw new Exception("Entre com um nome válido");
    }

    public Poupanca novaContaPoupanca(String prop, String numero, int variacao) throws Exception {
        if (prop != null || "".equals(prop)) {
            if (numero != null || "".equals(numero)) {
                try {
                    Integer.parseInt(numero);
                } catch (NumberFormatException ex) {
                    throw new Exception("Entre com um número válido");
                }
                if (Integer.parseInt(numero) < 1) {
                    throw new Exception("Número deve ser maior que 1");
                }
                if (contaPoupancas.containsKey(numero)) {
                    throw new Exception("Conta com esse número ja esta cadastrado");
                }
                Poupanca contaPoupanca = new Poupanca(prop, numero, variacao);
                contaPoupancas.put(numero, contaPoupanca);
                return contaPoupanca;
            }
            throw new Exception("Entre com um número válido");
        }
        throw new Exception("Entre com um nome válido");
    }

    public double getValorTotalSaldo() {
        double saldo = 0;
        Collection<ContaCorrente> contaCorrente = contaCorrentes.values();
        for (ContaCorrente conta : contaCorrente) {
            saldo += conta.saldo;
        }
        Collection<Poupanca> contaPoupanca = contaPoupancas.values();
        for (Poupanca conta : contaPoupanca) {
            saldo += conta.saldo;
        }
        return saldo;
    }

    public ContaCorrente getContaCorrente(String numero) throws Exception {
        try {
            Integer.parseInt(numero);
        } catch (NumberFormatException ex) {
            throw new Exception("Entre com um número válido");
        }
        if (Integer.parseInt(numero) < 1) {
            throw new Exception("Número deve ser maior que 1");
        }
        if (contaCorrentes.containsKey(numero)) {
            return contaCorrentes.get(numero);
        }
        throw new Exception("Conta não encontrada");
    }

    public Poupanca getPoupanca(String numero, int variacao) throws Exception {
        try {
            Integer.parseInt(numero);
        } catch (NumberFormatException ex) {
            throw new Exception("Entre com um número válido");
        }
        if (Integer.parseInt(numero) < 1) {
            throw new Exception("Número deve ser maior que 1");
        }
        if (contaPoupancas.containsKey(numero)) {
            return contaPoupancas.get(numero);
        }
        throw new Exception("Conta não encontrada");
    }
}

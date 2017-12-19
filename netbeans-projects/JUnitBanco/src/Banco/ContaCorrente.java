package Banco;

public class ContaCorrente extends Conta {

    ContaCorrente(String prop, String numero) {
        setProprietario(prop);
        setNumero(numero);
    }

    @Override
    public double sacar(double valor) throws Exception {
        return super.sacar(valor + 0.30);
    }
}

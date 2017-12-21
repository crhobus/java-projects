package Prova1;

public class ContaCorrente {

    private String agencia, conta, numeroSMS;
    private float saldo;

    public ContaCorrente(String agencia, String conta, String numeroSMS, float saldo) {
        this.agencia = agencia;
        this.conta = conta;
        this.numeroSMS = numeroSMS;
        this.saldo = saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getNumeroSMS() {
        return numeroSMS;
    }

    public void setNumeroSMS(String numeroSMS) {
        this.numeroSMS = numeroSMS;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}

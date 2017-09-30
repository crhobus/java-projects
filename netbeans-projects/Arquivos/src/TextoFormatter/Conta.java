package TextoFormatter;

public class Conta {

    private int conta;
    private String nome;
    private String sobrenome;
    private double saldo;

    public Conta() {
        this(0, "", "", 0.0);
    }

    public Conta(int conta, String nome, String sobrenome, double saldo) {
        setConta(conta);
        setNome(nome);
        setSobrenome(sobrenome);
        setSaldo(saldo);
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}

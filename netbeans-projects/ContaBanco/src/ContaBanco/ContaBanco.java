package ContaBanco;


public class ContaBanco {

    private String nomeCliente;
    private int numeroConta;
    private int tipoConta;
    private float saldo;
    private float limite;

    public ContaBanco(String quem, int num, int tp, float quanto, float lmt) {
        nomeCliente = quem;
        numeroConta = num;
        tipoConta = tp;
        saldo = quanto;
        if (tp == 2) {
            limite = lmt;
        } else {
            limite = 0;
        }
    }

    public String getNome() {
        return nomeCliente;
    }

    public int getNumero() {
        return numeroConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public boolean saque(float valor) {
        float maximo = saldo;
        if (tipoConta == 2) {
            maximo += limite;
        }
        if (valor > maximo) {
            return false;
        }
        saldo -= valor;
        return true;
    }

    public void mostrar() {
        System.out.println("Valor Atual da Conta: " + saldo);
    }

    public float deposito(float colocaValor) {
        saldo = saldo + colocaValor;
        return colocaValor;
    }

    public float setAlteraLimite(float numero) {
        limite = numero;
        return limite;
    }

    public int setAlteraTipo(int numero) {
        tipoConta = numero;
        return tipoConta;
    }
}

package ContaBanco;


public class Sistema {

    public static void main(String[] args) {
        ContaBanco meu = new ContaBanco("Caio", 200, 90, 30000, 5000);
        System.out.println("Nome Cliente: " + meu.getNome());
        System.out.println("Numero Conta: " + meu.getNumero());
        System.out.println("Saldo Conta: " + meu.getSaldo());
        System.out.println("Saque: " + meu.saque(9000));
        meu.mostrar();
        System.out.println("Deposito: " + meu.deposito(99));
        meu.mostrar();
        System.out.println("Novo Limite: " + meu.setAlteraLimite(80000));
        System.out.println("Novo Tipo: " + meu.setAlteraTipo(99));
        meu.mostrar();
    }
}

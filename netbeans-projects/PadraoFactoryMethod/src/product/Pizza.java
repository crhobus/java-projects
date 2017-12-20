package product;

public abstract class Pizza {

    public abstract String getNome();

    public void preparar() {
        System.out.println("Preparando......");
        System.out.println("\tFazendo a massa ");
        System.out.println("\tPegando os ingredientes");

    }

    public void assar() {
        System.out.println("Assando por 25min.");
    }

    public void cortar() {
        System.out.println("Cortando em  fatias.");
    }

    public void embalar() {
        System.out.println("Colocar a pizza na caixa.");
    }
}

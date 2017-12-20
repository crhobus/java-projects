package Restaurante;

public class MenuItem extends MenuComponente {

    private String nome, descricao;
    private boolean vegetariano;
    private double preco;

    public MenuItem(String nome, String descricao, boolean vegetariano, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.vegetariano = vegetariano;
        this.preco = preco;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public boolean isVegetariano() {
        return vegetariano;
    }

    @Override
    public void imprimir() {
        System.out.println(" " + getNome() + " ");
        if (isVegetariano()) {
            System.out.print("(v)");
        }
        System.out.print(" " + getPreco() + ", -- " + getDescricao() + "\n\n");
    }
}

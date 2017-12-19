package Exe3;

public class ItemPedido {

    private int numItem;
    private String item;
    private double valor;

    public ItemPedido(int numItem, String item, double valor) {
        this.numItem = numItem;
        this.item = item;
        this.valor = valor;
    }

    public int getNumItem() {
        return numItem;
    }

    public String getItem() {
        return item;
    }

    public double getValor() {
        return valor;
    }
}

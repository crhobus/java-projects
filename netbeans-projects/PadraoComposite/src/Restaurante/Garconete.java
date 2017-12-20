package Restaurante;

public class Garconete {

    private MenuComponente allMenus;

    public Garconete(MenuComponente allMenus) {
        this.allMenus = allMenus;
    }

    public void imprimirMenu() {
        allMenus.imprimir();
    }
}

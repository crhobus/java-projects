package Restaurante;

public class Sistema {

    public static void main(String[] args) {
        MenuPanquecaCasa menuPanquecaCasa = new MenuPanquecaCasa();
        MenuJantar menuJantar = new MenuJantar();
        MenuCafe menuCafe = new MenuCafe();
        Garconete garconete = new Garconete();

        garconete.setMenu(menuPanquecaCasa);
        garconete.setMenu(menuJantar);
        garconete.setMenu(menuCafe);
        garconete.imprimirMenu();
    }
}

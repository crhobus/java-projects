package Restaurante;

public class Sistema {

    public static void main(String[] args) {
        MenuComponente cafeManhaMenu = new Menu("Café da manhã menu", "Café da Manhã");
        MenuComponente panquecaCasaMenu = new Menu("Panqueca da casa menu", "Almoço");
        MenuComponente cafeTardeMenu = new Menu("Café da tarde menu", "Café da tarde");
        MenuComponente jantarMenu = new Menu("Jantar menu", "Jantar");

        MenuComponente todosMenus = new Menu("Todos menus", "Todos menus combinados");

        todosMenus.add(cafeManhaMenu);
        todosMenus.add(panquecaCasaMenu);
        todosMenus.add(cafeTardeMenu);
        todosMenus.add(jantarMenu);

        cafeManhaMenu.add(new MenuItem("Café da Casa com frutas", "Café com leite, uva, banana, maça", true, 3.00));
        cafeManhaMenu.add(new MenuItem("Café da Casa com Misto Quente", "Café co leite, tomate, apresuntado, mussarela, maionese", true, 4.00));

        panquecaCasaMenu.add(new MenuItem("Panqueca da casa", "Panquecas com ovos fritos e salsichas", false, 2.99));
        panquecaCasaMenu.add(new MenuItem("Panqueca de Frango", "Panquecas feita de frango com salsichas", false, 4.12));

        jantarMenu.add(new MenuItem("Sopa do dia", "Sopa do dia, com um lado de salada de batata", false, 4.50));
        jantarMenu.add(new MenuItem("Cachorro-quente", "Um cachorro-quente, com batata palha, cebola, coberto com queijo", false, 5.50));

        Garconete garconete = new Garconete(todosMenus);
        garconete.imprimirMenu();
    }
}

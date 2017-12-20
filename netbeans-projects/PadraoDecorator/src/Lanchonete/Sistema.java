package Lanchonete;

public class Sistema {

    public static void main(String[] args) {
        Lanchonete xSalada = new XSalada();
        xSalada = new Hamburguer(xSalada);
        xSalada = new Tomate(xSalada);
        xSalada = new Alface(xSalada);
        xSalada = new Pepino(xSalada);
        xSalada = new Mussarela(xSalada);
        xSalada = new Apresuntado(xSalada);
        xSalada = new Milho(xSalada);
        xSalada = new Ervilha(xSalada);
        xSalada = new BatataPalha(xSalada);
        System.out.printf("%s\nCusto: R$ %,.2f\n\n", xSalada.getDescricao(), xSalada.custo());

        Lanchonete xFrango = new XSalada();
        xFrango = new CarneFrango(xFrango);
        xFrango = new Tomate(xFrango);
        xFrango = new Alface(xFrango);
        xFrango = new Pepino(xFrango);
        xFrango = new Mussarela(xFrango);
        xFrango = new Apresuntado(xFrango);
        xFrango = new Milho(xFrango);
        xFrango = new Ervilha(xFrango);
        xFrango = new Palmito(xFrango);
        System.out.printf("%s\nCusto: R$ %,.2f\n\n", xFrango.getDescricao(), xFrango.custo());

        Lanchonete misturaCasa = new MisturaCasa();
        misturaCasa = new Cafe(misturaCasa);
        misturaCasa = new Leite(misturaCasa);
        System.out.printf("%s\nCusto: R$ %,.2f\n\n", misturaCasa.getDescricao(), misturaCasa.custo());

        Lanchonete pratoCasa = new PratoCasa();
        pratoCasa = new Farinha(pratoCasa);
        pratoCasa = new Tomate(pratoCasa);
        pratoCasa = new Pepino(pratoCasa);
        System.out.printf("%s\nCusto: R$ %,.2f\n\n", pratoCasa.getDescricao(), pratoCasa.custo());

        Lanchonete bebida3 = new Suco();
        bebida3 = new Acucar(bebida3);
        System.out.printf("%s\nCusto: R$ %,.2f\n", bebida3.getDescricao(), bebida3.custo());
    }
}

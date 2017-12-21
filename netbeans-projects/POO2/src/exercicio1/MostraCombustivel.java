package exercicio1;

public class MostraCombustivel implements MostradorCombustivel {

    public void mostraCombustivel(int c, int cMax) {
        if (c < cMax / 4) {
            System.out.println("Combustivel: 1/4");
        } else if (c < cMax / 2) {
            System.out.println("Combustivel: 1/2");
        } else if (c < (cMax / 4) * 3) {
            System.out.println("Combustivel: 3/4");
        } else {
            System.out.println("Combustivel: 1");
        }
    }
}

package envoltoriaConvexa;

import java.util.List;

public class Sistema {

    public static void main(String[] args) {
        //Alunos: Caio Renan Hobus
        //        Matheus Bortolon
        EnvoltoriaConvexa maiorDistancia = new EnvoltoriaConvexa();
        maiorDistancia.addPonto(new Ponto(1, 1));
        maiorDistancia.addPonto(new Ponto(3, 6));
        maiorDistancia.addPonto(new Ponto(4, 4));
        maiorDistancia.addPonto(new Ponto(6, 3));
        maiorDistancia.addPonto(new Ponto(7, 6));
        maiorDistancia.addPonto(new Ponto(3, 2));
        maiorDistancia.addPonto(new Ponto(5, 8));
        maiorDistancia.addPonto(new Ponto(6, 5));
        maiorDistancia.addPonto(new Ponto(6, 7));
        maiorDistancia.addPonto(new Ponto(3, 4));

        List<Ponto> pontos = maiorDistancia.getEnvoltoriaConvexa();
        System.out.println("Envoltória convexa");
        for (Ponto p : pontos) {
            System.out.println(p.getX() + ", " + p.getY());
        }
        System.out.println("Pontos com a maior distância:");
        System.out.println(maiorDistancia.getPontosMaiorDistancia(pontos));
    }
}

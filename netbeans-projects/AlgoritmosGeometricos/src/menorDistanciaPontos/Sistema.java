package menorDistanciaPontos;

public class Sistema {

    public static void main(String[] args) {
        /* Maior Distância
        
        Seguindo a solução que fiz abaixo para calcular a menor distância,
        para calcular a maior distância seria apenas alterar o método
        calcular(), alterando as seguintes linhas:
        
        double menorDistancia = Double.MAX_VALUE - para - double maiorDistancia = Double.MIN_VALUE 
        e 
        if (distCalculada < menorDistancia) - para - if (distCalculada > maiorDistancia)
        e
        menorDistancia = distCalculada; - para - maiorDistancia = distCalculada;*/

        // Solução menor distância
        MenorDistanciaPontos menorDistancia = new MenorDistanciaPontos();
        menorDistancia.addPonto(new Ponto(4, 5));
        menorDistancia.addPonto(new Ponto(3, 1));
        menorDistancia.addPonto(new Ponto(2, 3));
        menorDistancia.addPonto(new Ponto(0, 2));
        menorDistancia.addPonto(new Ponto(5, 4));
        menorDistancia.addPonto(new Ponto(5, 2));
        menorDistancia.addPonto(new Ponto(1, 5));
        menorDistancia.addPonto(new Ponto(2, 4));
        menorDistancia.addPonto(new Ponto(4, 0));
        menorDistancia.addPonto(new Ponto(0, 0));
        System.out.println("Menor Distância:");
        System.out.println(menorDistancia.calcular());
    }
}

package NumerosInteiros;

public class NumerosInteiros {

    private int linferior;
    private int lsuperior;

    public NumerosInteiros(int n1, int n2) {
        if (n1 < n2) {
            linferior = n1;
            lsuperior = n2;
        } else {
            linferior = n2;
            lsuperior = n1;
        }
    }

    public boolean numeroprimo(int numero) {
        int aux;
        boolean ehprimo = true;
        aux = numero - 1;
        while (aux > 1) {
            if ((numero % aux) == 0) {
                ehprimo = false;
                aux = 1;
            }
            aux--;
        }
        return ehprimo;
    }

    public int contaprimo() {
        int numero, quantos = 0;
        for (numero = linferior; numero <= lsuperior; numero++) {
            if (numeroprimo(numero) == true) {
                quantos++;
            }
        }
        return quantos;
    }

    public int menorPrimo() {
        int qual = lsuperior, aux;
        for (aux = lsuperior; aux >= linferior; aux--) {
            if (numeroprimo(aux) == true) {
                qual = aux;
            }
        }
        return qual;
    }

    public boolean multiplo3(int num) {
        if ((num % 3) == 0) {
            return true;
        }
        return false;
    }

    public boolean multiplo5(int num) {
        if ((num % 5) == 0) {
            return true;
        }
        return false;

    }

    public int mult3e5() {
        int aux, qtidade = 0;
        for (aux = linferior; aux <= lsuperior; aux++) {
            if (multiplo3(aux) == true) {
                if (multiplo5(aux) == true) {
                    qtidade++;
                }
            }
        }
        return qtidade;
    }

    public int fatorial(int quem) {
        int fatorial, aux;
        fatorial = 1;
        aux = quem;
        while (aux > 1) {
            fatorial = fatorial * aux;
            aux--;
        }
        return fatorial;
    }
}

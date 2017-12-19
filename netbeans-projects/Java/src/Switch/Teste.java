package Switch;

public class Teste {

    public Teste(int num) {
        verifica(num);
    }

    public void verifica(int n) {
        switch (n / 2) {
            case 4:
                System.out.printf("Numero %d / 2 == 4", n);
                break;
            case 6:
                System.out.printf("Numero %d / 2 == 6", n);
                break;
            case 12:
                System.out.printf("Numero %d / 2 == 12", n);
                break;
            case 40:
                System.out.printf("Numero %d / 2 == 40", n);
                break;
            case 2:
                System.out.printf("Numero %d / 2 == 2", n);
                break;
        }
    }
}

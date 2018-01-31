package Principal;

public class Saida {

    private String token, classe;
    private int numLinha;

    public Saida(String token, String classe, int numLinha) {
        this.token = token;
        this.classe = classe;
        this.numLinha = numLinha;
    }

    public String getToken() {
        return token;
    }

    public String getClasse() {
        return classe;
    }

    public int getNumLinha() {
        return numLinha;
    }
}

package Exe10;

public class Nome {

    private String nome;

    public Nome(String nome) {
        this.nome = nome;
    }

    public String getVerificaIgualdade(String nome) {
        if (this.nome.equalsIgnoreCase(nome)) {
            return "NOME CORRETO";
        }
        return "NOME INCORRETO";
    }
}

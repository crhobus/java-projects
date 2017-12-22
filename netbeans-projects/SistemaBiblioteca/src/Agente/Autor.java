package Agente;

public class Autor extends Agente {

    private String sobrenome;

    public Autor(int codigo, String nome, String sobrenome) {
        setCodigo(codigo);
        setNome(nome);
        setSobrenome(sobrenome);
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}

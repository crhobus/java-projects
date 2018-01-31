package Negocio;

public class Categoria {

    private String nome;
    private int potencia;

    public Categoria(String nome, int potencia) {
        setNome(nome);
        setPotencia(potencia);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null
                || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        if (potencia < 1) {
            throw new IllegalArgumentException("Potência inválida");
        }
        this.potencia = potencia;
    }
}

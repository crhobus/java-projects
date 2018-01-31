package Model;

public class Dados {

    private String nome;
    private int tipoDado;
    private int tipoRegArq;
    private int valor;
    private String nivel;

    public Dados(String nome, int tipoDado, int tipoRegArq) {
        this.nome = nome;
        this.tipoDado = tipoDado;
        this.tipoRegArq = tipoRegArq;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(int tipoDado) {
        this.tipoDado = tipoDado;
    }

    public int getTipoRegArq() {
        return tipoRegArq;
    }

    public void setTipoRegArq(int tipoRegArq) {
        this.tipoRegArq = tipoRegArq;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}

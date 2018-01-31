package Negocio;

public class Comodo {

    private double largura;
    private double comprimento;
    private Categoria categoria;

    public Comodo(double largura, double comprimento, Categoria categoria) {
        setLargura(largura);
        setComprimento(comprimento);
        setCategoria(categoria);
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        if (largura < 0) {
            throw new IllegalArgumentException("Largura inválida");
        }
        this.largura = largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        if (comprimento < 0) {
            throw new IllegalArgumentException("Comprimento inválido");
        }
        this.comprimento = comprimento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria inválida");
        }
        this.categoria = categoria;
    }

    public double getAreaCalculada() {
        return largura * comprimento;
    }

    public double getPotenciaCalculada() {
        return getAreaCalculada() * categoria.getPotencia();
    }

    public double getPotenciaIluminacao() {
        return 60 * getQtdMinLampadas();
    }

    public int getQtdMinLampadas() {
        return (int) Math.ceil(getPotenciaCalculada() / 60);
    }
}

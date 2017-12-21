package Exe2LojaInformaticaInterfaces;

public class Vendas implements LojaReceita {

    private String nomeProd;
    private double descontos, preco;
    private int qtdade;

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getNomeReceita() {
        return nomeProd;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getValorReceita() {
        double result1 = qtdade * preco;
        double result2 = (result1 / 100) * descontos;
        return result1 - result2;
    }

    public void setDescontos(double descontos) {
        this.descontos = descontos;
    }

    public double getDescontos() {
        return descontos;
    }

    public void setQtdade(int qtdade) {
        this.qtdade = qtdade;
    }

    public int getQtdade() {
        return qtdade;
    }
}

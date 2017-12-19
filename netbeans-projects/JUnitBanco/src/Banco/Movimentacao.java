package Banco;

import java.util.Date;

public class Movimentacao {

    private Date data;
    private double valor;
    private String tipo;

    public Movimentacao(Date data, double valor, String tipo) {
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }
}

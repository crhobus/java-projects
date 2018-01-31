package Negocio;

import java.util.ArrayList;
import java.util.List;

public abstract class Edificacao {

    private String responsavel;
    private String rua;
    private int numero;
    private List<Comodo> comodos;

    public Edificacao(String responsavel, String rua, int numero) {
        setResponsavel(responsavel);
        setRua(rua);
        setNumero(numero);
        comodos = new ArrayList<>();
    }

    @Override
    public abstract String toString();

    public abstract double getPotenciaFinal();

    public int getTotalLampadas() {
        int total = 0;
        for (Comodo comodo : getComodos()) {
            total += comodo.getQtdMinLampadas();
        }
        return total;
    }

    public void addComodo(Comodo comodo) {
        comodos.add(comodo);
    }

    public List<Comodo> getComodos() {
        return comodos;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        if (responsavel == null
                || responsavel.isEmpty()) {
            throw new IllegalArgumentException("Responsável inválido");
        }
        this.responsavel = responsavel;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        if (rua == null
                || rua.isEmpty()) {
            throw new IllegalArgumentException("Rua inválida");
        }
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (numero < 1) {
            throw new IllegalArgumentException("Número inválido");
        }
        this.numero = numero;
    }
}

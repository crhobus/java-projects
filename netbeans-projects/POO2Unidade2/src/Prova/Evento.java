package Prova;

import java.util.*;

public class Evento {

    private String nome;
    private Date data;
    private boolean chuva;
    private boolean capac;
    private boolean caro;

    public Evento(String nome, Date data, boolean chuva, boolean capac,
            boolean caro) {
        super();
        this.nome = nome;
        this.data = data;
        this.chuva = chuva;
        this.capac = capac;
        this.caro = caro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isChuva() {
        return chuva;
    }

    public void setChuva(boolean chuva) {
        this.chuva = chuva;
    }

    public boolean isCapac() {
        return capac;
    }

    public void setCapac(boolean capac) {
        this.capac = capac;
    }

    public boolean isCaro() {
        return caro;
    }

    public void setCaro(boolean caro) {
        this.caro = caro;
    }
}

package Exe3Impressora;

import java.util.*;

public class Impressora<T extends Folha> {

    private ArrayList<T> bandeja = new ArrayList<T>();
    private int tamBandeja;

    public Impressora(int tamBandeja) {
        this.tamBandeja = tamBandeja;
    }

    public void addFolhas(ArrayList<T> folhas) {
        while (folhas.size() > 0 & bandeja.size() < tamBandeja) {
            bandeja.add(folhas.remove(0));
        }
    }

    public void imprimir(ArrayList<String> texto) {
        T folha = bandeja.remove(0);
        while (texto.size() > 0 & bandeja.size() > 0) {
            if (folha.imprimir(texto.remove(0))) {
                folha = bandeja.remove(0);
                System.out.println("Pulou folha");
            }
        }
    }

    public int getTamBandeja() {
        return tamBandeja;
    }

    public void setTamBandeja(int tamBandeja) {
        this.tamBandeja = tamBandeja;
    }

    public ArrayList<? extends Folha> getBandeja() {
        return bandeja;
    }

    public void setBandeja(ArrayList<T> bandeja) {
        this.bandeja = bandeja;
    }
}
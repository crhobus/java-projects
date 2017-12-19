package exercicios.trabalho;

import java.util.Date;

public class Medida {

    Date dt;
    float peso;

    public Medida(float p) {
        dt = new Date();
        peso = p;
    }

    public Medida(Date d, float p) {
        dt = d;
        peso = p;
    }

    public Date getData() {
        return dt;
    }

    public float getPeso() {
        return peso;
    }
}

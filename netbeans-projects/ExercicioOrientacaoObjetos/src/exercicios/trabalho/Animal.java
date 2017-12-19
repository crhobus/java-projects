package exercicios.trabalho;

import java.text.DateFormat;
import java.util.Vector;

public abstract class Animal {

    static DateFormat df = DateFormat.getDateInstance();
    int id = 1;
    Vector medidas = new Vector();

    public Animal(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }

    public void addMedida(Medida m) {
        medidas.addElement(m);
    }

    public float getPeso() {
        return getUltimaMedida().getPeso();
    }

    public String toString() {
        Medida ultima = getUltimaMedida();
        if (ultima != null) {
            return getClass() + "(" + id + ") tem " + medidas.size()
                    + " medidas, sendo o peso atual " + ultima.getPeso()
                    + " medido em " + df.format(ultima.getData());
        } else {
            return getClass() + "(" + id + ") sem medidas";
        }
    }

    private Medida getUltimaMedida() {
        if (medidas.size() > 0) {
            return (Medida) medidas.elementAt(medidas.size() - 1);
        } else {
            return null;
        }
    }
}

package exercicios.trabalho;

import java.util.HashMap;
import java.util.Iterator;

public class Frigorifico {

    HashMap animais = new HashMap();

    public void addAnimal(Animal a) {
        animais.put(new Integer(a.getId()), a);
    }

    public void descobreMaisGordo() {
        Animal maior = null;
        Iterator ianimais = animais.values().iterator();
        while (ianimais.hasNext()) {
            Animal a = (Animal) ianimais.next();
            System.out.println("Perguntando para o Animal " + a);
            if (maior == null) {
                maior = a;
            } else {
                try {
                    if (a.getPeso() > maior.getPeso()) {
                        maior = a;
                        System.out.println("Maior passou a ser " + a);
                    }
                } catch (Exception ex) {}
            }
        }
        System.out.println("Mais gordo eh " + maior);
    }

    public Animal procuraAnimal(Integer id) {
        return (Animal) animais.get(id);
    }

    public Animal procuraAnimal(int id) {
        return (Animal) animais.get(new Integer(id));
    }
}

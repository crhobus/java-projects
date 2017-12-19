package exercicios.trabalho;

import java.text.DateFormat;
import java.text.ParseException;

public class EstDir {

    public static void main(String[] args) throws ParseException {
        DateFormat df = DateFormat.getDateInstance();
        Frigorifico f = new Frigorifico();
        Boi b = new Boi(1);
        b.addMedida(new Medida(100));

        Animal a = b;
        a.addMedida(new Medida(df.parse("19/11/2011"), 180));
        f.addAnimal(a);

        Porco p = new Porco(2);
        f.addAnimal(p);
        b = new Boi(3);
        b.addMedida(new Medida(300));
        f.addAnimal(b);

        p = new Porco(4);
        p.addMedida(new Medida(150));
        f.addAnimal(p);

        System.out.println("Descrição do Animal 2: " + f.procuraAnimal(2) + "        ");
        f.descobreMaisGordo();
    }
}

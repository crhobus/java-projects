package FabricaChocolate;

public class Sistema {

    public static void main(String[] args) {
        CaldeiraChocolate caldeiraChocolate = CaldeiraChocolate.getInstance();
        System.out.println(caldeiraChocolate.estaFervido());
    }
}

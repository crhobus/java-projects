package Exe24;

public class Calcula {

    public String getCustoConsumidor(double custoFabrica) {
        if (custoFabrica < 12000) {
            return "Distribuidor 5%: " + (custoFabrica * 0.05)
                    + "\nImpostos isento" + "\nCusto para o consumidor: R$ "
                    + (custoFabrica * 1.05);
        }
        if (custoFabrica >= 12000 && custoFabrica <= 25000) {
            return "Distribuidor 10%: " + (custoFabrica * 0.1)
                    + "\nImpostos 15%: " + (custoFabrica * 0.15)
                    + "\nCusto para o consumidor: R$ " + (custoFabrica * 1.25);
        }
        return "\nDistribuidor 15%: " + (custoFabrica * 0.15)
                + "\nImpostos 20%: " + (custoFabrica * 0.2)
                + "\nCusto para o consumidor: R$ " + (custoFabrica * 1.35);
    }
}

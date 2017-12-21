package Exe2PedidoVenda;

public class FreteSul {

    private float resultado1;
    private float resultado2;
    private float peso;
    private String regiao;

    public FreteSul(String Regiao, float Peso) {
        this.peso = Peso;
        this.regiao = Regiao;
        FreteNorte(peso);
        FreteNordeste(peso);
        FreteCentroOeste(peso);
        FreteSuldeste(peso);
        FreteSul(peso);
    }

    public void FreteNorte(float peso) {
        if (regiao.equalsIgnoreCase("Norte")) {
            float num1 = resultado1;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 3;
            resultado2 = resultado1 + calculo2;
            float calculo3 = peso * 2;
            float calculo4 = calculo3 + resultado2;
            System.out.println("Frete: 3% do valor do Pedido + R$ 2,00 o quilo da região Norte");
            System.out.println("Total Calculado com frete: R$" + calculo4);
        }
    }

    public void FreteNordeste(float peso) {
        if (regiao.equalsIgnoreCase("Nordeste")) {
            float num1 = resultado1;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 3;
            resultado2 = resultado1 + calculo2;
            float calculo3 = peso * (float) 1.80;
            float calculo4 = calculo3 + resultado2;
            System.out.println("Frete: 3% do valor do Pedido + R$ 1,80 o quilo da região Nordeste");
            System.out.println("Total Calculado com frete: R$" + calculo4);
        }
    }

    public void FreteCentroOeste(float peso) {
        if (regiao.equalsIgnoreCase("Centro Oeste")) {
            float num1 = resultado1;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 3;
            resultado2 = resultado1 + calculo2;
            float calculo3 = peso * (float) 1.50;
            float calculo4 = calculo3 + resultado2;
            System.out.println("Frete: 3% do valor do Pedido + R$ 1,50 o quilo da região Centro Oeste");
            System.out.println("Total Calculado com frete: R$" + calculo4);
        }
    }

    public void FreteSuldeste(float peso) {
        if (regiao.equalsIgnoreCase("Suldeste")) {
            float num1 = resultado1;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 3;
            resultado2 = resultado1 + calculo2;
            float calculo3 = peso * (float) 1.20;
            float calculo4 = calculo3 + resultado2;
            System.out.println("Frete: 3% do valor do Pedido + R$ 1,20 o quilo da região Suldeste");
            System.out.println("Total Calculado com frete: R$" + calculo4);
        }
    }

    public void FreteSul(float peso) {
        if (regiao.equalsIgnoreCase("Sul")) {
            float num1 = resultado1;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 3;
            resultado2 = resultado1 + calculo2;
            float calculo3 = peso * 1;
            float calculo4 = calculo3 + resultado2;
            System.out.println("Frete: 3% do valor do Pedido + R$ 1,00 o quilo da região Sul");
            System.out.println("Total Calculado com frete: R$" + calculo4);
        }
    }
}

package Exe2PedidoVenda;

public class FreteSuldeste {

    private String regiao;
    private float resultado;
    private float peso;

    public FreteSuldeste(String Regiao, float Peso) {
        this.regiao = Regiao;
        this.peso = Peso;
        FreteNorte(peso);
        FreteNordeste(peso);
        FreteCentroOeste(peso);
        FreteSuldeste(peso);
        FreteSul(peso);
    }

    public void FreteNorte(float peso) {
        if (regiao.equalsIgnoreCase("Norte")) {
            float calculo1 = peso * 40;
            float calculo2 = calculo1 + resultado;
            System.out.println("R$ 40,00 o quilo da região Norte");
            System.out.println("Total Calculado com frete: R$" + calculo2);
        }
    }

    public void FreteNordeste(float peso) {
        if (regiao.equalsIgnoreCase("Nordeste")) {
            float calculo1 = peso * 37;
            float calculo2 = calculo1 + resultado;
            System.out.println("R$ 37,00 o quilo da região Nordeste");
            System.out.println("Total Calculado com frete: R$" + calculo2);
        }
    }

    public void FreteCentroOeste(float peso) {
        if (regiao.equalsIgnoreCase("Centro Oeste")) {
            float calculo1 = peso * 30;
            float calculo2 = calculo1 + resultado;
            System.out.println("R$ 30,00 o quilo da região Centro Oeste");
            System.out.println("Total Calculado com frete: R$" + calculo2);
        }
    }

    public void FreteSuldeste(float peso) {
        if (regiao.equalsIgnoreCase("Suldeste")) {
            float calculo1 = peso * 15;
            float calculo2 = calculo1 + resultado;
            System.out.println("R$ 15,00 o quilo da região Suldeste");
            System.out.println("Total Calculado com frete: R$" + calculo2);
        }
    }

    public void FreteSul(float peso) {
        if (regiao.equalsIgnoreCase("Sul")) {
            float calculo1 = peso * 25;
            float calculo2 = calculo1 + resultado;
            System.out.println("R$ 25,00 o quilo da região Sul");
            System.out.println("Total Calculado com frete: R$" + calculo2);
        }
    }
}

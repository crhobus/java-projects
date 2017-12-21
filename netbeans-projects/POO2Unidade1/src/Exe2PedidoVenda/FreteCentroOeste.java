package Exe2PedidoVenda;

public class FreteCentroOeste {

    private float resultado;

    public FreteCentroOeste(float preco, String regiao) {
        FreteNorteSul(regiao);
        FreteNordesteSuldeste(regiao);
        FreteCentroOeste(regiao);
    }

    public void FreteNorteSul(String regiao) {
        if (regiao.equalsIgnoreCase("Norte") != regiao.equalsIgnoreCase("Sul")) {
            float calculo = resultado + 80;
            System.out.println("Frete de R$ 80,00 da região Norte ou Sul");
            System.out.println("Total Calculado com frete: R$" + calculo);
        }
    }

    public void FreteNordesteSuldeste(String regiao) {
        if (regiao.equalsIgnoreCase("Nordeste") != regiao.equalsIgnoreCase("Suldeste")) {
            float calculo = resultado + 70;
            System.out.println("Frete de R$ 70,00 da região Nordeste ou Suldeste");
            System.out.println("Total Calculado com frete: R$" + calculo);
        }
    }

    public void FreteCentroOeste(String regiao) {
        if (regiao.equalsIgnoreCase("Centro Oeste")) {
            float calculo = resultado + 30;
            System.out.println("Frete de R$ 30,00 da região Centro Oeste");
            System.out.println("Total Calculado com frete: R$" + calculo);
        }
    }
}

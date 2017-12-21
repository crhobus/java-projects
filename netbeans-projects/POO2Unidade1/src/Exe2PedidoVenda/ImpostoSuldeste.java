package Exe2PedidoVenda;

public class ImpostoSuldeste {

    private String regiao;
    private float resultado;

    public ImpostoSuldeste(float preco, String Regiao) {
        this.regiao = Regiao;
        ImpostoSuldeste(preco);
    }

    public void ImpostoSuldeste(float valor) {
        if (regiao.equalsIgnoreCase("Norte") != regiao.equalsIgnoreCase("Nordeste")) {
            float num1 = valor;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 5;
            resultado = valor + calculo2;
            System.out.println("Para região Suldeste 5% de Imposto se veio do Norte ou Nordeste");
            System.out.println("Valor Calculado com Imposto: R$ " + resultado);
        }
        if (regiao.equalsIgnoreCase("Centro Oeste")) {
            float num1 = valor;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 7;
            resultado = valor + calculo2;
            System.out.println("Para região Suldeste 5% de Imposto se veio do Centro Oeste");
            System.out.println("Valor Calculado com Imposto: R$ " + resultado);
        }
        if (regiao.equalsIgnoreCase("Sul") != regiao.equalsIgnoreCase("Suldeste")) {
            float num1 = valor;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 10;
            resultado = valor + calculo2;
            System.out.println("Para região Suldeste 10% de Imposto se veio do Sul ou Suldeste");
            System.out.println("Valor Calculado com Imposto: R$ " + resultado);
        }
    }
}

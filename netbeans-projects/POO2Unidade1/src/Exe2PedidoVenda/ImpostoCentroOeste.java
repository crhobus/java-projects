package Exe2PedidoVenda;

public class ImpostoCentroOeste {

    private float resultado;

    public ImpostoCentroOeste(float preco, String regiao) {
        if (preco <= 5000) {
            float num1 = preco;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 12;
            resultado = ((preco - calculo2) + 50);
            System.out.println("Para região Centro Oeste 12% de Imposto + R$ 50,00 sobre o valor");
            System.out.println("Valor Calculado com Imposto: R$ " + resultado);
        }
    }
}

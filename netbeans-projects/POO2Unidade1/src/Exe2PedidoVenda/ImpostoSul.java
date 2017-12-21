package Exe2PedidoVenda;

public class ImpostoSul {

    private float resultado;

    public ImpostoSul(float preco) {
        ImpostoSul(preco);
    }

    public void ImpostoSul(float valor) {
        float num1 = valor;
        float calculo1 = num1 / 100;
        float calculo2 = calculo1 * 12;
        resultado = valor + calculo2;
        System.out.println("Para região Sul 12% de Imposto sobre o valor");
        System.out.println("Valor Calculado com Imposto: R$ " + resultado);
    }
}

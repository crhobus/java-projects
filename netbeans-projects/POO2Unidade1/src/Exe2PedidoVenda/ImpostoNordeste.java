package Exe2PedidoVenda;

public class ImpostoNordeste {

    private float resultado;

    public ImpostoNordeste(float valor) {
        if (valor <= 1000) {
            float num1 = valor;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 8;
            resultado = valor + calculo2;
            System.out.println("Para região Nordeste 8% de Imposto sobre o valor");
            System.out.println("Valor Calculado com Imposto: R$ " + resultado);
        }
        if (valor > 1000 & valor <= 15000) {
            float num1 = valor;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 10;
            resultado = valor + calculo2;
            System.out.println("Para região Nordeste 10% de Imposto sobre o valor");
            System.out.println("Valor Calculado com Imposto: R$ " + resultado);
        }
        if (valor > 15000) {
            float num1 = valor;
            float calculo1 = num1 / 100;
            float calculo2 = calculo1 * 12;
            resultado = valor + calculo2;
            System.out.println("Para região Nordeste 12% de Imposto sobre o valor");
            System.out.println("Valor Calculado com Imposto: R$ " + resultado);
        }
    }
}

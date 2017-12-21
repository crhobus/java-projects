package Exe2PedidoVenda;

public class FreteNorteNordeste {

    private float resultado;

    public FreteNorteNordeste(float peso) {
        if (peso <= 3.5) {
            float calculo = resultado + 100;
            System.out.println("Até 3,5Kg frete de R$ 100,00 da região Norte ou Nordeste");
            System.out.println("Total Calculado com frete: R$" + calculo);
        }
    }
}

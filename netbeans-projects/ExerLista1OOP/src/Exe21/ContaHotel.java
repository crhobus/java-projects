package Exe21;

public class ContaHotel {

    public String getOpcoes() {
        return "Opções" + "\n1 - apto simples" + "\n2 - apto duplo"
                + "\n3 - suíte luxo";
    }

    public String getValorPagar(int opcao, int qtdadeDias) {
        if (opcao == 1) {
            return "Valor a ser pago: R$ " + (qtdadeDias * 45) + ",00 por dia";
        }
        if (opcao == 2) {
            return "Valor a ser pago: R$ " + (qtdadeDias * 65) + ",00 por dia";
        }
        if (opcao == 3) {
            return "Valor a ser pago: R$ " + (qtdadeDias * 110) + ",00 por dia";
        }
        return "Entre com uma opção válida";
    }
}

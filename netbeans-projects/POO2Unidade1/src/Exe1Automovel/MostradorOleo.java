package Exe1Automovel;

public class MostradorOleo {

    //mostrar oleo
    private String proporcao;

    public void mostrarOleo(double oleo) {

        if (oleo / 15 > 0.5) {
            proporcao = "acima de 1/2";
        } else if (oleo / 15 == 0.5) {
            proporcao = "1/2";
        } else if (oleo / 15 < 0.5) {
            proporcao = "abaixo de 1/2";
        }

        System.out.printf("Proporcão do óleo: %s\n", proporcao);
    }
}

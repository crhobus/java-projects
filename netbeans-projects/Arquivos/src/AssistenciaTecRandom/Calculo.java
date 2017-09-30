package AssistenciaTecRandom;

public class Calculo {

    public double Calculo(double subTotal, double descontos) {
        double resultado = 0;
        double result = (subTotal / 100) * descontos;
        resultado = subTotal - result;
        return resultado;
    }
}

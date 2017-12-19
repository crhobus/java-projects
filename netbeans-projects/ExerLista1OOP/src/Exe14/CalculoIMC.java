package Exe14;

public class CalculoIMC {

    public String getImc(double peso, double altura) {
        double imc = peso / (altura * altura);
        if (imc < 17) {
            return "Muito abaixo do peso";
        }
        if (imc >= 17 && imc <= 18.49) {
            return "Abaixo do peso";
        }
        if (imc >= 18.5 && imc <= 24.99) {
            return "Peso normal";
        }
        if (imc >= 25 && imc <= 29.99) {
            return "Acima do peso";
        }
        if (imc >= 30 && imc <= 34.99) {
            return "Obesidade I";
        }
        if (imc >= 35 && imc <= 39.99) {
            return "Obesidade II (severa)";
        }
        return "Obesidade III (mórbida)";
    }
}

package Exe3Planilha;

import java.util.ArrayList;

public class Calcula {

    public double Calcula(ArrayList<ArrayPlanilha> lista, ArrayPlanilha item) {

        double num1 = item.getNum1();
        double num2 = item.getNum2();
        String operacao = item.getOperacao();
        double resultado = 0;

        if (operacao == null) {
            return 0;
        }
        if (operacao.equals(" +")) {
            resultado = num1 + num2;
        }
        if (operacao.equals(" -")) {
            resultado = num1 - num2;
        }
        if (operacao.equals(" *")) {
            resultado = num1 * num2;
        }
        if (operacao.equals(" /")) {
            resultado = num1 / num2;
        }
        if (operacao.equals(" som c1")) {
            double n = 0;
            for (int i = 0; i < lista.size(); i++) {
                n = n + lista.get(i).getNum1();
            }
            resultado = n;
        }
        if (operacao.equals(" som c2")) {
            double n = 0;
            for (int i = 0; i < lista.size(); i++) {
                n = n + lista.get(i).getNum2();
            }
            resultado = n;
        }
        return resultado;
    }
}

package Exe3Planilha;

import java.util.ArrayList;

public class ArrayPlanilha {

    private double num1, num2;
    private String operacao;
    private ArrayList<ArrayPlanilha> lista;

    public ArrayPlanilha(ArrayList<ArrayPlanilha> lista) {
        this.lista = lista;
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public double getResultado() {
        Calcula cal = new Calcula();
        return cal.Calcula(lista, this);
    }
}

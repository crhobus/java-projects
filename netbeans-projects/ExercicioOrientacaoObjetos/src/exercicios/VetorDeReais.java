/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicios;

/**
 *
 * @author CaioRenan
 */
public class VetorDeReais {

    private double[] vetor;

    public VetorDeReais(int tamanho) {
        this.vetor = new double[tamanho];
    }

    public void setValor(int posicao, double valor) {
        this.vetor[posicao] = valor;
    }

    public double getValor(int posicao) {
        return vetor[posicao];
    }

    public int getTamanho() {
        return vetor.length;
    }

    public double multiplica(VetorDeReais v) {
        if (getTamanho() != v.getTamanho()) {
            return Double.MIN_VALUE;
        }
        double result = 0;
        for (int i = 0, y = vetor.length - 1; i < vetor.length; i++, y--) {
            result += vetor[i] * v.getValor(y);
        }
        return result;
    }

    public VetorDeReais divide(VetorDeReais v) {
        if (getTamanho() != v.getTamanho()) {
            return null;
        }
        VetorDeReais result = new VetorDeReais(getTamanho());
        double temp;
        for (int i = 0; i < vetor.length; i++) {
            temp = getValor(i) / v.getValor(i);
            result.setValor(i, temp);
        }
        return result;
    }

    public double maiorDiferenca() {
        double diferenca = 0, temp;
        for (int i = 1; i < getTamanho(); i++) {
            temp = Math.abs(vetor[i]) - Math.abs(vetor[i - 1]);
            if (diferenca < temp) {
                diferenca = temp;
            }
        }
        return diferenca;
    }
}

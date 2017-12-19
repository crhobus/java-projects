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
public class InteiroPositivo {

    private int x;

    public void setValor(int valor) {
        if (valor >= 0) {
            this.x = valor;
        }
    }

    public long multiplica(InteiroPositivo outro) {
        return this.x * outro.getValor();
    }

    public int getValor() {
        return this.x;
    }

    public long fatorial() {
        return fatorial(this.x);
    }

    private long fatorial(int num) {
        long res = 1;
        for (int c = 2; c <= num; c++) {
            res *= c;
        }
        return res;
    }

    public double valorS() {
        double res = 0;
        for (int exp = 20, den = 1; den < 20; exp--, den++) {
            res += Math.pow(this.x, exp) / fatorial(den);
        }
        return res;
    }
}

package exercicios.sindicatoJogadoresFutebol;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marcel
 */
public class Jogador {

    private String nome;
    private float salarioAtual;
    private char situacao;
    private Clube clube;

    public void setNome(String str) {
        nome = str;
    }

    public void setSalarioAtual(float valor) {
        if (valor >= 0) {
            salarioAtual = valor;
        } else {
            throw new IllegalArgumentException("Slário menor que ZERO!");
        }
    }

    public void setSituacao(char c) {
        if (c == 'T' || c == 'R') {
            situacao = c;
        } else {
            throw new IllegalArgumentException("Situação só pode ser (T)itular ou (R)eserva.");
        }
    }

    public String getNome() {
        return nome;
    }

    public float getSalarioAtual() {
        return salarioAtual;
    }

    public char getSituacao() {
        return situacao;
    }

    public float getNovoSalario() {
        float indice;
        if (salarioAtual < 9001) {
            indice = 1.20f;
        } else if (salarioAtual < 13001) {
            indice = 1.10f;
        } else if (salarioAtual < 18001) {
            indice = 1.05f;
        } else {
            indice = 1;
        }
        return (salarioAtual * indice);
    }

    public String toString() {
        return ("Nome: " + nome + " da equipe " + clube.getNome()
                + " recebe R$ " + salarioAtual
                + " receberão R$ " + this.getNovoSalario());
    }

    // retorna true caso salario deste jogador seja menor que o do outro
    public boolean menorSalario(Jogador outro) {
        if (outro == null
                || outro.getSalarioAtual() > this.getSalarioAtual()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the clube
     */
    public Clube getClube() {
        return clube;
    }

    /**
     * @param clube the clube to set
     */
    protected void setClube(Clube clube) {
        this.clube = clube;
    }
}

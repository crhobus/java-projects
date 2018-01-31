package CaioRenanHobus;

public class Alunos {

    private int numero;
    private char nome[];
    private byte discCursadas[];

    public byte[] getDiscCursadas() {
        return discCursadas;
    }

    public void setDiscCursadas(byte[] discCursadas) {
        this.discCursadas = discCursadas;
    }

    public char[] getNome() {
        return nome;
    }

    public void setNome(char[] nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}

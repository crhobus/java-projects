package PilhaGenerica;

public class PilhaCheiaException extends RuntimeException {

    public PilhaCheiaException() {
        this("Pilha está vazia");
    }

    public PilhaCheiaException(String ex) {
        super(ex);
    }
}

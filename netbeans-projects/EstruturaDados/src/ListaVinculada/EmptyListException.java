package ListaVinculada;

public class EmptyListException extends RuntimeException {

    public EmptyListException() {
        this("Lista");
    }

    public EmptyListException(String nome) {
        super(nome + " está vazia");
    }
}

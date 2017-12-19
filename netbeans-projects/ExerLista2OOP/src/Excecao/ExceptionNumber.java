package Excecao;

public class ExceptionNumber extends Exception {

    private static final long serialVersionUID = 1L;

    public ExceptionNumber() {
        super("Entre com um número válido");
    }

    public ExceptionNumber(String mensagem) {
        super(mensagem);
    }
}

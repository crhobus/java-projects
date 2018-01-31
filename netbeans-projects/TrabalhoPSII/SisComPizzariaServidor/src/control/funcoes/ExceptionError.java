package control.funcoes;

public class ExceptionError extends Excecao {

    public ExceptionError(String msg) {
        super(msg);
    }

    public ExceptionError(String dsCampoFocar, String msg) {
        super(dsCampoFocar, msg);
    }
}

package control.funcoes;

public class ExceptionInfo extends Excecao {

    public ExceptionInfo(String msg) {
        super(msg);
    }

    public ExceptionInfo(String dsCampoFocar, String msg) {
        super(dsCampoFocar, msg);
    }
}

package control.funcoes;

public abstract class Excecao extends Exception {

    private String dsCampoFocar;

    public Excecao(String msg) {
        super(msg);
    }

    public Excecao(String dsCampoFocar, String msg) {
        super(msg);
        this.dsCampoFocar = dsCampoFocar;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public String getDsCampoFocar() {
        return dsCampoFocar;
    }
}

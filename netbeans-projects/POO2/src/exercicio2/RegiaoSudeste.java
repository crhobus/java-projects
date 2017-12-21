package exercicio2;

public class RegiaoSudeste implements Regiao {

    public static RegiaoSudeste in;

    private RegiaoSudeste() {
        //Construtor vazio para utilizar o padr�o de projeto Singleton
    }

    public static RegiaoSudeste getInstancia() {
        if (in == null) {
            in = new RegiaoSudeste();
        }
        return in;
    }

    @Override
    public Frete getFrete() {
        return new FreteSudeste();
    }

    @Override
    public Imposto getImposto() {
        return new ImpostoSudeste();
    }
}

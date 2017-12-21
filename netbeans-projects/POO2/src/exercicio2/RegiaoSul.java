package exercicio2;

public class RegiaoSul implements Regiao {

    public static RegiaoSul in;

    private RegiaoSul() {
        //Construtor vazio para utilizar o padr�o de projeto Singleton
    }

    public static RegiaoSul getInstancia() {
        if (in == null) {
            in = new RegiaoSul();
        }
        return in;
    }

    @Override
    public Frete getFrete() {
        return new FreteSul();
    }

    @Override
    public Imposto getImposto() {
        return new ImpostoSul();
    }
}


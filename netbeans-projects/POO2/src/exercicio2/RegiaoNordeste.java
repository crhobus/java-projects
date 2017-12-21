package exercicio2;

public class RegiaoNordeste implements Regiao {

    public static RegiaoNordeste in;

    private RegiaoNordeste() {
        //Construtor vazio para utilizar o padrão de projeto Singleton
    }

    public static RegiaoNordeste getInstancia() {
        if (in == null) {
            in = new RegiaoNordeste();
        }
        return in;
    }

    @Override
    public Frete getFrete() {
        return new FreteNorte();
    }

    @Override
    public Imposto getImposto() {
        return new ImpostoNordeste();
    }
}

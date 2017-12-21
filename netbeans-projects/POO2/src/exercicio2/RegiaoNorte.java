package exercicio2;

public class RegiaoNorte implements Regiao {

    public static RegiaoNorte in;

    private RegiaoNorte() {
        //Construtor vazio para utilizar o padrão de projeto Singleton
    }

    public static RegiaoNorte getInstancia() {
        if (in == null) {
            in = new RegiaoNorte();
        }
        return in;
    }

    @Override
    public Frete getFrete() {
        return new FreteNorte();
    }

    @Override
    public Imposto getImposto() {
        return new ImpostoNorte();
    }
}

package exercicio2;

public class RegiaoCentroOeste implements Regiao {

    public static RegiaoCentroOeste in;

    private RegiaoCentroOeste() {
        //Construtor vazio para utilizar o padrão de projeto Singleton
    }

    public static RegiaoCentroOeste getInstancia() {
        if (in == null) {
            in = new RegiaoCentroOeste();
        }
        return in;
    }

    @Override
    public Frete getFrete() {
        return new FreteCentroOeste();
    }

    @Override
    public Imposto getImposto() {
        return new ImpostoCentroOeste();
    }
}

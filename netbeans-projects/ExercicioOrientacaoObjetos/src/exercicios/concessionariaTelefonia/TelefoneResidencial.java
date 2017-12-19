package exercicios.concessionariaTelefonia;

import java.util.Date;

public class TelefoneResidencial extends Telefone {

    private boolean conexaoInternet;

    public TelefoneResidencial(String nomeUsuario, String numero, Date dataInstalacao, String enderecoInstalacao, boolean conexaoInternet) {
        super(nomeUsuario, numero, dataInstalacao, enderecoInstalacao);
        setConexaoInternet(conexaoInternet);
    }

    public boolean isConexaoInternet() {
        return conexaoInternet;
    }

    public void setConexaoInternet(boolean conexaoInternet) {
        this.conexaoInternet = conexaoInternet;
    }

    @Override
    public String exibirDados() {
        return "Telefone residencial: " + this.numero
                + " do usuário " + this.nomeUsuario
                + " instalado em " + this.enderecoInstalacao
                + " na data " + this.dataInstalacao
                + (this.isConexaoInternet() ? " com" : " sem") + " conexão com a internet";
    }

    @Override
    public float getValorBasico() {
        return 15.0f;
    }
}

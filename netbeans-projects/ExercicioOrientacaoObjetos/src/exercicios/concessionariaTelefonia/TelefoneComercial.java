package exercicios.concessionariaTelefonia;

import java.util.Date;

public class TelefoneComercial extends Telefone {

    private int qtdRamais;
    private static Date dataBase;

    public TelefoneComercial(String nomeUsuario, String numero, Date dataInstalacao, String enderecoInstalacao, int qtdRamais) {
        super(nomeUsuario, numero, dataInstalacao, enderecoInstalacao);
        setQtdRamais(qtdRamais);
    }

    public int getQtdRamais() {
        return qtdRamais;
    }

    public void setQtdRamais(int qtdRamais) {
        if (qtdRamais < 0) {
            throw new IllegalArgumentException("Quantidade de ramais inválido");
        }
        this.qtdRamais = qtdRamais;
    }

    public static Date getDataBase() {
        return dataBase;
    }

    public static void setDataBase(Date dataBase) {
        if (dataBase == null) {
            throw new IllegalArgumentException("Data base inválida");
        }
        TelefoneComercial.dataBase = dataBase;
    }

    @Override
    public String exibirDados() {
        return "Telefone comercial: " + this.numero
                + " do usuário " + this.nomeUsuario
                + " instalado em " + this.enderecoInstalacao
                + " na data " + this.dataInstalacao
                + " com " + this.qtdRamais + " ramais";
    }

    @Override
    public float getValorBasico() {
        if (this.dataInstalacao.before(dataBase)) {
            return 25.0f;
        }
        return 37.50f;
    }
}

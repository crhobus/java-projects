package Negocio;

public class Residencial extends Edificacao {

    private int qtdMoradores;
    private String tipoConstrucao;

    public Residencial(String responsavel, String rua, int numero, int qtdMoradores, String tipoConstrucao) {
        super(responsavel, rua, numero);
        setQtdMoradores(qtdMoradores);
        setTipoConstrucao(tipoConstrucao);
    }

    @Override
    public String toString() {
        String result = getTipoConstrucao() + " do(a) " + getResponsavel() + ", na " + getRua() + ", " + getNumero() + ", com " + getQtdMoradores() + " moradores\n"
                + "Total de lâmpadas: " + getTotalLampadas() + "\n"
                + "Potência final necessária: " + getPotenciaFinal() + "W\n"
                + "Cômodos:\n";
        for (Comodo comodo : getComodos()) {
            result += comodo.getCategoria().getNome() + " com " + comodo.getAreaCalculada() + "m²"
                    + " que necessita de " + comodo.getQtdMinLampadas() + " lâmpadas, totalizando "
                    + comodo.getPotenciaIluminacao() + "W (potência calculada: " + comodo.getPotenciaCalculada() + ").\n";
        }
        return result;
    }

    @Override
    public double getPotenciaFinal() {
        double result = getQtdMoradores() * 200;
        for (Comodo comodo : getComodos()) {
            result += comodo.getPotenciaIluminacao();
        }
        return result;
    }

    public int getQtdMoradores() {
        return qtdMoradores;
    }

    public void setQtdMoradores(int qtdMoradores) {
        if (qtdMoradores < 0) {
            throw new IllegalArgumentException("Quantidade de moradores inválido");
        }
        this.qtdMoradores = qtdMoradores;
    }

    public String getTipoConstrucao() {
        return tipoConstrucao;
    }

    public void setTipoConstrucao(String tipoConstrucao) {
        if (tipoConstrucao == null
                || tipoConstrucao.isEmpty()) {
            throw new IllegalArgumentException("Tipo de Construção inválida");
        }
        this.tipoConstrucao = tipoConstrucao;
    }
}

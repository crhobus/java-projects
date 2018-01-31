package Negocio;

public class Comercial extends Edificacao {

    private String ramoAtividade;

    public Comercial(String responsavel, String rua, int numero, String ramoAtividade) {
        super(responsavel, rua, numero);
        setRamoAtividade(ramoAtividade);
    }

    @Override
    public String toString() {
        String result = "A empresa " + getResponsavel() + " está na " + getRua() + ", " + getNumero() + ". Ramo: " + getRamoAtividade() + ".\n"
                + "Total de lâmpadas: " + getTotalLampadas() + "\n"
                + "Potência final necessária: " + getPotenciaFinal() + "W\n"
                + "Comodos:\n";
        for (Comodo comodo : getComodos()) {
            result += comodo.getCategoria().getNome() + " com " + comodo.getAreaCalculada() + "m²"
                    + " que necessita de " + comodo.getQtdMinLampadas() + " lâmpadas, totalizando "
                    + comodo.getPotenciaIluminacao() + "W (potência calculada: " + comodo.getPotenciaCalculada() + ").\n";
        }
        return result;
    }

    @Override
    public double getPotenciaFinal() {
        double result = 0;
        for (Comodo comodo : getComodos()) {
            result += comodo.getPotenciaIluminacao();
        }
        return result * 1.5;
    }

    public String getRamoAtividade() {
        return ramoAtividade;
    }

    public void setRamoAtividade(String ramoAtividade) {
        if (ramoAtividade == null
                || ramoAtividade.isEmpty()) {
            throw new IllegalArgumentException("Ramo de atividade inválida");
        }
        this.ramoAtividade = ramoAtividade;
    }
}

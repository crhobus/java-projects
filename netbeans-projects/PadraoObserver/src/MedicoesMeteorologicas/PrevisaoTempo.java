package MedicoesMeteorologicas;

public class PrevisaoTempo extends Sujeito {

    private int temperatura, umidade, pressao;

    public void setTempo(int temperatura, int pressao, int umidade) {
        this.temperatura = temperatura;
        this.pressao = pressao;
        this.umidade = umidade;
        notifica();
    }

    public int getTemperatura() {
        return temperatura;
    }

    public int getUmidade() {
        return umidade;
    }

    public int getPressao() {
        return pressao;
    }
}

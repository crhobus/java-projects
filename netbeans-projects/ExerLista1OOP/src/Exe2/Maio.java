package Exe2;

public class Maio {

    private final String[] semana = {"Domingo", "Segunda-Feira",
        "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira",
        "Sábado"};
    private final String feriado = "Dia 01 domingo - Dia do Trabalhor\nDia 9  domingo - Dia das mães";
    private String dia;

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String diaSemana() {
        if (dia.equalsIgnoreCase("Feriado")) {
            return feriado;
        }
        int dia = Integer.parseInt(this.dia);
        int cont = 7;
        for (int i = 0; i < 31; i++) {
            while (cont > 7) {
                cont -= 7;
            }
            if (dia - 1 == i) {
                return semana[cont - 1];
            }
            cont++;
        }
        return "Dia inválido";
    }
}

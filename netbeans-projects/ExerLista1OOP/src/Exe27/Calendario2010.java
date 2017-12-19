package Exe27;

public class Calendario2010 {

    private final String[] semana = {"Domingo", "Segunda-Feira",
        "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira",
        "Sábado"};
    private final String[] feriados = {"Dia 01 - Confraternização Universal",
        "Dia 16 - Carnaval", "Não tem feriados",
        "Dia 02 - Paixão\nDia 04 - Páscoa\nDia 21 - Tiradentes",
        "Dia 01 - Dia do Trabalhor", "Dia 03 - Corpus Christi",
        "Não tem feriados", "Não tem feriados",
        "Dia 07 - Proclamação da Independência",
        "Dia 12 - Nossa Senhora de Aparecida",
        "Dia 02 - Dia de Finados\nDia 15 - Proclamação da República",
        "Dia 25 - Natal"};
    private final int[] diaLimiteMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};// limite de cada mês
    private final int[] primDiaSemana = {6, 2, 2, 5, 7, 3, 5, 1, 4, 6, 2, 4};// dias que se iniciam a semana no mês
    private int mes, dia;

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getDiaSemana() {
        if (mes >= 1 && mes <= 12) {
            if (dia == 0) {
                return "Feriados:\n" + feriados[mes - 1];
            }
            int diaLimite = diaLimiteMes[mes - 1];
            if (dia >= 1 && dia <= diaLimite) {
                int cont = primDiaSemana[mes - 1];
                for (int i = 0; i < diaLimite; i++) {
                    while (cont > 7) {
                        cont -= 7;
                    }
                    if (dia - 1 == i) {
                        return "Dia da semana: " + semana[cont - 1];
                    }
                    cont++;
                }
            }
            return "Entre com um dia válido";
        }
        return "Entre com um mês válido";
    }
}

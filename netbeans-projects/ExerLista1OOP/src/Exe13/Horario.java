package Exe13;

public class Horario {

    public String getMensagem(int horario) {
        if (horario < 0 || horario > 23) {
            return "Horário inválido";
        }
        if (horario >= 0 && horario <= 6) {
            return "Boa Madrugada";
        }
        if (horario >= 7 && horario <= 11) {
            return "Bom dia";
        }
        if (horario >= 12 && horario <= 18) {
            return "Boa Tarde";
        }
        return "Boa noite";
    }
}

package Exe12;

public class Fusorario {

    public String getPaises() {
        return "Países:\nBrasil\nInglaterra\nAlemanha";
    }

    public String getFusorario(String paisOrigem, String paisDestino, int hora) {
        if (hora < 0 || hora > 23) {
            return "Horário inválido";
        }
        if (paisOrigem.equalsIgnoreCase("Brasil") && paisDestino.equalsIgnoreCase("Inglaterra")) {
            return getBrasilInglaterra(hora);
        }
        if (paisOrigem.equalsIgnoreCase("Brasil") && paisDestino.equalsIgnoreCase("Alemanha")) {
            return getBrasilAlemanha(hora);
        }
        if (paisOrigem.equalsIgnoreCase("Inglaterra") && paisDestino.equalsIgnoreCase("Brasil")) {
            return getInglaterraBrasil(hora);
        }
        if (paisOrigem.equalsIgnoreCase("Inglaterra") && paisDestino.equalsIgnoreCase("Alemanha")) {
            return getInglaterraAlemanha(hora);
        }
        if (paisOrigem.equalsIgnoreCase("Alemanha") && paisDestino.equalsIgnoreCase("Brasil")) {
            return getAlemanhaBrasil(hora);
        }
        if (paisOrigem.equalsIgnoreCase("Alemanha") && paisDestino.equalsIgnoreCase("Inglaterra")) {
            return getAlemanhaInglaterra(hora);
        }
        return "País de origem ou destino inválido";
    }

    private String getBrasilInglaterra(int hora) {
        hora += 3;
        if (hora > 23) {
            return "Horário na Inglaterra: " + (hora - 24) + " Horas";
        } else {
            return "Horário na Inglaterra: " + hora + " Horas";
        }
    }

    private String getBrasilAlemanha(int hora) {
        hora += 4;
        if (hora > 23) {
            return "Horário na Alemanha: " + (hora - 24) + " Horas";
        } else {
            return "Horário na Alemanha: " + hora + " Horas";
        }
    }

    private String getInglaterraBrasil(int hora) {
        hora -= 3;
        if (hora < 0) {
            return "Horário no Brasil: " + (hora + 24) + " Horas";
        } else {
            return "Horário no Brasil: " + hora + " Horas";
        }
    }

    private String getInglaterraAlemanha(int hora) {
        hora += 1;
        if (hora > 23) {
            return "Horário na Alemanha: " + (hora - 24) + " Horas";
        } else {
            return "Horário na Alemanha: " + hora + " Horas";
        }
    }

    private String getAlemanhaBrasil(int hora) {
        hora -= 4;
        if (hora < 0) {
            return "Horário no Brasil: " + (hora + 24) + " Horas";
        } else {
            return "Horário no Brasil: " + hora + " Horas";
        }
    }

    private String getAlemanhaInglaterra(int hora) {
        hora -= 1;
        if (hora < 0) {
            return "Horário na Inlaterra: " + (hora + 24) + " Horas";
        } else {
            return "Horário na Inglaterra: " + hora + " Horas";
        }
    }
}

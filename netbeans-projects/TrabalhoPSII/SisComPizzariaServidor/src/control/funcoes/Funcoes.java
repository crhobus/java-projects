package control.funcoes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Funcoes {

    public static String dateToString(Date data, int formato) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat formataData;
        switch (formato) {
            case 1:
                formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                return formataData.format(data);
            case 2:
                formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                return formataData.format(data);
            case 3:
                formataData = new SimpleDateFormat("dd/MM/yyyy");
                String d = formataData.format(data);
                System.out.println(d);
                return "";
        }
        return "";
    }

    public static Date stringToDate(String data, boolean addTime) throws ParseException {
        if (data == null
                || "__/__/____".equals(data)) {
            return null;
        }
        if (addTime) {
            data = data + " 00:00:00";
        }
        SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formataData.parse(data);
    }

    public static boolean validaData(String data) {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(2, 4));
        int ano = Integer.parseInt(data.substring(4, 8));
        if (dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12 && ano >= 1900 && ano < 2100) {
            return true;
        }
        return false;
    }

    public static String validaString(Object o) {
        return (o == null ? "" : o.toString());
    }
}
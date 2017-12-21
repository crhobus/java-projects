package Trabalho1;

import java.util.ArrayList;

public class PDFCorpo implements Corpo {

    public String corpo(ArrayList<String> linhas) {
        String ret = "BODY {\n";
        for (String linha : linhas) {
            ret += "        " + linha + "#br\n";
        }
        return ret + " }";
    }
}

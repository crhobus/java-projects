package Trabalho1;

import java.util.ArrayList;

public class HTMLCorpo implements Corpo {

    public String corpo(ArrayList<String> linhas) {

        String ret = "";
        for (String linha : linhas) {
            ret += "        <tr><td>" + linha + "</td></tr>\n";
        }
        return ret;
    }
}

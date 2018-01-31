package control.funcoes;

import java.util.HashMap;

public class Dados {

    private HashMap<String, String> dados;

    public Dados() {
        this.dados = new HashMap<>();
    }

    public void addInfo(String dsChave, String info) {
        this.dados.put(dsChave, info);
    }

    public String getInfo(String dsChave) {
        return dados.get(dsChave);
    }

    public HashMap<String, String> getDados() {
        return dados;
    }
}

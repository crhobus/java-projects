package Exe1;

import java.util.ArrayList;
import java.util.List;

public class Cadastro {

    private List<Pessoa> lista = new ArrayList<Pessoa>();
    private final String cargos = "1 - Gerente\n2 - Atendente\n3 - Açougueiro\n4 - Secretária"
            + "\n5 - Almoxarife\n6 - Padeiro";
    private int masculino, feminino, gerente, atendente, acougueiro,
            secretaria, almoxarife, padeiro;

    public void addPessoa(Pessoa pessoa) {
        if (pessoa.getSexo().equalsIgnoreCase("M")) {
            masculino++;
        } else {
            feminino++;
        }
        switch (pessoa.getCargo()) {
            case 1:
                gerente++;
                break;
            case 2:
                atendente++;
                break;
            case 3:
                acougueiro++;
                break;
            case 4:
                secretaria++;
                break;
            case 5:
                almoxarife++;
                break;
            case 6:
                padeiro++;
                break;
        }
        lista.add(pessoa);
    }

    public String getCargos() {
        return cargos;
    }

    public int getMasculino() {
        return masculino;
    }

    public int getFeminino() {
        return feminino;
    }

    public int getGerente() {
        return gerente;
    }

    public int getAtendente() {
        return atendente;
    }

    public int getAcougueiro() {
        return acougueiro;
    }

    public int getSecretaria() {
        return secretaria;
    }

    public int getAlmoxarife() {
        return almoxarife;
    }

    public int getPadeiro() {
        return padeiro;
    }
}

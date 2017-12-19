package Exe5;

public class Controle {

    private Estado[] estados;
    private Cidade[] cidadeEscolhida;
    private int cont;

    public Controle(int qtdadeEstados) {
        estados = new Estado[qtdadeEstados];
    }

    public void addEstado(Estado estado) throws Exception {
        if (cont < estados.length) {
            estados[cont] = estado;
            cont++;
        } else {
            throw new Exception("Limite de estados esgotado");
        }
    }

    public String getEstados() {
        if (cont == 0) {
            return "Não há estados cadastrados";
        }
        String est = "Estados:";
        for (Estado estado : estados) {
            est += "\n" + estado.getNome();
        }
        return est;
    }

    public String getCidades(String estado) {
        for (Estado est : estados) {
            if (est.getNome().equalsIgnoreCase(estado)) {
                Cidade[] cidades = est.getCidades();
                String cid = "Cidades:";
                for (Cidade cidade : cidades) {
                    cid += "\n" + cidade.getNome();
                }
                cidadeEscolhida = cidades;
                return cid;
            }
        }
        return "Estado não encontrado";
    }

    public String getDados(String cidade) {
        for (Cidade cid : cidadeEscolhida) {
            if (cid.getNome().equalsIgnoreCase(cidade)) {
                String dados = "Dados:";
                dados += cid.getDados().getPopulacao() + "\n";
                dados += cid.getDados().getFesta() + "\n";
                dados += cid.getDados().getIdh() + "\n";
                return dados;
            }
        }
        return "Cidade não encontrada";
    }
}

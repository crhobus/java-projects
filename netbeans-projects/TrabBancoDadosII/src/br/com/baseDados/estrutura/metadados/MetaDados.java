package br.com.baseDados.estrutura.metadados;

import br.com.analizador.SemanticError;
import br.com.baseDados.BancoDadosDAO;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class MetaDados {

    private HashMap<String, Tabela> metaDados;
    private BancoDadosDAO bancoDadosDAO;

    public MetaDados(BancoDadosDAO bancoDadosDAO) throws Exception {
        this.bancoDadosDAO = bancoDadosDAO;
        metaDados = bancoDadosDAO.getMetaDados();
    }

    public void addMetaDados(Tabela tabela) throws SemanticError {
        metaDados.put(tabela.getCabecalhoTabela().getNmTabela().toLowerCase(), tabela);
        bancoDadosDAO.gravarMetaDados(tabela);
    }

    public Tabela getTabela(String nmTabela) {
        return metaDados.get(nmTabela.toLowerCase());
    }

    public int getCdTabela(String nmTabela) {
        Tabela tabela = metaDados.get(nmTabela.toLowerCase());
        if (tabela != null) {
            return tabela.getCabecalhoTabela().getCdTabela();
        }
        return 0;
    }

    public int getProximoCodigoTabela() {
        if (metaDados.isEmpty()) {
            return 1;
        }
        SortedSet<Integer> cdsTabelaOrdenados = new TreeSet<Integer>();
        for (Tabela tabela : metaDados.values()) {
            cdsTabelaOrdenados.add(tabela.getCabecalhoTabela().getCdTabela());
        }
        return cdsTabelaOrdenados.last() + 1;

    }
}

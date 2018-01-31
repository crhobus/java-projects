package br.com.baseDados.estrutura.dados;

import br.com.analizador.SemanticError;
import br.com.baseDados.BancoDadosDAO;
import java.util.ArrayList;
import java.util.HashMap;

public class IndiceDadosMemoria {

    private HashMap<Long, IndiceDados> indiceDadosMemoria;
    private BancoDadosDAO bancoDadosDAO;

    public IndiceDadosMemoria(BancoDadosDAO bancoDadosDAO) throws Exception {
        this.bancoDadosDAO = bancoDadosDAO;
        indiceDadosMemoria = bancoDadosDAO.getIndiceDados();
    }

    public void addIndiceDados(long posicao, IndiceDados indiceDados) throws SemanticError {
        long pos = bancoDadosDAO.gravarIndiceDados(posicao, indiceDados);
        indiceDadosMemoria.put(pos, indiceDados);
    }

    public void novaIndexacao(ArrayList<IndiceDados> novaIndexacaoInfo) throws SemanticError {
        indiceDadosMemoria = bancoDadosDAO.gravarNovaIndexacao(novaIndexacaoInfo);
    }

    public HashMap<Long, IndiceDados> getIndiceDadosMemoria() {
        return indiceDadosMemoria;
    }

    /*public HashMap<Long, IndiceDados> getIndiceDadosMemoria(int cdTabela) {
    HashMap<Long, IndiceDados> idxDadosMemoria = new HashMap<Long, IndiceDados>();
    Set<Long> posicoes = indiceDadosMemoria.keySet();
    IndiceDados indiceDados;
    for (Long posicao : posicoes) {
    indiceDados = indiceDadosMemoria.get(posicao);
    if (indiceDados.getCdTabela() == cdTabela && indiceDados.getRegistroAtivo() == 1) {
    idxDadosMemoria.put(posicao, indiceDados);
    }
    }
    return idxDadosMemoria;
    }*/
    public ArrayList<Registro> getDados(int cdTabela) throws SemanticError {
        return bancoDadosDAO.getDados(indiceDadosMemoria, cdTabela);
    }
}

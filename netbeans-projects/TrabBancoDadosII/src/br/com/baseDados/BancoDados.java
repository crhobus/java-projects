package br.com.baseDados;

import br.com.analizador.SemanticError;
import br.com.baseDados.estrutura.dados.IndiceDados;
import br.com.baseDados.estrutura.dados.IndiceDadosMemoria;
import br.com.baseDados.estrutura.dados.Registro;
import br.com.baseDados.estrutura.metadados.CabecalhoTabela;
import br.com.baseDados.estrutura.metadados.Coluna;
import br.com.baseDados.estrutura.metadados.Indice;
import br.com.baseDados.estrutura.metadados.MetaDados;
import br.com.baseDados.estrutura.metadados.Tabela;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class BancoDados {

    private MetaDados metaDados;
    private BancoDadosDAO bancoDadosDAO;
    private HashMap<String, Object> montaEstruturaComandos;
    private int qtColunas, qtConstraints, qtIndices, qtColunasIndice;
    private IndiceDadosMemoria indiceDadosMemoria;

    public BancoDados() {
        try {
            bancoDadosDAO = new BancoDadosDAO();
            metaDados = new MetaDados(bancoDadosDAO);
            indiceDadosMemoria = new IndiceDadosMemoria(bancoDadosDAO);
            montaEstruturaComandos = new HashMap<String, Object>();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        }
    }

    public void addMetaDados() throws SemanticError {
        Tabela tabela = new Tabela();

        //CODIGO_TABELA;NOME_TABELA;NUM_COLUNAS;NUM_CONSTRAINTS;NUM_INDICES
        CabecalhoTabela cabecalhoTabela = new CabecalhoTabela();
        cabecalhoTabela.setCdTabela((Integer) montaEstruturaComandos.get("CD_TABELA"));
        cabecalhoTabela.setNmTabela((String) montaEstruturaComandos.get("NM_TABELA"));
        cabecalhoTabela.setQtColunas(qtColunas);
        cabecalhoTabela.setQtConstraints(qtConstraints);
        cabecalhoTabela.setQtIndices(qtIndices);

        tabela.setCabecalhoTabela(cabecalhoTabela);

        if (qtColunas == 0) {
            throw new SemanticError("tabela sem colunas, a tabela deve conter no mínimo uma coluna");
        }
        if (qtColunas > 100) {
            throw new SemanticError("limite máximo de colunas excedido, o banco suporta no máximo 100 colunas");
        }

        //CODIGO_COLUNA;NOME_COLUNA;TIPO;TAMANHO;PRECISAO;NOT_NULL
        Coluna[] colunas = new Coluna[qtColunas];
        Coluna coluna;
        for (int i = 0; i < qtColunas; i++) {
            coluna = new Coluna();
            coluna.setCdColuna((Integer) montaEstruturaComandos.get("CD_COLUNA" + (i + 1)));
            coluna.setNmColuna((String) montaEstruturaComandos.get("NM_COLUNA" + (i + 1)));
            coluna.setTipo((Integer) montaEstruturaComandos.get("TIPO" + (i + 1)));
            if (montaEstruturaComandos.get("TAMANHO" + (i + 1)) != null) {
                coluna.setTamanho((Integer) montaEstruturaComandos.get("TAMANHO" + (i + 1)));
            }
            if (montaEstruturaComandos.get("PRECISAO" + (i + 1)) != null) {
                coluna.setPrecisao((Integer) montaEstruturaComandos.get("PRECISAO" + (i + 1)));
            }
            coluna.setNotNull("S".equalsIgnoreCase((String) montaEstruturaComandos.get("NOT_NULL" + (i + 1))));
            colunas[i] = coluna;
        }
        tabela.setColunas(colunas);

        if (qtIndices > 0) {
            //CODIGO_INDICE;NOME_INDICE;NUM_COLUNAS;COLUNAS
            Indice[] indices = new Indice[qtIndices];
            Indice indice;
            for (int i = 0; i < qtIndices; i++) {
                indice = new Indice();
                indice.setCdIndice((Integer) montaEstruturaComandos.get("CD_INDICE" + (i + 1)));
                indice.setNmIndice((String) montaEstruturaComandos.get("NM_INDICE" + (i + 1)));
                indice.setNrColunas(qtColunasIndice);
                int[] cdColunaIndiceTabela = new int[qtColunasIndice];
                for (int y = 0; y < cdColunaIndiceTabela.length; y++) {
                    cdColunaIndiceTabela[y] = (Integer) montaEstruturaComandos.get("CD_COLUNA_PRIMARY_KEY" + (y + 1));
                }
                indice.setCdColunaIndiceTabela(cdColunaIndiceTabela);
                indices[i] = indice;
            }
            tabela.setIndices(indices);
        }
        metaDados.addMetaDados(tabela);
    }

    public void addComandoInsert() throws SemanticError {
        //segmento(3) - tabela(5) - nº colunas(3) - tam(5) - valor(0 - 99999)
        Registro registro = new Registro();
        registro.setSegmento("000");
        registro.setCdTabela((String) montaEstruturaComandos.get("CD_TABELA"));
        registro.setQtColunas((String) montaEstruturaComandos.get("NR_COLUNAS"));
        int nrColunas = Integer.parseInt((String) montaEstruturaComandos.get("NR_COLUNAS"));
        String[] tamanho = new String[nrColunas];
        String[] informacao = new String[nrColunas];
        for (int i = 0; i < nrColunas; i++) {
            tamanho[i] = (String) montaEstruturaComandos.get("TAMANHO" + (i + 1));
            informacao[i] = (String) montaEstruturaComandos.get("VALOR" + (i + 1));
        }
        registro.setTamanho(tamanho);
        registro.setInformacao(informacao);
        long posicao = bancoDadosDAO.gravarDados(registro);
        IndiceDados indiceDados = new IndiceDados();
        indiceDados.setCdTabela(Integer.parseInt((String) montaEstruturaComandos.get("CD_TABELA")));
        indiceDados.setPosicaoDado(posicao);
        indiceDados.setRegistroAtivo(1);//1 indica que o arquivo está ativo ou seja não deletado
        indiceDadosMemoria.addIndiceDados(0, indiceDados);//indica que é para gravar no final do arquivo de indices
    }

    public String addComandoDelete() throws SemanticError {
        int qtDeletados = 0;
        int cdTabela = Integer.parseInt((String) montaEstruturaComandos.get("CD_TABELA"));
        HashMap<Long, IndiceDados> idxDadosMemoria = indiceDadosMemoria.getIndiceDadosMemoria();
        Set<Long> posicoes = idxDadosMemoria.keySet();
        IndiceDados indiceDados;
        for (long posicao : posicoes) {
            indiceDados = idxDadosMemoria.get(posicao);
            if (indiceDados.getCdTabela() == cdTabela && indiceDados.getRegistroAtivo() == 1) {
                indiceDados.setRegistroAtivo(0);//0 indica que o arquivo foi deletado
                indiceDadosMemoria.addIndiceDados(posicao + 1, indiceDados);//posição + 1 é para uma validação no método gravarIndiceDados(long, IndiceDados) 
                qtDeletados++;
            }
        }
        return "Foram deletados " + qtDeletados + " linhas";
    }

    public void addComandoUpdate() throws SemanticError {
        int cdTabela = Integer.parseInt((String) montaEstruturaComandos.get("CD_TABELA"));
        ArrayList<Registro> registrosAtuais = indiceDadosMemoria.getDados(cdTabela);
        if (registrosAtuais.isEmpty()) {
            throw new SemanticError("não há registros na tabela");
        }
        ArrayList<Registro> registrosNovos = new ArrayList<Registro>();
        Registro registroNovo;
        int nrColunas;
        String[] tamanhoAtual;
        String[] informacaoAtual;
        String[] tamanhoNovo;
        String[] informacaoNovo;
        for (Registro registroAtual : registrosAtuais) {
            registroNovo = new Registro();
            registroNovo.setSegmento(registroAtual.getSegmento());
            registroNovo.setCdTabela(registroAtual.getCdTabela());
            registroNovo.setQtColunas(registroAtual.getQtColunas());
            nrColunas = Integer.parseInt(registroNovo.getQtColunas());
            tamanhoAtual = registroAtual.getTamanho();
            informacaoAtual = registroAtual.getInformacao();
            tamanhoNovo = new String[nrColunas];
            informacaoNovo = new String[nrColunas];
            for (int i = 0; i < nrColunas; i++) {
                if ("Não Informado".equalsIgnoreCase((String) montaEstruturaComandos.get("VALOR" + (i + 1)))) {
                    tamanhoNovo[i] = tamanhoAtual[i];
                    informacaoNovo[i] = informacaoAtual[i];
                } else {
                    tamanhoNovo[i] = (String) montaEstruturaComandos.get("TAMANHO" + (i + 1));
                    informacaoNovo[i] = (String) montaEstruturaComandos.get("VALOR" + (i + 1));
                }
            }
            registroNovo.setTamanho(tamanhoNovo);
            registroNovo.setInformacao(informacaoNovo);
            registrosNovos.add(registroNovo);
        }

        ArrayList<IndiceDados> novaIndexacaoInfo = new ArrayList<IndiceDados>();

        ArrayList<Long> posicoesNovas = bancoDadosDAO.gravarListaDados(registrosNovos);

        HashMap<Long, IndiceDados> idxDadosMemoriaAtual = indiceDadosMemoria.getIndiceDadosMemoria();
        Set<Long> posicoesAtuais = idxDadosMemoriaAtual.keySet();

        IndiceDados indiceDados;
        int cont = -1;
        for (long posicao : posicoesAtuais) {
            indiceDados = idxDadosMemoriaAtual.get(posicao);
            if (indiceDados.getCdTabela() == cdTabela && indiceDados.getRegistroAtivo() == 1) {
                cont++;
                indiceDados.setPosicaoDado(posicoesNovas.get(cont));
            }
            novaIndexacaoInfo.add(indiceDados);
        }
        indiceDadosMemoria.novaIndexacao(novaIndexacaoInfo);
    }

    public boolean validaTipoColuna(int tipoColuna, String info, String nmColuna) throws SemanticError {
        switch (tipoColuna) {
            case 0://CHAR ou CHARACTER
                return info.startsWith("'") && info.endsWith("'");
            case 1://VARCHAR ou VARCHAR2
                return info.startsWith("'") && info.endsWith("'");
            case 2://NUMBER
                return validaNumberNumeric(info, nmColuna);
            case 3://NUMERIC
                return validaNumberNumeric(info, nmColuna);
            case 4:// INT ou INTEGER
                try {
                    Integer.parseInt(info);
                    return true;
                } catch (Exception ex) {
                    throw new SemanticError("o valor informado na coluna '" + nmColuna + "' não corresponde com o tipo da coluna");
                }
            case 5://SMALLINT
                try {
                    Double.parseDouble(info);
                    return true;
                } catch (Exception ex) {
                    throw new SemanticError("o valor informado na coluna '" + nmColuna + "'  não corresponde com o tipo da coluna");
                }
            case 6://DATE
                return validaData(info, nmColuna);
            case 7://TIMESTAMP ou TIME
                return validaData(info, nmColuna);
        }
        return false;
    }

    private boolean validaData(String info, String nmColuna) throws SemanticError {
        if (info.startsWith("'") && info.endsWith("'")) {
            info = info.substring(1, info.length() - 1);
            try {
                SimpleDateFormat format;
                if (info.length() == 10) {
                    format = new SimpleDateFormat("dd/MM/yyyy");
                    format.parse(info);
                    return true;
                } else if (info.length() == 8) {
                    format = new SimpleDateFormat("hh:mm:ss");
                    format.parse(info);
                    return true;
                } else if (info.length() == 5) {
                    format = new SimpleDateFormat("hh:mm");
                    format.parse(info);
                    return true;
                } else if (info.length() == 19) {
                    format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                    format.parse(info);
                    return true;
                }
                return false;
            } catch (Exception ex) {
                throw new SemanticError("o valor informado na coluna '" + nmColuna + "'  não corresponde com o tipo da coluna");
            }
        }
        throw new SemanticError("o valor informado na coluna '" + nmColuna + "'  não corresponde com o tipo da coluna");
    }

    private boolean validaNumberNumeric(String info, String nmColuna) throws SemanticError {
        try {
            if (info.contains(".")) {
                Double.parseDouble(info);
            } else {
                Long.parseLong(info);
            }
            return true;
        } catch (Exception ex) {
            throw new SemanticError("o valor informado na coluna '" + nmColuna + "'  não corresponde com o tipo da coluna");
        }
    }

    public Tabela getTabela(String nmTabela) {
        return metaDados.getTabela(nmTabela);
    }

    public int getCdTabela(String nmTabela) {
        return metaDados.getCdTabela(nmTabela);
    }

    public void addEstruturaComandos(String chave, Object valor) {
        montaEstruturaComandos.put(chave, valor);
    }

    public int getProximoCodigoTabela() {
        return metaDados.getProximoCodigoTabela();
    }

    public void limparEstruturaComandos() {
        montaEstruturaComandos.clear();
    }

    public boolean existeIdentificador(String nmIdentificador) {
        for (Object obj : montaEstruturaComandos.values()) {
            if (nmIdentificador.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public Object getValor(String chave) {
        return montaEstruturaComandos.get(chave);
    }

    public String getChave(Object obj) throws SemanticError {
        Object o;
        Set<String> chaves = montaEstruturaComandos.keySet();
        for (String chave : chaves) {
            o = montaEstruturaComandos.get(chave);
            if (obj.equals(o)) {
                return chave;
            }
        }
        throw new SemanticError("coluna inexistente");
    }

    public void setQtColunas(int qtColunas) {
        this.qtColunas = qtColunas;
    }

    public void setQtConstraints(int qtConstraints) {
        this.qtConstraints = qtConstraints;
    }

    public void setQtIndices(int qtIndices) {
        this.qtIndices = qtIndices;
    }

    public void setQtColunasIndice(int qtColunasIndice) {
        this.qtColunasIndice = qtColunasIndice;
    }
}

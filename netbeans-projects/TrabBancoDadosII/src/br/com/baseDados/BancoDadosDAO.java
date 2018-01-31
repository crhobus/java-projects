package br.com.baseDados;

import br.com.analizador.SemanticError;
import br.com.baseDados.estrutura.dados.IndiceDados;
import br.com.baseDados.estrutura.dados.Registro;
import br.com.baseDados.estrutura.metadados.CabecalhoTabela;
import br.com.baseDados.estrutura.metadados.Indice;
import br.com.baseDados.estrutura.metadados.Constraint;
import br.com.baseDados.estrutura.metadados.Coluna;
import br.com.baseDados.estrutura.metadados.Tabela;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BancoDadosDAO {

    public void gravarMetaDados(Tabela tabela) throws SemanticError {
        try {
            File diretorio = new File("Data base");
            boolean result = diretorio.mkdir();
            if (result || diretorio.exists()) {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(diretorio, "metadados.db"), true)));
                writer.println(tabela.getCabecalhoTabela().getCdTabela() + ";"
                        + tabela.getCabecalhoTabela().getNmTabela() + ";"
                        + tabela.getCabecalhoTabela().getQtColunas() + ";"
                        + tabela.getCabecalhoTabela().getQtConstraints() + ";"
                        + tabela.getCabecalhoTabela().getQtIndices());
                for (Coluna coluna : tabela.getColunas()) {
                    writer.println(coluna.getCdColuna() + ";"
                            + coluna.getNmColuna() + ";"
                            + coluna.getTipo() + ";"
                            + coluna.getTamanho() + ";"
                            + coluna.getPrecisao() + ";"
                            + (coluna.isNotNull() ? "S" : "N"));
                }
                String linha;
                if (tabela.getConstraints() != null) {
                    for (Constraint constraint : tabela.getConstraints()) {
                        linha = constraint.getCdConstraint() + ";"
                                + constraint.getNmConstraint() + ";"
                                + constraint.getTipo() + ";"
                                + constraint.getNrColunas() + ";";
                        for (int cdColunaConstraintTabela : constraint.getCdColunaConstraintTabela()) {
                            linha += cdColunaConstraintTabela + ";";
                        }
                        linha += constraint.getTabelaReferenciada().getCdTabela() + ";";
                        Coluna[] colunasReferenciada = constraint.getColunasReferenciada();
                        for (int i = 0; i < colunasReferenciada.length; i++) {
                            linha += (i == colunasReferenciada.length - 1 ? colunasReferenciada[i].getCdColuna() : colunasReferenciada[i].getCdColuna() + ";");
                        }
                        writer.println(linha);
                    }
                }
                if (tabela.getIndices() != null) {
                    for (Indice indice : tabela.getIndices()) {
                        linha = indice.getCdIndice() + ";"
                                + indice.getNmIndice() + ";"
                                + indice.getNrColunas() + ";";
                        int[] cdColunaIndiceTabela = indice.getCdColunaIndiceTabela();
                        for (int i = 0; i < cdColunaIndiceTabela.length; i++) {
                            linha += (i == cdColunaIndiceTabela.length - 1 ? cdColunaIndiceTabela[i] : cdColunaIndiceTabela[i] + ";");
                        }
                        writer.println(linha);
                    }
                }
                writer.close();
            }
        } catch (Exception ex) {
            throw new SemanticError("Erro ao criar ou salvar o arquivo metadados");
        }
    }

    public HashMap<String, Tabela> getMetaDados() throws Exception {
        HashMap<String, Tabela> metaDados = new HashMap<String, Tabela>();
        File arquivo = new File("Data base/metadados.db");
        if (arquivo.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                String linha;
                String[] informacoesLinha;
                Tabela tabela = null;
                CabecalhoTabela cabecalhoTabela = null;
                Coluna[] colunas = null;
                Constraint[] constraints = null;
                Indice[] indices = null;
                do {
                    linha = reader.readLine();
                    if (linha != null && !"".equals(linha)) {
                        informacoesLinha = linha.split(";");
                        if (cabecalhoTabela == null) {
                            cabecalhoTabela = getCabecalhoTabela(informacoesLinha);
                        }
                        if (colunas == null) {
                            colunas = getColunas(cabecalhoTabela.getQtColunas(), reader);
                        }
                        if (constraints == null) {
                            constraints = getConstraints(cabecalhoTabela.getQtConstraints(), reader);
                        }
                        if (indices == null) {
                            indices = getIndices(cabecalhoTabela.getQtIndices(), reader);
                        }
                        tabela = new Tabela();
                        tabela.setCabecalhoTabela(cabecalhoTabela);
                        tabela.setColunas(colunas);
                        tabela.setConstraints(constraints);
                        tabela.setIndices(indices);
                        metaDados.put(cabecalhoTabela.getNmTabela(), tabela);
                        cabecalhoTabela = null;
                        colunas = null;
                        constraints = null;
                        indices = null;
                    }
                } while (linha != null && !"".equals(linha));
                reader.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do metadados");
            }
        }
        return metaDados;
    }

    private CabecalhoTabela getCabecalhoTabela(String[] informacoesLinha) {
        CabecalhoTabela cabecalhoTabela = new CabecalhoTabela();
        cabecalhoTabela.setCdTabela(Integer.parseInt(informacoesLinha[0]));
        cabecalhoTabela.setNmTabela(informacoesLinha[1].toLowerCase());
        cabecalhoTabela.setQtColunas(Integer.parseInt(informacoesLinha[2]));
        cabecalhoTabela.setQtConstraints(Integer.parseInt(informacoesLinha[3]));
        cabecalhoTabela.setQtIndices(Integer.parseInt(informacoesLinha[4]));
        return cabecalhoTabela;
    }

    private Coluna[] getColunas(int qtColunas, BufferedReader reader) throws Exception {
        Coluna[] colunas = new Coluna[qtColunas];
        Coluna coluna;
        String[] informacoesLinha;
        for (int i = 0; i < qtColunas; i++) {
            informacoesLinha = reader.readLine().split(";");
            coluna = new Coluna();
            coluna.setCdColuna(Integer.parseInt(informacoesLinha[0]));
            coluna.setNmColuna(informacoesLinha[1]);
            coluna.setTipo(Integer.parseInt(informacoesLinha[2]));
            coluna.setTamanho(Integer.parseInt(informacoesLinha[3]));
            coluna.setPrecisao(Integer.parseInt(informacoesLinha[4]));
            coluna.setNotNull(informacoesLinha[5].equalsIgnoreCase("S") ? true : false);
            colunas[i] = coluna;
        }
        return colunas;
    }

    private Constraint[] getConstraints(int qtConstraints, BufferedReader reader) throws Exception {
        Constraint[] constraints = new Constraint[qtConstraints];
        Constraint constraint;
        int[] cdColunaConstraintTabela;
        CabecalhoTabela tabelaRef;
        Coluna[] colunasRef;
        Coluna colunaRef;
        String[] informacoesLinha;
        int cont;
        for (int i = 0; i < qtConstraints; i++) {
            informacoesLinha = reader.readLine().split(";");
            constraint = new Constraint();
            constraint.setCdConstraint(Integer.parseInt(informacoesLinha[0]));
            constraint.setNmConstraint(informacoesLinha[1]);
            constraint.setTipo(Integer.parseInt(informacoesLinha[2]));
            constraint.setNrColunas(Integer.parseInt(informacoesLinha[3]));
            cdColunaConstraintTabela = new int[constraint.getNrColunas()];
            cont = 4;
            for (int y = 0; y < cdColunaConstraintTabela.length; y++) {
                cdColunaConstraintTabela[y] = Integer.parseInt(informacoesLinha[cont]);
                cont++;
            }
            constraint.setCdColunaConstraintTabela(cdColunaConstraintTabela);
            tabelaRef = new CabecalhoTabela();
            tabelaRef.setCdTabela(Integer.parseInt(informacoesLinha[cont]));
            constraint.setTabelaReferenciada(tabelaRef);
            colunasRef = new Coluna[constraint.getNrColunas()];
            cont++;
            for (int y = 0; y < constraint.getNrColunas(); y++) {
                colunaRef = new Coluna();
                colunaRef.setCdColuna(Integer.parseInt(informacoesLinha[cont]));
                colunasRef[y] = colunaRef;
                cont++;
            }
            constraint.setColunasReferenciada(colunasRef);
            constraints[i] = constraint;
        }
        return constraints;
    }

    private Indice[] getIndices(int qtIndices, BufferedReader reader) throws Exception {
        Indice[] indices = new Indice[qtIndices];
        Indice indice;
        String[] informacoesLinha;
        int[] cdColunaIndiceTabela;
        for (int i = 0; i < qtIndices; i++) {
            informacoesLinha = reader.readLine().split(";");
            indice = new Indice();
            indice.setCdIndice(Integer.parseInt(informacoesLinha[0]));
            indice.setNmIndice(informacoesLinha[1]);
            indice.setNrColunas(Integer.parseInt(informacoesLinha[2]));
            cdColunaIndiceTabela = new int[qtIndices];
            for (int y = 0; y < qtIndices; y++) {
                cdColunaIndiceTabela[y] = Integer.parseInt(informacoesLinha[3 + y]);
            }
            indice.setCdColunaIndiceTabela(cdColunaIndiceTabela);
            indices[i] = indice;
        }
        return indices;
    }

    public long gravarDados(Registro registro) throws SemanticError {
        try {
            long posicaoRegistroGravado = -1;
            File diretorio = new File("Data base");
            boolean result = diretorio.mkdir();
            if (result || diretorio.exists()) {
                RandomAccessFile arquivo = new RandomAccessFile(new File(diretorio, "dados.data"), "rw");
                posicaoRegistroGravado = gravarArqDadosAux(arquivo, registro);
                arquivo.close();
            }
            return posicaoRegistroGravado;
        } catch (Exception ex) {
            throw new SemanticError("Erro ao criar ou salvar o arquivo de dados");
        }
    }

    public ArrayList<Long> gravarListaDados(ArrayList<Registro> registros) throws SemanticError {
        try {
            ArrayList<Long> listaIndiceDados = new ArrayList<Long>();
            File diretorio = new File("Data base");
            boolean result = diretorio.mkdir();
            if (result || diretorio.exists()) {
                RandomAccessFile arquivo = new RandomAccessFile(new File(diretorio, "dados.data"), "rw");
                for (Registro registro : registros) {
                    listaIndiceDados.add(gravarArqDadosAux(arquivo, registro));
                }
                arquivo.close();
            }
            return listaIndiceDados;
        } catch (Exception ex) {
            throw new SemanticError("Erro ao criar ou salvar o arquivo de dados");
        }
    }

    private long gravarArqDadosAux(RandomAccessFile arquivo, Registro registro) throws Exception {
        long posicaoRegistroGravado = arquivo.length();
        arquivo.seek(posicaoRegistroGravado);
        String str = registro.getSegmento()
                + registro.getCdTabela()
                + registro.getQtColunas();
        int qtColunas = Integer.parseInt(registro.getQtColunas());
        for (int i = 0; i < qtColunas; i++) {
            str += registro.getTamanho()[i];
            str += registro.getInformacao()[i];
        }
        arquivo.writeBytes(str);
        return posicaoRegistroGravado;
    }

    public ArrayList<Registro> getDados(HashMap<Long, IndiceDados> indiceDadosMemoria, int cdTabela) throws SemanticError {
        ArrayList<Registro> registros = new ArrayList<Registro>();
        File diretorio = new File("Data base/dados.data");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arquivo = new RandomAccessFile(diretorio, "rw");
                Registro registro;
                byte[] bytes;
                int qtColunas, tamanhoInfo;
                String[] tamanho, informacao;
                for (IndiceDados indiceDados : indiceDadosMemoria.values()) {
                    if (cdTabela == indiceDados.getCdTabela() && indiceDados.getRegistroAtivo() == 1) {
                        registro = new Registro();
                        arquivo.seek(indiceDados.getPosicaoDado());

                        bytes = new byte[3];
                        arquivo.read(bytes);
                        registro.setSegmento(new String(Arrays.copyOf(bytes, 3)));


                        bytes = new byte[5];
                        arquivo.read(bytes);
                        registro.setCdTabela(new String(Arrays.copyOf(bytes, 5)));

                        bytes = new byte[3];
                        arquivo.read(bytes);
                        registro.setQtColunas(new String(Arrays.copyOf(bytes, 3)));

                        qtColunas = Integer.parseInt(registro.getQtColunas());
                        tamanho = new String[qtColunas];
                        informacao = new String[qtColunas];
                        for (int i = 0; i < qtColunas; i++) {

                            bytes = new byte[5];
                            arquivo.read(bytes);
                            tamanho[i] = new String(Arrays.copyOf(bytes, 5));

                            tamanhoInfo = Integer.parseInt(tamanho[i]);
                            bytes = new byte[tamanhoInfo];
                            arquivo.read(bytes);
                            informacao[i] = new String(Arrays.copyOf(bytes, tamanhoInfo));
                        }
                        registro.setTamanho(tamanho);
                        registro.setInformacao(informacao);
                        registros.add(registro);
                    }
                }
                arquivo.close();
            } catch (Exception ex) {
                throw new SemanticError("Erro na leitura do arquivo de dados");
            }
        }
        return registros;
    }

    public long gravarIndiceDados(long posicao, IndiceDados indiceDados) throws SemanticError {
        try {
            File diretorio = new File("Data base");
            boolean result = diretorio.mkdir();
            if (result || diretorio.exists()) {
                RandomAccessFile arquivo = new RandomAccessFile(new File(diretorio, "indices.idx"), "rw");
                if (posicao != 0) {
                    posicao--;//decrementa 1 pois se indicar 0 significa que é para gravar no final do arquivo, se 1 significa que é para sobrescrever na posição 0 
                    arquivo.seek(posicao + 12);
                    arquivo.writeInt(indiceDados.getRegistroAtivo());
                } else {
                    posicao = arquivo.length();
                    arquivo.seek(posicao);
                    arquivo.writeInt(indiceDados.getCdTabela());
                    arquivo.writeLong(indiceDados.getPosicaoDado());
                    arquivo.writeInt(indiceDados.getRegistroAtivo());
                }
                arquivo.close();
            }
        } catch (Exception ex) {
            throw new SemanticError("Erro ao criar ou salvar o arquivo de indices");
        }
        return posicao;
    }

    public HashMap<Long, IndiceDados> gravarNovaIndexacao(ArrayList<IndiceDados> novaIndexacaoInfo) throws SemanticError {
        HashMap<Long, IndiceDados> novaIndexacao = new HashMap<Long, IndiceDados>();
        try {
            File arq = new File("Data base/indices.idx");
            if (arq.exists()) {
                arq.delete();
                RandomAccessFile arquivo = new RandomAccessFile(arq, "rw");
                long posicao;
                for (IndiceDados indiceDados : novaIndexacaoInfo) {
                    posicao = arquivo.length();
                    arquivo.seek(posicao);
                    arquivo.writeInt(indiceDados.getCdTabela());
                    arquivo.writeLong(indiceDados.getPosicaoDado());
                    arquivo.writeInt(indiceDados.getRegistroAtivo());
                    novaIndexacao.put(posicao, indiceDados);
                }
                arquivo.close();
            }
        } catch (Exception ex) {
            throw new SemanticError("Erro na reindexação do arquivo de indices");
        }
        return novaIndexacao;
    }

    public HashMap<Long, IndiceDados> getIndiceDados() throws Exception {
        HashMap<Long, IndiceDados> indiceDados = new HashMap<Long, IndiceDados>();
        File diretorio = new File("Data base/indices.idx");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arquivo = new RandomAccessFile(diretorio, "rw");
                IndiceDados idxDados;
                long posicao;
                for (int i = 0; i < arquivo.length(); i++) {
                    posicao = i * 16;
                    if (posicao >= arquivo.length()) {
                        break;
                    }
                    arquivo.seek(posicao);
                    idxDados = new IndiceDados();
                    idxDados.setCdTabela(arquivo.readInt());
                    idxDados.setPosicaoDado(arquivo.readLong());
                    idxDados.setRegistroAtivo(arquivo.readInt());
                    indiceDados.put(posicao, idxDados);
                }
                arquivo.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de indices");
            }
        }
        return indiceDados;
    }
}

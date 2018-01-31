package br.com.analizador;

import br.com.baseDados.BancoDados;
import br.com.baseDados.estrutura.metadados.Coluna;
import br.com.baseDados.estrutura.metadados.Tabela;
import br.com.baseDados.estrutura.metadados.tipos.DataType;
import java.util.ArrayList;
import java.util.Arrays;

public class Semantico implements Constants {

    private BancoDados bancoDados;
    private int contadorColunas, contadorIndices, qtColunasIndice;
    private boolean isTamanho, isPrimaryKey, isIdentificador, isCreateTable, isInsert, isDelete, isUpdate;//, isConstraintsPrimarykey, isPrimarykeySemConstraints;
    private String nmConstraintOuIndice, nmTabela;
    private ArrayList<String> nmColunasPrimaryKey;
    private ArrayList<String> nmColunas;
    private ArrayList<String> valoresColunas;

    public Semantico(BancoDados bancoDados) {
        this.bancoDados = bancoDados;
        contadorColunas = 0;
        contadorIndices = 0;
        qtColunasIndice = 0;
        isTamanho = false;
        isPrimaryKey = false;

        isIdentificador = false;
        isCreateTable = false;
        isInsert = false;
        isDelete = false;
        isUpdate = false;
        nmConstraintOuIndice = "";
        nmColunasPrimaryKey = new ArrayList<String>();
        nmColunas = new ArrayList<String>();
        valoresColunas = new ArrayList<String>();
        bancoDados.limparEstruturaComandos();
    }

    public void executeAction(int action, Token token) throws SemanticError {
        switch (action) {
            case 1:
                acao1(token);
                break;
            case 2:
                acao2();
                break;
            case 3:
                acao3(token);
                break;
            case 4:
                acao4(token);
                break;
            case 5:
                acao5(token);
                break;
            case 6:
                acao6(token);
                break;
            case 7:
                acao7();
                break;
            case 8:
                acao8();
                break;
            case 9:
                acao9();
                break;
            case 10:
                acao10();
                break;
            case 11:
                acao11();
                break;
            case 12:
                acao12();
                break;
            case 13:
                acao13();
                break;
            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
            case 17:
                acao17();
                break;
            case 18:
                acao18();
                break;
            case 19:
                break;
            case 20:
                break;
            case 21:
                acao21(token);
                break;
            case 22:
                break;
            case 23:
                break;
            case 24:
                break;
            case 25:
                acao25(token);
                break;
            case 26:
                acao26(token);
                break;
            case 27:
                acao27();
                break;
            case 28:
                acao28(token);
                break;
            case 29:
                acao29(token);
                break;
            case 30:
                break;
            case 31:
                break;
            case 32:
                break;
            case 33:
                break;
            case 34:
                break;
            case 35:
                acao35();
                break;
            case 36:
                acao36(token);
                break;
            case 37:
                acao37();
                break;
            case 38:
                acao38();
                break;
            case 39:
                acao39();
                break;
        }
    }

    private void acao1(Token token) throws SemanticError {
        if (bancoDados.getTabela(token.getLexeme()) != null) {
            throw new SemanticError("tabela " + token.getLexeme() + " já existente");
        }
        int proximoCodigoTabela = bancoDados.getProximoCodigoTabela();
        if (proximoCodigoTabela > 99999) {
            throw new SemanticError("limite máximo de tabelas excedido, o banco suporta no máximo 99999 tabelas");
        }
        bancoDados.addEstruturaComandos("CD_TABELA", proximoCodigoTabela);
        bancoDados.addEstruturaComandos("NM_TABELA", token.getLexeme());
        isCreateTable = true;
    }

    private void acao2() throws SemanticError {
        bancoDados.addMetaDados();
        bancoDados.limparEstruturaComandos();
    }

    private void acao3(Token token) throws SemanticError {
        if (bancoDados.existeIdentificador(token.getLexeme())) {
            throw new SemanticError("coluna " + token.getLexeme() + " duplicada");
        }
        contadorColunas++;
        bancoDados.setQtColunas(contadorColunas);
        bancoDados.addEstruturaComandos("CD_COLUNA" + contadorColunas, contadorColunas);
        bancoDados.addEstruturaComandos("NM_COLUNA" + contadorColunas, token.getLexeme());
    }

    private void acao4(Token token) {
        if ("SMALLINT".equalsIgnoreCase(token.getLexeme())) {
            bancoDados.addEstruturaComandos("TIPO" + contadorColunas, DataType.SMALLINT);
        }
        isTamanho = false;
        isIdentificador = true;
    }

    private void acao5(Token token) throws SemanticError {
        nmConstraintOuIndice = token.getLexeme();
    }

    private void acao6(Token token) throws SemanticError {
        if (isPrimaryKey) {
            isPrimaryKey = false;
            qtColunasIndice = 0;
        }
    }

    private void acao7() {
        bancoDados.addEstruturaComandos("TIPO" + contadorColunas, DataType.NUMBER);
    }

    private void acao8() {
        bancoDados.addEstruturaComandos("TIPO" + contadorColunas, DataType.NUMERIC);
    }

    private void acao9() {
        bancoDados.addEstruturaComandos("TIPO" + contadorColunas, DataType.INTEGER);
    }

    private void acao10() {
        bancoDados.addEstruturaComandos("TIPO" + contadorColunas, DataType.TIMESTAMP);
    }

    private void acao11() {
        bancoDados.addEstruturaComandos("TIPO" + contadorColunas, DataType.VARCHAR);
    }

    private void acao12() {
        bancoDados.addEstruturaComandos("TIPO" + contadorColunas, DataType.DATE);
    }

    private void acao13() {
        bancoDados.addEstruturaComandos("TIPO" + contadorColunas, DataType.CHAR);
    }

    private void acao17() {
        bancoDados.addEstruturaComandos("NOT_NULL" + contadorColunas, "S");
    }

    private void acao18() throws SemanticError {
        isPrimaryKey = true;
        contadorIndices++;
        if ("".equalsIgnoreCase(nmConstraintOuIndice)) {
            nmConstraintOuIndice = (String) bancoDados.getValor("NM_COLUNA" + contadorColunas);
            nmConstraintOuIndice = nmConstraintOuIndice + "_PK";
            if (isIdentificador) {
                qtColunasIndice++;
                bancoDados.setQtColunasIndice(qtColunasIndice);
                int cdColuna = (Integer) bancoDados.getValor("CD_COLUNA" + contadorColunas);
                bancoDados.addEstruturaComandos("CD_COLUNA_PRIMARY_KEY" + qtColunasIndice, cdColuna);
                bancoDados.addEstruturaComandos("NOT_NULL" + contadorColunas, "S");
            }
        }
        if (bancoDados.existeIdentificador(nmConstraintOuIndice)) {
            throw new SemanticError("constraint " + nmConstraintOuIndice + " duplicada");
        }
        bancoDados.setQtIndices(contadorIndices);
        bancoDados.addEstruturaComandos("CD_INDICE" + contadorIndices, contadorIndices);
        bancoDados.addEstruturaComandos("NM_INDICE" + contadorIndices, nmConstraintOuIndice);
    }

    private void acao21(Token token) throws SemanticError {
        if (isCreateTable) {
            if (!isTamanho) {
                int tamanho = Integer.parseInt(token.getLexeme());
                if (tamanho == 0) {
                    throw new SemanticError("não são permitidas colunas de tamanho zero");
                }
                bancoDados.addEstruturaComandos("TAMANHO" + contadorColunas, tamanho);
                isTamanho = true;
            } else {
                bancoDados.addEstruturaComandos("PRECISAO" + contadorColunas, Integer.parseInt(token.getLexeme()));
            }
        }
    }

    private void acao25(Token token) throws SemanticError {
        nmTabela = token.getLexeme();
        int cdTabela = bancoDados.getCdTabela(nmTabela);
        if (cdTabela == 0) {
            throw new SemanticError("tabela " + token.getLexeme() + " inexistente");
        }
        bancoDados.addEstruturaComandos("CD_TABELA", addZerosString(cdTabela, 5));
    }

    private String addZerosString(int n, int tamanhoMax) {
        String nAtual = Integer.toString(n);
        String str = "";
        while (str.length() < tamanhoMax && (str.length() + nAtual.length()) < tamanhoMax) {
            str += "0";
        }
        str += nAtual;
        return str;
    }

    private void acao26(Token token) throws SemanticError {
        if (isPrimaryKey) {
            if (!nmColunasPrimaryKey.contains(token.getLexeme())) {
                nmColunasPrimaryKey.add(token.getLexeme());
            } else {
                throw new SemanticError("primary key " + token.getLexeme() + " repetida");
            }
            String chave = bancoDados.getChave(token.getLexeme());
            char[] ch = chave.toCharArray();
            String nrColuna = "";
            for (char c : ch) {
                if (Character.isDigit(c)) {
                    nrColuna += c;
                }
            }
            qtColunasIndice++;
            bancoDados.setQtColunasIndice(qtColunasIndice);
            int cdColuna = (Integer) bancoDados.getValor("CD_COLUNA" + nrColuna);
            bancoDados.addEstruturaComandos("CD_COLUNA_PRIMARY_KEY" + qtColunasIndice, cdColuna);
            bancoDados.addEstruturaComandos("NOT_NULL" + nrColuna, "S");
        }
        isIdentificador = false;
    }

    private void acao27() {
        isInsert = true;
    }

    private void acao28(Token token) throws SemanticError {
        if (")".equals(token.getLexeme()) && isInsert) {
            if (nmColunas.size() != valoresColunas.size()) {
                throw new SemanticError("a quantidade de colunas não corresponde a quantidade de valores verifique!");
            }
            Tabela tabela = bancoDados.getTabela(nmTabela);
            ArrayList<Coluna> colunas = new ArrayList<Coluna>(Arrays.asList(tabela.getColunas()));
            boolean contem = false, encontrado = false, validaTipo, isnotNull;
            String nmColuna, strAntesVirgula, strAposVirgula, info;
            int tamanho, precisao;
            Coluna coluna;
            for (int i = 0; i < colunas.size(); i++) {
                coluna = colunas.get(i);
                for (int y = 0; y < nmColunas.size(); y++) {
                    nmColuna = nmColunas.get(y);
                    if (nmColuna.equalsIgnoreCase(coluna.getNmColuna())) {
                        info = valoresColunas.get(y);
                        if ("null".equalsIgnoreCase(info)) {
                            break;
                        }
                        contem = true;
                        if (info.length() > 99999) {
                            throw new SemanticError("limite máximo de caracteres excedido para a coluna '" + nmColuna + "', o banco suporta no máximo 99999 caracteres");
                        }
                        validaTipo = bancoDados.validaTipoColuna(coluna.getTipo(), info, nmColuna);
                        if (validaTipo) {
                            tamanho = coluna.getTamanho();
                            precisao = coluna.getPrecisao();
                            if (!info.contains("'")) {
                                if (info.contains(".")) {
                                    strAntesVirgula = info.substring(0, info.indexOf("."));
                                    strAposVirgula = info.substring(info.indexOf(".") + 1, info.length());
                                    if (tamanho != 0 && strAntesVirgula.length() > tamanho) {
                                        throw new SemanticError("o valor informado na coluna '" + nmColuna + "' é maior que o tamanho suportado");
                                    }
                                    if (precisao != 0 && strAposVirgula.length() > precisao) {
                                        throw new SemanticError("a precisão informada na coluna '" + nmColuna + "' é maior que a precisão suportada");
                                    }
                                    if (coluna.getTipo() == 5) {
                                        info = Integer.toString((int) Double.parseDouble(info));
                                    }
                                } else {
                                    if (tamanho != 0 && info.length() > tamanho) {
                                        throw new SemanticError("o valor informado na coluna '" + nmColuna + "' é maior que o tamanho suportado");
                                    }
                                }
                            } else {
                                info = info.substring(1, info.length() - 1);
                                if (tamanho != 0 && info.length() > tamanho) {
                                    throw new SemanticError("a quantidade de caracteres informado '" + nmColuna + "' é maior que o tamanho suportado");
                                }
                            }
                            bancoDados.addEstruturaComandos("TAMANHO" + (i + 1), addZerosString(info.length(), 5));
                            bancoDados.addEstruturaComandos("VALOR" + (i + 1), info);
                        } else {
                            throw new SemanticError("o valor informado na coluna '" + nmColuna + "' não corresponde com o tipo da coluna");
                        }
                        break;
                    } else {
                        for (Coluna c : colunas) {
                            if (nmColuna.equalsIgnoreCase(c.getNmColuna())) {
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            throw new SemanticError("coluna " + nmColuna + " inválida");
                        }
                        encontrado = false;
                    }
                }
                if (!contem) {
                    isnotNull = coluna.isNotNull();
                    if (isnotNull) {
                        if (!nmColunas.contains(coluna.getNmColuna()) || isnotNull) {
                            throw new SemanticError("coluna '" + coluna.getNmColuna() + "' é not null não é possível inserir null");
                        }
                    }
                    bancoDados.addEstruturaComandos("TAMANHO" + (i + 1), "4");
                    bancoDados.addEstruturaComandos("VALOR" + (i + 1), null);
                }
                contem = false;
            }
            bancoDados.addEstruturaComandos("NR_COLUNAS", addZerosString(colunas.size(), 3));
            bancoDados.addComandoInsert();
            bancoDados.limparEstruturaComandos();
            nmColunas.clear();
            valoresColunas.clear();
        } else {
            nmColunas.add(token.getLexeme());
        }
    }

    private void acao29(Token token) {
        valoresColunas.add(token.getLexeme());
    }

    private void acao35() {
        isUpdate = true;
    }

    private void acao36(Token token) {
        valoresColunas.add(token.getLexeme());
    }

    private void acao37() {
        isDelete = true;
    }

    private void acao38() throws SemanticError {
        if (isDelete) {
            System.out.println(bancoDados.addComandoDelete());
            bancoDados.limparEstruturaComandos();
            nmColunas.clear();
            isDelete = false;
        }
    }

    private void acao39() throws SemanticError {
        if (isUpdate) {
            Tabela tabela = bancoDados.getTabela(nmTabela);
            ArrayList<Coluna> colunas = new ArrayList<Coluna>(Arrays.asList(tabela.getColunas()));
            String nmColuna, info, strAntesVirgula, strAposVirgula;
            boolean validaTipo, contem = false;
            int tamanho, precisao;
            Coluna coluna;
            for (int i = 0; i < colunas.size(); i++) {
                coluna = colunas.get(i);
                for (int y = 0; y < nmColunas.size(); y++) {
                    nmColuna = nmColunas.get(y);
                    if (nmColuna.equalsIgnoreCase(coluna.getNmColuna())) {
                        info = valoresColunas.get(y);
                        contem = true;
                        if ("null".equalsIgnoreCase(info) && coluna.isNotNull()) {
                            throw new SemanticError("coluna '" + coluna.getNmColuna() + "' é not null não é possível inserir null");
                        }
                        if (info.length() > 99999) {
                            throw new SemanticError("limite máximo de caracteres excedido para a coluna '" + nmColuna + "', o banco suporta no máximo 99999 caracteres");
                        }
                        validaTipo = bancoDados.validaTipoColuna(coluna.getTipo(), info, nmColuna);
                        if (validaTipo) {
                            tamanho = coluna.getTamanho();
                            precisao = coluna.getPrecisao();
                            if (!info.contains("'")) {
                                if (info.contains(".")) {
                                    strAntesVirgula = info.substring(0, info.indexOf("."));
                                    strAposVirgula = info.substring(info.indexOf(".") + 1, info.length());
                                    if (tamanho != 0 && strAntesVirgula.length() > tamanho) {
                                        throw new SemanticError("o valor informado na coluna '" + nmColuna + "' é maior que o tamanho suportado");
                                    }
                                    if (precisao != 0 && strAposVirgula.length() > precisao) {
                                        throw new SemanticError("a precisão informada na coluna '" + nmColuna + "' é maior que a precisão suportada");
                                    }
                                    if (coluna.getTipo() == 5) {
                                        info = Integer.toString((int) Double.parseDouble(info));
                                    }
                                } else {
                                    if (tamanho != 0 && info.length() > tamanho) {
                                        throw new SemanticError("o valor  na coluna '" + nmColuna + "' é maior que o tamanho suportado");
                                    }
                                }
                            } else {
                                info = info.substring(1, info.length() - 1);
                                if (tamanho != 0 && info.length() > tamanho) {
                                    throw new SemanticError("a quantidade de caracteres informado '" + nmColuna + "' é maior que o tamanho suportado");
                                }
                            }
                            bancoDados.addEstruturaComandos("TAMANHO" + (i + 1), addZerosString(info.length(), 5));
                            bancoDados.addEstruturaComandos("VALOR" + (i + 1), info);
                        } else {
                            throw new SemanticError("o valor informado na coluna '" + nmColuna + "' não corresponde com o tipo da coluna");
                        }
                        break;
                    }
                }
                if (!contem) {
                    bancoDados.addEstruturaComandos("VALOR" + (i + 1), "Não Informado");
                }
                contem = false;
            }
            bancoDados.addComandoUpdate();
            bancoDados.limparEstruturaComandos();
            nmColunas.clear();
            valoresColunas.clear();
            isUpdate = false;
        }
    }
}
package br.server.action;
/*
 * Document   : ServiceAction.java
 * Created on : 21/09/2013, 21:00:12
 * Author     : Caio
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class ServiceAction {

    private static ServiceAction serviceAction;
    private Connection conexao;
    private List<Requisicao> listaRequisicoes;
    private List<Requisicao> listaReqArquivos;
    private File dirArquivosServidor;

    private ServiceAction() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        this.conexao = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "systemmidd", "key50100");
        this.listaRequisicoes = new ArrayList<Requisicao>();
        this.listaReqArquivos = new ArrayList<Requisicao>();
        this.dirArquivosServidor = new File(System.getProperty("user.home") + "\\" + ".middServ\\");
        this.dirArquivosServidor.mkdirs();
        criarArquivoServerUserPassKeystore();
    }

    public static ServiceAction getInstancie() throws Exception {
        if (serviceAction == null) {
            serviceAction = new ServiceAction();
        }
        return serviceAction;
    }

    public void addListaRequisicao(Requisicao requisicao) {
        this.listaRequisicoes.add(requisicao);
    }

    public void addListaReqArquivo(Requisicao requisicao) {
        this.listaReqArquivos.add(requisicao);
    }

    public void addRequisicao(byte[] requisicao, long nrSeqSistema) throws Exception {
        boolean existeSistema = false;
        for (Requisicao req : listaRequisicoes) {
            if (req.getNrSeqSistema() == nrSeqSistema) {
                existeSistema = true;
                long nrSeqRequiscao = getSequenceReqValue();
                if (nrSeqRequiscao == -1) {
                    throw new Exception("Erro ao processar a requisição");
                }
                insertRequisicaoBD(nrSeqRequiscao, nrSeqSistema, requisicao);
                req.inserir(nrSeqRequiscao);
                break;
            }
        }
        if (!existeSistema) {
            if (sistemaCadastrado(nrSeqSistema)) {
                addListaRequisicao(new Requisicao(nrSeqSistema));
                addRequisicao(requisicao, nrSeqSistema);
            } else {
                throw new Exception("O sistema informado não existe");
            }
        }
    }

    public String getRequisicao(long nrSeqSistema) throws Exception {
        for (Requisicao req : listaRequisicoes) {
            if (req.getNrSeqSistema() == nrSeqSistema) {
                long nrSeqRequiscao = req.retirar();
                String requisicao = new String(getRequisicaoBD(nrSeqRequiscao));
                if (deleteRequisicaoBD(nrSeqRequiscao)) {
                    return requisicao;
                } else {
                    throw new Exception("Erro ao processar a requisição");
                }
            }
        }
        return null;
    }

    public void addReqArquivo(long nrSeqSistema, String cdRegra, String cdPermissao, String dsArquivoDiretorio, String nmUsuario) throws Exception {
        boolean existeSistema = false;
        for (Requisicao req : listaReqArquivos) {
            if (req.getNrSeqSistema() == nrSeqSistema) {
                existeSistema = true;
                long nrSeqArquivo = getSequenceArqValue();
                if (nrSeqArquivo == -1) {
                    throw new Exception("Erro ao processar o arquivo");
                }
                insertReqArquivoBD(nrSeqArquivo, nrSeqSistema, cdRegra, cdPermissao, dsArquivoDiretorio, nmUsuario);
                req.inserir(nrSeqArquivo);
                break;
            }
        }
        if (!existeSistema) {
            if (sistemaCadastrado(nrSeqSistema)) {
                addListaReqArquivo(new Requisicao(nrSeqSistema));
                addReqArquivo(nrSeqSistema, cdRegra, cdPermissao, dsArquivoDiretorio, nmUsuario);
            } else {
                throw new Exception("O sistema informado não existe");
            }
        }
    }

    public Arquivo getReqArquivo(long nrSeqSistema) throws Exception {
        for (Requisicao req : listaReqArquivos) {
            if (req.getNrSeqSistema() == nrSeqSistema) {
                long nrSeqArquivo = req.retirar();
                Arquivo arquivo = getArquivo(nrSeqArquivo);
                if (deleteReqArquivoBD(nrSeqArquivo)) {
                    return arquivo;
                } else {
                    throw new Exception("Erro ao processar o arquivo");
                }
            }
        }
        return null;
    }

    public boolean verificaPermissaoRegra(String nmUsuario, long nrSeqSistema, String cdRegra, String cdPermissao) throws Exception {
        String iePermissao = getPermissaoUsuario("SELECT D.IE_PERMISSAO "
                + " FROM    USUARIO A, "
                + "         RECURSO B, "
                + "         REGRA C, "
                + "         RECURSO_PERMISSAO D, "
                + "         PERMISSAO E "
                + " WHERE   A.NM_USUARIO            = '" + nmUsuario + "' "
                + " AND     A.NR_SEQUENCIA          = B.NR_SEQ_USUARIO "
                + " AND     B.NR_SEQ_REGRA          = C.NR_SEQUENCIA "
                + " AND     C.CD_REGRA_COMUNIC      = '" + cdRegra + "' "
                + " AND     C.NR_SEQ_SISTEMA        = " + nrSeqSistema + " "
                + " AND     B.NR_SEQUENCIA          = D.NR_SEQ_RECURSO "
                + " AND     D.NR_SEQ_PERMISSAO      = E.NR_SEQUENCIA "
                + " AND     E.CD_PERMISSAO_COMUNIC  = '" + cdPermissao + "'");
        if (iePermissao != null) {
            return "1".equals(iePermissao);
        } else {
            iePermissao = getPermissaoUsuario("SELECT E.IE_PERMISSAO "
                    + "FROM    USUARIO A, "
                    + "        PERFIL B, "
                    + "        RECURSO C, "
                    + "        REGRA D, "
                    + "        RECURSO_PERMISSAO E, "
                    + "        PERMISSAO F "
                    + "WHERE   A.NM_USUARIO            = '" + nmUsuario + "' "
                    + "AND     A.NR_SEQ_PERFIL         = B.NR_SEQUENCIA "
                    + "AND     B.NR_SEQUENCIA          = C.NR_SEQ_PERFIL "
                    + "AND     C.NR_SEQ_REGRA          = D.NR_SEQUENCIA "
                    + "AND     D.CD_REGRA_COMUNIC      = '" + cdRegra + "' "
                    + "AND     D.NR_SEQ_SISTEMA        = " + nrSeqSistema + " "
                    + "AND     C.NR_SEQUENCIA          = E.NR_SEQ_RECURSO "
                    + "AND     E.NR_SEQ_PERMISSAO      = F.NR_SEQUENCIA "
                    + "AND     F.CD_PERMISSAO_COMUNIC  = '" + cdPermissao + "' ");
            if (iePermissao != null) {
                return "1".equals(iePermissao);
            }
            return false;
        }
    }

    private String getPermissaoUsuario(String sql) throws Exception {
        ResultSet rs = null;
        try {
            rs = conexao.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return rs.getString("IE_PERMISSAO");
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro ao processar a requisição");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {}
        }
    }

    public void removerArquivoServidor(String dsArquivo) throws Exception {
        File arquivo = new File(dsArquivo);
        if (arquivo.exists()) {
            arquivo.delete();
        }
    }

    public Connection getConexao() throws Exception {
        return conexao;
    }

    private long getSequenceReqValue() throws Exception {
        return getSequence("SELECT REQUISICAO_SEQ.NEXTVAL FROM DUAL", "Erro ao processar a requisição");
    }

    private long getSequenceArqValue() throws Exception {
        return getSequence("SELECT ARQUIVO_SEQ.NEXTVAL FROM DUAL", "Erro ao processar o arquivo");
    }

    private long getSequence(String sql, String msg) throws Exception {
        ResultSet rs = null;
        try {
            rs = conexao.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
            return -1;
        } catch (Exception ex) {
            throw new Exception(msg);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {}
        }
    }

    private void insertRequisicaoBD(long nrSeqRequisicao, long nrSeqSistema, byte[] requisicao) throws Exception {
        PreparedStatement stm = null;
        try {
            stm = conexao.prepareStatement("INSERT INTO REQUISICAO (NR_SEQUENCIA, NR_SEQ_SISTEMA, DS_REQUISICAO) VALUES (?, ?, ?)");
            stm.setLong(1, nrSeqRequisicao);
            stm.setLong(2, nrSeqSistema);
            stm.setBytes(3, requisicao);
            stm.execute();
        } catch (Exception ex) {
            throw new Exception("Erro ao processar a requisição");
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception ex) {}
        }
    }

    private void insertReqArquivoBD(long nrSequencia, long nrSeqSistema, String cdRegra, String cdPermissao, String arquivoDiretorio, String nmUsuario) throws Exception {
        PreparedStatement stm = null;
        try {
            stm = conexao.prepareStatement("INSERT INTO ARQUIVO (NR_SEQUENCIA, NR_SEQ_SISTEMA, CD_REGRA, CD_PERMISSAO, DS_ARQUIVO, NM_USUARIO) VALUES (?, ?, ?, ?, ?, ?)");
            stm.setLong(1, nrSequencia);
            stm.setLong(2, nrSeqSistema);
            stm.setString(3, cdRegra);
            stm.setString(4, cdPermissao);
            stm.setString(5, arquivoDiretorio);
            stm.setString(6, nmUsuario);
            stm.execute();
        } catch (Exception ex) {
            throw new Exception("Erro no processamento de arquivos");
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception ex) {}
        }
    }

    private byte[] getRequisicaoBD(long nrSeqRequisicao) throws Exception {
        ResultSet rs = null;
        try {
            rs = conexao.prepareStatement("SELECT DS_REQUISICAO FROM REQUISICAO "
                    + "WHERE NR_SEQUENCIA = " + nrSeqRequisicao).executeQuery();
            if (rs.next()) {
                return rs.getBytes("DS_REQUISICAO");
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro ao processar a requisição");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {}
        }
    }

    private Arquivo getArquivo(long nrSeqArquivo) throws Exception {
        ResultSet rs = null;
        try {
            rs = conexao.prepareStatement("SELECT * FROM ARQUIVO WHERE NR_SEQUENCIA = " + nrSeqArquivo).executeQuery();
            if (rs.next()) {
                Arquivo arquivo = new Arquivo();
                arquivo.setNmUsuario(rs.getString("NM_USUARIO"));
                arquivo.setCdRegra(rs.getString("CD_REGRA"));
                arquivo.setCdPermissao(rs.getString("CD_PERMISSAO"));
                File arq = new File(rs.getString("DS_ARQUIVO"));
                DataHandler dataHandler = new DataHandler(new FileDataSource(arq));
                arquivo.setBinaryData(dataHandler);
                arquivo.setNmArquivo(arq.getName());
                arquivo.setDsDirArquivoServidor(arq.getPath());
                return arquivo;
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro no processamento de arquivos");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {}
        }
    }

    private boolean deleteRequisicaoBD(long nrSeqRequisicao) throws Exception {
        return deleteBD("DELETE FROM REQUISICAO WHERE NR_SEQUENCIA = " + nrSeqRequisicao, "Erro ao processar a requisição");
    }

    private boolean deleteReqArquivoBD(long nrSeqArquivo) throws Exception {
        return deleteBD("DELETE FROM ARQUIVO WHERE NR_SEQUENCIA = " + nrSeqArquivo, "Erro no processamento de arquivos");
    }

    private boolean deleteBD(String sql, String msg) throws Exception {
        PreparedStatement stm = null;
        try {
            stm = conexao.prepareStatement(sql);
            return stm.executeUpdate() == 1;
        } catch (Exception ex) {
            throw new Exception(msg);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception ex) {}
        }
    }

    public void carregarFilaRequisicoesBD() throws Exception {
        ResultSet rs = null;
        try {
            rs = conexao.prepareStatement("SELECT NR_SEQUENCIA, NR_SEQ_SISTEMA FROM REQUISICAO ORDER BY NR_SEQ_SISTEMA, NR_SEQUENCIA").executeQuery();
            long nrSequencia;
            long nrSeqSistema;
            while (rs.next()) {
                nrSequencia = rs.getLong("NR_SEQUENCIA");
                nrSeqSistema = rs.getLong("NR_SEQ_SISTEMA");
                for (Requisicao req : listaRequisicoes) {
                    if (req.getNrSeqSistema() == nrSeqSistema) {
                        req.inserir(nrSequencia);
                    }
                }
            }
        } catch (Exception ex) {
            throw new Exception("Erro ao criar objetos no sistema");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {}
        }
    }

    public void carregarFilaReqArquivosBD() throws Exception {
        ResultSet rs = null;
        try {
            rs = conexao.prepareStatement("SELECT NR_SEQUENCIA, NR_SEQ_SISTEMA FROM ARQUIVO ORDER BY NR_SEQ_SISTEMA, NR_SEQUENCIA").executeQuery();
            long nrSequencia;
            long nrSeqSistema;
            while (rs.next()) {
                nrSequencia = rs.getLong("NR_SEQUENCIA");
                nrSeqSistema = rs.getLong("NR_SEQ_SISTEMA");
                for (Requisicao req : listaReqArquivos) {
                    if (req.getNrSeqSistema() == nrSeqSistema) {
                        req.inserir(nrSequencia);
                    }
                }
            }
        } catch (Exception ex) {
            throw new Exception("Erro ao criar objetos no sistema");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {}
        }
    }

    private boolean sistemaCadastrado(long nrSeqSistema) throws Exception {
        ResultSet rs = null;
        try {
            rs = conexao.prepareStatement("SELECT 1 "
                    + "FROM SISTEMA WHERE NR_SEQUENCIA = " + nrSeqSistema).executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new Exception("Erro ao processar a requisição");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {}
        }
    }

    private void criarArquivoServerUserPassKeystore() throws Exception {
        File arq = new File(System.getProperty("user.home") + "\\", "serverUserPassKeystore.key");
        if (!arq.exists()) {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(arq)));
            out.println("user:sysserver");
            out.println("password:serv50200");
            out.close();
        }
    }

    public File getDirArquivosServidor() {
        return dirArquivosServidor;
    }
}
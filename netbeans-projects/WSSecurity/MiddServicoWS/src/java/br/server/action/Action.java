package br.server.action;

/*
 * Document   : Action.java
 * Created on : 24/09/2013, 21:20:30
 * Author     : Caio
 */
import java.sql.ResultSet;
import java.sql.Statement;

public class Action {

    public Action() {
        try {
            ServiceAction serviceAction = ServiceAction.getInstancie();
            criarObjetosBD(serviceAction);
            criarFilas(serviceAction);
            serviceAction.carregarFilaRequisicoesBD();
            serviceAction.carregarFilaReqArquivosBD();
        } catch (Exception ex) {
            System.exit(0);
        }
    }

    private void criarObjetosBD(ServiceAction serviceAction) throws Exception {
        Statement stm = null;
        ResultSet rs = null;
        try {
            int cont = 0;
            rs = serviceAction.getConexao().prepareStatement("SELECT COUNT(*) "
                    + "FROM USER_TABLES "
                    + "WHERE TABLE_NAME = 'REQUISICAO'").executeQuery();
            if (rs.next()) {
                cont = rs.getInt(1);
            }
            if (cont == 0) {

                //table
                String create = "CREATE TABLE REQUISICAO( "
                        + "NR_SEQUENCIA NUMBER(10) PRIMARY KEY, "
                        + "NR_SEQ_SISTEMA NUMBER(10) NOT NULL, "
                        + "DS_REQUISICAO BLOB NOT NULL, "
                        + "CONSTRAINT FK_REQ_NR_SEQ_SISTEMA FOREIGN KEY (NR_SEQ_SISTEMA) REFERENCES SISTEMA (NR_SEQUENCIA))";
                stm = serviceAction.getConexao().createStatement();
                stm.execute(create);

                //sequence
                create = "CREATE SEQUENCE REQUISICAO_SEQ "
                        + "INCREMENT BY 1 "
                        + "START WITH 1 "
                        + "MINVALUE 1 "
                        + "MAXVALUE 9999999999 "
                        + "NOCYCLE "
                        + "NOCACHE";
                stm = serviceAction.getConexao().createStatement();
                stm.execute(create);

                //table
                create = "CREATE TABLE ARQUIVO( "
                        + "NR_SEQUENCIA NUMBER(10) PRIMARY KEY, "
                        + "NR_SEQ_SISTEMA NUMBER(10) NOT NULL, "
                        + "CD_REGRA VARCHAR2(20) NOT NULL, "
                        + "CD_PERMISSAO VARCHAR2(20) NOT NULL, "
                        + "DS_ARQUIVO VARCHAR2(100) NOT NULL, "
                        + "NM_USUARIO VARCHAR2(25) NOT NULL, "
                        + "CONSTRAINT FK_ARQ_NR_SEQ_SISTEMA FOREIGN KEY (NR_SEQ_SISTEMA) REFERENCES SISTEMA (NR_SEQUENCIA))";
                stm = serviceAction.getConexao().createStatement();
                stm.execute(create);

                //sequence
                create = "CREATE SEQUENCE ARQUIVO_SEQ "
                        + "INCREMENT BY 1 "
                        + "START WITH 1 "
                        + "MINVALUE 1 "
                        + "MAXVALUE 9999999999 "
                        + "NOCYCLE "
                        + "NOCACHE";
                stm = serviceAction.getConexao().createStatement();
                stm.execute(create);

            }
        } catch (Exception ex) {
            throw new Exception("Erro ao criar objetos no sistema");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception ex) {}
        }
    }

    private void criarFilas(ServiceAction serviceAction) throws Exception {
        ResultSet rs = null;
        try {
            long nrSeqSistema;
            rs = serviceAction.getConexao().prepareStatement("SELECT NR_SEQUENCIA "
                    + "FROM SISTEMA").executeQuery();
            while (rs.next()) {
                nrSeqSistema = rs.getLong("NR_SEQUENCIA");
                serviceAction.addListaRequisicao(new Requisicao(nrSeqSistema));
                serviceAction.addListaReqArquivo(new Requisicao(nrSeqSistema));
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
}
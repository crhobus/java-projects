package br.server.webservice;

/*
 * Document   : MiddWSInterface.java
 * Created on : 19/09/2013, 19:38:21
 * Author     : Caio
 */
import br.server.action.Arquivo;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface MiddWSInterface {

    /**
     * Enviar uma requisição ao servidor
     *
     * @param nmUsuario - usuário que está realizando a requisição
     * @param nrSeqSistemaOrigem - sistema que está enviando a requisição
     * @param nrSeqSistemaDestino - sistema na qual será enviada a requisição
     * @param requisicao - xml contendo a requisição
     * @param cdRegra - regra utilizada na comunicação
     * @param cdPermissao - operação a ser realizada com esta requisição
     *
     * @exception - lança exceção caso ocorra alguma falha na comunicação, ou se
     * o usuário não possua permissão
     *
     * @return - se a requisição foi bem sucedida
     */
    @WebMethod(operationName = "enviarRequisicao")
    public String enviarRequisicao(@WebParam(name = "nmUsuario") String nmUsuario,
            @WebParam(name = "nrSeqSistemaOrigem") long nrSeqSistemaOrigem,
            @WebParam(name = "nrSeqSistemaDestino") long nrSeqSistemaDestino,
            @WebParam(name = "requisicao") String requisicao,
            @WebParam(name = "cdRegra") String cdRegra,
            @WebParam(name = "cdPermissao") String cdPermissao) throws Exception;

    /**
     * Obtém a requisição enviada ao servidor
     *
     * @param nrSeqSistema - sistema na qual receberá a requisição
     *
     * @exception - lança exceção caso ocorra alguma falha na comunicação
     *
     * @return - requisição
     */
    @WebMethod(operationName = "receberRequisicao")
    public String receberRequisicao(@WebParam(name = "nrSeqSistema") long nrSeqSistema) throws Exception;

    /**
     * Enviar um arquivo ao servidor
     *
     * @param nmUsuario - usuário que está enviando o arquivo
     * @param nrSeqSistemaOrigem - sistema que está enviando o arquivo
     * @param nrSeqSistemaDestino - sistema na qual será enviado o arquivo
     * @param arquivo - arquivo de dados
     * @param cdRegra - regra utilizada na comunicação
     * @param cdPermissao - operação a ser realizada com este erquivo
     *
     * @exception - lança exceção caso ocorra alguma falha na comunicação, ou se
     * o usuário não possua permissão
     *
     * @return - se o arquivo foi enviado com sucesso
     */
    @WebMethod(operationName = "enviarArquivo")
    public String enviarArquivo(@WebParam(name = "nmUsuario") String nmUsuario,
            @WebParam(name = "nrSeqSistemaOrigem") long nrSeqSistemaOrigem,
            @WebParam(name = "nrSeqSistemaDestino") long nrSeqSistemaDestino,
            @WebParam(name = "arquivo") Arquivo arquivo,
            @WebParam(name = "cdRegra") String cdRegra,
            @WebParam(name = "cdPermissao") String cdPermissao) throws Exception;

    /**
     * Obtém o arquivo enviado ao servidor
     *
     * @param nrSeqSistema - sistema na qual receberá o arquivo
     *
     * @exception - lança exceção caso ocorra alguma falha na comunicação
     *
     * @return - arquivos de dados
     */
    @WebMethod(operationName = "receberArquivo")
    public Arquivo receberArquivo(@WebParam(name = "nrSeqSistema") long nrSeqSistema) throws Exception;

    /**
     * Remove o arquivo na qual foi consumido pelo middleware
     *
     * @param dsArquivo - diretório do arquivo no servidor
     *
     * @exception - lança exceção caso ocorra alguma falha na comunicação
     */
    @WebMethod(operationName = "removerArquivoServidor")
    public void removerArquivoServidor(@WebParam(name = "dsArquivo") String dsArquivo) throws Exception;
}
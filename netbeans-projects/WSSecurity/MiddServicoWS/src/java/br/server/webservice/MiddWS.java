package br.server.webservice;

/*
 * Document   : MiddWS.java
 * Created on : 19/09/2013, 19:40:38
 * Author     : Caio
 */
import br.server.action.Arquivo;
import br.server.action.ServiceAction;
import java.io.File;
import java.io.FileOutputStream;
import javax.activation.DataHandler;
import javax.jws.WebService;

@WebService(name = "MiddWS",
            serviceName = "MiddWSService",
            portName = "MiddWSPort")
public class MiddWS implements MiddWSInterface {

    @Override
    public String enviarRequisicao(String nmUsuario, long nrSeqSistemaOrigem, long nrSeqSistemaDestino, String requisicao, String cdRegra, String cdPermissao) throws Exception {
        ServiceAction serviceAction = ServiceAction.getInstancie();
        if (serviceAction.verificaPermissaoRegra(nmUsuario, nrSeqSistemaOrigem, cdRegra, cdPermissao)) {
            serviceAction.addRequisicao(requisicao.getBytes(), nrSeqSistemaDestino);
            return "Requisição enviada com sucesso";
        }
        throw new Exception("Usuário sem permissão para efetuar esta requisição");
    }

    @Override
    public String receberRequisicao(long nrSeqSistema) throws Exception {
        ServiceAction serviceAction = ServiceAction.getInstancie();
        return serviceAction.getRequisicao(nrSeqSistema);
    }

    @Override
    public String enviarArquivo(String nmUsuario, long nrSeqSistemaOrigem, long nrSeqSistemaDestino, Arquivo arquivo, String cdRegra, String cdPermissao) throws Exception {
        ServiceAction serviceAction = ServiceAction.getInstancie();
        if (serviceAction.verificaPermissaoRegra(nmUsuario, nrSeqSistemaOrigem, cdRegra, cdPermissao)) {

            DataHandler dataHandler = arquivo.getBinaryData();

            String nmArquivo = "SIS" + nrSeqSistemaOrigem + "_" + arquivo.getNmArquivo();

            String nome = nmArquivo.substring(0, nmArquivo.lastIndexOf("."));
            String extencao = nmArquivo.substring(nmArquivo.lastIndexOf("."), nmArquivo.length());
            File novoArquivo = new File(serviceAction.getDirArquivosServidor().getPath() + "\\", nmArquivo);
            int cont = 1;
            while (novoArquivo.exists()) {
                novoArquivo = new File(serviceAction.getDirArquivosServidor().getPath() + "\\", nome + "_" + cont + extencao);
                cont++;
            }

            FileOutputStream out = new FileOutputStream(novoArquivo);
            dataHandler.writeTo(out);
            out.flush();
            out.close();

            serviceAction.addReqArquivo(nrSeqSistemaDestino, cdRegra, cdPermissao, novoArquivo.getPath(), nmUsuario);

            return "Arquivo enviado com sucesso";
        }
        throw new Exception("Usuário sem permissão para efetuar esta requisição");
    }

    @Override
    public Arquivo receberArquivo(long nrSeqSistema) throws Exception {
        ServiceAction serviceAction = ServiceAction.getInstancie();
        return serviceAction.getReqArquivo(nrSeqSistema);
    }

    @Override
    public void removerArquivoServidor(String dsArquivo) throws Exception {
        ServiceAction serviceAction = ServiceAction.getInstancie();
        serviceAction.removerArquivoServidor(dsArquivo);
    }
}
package br.client.middleware;
/*
 * Document   : MiddlewareWSS.java
 * Created on : 22/09/2013, 10:37:48
 * Author     : Caio
 */

import br.client.middleware.action.dados.ArquivoDados;
import br.client.middleware.action.dados.Lista;
import br.client.middleware.action.dados.Registro;
import br.client.middleware.action.dados.RequisicaoLista;
import br.client.middleware.action.dados.RequisicaoRegistro;
import br.client.middleware.config.Config;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;
import ws.Arquivo;
import ws.Exception_Exception;
import ws.MiddWSInterface;

public class MiddlewareWSS {

    private MiddWSInterface port;
    private Config config;
    private long nrSeqSistema;
    private String dirRecebimentoArquivos;
    private static MiddlewareWSS middlewareWSS;

    private MiddlewareWSS() {
        this.config = Config.getInstancie();
    }

    public static MiddlewareWSS getInstancie() {
        if (middlewareWSS == null) {
            middlewareWSS = new MiddlewareWSS();
        }
        return middlewareWSS;
    }

    public void configurarMiddleware() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"br/client/middleware/config/xml/cxf-client.xml"});
        this.port = (MiddWSInterface) context.getBean("middWSClient");
        if (this.dirRecebimentoArquivos != null
                && !this.dirRecebimentoArquivos.endsWith("\\")) {
            this.dirRecebimentoArquivos += "\\";
        }
    }

    public void setUser(String nmUsuario, String dsSenha) {
        this.config.setNmUsuario(nmUsuario);
        this.config.setDsSenha(DigestUtils.md5DigestAsHex(dsSenha.getBytes()));
    }

    public String enviarRequisicao(Lista registros, long nrSeqSistemaRequisicao, String cdRegra, String cdPermissao) throws Exception {

        RequisicaoLista requisicao = new RequisicaoLista();
        requisicao.setCdRegra(cdRegra);
        requisicao.setCdPermissao(cdPermissao);
        requisicao.setNmUsuario(config.getNmUsuario());
        requisicao.setLista(registros);

        XStream xStream = new XStream();
        xStream.alias("requisicao", RequisicaoLista.class);
        xStream.alias("lista", Lista.class);
        xStream.alias("registro", Registro.class);

        String xml = xStream.toXML(requisicao);

        return port.enviarRequisicao(config.getNmUsuario(), nrSeqSistema, nrSeqSistemaRequisicao, xml, cdRegra, cdPermissao);
    }

    public String enviarRequisicao(Registro registro, long nrSeqSistemaRequisicao, String cdRegra, String cdPermissao) throws Exception {

        RequisicaoRegistro requisicao = new RequisicaoRegistro();
        requisicao.setCdRegra(cdRegra);
        requisicao.setCdPermissao(cdPermissao);
        requisicao.setNmUsuario(config.getNmUsuario());
        requisicao.setRegistro(registro);

        XStream xStream = new XStream();
        xStream.alias("requisicao", RequisicaoRegistro.class);
        xStream.alias("registro", Registro.class);

        String xml = xStream.toXML(requisicao);

        return port.enviarRequisicao(config.getNmUsuario(), nrSeqSistema, nrSeqSistemaRequisicao, xml, cdRegra, cdPermissao);
    }

    public String receberRequisicao() throws Exception_Exception {
        return port.receberRequisicao(nrSeqSistema);
    }

    public String enviarArquivo(File arquivo, long nrSeqSistemaRequisicao, String cdRegra, String cdPermissao) throws Exception {

        Arquivo arq = new Arquivo();
        arq.setCdRegra(cdRegra);
        arq.setCdPermissao(cdPermissao);
        arq.setNmUsuario(config.getNmUsuario());
        arq.setNmArquivo(arquivo.getName());

        DataHandler dataHandler = new DataHandler(new FileDataSource(arquivo));
        arq.setBinaryData(dataHandler);

        return port.enviarArquivo(config.getNmUsuario(), nrSeqSistema, nrSeqSistemaRequisicao, arq, cdRegra, cdPermissao);
    }

    public ArquivoDados receberArquivo() throws Exception {
        Arquivo arq = port.receberArquivo(nrSeqSistema);

        ArquivoDados arquivoDados = new ArquivoDados();
        arquivoDados.setNmUsuario(arq.getNmUsuario());
        arquivoDados.setCdRegra(arq.getCdRegra());
        arquivoDados.setCdPermissao(arq.getCdPermissao());

        DataHandler dataHandler = arq.getBinaryData();

        String nmArquivo = arq.getNmArquivo();

        String nome = nmArquivo.substring(0, nmArquivo.lastIndexOf("."));
        String extencao = nmArquivo.substring(nmArquivo.lastIndexOf("."), nmArquivo.length());
        File novoArquivo = new File(dirRecebimentoArquivos, nmArquivo);
        int cont = 1;
        while (novoArquivo.exists()) {
            novoArquivo = new File(dirRecebimentoArquivos, nome + "_" + cont + extencao);
            cont++;
        }

        FileOutputStream out = new FileOutputStream(novoArquivo);
        dataHandler.writeTo(out);
        out.flush();
        out.close();

        arquivoDados.setArquivo(novoArquivo);

        removerArquivoServidor(arq.getDsDirArquivoServidor());

        return arquivoDados;
    }

    private void removerArquivoServidor(String dsArquivo) {
        try {
            port.removerArquivoServidor(dsArquivo);
        } catch (Exception ex) {}
    }

    public long getNrSeqSistema() {
        return nrSeqSistema;
    }

    public void setNrSeqSistema(long nrSeqSistema) {
        this.nrSeqSistema = nrSeqSistema;
    }

    public String getDirRecebimentoArquivos() {
        return dirRecebimentoArquivos;
    }

    public void setDirRecebimentoArquivos(String dirRecebimentoArquivos) {
        this.dirRecebimentoArquivos = dirRecebimentoArquivos;
    }
}
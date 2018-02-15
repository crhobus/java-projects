package sistema;

import br.client.middleware.MiddlewareWSS;
import br.client.middleware.action.dados.ArquivoDados;

/*
 * Document   : Contabilidade.java
 * Created on : 23/11/2013, 15:21:42
 * Author     : Caio
 */
public class Contabilidade {

    public static void main(String[] args) {
        try {

            //Criação de uma única instância do middleware durante toda execução
            MiddlewareWSS middlewareWSS = MiddlewareWSS.getInstancie();

            //Configurações iniciais
            middlewareWSS.setNrSeqSistema(210);//Sequência da aplicação Contabilidade cadastrada
            middlewareWSS.setDirRecebimentoArquivos("C:\\Arquivos\\Contabilidade\\");//Diretório para recebimento de arquivos
            middlewareWSS.configurarMiddleware();//Cria uma conexão com o web service

            //Credenciais do usuário que irá realizar a comunicação
            middlewareWSS.setUser("ucontabilidade", "87654321");

            //Recebe uma requisição do servidor no formato xml
            System.out.println(middlewareWSS.receberRequisicao());

            //Recebe um arquivo do servidor
            ArquivoDados arq = middlewareWSS.receberArquivo();
            System.out.println(arq.getNmUsuario());
            System.out.println(arq.getCdRegra());
            System.out.println(arq.getCdPermissao());
            System.out.println(arq.getArquivo().getPath());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
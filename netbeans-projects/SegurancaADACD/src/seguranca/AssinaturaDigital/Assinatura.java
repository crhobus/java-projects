package seguranca.AssinaturaDigital;

import java.security.*;

public class Assinatura {

    public static void main(String[] args) {
        //define as variáveis
        KeyPairGenerator chaves = null;
        Signature sgn = null;
        Signature vrf = null;
        byte[] assina = null;

        //define uma mensagem
        String mensagem = "teste teste";
        /*    for(int i = 0; i < args.length; i++)
         mensagem = mensagem + " " + args[i];    */

        try {
            //cria uma instância do gerador de chaves 
            chaves = KeyPairGenerator.getInstance("DSA");

            //cria uma instância do DSA
            sgn = Signature.getInstance("DSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //inicializa e gera as chaves
        chaves.initialize(512);
        System.out.println("Gerando o par de chaves...");
        KeyPair par = chaves.generateKeyPair(); //gera as chaves        
        PrivateKey priv = par.getPrivate();   //obtem chave privada
        PublicKey pub = par.getPublic();  //obtem chave pública

        try {
            sgn.initSign(priv); //inicializa o algoritmo de assinatura

            //adiciona a mensagem ao algoritmo
            sgn.update(mensagem.getBytes());

            //faz a assinatura
            assina = sgn.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //saídas parte de assinatura
        System.out.println("Chave privada: " + priv);
        System.out.println("Chave pública: " + pub);
        
        System.out.println("tamanho privada: " + priv.getEncoded().length);
        System.out.println("tamanho pública: " + pub.getEncoded().length);
        
        System.out.println("Mensagem: " + mensagem);
        System.out.print("Assinatura da mensagem: ");
        for (int i = 0; i < assina.length; i++) {
            System.out.print((assina[i] + 128) + " ");
        }
        System.out.println("");
        System.out.println("Tamanho da mensagen assinada: " + assina.length);

        boolean ok;

        //inicia a verificação
        try {
            // cria uma instância do DAS
            vrf = Signature.getInstance("DSA");

            //inicializa uma verificação com a chave pública do parâmetro
            vrf.initVerify(pub);

            //adiciona o texto ao algoritmo
            vrf.update(mensagem.getBytes());

            //faz a verificação com a assinatura do parâmetro
            ok = vrf.verify(assina);

            //saída da parte de verificação
            System.out.println("Validou: " + ok);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

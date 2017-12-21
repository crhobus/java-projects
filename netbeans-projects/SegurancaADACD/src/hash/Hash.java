package hash;

import java.security.*;

public class Hash {

    public static void main(String[] args) {
        //define a variável do hash
        MessageDigest md = null;

        //cria uma mensagem
        String mensagem = "teste teste";
        /*for(int i = 0; i < args.length; i++)
         mensagem = mensagem + " " + args[i];*/

        try {
            //cria uma instância do gerador de hash
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //coloca a mensagem no gerador de hash
        md.update(mensagem.getBytes());

        //gera o hash
        byte[] resumo = md.digest();

        //saída do exemplo
        System.out.println(resumo.length);
        System.out.print("Resumo: ");
        for (int i = 0; i < resumo.length; i++) {
            System.out.print((resumo[i] + 128) + " ");
        }
    }
}
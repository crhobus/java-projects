package seguranca.criptografiaSimetrica;

import javax.crypto.*;
import javax.crypto.spec.*;

public class CifraBlowfish {

    public static void main(String[] args) throws Exception {

        //geração da chave
        KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        System.out.println("chave (array de byte): " + raw);
        System.out.println("chave (string): " + new String(raw));

        //encriptação
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");
        Cipher cipherEnc = Cipher.getInstance("Blowfish");
        cipherEnc.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipherEnc.doFinal("This is just an example".getBytes());
        System.out.println("cifrado (array de byte): " + encrypted);
        System.out.println("cifrado (string): " + new String(encrypted));

        //decriptação
        Cipher cipherDec = Cipher.getInstance("Blowfish");
        cipherDec.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipherDec.doFinal(encrypted);
        System.out.println("decifrado (array de byte): " + decrypted);
        System.out.println("decifrado (string): " + new String(decrypted));
    }
}

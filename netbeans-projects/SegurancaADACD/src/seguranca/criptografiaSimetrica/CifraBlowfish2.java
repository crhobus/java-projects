package seguranca.criptografiaSimetrica;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CifraBlowfish2 {

    public static void main(String[] args) {
        try {
            System.out.println("Getting key generator ...");
            KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
            System.out.println("Generating key ...");
            SecretKey secretKey = kgen.generateKey();
            byte[] bytes = secretKey.getEncoded();
            SecretKeySpec specKey = new SecretKeySpec(bytes, "Blowfish");

            System.out.println("Creating cipher ...");
            Cipher cipher = Cipher.getInstance("Blowfish");

            System.out.println("Encrypting ...");
            cipher.init(Cipher.ENCRYPT_MODE, specKey);
            String target = "Encrypt this buddy";
            byte[] encrypted = cipher.doFinal(target.getBytes());

            System.out.println("Before: " + target);
            System.out.println("After: " + new String(encrypted));

            cipher.init(Cipher.DECRYPT_MODE, specKey);
            byte[] decrypted = cipher.doFinal(encrypted);
            System.out.println("\nafter decrypt: " + new String(decrypted));
        } catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }
    }
}

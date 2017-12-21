package seguranca.certificadoDigital;

//How to export the private key from keystore?
//Does keytool not have an option to do so?
//This example use the "testkeys" file that comes with JSSE 1.0.3
//Alexey Zilber: Ported to work with Base64Coder: http://www.source-code.biz/snippets/java/2.htm
import java.security.cert.Certificate;

import java.security.*;
import java.io.File;
import java.io.FileInputStream;

public class ExportPriv {

    public static void main(String args[]) throws Exception {


        ExportPriv myep = new ExportPriv();
        myep.doit("meuKeytool.jks", "paulo", "12345678");
    }

    public void doit(String fileName, String aliasName, String pass) throws Exception {

        KeyStore ks = KeyStore.getInstance("JKS");

        char[] passPhrase = pass.toCharArray();
        //BASE64Encoder myB64 = new BASE64Encoder();

        File certificateFile = new File(fileName);
        ks.load(new FileInputStream(certificateFile), passPhrase);

        KeyPair kp = getPrivateKey(ks, aliasName, "123456".toCharArray());

        PrivateKey privKey = kp.getPrivate();

        char[] b64 = Base64Coder.encode(privKey.getEncoded());

        System.out.println("-----BEGIN PRIVATE KEY-----");
        System.out.println(b64);
        System.out.println("-----END PRIVATE KEY-----");

    }

//From http://javaalmanac.com/egs/java.security/GetKeyFromKs.html
    public KeyPair getPrivateKey(KeyStore keystore, String alias, char[] password) {
        try {
            // Get private key
            Key key = keystore.getKey(alias, password);
            if (key instanceof PrivateKey) {
                // Get certificate of public key
                Certificate cert = keystore.getCertificate(alias);

                // Get public key
                PublicKey publicKey = cert.getPublicKey();

                // Return a key pair
                return new KeyPair(publicKey, (PrivateKey) key);
            }
        } catch (UnrecoverableKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (KeyStoreException e) {
        }
        return null;
    }
}
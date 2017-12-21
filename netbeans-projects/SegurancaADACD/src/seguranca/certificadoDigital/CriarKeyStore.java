package seguranca.certificadoDigital;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class CriarKeyStore {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        KeyStore ks = Criar();



    }

    public static KeyStore Criar() throws Exception {

        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(null, null);
        ks.store(new FileOutputStream("NewClientKeyStore"), "MyPass".toCharArray());
        return ks;

    }

    public static void Importar(KeyStore ks) throws Exception {
        FileInputStream fis = new FileInputStream("teste.cer");
        BufferedInputStream bis = new BufferedInputStream(fis);
        //I USE x.509 BECAUSE THAT'S WHAT keytool CREATES  
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        //NOTE: THIS IS java.security.cert.Certificate NOT java.security.Certificate  
        Certificate cert = null;


        //GET THE CERTS CONTAINED IN THIS ROOT CERT FILE  
        while (bis.available() > 0) {
            cert = cf.generateCertificate(bis);
            ks.setCertificateEntry("teste", cert);
        }
        //ADD TO THE KEYSTORE AND GIVE IT AN ALIAS NAME  
        ks.setCertificateEntry("teste", cert);
    }

    public static void CriarCert() throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair KPair = keyPairGenerator.generateKeyPair();

        //X509V3CertificateGenerator v3CertGen = new X509V3CertificateGenerator();



    }
}

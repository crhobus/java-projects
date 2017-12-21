package seguranca.certificadoDigital;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Collection;
import java.util.Iterator;

public class TesteCert {

    public static void main(String[] args) throws Exception {

        //define o nome do arquivo do certificado
        String certfile = "cerificadoKeyTool.cer";

        //cria um input para o arquivo do certificado
        FileInputStream fis = new FileInputStream(certfile);
        BufferedInputStream bis = new BufferedInputStream(fis);

        //cria uma instância de um certificado X.509
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        //carrega os elementos do certificado
        Collection c = cf.generateCertificates(fis);
        Iterator i = c.iterator();

        //percorre os elemento do certificado
        while (i.hasNext()) {
            Certificate cert = (Certificate) i.next();
            System.out.println(cert);
        }
    }
}

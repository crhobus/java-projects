package SSL;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class EchoClient {

    private SSLSocket sslsocket;

    public void conectar(boolean certificado) {
        try {

            if (certificado) {
                System.setProperty("javax.net.ssl.trustStore", "KeyStore.jks");
                System.setProperty("javax.net.ssl.trustStorePassword", "key50800");
                SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 9998);
            } else {
                SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 9999);
                String[] enabledCipherSuites = {"SSL_DH_anon_WITH_RC4_128_MD5"};
                sslsocket.setEnabledCipherSuites(enabledCipherSuites);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mandarRequisicao(Object obj) {
        try {

            OutputStream outputstream = sslsocket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputstream);

            // Envia o objeto.
            out.writeObject(obj);
            // Libera da memoria.
            out.flush();
            // Fecha a conexão.
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void desconectar() {
        try {
            sslsocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
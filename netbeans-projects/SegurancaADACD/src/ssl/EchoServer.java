package ssl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

//para criar o keystore
//keytool -genkey -keystore mySrvKeystore -keyalg RSA
public class EchoServer {

    public static void main(String[] arstring) {
        try {
            System.setProperty("javax.net.ssl.keyStore", "KeyStore.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "key50800");
            //System.setProperty("javax.net.debug", "all");
            //System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");

            SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket sslserversocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(9999);
            
            //String[] enabledCipherSuites = {"SSL_DH_anon_WITH_RC4_128_MD5"};
            //sslserversocket.setEnabledCipherSuites(enabledCipherSuites);

            SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();

            //System.out.println("teste");
            InputStream inputstream = sslsocket.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

            String string = null;
            while ((string = bufferedreader.readLine()) != null) {
                System.out.println(string);
                System.out.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
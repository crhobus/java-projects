package ssl;

import java.io.*;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

//para criar o keystore
//keytool -genkey -keystore mySrvKeystore -keyalg RSA
public class EchoClient {

    public static void main(String[] arstring) {
        try {
            System.setProperty("javax.net.ssl.trustStore", "KeyStore.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "key50800");
            //System.setProperty("javax.net.debug", "ALL");
            //System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");

            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 9999);
            
            //String[] enabledCipherSuites = {"SSL_DH_anon_WITH_RC4_128_MD5"};
            //sslsocket.setEnabledCipherSuites(enabledCipherSuites);

            InputStream inputstream = System.in;
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

            OutputStream outputstream = sslsocket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputstream);
            //out.w
            
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
            BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);

            String string = null;
            while ((string = bufferedreader.readLine()) != null) {
                bufferedwriter.write(string + '\n');
                bufferedwriter.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
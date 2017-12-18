package edu.furb.easyboleto.security;

import biz.source_code.base64Coder.Base64Coder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.cert.X509Certificate;

public class Seguranca {

    //Simétrico
    //CifraDESede
    private byte[] resumoChave(String chave) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(chave.getBytes());
        return md.digest();
    }

    public String encriptarSimetrico(String chave, String str) throws Exception {
        byte key[] = resumoChave(chave);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey secretKey = keyFactory.generateSecret(new DESedeKeySpec(key));
        gravarChaveSecreta(secretKey);

        Cipher desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        desCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return new String(desCipher.doFinal(str.getBytes()));
    }

    public String decriptarSimetrico(String str) throws Exception {
        SecretKey secretKey = recuperarChaveSecreta();

        Cipher desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        desCipher.init(Cipher.DECRYPT_MODE, secretKey);

        return new String(desCipher.doFinal(str.getBytes()));
    }

    //Assimétrico
    //CifraRSA
    private KeyPair gerarParChaves() throws Exception {
        File arqChavePublica = new File("C:\\EasyBoleto\\public.key");
        File arqChavePrivada = new File("C:\\EasyBoleto\\private.key");
        if (arqChavePublica.exists()
                && arqChavePrivada.exists()) {

            ObjectInputStream input = new ObjectInputStream(new FileInputStream(arqChavePublica));
            PublicKey publicKey = (PublicKey) input.readObject();
            input.close();

            input = new ObjectInputStream(new FileInputStream(arqChavePrivada));
            PrivateKey privateKey = (PrivateKey) input.readObject();
            input.close();

            return new KeyPair(publicKey, privateKey);

        } else {
            KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");
            gerador.initialize(1024);
            KeyPair chaves = gerador.generateKeyPair();

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arqChavePublica));
            out.writeObject(chaves.getPublic());
            out.close();

            out = new ObjectOutputStream(new FileOutputStream(arqChavePrivada));
            out.writeObject(chaves.getPrivate());
            out.close();

            return chaves;
        }
    }

    private byte[] encriptarAssimetrico(SecretKey secretKey) throws Exception {
        Cipher cifra = Cipher.getInstance("RSA");
        KeyPair chaves = gerarParChaves();
        cifra.init(Cipher.ENCRYPT_MODE, chaves.getPublic());
        return cifra.doFinal(secretKey.getEncoded());
    }

    private byte[] decriptarAssimetrico(byte[] bytes) throws Exception {
        Cipher cifra = Cipher.getInstance("RSA");
        KeyPair chaves = gerarParChaves();
        cifra.init(Cipher.DECRYPT_MODE, chaves.getPrivate());
        return cifra.doFinal(bytes);
    }

    //Salvar chave secreta
    private void gravarChaveSecreta(SecretKey secretKey) throws Exception {
        DataFile dataFile = new DataFile();
        dataFile.setData(encriptarAssimetrico(secretKey));

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\EasyBoleto\\SecretKey.key"));
        out.writeObject(dataFile);
        out.close();
    }

    //Recuperar chave secreta
    private SecretKey recuperarChaveSecreta() throws Exception {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\EasyBoleto\\SecretKey.key"));
        DataFile dataFile = (DataFile) input.readObject();
        input.close();

        byte[] data = decriptarAssimetrico(dataFile.getData());
        SecretKey secretKey = new SecretKeySpec(data, 0, data.length, "DESede");
        return secretKey;
    }

    //Assinatura Digital RSA
    public byte[] assinarCampo(String str) throws Exception {
        Signature sgn = Signature.getInstance("MD5withRSA");
        KeyPair chaves = gerarParChaves();
        sgn.initSign(chaves.getPrivate());
        sgn.update(str.getBytes());
        return sgn.sign();
    }

    public boolean verificaAssinatura(String str, byte[] assinatura) throws Exception {
        Signature vrf = Signature.getInstance("MD5withRSA");
        KeyPair chaves = gerarParChaves();
        vrf.initVerify(chaves.getPublic());
        vrf.update(str.getBytes());
        return vrf.verify(assinatura);
    }

    //Certificado Digital
    public String encriptarCertificado(String str) throws Exception {
        FileInputStream fr = new FileInputStream("C:\\EasyBoleto\\EasyBoleto.cer");
        X509Certificate c = X509Certificate.getInstance(fr);
        c.verify(c.getPublicKey());
        c.checkValidity();
        Cipher cifra = Cipher.getInstance("RSA");
        cifra.init(Cipher.ENCRYPT_MODE, c.getPublicKey());
        byte[] data = cifra.doFinal(str.getBytes());
        char[] dataBase64 = Base64Coder.encode(data);
        return new String(dataBase64);
    }

    private String getDadosForKeyStore(String s) {
        switch (s) {
            case "FILE":
                return "C:\\EasyBoleto\\EasyBoletoKeyStore.jks";
            case "ALIAS_NAME":
                return "easyboleto";
            case "PASSWORD_ALIAS":
                return "key50200";
            case "PASSWORD_KEY_STORE":
                return "key50100";
            default:
                return null;
        }
    }

    public String decriptarCertificado(String str) throws Exception {
        byte[] data = Base64Coder.decode(str);
        
        KeyStore ks = KeyStore.getInstance("JKS");
        char[] passPhrase = getDadosForKeyStore("PASSWORD_KEY_STORE").toCharArray();
        File certificateFile = new File(getDadosForKeyStore("FILE"));
        ks.load(new FileInputStream(certificateFile), passPhrase);
        KeyPair kp = getPrivateKey(ks, getDadosForKeyStore("ALIAS_NAME"), getDadosForKeyStore("PASSWORD_ALIAS").toCharArray());
        PrivateKey privKey = kp.getPrivate();
        
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        
        return new String(cipher.doFinal(data));
    }

    private KeyPair getPrivateKey(KeyStore keystore, String alias, char[] password) throws Exception {
        Key key = keystore.getKey(alias, password);
        if (key instanceof PrivateKey) {
            Certificate cert = keystore.getCertificate(alias);
            PublicKey publicKey = cert.getPublicKey();
            return new KeyPair(publicKey, (PrivateKey) key);
        }
        return null;
    }
}

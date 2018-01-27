package Seguranca;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.security.cert.X509Certificate;

public class Seguranca {

    private byte[] chave;
    private PrivateKey chavePrivada;
    private PublicKey chavePublica;
    private String arquivoKeyStore, arquivoCertificado, aliasKeyStore, senhaKeyStore;

    public Seguranca(byte[] chave) throws Exception {
        this.chave = chave;
        verificarRepositorioChaves();
    }

    //Utilizado algoritmo Blowfish
    public byte[] encriptarSimetricamente(byte[] conteudo) throws Exception {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(chave, "Blowfish");
            Cipher cipherEnc = Cipher.getInstance("Blowfish");
            cipherEnc.init(Cipher.ENCRYPT_MODE, skeySpec);
            return cipherEnc.doFinal(conteudo);
        } catch (Exception ex) {
            throw new Exception("Não foi possível encriptar o conteúdo\n"
                    + "Verifique a chave digitada");
        }
    }

    //Utilizado algoritmo Blowfish
    public byte[] decriptarSimetricamente(byte[] conteudo) throws Exception {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(chave, "Blowfish");
            Cipher cipherDec = Cipher.getInstance("Blowfish");
            cipherDec.init(Cipher.DECRYPT_MODE, skeySpec);
            return cipherDec.doFinal(conteudo);
        } catch (Exception ex) {
            throw new Exception("Não foi possível decriptar o conteúdo\n"
                    + "Verifique a chave digitada");
        }
    }

    //Assina o conteudo digitalmente
    public byte[] assinarDigitalmente(byte[] conteudo) throws Exception {
        try {
            obterChaves(1);
            Signature sgn = Signature.getInstance("DSA");//cria uma instância do DSA
            sgn.initSign(chavePrivada);//inicializa o algoritmo de assinatura passando a chave privada
            sgn.update(conteudo);//adiciona a mensagem ao algoritmo
            return sgn.sign();//faz a assinatura
        } catch (Exception ex) {
            throw new Exception("Não foi possível assinar o conteúdo digitalmente\n"
                    + "Verifique a chave digitada");
        } finally {
            flush();
        }
    }

    //Verifica assinatura do conteudo
    public boolean verificaAssinaturaConteudo(byte[] conteudo, byte[] assinaturaOriginal) throws Exception {
        try {
            obterChaves(1);
            Signature vrf = Signature.getInstance("DSA");// cria uma instância do DSA
            vrf.initVerify(chavePublica);//inicializa a verificação passando a chave pública
            vrf.update(conteudo);//adiciona o texto ao algoritmo
            return vrf.verify(assinaturaOriginal);//faz a verificação com a assinatura passado por parâmetro
        } catch (Exception ex) {
            throw new Exception("Não foi possível verificar a assinatura do conteúdo\n"
                    + "Verifique a chave digitada");
        } finally {
            flush();
        }
    }

    //Utilizado algoritmo RSA
    public byte[] encriptarAssimetricamente(byte[] conteudo, boolean chaveCertificado) throws Exception {
        try {
            if (chaveCertificado) {
                obterChavesCertificado();
            } else {
                obterChaves(2);
            }
            Cipher cifra = Cipher.getInstance("RSA");
            cifra.init(Cipher.ENCRYPT_MODE, chavePublica);
            return cifra.doFinal(conteudo);
        } catch (Exception ex) {
            throw new Exception("Não foi possível encriptar o conteúdo\n"
                    + "Verifique a chave digitada");
        } finally {
            flush();
        }
    }

    //Utilizado algoritmo RSA
    public byte[] decriptarAssimetricamente(byte[] conteudo, boolean chaveCertificado) throws Exception {
        try {
            if (chaveCertificado) {
                obterChavesCertificado();
            } else {
                obterChaves(2);
            }
            Cipher cifra = Cipher.getInstance("RSA");
            cifra.init(Cipher.DECRYPT_MODE, chavePrivada);
            return cifra.doFinal(conteudo);
        } catch (Exception ex) {
            throw new Exception("Não foi possível decriptar o conteúdo\n"
                    + "Verifique a chave digitada");
        } finally {
            flush();
        }
    }

    //gerar um hash SHA-512
    public byte[] gerarHash(String conteudo) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(conteudo.getBytes());
        return md.digest();
    }

    //verifica se hash igual - utilizado o SHA-512
    public boolean verificarHash(byte[] hashOrig, String conteudo) throws Exception {
        byte[] resumo = gerarHash(conteudo);
        return Arrays.equals(hashOrig, resumo);
    }

    private void obterChavesCertificado() throws Exception {
        chavePublica = getChavePublicaCertificado();
        chavePrivada = getChavePrivadaKeyStore();
    }

    private PublicKey getChavePublicaCertificado() throws Exception {
        FileInputStream in = new FileInputStream(new File(arquivoCertificado));
        X509Certificate certificado = X509Certificate.getInstance(in);//cria a instância do certificado
        in.close();
        return certificado.getPublicKey();
    }

    private PrivateKey getChavePrivadaKeyStore() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");

        FileInputStream in = new FileInputStream(new File(arquivoKeyStore));

        keyStore.load(in, senhaKeyStore.toCharArray());

        in.close();

        KeyPair keyPair = getKeyPair(keyStore, aliasKeyStore, senhaKeyStore.toCharArray());

        return keyPair.getPrivate();
    }

    private KeyPair getKeyPair(KeyStore keystore, String alias, char[] senha) throws Exception {
        Key key = keystore.getKey(alias, senha);
        if (key instanceof PrivateKey) {
            Certificate cert = keystore.getCertificate(alias);
            PublicKey publicKey = cert.getPublicKey();
            return new KeyPair(publicKey, (PrivateKey) key);
        }
        return null;
    }

    private void flush() {
        chavePublica = null;
        chavePrivada = null;
        System.gc();
    }

    //Obtem o par de chaves
    private void obterChaves(int tipo) throws Exception {
        verificarRepositorioChaves();
        lerChaves(tipo);
    }

    private void lerChaves(int tipo) throws Exception {
        RandomAccessFile arq = null;
        try {
            int tam;
            byte[] bytes;
            arq = new RandomAccessFile(new File("repositorioChaves.key"), "r");
            if (tipo == 1) {//DSA
                arq.seek(0);
            } else if (tipo == 2) {//RSA
                arq.seek(1000);
            }
            tam = arq.readInt();
            bytes = getBytes(arq, tam);
            chavePublica = (PublicKey) getObjectAsBytesIn(bytes);

            tam = arq.readInt();
            bytes = getBytes(arq, tam);
            bytes = decriptarSimetricamente(bytes);
            chavePrivada = (PrivateKey) getObjectAsBytesIn(bytes);

        } finally {
            if (arq != null) {
                arq.close();
            }
        }
    }

    private byte[] getBytes(RandomAccessFile arq, int tam) throws Exception {
        byte[] bytes = new byte[tam];
        arq.read(bytes);
        return bytes;
    }

    private Object getObjectAsBytesIn(byte[] bytes) throws Exception {
        ByteArrayInputStream byteInChave = new ByteArrayInputStream(bytes);
        ObjectInputStream inChave = new ObjectInputStream(byteInChave);
        Object obj = inChave.readObject();
        inChave.close();
        byteInChave.close();
        return obj;
    }

    private void verificarRepositorioChaves() throws Exception {
        RandomAccessFile arq = null;
        try {
            File arquivo = new File("repositorioChaves.key");
            if (!arquivo.exists()) {

                arq = new RandomAccessFile(arquivo, "rw");

                generateKeyPair("DSA");//gera as chaves
                arq.seek(0);
                gravarBytesArquivo(arq);

                generateKeyPair("RSA");//gera as chaves
                arq.seek(1000);
                gravarBytesArquivo(arq);

            }
        } finally {
            if (arq != null) {
                arq.close();
            }
            flush();
        }
    }

    private void generateKeyPair(String tp) throws Exception {
        KeyPairGenerator key = null;
        if ("DSA".equals(tp)) {
            key = KeyPairGenerator.getInstance("DSA");//cria uma instância do gerador de chaves 
            key.initialize(512);//inicialização com o tamanho da chave a ser gerada
        } else if ("RSA".equals(tp)) {
            key = KeyPairGenerator.getInstance("RSA");
            key.initialize(2048);
        }
        KeyPair par = key.generateKeyPair();//gera as chaves;

        chavePublica = par.getPublic();//obtem chave publica
        chavePrivada = par.getPrivate();//obtem chave privada
    }

    private void gravarBytesArquivo(RandomAccessFile arq) throws Exception {
        byte[] bytes = getBytesAsObjectOut(false);//obtém a chave pública em bytes
        arq.writeInt(bytes.length);
        arq.write(bytes);

        bytes = getBytesAsObjectOut(true);//obtém a chave privada em bytes
        bytes = encriptarSimetricamente(bytes);
        arq.writeInt(bytes.length);
        arq.write(bytes);
    }

    private byte[] getBytesAsObjectOut(boolean flag) throws Exception {
        ByteArrayOutputStream byteOutChavePrivada = new ByteArrayOutputStream();
        ObjectOutputStream outChavePrivada = new ObjectOutputStream(byteOutChavePrivada);
        if (flag) {
            outChavePrivada.writeObject(chavePrivada);
        } else {
            outChavePrivada.writeObject(chavePublica);
        }
        outChavePrivada.close();
        byteOutChavePrivada.close();
        return byteOutChavePrivada.toByteArray();
    }

    public String getArquivoKeyStore() {
        return arquivoKeyStore;
    }

    public void setArquivoKeyStore(String arquivoKeyStore) {
        this.arquivoKeyStore = arquivoKeyStore;
    }

    public String getArquivoCertificado() {
        return arquivoCertificado;
    }

    public void setArquivoCertificado(String arquivoCertificado) {
        this.arquivoCertificado = arquivoCertificado;
    }

    public String getAliasKeyStore() {
        return aliasKeyStore;
    }

    public void setAliasKeyStore(String aliasKeyStore) {
        this.aliasKeyStore = aliasKeyStore;
    }

    public String getSenhaKeyStore() {
        return senhaKeyStore;
    }

    public void setSenhaKeyStore(String senhaKeyStore) {
        this.senhaKeyStore = senhaKeyStore;
    }
}

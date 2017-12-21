package ArquivoBinario;

import java.io.*;
import java.util.*;

public class RetanguloDAO {

    private String nomeArq;

    public RetanguloDAO(String nomeArq) throws Exception {
        this.nomeArq = nomeArq;
        File arquivo = new File(nomeArq + "Binario.dat");
        if (arquivo.exists() == false) {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(nomeArq + "Binario.dat"));
            out.writeInt(0);
            out.close();
        }
    }

    public void gravar(Retangulo retangulo) throws Exception {
        RandomAccessFile arq = new RandomAccessFile(nomeArq + ".dat", "rw");
        long posicaoArq = arq.length();
        arq.seek(arq.length());//vai para o final do arquivo
        arq.write(Arrays.copyOf(retangulo.getNome().getBytes(), 5));
        arq.writeInt(retangulo.getAltura());
        arq.writeInt(retangulo.getLargura());
        arq.close();
        gravarBinario(retangulo.getAltura() * retangulo.getLargura(), (int) posicaoArq / 13);
    }

    public Retangulo ler(int posicao) throws Exception {
        Retangulo retangulo = new Retangulo();
        RandomAccessFile arq = new RandomAccessFile(nomeArq + ".dat", "rw");
        arq.seek(13 * posicao);
        if (13 * posicao <= arq.length() - 13) {
            byte[] nome = new byte[5];
            arq.read(nome);
            int tam = 5;
            for (int i = 0; i < nome.length; i++) {
                if (nome[i] == 0) {
                    tam = i;
                    break;
                }
            }
            retangulo.setNome(new String(Arrays.copyOf(nome, tam)));
            retangulo.setAltura(arq.readInt());
            retangulo.setLargura(arq.readInt());
            arq.close();
        } else {
            throw new Exception("Registro não encontrado");
        }
        return retangulo;
    }

    private void gravarBinario(int area, int posicao) throws Exception {
        List<RetanguloBinario> lista = lerBinario();
        RetanguloBinario retanguloBinario = new RetanguloBinario();
        retanguloBinario.setArea(area);
        retanguloBinario.setPosicao(posicao);
        lista.add(retanguloBinario);
        Collections.sort(lista);
        DataOutputStream out = new DataOutputStream(new FileOutputStream(nomeArq + "Binario.dat"));
        out.writeInt(lista.size());
        for (RetanguloBinario r : lista) {
            out.writeInt(r.getArea());
            out.writeInt(r.getPosicao());
        }
        out.close();
    }

    private List<RetanguloBinario> lerBinario() throws Exception {
        List<RetanguloBinario> lista = new ArrayList<RetanguloBinario>();
        DataInputStream in = new DataInputStream(new FileInputStream(nomeArq + "Binario.dat"));
        int qtdadeRet = in.readInt();
        for (int i = 0; i < qtdadeRet; i++) {
            RetanguloBinario retBin = new RetanguloBinario();
            retBin.setArea(in.readInt());
            retBin.setPosicao(in.readInt());
            lista.add(retBin);
        }
        in.close();
        return lista;
    }

    public List<Retangulo> getRetanguloArea(int area) throws Exception {
        List<Retangulo> lista = new ArrayList<Retangulo>();
        File arquivo = new File(nomeArq + "Binario.dat");
        DataInputStream in = new DataInputStream(new FileInputStream(arquivo));
        int qtdadeRet = in.readInt();
        for (int i = 0; i < qtdadeRet; i++) {
            if (in.readInt() == area) {
                lista.add(ler(in.readInt()));
            } else {
                in.readInt();
            }
        }
        in.close();
        return lista;
    }

    public int qtdadeRetangulo() throws Exception {
        File arquivo = new File(nomeArq + "Binario.dat");
        DataInputStream in = new DataInputStream(new FileInputStream(arquivo));
        int qtdade = in.readInt();
        in.close();
        return qtdade;
    }
}

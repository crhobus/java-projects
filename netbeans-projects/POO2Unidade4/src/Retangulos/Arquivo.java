package Retangulos;

import java.io.*;
import java.util.*;

public class Arquivo {

    public List<Retangulo> lerTodosRetangulo() throws Exception {
        List<Retangulo> lista = new ArrayList<Retangulo>();
        RandomAccessFile arq = new RandomAccessFile("retang.dat", "rw");
        int i = 0;
        while (i < ((int) arq.length() / 11)) {
            Retangulo retangulo = new Retangulo();
            byte[] nome = new byte[3];
            arq.read(nome);
            int tam = 3;
            for (int j = 0; j < nome.length; j++) {
                if (nome[j] == 0) {
                    tam = j;
                    break;
                }
            }
            retangulo.setNome(new String(Arrays.copyOf(nome, tam)));
            retangulo.setAltura(arq.readInt());
            retangulo.setLargura(arq.readInt());
            lista.add(retangulo);
            i++;
        }
        arq.close();
        return lista;
    }

    public Retangulo lerRetangulo(int posicao) throws Exception {
        RandomAccessFile arq = new RandomAccessFile("retang.dat", "rw");
        arq.seek(11 * posicao);
        Retangulo retangulo = new Retangulo();
        byte[] nome = new byte[3];
        arq.read(nome);
        int tam = 3;
        for (int j = 0; j < nome.length; j++) {
            if (nome[j] == 0) {
                tam = j;
                break;
            }
        }
        retangulo.setNome(new String(Arrays.copyOf(nome, tam)));
        retangulo.setAltura(arq.readInt());
        retangulo.setLargura(arq.readInt());
        arq.close();
        return retangulo;
    }

    public List<AreaPosicaoRet> getAreaPosicao(int limiteInf, int limiteSup) throws Exception {
        List<AreaPosicaoRet> lista = new ArrayList<AreaPosicaoRet>();
        RandomAccessFile arq = new RandomAccessFile("retang.dat", "rw");
        int i = 0;
        while (i < ((int) arq.length() / 11)) {
            AreaPosicaoRet areaPosicaoRet = new AreaPosicaoRet();
            areaPosicaoRet.setPosicao(i);
            areaPosicaoRet.setArea(lerRetangulo(i).getAltura() * lerRetangulo(i).getLargura());
            if (limiteInf <= areaPosicaoRet.getArea()) {
                if (limiteSup >= areaPosicaoRet.getArea()) {
                    lista.add(areaPosicaoRet);
                }
            }
            i++;
        }
        arq.close();
        return lista;
    }
}

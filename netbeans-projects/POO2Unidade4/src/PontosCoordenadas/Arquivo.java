package PontosCoordenadas;

import java.io.*;
import java.util.*;

public class Arquivo {

    public Set<Pontos> lerPontos() throws Exception {
        Set<Pontos> lista = new HashSet<Pontos>();
        DataInputStream in = new DataInputStream(new FileInputStream("pontos.dat"));
        int qtdadePontos = in.readInt();
        for (int i = 0; i < qtdadePontos; i++) {
            Pontos pontos = new Pontos();
            pontos.setNome(in.readUTF());
            pontos.setX(in.readInt());
            pontos.setY(in.readInt());
            lista.add(pontos);
        }
        in.close();
        return lista;
    }

    public List<Coordenadas> lerCoordenadas() throws Exception {
        List<Coordenadas> lista = new ArrayList<Coordenadas>();
        FileReader fileReader = new FileReader(new File("coord.txt"));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            String[] split = s.split(",");
            Coordenadas coord = new Coordenadas();
            coord.setX(Integer.parseInt(split[0]));
            coord.setY(Integer.parseInt(split[1]));
            lista.add(coord);
        }
        bufferedReader.close();
        return lista;
    }
}

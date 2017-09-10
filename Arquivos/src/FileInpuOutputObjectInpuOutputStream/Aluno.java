package FileInpuOutputObjectInpuOutputStream;

import java.io.*;

public class Aluno implements Serializable {

    int matric;
    String nome;
    int curso;
    char sexo;

    Aluno(int mtr, String nm, int crs, char sx) {
        matric = mtr;
        nome = nm;
        curso = crs;
        sexo = sx;
    }
}

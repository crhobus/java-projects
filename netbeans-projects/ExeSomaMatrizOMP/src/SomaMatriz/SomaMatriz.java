package SomaMatriz;

import jomp.runtime.OMP;

public class SomaMatriz {

    public static void main(String[] args) {
        int matriz[][] = new int[4][4];
        matriz[0][0] = 56;
        matriz[0][1] = 5;
        matriz[0][2] = 4;
        matriz[0][3] = 3;
        matriz[1][0] = 8;
        matriz[1][1] = 10;
        matriz[1][2] = 21;
        matriz[1][3] = 74;
        matriz[2][0] = 56;
        matriz[2][1] = 90;
        matriz[2][2] = 100;
        matriz[2][3] = 255;
        matriz[3][0] = 30;
        matriz[3][1] = 40;
        matriz[3][2] = 21;
        matriz[3][3] = 12;

        int soma = 0;
        int myid;
        OMP.setNumThreads(4);

        //omp parallel private(myid) reduction(+:soma)
        {
            for (int i = 0; i < matriz.length; i++) {
                myid = OMP.getThreadNum();
                soma += matriz[myid][i];
                System.out.println("Elemento da linha: " + myid + " coluna: " + i);
            }
        }
        System.out.println("Soma dos valores: " + soma);
    }
}

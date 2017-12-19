package SomaMatriz;

import jomp.runtime.OMP;

public class SomaMatriz_jomp {

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
        OMP.setNumThreads(4);

        // OMP PARALLEL BLOCK BEGINS
        {
            __omp_Class0 __omp_Object0 = new __omp_Class0();
            // shared variables
            __omp_Object0.matriz = matriz;
            __omp_Object0.args = args;
            // firstprivate variables
            try {
                jomp.runtime.OMP.doParallel(__omp_Object0);
            } catch (Throwable __omp_exception) {
                System.err.println("OMP Warning: Illegal thread exception ignored!");
                System.err.println(__omp_exception);
            }
            // reduction variables
            soma += __omp_Object0._rd_soma;
            // shared variables
            matriz = __omp_Object0.matriz;
            args = __omp_Object0.args;
        }
        // OMP PARALLEL BLOCK ENDS
        System.out.println("Soma dos valores: " + soma);
    }

    // OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
    private static class __omp_Class0 extends jomp.runtime.BusyTask {
        // shared variables

        int[][] matriz;
        String[] args;
        // firstprivate variables
        // variables to hold results of reduction
        int _rd_soma;

        public void go(int __omp_me) throws Throwable {
            // firstprivate variables + init
            // private variables
            int myid;
            // reduction variables, init to default
            int soma = 0;
            // OMP USER CODE BEGINS
            {
                for (int i = 0; i < matriz.length; i++) {
                    myid = OMP.getThreadNum();
                    soma += matriz[myid][i];
                    System.out.println("Elemento da linha: " + myid + " coluna: " + i);
                }
            }
            // OMP USER CODE ENDS
            // call reducer
            soma = (int) jomp.runtime.OMP.doPlusReduce(__omp_me, soma);
            // output to _rd_ copy
            if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                _rd_soma = soma;
            }
        }
    }
    // OMP PARALLEL REGION INNER CLASS DEFINITION ENDS
}

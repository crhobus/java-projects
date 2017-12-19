package MultiplicacaoMatriz;

import jomp.runtime.OMP;

public class MultiplicacaoMatriz_jomp {

    public static void main(String[] args) {
        int matriz[][] = new int[4][4];
        int matrizResult[][] = new int[4][4];
        matriz[0][0] = 55;
        matriz[0][1] = 15;
        matriz[0][2] = 546;
        matriz[0][3] = 56;
        matriz[1][0] = 54;
        matriz[1][1] = 13;
        matriz[1][2] = 23;
        matriz[1][3] = 10;
        matriz[2][0] = 49;
        matriz[2][1] = 57;
        matriz[2][2] = 102;
        matriz[2][3] = 25;
        matriz[3][0] = 23;
        matriz[3][1] = 45;
        matriz[3][2] = 24;
        matriz[3][3] = 15;

        System.out.println("Matriz original: ");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("[");
            for (int y = 0; y < matriz.length; y++) {
                System.out.printf("%3d  ", matriz[i][y]);
            }
            System.out.println("]");
        }
        System.out.println();
        OMP.setNumThreads(4);

        // OMP PARALLEL BLOCK BEGINS
        {
            __omp_Class0 __omp_Object0 = new __omp_Class0();
            // shared variables
            __omp_Object0.matrizResult = matrizResult;
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
            // shared variables
            matrizResult = __omp_Object0.matrizResult;
            matriz = __omp_Object0.matriz;
            args = __omp_Object0.args;
        }
        // OMP PARALLEL BLOCK ENDS

        System.out.println();
        System.out.println("Matriz multiplicada por 4: ");
        for (int i = 0; i < matrizResult.length; i++) {
            System.out.print("[");
            for (int y = 0; y < matrizResult.length; y++) {
                System.out.printf("%4d  ", matrizResult[i][y]);
            }
            System.out.println("]");
        }
    }

    // OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
    private static class __omp_Class0 extends jomp.runtime.BusyTask {
        // shared variables

        int[][] matrizResult;
        int[][] matriz;
        String[] args;

        // firstprivate variables
        // variables to hold results of reduction
        public void go(int __omp_me) throws Throwable {
            // firstprivate variables + init
            // private variables
            int myid;
            // reduction variables, init to default
            // OMP USER CODE BEGINS

            {
                for (int i = 0; i < matriz.length; i++) {
                    myid = OMP.getThreadNum();
                    matrizResult[myid][i] = matriz[myid][i] * 4;
                    System.out.println("Elemento da linha: " + myid + " coluna: " + i);
                }
            }
            // OMP USER CODE ENDS
            // call reducer
            // output to _rd_ copy
            if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
            }
        }
    }
    // OMP PARALLEL REGION INNER CLASS DEFINITION ENDS
}

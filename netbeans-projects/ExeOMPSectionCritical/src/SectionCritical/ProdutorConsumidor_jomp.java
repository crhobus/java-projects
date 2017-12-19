package SectionCritical;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jomp.runtime.OMP;

public class ProdutorConsumidor_jomp {

    public static void main(String[] args) {
        List lista = new ArrayList();
        Random random = new Random();
        OMP.setNumThreads(5);

        // OMP PARALLEL BLOCK BEGINS
        {
            __omp_Class0 __omp_Object0 = new __omp_Class0();
            // shared variables
            __omp_Object0.random = random;
            __omp_Object0.lista = lista;
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
            random = __omp_Object0.random;
            lista = __omp_Object0.lista;
            args = __omp_Object0.args;
        }
        // OMP PARALLEL BLOCK ENDS

    }

    // OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
    private static class __omp_Class0 extends jomp.runtime.BusyTask {
        // shared variables

        int num;
        Random random;
        List lista;
        String[] args;

        // firstprivate variables
        // variables to hold results of reduction
        public void go(int __omp_me) throws Throwable {
            // firstprivate variables + init
            // private variables
            int myid;
            // reduction variables, init to default
            // OMP USER CODE BEGINS

            { // OMP SECTIONS BLOCK BEGINS
                // copy of firstprivate variables, initialized
                // copy of lastprivate variables
                // variables to hold result of reduction
                boolean amLast = false;
                {
                    // firstprivate variables + init
                    // [last]private variables
                    // reduction variables + init to default
                    // -------------------------------------
                    __ompName_1:
                    while (true) {
                        switch ((int) jomp.runtime.OMP.getTicket(__omp_me)) {
                            // OMP SECTION BLOCK 0 BEGINS
                            case 0: {
                                // OMP USER CODE BEGINS
                                {
                                    myid = OMP.getThreadNum();
                                    for (int i = 0; i < 100; i++) {
                                        num = random.nextInt();
                                        // OMP CRITICAL BLOCK BEGINS
                                        synchronized (jomp.runtime.OMP.getLockByName("")) {
                                            // OMP USER CODE BEGINS
                                            {
                                                lista.add(num);
                                            }
                                            // OMP USER CODE ENDS
                                        }
                                        // OMP CRITICAL BLOCK ENDS
                                        System.out.println("A thread " + myid + " produziu " + num);
                                    }
                                }
                                // OMP USER CODE ENDS
                            }
                            ;
                            break;
                            // OMP SECTION BLOCK 0 ENDS
                            // OMP SECTION BLOCK 1 BEGINS
                            case 1: {
                                // OMP USER CODE BEGINS
                                {
                                    myid = OMP.getThreadNum();
                                    for (int i = 0; i < 100; i++) {
                                        while (lista.isEmpty());
                                        // OMP CRITICAL BLOCK BEGINS
                                        synchronized (jomp.runtime.OMP.getLockByName("")) {
                                            // OMP USER CODE BEGINS
                                            {
                                                num = (Integer) lista.remove(lista.size() - 1);
                                            }
                                            // OMP USER CODE ENDS
                                        }
                                        // OMP CRITICAL BLOCK ENDS
                                        System.out.println("A thread " + myid + " consumiu " + num);
                                    }
                                }
                                // OMP USER CODE ENDS
                                amLast = true;
                            }
                            ;
                            break;
                            // OMP SECTION BLOCK 1 ENDS

                            default:
                                break __ompName_1;
                        } // of switch
                    } // of while
                    // call reducer
                    jomp.runtime.OMP.resetTicket(__omp_me);
                    jomp.runtime.OMP.doBarrier(__omp_me);
                    // copy lastprivate variables out
                    if (amLast) {
                    }
                }
                // update lastprivate variables
                if (amLast) {
                }
                // update reduction variables
                if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                }
            } // OMP SECTIONS BLOCK ENDS
            // OMP USER CODE ENDS
            // call reducer
            // output to _rd_ copy
            if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
            }
        }
    }
    // OMP PARALLEL REGION INNER CLASS DEFINITION ENDS
}

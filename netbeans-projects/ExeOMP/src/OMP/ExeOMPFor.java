package OMP;

import jomp.runtime.OMP;

public class ExeOMPFor {

    public static void main(String[] args) {
        int i, n = 10;
        int myid;
        OMP.setNumThreads(10);

        //omp parallel private(myid)
        {
            //omp for
            for (i = 0; i < n; i++) {
                myid = OMP.getThreadNum();
                System.out.println("Thread " + myid + ": hello do elemento " + i);
            }
        }
    }
}

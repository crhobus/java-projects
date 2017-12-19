package SectionCritical;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jomp.runtime.OMP;

public class ProdutorConsumidor {

    public static void main(String[] args) {
        List lista = new ArrayList();
        Random random = new Random();
        int myid;
        int num;
        OMP.setNumThreads(5);

        //omp parallel sections private(myid)
        {
            //omp section
            {
                myid = OMP.getThreadNum();
                for (int i = 0; i < 100; i++) {
                    num = random.nextInt();
                    //omp critical
                    {
                        lista.add(num);
                    }
                    System.out.println("A thread " + myid + " produziu " + num);
                }
            }
            //omp section
            {
                myid = OMP.getThreadNum();
                for (int i = 0; i < 100; i++) {
                    while (lista.isEmpty());
                    //omp critical
                    {
                        num = (Integer) lista.remove(lista.size() - 1);
                    }
                    System.out.println("A thread " + myid + " consumiu " + num);
                }
            }
        }
    }
}

package Ordenacao;

public class Ordenacao {

    public int[] bubbleSort(int v[]) {
        int i, j;
        int n = v.length;
        for (i = n - 1; i > 1; i--) {
            for (j = 0; j < i; j++) {
                if (v[j] > v[j + 1]) {
                    int temp = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = temp;
                }
            }
        }
        return v;
    }

    public int[] bubbleSort2(int v[]) {
        int i, j;
        int n = v.length;
        for (i = n - 1; i > 1; i--) {
            boolean troca = false;
            for (j = 0; j < i; j++) {
                if (v[j] > v[j + 1]) {
                    int temp = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = temp;
                    troca = true;
                }
            }
            if (troca != true) {
                return v;
            }
        }
        return v;
    }

    public void bubbleSortRecursivo(int n, int v[]) {
        int j;
        boolean troca = false;
        for (j = 0; j < n - 1; j++) {
            if (v[j] > v[j + 1]) {
                int temp = v[j];
                v[j] = v[j + 1];
                v[j + 1] = temp;
                troca = true;
            }
        }
        if (troca == true) {
            bubbleSortRecursivo(n - 1, v);
        }
    }

    private void troca(int v[], int a, int b) {
        int temp = v[a];
        v[a] = v[b];
        v[b] = temp;
    }

    private void quickSort(int v[], int a, int b) {
        if (a >= b) {
            return;
        }
        int indicePivo = particiona(v, a, b);
        quickSort(v, a, indicePivo);
        quickSort(v, indicePivo + 1, b);
    }

    private int particiona(int v[], int a, int b) {
        int x = v[a];
        while (a < b) {
            while (v[a] < x) {
                a++;
            }
            while (v[b] > x) {
                b--;
            }
            troca(v, a, b);
        }
        return a;
    }

    public void quickSort(int v[]) {
        quickSort(v, 0, v.length - 1);
    }

    private void merge(int a[], int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int vetor1[] = new int[n1 + 1];
        int vetor2[] = new int[n2 + 1];
        for (int i = 0; i < n1; i++) {
            vetor1[i] = a[p + i];
        }
        for (int j = 0; j < n2; j++) {
            vetor2[j] = a[q + j + 1];
        }
        vetor1[n1] = 999999999;
        vetor2[n2] = 999999999;
        int x = 0;
        int y = 0;
        for (int k = p; k <= r; k++) {
            if (vetor1[x] <= vetor2[y]) {
                a[k] = vetor1[x];
                x++;
            } else {
                a[k] = vetor2[y];
                y++;
            }
        }
    }

    public void mergeSort(int a[], int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(a, p, q);
            mergeSort(a, q + 1, r);
            merge(a, p, q, r);
        }
    }
}

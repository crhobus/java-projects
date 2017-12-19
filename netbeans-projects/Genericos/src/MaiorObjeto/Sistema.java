package MaiorObjeto;

public class Sistema {

    public static <T extends Comparable<T>> T maximo(T x, T y, T z) {
        T max = x;
        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.printf("Máximo de %d, %d e %d é %d\n\n", 3, 4, 5, maximo(3, 4, 5));
        System.out.printf("Máximo de %.1f, %.1f e %.1f é %.1f\n\n", 6.6, 8.8, 7.7, maximo(6.6, 8.8, 7.7));
        System.out.printf("Máximo de %s, %s e %s é %s\n", "pera", "maçã", "laranja", maximo("pera", "maçã", "laranja"));
    }
}

package TorresHanoiRecursivo;

public class Sistema {

    public static void main(String[] args) {
        int iniciarPeg = 1;
        int finalPeg = 3;
        int tempPeg = 2;
        int totalDisco = 3;
        TorresHanoi torresHanoi = new TorresHanoi(totalDisco);
        torresHanoi.resolvaTorres(totalDisco, iniciarPeg, finalPeg, tempPeg);
    }
}

package TorresHanoiRecursivo;

public class TorresHanoi {

    int numDisco;

    public TorresHanoi(int numDisco) {
        this.numDisco = numDisco;
    }

    public void resolvaTorres(int disco, int fontePeg, int destinoPeg, int tempPeg) {
        if (disco == 1) {
            System.out.printf("\n%d --> %d", fontePeg, destinoPeg);
            return;
        }
        resolvaTorres(disco - 1, fontePeg, tempPeg, destinoPeg);
        System.out.printf("\n%d --> %d", fontePeg, destinoPeg);
        resolvaTorres(disco - 1, tempPeg, destinoPeg, fontePeg);
    }
}

package BuscaArvoreBinaria;

public class NoArvoreBinaria {

    private int info;
    NoArvoreBinaria esq;
    NoArvoreBinaria dir;

    public NoArvoreBinaria(int info) {
        this.info = info;
        this.esq = null;
        this.dir = null;
    }

    public NoArvoreBinaria(int info, NoArvoreBinaria esq, NoArvoreBinaria dir) {
        this.info = info;
        this.esq = esq;
        this.dir = dir;
    }

    public NoArvoreBinaria getDir() {
        return dir;
    }

    public void setDir(NoArvoreBinaria dir) {
        this.dir = dir;
    }

    public NoArvoreBinaria getEsq() {
        return esq;
    }

    public void setEsq(NoArvoreBinaria esq) {
        this.esq = esq;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info + "";
    }
}

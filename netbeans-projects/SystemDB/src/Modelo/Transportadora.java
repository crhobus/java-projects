package Modelo;

public class Transportadora extends Pessoa {

    private int codTrans;
    private String fax;
    private double frete;

    public int getCodTrans() {
        return codTrans;
    }

    public void setCodTrans(int codTrans) {
        this.codTrans = codTrans;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }
}

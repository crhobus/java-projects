package Modelo;

public class Setor {

    private int codSetor;
    private String setor;
    private byte[] setorAssinadoDigital;

    public int getCodSetor() {
        return codSetor;
    }

    public void setCodSetor(int codSetor) {
        this.codSetor = codSetor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public byte[] getSetorAssinadoDigital() {
        return setorAssinadoDigital;
    }

    public void setSetorAssinadoDigital(byte[] setorAssinadoDigital) {
        this.setorAssinadoDigital = setorAssinadoDigital;
    }
}

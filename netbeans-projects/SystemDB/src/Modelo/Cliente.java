package Modelo;

public class Cliente extends Pessoa {

    private int codClie;
    private String empresa, foneEmpresa, profissao, fax;
    private double renda;

    public int getCodClie() {
        return codClie;
    }

    public void setCodClie(int codClie) {
        this.codClie = codClie;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFoneEmpresa() {
        return foneEmpresa;
    }

    public void setFoneEmpresa(String foneEmpresa) {
        this.foneEmpresa = foneEmpresa;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }
}

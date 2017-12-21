package Exe4AgenciaEmpregoPadraoObserver;

public class Vaga {

    private String area, empresa;
    private float salario;

    public Vaga(String area, String empresa, float salario) {
        this.area = area;
        this.empresa = empresa;
        this.salario = salario;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
}

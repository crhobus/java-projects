package Interface;

public class SalarioFuncionario extends Empregado {

    private double salarioSemana;

    public SalarioFuncionario(String primairo, String ultimo, String numeroSegurancaSocial, double salario) {
        super(primairo, ultimo, numeroSegurancaSocial);
        setSalarioSemana(salario);
    }

    public double getSalarioSemana() {
        return salarioSemana;
    }

    public void setSalarioSemana(double salarioSemana) {
        this.salarioSemana = salarioSemana;
    }

    public double getQtdadePagto() {
        return getSalarioSemana();
    }

    @Override
    public String toString() {
        return String.format("salários dos funcionários: %s\n%s: $%,.2f", super.toString(), "salário semanal", getSalarioSemana());
    }
}

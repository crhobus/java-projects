package Interface;

public abstract class Empregado implements APagar {

    private String primeiroNome;
    private String sobrenome;
    private String numeroSegurancaSocial;

    public Empregado(String primeiroNome, String sobrenome, String numeroSegurancaSocial) {
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.numeroSegurancaSocial = numeroSegurancaSocial;
    }

    public String getNumeroSegurancaSocial() {
        return numeroSegurancaSocial;
    }

    public void setNumeroSegurancaSocial(String numeroSegurancaSocial) {
        this.numeroSegurancaSocial = numeroSegurancaSocial;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        return String.format("%s %s\nNumero Seguranca Social: %s", getPrimeiroNome(), getSobrenome(), getNumeroSegurancaSocial());
    }
}

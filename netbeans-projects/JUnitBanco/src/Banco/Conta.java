package Banco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conta {

    protected double saldo;
    private String proprietario;
    private String numero;
    private List<Movimentacao> listaMov = new ArrayList<Movimentacao>();

    public void depositar(double valor) throws Exception {
        if (valor <= 0) {
            throw new Exception("Número deve ser maior que 0");
        }
        saldo += valor;
        Movimentacao movimentacao = new Movimentacao(new Date(), valor, "Credito");
        listaMov.add(movimentacao);
    }

    public double sacar(double valor) throws Exception {
        if (valor <= 0) {
            throw new Exception("Número deve ser maior que 0");
        }
        if (saldo >= valor) {
            saldo -= valor;
        } else {
            throw new Exception("saldo insuficiente");
        }
        Movimentacao movimentacao = new Movimentacao(new Date(), valor, "Debito");
        listaMov.add(movimentacao);
        return valor;
    }

    public String extrato() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        String str = "Proprietário: " + getProprietario() + "\nNúmero: " + getNumero() + "\n---------------------------------\n";
        for (Movimentacao movimentacao : listaMov) {
            if (movimentacao.getTipo().equals("Credito")) {
                str += formatDate.format(movimentacao.getData()) + "   " + formatHora.format(movimentacao.getData()) + "\t+ " + movimentacao.getValor() + "\n";
            } else {
                str += formatDate.format(movimentacao.getData()) + "   " + formatHora.format(movimentacao.getData()) + "\t- " + movimentacao.getValor() + "\n";
            }
        }
        str += "---------------------------------\n";
        str += saldo;
        return str;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

package br.com.model.beans;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Veiculo {

    private Long nrSequencia;
    @NotNull(message = "{veiculo.modelo}")
    private String modelo;
    @NotNull(message = "{veiculo.marca}")
    private String marca;
    @NotNull(message = "{veiculo.anoFabricacao}")
    @Min(value = 1900, message = "{veiculo.anoFabricacao.intervalo}")
    @Max(value = 2999, message = "{veiculo.anoFabricacao.intervalo}")
    private Integer anoFabricacao;
    @NotNull(message = "{veiculo.anoModelo}")
    @Min(value = 1900, message = "{veiculo.anoModelo.intervalo}")
    @Max(value = 2999, message = "{veiculo.anoModelo.intervalo}")
    private Integer anoModelo;
    @NotNull(message = "{veiculo.cor}")
    private String cor;
    @NotNull(message = "{veiculo.combustivel}")
    private String combustivel;//Gasolina/Álcool/Flex/Diesel
    @NotNull(message = "{veiculo.portas}")
    @Min(value = 1, message = "{veiculo.portas.intervalo}")
    @Max(value = 9, message = "{veiculo.portas.intervalo}")
    private Integer portas;
    @NotNull(message = "{veiculo.litros}")
    @DecimalMin(value = "1.0", message = "{veiculo.litros.intervalo}")
    @DecimalMax(value = "8.0", message = "{veiculo.litros.intervalo}")
    private BigDecimal litros;//Ex. 1.0/1.6/1.8/2.0
    @NotNull(message = "{veiculo.potencia}")
    @Min(value = 10, message = "{veiculo.potencia.min}")
    private Integer potencia;//Em cv
    @NotNull(message = "{veiculo.cilindros}")
    @Min(value = 1, message = "{veiculo.cilindros.intervalo}")
    @Max(value = 10, message = "{veiculo.cilindros.intervalo}")
    private Integer cilindros;//Ex. 3/4/5/6/8
    @NotNull(message = "{veiculo.valvulas}")
    @Min(value = 8, message = "{veiculo.valvulas.intervalo}")
    @Max(value = 20, message = "{veiculo.valvulas.intervalo}")
    private Integer valvulas;//8V/16V/20V
    @NotNull(message = "{veiculo.categoria}")
    private String categoria;//Básico/Intermediário/Completo - Ex. Civic tem o LXL/LXS/SI
    @NotNull(message = "{veiculo.lotacao}")
    @Min(value = 1, message = "{veiculo.lotacao.intervalo}")
    @Max(value = 8, message = "{veiculo.lotacao.intervalo}")
    private Integer lotacao;//Ex. 2 pessoas/5 pessoas
    @NotNull(message = "{veiculo.valor}")
    @DecimalMin(value = "1.0", message = "{veiculo.valor.min}")
    private BigDecimal valor;

    public Long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(Long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public Integer getPortas() {
        return portas;
    }

    public void setPortas(Integer portas) {
        this.portas = portas;
    }

    public BigDecimal getLitros() {
        return litros;
    }

    public void setLitros(BigDecimal litros) {
        this.litros = litros;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public Integer getCilindros() {
        return cilindros;
    }

    public void setCilindros(Integer cilindros) {
        this.cilindros = cilindros;
    }

    public Integer getValvulas() {
        return valvulas;
    }

    public void setValvulas(Integer valvulas) {
        this.valvulas = valvulas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getLotacao() {
        return lotacao;
    }

    public void setLotacao(Integer lotacao) {
        this.lotacao = lotacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}

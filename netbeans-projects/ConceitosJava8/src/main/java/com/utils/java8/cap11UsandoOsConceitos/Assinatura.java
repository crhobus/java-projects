package com.utils.java8.cap11UsandoOsConceitos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 *
 * @author crhobus
 */
public class Assinatura {

    private BigDecimal taxaMensal;
    private LocalDateTime dtInicio;
    private Optional<LocalDateTime> dtfim;
    private Cliente cliente;

    public Assinatura(BigDecimal taxaMensal, LocalDateTime dtInicio, Cliente cliente) {
        this.taxaMensal = taxaMensal;
        this.dtInicio = dtInicio;
        this.dtfim = Optional.empty();
        this.cliente = cliente;
    }

    public Assinatura(BigDecimal taxaMensal, LocalDateTime dtInicio, LocalDateTime dtfim, Cliente cliente) {
        this.taxaMensal = taxaMensal;
        this.dtInicio = dtInicio;
        this.dtfim = Optional.of(dtfim);
        this.cliente = cliente;
    }

    public BigDecimal getTaxaMensal() {
        return taxaMensal;
    }

    public LocalDateTime getDtInicio() {
        return dtInicio;
    }

    public Optional<LocalDateTime> getDtfim() {
        return dtfim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal obterVlTotalPago() {
        return getTaxaMensal()
                .multiply(new BigDecimal(ChronoUnit.MONTHS
                        .between(getDtInicio(), getDtfim().orElse(LocalDateTime.now()))));
    }
}

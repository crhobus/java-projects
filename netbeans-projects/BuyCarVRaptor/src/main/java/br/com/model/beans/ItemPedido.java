package br.com.model.beans;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class ItemPedido {

    private Long nrSequencia;
    private Opcional opcional;
    @NotNull(message = "{itemPedido.valorOpcional}")
    @DecimalMin(value = "1.0", message = "{itemPedido.valorOpcional.min}")
    private BigDecimal valorOpcional;

    public Long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(Long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }

    public Opcional getOpcional() {
        return opcional;
    }

    public void setOpcional(Opcional opcional) {
        this.opcional = opcional;
    }

    public BigDecimal getValorOpcional() {
        return valorOpcional;
    }

    public void setValorOpcional(BigDecimal valorOpcional) {
        this.valorOpcional = valorOpcional;
    }
}

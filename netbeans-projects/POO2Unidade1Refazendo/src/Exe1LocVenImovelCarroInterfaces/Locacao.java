package Exe1LocVenImovelCarroInterfaces;

import java.util.Date;

public class Locacao {

    private Date dataInicio;
    private Date dataFim;
    private ItemLocavel itemLocavel;

    public double getValorUnit() {
        return itemLocavel.getValorLocacao();
    }

    public double getValorTotal() {
        return (((dataFim.getTime() - dataInicio.getTime()) / 86400000) + 1) * getValorUnit();
    }
    
    public ItemLocavel getItemLocavel() {
        return itemLocavel;
    }

    public void setItemLocavel(ItemLocavel itemLocavel) {
        this.itemLocavel = itemLocavel;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

}

package Exe1LocVenImovelCarroInterfaces;

import java.util.Date;

public class Venda {

    private Date dataVenda;
    private double valorNegociado;
    private ItemVenda itemVenda;

    public double getValorBase(){
        return itemVenda.getValorBase();
    }

    public double getValorNegociado() {
        return valorNegociado;
    }

    public void setValorNegociado(double valorNegociado) {
        this.valorNegociado = valorNegociado;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }
}

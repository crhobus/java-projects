package CadastroPedido;

import Imposto.Imposto;
import Regiao.CentroOesteFactory;
import Regiao.NordesteFactory;
import Regiao.NorteFactory;
import Regiao.RegiaoFactory;
import Regiao.SulFactory;
import Regiao.SuldesteFactory;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {

    private Cliente cliente;
    private Date dataEmissao;
    private List<ItemPedido> itemPedido = new ArrayList<ItemPedido>();

    public Pedido(Cliente cliente, Date dataEmissao) {
        this.cliente = cliente;
        this.dataEmissao = dataEmissao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public double getValorPedido() {
        double valor = 0;
        for (ItemPedido item : itemPedido) {
            valor = valor + item.getValorItem();
        }
        return valor;
    }

    public double getPesoPedido() {
        double peso = 0;
        for (ItemPedido item : itemPedido) {
            peso = peso + item.getPesoItem();
        }
        return peso;
    }

    public List<ItemPedido> getItemPedido() {
        return itemPedido;
    }

    public void addItem(ItemPedido itemPedido) {
        this.itemPedido.add(itemPedido);
    }

    private RegiaoFactory getRegiaoFactory(Regiao regiao) {
        switch (regiao) {
            case SUL:
                return new SulFactory();
            case SULDESTE:
                return new SuldesteFactory();
            case CENTROOESTE:
                return new CentroOesteFactory();
            case NORTE:
                return new NorteFactory();
            case NORDESTE:
                return new NordesteFactory();
            default:
                return null;
        }
    }

    public void imprime() {
        SimpleDateFormat sDataf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sHoraf = new SimpleDateFormat("HH:mm");
        NumberFormat nRealf = NumberFormat.getCurrencyInstance();
        System.out.println("Nome: " + cliente.getNome() + ", Data Emissao: " + sDataf.format(dataEmissao).toString() + " as " + sHoraf.format(dataEmissao).toString() + ", Região: " + cliente.getRegiao());
        for (ItemPedido item : itemPedido) {
            System.out.println("Produto: " + item.getProduto() + ", Quantidade: " + item.getQtdade() + ", Valor Total: " + item.getValorItem() + ", Peso Total: " + item.getPesoItem());
        }
        System.out.println("Valor sem Imposto: " + nRealf.format(getValorPedido()).toString());
        System.out.println("Valor sem Frete: " + nRealf.format(getPesoPedido()).toString());
        System.out.println("Valor com Imposto: " + nRealf.format(getRegiaoFactory(Regiao.NORDESTE).getImposto().getValorImposto(getValorPedido())).toString());
        System.out.println("Valor com Frete: " + nRealf.format(getRegiaoFactory(cliente.getRegiao()).getFrete().getValorFrete(getPesoPedido(), getValorPedido())).toString());
    }
}

package model.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_pedido", columnDefinition = "number(10)")
    private int cdPedido;
    @Column(name = "ds_observacao", columnDefinition = "varchar2(150)", nullable = false, unique = true)
    private String dsObservacao;
    @Column(name = "dt_cadastro", columnDefinition = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadastro;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_cliente", columnDefinition = "number(10)", nullable = false)
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_pedido", columnDefinition = "number(10)")
    private List<ItemPedido> itens;

    public int getCdPedido() {
        return cdPedido;
    }

    public void setCdPedido(int cdPedido) {
        this.cdPedido = cdPedido;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void addItem(ItemPedido item) {
        if(itens == null){
            itens = new ArrayList();
        }
        this.itens.add(item);
    }
}

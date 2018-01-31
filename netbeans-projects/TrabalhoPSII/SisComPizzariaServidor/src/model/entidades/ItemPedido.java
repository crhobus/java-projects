/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

    // Código do itemPedido
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_item_pedido", columnDefinition = "number(10)")
    private int cdItem;
    // Codigo do pedido
    @Column(name = "cd_pedido", columnDefinition = "number(10)")
    private int cdPedido;
    // Sabor
    @OneToMany
    @JoinColumn(name = "cd_sabor")
    private List<Sabor> sabores;
    // Tamanho
    @ManyToOne
    @JoinColumn(name = "cd_tamanho")
    private Tamanho tamanho;
    // Valor do pedido
    @Column(name = "vl_pedido", columnDefinition = "number(10,3)")
    private double vlPedido;

    public int getCodItem() {
        return cdItem;
    }

    public void setCodItem(int codItem) {
        this.cdItem = codItem;
    }

    public List<Sabor> getSabor() {
        return sabores;
    }

    public void addSabor(Sabor sabor) {
        if (sabores == null) {
            sabores = new ArrayList();
        }
        this.sabores.add(sabor);
    }

    public void setSabor(List<Sabor> sabor) {
        this.setSabor(sabor);
    }

    public int getCodPedido() {
        return cdPedido;
    }

    public void setCodPedido(int codPedido) {
        this.cdPedido = codPedido;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public double getVlPedido() {
        return vlPedido;
    }

    public void setVlPedido(double vlPedido) {
        this.vlPedido = vlPedido;
    }
}

package com.net.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@ToString
@Entity(name = "item_pedido")
@Table(name = "ITEM_PEDIDO")
@Data
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO", nullable = false)
    private Pedido pedido;
    @OneToOne
    @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;
    @Column(name = "QT_PRODUTO")
    private Long qtProduto;

    public Long getPedido() {
        return pedido.getId();
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

package com.net.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity(name = "Pedido")
@Table(name = "PEDIDO")
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "VL_TOTAL")
    private Double vlTotal = 0d;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<ItemPedido> itemPedido;
}

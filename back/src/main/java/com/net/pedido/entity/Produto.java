package com.net.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PRODUTO")
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double preco;

}

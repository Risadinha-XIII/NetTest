package com.net.pedido.controller;

import com.net.pedido.entity.Produto;
import com.net.pedido.repository.ProdutoRepository;

import java.util.List;

public class ProdutoController {

    public void criarProduto(final String descricao,
                             final Double preco, ProdutoRepository produtoRepository){
        Produto produto = new Produto();
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produtoRepository.saveAndFlush(produto);
    }

    public List<Produto> obterTodosRegistros(ProdutoRepository produtoRepository){
        return produtoRepository.findAll();
    }
}

package com.net.pedido.requests;

import com.net.pedido.controller.ProdutoController;
import com.net.pedido.entity.Produto;
import com.net.pedido.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoRequests {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listProduto(){
        ProdutoController produtoController = new ProdutoController();
        return produtoController.obterTodosRegistros(produtoRepository);
    }

    @PostMapping
    public void postProduto(@RequestParam String descricao,
                            @RequestParam Double preco){
        ProdutoController produtoController = new ProdutoController();
        produtoController.criarProduto(descricao, preco, produtoRepository);
    }
}

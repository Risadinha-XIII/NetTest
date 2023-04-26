package com.net.pedido.requests;

import com.net.pedido.controller.ItemPedidoController;
import com.net.pedido.repository.ItemPedidoRepository;
import com.net.pedido.repository.PedidoRepository;
import com.net.pedido.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itemPedido")
public class ItemPedidoRequests {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @PostMapping
    public void post(@RequestParam final Long idPedido,
                     @RequestParam final Long idProduto,
                     @RequestParam final Long qtProduto){
        ItemPedidoController itemPedidoController = new ItemPedidoController();
        itemPedidoController.addItemPedido(idPedido, idProduto, qtProduto, pedidoRepository, produtoRepository, itemPedidoRepository);
    }
    @DeleteMapping
    public void delete(@RequestParam final Long idPedido,
                       @RequestParam final Long idProduto,
                       @RequestParam final Long qtProduto){
        ItemPedidoController itemPedidoController = new ItemPedidoController();
        itemPedidoController.removerItemPedido(idPedido, idProduto, qtProduto, itemPedidoRepository, pedidoRepository);
    }
}

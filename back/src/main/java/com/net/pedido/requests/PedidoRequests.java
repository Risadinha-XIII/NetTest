package com.net.pedido.requests;

import com.net.pedido.controller.PedidoController;
import com.net.pedido.entity.Pedido;
import com.net.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoRequests {

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping
    public Pedido get(@RequestParam final Long id){
        PedidoController pedidoController = new PedidoController();
        return pedidoController.getPedidoById(id, pedidoRepository);
    }

    @PostMapping
    public void post(){
        PedidoController pedidoController = new PedidoController();
        pedidoController.gerarNovoPedido(pedidoRepository);
    }
}

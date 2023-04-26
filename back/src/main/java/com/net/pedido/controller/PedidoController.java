package com.net.pedido.controller;

import com.net.pedido.entity.Pedido;
import com.net.pedido.repository.PedidoRepository;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
public class PedidoController {

    public Pedido getPedidoById(final Long idPedido, PedidoRepository pedidoRepository){

        Optional<Pedido> pedidoOp = pedidoRepository.findById(idPedido);
        if (pedidoOp.isPresent()){
            return pedidoOp.get();
        }
        return new Pedido();
    }

    public void gerarNovoPedido(PedidoRepository pedidoRepository){
        pedidoRepository.saveAndFlush(new Pedido());
    }
}

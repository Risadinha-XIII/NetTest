package com.net.pedido.requests;

import com.net.pedido.controller.FechamentoPedidoController;
import com.net.pedido.repository.PedidoRepository;
import com.net.pedido.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fechamento")
public class FechamentoPedidoRequests {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @PutMapping
    public void atualizarPrecoTotal(@RequestParam final Long idPedido){
        FechamentoPedidoController fechamentoPedidoController = new FechamentoPedidoController();
        fechamentoPedidoController.gravarPrecoTotalPedido(idPedido, pedidoRepository);
    }

    @GetMapping
    public Double precoTotalPedidoItem(@RequestParam final Map<String, String> params){
        if (params.containsKey("idPedido") && params.containsKey("listaItens")){
            final Long idPedido = Long.parseLong(params.get("idPedido"));
            final String paramListaItens = params.get("listaItens");
            final String listaItens = paramListaItens.substring(1,paramListaItens.length()-1);
            FechamentoPedidoController fechamentoPedidoController = new FechamentoPedidoController();
            return fechamentoPedidoController.getPrecoTotalPedidoItem(idPedido, gerarMapProdutoQuantidade(listaItens), pedidoRepository, produtoRepository);
        }
        return 0d;
    }

    private Map<Long, Long> gerarMapProdutoQuantidade(final String listaProduto){
        final String[] valores = listaProduto.split(";");
        Map<Long, Long> mapRetorno = new HashMap<>();
        for (String valor:valores) {
            final String[] chaveValor = valor.split(",");
            Long key = Long.parseLong(chaveValor[0].trim());
            Long value = Long.parseLong(chaveValor[1].trim());
            mapRetorno.put(key, value);
        }
        return mapRetorno;
    }
}

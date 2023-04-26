package com.net.pedido.controller;

import com.net.pedido.entity.ItemPedido;
import com.net.pedido.entity.Pedido;
import com.net.pedido.entity.Produto;
import com.net.pedido.repository.PedidoRepository;
import com.net.pedido.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FechamentoPedidoController {

    public void gravarPrecoTotalPedido(final Long idPedido,
                                       final PedidoRepository pedidoRepository){
        final Optional<Pedido> pedidoOp = pedidoRepository.findById(idPedido);
        if (pedidoOp.isPresent()){
            Double valorTotal = 0d;
            for (ItemPedido item : pedidoOp.get().getItemPedido()) {
                valorTotal += item.getQtProduto() * item.getProduto().getPreco();
            }
            pedidoOp.get().setVlTotal(valorTotal);
            pedidoRepository.saveAndFlush(pedidoOp.get());
        }

    }

    public Double getPrecoTotalPedidoItem(final Long idPedido,
                                          final Map<Long, Long> listProduto,
                                          final PedidoRepository pedidoRepository,
                                          final ProdutoRepository produtoRepository){
        final Optional<Pedido> pedidoOp = pedidoRepository.findById(idPedido);
        if (pedidoOp.isPresent()){
            final List<ItemPedido> listItemPedido = getListItemPedidoFilter(listProduto, pedidoOp.get());
            return calcularValorTotalPedidoItem(listProduto, listItemPedido, produtoRepository);
        }
        return 0d;
    }

    private Double calcularValorTotalPedidoItem(final Map<Long, Long> listProduto,
                                                final List<ItemPedido> listItemPedido,
                                                final ProdutoRepository produtoRepository) {
        Double valorTotal = 0d;
        for (ItemPedido item : listItemPedido){
            Long idProduto = item.getProduto().getId();
            Optional<Produto> produtoOp = produtoRepository.findById(idProduto);
            if (produtoOp.isPresent()){
                valorTotal += listProduto.get(idProduto) * produtoOp.get().getPreco();
            }
        }
        return valorTotal;
    }

    private static List<ItemPedido> getListItemPedidoFilter(Map<Long, Long> listProduto, Pedido pedido) {
        return pedido.getItemPedido().stream().filter(itemPedido -> listProduto.containsKey(itemPedido.getProduto().getId())).toList();
    }
}

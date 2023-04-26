package com.net.pedido.controller;

import com.net.pedido.entity.ItemPedido;
import com.net.pedido.entity.Pedido;
import com.net.pedido.entity.Produto;
import com.net.pedido.repository.ItemPedidoRepository;
import com.net.pedido.repository.PedidoRepository;
import com.net.pedido.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ItemPedidoController {

    public void addItemPedido(final Long idPedido,
                              final Long idProduto,
                              final Long qtProduto,
                              final PedidoRepository pedidoRepository,
                              final ProdutoRepository produtoRepository,
                              final ItemPedidoRepository itemPedidoRepository){
        Optional<Pedido> pedidoOp = pedidoRepository.findById(idPedido);
        Optional<Produto> produtoOp = produtoRepository.findById(idProduto);

        if (pedidoOp.isPresent() && produtoOp.isPresent()){
            final ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedidoOp.get());
            itemPedido.setProduto(produtoOp.get());
            itemPedido.setQtProduto(qtProduto);
            itemPedidoRepository.saveAndFlush(itemPedido);
        }
    }

    public void removerItemPedido(final Long idPedido,
                                  final Long idProduto,
                                  final Long qtProduto,
                                  final ItemPedidoRepository itemPedidoRepository,
                                  final PedidoRepository pedidoRepository){
        List<ItemPedido> listItem = getListItemPedido(idPedido, idProduto, pedidoRepository);
        if (qtProduto>0){
            removerQuantidadeItem(qtProduto, listItem, itemPedidoRepository);
        } else if (qtProduto <= 0) {
            itemPedidoRepository.deleteAllInBatch(listItem);
        }
    }

    private List<ItemPedido> getListItemPedido(final Long idPedido,
                                               final Long idProduto,
                                               final PedidoRepository pedidoRepository) {
        Optional<Pedido> pedidoOp = pedidoRepository.findById(idPedido);
        Stream<ItemPedido> listFiltered = Stream.empty();
        if (pedidoOp.isPresent()){
            List<ItemPedido> itemPedidoList = pedidoOp.get().getItemPedido();
            Stream<ItemPedido> stream = itemPedidoList.stream();
            listFiltered = stream.filter(item -> item.getProduto().getId().equals(idProduto));
        }
        return listFiltered.toList();
    }

    private void removerQuantidadeItem(final Long qtProduto,
                                       final List<ItemPedido> listItem,
                                       final ItemPedidoRepository itemPedidoRepository) {
        listItem.forEach(item -> {
            if (qtProduto < item.getQtProduto() && qtProduto >0){
                item.setQtProduto(qtProduto);
                itemPedidoRepository.saveAndFlush(item);
            }
        });
    }
}

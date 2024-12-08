package com.example.demo.dto;

import com.example.demo.model.Pedido;
import com.example.demo.model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public record PedidoRecordDTO(Long id, Long idCliente, List<ProdutoSimplificado> produtos) {


    public PedidoRecordDTO(Pedido pedido) {
        this(pedido.getId(), pedido.getIdCliente(),
                pedido.getProdutos().stream()
                        .map(produto -> new ProdutoSimplificado(produto))
                        .collect(Collectors.toList()));
    }

    public record ProdutoSimplificado(Long id, String nome, Double preco) {

        public ProdutoSimplificado(Produto produto) {
            this(produto.getId(), produto.getNome(), produto.getPreco());
        }
    }
}

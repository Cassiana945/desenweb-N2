package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Produto;
import com.example.demo.persistence.ClienteRepository;
import com.example.demo.persistence.PedidoRepository;
import com.example.demo.persistence.ProdutoRepository;

import java.util.List;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido criar(Long idCliente, List<Long> idsProdutos) {
        if (idCliente == null || !clienteRepository.existsById(idCliente)) {
            throw new IllegalArgumentException("O cliente é inválido");
        }
        if (idsProdutos == null) {
            throw new IllegalArgumentException("Não há produtos no pedido");
        }
        for (Long idProduto : idsProdutos) {
            if (!produtoRepository.existsById(idProduto)) {
                throw new IllegalArgumentException("Produto com ID " + idProduto + "não encontrado");
            }
        }
        List<Produto> produtos = produtoRepository.findAllById(idsProdutos);
        Pedido pedido = new Pedido(idCliente, produtos);
        return pedidoRepository.save(pedido);
    }

    public List <Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public List <Pedido> listarPorIdCliente(Long idCliente) {
        return pedidoRepository.findByIdCliente(idCliente);
    }

    public List<Pedido> listarPorIdProduto(Long idProduto) {
        return pedidoRepository.findByProdutosId(idProduto);
    }

    public Pedido listarPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
    }
}

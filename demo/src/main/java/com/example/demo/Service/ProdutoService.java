package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Produto;
import com.example.demo.persistence.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto criar(Produto produto) {
        if (produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome é um campo obrigatório.");
        }
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        return produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto listarPorNome(String nome) {
        return produtoRepository.findByNome(nome).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
    }
}

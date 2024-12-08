package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Produto;
import com.example.demo.persistence.ProdutoRepository;

@DataJpaTest
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void quandoSalvarProduto_entaoEncontrarPorNome() {
        Produto produto = new Produto();
        produto.setNome("Maça");
        produto.setPreco(3.50); 

        Produto produto2 = new Produto();
        produto2.setNome("Melancia");
        produto2.setPreco(10.50); 


        produtoRepository.save(produto);
        produtoRepository.save(produto2);

        List<Produto> produtos = produtoRepository.findAll();

        assertEquals(2, produtos.size(), "Deve retornar 2 produtos");

        Optional<Produto> encontradoPorNome = produtoRepository.findByNome("Maça");
        assertTrue(encontradoPorNome.isPresent());
        assertEquals("Maça", encontradoPorNome.get().getNome());
    }
}

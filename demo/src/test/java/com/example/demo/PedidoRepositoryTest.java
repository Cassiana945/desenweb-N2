package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Cliente;
import com.example.demo.model.Pedido;
import com.example.demo.model.Produto;
import com.example.demo.persistence.ClienteRepository;
import com.example.demo.persistence.PedidoRepository;
import com.example.demo.persistence.ProdutoRepository;

@DataJpaTest
public class PedidoRepositoryTest {

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Test
  public void quandoSalvarPedido_entaoEncontrarPorId() {
    // Criando um cliente
    Cliente cliente = new Cliente();
    cliente.setNome("João");
    cliente.setEmail("joao@email.com");
    cliente.setContato("123456789");

    // Salvando o cliente
    clienteRepository.save(cliente);

    // Criando produtos
    Produto produto1 = new Produto();
    produto1.setNome("Produto 1");
    produto1.setPreco(50.0);

    Produto produto2 = new Produto();
    produto2.setNome("Produto 2");
    produto2.setPreco(30.0);

    // Salvando os produtos
    produtoRepository.save(produto1);
    produtoRepository.save(produto2);

    // Criando um pedido
    Pedido pedido = new Pedido(cliente.getId(), List.of(produto1, produto2));

    // Salvando o pedido
    pedidoRepository.save(pedido);

    // Buscando o pedido pelo id
    Optional<Pedido> encontrado = pedidoRepository.findById(pedido.getId());

    assertTrue(encontrado.isPresent(), "Pedido deve ser encontrado");
    assertEquals(cliente.getId(), encontrado.get().getIdCliente(), "O pedido deve ser do cliente correto");
    assertEquals(2, encontrado.get().getProdutos().size(), "O pedido deve conter dois produtos");
  }

  @Test
  public void quandoBuscarPedidoPorIdCliente_entaoDeveRetornarPedidosDoCliente() {
    // Criando e salvando um cliente
    Cliente cliente = new Cliente();
    cliente.setNome("Maria");
    cliente.setEmail("maria@email.com");
    cliente.setContato("987654321");
    clienteRepository.save(cliente);

    // Criando produtos
    Produto produto1 = new Produto();
    produto1.setNome("macarrão");
    produto1.setPreco(25.0);

    Produto produto2 = new Produto();
    produto2.setNome("panela");
    produto2.setPreco(45.0);

    produtoRepository.save(produto1);
    produtoRepository.save(produto2);

    // Criando pedidos para o cliente
    Pedido pedido1 = new Pedido(cliente.getId(), List.of(produto1));
    Pedido pedido2 = new Pedido(cliente.getId(), List.of(produto2));

    // Salvando os pedidos
    pedidoRepository.save(pedido1);
    pedidoRepository.save(pedido2);

    // Buscando pedidos pelo id do cliente
    List<Pedido> pedidos = pedidoRepository.findByIdCliente(cliente.getId());

    assertEquals(2, pedidos.size(), "Deve retornar 2 pedidos para o cliente");
  }

  @Test
  public void quandoBuscarPedidosPorProduto_entaoDeveRetornarPedidos() {
    // Criando um cliente
    Cliente cliente = new Cliente();
    cliente.setNome("Carlos");
    cliente.setEmail("carlos@email.com");
    cliente.setContato("123123123");
    clienteRepository.save(cliente);

    // Criando produtos
    Produto produto1 = new Produto();
    produto1.setNome("Produto 1");
    produto1.setPreco(10.0);

    Produto produto2 = new Produto();
    produto2.setNome("Produto 2");
    produto2.setPreco(20.0);

    produtoRepository.save(produto1);
    produtoRepository.save(produto2);

    // Criando pedidos
    Pedido pedido1 = new Pedido(cliente.getId(), List.of(produto1));
    Pedido pedido2 = new Pedido(cliente.getId(), List.of(produto1, produto2));

    pedidoRepository.save(pedido1);
    pedidoRepository.save(pedido2);

    // Buscando pedidos que contenham o produto 1
    List<Pedido> pedidos = pedidoRepository.findByProdutosId(produto1.getId());

    assertEquals(2, pedidos.size(), "Deve retornar 2 pedidos que contém o Produto 1");
  }

}

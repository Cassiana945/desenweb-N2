package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Pedido;

public interface PedidoRepository extends JpaRepository <Pedido, Long> {
     List <Pedido> findByIdCliente(Long idCliente);
     List <Pedido> findByProdutosId(Long idProduto);
 
}

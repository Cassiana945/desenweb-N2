package com.example.demo.controllers;

import com.example.demo.dto.PedidoRecordDTO;
import com.example.demo.Service.PedidoService;
import com.example.demo.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @PostMapping
    public PedidoRecordDTO criar(@RequestBody PedidoCriacao pedidoCriacao) {
        Pedido pedidoCriado = pedidoService.criar(pedidoCriacao.getIdCliente(), pedidoCriacao.getIdsProdutos());
        return new PedidoRecordDTO(pedidoCriado);
    }

    @GetMapping
    public List<PedidoRecordDTO> listar() {
        List<Pedido> pedidos = pedidoService.listar();
        return pedidos.stream()
                .map(PedidoRecordDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/cliente/{idCliente}")
    public List<PedidoRecordDTO> listarPorIdCliente(@PathVariable Long idCliente) {
        List<Pedido> pedidos = pedidoService.listarPorIdCliente(idCliente);
        return pedidos.stream()
                .map(PedidoRecordDTO::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/produto/{idProduto}")
    public List<PedidoRecordDTO> listarPorIdProduto(@PathVariable Long idProduto) {
        List<Pedido> pedidos = pedidoService.listarPorIdProduto(idProduto);
        return pedidos.stream()
                .map(PedidoRecordDTO::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public PedidoRecordDTO listarPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.listarPorId(id);
        return new PedidoRecordDTO(pedido);
    }


    public static class PedidoCriacao {
        private Long idCliente;
        private List<Long> idsProdutos;


        public Long getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(Long idCliente) {
            this.idCliente = idCliente;
        }

        public List<Long> getIdsProdutos() {
            return idsProdutos;
        }

        public void setIdsProdutos(List<Long> idsProdutos) {
            this.idsProdutos = idsProdutos;
        }
    }
}

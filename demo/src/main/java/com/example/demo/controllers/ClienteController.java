package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Service.ClienteService;
import com.example.demo.model.Cliente;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping 
    public Cliente criar(@RequestBody Cliente cliente) {   
        return clienteService.criar(cliente);
    }

    @GetMapping 
    public List<Cliente> listar() {                       
        return clienteService.listar();
    }

    @GetMapping("/nome/{nome}")
    public Cliente listarPorNome(@PathVariable String nome) {      
        return clienteService.listarPorNome(nome);
    }
    
}
package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.persistence.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criar(Cliente cliente) {
        if (cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome é um campo obrigatório.");
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
       return clienteRepository.findAll();
       
    }

    public Cliente listarPorNome(String nome) {
        return clienteRepository.findByNome(nome).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }
}

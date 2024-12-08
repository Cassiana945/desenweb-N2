package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Cliente;
import com.example.demo.persistence.ClienteRepository;

@DataJpaTest
public class ClienteRepositoryTest {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void quandoSalvarCliente_entaoEncontrarPorNome() {
           Cliente cliente = new Cliente();
           cliente.setNome("Carolina");
           cliente.setContato("0987654");
           cliente.setEmail("carol@example.com");

           Cliente cliente2 = new Cliente();
           cliente2.setNome("João");
           cliente2.setContato("1234567");
           cliente2.setEmail("joao@example.com");
           
           clienteRepository.save(cliente);
           clienteRepository.save(cliente2);

           List<Cliente> clientes = clienteRepository.findAll();

           assertEquals(2, clientes.size(), "Deve retornar 2 clientes");
           assertTrue(clientes.stream().anyMatch(c -> c.getNome().equals("Carolina")), "Deve conter cliente Carolina");
           assertTrue(clientes.stream().anyMatch(c -> c.getNome().equals("João")), "Deve conter cliente João");

           Optional<Cliente> encontradoPorNome = clienteRepository.findByNome("Carolina");
           assertTrue(encontradoPorNome.isPresent());
           assertEquals("Carolina", encontradoPorNome.get().getNome());

           
    }
}

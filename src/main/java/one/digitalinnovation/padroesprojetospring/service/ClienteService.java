package one.digitalinnovation.padroesprojetospring.service;

import one.digitalinnovation.padroesprojetospring.model.Cliente;

// Permite várias implementações da mesma interface.

public interface ClienteService {
    
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);
    void inserir(Cliente cliente);
    void atualizar(Long id, Cliente cliente);
    void deletar(Long id);
}

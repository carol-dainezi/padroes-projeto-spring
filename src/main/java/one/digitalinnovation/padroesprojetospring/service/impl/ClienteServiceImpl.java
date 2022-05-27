package one.digitalinnovation.padroesprojetospring.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.padroesprojetospring.model.Cliente;
import one.digitalinnovation.padroesprojetospring.model.ClienteRepository;
import one.digitalinnovation.padroesprojetospring.model.Endereco;
import one.digitalinnovation.padroesprojetospring.model.EnderecoRepository;
import one.digitalinnovation.padroesprojetospring.service.ClienteService;
import one.digitalinnovation.padroesprojetospring.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired // Já é componente Spring pois tem as notações do Feign e outras.
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Busca cliente por ID, se existir.
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar cliente por ID.
        clienteRepository.deleteById(id);
    }

    // Método usado tanto para atualizar quanto pada inserir cliente.
    private void salvarClienteComCep(Cliente cliente) {
        // Checa se CEP do cliente existe.
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Se não existir, integrar com ViaCEP.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir cliente e vincular endereço.
        clienteRepository.save(cliente);
    }
}

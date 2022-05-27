package one.digitalinnovation.padroesprojetospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import one.digitalinnovation.padroesprojetospring.model.Cliente;
import one.digitalinnovation.padroesprojetospring.service.ClienteService;

// @RestController representa a Facade - abstrai a integração entre HS e ViaCEP de maneira simples.

@RestController
@RequestMapping("clientes")
public class ClienteRestController {


    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {

        return ResponseEntity.ok(clienteService.buscarTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    // Indica o POST do HTTP - quando o servidor precisa aceitar algum dado do cliente.
    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {

        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    // PUT do HTTP. Bem parecido com o POST, mas para atualizar os dados no servidor.
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    // DELETE do HTTP. Remove informações.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }
}

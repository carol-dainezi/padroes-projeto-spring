package one.digitalinnovation.padroesprojetospring.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import one.digitalinnovation.padroesprojetospring.model.Endereco;

// Endereço do viacep p/ o Feign
@FeignClient(name="viacep", url="https://viacep.com.br/ws")
public interface ViaCepService {
    
    // Define o GET do protocolo HTTP
    // @GetMapping também é válido no lugar de @RequestMapping
    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json/")
    Endereco consultarCep(@PathVariable("cep") String cep);
}

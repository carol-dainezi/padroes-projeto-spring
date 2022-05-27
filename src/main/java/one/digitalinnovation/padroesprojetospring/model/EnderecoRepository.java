package one.digitalinnovation.padroesprojetospring.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // Opcional
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
    
}

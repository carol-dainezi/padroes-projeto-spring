package one.digitalinnovation.padroesprojetospring.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // Opcional
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}

// CrudRepository é uma Strategy, uma interface que obriga a seguir certa estratégia de implementação.
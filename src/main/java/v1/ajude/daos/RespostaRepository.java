package v1.ajude.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v1.ajude.models.Resposta;

import java.io.Serializable;

@Repository
public interface RespostaRepository<T, ID extends Serializable> extends JpaRepository<Resposta, Long> {
}

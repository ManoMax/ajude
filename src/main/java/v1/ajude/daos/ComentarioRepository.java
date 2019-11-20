package v1.ajude.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v1.ajude.models.Comentario;

import java.io.Serializable;

@Repository
public interface ComentarioRepository<T, ID extends Serializable> extends JpaRepository<Comentario, Long> {

}

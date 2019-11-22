package v1.ajude.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v1.ajude.models.Campanha;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface CampanhaRepository<T, ID extends Serializable> extends JpaRepository<Campanha, Long> {
    Optional<T> findByURL(String URL);
}

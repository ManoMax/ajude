package v1.ajude.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v1.ajude.models.Likes;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface LikesRepository<T, ID extends Serializable> extends JpaRepository<Likes, Long> {

    Optional<T> findByURL(String url);
}

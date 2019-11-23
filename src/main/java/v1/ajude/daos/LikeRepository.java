package v1.ajude.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v1.ajude.models.Like;

import java.io.Serializable;

@Repository
public interface LikeRepository<T, ID extends Serializable> extends JpaRepository<Like, Long> {

}

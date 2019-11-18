package v1.ajude.models;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "respostas")
@Getter
@ToString(callSuper = true)
public class RespostasComentario extends ComentarioAbstract{

    public RespostasComentario(Usuario usuario, String comentario) {
        super(usuario, comentario);
    }

    public RespostasComentario(String comentario) {
        this.setComentario(comentario);
    }
}

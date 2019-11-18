package v1.ajude.models;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "comentario")
@Getter
@ToString(callSuper = true)
public class Comentario extends ComentarioAbstract {

    private ArrayList<RespostasComentario> respostas;

    public Comentario(Usuario usuario, String comentario) {
        super(usuario, comentario);
        this.respostas = new ArrayList<RespostasComentario>();
    }

    public void addRespostas(Usuario usuario, String comentario) {
        this.setUsuario(usuario);
        respostas.add(new RespostasComentario(comentario));
    }

}

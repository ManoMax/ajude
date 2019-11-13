package v1.ajude.models;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;

public class Comentario {

    private Usuario usuario;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComent;
    private String comentario;
    // private ArrayList<Respostas> respostas;

    public Comentario() {
        super();
    }

    public Comentario(Usuario usuario, String comentario) {
        this.usuario = usuario;
        this.comentario = comentario;
    }


}

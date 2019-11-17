package v1.ajude.models;

import java.util.ArrayList;

public class Comentario extends ComentarioAbstract {

    private ArrayList<RespostasComentario> respostas;

    public Comentario() {
        super();
        this.respostas = new ArrayList<RespostasComentario>();
    }

    public void addRespostas(Usuario usuario, String comentario) {
        this.setUsuario(usuario);
        respostas.add(new RespostasComentario(comentario));
    }

}

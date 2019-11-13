package v1.ajude.models;

import java.util.ArrayList;

public class Comentario extends ComentarioAbstract {

    private ArrayList<Respostas> respostas;

    public Comentario() {
        super();
        this.respostas = new ArrayList<Respostas>();
    }

    public void addRespostas(Usuario usuario, String comentario) {
        this.setUsuario(usuario);
        respostas.add(new Respostas(comentario));
    }

}

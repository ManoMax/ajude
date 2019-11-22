package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comentario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComent;

    @ManyToOne
    @JoinColumn(name = "email")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idCampanha")
    @JsonIgnore
    private Campanha campanha;

    private String textoComentario;

    @OneToMany(mappedBy = "comentarioPai", cascade = CascadeType.ALL)
    private List<Resposta> respostas;

    public Comentario() {
    }

    public Comentario(Campanha campanha, Usuario usuario, String textoComentario) {
        this.campanha = campanha;
        this.usuario = usuario;
        this.textoComentario = textoComentario;
        this.respostas = new ArrayList<Resposta>();
    }

    public long getIdComent() {
        return this.idComent;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public String getTextoComentario() {
        return this.textoComentario;
    }

    public List<Resposta> getRespostas() {
        return this.respostas;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public Comentario addResposta(Usuario usuario, Resposta resposta) {
        respostas.add(resposta);
        return this;
    }

}

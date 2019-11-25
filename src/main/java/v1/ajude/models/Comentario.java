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
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idCampanha")
    @JsonIgnore
    private Campanha campanha;

    private String textoComentario;

    @OneToMany(mappedBy = "comentarioPai", cascade = CascadeType.ALL)
    private List<Resposta> respostas;

    private boolean apagado;

    public Comentario() {
    }
    public Comentario(Campanha campanha, Usuario usuario, String textoComentario) {
        this.campanha = campanha;
        this.usuario = usuario;
        this.textoComentario = textoComentario;
        this.respostas = new ArrayList<Resposta>();
        this.apagado = false;
    }

    public long getIdComent() {
        return this.idComent;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    public UsuarioDTO getDonoComentario() {
        Usuario usuario = this.usuario;
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getUrlUser(), usuario.getPrimeiroNome(), usuario.getUltimoNome(), usuario.getEmail());
        return usuarioDTO;
    }
    public String getTextoComentario() {
        if (this.apagado) {
            return "";
        } else {
            return this.textoComentario;
        }
    }
    public List<Resposta> getRespostas() {
        return this.respostas;
    }
    public boolean getApagado() {
        return this.apagado;
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

    public void setApagado() {
        this.apagado = true;
    }
}

package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Resposta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idResposta;

    @ManyToOne
    @JoinColumn(name = "email")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JsonIgnore
    private Comentario comentarioPai;

    private String textoResposta;

    private boolean apagada;

    public Resposta(){
    }
    public Resposta(Comentario comentarioPai, Usuario usuario, String textoResposta) {
        this.comentarioPai = comentarioPai;
        this.usuario = usuario;
        this.textoResposta = textoResposta;
        this.apagada = false;
    }

    public long getIdResposta() {
        return this.idResposta;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    public UsuarioDTO getDonoResposta() {
        Usuario usuario = this.usuario;
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getUrlUser(), usuario.getPrimeiroNome(), usuario.getUltimoNome(), usuario.getEmail());
        return usuarioDTO;
    }
    public String getTextoResposta() {
        if (this.apagada) {
            return "";
        }
        return this.textoResposta;
    }
    public Comentario getComentarioPai() {
        return this.comentarioPai;
    }
    public boolean getApagada() {
        return this.apagada;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setComentarioPai(Comentario comentario) {
        this.comentarioPai = comentario;
    }
    public void setTextoResposta(String textoResposta) {
        this.textoResposta = textoResposta;
    }
    public void setApagada() {
        this.apagada = true;
    }
}

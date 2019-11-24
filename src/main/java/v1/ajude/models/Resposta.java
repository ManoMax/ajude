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

    @JsonIgnore
    @ManyToOne
    private Comentario comentarioPai;

    private String textoResposta;

    public Resposta(){
    }

    public Resposta(Comentario comentarioPai, Usuario usuario, String textoResposta) {
        this.comentarioPai = comentarioPai;
        this.usuario = usuario;
        this.textoResposta = textoResposta;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @JsonIgnore
    public long getIdResposta() {
        return this.idResposta;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public UsuarioDTO getDonoResposta() {
        Usuario usuario = this.usuario;
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getUrlUser(), usuario.getNickName(), usuario.getPrimeiroNome(), usuario.getUltimoNome(), usuario.getEmail());
        return usuarioDTO;
    }

    public String getTextoResposta() {
        return this.textoResposta;
    }

    public void setTextoResposta(String textoResposta) {
        this.textoResposta = textoResposta;
    }


}

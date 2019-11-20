package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Resposta{

    @ManyToOne
    @JoinColumn(name = "email")
    @JsonIgnore
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    private Comentario comentarioPai;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idResposta;
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

    public long getIdResposta() {
        return this.idResposta;
    }

    public String getTextoResposta() {
        return this.textoResposta;
    }

    public void setTextoResposta(String textoResposta) {
        this.textoResposta = textoResposta;
    }
}

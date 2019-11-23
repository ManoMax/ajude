package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    private String primeiroNome;
    private String ultimoNome;
    @Id
    private String email;
    private String numCartao;
    private String senha;

    @OneToMany
    private List<Campanha> campanhasCriadas;

    @OneToMany
    @JsonIgnore
    private List<Comentario> comentarios;
    @OneToMany
    @JsonIgnore
    private List<Likes> likes;

    public Usuario() {
        super();
    }
    public Usuario(String primeiroNome, String ultimoNome, String email, String numCartao, String senha) {
        super();
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
        this.numCartao = numCartao;
        this.senha = senha;
        this.campanhasCriadas = new ArrayList<Campanha>();
        this.comentarios = new ArrayList<Comentario>();
        this.likes = new ArrayList<Likes>();
    }

    public String getPrimeiroNome() {
        return this.primeiroNome;
    }
    public String getUltimoNome() {
        return this.ultimoNome;
    }
    public String getEmail() {
        return this.email;
    }
    public String getNumCartao() {
        return this.numCartao;
    }
    public String getSenha() {
        return this.senha;
    }
    public List<Campanha> getCampanhasCriadas() {
        return this.campanhasCriadas;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }
    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setCampanhasCriadas(Campanha campanha) {
        this.campanhasCriadas.add(campanha);
    }

    public void setComentario(Comentario novoComentario) {
        this.comentarios.add(novoComentario);
    }
    public void setLikes(Likes likes) {
        this.likes.add(likes);
    }
}
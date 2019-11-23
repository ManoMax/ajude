package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLike;

    @ManyToOne
    @JsonIgnore
    private Usuario likeUsuario;
    private boolean likeMode;
    @ManyToOne
    @JoinColumn(name = "idCampanhaLike")
    @JsonIgnore
    private Campanha campanha;
    private String URL;

    public Likes() {

    }

    public Likes(Usuario likeUsuario, boolean likeMode, Campanha campanha) {
        this.likeUsuario = likeUsuario;
        this.likeMode = likeMode;
        this.campanha = campanha;
        this.URL = campanha.getURL();
    }

    public Usuario getLikeUsuario() {
        return this.likeUsuario;
    }
    public boolean getLikeMode() {
        return this.likeMode;
    }
    public Campanha getCampanha() {
        return this.campanha;
    }
    @JsonIgnore
    public long getIdLike() {
        return this.idLike;
    }
    public String getURL() {
        return this.URL;
    }

    public void setLikeUsuario(Usuario likeUsuario) {
        this.likeUsuario = likeUsuario;
    }
    public void setLikeMode(boolean likeMode) {
        this.likeMode = likeMode;
    }
    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }

}

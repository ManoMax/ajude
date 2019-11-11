package v1.ajude.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numId;
    private String nomeCurto;
    private String URL; // identificador de URL único da campanha (gerado pelo frontend a partir do nome curto),
    private String descricao;
    private Date deadLine; // (data) para arrecadação,
    private boolean status; // Ativo ou Desativo
    private float meta; // (reais)
    private float doacoes;
    @OneToOne
    private Usuario dono;
    // private ArrayList<Comentario> comentarios;
    // private ArrayList<Like> likes;

    public Campanha() {
        super();
    }

    public Campanha(String nomeCurto, String URL, String descricao, float meta, Usuario dono) {
        this.nomeCurto = nomeCurto;
        // this.URL = URL;
        this.descricao = descricao;
        // this.deadLine = deadLine;
        this.status = true;
        this.meta = meta;
        this.doacoes = 0;
        this.dono = dono;
        // this.comentarios = new ArrayList<Comentario>();
        // this.likes = new ArrayList<Like>();
    }

    public long getId() {
        return this.numId;
    }
    public String getNomeCurto() {
        return this.nomeCurto;
    }
    public String getURL() {
        return this.URL;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public Date getDeadLine() {
        return this.deadLine;
    }
    public boolean getStatus() {
        return this.status;
    }
    public float getMeta() {
        return this.meta;
    }
    public float getDoacoes() {
        return this.doacoes;
    }
    public Usuario getDono() {
        return this.dono;
    }
    // public ArrayList<Comentario> getComentarios() {
    //    return this.comentarios;
    //}
    //public ArrayList<Like> getLikes() {
    //    return this.likes;
    //}

    public void setNomeCurto(String nomeCurto) {
        this.nomeCurto = nomeCurto;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public void setMeta(float meta) {
        this.meta = meta;
    }
    public void setDoacoes(float doacoes) {
        this.doacoes = doacoes;
    }
    public void setDono(Usuario dono) {
        this.dono = dono;
    }
    //public void setComentarios(ArrayList<Comentario> comentarios) {
    //    this.comentarios = comentarios;
    //}
    //public void setLikes(ArrayList<Like> likes) {
    //    this.likes = likes;
    //}
}

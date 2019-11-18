package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numId;
    private String nomeCurto;
    private String URL; // identificador de URL único da campanha (gerado pelo frontend a partir do nome curto),
    private String descricao;
    private LocalDate deadLine; // (término)
    private String status; // (Ativo ou Desativo)
    private float meta; // (reais)
    private float doacoes;
    @ManyToOne
    @JoinColumn(name = "email")
    @JsonIgnore
    private Usuario dono;
    @OneToOne
    private ArrayList<ComentarioAbstract> comentarios;
    /*
    private ArrayList<Like> likes;
    */

    public Campanha() {
        super();
    }

    public Campanha(String nomeCurto, String descricao, String deadLine, String url, float meta, Usuario dono) {
        super();
        this.nomeCurto = nomeCurto;
        this.URL = url;
        this.descricao = descricao;
        this.deadLine = LocalDate.parse(deadLine); // Format: "2016-08-16"
        this.status = "Ativa";
        this.meta = meta;
        this.doacoes = 0;
        this.dono = dono;
        this.comentarios = new ArrayList<ComentarioAbstract>();
        /*
        this.likes = new ArrayList<Like>();
        */
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
    public LocalDate getDeadLine() {
        return this.deadLine;
    }
    @JsonIgnore
    public String getDeadLineToString() {
        return this.deadLine.toString();
    }
    public String getStatus() {
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
    public String getIdDono() {
        return this.dono.getEmail();
    }
    public ArrayList<ComentarioAbstract> getComentarios() {
        return this.comentarios;
    }
    /*
    public ArrayList<Like> getLikes() {
        return this.likes;
    }
    */

    public void setNomeCurto(String nomeCurto) {
        this.nomeCurto = nomeCurto;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setDeadLine(String deadLine) {
        this.deadLine = LocalDate.parse(deadLine); // Format: "2016-08-16"
    }

    public void setStatus(boolean encerramento) {
        if (this.deadLine.isBefore(LocalDate.now())) {
            if(this.doacoes >= this.meta) {
                this.status = "Concluída";
            } else  {
                this.status = "Vencida";
            }
        }
        if (encerramento) {
            this.status = "Encerrada";
        }
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

    public void addComentario(Comentario comentario, Usuario usuario) {
        this.comentarios.add(new Comentario(usuario, comentario.getComentario()));
    }

    /*
    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }
    */
}

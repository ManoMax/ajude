package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Date deadLine; // (término)
    private String status; // (Ativo ou Desativo)
    private float meta; // (reais)
    private float doacoes;
    @ManyToOne
    @JoinColumn(name = "email")
    @JsonIgnore
    private Usuario dono;
    /*
    private ArrayList<ComentarioAbstract> comentarios;
    private ArrayList<Like> likes;
    */

    public Campanha() {
        super();
    }

    public Campanha(String nomeCurto, String descricao, String data, String url, String status, float meta, Usuario dono) {
        this.nomeCurto = nomeCurto;
        this.URL = url;
        this.descricao = descricao;

        String sDate1 = data;
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.deadLine = date1;
        this.status = status;
        this.meta = meta;
        this.doacoes = 0;
        this.dono = dono;
        /*
        this.comentarios = new ArrayList<ComentarioAbstract>();
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
    @JsonIgnore
    public Date getDataDeadLine() {
        return this.deadLine;
    }
    public String getDeadLine() {
        Date date = this.deadLine;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return strDate;
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
    /*
    public ArrayList<ComentarioAbstract> getComentarios() {
        return this.comentarios;
    }
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
    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }
    public void setStatus(String status) {
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
    /*
    public void setComentarios(ArrayList<ComentarioAbstract> comentarios) {
        this.comentarios = comentarios;
    }
    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }
    */
}

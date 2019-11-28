package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import v1.ajude.dtos.UsuarioDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numId;
    @Column(unique = true)
    private String nomeCurto;
    @Column(unique = true)
    private String URL;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deadLine;
    private String status;
    private float meta;
    private float doacoes;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "email")
    @JsonIgnore
    private Usuario dono;
    @OneToMany
    private List<Comentario> comentarios;
    @OneToMany
    @JsonIgnore
    private List<Likes> likes;
    private int contLike;
    @OneToMany
    @JsonIgnore
    private List<Doacao> listaDoacoes;

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
        this.comentarios = new LinkedList<Comentario>();
        this.likes = new ArrayList<Likes>();
        this.listaDoacoes = new ArrayList<Doacao>();
        this.contLike = 0;
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
    public String getDeadLineString() {
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
    public UsuarioDTO getInfoDono() {
        Usuario usuario = this.dono;
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getUrlUser(), usuario.getPrimeiroNome(),
                usuario.getUltimoNome(), usuario.getEmail());
        return usuarioDTO;
    }
    public List<Comentario> getComentarios() {
        List<Comentario> comentarios = new LinkedList<>(this.comentarios);
        Collections.reverse(comentarios);
        return comentarios;
    }
    public Comentario getComentario(int idComentario) {
        return this.comentarios.get(idComentario);
    }
    public List<Likes> getLikes() {
        return this.likes;
    }
    public int getNumeroDeLikes() {
        return this.contLike;
    }
    public List<Doacao> getListaDoacoes() {
        return this.listaDoacoes;
    }
    public List<UsuarioDTO> getDoadores() {
        List<UsuarioDTO> doadores = new ArrayList<UsuarioDTO>();
        if (this.listaDoacoes.size() > 0) {
            for (Doacao doacao : this.listaDoacoes) {
                doadores.add(doacao.getDonoDoacao());
            }
        }
        return doadores;
    }

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

    public Campanha addComentario(Comentario comentario) {
        this.comentarios.add(comentario);
        return this;
    }
    public void setContLike(Likes thatLikes) {
        if (thatLikes.getLikeMode()) {
            this.contLike += 1;
        } else {
            this.contLike -= 1;
        }
    }
    public void doarCampanha(Doacao novaDoacao) {
        this.doacoes += novaDoacao.getQuantia();
        this.setStatus(false);
        this.listaDoacoes.add(novaDoacao);
    }
}

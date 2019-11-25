package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDoacao;

    private float quantia;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataDeDoacao;
    @ManyToOne
    @JoinColumn(name = "idCampanha")
    @JsonIgnore
    private Campanha campanha;
    @ManyToOne
    @JoinColumn(name = "email")
    @JsonIgnore
    private Usuario usuario;


    public Doacao() {
    }
    public Doacao(float quantia, String dataDeDoacao, Campanha campanha, Usuario usuario) {
        this.quantia = quantia;
        this.dataDeDoacao = LocalDate.parse(dataDeDoacao); // Format: "2016-08-16"
        this.campanha = campanha;
        this.usuario = usuario;
    }

    public float getQuantia() {
        return this.quantia;
    }
    public LocalDate getDataDeDoacao() {
        return this.dataDeDoacao;
    }
    @JsonIgnore
    public String getDataDeDoacaoString() {
        return this.dataDeDoacao.toString();
    }
    public Campanha getCampanha() {
        return this.campanha;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    public UsuarioDTO getDonoDoacao() {
        Usuario usuario = this.usuario;
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getUrlUser(), usuario.getPrimeiroNome(), usuario.getUltimoNome(), usuario.getEmail());
        return usuarioDTO;
    }

    public void setQuantia(float quantia) {
        this.quantia = quantia;
    }
    public void setDataDeDoacao(LocalDate dataDeDoacao) {
        this.dataDeDoacao = dataDeDoacao;
    }
    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

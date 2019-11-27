package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CampanhaDTO {

    private String nomeCurto;
    private String URL;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deadLine;
    private String status;
    private float meta;
    private float doacoes;
    private String descricao;


    private int contLike;

    public CampanhaDTO(){
    }
    public CampanhaDTO(String nomeCurto, String URL, String deadLine, String status, float meta, float doacoes, String descricao, int contLike) {
        this.nomeCurto = nomeCurto;
        this.URL = URL;
        this.deadLine = LocalDate.parse(deadLine);
        this.status = status;
        this.meta = meta;
        this.doacoes = doacoes;
        this.descricao = descricao;
        this.contLike = contLike;
    }

    public String getNomeCurto() {
        return nomeCurto;
    }

    public void setNomeCurto(String nomeCurto) {
        this.nomeCurto = nomeCurto;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getMeta() {
        return meta;
    }

    public void setMeta(float meta) {
        this.meta = meta;
    }

    public float getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(float doacoes) {
        this.doacoes = doacoes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getContLike() {
        return contLike;
    }

    public void setContLike(int contLike) {
        this.contLike = contLike;
    }
}

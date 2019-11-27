package v1.ajude.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DoacaoDTO {

    private float quantia;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataDeDoacao;
    private CampanhaDTO campanhaDTO;

    public DoacaoDTO(){
    }
    public DoacaoDTO(float quantia, String dataDeDoacao, CampanhaDTO campanhaDTO) {
        this.quantia = quantia;
        this.dataDeDoacao = LocalDate.parse(dataDeDoacao);
        this.campanhaDTO = campanhaDTO;
    }

    public float getQuantia() {
        return quantia;
    }

    public void setQuantia(float quantia) {
        this.quantia = quantia;
    }

    public LocalDate getDataDeDoacao() {
        return dataDeDoacao;
    }

    public void setDataDeDoacao(LocalDate dataDeDoacao) {
        this.dataDeDoacao = dataDeDoacao;
    }

    public CampanhaDTO getCampanhaDTO() {
        return campanhaDTO;
    }

    public void setCampanhaDTO(CampanhaDTO campanhaDTO) {
        this.campanhaDTO = campanhaDTO;
    }
}

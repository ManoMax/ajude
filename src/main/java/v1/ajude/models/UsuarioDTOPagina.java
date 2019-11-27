package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class UsuarioDTOPagina {

    private String urlUser;
    private String primeiroNome;
    private String ultimoNome;
    private String email;
    private List<CampanhaDTO> campanhaDTOList;
    private List<DoacaoDTO> doacaoDTOList;

    public UsuarioDTOPagina() {
    }
    @JsonCreator
    public UsuarioDTOPagina(String urlUser, String primeiroNome, String ultimoNome, String email, List<CampanhaDTO> campanhaDTOList, List<DoacaoDTO> doacaoDTOSList) {
        this.urlUser = urlUser;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
        this.campanhaDTOList = campanhaDTOList;
        this.doacaoDTOList = doacaoDTOSList;
    }

    public String getUrlUser() {
        return urlUser;
    }
    public String getPrimeiroNome() {
        return primeiroNome;
    }
    public String getUltimoNome() {
        return ultimoNome;
    }
    public String getEmail() {
        return email;
    }


    public void setUrlUser(String urlUser) {
        this.urlUser = urlUser;
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

    public List<CampanhaDTO> getCampanhaDTOList() {
        return campanhaDTOList;
    }

    public void setCampanhaDTOList(List<CampanhaDTO> campanhaDTOList) {
        this.campanhaDTOList = campanhaDTOList;
    }

    public List<DoacaoDTO> getDoacaoDTOList() {
        return doacaoDTOList;
    }

    public void setDoacaoDTOList(List<DoacaoDTO> doacaoDTOList) {
        this.doacaoDTOList = doacaoDTOList;
    }
}

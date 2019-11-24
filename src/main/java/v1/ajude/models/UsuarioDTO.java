package v1.ajude.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UsuarioDTO {

    private String nickName;
    private String urlUser;
    private String primeiroNome;
    private String ultimoNome;
    private String email;

    public UsuarioDTO() {
    }

    @JsonCreator
    public UsuarioDTO(String urlUser, String nickName, String primeiroNome, String ultimoNome, String email) {
        this.urlUser = urlUser;
        this.nickName = nickName;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
    }

    public String getUrlUser() {
        return urlUser;
    }

    public void setUrlUser(String urlUser) {
        this.urlUser = urlUser;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package v1.ajude.models;


import com.fasterxml.jackson.annotation.JsonCreator;


public class UsuarioDTO {

    private String primeiroNome;
    private String ultimoNome;
    private String email;

    public UsuarioDTO() {

    }

    @JsonCreator
    public UsuarioDTO(String primeiroNome, String ultimoNome, String email) {
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
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

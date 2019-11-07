package v1.ajude.models;

import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Usuario {

    private String primeiroNome;
    private String ultimoNome;
    @Id
    private String email;
    private String numCartao;
    private String senha;

    public Usuario() {
        super();
    }
    public Usuario(String primeiroNome, String ultimoNome, String email, String numCartao, String senha) {
        super();
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
        this.numCartao = numCartao;
        this.senha = senha;
    }

    public String getPrimeiroNome() {
        return this.primeiroNome;
    }
    public String getUltimoNome() {
        return this.ultimoNome;
    }
    public String getEmail() {
        return this.email;
    }
    public String getNumCartao() {
        return this.numCartao;
    }
    public String getSenha() {
        return this.senha;
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
    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
package v1.ajude.models;

import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Usuario {

    private String firstName;
    private String lastName;
    @Id
    private String email;
    private String numCartao;
    private String senha;

    public Usuario() {
        super();
    }
    public Usuario(String firstName, String lastName, String email, String numCartao, String senha) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numCartao = numCartao;
        this.senha = senha;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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

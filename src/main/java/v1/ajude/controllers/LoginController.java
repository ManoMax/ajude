package v1.ajude.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v1.ajude.models.Usuario;
import v1.ajude.services.JWTService;
import v1.ajude.services.UsuarioServices;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioServices servicesUsuario;

    @Autowired
    private JWTService servicesJWT;

    private final String TOKEN_KEY = "login do ajude";

    @PostMapping("")
    public LoginResponse authenticate(@RequestBody Usuario usuario) throws ServletException {

        // verificacoes
        if (!servicesUsuario.exist(usuario)) {
            throw new ServletException("Usuario nao encontrado!");
        }

        if (!servicesUsuario.verificaSenha(usuario)) {
            throw new ServletException("Senha invalida!");
        }

        String token = Jwts.builder().setSubject(usuario.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000)).compact(); // 10 min.

        return new LoginResponse(token);
    }

    public class LoginResponse{

        private String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

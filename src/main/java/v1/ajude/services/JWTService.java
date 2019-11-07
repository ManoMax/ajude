package v1.ajude.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;
import v1.ajude.models.Usuario;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Optional;

@Service
public class JWTService {

    private UsuarioServices usuariosService;
    private final String TOKEN_KEY = "login do ajude";

    public JWTService(UsuarioServices usuarioServices) {
        super();
        this.usuariosService = usuarioServices;
    }

    public String geraToken(String email) {
        return Jwts.builder().setSubject(email)
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)).compact(); //5 min
    }

    public boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
        String subject = getSujeitoDoToken(authorizationHeader);

        Optional<Usuario> optUsuario = usuariosService.getUsuario(subject);
        return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
    }

    public String getSujeitoDoToken(String authorizationHeader) throws ServletException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ServletException("Token inexistente ou mal formatado!");
        }

        // Extraindo Token do Cabecalho.
        String token = authorizationHeader.substring(v1.ajude.filters.FiltroToken.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey("login do ajude").parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new ServletException("Token invalido ou expirado!");
        }
        return subject;
    }

    public boolean usuarioExiste(String authorizationHeader) throws ServletException {
        String subject = getSujeitoDoToken(authorizationHeader);

        return usuariosService.exist(subject);
    }
}

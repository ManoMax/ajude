package v1.ajude.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v1.ajude.models.Campanha;
import v1.ajude.models.Comentario;
import v1.ajude.models.Doacao;
import v1.ajude.models.Resposta;
import v1.ajude.services.CampanhaServices;
import v1.ajude.services.JWTService;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campanha")
public class CampanhaController {

    @Autowired
    private CampanhaServices campanhaServices;
    @Autowired
    private JWTService jwtService;

    public CampanhaController(CampanhaServices campanhaServices, JWTService jwtService) {
        super();
        this.campanhaServices = campanhaServices;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    public ResponseEntity<Campanha> criarCampanha(@RequestBody Campanha campanha, @RequestHeader("Authorization") String header) {
        try {
            if(jwtService.usuarioExiste(header)) {
                String email = jwtService.getSujeitoDoToken(header);
                return new ResponseEntity<Campanha>(campanhaServices.criarCampanha(email, campanha), HttpStatus.OK);
            }
        }catch(ServletException e){
            return new ResponseEntity<Campanha>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping("/{urlCampanha}")
    public ResponseEntity<Campanha> getCampanha(@PathVariable("urlCampanha") String urlCampanha, @RequestHeader("Authorization") String header) {
        try {
            if (jwtService.usuarioExiste(header)) {
                Optional<Campanha> campanha = campanhaServices.getCampanha(urlCampanha);
                if (campanha.isPresent()) {
                    return new ResponseEntity<Campanha>(campanha.get(), HttpStatus.OK);
                }
                return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
            }
        } catch (ServletException e) {
            return new ResponseEntity<Campanha>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);

    }

    @RequestMapping("/{urlCampanha}/status")
    public ResponseEntity<Campanha> encerraCampanha(@PathVariable String urlCampanha, @RequestHeader("Authorization") String header) {
        try {
            if(jwtService.usuarioExiste(header)) {
                Optional<Campanha> campanha = campanhaServices.getCampanha(urlCampanha);
                if (campanha.isPresent()) {
                    return new ResponseEntity<Campanha>(campanhaServices.encerraCampanha(campanha.get()), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
                }
            }
        }catch(ServletException e){
            return new ResponseEntity<Campanha>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/{urlCampanha}/comentario")
    public ResponseEntity<Comentario> addComentario(@PathVariable String urlCampanha, @RequestBody Comentario comentario, @RequestHeader("Authorization") String header) {
        try {
            if(jwtService.usuarioExiste(header)) {
                Optional<Campanha> campanha = campanhaServices.getCampanha(urlCampanha);
                if (campanha.isPresent()) {
                    String email = jwtService.getSujeitoDoToken(header);
                    return new ResponseEntity<Comentario>(campanhaServices.addComentario(campanha.get(), comentario, email), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
                }
            }
        }catch(ServletException e){
            return new ResponseEntity<Comentario>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Comentario>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/{urlCampanha}/apagarComentario/{idComentario}")
    public ResponseEntity<Comentario> apagarComentario(@PathVariable String urlCampanha, @PathVariable Long idComentario, @RequestHeader("Authorization") String header) {
        try {
            if(jwtService.usuarioExiste(header)) {
                Optional<Campanha> campanha = campanhaServices.getCampanha(urlCampanha);
                if (campanha.isPresent()) {
                    String email = jwtService.getSujeitoDoToken(header);
                    return new ResponseEntity<Comentario>(campanhaServices.apagarComentario(campanha.get(), idComentario, email), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
                }
            }
        }catch(ServletException e){
            return new ResponseEntity<Comentario>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Comentario>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/{urlCampanha}/comentario/{idComentario}")
    public ResponseEntity<Comentario> addResposta(@PathVariable String urlCampanha, @PathVariable Long idComentario, @RequestBody Resposta resposta, @RequestHeader("Authorization") String header) {
        try {
            if(jwtService.usuarioExiste(header)) {
                Optional<Campanha> campanha = campanhaServices.getCampanha(urlCampanha);
                if (campanha.isPresent()) {
                    String email = jwtService.getSujeitoDoToken(header);
                    return new ResponseEntity<Comentario>(campanhaServices.addResposta(campanha.get(), idComentario, resposta, email), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
                }
            }
        }catch(ServletException e){
            return new ResponseEntity<Comentario>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Comentario>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/{urlCampanha}/comentario/{idComentario}/apagarResposta/{idResposta}")
    public ResponseEntity<Comentario> apagarResposta(@PathVariable String urlCampanha, @PathVariable Long idComentario, @PathVariable Long idResposta, @RequestHeader("Authorization") String header) {
        try {
            if(jwtService.usuarioExiste(header)) {
                Optional<Campanha> campanha = campanhaServices.getCampanha(urlCampanha);
                if (campanha.isPresent()) {
                    String email = jwtService.getSujeitoDoToken(header);
                    return new ResponseEntity<Comentario>(campanhaServices.apagarResposta(campanha.get(), idComentario, idResposta, email), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
                }
            }
        }catch(ServletException e){
            return new ResponseEntity<Comentario>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Comentario>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping("/{urlCampanha}/like")
    public ResponseEntity<Campanha> addLike(@PathVariable("urlCampanha") String urlCampanha, @RequestHeader("Authorization") String header) {
        try {
            if(jwtService.usuarioExiste(header)) {
                Optional<Campanha> campanha = campanhaServices.getCampanha(urlCampanha);
                if (campanha.isPresent()) {
                    String email = jwtService.getSujeitoDoToken(header);
                    return new ResponseEntity<Campanha>(campanhaServices.addLike(campanha.get(), email), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
                }
            }
        }catch(ServletException e){
            return new ResponseEntity<Campanha>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/busca/{substring}")
    public ResponseEntity<List<Campanha>> getCampanhasPorSubString(@PathVariable String substring) {
        return new ResponseEntity<List<Campanha>>(campanhaServices.getCampanhas(substring), HttpStatus.OK);
    }

    @PostMapping("/{urlCampanha}/doacao")
    public ResponseEntity<Campanha> doarCampanha(@PathVariable("urlCampanha") String urlCampanha, @RequestBody Doacao doacao, @RequestHeader("Authorization") String header) {
        try {
            if(jwtService.usuarioExiste(header)) {
                Optional<Campanha> campanha = campanhaServices.getCampanha(urlCampanha);
                if (campanha.isPresent()) {
                    String email = jwtService.getSujeitoDoToken(header);
                    return new ResponseEntity<Campanha>(campanhaServices.doarCampanha(campanha.get(), doacao, email), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
                }
            }
        }catch(ServletException e){
            return new ResponseEntity<Campanha>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/campanhasLike")
    public ResponseEntity<List<Campanha>> getCampanhasLike() {
        return new ResponseEntity<List<Campanha>>(this.campanhaServices.getCampanhasLike(), HttpStatus.OK);
    }
    @GetMapping("/campanhasData")
    public ResponseEntity<List<Campanha>> getCampanhasData() {
        return new ResponseEntity<List<Campanha>>(this.campanhaServices.getCampanhasData(), HttpStatus.OK);
    }
    @GetMapping("/campanhasQuantia")
    public ResponseEntity<List<Campanha>> getCampanhasQuantia() {
        return new ResponseEntity<List<Campanha>>(this.campanhaServices.getCampanhasQuantia(), HttpStatus.OK);
    }

}
